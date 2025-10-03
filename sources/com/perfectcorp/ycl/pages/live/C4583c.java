package com.perfectcorp.ycl.pages.live;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import com.rockerhieu.emojicon.emoji.Emojicon;
import com.rockerhieu.emojicon.emoji.Nature;
import com.rockerhieu.emojicon.emoji.Objects;
import com.rockerhieu.emojicon.emoji.People;
import com.rockerhieu.emojicon.emoji.Places;
import com.rockerhieu.emojicon.emoji.Symbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import p047d5.C4680d;
import p047d5.C4681e;
import p189s0.AbstractC6243a;

/* renamed from: com.perfectcorp.ycl.pages.live.c */
/* loaded from: classes2.dex */
public class C4583c extends Fragment {

    /* renamed from: b */
    public List<GridView> f16079b;

    /* renamed from: c */
    public ViewPager f16080c;

    /* renamed from: d */
    public c f16081d;

    /* renamed from: e */
    public FragmentActivity f16082e;

    /* renamed from: f */
    public View f16083f;

    /* renamed from: g */
    public d f16084g;

    /* renamed from: com.perfectcorp.ycl.pages.live.c$a */
    public class a implements AdapterView.OnItemClickListener {
        public a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            if (C4583c.this.f16084g == null) {
                return;
            }
            C4583c.this.f16084g.mo13973a((Emojicon) ((b) adapterView.getAdapter()).getItem(i9));
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.c$b */
    public static class b extends BaseAdapter {

        /* renamed from: b */
        public Context f16086b;

        /* renamed from: c */
        public List<Emojicon> f16087c;

        /* renamed from: d */
        public LayoutInflater f16088d;

        /* renamed from: com.perfectcorp.ycl.pages.live.c$b$a */
        public static class a {

            /* renamed from: a */
            public TextView f16089a;

            public a() {
            }

            public /* synthetic */ a(a aVar) {
                this();
            }
        }

        public b(Context context, Emojicon[] emojiconArr, int i9) {
            this.f16086b = context;
            this.f16087c = new ArrayList(Arrays.asList(emojiconArr));
            this.f16088d = LayoutInflater.from(context);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f16087c.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i9) {
            return this.f16087c.get(i9);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i9) {
            return i9;
        }

        @Override // android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            View viewInflate;
            a aVar;
            if (view == null) {
                aVar = new a(null);
                viewInflate = this.f16088d.inflate(C4681e.view_item_emoji, viewGroup, false);
                aVar.f16089a = (TextView) viewInflate.findViewById(C4680d.emoji_image);
                viewInflate.setTag(aVar);
            } else {
                viewInflate = view;
                aVar = (a) view.getTag();
            }
            aVar.f16089a.setText(this.f16087c.get(i9).getEmoji());
            return viewInflate;
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.c$c */
    public static class c extends AbstractC6243a {

        /* renamed from: a */
        public List<GridView> f16090a;

        public c(Context context, List<GridView> list) {
            this.f16090a = list;
        }

        @Override // p189s0.AbstractC6243a
        public void destroyItem(ViewGroup viewGroup, int i9, Object obj) {
            viewGroup.removeView((View) obj);
        }

        @Override // p189s0.AbstractC6243a
        public int getCount() {
            return this.f16090a.size();
        }

        @Override // p189s0.AbstractC6243a
        public Object instantiateItem(ViewGroup viewGroup, int i9) {
            viewGroup.addView(this.f16090a.get(i9));
            return this.f16090a.get(i9);
        }

        @Override // p189s0.AbstractC6243a
        public boolean isViewFromObject(View view, Object obj) {
            return view.equals(obj);
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.c$d */
    public interface d {
        /* renamed from: a */
        void mo13973a(Emojicon emojicon);
    }

    /* renamed from: h */
    public final void m18206h() {
        Math.ceil(Symbols.DATA.length / 21.0f);
        this.f16079b = new ArrayList();
        int i9 = 0;
        while (i9 < 5) {
            Emojicon[] emojiconArr = i9 != 0 ? i9 != 1 ? i9 != 2 ? i9 != 3 ? i9 != 4 ? null : Symbols.DATA : Places.DATA : Objects.DATA : Nature.DATA : People.DATA;
            GridView gridView = new GridView(getContext());
            gridView.setAdapter((ListAdapter) new b(getContext(), emojiconArr, i9));
            gridView.setNumColumns(7);
            gridView.setGravity(17);
            gridView.setOnItemClickListener(new a());
            this.f16079b.add(gridView);
            i9++;
        }
        c cVar = new c(this.f16082e, this.f16079b);
        this.f16081d = cVar;
        this.f16080c.setAdapter(cVar);
    }

    /* renamed from: i */
    public void m18207i(d dVar) {
        this.f16084g = dVar;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f16082e = getActivity();
        m18206h();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C4681e.emoji_panel, viewGroup, false);
        this.f16080c = (ViewPager) viewInflate.findViewById(C4680d.emoji_pager);
        this.f16083f = viewInflate;
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }
}
