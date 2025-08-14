package me.levviata.computer.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MouseHelper;

import java.io.IOException;

public class SnakeScreen extends GuiScreen {

//    private GuiButton exampleButton;

    private static final int ROWS = 25;
    private static final int COLS = 25;
    private static final int TILE_SIZE = 7;
    private static final int[] GRID = {
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
    };

    private ScaledResolution resolution;
    private Minecraft minecraft = Minecraft.getMinecraft();

    @Override
    public void initGui() {
        this.buttonList.clear();
        //this.buttonList.add(exampleButton = new GuiButton(0, centerX - 50, centerY, 100, 20, "Click Me"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

//        ScaledResolution resolution = new ScaledResolution(mc);
//        // Draw a single white tile, 5 wide and 5 tall
//        drawRect(resolution.getScaledWidth() / 2 - 5,
//                resolution.getScaledHeight() / 2 - 5,
//                resolution.getScaledWidth() / 2 + 5,
//                resolution.getScaledHeight() / 2 + 5,
//                0xFFFFFFFF // ARGB
//        );

//        drawRect(resolution.getScaledWidth()/2 - 1*TILE_SIZE,
//                resolution.getScaledHeight()/2 - 1*TILE_SIZE,
//                resolution.getScaledWidth()/2 + 1*TILE_SIZE,
//                resolution.getScaledHeight()/2 + 1*TILE_SIZE,
//                0xFF000000 // ARGB
//        );
//
//        drawRect(resolution.getScaledWidth()/2 - 2*TILE_SIZE,
//                resolution.getScaledHeight()/2 - 2*TILE_SIZE,
//                resolution.getScaledWidth()/2 + 2*TILE_SIZE,
//                resolution.getScaledHeight()/2 + 2*TILE_SIZE,
//                0xFF0000FF // ARGB
//        );

        draw();

        this.drawCenteredString(this.fontRenderer, "Snake Game", this.width / 2, 20, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    @Override
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        MouseHelper mouseHelper = Minecraft.getMinecraft().mouseHelper;
        mouseHelper.grabMouseCursor(); // Hides the cursor
    }
    private void draw() {
        resolution = new ScaledResolution(minecraft);

        int gridWidth = COLS * TILE_SIZE;
        int gridHeight = ROWS * TILE_SIZE;

        int startX = (resolution.getScaledWidth() - gridWidth) / 2;
        int startY = (resolution.getScaledHeight() - gridHeight) / 2;

        // For as many values in the map's Y axis, go one value forward
        for (int tileY = 0; tileY < ROWS; tileY++) {
            // Again but for the X axis
            for (int tileX = 0; tileX < COLS; tileX++) {
                int value = GRID[tileY * COLS + tileX];
                int xLeft = startX + tileX * TILE_SIZE;
                int yTop = startY + tileY * TILE_SIZE;
                int xRight = xLeft + TILE_SIZE;
                int yDown = yTop + TILE_SIZE;

                int color;

                switch (value) {
                    case 1:
                        color = 0xFF00FF00; // Snake
                        break;
                    case 2:
                        color = 0xFFFF0000; // Food
                        break;
                    default:
                        color = 0xFF000000; // Empty
                        break;
                }
                drawRect(xLeft, yTop, xRight, yDown, color);
            }
        }
    }

   /* @Override
    protected void actionPerformed(GuiButton button) {
        if (button == exampleButton) {
            // Handle button click
            this.mc.player.sendChatMessage("Button clicked!");
        }
    }*/

   @Override
   public void onGuiClosed() {
       super.onGuiClosed();
       MouseHelper mouseHelper = Minecraft.getMinecraft().mouseHelper;
       mouseHelper.ungrabMouseCursor(); // Shows the cursor
   }
}