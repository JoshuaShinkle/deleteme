package p210u3;

import android.content.Context;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.p033u.glide.MediaLoader;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import java.util.ArrayList;
import java.util.List;
import p116k4.C5179r0;

/* renamed from: u3.a0 */
/* loaded from: classes.dex */
public class C6391a0 extends ArrayAdapter<C2973l0> {

    /* renamed from: j */
    public static final Object f21572j = new Object();

    /* renamed from: b */
    public final ArrayList<C2973l0> f21573b;

    /* renamed from: c */
    public SparseBooleanArray f21574c;

    /* renamed from: d */
    public Context f21575d;

    /* renamed from: e */
    public boolean f21576e;

    /* renamed from: f */
    public boolean f21577f;

    /* renamed from: g */
    public TextView f21578g;

    /* renamed from: h */
    public c f21579h;

    /* renamed from: i */
    public View.OnClickListener f21580i;

    /* renamed from: u3.a0$a */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iIntValue = ((Integer) view.getTag()).intValue();
            if (C6391a0.this.f21574c.get(iIntValue)) {
                C6391a0.this.f21574c.put(iIntValue, false);
                view.setSelected(false);
            } else {
                C6391a0.this.f21574c.put(iIntValue, true);
                view.setSelected(true);
            }
            C6391a0.this.m24549h();
        }
    }

    /* renamed from: u3.a0$b */
    public class b extends AsyncTask<Void, Void, List<C2973l0>> {

        /* renamed from: a */
        public final /* synthetic */ String f21582a;

        /* renamed from: b */
        public final /* synthetic */ d f21583b;

        public b(String str, d dVar) {
            this.f21582a = str;
            this.f21583b = dVar;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<C2973l0> doInBackground(Void... voidArr) {
            return C6391a0.this.f21577f ? C2950b0.m14914m().m14692A(this.f21582a, String.valueOf(Globals.m7388i0().m7568k1()), "ASC", 10000) : C2950b0.m14914m().m14729z(this.f21582a, "ASC", 10000);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<C2973l0> list) {
            synchronized (C6391a0.f21572j) {
                C6391a0.this.clear();
                if (list != null) {
                    C6391a0.this.addAll(list);
                }
                d dVar = this.f21583b;
                if (dVar != null) {
                    dVar.mo9346a(C6391a0.this.getCount());
                }
            }
        }
    }

    /* renamed from: u3.a0$c */
    public interface c {
        /* renamed from: a */
        void mo9338a(int i9);
    }

    /* renamed from: u3.a0$d */
    public interface d {
        /* renamed from: a */
        void mo9346a(int i9);
    }

    /* renamed from: u3.a0$e */
    public class e {

        /* renamed from: a */
        public ImageView f21585a;

        /* renamed from: b */
        public View f21586b;

        /* renamed from: c */
        public TextView f21587c;

        /* renamed from: d */
        public View f21588d;

        public e() {
        }

        public /* synthetic */ e(C6391a0 c6391a0, a aVar) {
            this();
        }
    }

    public C6391a0(Context context, int i9, ArrayList<C2973l0> arrayList) {
        super(context, i9, arrayList);
        this.f21578g = null;
        this.f21580i = new a();
        this.f21573b = arrayList;
        this.f21575d = context;
        this.f21576e = false;
        this.f21577f = false;
        this.f21574c = new SparseBooleanArray();
    }

    /* renamed from: e */
    public boolean m24546e(int i9) {
        return this.f21574c.get(i9);
    }

    /* renamed from: f */
    public long[] m24547f() {
        List<C2973l0> listM24548g = m24548g();
        long[] jArr = new long[listM24548g.size()];
        for (int i9 = 0; i9 < listM24548g.size(); i9++) {
            jArr[i9] = listM24548g.get(i9).m15144p();
        }
        return jArr;
    }

    /* renamed from: g */
    public List<C2973l0> m24548g() {
        ArrayList arrayList = new ArrayList();
        int size = this.f21574c.size();
        for (int i9 = 0; i9 < size; i9++) {
            int iKeyAt = this.f21574c.keyAt(i9);
            if (this.f21574c.get(iKeyAt)) {
                arrayList.add((C2973l0) getItem(iKeyAt));
            }
        }
        return arrayList;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        e eVar;
        if (view == null) {
            view = ((LayoutInflater) this.f21575d.getSystemService("layout_inflater")).inflate(R.layout.view_item_group_photo, viewGroup, false);
            eVar = new e(this, null);
            eVar.f21585a = (ImageView) view.findViewById(R.id.photoView);
            View viewFindViewById = view.findViewById(R.id.selectBtn);
            eVar.f21586b = viewFindViewById;
            viewFindViewById.setOnClickListener(this.f21580i);
            eVar.f21587c = (TextView) view.findViewById(R.id.commentCount);
            eVar.f21588d = view.findViewById(R.id.commentMedia);
            view.setTag(eVar);
        } else {
            eVar = (e) view.getTag();
        }
        C2973l0 c2973l0 = this.f21573b.get(i9);
        MediaLoader.m7156o(this.f21575d, eVar.f21585a, c2973l0, MediaLoader.Option.THUMBNAIL);
        eVar.f21586b.setTag(Integer.valueOf(i9));
        if (this.f21576e) {
            eVar.f21586b.setVisibility(0);
            if (Boolean.valueOf(this.f21574c.get(i9)).booleanValue()) {
                eVar.f21586b.setSelected(true);
            } else {
                eVar.f21586b.setSelected(false);
            }
        } else {
            eVar.f21586b.setVisibility(8);
        }
        int iM15139k = c2973l0.m15139k();
        if (iM15139k > 0) {
            eVar.f21587c.setVisibility(0);
            if (iM15139k > 100) {
                eVar.f21587c.setText("99+");
            } else {
                eVar.f21587c.setText(String.valueOf(iM15139k));
            }
        } else {
            eVar.f21587c.setVisibility(8);
        }
        if (c2973l0.m15134f() > 0) {
            eVar.f21588d.setVisibility(0);
        } else {
            eVar.f21588d.setVisibility(8);
        }
        return view;
    }

    /* renamed from: h */
    public final void m24549h() {
        m24552k();
        if (this.f21579h != null) {
            this.f21579h.mo9338a(m24548g().size());
        }
    }

    /* renamed from: i */
    public void m24550i(String str, d dVar) {
        this.f21574c.clear();
        this.f21576e = false;
        new b(str, dVar).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* renamed from: j */
    public void m24551j(TextView textView) {
        this.f21578g = textView;
        m24552k();
    }

    /* renamed from: k */
    public final void m24552k() {
        int size = m24548g().size();
        if (this.f21578g == null) {
            return;
        }
        this.f21578g.setText(new SpannableString(String.format(this.f21575d.getResources().getQuantityString(R.plurals.photo_selected, size), Integer.valueOf(size))));
        C5179r0.m20247b(this.f21578g, 1);
    }

    /* renamed from: l */
    public void m24553l(boolean z8) {
        this.f21576e = z8;
        this.f21574c.clear();
        m24549h();
        notifyDataSetChanged();
    }

    /* renamed from: m */
    public void m24554m(c cVar) {
        this.f21579h = cVar;
    }

    /* renamed from: n */
    public void m24555n(boolean z8) {
        this.f21577f = z8;
    }

    /* renamed from: o */
    public void m24556o(String str) {
        clear();
        List<C2973l0> listM14692A = this.f21577f ? C2950b0.m14914m().m14692A(str, String.valueOf(Globals.m7388i0().m7568k1()), "ASC", 10000) : C2950b0.m14914m().m14729z(str, "ASC", 10000);
        if (listM14692A != null) {
            addAll(listM14692A);
        }
    }

    /* renamed from: p */
    public void m24557p(long[] jArr) {
        if (jArr == null) {
            return;
        }
        this.f21574c.clear();
        for (long j9 : jArr) {
            int i9 = 0;
            while (true) {
                if (i9 >= this.f21573b.size()) {
                    break;
                }
                if (this.f21573b.get(i9).m15144p() == j9) {
                    this.f21574c.put(i9, true);
                    break;
                }
                i9++;
            }
        }
        m24549h();
        notifyDataSetChanged();
    }
}
