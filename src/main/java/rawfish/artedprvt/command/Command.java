package rawfish.artedprvt.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.MessageArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command implements com.mojang.brigadier.Command<CommandSource>{
    public abstract void process(List<String> args);
    public abstract List<String> complete(List<String> args);


    public String commandName;
    public ArgumentBuilder argumentBuilder;

    public Command(String commandName){
        this.commandName=commandName;
        argumentBuilder=new ArgumentBuilder(this,new Type(this));
        argumentBuilder.executes(this);
    }

    public String getName() {
        return commandName;
    }


    public String getUsage() {
        return "commands."+commandName+".usage";
    }

    public static final List<String> nullTab=new ArrayList<>();


    public static final List<String> nullArg=new ArrayList<>();
    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException{
        String str=context.getInput();// /command args...
        try {
            if (!str.contains(" ")) {
                process(nullArg);
            } else {
                String argsStr = str.substring(str.indexOf(" ") + 1);
                String[] args = argsStr.trim().split(" ");
                process(Arrays.asList(args));
            }
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
        return 0;
    }

    public void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(argumentBuilder);
    }
}
