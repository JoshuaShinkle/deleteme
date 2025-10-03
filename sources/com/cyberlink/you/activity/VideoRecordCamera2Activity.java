package com.cyberlink.you.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Size;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.widgetpool.common.FixedAspectRatioFrameLayout;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import p116k4.C5187v0;

@TargetApi(23)
/* loaded from: classes.dex */
public class VideoRecordCamera2Activity extends BaseActivity {

    /* renamed from: B */
    public View f9583B;

    /* renamed from: C */
    public Handler f9584C;

    /* renamed from: D */
    public HandlerThread f9585D;

    /* renamed from: E */
    public CaptureRequest.Builder f9586E;

    /* renamed from: F */
    public TextureView f9587F;

    /* renamed from: G */
    public Integer f9588G;

    /* renamed from: I */
    public CameraDevice f9590I;

    /* renamed from: J */
    public CameraCaptureSession f9591J;

    /* renamed from: K */
    public int f9592K;

    /* renamed from: c */
    public Size f9606c;

    /* renamed from: d */
    public Size f9607d;

    /* renamed from: e */
    public String f9608e;

    /* renamed from: f */
    public Uri f9609f;

    /* renamed from: h */
    public MediaRecorder f9611h;

    /* renamed from: j */
    public OrientationEventListener f9613j;

    /* renamed from: o */
    public int f9618o;

    /* renamed from: p */
    public long f9619p;

    /* renamed from: q */
    public FixedAspectRatioFrameLayout f9620q;

    /* renamed from: r */
    public View f9621r;

    /* renamed from: s */
    public View f9622s;

    /* renamed from: t */
    public View f9623t;

    /* renamed from: u */
    public ImageView f9624u;

    /* renamed from: v */
    public Button f9625v;

    /* renamed from: w */
    public Button f9626w;

    /* renamed from: x */
    public TextView f9627x;

    /* renamed from: y */
    public VideoView f9628y;

    /* renamed from: z */
    public MediaController f9629z;

    /* renamed from: g */
    public boolean f9610g = false;

    /* renamed from: i */
    public int f9612i = 0;

    /* renamed from: k */
    public boolean f9614k = false;

    /* renamed from: l */
    public boolean f9615l = false;

    /* renamed from: m */
    public boolean f9616m = false;

    /* renamed from: n */
    public boolean f9617n = false;

    /* renamed from: A */
    public Handler f9582A = new Handler();

    /* renamed from: H */
    public Semaphore f9589H = new Semaphore(1);

    /* renamed from: L */
    public boolean f9593L = false;

    /* renamed from: M */
    public TextureView.SurfaceTextureListener f9594M = new TextureViewSurfaceTextureListenerC1846f();

    /* renamed from: N */
    public CameraDevice.StateCallback f9595N = new C1847g();

    /* renamed from: O */
    public Runnable f9596O = new RunnableC1848h();

    /* renamed from: P */
    public View.OnClickListener f9597P = new View.OnClickListener() { // from class: com.cyberlink.you.activity.mm
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10909b.m10886C0(view);
        }
    };

    /* renamed from: Q */
    public View.OnClickListener f9598Q = new View.OnClickListener() { // from class: com.cyberlink.you.activity.nm
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10936b.m10888D0(view);
        }
    };

    /* renamed from: R */
    public View.OnClickListener f9599R = new ViewOnClickListenerC1849i();

    /* renamed from: S */
    public View.OnClickListener f9600S = new ViewOnClickListenerC1850j();

    /* renamed from: T */
    public View.OnClickListener f9601T = new ViewOnClickListenerC1851k();

    /* renamed from: U */
    public MediaRecorder.OnInfoListener f9602U = new C1852l();

    /* renamed from: V */
    public View.OnClickListener f9603V = new ViewOnClickListenerC1853m();

    /* renamed from: W */
    public MediaPlayer.OnPreparedListener f9604W = new C1854n();

    /* renamed from: X */
    public MediaPlayer.OnCompletionListener f9605X = new C1841a();

    /* renamed from: com.cyberlink.you.activity.VideoRecordCamera2Activity$a */
    public class C1841a implements MediaPlayer.OnCompletionListener {
        public C1841a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m10952b() {
            if (VideoRecordCamera2Activity.this.f9628y != null) {
                VideoRecordCamera2Activity.this.f9628y.seekTo(0);
            }
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            VideoRecordCamera2Activity.this.f9582A.post(new Runnable() { // from class: com.cyberlink.you.activity.om
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11023b.m10952b();
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordCamera2Activity$b */
    public class C1842b extends OrientationEventListener {
        public C1842b(Context context, int i9) {
            super(context, i9);
        }

        @Override // android.view.OrientationEventListener
        public void onOrientationChanged(int i9) {
            int i10;
            if (i9 == -1 || (i10 = (360 - ((((i9 + 45) % 360) / 90) * 90)) % 360) == VideoRecordCamera2Activity.this.f9612i) {
                return;
            }
            VideoRecordCamera2Activity.this.f9612i = i10;
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordCamera2Activity$c */
    public class C1843c extends CameraCaptureSession.StateCallback {
        public C1843c() {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
            Toast.makeText(VideoRecordCamera2Activity.this.m10944r0(), "Failed", 0).show();
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession cameraCaptureSession) throws CameraAccessException {
            VideoRecordCamera2Activity.this.f9591J = cameraCaptureSession;
            VideoRecordCamera2Activity.this.m10940Q0();
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordCamera2Activity$d */
    public class C1844d extends MediaController {
        public C1844d(Context context) {
            super(context);
        }

        @Override // android.widget.MediaController, android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == 4) {
                ((Activity) getContext()).finish();
            }
            return super.dispatchKeyEvent(keyEvent);
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordCamera2Activity$e */
    public class C1845e extends CameraCaptureSession.StateCallback {
        public C1845e() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m10954b() throws IllegalStateException {
            VideoRecordCamera2Activity.this.f9624u.setImageResource(R.drawable.video_btn_stop_n);
            VideoRecordCamera2Activity.this.f9619p = System.currentTimeMillis();
            VideoRecordCamera2Activity.this.f9627x.setVisibility(0);
            VideoRecordCamera2Activity.this.f9582A.post(VideoRecordCamera2Activity.this.f9596O);
            VideoRecordCamera2Activity.this.f9611h.start();
            VideoRecordCamera2Activity.this.f9615l = true;
            VideoRecordCamera2Activity.this.f9614k = false;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
            Log.d("VideoRecordActivity", "startRecording createCaptureSession failed");
            VideoRecordCamera2Activity.this.f9614k = false;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession cameraCaptureSession) throws CameraAccessException {
            VideoRecordCamera2Activity.this.f9591J = cameraCaptureSession;
            VideoRecordCamera2Activity.this.m10940Q0();
            VideoRecordCamera2Activity.this.m10944r0().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.pm
                @Override // java.lang.Runnable
                public final void run() throws IllegalStateException {
                    this.f11054b.m10954b();
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordCamera2Activity$f */
    public class TextureViewSurfaceTextureListenerC1846f implements TextureView.SurfaceTextureListener {
        public TextureViewSurfaceTextureListenerC1846f() {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i9, int i10) throws IllegalStateException, InterruptedException, CameraAccessException, IOException {
            VideoRecordCamera2Activity.this.m10928E0();
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i9, int i10) {
            VideoRecordCamera2Activity.this.m10943q0(i9, i10);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordCamera2Activity$g */
    public class C1847g extends CameraDevice.StateCallback {
        public C1847g() {
        }

        /* renamed from: a */
        public final void m10955a(CameraDevice cameraDevice) {
            VideoRecordCamera2Activity.this.f9589H.release();
            cameraDevice.close();
            VideoRecordCamera2Activity.this.f9590I = null;
            VideoRecordCamera2Activity.this.f9621r.setEnabled(true);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
            m10955a(cameraDevice);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int i9) {
            m10955a(cameraDevice);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice cameraDevice) throws CameraAccessException {
            VideoRecordCamera2Activity.this.f9590I = cameraDevice;
            VideoRecordCamera2Activity.this.m10936M0();
            VideoRecordCamera2Activity.this.f9589H.release();
            if (VideoRecordCamera2Activity.this.f9587F != null) {
                VideoRecordCamera2Activity videoRecordCamera2Activity = VideoRecordCamera2Activity.this;
                videoRecordCamera2Activity.m10943q0(videoRecordCamera2Activity.f9587F.getWidth(), VideoRecordCamera2Activity.this.f9587F.getHeight());
            }
            VideoRecordCamera2Activity.this.f9621r.setEnabled(true);
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordCamera2Activity$h */
    public class RunnableC1848h implements Runnable {
        public RunnableC1848h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (VideoRecordCamera2Activity.this.f9619p != 0) {
                VideoRecordCamera2Activity.this.f9627x.setText(VideoRecordCamera2Activity.this.m10946u0((int) ((System.currentTimeMillis() - VideoRecordCamera2Activity.this.f9619p) / 1000)));
                VideoRecordCamera2Activity.this.f9582A.postDelayed(VideoRecordCamera2Activity.this.f9596O, 1000L);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordCamera2Activity$i */
    public class ViewOnClickListenerC1849i implements View.OnClickListener {
        public ViewOnClickListenerC1849i() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws IllegalStateException, InterruptedException, CameraAccessException, IOException {
            if (VideoRecordCamera2Activity.this.f9587F.isAvailable()) {
                VideoRecordCamera2Activity.this.m10928E0();
            } else {
                VideoRecordCamera2Activity.this.f9587F.setSurfaceTextureListener(VideoRecordCamera2Activity.this.f9594M);
            }
            VideoRecordCamera2Activity.this.m10950z0();
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordCamera2Activity$j */
    public class ViewOnClickListenerC1850j implements View.OnClickListener {
        public ViewOnClickListenerC1850j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws CameraAccessException {
            if (VideoRecordCamera2Activity.this.f9610g) {
                return;
            }
            try {
                VideoRecordCamera2Activity videoRecordCamera2Activity = VideoRecordCamera2Activity.this;
                videoRecordCamera2Activity.f9593L = !videoRecordCamera2Activity.f9593L;
                VideoRecordCamera2Activity.this.f9622s.setActivated(VideoRecordCamera2Activity.this.f9593L);
                VideoRecordCamera2Activity.this.f9586E.set(CaptureRequest.FLASH_MODE, Integer.valueOf(VideoRecordCamera2Activity.this.f9593L ? 2 : 0));
                VideoRecordCamera2Activity.this.f9591J.setRepeatingRequest(VideoRecordCamera2Activity.this.f9586E.build(), null, VideoRecordCamera2Activity.this.f9584C);
            } catch (CameraAccessException e9) {
                e9.printStackTrace();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordCamera2Activity$k */
    public class ViewOnClickListenerC1851k implements View.OnClickListener {
        public ViewOnClickListenerC1851k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws IllegalStateException, InterruptedException, CameraAccessException, IOException {
            VideoRecordCamera2Activity.this.f9621r.setEnabled(false);
            VideoRecordCamera2Activity.this.m10941o0();
            VideoRecordCamera2Activity.this.f9610g = !r2.f9610g;
            if (VideoRecordCamera2Activity.this.f9587F.isAvailable()) {
                VideoRecordCamera2Activity.this.m10928E0();
            } else {
                VideoRecordCamera2Activity.this.f9587F.setSurfaceTextureListener(VideoRecordCamera2Activity.this.f9594M);
            }
            VideoRecordCamera2Activity.this.m10950z0();
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordCamera2Activity$l */
    public class C1852l implements MediaRecorder.OnInfoListener {
        public C1852l() {
        }

        @Override // android.media.MediaRecorder.OnInfoListener
        public void onInfo(MediaRecorder mediaRecorder, int i9, int i10) {
            if (i9 == 800) {
                try {
                    if (!VideoRecordCamera2Activity.this.f9615l) {
                        VideoRecordCamera2Activity.this.m10937N0();
                        return;
                    }
                    VideoRecordCamera2Activity.this.m10939P0(true);
                    if (!VideoRecordCamera2Activity.this.m10947w0()) {
                        if (VideoRecordCamera2Activity.this.f9587F.isAvailable()) {
                            VideoRecordCamera2Activity.this.m10928E0();
                        } else {
                            VideoRecordCamera2Activity.this.f9587F.setSurfaceTextureListener(VideoRecordCamera2Activity.this.f9594M);
                        }
                    }
                    C5187v0.m20268d(String.format(VideoRecordCamera2Activity.this.m10944r0().getResources().getString(R.string.record_videos_reach_max), String.valueOf(3)));
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordCamera2Activity$m */
    public class ViewOnClickListenerC1853m implements View.OnClickListener {
        public ViewOnClickListenerC1853m() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m10957b() {
            VideoRecordCamera2Activity.this.f9621r.setVisibility(8);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                if (VideoRecordCamera2Activity.this.f9615l) {
                    VideoRecordCamera2Activity.this.m10939P0(true);
                    if (!VideoRecordCamera2Activity.this.m10947w0()) {
                        if (VideoRecordCamera2Activity.this.f9587F.isAvailable()) {
                            VideoRecordCamera2Activity.this.m10928E0();
                        } else {
                            VideoRecordCamera2Activity.this.f9587F.setSurfaceTextureListener(VideoRecordCamera2Activity.this.f9594M);
                        }
                    }
                } else {
                    VideoRecordCamera2Activity.this.m10944r0().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.qm
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f11127b.m10957b();
                        }
                    });
                    VideoRecordCamera2Activity.this.m10937N0();
                }
            } catch (Exception e9) {
                Log.e("VideoRecordActivity", "click record exception:" + e9);
                e9.printStackTrace();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordCamera2Activity$n */
    public class C1854n implements MediaPlayer.OnPreparedListener {
        public C1854n() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m10959b() {
            if (VideoRecordCamera2Activity.this.f9628y == null || VideoRecordCamera2Activity.this.f9629z == null) {
                return;
            }
            VideoRecordCamera2Activity.this.f9628y.setMediaController(VideoRecordCamera2Activity.this.f9629z);
            VideoRecordCamera2Activity.this.f9628y.seekTo(0);
            VideoRecordCamera2Activity.this.f9629z.show();
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            VideoRecordCamera2Activity.this.f9582A.post(new Runnable() { // from class: com.cyberlink.you.activity.rm
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11157b.m10959b();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C0 */
    public /* synthetic */ void m10886C0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D0 */
    public /* synthetic */ void m10888D0(View view) {
        Intent intent = new Intent();
        Uri uri = this.f9609f;
        if (uri != null) {
            intent.putExtra("videoUri", uri.toString());
        }
        setResult(-1, intent);
        finish();
    }

    /* renamed from: n0 */
    public static Size m10918n0(Size[] sizeArr, int i9, int i10) {
        int iAbs;
        Size size = null;
        if (sizeArr == null) {
            return null;
        }
        int iAbs2 = 0;
        for (Size size2 : sizeArr) {
            if (size == null) {
                iAbs2 = Math.abs(size2.getWidth() - i9) + Math.abs(size2.getHeight() - i10);
                size = size2;
            } else if (size2.getWidth() >= i9 && size2.getHeight() >= i10 && size2.getHeight() == (size2.getWidth() * i10) / i9 && (iAbs = Math.abs(size2.getWidth() - i9) + Math.abs(size2.getHeight() - i10)) < iAbs2) {
                size = size2;
                iAbs2 = iAbs;
            }
        }
        return size;
    }

    /* renamed from: E0 */
    public final void m10928E0() throws IllegalStateException, InterruptedException, CameraAccessException, IOException {
        m10939P0(false);
        m10949y0();
        this.f9625v.setVisibility(8);
        this.f9626w.setVisibility(8);
        this.f9624u.setVisibility(0);
        View view = this.f9583B;
        if (view != null) {
            ((RelativeLayout.LayoutParams) view.getLayoutParams()).removeRule(3);
        }
        m10948x0(480, 360);
        CameraManager cameraManager = (CameraManager) m10944r0().getSystemService("camera");
        try {
            if (!this.f9589H.tryAcquire(2500L, TimeUnit.MILLISECONDS)) {
                throw new RuntimeException("Time out waiting to lock camera opening.");
            }
            this.f9592K = cameraManager.getCameraIdList().length;
            String str = cameraManager.getCameraIdList()[0];
            if (this.f9610g && this.f9592K > 1) {
                str = cameraManager.getCameraIdList()[1];
            }
            CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(str);
            StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            this.f9588G = (Integer) cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
            if (streamConfigurationMap == null) {
                throw new RuntimeException("Cannot get available preview/video sizes");
            }
            Size sizeM10918n0 = m10918n0(streamConfigurationMap.getOutputSizes(MediaRecorder.class), 480, 360);
            this.f9606c = sizeM10918n0;
            this.f9607d = sizeM10918n0;
            m10943q0(480, 360);
            cameraManager.openCamera(str, this.f9595N, (Handler) null);
            m10950z0();
            this.f9587F.setVisibility(0);
        } catch (CameraAccessException unused) {
            Toast.makeText(m10944r0(), "Cannot access the camera.", 0).show();
            m10944r0().finish();
        } catch (InterruptedException unused2) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while trying to lock camera opening.");
        } catch (NullPointerException unused3) {
        }
    }

    /* renamed from: F0 */
    public final void m10929F0() {
        VideoView videoView;
        if (!this.f9616m || (videoView = this.f9628y) == null) {
            return;
        }
        this.f9618o = videoView.getCurrentPosition();
        this.f9617n = !this.f9628y.isPlaying();
        this.f9628y.pause();
    }

    /* renamed from: G0 */
    public final void m10930G0() {
        MediaRecorder mediaRecorder = this.f9611h;
        if (mediaRecorder != null) {
            mediaRecorder.reset();
            this.f9611h.release();
            this.f9611h = null;
        }
    }

    /* renamed from: H0 */
    public final void m10931H0() throws IllegalStateException, InterruptedException, CameraAccessException, IOException {
        VideoView videoView;
        if (!this.f9616m || (videoView = this.f9628y) == null) {
            if (this.f9587F.isAvailable()) {
                m10928E0();
                return;
            } else {
                this.f9587F.setSurfaceTextureListener(this.f9594M);
                return;
            }
        }
        videoView.seekTo(this.f9618o);
        if (this.f9617n) {
            return;
        }
        this.f9628y.start();
    }

    /* renamed from: I0 */
    public final void m10932I0() throws IOException {
        ContentResolver contentResolver = getContentResolver();
        File file = new File(this.f9608e);
        if (!file.exists()) {
            Log.d("VideoRecordActivity", "saveFileInMediaStore not exist");
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, file.getName());
        contentValues.put("_display_name", file.getName());
        contentValues.put("mime_type", MimeTypes.VIDEO_MP4);
        long jCurrentTimeMillis = System.currentTimeMillis();
        contentValues.put("date_added", Long.valueOf(jCurrentTimeMillis));
        contentValues.put("date_modified", Long.valueOf(jCurrentTimeMillis));
        contentValues.put("duration", Long.valueOf(CLUtility.m16440G0(this.f9608e, null)));
        String strM16444H0 = CLUtility.m16444H0(MimeTypes.BASE_TYPE_VIDEO);
        if (Build.VERSION.SDK_INT >= 29) {
            contentValues.put("relative_path", strM16444H0);
        } else {
            contentValues.put("_data", strM16444H0 + File.separator + "U_" + CLUtility.m16443H() + ".mp4");
        }
        Uri uriInsert = contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
        this.f9609f = uriInsert;
        if (uriInsert != null) {
            try {
                OutputStream outputStreamOpenOutputStream = contentResolver.openOutputStream(uriInsert);
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        byte[] bArr = new byte[UserMetadata.MAX_INTERNAL_KEY_SIZE];
                        while (true) {
                            int i9 = fileInputStream.read(bArr);
                            if (i9 <= 0) {
                                break;
                            } else {
                                outputStreamOpenOutputStream.write(bArr, 0, i9);
                            }
                        }
                        fileInputStream.close();
                        if (outputStreamOpenOutputStream != null) {
                            outputStreamOpenOutputStream.close();
                        }
                    } finally {
                    }
                } finally {
                }
            } catch (Exception e9) {
                contentResolver.delete(this.f9609f, null, null);
                this.f9609f = null;
                Log.d("VideoRecordActivity", "saveFileInMediaStore ex:" + e9.getMessage());
            }
        }
    }

    /* renamed from: J0 */
    public final void m10933J0(CaptureRequest.Builder builder) {
        builder.set(CaptureRequest.CONTROL_MODE, 1);
        builder.set(CaptureRequest.FLASH_MODE, Integer.valueOf(this.f9593L ? 2 : 0));
    }

    /* renamed from: K0 */
    public final void m10934K0() throws IllegalStateException, IOException, IllegalArgumentException {
        MediaRecorder mediaRecorder = new MediaRecorder();
        this.f9611h = mediaRecorder;
        mediaRecorder.setOnInfoListener(this.f9602U);
        this.f9611h.setAudioSource(1);
        this.f9611h.setVideoSource(2);
        this.f9611h.setVideoEncodingBitRate(819200);
        this.f9611h.setAudioEncodingBitRate(C3322C.DEFAULT_BUFFER_SEGMENT_SIZE);
        this.f9611h.setOutputFormat(2);
        this.f9611h.setVideoFrameRate(30);
        this.f9611h.setVideoSize(this.f9606c.getWidth(), this.f9606c.getHeight());
        this.f9611h.setVideoEncoder(2);
        this.f9611h.setAudioEncoder(3);
        this.f9611h.setOutputFile(this.f9608e);
        this.f9611h.setMaxDuration(180000);
        try {
            this.f9611h.setOrientationHint(this.f9610g ? (this.f9588G.intValue() + this.f9612i) % 360 : ((this.f9588G.intValue() + 360) - this.f9612i) % 360);
        } catch (IllegalStateException e9) {
            Log.w("VideoRecordActivity", "Cannot setOrientationHint ", e9);
        }
        this.f9611h.prepare();
    }

    /* renamed from: L0 */
    public final void m10935L0() {
        HandlerThread handlerThread = new HandlerThread("CameraBackground");
        this.f9585D = handlerThread;
        handlerThread.start();
        this.f9584C = new Handler(this.f9585D.getLooper());
    }

    /* renamed from: M0 */
    public final void m10936M0() throws CameraAccessException {
        if (this.f9590I == null || !this.f9587F.isAvailable() || this.f9607d == null) {
            return;
        }
        try {
            m10942p0();
            SurfaceTexture surfaceTexture = this.f9587F.getSurfaceTexture();
            surfaceTexture.setDefaultBufferSize(this.f9607d.getWidth(), this.f9607d.getHeight());
            this.f9586E = this.f9590I.createCaptureRequest(1);
            Surface surface = new Surface(surfaceTexture);
            this.f9586E.addTarget(surface);
            this.f9590I.createCaptureSession(Collections.singletonList(surface), new C1843c(), this.f9584C);
        } catch (CameraAccessException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: N0 */
    public final void m10937N0() throws CameraAccessException {
        if (this.f9615l || this.f9590I == null || !this.f9587F.isAvailable() || this.f9607d == null) {
            return;
        }
        try {
            if (this.f9614k) {
                return;
            }
            this.f9614k = true;
            Log.i("VideoRecordActivity", "startRecording ");
            m10942p0();
            m10934K0();
            SurfaceTexture surfaceTexture = this.f9587F.getSurfaceTexture();
            surfaceTexture.setDefaultBufferSize(this.f9607d.getWidth(), this.f9607d.getHeight());
            this.f9586E = this.f9590I.createCaptureRequest(1);
            ArrayList arrayList = new ArrayList();
            Surface surface = new Surface(surfaceTexture);
            arrayList.add(surface);
            this.f9586E.addTarget(surface);
            Surface surface2 = this.f9611h.getSurface();
            arrayList.add(surface2);
            this.f9586E.addTarget(surface2);
            this.f9590I.createCaptureSession(arrayList, new C1845e(), this.f9584C);
        } catch (CameraAccessException | IOException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: O0 */
    public final void m10938O0() throws InterruptedException {
        this.f9585D.quitSafely();
        try {
            this.f9585D.join();
            this.f9585D = null;
            this.f9584C = null;
        } catch (InterruptedException e9) {
            Thread.currentThread().interrupt();
            e9.printStackTrace();
        }
    }

    /* renamed from: P0 */
    public final void m10939P0(boolean z8) throws IllegalStateException, InterruptedException, CameraAccessException, IOException {
        if (this.f9615l) {
            try {
                this.f9591J.stopRepeating();
                this.f9591J.abortCaptures();
                Thread.sleep(100L);
            } catch (CameraAccessException | InterruptedException e9) {
                Thread.currentThread().interrupt();
                e9.printStackTrace();
            }
            this.f9611h.setOnInfoListener(null);
            try {
                this.f9611h.stop();
            } catch (Exception e10) {
                e10.printStackTrace();
            }
            if (CLUtility.m16440G0(this.f9608e, this.f9609f) < 1000) {
                new File(this.f9608e).delete();
                if (z8) {
                    C5187v0.m20268d(getString(R.string.videos_too_short));
                }
            } else {
                m10932I0();
            }
            m10930G0();
            this.f9624u.setImageResource(R.drawable.image_selector_video_record_btn);
            this.f9615l = false;
            this.f9619p = 0L;
            this.f9627x.setVisibility(8);
        }
    }

    /* renamed from: Q0 */
    public final void m10940Q0() throws CameraAccessException {
        if (this.f9590I == null) {
            return;
        }
        try {
            m10933J0(this.f9586E);
            this.f9591J.setRepeatingRequest(this.f9586E.build(), null, this.f9584C);
        } catch (CameraAccessException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: o0 */
    public final void m10941o0() {
        try {
            try {
                this.f9589H.acquire();
                m10942p0();
                CameraDevice cameraDevice = this.f9590I;
                if (cameraDevice != null) {
                    cameraDevice.close();
                    this.f9590I = null;
                }
                MediaRecorder mediaRecorder = this.f9611h;
                if (mediaRecorder != null) {
                    mediaRecorder.release();
                    this.f9611h = null;
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while trying to lock camera closing.");
            }
        } finally {
            this.f9589H.release();
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_video_record);
        Intent intent = getIntent();
        this.f9608e = intent.hasExtra("videoPath") ? intent.getStringExtra("videoPath") : CLUtility.m16581r1();
        this.f9620q = (FixedAspectRatioFrameLayout) findViewById(R.id.camera_preview);
        View viewFindViewById = findViewById(R.id.switch_camera);
        this.f9621r = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f9601T);
        View viewFindViewById2 = findViewById(R.id.back);
        this.f9623t = viewFindViewById2;
        viewFindViewById2.setOnClickListener(this.f9597P);
        View viewFindViewById3 = findViewById(R.id.flash);
        this.f9622s = viewFindViewById3;
        viewFindViewById3.setOnClickListener(this.f9600S);
        ImageView imageView = (ImageView) findViewById(R.id.button_capture);
        this.f9624u = imageView;
        imageView.setOnClickListener(this.f9603V);
        Button button = (Button) findViewById(R.id.button_retry);
        this.f9625v = button;
        button.setOnClickListener(this.f9599R);
        Button button2 = (Button) findViewById(R.id.button_send);
        this.f9626w = button2;
        button2.setOnClickListener(this.f9598Q);
        this.f9627x = (TextView) findViewById(R.id.recording_time);
        this.f9583B = findViewById(R.id.toolbar);
        this.f9613j = new C1842b(this, 3);
        TextureView textureView = (TextureView) findViewById(R.id.texture);
        this.f9587F = textureView;
        textureView.setVisibility(0);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f9582A.removeCallbacks(this.f9596O);
        this.f9582A = null;
        View view = this.f9621r;
        if (view != null) {
            view.setOnClickListener(null);
        }
        View view2 = this.f9623t;
        if (view2 != null) {
            view2.setOnClickListener(null);
        }
        View view3 = this.f9622s;
        if (view3 != null) {
            view3.setOnClickListener(null);
        }
        ImageView imageView = this.f9624u;
        if (imageView != null) {
            imageView.setOnClickListener(null);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            new File(this.f9608e).delete();
        }
        this.f9597P = null;
        this.f9601T = null;
        this.f9603V = null;
        this.f9600S = null;
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onPause() throws IllegalStateException, InterruptedException, CameraAccessException, IOException {
        FixedAspectRatioFrameLayout fixedAspectRatioFrameLayout;
        VideoView videoView;
        super.onPause();
        m10938O0();
        m10939P0(false);
        m10941o0();
        m10929F0();
        if (!this.f9616m && (fixedAspectRatioFrameLayout = this.f9620q) != null && (videoView = this.f9628y) != null) {
            fixedAspectRatioFrameLayout.removeView(videoView);
        }
        this.f9613j.disable();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() throws IllegalStateException, InterruptedException, CameraAccessException, IOException {
        super.onResume();
        m10935L0();
        this.f9613j.enable();
        if (new File(this.f9608e).exists()) {
            m10931H0();
            return;
        }
        if (this.f9587F.isAvailable()) {
            m10928E0();
        } else {
            this.f9587F.setSurfaceTextureListener(this.f9594M);
        }
        m10950z0();
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    /* renamed from: p0 */
    public final void m10942p0() {
        CameraCaptureSession cameraCaptureSession = this.f9591J;
        if (cameraCaptureSession != null) {
            cameraCaptureSession.close();
            this.f9591J = null;
        }
    }

    /* renamed from: q0 */
    public final void m10943q0(int i9, int i10) {
        Activity activityM10944r0 = m10944r0();
        if (this.f9587F == null || this.f9607d == null) {
            return;
        }
        int rotation = activityM10944r0.getWindowManager().getDefaultDisplay().getRotation();
        Matrix matrix = new Matrix();
        float f9 = i9;
        float f10 = i10;
        RectF rectF = new RectF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, f9, f10);
        RectF rectF2 = new RectF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, this.f9607d.getHeight(), this.f9607d.getWidth());
        float fCenterX = rectF.centerX();
        float fCenterY = rectF.centerY();
        if (1 == rotation || 3 == rotation) {
            rectF2.offset(fCenterX - rectF2.centerX(), fCenterY - rectF2.centerY());
            matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
            float fMax = Math.max(f10 / this.f9607d.getHeight(), f9 / this.f9607d.getWidth());
            matrix.postScale(fMax, fMax, fCenterX, fCenterY);
            matrix.postRotate((rotation - 2) * 90, fCenterX, fCenterY);
        }
        this.f9587F.setTransform(matrix);
    }

    /* renamed from: r0 */
    public final Activity m10944r0() {
        return this;
    }

    /* renamed from: s0 */
    public final int m10945s0(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == 0) {
            return 0;
        }
        if (rotation == 1) {
            return 90;
        }
        if (rotation != 2) {
            return rotation != 3 ? 0 : 270;
        }
        return 180;
    }

    /* renamed from: u0 */
    public final String m10946u0(long j9) {
        int i9 = ((int) (j9 / 60)) % 60;
        int i10 = ((int) j9) % 60;
        if (i10 < 10) {
            return i9 + ":0" + i10;
        }
        return i9 + ":" + i10;
    }

    /* renamed from: w0 */
    public final boolean m10947w0() {
        this.f9625v.setVisibility(0);
        this.f9626w.setVisibility(0);
        this.f9624u.setVisibility(8);
        this.f9621r.setVisibility(8);
        this.f9622s.setVisibility(8);
        this.f9587F.setVisibility(8);
        this.f9628y = new VideoView(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.f9620q.addView(this.f9628y, layoutParams);
        View view = this.f9583B;
        if (view != null) {
            ((RelativeLayout.LayoutParams) view.getLayoutParams()).addRule(3, R.id.camera_preview);
        }
        this.f9629z = new C1844d(this);
        File file = new File(this.f9608e);
        if (!file.exists()) {
            return false;
        }
        this.f9628y.setVideoURI(Uri.fromFile(file));
        this.f9628y.setOnPreparedListener(this.f9604W);
        this.f9628y.setOnCompletionListener(this.f9605X);
        this.f9628y.requestFocus();
        this.f9616m = true;
        return true;
    }

    /* renamed from: x0 */
    public final void m10948x0(int i9, int i10) {
        if (m10945s0(this) == 0 || m10945s0(this) == 180) {
            this.f9620q.m17311a(i10, i9);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 0);
            layoutParams.addRule(13);
            this.f9620q.setLayoutParams(layoutParams);
            return;
        }
        this.f9620q.m17311a(i9, i10);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(0, -1);
        layoutParams2.addRule(13);
        this.f9620q.setLayoutParams(layoutParams2);
    }

    /* renamed from: y0 */
    public final void m10949y0() {
        VideoView videoView;
        this.f9616m = false;
        this.f9617n = false;
        FixedAspectRatioFrameLayout fixedAspectRatioFrameLayout = this.f9620q;
        if (fixedAspectRatioFrameLayout != null && (videoView = this.f9628y) != null) {
            fixedAspectRatioFrameLayout.removeView(videoView);
        }
        this.f9628y = null;
        File file = new File(this.f9608e);
        if (file.exists()) {
            file.delete();
        }
    }

    /* renamed from: z0 */
    public final void m10950z0() {
        if (this.f9592K >= 2) {
            this.f9621r.setVisibility(0);
        } else {
            this.f9621r.setVisibility(8);
        }
        if (this.f9610g) {
            this.f9622s.setVisibility(8);
        } else {
            this.f9622s.setVisibility(0);
        }
    }
}
