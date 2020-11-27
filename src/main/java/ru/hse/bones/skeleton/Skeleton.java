package ru.hse.bones.skeleton;

import java.util.ArrayList;

public class Skeleton {

    private final ArrayList<Bone> bones = new ArrayList<Bone>();

    public void addBone(Bone bone) {
        bones.add(bone);
    }

    public ArrayList<Bone> getBones() {
        return bones;
    }
}
