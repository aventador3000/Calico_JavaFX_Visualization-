package ru.hse.bones.skeleton;

import ru.hse.bones.skeleton.math.Matrix4f;
import ru.hse.bones.skeleton.math.Utils;
import ru.hse.bones.skeleton.math.Vector3F;
import ru.hse.bones.skeleton.math.Vector4F;

public final class Bone {

    private final int id;
    private final String name;
    private final Bone previous;
    private final float length;

    private final Vector3F shiftPosition = new Vector3F(new float[]{0.0f, 0.0f, 0.0f});
    private float yaw;
    private float pitch;

    public Bone(int id, String name, Bone previous, float length, float yaw, float pitch) {
        this.id = id;
        this.name = name;
        this.previous = previous;
        this.length = length;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Bone getPrevious() {
        return previous;
    }

    public float getLength() {
        return length;
    }

    public Vector3F getShiftPosition() {
        return shiftPosition;
    }

    public float getYaw() {
        return yaw;
    }

    public float getYawPi() {
        return (float) (yaw  * Math.PI / 180.0);
    }

    public float getPitch() {
        return pitch;
    }

    public float getPitchPi() {
        return (float) (pitch * Math.PI / 180.0);
    }

    public Vector3F getLookVector() {
        return new Vector3F(new float[]{
                (float) (Math.cos(pitch * Math.PI / 180.0) * Math.cos(yaw * Math.PI / 180.0)),
                (float) Math.sin(pitch * Math.PI / 180.0),
                (float) (Math.cos(pitch * Math.PI / 180.0) * Math.sin(yaw * Math.PI / 180.0))
        });
    }

    public Vector3F getLengthLookVector() {
        return new Vector3F(new float[]{
                (float) (Math.cos(pitch * Math.PI / 180.0) * Math.cos(yaw * Math.PI / 180.0)) * length,
                (float) Math.sin(pitch * Math.PI / 180.0) * length,
                (float) (Math.cos(pitch * Math.PI / 180.0) * Math.sin(yaw * Math.PI / 180.0)) * length
        });
    }

    public Vector3F getHalfLengthLookVector() {
        return new Vector3F(new float[]{
                (float) ((Math.cos(pitch * Math.PI / 180.0) * Math.cos(yaw * Math.PI / 180.0)) * (length / 2.0)),
                (float) (Math.sin(pitch * Math.PI / 180.0) * (length / 2.0)),
                (float) ((Math.cos(pitch * Math.PI / 180.0) * Math.sin(yaw * Math.PI / 180.0)) * (length / 2.0))
        });
    }

    public Vector3F getRecLookVector() {
        Vector3F vector =  new Vector3F(new float[]{0.0f, 0.0f, 0.0f});
        Vector3F local = getLengthLookVector();
        return new Vector3F(new float[]{
                (float) ((vector.getX() + local.getX())),
                (float) ((vector.getY() + local.getY())),
                (float) ((vector.getZ() + local.getZ()))
        });
    }

    public Vector3F getRecHalfLookVector() {
        Vector3F vector = getRecLookVector();
        return new Vector3F(new float[]{
                (float) ((vector.getX()) / 2.0),
                (float) ((vector.getY()) / 2.0) ,
                (float) ((vector.getZ()) / 2.0),
        });
    }

    public void incPitch() {
        pitch += 1;
    }

    public Matrix4f matrix() {
        Matrix4f matrix4f = previous == null ? Matrix4f.IDENTITY : previous.matrix();
        return matrix4f.mul(Utils.translate(getLengthLookVector()));
    }

    public Vector4F getPositionA() {
        Matrix4f matrix4f = previous == null ? Matrix4f.IDENTITY : previous.matrix();
        return matrix4f.mul(shiftPosition);
    }

    public Vector4F getPositionHalf() {
        Matrix4f matrix4f = previous == null ? Matrix4f.IDENTITY : previous.matrix();
        Matrix4f translate = Utils.translate(getHalfLengthLookVector());
        return matrix4f.mul(translate).mul(shiftPosition);
    }

    public void getPositionB() {

    }
//    public start
}
