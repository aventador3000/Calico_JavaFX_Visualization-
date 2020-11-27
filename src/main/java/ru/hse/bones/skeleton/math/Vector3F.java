package ru.hse.bones.skeleton.math;

public class Vector3F extends Vector4F {

    public Vector3F(float[] data) {
        super(new float[]{data[0], data[1], data[2], 1.0f});
    }


}
