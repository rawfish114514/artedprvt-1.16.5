package rawfish.artedprvt.mi;


import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import rawfish.artedprvt.core.ProgramUsable;
import rawfish.artedprvt.core.SideUsable;
import rawfish.artedprvt.core.Sides;
import rawfish.artedprvt.event.InputStringEvent;

/**
 * 常用的事件
 */
@SideUsable(Sides.ALL)
@ProgramUsable
public enum Events {
    /**
     * 服务端刻事件
     */
    @ProgramUsable
    @EventTarget(TickEvent.ServerTickEvent.class)
    tick,

    /**
     * 点击事件
     */
    @ProgramUsable
    @EventTarget(PlayerInteractEvent.class)
    click,

    /**
     * 使用物品事件
     */
    @ProgramUsable
    @EventTarget(LivingEntityUseItemEvent.Finish.class)
    use,

    /**
     * 实体加入世界事件
     */
    @ProgramUsable
    @EventTarget(EntityJoinWorldEvent.class)
    join,

    /**
     * 输入字符串事件
     */
    @ProgramUsable
    @EventTarget(InputStringEvent.class)
    input,

    /**
     * 工具提示事件
     */
    @ProgramUsable
    @EventTarget(ItemTooltipEvent.class)
    tooltip,

    /**
     * 方块放置事件
     */
    @ProgramUsable
    @EventTarget(BlockEvent.EntityPlaceEvent.class)
    bl_place,

    /**
     * 方块破坏事件
     */
    @ProgramUsable
    @EventTarget(BlockEvent.BreakEvent.class)
    bl_break,

    //side only Client

    /**
     * 客户端刻事件
     */
    @ProgramUsable
    @EventTarget(TickEvent.ClientTickEvent.class)
    c_tick,

    /**
     * 渲染刻事件
     */
    @ProgramUsable
    @EventTarget(TickEvent.RenderTickEvent.class)
    r_tick,

    /**
     * 客户端聊天事件
     */
    @ProgramUsable
    @EventTarget(ClientChatReceivedEvent.class)
    c_chat,
}
