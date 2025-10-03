package p057e5;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import com.perfectcorp.utility.C4507b;
import com.perfectcorp.utility.C4510e;
import p047d5.C4677a;
import p047d5.C4678b;
import p047d5.C4679c;
import p047d5.C4680d;
import p047d5.C4681e;
import p047d5.C4682f;
import p224w.dialogs.SimpleMessageDialog;

/* renamed from: e5.b */
/* loaded from: classes2.dex */
public class C4757b {

    /* renamed from: e5.b$a */
    public class a implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    /* renamed from: e5.b$b */
    public class b implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ Activity f16532b;

        /* renamed from: c */
        public final /* synthetic */ String f16533c;

        /* renamed from: d */
        public final /* synthetic */ String f16534d;

        /* renamed from: e */
        public final /* synthetic */ String f16535e;

        /* renamed from: f */
        public final /* synthetic */ Runnable f16536f;

        /* renamed from: g */
        public final /* synthetic */ Runnable f16537g;

        /* renamed from: h */
        public final /* synthetic */ boolean f16538h;

        /* renamed from: i */
        public final /* synthetic */ DialogInterface.OnDismissListener f16539i;

        /* renamed from: e5.b$b$a */
        public class a implements View.OnClickListener {

            /* renamed from: b */
            public final /* synthetic */ Dialog f16540b;

            public a(Dialog dialog) {
                this.f16540b = dialog;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Runnable runnable = b.this.f16536f;
                if (runnable != null) {
                    runnable.run();
                }
                this.f16540b.dismiss();
            }
        }

        /* renamed from: e5.b$b$b, reason: collision with other inner class name */
        public class ViewOnClickListenerC6868b implements View.OnClickListener {

            /* renamed from: b */
            public final /* synthetic */ Dialog f16542b;

            public ViewOnClickListenerC6868b(Dialog dialog) {
                this.f16542b = dialog;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Runnable runnable = b.this.f16537g;
                if (runnable != null) {
                    runnable.run();
                }
                this.f16542b.dismiss();
            }
        }

        public b(Activity activity, String str, String str2, String str3, Runnable runnable, Runnable runnable2, boolean z8, DialogInterface.OnDismissListener onDismissListener) {
            this.f16532b = activity;
            this.f16533c = str;
            this.f16534d = str2;
            this.f16535e = str3;
            this.f16536f = runnable;
            this.f16537g = runnable2;
            this.f16538h = z8;
            this.f16539i = onDismissListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f16532b.isFinishing()) {
                return;
            }
            Dialog dialog = new Dialog(this.f16532b);
            boolean z8 = true;
            dialog.requestWindowFeature(1);
            dialog.setContentView(C4681e.bc_dialog_prototype);
            ((TextView) dialog.findViewById(C4680d.bc_dialog_desc)).setText(Html.fromHtml(C4510e.m18121e(this.f16533c)));
            if (this.f16534d != null && this.f16535e != null) {
                z8 = false;
            }
            TextView textView = (TextView) dialog.findViewById(C4680d.bc_dialog_negative);
            if (textView != null) {
                textView.setVisibility(8);
                String str = this.f16534d;
                if (str != null) {
                    textView.setText(str);
                    textView.setVisibility(0);
                    textView.setOnClickListener(new a(dialog));
                    if (z8) {
                        textView.setBackgroundResource(C4679c.bc_dialog_button_one);
                    }
                }
            }
            TextView textView2 = (TextView) dialog.findViewById(C4680d.bc_dialog_positive);
            if (textView2 != null) {
                textView2.setVisibility(8);
                String str2 = this.f16535e;
                if (str2 != null) {
                    textView2.setText(str2);
                    textView2.setVisibility(0);
                    textView2.setOnClickListener(new ViewOnClickListenerC6868b(dialog));
                    if (z8) {
                        textView2.setBackgroundResource(C4679c.bc_dialog_button_one);
                    }
                }
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            this.f16532b.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            dialog.getWindow().getAttributes().width = displayMetrics.widthPixels;
            dialog.getWindow().setBackgroundDrawableResource(C4678b.bc_color_transparent);
            dialog.setCanceledOnTouchOutside(this.f16538h);
            dialog.setCancelable(this.f16538h);
            dialog.setOnDismissListener(this.f16539i);
            dialog.show();
        }
    }

    /* renamed from: a */
    public static void m18884a(Activity activity, String str, String str2, String str3, Runnable runnable, String str4, Runnable runnable2, boolean z8, DialogInterface.OnDismissListener onDismissListener) {
        if (activity == null) {
            C4507b.m18102c("activity is null");
        } else {
            activity.runOnUiThread(new b(activity, str2, str3, str4, runnable, runnable2, z8, onDismissListener));
        }
    }

    /* renamed from: b */
    public static void m18885b(Activity activity, int i9, int i10, Runnable runnable) {
        if (activity == null) {
            C4507b.m18102c("activity is null");
        } else {
            m18886c(activity, activity.getString(i9), activity.getString(i10), runnable);
        }
    }

    /* renamed from: c */
    public static void m18886c(Activity activity, String str, String str2, Runnable runnable) {
        if (activity == null) {
            C4507b.m18102c("activity is null");
        } else {
            m18887d(activity, str, str2, null, null, activity.getString(C4682f.bc_dialog_button_ok), runnable, true, null);
        }
    }

    /* renamed from: d */
    public static void m18887d(Activity activity, String str, String str2, String str3, Runnable runnable, String str4, Runnable runnable2, boolean z8, DialogInterface.OnDismissListener onDismissListener) {
        m18884a(activity, str, str2, str3, runnable, str4, runnable2, z8, onDismissListener);
    }

    /* renamed from: e */
    public static void m18888e(Activity activity, int i9) {
        new SimpleMessageDialog.C6499b(activity, false).m24868o(SimpleMessageDialog.LayoutType.LAYOUT_TYPE_VERTICAL_BUTTON).m24866m(new SimpleMessageDialog.C6500c(C4677a.m18710n().getString(C4682f.bc_dialog_button_close), new a(), true, SimpleMessageDialog.C6500c.f21850g)).m24870q(activity.getString(C4682f.bc_dialog_title_Notice), activity.getResources().getColor(C4678b.ycl_text_style_a)).m24869p(activity.getString(i9), SimpleMessageDialog.C6500c.f21849f).m24864k().show();
    }
}
