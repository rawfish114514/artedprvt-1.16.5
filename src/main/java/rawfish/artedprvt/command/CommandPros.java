package rawfish.artedprvt.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.event.HoverEvent;
import rawfish.artedprvt.command.adapter.CommandException;
import rawfish.artedprvt.command.adapter.WrongUsageException;
import rawfish.artedprvt.script.ScriptProcess;


public class CommandPros extends CommandIs {
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

    public CommandPros(String nameIn){
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
        return "commands.pros.usage";
    }

    @Override
    public void processCommand(CommandSource sender, String[] args) throws CommandException
    {
        if (args.length > 0) {
            throw new WrongUsageException(sender,"commands.pros.usage");
        }
        if(ScriptProcess.proList.size()==0){
            throw new WrongUsageException(sender,"commands.pros.usage");
        }
        for(ScriptProcess pro:ScriptProcess.proList){
            StringTextComponent chat=new StringTextComponent(String.format("process: %s pid: %s",pro.getPack(),pro.getId()));
            int ret=pro.getRet();
            if(ret!=0&&ret!=1){
                String hover=pro.getStatistics();
                chat.setStyle(Style.EMPTY.withHoverEvent(
                        new HoverEvent(HoverEvent.Action.SHOW_TEXT, new StringTextComponent(String.valueOf(hover)))));
            }
            sender.sendSuccess(chat,false);
        }
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
