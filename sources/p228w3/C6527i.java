package p228w3;

import com.cyberlink.clgpuimage.IBeautyFilter2;

/* renamed from: w3.i */
/* loaded from: classes.dex */
public class C6527i implements InterfaceC6541w {

    /* renamed from: a */
    public final String f21966a;

    /* renamed from: b */
    public final boolean f21967b;

    /* renamed from: c */
    public final IBeautyFilter2.FilterType f21968c;

    /* renamed from: d */
    public final IBeautyFilter2.EffectMode f21969d;

    public C6527i() {
        this(null, false, null);
    }

    /* renamed from: a */
    public IBeautyFilter2.EffectMode m24995a() {
        return this.f21969d;
    }

    /* renamed from: b */
    public IBeautyFilter2.FilterType m24996b() {
        return this.f21968c;
    }

    public C6527i(String str, boolean z8, IBeautyFilter2.FilterType filterType) {
        this(str, z8, filterType, null);
    }

    public C6527i(String str, boolean z8, IBeautyFilter2.FilterType filterType, IBeautyFilter2.EffectMode effectMode) {
        this.f21966a = str;
        this.f21967b = z8;
        this.f21968c = filterType;
        this.f21969d = effectMode;
    }
}
