package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
import androidx.core.widget.C0337l;
import p010b.C0560a;

/* loaded from: classes.dex */
public class AppCompatEditText extends EditText {
    private final C0223d mBackgroundTintHelper;
    private final C0237k mTextClassifierHelper;
    private final C0241m mTextHelper;

    public AppCompatEditText(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0223d c0223d = this.mBackgroundTintHelper;
        if (c0223d != null) {
            c0223d.m799b();
        }
        C0241m c0241m = this.mTextHelper;
        if (c0241m != null) {
            c0241m.m944b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0223d c0223d = this.mBackgroundTintHelper;
        if (c0223d != null) {
            return c0223d.m800c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0223d c0223d = this.mBackgroundTintHelper;
        if (c0223d != null) {
            return c0223d.m801d();
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        C0237k c0237k;
        return (Build.VERSION.SDK_INT >= 28 || (c0237k = this.mTextClassifierHelper) == null) ? super.getTextClassifier() : c0237k.m909a();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return C0229g.m845a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0223d c0223d = this.mBackgroundTintHelper;
        if (c0223d != null) {
            c0223d.m803f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i9) {
        super.setBackgroundResource(i9);
        C0223d c0223d = this.mBackgroundTintHelper;
        if (c0223d != null) {
            c0223d.m804g(i9);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(C0337l.m1622q(this, callback));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0223d c0223d = this.mBackgroundTintHelper;
        if (c0223d != null) {
            c0223d.m806i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0223d c0223d = this.mBackgroundTintHelper;
        if (c0223d != null) {
            c0223d.m807j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i9) {
        super.setTextAppearance(context, i9);
        C0241m c0241m = this.mTextHelper;
        if (c0241m != null) {
            c0241m.m958q(context, i9);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        C0237k c0237k;
        if (Build.VERSION.SDK_INT >= 28 || (c0237k = this.mTextClassifierHelper) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            c0237k.m910b(textClassifier);
        }
    }

    public AppCompatEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0560a.editTextStyle);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        return Build.VERSION.SDK_INT >= 28 ? super.getText() : super.getEditableText();
    }

    public AppCompatEditText(Context context, AttributeSet attributeSet, int i9) {
        super(C0244n0.m998b(context), attributeSet, i9);
        C0240l0.m934a(this, getContext());
        C0223d c0223d = new C0223d(this);
        this.mBackgroundTintHelper = c0223d;
        c0223d.m802e(attributeSet, i9);
        C0241m c0241m = new C0241m(this);
        this.mTextHelper = c0241m;
        c0241m.m954m(attributeSet, i9);
        c0241m.m944b();
        this.mTextClassifierHelper = new C0237k(this);
    }
}
