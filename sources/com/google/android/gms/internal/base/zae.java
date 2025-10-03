package com.google.android.gms.internal.base;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import net.sqlcipher.database.SQLiteDatabase;

/* loaded from: classes2.dex */
public final class zae extends Drawable implements Drawable.Callback {
    private int zaa;
    private long zab;
    private int zac;
    private int zad;
    private int zae;
    private int zaf;
    private int zag;
    private boolean zah;
    private boolean zai;
    private zah zaj;
    private Drawable zak;
    private Drawable zal;
    private boolean zam;
    private boolean zan;
    private boolean zao;
    private int zap;

    public zae(Drawable drawable, Drawable drawable2) {
        this(null);
        drawable = drawable == null ? zaf.zaa : drawable;
        this.zak = drawable;
        drawable.setCallback(this);
        zah zahVar = this.zaj;
        zahVar.zab = drawable.getChangingConfigurations() | zahVar.zab;
        drawable2 = drawable2 == null ? zaf.zaa : drawable2;
        this.zal = drawable2;
        drawable2.setCallback(this);
        zah zahVar2 = this.zaj;
        zahVar2.zab = drawable2.getChangingConfigurations() | zahVar2.zab;
    }

    private final boolean zab() {
        if (!this.zam) {
            this.zan = (this.zak.getConstantState() == null || this.zal.getConstantState() == null) ? false : true;
            this.zam = true;
        }
        return this.zan;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        int i9 = this.zaa;
        boolean z8 = false;
        if (i9 != 1) {
            if (i9 == 2 && this.zab >= 0) {
                float fUptimeMillis = (SystemClock.uptimeMillis() - this.zab) / this.zaf;
                z = fUptimeMillis >= 1.0f;
                if (z) {
                    this.zaa = 0;
                }
                this.zag = (int) ((this.zad * Math.min(fUptimeMillis, 1.0f)) + BitmapDescriptorFactory.HUE_RED);
            }
            z8 = z;
        } else {
            this.zab = SystemClock.uptimeMillis();
            this.zaa = 2;
        }
        int i10 = this.zag;
        boolean z9 = this.zah;
        Drawable drawable = this.zak;
        Drawable drawable2 = this.zal;
        if (z8) {
            if (!z9 || i10 == 0) {
                drawable.draw(canvas);
            }
            int i11 = this.zae;
            if (i10 == i11) {
                drawable2.setAlpha(i11);
                drawable2.draw(canvas);
                return;
            }
            return;
        }
        if (z9) {
            drawable.setAlpha(this.zae - i10);
        }
        drawable.draw(canvas);
        if (z9) {
            drawable.setAlpha(this.zae);
        }
        if (i10 > 0) {
            drawable2.setAlpha(i10);
            drawable2.draw(canvas);
            drawable2.setAlpha(this.zae);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        zah zahVar = this.zaj;
        return changingConfigurations | zahVar.zaa | zahVar.zab;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (!zab()) {
            return null;
        }
        this.zaj.zaa = getChangingConfigurations();
        return this.zaj;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return Math.max(this.zak.getIntrinsicHeight(), this.zal.getIntrinsicHeight());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return Math.max(this.zak.getIntrinsicWidth(), this.zal.getIntrinsicWidth());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        if (!this.zao) {
            this.zap = Drawable.resolveOpacity(this.zak.getOpacity(), this.zal.getOpacity());
            this.zao = true;
        }
        return this.zap;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        if (!this.zai && super.mutate() == this) {
            if (!zab()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.zak.mutate();
            this.zal.mutate();
            this.zai = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        this.zak.setBounds(rect);
        this.zal.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j9) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j9);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i9) {
        if (this.zag == this.zae) {
            this.zag = i9;
        }
        this.zae = i9;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.zak.setColorFilter(colorFilter);
        this.zal.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final Drawable zaa() {
        return this.zal;
    }

    public final void zaa(int i9) {
        this.zac = 0;
        this.zad = this.zae;
        this.zag = 0;
        this.zaf = SQLiteDatabase.MAX_SQL_CACHE_SIZE;
        this.zaa = 1;
        invalidateSelf();
    }

    public zae(zah zahVar) {
        this.zaa = 0;
        this.zae = 255;
        this.zag = 0;
        this.zah = true;
        this.zaj = new zah(zahVar);
    }
}
