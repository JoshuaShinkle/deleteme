package p116k4;

import android.util.Log;
import android.util.Pair;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.FriendsClient;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import p201t3.C6301o;

/* renamed from: k4.n0 */
/* loaded from: classes.dex */
public class C5167n0 {
    /* renamed from: a */
    public static long m20143a() throws JSONException {
        JSONObject jSONObject;
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        Pair<String, String> pairM15731j = new FriendsClient().m15731j("sticker", "pack.status", arrayList);
        if (pairM15731j == null) {
            return -1L;
        }
        String str = (String) pairM15731j.first;
        String str2 = (String) pairM15731j.second;
        if (str == null) {
            Log.d("StickerUtility", "[getNewestStickDate] Response is null");
            return 0L;
        }
        if (!str.equals("200")) {
            Log.d("StickerUtility", "[getNewestStickDate] statusCode=" + str);
            return 0L;
        }
        try {
            JSONObject jSONObjectM20195q = C5172p.m20195q(str2);
            if (jSONObjectM20195q == null) {
                return -1L;
            }
            try {
                jSONObject = jSONObjectM20195q.getJSONObject("lastPublished");
            } catch (JSONException unused) {
                Log.e("StickerUtility", "[getNewestStickDate] Get lastPublished failed");
                jSONObject = null;
            }
            if (jSONObject != null) {
                return jSONObject.getLong("all");
            }
            return -1L;
        } catch (JSONException unused2) {
            Log.e("StickerUtility", "[getNewestStickDate] Get result failed");
            return -1L;
        }
    }
}
