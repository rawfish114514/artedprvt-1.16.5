package rawfish.artedprvt.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import rawfish.artedprvt.command.adapter.CommandException;
import rawfish.artedprvt.id.FormatCode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CommandWorkspace extends CommandIs {
    @Override
    public void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource>
                command= Commands.literal(getCommandName())
                .requires((commandSource)->{return commandSource.hasPermission(getRequiredPermissionLevel());})
                .executes((commandSource)->{
                    processCommand(commandSource.getSource(),new String[0]);
                    return 0;
                });
        dispatcher.register(command);
    }

    public CommandWorkspace(String nameIn){
        name=nameIn;
    }

    public final String name;
    @Override
    public String getCommandName() {
        return name;
    }

    @Override
    public String getCommandUsage(CommandSource sender)
    {
        return "commands.workspace.usage";
    }

    @Override
    public void processCommand(CommandSource sender, String[] args) throws CommandException
    {
        String dir=System.getProperties().get("user.dir").toString()+"/artedprvt";//项目目录
        File file;
        file=new File(dir);
        if(file.exists()){
            throw new CommandException("目录/artedprvt已经存在");
        }
        file.mkdir();

        file=new File(dir+"/lib");
        file.mkdir();

        file=new File(dir+"/src");
        file.mkdir();

        file=new File(dir+"/src/script");
        file.mkdir();

        file=new File(dir+"/src/config.json");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Writer writer=new FileWriter(file);
            writer.write(fileConfig());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sender.sendSuccess(new StringTextComponent(FormatCode.COLOR_2+"创建成功: "+dir),false);
    }
    public String fileConfig(){
        return "{\n" +
                "  \"options\": [\n" +
                "    \"-pm\",\"-al\"\n" +
                "  ],\n" +
                "  \"pkg\": {\n" +
                "    \"pack\": \"main\"\n" +
                "  }\n" +
                "}";
    }
    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
}