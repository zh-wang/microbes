package zh.wang.android.microbes;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.DisplayMetrics;

import com.android.livewallpaper.microbesgl.Native;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {

    GLSurfaceView glSurfaceView;
    GLSurfaceView.Renderer mRenderer;

    int mWidth;
    int mHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        glSurfaceView = new GLSurfaceView(getApplicationContext());
        glSurfaceView.setEGLContextClientVersion(2);
        mRenderer = new MyGLRenderer();
        glSurfaceView.setRenderer(mRenderer);
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setContentView(glSurfaceView);
    }

    private void sendScrollInfo() {
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();

        Native.setScrollInfo(
                displayMetrics.widthPixels,
                displayMetrics.heightPixels,
                this.mWidth,
                this.mHeight,
                0);
    }

    public class MyGLRenderer implements GLSurfaceView.Renderer {

        private long mLastTime;

        @Override
        public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
            this.mLastTime = SystemClock.elapsedRealtime();
            sendScrollInfo();
            Native.initRenderer();
            Native.initScene();
        }

        @Override
        public void onSurfaceChanged(GL10 gl10, int width, int height) {
            mWidth = width;
            mHeight = height;
            sendScrollInfo();
        }

        @Override
        public void onDrawFrame(GL10 gl10) {
            long l = SystemClock.elapsedRealtime();
            float f = Math.min(0.1F, (float)(l - this.mLastTime) / 1000.0F);
            this.mLastTime = l;
            Native.step(f);
            Native.render();
        }
    }
}
