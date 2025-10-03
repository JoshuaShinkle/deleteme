package p103j1;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.util.Log;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import p022c1.InterfaceC0707d;
import p226w1.C6516i;

/* renamed from: j1.s */
/* loaded from: classes.dex */
public final class C5086s {

    /* renamed from: a */
    public static final Paint f17525a = new Paint(6);

    /* renamed from: b */
    public static final Paint f17526b = new Paint(7);

    /* renamed from: c */
    public static final Paint f17527c;

    /* renamed from: d */
    public static final Set<String> f17528d;

    /* renamed from: e */
    public static final Lock f17529e;

    /* renamed from: j1.s$a */
    public static final class a implements Lock {
        @Override // java.util.concurrent.locks.Lock
        public void lock() {
        }

        @Override // java.util.concurrent.locks.Lock
        public void lockInterruptibly() {
        }

        @Override // java.util.concurrent.locks.Lock
        public Condition newCondition() {
            throw new UnsupportedOperationException("Should not be called");
        }

        @Override // java.util.concurrent.locks.Lock
        public boolean tryLock() {
            return true;
        }

        @Override // java.util.concurrent.locks.Lock
        public boolean tryLock(long j9, TimeUnit timeUnit) {
            return true;
        }

        @Override // java.util.concurrent.locks.Lock
        public void unlock() {
        }
    }

    static {
        HashSet hashSet = new HashSet(Arrays.asList("XT1085", "XT1092", "XT1093", "XT1094", "XT1095", "XT1096", "XT1097", "XT1098", "XT1031", "XT1028", "XT937C", "XT1032", "XT1008", "XT1033", "XT1035", "XT1034", "XT939G", "XT1039", "XT1040", "XT1042", "XT1045", "XT1063", "XT1064", "XT1068", "XT1069", "XT1072", "XT1077", "XT1078", "XT1079"));
        f17528d = hashSet;
        f17529e = hashSet.contains(Build.MODEL) ? new ReentrantLock() : new a();
        Paint paint = new Paint(7);
        f17527c = paint;
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }

    /* renamed from: a */
    public static void m19891a(Bitmap bitmap, Bitmap bitmap2, Matrix matrix) {
        Lock lock = f17529e;
        lock.lock();
        try {
            Canvas canvas = new Canvas(bitmap2);
            canvas.drawBitmap(bitmap, matrix, f17525a);
            m19895e(canvas);
            lock.unlock();
        } catch (Throwable th) {
            f17529e.unlock();
            throw th;
        }
    }

    /* renamed from: b */
    public static Bitmap m19892b(InterfaceC0707d interfaceC0707d, Bitmap bitmap, int i9, int i10) {
        float width;
        float height;
        if (bitmap.getWidth() == i9 && bitmap.getHeight() == i10) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        int width2 = bitmap.getWidth() * i10;
        int height2 = bitmap.getHeight() * i9;
        float width3 = BitmapDescriptorFactory.HUE_RED;
        if (width2 > height2) {
            width = i10 / bitmap.getHeight();
            width3 = (i9 - (bitmap.getWidth() * width)) * 0.5f;
            height = 0.0f;
        } else {
            width = i9 / bitmap.getWidth();
            height = (i10 - (bitmap.getHeight() * width)) * 0.5f;
        }
        matrix.setScale(width, width);
        matrix.postTranslate((int) (width3 + 0.5f), (int) (height + 0.5f));
        Bitmap bitmapMo3488d = interfaceC0707d.mo3488d(i9, i10, m19901k(bitmap));
        m19906p(bitmap, bitmapMo3488d);
        m19891a(bitmap, bitmapMo3488d, matrix);
        return bitmapMo3488d;
    }

    /* renamed from: c */
    public static Bitmap m19893c(InterfaceC0707d interfaceC0707d, Bitmap bitmap, int i9, int i10) {
        if (bitmap.getWidth() > i9 || bitmap.getHeight() > i10) {
            if (Log.isLoggable("TransformationUtils", 2)) {
                Log.v("TransformationUtils", "requested target size too big for input, fit centering instead");
            }
            return m19896f(interfaceC0707d, bitmap, i9, i10);
        }
        if (Log.isLoggable("TransformationUtils", 2)) {
            Log.v("TransformationUtils", "requested target size larger or equal to input, returning input");
        }
        return bitmap;
    }

    /* renamed from: d */
    public static Bitmap m19894d(InterfaceC0707d interfaceC0707d, Bitmap bitmap, int i9, int i10) {
        int iMin = Math.min(i9, i10);
        float f9 = iMin;
        float f10 = f9 / 2.0f;
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();
        float fMax = Math.max(f9 / width, f9 / height);
        float f11 = width * fMax;
        float f12 = fMax * height;
        float f13 = (f9 - f11) / 2.0f;
        float f14 = (f9 - f12) / 2.0f;
        RectF rectF = new RectF(f13, f14, f11 + f13, f12 + f14);
        Bitmap bitmapM19897g = m19897g(interfaceC0707d, bitmap);
        Bitmap bitmapMo3488d = interfaceC0707d.mo3488d(iMin, iMin, m19898h(bitmap));
        bitmapMo3488d.setHasAlpha(true);
        Lock lock = f17529e;
        lock.lock();
        try {
            Canvas canvas = new Canvas(bitmapMo3488d);
            canvas.drawCircle(f10, f10, f10, f17526b);
            canvas.drawBitmap(bitmapM19897g, (Rect) null, rectF, f17527c);
            m19895e(canvas);
            lock.unlock();
            if (!bitmapM19897g.equals(bitmap)) {
                interfaceC0707d.mo3487c(bitmapM19897g);
            }
            return bitmapMo3488d;
        } catch (Throwable th) {
            f17529e.unlock();
            throw th;
        }
    }

    /* renamed from: e */
    public static void m19895e(Canvas canvas) {
        canvas.setBitmap(null);
    }

    /* renamed from: f */
    public static Bitmap m19896f(InterfaceC0707d interfaceC0707d, Bitmap bitmap, int i9, int i10) {
        if (bitmap.getWidth() == i9 && bitmap.getHeight() == i10) {
            if (Log.isLoggable("TransformationUtils", 2)) {
                Log.v("TransformationUtils", "requested target size matches input, returning input");
            }
            return bitmap;
        }
        float fMin = Math.min(i9 / bitmap.getWidth(), i10 / bitmap.getHeight());
        int iRound = Math.round(bitmap.getWidth() * fMin);
        int iRound2 = Math.round(bitmap.getHeight() * fMin);
        if (bitmap.getWidth() == iRound && bitmap.getHeight() == iRound2) {
            if (Log.isLoggable("TransformationUtils", 2)) {
                Log.v("TransformationUtils", "adjusted target size matches input, returning input");
            }
            return bitmap;
        }
        Bitmap bitmapMo3488d = interfaceC0707d.mo3488d((int) (bitmap.getWidth() * fMin), (int) (bitmap.getHeight() * fMin), m19901k(bitmap));
        m19906p(bitmap, bitmapMo3488d);
        if (Log.isLoggable("TransformationUtils", 2)) {
            Log.v("TransformationUtils", "request: " + i9 + "x" + i10);
            Log.v("TransformationUtils", "toFit:   " + bitmap.getWidth() + "x" + bitmap.getHeight());
            Log.v("TransformationUtils", "toReuse: " + bitmapMo3488d.getWidth() + "x" + bitmapMo3488d.getHeight());
            StringBuilder sb = new StringBuilder();
            sb.append("minPct:   ");
            sb.append(fMin);
            Log.v("TransformationUtils", sb.toString());
        }
        Matrix matrix = new Matrix();
        matrix.setScale(fMin, fMin);
        m19891a(bitmap, bitmapMo3488d, matrix);
        return bitmapMo3488d;
    }

    /* renamed from: g */
    public static Bitmap m19897g(InterfaceC0707d interfaceC0707d, Bitmap bitmap) {
        Bitmap.Config configM19898h = m19898h(bitmap);
        if (configM19898h.equals(bitmap.getConfig())) {
            return bitmap;
        }
        Bitmap bitmapMo3488d = interfaceC0707d.mo3488d(bitmap.getWidth(), bitmap.getHeight(), configM19898h);
        new Canvas(bitmapMo3488d).drawBitmap(bitmap, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (Paint) null);
        return bitmapMo3488d;
    }

    /* renamed from: h */
    public static Bitmap.Config m19898h(Bitmap bitmap) {
        return Bitmap.Config.RGBA_F16.equals(bitmap.getConfig()) ? Bitmap.Config.RGBA_F16 : Bitmap.Config.ARGB_8888;
    }

    /* renamed from: i */
    public static Lock m19899i() {
        return f17529e;
    }

    /* renamed from: j */
    public static int m19900j(int i9) {
        switch (i9) {
            case 3:
            case 4:
                return 180;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    /* renamed from: k */
    public static Bitmap.Config m19901k(Bitmap bitmap) {
        return bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888;
    }

    /* renamed from: l */
    public static void m19902l(int i9, Matrix matrix) {
        switch (i9) {
            case 2:
                matrix.setScale(-1.0f, 1.0f);
                break;
            case 3:
                matrix.setRotate(180.0f);
                break;
            case 4:
                matrix.setRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 5:
                matrix.setRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 6:
                matrix.setRotate(90.0f);
                break;
            case 7:
                matrix.setRotate(-90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 8:
                matrix.setRotate(-90.0f);
                break;
        }
    }

    /* renamed from: m */
    public static boolean m19903m(int i9) {
        switch (i9) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return true;
            default:
                return false;
        }
    }

    /* renamed from: n */
    public static Bitmap m19904n(InterfaceC0707d interfaceC0707d, Bitmap bitmap, int i9) {
        if (!m19903m(i9)) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        m19902l(i9, matrix);
        RectF rectF = new RectF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, bitmap.getWidth(), bitmap.getHeight());
        matrix.mapRect(rectF);
        Bitmap bitmapMo3488d = interfaceC0707d.mo3488d(Math.round(rectF.width()), Math.round(rectF.height()), m19901k(bitmap));
        matrix.postTranslate(-rectF.left, -rectF.top);
        m19891a(bitmap, bitmapMo3488d, matrix);
        return bitmapMo3488d;
    }

    /* renamed from: o */
    public static Bitmap m19905o(InterfaceC0707d interfaceC0707d, Bitmap bitmap, int i9) {
        C6516i.m24935a(i9 > 0, "roundingRadius must be greater than 0.");
        Bitmap.Config configM19898h = m19898h(bitmap);
        Bitmap bitmapM19897g = m19897g(interfaceC0707d, bitmap);
        Bitmap bitmapMo3488d = interfaceC0707d.mo3488d(bitmapM19897g.getWidth(), bitmapM19897g.getHeight(), configM19898h);
        bitmapMo3488d.setHasAlpha(true);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmapM19897g, tileMode, tileMode);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);
        RectF rectF = new RectF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, bitmapMo3488d.getWidth(), bitmapMo3488d.getHeight());
        Lock lock = f17529e;
        lock.lock();
        try {
            Canvas canvas = new Canvas(bitmapMo3488d);
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            float f9 = i9;
            canvas.drawRoundRect(rectF, f9, f9, paint);
            m19895e(canvas);
            lock.unlock();
            if (!bitmapM19897g.equals(bitmap)) {
                interfaceC0707d.mo3487c(bitmapM19897g);
            }
            return bitmapMo3488d;
        } catch (Throwable th) {
            f17529e.unlock();
            throw th;
        }
    }

    /* renamed from: p */
    public static void m19906p(Bitmap bitmap, Bitmap bitmap2) {
        bitmap2.setHasAlpha(bitmap.hasAlpha());
    }
}
