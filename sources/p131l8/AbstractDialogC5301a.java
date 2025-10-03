package p131l8;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import p047d5.C4683g;

/* renamed from: l8.a */
/* loaded from: classes3.dex */
public abstract class AbstractDialogC5301a extends Dialog {

    /* renamed from: b */
    public Activity f17999b;

    /* renamed from: c */
    public View f18000c;

    public AbstractDialogC5301a(Context context, boolean z8) {
        super(context, z8 ? C4683g.FullScreenDialogFragment : C4683g.NonFullScreenDialogFragment);
        requestWindowFeature(1);
        this.f17999b = (Activity) context;
    }

    /* renamed from: a */
    public abstract int mo20690a();

    /* renamed from: b */
    public void mo20691b(View view) {
    }

    /* renamed from: c */
    public final void m20692c() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(mo20690a(), (ViewGroup) null, false);
        this.f18000c = viewInflate;
        setContentView(viewInflate);
        mo20691b(this.f18000c);
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m20692c();
    }
}
