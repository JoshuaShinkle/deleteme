package com.perfectcorp.ycl.pages.live;

import android.util.Log;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.util.concurrent.FutureCallback;
import com.perfectcorp.utility.C4509d;
import com.perfectcorp.ycl.p040bc.model.network.Key;
import com.perfectcorp.ycl.p040bc.model.network.NetworkManager;
import com.perfectcorp.ycl.p040bc.model.network.NetworkMessage;
import com.perfectcorp.ycl.pages.live.C4597q;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import p097i5.C5053b;
import p128l5.C5292d;
import p138m5.C5327b;
import p147n5.C5366d;
import p147n5.C5369g;
import p147n5.C5370h;
import p147n5.C5371i;
import p147n5.InterfaceC5367e;

/* renamed from: com.perfectcorp.ycl.pages.live.x */
/* loaded from: classes2.dex */
public class C4604x {

    /* renamed from: a */
    public C5292d f16194a;

    /* renamed from: b */
    public C4597q f16195b;

    /* renamed from: c */
    public d f16196c;

    /* renamed from: d */
    public C5327b.a<C5371i> f16197d;

    /* renamed from: e */
    public C5327b.a<C5370h> f16198e;

    /* renamed from: f */
    public C5327b.a<C5366d> f16199f;

    /* renamed from: g */
    public String f16200g;

    /* renamed from: h */
    public String f16201h;

    /* renamed from: i */
    public boolean f16202i;

    /* renamed from: j */
    public boolean f16203j;

    /* renamed from: k */
    public List<C5371i> f16204k;

    /* renamed from: l */
    public List<C5366d> f16205l;

    /* renamed from: m */
    public long f16206m;

    /* renamed from: n */
    public long f16207n;

    /* renamed from: o */
    public f f16208o;

    /* renamed from: com.perfectcorp.ycl.pages.live.x$a */
    public class a implements FutureCallback<NetworkMessage.SendMessageResponse> {

        /* renamed from: a */
        public final /* synthetic */ C5371i f16209a;

        public a(C5371i c5371i) {
            this.f16209a = c5371i;
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(NetworkMessage.SendMessageResponse sendMessageResponse) {
            C5371i c5371i = this.f16209a;
            C4604x.this.m18375Q(new C5371i(c5371i.userId, c5371i.uuid, c5371i.name, c5371i.text, sendMessageResponse.messageId));
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onFailure(Throwable th) {
            Log.d("MessageDispatcher", "Send message fail");
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.x$b */
    public class b implements NetworkMessage.CallBack<List<JSONObject>> {

        /* renamed from: a */
        public final /* synthetic */ Set f16211a;

        public b(Set set) {
            this.f16211a = set;
        }

        @Override // com.perfectcorp.ycl.bc.model.network.NetworkMessage.CallBack
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(List<JSONObject> list) throws NumberFormatException {
            C5366d c5366dM18361C;
            C4604x.this.f16202i = true;
            if (!C4604x.this.f16203j) {
                C4604x.this.m18376q();
                return;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            C4604x.this.m18379u();
            int i9 = 0;
            while (true) {
                if (i9 >= list.size()) {
                    break;
                }
                JSONObject jSONObject = list.get(i9);
                long jOptLong = jSONObject.optLong(TtmlNode.ATTR_ID, -1L);
                if (jOptLong < C4604x.this.f16207n || C4604x.this.f16207n == -1) {
                    C4604x.this.f16206m = jOptLong;
                    String strM18378t = C4604x.this.m18378t(jSONObject);
                    if (C5371i.type.equals(strM18378t)) {
                        arrayList.add(C4604x.this.m18362D(jSONObject));
                    } else if (C5366d.type.equals(strM18378t) && (c5366dM18361C = C4604x.this.m18361C(jSONObject)) != null) {
                        arrayList2.add(c5366dM18361C);
                        String str = c5366dM18361C.event;
                        str.hashCode();
                        if (str.equals("live.user.assistant")) {
                            this.f16211a.add(c5366dM18361C.attributes.uid);
                        } else if (str.equals("live.user.normal.for.assistant.case")) {
                            this.f16211a.remove(c5366dM18361C.attributes.uid);
                        }
                    }
                    i9++;
                } else if (C4604x.this.f16208o != null && i9 > 0) {
                    JSONObject jSONObject2 = list.get(i9 - 1);
                    C4604x.this.f16208o.mo13933a("MessageDispatcher", "last handle archive message:" + jSONObject2.toString());
                }
            }
            if (this.f16211a.size() > 0) {
                C4604x.this.f16195b.m18279I(this.f16211a);
            }
            C4604x.this.m18373O(arrayList);
            C4604x.this.m18380v(arrayList2);
            if (C4604x.this.f16208o != null) {
                C4604x.this.f16208o.mo13933a("MessageDispatcher", "handle archive done");
            }
        }

        @Override // com.perfectcorp.ycl.bc.model.network.NetworkMessage.CallBack
        public void onError() {
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.x$c */
    public class c implements NetworkMessage.CallBack<List<JSONObject>> {

        /* renamed from: a */
        public final /* synthetic */ Set f16213a;

        /* renamed from: b */
        public final /* synthetic */ String f16214b;

        /* renamed from: c */
        public final /* synthetic */ String f16215c;

        /* renamed from: d */
        public final /* synthetic */ long f16216d;

        /* renamed from: e */
        public final /* synthetic */ NetworkMessage.CallBack f16217e;

        public c(Set set, String str, String str2, long j9, NetworkMessage.CallBack callBack) {
            this.f16213a = set;
            this.f16214b = str;
            this.f16215c = str2;
            this.f16216d = j9;
            this.f16217e = callBack;
        }

        @Override // com.perfectcorp.ycl.bc.model.network.NetworkMessage.CallBack
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(List<JSONObject> list) {
            for (int i9 = 0; i9 < list.size(); i9++) {
                String strOptString = list.get(i9).optString("uid");
                if (!C4509d.m18120b(strOptString)) {
                    this.f16213a.add(strOptString);
                }
            }
            NetworkMessage.downloadArchivedMessageRecursive(this.f16214b, this.f16215c, this.f16216d, 0L, new ArrayList(), this.f16217e);
        }

        @Override // com.perfectcorp.ycl.bc.model.network.NetworkMessage.CallBack
        public void onError() {
            if (C4604x.this.f16208o != null) {
                C4604x.this.f16208o.mo13933a("MessageDispatcher", "list assistant error");
            }
            NetworkMessage.downloadArchivedMessageRecursive(this.f16214b, this.f16215c, this.f16216d, 0L, new ArrayList(), this.f16217e);
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.x$d */
    public interface d {
        /* renamed from: a */
        void mo13768a(C5366d c5366d);

        /* renamed from: b */
        void mo13769b(C5366d c5366d);
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.x$e */
    public static class e {

        /* renamed from: a */
        public static final C4604x f16219a = new C4604x(null);
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.x$f */
    public interface f {
        /* renamed from: a */
        void mo13933a(String str, String str2);
    }

    public /* synthetic */ C4604x(a aVar) {
        this();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: A */
    public /* synthetic */ void m18341A(C5327b c5327b, C5370h c5370h) {
        m18374P(c5370h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B */
    public /* synthetic */ void m18342B(C5327b c5327b, C5371i c5371i) {
        Key.Init._Parameter _parameter = Key.Init.Parameter;
        if (_parameter == null || !c5371i.uuid.equals(_parameter.uuid)) {
            m18375Q(c5371i);
        }
    }

    /* renamed from: s */
    public static C4604x m18359s() {
        return e.f16219a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z */
    public /* synthetic */ void m18360z(C5327b c5327b, C5366d c5366d) {
        if (this.f16196c == null || c5366d == null) {
            return;
        }
        if (!this.f16202i) {
            this.f16205l.add(c5366d);
        } else {
            if (m18381w(c5366d)) {
                return;
            }
            this.f16196c.mo13768a(c5366d);
        }
    }

    /* renamed from: C */
    public final C5366d m18361C(JSONObject jSONObject) {
        C5366d c5366d = (C5366d) C5369g.GSON.fromJson(jSONObject.optString(TtmlNode.TAG_BODY), C5366d.class);
        if (c5366d != null) {
            c5366d.f18250id = jSONObject.optString(TtmlNode.ATTR_ID);
        }
        return c5366d;
    }

    /* renamed from: D */
    public final C5371i m18362D(JSONObject jSONObject) throws JSONException {
        String string;
        String string2;
        String string3;
        String string4;
        JSONObject jSONObject2;
        String strOptString = null;
        try {
            string2 = jSONObject.getString(TtmlNode.ATTR_ID);
            try {
                jSONObject2 = new JSONObject(jSONObject.getString(TtmlNode.TAG_BODY));
                string = jSONObject2.getString("userId");
            } catch (JSONException e9) {
                e = e9;
                string = null;
                string3 = null;
            }
        } catch (JSONException e10) {
            e = e10;
            string = null;
            string2 = null;
            string3 = null;
        }
        try {
            string3 = jSONObject2.getString("displayName");
            try {
                string4 = jSONObject2.getString(MimeTypes.BASE_TYPE_TEXT);
                try {
                    strOptString = jSONObject2.optString("uuid");
                } catch (JSONException e11) {
                    e = e11;
                    e.printStackTrace();
                    return new C5371i(string, strOptString, string3, string4, string2);
                }
            } catch (JSONException e12) {
                e = e12;
                string4 = null;
            }
        } catch (JSONException e13) {
            e = e13;
            string3 = null;
            string4 = string3;
            e.printStackTrace();
            return new C5371i(string, strOptString, string3, string4, string2);
        }
        return new C5371i(string, strOptString, string3, string4, string2);
    }

    /* renamed from: E */
    public void m18363E() {
        C5292d c5292d = this.f16194a;
        if (c5292d != null) {
            c5292d.m20631c0();
        }
    }

    /* renamed from: F */
    public void m18364F() {
        this.f16202i = false;
        this.f16203j = true;
        this.f16206m = -1L;
        this.f16207n = -1L;
        m18376q();
    }

    /* renamed from: G */
    public void m18365G(String str, String str2) {
        if (this.f16194a == null) {
            return;
        }
        String str3 = C4509d.m18120b(this.f16200g) ? "" : this.f16200g;
        Key.Init._Parameter _parameter = Key.Init.Parameter;
        C5371i c5371i = new C5371i(str3, _parameter != null ? _parameter.uuid : "", str, str2);
        C5053b.m19752a(this.f16194a.m20626Y(c5371i), new a(c5371i));
    }

    /* renamed from: H */
    public void m18366H(boolean z8) {
        this.f16195b.m18280J(z8);
    }

    /* renamed from: I */
    public void m18367I(boolean z8) {
        this.f16203j = z8;
    }

    /* renamed from: J */
    public void m18368J(C4597q c4597q) {
        this.f16195b = c4597q;
    }

    /* renamed from: K */
    public final void m18369K() {
        C5327b.a<C5366d> aVar = new C5327b.a() { // from class: com.perfectcorp.ycl.pages.live.v
            @Override // p138m5.C5327b.a
            /* renamed from: a */
            public final void mo18340a(C5327b c5327b, InterfaceC5367e interfaceC5367e) {
                this.f16192a.m18360z(c5327b, (C5366d) interfaceC5367e);
            }
        };
        this.f16199f = aVar;
        this.f16194a.m20930i(C5366d.class, aVar);
    }

    /* renamed from: L */
    public final void m18370L() {
        C5327b.a<C5370h> aVar = new C5327b.a() { // from class: com.perfectcorp.ycl.pages.live.w
            @Override // p138m5.C5327b.a
            /* renamed from: a */
            public final void mo18340a(C5327b c5327b, InterfaceC5367e interfaceC5367e) {
                this.f16193a.m18341A(c5327b, (C5370h) interfaceC5367e);
            }
        };
        this.f16198e = aVar;
        this.f16194a.m20930i(C5370h.class, aVar);
    }

    /* renamed from: M */
    public final void m18371M() {
        C5327b.a<C5371i> aVar = new C5327b.a() { // from class: com.perfectcorp.ycl.pages.live.u
            @Override // p138m5.C5327b.a
            /* renamed from: a */
            public final void mo18340a(C5327b c5327b, InterfaceC5367e interfaceC5367e) {
                this.f16191a.m18342B(c5327b, (C5371i) interfaceC5367e);
            }
        };
        this.f16197d = aVar;
        this.f16194a.m20930i(C5371i.class, aVar);
    }

    /* renamed from: N */
    public void m18372N() {
        C5292d c5292d = this.f16194a;
        if (c5292d != null) {
            c5292d.mo20629b();
        }
    }

    /* renamed from: O */
    public final void m18373O(List<C5371i> list) {
        ArrayList<C5371i> arrayList = new ArrayList(list);
        arrayList.addAll(this.f16204k);
        ArrayList arrayList2 = new ArrayList();
        for (C5371i c5371i : arrayList) {
            arrayList2.add(new C4597q.g(c5371i.name, c5371i.userId, c5371i.uuid, c5371i.text, c5371i.f18249id));
        }
        this.f16195b.m18284N(arrayList2);
        this.f16204k.clear();
    }

    /* renamed from: P */
    public final void m18374P(C5370h c5370h) {
        this.f16195b.m18287Q(new C4597q.f(c5370h.text));
    }

    /* renamed from: Q */
    public final void m18375Q(C5371i c5371i) {
        if (!this.f16202i) {
            this.f16204k.add(c5371i);
        } else {
            if (m18382x(c5371i)) {
                return;
            }
            this.f16195b.m18287Q(new C4597q.g(c5371i.name, c5371i.userId, c5371i.uuid, c5371i.text, c5371i.f18249id));
        }
    }

    /* renamed from: q */
    public void m18376q() {
        this.f16204k.clear();
        this.f16205l.clear();
    }

    /* renamed from: r */
    public void m18377r(String str, String str2, long j9) {
        if (!this.f16203j) {
            this.f16202i = true;
            m18376q();
        }
        if (this.f16202i) {
            return;
        }
        f fVar = this.f16208o;
        if (fVar != null) {
            fVar.mo13933a("MessageDispatcher", "getArchivedMessage liveId:" + str2 + " endOffset:" + j9);
        }
        HashSet hashSet = new HashSet();
        NetworkMessage.listAssistantRecursive(this.f16201h, str2, 0L, new ArrayList(), new c(hashSet, str, str2, j9, new b(hashSet)));
    }

    /* renamed from: t */
    public final String m18378t(JSONObject jSONObject) {
        try {
            return jSONObject.getString("type");
        } catch (JSONException e9) {
            e9.printStackTrace();
            return null;
        }
    }

    /* renamed from: u */
    public final void m18379u() throws NumberFormatException {
        if (this.f16204k.size() > 0) {
            long j9 = Long.parseLong(this.f16204k.get(0).f18249id);
            this.f16207n = j9;
            f fVar = this.f16208o;
            if (fVar != null) {
                fVar.mo13933a("MessageDispatcher", "wait archive done last live message id:" + j9);
            }
        }
        if (this.f16205l.size() > 0) {
            long j10 = Long.parseLong(this.f16205l.get(0).f18250id);
            long j11 = this.f16207n;
            if (j11 == -1 || j11 > j10) {
                this.f16207n = j10;
            }
            f fVar2 = this.f16208o;
            if (fVar2 != null) {
                fVar2.mo13933a("MessageDispatcher", "wait archive done last live event id:" + j10);
            }
        }
    }

    /* renamed from: v */
    public final void m18380v(List<C5366d> list) {
        Iterator<C5366d> it = list.iterator();
        while (it.hasNext()) {
            this.f16196c.mo13769b(it.next());
        }
        Iterator<C5366d> it2 = this.f16205l.iterator();
        while (it2.hasNext()) {
            this.f16196c.mo13768a(it2.next());
        }
        this.f16205l.clear();
    }

    /* renamed from: w */
    public final boolean m18381w(C5366d c5366d) {
        return this.f16206m >= Long.parseLong(c5366d.f18250id);
    }

    /* renamed from: x */
    public final boolean m18382x(C5371i c5371i) {
        return this.f16206m >= Long.parseLong(c5371i.f18249id);
    }

    /* renamed from: y */
    public void m18383y(String str, String str2, String str3, boolean z8, String str4, d dVar, f fVar) {
        if (NetworkManager.initResponse == null) {
            return;
        }
        this.f16200g = str4;
        this.f16201h = str3;
        this.f16196c = dVar;
        this.f16208o = fVar;
        m18372N();
        this.f16194a = new C5292d(str, str2, str3, z8);
        m18369K();
        m18371M();
        m18370L();
    }

    public C4604x() {
        this.f16202i = false;
        this.f16203j = true;
        this.f16204k = new ArrayList();
        this.f16205l = new ArrayList();
        this.f16206m = -1L;
        this.f16207n = -1L;
    }
}
