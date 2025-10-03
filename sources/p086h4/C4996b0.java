package p086h4;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.utility.LoadImageUtils;
import com.rockerhieu.emojicon.emoji.Emojicon;
import java.util.List;
import p189s0.AbstractC6243a;

/* renamed from: h4.b0 */
/* loaded from: classes.dex */
public class C4996b0 extends AbstractC6243a {

    /* renamed from: a */
    public List<C4993a> f17201a;

    /* renamed from: b */
    public boolean f17202b;

    /* renamed from: c */
    public Activity f17203c;

    /* renamed from: h4.b0$a */
    public class a {

        /* renamed from: a */
        public ViewGroup f17204a;

        /* renamed from: b */
        public ViewGroup f17205b;

        /* renamed from: c */
        public final int f17206c;

        /* renamed from: d */
        public int f17207d;

        /* renamed from: e */
        public int f17208e = 0;

        /* renamed from: f */
        public int f17209f = 0;

        public a(ViewGroup viewGroup) {
            this.f17207d = 0;
            this.f17204a = viewGroup;
            int childCount = viewGroup.getChildCount();
            this.f17206c = childCount;
            if (childCount != 1) {
                ViewGroup viewGroup2 = (ViewGroup) viewGroup.getChildAt(this.f17208e);
                this.f17205b = viewGroup2;
                this.f17207d = viewGroup2.getChildCount();
            }
        }

        /* renamed from: a */
        public View m19392a() {
            int i9 = this.f17209f;
            if (i9 < this.f17207d) {
                ViewGroup viewGroup = this.f17205b;
                this.f17209f = i9 + 1;
                return viewGroup.getChildAt(i9);
            }
            this.f17209f = 0;
            int i10 = this.f17208e + 1;
            this.f17208e = i10;
            ViewGroup viewGroup2 = (ViewGroup) this.f17204a.getChildAt(i10);
            this.f17205b = viewGroup2;
            int i11 = this.f17209f;
            this.f17209f = i11 + 1;
            return viewGroup2.getChildAt(i11);
        }

        /* renamed from: b */
        public boolean m19393b() {
            return !m19394c();
        }

        /* renamed from: c */
        public boolean m19394c() {
            return this.f17206c == this.f17208e + 1 && this.f17207d == this.f17209f;
        }
    }

    public C4996b0(Activity activity, List<C4993a> list, boolean z8) {
        this.f17201a = list;
        this.f17202b = z8;
        this.f17203c = activity;
    }

    /* renamed from: a */
    public void m19379a(int i9, List<C4993a> list) {
        this.f17201a.addAll(i9, list);
    }

    /* renamed from: b */
    public void m19380b(int i9, StickerObj stickerObj) {
        if (m19384f(i9).m19371d() instanceof ViewGroup) {
            a aVar = new a((ViewGroup) m19384f(i9).m19371d());
            while (aVar.m19393b()) {
                View viewM19392a = aVar.m19392a();
                if (viewM19392a.getTag() != stickerObj) {
                    m19389k(viewM19392a);
                }
            }
        }
    }

    /* renamed from: c */
    public void m19381c() {
        this.f17201a.clear();
    }

    /* renamed from: d */
    public C4993a m19382d(int i9) {
        return this.f17201a.get(i9);
    }

    @Override // p189s0.AbstractC6243a
    public void destroyItem(ViewGroup viewGroup, int i9, Object obj) {
        viewGroup.removeView(m19384f(i9).m19371d());
    }

    /* renamed from: e */
    public long m19383e(int i9) {
        return Long.parseLong(this.f17201a.get(i9).m19368a());
    }

    /* renamed from: f */
    public C4993a m19384f(int i9) {
        return this.f17201a.get(i9);
    }

    /* renamed from: g */
    public int m19385g(C4993a c4993a) {
        int i9 = 0;
        for (int i10 = 0; i10 < this.f17201a.size(); i10++) {
            if (this.f17201a.get(i10).m19373f(c4993a)) {
                i9++;
            }
        }
        return i9;
    }

    @Override // p189s0.AbstractC6243a
    public int getCount() {
        return this.f17201a.size();
    }

    @Override // p189s0.AbstractC6243a
    public int getItemPosition(Object obj) {
        return -2;
    }

    /* renamed from: h */
    public boolean m19386h() {
        return this.f17202b;
    }

    /* renamed from: i */
    public void m19387i(int i9) {
        if (i9 >= this.f17201a.size() || i9 < 0) {
            return;
        }
        m19388j(this.f17201a.get(i9).m19371d());
        this.f17201a.remove(i9);
    }

    @Override // p189s0.AbstractC6243a
    public Object instantiateItem(ViewGroup viewGroup, int i9) {
        View viewM19371d = m19384f(i9).m19371d();
        if (!(viewM19371d instanceof ViewGroup)) {
            m19388j(viewM19371d);
            viewGroup.addView(viewM19371d);
            return viewM19371d;
        }
        ViewGroup viewGroup2 = (ViewGroup) viewM19371d;
        if (viewGroup2.getParent() == null) {
            viewGroup.addView(viewGroup2);
        }
        a aVar = new a(viewGroup2);
        while (aVar.m19393b()) {
            View viewM19392a = aVar.m19392a();
            if (viewM19392a != null) {
                if (viewM19392a.getTag() instanceof Emojicon) {
                    ((TextView) viewM19392a).setText(((Emojicon) viewM19392a.getTag()).getEmoji());
                } else if (viewM19392a.getTag() instanceof StickerObj) {
                    LoadImageUtils.m16638w(this.f17203c, (StickerObj) viewM19392a.getTag(), true, (ImageView) viewM19392a, false);
                }
            }
        }
        return viewGroup2;
    }

    @Override // p189s0.AbstractC6243a
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    /* renamed from: j */
    public void m19388j(View view) {
        if (view.getParent() == null || !(view.getParent() instanceof ViewGroup)) {
            return;
        }
        ((ViewGroup) view.getParent()).removeView(view);
    }

    /* renamed from: k */
    public final void m19389k(View view) {
        view.setBackground(null);
    }

    /* renamed from: l */
    public synchronized void m19390l(int i9, List<C4993a> list, boolean z8) {
        m19387i(i9);
        if (list.size() > 1 && !z8) {
            for (int i10 = 1; i10 < list.size(); i10++) {
                m19387i(i9);
            }
        }
        m19379a(i9, list);
        notifyDataSetChanged();
    }

    /* renamed from: m */
    public void m19391m(int i9, StickerObj stickerObj) {
        if (m19384f(i9).m19371d() instanceof ViewGroup) {
            a aVar = new a((ViewGroup) m19384f(i9).m19371d());
            while (aVar.m19393b()) {
                View viewM19392a = aVar.m19392a();
                if (viewM19392a.getTag() == stickerObj) {
                    viewM19392a.setBackgroundResource(R.drawable.bg_border_sticker_preview);
                } else {
                    m19389k(viewM19392a);
                }
            }
        }
    }
}
