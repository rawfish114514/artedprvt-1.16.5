package rawfish.artedprvt.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;

public class CommandStt extends CommandIs {
    public CommandScript commandScript=new CommandScript("");
    public CommandStt(String nameIn){
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
        return "commands.stt.usage";
    }

    @Override
    public void processCommand(CommandSource sender, String[] args) throws CommandException
    {
        commandScript.processCommand(sender,new String[]{"main"});
    }
    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public String getArgumentName() {
        return "null";
    }
}