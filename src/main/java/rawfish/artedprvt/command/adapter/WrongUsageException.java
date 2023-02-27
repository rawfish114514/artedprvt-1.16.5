package rawfish.artedprvt.command.adapter;

import net.minecraft.util.text.TranslationTextComponent;

/**
 * 错误使用
 */
public class WrongUsageException extends RuntimeException{
    public WrongUsageException(String str){
        super(new net.minecraft.command.CommandException(new TranslationTextComponent(str)));
    }
}
