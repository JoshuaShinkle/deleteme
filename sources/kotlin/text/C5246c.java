package kotlin.text;

import java.nio.charset.Charset;
import org.apache.commons.lang3.CharEncoding;
import p007a6.C0042f;

/* renamed from: kotlin.text.c */
/* loaded from: classes2.dex */
public final class C5246c {

    /* renamed from: a */
    public static final C5246c f17845a = new C5246c();

    /* renamed from: b */
    public static final Charset f17846b;

    /* renamed from: c */
    public static final Charset f17847c;

    /* renamed from: d */
    public static final Charset f17848d;

    /* renamed from: e */
    public static final Charset f17849e;

    /* renamed from: f */
    public static final Charset f17850f;

    /* renamed from: g */
    public static final Charset f17851g;

    /* renamed from: h */
    public static volatile Charset f17852h;

    /* renamed from: i */
    public static volatile Charset f17853i;

    static {
        Charset charsetForName = Charset.forName("UTF-8");
        C0042f.m157d(charsetForName, "forName(...)");
        f17846b = charsetForName;
        Charset charsetForName2 = Charset.forName("UTF-16");
        C0042f.m157d(charsetForName2, "forName(...)");
        f17847c = charsetForName2;
        Charset charsetForName3 = Charset.forName(CharEncoding.UTF_16BE);
        C0042f.m157d(charsetForName3, "forName(...)");
        f17848d = charsetForName3;
        Charset charsetForName4 = Charset.forName(CharEncoding.UTF_16LE);
        C0042f.m157d(charsetForName4, "forName(...)");
        f17849e = charsetForName4;
        Charset charsetForName5 = Charset.forName("US-ASCII");
        C0042f.m157d(charsetForName5, "forName(...)");
        f17850f = charsetForName5;
        Charset charsetForName6 = Charset.forName(CharEncoding.ISO_8859_1);
        C0042f.m157d(charsetForName6, "forName(...)");
        f17851g = charsetForName6;
    }

    /* renamed from: a */
    public final Charset m20498a() {
        Charset charset = f17853i;
        if (charset != null) {
            return charset;
        }
        Charset charsetForName = Charset.forName("UTF-32BE");
        C0042f.m157d(charsetForName, "forName(...)");
        f17853i = charsetForName;
        return charsetForName;
    }

    /* renamed from: b */
    public final Charset m20499b() {
        Charset charset = f17852h;
        if (charset != null) {
            return charset;
        }
        Charset charsetForName = Charset.forName("UTF-32LE");
        C0042f.m157d(charsetForName, "forName(...)");
        f17852h = charsetForName;
        return charsetForName;
    }
}
