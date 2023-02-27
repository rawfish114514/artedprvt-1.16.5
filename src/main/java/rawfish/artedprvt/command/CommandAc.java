package rawfish.artedprvt.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;
import rawfish.artedprvt.command.adapter.WrongUsageException;
import rawfish.artedprvt.script.ScriptConst;

public class CommandAc extends CommandIs {
    public CommandAc(String nameIn){
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
        return "commands.ac.usage";
    }

    @Override
    public void processCommand(CommandSource sender, String[] args) throws CommandException
    {
        if (args.length > 0) {
            throw new WrongUsageException("commands.ac.usage");
        }
        ScriptConst.chat=!ScriptConst.chat;
        sender.sendSuccess(new StringTextComponent("Chat模式: "+ScriptConst.chat),false);
    }
    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
}
