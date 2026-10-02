package net.meander.subtlyd.client.renderer.gui;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.player.PlayerCapeModel;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.core.ClientAsset;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.entity.player.PlayerModelType;
import net.minecraft.world.entity.player.PlayerSkin;

/**
 * @see AvatarRenderer
 */
public class GuiAvatarRenderer {
    private final Minecraft minecraft = Minecraft.getInstance();
    private final Options options = minecraft.options;
    private final EntityRenderDispatcher dispatcher = minecraft.getEntityRenderDispatcher();
    private final PlayerSkin skin;
    private final PlayerCapeModel capeModel;
    private final Model.Simple capeSimple;
    private final Model.Simple wideModel;
    private final Model.Simple slimModel;

    public GuiAvatarRenderer() {
        this(Minecraft.getInstance().getGameProfile());
    }

    public GuiAvatarRenderer(GameProfile profile) {
        skin = minecraft.getSkinManager().createLookup(profile, false).get();
        capeModel = new PlayerCapeModel(minecraft.getEntityModels().bakeLayer(ModelLayers.PLAYER_CAPE));
        capeSimple = new Model.Simple(capeModel.root(), capeModel.renderType());

        AvatarRenderer<AbstractClientPlayer> wideModelRenderer = dispatcher.playerRenderers.get(PlayerModelType.WIDE);
        AvatarRenderer<AbstractClientPlayer> slimModelRenderer = dispatcher.playerRenderers.get(PlayerModelType.SLIM);

        wideModel = new Model.Simple(wideModelRenderer.getModel().root(), wideModelRenderer.getModel().renderType());
        slimModel = new Model.Simple(slimModelRenderer.getModel().root(), slimModelRenderer.getModel().renderType());
    }

    public void extractRenderState(final GuiGraphicsExtractor guiGraphics, final int x, final int y, final int entityScale, final float mouseX, final float mouseY) {
        AvatarRenderState state = new AvatarRenderState();

        setSkin(state);

        AvatarRenderer<AbstractClientPlayer> playerRenderer = dispatcher.playerRenderers.get(state.skin.model());

        if (playerRenderer != null) {
            int scaleModifier = entityScale * 2;
            int x0 = x - scaleModifier / 3;
            int y0 = y - scaleModifier;
            int x1 = x + scaleModifier / 3;
            int y1 = y + scaleModifier / 10;

            PlayerModel model = playerRenderer.getModel();
            Model.Simple playerModel = state.skin.model() == PlayerModelType.SLIM ? slimModel : wideModel;

            followCursor(state, x, y, mouseX, mouseY);
            model.setupAnim(state);
            extractCapeRenderState(guiGraphics, state, entityScale, x0, y0, x1, y1);
            guiGraphics.skin(playerModel, state.skin.body().texturePath(), entityScale, 0.0F, state.bodyRot, 0.0F, x0, y0, x1, y1);
        }
    }

    public void extractCapeRenderState(final GuiGraphicsExtractor guiGraphics, final AvatarRenderState state, final int scale, int x0, int y0, int x1, int y1) {
        if (state.showCape) {
            ClientAsset.Texture cape = state.skin.cape();

            if (cape != null) {
                state.capeFlap = 1.0F;
                state.capeLean = 0.5F;
                state.capeLean2 = 0.5F;

                capeModel.setupAnim(state);
                guiGraphics.skin(capeSimple, cape.texturePath(), scale, 0, state.bodyRot, 0, x0, y0, x1, y1);
            }
        }
    }

    public void followCursor(final AvatarRenderState state, final int x, final int y, final float mouseX, final float mouseY) {
        float yaw = (float) Mth.atan2(x - mouseX, 40.0);
        float pitch = (float) Mth.atan2(y - 35.0 - mouseY, 40.0);

        state.yRot = yaw * 20.0F;
        state.xRot = -pitch * 5.0F;
        state.bodyRot = -state.yRot * 0.5F;
    }

    public void setSkin(final AvatarRenderState state) {
        state.isCrouching = false;
        state.skin = skin;
        state.showHat = options.isModelPartEnabled(PlayerModelPart.HAT);
        state.showJacket = options.isModelPartEnabled(PlayerModelPart.JACKET);
        state.showLeftSleeve = options.isModelPartEnabled(PlayerModelPart.LEFT_SLEEVE);
        state.showRightSleeve = options.isModelPartEnabled(PlayerModelPart.RIGHT_SLEEVE);
        state.showLeftPants = options.isModelPartEnabled(PlayerModelPart.LEFT_PANTS_LEG);
        state.showRightPants = options.isModelPartEnabled(PlayerModelPart.RIGHT_PANTS_LEG);
        state.showCape = options.isModelPartEnabled(PlayerModelPart.CAPE);
    }
}
