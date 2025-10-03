package p086h4;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.StickerPackSettingActivity;
import com.cyberlink.you.activity.StickerShopActivity;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.sticker.StickerViewPager;
import com.cyberlink.you.sticker.view.GifImageView;
import com.cyberlink.you.utility.C3197a;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.LoadImageUtils;
import com.cyberlink.you.utility.ULogUtility;
import com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView;
import com.cyberlink.you.widgetpool.clhorizontalgridview.HorizontalGridView;
import com.rockerhieu.emojicon.emoji.Emojicon;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import p086h4.C4997c;
import p086h4.C5020x;
import p116k4.C5187v0;
import p201t3.C6288b;
import p218v2.C6456d;

/* renamed from: h4.s */
/* loaded from: classes.dex */
public class C5015s extends Fragment {

    /* renamed from: A */
    public RelativeLayout f17259A;

    /* renamed from: B */
    public ProgressBar f17260B;

    /* renamed from: C */
    public RelativeLayout f17261C;

    /* renamed from: D */
    public Button f17262D;

    /* renamed from: b */
    public StickerObj f17269b;

    /* renamed from: c */
    public g f17270c;

    /* renamed from: g */
    public int f17274g;

    /* renamed from: k */
    public C4998c0 f17278k;

    /* renamed from: l */
    public C5004h f17279l;

    /* renamed from: m */
    public C5006j f17280m;

    /* renamed from: n */
    public HorizontalGridView f17281n;

    /* renamed from: o */
    public View f17282o;

    /* renamed from: p */
    public StickerViewPager f17283p;

    /* renamed from: q */
    public View f17284q;

    /* renamed from: r */
    public FriendsClient f17285r;

    /* renamed from: s */
    public C4997c f17286s;

    /* renamed from: t */
    public C5020x f17287t;

    /* renamed from: u */
    public AbstractC5005i f17288u;

    /* renamed from: v */
    public Dialog f17289v;

    /* renamed from: w */
    public C5002f f17290w;

    /* renamed from: x */
    public AbstractC5003g f17291x;

    /* renamed from: y */
    public C4993a f17292y;

    /* renamed from: z */
    public StickerPackObj f17293z;

    /* renamed from: d */
    public boolean f17271d = false;

    /* renamed from: e */
    public boolean f17272e = false;

    /* renamed from: f */
    public boolean f17273f = false;

    /* renamed from: h */
    public boolean f17275h = false;

    /* renamed from: i */
    public boolean f17276i = false;

    /* renamed from: j */
    public boolean f17277j = false;

    /* renamed from: E */
    public C5020x.c f17263E = new a();

    /* renamed from: F */
    public C4997c.c f17264F = new b();

    /* renamed from: G */
    public View.OnClickListener f17265G = new View.OnClickListener() { // from class: h4.m
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f17254b.m19467Z(view);
        }
    };

    /* renamed from: H */
    public AdapterView.InterfaceC3229e f17266H = new c();

    /* renamed from: I */
    public View.OnClickListener f17267I = new e();

    /* renamed from: J */
    public ViewPager.InterfaceC0557j f17268J = new f();

    /* renamed from: h4.s$a */
    public class a implements C5020x.c {
        public a() {
        }

        @Override // p086h4.C5020x.c
        /* renamed from: a */
        public void mo19521a(StickerObj stickerObj, View view) throws IOException {
            C5015s.this.m19510l0(stickerObj, view);
        }

        @Override // p086h4.C5020x.c
        /* renamed from: b */
        public void mo19522b() {
            m19526f();
        }

        @Override // p086h4.C5020x.c
        /* renamed from: c */
        public void mo19523c() {
            C5015s.this.m19497R();
        }

        @Override // p086h4.C5020x.c
        /* renamed from: d */
        public void mo19524d(List<StickerPackObj> list) {
            List<AbstractC5003g> listM19438d = AbstractC5003g.m19438d(list, C5015s.this.getActivity());
            C5015s.this.f17279l.addAll(listM19438d);
            if (C5015s.this.f17273f) {
                C5015s.this.f17279l.add(AbstractC5003g.m19437c(C5015s.this.getActivity()));
            }
            C5015s.this.f17279l.notifyDataSetChanged();
            if (C5015s.this.f17291x == null) {
                C5015s.this.f17291x = listM19438d.get(0);
                C5015s c5015s = C5015s.this;
                c5015s.f17293z = c5015s.f17291x.m19439e();
            }
        }

        @Override // p086h4.C5020x.c
        /* renamed from: e */
        public void mo19525e() {
            if (C5015s.this.f17291x instanceof C4999d) {
                C5015s c5015s = C5015s.this;
                c5015s.m19520v0(c5015s.f17291x);
                C5015s c5015s2 = C5015s.this;
                c5015s2.m19519u0(c5015s2.f17279l.m19444d());
                C5015s.this.f17287t.m19554C("-999");
            }
        }

        /* renamed from: f */
        public final void m19526f() {
            int iM19446f = C5015s.this.f17279l.m19446f(C5015s.this.f17291x);
            if (iM19446f != -1) {
                C5015s.this.m19519u0(iM19446f);
                C5015s c5015s = C5015s.this;
                c5015s.m19520v0(c5015s.f17291x);
                return;
            }
            int iM19447g = C5015s.this.f17279l.m19447g();
            if (iM19447g != -1) {
                C5015s.this.m19519u0(iM19447g);
                C5015s c5015s2 = C5015s.this;
                c5015s2.m19520v0(c5015s2.f17291x);
            } else {
                if (C5015s.this.f17279l.getCount() <= 0 || !C5015s.this.f17271d) {
                    return;
                }
                if (!C5015s.this.f17275h && !C5015s.this.f17276i) {
                    C5015s.this.f17277j = true;
                    return;
                }
                C5015s.this.m19519u0(0);
                C5015s c5015s3 = C5015s.this;
                c5015s3.m19520v0(c5015s3.f17291x);
            }
        }
    }

    /* renamed from: h4.s$b */
    public class b implements C4997c.c {
        public b() {
        }

        @Override // p086h4.C4997c.c
        /* renamed from: a */
        public void mo19405a(Emojicon emojicon) {
            if (C5015s.this.f17270c != null) {
                C5015s.this.f17270c.mo7980b(emojicon);
            }
        }

        @Override // p086h4.C4997c.c
        /* renamed from: b */
        public void mo19406b() {
            C5015s.this.f17275h = true;
            C5015s.this.m19494O();
        }
    }

    /* renamed from: h4.s$c */
    public class c implements AdapterView.InterfaceC3229e {
        public c() {
        }

        @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView.InterfaceC3229e
        /* renamed from: a */
        public void mo17210a(AdapterView<?> adapterView, View view, int i9, long j9) {
            if (C5015s.this.getActivity() == null) {
                return;
            }
            C5015s.this.m19519u0(i9);
            AbstractC5003g abstractC5003g = (AbstractC5003g) adapterView.getAdapter().getItem(i9);
            if (abstractC5003g instanceof C5007k) {
                C5015s.this.getActivity().startActivityForResult(new Intent(C5015s.this.getActivity(), (Class<?>) StickerPackSettingActivity.class), 5050);
                return;
            }
            C5015s.this.m19520v0(abstractC5003g);
            if (abstractC5003g == null || abstractC5003g.m19439e() == null || !(abstractC5003g.m19439e().m14811o() == StickerPackObj.Status.NOT_DOWNLOADED || abstractC5003g.m19439e().m14811o() == StickerPackObj.Status.NONE)) {
                C5015s.this.m19514p0();
            } else {
                C5015s.this.m19495P();
            }
            if (abstractC5003g != null) {
                C5015s.this.f17293z = abstractC5003g.m19439e();
                C5015s.this.m19518t0();
            }
        }
    }

    /* renamed from: h4.s$d */
    public class d implements C5020x.d {

        /* renamed from: a */
        public final /* synthetic */ C3197a f17297a;

        public d(C3197a c3197a) {
            this.f17297a = c3197a;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m19528d() {
            C5015s.this.m19512n0(true);
        }

        @Override // p086h4.C5020x.d
        /* renamed from: a */
        public void mo19529a(List<StickerObj> list) {
            if (this.f17297a.m16986h()) {
                return;
            }
            this.f17297a.m16993p();
        }

        @Override // p086h4.C5020x.d
        /* renamed from: b */
        public void mo19530b() {
            ULogUtility.m16676l("StickerFragment", "getStickerListAsync failed");
            FragmentActivity activity = C5015s.this.getActivity();
            if (activity != null) {
                activity.runOnUiThread(new Runnable() { // from class: h4.t
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f17301b.m19528d();
                    }
                });
            }
        }
    }

    /* renamed from: h4.s$e */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!C6456d.m24714D().m24748G()) {
                C5187v0.m20267c(R.string.error_no_network);
                return;
            }
            C5015s.this.f17261C.setVisibility(8);
            C5015s.this.f17259A.setVisibility(0);
            C5015s.this.m19495P();
        }
    }

    /* renamed from: h4.s$f */
    public class f implements ViewPager.InterfaceC0557j {
        public f() {
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageScrollStateChanged(int i9) {
            if (i9 != 0 || C5015s.this.f17293z == null) {
                return;
            }
            if (C5015s.this.f17293z.m14811o() == StickerPackObj.Status.NOT_DOWNLOADED || C5015s.this.f17293z.m14811o() == StickerPackObj.Status.NONE) {
                C5015s.this.m19495P();
            } else {
                C5015s.this.m19514p0();
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageScrolled(int i9, float f9, int i10) {
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageSelected(int i9) {
            C5015s.this.f17288u.mo19402e(i9);
            C4996b0 c4996b0 = (C4996b0) C5015s.this.f17283p.getAdapter();
            if (c4996b0 != null && !c4996b0.m19386h()) {
                c4996b0.m19380b(i9, C5015s.this.f17269b);
            }
            if ((C5015s.this.f17291x instanceof C5018v) || (C5015s.this.f17291x instanceof C4999d)) {
                int iM19443c = C5015s.this.f17279l.m19443c(((C4996b0) C5015s.this.f17283p.getAdapter()).m19383e(i9));
                if (iM19443c != -1) {
                    C5015s.this.m19519u0(iM19443c);
                }
            }
            C5015s c5015s = C5015s.this;
            c5015s.f17292y = ((C4996b0) c5015s.f17283p.getAdapter()).m19384f(i9);
            C5015s c5015s2 = C5015s.this;
            c5015s2.f17293z = c5015s2.f17292y.m19372e();
            C5015s.this.m19518t0();
        }
    }

    /* renamed from: h4.s$g */
    public interface g {
        /* renamed from: a */
        void mo7979a(StickerObj stickerObj);

        /* renamed from: b */
        void mo7980b(Emojicon emojicon);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X */
    public /* synthetic */ void m19465X(List list) {
        Log.d("StickerFragment", "getMyStickerPack onComplete");
        m19500U();
    }

    /* renamed from: Y */
    public static /* synthetic */ void m19466Y(String str) {
        Log.e("StickerFragment", "getMyStickerPack error:" + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Z */
    public /* synthetic */ void m19467Z(View view) {
        if (getActivity() == null) {
            return;
        }
        getActivity().startActivityForResult(new Intent(getActivity(), (Class<?>) StickerShopActivity.class), 2000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a0 */
    public /* synthetic */ void m19468a0(DialogInterface dialogInterface) {
        this.f17284q.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b0 */
    public /* synthetic */ void m19469b0(DialogInterface dialogInterface) {
        this.f17284q.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c0 */
    public /* synthetic */ void m19470c0(View view) {
        g gVar = this.f17270c;
        if (gVar != null) {
            gVar.mo7979a(this.f17269b);
        }
        this.f17289v.dismiss();
        if (this.f17290w.m19431f()) {
            this.f17290w.m19426a(this.f17269b);
            m19500U();
            Log.d("StickerFragment", "onStickerPreviewClick");
        } else {
            this.f17290w.m19426a(this.f17269b);
        }
        this.f17287t.m19552A();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d0 */
    public /* synthetic */ void m19471d0(int i9) {
        this.f17279l.m19448h(i9);
        this.f17279l.notifyDataSetChanged();
        this.f17281n.m17116F0(i9);
    }

    /* renamed from: M */
    public final void m19492M() {
        this.f17279l.clear();
        if (this.f17271d) {
            this.f17279l.add(AbstractC5003g.m19435a(getActivity()));
        }
        if (!this.f17290w.m19431f()) {
            AbstractC5003g abstractC5003gM19436b = AbstractC5003g.m19436b(getActivity());
            this.f17279l.add(abstractC5003gM19436b);
            this.f17291x = abstractC5003gM19436b;
        }
        this.f17279l.notifyDataSetChanged();
    }

    /* renamed from: N */
    public void m19493N() {
        Log.d("StickerFragment", "checkAndResetSticker");
        if (m19502W()) {
            m19509k0();
        }
    }

    /* renamed from: O */
    public final void m19494O() {
        if (this.f17277j) {
            if (this.f17275h || this.f17276i) {
                this.f17277j = false;
                m19519u0(0);
                m19503e0();
            }
        }
    }

    /* renamed from: P */
    public void m19495P() {
        ViewPager viewPager;
        C4993a c4993aM19384f;
        StickerPackObj stickerPackObjM19372e;
        C4998c0 c4998c0 = this.f17278k;
        if (c4998c0 == null || (viewPager = c4998c0.f17224e) == null) {
            return;
        }
        C4996b0 c4996b0 = (C4996b0) viewPager.getAdapter();
        int currentItem = this.f17278k.f17224e.getCurrentItem();
        if (c4996b0 == null || (c4993aM19384f = c4996b0.m19384f(currentItem)) == null || (stickerPackObjM19372e = c4993aM19384f.m19372e()) == null) {
            return;
        }
        if (stickerPackObjM19372e.m14811o() == StickerPackObj.Status.NOT_DOWNLOADED || stickerPackObjM19372e.m14811o() == StickerPackObj.Status.NONE) {
            C3197a c3197aM19370c = c4993aM19384f.m19370c();
            if (c3197aM19370c != null) {
                this.f17283p.setPagingEnabled(false);
                this.f17283p.setVisibility(8);
                this.f17259A.setVisibility(0);
                this.f17280m.m19452c(8);
                this.f17261C.setVisibility(8);
                if (c3197aM19370c.m16986h()) {
                    Log.i("StickerFragment", "current pack is downloading, skip it.");
                    return;
                } else {
                    this.f17287t.m19558w(stickerPackObjM19372e, new d(c3197aM19370c));
                    return;
                }
            }
            this.f17283p.setPagingEnabled(false);
            this.f17283p.setVisibility(8);
            this.f17259A.setVisibility(0);
            this.f17280m.m19452c(8);
            this.f17261C.setVisibility(8);
            if (stickerPackObjM19372e.m14813q()) {
                return;
            }
            this.f17262D.setOnClickListener(c4993aM19384f.m19369b());
            m19512n0(false);
        }
    }

    /* renamed from: Q */
    public AbstractC5003g m19496Q() {
        return this.f17291x;
    }

    /* renamed from: R */
    public final void m19497R() {
        C5000d0.m19411b(new C6288b.d() { // from class: h4.n
            @Override // p201t3.C6288b.d
            public final void onComplete(Object obj) {
                this.f17255a.m19465X((List) obj);
            }
        }, new C6288b.h() { // from class: h4.o
            @Override // p201t3.C6288b.h
            public final void onError(String str) {
                C5015s.m19466Y(str);
            }
        }).m24088p();
    }

    /* renamed from: S */
    public StickerObj m19498S() {
        return this.f17269b;
    }

    /* renamed from: T */
    public final void m19499T(View view) {
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.stickerShopImageButton);
        view.findViewById(R.id.stickerShopImageButton).setVisibility(0);
        View viewFindViewById = view.findViewById(R.id.seperator);
        viewFindViewById.setVisibility(0);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewFindViewById.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.addRule(0, R.id.stickerShopImageButton);
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.f17281n.getLayoutParams();
        if (layoutParams2 != null) {
            layoutParams2.addRule(0, R.id.seperator);
        }
        imageButton.setOnClickListener(this.f17265G);
    }

    /* renamed from: U */
    public final void m19500U() {
        Log.d("StickerFragment", "initStickers");
        m19492M();
        this.f17287t.m19553B();
    }

    /* renamed from: V */
    public final void m19501V(View view) {
        C4998c0 c4998c0 = new C4998c0();
        this.f17278k = c4998c0;
        c4998c0.f17220a = view;
        c4998c0.f17223d = this.f17282o;
        c4998c0.f17222c = this.f17281n;
        c4998c0.f17221b = this.f17280m;
        c4998c0.f17225f = this.f17284q;
        c4998c0.f17224e = this.f17283p;
    }

    /* renamed from: W */
    public final boolean m19502W() {
        int size;
        List<StickerPackObj> listM15294l = C2950b0.m14925x().m15294l();
        if (listM15294l == null || this.f17279l.m19445e() != (size = listM15294l.size())) {
            return true;
        }
        int iM19447g = this.f17279l.m19447g();
        if (iM19447g == -1) {
            return size > 0;
        }
        int i9 = size + iM19447g;
        while (iM19447g < i9) {
            StickerPackObj stickerPackObjM19439e = ((AbstractC5003g) this.f17279l.getItem(iM19447g)).m19439e();
            if (stickerPackObjM19439e == null) {
                Log.e("StickerFragment", "[isStickerDBChanged] StickerPackObj is null");
                return true;
            }
            StickerPackObj stickerPackObjM15293k = C2950b0.m14925x().m15293k(stickerPackObjM19439e.m14803g());
            if (stickerPackObjM15293k != null && (!stickerPackObjM19439e.m14811o().equals(stickerPackObjM15293k.m14811o()) || stickerPackObjM19439e.m14815s() != stickerPackObjM15293k.m14815s())) {
                return true;
            }
            iM19447g++;
        }
        return false;
    }

    /* renamed from: e0 */
    public final void m19503e0() {
        m19508j0();
        this.f17287t.m19554C("-1");
    }

    /* renamed from: f0 */
    public final void m19504f0() {
        m19508j0();
        this.f17287t.m19554C("-999");
    }

    /* renamed from: g0 */
    public void m19505g0(long j9) {
        int iM19443c = this.f17279l.m19443c(j9);
        if (iM19443c != -1) {
            m19519u0(iM19443c);
            AbstractC5003g abstractC5003g = (AbstractC5003g) this.f17279l.getItem(iM19443c);
            if (abstractC5003g != null) {
                m19520v0(abstractC5003g);
            }
            m19506h0(j9);
        }
    }

    /* renamed from: h0 */
    public final void m19506h0(long j9) {
        m19508j0();
        this.f17287t.m19556E(j9);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x008b  */
    /* renamed from: i0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m19507i0(int[] iArr, Pair<Integer, Integer> pair, StickerObj stickerObj) throws IOException {
        if (getActivity() == null) {
            return;
        }
        Dialog dialog = this.f17289v;
        if (dialog != null) {
            dialog.dismiss();
            this.f17289v = null;
        }
        StickerPackObj stickerPackObjM15293k = C2950b0.m14925x().m15293k(stickerObj.m16284i());
        if (stickerPackObjM15293k == null) {
            ULogUtility.m16670f("StickerFragment", "openStickerPreview method stickerPackObj is null");
            return;
        }
        Dialog dialog2 = new Dialog(getActivity(), R.style.FriendSelectorDialog);
        this.f17289v = dialog2;
        dialog2.setOnShowListener(new DialogInterface.OnShowListener() { // from class: h4.p
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                this.f17256a.m19468a0(dialogInterface);
            }
        });
        this.f17289v.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: h4.q
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f17257b.m19469b0(dialogInterface);
            }
        });
        boolean z8 = true;
        this.f17289v.requestWindowFeature(1);
        this.f17289v.getWindow().clearFlags(2);
        WindowManager.LayoutParams attributes = this.f17289v.getWindow().getAttributes();
        attributes.gravity = 51;
        attributes.x = iArr[0];
        attributes.y = iArr[1] - CLUtility.m16533f1(getActivity());
        String strM14805i = stickerPackObjM15293k.m14805i();
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: h4.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f17258b.m19470c0(view);
            }
        };
        strM14805i.hashCode();
        switch (strM14805i.hashCode()) {
            case -1808614770:
                if (!strM14805i.equals("Static")) {
                    z8 = -1;
                    break;
                } else {
                    z8 = false;
                    break;
                }
            case -1172489372:
                if (!strM14805i.equals("Animation")) {
                }
                break;
            case 1338216485:
                if (strM14805i.equals("AnimationPNG")) {
                    z8 = 2;
                    break;
                }
                break;
        }
        switch (z8) {
            case false:
                this.f17289v.setContentView(R.layout.dialog_static_sticker_preview);
                ImageView imageView = (ImageView) this.f17289v.findViewById(R.id.staticSticker);
                imageView.getLayoutParams().width = Math.round(((Integer) pair.first).intValue() * 1.5f);
                imageView.getLayoutParams().height = Math.round(((Integer) pair.second).intValue() * 1.5f);
                imageView.setOnClickListener(onClickListener);
                File file = new File(stickerObj.m16282g());
                if (!file.getPath().equals("_") && file.exists()) {
                    try {
                        bitmapDecodeFile = BitmapFactory.decodeFile(file.getPath());
                    } catch (Exception e9) {
                        e9.printStackTrace();
                    } catch (OutOfMemoryError e10) {
                        e10.printStackTrace();
                    }
                    imageView.setImageBitmap(bitmapDecodeFile);
                    break;
                } else {
                    LoadImageUtils.m16639x(Globals.m7388i0(), stickerObj, imageView);
                    break;
                }
            case true:
                this.f17289v.setContentView(R.layout.dialog_anim_sticker_preview);
                GifImageView gifImageView = (GifImageView) this.f17289v.findViewById(R.id.animSticker);
                gifImageView.getLayoutParams().width = Math.round(((Integer) pair.first).intValue() * 1.5f);
                gifImageView.getLayoutParams().height = Math.round(((Integer) pair.second).intValue() * 1.5f);
                gifImageView.setOnClickListener(onClickListener);
                File file2 = new File(stickerObj.m16282g());
                if (!file2.getPath().equals("_") && file2.exists()) {
                    gifImageView.setGifImage(file2);
                    break;
                } else {
                    File file3 = new File(CLUtility.m16541h1(stickerObj.m16284i()));
                    bitmapDecodeFile = (file3.exists() || file3.mkdir()) ? file3 : null;
                    if (bitmapDecodeFile == null) {
                        Log.e("StickerFragment", "Cannot create dstFolder");
                        break;
                    } else {
                        LoadImageUtils.m16635t(stickerObj.m16283h(), gifImageView, bitmapDecodeFile + File.separator + stickerObj.m16285j());
                        break;
                    }
                }
                break;
            case true:
                this.f17289v.setContentView(R.layout.dialog_animpng_sticker_preview);
                ImageView imageView2 = (ImageView) this.f17289v.findViewById(R.id.animpngSticker);
                imageView2.getLayoutParams().width = Math.round(((Integer) pair.first).intValue() * 1.5f);
                imageView2.getLayoutParams().height = Math.round(((Integer) pair.second).intValue() * 1.5f);
                imageView2.setOnClickListener(onClickListener);
                LoadImageUtils.m16634s(getActivity(), this.f17269b, imageView2, false);
                break;
        }
        this.f17289v.show();
    }

    /* renamed from: j0 */
    public void m19508j0() {
        this.f17288u.m19449c();
        this.f17276i = true;
        m19494O();
    }

    /* renamed from: k0 */
    public final void m19509k0() {
        Log.d("StickerFragment", "resetStickers");
        m19500U();
        this.f17281n.m17226c1(17);
    }

    /* renamed from: l0 */
    public final void m19510l0(StickerObj stickerObj, View view) throws IOException {
        this.f17269b = stickerObj;
        if (stickerObj == null) {
            return;
        }
        if (this.f17274g == 0) {
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            m19507i0(iArr, Pair.create(Integer.valueOf(view.getWidth()), Integer.valueOf(view.getHeight())), this.f17269b);
            return;
        }
        C4996b0 c4996b0 = (C4996b0) this.f17283p.getAdapter();
        if (c4996b0 != null) {
            c4996b0.m19391m(this.f17283p.getCurrentItem(), this.f17269b);
        }
        g gVar = this.f17270c;
        if (gVar != null) {
            gVar.mo7979a(this.f17269b);
        }
    }

    /* renamed from: m0 */
    public void m19511m0(g gVar) {
        this.f17270c = gVar;
    }

    /* renamed from: n0 */
    public void m19512n0(boolean z8) {
        if (z8) {
            this.f17262D.setOnClickListener(this.f17267I);
        }
        this.f17259A.setVisibility(8);
        this.f17261C.setVisibility(0);
        this.f17283p.setPagingEnabled(false);
        this.f17283p.setVisibility(8);
        this.f17280m.m19452c(8);
    }

    /* renamed from: o0 */
    public void m19513o0() {
        this.f17259A.setVisibility(0);
        this.f17261C.setVisibility(8);
        this.f17283p.setPagingEnabled(false);
        this.f17283p.setVisibility(8);
        this.f17280m.m19452c(8);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        Log.d("StickerFragment", "onActivityResult");
        if (i9 == 2) {
            m19493N();
            return;
        }
        if (i9 == 3) {
            m19493N();
            return;
        }
        if (i9 != 2000) {
            if (i9 == 5050 && intent != null && -1 == i10 && intent.getBooleanExtra("isChanged", false)) {
                m19509k0();
                return;
            }
            return;
        }
        if (intent == null) {
            m19493N();
        } else if (-1 == i10 && intent.getBooleanExtra("isChanged", false)) {
            m19509k0();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        Log.d("StickerFragment", "onCreate");
        super.onCreate(bundle);
        this.f17271d = getArguments().getBoolean("Emoji", false);
        this.f17272e = getArguments().getBoolean("Shop", false);
        this.f17273f = getArguments().getBoolean("Setting", false);
        this.f17274g = getArguments().getInt("Mode", 0) != 0 ? 1 : 0;
        this.f17285r = new FriendsClient();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_stickerv2, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.f17287t.m19555D();
        this.f17285r.m15717U0();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z8) {
        super.onHiddenChanged(z8);
        if (z8) {
            return;
        }
        m19495P();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Log.d("StickerFragment", "onResume");
        this.f17287t.m19552A();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) throws IOException {
        Log.d("StickerFragment", "onViewCreated");
        this.f17281n = (HorizontalGridView) view.findViewById(R.id.stickerListGridView);
        StickerViewPager stickerViewPager = (StickerViewPager) view.findViewById(R.id.viewpager);
        this.f17283p = stickerViewPager;
        stickerViewPager.setOnPageChangeListener(this.f17268J);
        this.f17282o = view.findViewById(R.id.emojiListGridView);
        this.f17284q = view.findViewById(R.id.stickerInnerMask);
        this.f17280m = new C5006j((LinearLayout) view.findViewById(R.id.pageIndexContainer));
        this.f17259A = (RelativeLayout) view.findViewById(R.id.downloadStickerProgressLayout);
        this.f17260B = (ProgressBar) view.findViewById(R.id.downloadStickerProgress);
        this.f17261C = (RelativeLayout) view.findViewById(R.id.redownloadLayout);
        Button button = (Button) view.findViewById(R.id.btnDownload);
        this.f17262D = button;
        button.setOnClickListener(this.f17267I);
        m19501V(view);
        this.f17286s = new C4997c(this.f17278k, getActivity(), this.f17264F);
        C5020x c5020x = new C5020x(this, this.f17263E, this.f17278k);
        this.f17287t = c5020x;
        this.f17288u = c5020x;
        CLUtility.m16423C();
        this.f17290w = new C5002f(getActivity());
        this.f17275h = false;
        this.f17276i = false;
        if (this.f17271d) {
            this.f17286s.m19403j();
        }
        if (this.f17272e) {
            m19499T(view);
        }
        C5004h c5004h = new C5004h(getActivity(), android.R.layout.simple_list_item_1, new ArrayList());
        this.f17279l = c5004h;
        this.f17281n.setAdapter((ListAdapter) c5004h);
        this.f17281n.setOnItemClickListener(this.f17266H);
        m19500U();
    }

    /* renamed from: p0 */
    public void m19514p0() {
        this.f17283p.setPagingEnabled(true);
        this.f17283p.setVisibility(0);
        this.f17259A.setVisibility(8);
        this.f17261C.setVisibility(8);
        this.f17280m.m19452c(0);
    }

    /* renamed from: q0 */
    public void m19515q0(StickerPackObj stickerPackObj) {
        if (this.f17293z == null || stickerPackObj.m14803g() != this.f17293z.m14803g()) {
            return;
        }
        this.f17283p.setVisibility(0);
        this.f17259A.setVisibility(8);
        this.f17280m.m19452c(0);
        this.f17283p.setPagingEnabled(true);
    }

    /* renamed from: r0 */
    public void m19516r0(long j9) {
        int iM19443c;
        AbstractC5003g abstractC5003g;
        C5004h c5004h = this.f17279l;
        if (c5004h == null || (iM19443c = c5004h.m19443c(j9)) == -1 || (abstractC5003g = (AbstractC5003g) this.f17279l.getItem(iM19443c)) == null) {
            return;
        }
        this.f17293z = abstractC5003g.m19439e();
    }

    /* renamed from: s0 */
    public void m19517s0(StickerPackObj stickerPackObj) {
        ((AbstractC5003g) this.f17279l.getItem(this.f17279l.m19443c(stickerPackObj.m14803g()))).m19440f(stickerPackObj);
    }

    /* renamed from: t0 */
    public void m19518t0() {
        StickerPackObj stickerPackObj = this.f17293z;
        if (stickerPackObj != null) {
            this.f17260B.setProgress(stickerPackObj.m14798b());
        }
    }

    /* renamed from: u0 */
    public final void m19519u0(final int i9) {
        FragmentActivity activity;
        if (getActivity() == null || (((AbstractC5003g) this.f17279l.getItem(i9)) instanceof C5007k) || (activity = getActivity()) == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: h4.l
            @Override // java.lang.Runnable
            public final void run() {
                this.f17252b.m19471d0(i9);
            }
        });
    }

    /* renamed from: v0 */
    public final void m19520v0(AbstractC5003g abstractC5003g) {
        if (getActivity() == null) {
            return;
        }
        this.f17291x = abstractC5003g;
        if (abstractC5003g instanceof C4995b) {
            this.f17288u = this.f17286s;
            m19503e0();
            return;
        }
        if (abstractC5003g instanceof C5018v) {
            this.f17288u = this.f17287t;
            m19506h0(abstractC5003g.m19439e().m14803g());
        } else {
            if (abstractC5003g instanceof C4999d) {
                this.f17288u = this.f17287t;
                m19504f0();
                return;
            }
            this.f17282o.setVisibility(8);
            C4996b0 c4996b0 = (C4996b0) this.f17283p.getAdapter();
            if (c4996b0 != null) {
                c4996b0.m19381c();
            }
        }
    }
}
