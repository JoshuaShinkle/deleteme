package p103j1;

import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import p012b1.InterfaceC0595j;
import p022c1.InterfaceC0707d;
import p243y0.C6591d;
import p243y0.C6592e;
import p243y0.InterfaceC6593f;

/* renamed from: j1.v */
/* loaded from: classes.dex */
public class C5089v<T> implements InterfaceC6593f<T, Bitmap> {

    /* renamed from: d */
    public static final C6591d<Long> f17531d = C6591d.m25201a("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.TargetFrame", -1L, new a());

    /* renamed from: e */
    public static final C6591d<Integer> f17532e = C6591d.m25201a("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.FrameOption", 2, new b());

    /* renamed from: f */
    public static final d f17533f = new d();

    /* renamed from: a */
    public final e<T> f17534a;

    /* renamed from: b */
    public final InterfaceC0707d f17535b;

    /* renamed from: c */
    public final d f17536c;

    /* renamed from: j1.v$a */
    public class a implements C6591d.b<Long> {

        /* renamed from: a */
        public final ByteBuffer f17537a = ByteBuffer.allocate(8);

        @Override // p243y0.C6591d.b
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo19916a(byte[] bArr, Long l9, MessageDigest messageDigest) {
            messageDigest.update(bArr);
            synchronized (this.f17537a) {
                this.f17537a.position(0);
                messageDigest.update(this.f17537a.putLong(l9.longValue()).array());
            }
        }
    }

    /* renamed from: j1.v$b */
    public class b implements C6591d.b<Integer> {

        /* renamed from: a */
        public final ByteBuffer f17538a = ByteBuffer.allocate(4);

        @Override // p243y0.C6591d.b
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo19916a(byte[] bArr, Integer num, MessageDigest messageDigest) {
            if (num == null) {
                return;
            }
            messageDigest.update(bArr);
            synchronized (this.f17538a) {
                this.f17538a.position(0);
                messageDigest.update(this.f17538a.putInt(num.intValue()).array());
            }
        }
    }

    /* renamed from: j1.v$c */
    public static final class c implements e<AssetFileDescriptor> {
        public c() {
        }

        public /* synthetic */ c(a aVar) {
            this();
        }

        @Override // p103j1.C5089v.e
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo19919a(MediaMetadataRetriever mediaMetadataRetriever, AssetFileDescriptor assetFileDescriptor) throws IllegalArgumentException {
            mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        }
    }

    /* renamed from: j1.v$d */
    public static class d {
        /* renamed from: a */
        public MediaMetadataRetriever m19921a() {
            return new MediaMetadataRetriever();
        }
    }

    /* renamed from: j1.v$e */
    public interface e<T> {
        /* renamed from: a */
        void mo19919a(MediaMetadataRetriever mediaMetadataRetriever, T t8);
    }

    /* renamed from: j1.v$f */
    public static final class f implements e<ParcelFileDescriptor> {
        @Override // p103j1.C5089v.e
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo19919a(MediaMetadataRetriever mediaMetadataRetriever, ParcelFileDescriptor parcelFileDescriptor) throws IllegalArgumentException {
            mediaMetadataRetriever.setDataSource(parcelFileDescriptor.getFileDescriptor());
        }
    }

    public C5089v(InterfaceC0707d interfaceC0707d, e<T> eVar) {
        this(interfaceC0707d, eVar, f17533f);
    }

    /* renamed from: c */
    public static InterfaceC6593f<AssetFileDescriptor, Bitmap> m19911c(InterfaceC0707d interfaceC0707d) {
        return new C5089v(interfaceC0707d, new c(null));
    }

    /* renamed from: d */
    public static Bitmap m19912d(MediaMetadataRetriever mediaMetadataRetriever, long j9, int i9, int i10, int i11, DownsampleStrategy downsampleStrategy) {
        Bitmap bitmapM19914f = (Build.VERSION.SDK_INT < 27 || i10 == Integer.MIN_VALUE || i11 == Integer.MIN_VALUE || downsampleStrategy == DownsampleStrategy.f3883f) ? null : m19914f(mediaMetadataRetriever, j9, i9, i10, i11, downsampleStrategy);
        return bitmapM19914f == null ? m19913e(mediaMetadataRetriever, j9, i9) : bitmapM19914f;
    }

    /* renamed from: e */
    public static Bitmap m19913e(MediaMetadataRetriever mediaMetadataRetriever, long j9, int i9) {
        return mediaMetadataRetriever.getFrameAtTime(j9, i9);
    }

    @TargetApi(27)
    /* renamed from: f */
    public static Bitmap m19914f(MediaMetadataRetriever mediaMetadataRetriever, long j9, int i9, int i10, int i11, DownsampleStrategy downsampleStrategy) {
        try {
            int i12 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
            int i13 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
            int i14 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(24));
            if (i14 == 90 || i14 == 270) {
                i13 = i12;
                i12 = i13;
            }
            float fMo3969b = downsampleStrategy.mo3969b(i12, i13, i10, i11);
            return mediaMetadataRetriever.getScaledFrameAtTime(j9, i9, Math.round(i12 * fMo3969b), Math.round(fMo3969b * i13));
        } catch (Throwable th) {
            if (!Log.isLoggable("VideoDecoder", 3)) {
                return null;
            }
            Log.d("VideoDecoder", "Exception trying to decode frame on oreo+", th);
            return null;
        }
    }

    /* renamed from: g */
    public static InterfaceC6593f<ParcelFileDescriptor, Bitmap> m19915g(InterfaceC0707d interfaceC0707d) {
        return new C5089v(interfaceC0707d, new f());
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: a */
    public boolean mo3997a(T t8, C6592e c6592e) {
        return true;
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: b */
    public InterfaceC0595j<Bitmap> mo3998b(T t8, int i9, int i10, C6592e c6592e) throws IOException {
        long jLongValue = ((Long) c6592e.m25209c(f17531d)).longValue();
        if (jLongValue < 0 && jLongValue != -1) {
            throw new IllegalArgumentException("Requested frame must be non-negative, or DEFAULT_FRAME, given: " + jLongValue);
        }
        Integer num = (Integer) c6592e.m25209c(f17532e);
        if (num == null) {
            num = 2;
        }
        DownsampleStrategy downsampleStrategy = (DownsampleStrategy) c6592e.m25209c(DownsampleStrategy.f3885h);
        if (downsampleStrategy == null) {
            downsampleStrategy = DownsampleStrategy.f3884g;
        }
        DownsampleStrategy downsampleStrategy2 = downsampleStrategy;
        MediaMetadataRetriever mediaMetadataRetrieverM19921a = this.f17536c.m19921a();
        try {
            try {
                this.f17534a.mo19919a(mediaMetadataRetrieverM19921a, t8);
                Bitmap bitmapM19912d = m19912d(mediaMetadataRetrieverM19921a, jLongValue, num.intValue(), i9, i10, downsampleStrategy2);
                mediaMetadataRetrieverM19921a.release();
                return C5071d.m19858f(bitmapM19912d, this.f17535b);
            } catch (RuntimeException e9) {
                throw new IOException(e9);
            }
        } catch (Throwable th) {
            mediaMetadataRetrieverM19921a.release();
            throw th;
        }
    }

    public C5089v(InterfaceC0707d interfaceC0707d, e<T> eVar, d dVar) {
        this.f17535b = interfaceC0707d;
        this.f17534a = eVar;
        this.f17536c = dVar;
    }
}
