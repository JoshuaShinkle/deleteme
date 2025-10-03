package p116k4;

import android.os.AsyncTask;
import android.util.Log;
import com.cyberlink.you.Globals;
import com.cyberlink.you.utility.CLUtility;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5194z;
import p209u2.ThreadFactoryC6373j;

/* renamed from: k4.y */
/* loaded from: classes.dex */
public class C5192y {

    /* renamed from: c */
    public static ThreadPoolExecutor f17774c = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactoryC6373j("Notice.SingleThread"));

    /* renamed from: b */
    public AtomicBoolean f17776b = new AtomicBoolean(false);

    /* renamed from: a */
    public List<b> f17775a = new ArrayList();

    /* renamed from: k4.y$a */
    public class a extends AsyncTask<Void, Void, Void> {
        public a() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) throws JSONException {
            C5192y.this.m20285g(C5192y.this.m20288j());
            return null;
        }
    }

    /* renamed from: k4.y$b */
    public interface b {
        /* renamed from: a */
        void mo8768a(boolean z8);
    }

    /* renamed from: k4.y$c */
    public static class c {

        /* renamed from: a */
        public static final C5192y f17778a = new C5192y();
    }

    /* renamed from: f */
    public static C5192y m20281f() {
        return c.f17778a;
    }

    /* renamed from: c */
    public void m20282c(b bVar) {
        synchronized (m20281f()) {
            if (bVar != null) {
                this.f17775a.add(bVar);
            }
        }
    }

    /* renamed from: d */
    public final void m20283d(List<C5194z.b> list) throws JSONException {
        if (list == null || list.size() == 0) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        for (int i9 = 0; i9 < list.size(); i9++) {
            try {
                String str = "Notice_" + i9;
                C5194z.b bVar = list.get(i9);
                if (bVar != null) {
                    jSONObject.put(str, bVar.m20312g());
                }
            } catch (JSONException e9) {
                e9.printStackTrace();
                return;
            }
        }
        Globals.m7388i0().m7566k(jSONObject.toString());
    }

    /* renamed from: e */
    public boolean m20284e() {
        boolean z8;
        long jM7592p0 = Globals.m7388i0().m7592p0();
        long time = new Date().getTime();
        if (jM7592p0 <= 0 || time - jM7592p0 >= 28800000) {
            Globals.m7388i0().m7575l3(time);
            z8 = true;
        } else {
            z8 = false;
        }
        Log.d("NoticeHelper", "[checkLastNoticeCheckDateExpire] bExpired = " + z8);
        return z8;
    }

    /* renamed from: g */
    public final void m20285g(boolean z8) {
        synchronized (m20281f()) {
            Iterator<b> it = this.f17775a.iterator();
            while (it.hasNext()) {
                it.next().mo8768a(z8);
            }
        }
    }

    /* renamed from: h */
    public void m20286h(b bVar) {
        synchronized (m20281f()) {
            if (bVar != null) {
                this.f17775a.remove(bVar);
            }
        }
    }

    /* renamed from: i */
    public void m20287i() {
        if (this.f17776b.get()) {
            return;
        }
        new a().executeOnExecutor(f17774c, new Void[0]);
    }

    /* renamed from: j */
    public final boolean m20288j() throws JSONException {
        if (this.f17776b.get()) {
            return false;
        }
        Log.d("NoticeHelper", "updateNoticeListTask: start");
        boolean z8 = true;
        this.f17776b.set(true);
        if (C5194z.m20299i()) {
            List<C5194z.b> arrayList = new ArrayList<>();
            try {
                String strM20293c = C5194z.m20293c(1);
                if (strM20293c != null && !strM20293c.isEmpty()) {
                    JSONObject jSONObject = new JSONObject(strM20293c);
                    String string = jSONObject.getString("status");
                    if (string == null) {
                        Log.d("NoticeHelper", "[getNotice] statusString == null");
                    } else {
                        String upperCase = string.toUpperCase(Locale.US);
                        if (upperCase.equals("OK")) {
                            List<C5194z.b> listM20301k = C5194z.m20301k(jSONObject.getJSONArray("notices"));
                            if (listM20301k != null) {
                                arrayList.addAll(listM20301k);
                                int iM20303m = C5194z.m20303m(strM20293c);
                                int size = listM20301k.size();
                                int iM16559m = CLUtility.m16559m(iM20303m, 50);
                                if (iM20303m != -1 && size != -1) {
                                    if (iM20303m != size) {
                                        int i9 = 2;
                                        while (true) {
                                            if (i9 > iM16559m) {
                                                break;
                                            }
                                            String strM20293c2 = C5194z.m20293c(arrayList.size() + 1);
                                            if (strM20293c2 != null && !strM20293c2.isEmpty()) {
                                                JSONObject jSONObject2 = new JSONObject(strM20293c2);
                                                String string2 = jSONObject2.getString("status");
                                                if (string2 == null) {
                                                    Log.d("NoticeHelper", "[getNotice] statusString == null");
                                                    break;
                                                }
                                                String upperCase2 = string2.toUpperCase(Locale.US);
                                                if (!upperCase2.equals("OK")) {
                                                    Log.d("NoticeHelper", "[getNotice] statusString: " + upperCase2);
                                                    break;
                                                }
                                                List<C5194z.b> listM20301k2 = C5194z.m20301k(jSONObject2.getJSONArray("notices"));
                                                if (listM20301k2 != null) {
                                                    arrayList.addAll(listM20301k2);
                                                    listM20301k2.clear();
                                                }
                                            }
                                            i9++;
                                        }
                                    }
                                    m20283d(arrayList);
                                }
                            }
                        } else {
                            Log.d("NoticeHelper", "[getNotice] statusString: " + upperCase);
                        }
                    }
                }
            } catch (Exception e9) {
                Log.d("NoticeHelper", "[getNotice] " + e9.getMessage());
            }
            this.f17776b.set(false);
            Log.d("NoticeHelper", "updateNoticeListTask: end");
            return z8;
        }
        Log.d("NoticeHelper", "Service Domain is null");
        z8 = false;
        this.f17776b.set(false);
        Log.d("NoticeHelper", "updateNoticeListTask: end");
        return z8;
    }
}
