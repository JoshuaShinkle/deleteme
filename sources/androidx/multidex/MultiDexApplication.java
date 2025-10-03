package androidx.multidex;

import android.app.Application;
import android.content.Context;
import p133m0.C5310a;

/* loaded from: classes.dex */
public class MultiDexApplication extends Application {
    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        C5310a.m20780k(this);
    }
}
