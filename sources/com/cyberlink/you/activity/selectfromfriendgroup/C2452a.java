package com.cyberlink.you.activity.selectfromfriendgroup;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.ListAdapter;
import com.cyberlink.you.activity.selectfromfriendgroup.C2453b;
import com.cyberlink.you.activity.selectfromfriendgroup.C2455d;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.p036ui.DialogC3133q;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import p209u2.C6385v;

/* renamed from: com.cyberlink.you.activity.selectfromfriendgroup.a */
/* loaded from: classes.dex */
public class C2452a extends AbstractC2456e {

    /* renamed from: e */
    public C2453b f11192e;

    /* renamed from: f */
    public boolean f11193f;

    /* renamed from: g */
    public AdapterView.OnItemClickListener f11194g = new a();

    /* renamed from: com.cyberlink.you.activity.selectfromfriendgroup.a$a */
    public class a implements AdapterView.OnItemClickListener {
        public a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            C2453b.c cVar = (C2453b.c) view.getTag();
            if (cVar.f11208c.isEnabled()) {
                boolean zIsChecked = cVar.f11208c.isChecked();
                C2452a.this.f11192e.m12580f(i9, !zIsChecked);
                C2452a c2452a = C2452a.this;
                c2452a.f11230b.m12616f(!zIsChecked, c2452a.f11192e.getItem(i9));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s */
    public /* synthetic */ void m12569s(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            this.f11192e.m12581g((Friend) pair.first, ((Boolean) pair.second).booleanValue());
        }
    }

    @Override // com.cyberlink.you.activity.selectfromfriendgroup.AbstractC2456e
    /* renamed from: g */
    public Filter mo12570g() {
        C2453b c2453b = this.f11192e;
        if (c2453b == null) {
            return null;
        }
        return c2453b.getFilter();
    }

    @Override // com.cyberlink.you.activity.selectfromfriendgroup.AbstractC2456e
    /* renamed from: h */
    public C2455d.a mo12571h() {
        return new C2455d.a() { // from class: d3.a
            @Override // com.cyberlink.you.activity.selectfromfriendgroup.C2455d.a
            /* renamed from: a */
            public final void mo12617a(List list) {
                this.f16341a.m12569s(list);
            }
        };
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        if (!this.f11193f) {
            new b(this, null).executeOnExecutor(C6385v.f21554b, new Void[0]);
        } else {
            this.f11231c.setAdapter((ListAdapter) this.f11192e);
            this.f11231c.setOnItemClickListener(this.f11194g);
        }
    }

    /* renamed from: r */
    public final void m12572r() {
        Bundle arguments = getArguments();
        List<Friend> listM12619j = m12619j();
        List<Friend> listM12618i = m12618i();
        if (arguments != null) {
            this.f11192e.m12579e(listM12619j);
            if (listM12618i != null) {
                Iterator<Friend> it = listM12618i.iterator();
                while (it.hasNext()) {
                    this.f11192e.m12581g(it.next(), true);
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.selectfromfriendgroup.a$b */
    public class b extends AsyncTask<Void, List<Friend>, List<Friend>> {

        /* renamed from: a */
        public final DialogC3133q f11196a;

        public b() {
            this.f11196a = new DialogC3133q.b(C2452a.this.getActivity()).m16411b();
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<Friend> doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            List<Friend> listM15026r = C2950b0.m14899A().m15026r();
            List<Friend> listM15030v = C2950b0.m14899A().m15030v();
            if (listM15030v != null && !listM15030v.isEmpty()) {
                listM15026r.addAll(listM15030v);
            }
            if (listM15026r != null) {
                Collections.sort(listM15026r, new Friend.C3041b());
            }
            return listM15026r;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<Friend> list) {
            C2452a c2452a = C2452a.this;
            C2452a c2452a2 = C2452a.this;
            c2452a.f11192e = new C2453b(c2452a2, c2452a2.getActivity(), list);
            ((SelectFromFriendGroupActivity) C2452a.this.getActivity()).m12556p1();
            C2452a c2452a3 = C2452a.this;
            c2452a3.f11231c.setAdapter((ListAdapter) c2452a3.f11192e);
            C2452a c2452a4 = C2452a.this;
            c2452a4.f11231c.setOnItemClickListener(c2452a4.f11194g);
            C2452a.this.m12620k();
            C2452a.this.f11193f = true;
            C2452a.this.m12572r();
            this.f11196a.dismiss();
        }

        public /* synthetic */ b(C2452a c2452a, a aVar) {
            this();
        }
    }
}
