package com.cyberlink.you.p036ui;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.DialogC0123e;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.concurrent.atomic.AtomicInteger;
import p116k4.C5154j;
import p209u2.C6385v;

/* renamed from: com.cyberlink.you.ui.q */
/* loaded from: classes.dex */
public class DialogC3133q extends DialogC0123e {

    /* renamed from: b */
    public final Handler f14414b;

    /* renamed from: c */
    public final boolean f14415c;

    /* renamed from: d */
    public final AtomicInteger f14416d;

    /* renamed from: e */
    public long f14417e;

    /* renamed from: f */
    public boolean f14418f;

    /* renamed from: g */
    public final Runnable f14419g;

    /* renamed from: com.cyberlink.you.ui.q$b */
    public static class b {

        /* renamed from: a */
        public Activity f14420a;

        /* renamed from: b */
        public String f14421b = null;

        /* renamed from: c */
        public boolean f14422c = false;

        /* renamed from: d */
        public c f14423d = null;

        /* renamed from: e */
        public long f14424e = 400;

        public b(Activity activity) {
            this.f14420a = activity;
        }

        /* renamed from: a */
        public DialogC3133q m16410a() {
            return new DialogC3133q(this.f14420a, this.f14422c).m16407r(this.f14421b).m16406q(this.f14424e).m16409t(null).m16408s(this.f14423d);
        }

        /* renamed from: b */
        public DialogC3133q m16411b() {
            DialogC3133q dialogC3133qM16410a = m16410a();
            dialogC3133qM16410a.show();
            return dialogC3133qM16410a;
        }

        /* renamed from: c */
        public b m16412c(boolean z8) {
            this.f14422c = z8;
            return this;
        }

        /* renamed from: d */
        public b m16413d(long j9) {
            this.f14424e = j9;
            return this;
        }

        /* renamed from: e */
        public b m16414e(c cVar) {
            this.f14423d = cVar;
            return this;
        }
    }

    /* renamed from: com.cyberlink.you.ui.q$c */
    public interface c {
        /* renamed from: a */
        void mo10190a(DialogC3133q dialogC3133q);
    }

    /* renamed from: com.cyberlink.you.ui.q$d */
    public interface d {
        void onDismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public /* synthetic */ void m16399l() {
        int iDecrementAndGet;
        if (!m16405k() && (iDecrementAndGet = this.f14416d.decrementAndGet()) >= 0 && isShowing() && iDecrementAndGet <= 0) {
            super.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m */
    public /* synthetic */ void m16400m() {
        this.f14418f = true;
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0.4f;
        window.setAttributes(attributes);
        window.getDecorView().setAlpha(1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n */
    public /* synthetic */ void m16401n(c cVar, DialogInterface dialogInterface) {
        if (cVar != null) {
            cVar.mo10190a(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p */
    public /* synthetic */ void m16403p() {
        if (m16405k()) {
            return;
        }
        this.f14416d.incrementAndGet();
        if (isShowing()) {
            return;
        }
        long j9 = this.f14417e;
        if (j9 > 0) {
            this.f14418f = false;
            this.f14414b.postDelayed(this.f14419g, j9);
        }
        super.show();
    }

    @Override // androidx.appcompat.app.DialogC0123e, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        C6385v.m24527e(new Runnable() { // from class: com.cyberlink.you.ui.l
            @Override // java.lang.Runnable
            public final void run() {
                this.f14409b.m16399l();
            }
        });
    }

    /* renamed from: j */
    public final Activity m16404j() {
        Activity ownerActivity = getOwnerActivity();
        Context context = getContext();
        while (ownerActivity == null && context != null) {
            if (context instanceof Activity) {
                ownerActivity = context;
            } else {
                context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
            }
        }
        return ownerActivity;
    }

    /* renamed from: k */
    public final boolean m16405k() {
        Activity activityM16404j = m16404j();
        if (activityM16404j instanceof BaseActivity) {
            return ((BaseActivity) activityM16404j).m7364e();
        }
        if (activityM16404j instanceof BaseFragmentActivity) {
            return ((BaseFragmentActivity) activityM16404j).m7367J0();
        }
        if (activityM16404j != null) {
            return activityM16404j.isFinishing();
        }
        C5154j.m20077k("Dismiss dialog but attached Activity is unavailable.");
        return false;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        Window window;
        if (this.f14418f || (window = getWindow()) == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = BitmapDescriptorFactory.HUE_RED;
        window.setAttributes(attributes);
        window.getDecorView().setAlpha(BitmapDescriptorFactory.HUE_RED);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.f14414b.removeCallbacks(this.f14419g);
    }

    /* renamed from: q */
    public final DialogC3133q m16406q(long j9) {
        this.f14417e = j9;
        return this;
    }

    /* renamed from: r */
    public final DialogC3133q m16407r(String str) {
        TextView textView;
        if (!TextUtils.isEmpty(str) && (textView = (TextView) findViewById(R.id.waitingDialogMsg)) != null) {
            textView.setText(str);
            textView.setVisibility(0);
        }
        return this;
    }

    /* renamed from: s */
    public final DialogC3133q m16408s(final c cVar) {
        if (this.f14415c) {
            super.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.cyberlink.you.ui.p
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    this.f14412b.m16401n(cVar, dialogInterface);
                }
            });
        }
        return this;
    }

    @Override // android.app.Dialog
    public void show() {
        C6385v.m24527e(new Runnable() { // from class: com.cyberlink.you.ui.n
            @Override // java.lang.Runnable
            public final void run() {
                this.f14411b.m16403p();
            }
        });
    }

    /* renamed from: t */
    public final DialogC3133q m16409t(final d dVar) {
        if (dVar != null) {
            super.setOnDismissListener(new DialogInterface.OnDismissListener(dVar) { // from class: com.cyberlink.you.ui.o
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    ((DialogC3133q.d) null).onDismiss();
                }
            });
        }
        return this;
    }

    public DialogC3133q(Context context, boolean z8) {
        super(context, R.style.WaitingDialog);
        this.f14416d = new AtomicInteger(0);
        this.f14418f = true;
        this.f14419g = new Runnable() { // from class: com.cyberlink.you.ui.m
            @Override // java.lang.Runnable
            public final void run() {
                this.f14410b.m16400m();
            }
        };
        setContentView(R.layout.view_waiting_dialog);
        setCancelable(z8);
        this.f14414b = new Handler(context.getMainLooper());
        this.f14415c = z8;
    }
}
