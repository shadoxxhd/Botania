package vazkii.botania.common.network;

import baubles.common.network.PacketSyncBauble;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import vazkii.botania.common.item.relic.ItemLokiRing;

public class PacketLokiMirror implements IMessage, IMessageHandler<PacketLokiMirror, IMessage> {
    @Override
    public void fromBytes(ByteBuf byteBuf) {
        // not needed
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        // not needed
    }

    @Override
    public IMessage onMessage(PacketLokiMirror message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        final ItemStack aRing = ItemLokiRing.getLokiRing(player);
        if (aRing != null) {
            byte mode = ItemLokiRing.getRingMirrorMode(aRing);
            ItemLokiRing.setMirrorMode(aRing,(byte) ((mode+1)%8));
            ItemLokiRing.syncLokiRing(player);
            PacketHandler.INSTANCE.sendTo(new PacketLokiHudNotificationAck(ItemLokiRing.HUD_MESSAGE.MIRROR), player);
        }
        return null;
    }
}
