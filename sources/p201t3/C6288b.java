package p201t3;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.friends.SuggestionFriend;
import com.cyberlink.you.friends.UserInfo;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import p015b4.C0671a;
import p015b4.C0683m;
import p015b4.C0684n;
import p086h4.C5017u;
import p116k4.C5172p;
import p209u2.C6385v;

/* renamed from: t3.b */
/* loaded from: classes.dex */
public class C6288b<T> {

    /* renamed from: l */
    public static final String f21206l = "b";

    /* renamed from: a */
    public h f21207a;

    /* renamed from: b */
    public i f21208b;

    /* renamed from: c */
    public d f21209c;

    /* renamed from: d */
    public AsyncTask<String, Object, T> f21210d;

    /* renamed from: e */
    public String f21211e;

    /* renamed from: f */
    public String f21212f;

    /* renamed from: g */
    public List<C6301o> f21213g;

    /* renamed from: h */
    public T f21214h;

    /* renamed from: i */
    public boolean f21215i;

    /* renamed from: j */
    public boolean f21216j;

    /* renamed from: k */
    public Class<T> f21217k;

    /* renamed from: t3.b$a */
    public class a extends AsyncTask<String, Object, T> {
        public a() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public T doInBackground(String... strArr) {
            return (T) C6288b.this.m24093u(strArr);
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(T t8) {
            super.onPostExecute(t8);
            C6288b.this.f21214h = t8;
            if (t8 != null) {
                C6288b.this.f21209c.onComplete(t8);
            }
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
            C6288b.this.f21208b.mo17085a();
        }
    }

    /* renamed from: t3.b$b */
    public class b implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ String f21219b;

        public b(String str) {
            this.f21219b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            C6288b.this.f21207a.onError(this.f21219b);
        }
    }

    /* renamed from: t3.b$c */
    public static class c<T> {

        /* renamed from: a */
        public C6288b<T> f21221a;

        public c(String str, String str2, List<C6301o> list, Class<T> cls) {
            C6288b<T> c6288b = new C6288b<>(null);
            this.f21221a = c6288b;
            c6288b.f21211e = str;
            this.f21221a.f21212f = str2;
            this.f21221a.f21213g = list;
            this.f21221a.f21217k = cls;
        }

        /* renamed from: a */
        public C6288b<T> m24096a() {
            return this.f21221a;
        }

        /* renamed from: b */
        public c<T> m24097b(d dVar) {
            if (dVar != null) {
                this.f21221a.f21209c = dVar;
            }
            return this;
        }

        /* renamed from: c */
        public c<T> m24098c() {
            this.f21221a.f21215i = true;
            return this;
        }

        /* renamed from: d */
        public c<T> m24099d(h hVar) {
            if (hVar != null) {
                this.f21221a.f21207a = hVar;
            }
            return this;
        }

        /* renamed from: e */
        public c<T> m24100e() {
            this.f21221a.f21216j = true;
            return this;
        }

        /* renamed from: f */
        public c<T> m24101f(i iVar) {
            if (iVar != null) {
                this.f21221a.f21208b = iVar;
            }
            return this;
        }
    }

    /* renamed from: t3.b$d */
    public interface d<T> {
        void onComplete(T t8);
    }

    /* renamed from: t3.b$e */
    public class e implements d<Object> {
        public e() {
        }

        @Override // p201t3.C6288b.d
        public void onComplete(Object obj) {
        }

        public /* synthetic */ e(C6288b c6288b, a aVar) {
            this();
        }
    }

    /* renamed from: t3.b$f */
    public class f implements h {
        public f() {
        }

        @Override // p201t3.C6288b.h
        public void onError(String str) {
        }

        public /* synthetic */ f(C6288b c6288b, a aVar) {
            this();
        }
    }

    /* renamed from: t3.b$g */
    public class g implements i {
        public g() {
        }

        @Override // p201t3.C6288b.i
        /* renamed from: a */
        public void mo17085a() {
        }

        public /* synthetic */ g(C6288b c6288b, a aVar) {
            this();
        }
    }

    /* renamed from: t3.b$h */
    public interface h {
        void onError(String str);
    }

    /* renamed from: t3.b$i */
    public interface i {
        /* renamed from: a */
        void mo17085a();
    }

    public /* synthetic */ C6288b(a aVar) {
        this();
    }

    /* renamed from: n */
    public void m24086n() {
        this.f21210d.cancel(false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: o */
    public final void m24087o(T t8) {
        if (this.f21217k.isAssignableFrom(StickerPackObj.class) && this.f21216j) {
            C2950b0.m14925x().m15289g((List) t8);
            return;
        }
        if (this.f21217k.isAssignableFrom(Group.class)) {
            C2950b0.m14912k().m15069f((Group) t8);
        } else if (this.f21217k.isAssignableFrom(C2973l0.class) && this.f21216j) {
            C2950b0.m14914m().m14713j((List) t8);
        }
    }

    /* renamed from: p */
    public void m24088p() {
        a aVar = new a();
        this.f21210d = aVar;
        aVar.executeOnExecutor(C6385v.f21554b, this.f21211e, this.f21212f);
    }

    /* renamed from: q */
    public final String m24089q(String str) {
        for (C6301o c6301o : this.f21213g) {
            if (c6301o.m24110a().equals(str)) {
                return c6301o.m24111b();
            }
        }
        return "";
    }

    /* renamed from: r */
    public final String m24090r(String str) {
        try {
            return new JSONObject(str).getString("errorMessage");
        } catch (JSONException e9) {
            Log.e(f21206l, "wrong json response:" + str, e9);
            return null;
        }
    }

    /* renamed from: s */
    public final T m24091s(String str) {
        if (this.f21217k.isAssignableFrom(SuggestionFriend.class)) {
            return (T) C5172p.m20202x(str);
        }
        if (this.f21217k.isAssignableFrom(StickerPackObj.class)) {
            return (T) C5172p.m20177D(C5172p.m20196r(str), false, true);
        }
        if (this.f21217k.isAssignableFrom(C5017u.class)) {
            return (T) C5017u.m19531a(str);
        }
        if (!this.f21217k.isAssignableFrom(C2973l0.class)) {
            return (T) Collections.EMPTY_LIST;
        }
        return (T) C5172p.m20190l(m24089q("albumId"), C5172p.m20196r(str), false);
    }

    /* renamed from: t */
    public final T m24092t(String str) {
        return this.f21216j ? m24091s(str) : this.f21217k.isAssignableFrom(UserInfo.class) ? (T) C5172p.m20197s(C5172p.m20195q(str)) : this.f21217k.isAssignableFrom(Friend.class) ? (T) C5172p.m20184f(C5172p.m20195q(str)) : this.f21217k.isAssignableFrom(C0683m.class) ? (T) C0683m.m3409c(str) : this.f21217k.isAssignableFrom(C0671a.class) ? (T) C0671a.m3377a(str) : this.f21217k.isAssignableFrom(C0684n.class) ? (T) C0684n.m3415a(str) : this.f21217k.isAssignableFrom(Group.class) ? (T) C5172p.m20186h(C5172p.m20195q(str)) : "";
    }

    /* renamed from: u */
    public T m24093u(String... strArr) {
        String str = strArr[0];
        String str2 = strArr[1];
        Pair<String, String> pairM15676n = FriendsClient.m15676n(str, str2, this.f21213g);
        String str3 = (String) pairM15676n.first;
        String str4 = (String) pairM15676n.second;
        String str5 = f21206l;
        Log.d(str5, "response:" + str4);
        if (str3 == null) {
            m24094v("Server connection fail or table=" + str + " field=" + str2 + " command does not exists");
            return null;
        }
        if (!"200".equals(str3)) {
            String strM24090r = m24090r(str4);
            if (strM24090r != null) {
                m24094v(strM24090r);
            }
            return null;
        }
        T tM24092t = m24092t(str4);
        Log.d(str5, "parseResult:" + tM24092t);
        if (this.f21215i && tM24092t != null) {
            m24087o(tM24092t);
        }
        return tM24092t;
    }

    /* renamed from: v */
    public final void m24094v(String str) {
        C6385v.m24527e(new b(str));
    }

    public C6288b() {
        a aVar = null;
        this.f21207a = new f(this, aVar);
        this.f21208b = new g(this, aVar);
        this.f21209c = new e(this, aVar);
        this.f21215i = false;
        this.f21216j = false;
    }
}
