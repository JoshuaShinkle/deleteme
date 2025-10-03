package p123l0;

import android.text.TextUtils;
import p021c0.C0697c;

/* renamed from: l0.i */
/* loaded from: classes.dex */
public class C5270i implements InterfaceC5267f {

    /* renamed from: a */
    public String f17872a;

    /* renamed from: b */
    public int f17873b;

    /* renamed from: c */
    public int f17874c;

    public C5270i(String str, int i9, int i10) {
        this.f17872a = str;
        this.f17873b = i9;
        this.f17874c = i10;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C5270i)) {
            return false;
        }
        C5270i c5270i = (C5270i) obj;
        return TextUtils.equals(this.f17872a, c5270i.f17872a) && this.f17873b == c5270i.f17873b && this.f17874c == c5270i.f17874c;
    }

    public int hashCode() {
        return C0697c.m3462b(this.f17872a, Integer.valueOf(this.f17873b), Integer.valueOf(this.f17874c));
    }
}
