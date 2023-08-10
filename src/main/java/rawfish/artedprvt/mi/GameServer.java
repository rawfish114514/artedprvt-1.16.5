package rawfish.artedprvt.mi;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SChatPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.loading.FMLCommonLaunchHandler;
import net.minecraftforge.fml.loading.FMLServerLaunchProvider;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import rawfish.artedprvt.core.ProgramUsable;
import rawfish.artedprvt.core.ScriptObject;
import rawfish.artedprvt.core.SideUsable;
import rawfish.artedprvt.core.Sides;

import java.util.List;

@SideUsable(Sides.SERVER)
@ProgramUsable
public class GameServer implements ScriptObject {
    public MinecraftServer minecraft;

    @ProgramUsable
    public GameServer(){
        up();
        minecraft = ServerLifecycleHooks.getCurrentServer();
    }

    @ProgramUsable
    public ServerWorld getWorld(int i){
        Iterable<ServerWorld> iterable=minecraft.getAllLevels();
        int n=0;
        for(ServerWorld serverWorld:iterable){
            if(n++==i){
                return serverWorld;
            }
        }
        return null;
    }

    @ProgramUsable
    public ServerPlayerEntity getPlayer(ServerWorld world, String name){
        for (ServerPlayerEntity player:world.players()) {
            if(player.getGameProfile().getName().equals(name)){
                return player;
            }
        }
        return null;
    }

    @ProgramUsable
    public void sendChat(ServerWorld world,String chat){
        SChatPacket packetChat=null;
        for(ServerPlayerEntity player:world.players()){
            packetChat=new SChatPacket(new StringTextComponent(chat), ChatType.CHAT,player.getUUID());
            player.connection.send(packetChat);
        }
    }

    @Override
    public void onClose() {
        minecraft =null;
    }
}
