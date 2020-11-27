package ru.hse.bones.skeleton.math;

public class Matrix4f {

    public static final Matrix4f IDENTITY = getIdentityMatrix();

    private final float[][] data;

    public Matrix4f(float[][] data) {
        this.data = data;
    }

    private static Matrix4f getIdentityMatrix() {
        float[][] ints = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        return new Matrix4f(ints);
    }

    public Matrix4f mul(Matrix4f rhs) {
        float[][] res = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                float a = 0;
                for (int k = 0; k < 4; k++) {
                    a += data[i][k] * rhs.data[k][j];
                }
                res[i][j] = a;
            }
        }
        return new Matrix4f(res);
    }

    public Vector4F mul(Vector4F rhs) {
        float[][] m = data;
        float[] v = rhs.getData();
        return new Vector4F(new float[]{
                m[0][0] * v[0] + m[0][1] * v[1] + m[0][2] * v[2] + m[0][3] * v[3],
                m[1][0] * v[0] + m[1][1] * v[1] + m[1][2] * v[2] + m[1][3] * v[3],
                m[2][0] * v[0] + m[2][1] * v[1] + m[2][2] * v[2] + m[2][3] * v[3],
                m[3][0] * v[0] + m[3][1] * v[1] + m[3][2] * v[2] + m[3][3] * v[3],
        });
    }

}
