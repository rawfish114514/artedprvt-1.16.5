package rawfish.artedprvt.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.MessageArgument;

import java.util.ArrayList;
import java.util.List;

public class ArgumentBuilder extends LiteralArgumentBuilder<CommandSource>{
    public Command command;
    public ArgumentType type;
    public ArgumentBuilder(Command command,ArgumentType type){
        super(command.getName());
        this.command=command;
        this.type=type;
    }


    @Override
    public LiteralCommandNode<CommandSource> build() {
        LiteralCommandNode<CommandSource> result = new LiteralCommandNode<>(getLiteral(), getCommand(), getRequirement(), getRedirect(), getRedirectModifier(), isFork());

        Builder<CommandSource,String> argumentBuilder=Builder.argument("args",type);
        argumentBuilder.executes(command);
        result.addChild(argumentBuilder.build());
        return result;
    }
}