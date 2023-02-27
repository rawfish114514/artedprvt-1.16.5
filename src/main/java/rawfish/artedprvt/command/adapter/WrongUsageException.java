package rawfish.artedprvt.command.adapter;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.TranslationTextComponent;

/**
 * 错误使用
 */
public class WrongUsageException extends RuntimeException{
    public WrongUsageException(CommandSource senderIn,String str){
        super(new net.minecraft.command.CommandException(new TranslationTextComponent(str)));
        senderIn.sendSuccess(new TranslationTextComponent(str),false);
    }
}
