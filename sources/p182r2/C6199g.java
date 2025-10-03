package p182r2;

import com.cyberlink.you.utility.UploadMediaHelper;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: r2.g */
/* loaded from: classes.dex */
public class C6199g extends C6204l {

    /* renamed from: k */
    public final Object f20880k;

    /* renamed from: l */
    public final AtomicBoolean f20881l;

    /* renamed from: m */
    public boolean f20882m;

    /* renamed from: r2.g$a */
    public interface a extends UploadMediaHelper.InterfaceC3183t {
        void onCancel();

        void onPause();

        void onResume();
    }

    public C6199g(AbstractC6191b abstractC6191b, C6201i c6201i) {
        super(abstractC6191b, c6201i);
        this.f20880k = new Object();
        this.f20881l = new AtomicBoolean(false);
        this.f20882m = false;
    }

    /* renamed from: n */
    public final void m23712n() {
        synchronized (this.f20880k) {
            while (this.f20881l.get()) {
                try {
                    this.f20880k.wait(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
                } catch (InterruptedException unused) {
                    C6218z.m23770i("MediaMessageTask", "[beforeRun] paused is %b, messageId is %s", Boolean.valueOf(this.f20881l.get()), mo23707e());
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /* renamed from: o */
    public void m23713o() {
        synchronized (this.f20880k) {
            this.f20881l.set(false);
            this.f20882m = true;
            this.f20880k.notifyAll();
        }
    }

    /* renamed from: p */
    public void m23714p() {
        synchronized (this.f20880k) {
            this.f20881l.set(true);
        }
    }

    /* renamed from: q */
    public void m23715q() {
        synchronized (this.f20880k) {
            this.f20881l.set(false);
            this.f20880k.notifyAll();
        }
    }

    @Override // p182r2.AbstractC6197e, java.lang.Runnable
    public void run() {
        m23712n();
        if (this.f20882m) {
            return;
        }
        super.run();
    }
}
