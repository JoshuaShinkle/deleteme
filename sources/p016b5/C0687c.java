package p016b5;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.Checkable;

/* renamed from: b5.c */
/* loaded from: classes2.dex */
public class C0687c extends C0686b implements Checkable {
    public C0687c(Context context) {
        super(context);
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        KeyEvent.Callback childAt = getChildAt(0);
        if (childAt instanceof Checkable) {
            return ((Checkable) childAt).isChecked();
        }
        return false;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z8) {
        KeyEvent.Callback childAt = getChildAt(0);
        if (childAt instanceof Checkable) {
            ((Checkable) childAt).setChecked(z8);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        KeyEvent.Callback childAt = getChildAt(0);
        if (childAt instanceof Checkable) {
            ((Checkable) childAt).toggle();
        }
    }
}
