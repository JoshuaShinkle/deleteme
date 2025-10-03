package androidx.appcompat.widget;

import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: androidx.appcompat.widget.h0 */
/* loaded from: classes.dex */
public class C0232h0 extends Resources {

    /* renamed from: a */
    public final Resources f1083a;

    public C0232h0(Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.f1083a = resources;
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getAnimation(int i9) {
        return this.f1083a.getAnimation(i9);
    }

    @Override // android.content.res.Resources
    public boolean getBoolean(int i9) {
        return this.f1083a.getBoolean(i9);
    }

    @Override // android.content.res.Resources
    public int getColor(int i9) {
        return this.f1083a.getColor(i9);
    }

    @Override // android.content.res.Resources
    public ColorStateList getColorStateList(int i9) {
        return this.f1083a.getColorStateList(i9);
    }

    @Override // android.content.res.Resources
    public Configuration getConfiguration() {
        return this.f1083a.getConfiguration();
    }

    @Override // android.content.res.Resources
    public float getDimension(int i9) {
        return this.f1083a.getDimension(i9);
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelOffset(int i9) {
        return this.f1083a.getDimensionPixelOffset(i9);
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelSize(int i9) {
        return this.f1083a.getDimensionPixelSize(i9);
    }

    @Override // android.content.res.Resources
    public DisplayMetrics getDisplayMetrics() {
        return this.f1083a.getDisplayMetrics();
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i9) {
        return this.f1083a.getDrawable(i9);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int i9, int i10) {
        return this.f1083a.getDrawableForDensity(i9, i10);
    }

    @Override // android.content.res.Resources
    public float getFraction(int i9, int i10, int i11) {
        return this.f1083a.getFraction(i9, i10, i11);
    }

    @Override // android.content.res.Resources
    public int getIdentifier(String str, String str2, String str3) {
        return this.f1083a.getIdentifier(str, str2, str3);
    }

    @Override // android.content.res.Resources
    public int[] getIntArray(int i9) {
        return this.f1083a.getIntArray(i9);
    }

    @Override // android.content.res.Resources
    public int getInteger(int i9) {
        return this.f1083a.getInteger(i9);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getLayout(int i9) {
        return this.f1083a.getLayout(i9);
    }

    @Override // android.content.res.Resources
    public Movie getMovie(int i9) {
        return this.f1083a.getMovie(i9);
    }

    @Override // android.content.res.Resources
    public String getQuantityString(int i9, int i10, Object... objArr) {
        return this.f1083a.getQuantityString(i9, i10, objArr);
    }

    @Override // android.content.res.Resources
    public CharSequence getQuantityText(int i9, int i10) {
        return this.f1083a.getQuantityText(i9, i10);
    }

    @Override // android.content.res.Resources
    public String getResourceEntryName(int i9) {
        return this.f1083a.getResourceEntryName(i9);
    }

    @Override // android.content.res.Resources
    public String getResourceName(int i9) {
        return this.f1083a.getResourceName(i9);
    }

    @Override // android.content.res.Resources
    public String getResourcePackageName(int i9) {
        return this.f1083a.getResourcePackageName(i9);
    }

    @Override // android.content.res.Resources
    public String getResourceTypeName(int i9) {
        return this.f1083a.getResourceTypeName(i9);
    }

    @Override // android.content.res.Resources
    public String getString(int i9) {
        return this.f1083a.getString(i9);
    }

    @Override // android.content.res.Resources
    public String[] getStringArray(int i9) {
        return this.f1083a.getStringArray(i9);
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int i9) {
        return this.f1083a.getText(i9);
    }

    @Override // android.content.res.Resources
    public CharSequence[] getTextArray(int i9) {
        return this.f1083a.getTextArray(i9);
    }

    @Override // android.content.res.Resources
    public void getValue(int i9, TypedValue typedValue, boolean z8) throws Resources.NotFoundException {
        this.f1083a.getValue(i9, typedValue, z8);
    }

    @Override // android.content.res.Resources
    public void getValueForDensity(int i9, int i10, TypedValue typedValue, boolean z8) throws Resources.NotFoundException {
        this.f1083a.getValueForDensity(i9, i10, typedValue, z8);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getXml(int i9) {
        return this.f1083a.getXml(i9);
    }

    @Override // android.content.res.Resources
    public TypedArray obtainAttributes(AttributeSet attributeSet, int[] iArr) {
        return this.f1083a.obtainAttributes(attributeSet, iArr);
    }

    @Override // android.content.res.Resources
    public TypedArray obtainTypedArray(int i9) {
        return this.f1083a.obtainTypedArray(i9);
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int i9) {
        return this.f1083a.openRawResource(i9);
    }

    @Override // android.content.res.Resources
    public AssetFileDescriptor openRawResourceFd(int i9) {
        return this.f1083a.openRawResourceFd(i9);
    }

    @Override // android.content.res.Resources
    public void parseBundleExtra(String str, AttributeSet attributeSet, Bundle bundle) throws XmlPullParserException {
        this.f1083a.parseBundleExtra(str, attributeSet, bundle);
    }

    @Override // android.content.res.Resources
    public void parseBundleExtras(XmlResourceParser xmlResourceParser, Bundle bundle) throws XmlPullParserException, IOException {
        this.f1083a.parseBundleExtras(xmlResourceParser, bundle);
    }

    @Override // android.content.res.Resources
    public void updateConfiguration(Configuration configuration, DisplayMetrics displayMetrics) {
        super.updateConfiguration(configuration, displayMetrics);
        Resources resources = this.f1083a;
        if (resources != null) {
            resources.updateConfiguration(configuration, displayMetrics);
        }
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i9, Resources.Theme theme) {
        return this.f1083a.getDrawable(i9, theme);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int i9, int i10, Resources.Theme theme) {
        return this.f1083a.getDrawableForDensity(i9, i10, theme);
    }

    @Override // android.content.res.Resources
    public String getQuantityString(int i9, int i10) {
        return this.f1083a.getQuantityString(i9, i10);
    }

    @Override // android.content.res.Resources
    public String getString(int i9, Object... objArr) {
        return this.f1083a.getString(i9, objArr);
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int i9, CharSequence charSequence) {
        return this.f1083a.getText(i9, charSequence);
    }

    @Override // android.content.res.Resources
    public void getValue(String str, TypedValue typedValue, boolean z8) throws Resources.NotFoundException {
        this.f1083a.getValue(str, typedValue, z8);
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int i9, TypedValue typedValue) {
        return this.f1083a.openRawResource(i9, typedValue);
    }
}
