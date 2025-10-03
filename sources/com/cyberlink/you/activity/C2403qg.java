package com.cyberlink.you.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.LoadImageUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5172p;
import p201t3.C6301o;
import p209u2.C6385v;

/* renamed from: com.cyberlink.you.activity.qg */
/* loaded from: classes.dex */
public class C2403qg extends Fragment {

    /* renamed from: h */
    public static List<String> f11088h = new ArrayList();

    /* renamed from: b */
    public ListView f11089b;

    /* renamed from: c */
    public View f11090c;

    /* renamed from: d */
    public h f11091d;

    /* renamed from: e */
    public StickerPackObj f11092e;

    /* renamed from: f */
    public AbsListView.OnScrollListener f11093f = new a();

    /* renamed from: g */
    public AdapterView.OnItemClickListener f11094g = new c();

    /* renamed from: com.cyberlink.you.activity.qg$a */
    public class a implements AbsListView.OnScrollListener {

        /* renamed from: a */
        public int f11095a;

        /* renamed from: b */
        public int f11096b;

        /* renamed from: c */
        public int f11097c;

        public a() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i9, int i10, int i11) {
            this.f11095a = i9;
            this.f11096b = i10;
            this.f11097c = i11;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i9) {
            if (i9 == 0 && this.f11095a + this.f11096b == this.f11097c) {
                Log.d("StickerPurchasedList", "isBottom");
            }
            if (i9 == 0 && this.f11095a == 0) {
                Log.d("StickerPurchasedList", "isTop");
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.qg$b */
    public class b extends AsyncTask<Void, Void, List<StickerPackObj>> {
        public b() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<StickerPackObj> doInBackground(Void... voidArr) {
            String strM7449L = Globals.m7388i0().m7449L();
            ArrayList arrayList = new ArrayList();
            Log.d("StickerPurchasedList", " mPurchaseList.size() = " + C2403qg.f11088h.size());
            for (int i9 = 0; i9 < C2403qg.f11088h.size(); i9++) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new C6301o("token", strM7449L));
                arrayList2.add(new C6301o("packId", C2403qg.f11088h.get(i9)));
                Log.d("StickerPurchasedList", " mPurchaseList.get(i) = " + C2403qg.f11088h.get(i9));
                FriendsClient friendsClient = new FriendsClient(true);
                Pair<String, String> pairM15731j = friendsClient.m15731j("sticker", "pack.info", arrayList2);
                String str = (String) pairM15731j.first;
                String str2 = (String) pairM15731j.second;
                if (str == null || !str.equals("200")) {
                    Log.d("StickerPurchasedList", "statuscode = " + str);
                } else {
                    int iM16494U0 = CLUtility.m16494U0(str2);
                    if (iM16494U0 != -1) {
                        List listM12517s = C2403qg.this.m12517s(str2);
                        arrayList.addAll(listM12517s);
                        listM12517s.clear();
                    } else {
                        Log.d("StickerPurchasedList", " resultsSize " + iM16494U0);
                    }
                }
                friendsClient.m15717U0();
            }
            return arrayList;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<StickerPackObj> list) {
            if (list != null) {
                C2403qg.this.f11091d.addAll(list);
            }
            C2403qg.this.f11090c.setVisibility(8);
            C2403qg.this.f11089b.setVisibility(0);
        }
    }

    /* renamed from: com.cyberlink.you.activity.qg$c */
    public class c implements AdapterView.OnItemClickListener {
        public c() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            C2403qg c2403qg = C2403qg.this;
            c2403qg.f11092e = (StickerPackObj) c2403qg.f11091d.getItem(i9);
            if (C2403qg.this.f11092e.m14815s()) {
                Log.d("StickerPurchasedList", "mStickerPackObj isShowed. Doesn't need to download ");
            } else {
                C2403qg.this.m12515q();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.qg$d */
    public class d extends AsyncTask<Void, Void, Boolean> {

        /* renamed from: a */
        public final /* synthetic */ String f11101a;

        /* renamed from: b */
        public final /* synthetic */ String f11102b;

        /* renamed from: c */
        public final /* synthetic */ String f11103c;

        public d(String str, String str2, String str3) {
            this.f11101a = str;
            this.f11102b = str2;
            this.f11103c = str3;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) {
            if (CLUtility.m16463M(this.f11101a, this.f11102b) && CLUtility.m16474O2(this.f11102b, this.f11103c)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            if (bool.booleanValue()) {
                C2403qg.this.m12520v();
            } else {
                C2403qg.this.f11090c.setVisibility(8);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.qg$e */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C2403qg c2403qg = C2403qg.this;
            c2403qg.m12519u(c2403qg.f11092e.m14811o());
            C2403qg.this.f11090c.setVisibility(8);
        }
    }

    /* renamed from: com.cyberlink.you.activity.qg$f */
    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C2403qg.this.f11091d.notifyDataSetChanged();
        }
    }

    /* renamed from: com.cyberlink.you.activity.qg$g */
    public class g implements FriendsClient.InterfaceC3051i {

        /* renamed from: com.cyberlink.you.activity.qg$g$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                C2403qg.this.f11090c.setVisibility(8);
            }
        }

        /* renamed from: com.cyberlink.you.activity.qg$g$b */
        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                C2403qg.this.f11090c.setVisibility(8);
            }
        }

        /* renamed from: com.cyberlink.you.activity.qg$g$c */
        public class c implements Runnable {
            public c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                C2403qg.this.f11090c.setVisibility(8);
            }
        }

        public g() {
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
        /* renamed from: a */
        public void mo134a(String str, String str2, String str3, String str4) throws Throwable {
            if (str3 == null) {
                Log.d("StickerPurchasedList", "Response is null");
                C2403qg.this.getActivity().runOnUiThread(new a());
                return;
            }
            if (!str3.equals("200")) {
                Log.d("StickerPurchasedList", "statusCode=" + str3);
                C2403qg.this.getActivity().runOnUiThread(new b());
                return;
            }
            C2403qg c2403qg = C2403qg.this;
            List<StickerObj> listM12518t = c2403qg.m12518t(str4, c2403qg.f11092e);
            if (listM12518t != null) {
                C2950b0.m14924w().m15277e(listM12518t);
                C2403qg.this.m12519u(StickerPackObj.Status.DOWNLOADED);
            }
            C2403qg.this.getActivity().runOnUiThread(new c());
        }
    }

    /* renamed from: com.cyberlink.you.activity.qg$h */
    public class h extends ArrayAdapter<StickerPackObj> {

        /* renamed from: com.cyberlink.you.activity.qg$h$a */
        public class a {

            /* renamed from: a */
            public ImageView f11112a;

            /* renamed from: b */
            public ImageView f11113b;

            /* renamed from: c */
            public TextView f11114c;

            /* renamed from: d */
            public TextView f11115d;

            /* renamed from: e */
            public View f11116e;

            public a() {
            }

            public /* synthetic */ a(h hVar, a aVar) {
                this();
            }
        }

        public h(Context context, int i9, List<StickerPackObj> list) {
            super(context, i9, list);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.my_sticker_purchase_item, viewGroup, false);
                aVar = new a(this, null);
                aVar.f11114c = (TextView) view.findViewById(R.id.auther);
                aVar.f11115d = (TextView) view.findViewById(R.id.name);
                aVar.f11112a = (ImageView) view.findViewById(R.id.cover);
                aVar.f11113b = (ImageView) view.findViewById(R.id.toDetail);
                aVar.f11116e = view.findViewById(R.id.newIcon);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            StickerPackObj stickerPackObj = (StickerPackObj) getItem(i9);
            StickerPackObj stickerPackObjM15293k = C2950b0.m14925x().m15293k(stickerPackObj.m14803g());
            if (stickerPackObjM15293k != null) {
                if (!stickerPackObjM15293k.m14815s()) {
                    aVar.f11113b.setImageResource(R.drawable.icon_download_sticker_n);
                } else if (stickerPackObjM15293k.m14814r()) {
                    aVar.f11113b.setImageResource(R.drawable.icon_done_sticker);
                }
            }
            if (stickerPackObj.m14812p().f13074b != null) {
                LoadImageUtils.m16641z(getContext(), stickerPackObj, aVar.f11112a, true, true);
            }
            aVar.f11114c.setText(stickerPackObj.m14808l());
            if (stickerPackObj.m14804h() != null) {
                aVar.f11115d.setText(stickerPackObj.m14804h());
            }
            aVar.f11113b.setTag(stickerPackObj);
            return view;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_sticker_shop_list, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        ListView listView = (ListView) view.findViewById(R.id.stickerShopList);
        this.f11089b = listView;
        listView.setOnScrollListener(this.f11093f);
        this.f11090c = view.findViewById(R.id.loading);
        if (getArguments() != null) {
            f11088h = getArguments().getStringArrayList("purchasedList");
        }
        h hVar = new h(getActivity(), R.layout.my_sticker_purchase_item, new ArrayList());
        this.f11091d = hVar;
        this.f11089b.setAdapter((ListAdapter) hVar);
        this.f11089b.setOnItemClickListener(this.f11094g);
        m12516r(getArguments().getString("type"));
    }

    /* renamed from: q */
    public final void m12515q() {
        this.f11090c.setVisibility(0);
        String str = this.f11092e.m14812p().f13073a;
        StringBuilder sb = new StringBuilder();
        sb.append(getActivity().getCacheDir());
        String str2 = File.separator;
        sb.append(str2);
        sb.append(this.f11092e.m14803g());
        sb.append(".zip");
        d dVar = new d(str, sb.toString(), CLUtility.m16537g1() + str2);
        if (this.f11092e.m14814r()) {
            getActivity().runOnUiThread(new e());
        } else {
            dVar.executeOnExecutor(C6385v.f21554b, new Void[0]);
        }
    }

    /* renamed from: r */
    public final void m12516r(String str) {
        new b().execute(new Void[0]);
    }

    /* renamed from: s */
    public final List<StickerPackObj> m12517s(String str) {
        List<StickerPackObj> listM20177D = C5172p.m20177D(C5172p.m20196r(str), false, false);
        return listM20177D != null ? listM20177D : new ArrayList();
    }

    /* renamed from: t */
    public final List<StickerObj> m12518t(String str, StickerPackObj stickerPackObj) throws Throwable {
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
                            Log.e("StickerPurchasedList", "[sticker.sticker.list] Parse item error. JSONstr=" + jSONObject.toString());
                        }
                    } catch (JSONException unused2) {
                        Log.e("StickerPurchasedList", "[sticker.sticker.list] groupinfo parse error. JSONstr=" + str);
                    }
                }
                return arrayList;
            } catch (JSONException unused3) {
                Log.e("StickerPurchasedList", "[sticker.sticker.list] 'results' missing. JSONstr=" + str);
                return null;
            }
        } catch (JSONException unused4) {
            Log.e("StickerPurchasedList", "[sticker.sticker.list] Parse error. JSONstr=" + str);
            return null;
        }
    }

    /* renamed from: u */
    public final void m12519u(StickerPackObj.Status status) {
        this.f11092e.m14819w(status);
        this.f11092e.m14818v(true);
        if (C2950b0.m14925x().m15293k(this.f11092e.m14803g()) == null) {
            this.f11092e.m14812p().f13077e = CLUtility.m16541h1(this.f11092e.m14803g()) + File.separator + "thumbnail";
        }
        C2950b0.m14925x().m15287e(this.f11092e, true);
        C2950b0.m14925x().m15298p(this.f11092e.m14803g());
        getActivity().runOnUiThread(new f());
    }

    /* renamed from: v */
    public final void m12520v() {
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("packId", String.valueOf(this.f11092e.m14803g())));
        arrayList.add(new C6301o("pageIndex", String.valueOf(1)));
        arrayList.add(new C6301o("pageSize", String.valueOf(200)));
        new FriendsClient(true).m15734m("sticker", "sticker.list", arrayList, new g());
    }
}
