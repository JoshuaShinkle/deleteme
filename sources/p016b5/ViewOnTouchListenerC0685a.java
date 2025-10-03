package p016b5;

import android.graphics.Point;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.mobeta.android.dslv.C4500b;
import com.mobeta.android.dslv.DragSortListView;

/* renamed from: b5.a */
/* loaded from: classes2.dex */
public class ViewOnTouchListenerC0685a extends C4500b implements View.OnTouchListener, GestureDetector.OnGestureListener {

    /* renamed from: A */
    public boolean f3308A;

    /* renamed from: B */
    public DragSortListView f3309B;

    /* renamed from: C */
    public int f3310C;

    /* renamed from: D */
    public GestureDetector.OnGestureListener f3311D;

    /* renamed from: f */
    public int f3312f;

    /* renamed from: g */
    public boolean f3313g;

    /* renamed from: h */
    public int f3314h;

    /* renamed from: i */
    public boolean f3315i;

    /* renamed from: j */
    public boolean f3316j;

    /* renamed from: k */
    public GestureDetector f3317k;

    /* renamed from: l */
    public GestureDetector f3318l;

    /* renamed from: m */
    public int f3319m;

    /* renamed from: n */
    public int f3320n;

    /* renamed from: o */
    public int f3321o;

    /* renamed from: p */
    public int f3322p;

    /* renamed from: q */
    public int[] f3323q;

    /* renamed from: r */
    public int f3324r;

    /* renamed from: s */
    public int f3325s;

    /* renamed from: t */
    public int f3326t;

    /* renamed from: u */
    public int f3327u;

    /* renamed from: v */
    public boolean f3328v;

    /* renamed from: w */
    public float f3329w;

    /* renamed from: x */
    public int f3330x;

    /* renamed from: y */
    public int f3331y;

    /* renamed from: z */
    public int f3332z;

    /* renamed from: b5.a$a */
    public class a extends GestureDetector.SimpleOnGestureListener {
        public a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f9, float f10) {
            if (ViewOnTouchListenerC0685a.this.f3315i && ViewOnTouchListenerC0685a.this.f3316j) {
                int width = ViewOnTouchListenerC0685a.this.f3309B.getWidth() / 5;
                if (f9 > ViewOnTouchListenerC0685a.this.f3329w) {
                    if (ViewOnTouchListenerC0685a.this.f3310C > (-width)) {
                        ViewOnTouchListenerC0685a.this.f3309B.m18062s0(true, f9);
                    }
                } else if (f9 < (-ViewOnTouchListenerC0685a.this.f3329w) && ViewOnTouchListenerC0685a.this.f3310C < width) {
                    ViewOnTouchListenerC0685a.this.f3309B.m18062s0(true, f9);
                }
                ViewOnTouchListenerC0685a.this.f3316j = false;
            }
            return false;
        }
    }

    public ViewOnTouchListenerC0685a(DragSortListView dragSortListView, int i9, int i10, int i11, int i12, int i13) {
        super(dragSortListView);
        this.f3312f = 0;
        this.f3313g = true;
        this.f3315i = false;
        this.f3316j = false;
        this.f3320n = -1;
        this.f3321o = -1;
        this.f3322p = -1;
        this.f3323q = new int[2];
        this.f3328v = false;
        this.f3329w = 500.0f;
        this.f3311D = new a();
        this.f3309B = dragSortListView;
        this.f3317k = new GestureDetector(dragSortListView.getContext(), this);
        GestureDetector gestureDetector = new GestureDetector(dragSortListView.getContext(), this.f3311D);
        this.f3318l = gestureDetector;
        gestureDetector.setIsLongpressEnabled(false);
        this.f3319m = ViewConfiguration.get(dragSortListView.getContext()).getScaledTouchSlop();
        this.f3330x = i9;
        this.f3331y = i12;
        this.f3332z = i13;
        m3434o(i11);
        m3432m(i10);
    }

    @Override // com.mobeta.android.dslv.DragSortListView.InterfaceC4494m
    /* renamed from: c */
    public void mo3429c(View view, Point point, Point point2) {
        if (this.f3315i && this.f3316j) {
            this.f3310C = point.x;
        }
    }

    /* renamed from: k */
    public int m3430k(MotionEvent motionEvent) {
        return m3439t(motionEvent, this.f3330x);
    }

    /* renamed from: l */
    public int m3431l(MotionEvent motionEvent) {
        return m3439t(motionEvent, this.f3332z);
    }

    /* renamed from: m */
    public void m3432m(int i9) {
        this.f3312f = i9;
    }

    /* renamed from: n */
    public void m3433n(boolean z8) {
        this.f3315i = z8;
    }

    /* renamed from: o */
    public void m3434o(int i9) {
        this.f3314h = i9;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        if (this.f3315i && this.f3314h == 0) {
            this.f3322p = m3439t(motionEvent, this.f3331y);
        }
        int iM3437r = m3437r(motionEvent);
        this.f3320n = iM3437r;
        if (iM3437r != -1 && this.f3312f == 0) {
            m3436q(iM3437r, ((int) motionEvent.getX()) - this.f3324r, ((int) motionEvent.getY()) - this.f3325s);
        }
        this.f3316j = false;
        this.f3308A = true;
        this.f3310C = 0;
        this.f3321o = m3438s(motionEvent);
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f9, float f10) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        if (this.f3320n == -1 || this.f3312f != 2) {
            return;
        }
        this.f3309B.performHapticFeedback(0);
        m3436q(this.f3320n, this.f3326t - this.f3324r, this.f3327u - this.f3325s);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f9, float f10) {
        int i9;
        int x8 = (int) motionEvent.getX();
        int y8 = (int) motionEvent.getY();
        int x9 = (int) motionEvent2.getX();
        int y9 = (int) motionEvent2.getY();
        int i10 = x9 - this.f3324r;
        int i11 = y9 - this.f3325s;
        if (this.f3308A && !this.f3328v && ((i9 = this.f3320n) != -1 || this.f3321o != -1)) {
            if (i9 != -1) {
                if (this.f3312f == 1 && Math.abs(y9 - y8) > this.f3319m && this.f3313g) {
                    m3436q(this.f3320n, i10, i11);
                } else if (this.f3312f != 0 && Math.abs(x9 - x8) > this.f3319m && this.f3315i) {
                    this.f3316j = true;
                    m3436q(this.f3321o, i10, i11);
                }
            } else if (this.f3321o != -1) {
                if (Math.abs(x9 - x8) > this.f3319m && this.f3315i) {
                    this.f3316j = true;
                    m3436q(this.f3321o, i10, i11);
                } else if (Math.abs(y9 - y8) > this.f3319m) {
                    this.f3308A = false;
                }
            }
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        int i9;
        if (!this.f3315i || this.f3314h != 0 || (i9 = this.f3322p) == -1) {
            return true;
        }
        DragSortListView dragSortListView = this.f3309B;
        dragSortListView.m18053j0(i9 - dragSortListView.getHeaderViewsCount());
        return true;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.f3309B.m18048e0() && !this.f3309B.m18049f0()) {
            this.f3317k.onTouchEvent(motionEvent);
            if (this.f3315i && this.f3328v && this.f3314h == 1) {
                this.f3318l.onTouchEvent(motionEvent);
            }
            int action = motionEvent.getAction() & 255;
            if (action != 0) {
                if (action != 1) {
                    if (action == 3) {
                    }
                } else if (this.f3315i && this.f3316j) {
                    int i9 = this.f3310C;
                    if (i9 < 0) {
                        i9 = -i9;
                    }
                    if (i9 > this.f3309B.getWidth() / 2) {
                        this.f3309B.m18062s0(true, BitmapDescriptorFactory.HUE_RED);
                    }
                }
                this.f3316j = false;
                this.f3328v = false;
            } else {
                this.f3326t = (int) motionEvent.getX();
                this.f3327u = (int) motionEvent.getY();
            }
        }
        return false;
    }

    /* renamed from: p */
    public void m3435p(boolean z8) {
        this.f3313g = z8;
    }

    /* renamed from: q */
    public boolean m3436q(int i9, int i10, int i11) {
        int i12 = (!this.f3313g || this.f3316j) ? 0 : 12;
        if (this.f3315i && this.f3316j) {
            i12 = i12 | 1 | 2;
        }
        DragSortListView dragSortListView = this.f3309B;
        boolean zM18058o0 = dragSortListView.m18058o0(i9 - dragSortListView.getHeaderViewsCount(), i12, i10, i11);
        this.f3328v = zM18058o0;
        return zM18058o0;
    }

    /* renamed from: r */
    public int m3437r(MotionEvent motionEvent) {
        return m3430k(motionEvent);
    }

    /* renamed from: s */
    public int m3438s(MotionEvent motionEvent) {
        if (this.f3314h == 1) {
            return m3431l(motionEvent);
        }
        return -1;
    }

    /* renamed from: t */
    public int m3439t(MotionEvent motionEvent, int i9) {
        int iPointToPosition = this.f3309B.pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        int headerViewsCount = this.f3309B.getHeaderViewsCount();
        int footerViewsCount = this.f3309B.getFooterViewsCount();
        int count = this.f3309B.getCount();
        if (iPointToPosition != -1 && iPointToPosition >= headerViewsCount && iPointToPosition < count - footerViewsCount) {
            DragSortListView dragSortListView = this.f3309B;
            View childAt = dragSortListView.getChildAt(iPointToPosition - dragSortListView.getFirstVisiblePosition());
            int rawX = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            View viewFindViewById = i9 == 0 ? childAt : childAt.findViewById(i9);
            if (viewFindViewById != null) {
                viewFindViewById.getLocationOnScreen(this.f3323q);
                int[] iArr = this.f3323q;
                int i10 = iArr[0];
                if (rawX > i10 && rawY > iArr[1] && rawX < i10 + viewFindViewById.getWidth() && rawY < this.f3323q[1] + viewFindViewById.getHeight()) {
                    this.f3324r = childAt.getLeft();
                    this.f3325s = childAt.getTop();
                    return iPointToPosition;
                }
            }
        }
        return -1;
    }
}
