package p078g6;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.impl.DefaultBadger;
import p068f6.InterfaceC4789a;
import p088h6.C5028b;

/* renamed from: g6.e */
/* loaded from: classes2.dex */
public class C4964e implements InterfaceC4789a {

    /* renamed from: b */
    public static final String[] f17058b = {"_id", "class"};

    /* renamed from: a */
    public DefaultBadger f17059a = new DefaultBadger();

    @Override // p068f6.InterfaceC4789a
    /* renamed from: a */
    public List<String> mo19020a() {
        return Arrays.asList("com.sec.android.app.launcher", "com.sec.android.app.twlauncher");
    }

    @Override // p068f6.InterfaceC4789a
    /* renamed from: b */
    public void mo19021b(Context context, ComponentName componentName, int i9) throws ShortcutBadgeException {
        DefaultBadger defaultBadger = this.f17059a;
        if (defaultBadger != null && defaultBadger.m20985c(context)) {
            this.f17059a.mo19021b(context, componentName, i9);
            return;
        }
        Uri uri = Uri.parse("content://com.sec.badge/apps?notify=true");
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursorQuery = null;
        try {
            cursorQuery = contentResolver.query(uri, f17058b, "package=?", new String[]{componentName.getPackageName()}, null);
            if (cursorQuery != null) {
                String className = componentName.getClassName();
                boolean z8 = false;
                while (cursorQuery.moveToNext()) {
                    contentResolver.update(uri, m19242c(componentName, i9, false), "_id=?", new String[]{String.valueOf(cursorQuery.getInt(0))});
                    if (className.equals(cursorQuery.getString(cursorQuery.getColumnIndex("class")))) {
                        z8 = true;
                    }
                }
                if (!z8) {
                    contentResolver.insert(uri, m19242c(componentName, i9, true));
                }
            }
        } finally {
            C5028b.m19604a(cursorQuery);
        }
    }

    /* renamed from: c */
    public final ContentValues m19242c(ComponentName componentName, int i9, boolean z8) {
        ContentValues contentValues = new ContentValues();
        if (z8) {
            contentValues.put("package", componentName.getPackageName());
            contentValues.put("class", componentName.getClassName());
        }
        contentValues.put("badgecount", Integer.valueOf(i9));
        return contentValues;
    }
}
