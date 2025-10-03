package p046d4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.File;
import java.io.IOException;
import p056e4.C4754a;

/* renamed from: d4.a */
/* loaded from: classes.dex */
public class C4676a {
    /* renamed from: a */
    public static int m18675a(BitmapFactory.Options options, int i9, int i10) {
        int i11 = options.outHeight;
        int i12 = options.outWidth;
        if (i11 <= i10 && i12 <= i9) {
            return 1;
        }
        int iCeil = (int) Math.ceil(i11 / i10);
        int iCeil2 = (int) Math.ceil(i12 / i9);
        int i13 = i11 / 2;
        int i14 = i12 / 2;
        if (iCeil >= iCeil2) {
            iCeil = iCeil2;
        }
        while (i11 / iCeil > i10 && i12 / iCeil > i9) {
            iCeil++;
        }
        if (((i12 * i11) * 1.0f) / (iCeil * iCeil) <= i9 * i10 * 2.0f) {
            return iCeil;
        }
        while (true) {
            if (i13 / iCeil <= i10 && i14 / iCeil <= i9) {
                return (int) Math.pow(2.0d, (int) Math.ceil(Math.sqrt(iCeil)));
            }
            iCeil++;
        }
    }

    /* renamed from: b */
    public static Bitmap m18676b(String str, boolean z8, int i9) {
        Bitmap bitmapDecodeFile;
        int iM18872b;
        float f9;
        int i10;
        int i11;
        int i12;
        int i13;
        Log.d("ImageResizer", "[decodeSampledBitmapFromFileWithEXIF] in");
        Bitmap bitmapCreateBitmap = null;
        if (str != null && new File(str).exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            boolean z9 = true;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            int i14 = options.outWidth;
            int i15 = options.outHeight;
            if (i14 > 0 && i15 > 0) {
                if (i14 <= i15) {
                    i14 = i15;
                }
                int i16 = i9 > 0 ? i9 : 1280;
                if (i16 <= i14) {
                    i14 = i16;
                }
                options.inJustDecodeBounds = false;
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inPurgeable = true;
                options.inInputShareable = true;
                options.inSampleSize = m18675a(options, i14, i14);
                try {
                    bitmapDecodeFile = BitmapFactory.decodeFile(str, options);
                } catch (OutOfMemoryError unused) {
                    Log.e("ImageResizer", "decodeFile " + str + " OutOfMemoryError");
                    bitmapDecodeFile = null;
                }
                if (bitmapDecodeFile == null) {
                    return null;
                }
                int width = bitmapDecodeFile.getWidth();
                int height = bitmapDecodeFile.getHeight();
                if (width <= 0 || height <= 0) {
                    Log.d("ImageResizer", "[decodeSampledBitmapFromFileWithEXIF] Decode File - 2 : width=" + width + ", height=" + height);
                    return null;
                }
                boolean z10 = width < height;
                try {
                    iM18872b = C4754a.m18872b(str);
                } catch (IOException e9) {
                    Log.e("ImageResizer", "cannot get exif attribute", e9);
                    iM18872b = 0;
                }
                if (z8) {
                    int i17 = z10 ? 0 : (width - height) / 2;
                    i10 = z10 ? (height - width) / 2 : 0;
                    if (!z10) {
                        width = height;
                    }
                    f9 = i14 / width;
                    if (f9 <= BitmapDescriptorFactory.HUE_RED) {
                        f9 = 1.0f;
                    }
                    i12 = width;
                    i13 = i17;
                    i11 = i12;
                } else {
                    f9 = i14 / (z10 ? height : width);
                    i10 = 0;
                    i11 = width;
                    if (f9 <= BitmapDescriptorFactory.HUE_RED) {
                        i12 = height;
                        f9 = 1.0f;
                    } else {
                        i12 = height;
                    }
                    i13 = 0;
                }
                if (i13 == 0 && i10 == 0 && i11 == bitmapDecodeFile.getWidth() && i12 == bitmapDecodeFile.getHeight() && f9 == 1.0f && iM18872b == 0) {
                    z9 = false;
                }
                if (!z9) {
                    return bitmapDecodeFile;
                }
                Matrix matrix = new Matrix();
                matrix.postScale(f9, f9);
                matrix.postRotate(iM18872b);
                try {
                    bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeFile, i13, i10, i11, i12, matrix, true);
                } catch (Exception e10) {
                    e10.printStackTrace();
                }
                bitmapDecodeFile.recycle();
                return bitmapCreateBitmap;
            }
            Log.d("ImageResizer", "[decodeSampledBitmapFromFileWithEXIF] Decode File - 1 : width=" + i14 + ", height=" + i15);
        }
        return null;
    }
}
