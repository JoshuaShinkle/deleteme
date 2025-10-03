package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.widgetpool.common.FixedAspectRatioFrameLayout;
import com.google.android.exoplayer2.C3322C;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import p116k4.C5187v0;
import p126l3.C5283b;
import p126l3.C5285d;
import p126l3.SurfaceHolderCallbackC5282a;

/* loaded from: classes.dex */
public class VideoRecordActivity extends BaseActivity {

    /* renamed from: A */
    public MediaController f9537A;

    /* renamed from: B */
    public C5285d f9538B;

    /* renamed from: D */
    public View f9540D;

    /* renamed from: c */
    public Camera.Size f9550c;

    /* renamed from: d */
    public String f9551d;

    /* renamed from: e */
    public int f9552e;

    /* renamed from: g */
    public int f9554g;

    /* renamed from: h */
    public Camera f9555h;

    /* renamed from: i */
    public SurfaceHolderCallbackC5282a f9556i;

    /* renamed from: j */
    public MediaRecorder f9557j;

    /* renamed from: l */
    public OrientationEventListener f9559l;

    /* renamed from: p */
    public int f9563p;

    /* renamed from: q */
    public long f9564q;

    /* renamed from: r */
    public FixedAspectRatioFrameLayout f9565r;

    /* renamed from: s */
    public View f9566s;

    /* renamed from: t */
    public View f9567t;

    /* renamed from: u */
    public View f9568u;

    /* renamed from: v */
    public ImageView f9569v;

    /* renamed from: w */
    public Button f9570w;

    /* renamed from: x */
    public Button f9571x;

    /* renamed from: y */
    public TextView f9572y;

    /* renamed from: z */
    public VideoView f9573z;

    /* renamed from: f */
    public boolean f9553f = false;

    /* renamed from: k */
    public int f9558k = 0;

    /* renamed from: m */
    public boolean f9560m = false;

    /* renamed from: n */
    public boolean f9561n = false;

    /* renamed from: o */
    public boolean f9562o = false;

    /* renamed from: C */
    public Handler f9539C = new Handler();

    /* renamed from: E */
    public final Runnable f9541E = new RunnableC1833a();

    /* renamed from: F */
    public View.OnClickListener f9542F = new View.OnClickListener() { // from class: com.cyberlink.you.activity.gm
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10703b.m10843e0(view);
        }
    };

    /* renamed from: G */
    public final View.OnClickListener f9543G = new View.OnClickListener() { // from class: com.cyberlink.you.activity.hm
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10735b.m10844g0(view);
        }
    };

    /* renamed from: H */
    public final View.OnClickListener f9544H = new ViewOnClickListenerC1834b();

    /* renamed from: I */
    public View.OnClickListener f9545I = new ViewOnClickListenerC1835c();

    /* renamed from: J */
    public View.OnClickListener f9546J = new ViewOnClickListenerC1836d();

    /* renamed from: K */
    public final MediaRecorder.OnInfoListener f9547K = new MediaRecorder.OnInfoListener() { // from class: com.cyberlink.you.activity.im
        @Override // android.media.MediaRecorder.OnInfoListener
        public final void onInfo(MediaRecorder mediaRecorder, int i9, int i10) {
            this.f10766a.m10846i0(mediaRecorder, i9, i10);
        }
    };

    /* renamed from: L */
    public View.OnClickListener f9548L = new ViewOnClickListenerC1837e();

    /* renamed from: M */
    public final MediaPlayer.OnPreparedListener f9549M = new C1838f();

    /* renamed from: com.cyberlink.you.activity.VideoRecordActivity$a */
    public class RunnableC1833a implements Runnable {
        public RunnableC1833a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (VideoRecordActivity.this.f9564q != 0) {
                VideoRecordActivity.this.f9572y.setText(VideoRecordActivity.this.m10864X((int) ((System.currentTimeMillis() - VideoRecordActivity.this.f9564q) / 1000)));
                VideoRecordActivity.this.f9539C.postDelayed(VideoRecordActivity.this.f9541E, 1000L);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordActivity$b */
    public class ViewOnClickListenerC1834b implements View.OnClickListener {
        public ViewOnClickListenerC1834b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws IllegalStateException {
            if (VideoRecordActivity.this.f9553f) {
                VideoRecordActivity.this.m10865Y(1);
            } else {
                VideoRecordActivity.this.m10865Y(0);
            }
            if (VideoRecordActivity.this.f9555h == null) {
                VideoRecordActivity.this.m10860P();
            } else {
                VideoRecordActivity.this.m10869d0();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordActivity$c */
    public class ViewOnClickListenerC1835c implements View.OnClickListener {
        public ViewOnClickListenerC1835c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Camera.Parameters parameters;
            if (VideoRecordActivity.this.f9553f || (parameters = VideoRecordActivity.this.f9555h.getParameters()) == null) {
                return;
            }
            if (parameters.getFlashMode().equals("off")) {
                parameters.setFlashMode("torch");
                VideoRecordActivity.this.f9567t.setActivated(true);
            } else {
                parameters.setFlashMode("off");
                VideoRecordActivity.this.f9567t.setActivated(false);
            }
            VideoRecordActivity.this.f9555h.setParameters(parameters);
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordActivity$d */
    public class ViewOnClickListenerC1836d implements View.OnClickListener {
        public ViewOnClickListenerC1836d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws IllegalStateException {
            if (VideoRecordActivity.this.f9553f) {
                VideoRecordActivity.this.m10865Y(0);
            } else {
                VideoRecordActivity.this.m10865Y(1);
            }
            if (VideoRecordActivity.this.f9555h == null) {
                VideoRecordActivity.this.m10860P();
            } else {
                VideoRecordActivity.this.m10869d0();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordActivity$e */
    public class ViewOnClickListenerC1837e implements View.OnClickListener {
        public ViewOnClickListenerC1837e() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m10881b() {
            VideoRecordActivity.this.f9566s.setVisibility(8);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                if (VideoRecordActivity.this.f9560m) {
                    int i9 = 1;
                    VideoRecordActivity.this.m10879s0(true);
                    if (!VideoRecordActivity.this.m10866Z()) {
                        VideoRecordActivity videoRecordActivity = VideoRecordActivity.this;
                        if (!videoRecordActivity.f9553f) {
                            i9 = 0;
                        }
                        videoRecordActivity.m10865Y(i9);
                    }
                } else {
                    VideoRecordActivity.this.m10861Q().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.km
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f10836b.m10881b();
                        }
                    });
                    VideoRecordActivity.this.m10878r0();
                }
            } catch (Exception e9) {
                Log.e("VideoRecordActivity", "click record exception:" + e9);
                e9.printStackTrace();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordActivity$f */
    public class C1838f implements MediaPlayer.OnPreparedListener {
        public C1838f() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m10883b() {
            if (VideoRecordActivity.this.f9573z == null || VideoRecordActivity.this.f9537A == null) {
                return;
            }
            VideoRecordActivity.this.f9573z.setMediaController(VideoRecordActivity.this.f9537A);
            VideoRecordActivity.this.f9573z.seekTo(0);
            VideoRecordActivity.this.f9537A.show();
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            VideoRecordActivity.this.f9539C.post(new Runnable() { // from class: com.cyberlink.you.activity.lm
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10874b.m10883b();
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordActivity$g */
    public class C1839g extends OrientationEventListener {
        public C1839g(Context context, int i9) {
            super(context, i9);
        }

        @Override // android.view.OrientationEventListener
        public void onOrientationChanged(int i9) {
            int i10;
            if (i9 == -1 || (i10 = (360 - ((((i9 + 45) % 360) / 90) * 90)) % 360) == VideoRecordActivity.this.f9558k) {
                return;
            }
            VideoRecordActivity.this.f9558k = i10;
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoRecordActivity$h */
    public class C1840h extends MediaController {
        public C1840h(Context context) {
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

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e0 */
    public /* synthetic */ void m10843e0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g0 */
    public /* synthetic */ void m10844g0(View view) {
        setResult(-1);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i0 */
    public /* synthetic */ void m10846i0(MediaRecorder mediaRecorder, int i9, int i10) {
        if (i9 == 800) {
            try {
                if (!this.f9560m) {
                    m10878r0();
                    return;
                }
                m10879s0(true);
                if (!m10866Z()) {
                    m10865Y(this.f9553f ? 1 : 0);
                }
                C5187v0.m20268d(String.format(m10861Q().getResources().getString(R.string.record_videos_reach_max), String.valueOf(3)));
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }
    }

    /* renamed from: P */
    public void m10860P() {
        C5187v0.m20267c(R.string.video_record_no_available_camera);
        super.finish();
    }

    /* renamed from: Q */
    public final Activity m10861Q() {
        return this;
    }

    /* renamed from: T */
    public final Camera m10862T(int i9) {
        Camera cameraOpen;
        try {
            if (Camera.getNumberOfCameras() >= 2) {
                cameraOpen = Camera.open(i9);
            } else {
                if (Camera.getNumberOfCameras() != 1) {
                    return null;
                }
                cameraOpen = Camera.open(0);
            }
            return cameraOpen;
        } catch (Exception e9) {
            e9.printStackTrace();
            return null;
        }
    }

    /* renamed from: V */
    public final int m10863V(Activity activity) {
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

    /* renamed from: X */
    public final String m10864X(long j9) {
        int i9 = ((int) (j9 / 60)) % 60;
        int i10 = ((int) j9) % 60;
        if (i10 < 10) {
            return i9 + ":0" + i10;
        }
        return i9 + ":" + i10;
    }

    /* renamed from: Y */
    public final void m10865Y(int i9) throws IllegalStateException {
        m10879s0(false);
        m10872l0();
        m10867b0();
        this.f9570w.setVisibility(8);
        this.f9571x.setVisibility(8);
        this.f9569v.setVisibility(0);
        this.f9565r.removeAllViews();
        View view = this.f9540D;
        if (view != null) {
            ((RelativeLayout.LayoutParams) view.getLayoutParams()).removeRule(3);
        }
        if (Camera.getNumberOfCameras() <= 1) {
            this.f9554g = 0;
        } else {
            this.f9554g = i9;
        }
        Camera cameraM10862T = m10862T(this.f9554g);
        this.f9555h = cameraM10862T;
        if (cameraM10862T != null) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(this.f9554g, cameraInfo);
            this.f9553f = cameraInfo.facing == 1;
            m10876p0(this, this.f9554g, this.f9555h);
            m10875o0(this.f9555h);
            m10877q0(this.f9555h, 480, 360);
            this.f9550c = C5283b.m20566e(this.f9555h, 480, 360);
            m10868c0(480, 360);
        }
    }

    /* renamed from: Z */
    public final boolean m10866Z() {
        m10872l0();
        this.f9570w.setVisibility(0);
        this.f9571x.setVisibility(0);
        this.f9569v.setVisibility(8);
        this.f9565r.removeAllViews();
        m10869d0();
        this.f9573z = new VideoView(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.f9565r.addView(this.f9573z, layoutParams);
        View view = this.f9540D;
        if (view != null) {
            ((RelativeLayout.LayoutParams) view.getLayoutParams()).addRule(3, R.id.camera_preview);
        }
        this.f9537A = new C1840h(this);
        File file = new File(this.f9551d);
        if (!file.exists()) {
            return false;
        }
        this.f9573z.setVideoURI(Uri.fromFile(file));
        this.f9573z.setOnPreparedListener(this.f9549M);
        this.f9573z.requestFocus();
        this.f9561n = true;
        return true;
    }

    /* renamed from: b0 */
    public final void m10867b0() {
        this.f9561n = false;
        this.f9562o = false;
        this.f9573z = null;
        File file = new File(this.f9551d);
        if (file.exists()) {
            file.delete();
        }
    }

    /* renamed from: c0 */
    public final void m10868c0(int i9, int i10) {
        if (m10863V(this) == 0 || m10863V(this) == 180) {
            this.f9565r.m17311a(i10, i9);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 0);
            layoutParams.addRule(13);
            this.f9565r.setLayoutParams(layoutParams);
        } else {
            this.f9565r.m17311a(i9, i10);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(0, -1);
            layoutParams2.addRule(13);
            this.f9565r.setLayoutParams(layoutParams2);
        }
        SurfaceHolderCallbackC5282a surfaceHolderCallbackC5282a = new SurfaceHolderCallbackC5282a(this, this.f9555h);
        this.f9556i = surfaceHolderCallbackC5282a;
        this.f9565r.addView(surfaceHolderCallbackC5282a);
    }

    /* renamed from: d0 */
    public final void m10869d0() {
        if (this.f9555h == null) {
            this.f9566s.setVisibility(8);
            this.f9567t.setVisibility(8);
            return;
        }
        if (Camera.getNumberOfCameras() >= 2) {
            this.f9566s.setVisibility(0);
        } else if (Camera.getNumberOfCameras() == 1) {
            this.f9566s.setVisibility(8);
        }
        if (this.f9553f) {
            this.f9567t.setVisibility(8);
        } else {
            this.f9567t.setVisibility(0);
        }
    }

    @Override // android.app.Activity
    public void finish() {
        this.f9538B.m20577b();
        super.finish();
    }

    /* renamed from: j0 */
    public final void m10870j0() {
        VideoView videoView;
        if (!this.f9561n || (videoView = this.f9573z) == null) {
            return;
        }
        this.f9563p = videoView.getCurrentPosition();
        this.f9562o = !this.f9573z.isPlaying();
        this.f9573z.pause();
    }

    /* renamed from: k0 */
    public final boolean m10871k0() throws IllegalStateException, IOException, IllegalArgumentException {
        Log.i("VideoRecordActivity", "prepareVideoRecorder ");
        MediaRecorder mediaRecorder = new MediaRecorder();
        this.f9557j = mediaRecorder;
        mediaRecorder.setOnInfoListener(this.f9547K);
        this.f9555h.unlock();
        this.f9557j.setCamera(this.f9555h);
        Log.i("VideoRecordActivity", "mCamera unlocked ");
        this.f9557j.setAudioSource(1);
        this.f9557j.setVideoSource(1);
        Log.i("VideoRecordActivity", "setAudioSource  setVideoSource  set");
        CamcorderProfile camcorderProfile = CamcorderProfile.get(this.f9554g, 1);
        Camera.Size size = this.f9550c;
        if (size != null) {
            camcorderProfile.videoFrameWidth = size.width;
            camcorderProfile.videoFrameHeight = size.height;
        }
        camcorderProfile.fileFormat = 2;
        camcorderProfile.videoCodec = 2;
        camcorderProfile.videoBitRate = 819200;
        camcorderProfile.audioBitRate = C3322C.DEFAULT_BUFFER_SEGMENT_SIZE;
        this.f9557j.setProfile(camcorderProfile);
        this.f9557j.setMaxDuration(180000);
        try {
            this.f9557j.setOrientationHint(this.f9553f ? ((this.f9552e + 180) + this.f9558k) % 360 : ((this.f9552e + 360) - this.f9558k) % 360);
            this.f9557j.setOutputFile(this.f9551d);
            this.f9557j.setPreviewDisplay(this.f9556i.getHolder().getSurface());
        } catch (IllegalStateException unused) {
        }
        try {
            this.f9557j.prepare();
            return true;
        } catch (IOException e9) {
            Log.d("VideoRecordActivity", "IOException preparing MediaRecorder: " + e9.getMessage());
            m10873m0();
            return false;
        } catch (IllegalStateException e10) {
            Log.d("VideoRecordActivity", "IllegalStateException preparing MediaRecorder: " + e10.getMessage());
            m10873m0();
            return false;
        }
    }

    /* renamed from: l0 */
    public final void m10872l0() {
        Camera camera = this.f9555h;
        if (camera != null) {
            camera.release();
            this.f9555h = null;
        }
    }

    /* renamed from: m0 */
    public final void m10873m0() {
        MediaRecorder mediaRecorder = this.f9557j;
        if (mediaRecorder != null) {
            mediaRecorder.reset();
            this.f9557j.release();
            this.f9557j = null;
            this.f9555h.lock();
        }
    }

    /* renamed from: n0 */
    public final void m10874n0() throws IllegalStateException {
        VideoView videoView;
        if (!this.f9561n || (videoView = this.f9573z) == null) {
            if (m10866Z()) {
                return;
            }
            m10865Y(this.f9553f ? 1 : 0);
        } else {
            videoView.seekTo(this.f9563p);
            if (this.f9562o) {
                return;
            }
            this.f9573z.start();
        }
    }

    /* renamed from: o0 */
    public final void m10875o0(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        Iterator<String> it = camera.getParameters().getSupportedFocusModes().iterator();
        while (it.hasNext()) {
            if (it.next().equals("continuous-video")) {
                parameters.setFocusMode("continuous-video");
                camera.setParameters(parameters);
            }
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_video_record);
        Intent intent = getIntent();
        this.f9551d = intent.hasExtra("videoPath") ? intent.getStringExtra("videoPath") : CLUtility.m16581r1();
        this.f9565r = (FixedAspectRatioFrameLayout) findViewById(R.id.camera_preview);
        View viewFindViewById = findViewById(R.id.switch_camera);
        this.f9566s = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f9546J);
        View viewFindViewById2 = findViewById(R.id.back);
        this.f9568u = viewFindViewById2;
        viewFindViewById2.setOnClickListener(this.f9542F);
        View viewFindViewById3 = findViewById(R.id.flash);
        this.f9567t = viewFindViewById3;
        viewFindViewById3.setOnClickListener(this.f9545I);
        ImageView imageView = (ImageView) findViewById(R.id.button_capture);
        this.f9569v = imageView;
        imageView.setOnClickListener(this.f9548L);
        Button button = (Button) findViewById(R.id.button_retry);
        this.f9570w = button;
        button.setOnClickListener(this.f9544H);
        Button button2 = (Button) findViewById(R.id.button_send);
        this.f9571x = button2;
        button2.setOnClickListener(this.f9543G);
        this.f9572y = (TextView) findViewById(R.id.recording_time);
        this.f9540D = findViewById(R.id.toolbar);
        this.f9559l = new C1839g(this, 3);
        this.f9538B = new C5285d(new Runnable() { // from class: com.cyberlink.you.activity.jm
            @Override // java.lang.Runnable
            public final void run() {
                this.f10804b.m10872l0();
            }
        });
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f9538B.m20577b();
        this.f9539C.removeCallbacks(this.f9541E);
        this.f9539C = null;
        View view = this.f9566s;
        if (view != null) {
            view.setOnClickListener(null);
        }
        View view2 = this.f9568u;
        if (view2 != null) {
            view2.setOnClickListener(null);
        }
        View view3 = this.f9567t;
        if (view3 != null) {
            view3.setOnClickListener(null);
        }
        ImageView imageView = this.f9569v;
        if (imageView != null) {
            imageView.setOnClickListener(null);
        }
        this.f9542F = null;
        this.f9546J = null;
        this.f9548L = null;
        this.f9545I = null;
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onPause() throws IllegalStateException {
        super.onPause();
        if (isFinishing()) {
            this.f9538B.m20577b();
        }
        m10879s0(false);
        m10872l0();
        m10870j0();
        if (!this.f9561n) {
            this.f9565r.removeAllViews();
        }
        this.f9559l.disable();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() throws IllegalStateException {
        super.onResume();
        this.f9559l.enable();
        if (new File(this.f9551d).exists()) {
            m10874n0();
            return;
        }
        if (this.f9553f) {
            m10865Y(1);
        } else {
            m10865Y(0);
        }
        if (this.f9555h == null) {
            m10860P();
        } else {
            m10869d0();
        }
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    /* renamed from: p0 */
    public void m10876p0(Activity activity, int i9, Camera camera) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i9, cameraInfo);
        int iM10863V = m10863V(activity);
        int i10 = cameraInfo.facing == 1 ? (360 - ((cameraInfo.orientation + iM10863V) % 360)) % 360 : ((cameraInfo.orientation - iM10863V) + 360) % 360;
        this.f9552e = i10;
        camera.setDisplayOrientation(i10);
    }

    /* renamed from: q0 */
    public final void m10877q0(Camera camera, int i9, int i10) {
        Camera.Size sizeM20564c = C5283b.m20564c(camera, i9, i10);
        if (sizeM20564c != null) {
            Camera.Parameters parameters = camera.getParameters();
            parameters.setPreviewSize(sizeM20564c.width, sizeM20564c.height);
            camera.setParameters(parameters);
        }
    }

    /* renamed from: r0 */
    public final void m10878r0() throws IllegalStateException {
        Log.i("VideoRecordActivity", "startRecording ");
        if (this.f9560m) {
            return;
        }
        if (!m10871k0()) {
            Log.i("VideoRecordActivity", "releaseMediaRecorder ");
            m10873m0();
            return;
        }
        Log.i("VideoRecordActivity", "mMediaRecorder.start() ");
        this.f9557j.start();
        this.f9569v.setImageResource(R.drawable.video_btn_stop_n);
        this.f9560m = true;
        this.f9564q = System.currentTimeMillis();
        this.f9572y.setVisibility(0);
        this.f9539C.post(this.f9541E);
    }

    /* renamed from: s0 */
    public final void m10879s0(boolean z8) throws IllegalStateException {
        if (this.f9560m) {
            boolean z9 = true;
            try {
                this.f9557j.stop();
                if (CLUtility.m16440G0(this.f9551d, null) >= 1000) {
                    z9 = false;
                }
            } catch (RuntimeException unused) {
            }
            if (z9) {
                new File(this.f9551d).delete();
                if (z8) {
                    C5187v0.m20267c(R.string.videos_too_short);
                }
            }
            m10873m0();
            Camera camera = this.f9555h;
            if (camera != null) {
                camera.lock();
            }
            this.f9569v.setImageResource(R.drawable.image_selector_video_record_btn);
            this.f9560m = false;
            this.f9564q = 0L;
            this.f9572y.setVisibility(8);
        }
    }
}
