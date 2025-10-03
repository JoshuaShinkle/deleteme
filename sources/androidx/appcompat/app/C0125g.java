package androidx.appcompat.app;

import android.R;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.appcompat.widget.C0244n0;
import com.google.android.gms.analytics.ecommerce.Promotion;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.commons.lang3.CharUtils;
import p010b.C0569j;
import p042d0.C4647u;
import p071g.C4798d;
import p132m.C5308g;

/* renamed from: androidx.appcompat.app.g */
/* loaded from: classes.dex */
public class C0125g {
    private static final String LOG_TAG = "AppCompatViewInflater";
    private final Object[] mConstructorArgs = new Object[2];
    private static final Class<?>[] sConstructorSignature = {Context.class, AttributeSet.class};
    private static final int[] sOnClickAttrs = {R.attr.onClick};
    private static final String[] sClassPrefixList = {"android.widget.", "android.view.", "android.webkit."};
    private static final C5308g<String, Constructor<? extends View>> sConstructorMap = new C5308g<>();

    /* renamed from: androidx.appcompat.app.g$a */
    public static class a implements View.OnClickListener {

        /* renamed from: b */
        public final View f384b;

        /* renamed from: c */
        public final String f385c;

        /* renamed from: d */
        public Method f386d;

        /* renamed from: e */
        public Context f387e;

        public a(View view, String str) {
            this.f384b = view;
            this.f385c = str;
        }

        /* renamed from: a */
        public final void m405a(Context context) {
            String str;
            Method method;
            while (context != null) {
                try {
                    if (!context.isRestricted() && (method = context.getClass().getMethod(this.f385c, View.class)) != null) {
                        this.f386d = method;
                        this.f387e = context;
                        return;
                    }
                } catch (NoSuchMethodException unused) {
                }
                context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
            }
            int id = this.f384b.getId();
            if (id == -1) {
                str = "";
            } else {
                str = " with id '" + this.f384b.getContext().getResources().getResourceEntryName(id) + "'";
            }
            throw new IllegalStateException("Could not find method " + this.f385c + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.f384b.getClass() + str);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            if (this.f386d == null) {
                m405a(this.f384b.getContext());
            }
            try {
                this.f386d.invoke(this.f387e, view);
            } catch (IllegalAccessException e9) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e9);
            } catch (InvocationTargetException e10) {
                throw new IllegalStateException("Could not execute method for android:onClick", e10);
            }
        }
    }

    private void checkOnClickListener(View view, AttributeSet attributeSet) {
        Context context = view.getContext();
        if ((context instanceof ContextWrapper) && C4647u.m18508D(view)) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, sOnClickAttrs);
            String string = typedArrayObtainStyledAttributes.getString(0);
            if (string != null) {
                view.setOnClickListener(new a(view, string));
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    private View createViewByPrefix(Context context, String str, String str2) throws NoSuchMethodException, SecurityException {
        String str3;
        C5308g<String, Constructor<? extends View>> c5308g = sConstructorMap;
        Constructor<? extends View> constructor = c5308g.get(str);
        if (constructor == null) {
            if (str2 != null) {
                try {
                    str3 = str2 + str;
                } catch (Exception unused) {
                    return null;
                }
            } else {
                str3 = str;
            }
            constructor = Class.forName(str3, false, context.getClassLoader()).asSubclass(View.class).getConstructor(sConstructorSignature);
            c5308g.put(str, constructor);
        }
        constructor.setAccessible(true);
        return constructor.newInstance(this.mConstructorArgs);
    }

    private View createViewFromTag(Context context, String str, AttributeSet attributeSet) {
        if (str.equals(Promotion.ACTION_VIEW)) {
            str = attributeSet.getAttributeValue(null, "class");
        }
        try {
            Object[] objArr = this.mConstructorArgs;
            objArr[0] = context;
            objArr[1] = attributeSet;
            if (-1 != str.indexOf(46)) {
                return createViewByPrefix(context, str, null);
            }
            int i9 = 0;
            while (true) {
                String[] strArr = sClassPrefixList;
                if (i9 >= strArr.length) {
                    return null;
                }
                View viewCreateViewByPrefix = createViewByPrefix(context, str, strArr[i9]);
                if (viewCreateViewByPrefix != null) {
                    return viewCreateViewByPrefix;
                }
                i9++;
            }
        } catch (Exception unused) {
            return null;
        } finally {
            Object[] objArr2 = this.mConstructorArgs;
            objArr2[0] = null;
            objArr2[1] = null;
        }
    }

    private static Context themifyContext(Context context, AttributeSet attributeSet, boolean z8, boolean z9) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0569j.View, 0, 0);
        int resourceId = z8 ? typedArrayObtainStyledAttributes.getResourceId(C0569j.View_android_theme, 0) : 0;
        if (z9 && resourceId == 0 && (resourceId = typedArrayObtainStyledAttributes.getResourceId(C0569j.View_theme, 0)) != 0) {
            Log.i(LOG_TAG, "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        typedArrayObtainStyledAttributes.recycle();
        return resourceId != 0 ? ((context instanceof C4798d) && ((C4798d) context).m19045c() == resourceId) ? context : new C4798d(context, resourceId) : context;
    }

    private void verifyNotNull(View view, String str) {
        if (view != null) {
            return;
        }
        throw new IllegalStateException(getClass().getName() + " asked to inflate view for <" + str + ">, but returned null");
    }

    public AppCompatAutoCompleteTextView createAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        return new AppCompatAutoCompleteTextView(context, attributeSet);
    }

    public AppCompatButton createButton(Context context, AttributeSet attributeSet) {
        return new AppCompatButton(context, attributeSet);
    }

    public AppCompatCheckBox createCheckBox(Context context, AttributeSet attributeSet) {
        return new AppCompatCheckBox(context, attributeSet);
    }

    public AppCompatCheckedTextView createCheckedTextView(Context context, AttributeSet attributeSet) {
        return new AppCompatCheckedTextView(context, attributeSet);
    }

    public AppCompatEditText createEditText(Context context, AttributeSet attributeSet) {
        return new AppCompatEditText(context, attributeSet);
    }

    public AppCompatImageButton createImageButton(Context context, AttributeSet attributeSet) {
        return new AppCompatImageButton(context, attributeSet);
    }

    public AppCompatImageView createImageView(Context context, AttributeSet attributeSet) {
        return new AppCompatImageView(context, attributeSet);
    }

    public AppCompatMultiAutoCompleteTextView createMultiAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        return new AppCompatMultiAutoCompleteTextView(context, attributeSet);
    }

    public AppCompatRadioButton createRadioButton(Context context, AttributeSet attributeSet) {
        return new AppCompatRadioButton(context, attributeSet);
    }

    public AppCompatRatingBar createRatingBar(Context context, AttributeSet attributeSet) {
        return new AppCompatRatingBar(context, attributeSet);
    }

    public AppCompatSeekBar createSeekBar(Context context, AttributeSet attributeSet) {
        return new AppCompatSeekBar(context, attributeSet);
    }

    public AppCompatSpinner createSpinner(Context context, AttributeSet attributeSet) {
        return new AppCompatSpinner(context, attributeSet);
    }

    public AppCompatTextView createTextView(Context context, AttributeSet attributeSet) {
        return new AppCompatTextView(context, attributeSet);
    }

    public AppCompatToggleButton createToggleButton(Context context, AttributeSet attributeSet) {
        return new AppCompatToggleButton(context, attributeSet);
    }

    public View createView(Context context, String str, AttributeSet attributeSet) {
        return null;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final View createView(View view, String str, Context context, AttributeSet attributeSet, boolean z8, boolean z9, boolean z10, boolean z11) {
        View viewCreateRatingBar;
        Context context2 = (!z8 || view == null) ? context : view.getContext();
        if (z9 || z10) {
            context2 = themifyContext(context2, attributeSet, z9, z10);
        }
        if (z11) {
            context2 = C0244n0.m998b(context2);
        }
        str.hashCode();
        char c9 = 65535;
        switch (str.hashCode()) {
            case -1946472170:
                if (str.equals("RatingBar")) {
                    c9 = 0;
                    break;
                }
                break;
            case -1455429095:
                if (str.equals("CheckedTextView")) {
                    c9 = 1;
                    break;
                }
                break;
            case -1346021293:
                if (str.equals("MultiAutoCompleteTextView")) {
                    c9 = 2;
                    break;
                }
                break;
            case -938935918:
                if (str.equals("TextView")) {
                    c9 = 3;
                    break;
                }
                break;
            case -937446323:
                if (str.equals("ImageButton")) {
                    c9 = 4;
                    break;
                }
                break;
            case -658531749:
                if (str.equals("SeekBar")) {
                    c9 = 5;
                    break;
                }
                break;
            case -339785223:
                if (str.equals("Spinner")) {
                    c9 = 6;
                    break;
                }
                break;
            case 776382189:
                if (str.equals("RadioButton")) {
                    c9 = 7;
                    break;
                }
                break;
            case 799298502:
                if (str.equals("ToggleButton")) {
                    c9 = '\b';
                    break;
                }
                break;
            case 1125864064:
                if (str.equals("ImageView")) {
                    c9 = '\t';
                    break;
                }
                break;
            case 1413872058:
                if (str.equals("AutoCompleteTextView")) {
                    c9 = '\n';
                    break;
                }
                break;
            case 1601505219:
                if (str.equals("CheckBox")) {
                    c9 = 11;
                    break;
                }
                break;
            case 1666676343:
                if (str.equals("EditText")) {
                    c9 = '\f';
                    break;
                }
                break;
            case 2001146706:
                if (str.equals("Button")) {
                    c9 = CharUtils.f19105CR;
                    break;
                }
                break;
        }
        switch (c9) {
            case 0:
                viewCreateRatingBar = createRatingBar(context2, attributeSet);
                verifyNotNull(viewCreateRatingBar, str);
                break;
            case 1:
                viewCreateRatingBar = createCheckedTextView(context2, attributeSet);
                verifyNotNull(viewCreateRatingBar, str);
                break;
            case 2:
                viewCreateRatingBar = createMultiAutoCompleteTextView(context2, attributeSet);
                verifyNotNull(viewCreateRatingBar, str);
                break;
            case 3:
                viewCreateRatingBar = createTextView(context2, attributeSet);
                verifyNotNull(viewCreateRatingBar, str);
                break;
            case 4:
                viewCreateRatingBar = createImageButton(context2, attributeSet);
                verifyNotNull(viewCreateRatingBar, str);
                break;
            case 5:
                viewCreateRatingBar = createSeekBar(context2, attributeSet);
                verifyNotNull(viewCreateRatingBar, str);
                break;
            case 6:
                viewCreateRatingBar = createSpinner(context2, attributeSet);
                verifyNotNull(viewCreateRatingBar, str);
                break;
            case 7:
                viewCreateRatingBar = createRadioButton(context2, attributeSet);
                verifyNotNull(viewCreateRatingBar, str);
                break;
            case '\b':
                viewCreateRatingBar = createToggleButton(context2, attributeSet);
                verifyNotNull(viewCreateRatingBar, str);
                break;
            case '\t':
                viewCreateRatingBar = createImageView(context2, attributeSet);
                verifyNotNull(viewCreateRatingBar, str);
                break;
            case '\n':
                viewCreateRatingBar = createAutoCompleteTextView(context2, attributeSet);
                verifyNotNull(viewCreateRatingBar, str);
                break;
            case 11:
                viewCreateRatingBar = createCheckBox(context2, attributeSet);
                verifyNotNull(viewCreateRatingBar, str);
                break;
            case '\f':
                viewCreateRatingBar = createEditText(context2, attributeSet);
                verifyNotNull(viewCreateRatingBar, str);
                break;
            case '\r':
                viewCreateRatingBar = createButton(context2, attributeSet);
                verifyNotNull(viewCreateRatingBar, str);
                break;
            default:
                viewCreateRatingBar = createView(context2, str, attributeSet);
                break;
        }
        if (viewCreateRatingBar == null && context != context2) {
            viewCreateRatingBar = createViewFromTag(context2, str, attributeSet);
        }
        if (viewCreateRatingBar != null) {
            checkOnClickListener(viewCreateRatingBar, attributeSet);
        }
        return viewCreateRatingBar;
    }
}
