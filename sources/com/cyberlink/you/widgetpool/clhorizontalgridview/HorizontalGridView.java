package com.cyberlink.you.widgetpool.clhorizontalgridview;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.GridLayoutAnimationController;
import android.widget.Checkable;
import android.widget.ListAdapter;
import android.widget.RemoteViews;
import com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView;
import com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.extractor.p037ts.TsExtractor;
import p218v2.C6453a0;

@RemoteViews.RemoteView
/* loaded from: classes.dex */
public class HorizontalGridView extends AbsListView {

    /* renamed from: f1 */
    public int f14991f1;

    /* renamed from: g1 */
    public int f14992g1;

    /* renamed from: h1 */
    public int f14993h1;

    /* renamed from: i1 */
    public int f14994i1;

    /* renamed from: j1 */
    public int f14995j1;

    /* renamed from: k1 */
    public int f14996k1;

    /* renamed from: l1 */
    public int f14997l1;

    /* renamed from: m1 */
    public int f14998m1;

    /* renamed from: n1 */
    public View f14999n1;

    /* renamed from: o1 */
    public View f15000o1;

    /* renamed from: p1 */
    public int f15001p1;

    /* renamed from: q1 */
    public final Rect f15002q1;

    public HorizontalGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.gridViewStyle);
    }

    /* renamed from: N0 */
    public final void m17211N0(View view, int i9, int i10) {
        if (view.getLeft() < i9) {
            m17146m0(Math.min(i9 - view.getLeft(), i10 - view.getRight()));
        }
    }

    /* renamed from: O0 */
    public final void m17212O0(View view, int i9, int i10) {
        if (view.getRight() > i10) {
            m17146m0(-Math.min(view.getLeft() - i9, view.getRight() - i10));
        }
    }

    /* renamed from: P0 */
    public final void m17213P0() {
        int childCount = getChildCount();
        if (childCount > 0) {
            int i9 = 0;
            if (this.f14896l0) {
                int right = getChildAt(childCount - 1).getRight() - (getWidth() - this.f14864Q.right);
                if (this.f14960b + childCount < this.f14976r) {
                    right += this.f14992g1;
                }
                if (right <= 0) {
                    i9 = right;
                }
            } else {
                int left = getChildAt(0).getLeft() - this.f14864Q.left;
                if (this.f14960b != 0) {
                    left -= this.f14992g1;
                }
                if (left >= 0) {
                    i9 = left;
                }
            }
            if (i9 != 0) {
                m17146m0(-i9);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0077  */
    /* renamed from: Q0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean m17214Q0(int i9) {
        int iMin;
        int iMax;
        int i10 = this.f14973o;
        int i11 = this.f14991f1;
        boolean z8 = true;
        if (this.f14896l0) {
            int i12 = this.f14976r;
            iMin = (i12 - 1) - ((((i12 - 1) - i10) / i11) * i11);
            iMax = Math.max(0, (iMin - i11) + 1);
        } else {
            iMax = (i10 / i11) * i11;
            iMin = Math.min((iMax + i11) - 1, this.f14976r - 1);
        }
        if (i9 != 17) {
            if (i9 != 33) {
                if (i9 == 66) {
                    int i13 = this.f14976r;
                    if (iMin < i13 - 1) {
                        this.f14838D = 6;
                        setSelectionInt(Math.min(i10 + i11, i13 - 1));
                    }
                } else if (i9 == 130 && i10 < iMin) {
                    this.f14838D = 6;
                    setSelectionInt(Math.min(i10 + 1, this.f14976r - 1));
                } else {
                    z8 = false;
                }
            } else if (i10 > iMax) {
                this.f14838D = 6;
                setSelectionInt(Math.max(0, i10 - 1));
            }
        } else if (iMax > 0) {
            this.f14838D = 6;
            setSelectionInt(Math.max(0, i10 - i11));
        }
        if (z8) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i9));
            m17141g0();
        }
        if (z8) {
            awakenScrollBars();
        }
        return z8;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x013d  */
    /* renamed from: R0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m17215R0(int i9, int i10, KeyEvent keyEvent) {
        boolean zM17159y0;
        if (this.f14842F == null) {
            return false;
        }
        if (this.f14970l) {
            mo17144k0();
        }
        int action = keyEvent.getAction();
        if (action == 1) {
            zM17159y0 = false;
        } else if (i9 == 66) {
            if (keyEvent.hasNoModifiers()) {
                zM17159y0 = m17159y0();
                if (!zM17159y0 && keyEvent.getRepeatCount() == 0 && getChildCount() > 0) {
                    m17143j0();
                    zM17159y0 = true;
                }
            }
        } else if (i9 != 92) {
            if (i9 != 93) {
                if (i9 != 122) {
                    if (i9 != 123) {
                        switch (i9) {
                            case 19:
                                if (!keyEvent.hasNoModifiers() ? !(!keyEvent.hasModifiers(2) || (!m17159y0() && !m17226c1(33))) : !(!m17159y0() && !m17214Q0(33))) {
                                    zM17159y0 = true;
                                    break;
                                }
                                break;
                            case 20:
                                if (!keyEvent.hasNoModifiers() ? !keyEvent.hasModifiers(2) || (!m17159y0() && !m17226c1(TsExtractor.TS_STREAM_TYPE_HDMV_DTS)) : !m17159y0() && !m17214Q0(TsExtractor.TS_STREAM_TYPE_HDMV_DTS)) {
                                }
                                break;
                            case 21:
                                if (!keyEvent.hasNoModifiers() || (!m17159y0() && !m17214Q0(17))) {
                                }
                                break;
                            case 22:
                                if (!keyEvent.hasNoModifiers() || (!m17159y0() && !m17214Q0(66))) {
                                }
                                break;
                        }
                    } else if (!keyEvent.hasNoModifiers() || (!m17159y0() && !m17226c1(TsExtractor.TS_STREAM_TYPE_HDMV_DTS))) {
                    }
                } else if (!keyEvent.hasNoModifiers() || (!m17159y0() && !m17226c1(33))) {
                }
            } else if (!keyEvent.hasNoModifiers() ? !keyEvent.hasModifiers(2) || (!m17159y0() && !m17226c1(TsExtractor.TS_STREAM_TYPE_HDMV_DTS)) : !m17159y0() && !m17233j1(TsExtractor.TS_STREAM_TYPE_HDMV_DTS)) {
            }
        } else if (!keyEvent.hasNoModifiers() ? !keyEvent.hasModifiers(2) || (!m17159y0() && !m17226c1(33)) : !m17159y0() && !m17233j1(33)) {
        }
        if (zM17159y0) {
            return true;
        }
        if (action == 0) {
            return super.onKeyDown(i9, keyEvent);
        }
        if (action == 1) {
            return super.onKeyUp(i9, keyEvent);
        }
        if (action != 2) {
            return false;
        }
        return super.onKeyMultiple(i9, i10, keyEvent);
    }

    /* renamed from: S0 */
    public final void m17216S0(int i9, int i10, int i11) {
        if ((this.f14960b + i11) - 1 != this.f14976r - 1 || i11 <= 0) {
            return;
        }
        int right = ((getRight() - getLeft()) - this.f14864Q.right) - getChildAt(i11 - 1).getRight();
        View childAt = getChildAt(0);
        int left = childAt.getLeft();
        if (right > 0) {
            int i12 = this.f14960b;
            if (i12 > 0 || left < this.f14864Q.left) {
                if (i12 == 0) {
                    right = Math.min(right, this.f14864Q.left - left);
                }
                m17146m0(right);
                int i13 = this.f14960b;
                if (i13 > 0) {
                    if (this.f14896l0) {
                        i9 = 1;
                    }
                    m17222Y0(i13 - i9, childAt.getLeft() - i10);
                    m17213P0();
                }
            }
        }
    }

    /* renamed from: T0 */
    public final void m17217T0(int i9, int i10, int i11) {
        if (this.f14960b != 0 || i11 <= 0) {
            return;
        }
        int left = getChildAt(0).getLeft();
        int i12 = this.f14864Q.left;
        int right = (getRight() - getLeft()) - this.f14864Q.right;
        int iMin = left - i12;
        View childAt = getChildAt(i11 - 1);
        int right2 = childAt.getRight();
        int i13 = (this.f14960b + i11) - 1;
        if (iMin > 0) {
            int i14 = this.f14976r;
            if (i13 < i14 - 1 || right2 > right) {
                if (i13 == i14 - 1) {
                    iMin = Math.min(iMin, right2 - right);
                }
                m17146m0(-iMin);
                if (i13 < this.f14976r - 1) {
                    if (!this.f14896l0) {
                        i9 = 1;
                    }
                    m17223Z0(i13 + i9, childAt.getRight() + i10);
                    m17213P0();
                }
            }
        }
    }

    /* renamed from: U0 */
    public final boolean m17218U0(int i9) {
        int i10 = this.f14994i1;
        int i11 = this.f14995j1;
        int i12 = this.f14997l1;
        int i13 = this.f14998m1;
        if (i13 != -1) {
            this.f14991f1 = i13;
        } else if (i12 > 0) {
            this.f14991f1 = (i9 + i10) / (i12 + i10);
        } else {
            this.f14991f1 = 2;
        }
        if (this.f14991f1 <= 0) {
            this.f14991f1 = 1;
        }
        if (i11 != 0) {
            int i14 = this.f14991f1;
            int i15 = (i9 - (i14 * i12)) - ((i14 - 1) * i10);
            z = i15 < 0;
            if (i11 == 1) {
                this.f14996k1 = i12;
                if (i14 > 1) {
                    this.f14993h1 = i10 + (i15 / (i14 - 1));
                } else {
                    this.f14993h1 = i10 + i15;
                }
            } else if (i11 == 2) {
                this.f14996k1 = i12 + (i15 / i14);
                this.f14993h1 = i10;
            } else if (i11 == 3) {
                this.f14996k1 = i12;
                if (i14 > 1) {
                    this.f14993h1 = i10 + (i15 / (i14 + 1));
                } else {
                    this.f14993h1 = i10 + i15;
                }
            }
        } else {
            this.f14996k1 = i12;
            this.f14993h1 = i10;
        }
        return z;
    }

    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView
    /* renamed from: V */
    public void mo17131V(boolean z8) {
        int i9 = this.f14991f1;
        int i10 = this.f14992g1;
        int childCount = getChildCount();
        if (!z8) {
            int left = childCount > 0 ? getChildAt(0).getLeft() - i10 : getWidth() - 0;
            int i11 = this.f14960b;
            m17222Y0(!this.f14896l0 ? i11 - i9 : i11 - 1, left);
            m17217T0(i9, i10, getChildCount());
            return;
        }
        int right = childCount > 0 ? getChildAt(childCount - 1).getRight() + i10 : 0;
        int i12 = this.f14960b + childCount;
        if (this.f14896l0) {
            i12 += i9 - 1;
        }
        m17223Z0(i12, right);
        m17216S0(i9, i10, getChildCount());
    }

    /* renamed from: V0 */
    public final View m17219V0(int i9) {
        int iMin = Math.min(this.f14960b, this.f14973o);
        this.f14960b = iMin;
        int iMin2 = Math.min(iMin, this.f14976r - 1);
        this.f14960b = iMin2;
        if (iMin2 < 0) {
            this.f14960b = 0;
        }
        int i10 = this.f14960b;
        int i11 = i10 - (i10 % this.f14991f1);
        this.f14960b = i11;
        return m17223Z0(i11, i9);
    }

    /* renamed from: W0 */
    public final View m17220W0(int i9, int i10) {
        int iMin = Math.min(Math.max(i9, this.f14973o), this.f14976r - 1);
        int i11 = this.f14976r;
        int i12 = (i11 - 1) - iMin;
        return m17222Y0((i11 - 1) - (i12 - (i12 % this.f14991f1)), i10);
    }

    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView
    /* renamed from: X */
    public int mo17133X(int i9) {
        int childCount = getChildCount();
        if (childCount <= 0) {
            return -1;
        }
        int i10 = this.f14991f1;
        if (this.f14896l0) {
            for (int i11 = childCount - 1; i11 >= 0; i11 -= i10) {
                if (i9 >= getChildAt(i11).getLeft()) {
                    return this.f14960b + i11;
                }
            }
            return -1;
        }
        for (int i12 = 0; i12 < childCount; i12 += i10) {
            if (i9 <= getChildAt(i12).getRight()) {
                return this.f14960b + i12;
            }
        }
        return -1;
    }

    /* renamed from: X0 */
    public final View m17221X0(int i9, int i10, int i11) {
        int i12;
        int iMax;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int i13 = this.f14973o;
        int i14 = this.f14991f1;
        int i15 = this.f14992g1;
        if (this.f14896l0) {
            int i16 = this.f14976r;
            int i17 = (i16 - 1) - i13;
            i12 = (i16 - 1) - (i17 - (i17 % i14));
            iMax = Math.max(0, (i12 - i14) + 1);
        } else {
            iMax = i13 - (i13 % i14);
            i12 = -1;
        }
        int iM17227d1 = m17227d1(i10, verticalFadingEdgeLength, iMax);
        int iM17228e1 = m17228e1(i11, verticalFadingEdgeLength, i14, iMax);
        View viewM17231h1 = m17231h1(this.f14896l0 ? i12 : iMax, i9, true);
        this.f14960b = iMax;
        View view = this.f14999n1;
        m17211N0(view, iM17227d1, iM17228e1);
        m17212O0(view, iM17227d1, iM17228e1);
        if (this.f14896l0) {
            m17223Z0(i12 + i14, view.getRight() + i15);
            m17213P0();
            m17222Y0(iMax - 1, view.getLeft() - i15);
        } else {
            m17222Y0(iMax - i14, view.getLeft() - i15);
            m17213P0();
            m17223Z0(iMax + i14, view.getRight() + i15);
        }
        return viewM17231h1;
    }

    /* renamed from: Y0 */
    public final View m17222Y0(int i9, int i10) {
        View view = null;
        while (i10 > 0 && i9 >= 0) {
            View viewM17231h1 = m17231h1(i9, i10, false);
            if (viewM17231h1 != null) {
                view = viewM17231h1;
            }
            i10 = this.f14999n1.getLeft() - this.f14992g1;
            this.f14960b = i9;
            i9 -= this.f14991f1;
        }
        if (this.f14896l0) {
            this.f14960b = Math.max(0, i9 + 1);
        }
        return view;
    }

    /* renamed from: Z0 */
    public final View m17223Z0(int i9, int i10) {
        int right = getRight() - getLeft();
        View view = null;
        while (i10 < right && i9 < this.f14976r) {
            View viewM17231h1 = m17231h1(i9, i10, true);
            if (viewM17231h1 != null) {
                view = viewM17231h1;
            }
            i10 = this.f14999n1.getRight() + this.f14992g1;
            i9 += this.f14991f1;
        }
        return view;
    }

    /* renamed from: a1 */
    public final View m17224a1(int i9, int i10) {
        int i11;
        int iMax;
        int iM17153s0 = m17153s0();
        int i12 = this.f14991f1;
        int i13 = this.f14992g1;
        if (this.f14896l0) {
            int i14 = this.f14976r;
            int i15 = (i14 - 1) - iM17153s0;
            i11 = (i14 - 1) - (i15 - (i15 % i12));
            iMax = Math.max(0, (i11 - i12) + 1);
        } else {
            iMax = iM17153s0 - (iM17153s0 % i12);
            i11 = -1;
        }
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        View viewM17231h1 = m17231h1(this.f14896l0 ? i11 : iMax, m17227d1(i9, horizontalFadingEdgeLength, iMax), true);
        this.f14960b = iMax;
        View view = this.f14999n1;
        if (view == null) {
            return null;
        }
        if (this.f14896l0) {
            m17146m0(m17228e1(i10, horizontalFadingEdgeLength, i12, iMax) - view.getRight());
            m17222Y0(iMax - 1, view.getLeft() - i13);
            m17234k1(i9);
            m17223Z0(i11 + i12, view.getRight() + i13);
            m17213P0();
        } else {
            m17223Z0(iMax + i12, view.getRight() + i13);
            m17235l1(i10);
            m17222Y0(iMax - i12, view.getLeft() - i13);
            m17213P0();
        }
        return viewM17231h1;
    }

    @Override // android.view.ViewGroup
    public void attachLayoutAnimationParameters(View view, ViewGroup.LayoutParams layoutParams, int i9, int i10) {
        GridLayoutAnimationController.AnimationParameters animationParameters = (GridLayoutAnimationController.AnimationParameters) layoutParams.layoutAnimationParameters;
        if (animationParameters == null) {
            animationParameters = new GridLayoutAnimationController.AnimationParameters();
            layoutParams.layoutAnimationParameters = animationParameters;
        }
        animationParameters.count = i10;
        animationParameters.index = i9;
        int i11 = this.f14991f1;
        int i12 = i10 / i11;
        animationParameters.columnsCount = i12;
        animationParameters.rowsCount = i11;
        if (!this.f14896l0) {
            animationParameters.column = i9 / i11;
            animationParameters.row = i9 % i11;
        } else {
            int i13 = (i10 - 1) - i9;
            animationParameters.column = (i12 - 1) - (i13 / i11);
            animationParameters.row = (i11 - 1) - (i13 % i11);
        }
    }

    /* renamed from: b1 */
    public final View m17225b1(int i9, int i10) {
        int i11;
        int iMax;
        View viewM17223Z0;
        View viewM17222Y0;
        int i12 = this.f14991f1;
        if (this.f14896l0) {
            int i13 = this.f14976r;
            int i14 = (i13 - 1) - i9;
            i11 = (i13 - 1) - (i14 - (i14 % i12));
            iMax = Math.max(0, (i11 - i12) + 1);
        } else {
            iMax = i9 - (i9 % i12);
            i11 = -1;
        }
        View viewM17231h1 = m17231h1(this.f14896l0 ? i11 : iMax, i10, true);
        this.f14960b = iMax;
        View view = this.f14999n1;
        if (view == null) {
            return null;
        }
        int i15 = this.f14992g1;
        if (this.f14896l0) {
            viewM17223Z0 = m17223Z0(i11 + i12, view.getRight() + i15);
            m17213P0();
            viewM17222Y0 = m17222Y0(i11 - 1, view.getLeft() - i15);
            int childCount = getChildCount();
            if (childCount > 0) {
                m17217T0(i12, i15, childCount);
            }
        } else {
            viewM17222Y0 = m17222Y0(iMax - i12, view.getLeft() - i15);
            m17213P0();
            viewM17223Z0 = m17223Z0(iMax + i12, view.getRight() + i15);
            int childCount2 = getChildCount();
            if (childCount2 > 0) {
                m17216S0(i12, i15, childCount2);
            }
        }
        return viewM17231h1 != null ? viewM17231h1 : viewM17222Y0 != null ? viewM17222Y0 : viewM17223Z0;
    }

    /* renamed from: c1 */
    public boolean m17226c1(int i9) {
        boolean z8 = true;
        if (i9 == 17) {
            this.f14838D = 2;
            setSelectionInt(0);
            m17141g0();
        } else if (i9 == 66) {
            this.f14838D = 2;
            setSelectionInt(this.f14976r - 1);
            m17141g0();
        } else {
            z8 = false;
        }
        if (z8) {
            awakenScrollBars();
        }
        return z8;
    }

    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView, android.view.View
    public int computeHorizontalScrollExtent() {
        int childCount = getChildCount();
        if (childCount <= 0) {
            return 0;
        }
        int i9 = (((childCount + r2) - 1) / this.f14991f1) * 100;
        View childAt = getChildAt(0);
        int left = childAt.getLeft();
        int width = childAt.getWidth();
        if (width > 0) {
            i9 += (left * 100) / width;
        }
        View childAt2 = getChildAt(childCount - 1);
        int right = childAt2.getRight();
        int width2 = childAt2.getWidth();
        return width2 > 0 ? i9 - (((right - getWidth()) * 100) / width2) : i9;
    }

    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView, android.view.View
    public int computeHorizontalScrollOffset() {
        if (this.f14960b >= 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            int left = childAt.getLeft();
            int width = childAt.getWidth();
            if (width > 0) {
                int i9 = this.f14991f1;
                int i10 = ((this.f14976r + i9) - 1) / i9;
                return Math.max(((((this.f14960b + (m17142i0() ? (i10 * i9) - this.f14976r : 0)) / i9) * 100) - ((left * 100) / width)) + ((int) ((getScrollX() / getWidth()) * i10 * 100.0f)), 0);
            }
        }
        return 0;
    }

    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView, android.view.View
    public int computeHorizontalScrollRange() {
        int i9 = ((this.f14976r + r0) - 1) / this.f14991f1;
        int iMax = Math.max(i9 * 100, 0);
        return getScrollX() != 0 ? iMax + Math.abs((int) ((getScrollX() / getWidth()) * i9 * 100.0f)) : iMax;
    }

    /* renamed from: d1 */
    public final int m17227d1(int i9, int i10, int i11) {
        return i11 > 0 ? i9 + i10 : i9;
    }

    /* renamed from: e1 */
    public final int m17228e1(int i9, int i10, int i11, int i12) {
        return (i12 + i11) + (-1) < this.f14976r + (-1) ? i9 - i10 : i9;
    }

    /* renamed from: f1 */
    public final boolean m17229f1(int i9, int i10) {
        int iMax;
        int iMax2;
        int childCount = getChildCount();
        int i11 = childCount - 1;
        int i12 = i11 - i9;
        if (this.f14896l0) {
            int i13 = this.f14991f1;
            int i14 = i11 - (i12 - (i12 % i13));
            iMax = Math.max(0, (i14 - i13) + 1);
            iMax2 = i14;
        } else {
            int i15 = this.f14991f1;
            iMax = i9 - (i9 % i15);
            iMax2 = Math.max((i15 + iMax) - 1, childCount);
        }
        if (i10 == 1) {
            return i9 == iMax2 && iMax2 == i11;
        }
        if (i10 == 2) {
            return i9 == iMax && iMax == 0;
        }
        if (i10 == 17) {
            return iMax2 == i11;
        }
        if (i10 == 33) {
            return i9 == iMax2;
        }
        if (i10 == 66) {
            return iMax == 0;
        }
        if (i10 == 130) {
            return i9 == iMax;
        }
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT, FOCUS_FORWARD, FOCUS_BACKWARD}.");
    }

    /* renamed from: g1 */
    public final View m17230g1(int i9, int i10, boolean z8, int i11, boolean z9, int i12) {
        View viewM17180f;
        if (!this.f14970l && (viewM17180f = this.f14854L.m17180f(i9)) != null) {
            m17236m1(viewM17180f, i9, i10, z8, i11, z9, true, i12);
            return viewM17180f;
        }
        View viewM17145l0 = m17145l0(i9, this.f14857M0);
        m17236m1(viewM17145l0, i9, i10, z8, i11, z9, this.f14857M0[0], i12);
        return viewM17145l0;
    }

    public int getGravity() {
        return this.f15001p1;
    }

    public int getHorizontalSpacing() {
        return this.f14992g1;
    }

    @ViewDebug.ExportedProperty
    public int getNumRows() {
        return this.f14991f1;
    }

    public int getRequestedRowHeight() {
        return this.f14997l1;
    }

    public int getRequestedVerticalSpacing() {
        return this.f14994i1;
    }

    public int getRowHeight() {
        return this.f14996k1;
    }

    public int getStretchMode() {
        return this.f14995j1;
    }

    public int getVerticalSpacing() {
        return this.f14993h1;
    }

    /* renamed from: h1 */
    public final View m17231h1(int i9, int i10, boolean z8) {
        int iMin;
        int i11;
        int i12 = this.f14996k1;
        int i13 = this.f14993h1;
        boolean z9 = false;
        int i14 = this.f14864Q.top + (this.f14995j1 == 3 ? i13 : 0);
        if (this.f14896l0) {
            int i15 = i9 + 1;
            int iMax = Math.max(0, (i9 - this.f14991f1) + 1);
            int i16 = i15 - iMax;
            int i17 = this.f14991f1;
            if (i16 < i17) {
                i14 += (i17 - i16) * (i12 + i13);
            }
            iMin = i15;
            i11 = iMax;
        } else {
            i11 = i9;
            iMin = Math.min(i9 + this.f14991f1, this.f14976r);
        }
        boolean zM17113C0 = m17113C0();
        boolean zM17118H0 = m17118H0();
        int i18 = this.f14973o;
        View viewM17230g1 = null;
        int i19 = i14;
        View view = null;
        int i20 = i11;
        while (i20 < iMin) {
            boolean z10 = i20 == i18 ? true : z9;
            int i21 = i20;
            int i22 = i18;
            viewM17230g1 = m17230g1(i20, i10, z8, i19, z10, z8 ? -1 : i20 - i11);
            i19 += i12;
            if (i21 < iMin - 1) {
                i19 += i13;
            }
            if (z10 && (zM17113C0 || zM17118H0)) {
                view = viewM17230g1;
            }
            i20 = i21 + 1;
            i18 = i22;
            z9 = false;
        }
        this.f14999n1 = viewM17230g1;
        if (view != null) {
            this.f15000o1 = viewM17230g1;
        }
        return view;
    }

    /* renamed from: i1 */
    public final View m17232i1(int i9, int i10, int i11) {
        int i12;
        int iMax;
        int i13;
        View viewM17231h1;
        View view;
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        int i14 = this.f14973o;
        int i15 = this.f14991f1;
        int i16 = this.f14992g1;
        if (this.f14896l0) {
            int i17 = this.f14976r;
            int i18 = (i17 - 1) - i14;
            i12 = (i17 - 1) - (i18 - (i18 % i15));
            int iMax2 = Math.max(0, (i12 - i15) + 1);
            int i19 = this.f14976r;
            int i20 = (i19 - 1) - (i14 - i9);
            iMax = Math.max(0, (((i19 - 1) - (i20 - (i20 % i15))) - i15) + 1);
            i13 = iMax2;
        } else {
            int i21 = i14 - i9;
            iMax = i21 - (i21 % i15);
            i13 = i14 - (i14 % i15);
            i12 = -1;
        }
        int i22 = i13 - iMax;
        int iM17227d1 = m17227d1(i10, horizontalFadingEdgeLength, i13);
        int iM17228e1 = m17228e1(i11, horizontalFadingEdgeLength, i15, i13);
        this.f14960b = i13;
        if (i22 > 0) {
            View view2 = this.f15000o1;
            viewM17231h1 = m17231h1(this.f14896l0 ? i12 : i13, (view2 != null ? view2.getRight() : 0) + i16, true);
            view = this.f14999n1;
            m17212O0(view, iM17227d1, iM17228e1);
        } else if (i22 < 0) {
            View view3 = this.f15000o1;
            viewM17231h1 = m17231h1(this.f14896l0 ? i12 : i13, (view3 == null ? 0 : view3.getLeft()) - i16, false);
            view = this.f14999n1;
            m17211N0(view, iM17227d1, iM17228e1);
        } else {
            View view4 = this.f15000o1;
            viewM17231h1 = m17231h1(this.f14896l0 ? i12 : i13, view4 != null ? view4.getLeft() : 0, true);
            view = this.f14999n1;
        }
        if (this.f14896l0) {
            m17223Z0(i12 + i15, view.getRight() + i16);
            m17213P0();
            m17222Y0(i13 - 1, view.getLeft() - i16);
        } else {
            m17222Y0(i13 - i15, view.getLeft() - i16);
            m17213P0();
            m17223Z0(i13 + i15, view.getRight() + i16);
        }
        return viewM17231h1;
    }

    /* renamed from: j1 */
    public boolean m17233j1(int i9) {
        int iMax = i9 == 17 ? Math.max(0, this.f14973o - getChildCount()) : i9 == 66 ? Math.min(this.f14976r - 1, this.f14973o + getChildCount()) : -1;
        if (iMax < 0) {
            return false;
        }
        setSelectionInt(iMax);
        m17141g0();
        awakenScrollBars();
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0055  */
    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView
    /* renamed from: k0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void mo17144k0() {
        int i9;
        View childAt;
        View childAt2;
        View childAt3;
        View viewM17219V0;
        boolean z8 = this.f14983y;
        if (!z8) {
            this.f14983y = true;
        }
        try {
            super.mo17144k0();
            invalidate();
            if (this.f14842F == null) {
                m17157w0();
                m17141g0();
                if (z8) {
                    return;
                } else {
                    return;
                }
            }
            int left = this.f14864Q.left;
            int right = (getRight() - getLeft()) - this.f14864Q.right;
            int childCount = getChildCount();
            switch (this.f14838D) {
                case 1:
                case 3:
                case 4:
                case 5:
                    i9 = 0;
                    childAt = null;
                    childAt2 = null;
                    childAt3 = null;
                    break;
                case 2:
                    int i10 = this.f14971m - this.f14960b;
                    if (i10 >= 0 && i10 < childCount) {
                        childAt = getChildAt(i10);
                        i9 = 0;
                        childAt2 = null;
                        childAt3 = null;
                        break;
                    }
                    i9 = 0;
                    childAt = null;
                    childAt2 = null;
                    childAt3 = null;
                case 6:
                    int i11 = this.f14971m;
                    if (i11 >= 0) {
                        i9 = i11 - this.f14973o;
                    }
                    childAt = null;
                    childAt2 = null;
                    childAt3 = null;
                    break;
                default:
                    int i12 = this.f14973o - this.f14960b;
                    childAt2 = (i12 < 0 || i12 >= childCount) ? null : getChildAt(i12);
                    childAt3 = getChildAt(0);
                    childAt = null;
                    i9 = 0;
                    break;
            }
            boolean z9 = this.f14970l;
            if (z9) {
                m17136b0();
            }
            if (this.f14976r == 0) {
                m17157w0();
                m17141g0();
                if (z8) {
                    return;
                }
                this.f14983y = false;
                return;
            }
            setSelectedPositionInt(this.f14971m);
            int i13 = this.f14960b;
            AbsListView.C3221k c3221k = this.f14854L;
            if (z9) {
                for (int i14 = 0; i14 < childCount; i14++) {
                    c3221k.m17176b(getChildAt(i14), i13 + i14);
                }
            } else {
                c3221k.m17179e(childCount, i13);
            }
            detachAllViewsFromParent();
            c3221k.m17185k();
            switch (this.f14838D) {
                case 1:
                    this.f14960b = 0;
                    viewM17219V0 = m17219V0(left);
                    m17213P0();
                    break;
                case 2:
                    if (childAt != null) {
                        viewM17219V0 = m17221X0(childAt.getLeft(), left, right);
                        break;
                    } else {
                        viewM17219V0 = m17224a1(left, right);
                        break;
                    }
                case 3:
                    viewM17219V0 = m17222Y0(this.f14976r - 1, right);
                    m17213P0();
                    break;
                case 4:
                    viewM17219V0 = m17225b1(this.f14973o, this.f14961c);
                    break;
                case 5:
                    viewM17219V0 = m17225b1(this.f14962d, this.f14961c);
                    break;
                case 6:
                    viewM17219V0 = m17232i1(i9, left, right);
                    break;
                default:
                    if (childCount == 0) {
                        if (this.f14896l0) {
                            int i15 = this.f14976r - 1;
                            setSelectedPositionInt((this.f14842F == null || isInTouchMode()) ? -1 : i15);
                            viewM17219V0 = m17220W0(i15, right);
                            break;
                        } else {
                            setSelectedPositionInt((this.f14842F == null || isInTouchMode()) ? -1 : 0);
                            viewM17219V0 = m17219V0(left);
                            break;
                        }
                    } else {
                        int i16 = this.f14973o;
                        if (i16 < 0 || i16 >= this.f14976r) {
                            int i17 = this.f14960b;
                            if (i17 < this.f14976r) {
                                if (childAt3 != null) {
                                    left = childAt3.getLeft();
                                }
                                viewM17219V0 = m17225b1(i17, left);
                                break;
                            } else {
                                viewM17219V0 = m17225b1(0, left);
                                break;
                            }
                        } else {
                            if (childAt2 != null) {
                                left = childAt2.getLeft();
                            }
                            viewM17219V0 = m17225b1(i16, left);
                            break;
                        }
                    }
                    break;
            }
            c3221k.m17186l();
            if (viewM17219V0 != null) {
                m17152r0(-1, viewM17219V0);
                this.f14895k0 = viewM17219V0.getLeft();
            } else {
                int i18 = this.f14889e0;
                if (i18 <= 0 || i18 >= 3) {
                    this.f14895k0 = 0;
                    this.f14852K.setEmpty();
                } else {
                    View childAt4 = getChildAt(this.f14876W - this.f14960b);
                    if (childAt4 != null) {
                        m17152r0(this.f14876W, childAt4);
                    }
                }
            }
            this.f14838D = 0;
            this.f14970l = false;
            Runnable runnable = this.f14849I0;
            if (runnable != null) {
                post(runnable);
                this.f14849I0 = null;
            }
            this.f14965g = false;
            setNextSelectedPositionInt(this.f14973o);
            m17121K0();
            if (this.f14976r > 0) {
                m17198g();
            }
            m17141g0();
            if (z8) {
                return;
            }
            this.f14983y = false;
        } finally {
            if (!z8) {
                this.f14983y = false;
            }
        }
    }

    /* renamed from: k1 */
    public final void m17234k1(int i9) {
        int left;
        if (this.f14960b != 0 || (left = i9 - getChildAt(0).getLeft()) >= 0) {
            return;
        }
        m17146m0(left);
    }

    /* renamed from: l1 */
    public final void m17235l1(int i9) {
        int right;
        int childCount = getChildCount();
        if (this.f14960b + childCount != this.f14976r || (right = i9 - getChildAt(childCount - 1).getRight()) <= 0) {
            return;
        }
        m17146m0(right);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: m1 */
    public final void m17236m1(View view, int i9, int i10, boolean z8, int i11, boolean z9, boolean z10, int i12) {
        SparseBooleanArray sparseBooleanArray;
        boolean z11 = z9 && m17113C0();
        boolean z12 = z11 != view.isSelected();
        int i13 = this.f14889e0;
        boolean z13 = i13 > 0 && i13 < 3 && this.f14876W == i9;
        boolean z14 = z13 != view.isPressed();
        boolean z15 = !z10 || z12 || view.isLayoutRequested();
        AbsListView.C3217g c3217g = (AbsListView.C3217g) view.getLayoutParams();
        if (c3217g == null) {
            c3217g = (AbsListView.C3217g) generateDefaultLayoutParams();
        }
        c3217g.f14934a = this.f14842F.getItemViewType(i9);
        if (!z10 || c3217g.f14935b) {
            c3217g.f14935b = false;
            addViewInLayout(view, i12, c3217g, true);
        } else {
            attachViewToParent(view, i12, c3217g);
        }
        if (z12) {
            view.setSelected(z11);
            if (z11) {
                requestFocus();
            }
        }
        if (z14) {
            view.setPressed(z13);
        }
        if (this.f14910z != 0 && (sparseBooleanArray = this.f14834B) != null && (view instanceof Checkable)) {
            ((Checkable) view).setChecked(sparseBooleanArray.get(i9));
        }
        if (z15) {
            view.measure(ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), 0, ((ViewGroup.LayoutParams) c3217g).width), ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(this.f14996k1, 1073741824), 0, ((ViewGroup.LayoutParams) c3217g).height));
        } else {
            cleanupLayoutState(view);
        }
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i14 = z8 ? i10 : i10 - measuredWidth;
        int absoluteGravity = Gravity.getAbsoluteGravity(this.f15001p1, 0) & 7;
        int i15 = absoluteGravity != 16 ? absoluteGravity != 80 ? i11 : (i11 + this.f14996k1) - measuredHeight : i11 + ((this.f14996k1 - measuredHeight) / 2);
        if (z15) {
            view.layout(i14, i15, measuredWidth + i14, measuredHeight + i15);
        } else {
            view.offsetTopAndBottom(i15 - view.getTop());
            view.offsetLeftAndRight(i14 - view.getLeft());
        }
        if (this.f14872U) {
            view.setDrawingCacheEnabled(true);
        }
        if (!z10 || ((AbsListView.C3217g) view.getLayoutParams()).f14936c == i9) {
            return;
        }
        view.jumpDrawablesToCurrentState();
    }

    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView
    /* renamed from: n */
    public int mo17205n(int i9, boolean z8) {
        if (this.f14842F == null || isInTouchMode() || i9 < 0 || i9 >= this.f14976r) {
            return -1;
        }
        return i9;
    }

    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView, android.view.View
    public void onFocusChanged(boolean z8, int i9, Rect rect) {
        super.onFocusChanged(z8, i9, rect);
        int i10 = -1;
        if (z8 && rect != null) {
            rect.offset(getScrollX(), getScrollY());
            Rect rect2 = this.f15002q1;
            int childCount = getChildCount();
            int i11 = Integer.MAX_VALUE;
            for (int i12 = 0; i12 < childCount; i12++) {
                if (m17229f1(i12, i9)) {
                    View childAt = getChildAt(i12);
                    childAt.getDrawingRect(rect2);
                    offsetDescendantRectToMyCoords(childAt, rect2);
                    int iM17102a0 = AbsListView.m17102a0(rect, rect2, i9);
                    if (iM17102a0 < i11) {
                        i10 = i12;
                        i11 = iM17102a0;
                    }
                }
            }
        }
        if (i10 >= 0) {
            setSelection(i10 + this.f14960b);
        } else {
            requestLayout();
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(HorizontalGridView.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(HorizontalGridView.class.getName());
    }

    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i9, KeyEvent keyEvent) {
        return m17215R0(i9, 1, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyMultiple(int i9, int i10, KeyEvent keyEvent) {
        return m17215R0(i9, i10, keyEvent);
    }

    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i9, KeyEvent keyEvent) {
        return m17215R0(i9, 1, keyEvent);
    }

    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView, android.view.View
    public void onMeasure(int i9, int i10) {
        int measuredWidth;
        int i11;
        int i12;
        super.onMeasure(i9, i10);
        int mode = View.MeasureSpec.getMode(i9);
        int mode2 = View.MeasureSpec.getMode(i10);
        int size = View.MeasureSpec.getSize(i9);
        int size2 = View.MeasureSpec.getSize(i10);
        if (mode2 == 0) {
            int i13 = this.f14996k1;
            if (i13 > 0) {
                Rect rect = this.f14864Q;
                i12 = i13 + rect.top + rect.bottom;
            } else {
                Rect rect2 = this.f14864Q;
                i12 = rect2.bottom + rect2.top;
            }
            size2 = i12 + getHorizontalScrollbarHeight();
        }
        Rect rect3 = this.f14864Q;
        boolean zM17218U0 = m17218U0((size2 - rect3.top) - rect3.bottom);
        ListAdapter listAdapter = this.f14842F;
        int i14 = 0;
        int count = listAdapter == null ? 0 : listAdapter.getCount();
        this.f14976r = count;
        if (count > 0) {
            View viewM17145l0 = m17145l0(0, this.f14857M0);
            AbsListView.C3217g c3217g = (AbsListView.C3217g) viewM17145l0.getLayoutParams();
            if (c3217g == null) {
                c3217g = (AbsListView.C3217g) generateDefaultLayoutParams();
                viewM17145l0.setLayoutParams(c3217g);
            }
            c3217g.f14934a = this.f14842F.getItemViewType(0);
            c3217g.f14935b = true;
            viewM17145l0.measure(ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), 0, ((ViewGroup.LayoutParams) c3217g).width), ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(this.f14996k1, 1073741824), 0, ((ViewGroup.LayoutParams) c3217g).height));
            measuredWidth = viewM17145l0.getMeasuredWidth();
            if (this.f14854L.m17189o(c3217g.f14934a)) {
                this.f14854L.m17176b(viewM17145l0, -1);
            }
        } else {
            measuredWidth = 0;
        }
        if (mode == 0) {
            Rect rect4 = this.f14864Q;
            size = (getHorizontalFadingEdgeLength() * 2) + rect4.left + rect4.right + measuredWidth;
        }
        if (mode == Integer.MIN_VALUE) {
            Rect rect5 = this.f14864Q;
            int i15 = rect5.left + rect5.right;
            int i16 = this.f14991f1;
            while (true) {
                if (i14 >= count) {
                    size = i15;
                    break;
                }
                i15 += measuredWidth;
                i14 += i16;
                if (i14 < count) {
                    i15 += this.f14992g1;
                }
                if (i15 >= size) {
                    break;
                }
            }
        }
        if (mode2 == Integer.MIN_VALUE && (i11 = this.f14998m1) != -1) {
            int i17 = (this.f14996k1 * i11) + ((i11 - 1) * this.f14993h1);
            Rect rect6 = this.f14864Q;
            if (i17 + rect6.top + rect6.bottom > size2 || zM17218U0) {
                size2 |= C3322C.DEFAULT_MUXED_BUFFER_SIZE;
            }
        }
        setMeasuredDimension(size, size2);
        this.f14866R = i10;
    }

    public void setGravity(int i9) {
        if (this.f15001p1 != i9) {
            this.f15001p1 = i9;
            m17156v0();
        }
    }

    public void setHorizontalSpacing(int i9) {
        if (i9 != this.f14992g1) {
            this.f14992g1 = i9;
            m17156v0();
        }
    }

    public void setNumRows(int i9) {
        if (i9 != this.f14998m1) {
            this.f14998m1 = i9;
            m17156v0();
        }
    }

    public void setRowHeight(int i9) {
        if (i9 != this.f14997l1) {
            this.f14997l1 = i9;
            m17156v0();
        }
    }

    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView
    public void setSelection(int i9) {
        if (isInTouchMode()) {
            this.f14901q0 = i9;
        } else {
            setNextSelectedPositionInt(i9);
        }
        this.f14838D = 2;
        AbsListView.RunnableC3220j runnableC3220j = this.f14894j0;
        if (runnableC3220j != null) {
            runnableC3220j.m17174c();
        }
        requestLayout();
    }

    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView
    public void setSelectionInt(int i9) {
        int i10 = this.f14971m;
        AbsListView.RunnableC3220j runnableC3220j = this.f14894j0;
        if (runnableC3220j != null) {
            runnableC3220j.m17174c();
        }
        setNextSelectedPositionInt(i9);
        mo17144k0();
        boolean z8 = this.f14896l0;
        int i11 = z8 ? (this.f14976r - 1) - this.f14971m : this.f14971m;
        if (z8) {
            i10 = (this.f14976r - 1) - i10;
        }
        int i12 = this.f14991f1;
        if (i11 / i12 != i10 / i12) {
            awakenScrollBars();
        }
    }

    public void setStretchMode(int i9) {
        if (i9 != this.f14995j1) {
            this.f14995j1 = i9;
            m17156v0();
        }
    }

    public void setVerticalSpacing(int i9) {
        if (i9 != this.f14994i1) {
            this.f14994i1 = i9;
            m17156v0();
        }
    }

    public HorizontalGridView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f14991f1 = -1;
        this.f14992g1 = 0;
        this.f14993h1 = 0;
        this.f14995j1 = 2;
        this.f14999n1 = null;
        this.f15000o1 = null;
        this.f15001p1 = 8388611;
        this.f15002q1 = new Rect();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C6453a0.HorizontalGridView, i9, 0);
        setHorizontalSpacing(typedArrayObtainStyledAttributes.getDimensionPixelOffset(1, 0));
        setVerticalSpacing(typedArrayObtainStyledAttributes.getDimensionPixelOffset(5, 0));
        int i10 = typedArrayObtainStyledAttributes.getInt(4, 2);
        if (i10 >= 0) {
            setStretchMode(i10);
        }
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(3, -1);
        if (dimensionPixelOffset > 0) {
            setRowHeight(dimensionPixelOffset);
        }
        setNumRows(typedArrayObtainStyledAttributes.getInt(2, 1));
        int i11 = typedArrayObtainStyledAttributes.getInt(0, -1);
        if (i11 >= 0) {
            setGravity(i11);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView
    public ListAdapter getAdapter() {
        return this.f14842F;
    }

    @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView, com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView
    public void setAdapter(ListAdapter listAdapter) {
        AdapterView<ListAdapter>.C3227c c3227c;
        ListAdapter listAdapter2 = this.f14842F;
        if (listAdapter2 != null && (c3227c = this.f14840E) != null) {
            listAdapter2.unregisterDataSetObserver(c3227c);
        }
        m17157w0();
        this.f14854L.m17177c();
        this.f14842F = listAdapter;
        this.f14978t = -1;
        this.f14979u = Long.MIN_VALUE;
        super.setAdapter(listAdapter);
        ListAdapter listAdapter3 = this.f14842F;
        if (listAdapter3 != null) {
            this.f14977s = this.f14976r;
            this.f14976r = listAdapter3.getCount();
            this.f14970l = true;
            m17197f();
            AdapterView<ListAdapter>.C3227c c3227c2 = new AdapterView.C3227c();
            this.f14840E = c3227c2;
            this.f14842F.registerDataSetObserver(c3227c2);
            this.f14854L.m17188n(this.f14842F.getViewTypeCount());
            int iMo17205n = this.f14896l0 ? mo17205n(this.f14976r - 1, false) : mo17205n(0, true);
            setSelectedPositionInt(iMo17205n);
            setNextSelectedPositionInt(iMo17205n);
            m17198g();
        } else {
            m17197f();
            m17198g();
        }
        requestLayout();
    }
}
