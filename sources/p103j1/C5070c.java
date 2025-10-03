package p103j1;

import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.load.EncodeStrategy;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import p012b1.InterfaceC0595j;
import p022c1.InterfaceC0705b;
import p226w1.C6512e;
import p226w1.C6517j;
import p235x1.C6564b;
import p243y0.C6591d;
import p243y0.C6592e;
import p243y0.InterfaceC6594g;
import p252z0.C6804c;

/* renamed from: j1.c */
/* loaded from: classes.dex */
public class C5070c implements InterfaceC6594g<Bitmap> {

    /* renamed from: b */
    public static final C6591d<Integer> f17497b = C6591d.m25204f("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", 90);

    /* renamed from: c */
    public static final C6591d<Bitmap.CompressFormat> f17498c = C6591d.m25203e("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat");

    /* renamed from: a */
    public final InterfaceC0705b f17499a;

    public C5070c(InterfaceC0705b interfaceC0705b) {
        this.f17499a = interfaceC0705b;
    }

    @Override // p243y0.InterfaceC6594g
    /* renamed from: a */
    public EncodeStrategy mo19854a(C6592e c6592e) {
        return EncodeStrategy.TRANSFORMED;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0072 A[Catch: all -> 0x00c2, TRY_LEAVE, TryCatch #2 {all -> 0x00c2, blocks: (B:3:0x0021, B:12:0x004b, B:29:0x006c, B:31:0x0072, B:35:0x00be, B:36:0x00c1, B:26:0x0067), top: B:45:0x0021 }] */
    @Override // p243y0.InterfaceC6588a
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean mo19086b(InterfaceC0595j<Bitmap> interfaceC0595j, File file, C6592e c6592e) {
        boolean z8;
        FileOutputStream fileOutputStream;
        Bitmap bitmap = interfaceC0595j.get();
        Bitmap.CompressFormat compressFormatM19857d = m19857d(bitmap, c6592e);
        C6564b.m25136c("encode: [%dx%d] %s", Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()), compressFormatM19857d);
        try {
            long jM24923b = C6512e.m24923b();
            int iIntValue = ((Integer) c6592e.m25209c(f17497b)).intValue();
            OutputStream c6804c = null;
            try {
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (IOException e9) {
                    e = e9;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                c6804c = this.f17499a != null ? new C6804c(fileOutputStream, this.f17499a) : fileOutputStream;
                bitmap.compress(compressFormatM19857d, iIntValue, c6804c);
                c6804c.close();
                try {
                    c6804c.close();
                } catch (IOException unused) {
                }
                z8 = true;
            } catch (IOException e10) {
                e = e10;
                c6804c = fileOutputStream;
                if (Log.isLoggable("BitmapEncoder", 3)) {
                    Log.d("BitmapEncoder", "Failed to encode Bitmap", e);
                }
                if (c6804c != null) {
                    try {
                        c6804c.close();
                    } catch (IOException unused2) {
                    }
                }
                z8 = false;
                if (Log.isLoggable("BitmapEncoder", 2)) {
                }
                return z8;
            } catch (Throwable th2) {
                th = th2;
                c6804c = fileOutputStream;
                if (c6804c != null) {
                    try {
                        c6804c.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
            if (Log.isLoggable("BitmapEncoder", 2)) {
                Log.v("BitmapEncoder", "Compressed with type: " + compressFormatM19857d + " of size " + C6517j.m24947h(bitmap) + " in " + C6512e.m24922a(jM24923b) + ", options format: " + c6592e.m25209c(f17498c) + ", hasAlpha: " + bitmap.hasAlpha());
            }
            return z8;
        } finally {
            C6564b.m25137d();
        }
    }

    /* renamed from: d */
    public final Bitmap.CompressFormat m19857d(Bitmap bitmap, C6592e c6592e) {
        Bitmap.CompressFormat compressFormat = (Bitmap.CompressFormat) c6592e.m25209c(f17498c);
        return compressFormat != null ? compressFormat : bitmap.hasAlpha() ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG;
    }
}
