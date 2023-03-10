package rawfish.artedprvt.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.MessageArgument;
import rawfish.artedprvt.command.adapter.CommandException;
import rawfish.artedprvt.command.adapter.WrongUsageException;
import rawfish.artedprvt.script.ScriptProcess;

import java.util.ArrayList;
import java.util.List;

public class CommandScript extends CommandIs {
    public CommandScript(String nameIn){
        name=nameIn;
    }

    public final String name;
    @Override
    public String getCommandName() {
        return name;
    }

    @Override
    public String getCommandUsage(CommandSource sender) {
        return "commands.script.usage";
    }

    @Override
    public void processCommand(CommandSource sender, String[] argsIn) throws CommandException {
        if(argsIn.length<1){
            throw new WrongUsageException(sender,"commands.script.usage");
        }

        //去空参数
        List<String> slist=new ArrayList<>();
        for(String arg:argsIn){
            if(!arg.equals("")){
                slist.add(arg);
            }
        }


        int packindex=calpack(slist);
        if(packindex==-1){
            //没有包名
            throw new CommandException(sender,"找不到包名 请检查参数");
        }

        List<String> sargs=sargs(slist,packindex);//命令参数
        for(String sarg:sargs){
            if(!containsSargs(sarg)){
                throw new CommandException(sender,"无效参数: "+sarg);
            }
            if(repeatSargs(sargs,sarg)){
                throw new CommandException(sender,"重复参数: "+sarg);
            }
        }

        String pack=pack(slist,packindex);//脚本包名
        List<String> args=args(slist,packindex);//脚本参数
        String dir=System.getProperties().get("user.dir").toString()+"/artedprvt/src";//项目目录
        ScriptProcess script=new ScriptProcess(sender,getCommandName(),dir,sargs,pack,args);

        if(script.getRet()==0) {
            script.start();
        }
    }

    //计算包名索引
    public int calpack(List<String> argsIn){
        //每次都匹配命令参数直到匹配到非命令参数 再区分包名和脚本参数
        for(int i=0;i<argsIn.size();i++){
            if(!isSarg(argsIn.get(i))){
                //包名索引
                return i;
            }
        }
        return -1;
    }
    //分配参数
    public List<String> sargs(List<String> argsIn,int packindex){
        //[0,p)
        List<String> list=new ArrayList<>();
        for(int i=0;i<packindex;i++){
            list.add(argsIn.get(i));
        }
        return list;
    }
    public String pack(List<String> argsIn,int packindex){
        //[p]
        return argsIn.get(packindex);
    }
    public List<String> args(List<String> argsIn,int packindex){
        //[p+1,l)
        List<String> list=new ArrayList<>();
        for(int i=0;i<argsIn.size()-packindex-1;i++){
            list.add(argsIn.get(i+packindex+1));
        }
        return list;
    }


    //判断命令参数存在
    public boolean containsSargs(String sarg){
        return ScriptProcess.sargList.contains(sarg);
    }

    //判断命令参数重复
    public boolean repeatSargs(List<String> sargs,String sarg){
        int n=0;
        for(String s:sargs){
            if(s.equals(sarg)){
                n++;
            }
        }
        return n>1;
    }

    protected static String sargMatcher="-\\w+";
    //判断命令参数合法
    public boolean isSarg(String arg){
        return arg.matches(sargMatcher);
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public String getArgumentName(){
        return "[sargs...] <pack> [args...]";
    }
}
