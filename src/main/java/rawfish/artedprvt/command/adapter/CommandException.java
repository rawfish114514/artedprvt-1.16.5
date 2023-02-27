package rawfish.artedprvt.command.adapter;

import net.minecraft.util.text.StringTextComponent;

public class CommandException extends RuntimeException{
    public CommandException(String str){
        super(new net.minecraft.command.CommandException(new StringTextComponent(str)));
    }
}
