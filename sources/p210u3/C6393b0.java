package p210u3;

import android.content.Context;
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
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.utility.CLUtility;
import java.util.ArrayList;
import java.util.List;
import p116k4.C5179r0;

/* renamed from: u3.b0 */
/* loaded from: classes.dex */
public class C6393b0 extends ArrayAdapter<C2973l0> {

    /* renamed from: i */
    public static final Object f21592i = new Object();

    /* renamed from: b */
    public List<C2973l0> f21593b;

    /* renamed from: c */
    public SparseBooleanArray f21594c;

    /* renamed from: d */
    public Context f21595d;

    /* renamed from: e */
    public boolean f21596e;

    /* renamed from: f */
    public TextView f21597f;

    /* renamed from: g */
    public b f21598g;

    /* renamed from: h */
    public View.OnClickListener f21599h;

    /* renamed from: u3.b0$a */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iIntValue = ((Integer) view.getTag()).intValue();
            if (C6393b0.this.f21594c.get(iIntValue)) {
                C6393b0.this.f21594c.put(iIntValue, false);
                view.setSelected(false);
            } else {
                C6393b0.this.f21594c.put(iIntValue, true);
                view.setSelected(true);
            }
            C6393b0.this.m24564e();
        }
    }

    /* renamed from: u3.b0$b */
    public interface b {
        /* renamed from: a */
        void mo10786a(int i9);
    }

    /* renamed from: u3.b0$c */
    public class c {

        /* renamed from: a */
        public ImageView f21601a;

        /* renamed from: b */
        public ImageView f21602b;

        /* renamed from: c */
        public TextView f21603c;

        public c() {
        }

        public /* synthetic */ c(C6393b0 c6393b0, a aVar) {
            this();
        }
    }

    public C6393b0(Context context, int i9, List<C2973l0> list) {
        super(context, i9, list);
        this.f21597f = null;
        this.f21599h = new a();
        this.f21593b = list;
        this.f21595d = context;
        this.f21596e = false;
        this.f21594c = new SparseBooleanArray();
    }

    /* renamed from: c */
    public long[] m24562c() {
        List<C2973l0> listM24563d = m24563d();
        long[] jArr = new long[listM24563d.size()];
        for (int i9 = 0; i9 < listM24563d.size(); i9++) {
            jArr[i9] = listM24563d.get(i9).m15144p();
        }
        return jArr;
    }

    /* renamed from: d */
    public final List<C2973l0> m24563d() {
        ArrayList arrayList = new ArrayList();
        int size = this.f21594c.size();
        for (int i9 = 0; i9 < size; i9++) {
            int iKeyAt = this.f21594c.keyAt(i9);
            if (this.f21594c.get(iKeyAt)) {
                arrayList.add((C2973l0) getItem(iKeyAt));
            }
        }
        return arrayList;
    }

    /* renamed from: e */
    public final void m24564e() {
        m24567h();
        if (this.f21598g != null) {
            this.f21598g.mo10786a(m24563d().size());
        }
    }

    /* renamed from: f */
    public void m24565f(List<C2973l0> list) {
        this.f21594c.clear();
        this.f21596e = false;
        synchronized (f21592i) {
            clear();
            if (list != null) {
                addAll(list);
            }
        }
    }

    /* renamed from: g */
    public void m24566g(TextView textView) {
        this.f21597f = textView;
        m24567h();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        c cVar;
        if (view == null) {
            view = ((LayoutInflater) this.f21595d.getSystemService("layout_inflater")).inflate(R.layout.view_item_group_video, (ViewGroup) null);
            cVar = new c(this, null);
            cVar.f21601a = (ImageView) view.findViewById(R.id.photoView);
            ImageView imageView = (ImageView) view.findViewById(R.id.selectBtn);
            cVar.f21602b = imageView;
            imageView.setOnClickListener(this.f21599h);
            cVar.f21603c = (TextView) view.findViewById(R.id.duration);
            view.setTag(cVar);
        } else {
            cVar = (c) view.getTag();
        }
        C2973l0 c2973l0 = this.f21593b.get(i9);
        MediaLoader.m7156o(this.f21595d, cVar.f21601a, c2973l0, MediaLoader.Option.THUMBNAIL);
        cVar.f21602b.setTag(Integer.valueOf(i9));
        if (this.f21596e) {
            cVar.f21602b.setVisibility(0);
            if (Boolean.valueOf(this.f21594c.get(i9)).booleanValue()) {
                cVar.f21602b.setSelected(true);
            } else {
                cVar.f21602b.setSelected(false);
            }
        } else {
            cVar.f21602b.setVisibility(8);
        }
        String str = c2973l0.m15148t().f13203g;
        if (str != null) {
            cVar.f21603c.setText(CLUtility.m16519c(str));
        }
        return view;
    }

    /* renamed from: h */
    public final void m24567h() {
        int size = m24563d().size();
        if (this.f21597f == null) {
            return;
        }
        this.f21597f.setText(new SpannableString(String.format(this.f21595d.getResources().getQuantityString(R.plurals.video_selected, size), Integer.valueOf(size))));
        C5179r0.m20247b(this.f21597f, 1);
    }

    /* renamed from: i */
    public void m24568i(boolean z8) {
        this.f21596e = z8;
        this.f21594c.clear();
        m24564e();
        notifyDataSetChanged();
    }

    /* renamed from: j */
    public void m24569j(b bVar) {
        this.f21598g = bVar;
    }

    /* renamed from: k */
    public void m24570k(long[] jArr) {
        if (jArr == null) {
            return;
        }
        this.f21594c.clear();
        for (long j9 : jArr) {
            int i9 = 0;
            while (true) {
                if (i9 >= this.f21593b.size()) {
                    break;
                }
                if (this.f21593b.get(i9).m15144p() == j9) {
                    this.f21594c.put(i9, true);
                    break;
                }
                i9++;
            }
        }
        m24564e();
        notifyDataSetChanged();
    }
}
