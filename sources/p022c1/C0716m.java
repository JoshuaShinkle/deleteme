package p022c1;

import android.graphics.Bitmap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import p226w1.C6517j;

/* renamed from: c1.m */
/* loaded from: classes.dex */
public class C0716m implements InterfaceC0714k {

    /* renamed from: d */
    public static final Bitmap.Config[] f3387d;

    /* renamed from: e */
    public static final Bitmap.Config[] f3388e;

    /* renamed from: f */
    public static final Bitmap.Config[] f3389f;

    /* renamed from: g */
    public static final Bitmap.Config[] f3390g;

    /* renamed from: h */
    public static final Bitmap.Config[] f3391h;

    /* renamed from: a */
    public final c f3392a = new c();

    /* renamed from: b */
    public final C0710g<b, Bitmap> f3393b = new C0710g<>();

    /* renamed from: c */
    public final Map<Bitmap.Config, NavigableMap<Integer, Integer>> f3394c = new HashMap();

    /* renamed from: c1.m$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f3395a;

        static {
            int[] iArr = new int[Bitmap.Config.values().length];
            f3395a = iArr;
            try {
                iArr[Bitmap.Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3395a[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3395a[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3395a[Bitmap.Config.ALPHA_8.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* renamed from: c1.m$b */
    public static final class b implements InterfaceC0715l {

        /* renamed from: a */
        public final c f3396a;

        /* renamed from: b */
        public int f3397b;

        /* renamed from: c */
        public Bitmap.Config f3398c;

        public b(c cVar) {
            this.f3396a = cVar;
        }

        @Override // p022c1.InterfaceC0715l
        /* renamed from: a */
        public void mo3515a() {
            this.f3396a.m3484c(this);
        }

        /* renamed from: b */
        public void m3543b(int i9, Bitmap.Config config) {
            this.f3397b = i9;
            this.f3398c = config;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f3397b == bVar.f3397b && C6517j.m24943d(this.f3398c, bVar.f3398c);
        }

        public int hashCode() {
            int i9 = this.f3397b * 31;
            Bitmap.Config config = this.f3398c;
            return i9 + (config != null ? config.hashCode() : 0);
        }

        public String toString() {
            return C0716m.m3538h(this.f3397b, this.f3398c);
        }
    }

    /* renamed from: c1.m$c */
    public static class c extends AbstractC0706c<b> {
        @Override // p022c1.AbstractC0706c
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public b mo3482a() {
            return new b(this);
        }

        /* renamed from: e */
        public b m3545e(int i9, Bitmap.Config config) {
            b bVarM3483b = m3483b();
            bVarM3483b.m3543b(i9, config);
            return bVarM3483b;
        }
    }

    static {
        Bitmap.Config[] configArr = (Bitmap.Config[]) Arrays.copyOf(new Bitmap.Config[]{Bitmap.Config.ARGB_8888, null}, 3);
        configArr[configArr.length - 1] = Bitmap.Config.RGBA_F16;
        f3387d = configArr;
        f3388e = configArr;
        f3389f = new Bitmap.Config[]{Bitmap.Config.RGB_565};
        f3390g = new Bitmap.Config[]{Bitmap.Config.ARGB_4444};
        f3391h = new Bitmap.Config[]{Bitmap.Config.ALPHA_8};
    }

    /* renamed from: h */
    public static String m3538h(int i9, Bitmap.Config config) {
        return "[" + i9 + "](" + config + ")";
    }

    /* renamed from: i */
    public static Bitmap.Config[] m3539i(Bitmap.Config config) {
        if (Bitmap.Config.RGBA_F16.equals(config)) {
            return f3388e;
        }
        int i9 = a.f3395a[config.ordinal()];
        return i9 != 1 ? i9 != 2 ? i9 != 3 ? i9 != 4 ? new Bitmap.Config[]{config} : f3391h : f3390g : f3389f : f3387d;
    }

    @Override // p022c1.InterfaceC0714k
    /* renamed from: a */
    public String mo3533a(int i9, int i10, Bitmap.Config config) {
        return m3538h(C6517j.m24946g(i9, i10, config), config);
    }

    @Override // p022c1.InterfaceC0714k
    /* renamed from: b */
    public int mo3534b(Bitmap bitmap) {
        return C6517j.m24947h(bitmap);
    }

    @Override // p022c1.InterfaceC0714k
    /* renamed from: c */
    public void mo3535c(Bitmap bitmap) {
        b bVarM3545e = this.f3392a.m3545e(C6517j.m24947h(bitmap), bitmap.getConfig());
        this.f3393b.m3497d(bVarM3545e, bitmap);
        NavigableMap<Integer, Integer> navigableMapM3542j = m3542j(bitmap.getConfig());
        Integer num = navigableMapM3542j.get(Integer.valueOf(bVarM3545e.f3397b));
        navigableMapM3542j.put(Integer.valueOf(bVarM3545e.f3397b), Integer.valueOf(num != null ? 1 + num.intValue() : 1));
    }

    @Override // p022c1.InterfaceC0714k
    /* renamed from: d */
    public Bitmap mo3536d(int i9, int i10, Bitmap.Config config) {
        b bVarM3541g = m3541g(C6517j.m24946g(i9, i10, config), config);
        Bitmap bitmapM3494a = this.f3393b.m3494a(bVarM3541g);
        if (bitmapM3494a != null) {
            m3540f(Integer.valueOf(bVarM3541g.f3397b), bitmapM3494a);
            bitmapM3494a.reconfigure(i9, i10, bitmapM3494a.getConfig() != null ? bitmapM3494a.getConfig() : Bitmap.Config.ARGB_8888);
        }
        return bitmapM3494a;
    }

    @Override // p022c1.InterfaceC0714k
    /* renamed from: e */
    public String mo3537e(Bitmap bitmap) {
        return m3538h(C6517j.m24947h(bitmap), bitmap.getConfig());
    }

    /* renamed from: f */
    public final void m3540f(Integer num, Bitmap bitmap) {
        NavigableMap<Integer, Integer> navigableMapM3542j = m3542j(bitmap.getConfig());
        Integer num2 = navigableMapM3542j.get(num);
        if (num2 != null) {
            if (num2.intValue() == 1) {
                navigableMapM3542j.remove(num);
                return;
            } else {
                navigableMapM3542j.put(num, Integer.valueOf(num2.intValue() - 1));
                return;
            }
        }
        throw new NullPointerException("Tried to decrement empty size, size: " + num + ", removed: " + mo3537e(bitmap) + ", this: " + this);
    }

    /* renamed from: g */
    public final b m3541g(int i9, Bitmap.Config config) {
        b bVarM3545e = this.f3392a.m3545e(i9, config);
        for (Bitmap.Config config2 : m3539i(config)) {
            Integer numCeilingKey = m3542j(config2).ceilingKey(Integer.valueOf(i9));
            if (numCeilingKey != null && numCeilingKey.intValue() <= i9 * 8) {
                if (numCeilingKey.intValue() == i9) {
                    if (config2 == null) {
                        if (config == null) {
                            return bVarM3545e;
                        }
                    } else if (config2.equals(config)) {
                        return bVarM3545e;
                    }
                }
                this.f3392a.m3484c(bVarM3545e);
                return this.f3392a.m3545e(numCeilingKey.intValue(), config2);
            }
        }
        return bVarM3545e;
    }

    /* renamed from: j */
    public final NavigableMap<Integer, Integer> m3542j(Bitmap.Config config) {
        NavigableMap<Integer, Integer> navigableMap = this.f3394c.get(config);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.f3394c.put(config, treeMap);
        return treeMap;
    }

    @Override // p022c1.InterfaceC0714k
    public Bitmap removeLast() {
        Bitmap bitmapM3498f = this.f3393b.m3498f();
        if (bitmapM3498f != null) {
            m3540f(Integer.valueOf(C6517j.m24947h(bitmapM3498f)), bitmapM3498f);
        }
        return bitmapM3498f;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SizeConfigStrategy{groupedMap=");
        sb.append(this.f3393b);
        sb.append(", sortedSizes=(");
        for (Map.Entry<Bitmap.Config, NavigableMap<Integer, Integer>> entry : this.f3394c.entrySet()) {
            sb.append(entry.getKey());
            sb.append('[');
            sb.append(entry.getValue());
            sb.append("], ");
        }
        if (!this.f3394c.isEmpty()) {
            sb.replace(sb.length() - 2, sb.length(), "");
        }
        sb.append(")}");
        return sb.toString();
    }
}
