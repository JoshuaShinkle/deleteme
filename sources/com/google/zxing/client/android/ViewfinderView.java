package com.google.zxing.client.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.camera.CameraManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class ViewfinderView extends View {
    private static final long ANIMATION_DELAY = 80;
    private static final int CURRENT_POINT_OPACITY = 160;
    private static final int MAX_RESULT_POINTS = 20;
    private static final int POINT_SIZE = 6;
    private static final int[] SCANNER_ALPHA = {0, 64, 128, PsExtractor.AUDIO_STREAM, 255, PsExtractor.AUDIO_STREAM, 128, 64};
    private CameraManager cameraManager;
    private final int laserColor;
    private List<ResultPoint> lastPossibleResultPoints;
    private final int maskColor;
    private final Paint paint;
    private List<ResultPoint> possibleResultPoints;
    private Bitmap resultBitmap;
    private final int resultColor;
    private final int resultPointColor;
    private int scannerAlpha;

    public ViewfinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.paint = new Paint(1);
        Resources resources = getResources();
        this.maskColor = resources.getColor(C4453R.color.viewfinder_mask);
        this.resultColor = resources.getColor(C4453R.color.result_view);
        this.laserColor = resources.getColor(C4453R.color.viewfinder_laser);
        this.resultPointColor = resources.getColor(C4453R.color.possible_result_points);
        this.scannerAlpha = 0;
        this.possibleResultPoints = new ArrayList(5);
        this.lastPossibleResultPoints = null;
    }

    public void addPossibleResultPoint(ResultPoint resultPoint) {
        List<ResultPoint> list = this.possibleResultPoints;
        synchronized (list) {
            list.add(resultPoint);
            int size = list.size();
            if (size > 20) {
                list.subList(0, size - 10).clear();
            }
        }
    }

    public void drawResultBitmap(Bitmap bitmap) {
        this.resultBitmap = bitmap;
        invalidate();
    }

    public void drawViewfinder() {
        Bitmap bitmap = this.resultBitmap;
        this.resultBitmap = null;
        if (bitmap != null) {
            bitmap.recycle();
        }
        invalidate();
    }

    @Override // android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas canvas) {
        CameraManager cameraManager = this.cameraManager;
        if (cameraManager == null) {
            return;
        }
        Rect framingRect = cameraManager.getFramingRect();
        Rect framingRectInPreview = this.cameraManager.getFramingRectInPreview();
        if (framingRect == null || framingRectInPreview == null) {
            return;
        }
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        this.paint.setColor(this.resultBitmap != null ? this.resultColor : this.maskColor);
        float f9 = width;
        canvas.drawRect(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, f9, framingRect.top, this.paint);
        canvas.drawRect(BitmapDescriptorFactory.HUE_RED, framingRect.top, framingRect.left, framingRect.bottom + 1, this.paint);
        canvas.drawRect(framingRect.right + 1, framingRect.top, f9, framingRect.bottom + 1, this.paint);
        canvas.drawRect(BitmapDescriptorFactory.HUE_RED, framingRect.bottom + 1, f9, height, this.paint);
        if (this.resultBitmap != null) {
            this.paint.setAlpha(CURRENT_POINT_OPACITY);
            canvas.drawBitmap(this.resultBitmap, (Rect) null, framingRect, this.paint);
            return;
        }
        this.paint.setColor(this.laserColor);
        Paint paint = this.paint;
        int[] iArr = SCANNER_ALPHA;
        paint.setAlpha(iArr[this.scannerAlpha]);
        this.scannerAlpha = (this.scannerAlpha + 1) % iArr.length;
        int iHeight = (framingRect.height() / 2) + framingRect.top;
        canvas.drawRect(framingRect.left + 2, iHeight - 1, framingRect.right - 1, iHeight + 2, this.paint);
        float fWidth = framingRect.width() / framingRectInPreview.width();
        float fHeight = framingRect.height() / framingRectInPreview.height();
        List<ResultPoint> list = this.possibleResultPoints;
        List<ResultPoint> list2 = this.lastPossibleResultPoints;
        int i9 = framingRect.left;
        int i10 = framingRect.top;
        if (list.isEmpty()) {
            this.lastPossibleResultPoints = null;
        } else {
            this.possibleResultPoints = new ArrayList(5);
            this.lastPossibleResultPoints = list;
            this.paint.setAlpha(CURRENT_POINT_OPACITY);
            this.paint.setColor(this.resultPointColor);
            synchronized (list) {
                for (ResultPoint resultPoint : list) {
                    canvas.drawCircle(((int) (resultPoint.getX() * fWidth)) + i9, ((int) (resultPoint.getY() * fHeight)) + i10, 6.0f, this.paint);
                }
            }
        }
        if (list2 != null) {
            this.paint.setAlpha(80);
            this.paint.setColor(this.resultPointColor);
            synchronized (list2) {
                for (ResultPoint resultPoint2 : list2) {
                    canvas.drawCircle(((int) (resultPoint2.getX() * fWidth)) + i9, ((int) (resultPoint2.getY() * fHeight)) + i10, 3.0f, this.paint);
                }
            }
        }
        postInvalidateDelayed(ANIMATION_DELAY, framingRect.left - 6, framingRect.top - 6, framingRect.right + 6, framingRect.bottom + 6);
    }

    public void setCameraManager(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
    }
}
