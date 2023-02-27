package rawfish.artedprvt.command;


import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.data.CommandsReport;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommandLoader
{
    public CommandLoader()
    {

    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent  event){
        CommandDispatcher<CommandSource> dispatcher=event.getDispatcher();
        CommandApf commandApf=new CommandApf("artedprvt");

        registerCommand(dispatcher,commandApf);
        registerCommand(dispatcher,new CommandApf("apf"));

        for(CommandIs c:commandApf.cmdm.values()){
            registerCommand(dispatcher,c);
        }
    }

    public void registerCommand(CommandDispatcher<CommandSource> dispatcher,CommandIs c){
        System.out.println("-----CommandRegister: "+c.getCommandName());
        c.register(dispatcher);
    }

}