package p188s;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import p179r.C6178a;

/* renamed from: s.g */
/* loaded from: classes.dex */
public class C6232g {

    /* renamed from: s.g$a */
    public static class a {

        /* renamed from: a */
        public final Bundle f20971a;

        /* renamed from: b */
        public IconCompat f20972b;

        /* renamed from: c */
        public final C6240o[] f20973c;

        /* renamed from: d */
        public final C6240o[] f20974d;

        /* renamed from: e */
        public boolean f20975e;

        /* renamed from: f */
        public boolean f20976f;

        /* renamed from: g */
        public final int f20977g;

        /* renamed from: h */
        public final boolean f20978h;

        /* renamed from: i */
        @Deprecated
        public int f20979i;

        /* renamed from: j */
        public CharSequence f20980j;

        /* renamed from: k */
        public PendingIntent f20981k;

        public a(int i9, CharSequence charSequence, PendingIntent pendingIntent) {
            this(i9 != 0 ? IconCompat.m1501a(null, "", i9) : null, charSequence, pendingIntent);
        }

        /* renamed from: a */
        public PendingIntent m23816a() {
            return this.f20981k;
        }

        /* renamed from: b */
        public boolean m23817b() {
            return this.f20975e;
        }

        /* renamed from: c */
        public Bundle m23818c() {
            return this.f20971a;
        }

        /* renamed from: d */
        public IconCompat m23819d() {
            int i9;
            if (this.f20972b == null && (i9 = this.f20979i) != 0) {
                this.f20972b = IconCompat.m1501a(null, "", i9);
            }
            return this.f20972b;
        }

        /* renamed from: e */
        public C6240o[] m23820e() {
            return this.f20973c;
        }

        /* renamed from: f */
        public int m23821f() {
            return this.f20977g;
        }

        /* renamed from: g */
        public boolean m23822g() {
            return this.f20976f;
        }

        /* renamed from: h */
        public CharSequence m23823h() {
            return this.f20980j;
        }

        /* renamed from: i */
        public boolean m23824i() {
            return this.f20978h;
        }

        public a(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent) {
            this(iconCompat, charSequence, pendingIntent, new Bundle(), null, null, true, 0, true, false);
        }

        public a(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, C6240o[] c6240oArr, C6240o[] c6240oArr2, boolean z8, int i9, boolean z9, boolean z10) {
            this.f20976f = true;
            this.f20972b = iconCompat;
            if (iconCompat != null && iconCompat.m1509f() == 2) {
                this.f20979i = iconCompat.m1507b();
            }
            this.f20980j = e.m23831d(charSequence);
            this.f20981k = pendingIntent;
            this.f20971a = bundle == null ? new Bundle() : bundle;
            this.f20973c = c6240oArr;
            this.f20974d = c6240oArr2;
            this.f20975e = z8;
            this.f20977g = i9;
            this.f20976f = z9;
            this.f20978h = z10;
        }
    }

    /* renamed from: s.g$b */
    public static class b extends f {

        /* renamed from: e */
        public Bitmap f20982e;

        /* renamed from: f */
        public Bitmap f20983f;

        /* renamed from: g */
        public boolean f20984g;

        @Override // p188s.C6232g.f
        /* renamed from: b */
        public void mo23825b(InterfaceC6231f interfaceC6231f) {
            Notification.BigPictureStyle bigPictureStyleBigPicture = new Notification.BigPictureStyle(interfaceC6231f.mo23814a()).setBigContentTitle(this.f21030b).bigPicture(this.f20982e);
            if (this.f20984g) {
                bigPictureStyleBigPicture.bigLargeIcon(this.f20983f);
            }
            if (this.f21032d) {
                bigPictureStyleBigPicture.setSummaryText(this.f21031c);
            }
        }

        /* renamed from: g */
        public b m23826g(Bitmap bitmap) {
            this.f20983f = bitmap;
            this.f20984g = true;
            return this;
        }

        /* renamed from: h */
        public b m23827h(Bitmap bitmap) {
            this.f20982e = bitmap;
            return this;
        }
    }

    /* renamed from: s.g$c */
    public static class c extends f {

        /* renamed from: e */
        public CharSequence f20985e;

        @Override // p188s.C6232g.f
        /* renamed from: b */
        public void mo23825b(InterfaceC6231f interfaceC6231f) {
            Notification.BigTextStyle bigTextStyleBigText = new Notification.BigTextStyle(interfaceC6231f.mo23814a()).setBigContentTitle(this.f21030b).bigText(this.f20985e);
            if (this.f21032d) {
                bigTextStyleBigText.setSummaryText(this.f21031c);
            }
        }

        /* renamed from: g */
        public c m23828g(CharSequence charSequence) {
            this.f20985e = e.m23831d(charSequence);
            return this;
        }

        /* renamed from: h */
        public c m23829h(CharSequence charSequence) {
            this.f21030b = e.m23831d(charSequence);
            return this;
        }
    }

    /* renamed from: s.g$d */
    public static final class d {
        /* renamed from: a */
        public static Notification.BubbleMetadata m23830a(d dVar) {
            return null;
        }
    }

    /* renamed from: s.g$f */
    public static abstract class f {

        /* renamed from: a */
        public e f21029a;

        /* renamed from: b */
        public CharSequence f21030b;

        /* renamed from: c */
        public CharSequence f21031c;

        /* renamed from: d */
        public boolean f21032d = false;

        /* renamed from: a */
        public void m23861a(Bundle bundle) {
        }

        /* renamed from: b */
        public abstract void mo23825b(InterfaceC6231f interfaceC6231f);

        /* renamed from: c */
        public RemoteViews m23862c(InterfaceC6231f interfaceC6231f) {
            return null;
        }

        /* renamed from: d */
        public RemoteViews m23863d(InterfaceC6231f interfaceC6231f) {
            return null;
        }

        /* renamed from: e */
        public RemoteViews m23864e(InterfaceC6231f interfaceC6231f) {
            return null;
        }

        /* renamed from: f */
        public void m23865f(e eVar) {
            if (this.f21029a != eVar) {
                this.f21029a = eVar;
                if (eVar != null) {
                    eVar.m23860z(this);
                }
            }
        }
    }

    /* renamed from: a */
    public static Bundle m23815a(Notification notification) {
        return notification.extras;
    }

    /* renamed from: s.g$e */
    public static class e {

        /* renamed from: A */
        public String f20986A;

        /* renamed from: B */
        public Bundle f20987B;

        /* renamed from: C */
        public int f20988C;

        /* renamed from: D */
        public int f20989D;

        /* renamed from: E */
        public Notification f20990E;

        /* renamed from: F */
        public RemoteViews f20991F;

        /* renamed from: G */
        public RemoteViews f20992G;

        /* renamed from: H */
        public RemoteViews f20993H;

        /* renamed from: I */
        public String f20994I;

        /* renamed from: J */
        public int f20995J;

        /* renamed from: K */
        public String f20996K;

        /* renamed from: L */
        public long f20997L;

        /* renamed from: M */
        public int f20998M;

        /* renamed from: N */
        public boolean f20999N;

        /* renamed from: O */
        public Notification f21000O;

        /* renamed from: P */
        public boolean f21001P;

        /* renamed from: Q */
        @Deprecated
        public ArrayList<String> f21002Q;

        /* renamed from: a */
        public Context f21003a;

        /* renamed from: b */
        public ArrayList<a> f21004b;

        /* renamed from: c */
        public ArrayList<a> f21005c;

        /* renamed from: d */
        public CharSequence f21006d;

        /* renamed from: e */
        public CharSequence f21007e;

        /* renamed from: f */
        public PendingIntent f21008f;

        /* renamed from: g */
        public PendingIntent f21009g;

        /* renamed from: h */
        public RemoteViews f21010h;

        /* renamed from: i */
        public Bitmap f21011i;

        /* renamed from: j */
        public CharSequence f21012j;

        /* renamed from: k */
        public int f21013k;

        /* renamed from: l */
        public int f21014l;

        /* renamed from: m */
        public boolean f21015m;

        /* renamed from: n */
        public boolean f21016n;

        /* renamed from: o */
        public f f21017o;

        /* renamed from: p */
        public CharSequence f21018p;

        /* renamed from: q */
        public CharSequence[] f21019q;

        /* renamed from: r */
        public int f21020r;

        /* renamed from: s */
        public int f21021s;

        /* renamed from: t */
        public boolean f21022t;

        /* renamed from: u */
        public String f21023u;

        /* renamed from: v */
        public boolean f21024v;

        /* renamed from: w */
        public String f21025w;

        /* renamed from: x */
        public boolean f21026x;

        /* renamed from: y */
        public boolean f21027y;

        /* renamed from: z */
        public boolean f21028z;

        public e(Context context, String str) {
            this.f21004b = new ArrayList<>();
            this.f21005c = new ArrayList<>();
            this.f21015m = true;
            this.f21026x = false;
            this.f20988C = 0;
            this.f20989D = 0;
            this.f20995J = 0;
            this.f20998M = 0;
            Notification notification = new Notification();
            this.f21000O = notification;
            this.f21003a = context;
            this.f20994I = str;
            notification.when = System.currentTimeMillis();
            this.f21000O.audioStreamType = -1;
            this.f21014l = 0;
            this.f21002Q = new ArrayList<>();
            this.f20999N = true;
        }

        /* renamed from: d */
        public static CharSequence m23831d(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
        }

        /* renamed from: A */
        public e m23832A(CharSequence charSequence) {
            this.f21000O.tickerText = m23831d(charSequence);
            return this;
        }

        /* renamed from: B */
        public e m23833B(long[] jArr) {
            this.f21000O.vibrate = jArr;
            return this;
        }

        /* renamed from: C */
        public e m23834C(int i9) {
            this.f20989D = i9;
            return this;
        }

        /* renamed from: D */
        public e m23835D(long j9) {
            this.f21000O.when = j9;
            return this;
        }

        /* renamed from: a */
        public e m23836a(int i9, CharSequence charSequence, PendingIntent pendingIntent) {
            this.f21004b.add(new a(i9, charSequence, pendingIntent));
            return this;
        }

        /* renamed from: b */
        public Notification m23837b() {
            return new C6237l(this).m23871c();
        }

        /* renamed from: c */
        public Bundle m23838c() {
            if (this.f20987B == null) {
                this.f20987B = new Bundle();
            }
            return this.f20987B;
        }

        /* renamed from: e */
        public final Bitmap m23839e(Bitmap bitmap) throws Resources.NotFoundException {
            if (bitmap == null || Build.VERSION.SDK_INT >= 27) {
                return bitmap;
            }
            Resources resources = this.f21003a.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(C6178a.compat_notification_large_icon_max_width);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(C6178a.compat_notification_large_icon_max_height);
            if (bitmap.getWidth() <= dimensionPixelSize && bitmap.getHeight() <= dimensionPixelSize2) {
                return bitmap;
            }
            double dMin = Math.min(dimensionPixelSize / Math.max(1, bitmap.getWidth()), dimensionPixelSize2 / Math.max(1, bitmap.getHeight()));
            return Bitmap.createScaledBitmap(bitmap, (int) Math.ceil(bitmap.getWidth() * dMin), (int) Math.ceil(bitmap.getHeight() * dMin), true);
        }

        /* renamed from: f */
        public e m23840f(boolean z8) {
            m23849o(16, z8);
            return this;
        }

        /* renamed from: g */
        public e m23841g(String str) {
            this.f20986A = str;
            return this;
        }

        /* renamed from: h */
        public e m23842h(String str) {
            this.f20994I = str;
            return this;
        }

        /* renamed from: i */
        public e m23843i(int i9) {
            this.f20988C = i9;
            return this;
        }

        /* renamed from: j */
        public e m23844j(PendingIntent pendingIntent) {
            this.f21008f = pendingIntent;
            return this;
        }

        /* renamed from: k */
        public e m23845k(CharSequence charSequence) {
            this.f21007e = m23831d(charSequence);
            return this;
        }

        /* renamed from: l */
        public e m23846l(CharSequence charSequence) {
            this.f21006d = m23831d(charSequence);
            return this;
        }

        /* renamed from: m */
        public e m23847m(int i9) {
            Notification notification = this.f21000O;
            notification.defaults = i9;
            if ((i9 & 4) != 0) {
                notification.flags |= 1;
            }
            return this;
        }

        /* renamed from: n */
        public e m23848n(PendingIntent pendingIntent) {
            this.f21000O.deleteIntent = pendingIntent;
            return this;
        }

        /* renamed from: o */
        public final void m23849o(int i9, boolean z8) {
            if (z8) {
                Notification notification = this.f21000O;
                notification.flags = i9 | notification.flags;
            } else {
                Notification notification2 = this.f21000O;
                notification2.flags = (~i9) & notification2.flags;
            }
        }

        /* renamed from: p */
        public e m23850p(PendingIntent pendingIntent, boolean z8) {
            this.f21009g = pendingIntent;
            m23849o(128, z8);
            return this;
        }

        /* renamed from: q */
        public e m23851q(Bitmap bitmap) {
            this.f21011i = m23839e(bitmap);
            return this;
        }

        /* renamed from: r */
        public e m23852r(int i9, int i10, int i11) {
            Notification notification = this.f21000O;
            notification.ledARGB = i9;
            notification.ledOnMS = i10;
            notification.ledOffMS = i11;
            notification.flags = ((i10 == 0 || i11 == 0) ? 0 : 1) | (notification.flags & (-2));
            return this;
        }

        /* renamed from: s */
        public e m23853s(boolean z8) {
            this.f21026x = z8;
            return this;
        }

        /* renamed from: t */
        public e m23854t(int i9) {
            this.f21013k = i9;
            return this;
        }

        /* renamed from: u */
        public e m23855u(boolean z8) {
            m23849o(2, z8);
            return this;
        }

        /* renamed from: v */
        public e m23856v(int i9) {
            this.f21014l = i9;
            return this;
        }

        /* renamed from: w */
        public e m23857w(boolean z8) {
            this.f21015m = z8;
            return this;
        }

        /* renamed from: x */
        public e m23858x(int i9) {
            this.f21000O.icon = i9;
            return this;
        }

        /* renamed from: y */
        public e m23859y(Uri uri) {
            Notification notification = this.f21000O;
            notification.sound = uri;
            notification.audioStreamType = -1;
            notification.audioAttributes = new AudioAttributes.Builder().setContentType(4).setUsage(5).build();
            return this;
        }

        /* renamed from: z */
        public e m23860z(f fVar) {
            if (this.f21017o != fVar) {
                this.f21017o = fVar;
                if (fVar != null) {
                    fVar.m23865f(this);
                }
            }
            return this;
        }

        @Deprecated
        public e(Context context) {
            this(context, null);
        }
    }
}
