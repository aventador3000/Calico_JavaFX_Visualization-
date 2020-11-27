package ru.hse.bones;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.hse.bones.skeleton.Bone;
import ru.hse.bones.skeleton.Skeleton;
import ru.hse.bones.skeleton.math.Vector3F;
import ru.hse.bones.skeleton.math.Vector4F;

public class App extends Application {

    Skeleton skeleton = new Skeleton();

    public void start(Stage stage) throws Exception {

        final Bone bone = new Bone(1, "", null, 10, 0, 0);
        final Bone bone2 = new Bone(2, "", bone, 7, -40, 90);
        final Bone bone3 = new Bone(2, "", bone, 7, -40, 180 + 90);
        final Bone bone4 = new Bone(2, "", bone2, 7, 10, 180);
        final Bone bone5 = new Bone(2, "", bone3, 7, 10, 180);

        final Bone[] bones = new Bone[]{bone, bone2, bone3, bone4, bone5};
        final Cylinder[] cylinders = new Cylinder[5];

        for (int i = 0; i < 5; i++) {
            Bone currentBone = bones[i];

            final Cylinder cylinder = new Cylinder();
            cylinder.setHeight(currentBone.getLength());
            cylinder.setRadius(1);

            Vector4F positionA = currentBone.getPositionHalf();
            System.out.println(positionA);
            cylinder.setTranslateX(positionA.getY());
            cylinder.setTranslateY(-positionA.getX());
            cylinder.setTranslateZ(positionA.getZ());
            cylinder.setCullFace(CullFace.BACK);
            cylinder.setRotate(currentBone.getYaw());

            float pitch = currentBone.getPitchPi();
            float x = (float) Math.cos(pitch);
            float y = (float) Math.sin(pitch);
            cylinder.setRotationAxis(new Point3D(x, 0, y));
//- Math.cos(currentBone.getYawPi()) * currentBone.getLength()
            cylinders[i] = cylinder;
        }
//        final Cylinder cylinder = new Cylinder();
//        cylinder.setHeight(bone.getLength() * 50);
//        cylinder.setRadius(50);
//        cylinder.setTranslateX(200);
//        cylinder.setTranslateY(200);
//        cylinder.setCullFace(CullFace.BACK);
//        cylinder.setRotate(bone.getYaw());

//        Timeline timeline = new Timeline();
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.setAutoReverse(true);
//        final KeyValue kv = new KeyValue(new WritableValue<Integer>() {
//            public Integer getValue() {
//                return 5000;
//            }
//
//            public void setValue(Integer integer) {
//                float pitch = bone.getPitch();
//                float x = (float) Math.sin(pitch);
//                float y = (float) Math.cos(pitch);
//                Vector3F lookVector = bone.getLookVector2();
//                System.out.println(lookVector);
//                cylinder.setRotationAxis(new Point3D(0, x, y));
//                bone.incPitch();
//                System.out.println(123);
//            }
//        }, 5000);
//        timeline.setRate(0.001);

//        final KeyFrame kf = new KeyFrame(Duration.millis(50), event -> {
//            float pitch = bone.getPitchPi();
//            float x = (float) Math.cos(pitch);
//            float y = (float) Math.sin(pitch);
//            Vector3F lookVector = bone.getLookVector2();
////            System.out.println(lookVector);
//            System.out.println(pitch + " " + x + " " + y);
//            cylinder.setRotationAxis(new Point3D(x, 0, y));
//            bone.incPitch();
//        });
//        timeline.getKeyFrames().add(kf);
//        timeline.play();


//        //Drawing Sphere1
//        Sphere sphere1 = new Sphere();
//
//        //Setting the radius of the Sphere
//        sphere1.setRadius(50.0);
//
//        //Setting the position of the sphere
//        sphere1.setTranslateX(100);
//        sphere1.setTranslateY(150);
//        sphere1.setTranslateZ(150);
//        //setting the cull face of the sphere to front
//        sphere1.setCullFace(CullFace.FRONT);
//
//        //Drawing Sphere2
//        Sphere sphere2 = new Sphere();
//
//        //Setting the radius of the Sphere
//        sphere2.setRadius(50.0);
//
//        //Setting the position of the sphere
//        sphere2.setTranslateX(300);
//        sphere2.setTranslateY(150);
//
//        //Setting the cull face of the sphere to back
//        sphere2.setCullFace(CullFace.BACK);
//
//        //Drawing Sphere3
//        Sphere sphere3 = new Sphere();
//
//        //Setting the radius of the Sphere
//        sphere3.setRadius(50.0);
//
//        //Setting the position of the sphere
//        sphere3.setTranslateX(500);
//        sphere3.setTranslateY(150);
//
//        //Setting the cull face of the sphere to none
//        sphere2.setCullFace(CullFace.NONE);

        //Creating a Group object

        Translate pivot = new Translate();
        Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);

        // Create and position camera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll(
                pivot,
                yRotate,
                new Rotate(-20, Rotate.X_AXIS),
                new Translate(0, 0, -50)
        );
        // animate the camera position.
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(0),
                        new KeyValue(yRotate.angleProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(15),
                        new KeyValue(yRotate.angleProperty(), 360)
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Group root = new Group();

        root.getChildren().addAll(cylinders);
        Box box = new Box(2, 2, 2);
        root.getChildren().add(box);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300, true, SceneAntialiasing.BALANCED);

        scene.setCamera(camera);

        //Setting title to the Stage
        stage.setTitle("Human");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    void createHuman() {

    }

}
