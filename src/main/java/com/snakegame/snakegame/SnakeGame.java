package com.snakegame.snakegame;

import com.snakegame.snakegame.gui.Painter;
import com.snakegame.snakegame.logic.Board;
import com.snakegame.snakegame.logic.Direction;
import com.snakegame.snakegame.logic.GameLoop;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SnakeGame extends Application {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private Board board;
    private GameLoop gameLoop;
    private GraphicsContext graphicsContext;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        graphicsContext = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        reset();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        canvas.setOnKeyPressed(e -> {
            if (gameLoop.isKeyPressed()) {
                return;
            }
            switch (e.getCode()) {
                case UP:
                    if (board.getSnake().getDirection() != Direction.DOWN) {
                        gameLoop.setKeyPressed(true);
                        board.getSnake().setDirection(Direction.TOP);
                    }
                    break;
                case DOWN:
                    if (board.getSnake().getDirection() != Direction.TOP) {
                        gameLoop.setKeyPressed(true);
                        board.getSnake().setDirection(Direction.DOWN);
                    }
                    break;
                case LEFT:
                    if (board.getSnake().getDirection() != Direction.RIGHT) {
                        gameLoop.setKeyPressed(true);
                        board.getSnake().setDirection(Direction.LEFT);
                    }
                    break;
                case RIGHT:
                    if (board.getSnake().getDirection() != Direction.LEFT) {
                        gameLoop.setKeyPressed(true);
                        board.getSnake().setDirection(Direction.RIGHT);
                    }
                    break;
                case ENTER:
                    if (gameLoop.isPaused()) {
                        reset();
                        (new Thread(gameLoop)).start();
                    }
            }
        });

        stage.setResizable(false);
        stage.setTitle("Snake");
        stage.setOnCloseRequest(e -> System.exit(0));
        stage.setScene(scene);
        stage.show();

        (new Thread(gameLoop)).start();
    }

    private void reset() {
        board = new Board();
        gameLoop = new GameLoop(board, graphicsContext);
        Painter.paint(board, graphicsContext);
    }

}