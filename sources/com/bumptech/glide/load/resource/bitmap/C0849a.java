package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;
import com.bumptech.glide.load.C0824a;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.google.android.exoplayer2.C3322C;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import p012b1.InterfaceC0595j;
import p022c1.InterfaceC0705b;
import p022c1.InterfaceC0707d;
import p103j1.C5071d;
import p103j1.C5082o;
import p103j1.C5086s;
import p226w1.C6512e;
import p226w1.C6516i;
import p226w1.C6517j;
import p243y0.C6591d;
import p243y0.C6592e;

/* renamed from: com.bumptech.glide.load.resource.bitmap.a */
/* loaded from: classes.dex */
public final class C0849a {

    /* renamed from: f */
    public static final C6591d<DecodeFormat> f3895f = C6591d.m25204f("com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeFormat", DecodeFormat.f3695d);

    /* renamed from: g */
    @Deprecated
    public static final C6591d<DownsampleStrategy> f3896g = DownsampleStrategy.f3885h;

    /* renamed from: h */
    public static final C6591d<Boolean> f3897h;

    /* renamed from: i */
    public static final C6591d<Boolean> f3898i;

    /* renamed from: j */
    public static final Set<String> f3899j;

    /* renamed from: k */
    public static final b f3900k;

    /* renamed from: l */
    public static final Set<ImageHeaderParser.ImageType> f3901l;

    /* renamed from: m */
    public static final Queue<BitmapFactory.Options> f3902m;

    /* renamed from: a */
    public final InterfaceC0707d f3903a;

    /* renamed from: b */
    public final DisplayMetrics f3904b;

    /* renamed from: c */
    public final InterfaceC0705b f3905c;

    /* renamed from: d */
    public final List<ImageHeaderParser> f3906d;

    /* renamed from: e */
    public final C5082o f3907e = C5082o.m19884a();

    /* renamed from: com.bumptech.glide.load.resource.bitmap.a$a */
    public class a implements b {
        @Override // com.bumptech.glide.load.resource.bitmap.C0849a.b
        /* renamed from: a */
        public void mo3995a(InterfaceC0707d interfaceC0707d, Bitmap bitmap) {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.C0849a.b
        /* renamed from: b */
        public void mo3996b() {
        }
    }

    /* renamed from: com.bumptech.glide.load.resource.bitmap.a$b */
    public interface b {
        /* renamed from: a */
        void mo3995a(InterfaceC0707d interfaceC0707d, Bitmap bitmap);

        /* renamed from: b */
        void mo3996b();
    }

    static {
        Boolean bool = Boolean.FALSE;
        f3897h = C6591d.m25204f("com.bumptech.glide.load.resource.bitmap.Downsampler.FixBitmapSize", bool);
        f3898i = C6591d.m25204f("com.bumptech.glide.load.resource.bitmap.Downsampler.AllowHardwareDecode", bool);
        f3899j = Collections.unmodifiableSet(new HashSet(Arrays.asList("image/vnd.wap.wbmp", "image/x-ico")));
        f3900k = new a();
        f3901l = Collections.unmodifiableSet(EnumSet.of(ImageHeaderParser.ImageType.JPEG, ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG));
        f3902m = C6517j.m24945f(0);
    }

    public C0849a(List<ImageHeaderParser> list, DisplayMetrics displayMetrics, InterfaceC0707d interfaceC0707d, InterfaceC0705b interfaceC0705b) {
        this.f3906d = list;
        this.f3904b = (DisplayMetrics) C6516i.m24938d(displayMetrics);
        this.f3903a = (InterfaceC0707d) C6516i.m24938d(interfaceC0707d);
        this.f3905c = (InterfaceC0705b) C6516i.m24938d(interfaceC0705b);
    }

    /* renamed from: a */
    public static int m3973a(double d9) {
        return m3986t((d9 / (r1 / r0)) * m3986t(m3978j(d9) * d9));
    }

    /* renamed from: c */
    public static void m3974c(ImageHeaderParser.ImageType imageType, InputStream inputStream, b bVar, InterfaceC0707d interfaceC0707d, DownsampleStrategy downsampleStrategy, int i9, int i10, int i11, int i12, int i13, BitmapFactory.Options options) throws IOException {
        int iFloor;
        int iFloor2;
        if (i10 <= 0 || i11 <= 0) {
            if (Log.isLoggable("Downsampler", 3)) {
                Log.d("Downsampler", "Unable to determine dimensions for: " + imageType + " with target [" + i12 + "x" + i13 + "]");
                return;
            }
            return;
        }
        float fMo3969b = (i9 == 90 || i9 == 270) ? downsampleStrategy.mo3969b(i11, i10, i12, i13) : downsampleStrategy.mo3969b(i10, i11, i12, i13);
        if (fMo3969b <= BitmapDescriptorFactory.HUE_RED) {
            throw new IllegalArgumentException("Cannot scale with factor: " + fMo3969b + " from: " + downsampleStrategy + ", source: [" + i10 + "x" + i11 + "], target: [" + i12 + "x" + i13 + "]");
        }
        DownsampleStrategy.SampleSizeRounding sampleSizeRoundingMo3968a = downsampleStrategy.mo3968a(i10, i11, i12, i13);
        if (sampleSizeRoundingMo3968a == null) {
            throw new IllegalArgumentException("Cannot round with null rounding");
        }
        float f9 = i10;
        float f10 = i11;
        int iM3986t = i10 / m3986t(fMo3969b * f9);
        int iM3986t2 = i11 / m3986t(fMo3969b * f10);
        DownsampleStrategy.SampleSizeRounding sampleSizeRounding = DownsampleStrategy.SampleSizeRounding.MEMORY;
        int iMax = Math.max(1, Integer.highestOneBit(sampleSizeRoundingMo3968a == sampleSizeRounding ? Math.max(iM3986t, iM3986t2) : Math.min(iM3986t, iM3986t2)));
        if (sampleSizeRoundingMo3968a == sampleSizeRounding && iMax < 1.0f / fMo3969b) {
            iMax <<= 1;
        }
        options.inSampleSize = iMax;
        if (imageType == ImageHeaderParser.ImageType.JPEG) {
            float fMin = Math.min(iMax, 8);
            iFloor = (int) Math.ceil(f9 / fMin);
            iFloor2 = (int) Math.ceil(f10 / fMin);
            int i14 = iMax / 8;
            if (i14 > 0) {
                iFloor /= i14;
                iFloor2 /= i14;
            }
        } else if (imageType == ImageHeaderParser.ImageType.PNG || imageType == ImageHeaderParser.ImageType.PNG_A) {
            float f11 = iMax;
            iFloor = (int) Math.floor(f9 / f11);
            iFloor2 = (int) Math.floor(f10 / f11);
        } else if (imageType == ImageHeaderParser.ImageType.WEBP || imageType == ImageHeaderParser.ImageType.WEBP_A) {
            float f12 = iMax;
            iFloor = Math.round(f9 / f12);
            iFloor2 = Math.round(f10 / f12);
        } else if (i10 % iMax == 0 && i11 % iMax == 0) {
            iFloor = i10 / iMax;
            iFloor2 = i11 / iMax;
        } else {
            int[] iArrM3979k = m3979k(inputStream, options, bVar, interfaceC0707d);
            iFloor = iArrM3979k[0];
            iFloor2 = iArrM3979k[1];
        }
        double dMo3969b = downsampleStrategy.mo3969b(iFloor, iFloor2, i12, i13);
        options.inTargetDensity = m3973a(dMo3969b);
        options.inDensity = m3978j(dMo3969b);
        if (m3981o(options)) {
            options.inScaled = true;
        } else {
            options.inTargetDensity = 0;
            options.inDensity = 0;
        }
        if (Log.isLoggable("Downsampler", 2)) {
            Log.v("Downsampler", "Calculate scaling, source: [" + i10 + "x" + i11 + "], target: [" + i12 + "x" + i13 + "], power of two scaled: [" + iFloor + "x" + iFloor2 + "], exact scale factor: " + fMo3969b + ", power of 2 sample size: " + iMax + ", adjusted scale factor: " + dMo3969b + ", target density: " + options.inTargetDensity + ", density: " + options.inDensity);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:?, code lost:
    
        throw r1;
     */
    /* renamed from: g */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Bitmap m3975g(InputStream inputStream, BitmapFactory.Options options, b bVar, InterfaceC0707d interfaceC0707d) throws IOException {
        if (options.inJustDecodeBounds) {
            inputStream.mark(10485760);
        } else {
            bVar.mo3996b();
        }
        int i9 = options.outWidth;
        int i10 = options.outHeight;
        String str = options.outMimeType;
        C5086s.m19899i().lock();
        try {
            try {
                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStream, null, options);
                C5086s.m19899i().unlock();
                if (options.inJustDecodeBounds) {
                    inputStream.reset();
                }
                return bitmapDecodeStream;
            } catch (IllegalArgumentException e9) {
                IOException iOExceptionM3983q = m3983q(e9, i9, i10, str, options);
                if (Log.isLoggable("Downsampler", 3)) {
                    Log.d("Downsampler", "Failed to decode with inBitmap, trying again without Bitmap re-use", iOExceptionM3983q);
                }
                if (options.inBitmap == null) {
                    throw iOExceptionM3983q;
                }
                try {
                    inputStream.reset();
                    interfaceC0707d.mo3487c(options.inBitmap);
                    options.inBitmap = null;
                    Bitmap bitmapM3975g = m3975g(inputStream, options, bVar, interfaceC0707d);
                    C5086s.m19899i().unlock();
                    return bitmapM3975g;
                } catch (IOException unused) {
                    throw iOExceptionM3983q;
                }
            }
        } catch (Throwable th) {
            C5086s.m19899i().unlock();
            throw th;
        }
    }

    @TargetApi(19)
    /* renamed from: h */
    public static String m3976h(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return "[" + bitmap.getWidth() + "x" + bitmap.getHeight() + "] " + bitmap.getConfig() + (" (" + bitmap.getAllocationByteCount() + ")");
    }

    /* renamed from: i */
    public static synchronized BitmapFactory.Options m3977i() {
        BitmapFactory.Options optionsPoll;
        Queue<BitmapFactory.Options> queue = f3902m;
        synchronized (queue) {
            optionsPoll = queue.poll();
        }
        if (optionsPoll == null) {
            optionsPoll = new BitmapFactory.Options();
            m3985s(optionsPoll);
        }
        return optionsPoll;
    }

    /* renamed from: j */
    public static int m3978j(double d9) {
        if (d9 > 1.0d) {
            d9 = 1.0d / d9;
        }
        return (int) Math.round(d9 * 2.147483647E9d);
    }

    /* renamed from: k */
    public static int[] m3979k(InputStream inputStream, BitmapFactory.Options options, b bVar, InterfaceC0707d interfaceC0707d) throws IOException {
        options.inJustDecodeBounds = true;
        m3975g(inputStream, options, bVar, interfaceC0707d);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    /* renamed from: l */
    public static String m3980l(BitmapFactory.Options options) {
        return m3976h(options.inBitmap);
    }

    /* renamed from: o */
    public static boolean m3981o(BitmapFactory.Options options) {
        int i9;
        int i10 = options.inTargetDensity;
        return i10 > 0 && (i9 = options.inDensity) > 0 && i10 != i9;
    }

    /* renamed from: p */
    public static void m3982p(int i9, int i10, String str, BitmapFactory.Options options, Bitmap bitmap, int i11, int i12, long j9) {
        Log.v("Downsampler", "Decoded " + m3976h(bitmap) + " from [" + i9 + "x" + i10 + "] " + str + " with inBitmap " + m3980l(options) + " for [" + i11 + "x" + i12 + "], sample size: " + options.inSampleSize + ", density: " + options.inDensity + ", target density: " + options.inTargetDensity + ", thread: " + Thread.currentThread().getName() + ", duration: " + C6512e.m24922a(j9));
    }

    /* renamed from: q */
    public static IOException m3983q(IllegalArgumentException illegalArgumentException, int i9, int i10, String str, BitmapFactory.Options options) {
        return new IOException("Exception decoding bitmap, outWidth: " + i9 + ", outHeight: " + i10 + ", outMimeType: " + str + ", inBitmap: " + m3980l(options), illegalArgumentException);
    }

    /* renamed from: r */
    public static void m3984r(BitmapFactory.Options options) {
        m3985s(options);
        Queue<BitmapFactory.Options> queue = f3902m;
        synchronized (queue) {
            queue.offer(options);
        }
    }

    /* renamed from: s */
    public static void m3985s(BitmapFactory.Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.inDensity = 0;
        options.inTargetDensity = 0;
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        options.inBitmap = null;
        options.inMutable = true;
    }

    /* renamed from: t */
    public static int m3986t(double d9) {
        return (int) (d9 + 0.5d);
    }

    @TargetApi(26)
    /* renamed from: u */
    public static void m3987u(BitmapFactory.Options options, InterfaceC0707d interfaceC0707d, int i9, int i10) {
        Bitmap.Config config = options.inPreferredConfig;
        if (config == Bitmap.Config.HARDWARE) {
            return;
        }
        Bitmap.Config config2 = options.outConfig;
        if (config2 != null) {
            config = config2;
        }
        options.inBitmap = interfaceC0707d.mo3489e(i9, i10, config);
    }

    /* renamed from: b */
    public final void m3988b(InputStream inputStream, DecodeFormat decodeFormat, boolean z8, boolean z9, BitmapFactory.Options options, int i9, int i10) {
        boolean zHasAlpha;
        if (this.f3907e.m19886c(i9, i10, options, decodeFormat, z8, z9)) {
            return;
        }
        if (decodeFormat == DecodeFormat.PREFER_ARGB_8888) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            return;
        }
        try {
            zHasAlpha = C0824a.m3837b(this.f3906d, inputStream, this.f3905c).hasAlpha();
        } catch (IOException e9) {
            if (Log.isLoggable("Downsampler", 3)) {
                Log.d("Downsampler", "Cannot determine whether the image has alpha or not from header, format " + decodeFormat, e9);
            }
            zHasAlpha = false;
        }
        Bitmap.Config config = zHasAlpha ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        options.inPreferredConfig = config;
        if (config == Bitmap.Config.RGB_565) {
            options.inDither = true;
        }
    }

    /* renamed from: d */
    public InterfaceC0595j<Bitmap> m3989d(InputStream inputStream, int i9, int i10, C6592e c6592e) {
        return m3990e(inputStream, i9, i10, c6592e, f3900k);
    }

    /* renamed from: e */
    public InterfaceC0595j<Bitmap> m3990e(InputStream inputStream, int i9, int i10, C6592e c6592e, b bVar) {
        C6516i.m24935a(inputStream.markSupported(), "You must provide an InputStream that supports mark()");
        byte[] bArr = (byte[]) this.f3905c.mo3481d(C3322C.DEFAULT_BUFFER_SEGMENT_SIZE, byte[].class);
        BitmapFactory.Options optionsM3977i = m3977i();
        optionsM3977i.inTempStorage = bArr;
        DecodeFormat decodeFormat = (DecodeFormat) c6592e.m25209c(f3895f);
        DownsampleStrategy downsampleStrategy = (DownsampleStrategy) c6592e.m25209c(DownsampleStrategy.f3885h);
        boolean zBooleanValue = ((Boolean) c6592e.m25209c(f3897h)).booleanValue();
        C6591d<Boolean> c6591d = f3898i;
        try {
            return C5071d.m19858f(m3991f(inputStream, optionsM3977i, downsampleStrategy, decodeFormat, c6592e.m25209c(c6591d) != null && ((Boolean) c6592e.m25209c(c6591d)).booleanValue(), i9, i10, zBooleanValue, bVar), this.f3903a);
        } finally {
            m3984r(optionsM3977i);
            this.f3905c.put(bArr);
        }
    }

    /* renamed from: f */
    public final Bitmap m3991f(InputStream inputStream, BitmapFactory.Options options, DownsampleStrategy downsampleStrategy, DecodeFormat decodeFormat, boolean z8, int i9, int i10, boolean z9, b bVar) throws IOException {
        int i11;
        int iRound;
        int iRound2;
        long jM24923b = C6512e.m24923b();
        int[] iArrM3979k = m3979k(inputStream, options, bVar, this.f3903a);
        int i12 = iArrM3979k[0];
        int i13 = iArrM3979k[1];
        String str = options.outMimeType;
        boolean z10 = (i12 == -1 || i13 == -1) ? false : z8;
        int iM3836a = C0824a.m3836a(this.f3906d, inputStream, this.f3905c);
        int iM19900j = C5086s.m19900j(iM3836a);
        boolean zM19903m = C5086s.m19903m(iM3836a);
        int i14 = i9 == Integer.MIN_VALUE ? i12 : i9;
        int i15 = i10 == Integer.MIN_VALUE ? i13 : i10;
        ImageHeaderParser.ImageType imageTypeM3837b = C0824a.m3837b(this.f3906d, inputStream, this.f3905c);
        m3974c(imageTypeM3837b, inputStream, bVar, this.f3903a, downsampleStrategy, iM19900j, i12, i13, i14, i15, options);
        m3988b(inputStream, decodeFormat, z10, zM19903m, options, i14, i15);
        int i16 = options.inSampleSize;
        if (m3994v(imageTypeM3837b)) {
            if (i12 < 0 || i13 < 0 || !z9) {
                float f9 = m3981o(options) ? options.inTargetDensity / options.inDensity : 1.0f;
                int i17 = options.inSampleSize;
                float f10 = i17;
                int iCeil = (int) Math.ceil(i12 / f10);
                int iCeil2 = (int) Math.ceil(i13 / f10);
                iRound = Math.round(iCeil * f9);
                iRound2 = Math.round(iCeil2 * f9);
                if (Log.isLoggable("Downsampler", 2)) {
                    Log.v("Downsampler", "Calculated target [" + iRound + "x" + iRound2 + "] for source [" + i12 + "x" + i13 + "], sampleSize: " + i17 + ", targetDensity: " + options.inTargetDensity + ", density: " + options.inDensity + ", density multiplier: " + f9);
                }
            } else {
                iRound = i14;
                iRound2 = i15;
            }
            if (iRound > 0 && iRound2 > 0) {
                m3987u(options, this.f3903a, iRound, iRound2);
            }
        }
        Bitmap bitmapM3975g = m3975g(inputStream, options, bVar, this.f3903a);
        bVar.mo3995a(this.f3903a, bitmapM3975g);
        if (Log.isLoggable("Downsampler", 2)) {
            i11 = iM3836a;
            m3982p(i12, i13, str, options, bitmapM3975g, i9, i10, jM24923b);
        } else {
            i11 = iM3836a;
        }
        if (bitmapM3975g == null) {
            return null;
        }
        bitmapM3975g.setDensity(this.f3904b.densityDpi);
        Bitmap bitmapM19904n = C5086s.m19904n(this.f3903a, bitmapM3975g, i11);
        if (bitmapM3975g.equals(bitmapM19904n)) {
            return bitmapM19904n;
        }
        this.f3903a.mo3487c(bitmapM3975g);
        return bitmapM19904n;
    }

    /* renamed from: m */
    public boolean m3992m(InputStream inputStream) {
        return true;
    }

    /* renamed from: n */
    public boolean m3993n(ByteBuffer byteBuffer) {
        return true;
    }

    /* renamed from: v */
    public final boolean m3994v(ImageHeaderParser.ImageType imageType) {
        return true;
    }
}
