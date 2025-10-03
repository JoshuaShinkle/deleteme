package com.google.android.gms.internal.play_billing;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
abstract class zzbv {
    private static final Logger zza = Logger.getLogger(zzbi.class.getName());
    private static final String zzb = "com.google.protobuf.BlazeGeneratedExtensionRegistryLiteLoader";

    public static zzbn zzb(Class cls) {
        String str;
        ClassLoader classLoader = zzbv.class.getClassLoader();
        if (cls.equals(zzbn.class)) {
            str = zzb;
        } else {
            if (!cls.getPackage().equals(zzbv.class.getPackage())) {
                throw new IllegalArgumentException(cls.getName());
            }
            str = String.format("%s.BlazeGenerated%sLoader", cls.getPackage().getName(), cls.getSimpleName());
        }
        try {
            try {
                try {
                    try {
                        return (zzbn) cls.cast(((zzbv) Class.forName(str, true, classLoader).getConstructor(new Class[0]).newInstance(new Object[0])).zza());
                    } catch (IllegalAccessException e9) {
                        throw new IllegalStateException(e9);
                    }
                } catch (InvocationTargetException e10) {
                    throw new IllegalStateException(e10);
                }
            } catch (InstantiationException e11) {
                throw new IllegalStateException(e11);
            } catch (NoSuchMethodException e12) {
                throw new IllegalStateException(e12);
            }
        } catch (ClassNotFoundException unused) {
            Iterator it = ServiceLoader.load(zzbv.class, classLoader).iterator();
            ArrayList arrayList = new ArrayList();
            while (it.hasNext()) {
                try {
                    arrayList.add((zzbn) cls.cast(((zzbv) it.next()).zza()));
                } catch (ServiceConfigurationError e13) {
                    zza.logp(Level.SEVERE, "com.google.protobuf.GeneratedExtensionRegistryLoader", "load", "Unable to load ".concat(cls.getSimpleName()), (Throwable) e13);
                }
            }
            if (arrayList.size() == 1) {
                return (zzbn) arrayList.get(0);
            }
            if (arrayList.size() == 0) {
                return null;
            }
            try {
                return (zzbn) cls.getMethod("combine", Collection.class).invoke(null, arrayList);
            } catch (IllegalAccessException e14) {
                throw new IllegalStateException(e14);
            } catch (NoSuchMethodException e15) {
                throw new IllegalStateException(e15);
            } catch (InvocationTargetException e16) {
                throw new IllegalStateException(e16);
            }
        }
    }

    public abstract zzbn zza();
}
