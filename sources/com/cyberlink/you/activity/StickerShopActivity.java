package com.cyberlink.you.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.utility.LoadImageUtils;
import com.cyberlink.you.widgetpool.common.CLFragmentTabHost;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import p116k4.C5164m0;
import p116k4.C5167n0;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class StickerShopActivity extends BaseFragmentActivity {

    /* renamed from: i */
    public static AtomicBoolean f9120i = new AtomicBoolean(false);

    /* renamed from: j */
    public static ArrayList<String> f9121j = new ArrayList<>();

    /* renamed from: c */
    public boolean f9122c = false;

    /* renamed from: d */
    public boolean f9123d = false;

    /* renamed from: e */
    public AsyncTaskC1751d f9124e = null;

    /* renamed from: f */
    public CLFragmentTabHost f9125f = null;

    /* renamed from: g */
    public View.OnClickListener f9126g = new ViewOnClickListenerC1748a();

    /* renamed from: h */
    public View.OnClickListener f9127h = new ViewOnClickListenerC1749b();

    /* renamed from: com.cyberlink.you.activity.StickerShopActivity$a */
    public class ViewOnClickListenerC1748a implements View.OnClickListener {
        public ViewOnClickListenerC1748a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (StickerShopActivity.this.f9123d) {
                Intent intent = new Intent();
                intent.putExtra("isChanged", StickerShopActivity.this.f9123d);
                StickerShopActivity.this.setResult(-1, intent);
            }
            StickerShopActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.StickerShopActivity$b */
    public class ViewOnClickListenerC1749b implements View.OnClickListener {
        public ViewOnClickListenerC1749b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            StickerShopActivity.this.startActivityForResult(new Intent(StickerShopActivity.this.getApplicationContext(), (Class<?>) StickerPackSettingActivity.class), 1);
        }
    }

    /* renamed from: com.cyberlink.you.activity.StickerShopActivity$c */
    public class AsyncTaskC1750c extends AsyncTask<Void, Void, Void> {
        public AsyncTaskC1750c() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            FriendsClient.m15643G0();
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r12) {
            super.onPostExecute(r12);
            StickerShopActivity.this.m10135S0();
        }
    }

    /* renamed from: com.cyberlink.you.activity.StickerShopActivity$d */
    public class AsyncTaskC1751d extends AsyncTask<Void, Void, Long> {
        public AsyncTaskC1751d() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Long doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            Log.v("StickerShopActivity", "checkNewSticker");
            return Long.valueOf(C5167n0.m20143a());
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onCancelled(Long l9) {
            Log.v("StickerShopActivity", "checkNewSticker cancel");
        }

        @Override // android.os.AsyncTask
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Long l9) {
            if (StickerShopActivity.this.f9122c) {
                Log.v("StickerShopActivity", "Destorying, checkNewSticker cancel");
                return;
            }
            boolean z8 = l9.longValue() > Globals.m7388i0().m7639x0();
            if (z8) {
                Globals.m7388i0().m7618t3(l9.longValue());
                Globals.m7388i0().m7406B3(true);
                Globals.m7388i0().m7651z3(true);
            }
            StickerShopActivity.f9120i.set(false);
            C1752e c1752e = StickerShopActivity.this.f9125f.getCurrentTab() == 0 ? (C1752e) StickerShopActivity.this.getSupportFragmentManager().mo1848e("Top") : StickerShopActivity.this.f9125f.getCurrentTab() == 1 ? (C1752e) StickerShopActivity.this.getSupportFragmentManager().mo1848e("New") : null;
            if (c1752e != null) {
                c1752e.m10148n(z8);
            }
            Log.v("StickerShopActivity", "checkNewSticker done");
        }

        public /* synthetic */ AsyncTaskC1751d(StickerShopActivity stickerShopActivity, ViewOnClickListenerC1748a viewOnClickListenerC1748a) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.StickerShopActivity$e */
    public static class C1752e extends Fragment {

        /* renamed from: i */
        public static View f9132i;

        /* renamed from: j */
        public static long f9133j;

        /* renamed from: b */
        public ListView f9134b;

        /* renamed from: c */
        public View f9135c;

        /* renamed from: d */
        public d f9136d;

        /* renamed from: e */
        public String f9137e;

        /* renamed from: f */
        public AbsListView.OnScrollListener f9138f = new a();

        /* renamed from: g */
        public C5164m0.c f9139g = new b();

        /* renamed from: h */
        public AdapterView.OnItemClickListener f9140h = new c();

        /* renamed from: com.cyberlink.you.activity.StickerShopActivity$e$a */
        public class a implements AbsListView.OnScrollListener {

            /* renamed from: a */
            public int f9141a;

            /* renamed from: b */
            public int f9142b;

            /* renamed from: c */
            public int f9143c;

            public a() {
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i9, int i10, int i11) {
                this.f9141a = i9;
                this.f9142b = i10;
                this.f9143c = i11;
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i9) {
                if (i9 == 0 && this.f9141a + this.f9142b == this.f9143c) {
                    Log.d("StickerShopActivity", "isBottom");
                }
                if (i9 == 0 && this.f9141a == 0) {
                    Log.d("StickerShopActivity", "isTop");
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.StickerShopActivity$e$b */
        public class b implements C5164m0.c {

            /* renamed from: com.cyberlink.you.activity.StickerShopActivity$e$b$a */
            public class a implements Runnable {

                /* renamed from: b */
                public final /* synthetic */ boolean f9146b;

                /* renamed from: c */
                public final /* synthetic */ String f9147c;

                public a(boolean z8, String str) {
                    this.f9146b = z8;
                    this.f9147c = str;
                }

                @Override // java.lang.Runnable
                public void run() {
                    List<StickerPackObj> listM20120o;
                    if (this.f9146b && this.f9147c.equals(C1752e.this.f9137e) && (listM20120o = C5164m0.m20108m().m20120o(this.f9147c)) != null) {
                        C1752e.this.f9136d.clear();
                        C1752e.this.f9136d.addAll(listM20120o);
                        if (C1752e.this.f9137e.equals("New")) {
                            Globals.m7388i0().m7531c4(true);
                            if (C1752e.f9132i != null) {
                                C1752e.f9132i.setVisibility(8);
                            }
                        }
                    }
                    C1752e.this.f9135c.setVisibility(8);
                    Log.v("StickerShopActivity", "UpdateStickerShopTask " + this.f9147c + " done");
                }
            }

            public b() {
            }

            @Override // p116k4.C5164m0.c
            /* renamed from: a */
            public void mo10150a(String str, boolean z8) {
                if (C1752e.this.getActivity() == null) {
                    return;
                }
                C1752e.this.getActivity().runOnUiThread(new a(z8, str));
            }
        }

        /* renamed from: com.cyberlink.you.activity.StickerShopActivity$e$c */
        public class c implements AdapterView.OnItemClickListener {
            public c() {
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
                StickerPackObj stickerPackObj = (StickerPackObj) C1752e.this.f9136d.getItem(i9);
                ArrayList<String> arrayListM7478R0 = Globals.m7388i0().m7478R0();
                StickerShopActivity.f9121j = arrayListM7478R0;
                boolean z8 = false;
                if (arrayListM7478R0 != null) {
                    boolean z9 = false;
                    for (int i10 = 0; i10 < StickerShopActivity.f9121j.size(); i10++) {
                        if (StickerShopActivity.f9121j.get(i10).equals(String.valueOf(stickerPackObj.m14803g()))) {
                            z9 = true;
                        }
                    }
                    z8 = z9;
                }
                Intent intent = new Intent(C1752e.this.getActivity(), (Class<?>) StickerShopDetailActivity.class);
                intent.putExtra("stickerPckObj", stickerPackObj);
                intent.putExtra("isPurchased", z8);
                C1752e.this.getActivity().startActivity(intent);
            }
        }

        /* renamed from: com.cyberlink.you.activity.StickerShopActivity$e$d */
        public class d extends ArrayAdapter<StickerPackObj> {

            /* renamed from: com.cyberlink.you.activity.StickerShopActivity$e$d$a */
            public class a {

                /* renamed from: a */
                public ImageView f9151a;

                /* renamed from: b */
                public View f9152b;

                /* renamed from: c */
                public TextView f9153c;

                /* renamed from: d */
                public TextView f9154d;

                /* renamed from: e */
                public TextView f9155e;

                /* renamed from: f */
                public View f9156f;

                /* renamed from: g */
                public View f9157g;

                public a() {
                }

                public /* synthetic */ a(d dVar, ViewOnClickListenerC1748a viewOnClickListenerC1748a) {
                    this();
                }
            }

            public d(Context context, int i9, List<StickerPackObj> list) {
                super(context, i9, list);
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int i9, View view, ViewGroup viewGroup) {
                a aVar;
                if (view == null) {
                    view = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.sticker_shop_item, viewGroup, false);
                    aVar = new a(this, null);
                    aVar.f9153c = (TextView) view.findViewById(R.id.auther);
                    aVar.f9154d = (TextView) view.findViewById(R.id.name);
                    aVar.f9155e = (TextView) view.findViewById(R.id.text);
                    aVar.f9151a = (ImageView) view.findViewById(R.id.cover);
                    aVar.f9152b = view.findViewById(R.id.toDetail);
                    aVar.f9156f = view.findViewById(R.id.newIcon);
                    aVar.f9157g = view.findViewById(R.id.animationIcon);
                    view.setTag(aVar);
                } else {
                    aVar = (a) view.getTag();
                }
                StickerPackObj stickerPackObj = (StickerPackObj) getItem(i9);
                if (stickerPackObj.m14812p().f13074b != null) {
                    LoadImageUtils.m16641z(getContext(), stickerPackObj, aVar.f9151a, true, true);
                }
                aVar.f9153c.setText(stickerPackObj.m14808l());
                if (stickerPackObj.m14804h() != null) {
                    aVar.f9154d.setText(stickerPackObj.m14804h());
                }
                if (stickerPackObj.m14806j().equals("Free")) {
                    aVar.f9155e.setText(C1752e.this.getResources().getString(R.string.free_tab));
                } else if (stickerPackObj.m14806j().equals("Purchase")) {
                    String strM14800d = stickerPackObj.m14800d();
                    if (strM14800d.equals("sticker_1_buck")) {
                        aVar.f9155e.setText(Globals.m7388i0().m7538e1());
                    } else if (strM14800d.equals("sticker_2_buck")) {
                        aVar.f9155e.setText(Globals.m7388i0().m7544f1());
                    } else {
                        aVar.f9155e.setText(C1752e.this.getResources().getString(R.string.purchase));
                    }
                } else if (stickerPackObj.m14806j().equals("Share")) {
                    aVar.f9155e.setText(C1752e.this.getResources().getString(R.string.menu_share));
                }
                aVar.f9152b.setTag(stickerPackObj);
                if (C1752e.this.f9137e.equals("New")) {
                    if (stickerPackObj.m14807k() > C1752e.f9133j) {
                        aVar.f9156f.setVisibility(0);
                    } else {
                        aVar.f9156f.setVisibility(8);
                    }
                }
                aVar.f9157g.setVisibility(stickerPackObj.m14805i().equals("AnimationPNG") ? 0 : 8);
                return view;
            }
        }

        /* renamed from: n */
        public void m10148n(boolean z8) {
            String str;
            Log.d("StickerShopActivity", "[onCheckNewStickerComplete] bHasNew=" + z8);
            if (!z8) {
                String str2 = this.f9137e;
                if (str2 == null || !str2.equals("Top")) {
                    String str3 = this.f9137e;
                    if (str3 != null && str3.equals("New")) {
                        z8 = Globals.m7388i0().m7413D0();
                    }
                } else {
                    z8 = Globals.m7388i0().m7418E0();
                }
            }
            d dVar = this.f9136d;
            if (dVar != null) {
                if (z8 || dVar.isEmpty() || C5164m0.m20108m().m20114g() || ((str = this.f9137e) != null && str.equals("Top") && C5164m0.m20108m().m20115h())) {
                    C5164m0.m20108m().m20110a(new C5164m0.d(this.f9137e, getActivity()));
                    if (this.f9136d.isEmpty()) {
                        this.f9135c.setVisibility(0);
                    }
                }
            }
        }

        /* renamed from: o */
        public final void m10149o(String str) {
            List<StickerPackObj> listM20120o = C5164m0.m20108m().m20120o(str);
            Log.e("StickerShopActivity", "stickerPackList=" + listM20120o.size());
            this.f9136d.clear();
            this.f9136d.addAll(listM20120o);
            if (this.f9137e.equals("New")) {
                Globals.m7388i0().m7531c4(true);
                View view = f9132i;
                if (view != null) {
                    view.setVisibility(8);
                }
            }
            if (StickerShopActivity.f9120i.get()) {
                return;
            }
            m10148n(false);
        }

        @Override // androidx.fragment.app.Fragment
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(R.layout.fragment_sticker_shop_list, viewGroup, false);
        }

        @Override // androidx.fragment.app.Fragment
        public void onDestroyView() {
            C5164m0.m20108m().m20123s(this.f9139g);
            super.onDestroyView();
        }

        @Override // androidx.fragment.app.Fragment
        public void onViewCreated(View view, Bundle bundle) {
            if (!Globals.m7388i0().m7512Y1()) {
                f9132i.setVisibility(0);
            }
            ListView listView = (ListView) view.findViewById(R.id.stickerShopList);
            this.f9134b = listView;
            listView.setOnScrollListener(this.f9138f);
            this.f9135c = view.findViewById(R.id.loading);
            d dVar = new d(getActivity(), R.layout.sticker_shop_item, new ArrayList());
            this.f9136d = dVar;
            this.f9134b.setAdapter((ListAdapter) dVar);
            this.f9134b.setOnItemClickListener(this.f9140h);
            String string = getArguments().getString("type");
            this.f9137e = string;
            C5164m0.m20108m().m20111d(this.f9139g);
            m10149o(string);
        }
    }

    /* renamed from: P0 */
    public final void m10132P0() {
        f9120i.set(true);
        AsyncTaskC1751d asyncTaskC1751d = new AsyncTaskC1751d(this, null);
        this.f9124e = asyncTaskC1751d;
        asyncTaskC1751d.executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: Q0 */
    public final View m10133Q0(String str) {
        getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        int i9 = (int) (r0.widthPixels / 2);
        View viewInflate = getLayoutInflater().inflate(R.layout.chat_album_fragment_selection, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.chat_album_fragment_item_text)).setText(str);
        viewInflate.setLayoutParams(new ViewGroup.LayoutParams(i9, (int) (i9 * 0.19444445f)));
        return viewInflate;
    }

    /* renamed from: R0 */
    public final void m10134R0() {
        long unused = C1752e.f9133j = Globals.m7388i0().m7639x0();
        m10132P0();
        ArrayList<String> arrayListM7478R0 = Globals.m7388i0().m7478R0();
        f9121j = arrayListM7478R0;
        if (arrayListM7478R0 != null && arrayListM7478R0.size() == 0) {
            Globals.m7388i0().m7579m1();
        }
        View unused2 = C1752e.f9132i = findViewById(R.id.tabsNewIcon);
        findViewById(R.id.close).setOnClickListener(this.f9126g);
        findViewById(R.id.setting).setOnClickListener(this.f9127h);
        CLFragmentTabHost cLFragmentTabHost = (CLFragmentTabHost) findViewById(R.id.tabhost);
        this.f9125f = cLFragmentTabHost;
        cLFragmentTabHost.m17293g(this, getSupportFragmentManager(), R.id.realtabcontent);
    }

    /* renamed from: S0 */
    public final void m10135S0() {
        Bundle bundle = new Bundle();
        bundle.putString("type", "Top");
        CLFragmentTabHost cLFragmentTabHost = this.f9125f;
        cLFragmentTabHost.m17287a(cLFragmentTabHost.newTabSpec("Top").setIndicator(m10133Q0(getString(R.string.top_tab))), C1752e.class, bundle);
        Bundle bundle2 = new Bundle();
        bundle2.putString("type", "New");
        CLFragmentTabHost cLFragmentTabHost2 = this.f9125f;
        cLFragmentTabHost2.m17287a(cLFragmentTabHost2.newTabSpec("New").setIndicator(m10133Q0(getString(R.string.new_tab))), C1752e.class, bundle2);
        if (Globals.m7388i0().m7521a2()) {
            this.f9125f.setCurrentTabByTag("New");
        } else {
            Globals.m7388i0().m7541e4(true);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        if (i9 == 1 && intent != null && -1 == i10 && intent.getBooleanExtra("isChanged", false)) {
            this.f9123d = true;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.f9123d) {
            Intent intent = new Intent();
            intent.putExtra("isChanged", this.f9123d);
            setResult(-1, intent);
        }
        super.onBackPressed();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("StickerShopActivity", "onCreate");
        this.f9122c = false;
        this.f9123d = false;
        setContentView(R.layout.activity_sticker_shop);
        if (Locale.getDefault().getLanguage().equals(C5164m0.m20108m().m20119n().getString("last_sticker_language", ""))) {
            m10134R0();
            m10135S0();
        } else {
            m10134R0();
            C5164m0.m20108m().m20117j();
            new AsyncTaskC1750c().executeOnExecutor(C6385v.f21554b, new Void[0]);
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.f9122c = true;
        AsyncTaskC1751d asyncTaskC1751d = this.f9124e;
        if (asyncTaskC1751d != null && asyncTaskC1751d.getStatus() != AsyncTask.Status.FINISHED) {
            this.f9124e.cancel(true);
        }
        View unused = C1752e.f9132i = null;
        findViewById(R.id.close).setOnClickListener(null);
        Log.d("StickerShopActivity", "onDestroy & unregister mLocaleChangeReceiver");
        super.onDestroy();
    }
}
