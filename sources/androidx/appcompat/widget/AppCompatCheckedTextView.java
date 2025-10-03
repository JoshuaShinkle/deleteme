package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import androidx.core.widget.C0337l;
import p020c.C0694a;

/* loaded from: classes.dex */
public class AppCompatCheckedTextView extends CheckedTextView {

    /* renamed from: c */
    public static final int[] f788c = {R.attr.checkMark};

    /* renamed from: b */
    public final C0241m f789b;

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.checkedTextViewStyle);
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0241m c0241m = this.f789b;
        if (c0241m != null) {
            c0241m.m944b();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return C0229g.m845a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i9) {
        setCheckMarkDrawable(C0694a.m3458b(getContext(), i9));
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(C0337l.m1622q(this, callback));
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i9) {
        super.setTextAppearance(context, i9);
        C0241m c0241m = this.f789b;
        if (c0241m != null) {
            c0241m.m958q(context, i9);
        }
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet, int i9) {
        super(C0244n0.m998b(context), attributeSet, i9);
        C0240l0.m934a(this, getContext());
        C0241m c0241m = new C0241m(this);
        this.f789b = c0241m;
        c0241m.m954m(attributeSet, i9);
        c0241m.m944b();
        C0250q0 c0250q0M1004v = C0250q0.m1004v(getContext(), attributeSet, f788c, i9, 0);
        setCheckMarkDrawable(c0250q0M1004v.m1011g(0));
        c0250q0M1004v.m1024w();
    }
}
