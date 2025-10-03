package com.cyberlink.uno.unocore;

import android.util.Log;
import com.cyberlink.uno.log.UNOFileLog;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.AbstractC5524y;
import okhttp3.C5487d;
import okhttp3.C5520u;
import okhttp3.C5521v;
import okhttp3.C5522w;
import okhttp3.C5523x;
import okhttp3.C5525z;
import okhttp3.InterfaceC5488e;
import okhttp3.InterfaceC5489f;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.cyberlink.uno.unocore.j */
/* loaded from: classes.dex */
public class C1398j {

    /* renamed from: c */
    public static C5522w f7218c;

    /* renamed from: a */
    public final String f7221a;

    /* renamed from: b */
    public static final C5520u f7217b = C5520u.m21709f("text/plain; charset=utf-8");

    /* renamed from: d */
    public static final SSLSocketFactory f7219d = null;

    /* renamed from: e */
    public static final HostnameVerifier f7220e = null;

    /* renamed from: com.cyberlink.uno.unocore.j$a */
    public class a implements InterfaceC5489f {

        /* renamed from: b */
        public final /* synthetic */ b f7222b;

        public a(b bVar) {
            this.f7222b = bVar;
        }

        @Override // okhttp3.InterfaceC5489f
        /* renamed from: c */
        public void mo7305c(InterfaceC5488e interfaceC5488e, C5525z c5525z) {
            try {
                String strM21231y = c5525z.m21849f().m21231y();
                Log.d("UNOHttpClient", " getConfig jsonString =" + strM21231y);
                if (!c5525z.m21840D()) {
                    Log.d("UNOHttpClient", "getConfig response failed!!");
                    this.f7222b.onFailure(new JSONException("No json response!!"));
                    return;
                }
                JSONObject jSONObject = new JSONObject(strM21231y);
                C1395g c1395g = new C1395g();
                if (jSONObject.has("First_Interval")) {
                    c1395g.m7282e(jSONObject.getInt("First_Interval"));
                }
                if (jSONObject.has("Interval")) {
                    c1395g.m7283f(jSONObject.getInt("Interval"));
                }
                if (jSONObject.has("Size_Limit")) {
                    c1395g.m7281d(jSONObject.getInt("Size_Limit"));
                }
                this.f7222b.mo7276a(c1395g);
            } catch (IOException e9) {
                UNOFileLog.m7215l(UNOFileLog.LogType.EXCEPTIONEVENT, "Get IOException when get config from server ", e9);
                Log.d("UNOHttpClient", "getConfig IOException ", e9);
                this.f7222b.onFailure(e9);
            } catch (JSONException e10) {
                UNOFileLog.m7215l(UNOFileLog.LogType.EXCEPTIONEVENT, "Get JSONException when get config from server ", e10);
                Log.d("UNOHttpClient", "getConfig JSONException ", e10);
                this.f7222b.onFailure(e10);
            }
        }

        @Override // okhttp3.InterfaceC5489f
        /* renamed from: d */
        public void mo7306d(InterfaceC5488e interfaceC5488e, IOException iOException) {
            this.f7222b.onFailure(iOException);
        }
    }

    /* renamed from: com.cyberlink.uno.unocore.j$b */
    public interface b {
        /* renamed from: a */
        void mo7276a(C1395g c1395g);

        void onFailure(Exception exc);
    }

    public C1398j(String str) {
        this.f7221a = str;
    }

    /* renamed from: b */
    public static synchronized C5522w m7302b() {
        if (f7218c == null) {
            C5522w.a aVar = new C5522w.a();
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            aVar.m21777c(SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS, timeUnit);
            aVar.m21773H(SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS, timeUnit);
            aVar.m21774I(SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS, timeUnit);
            f7218c = aVar.m21775a();
        }
        return f7218c;
    }

    /* renamed from: a */
    public void m7303a(b bVar) {
        m7302b().mo21256a(new C5523x.a().m21820i(this.f7221a + "/dna/getconfig.jsp").m21814c(new C5487d.a().m21251d().m21248a()).m21818g(AbstractC5524y.m21824e(null, new byte[0])).m21813b()).mo21255f(new a(bVar));
    }

    /* renamed from: c */
    public boolean m7304c(String str) throws JSONException {
        boolean z8 = false;
        try {
            C5525z c5525zExecute = m7302b().mo21256a(new C5523x.a().m21820i(this.f7221a + "/dna/sendlog.jsp").m21814c(new C5487d.a().m21251d().m21248a()).m21818g(new C5521v.a().m21724e(C5521v.f18974l).m21721b("FILE", "file.json", AbstractC5524y.m21823d(f7217b, str)).m21720a("format", "json").m21723d()).m21813b()).execute();
            String strM21231y = c5525zExecute.m21849f().m21231y();
            Log.d("UNOHttpClient", " upload jsonString =" + strM21231y);
            if (c5525zExecute.m21840D()) {
                String string = new JSONObject(strM21231y).getString("Result");
                if (string.equals("SUCCESS")) {
                    Log.d("UNOHttpClient", "Upload events succeeded!!");
                    z8 = true;
                } else if (string.equals("FAIL")) {
                    Log.d("UNOHttpClient", "Upload events failed!!");
                } else {
                    Log.d("UNOHttpClient", "Invalid response format failed!!");
                }
            } else {
                Log.d("UNOHttpClient", "Upload response failed!!");
            }
        } catch (IOException e9) {
            UNOFileLog.m7215l(UNOFileLog.LogType.EXCEPTIONEVENT, "Get IOException when upload events ", e9);
            Log.d("UNOHttpClient", "upload IOException ", e9);
        } catch (JSONException e10) {
            UNOFileLog.m7215l(UNOFileLog.LogType.EXCEPTIONEVENT, "Get JSONException when upload events", e10);
            Log.d("UNOHttpClient", "upload JSONException ", e10);
        }
        return z8;
    }
}
