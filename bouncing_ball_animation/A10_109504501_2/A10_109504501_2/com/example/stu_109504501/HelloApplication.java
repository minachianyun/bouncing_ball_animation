package com.example.stu_109504501;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelloApplication extends Application {
    // 球的移動速度
    static int dx = 4;
    static int dy = 3;

    @Override
    public void start(final Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 300, 300, Color.BLACK);
        stage.setTitle("Ball Ani");
        stage.setScene(scene);
        stage.show();

        bounce_ball(scene);
    }

    private void bounce_ball(final Scene scene) {

        final Circle ball = new Circle(100, 100, 20, Color.GREENYELLOW);
        final Group root = (Group) scene.getRoot();
        root.getChildren().add(ball);

        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(.0200),
                (ActionEvent event) -> {
                    double xMin = ball.getBoundsInParent().getMinX();
                    double yMin = ball.getBoundsInParent().getMinY();
                    double xMax = ball.getBoundsInParent().getMaxX();
                    double yMax = ball.getBoundsInParent().getMaxY();

                    // Collision - boundaries
                    if(xMin < 0 || xMax > scene.getWidth()){
                        dx = dx * -1;
                    }
                    if(yMin < 0 || yMax > scene.getHeight()){
                        dy = dy * -1;
                    }

                    ball.setTranslateX(ball.getTranslateX() + dx);
                    ball.setTranslateY(ball.getTranslateY() + dy);
                });

        tl.getKeyFrames().add(moveBall);
        tl.play();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
