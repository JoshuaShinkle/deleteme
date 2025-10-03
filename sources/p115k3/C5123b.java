package p115k3;

import com.cyberlink.you.Globals;
import com.cyberlink.you.bulletin.TopicCommentObj;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.database.TopicObj;
import com.cyberlink.you.utility.UploadMediaHelper;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5154j;
import p209u2.C6385v;

/* renamed from: k3.b */
/* loaded from: classes.dex */
public class C5123b {

    /* renamed from: k3.b$a */
    public class a implements XMPPManager.InterfaceC2873x {

        /* renamed from: a */
        public final /* synthetic */ TopicObj f17604a;

        public a(TopicObj topicObj) {
            this.f17604a = topicObj;
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2873x
        /* renamed from: a */
        public void mo5816a() {
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2873x
        public void onSuccess() {
            C2950b0.m14906e().m14989s(this.f17604a.m14849o(), this.f17604a, "lastReadTime");
        }
    }

    /* renamed from: b */
    public static String m19984b(List<UploadMediaHelper> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (UploadMediaHelper uploadMediaHelper : list) {
            C2973l0 c2973l0M16851j1 = uploadMediaHelper.m16851j1();
            C2973l0 c2973l0M16826X0 = uploadMediaHelper.m16826X0();
            C2973l0 c2973l0M16846h1 = uploadMediaHelper.m16846h1();
            if (c2973l0M16826X0 != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("type", "Photo");
                    jSONObject.put("value", c2973l0M16826X0.m15144p());
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
                jSONArray.put(jSONObject);
            }
            if (c2973l0M16851j1 != null) {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("type", "Audio");
                    jSONObject2.put("value", c2973l0M16851j1.m15144p());
                } catch (JSONException e10) {
                    e10.printStackTrace();
                }
                jSONArray.put(jSONObject2);
            }
            if (c2973l0M16846h1 != null) {
                JSONObject jSONObject3 = new JSONObject();
                try {
                    jSONObject3.put("type", "Video");
                    jSONObject3.put("value", c2973l0M16846h1.m15144p());
                } catch (JSONException e11) {
                    e11.printStackTrace();
                }
                jSONArray.put(jSONObject3);
            }
        }
        return jSONArray.toString();
    }

    /* renamed from: c */
    public static /* synthetic */ void m19985c(TopicObj topicObj) throws JSONException {
        C2950b0.m14905d().m14957t(topicObj.m14849o());
        TopicCommentObj topicCommentObjM14951n = C2950b0.m14905d().m14951n(topicObj.m14849o());
        if (topicCommentObjM14951n == null) {
            if (topicObj.m14845j() < topicObj.m14840e()) {
                m19986d(topicObj, topicObj.m14840e());
            }
        } else if (topicCommentObjM14951n.m14036k() > topicObj.m14845j()) {
            m19986d(topicObj, topicCommentObjM14951n.m14036k());
        }
    }

    /* renamed from: d */
    public static void m19986d(TopicObj topicObj, long j9) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("eventType", "bulletin.topic.lastRead");
            jSONObject.put("groupId", String.valueOf(topicObj.m14843h()));
            jSONObject.put("topicId", String.valueOf(topicObj.m14849o()));
            jSONObject.put("lastRead", String.valueOf(j9));
            topicObj.m14831C(j9);
            XMPPManager.m14184g0().m14241e1(C2925v.m14593f("Dual", Globals.m7388i0().m7587o0(), C2925v.m14558I("-1", MessageObj.MessageType.TOPIC, jSONObject.toString(), 0, null)), new a(topicObj));
        } catch (Exception e9) {
            C5154j.m20076j(e9);
        }
    }

    /* renamed from: e */
    public static void m19987e(final TopicObj topicObj) {
        C6385v.f21553a.execute(new Runnable() { // from class: k3.a
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                C5123b.m19985c(topicObj);
            }
        });
    }
}
