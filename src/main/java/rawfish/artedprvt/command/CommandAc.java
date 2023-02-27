package rawfish.artedprvt.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.MessageArgument;
import net.minecraft.util.text.StringTextComponent;
import rawfish.artedprvt.command.adapter.WrongUsageException;
import rawfish.artedprvt.script.ScriptConst;

public class CommandAc extends CommandIs {
    @Override
    public void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource>
                command=Commands.literal(getCommandName())
                .requires((commandSource)->{return commandSource.hasPermission(getRequiredPermissionLevel());})
                        .executes((commandSource)->{
                            processCommand(commandSource.getSource(),new String[0]);
                            return 0;
                        });
        dispatcher.register(command);
    }

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
            throw new WrongUsageException(sender,"commands.ac.usage");
        }
        ScriptConst.chat=!ScriptConst.chat;
        sender.sendSuccess(new StringTextComponent("Chat模式: "+ScriptConst.chat),false);
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
