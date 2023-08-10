package rawfish.artedprvt.mi;


import net.minecraftforge.eventbus.api.Event;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface EventTarget {
    Class<? extends Event> value();
}
