package p011b0;

import java.util.Locale;

/* renamed from: b0.o */
/* loaded from: classes.dex */
public final class C0584o {

    /* renamed from: a */
    public static final InterfaceC0583n f3089a = new e(null, false);

    /* renamed from: b */
    public static final InterfaceC0583n f3090b = new e(null, true);

    /* renamed from: c */
    public static final InterfaceC0583n f3091c;

    /* renamed from: d */
    public static final InterfaceC0583n f3092d;

    /* renamed from: e */
    public static final InterfaceC0583n f3093e;

    /* renamed from: f */
    public static final InterfaceC0583n f3094f;

    /* renamed from: b0.o$a */
    public static class a implements c {

        /* renamed from: b */
        public static final a f3095b = new a(true);

        /* renamed from: a */
        public final boolean f3096a;

        public a(boolean z8) {
            this.f3096a = z8;
        }

        @Override // p011b0.C0584o.c
        /* renamed from: a */
        public int mo3261a(CharSequence charSequence, int i9, int i10) {
            int i11 = i10 + i9;
            boolean z8 = false;
            while (i9 < i11) {
                int iM3259a = C0584o.m3259a(Character.getDirectionality(charSequence.charAt(i9)));
                if (iM3259a != 0) {
                    if (iM3259a != 1) {
                        continue;
                        i9++;
                        z8 = z8;
                    } else if (!this.f3096a) {
                        return 1;
                    }
                } else if (this.f3096a) {
                    return 0;
                }
                z8 = true;
                i9++;
                z8 = z8;
            }
            if (z8) {
                return this.f3096a ? 1 : 0;
            }
            return 2;
        }
    }

    /* renamed from: b0.o$b */
    public static class b implements c {

        /* renamed from: a */
        public static final b f3097a = new b();

        @Override // p011b0.C0584o.c
        /* renamed from: a */
        public int mo3261a(CharSequence charSequence, int i9, int i10) {
            int i11 = i10 + i9;
            int iM3260b = 2;
            while (i9 < i11 && iM3260b == 2) {
                iM3260b = C0584o.m3260b(Character.getDirectionality(charSequence.charAt(i9)));
                i9++;
            }
            return iM3260b;
        }
    }

    /* renamed from: b0.o$c */
    public interface c {
        /* renamed from: a */
        int mo3261a(CharSequence charSequence, int i9, int i10);
    }

    /* renamed from: b0.o$d */
    public static abstract class d implements InterfaceC0583n {

        /* renamed from: a */
        public final c f3098a;

        public d(c cVar) {
            this.f3098a = cVar;
        }

        /* renamed from: a */
        public abstract boolean mo3262a();

        /* renamed from: b */
        public final boolean m3263b(CharSequence charSequence, int i9, int i10) {
            int iMo3261a = this.f3098a.mo3261a(charSequence, i9, i10);
            if (iMo3261a == 0) {
                return true;
            }
            if (iMo3261a != 1) {
                return mo3262a();
            }
            return false;
        }

        @Override // p011b0.InterfaceC0583n
        public boolean isRtl(CharSequence charSequence, int i9, int i10) {
            if (charSequence == null || i9 < 0 || i10 < 0 || charSequence.length() - i10 < i9) {
                throw new IllegalArgumentException();
            }
            return this.f3098a == null ? mo3262a() : m3263b(charSequence, i9, i10);
        }
    }

    /* renamed from: b0.o$e */
    public static class e extends d {

        /* renamed from: b */
        public final boolean f3099b;

        public e(c cVar, boolean z8) {
            super(cVar);
            this.f3099b = z8;
        }

        @Override // p011b0.C0584o.d
        /* renamed from: a */
        public boolean mo3262a() {
            return this.f3099b;
        }
    }

    /* renamed from: b0.o$f */
    public static class f extends d {

        /* renamed from: b */
        public static final f f3100b = new f();

        public f() {
            super(null);
        }

        @Override // p011b0.C0584o.d
        /* renamed from: a */
        public boolean mo3262a() {
            return C0585p.m3264a(Locale.getDefault()) == 1;
        }
    }

    static {
        b bVar = b.f3097a;
        f3091c = new e(bVar, false);
        f3092d = new e(bVar, true);
        f3093e = new e(a.f3095b, false);
        f3094f = f.f3100b;
    }

    /* renamed from: a */
    public static int m3259a(int i9) {
        if (i9 != 0) {
            return (i9 == 1 || i9 == 2) ? 0 : 2;
        }
        return 1;
    }

    /* renamed from: b */
    public static int m3260b(int i9) {
        if (i9 != 0) {
            if (i9 == 1 || i9 == 2) {
                return 0;
            }
            switch (i9) {
                case 14:
                case 15:
                    break;
                case 16:
                case 17:
                    return 0;
                default:
                    return 2;
            }
        }
        return 1;
    }
}
