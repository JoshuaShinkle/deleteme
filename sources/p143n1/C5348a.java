package p143n1;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.load.C0824a;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Queue;
import p022c1.InterfaceC0705b;
import p022c1.InterfaceC0707d;
import p093i1.C5043b;
import p225w0.C6505c;
import p225w0.C6506d;
import p225w0.C6507e;
import p225w0.InterfaceC6503a;
import p226w1.C6512e;
import p226w1.C6517j;
import p243y0.C6592e;
import p243y0.InterfaceC6593f;

/* renamed from: n1.a */
/* loaded from: classes.dex */
public class C5348a implements InterfaceC6593f<ByteBuffer, C5350c> {

    /* renamed from: f */
    public static final a f18197f = new a();

    /* renamed from: g */
    public static final b f18198g = new b();

    /* renamed from: a */
    public final Context f18199a;

    /* renamed from: b */
    public final List<ImageHeaderParser> f18200b;

    /* renamed from: c */
    public final b f18201c;

    /* renamed from: d */
    public final a f18202d;

    /* renamed from: e */
    public final C5349b f18203e;

    /* renamed from: n1.a$a */
    public static class a {
        /* renamed from: a */
        public InterfaceC6503a m21000a(InterfaceC6503a.a aVar, C6505c c6505c, ByteBuffer byteBuffer, int i9) {
            return new C6507e(aVar, c6505c, byteBuffer, i9);
        }
    }

    /* renamed from: n1.a$b */
    public static class b {

        /* renamed from: a */
        public final Queue<C6506d> f18204a = C6517j.m24945f(0);

        /* renamed from: a */
        public synchronized C6506d m21001a(ByteBuffer byteBuffer) {
            C6506d c6506dPoll;
            c6506dPoll = this.f18204a.poll();
            if (c6506dPoll == null) {
                c6506dPoll = new C6506d();
            }
            return c6506dPoll.m24899p(byteBuffer);
        }

        /* renamed from: b */
        public synchronized void m21002b(C6506d c6506d) {
            c6506d.m24884a();
            this.f18204a.offer(c6506d);
        }
    }

    public C5348a(Context context, List<ImageHeaderParser> list, InterfaceC0707d interfaceC0707d, InterfaceC0705b interfaceC0705b) {
        this(context, list, interfaceC0707d, interfaceC0705b, f18198g, f18197f);
    }

    /* renamed from: e */
    public static int m20996e(C6505c c6505c, int i9, int i10) {
        int iMin = Math.min(c6505c.m24880a() / i10, c6505c.m24883d() / i9);
        int iMax = Math.max(1, iMin == 0 ? 0 : Integer.highestOneBit(iMin));
        if (Log.isLoggable("BufferGifDecoder", 2) && iMax > 1) {
            Log.v("BufferGifDecoder", "Downsampling GIF, sampleSize: " + iMax + ", target dimens: [" + i9 + "x" + i10 + "], actual dimens: [" + c6505c.m24883d() + "x" + c6505c.m24880a() + "]");
        }
        return iMax;
    }

    /* renamed from: c */
    public final C5352e m20997c(ByteBuffer byteBuffer, int i9, int i10, C6506d c6506d, C6592e c6592e) {
        long jM24923b = C6512e.m24923b();
        try {
            C6505c c6505cM24886c = c6506d.m24886c();
            if (c6505cM24886c.m24881b() > 0 && c6505cM24886c.m24882c() == 0) {
                Bitmap.Config config = c6592e.m25209c(C5356i.f18240a) == DecodeFormat.PREFER_RGB_565 ? Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_8888;
                InterfaceC6503a interfaceC6503aM21000a = this.f18202d.m21000a(this.f18203e, c6505cM24886c, byteBuffer, m20996e(c6505cM24886c, i9, i10));
                interfaceC6503aM21000a.mo24876c(config);
                interfaceC6503aM21000a.advance();
                Bitmap nextFrame = interfaceC6503aM21000a.getNextFrame();
                if (nextFrame == null) {
                    return null;
                }
                C5352e c5352e = new C5352e(new C5350c(this.f18199a, interfaceC6503aM21000a, C5043b.m19695c(), i9, i10, nextFrame));
                if (Log.isLoggable("BufferGifDecoder", 2)) {
                    Log.v("BufferGifDecoder", "Decoded GIF from stream in " + C6512e.m24922a(jM24923b));
                }
                return c5352e;
            }
            if (Log.isLoggable("BufferGifDecoder", 2)) {
                Log.v("BufferGifDecoder", "Decoded GIF from stream in " + C6512e.m24922a(jM24923b));
            }
            return null;
        } finally {
            if (Log.isLoggable("BufferGifDecoder", 2)) {
                Log.v("BufferGifDecoder", "Decoded GIF from stream in " + C6512e.m24922a(jM24923b));
            }
        }
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public C5352e mo3998b(ByteBuffer byteBuffer, int i9, int i10, C6592e c6592e) {
        C6506d c6506dM21001a = this.f18201c.m21001a(byteBuffer);
        try {
            return m20997c(byteBuffer, i9, i10, c6506dM21001a, c6592e);
        } finally {
            this.f18201c.m21002b(c6506dM21001a);
        }
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public boolean mo3997a(ByteBuffer byteBuffer, C6592e c6592e) {
        return !((Boolean) c6592e.m25209c(C5356i.f18241b)).booleanValue() && C0824a.m3838c(this.f18200b, byteBuffer) == ImageHeaderParser.ImageType.GIF;
    }

    public C5348a(Context context, List<ImageHeaderParser> list, InterfaceC0707d interfaceC0707d, InterfaceC0705b interfaceC0705b, b bVar, a aVar) {
        this.f18199a = context.getApplicationContext();
        this.f18200b = list;
        this.f18202d = aVar;
        this.f18203e = new C5349b(interfaceC0707d, interfaceC0705b);
        this.f18201c = bVar;
    }
}
