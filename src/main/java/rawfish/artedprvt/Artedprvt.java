package rawfish.artedprvt;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rawfish.artedprvt.client.ClientProxy;
import rawfish.artedprvt.common.CommonProxy;

import net.minecraftforge.fml.common.Mod;


@Mod(Artedprvt.MODID)
public class Artedprvt
{
    public static CommonProxy proxy;
    public static ClientProxy clientProxy;
    public static final String MODID="artedprvt";
    public static final String NAME="Artedprvt Frame";

    public static Artedprvt instance;

    public static Logger logger;

    public Artedprvt(){
        instance=this;
        init();

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
        logger=LogManager.getLogger(MODID);
        proxy=new CommonProxy();
        proxy.setup(event);
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
        clientProxy=new ClientProxy();
        clientProxy.setup(event);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {

    }

    private void processIMC(final InterModProcessEvent event)
    {

    }


    public String getDescription(){
        return "Artedprvt Frame 是专为 Minecraft 设计的脚本运行框架，它在游戏中随时运行单个脚本文件或apkg文件。" +
                "\n\n作者 ↓\nhttps://space.bilibili.com/455906194";
    }

    public boolean isNotDevelopment() {
        return isNotDevelopment;
    }

    private boolean isNotDevelopment;

    public boolean isHasClientSide() {
        return hasClientSide;
    }

    private boolean hasClientSide;

    public void init() {
        try {
            MinecraftServer.class.getDeclaredField("mcServer");
            isNotDevelopment=false;
        } catch (NoSuchFieldException e) {
            isNotDevelopment=true;
        }

        try {
            Class.forName("net.minecraft.client.Minecraft");
            hasClientSide=true;
        } catch (ClassNotFoundException e) {
            hasClientSide=false;
        }
        System.out.println("Artedprvt.init.ind");
        System.out.println("开发环境: d:"+!isNotDevelopment+" c:"+hasClientSide);
    }
}