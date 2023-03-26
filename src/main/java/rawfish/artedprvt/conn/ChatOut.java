package rawfish.artedprvt.conn;


import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;

public class ChatOut {
    public CommandSource sender;

    public ChatOut(CommandSource sender){
        this.sender=sender;
    }

    public void print(String str){
        sender.sendSuccess(new StringTextComponent(str),false);
    }
}
