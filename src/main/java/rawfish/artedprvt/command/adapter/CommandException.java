package rawfish.artedprvt.command.adapter;

import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;

public class CommandException extends RuntimeException{
    public CommandException(CommandSource senderIn,String str){
        super(new net.minecraft.command.CommandException(new StringTextComponent(str)));
        senderIn.sendSuccess(new StringTextComponent(str),false);
    }
}
