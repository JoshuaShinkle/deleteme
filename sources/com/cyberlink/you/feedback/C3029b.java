package com.cyberlink.you.feedback;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import com.cyberlink.you.Globals;
import com.cyberlink.you.feedback.C3028a;
import com.cyberlink.you.feedback.C3032e;
import com.cyberlink.you.utility.CLUtility;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import p173q2.C6136j;

/* renamed from: com.cyberlink.you.feedback.b */
/* loaded from: classes.dex */
public class C3029b {

    /* renamed from: com.cyberlink.you.feedback.b$a */
    public static class a {

        /* renamed from: d */
        public static final a f13478d = new a(1920, 1080, 70);

        /* renamed from: a */
        public int f13479a;

        /* renamed from: b */
        public int f13480b;

        /* renamed from: c */
        public int f13481c;

        public a(int i9, int i10, int i11) {
            this.f13479a = i9;
            this.f13480b = i10;
            this.f13481c = i11;
        }
    }

    /* renamed from: a */
    public static C3032e.a m15490a(Bitmap bitmap, a aVar) throws IOException {
        try {
            Bitmap bitmapM15492c = m15492c(bitmap, aVar);
            if (bitmapM15492c == null) {
                return null;
            }
            String strM15493d = m15493d(Globals.m7372O());
            FileOutputStream fileOutputStream = new FileOutputStream(strM15493d);
            if (!bitmapM15492c.compress(Bitmap.CompressFormat.JPEG, aVar.f13481c, fileOutputStream)) {
                fileOutputStream.close();
                return null;
            }
            fileOutputStream.close();
            ExifInterface exifInterface = new ExifInterface(strM15493d);
            exifInterface.setAttribute("Orientation", String.valueOf(0));
            exifInterface.setAttribute("ImageWidth", String.valueOf(bitmapM15492c.getWidth()));
            exifInterface.setAttribute("ImageLength", String.valueOf(bitmapM15492c.getHeight()));
            exifInterface.saveAttributes();
            C3028a.c cVar = new C3028a.c();
            cVar.f13474c = Integer.valueOf(bitmapM15492c.getWidth());
            cVar.f13475d = Integer.valueOf(bitmapM15492c.getHeight());
            cVar.f13476e = 0;
            cVar.f13477f = m15494e(bitmapM15492c);
            return C3032e.m15508a(strM15493d, cVar);
        } catch (Exception e9) {
            e9.printStackTrace();
            return null;
        } catch (OutOfMemoryError e10) {
            e10.printStackTrace();
            System.gc();
            return null;
        }
    }

    /* renamed from: b */
    public static C3032e.a m15491b(Uri uri, a aVar) {
        if (uri == null || aVar == null) {
            return null;
        }
        return m15490a(C6136j.m23588h(Globals.m7372O(), uri.toString(), aVar.f13479a, aVar.f13480b), aVar);
    }

    /* renamed from: c */
    public static Bitmap m15492c(Bitmap bitmap, a aVar) {
        float f9;
        float f10;
        float f11;
        float f12;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > height) {
            int i9 = aVar.f13479a;
            f9 = width > i9 ? i9 / width : 1.0f;
            int i10 = aVar.f13480b;
            if (height > i10) {
                f10 = i10;
                f11 = height;
                f12 = f10 / f11;
            }
            f12 = 1.0f;
        } else {
            int i11 = aVar.f13479a;
            f9 = height > i11 ? i11 / height : 1.0f;
            int i12 = aVar.f13480b;
            if (width > i12) {
                f10 = i12;
                f11 = width;
                f12 = f10 / f11;
            }
            f12 = 1.0f;
        }
        float fMin = Math.min(f9, f12);
        if (fMin >= 1.0f) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(fMin, fMin);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    /* renamed from: d */
    public static String m15493d(Context context) {
        return CLUtility.m16472O0(context) + File.separator + "U_" + new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US).format(new Date()) + ".jpg";
    }

    /* renamed from: e */
    public static String m15494e(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            int[] iArr = m15495f(Bitmap.createScaledBitmap(bitmap, 64, 64, true)).get(0);
            return String.format(Locale.getDefault(), "#%06X", Integer.valueOf(Integer.valueOf(Color.rgb(iArr[0], iArr[1], iArr[2])).intValue() & 16777215));
        } catch (Exception e9) {
            e9.printStackTrace();
            return null;
        }
    }

    /* renamed from: f */
    public static List<int[]> m15495f(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i9 = 0; i9 < height; i9++) {
            for (int i10 = 0; i10 < width; i10++) {
                arrayList2.add(Integer.valueOf(bitmap.getPixel(i10, i9)));
            }
        }
        for (int i11 = 0; i11 < arrayList2.size(); i11 += 10) {
            int iIntValue = ((Integer) arrayList2.get(i11)).intValue();
            int i12 = (iIntValue >> 16) & 255;
            int i13 = (iIntValue >> 8) & 255;
            int i14 = iIntValue & 255;
            int[] iArr = {i12, i13, i14};
            if (i12 <= 250 || i13 <= 250 || i14 <= 250) {
                arrayList.add(iArr);
            }
        }
        return arrayList;
    }
}
