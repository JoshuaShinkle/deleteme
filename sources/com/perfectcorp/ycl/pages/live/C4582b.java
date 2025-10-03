package com.perfectcorp.ycl.pages.live;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import com.perfectcorp.ycl.BaseActivity;
import java.lang.ref.WeakReference;
import java.util.regex.Pattern;
import p047d5.C4677a;
import p047d5.C4678b;
import p047d5.C4682f;
import p057e5.C4757b;
import p224w.dialogs.SimpleMessageDialog;

/* renamed from: com.perfectcorp.ycl.pages.live.b */
/* loaded from: classes2.dex */
public class C4582b extends C4590j {

    /* renamed from: k */
    public static final Pattern f16073k = Pattern.compile(".*<[^>]+>.*");

    /* renamed from: l */
    public static WeakReference<Context> f16074l;

    /* renamed from: g */
    public final C4600t f16075g;

    /* renamed from: h */
    public final b f16076h;

    /* renamed from: i */
    public a f16077i;

    /* renamed from: j */
    public String f16078j;

    /* renamed from: com.perfectcorp.ycl.pages.live.b$a */
    public interface a {
        /* renamed from: a */
        void mo13948a(int i9);
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.b$b */
    public interface b {
        /* renamed from: a */
        void mo13762a();

        /* renamed from: b */
        void mo13763b();
    }

    public C4582b(Context context, View view, C4600t c4600t, b bVar, a aVar) {
        super(view);
        f16074l = new WeakReference<>(context);
        this.f16075g = c4600t;
        this.f16076h = bVar;
        this.f16077i = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C */
    public /* synthetic */ void m18194C(DialogInterface dialogInterface, int i9) {
        String strM24853d = ((SimpleMessageDialog) dialogInterface).m24853d();
        if (strM24853d.equals("")) {
            return;
        }
        if (f16073k.matcher(strM24853d).matches()) {
            C4757b.m18888e(m18196A(), C4682f.wbn_display_name_html_tag_prohibited);
            return;
        }
        this.f16078j = strM24853d;
        C4677a.m18686L(strM24853d);
        mo18201r();
    }

    /* renamed from: A */
    public final Activity m18196A() {
        return (Activity) f16074l.get();
    }

    /* renamed from: B */
    public final void m18197B() {
        ((BaseActivity) m18196A()).mo13673M0();
    }

    /* renamed from: D */
    public final void m18198D() {
        SimpleMessageDialog simpleMessageDialogM24864k = new SimpleMessageDialog.C6499b(m18196A(), false).m24868o(SimpleMessageDialog.LayoutType.LAYOUT_TYPE_VERTICAL_BUTTON).m24866m(new SimpleMessageDialog.C6500c(C4677a.m18710n().getString(C4682f.ycl_dialog_confirm), new DialogInterface.OnClickListener() { // from class: com.perfectcorp.ycl.pages.live.a
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f16072b.m18194C(dialogInterface, i9);
            }
        }, SimpleMessageDialog.C6500c.f21850g)).m24865l(true).m24870q(C4677a.m18710n().getString(C4682f.ycl_display_name_title), m18196A().getResources().getColor(C4678b.ycl_text_style_a)).m24864k();
        simpleMessageDialogM24864k.getWindow().setSoftInputMode(16);
        simpleMessageDialogM24864k.show();
    }

    /* renamed from: E */
    public final void m18199E(String str, String str2) {
        C4604x.m18359s().m18365G(str, str2);
    }

    @Override // com.perfectcorp.ycl.pages.live.C4590j
    /* renamed from: p */
    public void mo18200p() {
        String strM18708l = C4677a.m18708l();
        this.f16078j = strM18708l;
        if (strM18708l.equals("")) {
            m18198D();
        }
    }

    @Override // com.perfectcorp.ycl.pages.live.C4590j
    /* renamed from: q */
    public void mo13764q(View view) {
        m18197B();
    }

    @Override // com.perfectcorp.ycl.pages.live.C4590j
    /* renamed from: r */
    public void mo18201r() {
        String strM18230i = m18230i();
        if (strM18230i.isEmpty()) {
            return;
        }
        String strM18708l = C4677a.m18708l();
        this.f16078j = strM18708l;
        if (strM18708l.equals("")) {
            m18198D();
            if (this.f16078j.equals("")) {
                return;
            }
        }
        if (f16073k.matcher(strM18230i).matches()) {
            C4757b.m18888e(m18196A(), C4682f.wbn_chat_message_html_tag_prohibited);
            return;
        }
        m18199E(this.f16078j, strM18230i);
        m18197B();
        m18228g();
    }

    @Override // com.perfectcorp.ycl.pages.live.C4590j
    /* renamed from: s */
    public void mo18202s() {
        if (this.f16104e) {
            this.f16104e = false;
            this.f16076h.mo13762a();
        }
    }

    @Override // com.perfectcorp.ycl.pages.live.C4590j
    /* renamed from: t */
    public void mo18203t(int i9) {
        this.f16077i.mo13948a(i9);
    }

    @Override // com.perfectcorp.ycl.pages.live.C4590j
    /* renamed from: u */
    public void mo18204u() {
        if (this.f16104e) {
            return;
        }
        this.f16104e = true;
        this.f16076h.mo13763b();
    }
}
