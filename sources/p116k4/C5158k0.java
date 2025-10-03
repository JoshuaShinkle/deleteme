package p116k4;

import android.media.MediaRecorder;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.File;
import java.io.IOException;

/* renamed from: k4.k0 */
/* loaded from: classes.dex */
public class C5158k0 {

    /* renamed from: c */
    public long f17679c;

    /* renamed from: d */
    public double f17680d;

    /* renamed from: a */
    public int f17677a = 0;

    /* renamed from: b */
    public MediaRecorder f17678b = null;

    /* renamed from: e */
    public d f17681e = null;

    /* renamed from: f */
    public MediaRecorder.OnErrorListener f17682f = new a();

    /* renamed from: g */
    public MediaRecorder.OnInfoListener f17683g = new b();

    /* renamed from: k4.k0$a */
    public class a implements MediaRecorder.OnErrorListener {
        public a() {
        }

        @Override // android.media.MediaRecorder.OnErrorListener
        public void onError(MediaRecorder mediaRecorder, int i9, int i10) throws IllegalStateException {
            C5158k0.this.m20103q(false);
            C5158k0.this.m20098l(2);
        }
    }

    /* renamed from: k4.k0$b */
    public class b implements MediaRecorder.OnInfoListener {
        public b() {
        }

        @Override // android.media.MediaRecorder.OnInfoListener
        public void onInfo(MediaRecorder mediaRecorder, int i9, int i10) throws IllegalStateException {
            if (i9 == 800) {
                C5158k0.this.m20103q(false);
                C5158k0.this.m20099m(0);
            }
        }
    }

    /* renamed from: k4.k0$c */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() throws IllegalStateException, InterruptedException {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e9) {
                e9.printStackTrace();
            }
            C5158k0.this.m20090d();
        }
    }

    /* renamed from: k4.k0$d */
    public interface d {
        /* renamed from: a */
        void mo11022a(int i9);

        /* renamed from: b */
        void mo11023b(int i9);

        /* renamed from: c */
        void mo11024c(int i9);
    }

    /* renamed from: c */
    public void m20089c() throws IllegalStateException {
        m20103q(false);
        this.f17680d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        m20102p(0);
    }

    /* renamed from: d */
    public void m20090d() throws IllegalStateException {
        MediaRecorder mediaRecorder = this.f17678b;
        if (mediaRecorder == null) {
            return;
        }
        try {
            mediaRecorder.stop();
        } catch (RuntimeException unused) {
            m20098l(2);
        }
        MediaRecorder mediaRecorder2 = this.f17678b;
        if (mediaRecorder2 != null) {
            mediaRecorder2.release();
            this.f17678b = null;
        }
        m20100n(0);
    }

    /* renamed from: e */
    public long m20091e() {
        return System.currentTimeMillis() - this.f17679c;
    }

    /* renamed from: f */
    public double m20092f() {
        double d9 = this.f17680d;
        if (d9 > 120.0d) {
            return 120.0d;
        }
        return d9;
    }

    /* renamed from: g */
    public int m20093g() {
        return 120000;
    }

    /* renamed from: h */
    public int m20094h() {
        MediaRecorder mediaRecorder = this.f17678b;
        if (mediaRecorder != null && this.f17677a == 1) {
            return mediaRecorder.getMaxAmplitude();
        }
        return 0;
    }

    /* renamed from: i */
    public double m20095i() {
        if (this.f17677a != 1) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        double dCurrentTimeMillis = System.currentTimeMillis() - (this.f17679c * 1.0d);
        if (dCurrentTimeMillis > 120000.0d) {
            dCurrentTimeMillis = 120000.0d;
        }
        return dCurrentTimeMillis / 120000.0d;
    }

    /* renamed from: j */
    public int m20096j() {
        return this.f17677a;
    }

    /* renamed from: k */
    public void m20097k(d dVar) {
        this.f17681e = dVar;
    }

    /* renamed from: l */
    public final void m20098l(int i9) {
        d dVar = this.f17681e;
        if (dVar != null) {
            dVar.mo11024c(i9);
        }
    }

    /* renamed from: m */
    public final void m20099m(int i9) {
        d dVar = this.f17681e;
        if (dVar != null) {
            dVar.mo11023b(i9);
        }
    }

    /* renamed from: n */
    public final void m20100n(int i9) {
        if (i9 == this.f17677a) {
            return;
        }
        this.f17677a = i9;
        m20102p(i9);
    }

    /* renamed from: o */
    public void m20101o(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        if (this.f17678b != null) {
            m20103q(false);
        }
        File file = new File(str);
        try {
            file.createNewFile();
            MediaRecorder mediaRecorder = new MediaRecorder();
            this.f17678b = mediaRecorder;
            mediaRecorder.setAudioSource(1);
            this.f17678b.setOutputFormat(2);
            this.f17678b.setOutputFile(file.getAbsolutePath());
            this.f17678b.setAudioEncoder(3);
            this.f17678b.setMaxDuration(120000);
            this.f17678b.setAudioSamplingRate(8000);
            this.f17678b.setAudioChannels(1);
            this.f17678b.setAudioEncodingBitRate(16000);
            this.f17678b.setOnInfoListener(this.f17683g);
            this.f17678b.setOnErrorListener(this.f17682f);
            try {
                this.f17678b.prepare();
                try {
                    this.f17678b.start();
                    this.f17679c = System.currentTimeMillis();
                    this.f17680d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                    m20100n(1);
                } catch (RuntimeException unused) {
                    m20098l(2);
                    this.f17678b.reset();
                    this.f17678b.release();
                    this.f17678b = null;
                }
            } catch (IOException unused2) {
                m20098l(2);
                this.f17678b.reset();
                this.f17678b.release();
                this.f17678b = null;
            }
        } catch (IOException unused3) {
            m20098l(1);
        }
    }

    /* renamed from: p */
    public final void m20102p(int i9) {
        d dVar = this.f17681e;
        if (dVar != null) {
            dVar.mo11022a(i9);
        }
    }

    /* renamed from: q */
    public void m20103q(boolean z8) throws IllegalStateException {
        if (z8) {
            new Thread(new c()).start();
            this.f17680d = ((System.currentTimeMillis() - this.f17679c) / 1000.0d) + 0.5d;
        } else {
            m20090d();
            this.f17680d = (System.currentTimeMillis() - this.f17679c) / 1000.0d;
        }
    }
}
