package com.google.zxing.client.android.share;

import android.graphics.drawable.Drawable;

/* loaded from: classes2.dex */
final class AppInfo implements Comparable<AppInfo> {
    private final Drawable icon;
    private final String label;
    private final String packageName;

    public AppInfo(String str, String str2, Drawable drawable) {
        this.packageName = str;
        this.label = str2;
        this.icon = drawable;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AppInfo) {
            return this.label.equals(((AppInfo) obj).label);
        }
        return false;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public int hashCode() {
        return this.label.hashCode();
    }

    public String toString() {
        return this.label;
    }

    @Override // java.lang.Comparable
    public int compareTo(AppInfo appInfo) {
        return this.label.compareTo(appInfo.label);
    }
}
