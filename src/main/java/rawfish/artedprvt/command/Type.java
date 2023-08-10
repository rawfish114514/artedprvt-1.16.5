package rawfish.artedprvt.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.arguments.MessageArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Type extends MessageArgument {
    public Command command;
    public Type(Command command){
        this.command=command;
    }

    public List<String> args=new ArrayList<>();

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        List<String> args=new ArrayList<>();
        String input=context.getInput();
        String[] array=input.split(" ");
        for(int i=1;i<array.length;i++){
            args.add(array[i]);
        }
        if(input.endsWith(" ")){
            args.add("");
        }
        if(args.size()==0){
            args.add("");
        }
        int length=0;
        length=command.getName().length()+2;
        for(int i=0;i<args.size()-1;i++){
            length+=args.get(i).length()+1;
        }
        System.out.println(length);
        System.out.println(context.getInput());
        CompletableFuture<Suggestions> completableFuture=new CompletableFuture<>();
        List<Suggestion> suggestions=new ArrayList<>();
        StringRange stringRange=new StringRange(length,32500);
        List<String> completes=command.complete(args);
        for(int i=0;i<completes.size();i++){
            suggestions.add(new Suggestion(
                    stringRange,
                    completes.get(i)));
        }
        completableFuture.complete(new Suggestions(stringRange,suggestions));
        return completableFuture;
    }
}
