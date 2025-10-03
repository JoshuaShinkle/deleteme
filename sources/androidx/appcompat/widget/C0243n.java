package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import p010b.C0569j;
import p042d0.C4647u;

/* renamed from: androidx.appcompat.widget.n */
/* loaded from: classes.dex */
public class C0243n {

    /* renamed from: l */
    public static final RectF f1169l = new RectF();

    /* renamed from: m */
    public static ConcurrentHashMap<String, Method> f1170m = new ConcurrentHashMap<>();

    /* renamed from: n */
    public static ConcurrentHashMap<String, Field> f1171n = new ConcurrentHashMap<>();

    /* renamed from: a */
    public int f1172a = 0;

    /* renamed from: b */
    public boolean f1173b = false;

    /* renamed from: c */
    public float f1174c = -1.0f;

    /* renamed from: d */
    public float f1175d = -1.0f;

    /* renamed from: e */
    public float f1176e = -1.0f;

    /* renamed from: f */
    public int[] f1177f = new int[0];

    /* renamed from: g */
    public boolean f1178g = false;

    /* renamed from: h */
    public TextPaint f1179h;

    /* renamed from: i */
    public final TextView f1180i;

    /* renamed from: j */
    public final Context f1181j;

    /* renamed from: k */
    public final c f1182k;

    /* renamed from: androidx.appcompat.widget.n$a */
    public static class a extends c {
        @Override // androidx.appcompat.widget.C0243n.c
        /* renamed from: a */
        public void mo995a(StaticLayout.Builder builder, TextView textView) {
            builder.setTextDirection((TextDirectionHeuristic) C0243n.m969n(textView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR));
        }
    }

    /* renamed from: androidx.appcompat.widget.n$b */
    public static class b extends a {
        @Override // androidx.appcompat.widget.C0243n.a, androidx.appcompat.widget.C0243n.c
        /* renamed from: a */
        public void mo995a(StaticLayout.Builder builder, TextView textView) {
            builder.setTextDirection(textView.getTextDirectionHeuristic());
        }

        @Override // androidx.appcompat.widget.C0243n.c
        /* renamed from: b */
        public boolean mo996b(TextView textView) {
            return textView.isHorizontallyScrollable();
        }
    }

    /* renamed from: androidx.appcompat.widget.n$c */
    public static class c {
        /* renamed from: a */
        public void mo995a(StaticLayout.Builder builder, TextView textView) {
            throw null;
        }

        /* renamed from: b */
        public boolean mo996b(TextView textView) {
            return ((Boolean) C0243n.m969n(textView, "getHorizontallyScrolling", Boolean.FALSE)).booleanValue();
        }
    }

    public C0243n(TextView textView) {
        this.f1180i = textView;
        this.f1181j = textView.getContext();
        if (Build.VERSION.SDK_INT >= 29) {
            this.f1182k = new b();
        } else {
            this.f1182k = new a();
        }
    }

    /* renamed from: l */
    public static Method m968l(String str) throws SecurityException {
        try {
            Method declaredMethod = f1170m.get(str);
            if (declaredMethod == null && (declaredMethod = TextView.class.getDeclaredMethod(str, new Class[0])) != null) {
                declaredMethod.setAccessible(true);
                f1170m.put(str, declaredMethod);
            }
            return declaredMethod;
        } catch (Exception e9) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + str + "() method", e9);
            return null;
        }
    }

    /* renamed from: n */
    public static <T> T m969n(Object obj, String str, T t8) {
        try {
            return (T) m968l(str).invoke(obj, new Object[0]);
        } catch (Exception e9) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + str + "() method", e9);
            return t8;
        }
    }

    /* renamed from: A */
    public final void m970A(float f9, float f10, float f11) {
        if (f9 <= BitmapDescriptorFactory.HUE_RED) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f9 + "px) is less or equal to (0px)");
        }
        if (f10 <= f9) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f10 + "px) is less or equal to minimum auto-size text size (" + f9 + "px)");
        }
        if (f11 <= BitmapDescriptorFactory.HUE_RED) {
            throw new IllegalArgumentException("The auto-size step granularity (" + f11 + "px) is less or equal to (0px)");
        }
        this.f1172a = 1;
        this.f1175d = f9;
        this.f1176e = f10;
        this.f1174c = f11;
        this.f1178g = false;
    }

    /* renamed from: a */
    public void m971a() {
        if (m983o()) {
            if (this.f1173b) {
                if (this.f1180i.getMeasuredHeight() <= 0 || this.f1180i.getMeasuredWidth() <= 0) {
                    return;
                }
                int measuredWidth = this.f1182k.mo996b(this.f1180i) ? ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : (this.f1180i.getMeasuredWidth() - this.f1180i.getTotalPaddingLeft()) - this.f1180i.getTotalPaddingRight();
                int height = (this.f1180i.getHeight() - this.f1180i.getCompoundPaddingBottom()) - this.f1180i.getCompoundPaddingTop();
                if (measuredWidth <= 0 || height <= 0) {
                    return;
                }
                RectF rectF = f1169l;
                synchronized (rectF) {
                    rectF.setEmpty();
                    rectF.right = measuredWidth;
                    rectF.bottom = height;
                    float fM976f = m976f(rectF);
                    if (fM976f != this.f1180i.getTextSize()) {
                        m989u(0, fM976f);
                    }
                }
            }
            this.f1173b = true;
        }
    }

    /* renamed from: b */
    public final int[] m972b(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i9 : iArr) {
            if (i9 > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i9)) < 0) {
                arrayList.add(Integer.valueOf(i9));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i10 = 0; i10 < size; i10++) {
            iArr2[i10] = ((Integer) arrayList.get(i10)).intValue();
        }
        return iArr2;
    }

    /* renamed from: c */
    public final void m973c() {
        this.f1172a = 0;
        this.f1175d = -1.0f;
        this.f1176e = -1.0f;
        this.f1174c = -1.0f;
        this.f1177f = new int[0];
        this.f1173b = false;
    }

    /* renamed from: d */
    public StaticLayout m974d(CharSequence charSequence, Layout.Alignment alignment, int i9, int i10) {
        return m975e(charSequence, alignment, i9, i10);
    }

    /* renamed from: e */
    public final StaticLayout m975e(CharSequence charSequence, Layout.Alignment alignment, int i9, int i10) {
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), this.f1179h, i9);
        StaticLayout.Builder hyphenationFrequency = builderObtain.setAlignment(alignment).setLineSpacing(this.f1180i.getLineSpacingExtra(), this.f1180i.getLineSpacingMultiplier()).setIncludePad(this.f1180i.getIncludeFontPadding()).setBreakStrategy(this.f1180i.getBreakStrategy()).setHyphenationFrequency(this.f1180i.getHyphenationFrequency());
        if (i10 == -1) {
            i10 = Integer.MAX_VALUE;
        }
        hyphenationFrequency.setMaxLines(i10);
        try {
            this.f1182k.mo995a(builderObtain, this.f1180i);
        } catch (ClassCastException unused) {
            Log.w("ACTVAutoSizeHelper", "Failed to obtain TextDirectionHeuristic, auto size may be incorrect");
        }
        return builderObtain.build();
    }

    /* renamed from: f */
    public final int m976f(RectF rectF) {
        int length = this.f1177f.length;
        if (length == 0) {
            throw new IllegalStateException("No available text sizes to choose from.");
        }
        int i9 = 1;
        int i10 = length - 1;
        int i11 = 0;
        while (i9 <= i10) {
            int i12 = (i9 + i10) / 2;
            if (m993y(this.f1177f[i12], rectF)) {
                int i13 = i12 + 1;
                i11 = i9;
                i9 = i13;
            } else {
                i11 = i12 - 1;
                i10 = i11;
            }
        }
        return this.f1177f[i11];
    }

    /* renamed from: g */
    public int m977g() {
        return Math.round(this.f1176e);
    }

    /* renamed from: h */
    public int m978h() {
        return Math.round(this.f1175d);
    }

    /* renamed from: i */
    public int m979i() {
        return Math.round(this.f1174c);
    }

    /* renamed from: j */
    public int[] m980j() {
        return this.f1177f;
    }

    /* renamed from: k */
    public int m981k() {
        return this.f1172a;
    }

    /* renamed from: m */
    public void m982m(int i9) {
        TextPaint textPaint = this.f1179h;
        if (textPaint == null) {
            this.f1179h = new TextPaint();
        } else {
            textPaint.reset();
        }
        this.f1179h.set(this.f1180i.getPaint());
        this.f1179h.setTextSize(i9);
    }

    /* renamed from: o */
    public boolean m983o() {
        return m994z() && this.f1172a != 0;
    }

    /* renamed from: p */
    public void m984p(AttributeSet attributeSet, int i9) {
        int resourceId;
        Context context = this.f1181j;
        int[] iArr = C0569j.AppCompatTextView;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i9, 0);
        TextView textView = this.f1180i;
        C4647u.m18528X(textView, textView.getContext(), iArr, attributeSet, typedArrayObtainStyledAttributes, i9, 0);
        int i10 = C0569j.AppCompatTextView_autoSizeTextType;
        if (typedArrayObtainStyledAttributes.hasValue(i10)) {
            this.f1172a = typedArrayObtainStyledAttributes.getInt(i10, 0);
        }
        int i11 = C0569j.AppCompatTextView_autoSizeStepGranularity;
        float dimension = typedArrayObtainStyledAttributes.hasValue(i11) ? typedArrayObtainStyledAttributes.getDimension(i11, -1.0f) : -1.0f;
        int i12 = C0569j.AppCompatTextView_autoSizeMinTextSize;
        float dimension2 = typedArrayObtainStyledAttributes.hasValue(i12) ? typedArrayObtainStyledAttributes.getDimension(i12, -1.0f) : -1.0f;
        int i13 = C0569j.AppCompatTextView_autoSizeMaxTextSize;
        float dimension3 = typedArrayObtainStyledAttributes.hasValue(i13) ? typedArrayObtainStyledAttributes.getDimension(i13, -1.0f) : -1.0f;
        int i14 = C0569j.AppCompatTextView_autoSizePresetSizes;
        if (typedArrayObtainStyledAttributes.hasValue(i14) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(i14, 0)) > 0) {
            TypedArray typedArrayObtainTypedArray = typedArrayObtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            m991w(typedArrayObtainTypedArray);
            typedArrayObtainTypedArray.recycle();
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!m994z()) {
            this.f1172a = 0;
            return;
        }
        if (this.f1172a == 1) {
            if (!this.f1178g) {
                DisplayMetrics displayMetrics = this.f1181j.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                m970A(dimension2, dimension3, dimension);
            }
            m990v();
        }
    }

    /* renamed from: q */
    public void m985q(int i9, int i10, int i11, int i12) {
        if (m994z()) {
            DisplayMetrics displayMetrics = this.f1181j.getResources().getDisplayMetrics();
            m970A(TypedValue.applyDimension(i12, i9, displayMetrics), TypedValue.applyDimension(i12, i10, displayMetrics), TypedValue.applyDimension(i12, i11, displayMetrics));
            if (m990v()) {
                m971a();
            }
        }
    }

    /* renamed from: r */
    public void m986r(int[] iArr, int i9) {
        if (m994z()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArrCopyOf = new int[length];
                if (i9 == 0) {
                    iArrCopyOf = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = this.f1181j.getResources().getDisplayMetrics();
                    for (int i10 = 0; i10 < length; i10++) {
                        iArrCopyOf[i10] = Math.round(TypedValue.applyDimension(i9, iArr[i10], displayMetrics));
                    }
                }
                this.f1177f = m972b(iArrCopyOf);
                if (!m992x()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                this.f1178g = false;
            }
            if (m990v()) {
                m971a();
            }
        }
    }

    /* renamed from: s */
    public void m987s(int i9) {
        if (m994z()) {
            if (i9 == 0) {
                m973c();
                return;
            }
            if (i9 != 1) {
                throw new IllegalArgumentException("Unknown auto-size text type: " + i9);
            }
            DisplayMetrics displayMetrics = this.f1181j.getResources().getDisplayMetrics();
            m970A(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (m990v()) {
                m971a();
            }
        }
    }

    /* renamed from: t */
    public final void m988t(float f9) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (f9 != this.f1180i.getPaint().getTextSize()) {
            this.f1180i.getPaint().setTextSize(f9);
            boolean zIsInLayout = this.f1180i.isInLayout();
            if (this.f1180i.getLayout() != null) {
                this.f1173b = false;
                try {
                    Method methodM968l = m968l("nullLayouts");
                    if (methodM968l != null) {
                        methodM968l.invoke(this.f1180i, new Object[0]);
                    }
                } catch (Exception e9) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e9);
                }
                if (zIsInLayout) {
                    this.f1180i.forceLayout();
                } else {
                    this.f1180i.requestLayout();
                }
                this.f1180i.invalidate();
            }
        }
    }

    /* renamed from: u */
    public void m989u(int i9, float f9) {
        Context context = this.f1181j;
        m988t(TypedValue.applyDimension(i9, f9, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics()));
    }

    /* renamed from: v */
    public final boolean m990v() {
        if (m994z() && this.f1172a == 1) {
            if (!this.f1178g || this.f1177f.length == 0) {
                int iFloor = ((int) Math.floor((this.f1176e - this.f1175d) / this.f1174c)) + 1;
                int[] iArr = new int[iFloor];
                for (int i9 = 0; i9 < iFloor; i9++) {
                    iArr[i9] = Math.round(this.f1175d + (i9 * this.f1174c));
                }
                this.f1177f = m972b(iArr);
            }
            this.f1173b = true;
        } else {
            this.f1173b = false;
        }
        return this.f1173b;
    }

    /* renamed from: w */
    public final void m991w(TypedArray typedArray) {
        int length = typedArray.length();
        int[] iArr = new int[length];
        if (length > 0) {
            for (int i9 = 0; i9 < length; i9++) {
                iArr[i9] = typedArray.getDimensionPixelSize(i9, -1);
            }
            this.f1177f = m972b(iArr);
            m992x();
        }
    }

    /* renamed from: x */
    public final boolean m992x() {
        boolean z8 = this.f1177f.length > 0;
        this.f1178g = z8;
        if (z8) {
            this.f1172a = 1;
            this.f1175d = r0[0];
            this.f1176e = r0[r1 - 1];
            this.f1174c = -1.0f;
        }
        return z8;
    }

    /* renamed from: y */
    public final boolean m993y(int i9, RectF rectF) {
        CharSequence transformation;
        CharSequence text = this.f1180i.getText();
        TransformationMethod transformationMethod = this.f1180i.getTransformationMethod();
        if (transformationMethod != null && (transformation = transformationMethod.getTransformation(text, this.f1180i)) != null) {
            text = transformation;
        }
        int maxLines = this.f1180i.getMaxLines();
        m982m(i9);
        StaticLayout staticLayoutM974d = m974d(text, (Layout.Alignment) m969n(this.f1180i, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL), Math.round(rectF.right), maxLines);
        return (maxLines == -1 || (staticLayoutM974d.getLineCount() <= maxLines && staticLayoutM974d.getLineEnd(staticLayoutM974d.getLineCount() - 1) == text.length())) && ((float) staticLayoutM974d.getHeight()) <= rectF.bottom;
    }

    /* renamed from: z */
    public final boolean m994z() {
        return !(this.f1180i instanceof AppCompatEditText);
    }
}
