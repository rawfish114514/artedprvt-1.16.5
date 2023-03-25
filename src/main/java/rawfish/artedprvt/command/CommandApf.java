package rawfish.artedprvt.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import rawfish.artedprvt.command.adapter.WrongUsageException;

import java.util.HashMap;
import java.util.Map;

public class CommandApf extends CommandIs {
    public CommandApf(String nameIn){
        name=nameIn;
        cmdm=new HashMap<>();
        cmdm.put("ac",new CommandAc("ac"));
        cmdm.put("ad",new CommandAd("ad"));
        cmdm.put("apkg",new CommandApkg("apkg"));
        cmdm.put("in",new CommandIn("in"));
        cmdm.put("pros",new CommandPros("pros"));
        cmdm.put("script",new CommandScript("script"));
        cmdm.put("stops",new CommandStops("stops"));
        cmdm.put("workspace",new CommandWorkspace("workspace"));
        cmdm.put("stt",new CommandStt("stt"));
    }

    public Map<String,CommandIs> cmdm;

    public final String name;
    @Override
    public String getCommandName() {
        return name;
    }

    @Override
    public String getCommandUsage(CommandSource sender)
    {
        return "commands.apf.usage";
    }

    @Override
    public void processCommand(CommandSource sender, String[] args) throws CommandException
    {
        throw new WrongUsageException(sender,"commands.apf.usage");
    }
    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public String getArgumentName(){
        return "key";
    }
}
