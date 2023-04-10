package rawfish.artedprvt.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import rawfish.artedprvt.command.adapter.CommandException;
import rawfish.artedprvt.id.FormatCode;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CommandWorkspace extends CommandIs {
    @Override
    public void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource>
                command= Commands.literal(getCommandName())
                .requires((commandSource)->{return commandSource.hasPermission(getRequiredPermissionLevel());})
                .executes((commandSource)->{
                    processCommand(commandSource.getSource(),new String[0]);
                    return 0;
                });
        dispatcher.register(command);
    }

    public CommandWorkspace(String nameIn){
        name=nameIn;
    }

    public final String name;
    @Override
    public String getCommandName() {
        return name;
    }

    @Override
    public String getCommandUsage(CommandSource sender)
    {
        return "commands.workspace.usage";
    }

    @Override
    public void processCommand(CommandSource sender, String[] args) throws CommandException
    {
        String dir=System.getProperties().get("user.dir").toString()+"/artedprvt";//项目目录
        File file;
        file=new File(dir);
        if(file.exists()){
            throw new CommandException(sender,"目录/artedprvt已经存在");
        }
        file.mkdir();

        file=new File(dir+"/lib");
        file.mkdir();

        file=new File(dir+"/src");
        file.mkdir();

        file=new File(dir+"/src/script");
        file.mkdir();

        file=new File(dir+"/src/config.json");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Writer writer=new FileWriter(file);
            writer.write(fileConfig());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        file=new File(dir+"/src/script/main.js");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Writer writer=new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            writer.write(fileMainjs());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sender.sendSuccess(new StringTextComponent(FormatCode.COLOR_2+"创建成功: "+dir),false);
    }
    public String fileConfig(){
        return "{\n" +
                "  \"options\": [\n" +
                "    \"-pm\",\"-al\",\"-rc\"\n" +
                "  ],\n" +
                "  \"pkg\": {\n" +
                "    \"pack\": \"main\"\n" +
                "  }\n" +
                "}";
    }
    public String fileMainjs(){
        return "//Hello Minecraft!\n" +
                "//Created by Rawfishc on 2023/4/10\n" +
                "\n" +
                "var r=giveBook();\n" +
                "drawLogo();\n" +
                "\n" +
                "function drawLogo(){\n" +
                "    var a=print;\n" +
                "    a(\"§0█████§4███§0█████████\");\n" +
                "    a(\"§0█████§4██§3█§0█§0██████§f██\");\n" +
                "    a(\"§0████§4██§0█§3██§0██████§f██\");\n" +
                "    a(\"§0████§4██§0█§3██§0██████§f██\");\n" +
                "    a(\"§0███§4██§0███§3██§0███████\");\n" +
                "    a(\"§0███§4██§0███§3██§0███████\");\n" +
                "    a(\"§0███§4██§0███§3██§0███████\");\n" +
                "    a(\"§0██§4██§0█████§3██§0██████\");\n" +
                "    a(\"§0██§4███████§3██§0████§f██\");\n" +
                "    a(\"§0█§4█████████§3██§0███§f██\");\n" +
                "    a(\"§0█§4██§0███████§3██§0███§f██\");\n" +
                "    a(\"§0█§4██§0███████§3██§0████§f█\");\n" +
                "    a(\"§0§4██§0█████████§3██§0██§f█§0█\");\n" +
                "}\n" +
                "\n" +
                "function giveBook(){\n" +
                "    import(\"-net.minecraft.client.Minecraft\");\n" +
                "    import(\"-net.minecraft.item.ItemStack\");\n" +
                "    import(\"-net.minecraft.nbt.CompoundNBT\");\n" +
                "    import(\"-net.minecraft.nbt.ListNBT\");\n" +
                "    import(\"-net.minecraft.nbt.StringNBT\");\n" +
                "    import(\"-net.minecraft.util.IItemProvider\");\n" +
                "\n" +
                "    var entityPlayer=Minecraft.getInstance().getSingleplayerServer().getPlayerList().getPlayerByName(getThisPlayer().getDisplayName().getString());\n" +
                "    var nbt=new CompoundNBT();\n" +
                "        var pagesTag=buildPageArray();\n" +
                "        var authorTag=StringNBT.valueOf(\"\\u00a73Rawfishc\\u00a77\");\n" +
                "        var titleTag=StringNBT.valueOf(\"\\u00a7lHello Minecraft\");\n" +
                "    nbt.put(\"pages\",pagesTag);\n" +
                "    nbt.put(\"author\",authorTag);\n" +
                "    nbt.put(\"title\",titleTag);\n" +
                "    var itemStack = new ItemStack(new IItemProvider({func_199767_j:function(){return Items.WRITTEN_BOOK}}));\n" +
                "    itemStack.setTag(nbt);\n" +
                "    var flag=entityPlayer.inventory.add(itemStack);\n" +
                "\n" +
                "    function buildPageArray(){\n" +
                "        var tag=new ListNBT();\n" +
                "        var pageMethods=[page_0,page_1];\n" +
                "        for(var i=0;i<pageMethods.length;i++){\n" +
                "            var page=pageMethods[i]();\n" +
                "            tag.add(StringNBT.valueOf(page));\n" +
                "        }\n" +
                "        return tag;\n" +
                "    }\n" +
                "    function page_0(){\n" +
                "        return \"\\\"\\u00a7lAPF(Artedprvt Frame)\\u00a7r\\n\\nAPF是用于Minecraft的Js运行环境。\\n\\n目的是在游戏中动态的运行代码。\\\"\";\n" +
                "    }\n" +
                "    function page_1(){\n" +
                "        return \"\\\"对于只使用脚本的玩家来说，没有任何门槛。\\n\\n如果你有Js编程基础可以编写脚本实现一些简单的功能。\"+\n" +
                "            \"\\n\\n要实现稍微复杂的功能可能需要你有较高的Js和Java水平以及Mod开发经验。\\n\\n降低开发门槛也是APF的主要目标！\\\"\";\n" +
                "    }\n" +
                "    return Math.log(1919810)/Math.log(114514);\n" +
                "}";
    }
    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public String getArgumentName(){
        return "null";
    }
}