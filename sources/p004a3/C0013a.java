package p004a3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.utility.CLUtility;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/* renamed from: a3.a */
/* loaded from: classes.dex */
public class C0013a extends ArrayAdapter<C2973l0> {

    /* renamed from: b */
    public final HashSet<Long> f84b;

    /* renamed from: c */
    public boolean f85c;

    /* renamed from: d */
    public LayoutInflater f86d;

    /* renamed from: a3.a$b */
    public static class b {

        /* renamed from: a */
        public final ImageView f87a;

        /* renamed from: b */
        public final TextView f88b;

        /* renamed from: c */
        public final TextView f89c;

        /* renamed from: d */
        public final TextView f90d;

        /* renamed from: e */
        public final View f91e;

        public b(View view) {
            this.f87a = (ImageView) view.findViewById(R.id.fileIcon);
            this.f89c = (TextView) view.findViewById(R.id.fileTime);
            this.f88b = (TextView) view.findViewById(R.id.fileTitle);
            this.f90d = (TextView) view.findViewById(R.id.fileSize);
            this.f91e = view.findViewById(R.id.deleteCheckbox);
        }
    }

    public C0013a(Context context, int i9, List<C2973l0> list) {
        super(context, i9, list);
        this.f84b = new HashSet<>();
        this.f85c = false;
        this.f86d = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    /* renamed from: a */
    public List<C2973l0> m108a() {
        if (this.f84b.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (int count = getCount() - 1; count >= 0; count--) {
            C2973l0 c2973l0 = (C2973l0) getItem(count);
            if (c2973l0 != null && this.f84b.contains(Long.valueOf(c2973l0.m15144p()))) {
                arrayList.add(c2973l0);
                if (arrayList.size() == this.f84b.size()) {
                    break;
                }
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public long[] m109b() {
        List<C2973l0> listM108a = m108a();
        long[] jArr = new long[listM108a.size()];
        for (int i9 = 0; i9 < listM108a.size(); i9++) {
            jArr[i9] = listM108a.get(i9).m15144p();
        }
        return jArr;
    }

    /* renamed from: c */
    public int m110c() {
        return this.f84b.size();
    }

    /* renamed from: d */
    public boolean m111d(int i9) {
        return m112e((C2973l0) getItem(i9));
    }

    /* renamed from: e */
    public final boolean m112e(C2973l0 c2973l0) {
        return c2973l0 != null && this.f84b.contains(Long.valueOf(c2973l0.m15144p()));
    }

    /* renamed from: f */
    public boolean m113f() {
        return this.f85c;
    }

    /* renamed from: g */
    public void m114g(long j9) {
        for (int i9 = 0; i9 < getCount(); i9++) {
            C2973l0 c2973l0 = (C2973l0) getItem(i9);
            if (c2973l0 != null && j9 == c2973l0.m15144p()) {
                this.f84b.remove(Long.valueOf(j9));
                remove(c2973l0);
                return;
            }
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = this.f86d.inflate(R.layout.view_item_file_list, (ViewGroup) null);
            bVar = new b(view);
            view.setTag(R.id.tag_ViewHolder, bVar);
        } else {
            bVar = (b) view.getTag(R.id.tag_ViewHolder);
        }
        C2973l0 c2973l0 = (C2973l0) getItem(i9);
        bVar.f88b.setText(c2973l0.m15145q());
        bVar.f89c.setText(CLUtility.m16596v0(getContext(), c2973l0.m15143o(), "yyyy/MM/dd"));
        bVar.f90d.setText(CLUtility.m16592u0(getContext(), c2973l0.m15148t().f13197a));
        bVar.f87a.setImageResource(CLUtility.m16560m0(c2973l0.m15145q()));
        bVar.f91e.setSelected(m112e(c2973l0));
        bVar.f91e.setVisibility(this.f85c ? 0 : 8);
        return view;
    }

    /* renamed from: h */
    public void m115h(int i9, boolean z8) {
        C2973l0 c2973l0 = (C2973l0) getItem(i9);
        if (c2973l0 == null) {
            return;
        }
        if (!z8 || m112e(c2973l0)) {
            this.f84b.remove(Long.valueOf(c2973l0.m15144p()));
        } else {
            this.f84b.add(Long.valueOf(c2973l0.m15144p()));
        }
        notifyDataSetChanged();
    }

    /* renamed from: i */
    public void m116i(boolean z8) {
        this.f85c = z8;
        if (z8) {
            this.f84b.clear();
        }
        notifyDataSetChanged();
    }
}
