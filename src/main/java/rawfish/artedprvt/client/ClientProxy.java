package rawfish.artedprvt.client;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import rawfish.artedprvt.command.CommandLoader;

public class ClientProxy
{
    public void setup(FMLClientSetupEvent event){
        MinecraftForge.EVENT_BUS.register(new CommandLoader());
    }
}