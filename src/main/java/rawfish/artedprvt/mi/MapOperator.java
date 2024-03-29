package rawfish.artedprvt.mi;

import net.minecraft.world.World;
import rawfish.artedprvt.core.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 地图操作
 */
@SideUsable(Sides.ALL)
@ProgramUsable
public class MapOperator implements ScriptObject {
    private ScriptProcess process;

    /**
     * 世界
     */
    public World world;

    public List<MapGraphics> graphicsList;

    @ProgramUsable
    public MapOperator(World world){
        up();
        this.world=world;
        graphicsList=new ArrayList<>();
    }

    /**
     * 创建并返回{@link MapGraphics}对象
     * @return
     */
    @ProgramUsable
    public MapGraphics getGraphics(){
        MapGraphics graphics=new MapGraphics(this);
        graphicsList.add(graphics);
        return graphics;
    }

    /**
     * 计算操作方块数
     * @return
     */
    @ProgramUsable
    public int getBlockOper(){
        int blockOper=0;
        for(MapGraphics graphics:graphicsList){
            blockOper+=graphics.getDrawCount();
        }
        return blockOper;
    }

    @Override
    public void onClose() {
        int blockOper=getBlockOper();
        if(blockOper>0){
            if(process!=null){
                process.getScriptSystem().print(ScriptSystem.CHAT,"§d任务结束 操作方块: "+blockOper);
            }
        }
        world=null;
    }
}

