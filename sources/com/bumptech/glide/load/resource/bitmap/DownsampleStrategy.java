package com.bumptech.glide.load.resource.bitmap;

import p243y0.C6591d;

/* loaded from: classes.dex */
public abstract class DownsampleStrategy {

    /* renamed from: a */
    public static final DownsampleStrategy f3878a = new C0847e();

    /* renamed from: b */
    public static final DownsampleStrategy f3879b;

    /* renamed from: c */
    public static final DownsampleStrategy f3880c;

    /* renamed from: d */
    public static final DownsampleStrategy f3881d;

    /* renamed from: e */
    public static final DownsampleStrategy f3882e;

    /* renamed from: f */
    public static final DownsampleStrategy f3883f;

    /* renamed from: g */
    public static final DownsampleStrategy f3884g;

    /* renamed from: h */
    public static final C6591d<DownsampleStrategy> f3885h;

    public enum SampleSizeRounding {
        MEMORY,
        QUALITY
    }

    /* renamed from: com.bumptech.glide.load.resource.bitmap.DownsampleStrategy$a */
    public static class C0843a extends DownsampleStrategy {
        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        /* renamed from: a */
        public SampleSizeRounding mo3968a(int i9, int i10, int i11, int i12) {
            return SampleSizeRounding.QUALITY;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        /* renamed from: b */
        public float mo3969b(int i9, int i10, int i11, int i12) {
            if (Math.min(i10 / i12, i9 / i11) == 0) {
                return 1.0f;
            }
            return 1.0f / Integer.highestOneBit(r1);
        }
    }

    /* renamed from: com.bumptech.glide.load.resource.bitmap.DownsampleStrategy$b */
    public static class C0844b extends DownsampleStrategy {
        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        /* renamed from: a */
        public SampleSizeRounding mo3968a(int i9, int i10, int i11, int i12) {
            return SampleSizeRounding.MEMORY;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        /* renamed from: b */
        public float mo3969b(int i9, int i10, int i11, int i12) {
            int iCeil = (int) Math.ceil(Math.max(i10 / i12, i9 / i11));
            return 1.0f / (r2 << (Math.max(1, Integer.highestOneBit(iCeil)) >= iCeil ? 0 : 1));
        }
    }

    /* renamed from: com.bumptech.glide.load.resource.bitmap.DownsampleStrategy$c */
    public static class C0845c extends DownsampleStrategy {
        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        /* renamed from: a */
        public SampleSizeRounding mo3968a(int i9, int i10, int i11, int i12) {
            return SampleSizeRounding.QUALITY;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        /* renamed from: b */
        public float mo3969b(int i9, int i10, int i11, int i12) {
            return Math.min(1.0f, DownsampleStrategy.f3878a.mo3969b(i9, i10, i11, i12));
        }
    }

    /* renamed from: com.bumptech.glide.load.resource.bitmap.DownsampleStrategy$d */
    public static class C0846d extends DownsampleStrategy {
        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        /* renamed from: a */
        public SampleSizeRounding mo3968a(int i9, int i10, int i11, int i12) {
            return SampleSizeRounding.QUALITY;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        /* renamed from: b */
        public float mo3969b(int i9, int i10, int i11, int i12) {
            return Math.max(i11 / i9, i12 / i10);
        }
    }

    /* renamed from: com.bumptech.glide.load.resource.bitmap.DownsampleStrategy$e */
    public static class C0847e extends DownsampleStrategy {
        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        /* renamed from: a */
        public SampleSizeRounding mo3968a(int i9, int i10, int i11, int i12) {
            return SampleSizeRounding.QUALITY;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        /* renamed from: b */
        public float mo3969b(int i9, int i10, int i11, int i12) {
            return Math.min(i11 / i9, i12 / i10);
        }
    }

    /* renamed from: com.bumptech.glide.load.resource.bitmap.DownsampleStrategy$f */
    public static class C0848f extends DownsampleStrategy {
        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        /* renamed from: a */
        public SampleSizeRounding mo3968a(int i9, int i10, int i11, int i12) {
            return SampleSizeRounding.QUALITY;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        /* renamed from: b */
        public float mo3969b(int i9, int i10, int i11, int i12) {
            return 1.0f;
        }
    }

    static {
        C0846d c0846d = new C0846d();
        f3879b = c0846d;
        f3880c = new C0843a();
        f3881d = new C0844b();
        f3882e = new C0845c();
        f3883f = new C0848f();
        f3884g = c0846d;
        f3885h = C6591d.m25204f("com.bumptech.glide.load.resource.bitmap.Downsampler.DownsampleStrategy", c0846d);
    }

    /* renamed from: a */
    public abstract SampleSizeRounding mo3968a(int i9, int i10, int i11, int i12);

    /* renamed from: b */
    public abstract float mo3969b(int i9, int i10, int i11, int i12);
}
