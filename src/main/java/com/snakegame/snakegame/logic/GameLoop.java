package com.snakegame.snakegame.logic;

import com.snakegame.snakegame.gui.Painter;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameLoop implements Runnable{
    private final Board board;
    private final GraphicsContext context;
    private int frameRate;
    private float interval;
    private boolean running;
    private boolean paused;
    private boolean keyPressed;

    public GameLoop(final Board grid, final GraphicsContext context) {
        this.board = grid;
        this.context = context;
        frameRate = 20;
        interval = 1000.0f / frameRate;
        running = true;
        paused = false;
        keyPressed = false;
    }

    @Override
    public void run() {
        while (running && !paused) {
            float time = System.currentTimeMillis();

            board.getSnake().move();
            if (board.getSnake().getHead().compareByPosition(board.getFood())) {
                board.growSnake();
            }
            Painter.paint(board, getContext());

            if (board.getSnake().isDead()) {
                pause();
                Painter.paintGameOver(getContext());
                setKeyPressed(false);
                break;
            }

            setKeyPressed(false);

            time = System.currentTimeMillis() - time;

            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException ignore) {
                }
            }
        }
    }

    private void pause() {
        paused = true;
    }
}
