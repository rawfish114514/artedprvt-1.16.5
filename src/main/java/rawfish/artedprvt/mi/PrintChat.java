package rawfish.artedprvt.mi;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.NewChatGui;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import rawfish.artedprvt.Artedprvt;
import rawfish.artedprvt.core.*;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

/**
 * 聊天消息打印
 */
@SideUsable(Sides.ALL)
@ProgramUsable
public class PrintChat implements ScriptObject{
    private ScriptProcess process;
    private ScriptLogger logger=null;
    public NewChatGui guiNewChat;
    public boolean isLog=true;
    public boolean longtime=false;

    @ProgramUsable
    public PrintChat(){
        process=up();
        if(process!=null){
            logger=process.getScriptLogger();
        }
        if(Artedprvt.instance.isHasClientSide()) {
            guiNewChat = Minecraft.getInstance().gui.getChat();
        }else {
            guiNewChat=null;
        }
    }

    /**
     * 设置消息行
     * 但他们只对自己是可见的
     * @param chat 聊天信息字符串
     * @param id
     */
    @ProgramUsable
    public void line(String chat,int id){
        if(server(chat)){
            return;
        }
        info(chat);
        guiNewChat.addMessage(new StringTextComponent(chat));
    }

    /**
     * 设置消息行
     * 但他们只对自己是可见的
     * @param chat 聊天信息字符串
     * @param hover 悬浮信息字符串
     * @param id
     */
    @ProgramUsable
    public void line(String chat,String hover,int id){
        if(server(chat)){
            return;
        }
        info(chat);
        StringTextComponent chatComponentText=new StringTextComponent(chat);
        StringTextComponent hoverComponent=new StringTextComponent(hover);
        chatComponentText.setStyle(Style.EMPTY
                .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,hoverComponent))
                .withClickEvent(new CopyEvent(hoverComponent))
        );
        guiNewChat.addMessage(chatComponentText);
    }

    /**
     * 设置消息行
     * 但他们只对自己是可见的
     * @param chat 聊天信息字符串
     * @param hover 悬浮信息供应商
     * @param id
     */
    @ProgramUsable
    public void line(String chat,ChatProvider hover,int id){
        if(server(chat)){
            return;
        }
        info(chat);
        StringTextComponent chatComponentText=new StringTextComponent(chat);
        StringTextComponent hoverComponent=new ChatProviderComponent(hover);
        chatComponentText.setStyle(Style.EMPTY
                .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,hoverComponent))
                .withClickEvent(new CopyEvent(hoverComponent))
        );
        guiNewChat.addMessage(chatComponentText);
    }

    /**
     * 打印到聊天栏
     * 但他们只对自己是可见的
     * @param chat 聊天信息字符串
     */
    @ProgramUsable
    public void print(String chat){
        if(server(chat)){
            return;
        }
        line(chat,0);
    }

    /**
     * 打印到聊天栏
     * 但他们只对自己是可见的
     * @param chat 聊天信息字符串
     * @param hover 悬浮信息供应商
     */
    @ProgramUsable
    public void print(String chat,String hover){
        if(server(chat+": "+hover)){
            return;
        }
        line(chat,hover,0);
    }

    /**
     * 打印到聊天栏
     * 但他们只对自己是可见的
     * @param chat 聊天信息字符串
     * @param hover 悬浮信息供应商
     */
    @ProgramUsable
    public void print(String chat,ChatProvider hover){
        if(server(chat+": "+hover.getChat())){
            return;
        }
        line(chat,hover,0);
    }

    /**
     * 删除指定id的聊天消息
     * @param id
     */
    @ProgramUsable
    public void remove(int id){
        if(server("remove "+id)){
            return;
        }
        //guiNewChat.deleteChatLine(id);
    }

    private void info(String message){
        if(isLog&&logger!=null){
            logger.info(message);
        }
    }

    @Override
    public void onClose() {
        if(!longtime) {
            guiNewChat = null;
        }
    }


    private boolean server(String message){
        if(guiNewChat==null){
            System.out.println(message);
            return true;
        }
        return false;
    }

    public static class ChatProviderComponent extends StringTextComponent implements ScriptObject {
        private ChatProvider provider;
        private String str;
        public ChatProviderComponent(ChatProvider provider){
            super("");
            up();
            this.provider=provider;
            if(provider==null){
                str="null";
            }else{
                str=provider.getChat();
            }
        }

        @Override
        public String getContents(){
            try{
                String ts;
                if(provider!=null){
                    ts=provider.getChat();
                    if(ts!=null){
                        str=ts;
                    }
                }
                return str;
            }catch (Throwable e){
                e.printStackTrace(System.err);
                return str;
            }
        }

        @Override
        public void onClose() {
            provider=null;
        }
    }

    /**
     * 点击复制事件
     */
    public static class CopyEvent extends ClickEvent {
        public StringTextComponent chatComponents;
        public CopyEvent(StringTextComponent chatComponents) {
            super(null, null);
            this.chatComponents=chatComponents;
        }

        @Override
        public ClickEvent.Action getAction(){
            set(chatComponents.getContents().replaceAll("\u00a7[0-9a-fk-or]",""));
            return null;
        }

        public void set(String str){
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(str),null);
        }
    }
}
