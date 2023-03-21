package rawfish.artedprvt;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import rawfish.artedprvt.command.CommandLoader;

import net.minecraftforge.fml.common.Mod;
import rawfish.artedprvt.script.ScriptProcess;
import rawfish.artedprvt.script.js.ClassCollection;
import rawfish.artedprvt.script.js.McpToSrgString;


@Mod(Artedprvt.MODID)
public class Artedprvt
{
    public static final String MODID="artedprvt";
    public static final String NAME="ArtedPrvt Frame";

    public Artedprvt(){

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }


    private void setup(final FMLCommonSetupEvent event)
    {
        System.out.println("-----common");

        MinecraftForge.EVENT_BUS.register(new CommandLoader());
        ScriptProcess.initSargs();
        ClassCollection.load(McpToSrgString.getMcpToSrgString());
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
        System.out.println("-----client");

        MinecraftForge.EVENT_BUS.register(new CommandLoader());
        ScriptProcess.initSargs();
        ClassCollection.load(McpToSrgString.getMcpToSrgString());
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        System.out.println("-----InterModEnqueueEvent");
    }

    private void processIMC(final InterModProcessEvent event)
    {
        System.out.println("-----InterModProcessEvent");
    }
}