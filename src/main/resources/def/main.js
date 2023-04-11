//Hello Minecraft!
//Created by Rawfishc on 2023/4/10

var r=giveBook();
drawLogo();

function drawLogo(){
    var a=print;
    a("§0█████§4███§0█████████");
    a("§0█████§4██§3█§0█§0██████§f██");
    a("§0████§4██§0█§3██§0██████§f██");
    a("§0████§4██§0█§3██§0██████§f██");
    a("§0███§4██§0███§3██§0███████");
    a("§0███§4██§0███§3██§0███████");
    a("§0███§4██§0███§3██§0███████");
    a("§0██§4██§0█████§3██§0██████");
    a("§0██§4███████§3██§0████§f██");
    a("§0█§4█████████§3██§0███§f██");
    a("§0█§4██§0███████§3██§0███§f██");
    a("§0█§4██§0███████§3██§0████§f█");
    a("§0§4██§0█████████§3██§0██§f█§0█");
}

function giveBook(){
    import("-net.minecraft.client.Minecraft");
    import("-net.minecraft.item.ItemStack");
    import("-net.minecraft.nbt.CompoundNBT");
    import("-net.minecraft.nbt.ListNBT");
    import("-net.minecraft.nbt.StringNBT");
    import("-net.minecraft.util.IItemProvider");

    var entityPlayer=Minecraft.getInstance().getSingleplayerServer().getPlayerList().getPlayerByName(getThisPlayer().getDisplayName().getString());
    var nbt=new CompoundNBT();
        var pagesTag=buildPageArray();
        var authorTag=StringNBT.valueOf("\u00a73Rawfishc\u00a77");
        var titleTag=StringNBT.valueOf("\u00a7lHello Minecraft");
    nbt.put("pages",pagesTag);
    nbt.put("author",authorTag);
    nbt.put("title",titleTag);
    var itemStack = new ItemStack(new IItemProvider({asItem:function(){return Items.WRITTEN_BOOK}}));
    itemStack.setTag(nbt);
    var flag=entityPlayer.inventory.add(itemStack);

    function buildPageArray(){
        var tag=new ListNBT();
        var pageMethods=[page_0,page_1];
        for(var i=0;i<pageMethods.length;i++){
            var page=pageMethods[i]();
            tag.add(StringNBT.valueOf(page));
        }
        return tag;
    }
    function page_0(){
        return "\"\u00a7lAPF(Artedprvt Frame)\u00a7r\n\nAPF是用于Minecraft的Js运行环境。\n\n目的是在游戏中动态的运行代码。\"";
    }
    function page_1(){
        return "\"对于只使用脚本的玩家来说，没有任何门槛。\n\n如果你有Js编程基础可以编写脚本实现一些简单的功能。"+
            "\n\n要实现稍微复杂的功能可能需要你有较高的Js和Java水平以及Mod开发经验。\n\n降低开发门槛也是APF的主要目标！\"";
    }
    return Math.log(1919810)/Math.log(114514);
}