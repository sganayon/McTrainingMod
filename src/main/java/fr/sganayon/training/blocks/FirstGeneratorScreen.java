package fr.sganayon.training.blocks;

import com.mojang.blaze3d.platform.GlStateManager;
import fr.sganayon.training.McTrainingMod;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

//client only
public class FirstGeneratorScreen extends ContainerScreen<FirstGeneratorContainer> {

    private ResourceLocation GUI = new ResourceLocation(McTrainingMod.MODID, "textures/gui/firstgenerator_gui.png");

    public FirstGeneratorScreen(FirstGeneratorContainer container, PlayerInventory playerInventory, ITextComponent name) {
        super(container, playerInventory, name);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        // width : total width of the screen,
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
    }
}
