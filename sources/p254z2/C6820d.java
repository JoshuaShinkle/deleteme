package p254z2;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.utility.CLUtility;

/* renamed from: z2.d */
/* loaded from: classes.dex */
public class C6820d {

    /* renamed from: a */
    public int f22639a;

    /* renamed from: b */
    public int f22640b;

    /* renamed from: c */
    public int f22641c;

    /* renamed from: d */
    public int f22642d;

    /* renamed from: e */
    public int f22643e;

    /* renamed from: f */
    public int f22644f;

    /* renamed from: g */
    public int f22645g;

    /* renamed from: h */
    public int f22646h;

    /* renamed from: i */
    public int f22647i;

    /* renamed from: j */
    public int f22648j;

    /* renamed from: k */
    public int f22649k;

    /* renamed from: l */
    public int f22650l;

    /* renamed from: m */
    public int f22651m;

    /* renamed from: n */
    public int f22652n;

    /* renamed from: o */
    public final float f22653o = 0.88f;

    /* renamed from: p */
    public final int f22654p;

    /* renamed from: q */
    public final int f22655q;

    /* renamed from: r */
    public final int f22656r;

    /* renamed from: s */
    public final int f22657s;

    /* renamed from: t */
    public Context f22658t;

    public C6820d(Context context) {
        this.f22658t = context;
        m25413e();
        m25415g();
        m25409a();
        m25414f();
        m25410b();
        m25411c();
        m25412d();
        this.f22654p = m25418j(R.dimen.chat_item_check_box_margin_left) + m25418j(R.dimen.chat_item_check_box_margin_right) + m25418j(R.dimen.checkbox_size_small);
        this.f22655q = m25418j(R.dimen.t70dp);
        this.f22656r = m25418j(R.dimen.t30dp);
        this.f22657s = m25418j(R.dimen.t10dp);
    }

    /* renamed from: a */
    public final void m25409a() {
        this.f22648j = (int) (Globals.m7388i0().getResources().getDisplayMetrics().widthPixels * 0.95f);
    }

    /* renamed from: b */
    public final void m25410b() {
        DisplayMetrics displayMetrics = Globals.m7388i0().getResources().getDisplayMetrics();
        int iRound = Math.round(TypedValue.applyDimension(1, 46.0f, displayMetrics));
        this.f22644f = ((int) (displayMetrics.widthPixels * 0.88f)) - iRound;
        this.f22645g = ((int) ((r0 - m25418j(R.dimen.recv_avatar_image_width)) * 0.88f)) - iRound;
    }

    /* renamed from: c */
    public final void m25411c() {
        DisplayMetrics displayMetrics = Globals.m7388i0().getResources().getDisplayMetrics();
        this.f22642d = ((int) (displayMetrics.widthPixels * 0.88f)) - Math.round(TypedValue.applyDimension(1, 125.0f, displayMetrics));
        this.f22643e = ((int) ((displayMetrics.widthPixels - m25418j(R.dimen.recv_avatar_image_width)) * 0.88f)) - Math.round(TypedValue.applyDimension(1, 155.0f, displayMetrics));
    }

    /* renamed from: d */
    public final void m25412d() {
        this.f22640b = Math.round(Globals.m7388i0().getResources().getDisplayMetrics().widthPixels * 0.5f);
    }

    /* renamed from: e */
    public final void m25413e() {
        int iM16583s = CLUtility.m16583s(78.0f);
        int i9 = Globals.m7388i0().getResources().getDisplayMetrics().widthPixels;
        this.f22649k = (int) ((i9 - iM16583s) * 0.88f);
        this.f22650l = (int) (((i9 - m25418j(R.dimen.recv_avatar_image_width)) - iM16583s) * 0.88f);
    }

    /* renamed from: f */
    public final void m25414f() {
        DisplayMetrics displayMetrics = Globals.m7388i0().getResources().getDisplayMetrics();
        int iRound = Math.round(TypedValue.applyDimension(1, 25.0f, displayMetrics));
        int i9 = displayMetrics.widthPixels;
        this.f22639a = (int) ((i9 - iRound) * 0.88f);
        this.f22641c = (int) (((i9 - m25418j(R.dimen.recv_avatar_image_width)) - iRound) * 0.88f);
        int i10 = displayMetrics.widthPixels;
        this.f22651m = (int) (((i10 - iRound) - iRound) * 0.88f);
        this.f22652n = (int) (((i10 - m25418j(R.dimen.recv_avatar_image_width)) - (iRound * 1.5d)) * 0.8799999952316284d);
    }

    /* renamed from: g */
    public final void m25415g() {
        DisplayMetrics displayMetrics = Globals.m7388i0().getResources().getDisplayMetrics();
        int iRound = Math.round(TypedValue.applyDimension(1, 110.0f, displayMetrics));
        this.f22646h = ((int) (displayMetrics.widthPixels * 0.88f)) - iRound;
        this.f22647i = ((int) ((r0 - m25418j(R.dimen.recv_avatar_image_width)) * 0.88f)) - iRound;
    }

    /* renamed from: h */
    public int m25416h() {
        return this.f22655q;
    }

    /* renamed from: i */
    public final int m25417i() {
        return this.f22654p;
    }

    /* renamed from: j */
    public final int m25418j(int i9) {
        return this.f22658t.getResources().getDimensionPixelSize(i9);
    }

    /* renamed from: k */
    public int m25419k() {
        return this.f22656r;
    }

    /* renamed from: l */
    public int m25420l() {
        return this.f22657s;
    }

    /* renamed from: m */
    public int m25421m(int i9) {
        switch (i9) {
            case 0:
            case 13:
                return this.f22639a;
            case 1:
            case 2:
            case 3:
            case 4:
            case 9:
            case 11:
            case 12:
            case 15:
            case 19:
            case 22:
            case 23:
            case 24:
            case 25:
            case 30:
            case 32:
            case 33:
            case 40:
            case 44:
            default:
                return 0;
            case 5:
            case 7:
                return this.f22642d;
            case 6:
                return this.f22644f;
            case 8:
            case 14:
            case 29:
            case 34:
            case 36:
            case 38:
            case 39:
                return this.f22640b;
            case 10:
                return this.f22646h;
            case 16:
            case 17:
                return this.f22649k;
            case 18:
            case 20:
                return this.f22651m;
            case 21:
                return this.f22641c;
            case 26:
            case 28:
                return this.f22643e;
            case 27:
            case 37:
                return this.f22645g;
            case 31:
                return this.f22647i;
            case 35:
                return this.f22648j;
            case 41:
            case 42:
            case 45:
                return this.f22650l;
            case 43:
            case 46:
                return this.f22652n;
        }
    }

    /* renamed from: n */
    public int m25422n(int i9, boolean z8) {
        int iM25421m = m25421m(i9);
        if (iM25421m == 0) {
            return 0;
        }
        return z8 ? iM25421m - m25417i() : iM25421m;
    }
}
