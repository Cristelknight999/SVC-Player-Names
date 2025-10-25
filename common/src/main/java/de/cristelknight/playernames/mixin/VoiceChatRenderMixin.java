package de.cristelknight.playernames.mixin;

import de.cristelknight.cristellib.config.simple.ConfigRegistry;
import de.cristelknight.playernames.PNConfig;
import de.maxhenkel.voicechat.voice.client.ClientVoicechat;
import de.maxhenkel.voicechat.voice.client.GroupChatManager;
import de.maxhenkel.voicechat.voice.common.PlayerState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(GroupChatManager.class)
public class VoiceChatRenderMixin {

    @Inject(method = "renderIcons", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private static void svcpn$renderIcons(GuiGraphics guiGraphics, CallbackInfo ci, ClientVoicechat client, Minecraft mc, List<PlayerState> groupMembers, int posX, int posY, float mainScale, boolean vertical, int i, PlayerState state) {
        PNConfig config = ConfigRegistry.get(PNConfig.class);
        if(!vertical || (!config.alwaysShow() && !client.getTalkCache().isTalking(state.getUuid()))) return;
        guiGraphics.pose().pushMatrix();

        float scale = (float) config.scale();
        guiGraphics.pose().scale(scale, scale);

        Component name = Component.literal(state.getName());
        int textWidth = mc.font.width(name);
        int textHeight = mc.font.lineHeight;

        boolean isRight = posX < 0;

        float x;
        if(isRight) x = ((float) (-config.xOffset() - 11 - (textWidth * scale)) / scale);
        else x = ((float) config.xOffset() + 12) / scale;

        float y = (((float) config.yOffset() + 6.5f) - (textHeight * scale) / 2f) / scale;

        guiGraphics.drawString(mc.font, name, (int) x, (int) y, config.color().toInt(), false);
        guiGraphics.pose().popMatrix();
    }
}