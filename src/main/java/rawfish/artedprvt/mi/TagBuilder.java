package rawfish.artedprvt.mi;

import net.minecraft.nbt.*;
import rawfish.artedprvt.core.ProgramUsable;
import rawfish.artedprvt.core.SideUsable;
import rawfish.artedprvt.core.Sides;

/**
 * 标签生成器
 */
@SideUsable(Sides.ALL)
@ProgramUsable
public class TagBuilder {
    @ProgramUsable
    public static CompoundTagBuilder compound(){
        return new CompoundTagBuilder();
    }

    @ProgramUsable
    public static ListTagBuilder list(){
        return new ListTagBuilder();
    }

    /**
     * 列表标签生成器
     */
    @ProgramUsable
    public static class ListTagBuilder{
        private ListNBT list;

        @ProgramUsable
        public ListTagBuilder(){
            list=new ListNBT();
        }

        @ProgramUsable
        public ListTagBuilder put(int key,INBT tag){
            list.set(key,tag);
            return this;
        }

        @ProgramUsable
        public ListTagBuilder addTag(INBT tag){
            list.add(tag);
            return this;
        }

        @ProgramUsable
        public ListTagBuilder setByte(int key,byte value){
            list.set(key,ByteNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public ListTagBuilder setShort(int key,short value){
            list.set(key,ShortNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public ListTagBuilder setInt(int key,int value){
            list.set(key,IntNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public ListTagBuilder setLong(int key,long value){
            list.set(key,LongNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public ListTagBuilder setFloat(int key,float value){
            list.set(key,FloatNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public ListTagBuilder setDouble(int key,double value){
            list.set(key,DoubleNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public ListTagBuilder setByteArray(int key,byte[] value){
            list.set(key,new ByteArrayNBT(value));
            return this;
        }

        @ProgramUsable
        public ListTagBuilder setIntArray(int key,int[] value){
            list.set(key,new IntArrayNBT(value));
            return this;
        }

        @ProgramUsable
        public ListTagBuilder setString(int key,String value){
            list.set(key,StringNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public ListTagBuilder setList(int key,ListNBT listTag){
            list.set(key,listTag);
            return this;
        }

        @ProgramUsable
        public ListTagBuilder setList(int key,ListTagBuilder listBuilder){
            list.set(key,listBuilder.build());
            return this;
        }

        @ProgramUsable
        public ListTagBuilder setCompound(int key,CompoundNBT compoundTag){
            list.set(key,compoundTag);
            return this;
        }

        @ProgramUsable
        public ListTagBuilder setCompound(int key,CompoundTagBuilder compoundBuilder){
            list.set(key,compoundBuilder.build());
            return this;
        }

        @ProgramUsable
        public ListTagBuilder addByte(byte value){
            list.add(ByteNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public ListTagBuilder addShort(short value){
            list.add(ShortNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public ListTagBuilder addInt(int value){
            list.add(IntNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public ListTagBuilder addLong(long value){
            list.add(LongNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public ListTagBuilder addFloat(float value){
            list.add(FloatNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public ListTagBuilder addDouble(double value){
            list.add(DoubleNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public ListTagBuilder addByteArray(byte[] value){
            list.add(new ByteArrayNBT(value));
            return this;
        }

        @ProgramUsable
        public ListTagBuilder addIntArray(int[] value){
            list.add(new IntArrayNBT(value));
            return this;
        }

        @ProgramUsable
        public ListTagBuilder addString(String value){
            list.add(StringNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public ListTagBuilder addList(ListNBT listTag){
            list.add(listTag);
            return this;
        }

        @ProgramUsable
        public ListTagBuilder addList(ListTagBuilder listBuilder){
            list.add(listBuilder.build());
            return this;
        }

        @ProgramUsable
        public ListTagBuilder addCompound(CompoundNBT compoundTag){
            list.add(compoundTag);
            return this;
        }

        @ProgramUsable
        public ListTagBuilder addCompound(CompoundTagBuilder compoundBuilder){
            list.add(compoundBuilder.build());
            return this;
        }

        @ProgramUsable
        public ListTagBuilder remove(int key){
            list.remove(key);
            return this;
        }

        @ProgramUsable
        public ListNBT build(){
            return (ListNBT)list.copy();
        }

        @ProgramUsable
        public ListNBT get(){
            return list;
        }

        @Override
        public String toString(){
            return list.toString();
        }
    }

    /**
     * 复合标签生成器
     */
    @ProgramUsable
    public static class CompoundTagBuilder{
        private CompoundNBT compound;

        @ProgramUsable
        public CompoundTagBuilder(){
            compound=new CompoundNBT();
        }

        @ProgramUsable
        public CompoundTagBuilder put(String key,INBT tag){
            compound.put(key,tag);
            return this;
        }

        @ProgramUsable
        public CompoundTagBuilder setByte(String key,byte value){
            compound.put(key,ByteNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public CompoundTagBuilder setShort(String key,short value){
            compound.put(key,ShortNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public CompoundTagBuilder setInt(String key,int value){
            compound.put(key,IntNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public CompoundTagBuilder setLong(String key,long value){
            compound.put(key,LongNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public CompoundTagBuilder setFloat(String key,float value){
            compound.put(key,FloatNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public CompoundTagBuilder setDouble(String key,double value){
            compound.put(key,DoubleNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public CompoundTagBuilder setByteArray(String key,byte[] value){
            compound.put(key,new ByteArrayNBT(value));
            return this;
        }

        @ProgramUsable
        public CompoundTagBuilder setIntArray(String key,int[] value){
            compound.put(key,new IntArrayNBT(value));
            return this;
        }

        @ProgramUsable
        public CompoundTagBuilder setString(String key,String value){
            compound.put(key,StringNBT.valueOf(value));
            return this;
        }

        @ProgramUsable
        public CompoundTagBuilder setList(String key,ListNBT listTag){
            compound.put(key,listTag);
            return this;
        }

        @ProgramUsable
        public CompoundTagBuilder setList(String key,ListTagBuilder listBuilder){
            compound.put(key,listBuilder.build());
            return this;
        }

        @ProgramUsable
        public CompoundTagBuilder setCompound(String key,CompoundNBT compoundTag){
            compound.put(key,compoundTag);
            return this;
        }

        @ProgramUsable
        public CompoundTagBuilder setCompound(String key,CompoundTagBuilder compoundBuilder){
            compound.put(key,compoundBuilder.build());
            return this;
        }

        @ProgramUsable
        public CompoundTagBuilder remove(String key){
            compound.remove(key);
            return this;
        }

        @ProgramUsable
        public CompoundNBT build(){
            return (CompoundNBT)compound.copy();
        }

        @ProgramUsable
        public CompoundNBT get(){
            return compound;
        }

        @Override
        public String toString(){
            return compound.toString();
        }
    }
}
