package p225w0;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

/* renamed from: w0.a */
/* loaded from: classes.dex */
public interface InterfaceC6503a {

    /* renamed from: w0.a$a */
    public interface a {
        /* renamed from: a */
        Bitmap mo21003a(int i9, int i10, Bitmap.Config config);

        /* renamed from: b */
        int[] mo21004b(int i9);

        /* renamed from: c */
        void mo21005c(Bitmap bitmap);

        /* renamed from: d */
        void mo21006d(byte[] bArr);

        /* renamed from: e */
        byte[] mo21007e(int i9);

        /* renamed from: f */
        void mo21008f(int[] iArr);
    }

    /* renamed from: a */
    int mo24874a();

    void advance();

    /* renamed from: b */
    int mo24875b();

    /* renamed from: c */
    void mo24876c(Bitmap.Config config);

    void clear();

    /* renamed from: d */
    void mo24877d();

    /* renamed from: e */
    int mo24878e();

    /* renamed from: f */
    int mo24879f();

    ByteBuffer getData();

    Bitmap getNextFrame();
}
