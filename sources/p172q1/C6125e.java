package p172q1;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Deprecated
/* renamed from: q1.e */
/* loaded from: classes.dex */
public final class C6125e {

    /* renamed from: a */
    public final Context f20751a;

    public C6125e(Context context) {
        this.f20751a = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002d  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static InterfaceC6123c m23457b(String str) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Object objNewInstance;
        try {
            Class<?> cls = Class.forName(str);
            try {
                objNewInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (IllegalAccessException e9) {
                m23458c(cls, e9);
                objNewInstance = null;
                if (objNewInstance instanceof InterfaceC6123c) {
                }
            } catch (InstantiationException e10) {
                m23458c(cls, e10);
                objNewInstance = null;
                if (objNewInstance instanceof InterfaceC6123c) {
                }
            } catch (NoSuchMethodException e11) {
                m23458c(cls, e11);
                objNewInstance = null;
                if (objNewInstance instanceof InterfaceC6123c) {
                }
            } catch (InvocationTargetException e12) {
                m23458c(cls, e12);
                objNewInstance = null;
                if (objNewInstance instanceof InterfaceC6123c) {
                }
            }
            if (objNewInstance instanceof InterfaceC6123c) {
                return (InterfaceC6123c) objNewInstance;
            }
            throw new RuntimeException("Expected instanceof GlideModule, but found: " + objNewInstance);
        } catch (ClassNotFoundException e13) {
            throw new IllegalArgumentException("Unable to find GlideModule implementation", e13);
        }
    }

    /* renamed from: c */
    public static void m23458c(Class<?> cls, Exception exc) {
        throw new RuntimeException("Unable to instantiate GlideModule implementation for " + cls, exc);
    }

    /* renamed from: a */
    public List<InterfaceC6123c> m23459a() throws PackageManager.NameNotFoundException {
        if (Log.isLoggable("ManifestParser", 3)) {
            Log.d("ManifestParser", "Loading Glide modules");
        }
        ArrayList arrayList = new ArrayList();
        try {
            ApplicationInfo applicationInfo = this.f20751a.getPackageManager().getApplicationInfo(this.f20751a.getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                if (Log.isLoggable("ManifestParser", 3)) {
                    Log.d("ManifestParser", "Got null app info metadata");
                }
                return arrayList;
            }
            if (Log.isLoggable("ManifestParser", 2)) {
                Log.v("ManifestParser", "Got app info metadata: " + applicationInfo.metaData);
            }
            for (String str : applicationInfo.metaData.keySet()) {
                if ("GlideModule".equals(applicationInfo.metaData.get(str))) {
                    arrayList.add(m23457b(str));
                    if (Log.isLoggable("ManifestParser", 3)) {
                        Log.d("ManifestParser", "Loaded Glide module: " + str);
                    }
                }
            }
            if (Log.isLoggable("ManifestParser", 3)) {
                Log.d("ManifestParser", "Finished loading Glide modules");
            }
            return arrayList;
        } catch (PackageManager.NameNotFoundException e9) {
            throw new RuntimeException("Unable to find metadata to parse GlideModules", e9);
        }
    }
}
