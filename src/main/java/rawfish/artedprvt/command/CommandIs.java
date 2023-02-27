package rawfish.artedprvt.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.MessageArgument;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;

public abstract class CommandIs {

    public String getName() {
        return getCommandName();
    }

    public String getUsage(CommandSource sender){
        return getCommandUsage(sender);
    }
    public void execute(MinecraftServer server, CommandSource sender, String[] args) throws CommandException{
        processCommand(sender,args);
    }

    public abstract String getCommandName();

    public abstract String getCommandUsage(CommandSource sender);

    public abstract void processCommand(CommandSource sender, String[] args) throws CommandException;

    public abstract int getRequiredPermissionLevel();

    public abstract String getArgumentName();

    public void register(CommandDispatcher<CommandSource> dispatcher) {
        RequiredArgumentBuilder<CommandSource, MessageArgument.Message>
                argumentSarg=Commands.argument(getArgumentName(), MessageArgument.message())
                .executes((commandSource)->{
                    String [] elem=commandSource.getInput().split(" ");
                    String[] arguments=new String[elem.length-1];
                    System.arraycopy(elem, 1, arguments, 0, arguments.length);
                    processCommand(commandSource.getSource(),arguments);
                    return 0;
                });
        LiteralArgumentBuilder<CommandSource>
                command=Commands.literal(getCommandName())
                .requires((commandSource)->{return commandSource.hasPermission(getRequiredPermissionLevel());})
                .then(argumentSarg);

        dispatcher.register(command);
    }
}
