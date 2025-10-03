package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AlertController;
import p010b.C0560a;

/* renamed from: androidx.appcompat.app.b */
/* loaded from: classes.dex */
public class DialogInterfaceC0120b extends DialogC0123e implements DialogInterface {

    /* renamed from: b */
    public final AlertController f377b;

    /* renamed from: androidx.appcompat.app.b$a */
    public static class a {

        /* renamed from: a */
        public final AlertController.C0098b f378a;

        /* renamed from: b */
        public final int f379b;

        public a(Context context) {
            this(context, DialogInterfaceC0120b.m388b(context, 0));
        }

        /* renamed from: a */
        public DialogInterfaceC0120b m390a() {
            DialogInterfaceC0120b dialogInterfaceC0120b = new DialogInterfaceC0120b(this.f378a.f239a, this.f379b);
            this.f378a.m246a(dialogInterfaceC0120b.f377b);
            dialogInterfaceC0120b.setCancelable(this.f378a.f256r);
            if (this.f378a.f256r) {
                dialogInterfaceC0120b.setCanceledOnTouchOutside(true);
            }
            dialogInterfaceC0120b.setOnCancelListener(this.f378a.f257s);
            dialogInterfaceC0120b.setOnDismissListener(this.f378a.f258t);
            DialogInterface.OnKeyListener onKeyListener = this.f378a.f259u;
            if (onKeyListener != null) {
                dialogInterfaceC0120b.setOnKeyListener(onKeyListener);
            }
            return dialogInterfaceC0120b;
        }

        /* renamed from: b */
        public Context m391b() {
            return this.f378a.f239a;
        }

        /* renamed from: c */
        public a m392c(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.C0098b c0098b = this.f378a;
            c0098b.f261w = listAdapter;
            c0098b.f262x = onClickListener;
            return this;
        }

        /* renamed from: d */
        public a m393d(View view) {
            this.f378a.f245g = view;
            return this;
        }

        /* renamed from: e */
        public a m394e(Drawable drawable) {
            this.f378a.f242d = drawable;
            return this;
        }

        /* renamed from: f */
        public a m395f(DialogInterface.OnKeyListener onKeyListener) {
            this.f378a.f259u = onKeyListener;
            return this;
        }

        /* renamed from: g */
        public a m396g(ListAdapter listAdapter, int i9, DialogInterface.OnClickListener onClickListener) {
            AlertController.C0098b c0098b = this.f378a;
            c0098b.f261w = listAdapter;
            c0098b.f262x = onClickListener;
            c0098b.f232I = i9;
            c0098b.f231H = true;
            return this;
        }

        /* renamed from: h */
        public a m397h(CharSequence charSequence) {
            this.f378a.f244f = charSequence;
            return this;
        }

        public a(Context context, int i9) {
            this.f378a = new AlertController.C0098b(new ContextThemeWrapper(context, DialogInterfaceC0120b.m388b(context, i9)));
            this.f379b = i9;
        }
    }

    public DialogInterfaceC0120b(Context context, int i9) {
        super(context, m388b(context, i9));
        this.f377b = new AlertController(getContext(), this, getWindow());
    }

    /* renamed from: b */
    public static int m388b(Context context, int i9) {
        if (((i9 >>> 24) & 255) >= 1) {
            return i9;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(C0560a.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    /* renamed from: a */
    public ListView m389a() {
        return this.f377b.m224d();
    }

    @Override // androidx.appcompat.app.DialogC0123e, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f377b.m225e();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i9, KeyEvent keyEvent) {
        if (this.f377b.m226f(i9, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i9, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i9, KeyEvent keyEvent) {
        if (this.f377b.m227g(i9, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i9, keyEvent);
    }

    @Override // androidx.appcompat.app.DialogC0123e, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f377b.m236p(charSequence);
    }
}
