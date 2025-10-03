package p003a2;

import android.text.TextUtils;
import com.cyberlink.clrtc.NetQuality;
import com.cyberlink.clrtc.NileNetwork;
import com.cyberlink.clrtc.model.ActiveMedia;

/* renamed from: a2.b */
/* loaded from: classes.dex */
public class C0012b implements Comparable<C0012b> {

    /* renamed from: b */
    public final C0011a f66b;

    /* renamed from: c */
    public boolean f67c;

    /* renamed from: d */
    public boolean f68d;

    /* renamed from: e */
    public ActiveMedia f69e;

    /* renamed from: f */
    public boolean f70f;

    /* renamed from: g */
    public boolean f71g;

    /* renamed from: h */
    public boolean f72h;

    /* renamed from: i */
    public boolean f73i;

    /* renamed from: j */
    public int f74j;

    /* renamed from: k */
    public boolean f75k;

    /* renamed from: l */
    public boolean f76l;

    /* renamed from: m */
    public String f77m;

    /* renamed from: n */
    public boolean f78n;

    /* renamed from: o */
    public NetQuality.Quality f79o;

    /* renamed from: p */
    public NetQuality.Quality f80p;

    /* renamed from: q */
    public NileNetwork.PlatformType f81q;

    /* renamed from: r */
    public NileNetwork.ParticipantState f82r;

    /* renamed from: s */
    public int f83s;

    public C0012b(C0011a c0011a) {
        this.f69e = ActiveMedia.f5373c;
        this.f70f = false;
        this.f71g = false;
        this.f72h = false;
        this.f73i = false;
        this.f74j = -1;
        this.f75k = false;
        this.f76l = false;
        this.f77m = null;
        this.f78n = false;
        NetQuality.Quality quality = NetQuality.Quality.UNAVAILABLE;
        this.f79o = quality;
        this.f80p = quality;
        this.f81q = NileNetwork.PlatformType.Platform_Unknown;
        this.f82r = NileNetwork.ParticipantState.ParticipantState_Unknown;
        this.f83s = 0;
        this.f66b = c0011a;
    }

    /* renamed from: A */
    public boolean m71A(int i9) {
        if (this.f83s == i9) {
            return false;
        }
        this.f83s = i9;
        return true;
    }

    /* renamed from: B */
    public boolean m72B(String str) {
        if (TextUtils.equals(this.f66b.m68c(), str)) {
            return false;
        }
        this.f66b.m70e(str);
        return true;
    }

    /* renamed from: C */
    public void m73C(boolean z8) {
        this.f75k = z8;
    }

    /* renamed from: D */
    public boolean m74D(NileNetwork.PlatformType platformType) {
        if (this.f81q == platformType) {
            return false;
        }
        this.f81q = platformType;
        return true;
    }

    /* renamed from: E */
    public boolean m75E(boolean z8) {
        if (this.f76l == z8) {
            return false;
        }
        this.f76l = z8;
        return true;
    }

    /* renamed from: F */
    public void m76F(int i9) {
        this.f74j = i9;
    }

    /* renamed from: G */
    public void m77G(boolean z8) {
        this.f73i = z8;
    }

    /* renamed from: H */
    public boolean m78H(boolean z8) {
        if (this.f71g == z8) {
            return false;
        }
        this.f71g = z8;
        return true;
    }

    /* renamed from: I */
    public boolean m79I(NileNetwork.ParticipantState participantState) {
        if (this.f82r == participantState) {
            return false;
        }
        this.f82r = participantState;
        return true;
    }

    /* renamed from: J */
    public boolean m80J(NetQuality.Quality quality) {
        if (this.f79o == quality) {
            return false;
        }
        this.f79o = quality;
        return true;
    }

    /* renamed from: K */
    public boolean m81K(boolean z8) {
        if (this.f67c == z8) {
            return false;
        }
        this.f67c = z8;
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0012b c0012b) {
        int iCompare = Boolean.compare(!this.f73i, !c0012b.f73i);
        if (iCompare == 0) {
            iCompare = Integer.compare(this.f74j, c0012b.f74j);
        }
        return iCompare == 0 ? this.f66b.compareTo(c0012b.f66b) : iCompare;
    }

    /* renamed from: b */
    public ActiveMedia m83b() {
        return this.f69e;
    }

    /* renamed from: c */
    public NetQuality.Quality m84c() {
        return this.f80p;
    }

    /* renamed from: d */
    public String m85d() {
        return this.f77m;
    }

    /* renamed from: e */
    public String m86e() {
        return this.f66b.m68c();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C0012b)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        C0012b c0012b = (C0012b) obj;
        if (!this.f66b.equals(c0012b.f66b) || this.f67c != c0012b.f67c || this.f68d != c0012b.f68d || this.f72h != c0012b.f72h) {
            return false;
        }
        String str = this.f77m;
        return TextUtils.equals(str, str) && this.f69e.equals(c0012b.f69e) && this.f70f == c0012b.f70f && this.f83s == c0012b.f83s;
    }

    /* renamed from: f */
    public NileNetwork.PlatformType m87f() {
        return this.f81q;
    }

    /* renamed from: g */
    public NileNetwork.ParticipantState m88g() {
        return this.f82r;
    }

    /* renamed from: h */
    public NetQuality.Quality m89h() {
        return this.f79o;
    }

    /* renamed from: i */
    public boolean m90i() {
        return this.f70f;
    }

    /* renamed from: j */
    public boolean m91j() {
        return this.f69e != ActiveMedia.f5373c;
    }

    /* renamed from: k */
    public boolean m92k() {
        return this.f68d;
    }

    /* renamed from: l */
    public boolean m93l() {
        return this.f78n;
    }

    /* renamed from: m */
    public boolean m94m() {
        return this.f72h;
    }

    /* renamed from: n */
    public boolean m95n() {
        return this.f75k;
    }

    /* renamed from: o */
    public boolean m96o() {
        return this.f76l;
    }

    /* renamed from: p */
    public boolean m97p() {
        return this.f73i;
    }

    /* renamed from: q */
    public boolean m98q() {
        return this.f71g;
    }

    /* renamed from: r */
    public boolean m99r() {
        return this.f67c;
    }

    /* renamed from: s */
    public String m100s() {
        return this.f66b.m69d();
    }

    /* renamed from: t */
    public boolean m101t(ActiveMedia activeMedia) {
        if (this.f69e.equals(activeMedia)) {
            return false;
        }
        this.f69e = activeMedia;
        return true;
    }

    /* renamed from: u */
    public boolean m102u(boolean z8) {
        if (this.f68d == z8 && (!z8 || !this.f70f)) {
            return false;
        }
        this.f68d = z8;
        if (!z8) {
            return true;
        }
        this.f70f = false;
        return true;
    }

    /* renamed from: v */
    public boolean m103v(boolean z8) {
        if (this.f78n == z8) {
            return false;
        }
        this.f78n = z8;
        return true;
    }

    /* renamed from: w */
    public boolean m104w(NetQuality.Quality quality) {
        if (this.f80p == quality) {
            return false;
        }
        this.f80p = quality;
        return true;
    }

    /* renamed from: x */
    public boolean m105x(String str) {
        if (TextUtils.equals(this.f77m, str)) {
            return false;
        }
        this.f77m = str;
        return true;
    }

    /* renamed from: y */
    public boolean m106y(boolean z8) {
        if (this.f72h == z8) {
            return false;
        }
        this.f72h = z8;
        return true;
    }

    /* renamed from: z */
    public boolean m107z(boolean z8) {
        if (this.f70f == z8) {
            return false;
        }
        this.f70f = z8;
        return true;
    }

    public C0012b(int i9, String str, long j9) {
        this.f69e = ActiveMedia.f5373c;
        this.f70f = false;
        this.f71g = false;
        this.f72h = false;
        this.f73i = false;
        this.f74j = -1;
        this.f75k = false;
        this.f76l = false;
        this.f77m = null;
        this.f78n = false;
        NetQuality.Quality quality = NetQuality.Quality.UNAVAILABLE;
        this.f79o = quality;
        this.f80p = quality;
        this.f81q = NileNetwork.PlatformType.Platform_Unknown;
        this.f82r = NileNetwork.ParticipantState.ParticipantState_Unknown;
        this.f83s = 0;
        this.f66b = new C0011a(i9, str, j9);
    }

    public C0012b(int i9, String str, long j9, boolean z8, boolean z9) {
        this(i9, str, j9);
        this.f67c = z8;
        this.f68d = z9;
    }
}
