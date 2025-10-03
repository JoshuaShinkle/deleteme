package p204t6;

import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import okio.ByteString;

/* renamed from: t6.e */
/* loaded from: classes.dex */
public interface InterfaceC6324e extends InterfaceC6342w, ReadableByteChannel {
    /* renamed from: b */
    C6322c mo24215b();

    /* renamed from: c */
    InputStream mo24216c();

    /* renamed from: e */
    ByteString mo24217e(long j9);

    /* renamed from: g */
    boolean mo24218g();

    /* renamed from: i */
    String mo24220i(long j9);

    /* renamed from: k */
    String mo24222k(Charset charset);

    /* renamed from: m */
    String mo24224m();

    /* renamed from: n */
    int mo24225n(C6334o c6334o);

    /* renamed from: o */
    byte[] mo24226o(long j9);

    /* renamed from: r */
    void mo24228r(long j9);

    byte readByte();

    int readInt();

    short readShort();

    void skip(long j9);

    /* renamed from: t */
    long mo24230t();
}
