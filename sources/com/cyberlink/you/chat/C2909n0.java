package com.cyberlink.you.chat;

import android.util.Log;
import android.util.Pair;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.friends.FriendsClient;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import p116k4.C5172p;
import p201t3.C6301o;

/* renamed from: com.cyberlink.you.chat.n0 */
/* loaded from: classes.dex */
public class C2909n0 {

    /* renamed from: a */
    public c f12781a;

    /* renamed from: b */
    public Object f12782b = new Object();

    /* renamed from: c */
    public List<b> f12783c = new ArrayList();

    /* renamed from: com.cyberlink.you.chat.n0$a */
    public static class a {

        /* renamed from: a */
        public static final C2909n0 f12784a = new C2909n0();
    }

    /* renamed from: com.cyberlink.you.chat.n0$b */
    public interface b {
        /* renamed from: a */
        boolean mo11653a();
    }

    /* renamed from: com.cyberlink.you.chat.n0$c */
    public class c {

        /* renamed from: a */
        public FriendsClient f12785a = new FriendsClient();

        /* renamed from: com.cyberlink.you.chat.n0$c$a */
        public class a extends Thread {

            /* renamed from: b */
            public final /* synthetic */ C2973l0 f12787b;

            public a(C2973l0 c2973l0) {
                this.f12787b = c2973l0;
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Thread.currentThread().setName("updateDB");
                if (!c.this.m14538b(this.f12787b)) {
                    Log.d("UpdateDBManager", "does not need to notify observer");
                } else {
                    Log.d("UpdateDBManager", "notify observer");
                    C2909n0.this.m14533c();
                }
            }
        }

        public c() {
        }

        /* renamed from: b */
        public final boolean m14538b(C2973l0 c2973l0) {
            C2973l0 c2973l0M20188j;
            Log.d("UpdateDBManager", "into action");
            Long lValueOf = Long.valueOf(c2973l0.m15144p());
            String strM15131c = c2973l0.m15131c();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("mediaId", String.valueOf(lValueOf)));
            Pair<String, String> pairM15731j = this.f12785a.m15731j("media", "mediaInfo", arrayList);
            String str = (String) pairM15731j.first;
            if (str == null || !str.equals("200")) {
                Log.d("UpdateDBManager", "statusCode = " + str);
                Log.d("UpdateDBManager", "leave action");
                return false;
            }
            JSONArray jSONArrayM20196r = C5172p.m20196r((String) pairM15731j.second);
            if (jSONArrayM20196r != null) {
                for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
                    try {
                        c2973l0M20188j = C5172p.m20188j(strM15131c, jSONArrayM20196r.getJSONObject(i9));
                    } catch (JSONException e9) {
                        e9.printStackTrace();
                    }
                    if (c2973l0M20188j != null && C2950b0.m14914m().m14712i(c2973l0M20188j)) {
                        Log.d("UpdateDBManager", "leave action");
                        return true;
                    }
                }
            }
            Log.d("UpdateDBManager", "leave action");
            return false;
        }

        /* renamed from: c */
        public void m14539c(C2973l0 c2973l0) {
            Log.d("UpdateDBManager", "into updateDB");
            new a(c2973l0).start();
        }
    }

    public C2909n0() {
        Log.d("UpdateDBManager", "new UpdateDBManager");
        this.f12781a = new c();
    }

    /* renamed from: b */
    public static C2909n0 m14531b() {
        Log.d("UpdateDBManager", "get UpdateDB Instance");
        return a.f12784a;
    }

    /* renamed from: a */
    public void m14532a(b bVar) {
        synchronized (this.f12782b) {
            if (bVar != null) {
                this.f12783c.add(bVar);
            }
        }
    }

    /* renamed from: c */
    public boolean m14533c() {
        boolean zMo11653a;
        synchronized (this.f12782b) {
            zMo11653a = false;
            for (b bVar : this.f12783c) {
                if (bVar != null) {
                    zMo11653a = bVar.mo11653a();
                }
            }
        }
        return zMo11653a;
    }

    /* renamed from: d */
    public void m14534d(b bVar) {
        synchronized (this.f12782b) {
            if (bVar != null) {
                this.f12783c.remove(bVar);
            }
        }
    }

    /* renamed from: e */
    public void m14535e(C2973l0 c2973l0) {
        if (c2973l0 == null) {
            return;
        }
        this.f12781a.m14539c(c2973l0);
    }
}
