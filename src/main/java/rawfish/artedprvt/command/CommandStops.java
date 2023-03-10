package rawfish.artedprvt.command;

import net.minecraft.command.CommandSource;
import rawfish.artedprvt.command.adapter.CommandException;
import rawfish.artedprvt.command.adapter.WrongUsageException;
import rawfish.artedprvt.script.ScriptProcess;

public class CommandStops extends CommandIs {
    public CommandStops(String nameIn){
        name=nameIn;
    }

    public final String name;
    @Override
    public String getCommandName() {
        return name;
    }

    @Override
    public String getCommandUsage(CommandSource sender) {
        return "commands.stops.usage";
    }

    @Override
    public void processCommand(CommandSource sender, String[] args) throws CommandException {
        ScriptProcess[] pros=new ScriptProcess[ScriptProcess.proList.size()];
        if(pros.length==0){
            throw new WrongUsageException(sender,"commands.stops.usage");
        }
        if(args.length>1){
            throw new WrongUsageException(sender,"commands.stops.usage");
        }
        ScriptProcess.proList.toArray(pros);

        if(args.length==1){
            String sp=args[0];
            boolean nisp=true;
            for (ScriptProcess pro : pros) {
                if(sp.equals(pro.getPack())||sp.equals(String.valueOf(pro.getId()))){
                    pro.stop(null);
                    nisp=false;
                }
            }
            if(nisp){
                throw new CommandException(sender,"找不到进程 请检查进程名或进程id");
            }
        }else {
            for (ScriptProcess pro : pros) {
                pro.stop(null);
            }
        }
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public String getArgumentName(){
        return "name|id";
    }
}
