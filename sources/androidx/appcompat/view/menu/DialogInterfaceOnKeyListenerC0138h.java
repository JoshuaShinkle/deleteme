package androidx.appcompat.view.menu;

import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.DialogInterfaceC0120b;
import androidx.appcompat.view.menu.InterfaceC0143m;
import p010b.C0566g;

/* renamed from: androidx.appcompat.view.menu.h */
/* loaded from: classes.dex */
public class DialogInterfaceOnKeyListenerC0138h implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, InterfaceC0143m.a {

    /* renamed from: b */
    public C0137g f562b;

    /* renamed from: c */
    public DialogInterfaceC0120b f563c;

    /* renamed from: d */
    public C0135e f564d;

    /* renamed from: e */
    public InterfaceC0143m.a f565e;

    public DialogInterfaceOnKeyListenerC0138h(C0137g c0137g) {
        this.f562b = c0137g;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m.a
    /* renamed from: a */
    public boolean mo352a(C0137g c0137g) {
        InterfaceC0143m.a aVar = this.f565e;
        if (aVar != null) {
            return aVar.mo352a(c0137g);
        }
        return false;
    }

    /* renamed from: b */
    public void m519b() {
        DialogInterfaceC0120b dialogInterfaceC0120b = this.f563c;
        if (dialogInterfaceC0120b != null) {
            dialogInterfaceC0120b.dismiss();
        }
    }

    /* renamed from: c */
    public void m520c(IBinder iBinder) {
        C0137g c0137g = this.f562b;
        DialogInterfaceC0120b.a aVar = new DialogInterfaceC0120b.a(c0137g.getContext());
        C0135e c0135e = new C0135e(aVar.m391b(), C0566g.abc_list_menu_item_layout);
        this.f564d = c0135e;
        c0135e.setCallback(this);
        this.f562b.addMenuPresenter(this.f564d);
        aVar.m392c(this.f564d.m509a(), this);
        View headerView = c0137g.getHeaderView();
        if (headerView != null) {
            aVar.m393d(headerView);
        } else {
            aVar.m394e(c0137g.getHeaderIcon()).m397h(c0137g.getHeaderTitle());
        }
        aVar.m395f(this);
        DialogInterfaceC0120b dialogInterfaceC0120bM390a = aVar.m390a();
        this.f563c = dialogInterfaceC0120bM390a;
        dialogInterfaceC0120bM390a.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.f563c.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.f563c.show();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i9) {
        this.f562b.performItemAction((C0139i) this.f564d.m509a().getItem(i9), 0);
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m.a
    public void onCloseMenu(C0137g c0137g, boolean z8) {
        if (z8 || c0137g == this.f562b) {
            m519b();
        }
        InterfaceC0143m.a aVar = this.f565e;
        if (aVar != null) {
            aVar.onCloseMenu(c0137g, z8);
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.f564d.onCloseMenu(this.f562b, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i9, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i9 == 82 || i9 == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.f563c.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f563c.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f562b.close(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f562b.performShortcut(i9, keyEvent, 0);
    }
}
