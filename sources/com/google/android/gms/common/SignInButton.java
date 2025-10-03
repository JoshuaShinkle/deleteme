package com.google.android.gms.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.base.C3455R;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zaw;
import com.google.android.gms.common.internal.zay;
import com.google.android.gms.dynamic.RemoteCreator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes2.dex */
public final class SignInButton extends FrameLayout implements View.OnClickListener {

    @RecentlyNonNull
    public static final int COLOR_AUTO = 2;

    @RecentlyNonNull
    public static final int COLOR_DARK = 0;

    @RecentlyNonNull
    public static final int COLOR_LIGHT = 1;

    @RecentlyNonNull
    public static final int SIZE_ICON_ONLY = 2;

    @RecentlyNonNull
    public static final int SIZE_STANDARD = 0;

    @RecentlyNonNull
    public static final int SIZE_WIDE = 1;
    private int zaa;
    private int zab;
    private View zac;
    private View.OnClickListener zad;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ButtonSize {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorScheme {
    }

    public SignInButton(@RecentlyNonNull Context context) {
        this(context, null);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(@RecentlyNonNull View view) {
        View.OnClickListener onClickListener = this.zad;
        if (onClickListener == null || view != this.zac) {
            return;
        }
        onClickListener.onClick(this);
    }

    public final void setColorScheme(@RecentlyNonNull int i9) {
        setStyle(this.zaa, i9);
    }

    @Override // android.view.View
    public final void setEnabled(@RecentlyNonNull boolean z8) {
        super.setEnabled(z8);
        this.zac.setEnabled(z8);
    }

    @Override // android.view.View
    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.zad = onClickListener;
        View view = this.zac;
        if (view != null) {
            view.setOnClickListener(this);
        }
    }

    @Deprecated
    public final void setScopes(@RecentlyNonNull Scope[] scopeArr) {
        setStyle(this.zaa, this.zab);
    }

    public final void setSize(@RecentlyNonNull int i9) {
        setStyle(i9, this.zab);
    }

    public final void setStyle(@RecentlyNonNull int i9, @RecentlyNonNull int i10) {
        this.zaa = i9;
        this.zab = i10;
        Context context = getContext();
        View view = this.zac;
        if (view != null) {
            removeView(view);
        }
        try {
            this.zac = zaw.zaa(context, this.zaa, this.zab);
        } catch (RemoteCreator.RemoteCreatorException unused) {
            Log.w("SignInButton", "Sign in button not found, using placeholder instead");
            int i11 = this.zaa;
            int i12 = this.zab;
            zay zayVar = new zay(context);
            zayVar.zaa(context.getResources(), i11, i12);
            this.zac = zayVar;
        }
        addView(this.zac);
        this.zac.setEnabled(isEnabled());
        this.zac.setOnClickListener(this);
    }

    public SignInButton(@RecentlyNonNull Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SignInButton(@RecentlyNonNull Context context, AttributeSet attributeSet, @RecentlyNonNull int i9) {
        super(context, attributeSet, i9);
        this.zad = null;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C3455R.styleable.SignInButton, 0, 0);
        try {
            this.zaa = typedArrayObtainStyledAttributes.getInt(C3455R.styleable.SignInButton_buttonSize, 0);
            this.zab = typedArrayObtainStyledAttributes.getInt(C3455R.styleable.SignInButton_colorScheme, 2);
            typedArrayObtainStyledAttributes.recycle();
            setStyle(this.zaa, this.zab);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Deprecated
    public final void setStyle(@RecentlyNonNull int i9, @RecentlyNonNull int i10, @RecentlyNonNull Scope[] scopeArr) {
        setStyle(i9, i10);
    }
}
