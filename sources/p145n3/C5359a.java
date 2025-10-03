package p145n3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* renamed from: n3.a */
/* loaded from: classes.dex */
public class C5359a {
    /* renamed from: a */
    public static Bitmap m21051a(byte[] bArr, int i9, int i10) {
        YuvImage yuvImage = new YuvImage(bArr, 17, i9, i10, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        if (!yuvImage.compressToJpeg(new Rect(0, 0, i9, i10), 100, byteArrayOutputStream)) {
            return null;
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    /* renamed from: b */
    public static Bitmap m21052b(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /* renamed from: c */
    public static Bitmap m21053c(Bitmap bitmap, float f9) {
        Matrix matrix = new Matrix();
        matrix.postRotate(f9);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /* renamed from: d */
    public static void m21054d(Bitmap bitmap, File file) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.close();
            } finally {
            }
        } catch (IOException e9) {
            e9.printStackTrace();
        }
    }
}
