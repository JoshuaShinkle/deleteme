package p116k4;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;

/* renamed from: k4.e0 */
/* loaded from: classes.dex */
public class C5140e0 {

    /* renamed from: a */
    public int f17648a = 0;

    /* renamed from: b */
    public int f17649b = -1;

    /* renamed from: c */
    public MediaPlayer f17650c = null;

    /* renamed from: d */
    public MediaPlayer.OnCompletionListener f17651d = new MediaPlayer.OnCompletionListener() { // from class: k4.c0
        @Override // android.media.MediaPlayer.OnCompletionListener
        public final void onCompletion(MediaPlayer mediaPlayer) throws IllegalStateException {
            this.f17643b.m20011g(mediaPlayer);
        }
    };

    /* renamed from: e */
    public MediaPlayer.OnErrorListener f17652e = new MediaPlayer.OnErrorListener() { // from class: k4.d0
        @Override // android.media.MediaPlayer.OnErrorListener
        public final boolean onError(MediaPlayer mediaPlayer, int i9, int i10) {
            return this.f17645b.m20012h(mediaPlayer, i9, i10);
        }
    };

    /* renamed from: f */
    public a f17653f = null;

    /* renamed from: k4.e0$a */
    public interface a {
        /* renamed from: a */
        void mo9127a(int i9);

        /* renamed from: b */
        void mo9128b();

        /* renamed from: c */
        void mo9129c(int i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public /* synthetic */ void m20011g(MediaPlayer mediaPlayer) throws IllegalStateException {
        m20027s();
        m20022n();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public /* synthetic */ boolean m20012h(MediaPlayer mediaPlayer, int i9, int i10) throws IllegalStateException {
        m20027s();
        m20023o(2);
        return true;
    }

    /* renamed from: c */
    public int m20013c() {
        MediaPlayer mediaPlayer = this.f17650c;
        if (mediaPlayer == null) {
            return -1;
        }
        try {
            return mediaPlayer.getCurrentPosition();
        } catch (Exception e9) {
            e9.printStackTrace();
            return -1;
        }
    }

    /* renamed from: d */
    public int m20014d() {
        MediaPlayer mediaPlayer = this.f17650c;
        if (mediaPlayer != null && this.f17648a == 1) {
            try {
                return mediaPlayer.getDuration();
            } catch (Exception unused) {
            }
        }
        return -1;
    }

    /* renamed from: e */
    public double m20015e() {
        return this.f17650c == null ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : m20013c() / m20014d();
    }

    /* renamed from: f */
    public int m20016f() {
        return this.f17648a;
    }

    /* renamed from: i */
    public void m20017i(Context context, Uri uri) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        if (this.f17650c != null && this.f17648a == 1) {
            m20027s();
        }
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.f17650c = mediaPlayer;
        try {
            mediaPlayer.setDataSource(context, uri);
            this.f17650c.setOnCompletionListener(this.f17651d);
            this.f17650c.setOnErrorListener(this.f17652e);
            int i9 = this.f17649b;
            if (i9 != -1) {
                this.f17650c.setAudioStreamType(i9);
            }
            this.f17650c.prepare();
            this.f17650c.start();
            m20024p(1);
        } catch (IOException unused) {
            m20023o(1);
            this.f17650c = null;
        } catch (Exception unused2) {
            m20023o(2);
            this.f17650c = null;
        }
    }

    /* renamed from: j */
    public void m20018j(String str) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        if (this.f17650c != null && this.f17648a == 1) {
            m20027s();
        }
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.f17650c = mediaPlayer;
        try {
            mediaPlayer.setDataSource(str);
            this.f17650c.setOnCompletionListener(this.f17651d);
            this.f17650c.setOnErrorListener(this.f17652e);
            int i9 = this.f17649b;
            if (i9 != -1) {
                this.f17650c.setAudioStreamType(i9);
            }
            this.f17650c.prepare();
            this.f17650c.start();
            m20024p(1);
        } catch (IOException unused) {
            m20023o(1);
            this.f17650c = null;
        } catch (Exception unused2) {
            m20023o(2);
            this.f17650c = null;
        }
    }

    /* renamed from: k */
    public boolean m20019k(AssetFileDescriptor assetFileDescriptor, boolean z8) throws IllegalStateException, IOException, IllegalArgumentException {
        if (this.f17650c != null && this.f17648a == 1) {
            if (!z8) {
                return false;
            }
            m20027s();
        }
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.f17650c = mediaPlayer;
        try {
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            this.f17650c.setOnCompletionListener(this.f17651d);
            this.f17650c.setOnErrorListener(this.f17652e);
            int i9 = this.f17649b;
            if (i9 != -1) {
                this.f17650c.setAudioStreamType(i9);
            }
            this.f17650c.prepare();
            this.f17650c.start();
            m20024p(1);
            return true;
        } catch (IOException unused) {
            m20023o(1);
            this.f17650c = null;
            return false;
        } catch (Exception unused2) {
            m20023o(2);
            this.f17650c = null;
            return false;
        }
    }

    /* renamed from: l */
    public void m20020l() throws IllegalStateException {
        m20027s();
        m20026r(0);
    }

    /* renamed from: m */
    public void m20021m(a aVar) {
        this.f17653f = aVar;
    }

    /* renamed from: n */
    public final void m20022n() {
        a aVar = this.f17653f;
        if (aVar != null) {
            aVar.mo9128b();
        }
    }

    /* renamed from: o */
    public final void m20023o(int i9) {
        a aVar = this.f17653f;
        if (aVar != null) {
            aVar.mo9129c(i9);
        }
    }

    /* renamed from: p */
    public final void m20024p(int i9) {
        if (i9 == this.f17648a) {
            return;
        }
        this.f17648a = i9;
        m20026r(i9);
    }

    /* renamed from: q */
    public void m20025q(int i9) {
        this.f17649b = i9;
    }

    /* renamed from: r */
    public final void m20026r(int i9) {
        a aVar = this.f17653f;
        if (aVar != null) {
            aVar.mo9127a(i9);
        }
    }

    /* renamed from: s */
    public void m20027s() throws IllegalStateException {
        MediaPlayer mediaPlayer = this.f17650c;
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.stop();
        this.f17650c.release();
        this.f17650c = null;
        m20024p(0);
    }
}
