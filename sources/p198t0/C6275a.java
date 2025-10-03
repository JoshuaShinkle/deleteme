package p198t0;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;

/* renamed from: t0.a */
/* loaded from: classes.dex */
public class C6275a extends ShapeDrawable {

    /* renamed from: a */
    public final Paint f21156a;

    /* renamed from: b */
    public final Paint f21157b;

    /* renamed from: c */
    public final String f21158c;

    /* renamed from: d */
    public final int f21159d;

    /* renamed from: e */
    public final RectShape f21160e;

    /* renamed from: f */
    public final int f21161f;

    /* renamed from: g */
    public final int f21162g;

    /* renamed from: h */
    public final int f21163h;

    /* renamed from: i */
    public final float f21164i;

    /* renamed from: j */
    public final int f21165j;

    /* renamed from: t0.a$b */
    public static class b implements d, c {

        /* renamed from: a */
        public String f21166a;

        /* renamed from: b */
        public int f21167b;

        /* renamed from: c */
        public int f21168c;

        /* renamed from: d */
        public int f21169d;

        /* renamed from: e */
        public int f21170e;

        /* renamed from: f */
        public Typeface f21171f;

        /* renamed from: g */
        public RectShape f21172g;

        /* renamed from: h */
        public int f21173h;

        /* renamed from: i */
        public int f21174i;

        /* renamed from: j */
        public boolean f21175j;

        /* renamed from: k */
        public boolean f21176k;

        /* renamed from: l */
        public float f21177l;

        @Override // p198t0.C6275a.d
        /* renamed from: a */
        public C6275a mo24045a(String str, int i9, int i10) {
            m24047m(i10);
            return m24046l(str, i9);
        }

        /* renamed from: l */
        public C6275a m24046l(String str, int i9) {
            this.f21167b = i9;
            this.f21166a = str;
            return new C6275a(this);
        }

        /* renamed from: m */
        public c m24047m(int i9) {
            float f9 = i9;
            this.f21177l = f9;
            this.f21172g = new RoundRectShape(new float[]{f9, f9, f9, f9, f9, f9, f9, f9}, null, null);
            return this;
        }

        public b() {
            this.f21166a = "";
            this.f21167b = -7829368;
            this.f21173h = -1;
            this.f21168c = 0;
            this.f21169d = -1;
            this.f21170e = -1;
            this.f21172g = new RectShape();
            this.f21171f = Typeface.create("sans-serif-light", 0);
            this.f21174i = -1;
            this.f21175j = false;
            this.f21176k = false;
        }
    }

    /* renamed from: t0.a$c */
    public interface c {
    }

    /* renamed from: t0.a$d */
    public interface d {
        /* renamed from: a */
        C6275a mo24045a(String str, int i9, int i10);
    }

    /* renamed from: a */
    public static d m24032a() {
        return new b();
    }

    /* renamed from: b */
    public final void m24033b(Canvas canvas) {
        RectF rectF = new RectF(getBounds());
        int i9 = this.f21165j;
        rectF.inset(i9 / 2, i9 / 2);
        RectShape rectShape = this.f21160e;
        if (rectShape instanceof OvalShape) {
            canvas.drawOval(rectF, this.f21157b);
        } else if (!(rectShape instanceof RoundRectShape)) {
            canvas.drawRect(rectF, this.f21157b);
        } else {
            float f9 = this.f21164i;
            canvas.drawRoundRect(rectF, f9, f9, this.f21157b);
        }
    }

    /* renamed from: c */
    public final int m24034c(int i9) {
        return Color.rgb((int) (Color.red(i9) * 0.9f), (int) (Color.green(i9) * 0.9f), (int) (Color.blue(i9) * 0.9f));
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Rect bounds = getBounds();
        if (this.f21165j > 0) {
            m24033b(canvas);
        }
        int iSave = canvas.save();
        canvas.translate(bounds.left, bounds.top);
        int iWidth = this.f21162g;
        if (iWidth < 0) {
            iWidth = bounds.width();
        }
        int iHeight = this.f21161f;
        if (iHeight < 0) {
            iHeight = bounds.height();
        }
        int iMin = this.f21163h;
        if (iMin < 0) {
            iMin = Math.min(iWidth, iHeight) / 2;
        }
        this.f21156a.setTextSize(iMin);
        canvas.drawText(this.f21158c, iWidth / 2, (iHeight / 2) - ((this.f21156a.descent() + this.f21156a.ascent()) / 2.0f), this.f21156a);
        canvas.restoreToCount(iSave);
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f21161f;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f21162g;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int i9) {
        this.f21156a.setAlpha(i9);
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f21156a.setColorFilter(colorFilter);
    }

    public C6275a(b bVar) {
        super(bVar.f21172g);
        this.f21160e = bVar.f21172g;
        this.f21161f = bVar.f21170e;
        this.f21162g = bVar.f21169d;
        this.f21164i = bVar.f21177l;
        this.f21158c = bVar.f21176k ? bVar.f21166a.toUpperCase() : bVar.f21166a;
        int i9 = bVar.f21167b;
        this.f21159d = i9;
        this.f21163h = bVar.f21174i;
        Paint paint = new Paint();
        this.f21156a = paint;
        paint.setColor(bVar.f21173h);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(bVar.f21175j);
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(bVar.f21171f);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStrokeWidth(bVar.f21168c);
        int i10 = bVar.f21168c;
        this.f21165j = i10;
        Paint paint2 = new Paint();
        this.f21157b = paint2;
        paint2.setColor(m24034c(i9));
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(i10);
        getPaint().setColor(i9);
    }
}
