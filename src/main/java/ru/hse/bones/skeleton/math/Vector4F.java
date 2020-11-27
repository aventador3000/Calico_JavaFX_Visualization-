package ru.hse.bones.skeleton.math;

import java.util.Arrays;

public class Vector4F {

    private final float[] data;

    public Vector4F(float[] data) {
        this.data = data;
    }

    public float[] getData() {
        return data;
    }

    public float getX() {
        return data[0];
    }

    public float getY() {
        return data[1];
    }

    public float getZ() {
        return data[2];
    }

    public float getW() {
        return data[0];
    }

    public Vector4F mul(float rhs) {
        return new Vector4F(new float[]{
                data[0] * rhs,
                data[1] * rhs,
                data[2] * rhs,
                data[3] * rhs
        });
    }

    @Override
    public String toString() {
        return "Vector4F{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
