package p204t6;

import java.nio.channels.WritableByteChannel;
import okio.ByteString;

/* renamed from: t6.d */
/* loaded from: classes.dex */
public interface InterfaceC6323d extends InterfaceC6340u, WritableByteChannel {
    /* renamed from: b */
    C6322c mo24215b();

    @Override // p204t6.InterfaceC6340u, java.io.Flushable
    void flush();

    /* renamed from: h */
    long mo24219h(InterfaceC6342w interfaceC6342w);

    /* renamed from: j */
    InterfaceC6323d mo24221j(String str);

    /* renamed from: l */
    InterfaceC6323d mo24223l(long j9);

    /* renamed from: p */
    InterfaceC6323d mo24227p(ByteString byteString);

    /* renamed from: s */
    InterfaceC6323d mo24229s(long j9);

    InterfaceC6323d write(byte[] bArr);

    InterfaceC6323d write(byte[] bArr, int i9, int i10);

    InterfaceC6323d writeByte(int i9);

    InterfaceC6323d writeInt(int i9);

    InterfaceC6323d writeShort(int i9);
}
