package p011b0;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import p021c0.C0697c;

/* renamed from: b0.m */
/* loaded from: classes.dex */
public class C0582m implements Spannable {

    /* renamed from: e */
    public static final Object f3076e = new Object();

    /* renamed from: b */
    public final Spannable f3077b;

    /* renamed from: c */
    public final a f3078c;

    /* renamed from: d */
    public final PrecomputedText f3079d;

    /* renamed from: a */
    public a m3248a() {
        return this.f3078c;
    }

    /* renamed from: b */
    public PrecomputedText m3249b() {
        Spannable spannable = this.f3077b;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i9) {
        return this.f3077b.charAt(i9);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f3077b.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f3077b.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f3077b.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i9, int i10, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.f3079d.getSpans(i9, i10, cls) : (T[]) this.f3077b.getSpans(i9, i10, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f3077b.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i9, int i10, Class cls) {
        return this.f3077b.nextSpanTransition(i9, i10, cls);
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f3079d.removeSpan(obj);
        } else {
            this.f3077b.removeSpan(obj);
        }
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void setSpan(Object obj, int i9, int i10, int i11) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f3079d.setSpan(obj, i9, i10, i11);
        } else {
            this.f3077b.setSpan(obj, i9, i10, i11);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i9, int i10) {
        return this.f3077b.subSequence(i9, i10);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f3077b.toString();
    }

    /* renamed from: b0.m$a */
    public static final class a {

        /* renamed from: a */
        public final TextPaint f3080a;

        /* renamed from: b */
        public final TextDirectionHeuristic f3081b;

        /* renamed from: c */
        public final int f3082c;

        /* renamed from: d */
        public final int f3083d;

        /* renamed from: e */
        public final PrecomputedText.Params f3084e;

        /* renamed from: b0.m$a$a, reason: collision with other inner class name */
        public static class C6841a {

            /* renamed from: a */
            public final TextPaint f3085a;

            /* renamed from: c */
            public int f3087c = 1;

            /* renamed from: d */
            public int f3088d = 1;

            /* renamed from: b */
            public TextDirectionHeuristic f3086b = TextDirectionHeuristics.FIRSTSTRONG_LTR;

            public C6841a(TextPaint textPaint) {
                this.f3085a = textPaint;
            }

            /* renamed from: a */
            public a m3255a() {
                return new a(this.f3085a, this.f3086b, this.f3087c, this.f3088d);
            }

            /* renamed from: b */
            public C6841a m3256b(int i9) {
                this.f3087c = i9;
                return this;
            }

            /* renamed from: c */
            public C6841a m3257c(int i9) {
                this.f3088d = i9;
                return this;
            }

            /* renamed from: d */
            public C6841a m3258d(TextDirectionHeuristic textDirectionHeuristic) {
                this.f3086b = textDirectionHeuristic;
                return this;
            }
        }

        @SuppressLint({"NewApi"})
        public a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i9, int i10) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.f3084e = new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i9).setHyphenationFrequency(i10).setTextDirection(textDirectionHeuristic).build();
            } else {
                this.f3084e = null;
            }
            this.f3080a = textPaint;
            this.f3081b = textDirectionHeuristic;
            this.f3082c = i9;
            this.f3083d = i10;
        }

        /* renamed from: a */
        public boolean m3250a(a aVar) {
            if (this.f3082c == aVar.m3251b() && this.f3083d == aVar.m3252c() && this.f3080a.getTextSize() == aVar.m3254e().getTextSize() && this.f3080a.getTextScaleX() == aVar.m3254e().getTextScaleX() && this.f3080a.getTextSkewX() == aVar.m3254e().getTextSkewX() && this.f3080a.getLetterSpacing() == aVar.m3254e().getLetterSpacing() && TextUtils.equals(this.f3080a.getFontFeatureSettings(), aVar.m3254e().getFontFeatureSettings()) && this.f3080a.getFlags() == aVar.m3254e().getFlags() && this.f3080a.getTextLocales().equals(aVar.m3254e().getTextLocales())) {
                return this.f3080a.getTypeface() == null ? aVar.m3254e().getTypeface() == null : this.f3080a.getTypeface().equals(aVar.m3254e().getTypeface());
            }
            return false;
        }

        /* renamed from: b */
        public int m3251b() {
            return this.f3082c;
        }

        /* renamed from: c */
        public int m3252c() {
            return this.f3083d;
        }

        /* renamed from: d */
        public TextDirectionHeuristic m3253d() {
            return this.f3081b;
        }

        /* renamed from: e */
        public TextPaint m3254e() {
            return this.f3080a;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return m3250a(aVar) && this.f3081b == aVar.m3253d();
        }

        public int hashCode() {
            return C0697c.m3462b(Float.valueOf(this.f3080a.getTextSize()), Float.valueOf(this.f3080a.getTextScaleX()), Float.valueOf(this.f3080a.getTextSkewX()), Float.valueOf(this.f3080a.getLetterSpacing()), Integer.valueOf(this.f3080a.getFlags()), this.f3080a.getTextLocales(), this.f3080a.getTypeface(), Boolean.valueOf(this.f3080a.isElegantTextHeight()), this.f3081b, Integer.valueOf(this.f3082c), Integer.valueOf(this.f3083d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f3080a.getTextSize());
            sb.append(", textScaleX=" + this.f3080a.getTextScaleX());
            sb.append(", textSkewX=" + this.f3080a.getTextSkewX());
            sb.append(", letterSpacing=" + this.f3080a.getLetterSpacing());
            sb.append(", elegantTextHeight=" + this.f3080a.isElegantTextHeight());
            sb.append(", textLocale=" + this.f3080a.getTextLocales());
            sb.append(", typeface=" + this.f3080a.getTypeface());
            sb.append(", variationSettings=" + this.f3080a.getFontVariationSettings());
            sb.append(", textDir=" + this.f3081b);
            sb.append(", breakStrategy=" + this.f3082c);
            sb.append(", hyphenationFrequency=" + this.f3083d);
            sb.append("}");
            return sb.toString();
        }

        public a(PrecomputedText.Params params) {
            this.f3080a = params.getTextPaint();
            this.f3081b = params.getTextDirection();
            this.f3082c = params.getBreakStrategy();
            this.f3083d = params.getHyphenationFrequency();
            this.f3084e = Build.VERSION.SDK_INT < 29 ? null : params;
        }
    }
}
