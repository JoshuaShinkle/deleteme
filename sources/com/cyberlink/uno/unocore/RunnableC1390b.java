package com.cyberlink.uno.unocore;

import android.util.Log;
import com.cyberlink.uno.log.UNOFileLog;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.cyberlink.uno.unocore.b */
/* loaded from: classes.dex */
public class RunnableC1390b implements Runnable {

    /* renamed from: b */
    public final C1397i f7182b;

    /* renamed from: c */
    public final C1398j f7183c;

    public RunnableC1390b(C1398j c1398j, C1397i c1397i) {
        this.f7183c = c1398j;
        this.f7182b = c1397i;
    }

    /* renamed from: a */
    public final String m7232a(String str) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray jSONArray = jSONObject.getJSONArray("event");
            for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                ((JSONObject) jSONArray.get(i9)).remove(TtmlNode.ATTR_ID);
            }
            return jSONObject.toString();
        } catch (JSONException e9) {
            UNOFileLog.m7215l(UNOFileLog.LogType.EXCEPTIONEVENT, "Get exception convert string to json object", e9);
            return str;
        }
    }

    @Override // java.lang.Runnable
    public void run() throws JSONException {
        while (true) {
            String[] strArrM7292e = this.f7182b.m7292e();
            if (strArrM7292e.length == 0) {
                return;
            }
            if (!this.f7183c.m7304c(m7232a(strArrM7292e[0]))) {
                UNOFileLog.m7214k(UNOFileLog.LogType.FAILUREEVENT, strArrM7292e[0]);
                return;
            }
            Log.d("UNOCoreProcessor", "ok ->" + strArrM7292e[0]);
            UNOFileLog.m7214k(UNOFileLog.LogType.SUCCESSEVENT, strArrM7292e[0]);
            this.f7182b.m7298m(strArrM7292e[0]);
        }
    }
}
