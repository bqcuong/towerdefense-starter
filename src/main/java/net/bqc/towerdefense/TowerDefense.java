//package net.bqc.towerdefense;
//
//import javafx.animation.AnimationTimer;
//import javafx.application.Application;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.SnapshotParameters;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TowerDefense extends Application {
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    GraphicsContext gc;
//    private List<GameObject> gameObjects = new ArrayList<>();
//
//    @Override
//    public void start(Stage stage) {
//        Canvas canvas = new Canvas(128 * 10, 128 * 7);
//        gc = canvas.getGraphicsContext2D();
//        Group root = new Group();
//        root.getChildren().add(canvas);
//        stage.setScene(new Scene(root));
//        stage.show();
//
//        AnimationTimer h = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                gc.clearRect(0, 0, 128 * 10, 128 * 7);
//                render();
//                update();
//            }
//        };
//        h.start();
//
//        gameObjects.add(new Tank(gc));
//    }
//
//    private final String[][] MAP_SPRITES = new String[][] {
//            { "024", "024", "003", "047", "047", "047", "004", "024", "024", "024" },
//            { "024", "024", "025", "299", "001", "002", "023", "024", "024", "024" },
//            { "024", "024", "025", "023", "024", "025", "023", "024", "024", "024" },
//            { "003", "047", "048", "023", "024", "025", "023", "024", "024", "024" },
//            { "025", "299", "001", "027", "024", "025", "046", "047", "047", "047" },
//            { "025", "023", "024", "024", "024", "026", "001", "001", "001", "001" },
//            { "025", "023", "024", "024", "024", "024", "024", "024", "024", "024" },
//    };
//
//    private void drawMap(GraphicsContext gc) {
//        for (int i = 0; i < MAP_SPRITES.length; i++) {
//            for (int j = 0; j < MAP_SPRITES[i].length; j++) {
//                gc.drawImage(new Image("file:src/main/resources/AssetsKit_2/PNG/Retina/towerDefense_tile" + MAP_SPRITES[i][j] + ".png"), j * 128, i * 128);
//            }
//        }
//    }
//
//    private void render() {
//        drawMap(gc);
//        gameObjects.forEach(GameObject::render);
//    }
//
//    private void update() {
//        gameObjects.forEach(GameObject::update);
//    }
//}
//
//enum DIRECTION {
//    LEFT, UP, RIGHT, DOWN
//}
//
//abstract class GameObject {
//    double x = 500;
//    double y = 500;
//    double speed = 10.0;
//    GraphicsContext gc;
//
//    public GameObject(GraphicsContext gc) {
//        this.gc = gc;
//    }
//
//    public abstract void render();
//
//    public abstract void update();
//}
//
//class Tank extends GameObject {
//    int rotationBase = 0;
//    int rotationGun = 0;
//    double rotationSpeed = 1;
//    DIRECTION direction;
//    public Tank(GraphicsContext gc) {
//        super(gc);
//    }
//
//    @Override
//    public void render() {
//        ImageView iv = new ImageView(new Image("file:src/main/resources/AssetsKit_2/PNG/Retina/towerDefense_tile268.png"));
//        iv.setRotate(rotationBase);
//        SnapshotParameters params = new SnapshotParameters();
//        params.setFill(Color.TRANSPARENT);
//        Image base = iv.snapshot(params, null);
//
//        ImageView iv2 = new ImageView(new Image("file:src/main/resources/AssetsKit_2/PNG/Retina/towerDefense_tile291.png"));
//        iv2.setRotate(rotationGun);
//        Image gun = iv2.snapshot(params, null);
//
//        gc.drawImage(base, x, y);
//        gc.drawImage(gun, x, y);
////        gc.strokeRoundRect(x, y, 128, 128, 0,0);
//    }
//
//    @Override
//    public void update() {
////        x += speed;
//        rotationGun += rotationSpeed;
//    }
//}