package rawfish.artedprvt.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraftforge.eventbus.api.Event;
import rawfish.artedprvt.common.EventLoader;
import rawfish.artedprvt.event.InputStringEvent;

public class CommandIn extends CommandIs {
    public CommandIn(String nameIn){
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
        return "commands.in.usage";
    }

    @Override
    public void processCommand(CommandSource sender, String[] args) throws CommandException
    {
        String string=String.join(" ",args);
        Event e=new InputStringEvent(string);
        EventLoader.EVENT_BUS.post(e);
    }
    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public String getArgumentName(){
        return "input";
    }
}