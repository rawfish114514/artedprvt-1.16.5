package rawfish.artedprvt.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.server.MinecraftServer;
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


    public void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal(getCommandName())
                .requires((commandSource)->{return commandSource.hasPermission(getRequiredPermissionLevel());})
                .executes((commandSource)->{
                    processCommand((CommandSource) commandSource.getSource(),commandSource.getInput().split(" "));
                    return 0;
                })
        );
    }
}
