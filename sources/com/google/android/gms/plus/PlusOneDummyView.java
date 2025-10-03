package com.google.android.gms.plus;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.FrameLayout;

@Deprecated
/* loaded from: classes2.dex */
public class PlusOneDummyView extends FrameLayout {

    @Deprecated
    public static final String TAG = "PlusOneDummyView";

    public static class zza implements zzd {
        private Context mContext;

        private zza(Context context) {
            this.mContext = context;
        }

        @Override // com.google.android.gms.plus.PlusOneDummyView.zzd
        public final Drawable getDrawable(int i9) {
            return this.mContext.getResources().getDrawable(android.R.drawable.btn_default);
        }

        @Override // com.google.android.gms.plus.PlusOneDummyView.zzd
        public final boolean isValid() {
            return true;
        }
    }

    public static class zzb implements zzd {
        private Context mContext;

        private zzb(Context context) {
            this.mContext = context;
        }

        @Override // com.google.android.gms.plus.PlusOneDummyView.zzd
        public final Drawable getDrawable(int i9) {
            try {
                Resources resources = this.mContext.createPackageContext("com.google.android.gms", 4).getResources();
                return resources.getDrawable(resources.getIdentifier(i9 != 0 ? i9 != 1 ? i9 != 2 ? "ic_plusone_standard" : "ic_plusone_tall" : "ic_plusone_medium" : "ic_plusone_small", "drawable", "com.google.android.gms"));
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }

        @Override // com.google.android.gms.plus.PlusOneDummyView.zzd
        public final boolean isValid() {
            try {
                this.mContext.createPackageContext("com.google.android.gms", 4).getResources();
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
                return false;
            }
        }
    }

    public static class zzc implements zzd {
        private Context mContext;

        private zzc(Context context) {
            this.mContext = context;
        }

        @Override // com.google.android.gms.plus.PlusOneDummyView.zzd
        public final Drawable getDrawable(int i9) {
            return this.mContext.getResources().getDrawable(this.mContext.getResources().getIdentifier(i9 != 0 ? i9 != 1 ? i9 != 2 ? "ic_plusone_standard_off_client" : "ic_plusone_tall_off_client" : "ic_plusone_medium_off_client" : "ic_plusone_small_off_client", "drawable", this.mContext.getPackageName()));
        }

        @Override // com.google.android.gms.plus.PlusOneDummyView.zzd
        public final boolean isValid() {
            return (this.mContext.getResources().getIdentifier("ic_plusone_small_off_client", "drawable", this.mContext.getPackageName()) == 0 || this.mContext.getResources().getIdentifier("ic_plusone_medium_off_client", "drawable", this.mContext.getPackageName()) == 0 || this.mContext.getResources().getIdentifier("ic_plusone_tall_off_client", "drawable", this.mContext.getPackageName()) == 0 || this.mContext.getResources().getIdentifier("ic_plusone_standard_off_client", "drawable", this.mContext.getPackageName()) == 0) ? false : true;
        }
    }

    public interface zzd {
        Drawable getDrawable(int i9);

        boolean isValid();
    }

    @Deprecated
    public PlusOneDummyView(Context context, int i9) {
        int i10;
        super(context);
        Button button = new Button(context);
        button.setEnabled(false);
        zzd zzbVar = new zzb(getContext());
        zzbVar = zzbVar.isValid() ? zzbVar : new zzc(getContext());
        button.setBackgroundDrawable((zzbVar.isValid() ? zzbVar : new zza(getContext())).getDrawable(i9));
        Point point = new Point();
        int i11 = 24;
        if (i9 != 0) {
            i10 = 20;
            if (i9 == 1) {
                i11 = 32;
            } else if (i9 != 2) {
                i10 = 24;
                i11 = 38;
            } else {
                i11 = 50;
            }
        } else {
            i10 = 14;
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float fApplyDimension = TypedValue.applyDimension(1, i11, displayMetrics);
        float fApplyDimension2 = TypedValue.applyDimension(1, i10, displayMetrics);
        point.x = (int) (fApplyDimension + 0.5d);
        point.y = (int) (fApplyDimension2 + 0.5d);
        addView(button, new FrameLayout.LayoutParams(point.x, point.y, 17));
    }
}
