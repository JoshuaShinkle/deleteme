package p245y2;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.friends.Group;
import java.util.Collections;
import net.sqlcipher.database.SQLiteDatabase;
import p116k4.C5154j;
import p173q2.C6127a;
import p209u2.AbstractC6381r;
import p209u2.C6385v;

/* renamed from: y2.j5 */
/* loaded from: classes.dex */
public class C6675j5 {

    /* renamed from: y2.j5$a */
    public class a extends AbstractC6381r<Void, Exception> {

        /* renamed from: c */
        public final /* synthetic */ AbstractC6381r f22330c;

        public a(AbstractC6381r abstractC6381r) {
            this.f22330c = abstractC6381r;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Void r12) {
            this.f22330c.m24505c();
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Exception exc) {
            C5154j.m20076j(exc);
            this.f22330c.m24507e();
        }
    }

    /* renamed from: c */
    public static void m25302c(final Context context, final Group group, final AbstractC6381r<Void, Exception> abstractC6381r) {
        C6385v.m24526d(new Runnable() { // from class: y2.h5
            @Override // java.lang.Runnable
            public final void run() {
                C6675j5.m25305f(context, group, abstractC6381r);
            }
        });
    }

    /* renamed from: d */
    public static void m25303d(Context context, Group group, AbstractC6381r<Void, Void> abstractC6381r) {
        m25302c(context, group, new a(abstractC6381r));
    }

    /* renamed from: e */
    public static void m25304e(final Context context, final Group group, final Bitmap bitmap, final AbstractC6381r<Void, Exception> abstractC6381r) {
        C6385v.m24527e(new Runnable() { // from class: y2.i5
            @Override // java.lang.Runnable
            public final void run() {
                C6675j5.m25306g(context, group, bitmap, abstractC6381r);
            }
        });
    }

    /* renamed from: f */
    public static /* synthetic */ void m25305f(Context context, Group group, AbstractC6381r abstractC6381r) {
        try {
            Bitmap bitmapM23463d = C6127a.m23463d(context, group);
            if (bitmapM23463d == null) {
                bitmapM23463d = null;
            }
            m25304e(context, group, bitmapM23463d, abstractC6381r);
        } catch (Exception e9) {
            abstractC6381r.m24508f(e9);
        }
    }

    /* renamed from: g */
    public static /* synthetic */ void m25306g(Context context, Group group, Bitmap bitmap, AbstractC6381r abstractC6381r) {
        Intent intent = new Intent(context, (Class<?>) ChatDialogActivity.class);
        intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        intent.addFlags(67108864);
        intent.putExtra("groupId", group.f13727n);
        ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(ShortcutManager.class);
        if (shortcutManager == null || !shortcutManager.isRequestPinShortcutSupported()) {
            abstractC6381r.m24508f(new Exception("ShortcutManager is null or not support pinned shortcuts"));
            return;
        }
        intent.setAction("android.intent.action.CREATE_SHORTCUT");
        ShortcutInfo shortcutInfoBuild = new ShortcutInfo.Builder(context, String.valueOf(group.f13727n)).setShortLabel(group.f13717d).setIcon(Icon.createWithBitmap(bitmap)).setIntent(intent).build();
        shortcutManager.updateShortcuts(Collections.singletonList(shortcutInfoBuild));
        shortcutManager.requestPinShortcut(shortcutInfoBuild, null);
        abstractC6381r.m24505c();
    }
}
