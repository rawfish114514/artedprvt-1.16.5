package rawfish.artedprvt.common;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import rawfish.artedprvt.command.CommandLoader;
import rawfish.artedprvt.core.ProcessController;
import rawfish.artedprvt.core.engine.ServiceEngines;
import rawfish.artedprvt.core.rhino.ClassCollection;
import rawfish.artedprvt.core.rhino.McpToSrgString;
import rawfish.artedprvt.id.Local;
import rawfish.artedprvt.mi.group.ClassGroupLoader;

public class CommonProxy
{
    public void setup(FMLCommonSetupEvent event){
        new EventLoader();
        ServiceEngines.init();
        ProcessController.init();
        ClassGroupLoader.reg();
        MinecraftForge.EVENT_BUS.register(new CommandLoader());
        ClassCollection.load(McpToSrgString.getMcpToSrgString());
        Local.load();
    }
}