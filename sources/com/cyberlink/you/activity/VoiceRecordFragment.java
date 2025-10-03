package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.VoiceRecordFragment;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import p116k4.C5140e0;
import p116k4.C5158k0;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;

/* loaded from: classes.dex */
public class VoiceRecordFragment extends Fragment {

    /* renamed from: F */
    public static String f9644F;

    /* renamed from: e */
    public boolean f9653e;

    /* renamed from: f */
    public boolean f9654f;

    /* renamed from: g */
    public ImageView f9655g;

    /* renamed from: h */
    public ImageView f9656h;

    /* renamed from: i */
    public ImageView f9657i;

    /* renamed from: j */
    public ImageView f9658j;

    /* renamed from: k */
    public ImageView f9659k;

    /* renamed from: l */
    public ImageView f9660l;

    /* renamed from: m */
    public ImageView f9661m;

    /* renamed from: n */
    public ViewGroup f9662n;

    /* renamed from: o */
    public ProgressBar f9663o;

    /* renamed from: p */
    public TextView f9664p;

    /* renamed from: q */
    public LinearLayout f9665q;

    /* renamed from: r */
    public LinearLayout f9666r;

    /* renamed from: v */
    public InterfaceC1861g f9670v;

    /* renamed from: b */
    public C5158k0 f9650b = null;

    /* renamed from: c */
    public C5140e0 f9651c = null;

    /* renamed from: d */
    public double f9652d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;

    /* renamed from: s */
    public Handler f9667s = new Handler();

    /* renamed from: t */
    public Runnable f9668t = new Runnable() { // from class: com.cyberlink.you.activity.tm
        @Override // java.lang.Runnable
        public final void run() {
            this.f11417b.m10969P();
        }
    };

    /* renamed from: u */
    public final Runnable f9669u = new RunnableC1855a();

    /* renamed from: w */
    public RecordState f9671w = RecordState.NONE;

    /* renamed from: x */
    public View.OnDragListener f9672x = new View.OnDragListener() { // from class: com.cyberlink.you.activity.um
        @Override // android.view.View.OnDragListener
        public final boolean onDrag(View view, DragEvent dragEvent) {
            return this.f11760a.m10970Q(view, dragEvent);
        }
    };

    /* renamed from: y */
    public View.OnDragListener f9673y = new ViewOnDragListenerC1856b();

    /* renamed from: z */
    public View.OnDragListener f9674z = new ViewOnDragListenerC1857c();

    /* renamed from: A */
    public View.OnLongClickListener f9645A = new ViewOnLongClickListenerC1858d();

    /* renamed from: B */
    public View.OnClickListener f9646B = new View.OnClickListener() { // from class: com.cyberlink.you.activity.vm
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11832b.m10971R(view);
        }
    };

    /* renamed from: C */
    public View.OnClickListener f9647C = new View.OnClickListener() { // from class: com.cyberlink.you.activity.wm
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f12239b.m10972S(view);
        }
    };

    /* renamed from: D */
    public View.OnClickListener f9648D = new View.OnClickListener() { // from class: com.cyberlink.you.activity.xm
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
            this.f12263b.m10973T(view);
        }
    };

    /* renamed from: E */
    public View.OnClickListener f9649E = new View.OnClickListener() { // from class: com.cyberlink.you.activity.ym
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) throws IllegalStateException {
            this.f12290b.m10974U(view);
        }
    };

    public enum RecordState {
        NONE,
        RECORDING,
        RECORD_FINISH
    }

    /* renamed from: com.cyberlink.you.activity.VoiceRecordFragment$a */
    public class RunnableC1855a implements Runnable {
        public RunnableC1855a() {
        }

        @Override // java.lang.Runnable
        public void run() throws IllegalStateException, IOException, IllegalArgumentException {
            Log.d("VoiceRecordFragment", "[Run]Start to record");
            if (VoiceRecordFragment.this.f9670v != null) {
                VoiceRecordFragment.this.f9670v.mo7976a();
            }
            VoiceRecordFragment.this.m11004Y(RecordState.RECORDING);
            VoiceRecordFragment.this.m11006a0();
        }
    }

    /* renamed from: com.cyberlink.you.activity.VoiceRecordFragment$b */
    public class ViewOnDragListenerC1856b implements View.OnDragListener {
        public ViewOnDragListenerC1856b() {
        }

        @Override // android.view.View.OnDragListener
        public boolean onDrag(View view, DragEvent dragEvent) throws IllegalStateException {
            switch (dragEvent.getAction()) {
                case 3:
                    VoiceRecordFragment.this.f9654f = true;
                    VoiceRecordFragment.this.m11008c0(true);
                case 1:
                case 2:
                case 4:
                    return true;
                case 5:
                    VoiceRecordFragment.this.f9659k.setSelected(true);
                    VoiceRecordFragment.this.f9658j.setSelected(true);
                    return true;
                case 6:
                    VoiceRecordFragment.this.f9659k.setSelected(false);
                    VoiceRecordFragment.this.f9658j.setSelected(false);
                    return true;
                default:
                    Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                    return false;
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.VoiceRecordFragment$c */
    public class ViewOnDragListenerC1857c implements View.OnDragListener {
        public ViewOnDragListenerC1857c() {
        }

        @Override // android.view.View.OnDragListener
        public boolean onDrag(View view, DragEvent dragEvent) throws IllegalStateException {
            switch (dragEvent.getAction()) {
                case 3:
                    VoiceRecordFragment.this.f9653e = true;
                    VoiceRecordFragment.this.m11008c0(true);
                case 1:
                case 2:
                case 4:
                    return true;
                case 5:
                    VoiceRecordFragment.this.f9661m.setSelected(true);
                    VoiceRecordFragment.this.f9660l.setSelected(true);
                    return true;
                case 6:
                    VoiceRecordFragment.this.f9661m.setSelected(false);
                    VoiceRecordFragment.this.f9660l.setSelected(false);
                    return true;
                default:
                    Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                    return false;
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.VoiceRecordFragment$d */
    public class ViewOnLongClickListenerC1858d implements View.OnLongClickListener {

        /* renamed from: com.cyberlink.you.activity.VoiceRecordFragment$d$a */
        public class a implements InterfaceC5288c {
            public a() {
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: a */
            public void mo6907a(boolean z8) {
                if (z8) {
                    C5183t0.m20262b(VoiceRecordFragment.this.getActivity(), Permission.MICROPHONE);
                } else {
                    C5187v0.m20267c(R.string.permission_denied);
                }
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: b */
            public void mo6908b() {
            }
        }

        public ViewOnLongClickListenerC1858d() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (VoiceRecordFragment.this.f9671w.equals(RecordState.RECORDING)) {
                return false;
            }
            Permission permission = Permission.MICROPHONE;
            if (!C5287b.m20579b(permission, VoiceRecordFragment.this.getActivity())) {
                C5287b.m20583f(permission, new a(), VoiceRecordFragment.this.getActivity());
                return true;
            }
            VoiceRecordFragment.this.f9655g.setImageResource(R.drawable.icon_recording_moving);
            view.startDrag(ClipData.newPlainText("dummyData", ""), new View.DragShadowBuilder(view), null, 0);
            VoiceRecordFragment.this.f9655g.setImageResource(R.drawable.btn_recording_n);
            return true;
        }
    }

    /* renamed from: com.cyberlink.you.activity.VoiceRecordFragment$e */
    public class C1859e implements C5140e0.a {
        public C1859e() {
        }

        /* renamed from: e */
        public static /* synthetic */ void m11017e(int i9, Activity activity) {
            if (i9 == 1) {
                activity.getWindow().addFlags(128);
            } else {
                activity.getWindow().clearFlags(128);
            }
        }

        @Override // p116k4.C5140e0.a
        /* renamed from: a */
        public void mo9127a(final int i9) {
            final FragmentActivity activity = VoiceRecordFragment.this.getActivity();
            if (activity == null) {
                return;
            }
            activity.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.bn
                @Override // java.lang.Runnable
                public final void run() {
                    VoiceRecordFragment.C1859e.m11017e(i9, activity);
                }
            });
        }

        @Override // p116k4.C5140e0.a
        /* renamed from: b */
        public void mo9128b() throws IllegalStateException {
            VoiceRecordFragment.this.m11007b0();
            VoiceRecordFragment.this.m11013h0(false);
            VoiceRecordFragment.this.m10995H();
        }

        @Override // p116k4.C5140e0.a
        /* renamed from: c */
        public void mo9129c(int i9) throws IllegalStateException {
            VoiceRecordFragment.this.m11007b0();
            VoiceRecordFragment.this.m11013h0(false);
            VoiceRecordFragment.this.m10995H();
        }
    }

    /* renamed from: com.cyberlink.you.activity.VoiceRecordFragment$f */
    public class C1860f implements C5158k0.d {
        public C1860f() {
        }

        /* renamed from: f */
        public static /* synthetic */ void m11020f(Activity activity) {
            activity.getWindow().addFlags(128);
        }

        /* renamed from: g */
        public static /* synthetic */ void m11021g(Activity activity) {
            activity.getWindow().clearFlags(128);
        }

        @Override // p116k4.C5158k0.d
        /* renamed from: a */
        public void mo11022a(int i9) {
            final FragmentActivity activity = VoiceRecordFragment.this.getActivity();
            if (i9 == 1) {
                if (activity != null) {
                    activity.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.cn
                        @Override // java.lang.Runnable
                        public final void run() {
                            VoiceRecordFragment.C1860f.m11020f(activity);
                        }
                    });
                    return;
                }
                return;
            }
            if (activity != null) {
                activity.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.dn
                    @Override // java.lang.Runnable
                    public final void run() {
                        VoiceRecordFragment.C1860f.m11021g(activity);
                    }
                });
            }
            if (VoiceRecordFragment.this.f9671w.equals(RecordState.RECORDING)) {
                VoiceRecordFragment.this.m11004Y(RecordState.RECORD_FINISH);
            }
            VoiceRecordFragment.this.m10995H();
            if (VoiceRecordFragment.this.f9653e) {
                VoiceRecordFragment.this.m10997J();
            } else if (VoiceRecordFragment.this.f9654f) {
                VoiceRecordFragment.this.m10996I();
            }
        }

        @Override // p116k4.C5158k0.d
        /* renamed from: b */
        public void mo11023b(int i9) throws IllegalStateException {
            if (i9 == 0) {
                VoiceRecordFragment.this.m11008c0(true);
                VoiceRecordFragment.this.m11004Y(RecordState.RECORD_FINISH);
                VoiceRecordFragment.this.m10995H();
            }
        }

        @Override // p116k4.C5158k0.d
        /* renamed from: c */
        public void mo11024c(int i9) throws IllegalStateException {
            VoiceRecordFragment.this.m11004Y(RecordState.NONE);
            VoiceRecordFragment.this.m11008c0(false);
        }
    }

    /* renamed from: com.cyberlink.you.activity.VoiceRecordFragment$g */
    public interface InterfaceC1861g {
        /* renamed from: a */
        void mo7976a();

        /* renamed from: b */
        void mo7977b(boolean z8);

        /* renamed from: c */
        void mo7978c(String str, String str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N */
    public /* synthetic */ void m10967N() throws IllegalStateException {
        if (!this.f9671w.equals(RecordState.RECORD_FINISH) || getActivity() == null) {
            return;
        }
        m11007b0();
        m11004Y(RecordState.NONE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O */
    public /* synthetic */ void m10968O() throws IllegalStateException {
        if (TextUtils.isEmpty(f9644F)) {
            return;
        }
        m11004Y(RecordState.NONE);
        m11007b0();
        InterfaceC1861g interfaceC1861g = this.f9670v;
        if (interfaceC1861g != null) {
            interfaceC1861g.mo7978c(f9644F, CLUtility.m16603x(this.f9652d));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P */
    public /* synthetic */ void m10969P() {
        m11014i0();
        m11011f0();
        m11010e0(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q */
    public /* synthetic */ boolean m10970Q(View view, DragEvent dragEvent) throws IllegalStateException {
        switch (dragEvent.getAction()) {
            case 1:
                this.f9667s.post(this.f9669u);
                return true;
            case 3:
                m11008c0(true);
            case 2:
                return true;
            case 4:
                C5158k0 c5158k0 = this.f9650b;
                if (c5158k0 != null && c5158k0.m20096j() == 1) {
                    m11008c0(true);
                }
                return true;
            case 5:
            case 6:
                return true;
            default:
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: R */
    public /* synthetic */ void m10971R(View view) {
        m10996I();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: S */
    public /* synthetic */ void m10972S(View view) {
        m10997J();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T */
    public /* synthetic */ void m10973T(View view) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        m11005Z();
        m11013h0(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: U */
    public /* synthetic */ void m10974U(View view) throws IllegalStateException {
        m11007b0();
        m11013h0(false);
    }

    /* renamed from: H */
    public final void m10995H() {
        this.f9667s.removeCallbacks(this.f9668t);
    }

    /* renamed from: I */
    public final void m10996I() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.zm
                @Override // java.lang.Runnable
                public final void run() throws IllegalStateException {
                    this.f12317b.m10967N();
                }
            });
        }
    }

    /* renamed from: J */
    public final void m10997J() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.an
                @Override // java.lang.Runnable
                public final void run() throws IllegalStateException {
                    this.f9732b.m10968O();
                }
            });
        }
    }

    /* renamed from: K */
    public final String m10998K() {
        if (getActivity() != null) {
            return CLUtility.m16488S0(getActivity(), Globals.m7388i0().m7449L());
        }
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/audiorecordtest.m4a";
    }

    /* renamed from: L */
    public final String m10999L(double d9) {
        long jRound = Math.round(d9);
        int i9 = ((int) (jRound / 60)) % 60;
        int i10 = ((int) jRound) % 60;
        if (i10 < 10) {
            return i9 + ":0" + i10;
        }
        return i9 + ":" + i10;
    }

    /* renamed from: M */
    public final String m11000M(long j9, long j10, boolean z8) {
        if (z8) {
            j10 = j9 - j10;
        }
        long j11 = j10 / 1000;
        int i9 = ((int) (j11 / 60)) % 60;
        int i10 = ((int) j11) % 60;
        if (i10 < 10) {
            return i9 + ":0" + i10;
        }
        return i9 + ":" + i10;
    }

    /* renamed from: V */
    public void m11001V() {
        m10996I();
    }

    /* renamed from: W */
    public final void m11002W() {
        this.f9664p.setText(m10999L(this.f9652d));
    }

    /* renamed from: X */
    public void m11003X(InterfaceC1861g interfaceC1861g) {
        this.f9670v = interfaceC1861g;
    }

    /* renamed from: Y */
    public final void m11004Y(RecordState recordState) {
        this.f9671w = recordState;
        if (recordState.equals(RecordState.NONE)) {
            this.f9654f = false;
            this.f9653e = false;
        }
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.sm
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11359b.m11015j0();
                }
            });
        }
    }

    /* renamed from: Z */
    public final void m11005Z() throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        m11007b0();
        C5140e0 c5140e0 = new C5140e0();
        this.f9651c = c5140e0;
        c5140e0.m20021m(new C1859e());
        this.f9651c.m20018j(f9644F);
        m11009d0();
    }

    /* renamed from: a0 */
    public final void m11006a0() throws IllegalStateException, IOException, IllegalArgumentException {
        C5158k0 c5158k0 = this.f9650b;
        if (c5158k0 != null) {
            c5158k0.m20103q(false);
        }
        C5158k0 c5158k02 = new C5158k0();
        this.f9650b = c5158k02;
        c5158k02.m20097k(new C1860f());
        String strM10998K = m10998K();
        f9644F = strM10998K;
        this.f9650b.m20101o(strM10998K);
        m11009d0();
    }

    /* renamed from: b0 */
    public final void m11007b0() throws IllegalStateException {
        C5140e0 c5140e0 = this.f9651c;
        if (c5140e0 == null) {
            return;
        }
        c5140e0.m20027s();
        this.f9651c = null;
    }

    /* renamed from: c0 */
    public final void m11008c0(boolean z8) throws IllegalStateException {
        C5158k0 c5158k0 = this.f9650b;
        if (c5158k0 != null) {
            if (z8) {
                c5158k0.m20103q(true);
                this.f9652d = this.f9650b.m20092f();
            } else {
                this.f9652d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            }
            this.f9650b = null;
            if (!z8) {
                f9644F = null;
            }
            if (this.f9652d >= 1.0d) {
                InterfaceC1861g interfaceC1861g = this.f9670v;
                if (interfaceC1861g != null) {
                    interfaceC1861g.mo7977b(z8);
                    return;
                }
                return;
            }
            if (getActivity() == null || !z8) {
                return;
            }
            C5187v0.m20267c(R.string.voices_too_short);
            m11004Y(RecordState.NONE);
        }
    }

    /* renamed from: d0 */
    public final void m11009d0() {
        m11010e0(false);
    }

    /* renamed from: e0 */
    public final void m11010e0(boolean z8) {
        Runnable runnable = this.f9668t;
        if (runnable != null) {
            if (z8) {
                this.f9667s.postDelayed(runnable, 500L);
            } else {
                this.f9667s.post(runnable);
            }
        }
    }

    /* renamed from: f0 */
    public final void m11011f0() {
        C5158k0 c5158k0 = this.f9650b;
        if (c5158k0 != null) {
            m11012g0(c5158k0.m20094h());
        } else {
            m11012g0(0);
        }
    }

    /* renamed from: g0 */
    public final void m11012g0(int i9) {
        int i10 = i9 / 2000;
        int childCount = this.f9665q.getChildCount();
        if (childCount < i10) {
            for (int i11 = 0; i11 < i10 - childCount; i11++) {
                if (getActivity() != null) {
                    ImageView imageView = new ImageView(getActivity());
                    ImageView imageView2 = new ImageView(getActivity());
                    imageView.setImageResource(R.drawable.voice_r);
                    imageView2.setImageResource(R.drawable.voice_l);
                    this.f9665q.addView(imageView);
                    this.f9666r.addView(imageView2);
                }
            }
        }
        for (int i12 = 0; i12 < this.f9665q.getChildCount(); i12++) {
            View childAt = this.f9665q.getChildAt(i12);
            View childAt2 = this.f9666r.getChildAt(i12);
            if (i12 < i10) {
                childAt.setVisibility(0);
                childAt2.setVisibility(0);
            } else {
                childAt.setVisibility(8);
                childAt2.setVisibility(8);
            }
        }
    }

    /* renamed from: h0 */
    public final void m11013h0(boolean z8) {
        if (z8) {
            this.f9656h.setVisibility(8);
            this.f9657i.setVisibility(0);
            this.f9663o.setVisibility(0);
        } else {
            this.f9656h.setVisibility(0);
            this.f9657i.setVisibility(8);
            this.f9663o.setVisibility(8);
            m11002W();
        }
    }

    /* renamed from: i0 */
    public final void m11014i0() {
        if (this.f9650b != null) {
            this.f9664p.setText(m11000M(r0.m20093g(), this.f9650b.m20091e(), false));
            this.f9663o.setProgress((int) Math.round(this.f9650b.m20095i() * 100.0d));
            return;
        }
        C5140e0 c5140e0 = this.f9651c;
        if (c5140e0 == null || c5140e0.m20014d() == -1) {
            return;
        }
        this.f9664p.setText(m11000M(this.f9651c.m20014d(), this.f9651c.m20013c(), true));
        this.f9663o.setProgress((int) Math.round(this.f9651c.m20015e() * 100.0d));
    }

    /* renamed from: j0 */
    public final void m11015j0() {
        if (this.f9671w.equals(RecordState.NONE)) {
            this.f9655g.setImageResource(R.drawable.btn_recording_n);
            this.f9655g.setVisibility(0);
            this.f9656h.setVisibility(8);
            this.f9657i.setVisibility(8);
            this.f9663o.setProgress(0);
            this.f9663o.setVisibility(8);
            this.f9659k.setVisibility(8);
            this.f9661m.setVisibility(8);
            this.f9658j.setVisibility(8);
            this.f9660l.setVisibility(8);
            this.f9659k.setSelected(false);
            this.f9661m.setSelected(false);
            this.f9658j.setSelected(false);
            this.f9660l.setSelected(false);
            this.f9665q.setVisibility(8);
            this.f9666r.setVisibility(8);
            this.f9664p.setText(getString(R.string.voices_record_hint));
            this.f9664p.setTextColor(getResources().getColor(R.color.you_color_normal_gray_text));
            return;
        }
        if (this.f9671w.equals(RecordState.RECORDING)) {
            this.f9655g.setImageResource(R.drawable.btn_recording_r);
            this.f9655g.setVisibility(0);
            this.f9656h.setVisibility(8);
            this.f9657i.setVisibility(8);
            this.f9663o.setProgress(0);
            this.f9663o.setProgressDrawable(getResources().getDrawable(R.drawable.rotate_voice_record_progressbar));
            this.f9663o.setVisibility(0);
            this.f9659k.setVisibility(0);
            this.f9661m.setVisibility(0);
            this.f9658j.setVisibility(0);
            this.f9660l.setVisibility(0);
            this.f9659k.setSelected(false);
            this.f9661m.setSelected(false);
            this.f9658j.setSelected(false);
            this.f9660l.setSelected(false);
            this.f9665q.setVisibility(0);
            this.f9666r.setVisibility(0);
            this.f9664p.setText("");
            this.f9664p.setTextColor(getResources().getColor(R.color.you_color_normal_pink_text));
            return;
        }
        if (this.f9671w.equals(RecordState.RECORD_FINISH)) {
            this.f9655g.setVisibility(8);
            this.f9656h.setVisibility(0);
            this.f9657i.setVisibility(8);
            this.f9663o.setProgress(0);
            this.f9663o.setProgressDrawable(getResources().getDrawable(R.drawable.rotate_playback_record_progressbar));
            this.f9663o.setVisibility(8);
            this.f9659k.setVisibility(4);
            this.f9661m.setVisibility(4);
            this.f9658j.setVisibility(0);
            this.f9660l.setVisibility(0);
            this.f9659k.setSelected(true);
            this.f9661m.setSelected(true);
            this.f9658j.setSelected(true);
            this.f9660l.setSelected(true);
            this.f9665q.setVisibility(8);
            this.f9666r.setVisibility(8);
            this.f9664p.setText(m10999L(this.f9652d));
            this.f9664p.setTextColor(getResources().getColor(R.color.you_color_normal_blue_text));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        List<Fragment> listMo1852i = getChildFragmentManager().mo1852i();
        if (listMo1852i != null) {
            Iterator<Fragment> it = listMo1852i.iterator();
            while (it.hasNext()) {
                it.next().onActivityResult(i9, i10, intent);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        Log.d("VoiceRecordFragment", "onAttach");
        super.onAttach(activity);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        Log.d("VoiceRecordFragment", "onCreate");
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_voice_recordv2, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Log.d("VoiceRecordFragment", "onDestroy");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        Log.d("VoiceRecordFragment", "onDetach");
        super.onDetach();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() throws IllegalStateException {
        super.onPause();
        C5158k0 c5158k0 = this.f9650b;
        if (c5158k0 != null) {
            c5158k0.m20089c();
            this.f9650b = null;
        }
        C5140e0 c5140e0 = this.f9651c;
        if (c5140e0 != null) {
            c5140e0.m20020l();
            this.f9651c = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.setClickable(true);
        view.findViewById(R.id.layout_cancel).setOnDragListener(this.f9673y);
        ImageView imageView = (ImageView) view.findViewById(R.id.btn_cancel);
        this.f9658j = imageView;
        imageView.setOnClickListener(this.f9646B);
        this.f9659k = (ImageView) view.findViewById(R.id.arrow_cancel);
        view.findViewById(R.id.layout_send).setOnDragListener(this.f9674z);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.btn_send);
        this.f9660l = imageView2;
        imageView2.setOnClickListener(this.f9647C);
        this.f9661m = (ImageView) view.findViewById(R.id.arrow_send);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.btn_record);
        this.f9655g = imageView3;
        imageView3.setOnLongClickListener(this.f9645A);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.layout_record);
        this.f9662n = viewGroup;
        viewGroup.setOnDragListener(this.f9672x);
        ImageView imageView4 = (ImageView) view.findViewById(R.id.btn_play);
        this.f9656h = imageView4;
        imageView4.setOnClickListener(this.f9648D);
        ImageView imageView5 = (ImageView) view.findViewById(R.id.btn_stop);
        this.f9657i = imageView5;
        imageView5.setOnClickListener(this.f9649E);
        this.f9664p = (TextView) view.findViewById(R.id.top_bar_text);
        this.f9663o = (ProgressBar) view.findViewById(R.id.progress_voice_record);
        this.f9665q = (LinearLayout) view.findViewById(R.id.amplitudeValue_left);
        this.f9666r = (LinearLayout) view.findViewById(R.id.amplitudeValue_right);
        m11004Y(RecordState.NONE);
    }
}
