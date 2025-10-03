package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AutoCompleteTextView;
import androidx.core.widget.C0337l;
import p010b.C0560a;
import p020c.C0694a;

/* loaded from: classes.dex */
public class AppCompatAutoCompleteTextView extends AutoCompleteTextView {

    /* renamed from: d */
    public static final int[] f785d = {R.attr.popupBackground};

    /* renamed from: b */
    public final C0223d f786b;

    /* renamed from: c */
    public final C0241m f787c;

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0560a.autoCompleteTextViewStyle);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0223d c0223d = this.f786b;
        if (c0223d != null) {
            c0223d.m799b();
        }
        C0241m c0241m = this.f787c;
        if (c0241m != null) {
            c0241m.m944b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0223d c0223d = this.f786b;
        if (c0223d != null) {
            return c0223d.m800c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0223d c0223d = this.f786b;
        if (c0223d != null) {
            return c0223d.m801d();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return C0229g.m845a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0223d c0223d = this.f786b;
        if (c0223d != null) {
            c0223d.m803f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i9) {
        super.setBackgroundResource(i9);
        C0223d c0223d = this.f786b;
        if (c0223d != null) {
            c0223d.m804g(i9);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(C0337l.m1622q(this, callback));
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i9) {
        setDropDownBackgroundDrawable(C0694a.m3458b(getContext(), i9));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0223d c0223d = this.f786b;
        if (c0223d != null) {
            c0223d.m806i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0223d c0223d = this.f786b;
        if (c0223d != null) {
            c0223d.m807j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i9) {
        super.setTextAppearance(context, i9);
        C0241m c0241m = this.f787c;
        if (c0241m != null) {
            c0241m.m958q(context, i9);
        }
    }

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attributeSet, int i9) {
        super(C0244n0.m998b(context), attributeSet, i9);
        C0240l0.m934a(this, getContext());
        C0250q0 c0250q0M1004v = C0250q0.m1004v(getContext(), attributeSet, f785d, i9, 0);
        if (c0250q0M1004v.m1023s(0)) {
            setDropDownBackgroundDrawable(c0250q0M1004v.m1011g(0));
        }
        c0250q0M1004v.m1024w();
        C0223d c0223d = new C0223d(this);
        this.f786b = c0223d;
        c0223d.m802e(attributeSet, i9);
        C0241m c0241m = new C0241m(this);
        this.f787c = c0241m;
        c0241m.m954m(attributeSet, i9);
        c0241m.m944b();
    }
}
