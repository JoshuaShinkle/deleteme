package com.cyberlink.you.activity.webinar;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.C0475d;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.webinar.C2666e2;
import com.cyberlink.you.activity.webinar.ExpandableData;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.utility.CLUtility;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.p040bc.model.Live;
import com.perfectcorp.ycl.p040bc.model.Register;
import com.perfectcorp.ycl.p040bc.model.WatchHistoryList;
import com.perfectcorp.ycl.p040bc.model.network.NetworkLive;
import com.perfectcorp.ycl.p040bc.model.network.NetworkRegister;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import p116k4.C5179r0;
import p116k4.C5187v0;
import p209u2.AbstractC6381r;

/* loaded from: classes.dex */
public class MyWebinarActivity extends BaseActivity {

    /* renamed from: e */
    public C2666e2 f12021e;

    /* renamed from: f */
    public View f12022f;

    /* renamed from: g */
    public View f12023g;

    /* renamed from: h */
    public TextView f12024h;

    /* renamed from: i */
    public TextView f12025i;

    /* renamed from: j */
    public TextView f12026j;

    /* renamed from: k */
    public Button f12027k;

    /* renamed from: l */
    public String f12028l;

    /* renamed from: m */
    public DialogC3133q f12029m;

    /* renamed from: c */
    public final ArrayList<WatchHistoryList.LiveHistoryInfo> f12019c = new ArrayList<>();

    /* renamed from: d */
    public final ArrayList<Live> f12020d = new ArrayList<>();

    /* renamed from: n */
    public View.OnClickListener f12030n = new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.w1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f12220b.m13848d0(view);
        }
    };

    /* renamed from: o */
    public View.OnClickListener f12031o = new ViewOnClickListenerC2636c();

    /* renamed from: p */
    public int f12032p = 0;

    /* renamed from: com.cyberlink.you.activity.webinar.MyWebinarActivity$a */
    public class C2634a extends PromisedTask.AbstractC4504d<WatchHistoryList> {
        public C2634a() {
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onDone(WatchHistoryList watchHistoryList) {
            ArrayList<WatchHistoryList.LiveHistoryInfo> arrayList;
            if (watchHistoryList == null || (arrayList = watchHistoryList.results) == null || arrayList.size() == 0) {
                return;
            }
            WatchHistoryList.LiveHistoryInfo liveHistoryInfo = watchHistoryList.results.get(0);
            if (MyWebinarActivity.this.f12019c.contains(liveHistoryInfo)) {
                return;
            }
            MyWebinarActivity.this.f12019c.add(liveHistoryInfo);
            MyWebinarActivity.this.m13879p0();
            MyWebinarActivity.this.f12021e.m13936c(MyWebinarActivity.this.f12019c.indexOf(liveHistoryInfo), liveHistoryInfo);
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.MyWebinarActivity$b */
    public class C2635b extends PromisedTask.AbstractC4504d<Register.listUserRegisterLiveResult> {
        public C2635b() {
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onDone(Register.listUserRegisterLiveResult listuserregisterliveresult) {
            ArrayList<Live> arrayList;
            if (listuserregisterliveresult == null || (arrayList = listuserregisterliveresult.results) == null || arrayList.size() == 0) {
                return;
            }
            Live live = listuserregisterliveresult.results.get(0);
            if (MyWebinarActivity.this.f12020d.contains(live)) {
                return;
            }
            MyWebinarActivity.this.f12020d.add(live);
            MyWebinarActivity.this.m13880q0();
            MyWebinarActivity.this.f12021e.m13937d(MyWebinarActivity.this.f12020d.indexOf(live), live);
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.MyWebinarActivity$c */
    public class ViewOnClickListenerC2636c implements View.OnClickListener {
        public ViewOnClickListenerC2636c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws Resources.NotFoundException {
            ArrayList<WatchHistoryList.LiveHistoryInfo> arrayListM13942i = MyWebinarActivity.this.f12021e.m13942i();
            if (arrayListM13942i.size() > 0) {
                MyWebinarActivity.this.m13865J(arrayListM13942i);
            }
            MyWebinarActivity.this.m13876m0(false);
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.MyWebinarActivity$d */
    public class C2637d extends AbstractC6381r<Void, Integer> {
        public C2637d(Handler handler) {
            super(handler);
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Void r12) {
            MyWebinarActivity.this.m13879p0();
            MyWebinarActivity.this.m13867N();
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Integer num) {
            Log.e("MyWebinarActivity", "loadWebinarsHistory errorCode : " + num);
            if (MyWebinarActivity.this.m7364e()) {
                return;
            }
            C5187v0.m20267c(R.string.error_server_response);
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.MyWebinarActivity$e */
    public class C2638e extends PromisedTask.AbstractC4504d<WatchHistoryList> {

        /* renamed from: a */
        public final /* synthetic */ int f12037a;

        /* renamed from: b */
        public final /* synthetic */ String f12038b;

        /* renamed from: c */
        public final /* synthetic */ AbstractC6381r f12039c;

        public C2638e(int i9, String str, AbstractC6381r abstractC6381r) {
            this.f12037a = i9;
            this.f12038b = str;
            this.f12039c = abstractC6381r;
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onDone(WatchHistoryList watchHistoryList) {
            ArrayList<WatchHistoryList.LiveHistoryInfo> arrayList;
            if (MyWebinarActivity.this.m7364e()) {
                Log.e("MyWebinarActivity", "activity is destroyed, stop loadWebinarsHistory");
                return;
            }
            if (watchHistoryList != null && (this.f12037a + 1) * 30 < watchHistoryList.totalSize) {
                MyWebinarActivity.this.f12019c.addAll(watchHistoryList.results);
                MyWebinarActivity.this.m13873j0(this.f12038b, this.f12037a + 1, this.f12039c);
                return;
            }
            if (watchHistoryList != null && (arrayList = watchHistoryList.results) != null && arrayList.size() > 0) {
                MyWebinarActivity.this.f12019c.addAll(watchHistoryList.results);
            }
            this.f12039c.m24505c();
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            this.f12039c.m24508f(Integer.valueOf(i9));
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.MyWebinarActivity$f */
    public class C2639f extends AbstractC6381r<Void, Integer> {
        public C2639f(Handler handler) {
            super(handler);
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Void r12) {
            MyWebinarActivity.this.m13880q0();
            MyWebinarActivity.this.m13867N();
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Integer num) {
            Log.e("MyWebinarActivity", "loadWebinarsHistory errorCode : " + num);
            if (MyWebinarActivity.this.m7364e()) {
                return;
            }
            C5187v0.m20267c(R.string.error_server_response);
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.MyWebinarActivity$g */
    public class C2640g extends PromisedTask.AbstractC4504d<Register.listUserRegisterLiveResult> {

        /* renamed from: a */
        public final /* synthetic */ int f12042a;

        /* renamed from: b */
        public final /* synthetic */ String f12043b;

        /* renamed from: c */
        public final /* synthetic */ AbstractC6381r f12044c;

        public C2640g(int i9, String str, AbstractC6381r abstractC6381r) {
            this.f12042a = i9;
            this.f12043b = str;
            this.f12044c = abstractC6381r;
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onDone(Register.listUserRegisterLiveResult listuserregisterliveresult) {
            ArrayList<Live> arrayList;
            if (MyWebinarActivity.this.m7364e()) {
                Log.e("MyWebinarActivity", "activity is destroyed, stop loadWebinarsHistory");
                return;
            }
            if (listuserregisterliveresult != null && (this.f12042a + 1) * 30 < listuserregisterliveresult.totalSize) {
                MyWebinarActivity.this.f12020d.addAll(listuserregisterliveresult.results);
                MyWebinarActivity.this.m13873j0(this.f12043b, this.f12042a + 1, this.f12044c);
                return;
            }
            if (listuserregisterliveresult != null && (arrayList = listuserregisterliveresult.results) != null && arrayList.size() > 0) {
                MyWebinarActivity.this.f12020d.addAll(listuserregisterliveresult.results);
            }
            this.f12044c.m24505c();
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            this.f12044c.m24508f(Integer.valueOf(i9));
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.MyWebinarActivity$h */
    public class C2641h extends PromisedTask.AbstractC4504d {

        /* renamed from: a */
        public final /* synthetic */ ArrayList f12046a;

        /* renamed from: b */
        public final /* synthetic */ DialogC3133q f12047b;

        public C2641h(ArrayList arrayList, DialogC3133q dialogC3133q) {
            this.f12046a = arrayList;
            this.f12047b = dialogC3133q;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m13894c(ArrayList arrayList) {
            if (MyWebinarActivity.this.m7364e()) {
                Log.e("MyWebinarActivity", "[deleteHistory] activity is destroyed, do nothing ");
            } else {
                MyWebinarActivity.this.f12021e.m13940g(true, arrayList);
                m13896e();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m13895d(int i9, DialogC3133q dialogC3133q, ArrayList arrayList) {
            Log.e("MyWebinarActivity", "deleteHistory errorCode : " + i9);
            if (MyWebinarActivity.this.m7364e()) {
                return;
            }
            dialogC3133q.dismiss();
            MyWebinarActivity.this.f12021e.m13940g(false, arrayList);
            C5187v0.m20267c(R.string.error_server_response);
        }

        /* renamed from: e */
        public final void m13896e() {
            this.f12047b.dismiss();
            MyWebinarActivity.this.f12019c.removeAll(this.f12046a);
            MyWebinarActivity.this.f12021e.notifyDataSetChanged();
            MyWebinarActivity.this.m13882s0();
            MyWebinarActivity.this.m13878o0();
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        public void onDone(Object obj) {
            MyWebinarActivity myWebinarActivity = MyWebinarActivity.this;
            final ArrayList arrayList = this.f12046a;
            myWebinarActivity.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.webinar.d2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f12115b.m13894c(arrayList);
                }
            });
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(final int i9) {
            super.onError(i9);
            MyWebinarActivity myWebinarActivity = MyWebinarActivity.this;
            final DialogC3133q dialogC3133q = this.f12047b;
            final ArrayList arrayList = this.f12046a;
            myWebinarActivity.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.webinar.c2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f12110b.m13895d(i9, dialogC3133q, arrayList);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V */
    public /* synthetic */ void m13842V(View view) {
        finish();
    }

    /* renamed from: X */
    public static /* synthetic */ void m13843X(RecyclerView recyclerView, int i9) {
        ((LinearLayoutManager) recyclerView.getLayoutManager()).m2323z2(i9, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Y */
    public /* synthetic */ void m13844Y(DialogC3133q dialogC3133q) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Z */
    public /* synthetic */ void m13845Z(View view) throws Resources.NotFoundException {
        m13876m0(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b0 */
    public /* synthetic */ void m13846b0(Dialog dialog, View view) throws Resources.NotFoundException {
        dialog.dismiss();
        m13876m0(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d0 */
    public /* synthetic */ void m13848d0(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_webinar_history_more);
        dialog.findViewById(R.id.deleteRecords).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.a2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) throws Resources.NotFoundException {
                this.f12097b.m13846b0(dialog, view2);
            }
        });
        dialog.findViewById(R.id.DialogCancel).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.b2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                dialog.dismiss();
            }
        });
        CLUtility.m16578q2(this, dialog);
        dialog.show();
    }

    /* renamed from: e0 */
    public static /* synthetic */ int m13849e0(WatchHistoryList.LiveHistoryInfo liveHistoryInfo, WatchHistoryList.LiveHistoryInfo liveHistoryInfo2) {
        Date dateM13954f = C2698m2.m13954f(liveHistoryInfo.publishDate);
        Date dateM13954f2 = C2698m2.m13954f(liveHistoryInfo2.publishDate);
        return (dateM13954f == null || dateM13954f2 == null) ? liveHistoryInfo2.title.compareTo(liveHistoryInfo.title) : dateM13954f2.compareTo(dateM13954f);
    }

    /* renamed from: g0 */
    public static /* synthetic */ int m13850g0(Live live, Live live2) {
        Date dateM13954f = C2698m2.m13954f(live.scheduleInterval.startDate);
        Date dateM13954f2 = C2698m2.m13954f(live2.scheduleInterval.startDate);
        return (dateM13954f == null || dateM13954f2 == null) ? live2.title.compareTo(live.title) : dateM13954f2.compareTo(dateM13954f);
    }

    /* renamed from: J */
    public final void m13865J(ArrayList<WatchHistoryList.LiveHistoryInfo> arrayList) {
        DialogC3133q dialogC3133qM16411b = new DialogC3133q.b(this).m16411b();
        Log.d("MyWebinarActivity", "deleteHistoryInfos = " + arrayList);
        NetworkLive.deleteWatchHistory(this.f12028l, arrayList).done(new C2641h(arrayList, dialogC3133qM16411b));
    }

    /* renamed from: L */
    public final void m13866L() {
        findViewById(R.id.WebinarHistorBackBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.v1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12217b.m13842V(view);
            }
        });
        View viewFindViewById = findViewById(R.id.more);
        this.f12022f = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f12030n);
    }

    /* renamed from: N */
    public final void m13867N() {
        int i9 = this.f12032p + 1;
        this.f12032p = i9;
        if (i9 < 2) {
            return;
        }
        this.f12029m.dismiss();
        ArrayList arrayList = new ArrayList();
        if (this.f12020d.size() > 0) {
            ExpandableData expandableData = new ExpandableData(getString(R.string.clmw_upcoming_webinars_title), ExpandableData.DataType.REGISTER);
            ArrayList<Live> arrayListM13496e = expandableData.m13496e();
            arrayList.add(expandableData);
            Iterator<Live> it = this.f12020d.iterator();
            while (it.hasNext()) {
                Live next = it.next();
                arrayListM13496e.add(next);
                arrayList.add(new ExpandableData(next));
            }
        }
        if (this.f12019c.size() > 0) {
            ExpandableData expandableData2 = new ExpandableData(getString(R.string.clmw_recorded_webinars_title), ExpandableData.DataType.HISTORY);
            ArrayList<WatchHistoryList.LiveHistoryInfo> arrayListM13495d = expandableData2.m13495d();
            arrayList.add(expandableData2);
            Iterator<WatchHistoryList.LiveHistoryInfo> it2 = this.f12019c.iterator();
            while (it2.hasNext()) {
                WatchHistoryList.LiveHistoryInfo next2 = it2.next();
                arrayListM13495d.add(next2);
                arrayList.add(new ExpandableData(next2));
            }
        }
        if (!m13878o0()) {
            final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.WebinarRecyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            RecyclerView.AbstractC0451l itemAnimator = recyclerView.getItemAnimator();
            if (itemAnimator instanceof C0475d) {
                ((C0475d) itemAnimator).m2905Q(false);
            }
            C2666e2 c2666e2 = new C2666e2(arrayList, new C2666e2.b() { // from class: com.cyberlink.you.activity.webinar.z1
                @Override // com.cyberlink.you.activity.webinar.C2666e2.b
                /* renamed from: a */
                public final void mo13947a(int i10) {
                    MyWebinarActivity.m13843X(recyclerView, i10);
                }
            }, this);
            this.f12021e = c2666e2;
            recyclerView.setAdapter(c2666e2);
        }
        m13882s0();
        this.f12032p = 0;
    }

    /* renamed from: O */
    public final void m13868O() {
        this.f12026j = (TextView) findViewById(R.id.noRecordsTextView);
        Button button = (Button) findViewById(R.id.delete);
        this.f12027k = button;
        button.setOnClickListener(this.f12031o);
        this.f12029m = new DialogC3133q.b(this).m16412c(true).m16414e(new DialogC3133q.c() { // from class: com.cyberlink.you.activity.webinar.y1
            @Override // com.cyberlink.you.p036ui.DialogC3133q.c
            /* renamed from: a */
            public final void mo10190a(DialogC3133q dialogC3133q) {
                this.f12227a.m13844Y(dialogC3133q);
            }
        }).m16410a();
    }

    /* renamed from: P */
    public final void m13869P() {
        this.f12023g = findViewById(R.id.toolbar);
        this.f12024h = (TextView) findViewById(R.id.numberOfSelectedItem);
        TextView textView = (TextView) findViewById(R.id.cancel);
        this.f12025i = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.x1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Resources.NotFoundException {
                this.f12224b.m13845Z(view);
            }
        });
    }

    /* renamed from: Q */
    public final void m13870Q() {
        this.f12020d.clear();
        this.f12019c.clear();
        this.f12028l = Globals.m7388i0().m7506X();
        this.f12029m.show();
        m13874k0(this.f12028l);
        m13872i0(this.f12028l);
    }

    /* renamed from: T */
    public void m13871T(String str) {
        C2698m2.m13955g(Globals.m7388i0().m7506X(), NetworkLive.EMPTY_PARAMETER.longValue(), str, this, "MyWebinarActivity", this.f12029m, false, null);
    }

    /* renamed from: i0 */
    public final void m13872i0(String str) {
        m13873j0(str, 0, new C2637d(new Handler(Looper.getMainLooper())));
    }

    /* renamed from: j0 */
    public final void m13873j0(String str, int i9, AbstractC6381r<Void, Integer> abstractC6381r) {
        NetworkLive.listWatchHistory(str, i9, 30).done(new C2638e(i9, str, abstractC6381r));
    }

    /* renamed from: k0 */
    public final void m13874k0(String str) {
        m13875l0(str, 0, new C2639f(new Handler(Looper.getMainLooper())));
    }

    /* renamed from: l0 */
    public final void m13875l0(String str, int i9, AbstractC6381r<Void, Integer> abstractC6381r) {
        NetworkRegister.listRegisterLives(str, i9, 30).done(new C2640g(i9, str, abstractC6381r));
    }

    /* renamed from: m0 */
    public final void m13876m0(boolean z8) throws Resources.NotFoundException {
        this.f12021e.m13946m(z8);
        if (!z8) {
            this.f12023g.setVisibility(8);
            this.f12027k.setVisibility(8);
            return;
        }
        this.f12023g.setVisibility(0);
        this.f12027k.setVisibility(0);
        this.f12027k.setEnabled(false);
        this.f12027k.setText(R.string.delete_btn);
        this.f12024h.setText(new SpannableString(String.format(getResources().getQuantityString(R.plurals.record_selected, 0), 0)));
        C5179r0.m20247b(this.f12024h, 1);
    }

    /* renamed from: n0 */
    public void m13877n0(int i9) {
        if (i9 <= 0) {
            this.f12027k.setEnabled(false);
            this.f12027k.setText(getString(R.string.delete_btn));
            this.f12024h.setText(new SpannableString(String.format(getResources().getQuantityString(R.plurals.record_selected, i9), Integer.valueOf(i9))));
            C5179r0.m20247b(this.f12024h, 1);
            return;
        }
        this.f12027k.setEnabled(true);
        this.f12027k.setText(getString(R.string.delete_btn) + " (" + i9 + ")");
        this.f12024h.setText(new SpannableString(String.format(getResources().getQuantityString(R.plurals.record_selected, i9), Integer.valueOf(i9))));
        C5179r0.m20247b(this.f12024h, 1);
    }

    /* renamed from: o0 */
    public final boolean m13878o0() {
        if (this.f12020d.size() + this.f12019c.size() != 0) {
            return false;
        }
        this.f12026j.setVisibility(0);
        return true;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.f12023g.getVisibility() == 0) {
            this.f12025i.callOnClick();
        } else {
            finish();
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_webinar_history);
        m13866L();
        m13869P();
        m13868O();
        m13870Q();
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
        m13881r0();
        m13883u0();
        m13882s0();
    }

    /* renamed from: p0 */
    public final void m13879p0() {
        Collections.sort(this.f12019c, new Comparator() { // from class: com.cyberlink.you.activity.webinar.u1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return MyWebinarActivity.m13849e0((WatchHistoryList.LiveHistoryInfo) obj, (WatchHistoryList.LiveHistoryInfo) obj2);
            }
        });
    }

    /* renamed from: q0 */
    public final void m13880q0() {
        Collections.sort(this.f12020d, new Comparator() { // from class: com.cyberlink.you.activity.webinar.t1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return MyWebinarActivity.m13850g0((Live) obj, (Live) obj2);
            }
        });
    }

    /* renamed from: r0 */
    public final void m13881r0() {
        NetworkLive.listWatchHistory(this.f12028l, 0, 1).done(new C2634a());
    }

    /* renamed from: s0 */
    public final void m13882s0() {
        this.f12022f.setVisibility(this.f12019c.size() > 0 ? 0 : 8);
    }

    /* renamed from: u0 */
    public final void m13883u0() {
        NetworkRegister.listRegisterLives(this.f12028l, 0, 1).done(new C2635b());
    }
}
