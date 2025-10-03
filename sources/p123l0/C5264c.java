package p123l0;

import androidx.media.AudioAttributesCompat;
import java.util.Arrays;

/* renamed from: l0.c */
/* loaded from: classes.dex */
public class C5264c implements InterfaceC5262a {

    /* renamed from: a */
    public int f17866a = 0;

    /* renamed from: b */
    public int f17867b = 0;

    /* renamed from: c */
    public int f17868c = 0;

    /* renamed from: d */
    public int f17869d = -1;

    /* renamed from: a */
    public int m20527a() {
        return this.f17867b;
    }

    /* renamed from: b */
    public int m20528b() {
        int i9 = this.f17868c;
        int iM20529c = m20529c();
        if (iM20529c == 6) {
            i9 |= 4;
        } else if (iM20529c == 7) {
            i9 |= 1;
        }
        return i9 & 273;
    }

    /* renamed from: c */
    public int m20529c() {
        int i9 = this.f17869d;
        return i9 != -1 ? i9 : AudioAttributesCompat.m2135a(false, this.f17868c, this.f17866a);
    }

    /* renamed from: d */
    public int m20530d() {
        return this.f17866a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C5264c)) {
            return false;
        }
        C5264c c5264c = (C5264c) obj;
        return this.f17867b == c5264c.m20527a() && this.f17868c == c5264c.m20528b() && this.f17866a == c5264c.m20530d() && this.f17869d == c5264c.f17869d;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f17867b), Integer.valueOf(this.f17868c), Integer.valueOf(this.f17866a), Integer.valueOf(this.f17869d)});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.f17869d != -1) {
            sb.append(" stream=");
            sb.append(this.f17869d);
            sb.append(" derived");
        }
        sb.append(" usage=");
        sb.append(AudioAttributesCompat.m2136b(this.f17866a));
        sb.append(" content=");
        sb.append(this.f17867b);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.f17868c).toUpperCase());
        return sb.toString();
    }
}
