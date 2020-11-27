package ru.hse.bones.skeleton.math;

public class Utils {

    private Utils() {
    }

    public static Matrix4f rotate(float angle, Vector3F xyz) {
        float c = (float) Math.cos(angle);
        float s = (float) Math.sin(angle);

        float x = xyz.getX();
        float y = xyz.getY();
        float z = xyz.getZ();

        float[][] floats = {
                {1.0f + y * c + z * c, -z * s, y * s, 0.0f},
                {z * s, 1.0f + x * c + z * c, -x * s, 0.0f},
                {-y * s, x * s, 1.0f + x * c + y * c, 0.0f},
                {0.0f, 0.0f, 0.0f, 1.0f}
        };
        return new Matrix4f(floats);
    }

    public static Matrix4f translate(Vector4F xyz) {
        float x = xyz.getX();
        float y = xyz.getY();
        float z = xyz.getZ();

        float[][] floats = {
                {1.0f, 0.0f, 0.0f, x},
                {0.0f, 1.0f, 0.0f, y},
                {0.0f, 0.0f, 1.0f, z},
                {0.0f, 0.0f, 0.0f, 1.0f}
        };
        return new Matrix4f(floats);
    }


    //#[allow(dead_code)]
    //#[inline(always)]
    //pub fn translate(xyz: Vec3) -> Matrix4x4 {
    //    let x = xyz.x();
    //    let y = xyz.y();
    //    let z = xyz.z();
    //
    //    Matrix4x4([
    //        [1.0, 0.0, 0.0, x],
    //        [0.0, 1.0, 0.0, y],
    //        [0.0, 0.0, 1.0, z],
    //        [0.0, 0.0, 0.0, 1.0],
    //    ])
    //}

}
