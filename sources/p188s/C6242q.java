package p188s;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import net.sqlcipher.database.SQLiteDatabase;
import p197t.C6273a;

/* renamed from: s.q */
/* loaded from: classes.dex */
public final class C6242q implements Iterable<Intent> {

    /* renamed from: b */
    public final ArrayList<Intent> f21048b = new ArrayList<>();

    /* renamed from: c */
    public final Context f21049c;

    /* renamed from: s.q$a */
    public interface a {
        /* renamed from: C */
        Intent mo248C();
    }

    public C6242q(Context context) {
        this.f21049c = context;
    }

    /* renamed from: d */
    public static C6242q m23880d(Context context) {
        return new C6242q(context);
    }

    /* renamed from: a */
    public C6242q m23881a(Intent intent) {
        this.f21048b.add(intent);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    public C6242q m23882b(Activity activity) {
        Intent intentMo248C = activity instanceof a ? ((a) activity).mo248C() : null;
        if (intentMo248C == null) {
            intentMo248C = C6230e.m23808a(activity);
        }
        if (intentMo248C != null) {
            ComponentName component = intentMo248C.getComponent();
            if (component == null) {
                component = intentMo248C.resolveActivity(this.f21049c.getPackageManager());
            }
            m23883c(component);
            m23881a(intentMo248C);
        }
        return this;
    }

    /* renamed from: c */
    public C6242q m23883c(ComponentName componentName) {
        int size = this.f21048b.size();
        try {
            Intent intentM23809b = C6230e.m23809b(this.f21049c, componentName);
            while (intentM23809b != null) {
                this.f21048b.add(size, intentM23809b);
                intentM23809b = C6230e.m23809b(this.f21049c, intentM23809b.getComponent());
            }
            return this;
        } catch (PackageManager.NameNotFoundException e9) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e9);
        }
    }

    /* renamed from: e */
    public void m23884e() {
        m23885f(null);
    }

    /* renamed from: f */
    public void m23885f(Bundle bundle) {
        if (this.f21048b.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        ArrayList<Intent> arrayList = this.f21048b;
        Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        if (C6273a.m24029h(this.f21049c, intentArr, bundle)) {
            return;
        }
        Intent intent = new Intent(intentArr[intentArr.length - 1]);
        intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        this.f21049c.startActivity(intent);
    }

    @Override // java.lang.Iterable
    @Deprecated
    public Iterator<Intent> iterator() {
        return this.f21048b.iterator();
    }
}
