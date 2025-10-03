package com.cyberlink.clrtc.p031ve;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.os.Build;
import android.os.CountDownTimer;
import com.cyberlink.clrtc.p031ve.ScreenCaptureService;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.webrtc.Logging;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.ThreadUtils;
import org.webrtc.VideoCapturer;

@TargetApi(21)
/* renamed from: com.cyberlink.clrtc.ve.d */
/* loaded from: classes.dex */
public class C1159d implements VideoCapturer, SurfaceTextureHelper.OnTextureFrameAvailableListener {

    /* renamed from: a */
    public Intent f5694a;

    /* renamed from: b */
    public final MediaProjection.Callback f5695b;

    /* renamed from: c */
    public final Context f5696c;

    /* renamed from: d */
    public int f5697d;

    /* renamed from: e */
    public int f5698e;

    /* renamed from: f */
    public int f5699f;

    /* renamed from: g */
    public SurfaceTextureHelper f5700g;

    /* renamed from: h */
    public VideoCapturer.CapturerObserver f5701h;

    /* renamed from: i */
    public long f5702i = 0;

    /* renamed from: j */
    public boolean f5703j = false;

    /* renamed from: k */
    public CountDownTimer f5704k = null;

    /* renamed from: l */
    public int f5705l = 0;

    /* renamed from: com.cyberlink.clrtc.ve.d$a */
    public class a extends ScreenCaptureService.AbstractC1145a {

        /* renamed from: b */
        public final /* synthetic */ CountDownLatch f5706b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, CountDownLatch countDownLatch) {
            super(str);
            this.f5706b = countDownLatch;
        }

        @Override // com.cyberlink.clrtc.p031ve.ScreenCaptureService.AbstractC1145a
        /* renamed from: b */
        public void mo5177b() {
            this.f5706b.countDown();
        }
    }

    /* renamed from: com.cyberlink.clrtc.ve.d$b */
    public class b implements Runnable {

        /* renamed from: com.cyberlink.clrtc.ve.d$b$a */
        public class a extends CountDownTimer {
            public a(long j9, long j10) {
                super(j9, j10);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                Logging.m23189w("ScreenCapturer", "[CountDownTimer onFinish] Timeout, restart ScreenCaptureService");
                C1159d.this.f5702i = 0L;
                C1159d c1159d = C1159d.this;
                c1159d.changeCaptureFormat(c1159d.f5697d, C1159d.this.f5698e, C1159d.this.f5699f);
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j9) {
                Logging.m23189w("ScreenCapturer", "[CountDownTimer onTick] Not enough frame in " + j9 + " milliseconds, timerFrameCount = " + C1159d.this.f5705l);
            }
        }

        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C1159d.this.f5704k = new a(AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS, 100L).start();
        }
    }

    public C1159d(Context context, MediaProjection.Callback callback) {
        this.f5696c = context;
        this.f5695b = callback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public /* synthetic */ void m5210k(int i9, int i10) {
        ScreenCaptureService.m5167b(i9, i10, this.f5700g.getSurfaceTexture());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public /* synthetic */ void m5211l() {
        this.f5700g.stopListening();
        this.f5701h.onCapturerStopped();
        ScreenCaptureService.m5170i(this.f5695b);
        m5212i();
    }

    @Override // org.webrtc.VideoCapturer
    public void changeCaptureFormat(final int i9, final int i10, int i11) {
        m5213j();
        this.f5697d = i9;
        this.f5698e = i10;
        this.f5699f = i11;
        boolean zM5168e = ScreenCaptureService.m5168e();
        Logging.m23189w("ScreenCapturer", "[changeCaptureFormat] width = " + i9 + " height = " + i10 + " isCapturing = " + zM5168e);
        if (zM5168e) {
            Logging.m23189w("ScreenCapturer", "[changeCaptureFormat] ScreenCaptureService.changeFormat");
            ThreadUtils.invokeAtFrontUninterruptibly(this.f5700g.getHandler(), new Runnable() { // from class: com.cyberlink.clrtc.ve.c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5691b.m5210k(i9, i10);
                }
            });
            m5214m();
        }
    }

    @Override // org.webrtc.VideoCapturer
    public synchronized void dispose() {
        this.f5703j = true;
    }

    /* renamed from: i */
    public final void m5212i() {
        CountDownTimer countDownTimer;
        if (Build.VERSION.SDK_INT >= 31 && (countDownTimer = this.f5704k) != null) {
            countDownTimer.cancel();
            this.f5704k = null;
            this.f5705l = 0;
        }
    }

    @Override // org.webrtc.VideoCapturer
    public synchronized void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, VideoCapturer.CapturerObserver capturerObserver) {
        m5213j();
        if (capturerObserver == null) {
            throw new RuntimeException("capturerObserver not set.");
        }
        this.f5701h = capturerObserver;
        if (surfaceTextureHelper == null) {
            throw new RuntimeException("surfaceTextureHelper not set.");
        }
        this.f5700g = surfaceTextureHelper;
    }

    @Override // org.webrtc.VideoCapturer
    public boolean isScreencast() {
        return true;
    }

    /* renamed from: j */
    public final void m5213j() {
        if (this.f5703j) {
            throw new RuntimeException("capturer is disposed.");
        }
    }

    /* renamed from: m */
    public final void m5214m() {
        if (Build.VERSION.SDK_INT < 31) {
            return;
        }
        m5212i();
        this.f5700g.getHandler().post(new b());
    }

    /* renamed from: n */
    public void m5215n(Intent intent) {
        this.f5694a = intent;
    }

    @Override // org.webrtc.SurfaceTextureHelper.OnTextureFrameAvailableListener
    public void onTextureFrameAvailable(int i9, float[] fArr, long j9) {
        this.f5702i++;
        this.f5701h.onTextureFrameCaptured(this.f5697d, this.f5698e, i9, fArr, 0, j9);
        if (this.f5704k != null) {
            int i10 = this.f5705l + 1;
            this.f5705l = i10;
            if (i10 <= 0 || i10 % (this.f5699f / 2) != 0) {
                return;
            }
            m5212i();
        }
    }

    @Override // org.webrtc.VideoCapturer
    public void resetFormat(int i9, int i10, int i11) {
        this.f5697d = i9;
        this.f5698e = i10;
        this.f5699f = i11;
    }

    @Override // org.webrtc.VideoCapturer
    public void resumeCapture() {
    }

    @Override // org.webrtc.VideoCapturer
    public synchronized void startCapture(int i9, int i10, int i11) {
        m5213j();
        if (this.f5694a == null) {
            return;
        }
        String string = UUID.randomUUID().toString();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ScreenCaptureService.m5166a(new a(string, countDownLatch));
        Intent intent = new Intent(this.f5696c, (Class<?>) ScreenCaptureService.class);
        intent.putExtra("extra.token", string);
        this.f5696c.startForegroundService(intent);
        try {
            countDownLatch.await(5L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
        ScreenCaptureService.m5169g(this.f5694a, this.f5697d, this.f5698e, this.f5695b, this.f5700g.getSurfaceTexture(), this.f5700g.getHandler());
        this.f5701h.onCapturerStarted(true);
        this.f5700g.startListening(this);
        m5214m();
    }

    @Override // org.webrtc.VideoCapturer
    public synchronized void stopCapture() {
        m5213j();
        ThreadUtils.invokeAtFrontUninterruptibly(this.f5700g.getHandler(), new Runnable() { // from class: com.cyberlink.clrtc.ve.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f5690b.m5211l();
            }
        });
    }
}
