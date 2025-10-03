package p245y2;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ListView;
import com.cyberlink.you.Globals;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.iid.ServiceStarter;
import java.lang.ref.WeakReference;

/* renamed from: y2.x5 */
/* loaded from: classes.dex */
public class C6773x5 implements AbsListView.OnScrollListener {

    /* renamed from: a */
    public WeakReference<ListView> f22490a;

    /* renamed from: b */
    public WeakReference<View> f22491b;

    /* renamed from: c */
    public WeakReference<View> f22492c;

    /* renamed from: i */
    public View f22498i;

    /* renamed from: l */
    public ValueAnimator f22501l;

    /* renamed from: m */
    public ValueAnimator f22502m;

    /* renamed from: d */
    public int f22493d = -1;

    /* renamed from: e */
    public int f22494e = -1;

    /* renamed from: f */
    public int f22495f = -1;

    /* renamed from: g */
    public int f22496g = -1;

    /* renamed from: h */
    public Adapter f22497h = null;

    /* renamed from: j */
    public boolean f22499j = false;

    /* renamed from: k */
    public boolean f22500k = false;

    /* renamed from: y2.x5$a */
    public class a implements Animator.AnimatorListener {
        public a() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            C6773x5.this.f22499j = false;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            C6773x5.this.f22499j = false;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            C6773x5.this.f22499j = true;
        }
    }

    /* renamed from: y2.x5$b */
    public class b implements Animator.AnimatorListener {
        public b() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            C6773x5.this.f22500k = false;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            C6773x5.this.f22500k = false;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            C6773x5.this.f22500k = true;
        }
    }

    public C6773x5(ListView listView, View view, View view2) {
        this.f22490a = new WeakReference<>(listView);
        this.f22491b = new WeakReference<>(view);
        this.f22492c = new WeakReference<>(view2);
    }

    /* renamed from: a */
    public final boolean m25317a() {
        return this.f22490a.get().getFirstVisiblePosition() == 0;
    }

    /* renamed from: b */
    public final void m25318b() {
        View view;
        WeakReference<View> weakReference = this.f22491b;
        if (weakReference == null || (view = weakReference.get()) == null || this.f22499j) {
            return;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "translationY", -view.getHeight());
        this.f22501l = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        this.f22501l.setDuration(300L);
        this.f22501l.addListener(new a());
        this.f22501l.start();
        View view2 = this.f22492c.get();
        if (view2 != null) {
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view2, "translationY", -view.getHeight());
            this.f22502m = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
            this.f22502m.setDuration(300L);
            this.f22502m.start();
        }
    }

    /* renamed from: c */
    public final int m25319c() {
        ListView listView;
        int top;
        int i9;
        WeakReference<ListView> weakReference = this.f22490a;
        int height = ServiceStarter.ERROR_UNKNOWN;
        if (weakReference == null || (listView = weakReference.get()) == null || listView.getChildCount() <= 1) {
            return ServiceStarter.ERROR_UNKNOWN;
        }
        Adapter adapter = this.f22497h;
        if (adapter != null && adapter != listView.getAdapter()) {
            this.f22493d = -1;
        }
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int lastVisiblePosition = listView.getLastVisiblePosition();
        int i10 = 0;
        View childAt = listView.getChildAt(0);
        View childAt2 = listView.getChildAt(listView.getChildCount() - 1);
        int i11 = this.f22493d;
        if (i11 == -1) {
            Log.v("OnQRScrollListener", "init first_pos=" + firstVisiblePosition + " last_pos=" + lastVisiblePosition);
            this.f22493d = firstVisiblePosition;
            this.f22495f = lastVisiblePosition;
            this.f22494e = childAt.getTop();
            this.f22496g = childAt2.getTop();
            this.f22497h = listView.getAdapter();
            return ServiceStarter.ERROR_UNKNOWN;
        }
        if (i11 >= lastVisiblePosition || i11 <= firstVisiblePosition) {
            int i12 = this.f22495f;
            if (i12 >= lastVisiblePosition || i12 <= firstVisiblePosition || firstVisiblePosition == 0) {
                if (lastVisiblePosition <= i11) {
                    height = listView.getHeight() != 0 ? -listView.getHeight() : -500;
                } else if (firstVisiblePosition >= i12) {
                    if (listView.getHeight() != 0) {
                        height = listView.getHeight();
                    }
                } else {
                    if (i11 != firstVisiblePosition) {
                        if (i12 == lastVisiblePosition) {
                            top = childAt2.getTop();
                            i9 = this.f22496g;
                        }
                        this.f22493d = firstVisiblePosition;
                        this.f22495f = lastVisiblePosition;
                        this.f22494e = childAt.getTop();
                        this.f22496g = childAt2.getTop();
                        this.f22497h = listView.getAdapter();
                        return i10;
                    }
                    top = childAt.getTop();
                    i9 = this.f22494e;
                }
                i10 = height;
                this.f22493d = firstVisiblePosition;
                this.f22495f = lastVisiblePosition;
                this.f22494e = childAt.getTop();
                this.f22496g = childAt2.getTop();
                this.f22497h = listView.getAdapter();
                return i10;
            }
            top = listView.getChildAt(i12 - firstVisiblePosition).getTop();
            i9 = this.f22496g;
        } else {
            top = listView.getChildAt(i11 - firstVisiblePosition).getTop();
            i9 = this.f22494e;
        }
        i10 = top - i9;
        this.f22493d = firstVisiblePosition;
        this.f22495f = lastVisiblePosition;
        this.f22494e = childAt.getTop();
        this.f22496g = childAt2.getTop();
        this.f22497h = listView.getAdapter();
        return i10;
    }

    /* renamed from: d */
    public float m25320d() {
        View view = this.f22491b.get();
        return (view == null || view.getVisibility() != 0) ? BitmapDescriptorFactory.HUE_RED : view.getTranslationY() + view.getHeight();
    }

    /* renamed from: e */
    public void m25321e() {
        if (m25317a()) {
            return;
        }
        m25318b();
    }

    /* renamed from: f */
    public final void m25322f(Context context) {
        if (this.f22498i == null) {
            int iRound = Math.round((Globals.m7388i0().getResources().getDisplayMetrics().widthPixels / 1080.0f) * 180.0f);
            this.f22498i = new View(context);
            this.f22498i.setLayoutParams(new AbsListView.LayoutParams(-1, iRound));
        }
    }

    /* renamed from: g */
    public void m25323g(Context context) {
        ListView listView = this.f22490a.get();
        m25322f(context);
        listView.addHeaderView(this.f22498i);
    }

    /* renamed from: h */
    public void m25324h() {
        View view;
        WeakReference<View> weakReference = this.f22491b;
        if (weakReference == null || (view = weakReference.get()) == null || this.f22500k) {
            return;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "translationY", BitmapDescriptorFactory.HUE_RED);
        this.f22501l = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        this.f22501l.setDuration(300L);
        this.f22501l.addListener(new b());
        this.f22501l.start();
        View view2 = this.f22492c.get();
        if (view2 != null) {
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view2, "translationY", BitmapDescriptorFactory.HUE_RED);
            this.f22502m = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
            this.f22502m.setDuration(300L);
            this.f22502m.start();
        }
    }

    /* renamed from: i */
    public void m25325i() {
        m25326j(0);
    }

    /* renamed from: j */
    public void m25326j(int i9) {
        View view;
        WeakReference<View> weakReference = this.f22491b;
        if (weakReference == null || (view = weakReference.get()) == null) {
            return;
        }
        float f9 = i9;
        if (view.getTranslationY() == f9) {
            return;
        }
        view.setTranslationY(f9);
        View view2 = this.f22492c.get();
        if (view2 != null) {
            view2.setTranslationY(f9);
        }
        this.f22490a.get().setTranscriptMode(0);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i9, int i10, int i11) {
        WeakReference<ListView> weakReference = this.f22490a;
        if (weakReference == null || this.f22491b == null) {
            return;
        }
        ListView listView = weakReference.get();
        View view = this.f22491b.get();
        if (listView == null || listView.getAdapter() == null || view == null) {
            return;
        }
        int iM25319c = m25319c();
        if (iM25319c > 0) {
            m25324h();
        } else if (iM25319c < 0) {
            m25321e();
        }
        Log.v("OnQRScrollListener", "rawY=" + iM25319c);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i9) {
    }
}
