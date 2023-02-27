package rawfish.artedprvt.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;
import rawfish.artedprvt.command.adapter.WrongUsageException;
import rawfish.artedprvt.script.ScriptConst;

public class CommandAd extends CommandIs {
    public CommandAd(String nameIn){
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
        return "commands.ad.usage";
    }

    @Override
    public void processCommand(CommandSource sender, String[] args) throws CommandException
    {
        if (args.length > 0) {
            throw new WrongUsageException("commands.ad.usage");
        }
        ScriptConst.debug=!ScriptConst.debug;
        sender.sendSuccess(new StringTextComponent("Debug模式: "+ScriptConst.debug),false);
    }
    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
}
