package com.cyberlink.you.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.C0487p;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.p036ui.DialogC3133q;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5154j;
import p116k4.C5187v0;
import p173q2.C6127a;

/* loaded from: classes.dex */
public class SuUsersListActivity extends BaseActivity {

    /* renamed from: c */
    public C1770i f9197c;

    /* renamed from: com.cyberlink.you.activity.SuUsersListActivity$a */
    public class ViewOnClickListenerC1762a implements View.OnClickListener {
        public ViewOnClickListenerC1762a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SuUsersListActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SuUsersListActivity$b */
    public class C1763b implements InterfaceC1769h {
        public C1763b() {
        }

        @Override // com.cyberlink.you.activity.SuUsersListActivity.InterfaceC1769h
        /* renamed from: a */
        public void mo10189a(Friend friend) {
            SuUsersListActivity.this.m10188w(friend);
        }
    }

    /* renamed from: com.cyberlink.you.activity.SuUsersListActivity$c */
    public class C1764c implements DialogC3133q.c {
        public C1764c() {
        }

        @Override // com.cyberlink.you.p036ui.DialogC3133q.c
        /* renamed from: a */
        public void mo10190a(DialogC3133q dialogC3133q) {
            SuUsersListActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SuUsersListActivity$d */
    public class AsyncTaskC1765d extends AsyncTask<Void, Void, List<Friend>> {

        /* renamed from: a */
        public final /* synthetic */ DialogC3133q f9201a;

        public AsyncTaskC1765d(DialogC3133q dialogC3133q) {
            this.f9201a = dialogC3133q;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<Friend> doInBackground(Void... voidArr) {
            List<Friend> listM15025q = C2950b0.m14899A().m15025q();
            long jLongValue = Globals.m7388i0().m7568k1().longValue();
            Iterator<Friend> it = listM15025q.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Friend next = it.next();
                if (next.f13645c == jLongValue) {
                    listM15025q.remove(next);
                    break;
                }
            }
            return listM15025q;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<Friend> list) {
            if (SuUsersListActivity.this.m7364e()) {
                return;
            }
            SuUsersListActivity.this.f9197c.f9214c.m2908c(list);
            this.f9201a.dismiss();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SuUsersListActivity$e */
    public class ViewOnClickListenerC1766e implements View.OnClickListener {

        /* renamed from: b */
        public final /* synthetic */ Dialog f9203b;

        public ViewOnClickListenerC1766e(Dialog dialog) {
            this.f9203b = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f9203b.dismiss();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SuUsersListActivity$f */
    public class ViewOnClickListenerC1767f implements View.OnClickListener {

        /* renamed from: b */
        public final /* synthetic */ View f9205b;

        /* renamed from: c */
        public final /* synthetic */ Friend f9206c;

        /* renamed from: d */
        public final /* synthetic */ Dialog f9207d;

        public ViewOnClickListenerC1767f(View view, Friend friend, Dialog dialog) {
            this.f9205b = view;
            this.f9206c = friend;
            this.f9207d = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws JSONException, NumberFormatException {
            int i9;
            ArrayList arrayList = new ArrayList();
            if (SuUsersListActivity.m10181s(this.f9205b, R.id.moduleU)) {
                arrayList.add("U");
            }
            if (SuUsersListActivity.m10181s(this.f9205b, R.id.moduleM)) {
                arrayList.add("M");
            }
            if (SuUsersListActivity.m10181s(this.f9205b, R.id.moduleW)) {
                arrayList.add("W");
            }
            if (arrayList.isEmpty()) {
                C5187v0.m20268d("Please select at least one module.");
                return;
            }
            try {
                i9 = Integer.parseInt((String) this.f9205b.findViewById(((RadioGroup) this.f9205b.findViewById(R.id.radioGroupLogDays)).getCheckedRadioButtonId()).getTag());
            } catch (Exception e9) {
                C5154j.m20076j(e9);
                i9 = 1;
            }
            SuUsersListActivity.this.m10187v(this.f9206c.f13648f, i9, TextUtils.join(",", arrayList));
            this.f9207d.dismiss();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SuUsersListActivity$g */
    public class C1768g implements XMPPManager.InterfaceC2873x {

        /* renamed from: a */
        public final /* synthetic */ String f9209a;

        /* renamed from: b */
        public final /* synthetic */ DialogC3133q f9210b;

        public C1768g(String str, DialogC3133q dialogC3133q) {
            this.f9209a = str;
            this.f9210b = dialogC3133q;
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2873x
        /* renamed from: a */
        public void mo5816a() {
            C5187v0.m20268d("Event [" + this.f9209a + "] sent failed.");
            this.f9210b.dismiss();
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2873x
        public void onSuccess() {
            C5187v0.m20268d("Event [" + this.f9209a + "] sent successfully.");
            this.f9210b.dismiss();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SuUsersListActivity$h */
    public interface InterfaceC1769h {
        /* renamed from: a */
        void mo10189a(Friend friend);
    }

    /* renamed from: com.cyberlink.you.activity.SuUsersListActivity$i */
    public static class C1770i extends RecyclerView.AbstractC0446g<C1771j> {

        /* renamed from: a */
        public final Context f9212a;

        /* renamed from: b */
        public final InterfaceC1769h f9213b;

        /* renamed from: c */
        public final C0487p<Friend> f9214c = m10195e();

        /* renamed from: com.cyberlink.you.activity.SuUsersListActivity$i$a */
        public class a extends C0487p.b<Friend> {

            /* renamed from: b */
            public final Collator f9215b = Collator.getInstance();

            public a() {
            }

            @Override // androidx.recyclerview.widget.InterfaceC0481j
            /* renamed from: a */
            public void mo2761a(int i9, int i10) {
                C1770i.this.notifyItemMoved(i9, i10);
            }

            @Override // androidx.recyclerview.widget.InterfaceC0481j
            /* renamed from: b */
            public void mo2762b(int i9, int i10) {
                C1770i.this.notifyItemRangeInserted(i9, i10);
            }

            @Override // androidx.recyclerview.widget.InterfaceC0481j
            /* renamed from: c */
            public void mo2763c(int i9, int i10) {
                C1770i.this.notifyItemRangeRemoved(i9, i10);
            }

            @Override // androidx.recyclerview.widget.C0487p.b
            /* renamed from: h */
            public void mo2933h(int i9, int i10) {
                C1770i.this.notifyItemRangeChanged(i9, i10);
            }

            @Override // androidx.recyclerview.widget.C0487p.b
            /* renamed from: i, reason: merged with bridge method [inline-methods] */
            public boolean mo2930e(Friend friend, Friend friend2) {
                return TextUtils.equals(friend.f13647e, friend2.f13647e) && TextUtils.equals(friend.m15621b(), friend2.m15621b()) && friend.f13657o == friend2.f13657o;
            }

            @Override // androidx.recyclerview.widget.C0487p.b
            /* renamed from: j, reason: merged with bridge method [inline-methods] */
            public boolean mo2931f(Friend friend, Friend friend2) {
                return friend.f13645c == friend2.f13645c;
            }

            @Override // androidx.recyclerview.widget.C0487p.b, java.util.Comparator
            /* renamed from: k, reason: merged with bridge method [inline-methods] */
            public int compare(Friend friend, Friend friend2) {
                return this.f9215b.compare(friend.m15621b(), friend2.m15621b());
            }
        }

        /* renamed from: com.cyberlink.you.activity.SuUsersListActivity$i$b */
        public class b implements View.OnClickListener {

            /* renamed from: b */
            public final /* synthetic */ C1771j f9217b;

            public b(C1771j c1771j) {
                this.f9217b = c1771j;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Friend friend;
                int adapterPosition = this.f9217b.getAdapterPosition();
                if (adapterPosition == -1 || (friend = (Friend) C1770i.this.f9214c.m2918m(adapterPosition)) == null) {
                    return;
                }
                C1770i.this.f9213b.mo10189a(friend);
            }
        }

        public C1770i(Context context, InterfaceC1769h interfaceC1769h) {
            this.f9212a = context;
            this.f9213b = interfaceC1769h;
        }

        /* renamed from: e */
        public final C0487p<Friend> m10195e() {
            return new C0487p<>(Friend.class, new a());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(C1771j c1771j, int i9) {
            Friend friendM2918m = this.f9214c.m2918m(i9);
            C6127a.m23469j(this.f9212a, c1771j.f9219b, friendM2918m);
            c1771j.f9220c.setText(friendM2918m.m15621b());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public C1771j onCreateViewHolder(ViewGroup viewGroup, int i9) {
            View viewInflate = LayoutInflater.from(this.f9212a).inflate(R.layout.view_su_user, viewGroup, false);
            C1771j c1771j = new C1771j(viewInflate);
            m10198h(viewInflate, c1771j);
            return c1771j;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
        public int getItemCount() {
            return this.f9214c.m2926u();
        }

        /* renamed from: h */
        public final void m10198h(View view, C1771j c1771j) {
            view.setOnClickListener(new b(c1771j));
        }
    }

    /* renamed from: com.cyberlink.you.activity.SuUsersListActivity$j */
    public static class C1771j extends RecyclerView.AbstractC0442c0 {

        /* renamed from: b */
        public final ImageView f9219b;

        /* renamed from: c */
        public final TextView f9220c;

        public C1771j(View view) {
            super(view);
            this.f9219b = (ImageView) view.findViewById(R.id.userAvatar);
            this.f9220c = (TextView) view.findViewById(R.id.userName);
        }
    }

    /* renamed from: s */
    public static boolean m10181s(View view, int i9) {
        return ((CheckBox) view.findViewById(i9)).isChecked();
    }

    /* renamed from: i */
    public final void m10182i() {
        m10184q();
        m10185r();
    }

    /* renamed from: o */
    public final void m10183o() {
        new AsyncTaskC1765d(new DialogC3133q.b(this).m16412c(true).m16414e(new C1764c()).m16411b()).executeOnExecutor(Executors.newSingleThreadExecutor(), new Void[0]);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_su_users_list);
        m10182i();
    }

    /* renamed from: q */
    public final void m10184q() {
        findViewById(R.id.btnBack).setOnClickListener(new ViewOnClickListenerC1762a());
    }

    /* renamed from: r */
    public final void m10185r() {
        this.f9197c = new C1770i(getApplicationContext(), new C1763b());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.f9197c);
        m10183o();
    }

    /* renamed from: u */
    public final void m10186u(String str, JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString("eventType");
        XMPPManager.m14184g0().m14241e1(C2925v.m14593f("Dual", str, C2925v.m14558I("-1", MessageObj.MessageType.SUPERVISE, jSONObject.toString(), 0, null)), new C1768g(string, new DialogC3133q.b(this).m16411b()));
    }

    /* renamed from: v */
    public final void m10187v(String str, int i9, String str2) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("eventType", "supervise.log.request");
            jSONObject.put("days", String.valueOf(i9));
            jSONObject.put("modules", str2);
            m10186u(str, jSONObject);
        } catch (Exception e9) {
            C5154j.m20076j(e9);
        }
    }

    /* renamed from: w */
    public final void m10188w(Friend friend) {
        Dialog dialogM16384c = C3123g.m16384c(this);
        View viewInflate = getLayoutInflater().inflate(R.layout.view_su_log_opt, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.suTargetName)).setText("To: " + friend.m15621b());
        viewInflate.findViewById(R.id.btnCancel).setOnClickListener(new ViewOnClickListenerC1766e(dialogM16384c));
        viewInflate.findViewById(R.id.btnOK).setOnClickListener(new ViewOnClickListenerC1767f(viewInflate, friend, dialogM16384c));
        dialogM16384c.setContentView(viewInflate);
        dialogM16384c.show();
    }
}
