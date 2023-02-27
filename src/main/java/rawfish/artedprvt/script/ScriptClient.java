package rawfish.artedprvt.script;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.client.CChatMessagePacket;
import net.minecraft.network.play.client.CUseEntityPacket;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Scriptable;

import java.util.List;

/**
 * 提供客户端专用功能
 */
public class ScriptClient {
    public Minecraft minecraft;
    public NetworkManager manager;
    public ScriptClient(){
        minecraft=Minecraft.getInstance();
        manager=minecraft.getConnection().getConnection();

    }

    /**
     * 发送消息数据包
     * @param message 消息
     */
    public void sendChat(String message) {
        manager.send(new CChatMessagePacket(message));
    }

    /**
     * 发送交互实体数据包
     * @param entity 攻击的实体
     */
    public void sendUse(Entity entity){
        manager.send(new CUseEntityPacket(entity,false));
    }

    /**
     * 获取所有实体
     * @return
     */
    public List<Entity> getAllEntity(){
        return Minecraft.getInstance().level.getEntities(null,null);
    }

    /**
     * 获取自己实体
     * @return
     */
    public ClientPlayerEntity getThisPlayer(){
        return minecraft.player;
    }

}
