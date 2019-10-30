package net.bqc.towerdefense;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TowerDefense2 extends Application {

    GraphicsContext gc;
    List<GameObject> gameObjects = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        Canvas canvas = new Canvas(128 * 10, 128 * 7);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        gameObjects.add(createTank());
    }

    // Factory Method
    public Tank createTank() {
        Tank tank = new Tank();
        tank.i = 0;
        tank.j = 6;
        tank.x = tank.i * 128 + 64;
        tank.y = tank.j * 128;
        tank.speed = 5;
        tank.direction = Direction.UP;
        tank.img = new Image("file:src/main/resources/AssetsKit_2/PNG/Retina/towerDefense_tile268.png");
        tank.gunImg = new Image("file:src/main/resources/AssetsKit_2/PNG/Retina/towerDefense_tile291.png");
        return tank;
    }

    public static final String[][] MAP_SPRITES = new String[][] {
            { "024", "024", "003", "047", "047", "047", "004", "024", "024", "024" },
            { "024", "024", "025", "299", "001", "002", "023", "024", "024", "024" },
            { "024", "024", "025", "023", "024", "025", "023", "024", "024", "024" },
            { "003", "047", "048", "023", "024", "025", "023", "024", "024", "024" },
            { "025", "299", "001", "027", "024", "025", "046", "047", "047", "047" },
            { "025", "023", "024", "024", "024", "026", "001", "001", "001", "001" },
            { "025", "023", "024", "024", "024", "024", "024", "024", "024", "024" },
    };

    private void drawMap(GraphicsContext gc) {
        for (int i = 0; i < MAP_SPRITES.length; i++) {
            for (int j = 0; j < MAP_SPRITES[i].length; j++) {
                gc.drawImage(new Image("file:src/main/resources/AssetsKit_2/PNG/Retina/towerDefense_tile" + MAP_SPRITES[i][j] + ".png"), j * 128, i * 128);
            }
        }
    }

    public void update() {
        gameObjects.forEach(GameObject::update);
    }

    public void render() {
        drawMap(gc);
        gameObjects.forEach(g -> g.render(gc));
    }
}

enum Direction {
    LEFT(180), UP(270), RIGHT(0), DOWN(90);

    int degree;

    Direction(int i) {
        degree = i;
    }

    int getDegree() {
        return degree;
    }
}

abstract class GameObject {
    int i, j;
    int x;
    int y;
    Image img;

    abstract void render(GraphicsContext gc);
    abstract void update();

}

abstract class MovableObject extends GameObject {
    double speed;
    Direction direction;
}

abstract class VulnerableObject extends MovableObject {
    int health;
    double reward;
}

abstract class AttackableObject extends VulnerableObject {
    double damage;
    double fireRate;
    double fireRange;
}

class Tank extends AttackableObject {
    Image gunImg;
    int gunRotation;

    @Override
    void render(GraphicsContext gc) {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(img);
        iv.setRotate(this.direction.getDegree());
        Image base = iv.snapshot(params, null);

        ImageView iv2 = new ImageView(gunImg);
        iv2.setRotate(this.direction.getDegree());
        Image gun = iv2.snapshot(params, null);

        gc.drawImage(base, x, y);
        gc.drawImage(gun, x, y);
    }

    @Override
    void update() {

        calculateDirection();

        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
        }
    }

    void calculateDirection() {
        // Tinh huong di tiep theo cho Object
    }
}
