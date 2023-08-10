package rawfish.artedprvt.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rawfish.artedprvt.command.item.CommandApf;

public class CommandLoader {
    public CommandApf apf=new CommandApf("apf");
    public CommandApf artedprvt=new CommandApf("artedprvt");

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event){
        CommandDispatcher<CommandSource> dispatcher=event.getDispatcher();
        registerCommand(dispatcher,apf);
        registerCommand(dispatcher,artedprvt);

        for(Command c:apf.commandList){
            registerCommand(dispatcher,c);
        }
    }

    public void registerCommand(CommandDispatcher<CommandSource> dispatcher,Command c){
        c.register(dispatcher);
    }
}
