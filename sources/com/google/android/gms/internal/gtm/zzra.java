package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzqp;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
abstract class zzra<T extends zzqp> {
    private static final Logger logger = Logger.getLogger(zzqj.class.getName());
    private static String zzbai = "com.google.protobuf.BlazeGeneratedExtensionRegistryLiteLoader";

    public static <T extends zzqp> T zzd(Class<T> cls) {
        String str;
        ClassLoader classLoader = zzra.class.getClassLoader();
        if (cls.equals(zzqp.class)) {
            str = zzbai;
        } else {
            if (!cls.getPackage().equals(zzra.class.getPackage())) {
                throw new IllegalArgumentException(cls.getName());
            }
            str = String.format("%s.BlazeGenerated%sLoader", cls.getPackage().getName(), cls.getSimpleName());
        }
        try {
            try {
                try {
                    try {
                        return cls.cast(((zzra) Class.forName(str, true, classLoader).getConstructor(new Class[0]).newInstance(new Object[0])).zzpb());
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
            Iterator it = ServiceLoader.load(zzra.class, classLoader).iterator();
            ArrayList arrayList = new ArrayList();
            while (it.hasNext()) {
                try {
                    arrayList.add(cls.cast(((zzra) it.next()).zzpb()));
                } catch (ServiceConfigurationError e13) {
                    Logger logger2 = logger;
                    Level level = Level.SEVERE;
                    String simpleName = cls.getSimpleName();
                    logger2.logp(level, "com.google.protobuf.GeneratedExtensionRegistryLoader", "load", simpleName.length() != 0 ? "Unable to load ".concat(simpleName) : new String("Unable to load "), (Throwable) e13);
                }
            }
            if (arrayList.size() == 1) {
                return (T) arrayList.get(0);
            }
            if (arrayList.size() == 0) {
                return null;
            }
            try {
                return (T) cls.getMethod("combine", Collection.class).invoke(null, arrayList);
            } catch (IllegalAccessException e14) {
                throw new IllegalStateException(e14);
            } catch (NoSuchMethodException e15) {
                throw new IllegalStateException(e15);
            } catch (InvocationTargetException e16) {
                throw new IllegalStateException(e16);
            }
        }
    }

    public abstract T zzpb();
}
