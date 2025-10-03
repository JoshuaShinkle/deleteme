package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.WorkSource;
import android.util.Log;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@KeepForSdk
/* loaded from: classes2.dex */
public class WorkSourceUtil {
    private static final int zza = Process.myUid();
    private static final Method zzb;
    private static final Method zzc;
    private static final Method zzd;
    private static final Method zze;
    private static final Method zzf;
    private static final Method zzg;
    private static final Method zzh;
    private static final Method zzi;

    static {
        Method method;
        Method method2;
        Method method3;
        Method method4;
        Method method5;
        Method method6;
        Method method7;
        Method method8 = null;
        try {
            method = WorkSource.class.getMethod(ProductAction.ACTION_ADD, Integer.TYPE);
        } catch (Exception unused) {
            method = null;
        }
        zzb = method;
        if (PlatformVersion.isAtLeastJellyBeanMR2()) {
            try {
                method2 = WorkSource.class.getMethod(ProductAction.ACTION_ADD, Integer.TYPE, String.class);
            } catch (Exception unused2) {
            }
        } else {
            method2 = null;
        }
        zzc = method2;
        try {
            method3 = WorkSource.class.getMethod("size", new Class[0]);
        } catch (Exception unused3) {
            method3 = null;
        }
        zzd = method3;
        try {
            method4 = WorkSource.class.getMethod("get", Integer.TYPE);
        } catch (Exception unused4) {
            method4 = null;
        }
        zze = method4;
        if (PlatformVersion.isAtLeastJellyBeanMR2()) {
            try {
                method5 = WorkSource.class.getMethod("getName", Integer.TYPE);
            } catch (Exception unused5) {
            }
        } else {
            method5 = null;
        }
        zzf = method5;
        if (PlatformVersion.isAtLeastP()) {
            try {
                method6 = WorkSource.class.getMethod("createWorkChain", new Class[0]);
            } catch (Exception e9) {
                Log.w("WorkSourceUtil", "Missing WorkChain API createWorkChain", e9);
            }
        } else {
            method6 = null;
        }
        zzg = method6;
        if (PlatformVersion.isAtLeastP()) {
            try {
                method7 = Class.forName("android.os.WorkSource$WorkChain").getMethod("addNode", Integer.TYPE, String.class);
            } catch (Exception e10) {
                Log.w("WorkSourceUtil", "Missing WorkChain class", e10);
            }
        } else {
            method7 = null;
        }
        zzh = method7;
        if (PlatformVersion.isAtLeastP()) {
            try {
                method8 = WorkSource.class.getMethod("isEmpty", new Class[0]);
                method8.setAccessible(true);
            } catch (Exception unused6) {
            }
        }
        zzi = method8;
    }

    private WorkSourceUtil() {
    }

    @KeepForSdk
    public static void add(WorkSource workSource, int i9, String str) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = zzc;
        if (method != null) {
            if (str == null) {
                str = "";
            }
            try {
                method.invoke(workSource, Integer.valueOf(i9), str);
                return;
            } catch (Exception e9) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e9);
                return;
            }
        }
        Method method2 = zzb;
        if (method2 != null) {
            try {
                method2.invoke(workSource, Integer.valueOf(i9));
            } catch (Exception e10) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e10);
            }
        }
    }

    @KeepForSdk
    public static WorkSource fromPackage(Context context, String str) {
        if (context != null && context.getPackageManager() != null && str != null) {
            try {
                ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
                if (applicationInfo == null) {
                    Log.e("WorkSourceUtil", "Could not get applicationInfo from package: ".concat(str));
                    return null;
                }
                int i9 = applicationInfo.uid;
                WorkSource workSource = new WorkSource();
                add(workSource, i9, str);
                return workSource;
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("WorkSourceUtil", "Could not find package: ".concat(str));
            }
        }
        return null;
    }

    @KeepForSdk
    public static WorkSource fromPackageAndModuleExperimentalPi(Context context, String str, String str2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method;
        if (context == null || context.getPackageManager() == null || str2 == null || str == null) {
            Log.w("WorkSourceUtil", "Unexpected null arguments");
            return null;
        }
        int i9 = -1;
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
            if (applicationInfo == null) {
                Log.e("WorkSourceUtil", "Could not get applicationInfo from package: ".concat(str));
            } else {
                i9 = applicationInfo.uid;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("WorkSourceUtil", "Could not find package: ".concat(str));
        }
        if (i9 < 0) {
            return null;
        }
        WorkSource workSource = new WorkSource();
        Method method2 = zzg;
        if (method2 == null || (method = zzh) == null) {
            add(workSource, i9, str);
        } else {
            try {
                Object objInvoke = method2.invoke(workSource, new Object[0]);
                int i10 = zza;
                if (i9 != i10) {
                    method.invoke(objInvoke, Integer.valueOf(i9), str);
                }
                method.invoke(objInvoke, Integer.valueOf(i10), str2);
            } catch (Exception e9) {
                Log.w("WorkSourceUtil", "Unable to assign chained blame through WorkSource", e9);
            }
        }
        return workSource;
    }

    @KeepForSdk
    public static int get(WorkSource workSource, int i9) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = zze;
        if (method != null) {
            try {
                Object objInvoke = method.invoke(workSource, Integer.valueOf(i9));
                Preconditions.checkNotNull(objInvoke);
                return ((Integer) objInvoke).intValue();
            } catch (Exception e9) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e9);
            }
        }
        return 0;
    }

    @KeepForSdk
    public static String getName(WorkSource workSource, int i9) {
        Method method = zzf;
        if (method == null) {
            return null;
        }
        try {
            return (String) method.invoke(workSource, Integer.valueOf(i9));
        } catch (Exception e9) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e9);
            return null;
        }
    }

    @KeepForSdk
    public static List<String> getNames(WorkSource workSource) {
        ArrayList arrayList = new ArrayList();
        int size = workSource == null ? 0 : size(workSource);
        if (size != 0) {
            for (int i9 = 0; i9 < size; i9++) {
                String name = getName(workSource, i9);
                if (!Strings.isEmptyOrWhitespace(name)) {
                    Preconditions.checkNotNull(name);
                    arrayList.add(name);
                }
            }
        }
        return arrayList;
    }

    @KeepForSdk
    public static boolean hasWorkSourcePermission(Context context) {
        return (context == null || context.getPackageManager() == null || Wrappers.packageManager(context).checkPermission("android.permission.UPDATE_DEVICE_STATS", context.getPackageName()) != 0) ? false : true;
    }

    @KeepForSdk
    public static boolean isEmpty(WorkSource workSource) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = zzi;
        if (method != null) {
            try {
                Object objInvoke = method.invoke(workSource, new Object[0]);
                Preconditions.checkNotNull(objInvoke);
                return ((Boolean) objInvoke).booleanValue();
            } catch (Exception e9) {
                Log.e("WorkSourceUtil", "Unable to check WorkSource emptiness", e9);
            }
        }
        return size(workSource) == 0;
    }

    @KeepForSdk
    public static int size(WorkSource workSource) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = zzd;
        if (method != null) {
            try {
                Object objInvoke = method.invoke(workSource, new Object[0]);
                Preconditions.checkNotNull(objInvoke);
                return ((Integer) objInvoke).intValue();
            } catch (Exception e9) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e9);
            }
        }
        return 0;
    }
}
