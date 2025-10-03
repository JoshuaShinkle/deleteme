package p116k4;

import android.app.Activity;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.Pair;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C3001z0;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.gms.plus.PlusShare;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p201t3.C6301o;

/* renamed from: k4.m0 */
/* loaded from: classes.dex */
public class C5164m0 {

    /* renamed from: e */
    public static final List<Long> f17692e;

    /* renamed from: a */
    public final Object f17693a = new Object();

    /* renamed from: b */
    public boolean f17694b = false;

    /* renamed from: d */
    public final BlockingQueue<d> f17696d = new LinkedBlockingQueue();

    /* renamed from: c */
    public List<c> f17695c = new ArrayList();

    /* renamed from: k4.m0$a */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() throws JSONException {
            Thread.currentThread().setName("Update Sticker Shop List");
            while (!C5164m0.this.f17694b) {
                C5164m0.this.m20118k();
            }
        }
    }

    /* renamed from: k4.m0$b */
    public static class b {

        /* renamed from: a */
        public static final C5164m0 f17698a = new C5164m0();
    }

    /* renamed from: k4.m0$c */
    public interface c {
        /* renamed from: a */
        void mo10150a(String str, boolean z8);
    }

    /* renamed from: k4.m0$d */
    public static class d {

        /* renamed from: a */
        public String f17699a;

        /* renamed from: b */
        public Activity f17700b;

        public d(String str, Activity activity) {
            this.f17699a = str;
            this.f17700b = activity;
        }
    }

    static {
        if (Globals.m7380c2()) {
            f17692e = new ArrayList(Arrays.asList(504L, 503L, 203L, 229L, 481L));
        } else {
            f17692e = new ArrayList(Arrays.asList(367L, 366L, 203L, 229L, 344L));
        }
    }

    public C5164m0() {
        new Thread(new a()).start();
    }

    /* renamed from: l */
    public static long m20107l() {
        return Globals.m7380c2() ? 504L : 367L;
    }

    /* renamed from: m */
    public static C5164m0 m20108m() {
        return b.f17698a;
    }

    /* renamed from: q */
    public static List<StickerObj> m20109q(String str, StickerPackObj stickerPackObj) throws Throwable {
        int i9;
        int iIntValue;
        try {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("results");
                ArrayList arrayList = new ArrayList();
                for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                    try {
                        JSONObject jSONObject = jSONArray.getJSONObject(i10);
                        try {
                            long j9 = jSONObject.getLong("stickerId");
                            long j10 = jSONObject.getLong("stickerOrder");
                            long j11 = jSONObject.getLong("lastModified");
                            String string = jSONObject.getString("originalURL");
                            String string2 = jSONObject.getString("thumbnailURL");
                            StringBuilder sb = new StringBuilder();
                            sb.append(CLUtility.m16541h1(stickerPackObj.m14803g()));
                            String str2 = File.separator;
                            sb.append(str2);
                            String string3 = sb.toString();
                            String str3 = string3 + Long.toString(j9);
                            String str4 = str3 + "_thumbnail";
                            if (stickerPackObj.m14805i().equals("AnimationPNG")) {
                                File file = new File(str3);
                                File file2 = new File(str3 + ".tmp");
                                if (file2.exists()) {
                                    CLUtility.m16447I(file2);
                                }
                                file.renameTo(file2);
                                CLUtility.m16474O2(str3 + ".tmp", string3);
                                if (file2.exists()) {
                                    file2.delete();
                                }
                                if (!CLUtility.m16613z1(str3 + str2 + "info.json", null)) {
                                    return null;
                                }
                            } else {
                                if (!CLUtility.m16613z1(str3, null)) {
                                    return null;
                                }
                                if (!CLUtility.m16613z1(str4, null)) {
                                    return null;
                                }
                            }
                            Pair<Integer, Integer> pairM16545i1 = CLUtility.m16545i1(str3);
                            if (pairM16545i1 != null) {
                                int iIntValue2 = ((Integer) pairM16545i1.first).intValue();
                                iIntValue = ((Integer) pairM16545i1.second).intValue();
                                i9 = iIntValue2;
                            } else {
                                i9 = 0;
                                iIntValue = 0;
                            }
                            arrayList.add(new StickerObj(-1L, j9, stickerPackObj.m14803g(), j10, j11, string, str3, string2, str4, i9, iIntValue));
                        } catch (JSONException unused) {
                            Log.e("StickerHelper", "[sticker.sticker.list] Parse item error. JSONstr=" + jSONObject.toString());
                        }
                    } catch (JSONException unused2) {
                        Log.e("StickerHelper", "[sticker.sticker.list] groupInfo parse error. JSONstr=" + str);
                    }
                }
                return arrayList;
            } catch (JSONException unused3) {
                Log.e("StickerHelper", "[sticker.sticker.list] 'results' missing. JSONstr=" + str);
                return null;
            }
        } catch (JSONException unused4) {
            Log.e("StickerHelper", "[sticker.sticker.list] Parse error. JSONstr=" + str);
            return null;
        }
    }

    /* renamed from: a */
    public void m20110a(d dVar) {
        Iterator<d> it = this.f17696d.iterator();
        while (it.hasNext()) {
            if (it.next().f17699a.equals(dVar.f17699a)) {
                return;
            }
        }
        this.f17696d.add(dVar);
        synchronized (this.f17693a) {
            this.f17693a.notify();
        }
    }

    /* renamed from: d */
    public void m20111d(c cVar) {
        synchronized (m20108m()) {
            if (cVar != null) {
                this.f17695c.add(cVar);
            }
        }
    }

    /* renamed from: e */
    public final void m20112e(List<StickerPackObj> list, String str) throws JSONException {
        if (list == null || list.size() == 0) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        for (int i9 = 0; i9 < list.size(); i9++) {
            try {
                String str2 = "StickerPack_" + i9;
                StickerPackObj stickerPackObj = list.get(i9);
                if (stickerPackObj != null) {
                    jSONObject.put(str2, stickerPackObj.m14821y());
                }
            } catch (JSONException e9) {
                e9.printStackTrace();
                return;
            }
        }
        Globals.m7388i0().m7572l(str, jSONObject.toString());
        if (str.equals("Top")) {
            Globals.m7388i0().m7624u3(new Date().getTime());
        }
    }

    /* renamed from: f */
    public boolean m20113f() {
        if (C5167n0.m20143a() <= Globals.m7388i0().m7639x0()) {
            Globals.m7388i0().m7531c4(true);
            Globals.m7388i0().m7426F3(false);
            return false;
        }
        Globals.m7388i0().m7531c4(false);
        Globals.m7388i0().m7426F3(true);
        Globals.m7388i0().m7406B3(true);
        Globals.m7388i0().m7651z3(true);
        return true;
    }

    /* renamed from: g */
    public boolean m20114g() {
        boolean z8;
        long jM7633w0 = Globals.m7388i0().m7633w0();
        long time = new Date().getTime();
        if (jM7633w0 <= 0 || time - jM7633w0 >= 28800000) {
            Globals.m7388i0().m7612s3(time);
            z8 = true;
        } else {
            z8 = false;
        }
        Log.d("StickerHelper", "[checkLastStickerCheckDateExpire] bExpired = " + z8);
        return z8;
    }

    /* renamed from: h */
    public boolean m20115h() {
        long jM7645y0 = Globals.m7388i0().m7645y0();
        boolean z8 = jM7645y0 <= 0 || new Date().getTime() - jM7645y0 >= DateUtils.MILLIS_PER_DAY;
        Log.d("StickerHelper", "[checkTopLastStickerCheckDateExpire] bExpired = " + z8);
        return z8;
    }

    /* renamed from: i */
    public void m20116i(StickerObj stickerObj) {
        if (stickerObj != null) {
            if (stickerObj.m16289n() == 0 || stickerObj.m16279d() == 0) {
                try {
                    Pair<Integer, Integer> pairM16545i1 = CLUtility.m16545i1(stickerObj.m16282g());
                    stickerObj.m16295t(((Integer) pairM16545i1.first).intValue());
                    stickerObj.m16292q(((Integer) pairM16545i1.second).intValue());
                    C2950b0.m14924w().m15281i(stickerObj.m16285j(), stickerObj);
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
            }
        }
    }

    /* renamed from: j */
    public void m20117j() {
        Globals.m7388i0().m7632w("Top");
        Globals.m7388i0().m7632w("New");
    }

    /* renamed from: k */
    public final void m20118k() throws JSONException {
        try {
            if (this.f17696d.isEmpty()) {
                synchronized (this.f17693a) {
                    if (this.f17696d.isEmpty()) {
                        this.f17693a.wait();
                    }
                }
            }
            d dVarPeek = this.f17696d.peek();
            if (dVarPeek != null) {
                boolean zM20125u = m20125u(dVarPeek.f17699a, dVarPeek.f17700b);
                this.f17696d.remove();
                m20121p(dVarPeek.f17699a, zM20125u);
            }
        } catch (InterruptedException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: n */
    public SharedPreferences m20119n() {
        return Globals.m7388i0().getSharedPreferences("U", 0);
    }

    /* renamed from: o */
    public List<StickerPackObj> m20120o(String str) {
        ArrayList arrayList = new ArrayList();
        String strM7599q2 = Globals.m7388i0().m7599q2(str);
        if (strM7599q2.equals("")) {
            return arrayList;
        }
        try {
            JSONObject jSONObject = new JSONObject(strM7599q2);
            int length = jSONObject.length();
            for (int i9 = 0; i9 < length; i9++) {
                arrayList.add(new StickerPackObj(new JSONObject(jSONObject.getString("StickerPack_" + i9))));
            }
        } catch (JSONException e9) {
            e9.printStackTrace();
            arrayList.clear();
        }
        return arrayList;
    }

    /* renamed from: p */
    public final void m20121p(String str, boolean z8) {
        Log.d("StickerHelper", "[notifyListener] In : CallbackType = " + str);
        synchronized (m20108m()) {
            Iterator<c> it = this.f17695c.iterator();
            while (it.hasNext()) {
                it.next().mo10150a(str, z8);
            }
        }
    }

    /* renamed from: r */
    public final List<StickerPackObj> m20122r(String str) {
        List<StickerPackObj> listM20177D = C5172p.m20177D(C5172p.m20196r(str), false, false);
        return listM20177D != null ? listM20177D : new ArrayList();
    }

    /* renamed from: s */
    public void m20123s(c cVar) {
        synchronized (m20108m()) {
            if (cVar != null) {
                this.f17695c.remove(cVar);
            }
        }
    }

    /* renamed from: t */
    public final void m20124t() {
        SharedPreferences sharedPreferencesM20119n = m20119n();
        sharedPreferencesM20119n.edit().putString("last_sticker_language", Locale.getDefault().getLanguage()).commit();
    }

    /* renamed from: u */
    public boolean m20125u(String str, Activity activity) throws JSONException {
        boolean z8;
        String str2;
        Log.v("StickerHelper", "updateStickerShopList " + str);
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("pageIndex", String.valueOf(1)));
        arrayList.add(new C6301o("pageSize", String.valueOf(50)));
        str.hashCode();
        String str3 = "pack.newV2";
        if (!str.equals("New") && str.equals("Top")) {
            str3 = "pack.topV2";
        }
        FriendsClient friendsClient = new FriendsClient(true);
        Pair<String, String> pairM15731j = friendsClient.m15731j("sticker", str3, arrayList);
        String str4 = (String) pairM15731j.first;
        String str5 = (String) pairM15731j.second;
        List<StickerPackObj> arrayList2 = new ArrayList<>();
        if ("200".equals(str4)) {
            int iM16553k1 = CLUtility.m16553k1(str5);
            int iM16494U0 = CLUtility.m16494U0(str5);
            String str6 = "StickerHelper";
            int iM16559m = CLUtility.m16559m(iM16553k1, 50);
            if (iM16553k1 == -1 || iM16494U0 == -1) {
                Log.d(str6, "totalSize = " + iM16553k1 + " resultsSize " + iM16494U0);
            } else if (iM16553k1 != iM16494U0) {
                List<StickerPackObj> listM20122r = m20122r(str5);
                arrayList2.addAll(listM20122r);
                listM20122r.clear();
                int i9 = 2;
                while (i9 <= iM16559m) {
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.add(new C6301o("token", strM7449L));
                    arrayList3.add(new C6301o("pageIndex", String.valueOf(i9)));
                    String str7 = strM7449L;
                    arrayList3.add(new C6301o("pageSize", String.valueOf(50)));
                    Pair<String, String> pairM15731j2 = friendsClient.m15731j("sticker", str3, arrayList3);
                    String str8 = (String) pairM15731j2.first;
                    String str9 = (String) pairM15731j2.second;
                    if ("200".equals(str8)) {
                        List<StickerPackObj> listM20122r2 = m20122r(str9);
                        arrayList2.addAll(listM20122r2);
                        listM20122r2.clear();
                        str2 = str6;
                    } else {
                        str2 = str6;
                        Log.d(str2, "statusCode = " + str8);
                    }
                    i9++;
                    str6 = str2;
                    strM7449L = str7;
                }
            }
            z8 = true;
        } else {
            Log.d("StickerHelper", "statusCode = " + str4);
            z8 = false;
        }
        friendsClient.m15717U0();
        if (z8) {
            m20112e(arrayList2, str);
        }
        m20124t();
        m20126v(arrayList2);
        return z8;
    }

    /* renamed from: v */
    public final void m20126v(List<StickerPackObj> list) {
        C3001z0 c3001z0M14925x = C2950b0.m14925x();
        for (StickerPackObj stickerPackObj : list) {
            if (c3001z0M14925x.m15297o(stickerPackObj.m14803g())) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("packName", stickerPackObj.m14804h());
                contentValues.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, stickerPackObj.m14797a());
                contentValues.put("lastModified", Long.valueOf(stickerPackObj.m14802f().getTime()));
                contentValues.put("publisherLastModified", Long.valueOf(stickerPackObj.m14807k()));
                contentValues.put("publisherName", stickerPackObj.m14808l());
                c3001z0M14925x.m15302t(contentValues, "PackId=?", new String[]{String.valueOf(stickerPackObj.m14803g())});
            }
        }
    }
}
