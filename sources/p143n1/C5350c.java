package p143n1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import java.nio.ByteBuffer;
import p143n1.C5354g;
import p207u0.ComponentCallbacks2C6355e;
import p225w0.InterfaceC6503a;
import p226w1.C6516i;
import p243y0.InterfaceC6595h;

/* renamed from: n1.c */
/* loaded from: classes.dex */
public class C5350c extends Drawable implements C5354g.b, Animatable {

    /* renamed from: b */
    public final a f18207b;

    /* renamed from: c */
    public boolean f18208c;

    /* renamed from: d */
    public boolean f18209d;

    /* renamed from: e */
    public boolean f18210e;

    /* renamed from: f */
    public boolean f18211f;

    /* renamed from: g */
    public int f18212g;

    /* renamed from: h */
    public int f18213h;

    /* renamed from: i */
    public boolean f18214i;

    /* renamed from: j */
    public Paint f18215j;

    /* renamed from: k */
    public Rect f18216k;

    /* renamed from: n1.c$a */
    public static final class a extends Drawable.ConstantState {

        /* renamed from: a */
        public final C5354g f18217a;

        public a(C5354g c5354g) {
            this.f18217a = c5354g;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return newDrawable();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new C5350c(this);
        }
    }

    public C5350c(Context context, InterfaceC6503a interfaceC6503a, InterfaceC6595h<Bitmap> interfaceC6595h, int i9, int i10, Bitmap bitmap) {
        this(new a(new C5354g(ComponentCallbacks2C6355e.m24381d(context), interfaceC6503a, i9, i10, interfaceC6595h, bitmap)));
    }

    @Override // p143n1.C5354g.b
    /* renamed from: a */
    public void mo21009a() {
        if (m21010b() == null) {
            stop();
            invalidateSelf();
            return;
        }
        invalidateSelf();
        if (m21015g() == m21014f() - 1) {
            this.f18212g++;
        }
        int i9 = this.f18213h;
        if (i9 == -1 || this.f18212g < i9) {
            return;
        }
        stop();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    public final Drawable.Callback m21010b() {
        Drawable.Callback callback = getCallback();
        while (callback instanceof Drawable) {
            callback = ((Drawable) callback).getCallback();
        }
        return callback;
    }

    /* renamed from: c */
    public ByteBuffer m21011c() {
        return this.f18207b.f18217a.m21027b();
    }

    /* renamed from: d */
    public final Rect m21012d() {
        if (this.f18216k == null) {
            this.f18216k = new Rect();
        }
        return this.f18216k;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.f18210e) {
            return;
        }
        if (this.f18214i) {
            Gravity.apply(119, getIntrinsicWidth(), getIntrinsicHeight(), getBounds(), m21012d());
            this.f18214i = false;
        }
        canvas.drawBitmap(this.f18207b.f18217a.m21028c(), (Rect) null, m21012d(), m21016h());
    }

    /* renamed from: e */
    public Bitmap m21013e() {
        return this.f18207b.f18217a.m21030e();
    }

    /* renamed from: f */
    public int m21014f() {
        return this.f18207b.f18217a.m21031f();
    }

    /* renamed from: g */
    public int m21015g() {
        return this.f18207b.f18217a.m21029d();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f18207b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f18207b.f18217a.m21033i();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f18207b.f18217a.m21035l();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    /* renamed from: h */
    public final Paint m21016h() {
        if (this.f18215j == null) {
            this.f18215j = new Paint(2);
        }
        return this.f18215j;
    }

    /* renamed from: i */
    public int m21017i() {
        return this.f18207b.f18217a.m21034k();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.f18208c;
    }

    /* renamed from: j */
    public void m21018j() {
        this.f18210e = true;
        this.f18207b.f18217a.m21026a();
    }

    /* renamed from: k */
    public final void m21019k() {
        this.f18212g = 0;
    }

    /* renamed from: l */
    public void m21020l(InterfaceC6595h<Bitmap> interfaceC6595h, Bitmap bitmap) {
        this.f18207b.f18217a.m21039p(interfaceC6595h, bitmap);
    }

    /* renamed from: m */
    public final void m21021m() {
        C6516i.m24935a(!this.f18210e, "You cannot start a recycled Drawable. Ensure thatyou clear any references to the Drawable when clearing the corresponding request.");
        if (this.f18207b.f18217a.m21031f() == 1) {
            invalidateSelf();
        } else {
            if (this.f18208c) {
                return;
            }
            this.f18208c = true;
            this.f18207b.f18217a.m21042s(this);
            invalidateSelf();
        }
    }

    /* renamed from: n */
    public final void m21022n() {
        this.f18208c = false;
        this.f18207b.f18217a.m21043t(this);
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f18214i = true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i9) {
        m21016h().setAlpha(i9);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        m21016h().setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z8, boolean z9) {
        C6516i.m24935a(!this.f18210e, "Cannot change the visibility of a recycled resource. Ensure that you unset the Drawable from your View before changing the View's visibility.");
        this.f18211f = z8;
        if (!z8) {
            m21022n();
        } else if (this.f18209d) {
            m21021m();
        }
        return super.setVisible(z8, z9);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.f18209d = true;
        m21019k();
        if (this.f18211f) {
            m21021m();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.f18209d = false;
        m21022n();
    }

    public C5350c(a aVar) {
        this.f18211f = true;
        this.f18213h = -1;
        this.f18207b = (a) C6516i.m24938d(aVar);
    }
}
