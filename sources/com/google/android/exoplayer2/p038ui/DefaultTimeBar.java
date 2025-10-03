package com.google.android.exoplayer2.p038ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.p038ui.TimeBar;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes.dex */
public class DefaultTimeBar extends View implements TimeBar {
    public static final int DEFAULT_AD_MARKER_COLOR = -1291845888;
    public static final int DEFAULT_AD_MARKER_WIDTH_DP = 4;
    public static final int DEFAULT_BAR_HEIGHT_DP = 4;
    private static final int DEFAULT_INCREMENT_COUNT = 20;
    public static final int DEFAULT_PLAYED_COLOR = -1;
    public static final int DEFAULT_SCRUBBER_DISABLED_SIZE_DP = 0;
    public static final int DEFAULT_SCRUBBER_DRAGGED_SIZE_DP = 16;
    public static final int DEFAULT_SCRUBBER_ENABLED_SIZE_DP = 12;
    public static final int DEFAULT_TOUCH_TARGET_HEIGHT_DP = 26;
    private static final int FINE_SCRUB_RATIO = 3;
    private static final int FINE_SCRUB_Y_THRESHOLD_DP = -50;
    private static final long STOP_SCRUBBING_TIMEOUT_MS = 1000;
    private int adGroupCount;
    private long[] adGroupTimesMs;
    private final Paint adMarkerPaint;
    private final int adMarkerWidth;
    private final int barHeight;
    private final Rect bufferedBar;
    private final Paint bufferedPaint;
    private long bufferedPosition;
    private long duration;
    private final int fineScrubYThreshold;
    private final StringBuilder formatBuilder;
    private final Formatter formatter;
    private int keyCountIncrement;
    private long keyTimeIncrement;
    private int lastCoarseScrubXPosition;
    private final CopyOnWriteArraySet<TimeBar.OnScrubListener> listeners;
    private int[] locationOnScreen;
    private boolean[] playedAdGroups;
    private final Paint playedAdMarkerPaint;
    private final Paint playedPaint;
    private long position;
    private final Rect progressBar;
    private long scrubPosition;
    private final Rect scrubberBar;
    private final int scrubberDisabledSize;
    private final int scrubberDraggedSize;
    private final Drawable scrubberDrawable;
    private final int scrubberEnabledSize;
    private final int scrubberPadding;
    private final Paint scrubberPaint;
    private boolean scrubbing;
    private final Rect seekBounds;
    private final Runnable stopScrubbingRunnable;
    private Point touchPosition;
    private final int touchTargetHeight;
    private final Paint unplayedPaint;

    public DefaultTimeBar(Context context, AttributeSet attributeSet) {
        boolean z8;
        super(context, attributeSet);
        this.seekBounds = new Rect();
        this.progressBar = new Rect();
        this.bufferedBar = new Rect();
        this.scrubberBar = new Rect();
        Paint paint = new Paint();
        this.playedPaint = paint;
        Paint paint2 = new Paint();
        this.bufferedPaint = paint2;
        Paint paint3 = new Paint();
        this.unplayedPaint = paint3;
        Paint paint4 = new Paint();
        this.adMarkerPaint = paint4;
        Paint paint5 = new Paint();
        this.playedAdMarkerPaint = paint5;
        Paint paint6 = new Paint();
        this.scrubberPaint = paint6;
        paint6.setAntiAlias(true);
        this.listeners = new CopyOnWriteArraySet<>();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.fineScrubYThreshold = dpToPx(displayMetrics, FINE_SCRUB_Y_THRESHOLD_DP);
        int iDpToPx = dpToPx(displayMetrics, 4);
        int iDpToPx2 = dpToPx(displayMetrics, 26);
        int iDpToPx3 = dpToPx(displayMetrics, 4);
        int iDpToPx4 = dpToPx(displayMetrics, 12);
        int iDpToPx5 = dpToPx(displayMetrics, 0);
        int iDpToPx6 = dpToPx(displayMetrics, 16);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C3429R.styleable.DefaultTimeBar, 0, 0);
            try {
                Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(C3429R.styleable.DefaultTimeBar_scrubber_drawable);
                this.scrubberDrawable = drawable;
                if (drawable != null) {
                    setDrawableLayoutDirection(drawable);
                    iDpToPx2 = Math.max(drawable.getMinimumHeight(), iDpToPx2);
                }
                this.barHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3429R.styleable.DefaultTimeBar_bar_height, iDpToPx);
                this.touchTargetHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3429R.styleable.DefaultTimeBar_touch_target_height, iDpToPx2);
                this.adMarkerWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3429R.styleable.DefaultTimeBar_ad_marker_width, iDpToPx3);
                this.scrubberEnabledSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3429R.styleable.DefaultTimeBar_scrubber_enabled_size, iDpToPx4);
                this.scrubberDisabledSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3429R.styleable.DefaultTimeBar_scrubber_disabled_size, iDpToPx5);
                this.scrubberDraggedSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(C3429R.styleable.DefaultTimeBar_scrubber_dragged_size, iDpToPx6);
                int i9 = typedArrayObtainStyledAttributes.getInt(C3429R.styleable.DefaultTimeBar_played_color, -1);
                int i10 = typedArrayObtainStyledAttributes.getInt(C3429R.styleable.DefaultTimeBar_scrubber_color, getDefaultScrubberColor(i9));
                int i11 = typedArrayObtainStyledAttributes.getInt(C3429R.styleable.DefaultTimeBar_buffered_color, getDefaultBufferedColor(i9));
                int i12 = typedArrayObtainStyledAttributes.getInt(C3429R.styleable.DefaultTimeBar_unplayed_color, getDefaultUnplayedColor(i9));
                int i13 = typedArrayObtainStyledAttributes.getInt(C3429R.styleable.DefaultTimeBar_ad_marker_color, DEFAULT_AD_MARKER_COLOR);
                int i14 = typedArrayObtainStyledAttributes.getInt(C3429R.styleable.DefaultTimeBar_played_ad_marker_color, getDefaultPlayedAdMarkerColor(i13));
                paint.setColor(i9);
                paint6.setColor(i10);
                paint2.setColor(i11);
                paint3.setColor(i12);
                paint4.setColor(i13);
                paint5.setColor(i14);
            } finally {
                typedArrayObtainStyledAttributes.recycle();
            }
        } else {
            this.barHeight = iDpToPx;
            this.touchTargetHeight = iDpToPx2;
            this.adMarkerWidth = iDpToPx3;
            this.scrubberEnabledSize = iDpToPx4;
            this.scrubberDisabledSize = iDpToPx5;
            this.scrubberDraggedSize = iDpToPx6;
            paint.setColor(-1);
            paint6.setColor(getDefaultScrubberColor(-1));
            paint2.setColor(getDefaultBufferedColor(-1));
            paint3.setColor(getDefaultUnplayedColor(-1));
            paint4.setColor(DEFAULT_AD_MARKER_COLOR);
            this.scrubberDrawable = null;
        }
        StringBuilder sb = new StringBuilder();
        this.formatBuilder = sb;
        this.formatter = new Formatter(sb, Locale.getDefault());
        this.stopScrubbingRunnable = new Runnable() { // from class: com.google.android.exoplayer2.ui.DefaultTimeBar.1
            @Override // java.lang.Runnable
            public void run() {
                DefaultTimeBar.this.stopScrubbing(false);
            }
        };
        Drawable drawable2 = this.scrubberDrawable;
        if (drawable2 != null) {
            z8 = true;
            this.scrubberPadding = (drawable2.getMinimumWidth() + 1) / 2;
        } else {
            z8 = true;
            this.scrubberPadding = (Math.max(this.scrubberDisabledSize, Math.max(this.scrubberEnabledSize, this.scrubberDraggedSize)) + 1) / 2;
        }
        this.duration = C3322C.TIME_UNSET;
        this.keyTimeIncrement = C3322C.TIME_UNSET;
        this.keyCountIncrement = 20;
        setFocusable(z8);
        if (Util.SDK_INT >= 16) {
            maybeSetImportantForAccessibilityV16();
        }
    }

    private static int dpToPx(DisplayMetrics displayMetrics, int i9) {
        return (int) ((i9 * displayMetrics.density) + 0.5f);
    }

    private void drawPlayhead(Canvas canvas) {
        if (this.duration <= 0) {
            return;
        }
        Rect rect = this.scrubberBar;
        int iConstrainValue = Util.constrainValue(rect.right, rect.left, this.progressBar.right);
        int iCenterY = this.scrubberBar.centerY();
        Drawable drawable = this.scrubberDrawable;
        if (drawable == null) {
            canvas.drawCircle(iConstrainValue, iCenterY, ((this.scrubbing || isFocused()) ? this.scrubberDraggedSize : isEnabled() ? this.scrubberEnabledSize : this.scrubberDisabledSize) / 2, this.scrubberPaint);
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth() / 2;
        int intrinsicHeight = this.scrubberDrawable.getIntrinsicHeight() / 2;
        this.scrubberDrawable.setBounds(iConstrainValue - intrinsicWidth, iCenterY - intrinsicHeight, iConstrainValue + intrinsicWidth, iCenterY + intrinsicHeight);
        this.scrubberDrawable.draw(canvas);
    }

    private void drawTimeBar(Canvas canvas) {
        int iHeight = this.progressBar.height();
        int iCenterY = this.progressBar.centerY() - (iHeight / 2);
        int i9 = iHeight + iCenterY;
        if (this.duration <= 0) {
            Rect rect = this.progressBar;
            canvas.drawRect(rect.left, iCenterY, rect.right, i9, this.unplayedPaint);
            return;
        }
        Rect rect2 = this.bufferedBar;
        int i10 = rect2.left;
        int i11 = rect2.right;
        int iMax = Math.max(Math.max(this.progressBar.left, i11), this.scrubberBar.right);
        int i12 = this.progressBar.right;
        if (iMax < i12) {
            canvas.drawRect(iMax, iCenterY, i12, i9, this.unplayedPaint);
        }
        int iMax2 = Math.max(i10, this.scrubberBar.right);
        if (i11 > iMax2) {
            canvas.drawRect(iMax2, iCenterY, i11, i9, this.bufferedPaint);
        }
        if (this.scrubberBar.width() > 0) {
            Rect rect3 = this.scrubberBar;
            canvas.drawRect(rect3.left, iCenterY, rect3.right, i9, this.playedPaint);
        }
        int i13 = this.adMarkerWidth / 2;
        for (int i14 = 0; i14 < this.adGroupCount; i14++) {
            int iWidth = ((int) ((this.progressBar.width() * Util.constrainValue(this.adGroupTimesMs[i14], 0L, this.duration)) / this.duration)) - i13;
            Rect rect4 = this.progressBar;
            canvas.drawRect(rect4.left + Math.min(rect4.width() - this.adMarkerWidth, Math.max(0, iWidth)), iCenterY, r7 + this.adMarkerWidth, i9, this.playedAdGroups[i14] ? this.playedAdMarkerPaint : this.adMarkerPaint);
        }
    }

    public static int getDefaultBufferedColor(int i9) {
        return (i9 & 16777215) | (-872415232);
    }

    public static int getDefaultPlayedAdMarkerColor(int i9) {
        return (i9 & 16777215) | 855638016;
    }

    public static int getDefaultScrubberColor(int i9) {
        return i9 | (-16777216);
    }

    public static int getDefaultUnplayedColor(int i9) {
        return (i9 & 16777215) | 855638016;
    }

    private long getPositionIncrement() {
        long j9 = this.keyTimeIncrement;
        if (j9 != C3322C.TIME_UNSET) {
            return j9;
        }
        long j10 = this.duration;
        if (j10 == C3322C.TIME_UNSET) {
            return 0L;
        }
        return j10 / this.keyCountIncrement;
    }

    private String getProgressText() {
        return Util.getStringForTime(this.formatBuilder, this.formatter, this.position);
    }

    private long getScrubberPosition() {
        if (this.progressBar.width() <= 0 || this.duration == C3322C.TIME_UNSET) {
            return 0L;
        }
        return (this.scrubberBar.width() * this.duration) / this.progressBar.width();
    }

    private boolean isInSeekBar(float f9, float f10) {
        return this.seekBounds.contains((int) f9, (int) f10);
    }

    @TargetApi(16)
    private void maybeSetImportantForAccessibilityV16() {
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    private void positionScrubber(float f9) {
        Rect rect = this.scrubberBar;
        Rect rect2 = this.progressBar;
        rect.right = Util.constrainValue((int) f9, rect2.left, rect2.right);
    }

    private Point resolveRelativeTouchPosition(MotionEvent motionEvent) {
        if (this.locationOnScreen == null) {
            this.locationOnScreen = new int[2];
            this.touchPosition = new Point();
        }
        getLocationOnScreen(this.locationOnScreen);
        this.touchPosition.set(((int) motionEvent.getRawX()) - this.locationOnScreen[0], ((int) motionEvent.getRawY()) - this.locationOnScreen[1]);
        return this.touchPosition;
    }

    private boolean scrubIncrementally(long j9) {
        if (this.duration <= 0) {
            return false;
        }
        long scrubberPosition = getScrubberPosition();
        long jConstrainValue = Util.constrainValue(scrubberPosition + j9, 0L, this.duration);
        this.scrubPosition = jConstrainValue;
        if (jConstrainValue == scrubberPosition) {
            return false;
        }
        if (!this.scrubbing) {
            startScrubbing();
        }
        Iterator<TimeBar.OnScrubListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onScrubMove(this, this.scrubPosition);
        }
        update();
        return true;
    }

    private boolean setDrawableLayoutDirection(Drawable drawable) {
        return Util.SDK_INT >= 23 && setDrawableLayoutDirection(drawable, getLayoutDirection());
    }

    private void startScrubbing() {
        this.scrubbing = true;
        setPressed(true);
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        Iterator<TimeBar.OnScrubListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onScrubStart(this, getScrubberPosition());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopScrubbing(boolean z8) {
        this.scrubbing = false;
        setPressed(false);
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        invalidate();
        Iterator<TimeBar.OnScrubListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onScrubStop(this, getScrubberPosition(), z8);
        }
    }

    private void update() {
        this.bufferedBar.set(this.progressBar);
        this.scrubberBar.set(this.progressBar);
        long j9 = this.scrubbing ? this.scrubPosition : this.position;
        if (this.duration > 0) {
            int iWidth = (int) ((this.progressBar.width() * this.bufferedPosition) / this.duration);
            Rect rect = this.bufferedBar;
            Rect rect2 = this.progressBar;
            rect.right = Math.min(rect2.left + iWidth, rect2.right);
            int iWidth2 = (int) ((this.progressBar.width() * j9) / this.duration);
            Rect rect3 = this.scrubberBar;
            Rect rect4 = this.progressBar;
            rect3.right = Math.min(rect4.left + iWidth2, rect4.right);
        } else {
            Rect rect5 = this.bufferedBar;
            int i9 = this.progressBar.left;
            rect5.right = i9;
            this.scrubberBar.right = i9;
        }
        invalidate(this.seekBounds);
    }

    private void updateDrawableState() {
        Drawable drawable = this.scrubberDrawable;
        if (drawable != null && drawable.isStateful() && this.scrubberDrawable.setState(getDrawableState())) {
            invalidate();
        }
    }

    @Override // com.google.android.exoplayer2.p038ui.TimeBar
    public void addListener(TimeBar.OnScrubListener onScrubListener) {
        this.listeners.add(onScrubListener);
    }

    @Override // android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        updateDrawableState();
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.scrubberDrawable;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        canvas.save();
        drawTimeBar(canvas);
        drawPlayhead(canvas);
        canvas.restore();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (accessibilityEvent.getEventType() == 4) {
            accessibilityEvent.getText().add(getProgressText());
        }
        accessibilityEvent.setClassName(DefaultTimeBar.class.getName());
    }

    @Override // android.view.View
    @TargetApi(21)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(DefaultTimeBar.class.getCanonicalName());
        accessibilityNodeInfo.setContentDescription(getProgressText());
        if (this.duration <= 0) {
            return;
        }
        int i9 = Util.SDK_INT;
        if (i9 >= 21) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
        } else if (i9 >= 16) {
            accessibilityNodeInfo.addAction(4096);
            accessibilityNodeInfo.addAction(UserMetadata.MAX_INTERNAL_KEY_SIZE);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    @Override // android.view.View, android.view.KeyEvent.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onKeyDown(int i9, KeyEvent keyEvent) {
        if (isEnabled()) {
            long positionIncrement = getPositionIncrement();
            if (i9 != 66) {
                switch (i9) {
                    case 21:
                        positionIncrement = -positionIncrement;
                        if (scrubIncrementally(positionIncrement)) {
                            removeCallbacks(this.stopScrubbingRunnable);
                            postDelayed(this.stopScrubbingRunnable, 1000L);
                            break;
                        }
                        break;
                    case 22:
                        if (scrubIncrementally(positionIncrement)) {
                        }
                        break;
                    case 23:
                        if (this.scrubbing) {
                            removeCallbacks(this.stopScrubbingRunnable);
                            this.stopScrubbingRunnable.run();
                            break;
                        }
                        break;
                }
                return true;
            }
        }
        return super.onKeyDown(i9, keyEvent);
    }

    @Override // android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        int i13 = ((i12 - i10) - this.touchTargetHeight) / 2;
        int paddingLeft = getPaddingLeft();
        int paddingRight = (i11 - i9) - getPaddingRight();
        int i14 = this.touchTargetHeight;
        int i15 = ((i14 - this.barHeight) / 2) + i13;
        this.seekBounds.set(paddingLeft, i13, paddingRight, i14 + i13);
        Rect rect = this.progressBar;
        Rect rect2 = this.seekBounds;
        int i16 = rect2.left;
        int i17 = this.scrubberPadding;
        rect.set(i16 + i17, i15, rect2.right - i17, this.barHeight + i15);
        update();
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        int mode = View.MeasureSpec.getMode(i10);
        int size = View.MeasureSpec.getSize(i10);
        if (mode == 0) {
            size = this.touchTargetHeight;
        } else if (mode != 1073741824) {
            size = Math.min(this.touchTargetHeight, size);
        }
        setMeasuredDimension(View.MeasureSpec.getSize(i9), size);
        updateDrawableState();
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i9) {
        Drawable drawable = this.scrubberDrawable;
        if (drawable == null || !setDrawableLayoutDirection(drawable, i9)) {
            return;
        }
        invalidate();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0066  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled() && this.duration > 0) {
            Point pointResolveRelativeTouchPosition = resolveRelativeTouchPosition(motionEvent);
            int i9 = pointResolveRelativeTouchPosition.x;
            int i10 = pointResolveRelativeTouchPosition.y;
            int action = motionEvent.getAction();
            if (action == 0) {
                float f9 = i9;
                if (isInSeekBar(f9, i10)) {
                    startScrubbing();
                    positionScrubber(f9);
                    this.scrubPosition = getScrubberPosition();
                    update();
                    invalidate();
                    return true;
                }
            } else if (action == 1) {
                if (this.scrubbing) {
                    stopScrubbing(motionEvent.getAction() == 3);
                    return true;
                }
            } else if (action != 2) {
                if (action == 3) {
                }
            } else if (this.scrubbing) {
                if (i10 < this.fineScrubYThreshold) {
                    int i11 = this.lastCoarseScrubXPosition;
                    positionScrubber(i11 + ((i9 - i11) / 3));
                } else {
                    this.lastCoarseScrubXPosition = i9;
                    positionScrubber(i9);
                }
                this.scrubPosition = getScrubberPosition();
                Iterator<TimeBar.OnScrubListener> it = this.listeners.iterator();
                while (it.hasNext()) {
                    it.next().onScrubMove(this, this.scrubPosition);
                }
                update();
                invalidate();
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    @TargetApi(16)
    public boolean performAccessibilityAction(int i9, Bundle bundle) {
        if (super.performAccessibilityAction(i9, bundle)) {
            return true;
        }
        if (this.duration <= 0) {
            return false;
        }
        if (i9 == 8192) {
            if (scrubIncrementally(-getPositionIncrement())) {
                stopScrubbing(false);
            }
        } else {
            if (i9 != 4096) {
                return false;
            }
            if (scrubIncrementally(getPositionIncrement())) {
                stopScrubbing(false);
            }
        }
        sendAccessibilityEvent(4);
        return true;
    }

    @Override // com.google.android.exoplayer2.p038ui.TimeBar
    public void removeListener(TimeBar.OnScrubListener onScrubListener) {
        this.listeners.remove(onScrubListener);
    }

    @Override // com.google.android.exoplayer2.p038ui.TimeBar
    public void setAdGroupTimesMs(long[] jArr, boolean[] zArr, int i9) {
        Assertions.checkArgument(i9 == 0 || !(jArr == null || zArr == null));
        this.adGroupCount = i9;
        this.adGroupTimesMs = jArr;
        this.playedAdGroups = zArr;
        update();
    }

    @Override // com.google.android.exoplayer2.p038ui.TimeBar
    public void setBufferedPosition(long j9) {
        this.bufferedPosition = j9;
        update();
    }

    @Override // com.google.android.exoplayer2.p038ui.TimeBar
    public void setDuration(long j9) {
        this.duration = j9;
        if (this.scrubbing && j9 == C3322C.TIME_UNSET) {
            stopScrubbing(true);
        }
        update();
    }

    @Override // android.view.View, com.google.android.exoplayer2.p038ui.TimeBar
    public void setEnabled(boolean z8) {
        super.setEnabled(z8);
        if (!this.scrubbing || z8) {
            return;
        }
        stopScrubbing(true);
    }

    @Override // com.google.android.exoplayer2.p038ui.TimeBar
    public void setKeyCountIncrement(int i9) {
        Assertions.checkArgument(i9 > 0);
        this.keyCountIncrement = i9;
        this.keyTimeIncrement = C3322C.TIME_UNSET;
    }

    @Override // com.google.android.exoplayer2.p038ui.TimeBar
    public void setKeyTimeIncrement(long j9) {
        Assertions.checkArgument(j9 > 0);
        this.keyCountIncrement = -1;
        this.keyTimeIncrement = j9;
    }

    @Override // com.google.android.exoplayer2.p038ui.TimeBar
    public void setPosition(long j9) {
        this.position = j9;
        setContentDescription(getProgressText());
        update();
    }

    private static boolean setDrawableLayoutDirection(Drawable drawable, int i9) {
        return Util.SDK_INT >= 23 && drawable.setLayoutDirection(i9);
    }
}
