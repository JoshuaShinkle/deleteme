package com.cyberlink.you.bulletin;

import android.util.Log;
import com.cyberlink.you.bulletin.TopicCommentObj;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.sticker.StickerObj;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Comparator;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5172p;

/* renamed from: com.cyberlink.you.bulletin.a */
/* loaded from: classes.dex */
public class C2833a {

    /* renamed from: a */
    public String f12408a;

    /* renamed from: b */
    public long f12409b;

    /* renamed from: c */
    public Object f12410c;

    /* renamed from: d */
    public long f12411d;

    /* renamed from: e */
    public long f12412e;

    /* renamed from: f */
    public boolean f12413f;

    /* renamed from: com.cyberlink.you.bulletin.a$a */
    public static class a implements Comparator<C2833a> {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(C2833a c2833a, C2833a c2833a2) {
            long j9 = c2833a.f12411d;
            long j10 = c2833a2.f12411d;
            if (j9 > j10) {
                return 1;
            }
            return j9 < j10 ? -1 : 0;
        }
    }

    public C2833a(TopicCommentObj.FromSource fromSource, JSONObject jSONObject, long j9) {
        this.f12413f = true;
        this.f12412e = j9;
        if (fromSource.equals(TopicCommentObj.FromSource.Server)) {
            m14049b(jSONObject);
        } else if (fromSource.equals(TopicCommentObj.FromSource.Database)) {
            m14048a(jSONObject);
        } else {
            this.f12413f = false;
        }
    }

    /* renamed from: a */
    public final void m14048a(JSONObject jSONObject) {
        try {
            this.f12408a = jSONObject.getString("type");
            this.f12411d = jSONObject.getLong("lastModified") * 1000;
            this.f12409b = jSONObject.getLong("value");
            if (this.f12408a.equals("Photo") || this.f12408a.equals("Video") || this.f12408a.equals("Audio")) {
                this.f12410c = C2950b0.m14914m().m14725v(this.f12409b);
            } else if (this.f12408a.equals("Sticker")) {
                this.f12410c = C2950b0.m14924w().m15278f(this.f12409b);
            }
        } catch (JSONException unused) {
            this.f12413f = false;
            Log.e("TopicCommentComponent", "[parseFromDB] JSONstr=" + jSONObject.toString());
        }
    }

    /* renamed from: b */
    public final void m14049b(JSONObject jSONObject) {
        try {
            this.f12408a = jSONObject.getString("type");
            this.f12411d = jSONObject.has("lastModified") ? jSONObject.getLong("lastModified") * 1000 : 0L;
            if (!this.f12408a.equals("Photo") && !this.f12408a.equals("Video") && !this.f12408a.equals("Audio")) {
                if (this.f12408a.equals("Sticker")) {
                    StickerObj stickerObjM20199u = C5172p.m20199u(jSONObject.getJSONObject(FirebaseAnalytics.Param.CONTENT));
                    this.f12410c = stickerObjM20199u;
                    if (stickerObjM20199u != null) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(stickerObjM20199u);
                        C2950b0.m14924w().m15277e(arrayList);
                        this.f12409b = stickerObjM20199u.m16285j();
                        return;
                    }
                    return;
                }
                return;
            }
            C2973l0 c2973l0M20189k = C5172p.m20189k("TopicPostId:" + String.valueOf(this.f12412e), jSONObject.getJSONObject(FirebaseAnalytics.Param.CONTENT), true);
            this.f12410c = c2973l0M20189k;
            if (c2973l0M20189k != null) {
                C2950b0.m14914m().m14712i(c2973l0M20189k);
                this.f12409b = c2973l0M20189k.m15144p();
                this.f12411d = c2973l0M20189k.m15143o() == null ? this.f12411d : c2973l0M20189k.m15143o().getTime();
            }
        } catch (JSONException unused) {
            this.f12413f = false;
            Log.e("TopicCommentComponent", "[parseFromServer] JSONstr=" + jSONObject.toString());
        }
    }

    /* renamed from: c */
    public JSONObject m14050c() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.f12408a);
            jSONObject.put("value", this.f12409b);
            jSONObject.put("lastModified", this.f12411d);
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
        return jSONObject;
    }
}
