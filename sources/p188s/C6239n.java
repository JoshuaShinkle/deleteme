package p188s;

import android.app.NotificationManager;
import android.content.Context;
import java.util.HashSet;
import java.util.Set;

/* renamed from: s.n */
/* loaded from: classes.dex */
public final class C6239n {

    /* renamed from: c */
    public static final Object f21043c = new Object();

    /* renamed from: d */
    public static Set<String> f21044d = new HashSet();

    /* renamed from: e */
    public static final Object f21045e = new Object();

    /* renamed from: a */
    public final Context f21046a;

    /* renamed from: b */
    public final NotificationManager f21047b;

    public C6239n(Context context) {
        this.f21046a = context;
        this.f21047b = (NotificationManager) context.getSystemService("notification");
    }

    /* renamed from: b */
    public static C6239n m23876b(Context context) {
        return new C6239n(context);
    }

    /* renamed from: a */
    public boolean m23877a() {
        return this.f21047b.areNotificationsEnabled();
    }
}
