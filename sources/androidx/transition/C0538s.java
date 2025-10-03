package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: androidx.transition.s */
/* loaded from: classes.dex */
public class C0538s {

    /* renamed from: a */
    public static final boolean f2962a;

    /* renamed from: b */
    public static final boolean f2963b;

    /* renamed from: c */
    public static final boolean f2964c;

    static {
        int i9 = Build.VERSION.SDK_INT;
        f2962a = true;
        f2963b = true;
        f2964c = i9 >= 28;
    }

    /* renamed from: a */
    public static View m3116a(ViewGroup viewGroup, View view, View view2) {
        Matrix matrix = new Matrix();
        matrix.setTranslate(-view2.getScrollX(), -view2.getScrollY());
        C0514b0.m3031j(view, matrix);
        C0514b0.m3032k(viewGroup, matrix);
        RectF rectF = new RectF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, view.getWidth(), view.getHeight());
        matrix.mapRect(rectF);
        int iRound = Math.round(rectF.left);
        int iRound2 = Math.round(rectF.top);
        int iRound3 = Math.round(rectF.right);
        int iRound4 = Math.round(rectF.bottom);
        ImageView imageView = new ImageView(view.getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Bitmap bitmapM3117b = m3117b(view, matrix, rectF, viewGroup);
        if (bitmapM3117b != null) {
            imageView.setImageBitmap(bitmapM3117b);
        }
        imageView.measure(View.MeasureSpec.makeMeasureSpec(iRound3 - iRound, 1073741824), View.MeasureSpec.makeMeasureSpec(iRound4 - iRound2, 1073741824));
        imageView.layout(iRound, iRound2, iRound3, iRound4);
        return imageView;
    }

    /* renamed from: b */
    public static Bitmap m3117b(View view, Matrix matrix, RectF rectF, ViewGroup viewGroup) {
        boolean z8;
        boolean zIsAttachedToWindow;
        int iIndexOfChild;
        ViewGroup viewGroup2;
        if (f2962a) {
            z8 = !view.isAttachedToWindow();
            zIsAttachedToWindow = viewGroup == null ? false : viewGroup.isAttachedToWindow();
        } else {
            z8 = false;
            zIsAttachedToWindow = false;
        }
        boolean z9 = f2963b;
        Bitmap bitmapCreateBitmap = null;
        if (!z9 || !z8) {
            iIndexOfChild = 0;
            viewGroup2 = null;
        } else {
            if (!zIsAttachedToWindow) {
                return null;
            }
            viewGroup2 = (ViewGroup) view.getParent();
            iIndexOfChild = viewGroup2.indexOfChild(view);
            viewGroup.getOverlay().add(view);
        }
        int iRound = Math.round(rectF.width());
        int iRound2 = Math.round(rectF.height());
        if (iRound > 0 && iRound2 > 0) {
            float fMin = Math.min(1.0f, 1048576.0f / (iRound * iRound2));
            int iRound3 = Math.round(iRound * fMin);
            int iRound4 = Math.round(iRound2 * fMin);
            matrix.postTranslate(-rectF.left, -rectF.top);
            matrix.postScale(fMin, fMin);
            if (f2964c) {
                Picture picture = new Picture();
                Canvas canvasBeginRecording = picture.beginRecording(iRound3, iRound4);
                canvasBeginRecording.concat(matrix);
                view.draw(canvasBeginRecording);
                picture.endRecording();
                bitmapCreateBitmap = Bitmap.createBitmap(picture);
            } else {
                bitmapCreateBitmap = Bitmap.createBitmap(iRound3, iRound4, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                canvas.concat(matrix);
                view.draw(canvas);
            }
        }
        if (z9 && z8) {
            viewGroup.getOverlay().remove(view);
            viewGroup2.addView(view, iIndexOfChild);
        }
        return bitmapCreateBitmap;
    }

    /* renamed from: c */
    public static Animator m3118c(Animator animator, Animator animator2) {
        if (animator == null) {
            return animator2;
        }
        if (animator2 == null) {
            return animator;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator, animator2);
        return animatorSet;
    }
}
