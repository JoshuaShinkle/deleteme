package p065f3;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import java.io.File;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import p116k4.C5154j;
import p209u2.C6369f;
import p209u2.C6370g;

/* renamed from: f3.b */
/* loaded from: classes.dex */
public class AsyncTaskC4780b extends AsyncTask<Void, Void, Boolean> {

    /* renamed from: a */
    public final WeakReference<Context> f16628a;

    public AsyncTaskC4780b(Context context) {
        this.f16628a = new WeakReference<>(context);
    }

    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Boolean doInBackground(Void[] voidArr) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Context context = this.f16628a.get();
        if (context == null) {
            return Boolean.FALSE;
        }
        AssetManager assets = context.getAssets();
        File file = new File(C6369f.m24468j(context), "LICENSE.txt");
        if (file.exists()) {
            return Boolean.TRUE;
        }
        InputStream inputStreamOpen = null;
        try {
            inputStreamOpen = assets.open("data/LICENSE.txt");
            return Boolean.valueOf(C6369f.m24460b(inputStreamOpen, file));
        } catch (Exception e9) {
            C5154j.m20076j(e9);
            return Boolean.FALSE;
        } finally {
            C6370g.m24480b(inputStreamOpen);
        }
    }
}
