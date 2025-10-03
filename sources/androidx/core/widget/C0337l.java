package androidx.core.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build;
import android.text.Editable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.PasswordTransformationMethod;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import p011b0.C0582m;
import p021c0.C0702h;

/* renamed from: androidx.core.widget.l */
/* loaded from: classes.dex */
public final class C0337l {

    /* renamed from: androidx.core.widget.l$a */
    public static class a implements ActionMode.Callback {

        /* renamed from: a */
        public final ActionMode.Callback f1852a;

        /* renamed from: b */
        public final TextView f1853b;

        /* renamed from: c */
        public Class<?> f1854c;

        /* renamed from: d */
        public Method f1855d;

        /* renamed from: e */
        public boolean f1856e;

        /* renamed from: f */
        public boolean f1857f = false;

        public a(ActionMode.Callback callback, TextView textView) {
            this.f1852a = callback;
            this.f1853b = textView;
        }

        /* renamed from: a */
        public final Intent m1623a() {
            return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
        }

        /* renamed from: b */
        public final Intent m1624b(ResolveInfo resolveInfo, TextView textView) {
            Intent intentPutExtra = m1623a().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !m1626d(textView));
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            return intentPutExtra.setClassName(activityInfo.packageName, activityInfo.name);
        }

        /* renamed from: c */
        public final List<ResolveInfo> m1625c(Context context, PackageManager packageManager) {
            ArrayList arrayList = new ArrayList();
            if (!(context instanceof Activity)) {
                return arrayList;
            }
            for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(m1623a(), 0)) {
                if (m1627e(resolveInfo, context)) {
                    arrayList.add(resolveInfo);
                }
            }
            return arrayList;
        }

        /* renamed from: d */
        public final boolean m1626d(TextView textView) {
            return (textView instanceof Editable) && textView.onCheckIsTextEditor() && textView.isEnabled();
        }

        /* renamed from: e */
        public final boolean m1627e(ResolveInfo resolveInfo, Context context) {
            if (context.getPackageName().equals(resolveInfo.activityInfo.packageName)) {
                return true;
            }
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (!activityInfo.exported) {
                return false;
            }
            String str = activityInfo.permission;
            return str == null || context.checkSelfPermission(str) == 0;
        }

        /* renamed from: f */
        public final void m1628f(Menu menu) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
            Context context = this.f1853b.getContext();
            PackageManager packageManager = context.getPackageManager();
            if (!this.f1857f) {
                this.f1857f = true;
                try {
                    Class<?> cls = Class.forName("com.android.internal.view.menu.MenuBuilder");
                    this.f1854c = cls;
                    this.f1855d = cls.getDeclaredMethod("removeItemAt", Integer.TYPE);
                    this.f1856e = true;
                } catch (ClassNotFoundException | NoSuchMethodException unused) {
                    this.f1854c = null;
                    this.f1855d = null;
                    this.f1856e = false;
                }
            }
            try {
                Method declaredMethod = (this.f1856e && this.f1854c.isInstance(menu)) ? this.f1855d : menu.getClass().getDeclaredMethod("removeItemAt", Integer.TYPE);
                for (int size = menu.size() - 1; size >= 0; size--) {
                    MenuItem item = menu.getItem(size);
                    if (item.getIntent() != null && "android.intent.action.PROCESS_TEXT".equals(item.getIntent().getAction())) {
                        declaredMethod.invoke(menu, Integer.valueOf(size));
                    }
                }
                List<ResolveInfo> listM1625c = m1625c(context, packageManager);
                for (int i9 = 0; i9 < listM1625c.size(); i9++) {
                    ResolveInfo resolveInfo = listM1625c.get(i9);
                    menu.add(0, 0, i9 + 100, resolveInfo.loadLabel(packageManager)).setIntent(m1624b(resolveInfo, this.f1853b)).setShowAsAction(1);
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused2) {
            }
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.f1852a.onActionItemClicked(actionMode, menuItem);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.f1852a.onCreateActionMode(actionMode, menu);
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
            this.f1852a.onDestroyActionMode(actionMode);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
            m1628f(menu);
            return this.f1852a.onPrepareActionMode(actionMode, menu);
        }
    }

    /* renamed from: a */
    public static Drawable[] m1606a(TextView textView) {
        return textView.getCompoundDrawablesRelative();
    }

    /* renamed from: b */
    public static int m1607b(TextView textView) {
        return textView.getPaddingTop() - textView.getPaint().getFontMetricsInt().top;
    }

    /* renamed from: c */
    public static int m1608c(TextView textView) {
        return textView.getPaddingBottom() + textView.getPaint().getFontMetricsInt().bottom;
    }

    /* renamed from: d */
    public static int m1609d(TextView textView) {
        return textView.getMaxLines();
    }

    /* renamed from: e */
    public static int m1610e(TextDirectionHeuristic textDirectionHeuristic) {
        if (textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL || textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
            return 1;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.ANYRTL_LTR) {
            return 2;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.LTR) {
            return 3;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.RTL) {
            return 4;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.LOCALE) {
            return 5;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
            return 6;
        }
        return textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL ? 7 : 1;
    }

    /* renamed from: f */
    public static TextDirectionHeuristic m1611f(TextView textView) {
        if (textView.getTransformationMethod() instanceof PasswordTransformationMethod) {
            return TextDirectionHeuristics.LTR;
        }
        if (Build.VERSION.SDK_INT >= 28 && (textView.getInputType() & 15) == 3) {
            byte directionality = Character.getDirectionality(DecimalFormatSymbols.getInstance(textView.getTextLocale()).getDigitStrings()[0].codePointAt(0));
            return (directionality == 1 || directionality == 2) ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
        }
        boolean z8 = textView.getLayoutDirection() == 1;
        switch (textView.getTextDirection()) {
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                if (!z8) {
                    break;
                } else {
                    break;
                }
        }
        return TextDirectionHeuristics.LTR;
    }

    /* renamed from: g */
    public static C0582m.a m1612g(TextView textView) {
        if (Build.VERSION.SDK_INT >= 28) {
            return new C0582m.a(textView.getTextMetricsParams());
        }
        C0582m.a.C6841a c6841a = new C0582m.a.C6841a(new TextPaint(textView.getPaint()));
        c6841a.m3256b(textView.getBreakStrategy());
        c6841a.m3257c(textView.getHyphenationFrequency());
        c6841a.m3258d(m1611f(textView));
        return c6841a.m3255a();
    }

    /* renamed from: h */
    public static void m1613h(TextView textView, ColorStateList colorStateList) {
        C0702h.m3468b(textView);
        textView.setCompoundDrawableTintList(colorStateList);
    }

    /* renamed from: i */
    public static void m1614i(TextView textView, PorterDuff.Mode mode) {
        C0702h.m3468b(textView);
        textView.setCompoundDrawableTintMode(mode);
    }

    /* renamed from: j */
    public static void m1615j(TextView textView, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        textView.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
    }

    /* renamed from: k */
    public static void m1616k(TextView textView, int i9) {
        C0702h.m3467a(i9);
        if (Build.VERSION.SDK_INT >= 28) {
            textView.setFirstBaselineToTopHeight(i9);
            return;
        }
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i10 = textView.getIncludeFontPadding() ? fontMetricsInt.top : fontMetricsInt.ascent;
        if (i9 > Math.abs(i10)) {
            textView.setPadding(textView.getPaddingLeft(), i9 + i10, textView.getPaddingRight(), textView.getPaddingBottom());
        }
    }

    /* renamed from: l */
    public static void m1617l(TextView textView, int i9) {
        C0702h.m3467a(i9);
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i10 = textView.getIncludeFontPadding() ? fontMetricsInt.bottom : fontMetricsInt.descent;
        if (i9 > Math.abs(i10)) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i9 - i10);
        }
    }

    /* renamed from: m */
    public static void m1618m(TextView textView, int i9) {
        C0702h.m3467a(i9);
        if (i9 != textView.getPaint().getFontMetricsInt(null)) {
            textView.setLineSpacing(i9 - r0, 1.0f);
        }
    }

    /* renamed from: n */
    public static void m1619n(TextView textView, C0582m c0582m) {
        if (Build.VERSION.SDK_INT >= 29) {
            textView.setText(c0582m.m3249b());
        } else {
            if (!m1612g(textView).m3250a(c0582m.m3248a())) {
                throw new IllegalArgumentException("Given text can not be applied to TextView.");
            }
            textView.setText(c0582m);
        }
    }

    /* renamed from: o */
    public static void m1620o(TextView textView, int i9) {
        textView.setTextAppearance(i9);
    }

    /* renamed from: p */
    public static void m1621p(TextView textView, C0582m.a aVar) {
        textView.setTextDirection(m1610e(aVar.m3253d()));
        textView.getPaint().set(aVar.m3254e());
        textView.setBreakStrategy(aVar.m3251b());
        textView.setHyphenationFrequency(aVar.m3252c());
    }

    /* renamed from: q */
    public static ActionMode.Callback m1622q(TextView textView, ActionMode.Callback callback) {
        return (Build.VERSION.SDK_INT > 27 || (callback instanceof a)) ? callback : new a(callback, textView);
    }
}
