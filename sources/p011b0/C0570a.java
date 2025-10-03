package p011b0;

import android.text.SpannableStringBuilder;
import com.google.common.base.Ascii;
import java.util.Locale;

/* renamed from: b0.a */
/* loaded from: classes.dex */
public final class C0570a {

    /* renamed from: d */
    public static final InterfaceC0583n f3059d;

    /* renamed from: e */
    public static final String f3060e;

    /* renamed from: f */
    public static final String f3061f;

    /* renamed from: g */
    public static final C0570a f3062g;

    /* renamed from: h */
    public static final C0570a f3063h;

    /* renamed from: a */
    public final boolean f3064a;

    /* renamed from: b */
    public final int f3065b;

    /* renamed from: c */
    public final InterfaceC0583n f3066c;

    /* renamed from: b0.a$a */
    public static final class a {

        /* renamed from: a */
        public boolean f3067a;

        /* renamed from: b */
        public int f3068b;

        /* renamed from: c */
        public InterfaceC0583n f3069c;

        public a() {
            m3227c(C0570a.m3219e(Locale.getDefault()));
        }

        /* renamed from: b */
        public static C0570a m3225b(boolean z8) {
            return z8 ? C0570a.f3063h : C0570a.f3062g;
        }

        /* renamed from: a */
        public C0570a m3226a() {
            return (this.f3068b == 2 && this.f3069c == C0570a.f3059d) ? m3225b(this.f3067a) : new C0570a(this.f3067a, this.f3068b, this.f3069c);
        }

        /* renamed from: c */
        public final void m3227c(boolean z8) {
            this.f3067a = z8;
            this.f3069c = C0570a.f3059d;
            this.f3068b = 2;
        }
    }

    /* renamed from: b0.a$b */
    public static class b {

        /* renamed from: f */
        public static final byte[] f3070f = new byte[1792];

        /* renamed from: a */
        public final CharSequence f3071a;

        /* renamed from: b */
        public final boolean f3072b;

        /* renamed from: c */
        public final int f3073c;

        /* renamed from: d */
        public int f3074d;

        /* renamed from: e */
        public char f3075e;

        static {
            for (int i9 = 0; i9 < 1792; i9++) {
                f3070f[i9] = Character.getDirectionality(i9);
            }
        }

        public b(CharSequence charSequence, boolean z8) {
            this.f3071a = charSequence;
            this.f3072b = z8;
            this.f3073c = charSequence.length();
        }

        /* renamed from: c */
        public static byte m3228c(char c9) {
            return c9 < 1792 ? f3070f[c9] : Character.getDirectionality(c9);
        }

        /* renamed from: a */
        public byte m3229a() {
            char cCharAt = this.f3071a.charAt(this.f3074d - 1);
            this.f3075e = cCharAt;
            if (Character.isLowSurrogate(cCharAt)) {
                int iCodePointBefore = Character.codePointBefore(this.f3071a, this.f3074d);
                this.f3074d -= Character.charCount(iCodePointBefore);
                return Character.getDirectionality(iCodePointBefore);
            }
            this.f3074d--;
            byte bM3228c = m3228c(this.f3075e);
            if (!this.f3072b) {
                return bM3228c;
            }
            char c9 = this.f3075e;
            return c9 == '>' ? m3235h() : c9 == ';' ? m3233f() : bM3228c;
        }

        /* renamed from: b */
        public byte m3230b() {
            char cCharAt = this.f3071a.charAt(this.f3074d);
            this.f3075e = cCharAt;
            if (Character.isHighSurrogate(cCharAt)) {
                int iCodePointAt = Character.codePointAt(this.f3071a, this.f3074d);
                this.f3074d += Character.charCount(iCodePointAt);
                return Character.getDirectionality(iCodePointAt);
            }
            this.f3074d++;
            byte bM3228c = m3228c(this.f3075e);
            if (!this.f3072b) {
                return bM3228c;
            }
            char c9 = this.f3075e;
            return c9 == '<' ? m3236i() : c9 == '&' ? m3234g() : bM3228c;
        }

        /* renamed from: d */
        public int m3231d() {
            this.f3074d = 0;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            while (this.f3074d < this.f3073c && i9 == 0) {
                byte bM3230b = m3230b();
                if (bM3230b != 0) {
                    if (bM3230b == 1 || bM3230b == 2) {
                        if (i11 == 0) {
                            return 1;
                        }
                    } else if (bM3230b != 9) {
                        switch (bM3230b) {
                            case 14:
                            case 15:
                                i11++;
                                i10 = -1;
                                continue;
                            case 16:
                            case 17:
                                i11++;
                                i10 = 1;
                                continue;
                            case 18:
                                i11--;
                                i10 = 0;
                                continue;
                        }
                    }
                } else if (i11 == 0) {
                    return -1;
                }
                i9 = i11;
            }
            if (i9 == 0) {
                return 0;
            }
            if (i10 != 0) {
                return i10;
            }
            while (this.f3074d > 0) {
                switch (m3229a()) {
                    case 14:
                    case 15:
                        if (i9 == i11) {
                            return -1;
                        }
                        break;
                    case 16:
                    case 17:
                        if (i9 == i11) {
                            return 1;
                        }
                        break;
                    case 18:
                        i11++;
                        continue;
                }
                i11--;
            }
            return 0;
        }

        /* renamed from: e */
        public int m3232e() {
            this.f3074d = this.f3073c;
            int i9 = 0;
            while (true) {
                int i10 = i9;
                while (this.f3074d > 0) {
                    byte bM3229a = m3229a();
                    if (bM3229a != 0) {
                        if (bM3229a == 1 || bM3229a == 2) {
                            if (i9 == 0) {
                                return 1;
                            }
                            if (i10 == 0) {
                                break;
                            }
                        } else if (bM3229a != 9) {
                            switch (bM3229a) {
                                case 14:
                                case 15:
                                    if (i10 == i9) {
                                        return -1;
                                    }
                                    i9--;
                                    break;
                                case 16:
                                case 17:
                                    if (i10 == i9) {
                                        return 1;
                                    }
                                    i9--;
                                    break;
                                case 18:
                                    i9++;
                                    break;
                                default:
                                    if (i10 != 0) {
                                        break;
                                    } else {
                                        break;
                                    }
                            }
                        } else {
                            continue;
                        }
                    } else {
                        if (i9 == 0) {
                            return -1;
                        }
                        if (i10 == 0) {
                            break;
                        }
                    }
                }
                return 0;
            }
        }

        /* renamed from: f */
        public final byte m3233f() {
            char cCharAt;
            int i9 = this.f3074d;
            do {
                int i10 = this.f3074d;
                if (i10 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f3071a;
                int i11 = i10 - 1;
                this.f3074d = i11;
                cCharAt = charSequence.charAt(i11);
                this.f3075e = cCharAt;
                if (cCharAt == '&') {
                    return Ascii.f15382FF;
                }
            } while (cCharAt != ';');
            this.f3074d = i9;
            this.f3075e = ';';
            return Ascii.f15380CR;
        }

        /* renamed from: g */
        public final byte m3234g() {
            char cCharAt;
            do {
                int i9 = this.f3074d;
                if (i9 >= this.f3073c) {
                    return Ascii.f15382FF;
                }
                CharSequence charSequence = this.f3071a;
                this.f3074d = i9 + 1;
                cCharAt = charSequence.charAt(i9);
                this.f3075e = cCharAt;
            } while (cCharAt != ';');
            return Ascii.f15382FF;
        }

        /* renamed from: h */
        public final byte m3235h() {
            char cCharAt;
            int i9 = this.f3074d;
            while (true) {
                int i10 = this.f3074d;
                if (i10 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f3071a;
                int i11 = i10 - 1;
                this.f3074d = i11;
                char cCharAt2 = charSequence.charAt(i11);
                this.f3075e = cCharAt2;
                if (cCharAt2 == '<') {
                    return Ascii.f15382FF;
                }
                if (cCharAt2 == '>') {
                    break;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    do {
                        int i12 = this.f3074d;
                        if (i12 > 0) {
                            CharSequence charSequence2 = this.f3071a;
                            int i13 = i12 - 1;
                            this.f3074d = i13;
                            cCharAt = charSequence2.charAt(i13);
                            this.f3075e = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
            this.f3074d = i9;
            this.f3075e = '>';
            return Ascii.f15380CR;
        }

        /* renamed from: i */
        public final byte m3236i() {
            char cCharAt;
            int i9 = this.f3074d;
            while (true) {
                int i10 = this.f3074d;
                if (i10 >= this.f3073c) {
                    this.f3074d = i9;
                    this.f3075e = '<';
                    return Ascii.f15380CR;
                }
                CharSequence charSequence = this.f3071a;
                this.f3074d = i10 + 1;
                char cCharAt2 = charSequence.charAt(i10);
                this.f3075e = cCharAt2;
                if (cCharAt2 == '>') {
                    return Ascii.f15382FF;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    do {
                        int i11 = this.f3074d;
                        if (i11 < this.f3073c) {
                            CharSequence charSequence2 = this.f3071a;
                            this.f3074d = i11 + 1;
                            cCharAt = charSequence2.charAt(i11);
                            this.f3075e = cCharAt;
                        }
                    } while (cCharAt != cCharAt2);
                }
            }
        }
    }

    static {
        InterfaceC0583n interfaceC0583n = C0584o.f3091c;
        f3059d = interfaceC0583n;
        f3060e = Character.toString((char) 8206);
        f3061f = Character.toString((char) 8207);
        f3062g = new C0570a(false, 2, interfaceC0583n);
        f3063h = new C0570a(true, 2, interfaceC0583n);
    }

    public C0570a(boolean z8, int i9, InterfaceC0583n interfaceC0583n) {
        this.f3064a = z8;
        this.f3065b = i9;
        this.f3066c = interfaceC0583n;
    }

    /* renamed from: a */
    public static int m3216a(CharSequence charSequence) {
        return new b(charSequence, false).m3231d();
    }

    /* renamed from: b */
    public static int m3217b(CharSequence charSequence) {
        return new b(charSequence, false).m3232e();
    }

    /* renamed from: c */
    public static C0570a m3218c() {
        return new a().m3226a();
    }

    /* renamed from: e */
    public static boolean m3219e(Locale locale) {
        return C0585p.m3264a(locale) == 1;
    }

    /* renamed from: d */
    public boolean m3220d() {
        return (this.f3065b & 2) != 0;
    }

    /* renamed from: f */
    public final String m3221f(CharSequence charSequence, InterfaceC0583n interfaceC0583n) {
        boolean zIsRtl = interfaceC0583n.isRtl(charSequence, 0, charSequence.length());
        return (this.f3064a || !(zIsRtl || m3217b(charSequence) == 1)) ? this.f3064a ? (!zIsRtl || m3217b(charSequence) == -1) ? f3061f : "" : "" : f3060e;
    }

    /* renamed from: g */
    public final String m3222g(CharSequence charSequence, InterfaceC0583n interfaceC0583n) {
        boolean zIsRtl = interfaceC0583n.isRtl(charSequence, 0, charSequence.length());
        return (this.f3064a || !(zIsRtl || m3216a(charSequence) == 1)) ? this.f3064a ? (!zIsRtl || m3216a(charSequence) == -1) ? f3061f : "" : "" : f3060e;
    }

    /* renamed from: h */
    public CharSequence m3223h(CharSequence charSequence) {
        return m3224i(charSequence, this.f3066c, true);
    }

    /* renamed from: i */
    public CharSequence m3224i(CharSequence charSequence, InterfaceC0583n interfaceC0583n, boolean z8) {
        if (charSequence == null) {
            return null;
        }
        boolean zIsRtl = interfaceC0583n.isRtl(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (m3220d() && z8) {
            spannableStringBuilder.append((CharSequence) m3222g(charSequence, zIsRtl ? C0584o.f3090b : C0584o.f3089a));
        }
        if (zIsRtl != this.f3064a) {
            spannableStringBuilder.append(zIsRtl ? (char) 8235 : (char) 8234);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z8) {
            spannableStringBuilder.append((CharSequence) m3221f(charSequence, zIsRtl ? C0584o.f3090b : C0584o.f3089a));
        }
        return spannableStringBuilder;
    }
}
