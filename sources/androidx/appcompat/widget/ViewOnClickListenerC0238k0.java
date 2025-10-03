package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;
import p010b.C0560a;
import p010b.C0565f;
import p062f0.AbstractC4776c;
import p197t.C6273a;

@SuppressLint({"RestrictedAPI"})
/* renamed from: androidx.appcompat.widget.k0 */
/* loaded from: classes.dex */
public class ViewOnClickListenerC0238k0 extends AbstractC4776c implements View.OnClickListener {

    /* renamed from: A */
    public int f1123A;

    /* renamed from: n */
    public final SearchView f1124n;

    /* renamed from: o */
    public final SearchableInfo f1125o;

    /* renamed from: p */
    public final Context f1126p;

    /* renamed from: q */
    public final WeakHashMap<String, Drawable.ConstantState> f1127q;

    /* renamed from: r */
    public final int f1128r;

    /* renamed from: s */
    public boolean f1129s;

    /* renamed from: t */
    public int f1130t;

    /* renamed from: u */
    public ColorStateList f1131u;

    /* renamed from: v */
    public int f1132v;

    /* renamed from: w */
    public int f1133w;

    /* renamed from: x */
    public int f1134x;

    /* renamed from: y */
    public int f1135y;

    /* renamed from: z */
    public int f1136z;

    /* renamed from: androidx.appcompat.widget.k0$a */
    public static final class a {

        /* renamed from: a */
        public final TextView f1137a;

        /* renamed from: b */
        public final TextView f1138b;

        /* renamed from: c */
        public final ImageView f1139c;

        /* renamed from: d */
        public final ImageView f1140d;

        /* renamed from: e */
        public final ImageView f1141e;

        public a(View view) {
            this.f1137a = (TextView) view.findViewById(R.id.text1);
            this.f1138b = (TextView) view.findViewById(R.id.text2);
            this.f1139c = (ImageView) view.findViewById(R.id.icon1);
            this.f1140d = (ImageView) view.findViewById(R.id.icon2);
            this.f1141e = (ImageView) view.findViewById(C0565f.edit_query);
        }
    }

    public ViewOnClickListenerC0238k0(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, Drawable.ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), null, true);
        this.f1129s = false;
        this.f1130t = 1;
        this.f1132v = -1;
        this.f1133w = -1;
        this.f1134x = -1;
        this.f1135y = -1;
        this.f1136z = -1;
        this.f1123A = -1;
        this.f1124n = searchView;
        this.f1125o = searchableInfo;
        this.f1128r = searchView.getSuggestionCommitIconResId();
        this.f1126p = context;
        this.f1127q = weakHashMap;
    }

    /* renamed from: p */
    public static String m911p(Cursor cursor, String str) {
        return m912x(cursor, cursor.getColumnIndex(str));
    }

    /* renamed from: x */
    public static String m912x(Cursor cursor, int i9) {
        if (i9 == -1) {
            return null;
        }
        try {
            return cursor.getString(i9);
        } catch (Exception e9) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e9);
            return null;
        }
    }

    /* renamed from: A */
    public final void m913A(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    /* renamed from: B */
    public final void m914B(String str, Drawable drawable) {
        if (drawable != null) {
            this.f1127q.put(str, drawable.getConstantState());
        }
    }

    /* renamed from: C */
    public final void m915C(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras != null) {
            extras.getBoolean("in_progress");
        }
    }

    @Override // p062f0.AbstractC4774a, p062f0.C4775b.a
    /* renamed from: b */
    public void mo916b(Cursor cursor) {
        if (this.f1129s) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super.mo916b(cursor);
            if (cursor != null) {
                this.f1132v = cursor.getColumnIndex("suggest_text_1");
                this.f1133w = cursor.getColumnIndex("suggest_text_2");
                this.f1134x = cursor.getColumnIndex("suggest_text_2_url");
                this.f1135y = cursor.getColumnIndex("suggest_icon_1");
                this.f1136z = cursor.getColumnIndex("suggest_icon_2");
                this.f1123A = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e9) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e9);
        }
    }

    @Override // p062f0.AbstractC4774a, p062f0.C4775b.a
    public CharSequence convertToString(Cursor cursor) {
        String strM911p;
        String strM911p2;
        if (cursor == null) {
            return null;
        }
        String strM911p3 = m911p(cursor, "suggest_intent_query");
        if (strM911p3 != null) {
            return strM911p3;
        }
        if (this.f1125o.shouldRewriteQueryFromData() && (strM911p2 = m911p(cursor, "suggest_intent_data")) != null) {
            return strM911p2;
        }
        if (!this.f1125o.shouldRewriteQueryFromText() || (strM911p = m911p(cursor, "suggest_text_1")) == null) {
            return null;
        }
        return strM911p;
    }

    @Override // p062f0.AbstractC4774a, p062f0.C4775b.a
    /* renamed from: e */
    public Cursor mo917e(CharSequence charSequence) {
        String string = charSequence == null ? "" : charSequence.toString();
        if (this.f1124n.getVisibility() == 0 && this.f1124n.getWindowVisibility() == 0) {
            try {
                Cursor cursorM930w = m930w(this.f1125o, string, 50);
                if (cursorM930w != null) {
                    cursorM930w.getCount();
                    return cursorM930w;
                }
            } catch (RuntimeException e9) {
                Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e9);
            }
        }
        return null;
    }

    @Override // p062f0.AbstractC4774a
    /* renamed from: f */
    public void mo918f(View view, Context context, Cursor cursor) {
        a aVar = (a) view.getTag();
        int i9 = this.f1123A;
        int i10 = i9 != -1 ? cursor.getInt(i9) : 0;
        if (aVar.f1137a != null) {
            m913A(aVar.f1137a, m912x(cursor, this.f1132v));
        }
        if (aVar.f1138b != null) {
            String strM912x = m912x(cursor, this.f1134x);
            CharSequence charSequenceM921m = strM912x != null ? m921m(strM912x) : m912x(cursor, this.f1133w);
            if (TextUtils.isEmpty(charSequenceM921m)) {
                TextView textView = aVar.f1137a;
                if (textView != null) {
                    textView.setSingleLine(false);
                    aVar.f1137a.setMaxLines(2);
                }
            } else {
                TextView textView2 = aVar.f1137a;
                if (textView2 != null) {
                    textView2.setSingleLine(true);
                    aVar.f1137a.setMaxLines(1);
                }
            }
            m913A(aVar.f1138b, charSequenceM921m);
        }
        ImageView imageView = aVar.f1139c;
        if (imageView != null) {
            m932z(imageView, m928u(cursor), 4);
        }
        ImageView imageView2 = aVar.f1140d;
        if (imageView2 != null) {
            m932z(imageView2, m929v(cursor), 8);
        }
        int i11 = this.f1130t;
        if (i11 != 2 && (i11 != 1 || (i10 & 1) == 0)) {
            aVar.f1141e.setVisibility(8);
            return;
        }
        aVar.f1141e.setVisibility(0);
        aVar.f1141e.setTag(aVar.f1137a.getText());
        aVar.f1141e.setOnClickListener(this);
    }

    @Override // p062f0.AbstractC4774a, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i9, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i9, view, viewGroup);
        } catch (RuntimeException e9) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e9);
            View viewMo3440h = mo3440h(this.f16607e, this.f16606d, viewGroup);
            if (viewMo3440h != null) {
                ((a) viewMo3440h.getTag()).f1137a.setText(e9.toString());
            }
            return viewMo3440h;
        }
    }

    @Override // p062f0.AbstractC4774a, android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i9, view, viewGroup);
        } catch (RuntimeException e9) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e9);
            View viewMo919i = mo919i(this.f16607e, this.f16606d, viewGroup);
            if (viewMo919i != null) {
                ((a) viewMo919i.getTag()).f1137a.setText(e9.toString());
            }
            return viewMo919i;
        }
    }

    @Override // p062f0.AbstractC4774a, android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return false;
    }

    @Override // p062f0.AbstractC4776c, p062f0.AbstractC4774a
    /* renamed from: i */
    public View mo919i(Context context, Cursor cursor, ViewGroup viewGroup) {
        View viewMo919i = super.mo919i(context, cursor, viewGroup);
        viewMo919i.setTag(new a(viewMo919i));
        ((ImageView) viewMo919i.findViewById(C0565f.edit_query)).setImageResource(this.f1128r);
        return viewMo919i;
    }

    /* renamed from: l */
    public final Drawable m920l(String str) {
        Drawable.ConstantState constantState = this.f1127q.get(str);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable();
    }

    /* renamed from: m */
    public final CharSequence m921m(CharSequence charSequence) {
        if (this.f1131u == null) {
            TypedValue typedValue = new TypedValue();
            this.f16607e.getTheme().resolveAttribute(C0560a.textColorSearchUrl, typedValue, true);
            this.f1131u = this.f16607e.getResources().getColorStateList(typedValue.resourceId);
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.f1131u, null), 0, charSequence.length(), 33);
        return spannableString;
    }

    /* renamed from: n */
    public final Drawable m922n(ComponentName componentName) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = this.f16607e.getPackageManager();
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 128);
            int iconResource = activityInfo.getIconResource();
            if (iconResource == 0) {
                return null;
            }
            Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
            if (drawable != null) {
                return drawable;
            }
            Log.w("SuggestionsAdapter", "Invalid icon resource " + iconResource + " for " + componentName.flattenToShortString());
            return null;
        } catch (PackageManager.NameNotFoundException e9) {
            Log.w("SuggestionsAdapter", e9.toString());
            return null;
        }
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        m915C(mo19006d());
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        m915C(mo19006d());
    }

    /* renamed from: o */
    public final Drawable m923o(ComponentName componentName) throws PackageManager.NameNotFoundException {
        String strFlattenToShortString = componentName.flattenToShortString();
        if (!this.f1127q.containsKey(strFlattenToShortString)) {
            Drawable drawableM922n = m922n(componentName);
            this.f1127q.put(strFlattenToShortString, drawableM922n != null ? drawableM922n.getConstantState() : null);
            return drawableM922n;
        }
        Drawable.ConstantState constantState = this.f1127q.get(strFlattenToShortString);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable(this.f1126p.getResources());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.f1124n.m746u((CharSequence) tag);
        }
    }

    /* renamed from: q */
    public final Drawable m924q() throws PackageManager.NameNotFoundException {
        Drawable drawableM923o = m923o(this.f1125o.getSearchActivity());
        return drawableM923o != null ? drawableM923o : this.f16607e.getPackageManager().getDefaultActivityIcon();
    }

    /* renamed from: r */
    public final Drawable m925r(Uri uri) throws IOException {
        try {
            if ("android.resource".equals(uri.getScheme())) {
                try {
                    return m926s(uri);
                } catch (Resources.NotFoundException unused) {
                    throw new FileNotFoundException("Resource does not exist: " + uri);
                }
            }
            InputStream inputStreamOpenInputStream = this.f1126p.getContentResolver().openInputStream(uri);
            if (inputStreamOpenInputStream == null) {
                throw new FileNotFoundException("Failed to open " + uri);
            }
            try {
                return Drawable.createFromStream(inputStreamOpenInputStream, null);
            } finally {
                try {
                    inputStreamOpenInputStream.close();
                } catch (IOException e9) {
                    Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e9);
                }
            }
        } catch (FileNotFoundException e10) {
            Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e10.getMessage());
            return null;
        }
        Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e10.getMessage());
        return null;
    }

    /* renamed from: s */
    public Drawable m926s(Uri uri) throws PackageManager.NameNotFoundException, NumberFormatException, FileNotFoundException {
        int identifier;
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        try {
            Resources resourcesForApplication = this.f16607e.getPackageManager().getResourcesForApplication(authority);
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                throw new FileNotFoundException("No path: " + uri);
            }
            int size = pathSegments.size();
            if (size == 1) {
                try {
                    identifier = Integer.parseInt(pathSegments.get(0));
                } catch (NumberFormatException unused) {
                    throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                }
            } else {
                if (size != 2) {
                    throw new FileNotFoundException("More than two path segments: " + uri);
                }
                identifier = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
            }
            if (identifier != 0) {
                return resourcesForApplication.getDrawable(identifier);
            }
            throw new FileNotFoundException("No resource found for: " + uri);
        } catch (PackageManager.NameNotFoundException unused2) {
            throw new FileNotFoundException("No package found for authority: " + uri);
        }
    }

    /* renamed from: t */
    public final Drawable m927t(String str) throws NumberFormatException, IOException {
        if (str == null || str.isEmpty() || "0".equals(str)) {
            return null;
        }
        try {
            int i9 = Integer.parseInt(str);
            String str2 = "android.resource://" + this.f1126p.getPackageName() + "/" + i9;
            Drawable drawableM920l = m920l(str2);
            if (drawableM920l != null) {
                return drawableM920l;
            }
            Drawable drawableM24025d = C6273a.m24025d(this.f1126p, i9);
            m914B(str2, drawableM24025d);
            return drawableM24025d;
        } catch (Resources.NotFoundException unused) {
            Log.w("SuggestionsAdapter", "Icon resource not found: " + str);
            return null;
        } catch (NumberFormatException unused2) {
            Drawable drawableM920l2 = m920l(str);
            if (drawableM920l2 != null) {
                return drawableM920l2;
            }
            Drawable drawableM925r = m925r(Uri.parse(str));
            m914B(str, drawableM925r);
            return drawableM925r;
        }
    }

    /* renamed from: u */
    public final Drawable m928u(Cursor cursor) throws NumberFormatException, IOException {
        int i9 = this.f1135y;
        if (i9 == -1) {
            return null;
        }
        Drawable drawableM927t = m927t(cursor.getString(i9));
        return drawableM927t != null ? drawableM927t : m924q();
    }

    /* renamed from: v */
    public final Drawable m929v(Cursor cursor) {
        int i9 = this.f1136z;
        if (i9 == -1) {
            return null;
        }
        return m927t(cursor.getString(i9));
    }

    /* renamed from: w */
    public Cursor m930w(SearchableInfo searchableInfo, String str, int i9) {
        String suggestAuthority;
        String[] strArr = null;
        if (searchableInfo == null || (suggestAuthority = searchableInfo.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder builderFragment = new Uri.Builder().scheme(FirebaseAnalytics.Param.CONTENT).authority(suggestAuthority).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            builderFragment.appendEncodedPath(suggestPath);
        }
        builderFragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            builderFragment.appendPath(str);
        }
        String[] strArr2 = strArr;
        if (i9 > 0) {
            builderFragment.appendQueryParameter("limit", String.valueOf(i9));
        }
        return this.f16607e.getContentResolver().query(builderFragment.build(), null, suggestSelection, strArr2, null);
    }

    /* renamed from: y */
    public void m931y(int i9) {
        this.f1130t = i9;
    }

    /* renamed from: z */
    public final void m932z(ImageView imageView, Drawable drawable, int i9) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i9);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }
}
