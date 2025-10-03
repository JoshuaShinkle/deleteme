package p173q2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.CorpAccount;
import com.cyberlink.you.friends.DepartmentFriend;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.friends.InvitationFriend;
import com.cyberlink.you.friends.SuggestionFriend;
import com.cyberlink.you.friends.UserInfo;
import p012b1.AbstractC0588c;
import p190s1.InterfaceC6249f;
import p199t1.InterfaceC6283h;
import p209u2.AbstractC6381r;
import p209u2.C6383t;
import p209u2.C6385v;

/* renamed from: q2.a */
/* loaded from: classes.dex */
public final class C6127a {

    /* renamed from: q2.a$a */
    public class a extends AsyncTask<Void, Void, Boolean> {

        /* renamed from: a */
        public final /* synthetic */ Group f20752a;

        /* renamed from: b */
        public final /* synthetic */ Context f20753b;

        /* renamed from: c */
        public final /* synthetic */ ImageView f20754c;

        /* renamed from: d */
        public final /* synthetic */ String f20755d;

        public a(Group group, Context context, ImageView imageView, String str) {
            this.f20752a = group;
            this.f20753b = context;
            this.f20754c = imageView;
            this.f20755d = str;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) {
            return Boolean.valueOf(this.f20752a.m15751i());
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            if (bool.booleanValue()) {
                C6127a.m23474o(this.f20753b, this.f20754c, this.f20755d, R.drawable.pic_default_todo);
            }
        }
    }

    /* renamed from: q2.a$b */
    public class b implements InterfaceC6249f<Bitmap> {

        /* renamed from: b */
        public final /* synthetic */ AbstractC6381r f20756b;

        public b(AbstractC6381r abstractC6381r) {
            this.f20756b = abstractC6381r;
        }

        @Override // p190s1.InterfaceC6249f
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public boolean mo7171d(Bitmap bitmap, Object obj, InterfaceC6283h<Bitmap> interfaceC6283h, DataSource dataSource, boolean z8) {
            AbstractC6381r abstractC6381r = this.f20756b;
            if (abstractC6381r == null) {
                return false;
            }
            abstractC6381r.m24505c();
            return false;
        }

        @Override // p190s1.InterfaceC6249f
        /* renamed from: c */
        public boolean mo7170c(GlideException glideException, Object obj, InterfaceC6283h<Bitmap> interfaceC6283h, boolean z8) {
            AbstractC6381r abstractC6381r = this.f20756b;
            if (abstractC6381r == null) {
                return false;
            }
            abstractC6381r.m24507e();
            return false;
        }
    }

    /* renamed from: q2.a$c */
    public class c implements InterfaceC6249f<Bitmap> {

        /* renamed from: b */
        public final /* synthetic */ AbstractC6381r f20757b;

        public c(AbstractC6381r abstractC6381r) {
            this.f20757b = abstractC6381r;
        }

        @Override // p190s1.InterfaceC6249f
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public boolean mo7171d(Bitmap bitmap, Object obj, InterfaceC6283h<Bitmap> interfaceC6283h, DataSource dataSource, boolean z8) {
            AbstractC6381r abstractC6381r = this.f20757b;
            if (abstractC6381r == null) {
                return false;
            }
            abstractC6381r.m24505c();
            return false;
        }

        @Override // p190s1.InterfaceC6249f
        /* renamed from: c */
        public boolean mo7170c(GlideException glideException, Object obj, InterfaceC6283h<Bitmap> interfaceC6283h, boolean z8) {
            AbstractC6381r abstractC6381r = this.f20757b;
            if (abstractC6381r == null) {
                return false;
            }
            abstractC6381r.m24507e();
            return false;
        }
    }

    /* renamed from: a */
    public static Bitmap m23460a(Context context, String str, int i9, int i10) {
        return m23461b(context, str, i9, i10, i10);
    }

    @SuppressLint({"CheckResult"})
    /* renamed from: b */
    public static Bitmap m23461b(Context context, String str, int i9, int i10, int i11) {
        if (context == null || C6143q.m23608a(context)) {
            return null;
        }
        if (C6383t.m24517f(str) || "null".equalsIgnoreCase(str)) {
            return C6136j.m23583c(context, i9, i10, i11);
        }
        C6134h<Bitmap> c6134hM23546E = C6132f.m23492b(context).mo23575d().mo23570t(str).m23544C().m23558Q().m23546E(DownsampleStrategy.f3882e);
        if (i10 != -1 && i11 != -1) {
            c6134hM23546E.m23560S(i10, i11);
        }
        try {
            return c6134hM23546E.m24428w().get();
        } catch (Exception unused) {
            return C6136j.m23581a(context, i9);
        }
    }

    /* renamed from: c */
    public static Bitmap m23462c(Context context, String str, int i9) {
        return m23461b(context, str, i9, (int) Globals.m7374X0().getDimension(android.R.dimen.notification_large_icon_width), (int) Globals.m7374X0().getDimension(android.R.dimen.notification_large_icon_height));
    }

    /* renamed from: d */
    public static Bitmap m23463d(Context context, Group group) {
        return m23460a(context, group == null ? null : group.f13724k, (group == null || group.m15750g()) ? R.drawable.pic_default : "Corporate".equals(group.f13738y) ? R.drawable.pic_default_official : group.m15751i() ? R.drawable.pic_default_todo : R.drawable.pic_default_group, Math.max((int) Globals.m7374X0().getDimension(android.R.dimen.app_icon_size), 256));
    }

    /* renamed from: e */
    public static void m23464e(Context context, ImageView imageView, int i9) {
        m23474o(context, imageView, "", i9);
    }

    /* renamed from: f */
    public static void m23465f(Context context, ImageView imageView, Uri uri, int i9) {
        m23466g(context, imageView, uri, i9, null);
    }

    @SuppressLint({"CheckResult"})
    /* renamed from: g */
    public static void m23466g(Context context, ImageView imageView, Uri uri, int i9, AbstractC6381r<Void, Void> abstractC6381r) {
        if (context == null || imageView == null || C6143q.m23608a(context)) {
            return;
        }
        C6134h<Bitmap> c6134hM23558Q = C6132f.m23492b(context).mo23575d().m23545D(AbstractC0588c.f3107a).m23550I(DecodeFormat.PREFER_RGB_565).m23558Q();
        if (uri != null) {
            c6134hM23558Q.m23553L(uri).m23561T(i9);
        } else {
            c6134hM23558Q.mo23568r(Integer.valueOf(i9));
        }
        c6134hM23558Q.m23552K(new c(abstractC6381r)).m24422n(imageView);
    }

    /* renamed from: h */
    public static void m23467h(Context context, ImageView imageView, CorpAccount corpAccount) {
        m23474o(context, imageView, corpAccount == null ? null : corpAccount.f13617f, R.drawable.pic_default_official);
    }

    /* renamed from: i */
    public static void m23468i(Context context, ImageView imageView, DepartmentFriend departmentFriend) {
        m23474o(context, imageView, departmentFriend == null ? null : departmentFriend.f13631g, R.drawable.pic_default);
    }

    /* renamed from: j */
    public static void m23469j(Context context, ImageView imageView, Friend friend) {
        m23474o(context, imageView, friend == null ? null : friend.f13647e, (friend == null || !"Corporate".equals(friend.m15623d())) ? R.drawable.pic_default : R.drawable.pic_default_official);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* renamed from: k */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m23470k(Context context, ImageView imageView, Group group) {
        int i9;
        boolean z8;
        if (group == null || group.m15750g()) {
            i9 = R.drawable.pic_default;
        } else if ("Corporate".equals(group.f13738y)) {
            i9 = R.drawable.pic_default_official;
        } else {
            if (Globals.m7388i0().m7543f0() != group.f13727n) {
                i9 = R.drawable.pic_default_group;
                z8 = true;
                String str = group != null ? null : group.f13724k;
                m23474o(context, imageView, str, i9);
                if (z8) {
                    return;
                }
                new a(group, context, imageView, str).executeOnExecutor(C6385v.f21553a, new Void[0]);
                return;
            }
            i9 = R.drawable.pic_default_todo;
        }
        z8 = false;
        if (group != null) {
        }
        m23474o(context, imageView, str, i9);
        if (z8) {
        }
    }

    /* renamed from: l */
    public static void m23471l(Context context, ImageView imageView, InvitationFriend invitationFriend) {
        m23474o(context, imageView, invitationFriend == null ? null : invitationFriend.f13743d, R.drawable.pic_default);
    }

    /* renamed from: m */
    public static void m23472m(Context context, ImageView imageView, SuggestionFriend suggestionFriend) {
        m23474o(context, imageView, suggestionFriend == null ? null : suggestionFriend.f13760d, R.drawable.pic_default);
    }

    /* renamed from: n */
    public static void m23473n(Context context, ImageView imageView, UserInfo userInfo) {
        m23474o(context, imageView, userInfo == null ? null : userInfo.f13779d, R.drawable.pic_default);
    }

    /* renamed from: o */
    public static void m23474o(Context context, ImageView imageView, String str, int i9) {
        m23475p(context, imageView, str, i9, null);
    }

    @SuppressLint({"CheckResult"})
    /* renamed from: p */
    public static void m23475p(Context context, ImageView imageView, String str, int i9, AbstractC6381r<Void, Void> abstractC6381r) {
        if (context == null || imageView == null || C6143q.m23608a(context)) {
            return;
        }
        boolean z8 = (C6383t.m24517f(str) || "null".equalsIgnoreCase(str)) ? false : true;
        C6134h<Bitmap> c6134hM23558Q = C6132f.m23492b(context).mo23575d().m23545D(AbstractC0588c.f3107a).m23550I(DecodeFormat.PREFER_RGB_565).m23558Q();
        if (z8) {
            c6134hM23558Q.mo23570t(str).m23561T(i9);
        } else {
            c6134hM23558Q.mo23568r(Integer.valueOf(i9));
        }
        c6134hM23558Q.m23552K(new b(abstractC6381r)).m24422n(imageView);
    }
}
