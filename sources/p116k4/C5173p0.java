package p116k4;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.SuggestionFriend;
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
import org.json.JSONObject;
import p201t3.C6301o;
import p209u2.ThreadFactoryC6373j;

/* renamed from: k4.p0 */
/* loaded from: classes.dex */
public class C5173p0 {

    /* renamed from: d */
    public static final ThreadPoolExecutor f17726d = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactoryC6373j("Suggestion.SingleThread"));

    /* renamed from: a */
    public long f17727a = -1;

    /* renamed from: c */
    public AtomicBoolean f17729c = new AtomicBoolean(false);

    /* renamed from: b */
    public final List<b> f17728b = new ArrayList();

    /* renamed from: k4.p0$a */
    public class a extends AsyncTask<Void, Void, Void> {
        public a() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            C5173p0.this.m20212h(C5173p0.this.m20216l());
            return null;
        }
    }

    /* renamed from: k4.p0$b */
    public interface b {
        /* renamed from: a */
        void mo3298a(boolean z8);
    }

    /* renamed from: k4.p0$c */
    public static class c {

        /* renamed from: a */
        public static final C5173p0 f17731a = new C5173p0();
    }

    /* renamed from: e */
    public static C5173p0 m20207e() {
        return c.f17731a;
    }

    /* renamed from: c */
    public void m20208c(b bVar) {
        synchronized (C5192y.m20281f()) {
            if (bVar != null) {
                this.f17728b.add(bVar);
            }
        }
    }

    /* renamed from: d */
    public void m20209d(List<SuggestionFriend> list) {
        if (list == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        for (int i9 = 0; i9 < list.size(); i9++) {
            try {
                String str = "SuggestionFriend_" + i9;
                SuggestionFriend suggestionFriend = list.get(i9);
                if (suggestionFriend != null) {
                    jSONObject.put(str, suggestionFriend.m15767a());
                }
            } catch (JSONException e9) {
                e9.printStackTrace();
                return;
            }
        }
        Globals.m7388i0().m7577m(jSONObject.toString());
    }

    /* renamed from: f */
    public long m20210f() {
        return this.f17727a;
    }

    /* renamed from: g */
    public ArrayList<SuggestionFriend> m20211g() {
        Log.d("SuggestionFriendHelper", "[getSuggestionListFromCache] start");
        ArrayList<SuggestionFriend> arrayList = new ArrayList<>();
        String strM7611s2 = Globals.m7388i0().m7611s2();
        if (strM7611s2.equals("")) {
            return arrayList;
        }
        try {
            JSONObject jSONObject = new JSONObject(strM7611s2);
            int length = jSONObject.length();
            for (int i9 = 0; i9 < length; i9++) {
                arrayList.add(new SuggestionFriend(new JSONObject(jSONObject.getString("SuggestionFriend_" + i9))));
            }
        } catch (JSONException e9) {
            e9.printStackTrace();
            arrayList.clear();
        }
        return arrayList;
    }

    /* renamed from: h */
    public final void m20212h(boolean z8) {
        synchronized (C5192y.m20281f()) {
            Iterator<b> it = this.f17728b.iterator();
            while (it.hasNext()) {
                it.next().mo3298a(z8);
            }
        }
    }

    /* renamed from: i */
    public final List<SuggestionFriend> m20213i(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return null;
        }
        for (int i9 = 0; i9 < jSONArray.length(); i9++) {
            try {
                SuggestionFriend suggestionFriendM20203y = C5172p.m20203y(jSONArray.getJSONObject(i9));
                if (suggestionFriendM20203y != null) {
                    long j9 = suggestionFriendM20203y.f13765i;
                    long j10 = this.f17727a;
                    if (j9 <= j10) {
                        j9 = j10;
                    }
                    this.f17727a = j9;
                    arrayList.add(suggestionFriendM20203y);
                }
            } catch (JSONException e9) {
                Log.e("SuggestionFriendHelper", "[parseSuggestionList] 'SuggestionJObj' parse error. " + e9.getMessage());
            }
        }
        return arrayList;
    }

    /* renamed from: j */
    public void m20214j(b bVar) {
        synchronized (C5192y.m20281f()) {
            if (bVar != null) {
                this.f17728b.remove(bVar);
            }
        }
    }

    /* renamed from: k */
    public void m20215k(boolean z8) {
        if (z8) {
            m20212h(m20216l());
        } else {
            if (this.f17729c.get()) {
                return;
            }
            new a().executeOnExecutor(f17726d, new Void[0]);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0171  */
    /* renamed from: l */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m20216l() {
        String str;
        boolean z8;
        String str2;
        String str3;
        String str4;
        String str5 = "pageIndex";
        String str6 = "None";
        if (this.f17729c.get()) {
            return false;
        }
        this.f17729c.set(true);
        ArrayList arrayList = new ArrayList();
        FriendsClient friendsClient = new FriendsClient(1);
        JSONArray jSONArray = new JSONArray();
        try {
            ArrayList arrayList2 = new ArrayList();
            try {
                arrayList2.add(new C6301o("token", Globals.m7388i0().m7449L()));
                arrayList2.add(new C6301o("inviteStatus", "None"));
                arrayList2.add(new C6301o("inviteStatus", "Cancel"));
                str2 = "Cancel";
                arrayList2.add(new C6301o("pageIndex", String.valueOf(1)));
                arrayList2.add(new C6301o("pageSize", String.valueOf(100)));
                Pair<String, String> pairM15731j = friendsClient.m15731j("friend", "listSuggestions", arrayList2);
                str3 = (String) pairM15731j.first;
                str4 = (String) pairM15731j.second;
            } catch (Exception e9) {
                e = e9;
                str = "SuggestionFriendHelper";
            }
        } catch (Exception e10) {
            e = e10;
            str = "SuggestionFriendHelper";
        }
        if ("200".equals(str3)) {
            int iM16553k1 = CLUtility.m16553k1(str4);
            int iM16494U0 = CLUtility.m16494U0(str4);
            if (iM16553k1 >= 0 && iM16494U0 >= 0) {
                JSONArray jSONArrayM20196r = C5172p.m20196r(str4);
                if (jSONArrayM20196r != null) {
                    jSONArray.put(jSONArrayM20196r);
                    List<SuggestionFriend> listM20213i = m20213i(jSONArrayM20196r);
                    if (listM20213i != null) {
                        arrayList.addAll(listM20213i);
                    }
                }
                if (iM16553k1 != iM16494U0) {
                    int iM16559m = CLUtility.m16559m(iM16553k1, 100);
                    int i9 = 2;
                    while (i9 <= iM16559m) {
                        ArrayList arrayList3 = new ArrayList();
                        int i10 = iM16559m;
                        arrayList3.add(new C6301o("token", Globals.m7388i0().m7449L()));
                        arrayList3.add(new C6301o("inviteStatus", str6));
                        String str7 = str2;
                        arrayList3.add(new C6301o("inviteStatus", str7));
                        String str8 = str6;
                        arrayList3.add(new C6301o(str5, String.valueOf(i9)));
                        String str9 = str5;
                        arrayList3.add(new C6301o("pageSize", String.valueOf(100)));
                        Pair<String, String> pairM15731j2 = friendsClient.m15731j("friend", "listSuggestions", arrayList3);
                        String str10 = (String) pairM15731j2.first;
                        String str11 = (String) pairM15731j2.second;
                        if ("200".equals(str10)) {
                            if (CLUtility.m16494U0(str11) >= 0) {
                                JSONArray jSONArrayM20196r2 = C5172p.m20196r(str11);
                                if (jSONArrayM20196r2 != null) {
                                    jSONArray.put(jSONArrayM20196r2);
                                    List<SuggestionFriend> listM20213i2 = m20213i(jSONArrayM20196r2);
                                    if (listM20213i2 != null) {
                                        arrayList.addAll(listM20213i2);
                                    }
                                }
                            }
                        }
                        i9++;
                        str5 = str9;
                        iM16559m = i10;
                        str6 = str8;
                        str2 = str7;
                    }
                }
                z8 = true;
            }
            if (z8) {
                m20209d(arrayList);
            }
            this.f17729c.set(false);
            return z8;
        }
        str = "SuggestionFriendHelper";
        try {
            Log.e(str, "[QuerySuggestionFriendList] statusCode=" + str3);
        } catch (Exception e11) {
            e = e11;
            Log.e(str, "[QuerySuggestionFriendList] " + e.getMessage());
            z8 = false;
            if (z8) {
            }
            this.f17729c.set(false);
            return z8;
        }
        z8 = false;
        if (z8) {
        }
        this.f17729c.set(false);
        return z8;
    }
}
