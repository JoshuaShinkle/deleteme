package p073g1;

import android.util.Log;
import com.google.android.exoplayer2.C3322C;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import p022c1.InterfaceC0705b;
import p243y0.C6592e;
import p243y0.InterfaceC6588a;

/* renamed from: g1.t */
/* loaded from: classes.dex */
public class C4826t implements InterfaceC6588a<InputStream> {

    /* renamed from: a */
    public final InterfaceC0705b f16806a;

    public C4826t(InterfaceC0705b interfaceC0705b) {
        this.f16806a = interfaceC0705b;
    }

    @Override // p243y0.InterfaceC6588a
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean mo19086b(InputStream inputStream, File file, C6592e c6592e) throws Throwable {
        byte[] bArr = (byte[]) this.f16806a.mo3481d(C3322C.DEFAULT_BUFFER_SEGMENT_SIZE, byte[].class);
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                while (true) {
                    try {
                        int i9 = inputStream.read(bArr);
                        if (i9 == -1) {
                            break;
                        }
                        fileOutputStream2.write(bArr, 0, i9);
                    } catch (IOException e9) {
                        e = e9;
                        fileOutputStream = fileOutputStream2;
                        if (Log.isLoggable("StreamEncoder", 3)) {
                            Log.d("StreamEncoder", "Failed to encode data onto the OutputStream", e);
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused) {
                            }
                        }
                        this.f16806a.put(bArr);
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused2) {
                            }
                        }
                        this.f16806a.put(bArr);
                        throw th;
                    }
                }
                fileOutputStream2.close();
                try {
                    fileOutputStream2.close();
                } catch (IOException unused3) {
                }
                this.f16806a.put(bArr);
                return true;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e10) {
            e = e10;
        }
    }
}
