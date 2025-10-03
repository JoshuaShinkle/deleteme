package p143n1;

import android.util.Log;
import com.bumptech.glide.load.C0824a;
import com.bumptech.glide.load.ImageHeaderParser;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;
import p012b1.InterfaceC0595j;
import p022c1.InterfaceC0705b;
import p243y0.C6592e;
import p243y0.InterfaceC6593f;

/* renamed from: n1.j */
/* loaded from: classes.dex */
public class C5357j implements InterfaceC6593f<InputStream, C5350c> {

    /* renamed from: a */
    public final List<ImageHeaderParser> f18242a;

    /* renamed from: b */
    public final InterfaceC6593f<ByteBuffer, C5350c> f18243b;

    /* renamed from: c */
    public final InterfaceC0705b f18244c;

    public C5357j(List<ImageHeaderParser> list, InterfaceC6593f<ByteBuffer, C5350c> interfaceC6593f, InterfaceC0705b interfaceC0705b) {
        this.f18242a = list;
        this.f18243b = interfaceC6593f;
        this.f18244c = interfaceC0705b;
    }

    /* renamed from: e */
    public static byte[] m21048e(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16384);
        try {
            byte[] bArr = new byte[16384];
            while (true) {
                int i9 = inputStream.read(bArr);
                if (i9 == -1) {
                    byteArrayOutputStream.flush();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, i9);
            }
        } catch (IOException e9) {
            if (!Log.isLoggable("StreamGifDecoder", 5)) {
                return null;
            }
            Log.w("StreamGifDecoder", "Error reading data from stream", e9);
            return null;
        }
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC0595j<C5350c> mo3998b(InputStream inputStream, int i9, int i10, C6592e c6592e) throws IOException {
        byte[] bArrM21048e = m21048e(inputStream);
        if (bArrM21048e == null) {
            return null;
        }
        return this.f18243b.mo3998b(ByteBuffer.wrap(bArrM21048e), i9, i10, c6592e);
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3997a(InputStream inputStream, C6592e c6592e) {
        return !((Boolean) c6592e.m25209c(C5356i.f18241b)).booleanValue() && C0824a.m3837b(this.f18242a, inputStream, this.f18244c) == ImageHeaderParser.ImageType.GIF;
    }
}
