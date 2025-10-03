package p116k4;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.CorpAccount;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.utility.CLUtility;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import p201t3.C6301o;
import p209u2.ThreadFactoryC6373j;

/* renamed from: k4.h */
/* loaded from: classes.dex */
public class C5148h {

    /* renamed from: d */
    public static ThreadPoolExecutor f17664d = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactoryC6373j("Corporate.SingleThread"));

    /* renamed from: a */
    public List<CorpAccount> f17665a;

    /* renamed from: c */
    public AtomicBoolean f17667c = new AtomicBoolean(false);

    /* renamed from: b */
    public List<b> f17666b = new ArrayList();

    /* renamed from: k4.h$a */
    public class a extends AsyncTask<Void, Void, Void> {
        public a() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            C5148h.this.m20058f(C5148h.this.m20061i());
            return null;
        }
    }

    /* renamed from: k4.h$b */
    public interface b {
        /* renamed from: a */
        void mo8784a(boolean z8, List<CorpAccount> list);
    }

    /* renamed from: k4.h$c */
    public static class c {

        /* renamed from: a */
        public static final C5148h f17669a = new C5148h();
    }

    /* renamed from: e */
    public static C5148h m20055e() {
        return c.f17669a;
    }

    /* renamed from: c */
    public void m20056c(b bVar) {
        synchronized (C5192y.m20281f()) {
            if (bVar != null) {
                this.f17666b.add(bVar);
            }
        }
    }

    /* renamed from: d */
    public void m20057d(List<CorpAccount> list) {
        if (list == null) {
            return;
        }
        this.f17665a = list;
    }

    /* renamed from: f */
    public final void m20058f(boolean z8) {
        synchronized (C5192y.m20281f()) {
            Iterator<b> it = this.f17666b.iterator();
            while (it.hasNext()) {
                it.next().mo8784a(z8, this.f17665a);
            }
        }
    }

    /* renamed from: g */
    public final List<CorpAccount> m20059g(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return null;
        }
        for (int i9 = 0; i9 < jSONArray.length(); i9++) {
            try {
                arrayList.add(new CorpAccount(jSONArray.getJSONObject(i9)));
            } catch (JSONException e9) {
                Log.e("CorporateAccountHelper", "[parseCorpAccountList] 'CorpAccountJObj' parse error. " + e9.getMessage());
            }
        }
        return arrayList;
    }

    /* renamed from: h */
    public void m20060h(boolean z8) {
        if (z8) {
            m20058f(m20061i());
        } else {
            if (this.f17667c.get()) {
                return;
            }
            new a().executeOnExecutor(f17664d, new Void[0]);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0132  */
    /* renamed from: i */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m20061i() {
        boolean z8;
        String str;
        String str2;
        String str3 = "pageIndex";
        if (this.f17667c.get()) {
            return false;
        }
        this.f17667c.set(true);
        ArrayList arrayList = new ArrayList();
        FriendsClient friendsClient = new FriendsClient(1);
        JSONArray jSONArray = new JSONArray();
        try {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList2.add(new C6301o("pageIndex", String.valueOf(1)));
            arrayList2.add(new C6301o("pageSize", String.valueOf(100)));
            Pair<String, String> pairM15731j = friendsClient.m15731j("user", "listCorporateAccount", arrayList2);
            str = (String) pairM15731j.first;
            str2 = (String) pairM15731j.second;
        } catch (Exception e9) {
            Log.e("CorporateAccountHelper", "[QueryCorpAccountList] " + e9.getMessage());
        }
        if (str != null && str.equals("200")) {
            int iM16553k1 = CLUtility.m16553k1(str2);
            int iM16494U0 = CLUtility.m16494U0(str2);
            if (iM16553k1 >= 0 && iM16494U0 >= 0) {
                JSONArray jSONArrayM20196r = C5172p.m20196r(str2);
                if (jSONArrayM20196r != null) {
                    jSONArray.put(jSONArrayM20196r);
                    List<CorpAccount> listM20059g = m20059g(jSONArrayM20196r);
                    if (listM20059g != null) {
                        arrayList.addAll(listM20059g);
                    }
                }
                if (iM16553k1 != iM16494U0) {
                    int iM16559m = CLUtility.m16559m(iM16553k1, 100);
                    int i9 = 2;
                    while (i9 <= iM16559m) {
                        ArrayList arrayList3 = new ArrayList();
                        arrayList3.add(new C6301o("token", Globals.m7388i0().m7449L()));
                        arrayList3.add(new C6301o(str3, String.valueOf(i9)));
                        String str4 = str3;
                        arrayList3.add(new C6301o("pageSize", String.valueOf(100)));
                        Pair<String, String> pairM15731j2 = friendsClient.m15731j("user", "listCorporateAccount", arrayList3);
                        String str5 = (String) pairM15731j2.first;
                        String str6 = (String) pairM15731j2.second;
                        if (str5 != null && str5.equals("200")) {
                            if (CLUtility.m16494U0(str6) >= 0) {
                                JSONArray jSONArrayM20196r2 = C5172p.m20196r(str6);
                                if (jSONArrayM20196r2 != null) {
                                    jSONArray.put(jSONArrayM20196r2);
                                    List<CorpAccount> listM20059g2 = m20059g(jSONArrayM20196r2);
                                    if (listM20059g2 != null) {
                                        arrayList.addAll(listM20059g2);
                                    }
                                }
                            }
                        }
                        i9++;
                        str3 = str4;
                    }
                }
                z8 = true;
            }
            if (z8) {
                m20057d(arrayList);
            }
            this.f17667c.set(false);
            return z8;
        }
        Log.e("CorporateAccountHelper", "[QueryCorpAccountList] statusCode=" + str);
        z8 = false;
        if (z8) {
        }
        this.f17667c.set(false);
        return z8;
    }
}
