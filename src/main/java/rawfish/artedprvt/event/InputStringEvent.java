package rawfish.artedprvt.event;

import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class InputStringEvent extends Event {
    public String input;
    public InputStringEvent(String input){
        this.input=input;
    }
}
