package rawfish.artedprvt.script.mi;


import net.minecraftforge.eventbus.api.Event;

/**
 * 事件回调函数
 * 运行时动态代理实现
 */
public interface EventFunction {
    /**
     * 函数接口方法
     * @param event 事件
     */
    public void run(Event event);
}
