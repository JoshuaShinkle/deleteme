package com.cyberlink.you.activity.selectfromfriendgroup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.ListAdapter;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.SelectUsersActivity;
import com.cyberlink.you.activity.selectfromfriendgroup.C2455d;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.DialogC3133q;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p045d3.C4667b;
import p132m.C5305d;
import p201t3.C6300n;
import p209u2.C6385v;

/* renamed from: com.cyberlink.you.activity.selectfromfriendgroup.c */
/* loaded from: classes.dex */
public class C2454c extends AbstractC2456e {

    /* renamed from: e */
    public List<Friend> f11210e;

    /* renamed from: f */
    public C4667b f11211f;

    /* renamed from: g */
    public boolean f11212g;

    /* renamed from: h */
    public ProgressDialog f11213h;

    /* renamed from: i */
    public long f11214i;

    /* renamed from: j */
    public long f11215j;

    /* renamed from: l */
    public Group f11217l;

    /* renamed from: p */
    public AsyncTask<Void, ArrayList<Friend>, ArrayList<Friend>> f11221p;

    /* renamed from: q */
    public boolean f11222q;

    /* renamed from: k */
    public boolean f11216k = false;

    /* renamed from: m */
    public String f11218m = "";

    /* renamed from: n */
    public final C5305d<Map<Friend, Boolean>> f11219n = new C5305d<>();

    /* renamed from: o */
    public AdapterView.OnItemClickListener f11220o = new a();

    /* renamed from: com.cyberlink.you.activity.selectfromfriendgroup.c$a */
    public class a implements AdapterView.OnItemClickListener {
        public a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            if (C2454c.this.f11216k) {
                return;
            }
            Group item = C2454c.this.f11211f.getItem(i9);
            C2454c.this.f11216k = true;
            C2454c.this.m12603G(item);
            C2454c.this.f11214i = item.f13727n;
        }
    }

    /* renamed from: com.cyberlink.you.activity.selectfromfriendgroup.c$b */
    public class b extends AsyncTask<Void, ArrayList<Friend>, ArrayList<Friend>> {

        /* renamed from: a */
        public final /* synthetic */ Group f11224a;

        public b(Group group) {
            this.f11224a = group;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ArrayList<Friend> doInBackground(Void... voidArr) {
            ArrayList<Friend> arrayList = new ArrayList<>(this.f11224a.m15747c());
            ArrayList arrayList2 = new ArrayList();
            Iterator<Friend> it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(new C6300n(Long.valueOf(this.f11224a.f13727n), it.next()));
            }
            C2454c.this.m12601E(arrayList2);
            Collections.sort(arrayList, new Friend.C3041b());
            return arrayList;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(ArrayList<Friend> arrayList) {
            if (C2454c.this.f11213h != null && C2454c.this.f11213h.isShowing()) {
                C2454c.this.f11213h.dismiss();
            }
            C2454c.this.f11222q = false;
            Intent intent = new Intent(C2454c.this.getActivity(), (Class<?>) SelectUsersActivity.class);
            Bundle bundle = new Bundle();
            Globals.C1408b.m7655b().m7657d("INTENT_PREV_SELECTED_USERS_LIST", C2454c.this.m12599C(this.f11224a.f13727n));
            Globals.C1408b.m7655b().m7657d("INTENT_USER_LIST", arrayList);
            bundle.putString("page", C2454c.this.f11218m);
            if (C2454c.this.f11210e != null) {
                Globals.C1408b.m7655b().m7657d("INTENT_CANNOT_MODIFIED_USERS_LIST", new ArrayList(C2454c.this.f11210e));
            }
            intent.putExtras(bundle);
            C2454c.this.startActivityForResult(intent, 1);
        }
    }

    /* renamed from: B */
    public int m12598B(long j9) {
        int i9;
        synchronized (this.f11219n) {
            Map<Friend, Boolean> mapM20722e = this.f11219n.m20722e(j9);
            i9 = 0;
            if (mapM20722e != null) {
                int i10 = 0;
                for (Map.Entry<Friend, Boolean> entry : mapM20722e.entrySet()) {
                    Friend key = entry.getKey();
                    boolean zBooleanValue = entry.getValue().booleanValue();
                    if (key.f13645c != this.f11215j) {
                        List<Friend> list = this.f11210e;
                        if (!(list != null && list.contains(key)) && zBooleanValue) {
                            i10++;
                        }
                    }
                }
                i9 = i10;
            }
        }
        return i9;
    }

    /* renamed from: C */
    public final ArrayList<Friend> m12599C(long j9) {
        ArrayList<Friend> arrayList = new ArrayList<>();
        synchronized (this.f11219n) {
            Map<Friend, Boolean> mapM20722e = this.f11219n.m20722e(j9);
            if (mapM20722e != null) {
                for (Map.Entry<Friend, Boolean> entry : mapM20722e.entrySet()) {
                    Friend key = entry.getKey();
                    if (entry.getValue().booleanValue()) {
                        arrayList.add(key);
                    }
                }
            }
        }
        return arrayList;
    }

    /* renamed from: D */
    public final void m12600D() {
        Bundle arguments = getArguments();
        this.f11210e = m12619j();
        if (arguments != null) {
            this.f11217l = (Group) arguments.getParcelable("group");
            this.f11218m = arguments.getString("page", "");
        }
    }

    /* renamed from: E */
    public final void m12601E(List<C6300n> list) {
        Map<Friend, Boolean> mapM12611a = this.f11230b.m12611a();
        synchronized (this.f11219n) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            for (C6300n c6300n : list) {
                Friend friendM24109b = c6300n.m24109b();
                long jLongValue = c6300n.m24108a().longValue();
                Map<Friend, Boolean> mapM20722e = this.f11219n.m20722e(jLongValue);
                if (mapM20722e == null) {
                    mapM20722e = new HashMap<>();
                    this.f11219n.m20726j(jLongValue, mapM20722e);
                }
                if (mapM12611a.containsKey(friendM24109b)) {
                    mapM20722e.put(friendM24109b, mapM12611a.get(friendM24109b));
                } else {
                    mapM20722e.put(friendM24109b, Boolean.FALSE);
                }
            }
            Log.d("Group", "initGroupMembersCheckState cost time=" + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
        }
    }

    /* renamed from: F */
    public final void m12602F(List<Pair<Friend, Boolean>> list) {
        synchronized (this.f11219n) {
            int iM20730n = this.f11219n.m20730n();
            for (int i9 = 0; i9 < iM20730n; i9++) {
                Map<Friend, Boolean> mapM20722e = this.f11219n.m20722e(this.f11219n.m20725i(i9));
                if (mapM20722e != null) {
                    for (Pair<Friend, Boolean> pair : list) {
                        if (mapM20722e.get(pair.first) != null) {
                            mapM20722e.put((Friend) pair.first, (Boolean) pair.second);
                        }
                    }
                }
            }
        }
        C4667b c4667b = this.f11211f;
        if (c4667b != null) {
            c4667b.notifyDataSetChanged();
        }
    }

    /* renamed from: G */
    public final void m12603G(Group group) {
        this.f11213h = ProgressDialog.show(getActivity(), "", getString(R.string.loading), true);
        if (this.f11222q) {
            return;
        }
        this.f11222q = true;
        b bVar = new b(group);
        this.f11221p = bVar;
        bVar.executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: H */
    public final void m12604H(long j9) {
        synchronized (this.f11219n) {
            Map<Friend, Boolean> mapM20722e = this.f11219n.m20722e(j9);
            int iM20730n = this.f11219n.m20730n();
            if (mapM20722e != null) {
                for (Map.Entry<Friend, Boolean> entry : mapM20722e.entrySet()) {
                    Friend key = entry.getKey();
                    boolean zBooleanValue = entry.getValue().booleanValue();
                    for (int i9 = 0; i9 < iM20730n; i9++) {
                        Map<Friend, Boolean> mapM20722e2 = this.f11219n.m20722e(this.f11219n.m20725i(i9));
                        if (mapM20722e2.get(key) != null) {
                            mapM20722e2.put(key, Boolean.valueOf(zBooleanValue));
                        }
                    }
                }
            }
        }
    }

    /* renamed from: I */
    public final Map<Friend, Boolean> m12605I(long j9, List<Friend> list) {
        Map<Friend, Boolean> mapM20722e;
        synchronized (this.f11219n) {
            mapM20722e = this.f11219n.m20722e(j9);
            if (mapM20722e != null && list != null) {
                ArrayList arrayList = new ArrayList();
                for (Friend friend : mapM20722e.keySet()) {
                    if (friend.f13645c != this.f11215j) {
                        arrayList.add(friend);
                    }
                }
                m12606J(list, mapM20722e, arrayList);
            }
        }
        return mapM20722e;
    }

    /* renamed from: J */
    public final void m12606J(List<Friend> list, Map<Friend, Boolean> map, List<Friend> list2) {
        ArrayList arrayList = new ArrayList();
        for (Friend friend : list2) {
            boolean zContains = list.contains(friend);
            map.put(friend, Boolean.valueOf(zContains));
            arrayList.add(new Pair(friend, Boolean.valueOf(zContains)));
        }
        this.f11230b.m12615e(arrayList);
    }

    @Override // com.cyberlink.you.activity.selectfromfriendgroup.AbstractC2456e
    /* renamed from: g */
    public Filter mo12570g() {
        C4667b c4667b = this.f11211f;
        if (c4667b == null) {
            return null;
        }
        return c4667b.getFilter();
    }

    @Override // com.cyberlink.you.activity.selectfromfriendgroup.AbstractC2456e
    /* renamed from: h */
    public C2455d.a mo12571h() {
        return new C2455d.a() { // from class: d3.c
            @Override // com.cyberlink.you.activity.selectfromfriendgroup.C2455d.a
            /* renamed from: a */
            public final void mo12617a(List list) {
                this.f16353a.m12602F(list);
            }
        };
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        if (intent != null && i9 == 1) {
            Bundle extras = intent.getExtras();
            this.f11216k = false;
            if (extras == null) {
                return;
            }
            m12605I(this.f11214i, Globals.C1408b.m7655b().m7656c("INTENT_RESULT_SELECTED_USERS_LIST"));
            m12604H(this.f11214i);
            this.f11231c.invalidateViews();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        AsyncTask<Void, ArrayList<Friend>, ArrayList<Friend>> asyncTask = this.f11221p;
        if (asyncTask != null) {
            asyncTask.cancel(false);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        m12600D();
        synchronized (this.f11219n) {
            this.f11215j = Globals.m7388i0().m7568k1().longValue();
        }
        this.f11216k = false;
        if (!this.f11212g) {
            new c(this, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            this.f11231c.setAdapter((ListAdapter) this.f11211f);
            this.f11231c.setOnItemClickListener(this.f11220o);
        }
    }

    /* renamed from: com.cyberlink.you.activity.selectfromfriendgroup.c$c */
    public class c extends AsyncTask<Void, List<Group>, List<Group>> {

        /* renamed from: a */
        public final DialogC3133q f11226a;

        public c() {
            this.f11226a = new DialogC3133q.b(C2454c.this.getActivity()).m16411b();
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<Group> doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            List<Group> listM15083t = C2950b0.m14912k().m15083t("Circle");
            ArrayList arrayList = new ArrayList();
            for (Group group : listM15083t) {
                if (!group.m15751i() && (C2454c.this.f11217l == null || C2454c.this.f11217l.f13727n != group.f13727n)) {
                    arrayList.add(group);
                }
            }
            C2454c.this.m12601E(C2950b0.m14899A().m15027s());
            Collections.sort(arrayList, new Group.C3055b());
            Log.d("Group", "GetGroupsAsyncTask " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
            return arrayList;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<Group> list) {
            C2454c c2454c = C2454c.this;
            C2454c c2454c2 = C2454c.this;
            c2454c.f11211f = new C4667b(c2454c2, c2454c2.getActivity(), list);
            ((SelectFromFriendGroupActivity) C2454c.this.getActivity()).m12556p1();
            C2454c c2454c3 = C2454c.this;
            c2454c3.f11231c.setAdapter((ListAdapter) c2454c3.f11211f);
            C2454c c2454c4 = C2454c.this;
            c2454c4.f11231c.setOnItemClickListener(c2454c4.f11220o);
            C2454c.this.m12620k();
            C2454c.this.f11212g = true;
            this.f11226a.dismiss();
        }

        public /* synthetic */ c(C2454c c2454c, a aVar) {
            this();
        }
    }
}
