package me.leolin.shortcutbadger.impl;

import android.content.AsyncQueryHandler;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Looper;
import java.util.Arrays;
import java.util.List;
import p068f6.InterfaceC4789a;

/* loaded from: classes2.dex */
public class SonyHomeBadger implements InterfaceC4789a {

    /* renamed from: a */
    public final Uri f18188a = Uri.parse("content://com.sonymobile.home.resourceprovider/badge");

    /* renamed from: b */
    public AsyncQueryHandler f18189b;

    /* renamed from: me.leolin.shortcutbadger.impl.SonyHomeBadger$a */
    public class C5340a extends AsyncQueryHandler {
        public C5340a(ContentResolver contentResolver) {
            super(contentResolver);
        }
    }

    /* renamed from: d */
    public static void m20986d(Context context, ComponentName componentName, int i9) {
        Intent intent = new Intent("com.sonyericsson.home.action.UPDATE_BADGE");
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", componentName.getPackageName());
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", componentName.getClassName());
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", String.valueOf(i9));
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", i9 > 0);
        context.sendBroadcast(intent);
    }

    /* renamed from: h */
    public static boolean m20987h(Context context) {
        return context.getPackageManager().resolveContentProvider("com.sonymobile.home.resourceprovider", 0) != null;
    }

    @Override // p068f6.InterfaceC4789a
    /* renamed from: a */
    public List<String> mo19020a() {
        return Arrays.asList("com.sonyericsson.home", "com.sonymobile.home");
    }

    @Override // p068f6.InterfaceC4789a
    /* renamed from: b */
    public void mo19021b(Context context, ComponentName componentName, int i9) {
        if (m20987h(context)) {
            m20989e(context, componentName, i9);
        } else {
            m20986d(context, componentName, i9);
        }
    }

    /* renamed from: c */
    public final ContentValues m20988c(int i9, ComponentName componentName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("badge_count", Integer.valueOf(i9));
        contentValues.put("package_name", componentName.getPackageName());
        contentValues.put("activity_name", componentName.getClassName());
        return contentValues;
    }

    /* renamed from: e */
    public final void m20989e(Context context, ComponentName componentName, int i9) {
        if (i9 < 0) {
            return;
        }
        ContentValues contentValuesM20988c = m20988c(i9, componentName);
        if (Looper.myLooper() != Looper.getMainLooper()) {
            m20991g(context, contentValuesM20988c);
            return;
        }
        if (this.f18189b == null) {
            this.f18189b = new C5340a(context.getApplicationContext().getContentResolver());
        }
        m20990f(contentValuesM20988c);
    }

    /* renamed from: f */
    public final void m20990f(ContentValues contentValues) {
        this.f18189b.startInsert(0, null, this.f18188a, contentValues);
    }

    /* renamed from: g */
    public final void m20991g(Context context, ContentValues contentValues) {
        context.getApplicationContext().getContentResolver().insert(this.f18188a, contentValues);
    }
}
