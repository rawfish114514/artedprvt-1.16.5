package rawfish.artedprvt.common;

import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MinecraftGame;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rawfish.artedprvt.core.DebugInfo;

public class EventLoader
{
    public static final IEventBus EVENT_BUS = MinecraftForge.EVENT_BUS;
    public EventLoader()
    {
        EVENT_BUS.register(this);
    }

    RenderGameOverlayEvent b;
    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onRenderOverlay(RenderGameOverlayEvent.Text event){
        event.getRight().addAll(DebugInfo.call(Minecraft.getInstance().options.renderDebug));
    }

    @SubscribeEvent(priority= EventPriority.HIGHEST)
    public void onUnload(WorldEvent.Unload event){
        System.out.println("卸载: "+event.getWorld().toString());
    }

    public static void post(Event event){
        EVENT_BUS.post(event);
    }
}