package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.AbstractC0368g;
import androidx.fragment.app.AbstractC0372k;
import androidx.viewpager.widget.ViewPager;
import com.cyberlink.p030U.R;
import com.cyberlink.p033u.glide.MediaLoader;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.MediaDao;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.pages.photoimport.view.TouchViewPager;
import com.cyberlink.you.pages.photoimport.view.tiv.TouchImageView;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import p116k4.C5170o0;
import p116k4.C5187v0;
import p132m.C5305d;
import p136m3.C5321e;
import p173q2.C6136j;
import p189s0.AbstractC6243a;
import p209u2.AbstractC6378o;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class ShowMediaActivity extends BaseFragmentActivity {

    /* renamed from: c */
    public PhotoCommentFragment f9044c;

    /* renamed from: d */
    public C1739d f9045d;

    /* renamed from: e */
    public TouchViewPager f9046e;

    /* renamed from: f */
    public View f9047f;

    /* renamed from: g */
    public View f9048g;

    /* renamed from: h */
    public View f9049h;

    /* renamed from: i */
    public Group f9050i;

    /* renamed from: k */
    public boolean f9052k;

    /* renamed from: o */
    public String f9056o;

    /* renamed from: p */
    public String f9057p;

    /* renamed from: j */
    public int f9051j = 0;

    /* renamed from: l */
    public boolean f9053l = false;

    /* renamed from: m */
    public long f9054m = -1;

    /* renamed from: n */
    public long f9055n = 0;

    /* renamed from: q */
    public C5305d<String> f9058q = new C5305d<>();

    /* renamed from: r */
    public Set<Long> f9059r = new HashSet();

    /* renamed from: s */
    public TouchViewPager.InterfaceC3108b f9060s = new TouchViewPager.InterfaceC3108b() { // from class: com.cyberlink.you.activity.pf
        @Override // com.cyberlink.you.pages.photoimport.view.TouchViewPager.InterfaceC3108b
        /* renamed from: a */
        public final void mo12492a() {
            this.f11044a.m10013l1();
        }
    };

    /* renamed from: t */
    public SparseArray<TouchImageView> f9061t = new SparseArray<>();

    /* renamed from: u */
    public boolean f9062u = false;

    /* renamed from: v */
    public View.OnClickListener f9063v = new ViewOnClickListenerC1736a();

    /* renamed from: w */
    public C5321e.m f9064w = new C5321e.m() { // from class: com.cyberlink.you.activity.qf
        @Override // p136m3.C5321e.m
        /* renamed from: A0 */
        public final boolean mo8241A0(C2904l c2904l, Map map) {
            return this.f11087b.m10001s1(c2904l, map);
        }
    };

    /* renamed from: x */
    public ViewPager.InterfaceC0557j f9065x = new C1738c();

    /* renamed from: com.cyberlink.you.activity.ShowMediaActivity$a */
    public class ViewOnClickListenerC1736a implements View.OnClickListener {
        public ViewOnClickListenerC1736a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractC0368g supportFragmentManager;
            if (ShowMediaActivity.this.f9044c == null || (supportFragmentManager = ShowMediaActivity.this.getSupportFragmentManager()) == null) {
                return;
            }
            AbstractC0372k abstractC0372kMo1844a = supportFragmentManager.mo1844a();
            if (ShowMediaActivity.this.f9044c.m9019E1()) {
                ShowMediaActivity.this.f9044c.m9015C1();
            } else if (ShowMediaActivity.this.f9044c.isHidden()) {
                abstractC0372kMo1844a.mo1802r(ShowMediaActivity.this.f9044c).mo1794h();
            } else {
                abstractC0372kMo1844a.mo1799n(ShowMediaActivity.this.f9044c).mo1794h();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.ShowMediaActivity$b */
    public class AsyncTaskC1737b extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public final /* synthetic */ String f9067a;

        /* renamed from: b */
        public final /* synthetic */ int f9068b;

        public AsyncTaskC1737b(String str, int i9) {
            this.f9067a = str;
            this.f9068b = i9;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) throws JSONException {
            C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(Long.parseLong(this.f9067a));
            if ((c2973l0M14725v != null && !C5170o0.m20170e(c2973l0M14725v.m15147s())) || this.f9068b != ShowMediaActivity.this.f9045d.getCount() - 1) {
                return null;
            }
            FriendsClient.m15657X(ShowMediaActivity.this.f9057p, Long.valueOf(this.f9067a).longValue());
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r12) {
            ShowMediaActivity.this.f9045d.m10027d();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ShowMediaActivity$c */
    public class C1738c implements ViewPager.InterfaceC0557j {
        public C1738c() {
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageScrollStateChanged(int i9) {
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageScrolled(int i9, float f9, int i10) {
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageSelected(int i9) {
            TouchImageView touchImageView = (TouchImageView) ShowMediaActivity.this.f9061t.get(i9);
            if (touchImageView != null) {
                ShowMediaActivity.this.f9046e.f14275k0 = touchImageView;
                touchImageView.m16273r();
            }
            ShowMediaActivity.this.f9046e.setCurrentItem(i9);
            C2973l0 c2973l0M10029f = ShowMediaActivity.this.f9045d.m10029f(i9);
            if (ShowMediaActivity.this.f9044c == null || c2973l0M10029f == null) {
                return;
            }
            if (ShowMediaActivity.this.f9059r.contains(Long.valueOf(c2973l0M10029f.m15144p()))) {
                ShowMediaActivity.this.f9045d.m10034k(ShowMediaActivity.this.f9046e.getCurrentItem(), ShowMediaActivity.this.f9046e.f14275k0, false);
            } else {
                ShowMediaActivity.this.f9049h.setVisibility(8);
            }
            ShowMediaActivity.this.f9044c.m9064v2(c2973l0M10029f);
        }
    }

    /* renamed from: com.cyberlink.you.activity.ShowMediaActivity$d */
    public class C1739d extends AbstractC6243a {

        /* renamed from: a */
        public SparseArray<C2973l0> f9071a;

        /* renamed from: b */
        public Activity f9072b;

        /* renamed from: c */
        public int f9073c = -1;

        /* renamed from: d */
        public final HashSet<Long> f9074d = new HashSet<>();

        /* renamed from: e */
        public int f9075e;

        /* renamed from: f */
        public int f9076f;

        /* renamed from: g */
        public int f9077g;

        /* renamed from: com.cyberlink.you.activity.ShowMediaActivity$d$a */
        public class a extends AbstractC6378o<Bitmap, Void, Void> {

            /* renamed from: d */
            public final /* synthetic */ int f9079d;

            /* renamed from: e */
            public final /* synthetic */ C2973l0 f9080e;

            /* renamed from: f */
            public final /* synthetic */ TouchImageView f9081f;

            /* renamed from: g */
            public final /* synthetic */ AtomicBoolean f9082g;

            public a(int i9, C2973l0 c2973l0, TouchImageView touchImageView, AtomicBoolean atomicBoolean) {
                this.f9079d = i9;
                this.f9080e = c2973l0;
                this.f9081f = touchImageView;
                this.f9082g = atomicBoolean;
            }

            /* renamed from: p */
            public final boolean m10035p() {
                return (ShowMediaActivity.this.f9046e != null ? ShowMediaActivity.this.f9046e.getCurrentItem() : -1) == this.f9079d;
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: q, reason: merged with bridge method [inline-methods] */
            public void m24503g(Bitmap bitmap) throws Throwable {
                if (m10035p()) {
                    ShowMediaActivity.this.f9048g.setVisibility(8);
                    ShowMediaActivity.this.f9049h.setVisibility(8);
                }
                this.f9081f.setVisibility(0);
                ShowMediaActivity.m9976D1(this.f9080e, bitmap.getWidth(), bitmap.getHeight());
                this.f9082g.set(true);
                C1739d.this.f9074d.remove(Long.valueOf(this.f9080e.m15144p()));
                ShowMediaActivity.this.f9059r.remove(Long.valueOf(this.f9080e.m15144p()));
                this.f9081f.setImageBitmap(bitmap);
                this.f9081f.setTag(R.id.tag_original_photo, bitmap);
                String str = (String) ShowMediaActivity.this.f9058q.m20722e(this.f9080e.m15144p());
                String strM15225n = C2950b0.m14920s().m15225n(this.f9080e.m15144p());
                if (strM15225n == null || strM15225n.equals(str)) {
                    return;
                }
                ShowMediaActivity.this.m10009T0(this.f9081f, this.f9080e);
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: r, reason: merged with bridge method [inline-methods] */
            public void m24504h(Void r42) {
                this.f9081f.setVisibility(8);
                if (m10035p()) {
                    ShowMediaActivity.this.f9048g.setVisibility(8);
                    if (CLUtility.m16613z1(this.f9080e.m15149u().f13201e, null)) {
                        C6136j.m23599s(ShowMediaActivity.this, this.f9081f, this.f9080e.m15149u().f13201e);
                        this.f9081f.setVisibility(0);
                    } else {
                        ShowMediaActivity.this.f9049h.setVisibility(0);
                    }
                }
                C1739d.this.f9074d.remove(Long.valueOf(this.f9080e.m15144p()));
                ShowMediaActivity.this.f9059r.add(Long.valueOf(this.f9080e.m15144p()));
            }

            @Override // p209u2.AbstractC6378o
            /* renamed from: s, reason: merged with bridge method [inline-methods] */
            public void m24498l(Void r32) {
                C1739d.this.f9074d.add(Long.valueOf(this.f9080e.m15144p()));
                if (m10035p()) {
                    ShowMediaActivity.this.f9048g.setVisibility(0);
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.ShowMediaActivity$d$b */
        public class b extends AbstractC6378o<Bitmap, Void, Void> {

            /* renamed from: d */
            public final /* synthetic */ AtomicBoolean f9084d;

            /* renamed from: e */
            public final /* synthetic */ TouchImageView f9085e;

            public b(AtomicBoolean atomicBoolean, TouchImageView touchImageView) {
                this.f9084d = atomicBoolean;
                this.f9085e = touchImageView;
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: p, reason: merged with bridge method [inline-methods] */
            public void m24503g(Bitmap bitmap) {
                if (this.f9084d.get()) {
                    return;
                }
                this.f9085e.setImageBitmap(bitmap);
            }

            @Override // p209u2.AbstractC6378o
            /* renamed from: q, reason: merged with bridge method [inline-methods] */
            public void m24498l(Void r12) {
            }
        }

        public C1739d(Activity activity, SparseArray<C2973l0> sparseArray, int i9) {
            this.f9072b = activity;
            this.f9071a = sparseArray;
            this.f9075e = i9;
            Pair<Integer, Integer> pairM10031h = m10031h();
            this.f9076f = ((Integer) pairM10031h.first).intValue();
            this.f9077g = ((Integer) pairM10031h.second).intValue();
        }

        /* renamed from: d */
        public void m10027d() {
            this.f9075e++;
            m10032i(true);
            ShowMediaActivity.this.f9045d.notifyDataSetChanged();
        }

        @Override // p189s0.AbstractC6243a
        public void destroyItem(ViewGroup viewGroup, int i9, Object obj) {
            ShowMediaActivity.this.f9061t.remove(i9);
            viewGroup.removeView((View) obj);
            C2973l0 c2973l0M10029f = ShowMediaActivity.this.f9045d.m10029f(i9);
            if (c2973l0M10029f != null) {
                ShowMediaActivity.this.f9058q.m20727k(c2973l0M10029f.m15144p());
            }
        }

        /* renamed from: e */
        public int m10028e() {
            return this.f9077g;
        }

        /* renamed from: f */
        public C2973l0 m10029f(int i9) {
            return this.f9071a.get(i9);
        }

        /* renamed from: g */
        public int m10030g(long j9) {
            int iKeyAt;
            int i9 = 0;
            while (true) {
                if (i9 >= this.f9071a.size()) {
                    iKeyAt = -1;
                    break;
                }
                if (this.f9071a.valueAt(i9).m15144p() == j9) {
                    iKeyAt = this.f9071a.keyAt(i9);
                    break;
                }
                i9++;
            }
            return iKeyAt == -1 ? C2950b0.m14914m().m14693B(ShowMediaActivity.this.f9057p, j9) : iKeyAt;
        }

        @Override // p189s0.AbstractC6243a
        public int getCount() {
            return this.f9075e;
        }

        /* renamed from: h */
        public final Pair<Integer, Integer> m10031h() {
            int i9 = 0;
            for (int i10 = 0; i10 < this.f9071a.size(); i10++) {
                int iKeyAt = this.f9071a.keyAt(i10);
                if (iKeyAt > i9) {
                    i9 = iKeyAt;
                }
            }
            return new Pair<>(Integer.valueOf((i9 - this.f9071a.size()) + 1), Integer.valueOf(i9));
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x003d  */
        /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
        /* renamed from: i */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void m10032i(boolean z8) {
            MediaDao.SelectType selectType;
            int i9 = this.f9073c;
            int i10 = this.f9077g;
            int i11 = 1;
            boolean z9 = i9 > i10 + (-5) && i10 < this.f9075e - 1;
            int i12 = this.f9076f;
            boolean z10 = i9 < i12 + 5 && i12 > 0;
            if (!z8) {
                if (z9 && z10) {
                    selectType = MediaDao.SelectType.BOTH;
                } else if (z9) {
                    selectType = MediaDao.SelectType.NEXT;
                } else if (z10) {
                    selectType = MediaDao.SelectType.PREV;
                    i10 = i12;
                }
                i11 = 50;
                if (selectType != null) {
                }
            } else if (z9) {
                selectType = MediaDao.SelectType.NEXT;
                if (selectType != null) {
                    SparseArray<C2973l0> sparseArrayM14694C = C2950b0.m14914m().m14694C(ShowMediaActivity.this.f9057p, i10, i11, selectType);
                    for (int i13 = 0; i13 < sparseArrayM14694C.size(); i13++) {
                        this.f9071a.put(sparseArrayM14694C.keyAt(i13), sparseArrayM14694C.valueAt(i13));
                    }
                    if (selectType == MediaDao.SelectType.BOTH) {
                        Pair<Integer, Integer> pairM10031h = m10031h();
                        this.f9076f = ((Integer) pairM10031h.first).intValue();
                        this.f9077g = ((Integer) pairM10031h.second).intValue();
                        return;
                    } else if (selectType == MediaDao.SelectType.NEXT) {
                        this.f9077g += sparseArrayM14694C.size();
                        return;
                    } else {
                        this.f9076f -= sparseArrayM14694C.size();
                        return;
                    }
                }
                return;
            }
            selectType = null;
            i10 = -1;
            i11 = 50;
            if (selectType != null) {
            }
        }

        @Override // p189s0.AbstractC6243a
        public Object instantiateItem(ViewGroup viewGroup, int i9) {
            View viewInflate = this.f9072b.getLayoutInflater().inflate(R.layout.view_item_show_media, viewGroup, false);
            TouchImageView touchImageView = (TouchImageView) viewInflate.findViewById(R.id.imageViewPhoto);
            viewInflate.setTag(touchImageView);
            ShowMediaActivity.this.f9061t.put(i9, touchImageView);
            if (ShowMediaActivity.this.f9046e.getCurrentItem() == i9) {
                ShowMediaActivity.this.f9046e.f14275k0 = touchImageView;
            }
            if (ShowMediaActivity.this.f9052k) {
                touchImageView.setOnClickListener(ShowMediaActivity.this.f9063v);
            }
            m10034k(i9, touchImageView, false);
            viewGroup.addView(viewInflate, 0);
            return viewInflate;
        }

        @Override // p189s0.AbstractC6243a
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        /* renamed from: j */
        public void m10033j(ViewPager viewPager, int i9) {
            int i10;
            if (i9 <= this.f9077g) {
                m10031h();
                int i11 = this.f9076f;
                if (i9 >= i11) {
                    i11 = i9 + 1;
                }
                while (true) {
                    i10 = this.f9077g;
                    if (i11 > i10) {
                        break;
                    }
                    this.f9071a.put(i11 - 1, this.f9071a.get(i11));
                    i11++;
                }
                this.f9071a.remove(i10);
                m10031h();
                int i12 = this.f9076f;
                if (i12 > i9) {
                    this.f9076f = i12 - 1;
                }
                this.f9077g--;
            }
            this.f9075e--;
            m10032i(false);
            viewPager.setAdapter(this);
        }

        /* renamed from: k */
        public final void m10034k(int i9, TouchImageView touchImageView, boolean z8) {
            C2973l0 c2973l0M10029f = ShowMediaActivity.this.f9045d.m10029f(i9);
            if (c2973l0M10029f == null || touchImageView == null) {
                return;
            }
            if (z8) {
                ShowMediaActivity showMediaActivity = ShowMediaActivity.this;
                C6136j.m23599s(showMediaActivity, touchImageView, showMediaActivity.f9056o);
            } else {
                AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                MediaLoader.m7146e(ShowMediaActivity.this, c2973l0M10029f, false, new a(i9, c2973l0M10029f, touchImageView, atomicBoolean));
                MediaLoader.m7146e(ShowMediaActivity.this, c2973l0M10029f, true, new b(atomicBoolean, touchImageView));
            }
        }

        @Override // p189s0.AbstractC6243a
        public void setPrimaryItem(ViewGroup viewGroup, int i9, Object obj) {
            C2973l0 c2973l0M10029f;
            if (this.f9073c == i9 || (c2973l0M10029f = ShowMediaActivity.this.f9045d.m10029f(i9)) == null) {
                return;
            }
            if (this.f9074d.contains(Long.valueOf(c2973l0M10029f.m15144p()))) {
                ShowMediaActivity.this.f9048g.setVisibility(0);
            } else {
                ShowMediaActivity.this.f9048g.setVisibility(8);
            }
            this.f9073c = i9;
            m10032i(false);
        }
    }

    /* renamed from: D1 */
    public static void m9976D1(final C2973l0 c2973l0, int i9, int i10) {
        if (c2973l0.m15151w() == i9 && c2973l0.m15141m() == i10) {
            return;
        }
        c2973l0.m15124K(i9);
        c2973l0.m15121H(i10);
        C6385v.m24526d(new Runnable() { // from class: com.cyberlink.you.activity.uf
            @Override // java.lang.Runnable
            public final void run() {
                ShowMediaActivity.m10003u1(c2973l0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p1 */
    public /* synthetic */ void m9998p1(String str, C2973l0 c2973l0, ImageView imageView, Bitmap bitmap) {
        if (!m7367J0() && str.equals(this.f9058q.m20722e(c2973l0.m15144p()))) {
            imageView.setImageBitmap(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q1 */
    public /* synthetic */ void m9999q1(final String str, Bitmap bitmap, final C2973l0 c2973l0, final ImageView imageView) {
        final Bitmap bitmapM10018w1 = m10018w1(bitmap, C6136j.m23586f(this, str));
        C6385v.m24527e(new Runnable() { // from class: com.cyberlink.you.activity.vf
            @Override // java.lang.Runnable
            public final void run() {
                this.f11820b.m9998p1(str, c2973l0, imageView, bitmapM10018w1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r1 */
    public /* synthetic */ void m10000r1(int i9) {
        TouchViewPager touchViewPager = this.f9046e;
        if (touchViewPager == null) {
            return;
        }
        int currentItem = touchViewPager.getCurrentItem();
        if (i9 == currentItem) {
            m10012k1(currentItem);
            return;
        }
        if (this.f9046e.getChildCount() <= 1) {
            m10013l1();
            return;
        }
        this.f9045d.m10033j(this.f9046e, i9);
        if (i9 < currentItem) {
            currentItem--;
        }
        this.f9046e.setCurrentItem(currentItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s1 */
    public /* synthetic */ boolean m10001s1(C2904l c2904l, Map map) {
        String str = (String) map.get("eventType");
        str.hashCode();
        if (!str.equals("media.media.created") && !str.equals("media.media.deleted")) {
            return true;
        }
        m10016o1(str, (String) map.get("mediaId"));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t1 */
    public /* synthetic */ void m10002t1(View view) {
        finish();
    }

    /* renamed from: u1 */
    public static /* synthetic */ void m10003u1(C2973l0 c2973l0) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("Height");
        arrayList.add("Width");
        C2950b0.m14914m().m14699I(c2973l0.m15144p(), c2973l0, arrayList);
    }

    /* renamed from: A1 */
    public final void m10004A1(int i9) {
        this.f9051j = i9 | this.f9051j;
    }

    /* renamed from: B1 */
    public final void m10005B1(SparseArray<C2973l0> sparseArray, int i9, int i10) {
        C1739d c1739d = new C1739d(this, sparseArray, i10);
        this.f9045d = c1739d;
        this.f9046e.setAdapter(c1739d);
        this.f9046e.m3187c(this.f9065x);
        this.f9046e.setCurrentItem(i9);
        if (i9 == 0) {
            C2973l0 c2973l0 = sparseArray.get(i9);
            PhotoCommentFragment photoCommentFragment = this.f9044c;
            if (photoCommentFragment == null || c2973l0 == null) {
                return;
            }
            photoCommentFragment.m9064v2(c2973l0);
        }
    }

    /* renamed from: C1 */
    public void m10006C1(String str) {
        String str2 = this.f9056o;
        if (str2 == null || !str2.equals(str)) {
            this.f9056o = str;
            if (str == null) {
                this.f9045d.m10034k(this.f9046e.getCurrentItem(), this.f9046e.f14275k0, false);
            } else {
                this.f9045d.m10034k(this.f9046e.getCurrentItem(), this.f9046e.f14275k0, true);
            }
        }
    }

    /* renamed from: R0 */
    public void m10007R0() {
        m10004A1(2);
    }

    /* renamed from: S0 */
    public void m10008S0() throws Throwable {
        Log.d("ShowMediaActivity", "start reload doodle");
        m10009T0(this.f9046e.f14275k0, this.f9045d.m10029f(this.f9046e.getCurrentItem()));
    }

    /* renamed from: T0 */
    public final void m10009T0(ImageView imageView, C2973l0 c2973l0) throws Throwable {
        Bitmap bitmap;
        if (imageView == null || (bitmap = (Bitmap) imageView.getTag(R.id.tag_original_photo)) == null) {
            return;
        }
        m10010U0(imageView, c2973l0, bitmap);
    }

    /* renamed from: U0 */
    public final void m10010U0(final ImageView imageView, final C2973l0 c2973l0, final Bitmap bitmap) throws Throwable {
        final String strM15225n = C2950b0.m14920s().m15225n(c2973l0.m15144p());
        if (strM15225n == null || strM15225n.equals(this.f9058q.m20722e(c2973l0.m15144p()))) {
            return;
        }
        this.f9058q.m20726j(c2973l0.m15144p(), strM15225n);
        ULogUtility.m16670f("ShowMediaActivity", "[" + ULogUtility.m16677m() + "] load doodle | mediaId  = " + c2973l0.m15144p());
        C6385v.m24525c(new Runnable() { // from class: com.cyberlink.you.activity.tf
            @Override // java.lang.Runnable
            public final void run() {
                this.f11400b.m9999q1(strM15225n, bitmap, c2973l0, imageView);
            }
        });
    }

    /* renamed from: j1 */
    public final SparseArray<C2973l0> m10011j1(List<C2973l0> list) {
        SparseArray<C2973l0> sparseArray = new SparseArray<>();
        for (int i9 = 0; i9 < list.size(); i9++) {
            sparseArray.put(i9, list.get(i9));
        }
        return sparseArray;
    }

    /* renamed from: k1 */
    public void m10012k1(int i9) {
        m10004A1(1);
        if (this.f9046e.getChildCount() <= 1) {
            m10013l1();
            return;
        }
        boolean z8 = i9 == this.f9045d.m10028e();
        this.f9045d.m10033j(this.f9046e, i9);
        if ((i9 != 0 && !this.f9046e.f14277m0) || z8) {
            i9--;
        }
        this.f9046e.setCurrentItem(i9);
        m10019x1(this.f9045d.m10029f(i9));
    }

    /* renamed from: l1 */
    public final void m10013l1() {
        CLUtility.m16589t1(m10014m1());
        Intent intent = new Intent();
        intent.putExtra("operationResult", this.f9051j);
        setResult(-1, intent);
        intent.putExtra("ShowShareToMyAlbum", this.f9053l);
        finish();
    }

    /* renamed from: m1 */
    public final Activity m10014m1() {
        return this;
    }

    /* renamed from: n1 */
    public Group m10015n1() {
        return this.f9050i;
    }

    /* renamed from: o1 */
    public final void m10016o1(String str, String str2) {
        if (C5170o0.m20170e(str2)) {
            return;
        }
        final int i9 = 0;
        if ("media.media.created".equals(str)) {
            TouchViewPager touchViewPager = this.f9046e;
            if (touchViewPager == null) {
                return;
            }
            new AsyncTaskC1737b(str2, touchViewPager.getCurrentItem()).executeOnExecutor(C6385v.f21554b, new Void[0]);
            return;
        }
        if ("media.media.deleted".equals(str)) {
            int iM10030g = this.f9045d.m10030g(Long.parseLong(str2));
            if (iM10030g < 0) {
                int iM14693B = this.f9045d.f9077g;
                C1739d c1739d = this.f9045d;
                C2973l0 c2973l0M10029f = c1739d.m10029f(c1739d.f9077g);
                if (c2973l0M10029f != null) {
                    iM14693B = C2950b0.m14914m().m14693B(this.f9057p, c2973l0M10029f.m15144p());
                }
                if (iM14693B >= this.f9045d.f9077g) {
                    i9 = this.f9045d.f9077g + 1;
                }
            } else {
                i9 = iM10030g;
            }
            runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.sf
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11233b.m10000r1(i9);
                }
            });
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        if (i9 == 0) {
            if (i10 == -1) {
                this.f9053l = intent.getBooleanExtra("ShowShareToMyAlbum", false);
            }
        } else if (i9 == 1 && i10 == -1) {
            Group group = (Group) intent.getParcelableExtra("StartOtherGroup");
            if (group != null) {
                m10017v1(group);
            }
            finish();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        View view = this.f9047f;
        if (view != null && view.getVisibility() == 0) {
            finish();
            return;
        }
        PhotoCommentFragment photoCommentFragment = this.f9044c;
        if (photoCommentFragment == null || !photoCommentFragment.m9019E1()) {
            m10013l1();
        } else {
            this.f9044c.m9015C1();
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        int iM14723t;
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.activity_show_media);
        CLUtility.m16582r2(true, getWindow());
        this.f9048g = findViewById(R.id.loading);
        this.f9049h = findViewById(R.id.error);
        Intent intent = getIntent();
        this.f9057p = intent.getStringExtra("albumId");
        long longExtra = intent.getLongExtra("mediaId", -1L);
        this.f9050i = (Group) intent.getParcelableExtra("Group");
        long longExtra2 = intent.getLongExtra("scheduleTime", 0L);
        this.f9055n = longExtra2;
        int iKeyAt = 0;
        this.f9052k = longExtra2 == 0 || longExtra2 < System.currentTimeMillis();
        String stringExtra = intent.getStringExtra("activityName");
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.f9062u = intent.getBooleanExtra("isOpenFromMeeting", false);
        TouchViewPager touchViewPager = (TouchViewPager) findViewById(R.id.viewpager);
        this.f9046e = touchViewPager;
        touchViewPager.setSwipeOutListener(this.f9060s);
        this.f9053l = intent.getBooleanExtra("ShowShareToMyAlbum", false);
        String stringExtra2 = intent.getStringExtra("commentType");
        this.f9054m = intent.getLongExtra("commentId", -1L);
        boolean booleanExtra = intent.getBooleanExtra("bulletinMode", false);
        boolean booleanExtra2 = intent.getBooleanExtra("bulletinComment", false);
        if (this.f9052k) {
            if (bundle == null) {
                PhotoCommentFragment photoCommentFragment = new PhotoCommentFragment();
                this.f9044c = photoCommentFragment;
                photoCommentFragment.m9018D2(this.f9062u);
                Bundle bundle2 = new Bundle();
                bundle2.putBoolean("bulletinMode", booleanExtra);
                this.f9044c.setArguments(bundle2);
                getSupportFragmentManager().mo1844a().m1981c(R.id.comentFragmentContainer, this.f9044c, "commentFragment").mo1794h();
            } else {
                this.f9044c = (PhotoCommentFragment) getSupportFragmentManager().mo1848e("commentFragment");
            }
            if (this.f9044c != null) {
                if (stringExtra.equals("chatDialogActivity")) {
                    this.f9044c.m9050o2(true);
                }
                this.f9044c.m9016C2(Boolean.valueOf(this.f9053l));
                PhotoCommentFragment photoCommentFragment2 = this.f9044c;
                photoCommentFragment2.m9052p2(photoCommentFragment2);
                Group group = this.f9050i;
                if (group != null) {
                    this.f9044c.m9022G2(group.f13720g);
                } else {
                    UserInfo userInfoM16497V0 = CLUtility.m16497V0(m10014m1());
                    if (userInfoM16497V0 != null) {
                        this.f9044c.m9022G2(userInfoM16497V0.f13787l);
                    } else {
                        this.f9044c.m9022G2("UserHidden:" + Globals.m7388i0().m7568k1());
                    }
                }
                if (stringExtra2 != null) {
                    this.f9044c.m9014B2(this.f9054m, longExtra, stringExtra2);
                }
            }
            if (this.f9057p != null) {
                SparseArray<C2973l0> sparseArray = new SparseArray<>();
                if (!booleanExtra) {
                    List listM7656c = Globals.C1408b.m7655b().m7656c(this.f9057p);
                    if (listM7656c == null || listM7656c.size() <= 0) {
                        iM14723t = 0;
                    } else {
                        sparseArray = (SparseArray) listM7656c.get(0);
                        iM14723t = C2950b0.m14914m().m14723t(this.f9057p);
                    }
                } else if (booleanExtra2) {
                    sparseArray.put(0, C2950b0.m14914m().m14725v(longExtra));
                    iM14723t = 1;
                } else {
                    sparseArray = m10011j1(TopicContentActivity.m10338p3());
                    iM14723t = sparseArray.size();
                }
                int i9 = 0;
                while (true) {
                    if (i9 >= sparseArray.size()) {
                        break;
                    }
                    if (sparseArray.valueAt(i9).m15144p() == longExtra) {
                        iKeyAt = sparseArray.keyAt(i9);
                        break;
                    }
                    i9++;
                }
                m10005B1(sparseArray, iKeyAt, iM14723t);
            }
        } else {
            View viewFindViewById = findViewById(R.id.cancel);
            this.f9047f = viewFindViewById;
            viewFindViewById.setVisibility(0);
            this.f9047f.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.rf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f11146b.m10002t1(view);
                }
            });
            if (longExtra != -1) {
                C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(longExtra);
                SparseArray<C2973l0> sparseArray2 = new SparseArray<>();
                sparseArray2.put(0, c2973l0M14725v);
                m10005B1(sparseArray2, 0, sparseArray2.size());
            }
        }
        if (!intent.getBooleanExtra("commentTextExist", true)) {
            C5187v0.m20267c(R.string.error_media_comment_mediaInfo_404);
        }
        C5321e.m20824o().m20875k(this.f9064w);
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        C5321e.m20824o().m20832B0(this.f9064w);
        TouchViewPager touchViewPager = this.f9046e;
        if (touchViewPager != null) {
            touchViewPager.m3176I(this.f9065x);
            this.f9046e.setAdapter(null);
            this.f9046e = null;
        }
        this.f9044c = null;
        System.gc();
    }

    /* renamed from: v1 */
    public final void m10017v1(Group group) {
        Intent intent = new Intent(m10014m1(), (Class<?>) ChatDialogActivity.class);
        intent.putExtra("Group", group);
        startActivity(intent);
    }

    /* renamed from: w1 */
    public final Bitmap m10018w1(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap != null && bitmap2 != null) {
            try {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
                if (bitmap.getWidth() != bitmap2.getWidth() || bitmap.getHeight() != bitmap2.getHeight()) {
                    new Matrix().postScale(bitmap2.getWidth() / bitmap.getWidth(), bitmap2.getHeight() / bitmap.getHeight());
                    bitmap2 = Bitmap.createScaledBitmap(bitmap2, bitmap.getWidth(), bitmap.getHeight(), false);
                }
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                canvas.drawBitmap(bitmap, new Matrix(), null);
                canvas.drawBitmap(bitmap2, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (Paint) null);
                return bitmapCreateBitmap;
            } catch (OutOfMemoryError e9) {
                e9.printStackTrace();
            }
        }
        return null;
    }

    /* renamed from: x1 */
    public void m10019x1(C2973l0 c2973l0) {
        PhotoCommentFragment photoCommentFragment;
        if (c2973l0 == null || (photoCommentFragment = this.f9044c) == null) {
            return;
        }
        photoCommentFragment.m9062u2(c2973l0);
    }

    /* renamed from: y1 */
    public void m10020y1(long j9) throws Throwable {
        C2973l0 c2973l0M10029f = this.f9045d.m10029f(this.f9046e.getCurrentItem());
        if (c2973l0M10029f == null || c2973l0M10029f.m15144p() != j9) {
            return;
        }
        String strM20722e = this.f9058q.m20722e(c2973l0M10029f.m15144p());
        String strM15225n = C2950b0.m14920s().m15225n(c2973l0M10029f.m15144p());
        if (strM15225n == null) {
            m10021z1();
        } else {
            if (strM15225n.equals(strM20722e)) {
                return;
            }
            m10008S0();
        }
    }

    /* renamed from: z1 */
    public void m10021z1() {
        Bitmap bitmap;
        TouchImageView touchImageView = this.f9046e.f14275k0;
        if (touchImageView == null || (bitmap = (Bitmap) touchImageView.getTag(R.id.tag_original_photo)) == null) {
            return;
        }
        this.f9046e.f14275k0.setImageBitmap(bitmap);
    }
}
