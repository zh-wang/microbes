package com.android.livewallpaper.microbesgl;

public class Native {
    static  {
        System.loadLibrary("microbes_jni");
    }

    public static native void initRenderer();

    public static native void initScene();

    public static native void motion(float paramFloat1, float paramFloat2);

    public static native void render();

    public static native void setScrollInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);

    public static native void step(float paramFloat);

    public static native void touch(float paramFloat1, float paramFloat2);
}

