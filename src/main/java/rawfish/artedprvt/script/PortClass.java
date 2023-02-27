package rawfish.artedprvt.script;

import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeJavaClass;
import org.mozilla.javascript.ScriptableObject;
import rawfish.artedprvt.script.js.ClassLevel;
import rawfish.artedprvt.script.mi.*;

import java.util.HashMap;
import java.util.Map;

public class PortClass {
    protected Map<String,NativeJavaClass> classes;//类简单名:本地java类
    protected ScriptProcess pro;
    protected Context rhino;
    protected ScriptableObject scope;
    public PortClass(ScriptProcess proIn){
        pro=proIn;
        classes=new HashMap<>();

        rhino=pro.rhino;
        scope=rhino.initStandardObjects();
        //混淆化
        scope.put(ClassLevel.varRc,scope,String.valueOf(pro.getValueRc()));

        add();
    }

    private void add(){
        //mi.*
        put(LifeDepend.class);
        put(Events.class);
        put(EventListener.class);
        put(WorldManager.class);
        
        //net.minecraft
        put(Items.class);
        put(Blocks.class);
        put(BlockPos.class);

        //test
    }
    private void put(Class clas){
        classes.put(clas.getSimpleName(),new NativeJavaClass(scope,clas));
    }
}
