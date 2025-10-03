package p116k4;

import android.content.Intent;
import android.graphics.Bitmap;
import com.cyberlink.you.utility.CLUtility;
import com.google.firebase.iid.ServiceStarter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.HashMap;
import p209u2.C6370g;

/* renamed from: k4.j0 */
/* loaded from: classes.dex */
public final class C5155j0 {
    /* renamed from: a */
    public static Intent m20083a(String str, String str2, String str3, String[] strArr) {
        Intent intentM20237f = C5178r.m20237f(str, str2, strArr);
        try {
            intentM20237f.putExtra("android.intent.extra.STREAM", CLUtility.m16554k2(m20084b(str3), false));
        } catch (Exception unused) {
        }
        return intentM20237f;
    }

    /* renamed from: b */
    public static Bitmap m20084b(String str) throws Throwable {
        QRCodeWriter qRCodeWriter;
        QRCodeWriter qRCodeWriter2 = null;
        try {
            qRCodeWriter = new QRCodeWriter();
            try {
                HashMap map = new HashMap();
                map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                BitMatrix bitMatrixEncode = qRCodeWriter.encode(str, BarcodeFormat.QR_CODE, ServiceStarter.ERROR_UNKNOWN, ServiceStarter.ERROR_UNKNOWN, map);
                int width = bitMatrixEncode.getWidth();
                int height = bitMatrixEncode.getHeight();
                int[] iArr = new int[width * height];
                for (int i9 = 0; i9 < height; i9++) {
                    int i10 = i9 * width;
                    for (int i11 = 0; i11 < width; i11++) {
                        iArr[i10 + i11] = bitMatrixEncode.get(i11, i9) ? -16777216 : -1;
                    }
                }
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
                C6370g.m24480b(qRCodeWriter);
                return bitmapCreateBitmap;
            } catch (Exception unused) {
                C6370g.m24480b(qRCodeWriter);
                return null;
            } catch (Throwable th) {
                th = th;
                qRCodeWriter2 = qRCodeWriter;
                C6370g.m24480b(qRCodeWriter2);
                throw th;
            }
        } catch (Exception unused2) {
            qRCodeWriter = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
