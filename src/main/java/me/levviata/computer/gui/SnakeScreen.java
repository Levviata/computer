package me.levviata.computer.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MouseHelper;

import java.util.ArrayList;
import java.util.Random;
import org.lwjglx.input.Keyboard;

import java.io.IOException;
import java.util.Arrays;

public class SnakeScreen extends GuiScreen {

//    private GuiButton exampleButton;

    private static final int ROWS = 25;
    private static final int COLS = 25;
    private static final int TILE_SIZE = 10;

    private static final int GAME_WIDTH = TILE_SIZE * COLS;
    private static final int GAME_HEIGHT = TILE_SIZE * ROWS;

    private static Tile snake = new Tile(0, 0);
    private static Tile food = new Tile(0, 0);
    private static final ArrayList<Tile> snakeBody = new ArrayList<>();
    private int score = 0;

    private int timer = 0;

    private int velocityX, velocityY;

    private boolean gameOver = false;

    private ScaledResolution resolution;
    private Minecraft minecraft = Minecraft.getMinecraft();

    @Override
    public void initGui() {
        //this.buttonList.clear();
        //this.buttonList.add(exampleButton = new GuiButton(0, centerX - 50, centerY, 100, 20, "Click Me"));
        snake = new Tile(5 * TILE_SIZE, 5 * TILE_SIZE);
        food = new Tile(10 * TILE_SIZE, 10 * TILE_SIZE);
        gameOver = false;
        snakeBody.clear();
        velocityX = 0;
        velocityY = 0;
        timer = 0;
        score = 0;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        timer++;

        resolution = new ScaledResolution(minecraft);
        int screenWidth = resolution.getScaledWidth();
        int screenHeight = resolution.getScaledHeight();

        int halfWidth = screenWidth / 2;
        int halfHeight = screenHeight / 2;


        // top left region
        int gameCenterX = halfWidth - (GAME_WIDTH / 2);
        int gameCenterY = halfHeight - (GAME_HEIGHT / 2);

        if (timer % 10 == 0) {
            move();
        }

        // The background
        drawRect(gameCenterX,
                gameCenterY,
                halfWidth + (GAME_WIDTH / 2),
                halfHeight + (GAME_HEIGHT / 2),
                0xFF000000);

        // The food
        drawRect(gameCenterX + food.x,
                gameCenterY + food.y,
                gameCenterX +  (food.x + TILE_SIZE),
                gameCenterY + (food.y + TILE_SIZE),
                0xFFFF0000);

        // The snake
        drawRect(gameCenterX + snake.x,
                gameCenterY + snake.y,
                gameCenterX + snake.x + TILE_SIZE,
                gameCenterY + snake.y + TILE_SIZE,
                0xFF00FF00);

        for (Tile tile : snakeBody) {
            drawRect(gameCenterX +tile.x,
                    gameCenterY + tile.y,
                    gameCenterX +tile.x + TILE_SIZE,
                    gameCenterY +tile.y + TILE_SIZE,
                    0xFF00FF00);
        }

        if (gameOver) {
            this.drawCenteredString(this.fontRenderer, "Game Over: " + score, this.width / 2, this.height / 2, 0xFF0000);
        } else {
            this.drawCenteredString(this.fontRenderer, "Score: " + score, this.width / 2, gameCenterY - 10, 0xFFFFFF);
        }

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

        //draw();

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

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);

        if (gameOver) {
            return;
        }

        if (keyCode == Keyboard.KEY_W && velocityY != 1) {
           //System.out.println("Pressed W");
           velocityX = 0;
           velocityY = -1;
        }

        if (keyCode == Keyboard.KEY_S && velocityY != -1) {
            //System.out.println("Pressed S");
            velocityX = 0;
            velocityY = 1;
        }

        if (keyCode == Keyboard.KEY_A && velocityX != 1) {
            //System.out.println("Pressed A");
            velocityX = -1;
            velocityY = 0;
        }

        if (keyCode == Keyboard.KEY_D && velocityX != -1) {
            //System.out.println("Pressed D");
            velocityX = 1;
            velocityY = 0;
        }
    }

    private void move() {
        Random random = new Random();

        if (gameOver) {
            return;
        }

        if (snake.x < 0 || snake.x >= GAME_WIDTH || snake.y < 0 || snake.y >= GAME_HEIGHT) {
            gameOver = true;
            return;
        }

        for (Tile tile : snakeBody) {
            if (snake.x == tile.x && snake.y == tile.y) {
                gameOver = true;
                return;
            }
        }

        // Collision handling
        if (snake.x == food.x && snake.y == food.y) {
            snakeBody.add(new Tile(food.x, food.y));
            food.x = random.nextInt(COLS) * TILE_SIZE;
            food.y = random.nextInt(ROWS) * TILE_SIZE;

            score += 1;
        }

        // Move snake body
        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile tile = snakeBody.get(i);
            if (i == 0) { // The head of the snake
                tile.x = snake.x;
                tile.y = snake.y;
            } else {
                Tile previousTile = snakeBody.get(i - 1);
                tile.x = previousTile.x;
                tile.y = previousTile.y;
            }
        }

        snake.x += velocityX * TILE_SIZE;
        snake.y += velocityY * TILE_SIZE;
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

   private static class Tile
   {
         int x, y;

         Tile(int x, int y) {
              this.x = x;
              this.y = y;
         }
   }
}