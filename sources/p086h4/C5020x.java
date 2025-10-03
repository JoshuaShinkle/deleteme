package p086h4;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.utility.C3197a;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p086h4.C5001e;
import p116k4.C5170o0;
import p116k4.C5187v0;
import p189s0.AbstractC6243a;
import p201t3.C6301o;
import p218v2.C6456d;

/* renamed from: h4.x */
/* loaded from: classes.dex */
public class C5020x extends AbstractC5005i {

    /* renamed from: c */
    public boolean f17310c;

    /* renamed from: d */
    public c f17311d;

    /* renamed from: e */
    public boolean f17312e;

    /* renamed from: f */
    public C4996b0 f17313f;

    /* renamed from: g */
    public List<C4993a> f17314g;

    /* renamed from: h */
    public List<C4993a> f17315h;

    /* renamed from: i */
    public List<C4993a> f17316i;

    /* renamed from: j */
    public String f17317j;

    /* renamed from: k */
    public C5001e f17318k;

    /* renamed from: l */
    public C5015s f17319l;

    /* renamed from: m */
    public e f17320m;

    /* renamed from: n */
    public C5001e.c f17321n;

    /* renamed from: o */
    public final Executor f17322o;

    /* renamed from: p */
    public final Executor f17323p;

    /* renamed from: q */
    public View.OnClickListener f17324q;

    /* renamed from: h4.x$a */
    public class a implements C5001e.c {
        public a() {
        }

        @Override // p086h4.C5001e.c
        /* renamed from: a */
        public void mo19424a(StickerObj stickerObj, View view) {
            C5020x.this.f17311d.mo19521a(stickerObj, view);
        }

        @Override // p086h4.C5001e.c
        /* renamed from: b */
        public void mo19425b(List<C4993a> list) {
            C5020x.this.f17316i.clear();
            C5020x.this.f17316i.addAll(list);
            C5020x c5020x = C5020x.this;
            c5020x.f17313f = new C4996b0(c5020x.f17248a, c5020x.m19557v(), false);
            C5020x.this.f17311d.mo19525e();
        }
    }

    /* renamed from: h4.x$b */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C5020x.this.f17311d.mo19521a((StickerObj) view.getTag(), view);
        }
    }

    /* renamed from: h4.x$c */
    public interface c {
        /* renamed from: a */
        void mo19521a(StickerObj stickerObj, View view);

        /* renamed from: b */
        void mo19522b();

        /* renamed from: c */
        void mo19523c();

        /* renamed from: d */
        void mo19524d(List<StickerPackObj> list);

        /* renamed from: e */
        void mo19525e();
    }

    /* renamed from: h4.x$d */
    public interface d {
        /* renamed from: a */
        void mo19529a(List<StickerObj> list);

        /* renamed from: b */
        void mo19530b();
    }

    /* renamed from: h4.x$f */
    public class f extends AsyncTask<String, Void, List<Pair<StickerObj, Bitmap>>> {

        /* renamed from: a */
        public g f17331a;

        /* renamed from: b */
        public StickerPackObj f17332b;

        /* renamed from: c */
        public List<StickerObj> f17333c;

        /* renamed from: d */
        public a f17334d;

        public f(StickerPackObj stickerPackObj, g gVar) {
            this.f17332b = stickerPackObj;
            this.f17331a = gVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: n */
        public /* synthetic */ void m19575n(View view) {
            if (!C6456d.m24714D().m24748G()) {
                C5187v0.m20267c(R.string.error_no_network);
                return;
            }
            a aVar = new a(true);
            this.f17334d = aVar;
            aVar.executeOnExecutor(C5020x.this.f17323p, Long.valueOf(this.f17332b.m14803g()));
            this.f17332b.m14817u(true);
            C5020x.this.f17319l.m19513o0();
        }

        @Override // android.os.AsyncTask
        /* renamed from: i, reason: merged with bridge method [inline-methods] */
        public List<Pair<StickerObj, Bitmap>> doInBackground(String... strArr) {
            if (C5020x.this.f17312e) {
                return null;
            }
            this.f17333c = C2950b0.m14924w().m15279g(this.f17332b.m14803g());
            if (this.f17332b.m14811o() == StickerPackObj.Status.NONE || this.f17332b.m14811o() == StickerPackObj.Status.NOT_DOWNLOADED || this.f17333c == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (StickerObj stickerObj : this.f17333c) {
                if (C5020x.this.f17312e) {
                    return null;
                }
                try {
                    arrayList.add(Pair.create(stickerObj, null));
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
            }
            return arrayList;
        }

        /* renamed from: j */
        public final int m19577j() {
            for (int i9 = 0; i9 < C5020x.this.f17313f.getCount(); i9++) {
                StickerPackObj stickerPackObjM19372e = C5020x.this.f17313f.m19382d(i9).m19372e();
                if (stickerPackObjM19372e != null && this.f17332b != null && stickerPackObjM19372e.m14803g() == this.f17332b.m14803g()) {
                    return i9;
                }
            }
            return -1;
        }

        /* renamed from: k */
        public final void m19578k() {
            a aVar = new a();
            this.f17334d = aVar;
            aVar.executeOnExecutor(C5020x.this.f17323p, new Long[0]);
        }

        /* renamed from: l */
        public final void m19579l() {
            ArrayList arrayList = new ArrayList();
            C4993a c4993a = new C4993a(this.f17332b.m14803g(), C5020x.this.f17248a.getLayoutInflater().inflate(R.layout.view_item_sticker_preview, (ViewGroup) null));
            c4993a.m19376i(this.f17332b);
            c4993a.m19374g(new View.OnClickListener() { // from class: h4.z
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f17340b.m19575n(view);
                }
            });
            arrayList.add(c4993a);
            this.f17333c = null;
            this.f17331a.mo19377a(arrayList);
        }

        /* renamed from: m */
        public final List<C4993a> m19580m(List<Pair<StickerObj, Bitmap>> list) {
            ArrayList arrayList = new ArrayList();
            View viewInflate = null;
            for (int i9 = 0; i9 < list.size(); i9++) {
                Pair<StickerObj, Bitmap> pair = list.get(i9);
                int i10 = i9 % 8;
                if (i10 == 0) {
                    if (viewInflate != null) {
                        C4993a c4993a = new C4993a(((StickerObj) pair.first).m16284i(), viewInflate);
                        c4993a.m19376i(this.f17332b);
                        arrayList.add(c4993a);
                    }
                    viewInflate = C5020x.this.f17248a.getLayoutInflater().inflate(R.layout.view_item_sticker_preview, (ViewGroup) null);
                }
                if (!C5020x.this.f17312e) {
                    ImageView imageView = (ImageView) viewInflate.findViewById(C5020x.this.f17248a.getResources().getIdentifier("stickerView" + i10, TtmlNode.ATTR_ID, C5020x.this.f17248a.getPackageName()));
                    if (imageView != null) {
                        imageView.setImageBitmap(null);
                        imageView.setTag(pair.first);
                        imageView.setOnClickListener(C5020x.this.f17324q);
                    }
                }
            }
            Pair<StickerObj, Bitmap> pair2 = list.get(list.size() - 1);
            if (viewInflate != null) {
                C4993a c4993a2 = new C4993a(((StickerObj) pair2.first).m16284i(), viewInflate);
                c4993a2.m19376i(this.f17332b);
                arrayList.add(c4993a2);
            }
            return arrayList;
        }

        @Override // android.os.AsyncTask
        /* renamed from: o, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<Pair<StickerObj, Bitmap>> list) {
            if (C5020x.this.f17312e) {
                this.f17333c = null;
            } else {
                if (list == null) {
                    m19578k();
                    return;
                }
                List<C4993a> listM19580m = m19580m(list);
                this.f17333c = null;
                this.f17331a.mo19377a(listM19580m);
            }
        }

        /* renamed from: p */
        public final void m19582p(List<C4993a> list) {
            int i9 = -1;
            int i10 = 0;
            for (int i11 = 0; i11 < C5020x.this.f17315h.size(); i11++) {
                if (!((C4993a) C5020x.this.f17315h.get(i11)).m19373f(list.get(0))) {
                    if (i9 >= 0) {
                        break;
                    }
                } else {
                    if (i9 < 0) {
                        i9 = i11;
                    }
                    i10++;
                }
            }
            if (i9 >= 0) {
                for (int i12 = 0; i12 < i10; i12++) {
                    C5020x.this.f17315h.remove(i9);
                }
            }
            C5020x.this.f17315h.addAll(i9, list);
            C5020x c5020x = C5020x.this;
            c5020x.f17313f = new C4996b0(c5020x.f17248a, c5020x.m19557v(), false);
            if (C5170o0.m20169d(C5020x.this.f17317j) || C5020x.this.f17317j.equals(list.get(0).m19368a())) {
                C5020x.this.m19449c();
                C5020x.this.m19556E(list.get(0).m19372e().m14803g());
            }
        }

        /* renamed from: h4.x$f$a */
        public class a extends AsyncTask<Long, Void, List<StickerObj>> {

            /* renamed from: a */
            public boolean f17336a;

            /* renamed from: h4.x$f$a$a, reason: collision with other inner class name */
            public class C6871a implements C3197a.b {
                public C6871a() {
                }

                /* JADX INFO: Access modifiers changed from: private */
                /* renamed from: e */
                public /* synthetic */ void m19587e(List list) {
                    int iM19577j = f.this.m19577j();
                    f.this.m19582p(list);
                    if (iM19577j != -1) {
                        C5020x.this.f17313f.m19390l(iM19577j, list, a.this.f17336a);
                    }
                    C5020x.this.f17319l.m19515q0(f.this.f17332b);
                    if (f.this.f17332b != null) {
                        Log.d("StickerPageController", "download " + f.this.f17332b.m14803g() + " done");
                    }
                    if (a.this.f17336a) {
                        C5020x.this.mo19400b();
                    }
                }

                @Override // com.cyberlink.you.utility.C3197a.b
                /* renamed from: a */
                public void mo9122a() {
                    C5020x.this.f17319l.m19512n0(true);
                    f.this.f17332b.m14816t(0);
                    C5020x.this.f17319l.m19518t0();
                    if (a.this.f17336a) {
                        f.this.f17332b.m14817u(false);
                    }
                }

                @Override // com.cyberlink.you.utility.C3197a.b
                /* renamed from: b */
                public void mo9123b(String str) {
                    f.this.f17332b.m14819w(StickerPackObj.Status.DOWNLOADED);
                    f.this.f17332b.m14818v(true);
                    if (C2950b0.m14925x().m15293k(f.this.f17332b.m14803g()) == null) {
                        f.this.f17332b.m14812p().f13077e = CLUtility.m16541h1(f.this.f17332b.m14803g()) + File.separator + "thumbnail";
                    }
                    C5020x.this.f17319l.m19517s0(f.this.f17332b);
                    C2950b0.m14925x().m15287e(f.this.f17332b, true);
                    f fVar = f.this;
                    C5020x.this.new f(fVar.f17332b, new g() { // from class: h4.a0
                        @Override // p086h4.C5020x.g
                        /* renamed from: a */
                        public final void mo19377a(List list) {
                            this.f17200a.m19587e(list);
                        }
                    }).executeOnExecutor(C5020x.this.f17322o, new String[0]);
                    if (a.this.f17336a) {
                        f.this.f17332b.m14817u(false);
                    }
                }

                @Override // com.cyberlink.you.utility.C3197a.b
                /* renamed from: c */
                public void mo9124c(int i9, int i10, int i11) {
                    f.this.f17332b.m14816t(i9);
                    if (f.this.f17332b != null) {
                        Log.d("StickerPageController", "download " + f.this.f17332b.m14803g() + " - " + i9 + "%");
                    }
                    C5020x.this.f17319l.m19518t0();
                }
            }

            public a() {
                this.f17336a = false;
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public List<StickerObj> doInBackground(Long... lArr) {
                if (f.this.f17333c != null && !f.this.f17333c.isEmpty()) {
                    return f.this.f17333c;
                }
                f fVar = f.this;
                return C5020x.this.m19559x(fVar.f17332b);
            }

            @Override // android.os.AsyncTask
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(List<StickerObj> list) {
                super.onPostExecute(list);
                if (list == null) {
                    if (!this.f17336a) {
                        f.this.m19579l();
                        return;
                    }
                    C5020x.this.f17319l.m19512n0(false);
                    f.this.f17332b.m14816t(0);
                    C5020x.this.f17319l.m19518t0();
                    f.this.f17332b.m14817u(false);
                    return;
                }
                ArrayList arrayList = new ArrayList();
                C3197a c3197a = new C3197a();
                c3197a.m16992o(f.this.f17332b.m14812p().f13073a);
                c3197a.m16990m(CLUtility.m16472O0(C5020x.this.f17248a) + File.separator + f.this.f17332b.m14803g() + ".zip");
                c3197a.m16989l(true);
                c3197a.m16991n(2);
                c3197a.m16988k(20000);
                c3197a.m16987j(new C6871a());
                if (this.f17336a) {
                    c3197a.m16993p();
                    return;
                }
                View viewInflate = null;
                for (int i9 = 0; i9 < list.size(); i9++) {
                    StickerObj stickerObj = list.get(i9);
                    int i10 = i9 % 8;
                    if (i10 == 0) {
                        if (viewInflate != null) {
                            C4993a c4993a = new C4993a(stickerObj.m16284i(), viewInflate);
                            c4993a.m19376i(f.this.f17332b);
                            c4993a.m19375h(c3197a);
                            arrayList.add(c4993a);
                        }
                        viewInflate = C5020x.this.f17248a.getLayoutInflater().inflate(R.layout.view_item_sticker_preview, (ViewGroup) null);
                    }
                    if (!C5020x.this.f17312e) {
                        ImageView imageView = (ImageView) viewInflate.findViewById(C5020x.this.f17248a.getResources().getIdentifier("stickerView" + i10, TtmlNode.ATTR_ID, C5020x.this.f17248a.getPackageName()));
                        if (imageView != null) {
                            imageView.setImageBitmap(null);
                            imageView.setTag(stickerObj);
                        }
                    }
                }
                StickerObj stickerObj2 = list.get(list.size() - 1);
                if (viewInflate != null) {
                    C4993a c4993a2 = new C4993a(stickerObj2.m16284i(), viewInflate);
                    c4993a2.m19376i(f.this.f17332b);
                    c4993a2.m19375h(c3197a);
                    arrayList.add(c4993a2);
                }
                f.this.f17333c = null;
                f.this.f17331a.mo19377a(arrayList);
            }

            public a(boolean z8) {
                this.f17336a = z8;
            }
        }
    }

    /* renamed from: h4.x$g */
    public interface g {
        /* renamed from: a */
        void mo19377a(List<C4993a> list);
    }

    public C5020x(C5015s c5015s, c cVar, C4998c0 c4998c0) {
        super(c5015s.getActivity(), c4998c0);
        this.f17310c = false;
        this.f17312e = false;
        this.f17314g = new ArrayList();
        this.f17315h = new ArrayList();
        this.f17316i = new ArrayList();
        this.f17317j = "";
        this.f17321n = new a();
        this.f17322o = Executors.newSingleThreadExecutor();
        this.f17323p = Executors.newFixedThreadPool(5);
        this.f17324q = new b();
        this.f17319l = c5015s;
        this.f17311d = cVar;
        this.f17318k = new C5001e(this.f17248a, this.f17321n, c4998c0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y */
    public /* synthetic */ void m19551y(StickerPackObj stickerPackObj, d dVar, String str, String str2, String str3, String str4) throws JSONException {
        if (!"200".equals(str3)) {
            if (dVar != null) {
                dVar.mo19530b();
            }
        } else {
            List<StickerObj> listM19560z = m19560z(str4, stickerPackObj);
            C2950b0.m14924w().m15277e(listM19560z);
            if (dVar != null) {
                dVar.mo19529a(listM19560z);
            }
        }
    }

    /* renamed from: A */
    public void m19552A() {
        if (new C5002f(this.f17248a).m19431f()) {
            return;
        }
        this.f17318k.m19418e();
    }

    /* renamed from: B */
    public void m19553B() {
        this.f17315h.clear();
        e eVar = this.f17320m;
        if (eVar != null) {
            eVar.cancel(true);
        }
        e eVar2 = new e(this, null);
        this.f17320m = eVar2;
        eVar2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* renamed from: C */
    public void m19554C(String str) {
        this.f17317j = str;
    }

    /* renamed from: D */
    public void m19555D() {
        this.f17312e = true;
        this.f17318k.m19419f();
        ((ExecutorService) this.f17322o).shutdown();
        ((ExecutorService) this.f17323p).shutdown();
    }

    /* renamed from: E */
    public void m19556E(long j9) {
        int i9 = 0;
        int i10 = 0;
        while (true) {
            if (i10 >= this.f17314g.size()) {
                break;
            }
            if (j9 == Long.valueOf(this.f17314g.get(i10).m19368a()).longValue()) {
                i9 = i10;
                break;
            }
            i10++;
        }
        this.f17249b.f17224e.setCurrentItem(i9);
        mo19400b();
    }

    @Override // p086h4.AbstractC5005i
    /* renamed from: a */
    public AbstractC6243a mo19399a() {
        return this.f17313f;
    }

    @Override // p086h4.AbstractC5005i
    /* renamed from: b */
    public void mo19400b() {
        C4996b0 c4996b0 = (C4996b0) this.f17249b.f17224e.getAdapter();
        int currentItem = this.f17249b.f17224e.getCurrentItem();
        if (c4996b0 != null) {
            this.f17249b.f17221b.m19450a(this.f17248a, c4996b0.m19385g(c4996b0.m19384f(currentItem)));
        }
    }

    @Override // p086h4.AbstractC5005i
    /* renamed from: d */
    public void mo19401d() {
        this.f17249b.f17223d.setVisibility(8);
    }

    @Override // p086h4.AbstractC5005i
    /* renamed from: e */
    public void mo19402e(int i9) {
        C4996b0 c4996b0 = (C4996b0) this.f17249b.f17224e.getAdapter();
        C4993a c4993aM19384f = c4996b0.m19384f(0);
        int i10 = 0;
        for (int i11 = 1; i11 <= i9; i11++) {
            C4993a c4993aM19384f2 = c4996b0.m19384f(i11);
            if (c4993aM19384f2.m19373f(c4993aM19384f)) {
                i10++;
            } else {
                i10 = 0;
                c4993aM19384f = c4993aM19384f2;
            }
        }
        if (!this.f17317j.equals(c4993aM19384f.m19368a())) {
            this.f17317j = c4993aM19384f.m19368a();
            mo19400b();
        }
        this.f17249b.f17221b.m19451b(i10);
    }

    /* renamed from: v */
    public final List<C4993a> m19557v() {
        ArrayList arrayList = new ArrayList(this.f17316i);
        this.f17314g = arrayList;
        arrayList.addAll(this.f17315h);
        return this.f17314g;
    }

    /* renamed from: w */
    public void m19558w(final StickerPackObj stickerPackObj, final d dVar) {
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("packId", String.valueOf(stickerPackObj.m14803g())));
        arrayList.add(new C6301o("pageIndex", String.valueOf(1)));
        arrayList.add(new C6301o("pageSize", String.valueOf(200)));
        new FriendsClient(true).m15734m("sticker", "sticker.list", arrayList, new FriendsClient.InterfaceC3051i() { // from class: h4.w
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) throws JSONException {
                this.f17307a.m19551y(stickerPackObj, dVar, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: x */
    public List<StickerObj> m19559x(StickerPackObj stickerPackObj) throws JSONException {
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("packId", String.valueOf(stickerPackObj.m14803g())));
        arrayList.add(new C6301o("pageIndex", String.valueOf(1)));
        arrayList.add(new C6301o("pageSize", String.valueOf(200)));
        Pair<String, String> pairM15731j = new FriendsClient(true).m15731j("sticker", "sticker.list", arrayList);
        String str = (String) pairM15731j.first;
        String str2 = (String) pairM15731j.second;
        if (str == null || !str.equals("200")) {
            return null;
        }
        List<StickerObj> listM19560z = m19560z(str2, stickerPackObj);
        C2950b0.m14924w().m15277e(listM19560z);
        return listM19560z;
    }

    /* renamed from: z */
    public final List<StickerObj> m19560z(String str, StickerPackObj stickerPackObj) throws JSONException {
        try {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("results");
                ArrayList arrayList = new ArrayList();
                for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                    try {
                        JSONObject jSONObject = jSONArray.getJSONObject(i9);
                        try {
                            long j9 = jSONObject.getLong("stickerId");
                            long j10 = jSONObject.getLong("stickerOrder");
                            long j11 = jSONObject.getLong("lastModified");
                            String string = jSONObject.getString("originalURL");
                            String string2 = jSONObject.getString("thumbnailURL");
                            String str2 = (CLUtility.m16541h1(stickerPackObj.m14803g()) + File.separator) + j9;
                            arrayList.add(new StickerObj(-1L, j9, stickerPackObj.m14803g(), j10, j11, string, str2, string2, str2 + "_thumbnail", 0, 0));
                        } catch (JSONException unused) {
                            Log.e("StickerPageController", "[sticker.sticker.list] Parse item error. JSONstr=" + jSONObject.toString());
                        }
                    } catch (JSONException unused2) {
                        Log.e("StickerPageController", "[sticker.sticker.list] groupInfo parse error. JSONstr=" + str);
                    }
                }
                return arrayList;
            } catch (JSONException unused3) {
                Log.e("StickerPageController", "[sticker.sticker.list] 'results' missing. JSONstr=" + str);
                return null;
            }
        } catch (JSONException unused4) {
            Log.e("StickerPageController", "[sticker.sticker.list] Parse error. JSONstr=" + str);
            return null;
        }
    }

    /* renamed from: h4.x$e */
    public class e extends AsyncTask<Void, Void, List<StickerPackObj>> {

        /* renamed from: a */
        public int f17327a;

        /* renamed from: b */
        public int f17328b;

        /* renamed from: c */
        public List<StickerPackObj> f17329c;

        public e() {
            this.f17327a = 0;
            this.f17328b = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m19562c(List list) {
            this.f17328b++;
            C5020x.this.f17315h.addAll(list);
            if (!C5020x.this.f17310c || this.f17328b < this.f17327a) {
                return;
            }
            m19566f();
            C5020x.this.f17310c = false;
            C5020x c5020x = C5020x.this;
            c5020x.f17313f = new C4996b0(c5020x.f17248a, c5020x.m19557v(), false);
            C5020x.this.f17311d.mo19522b();
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public List<StickerPackObj> doInBackground(Void... voidArr) {
            if (!C5020x.this.f17312e) {
                return C2950b0.m14925x().m15294l();
            }
            cancel(true);
            return null;
        }

        /* renamed from: d */
        public final void m19564d(StickerPackObj stickerPackObj) {
            C5020x.this.new f(stickerPackObj, new g() { // from class: h4.y
                @Override // p086h4.C5020x.g
                /* renamed from: a */
                public final void mo19377a(List list) {
                    this.f17339a.m19562c(list);
                }
            }).executeOnExecutor(C5020x.this.f17322o, new String[0]);
        }

        @Override // android.os.AsyncTask
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<StickerPackObj> list) {
            this.f17329c = list;
            if (list == null || list.size() == 0) {
                C5020x.this.f17311d.mo19523c();
                return;
            }
            this.f17327a = list.size();
            this.f17328b = 0;
            for (StickerPackObj stickerPackObj : list) {
                if (C5020x.this.f17312e) {
                    break;
                } else {
                    m19564d(stickerPackObj);
                }
            }
            if (this.f17328b < this.f17327a) {
                C5020x.this.f17310c = true;
            } else {
                C5020x.this.f17311d.mo19522b();
            }
            C5020x.this.f17311d.mo19524d(list);
        }

        /* renamed from: f */
        public final void m19566f() {
            ArrayList arrayList = new ArrayList();
            for (int i9 = 0; i9 < this.f17329c.size(); i9++) {
                for (C4993a c4993a : C5020x.this.f17315h) {
                    if (c4993a.m19368a().equals(String.valueOf(this.f17329c.get(i9).m14803g()))) {
                        arrayList.add(c4993a);
                    }
                }
            }
            C5020x.this.f17315h = arrayList;
        }

        public /* synthetic */ e(C5020x c5020x, a aVar) {
            this();
        }
    }
}
