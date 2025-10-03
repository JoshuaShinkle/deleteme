package p133m0;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;
import androidx.multidex.MultiDexExtractor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: m0.a */
/* loaded from: classes.dex */
public final class C5310a {

    /* renamed from: a */
    public static final Set<File> f18049a = new HashSet();

    /* renamed from: b */
    public static final boolean f18050b = m20782m(System.getProperty("java.vm.version"));

    /* renamed from: m0.a$a */
    public static final class a {
        /* renamed from: a */
        public static void m20784a(ClassLoader classLoader, List<? extends File> list, File file) throws IllegalAccessException, NoSuchFieldException, SecurityException, IOException, IllegalArgumentException {
            IOException[] iOExceptionArr;
            Object obj = C5310a.m20776g(classLoader, "pathList").get(classLoader);
            ArrayList arrayList = new ArrayList();
            C5310a.m20775f(obj, "dexElements", m20785b(obj, new ArrayList(list), file, arrayList));
            if (arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Log.w("MultiDex", "Exception in makeDexElement", (IOException) it.next());
                }
                Field fieldM20776g = C5310a.m20776g(obj, "dexElementsSuppressedExceptions");
                IOException[] iOExceptionArr2 = (IOException[]) fieldM20776g.get(obj);
                if (iOExceptionArr2 == null) {
                    iOExceptionArr = (IOException[]) arrayList.toArray(new IOException[arrayList.size()]);
                } else {
                    IOException[] iOExceptionArr3 = new IOException[arrayList.size() + iOExceptionArr2.length];
                    arrayList.toArray(iOExceptionArr3);
                    System.arraycopy(iOExceptionArr2, 0, iOExceptionArr3, arrayList.size(), iOExceptionArr2.length);
                    iOExceptionArr = iOExceptionArr3;
                }
                fieldM20776g.set(obj, iOExceptionArr);
                IOException iOException = new IOException("I/O exception during makeDexElement");
                iOException.initCause((Throwable) arrayList.get(0));
                throw iOException;
            }
        }

        /* renamed from: b */
        public static Object[] m20785b(Object obj, ArrayList<File> arrayList, File file, ArrayList<IOException> arrayList2) {
            return (Object[]) C5310a.m20777h(obj, "makeDexElements", ArrayList.class, File.class, ArrayList.class).invoke(obj, arrayList, file, arrayList2);
        }
    }

    /* renamed from: d */
    public static void m20773d(Context context) {
        File file = new File(context.getFilesDir(), "secondary-dexes");
        if (file.isDirectory()) {
            Log.i("MultiDex", "Clearing old secondary dex dir (" + file.getPath() + ").");
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                Log.w("MultiDex", "Failed to list secondary dex dir content (" + file.getPath() + ").");
                return;
            }
            for (File file2 : fileArrListFiles) {
                Log.i("MultiDex", "Trying to delete old file " + file2.getPath() + " of size " + file2.length());
                if (file2.delete()) {
                    Log.i("MultiDex", "Deleted old file " + file2.getPath());
                } else {
                    Log.w("MultiDex", "Failed to delete old file " + file2.getPath());
                }
            }
            if (file.delete()) {
                Log.i("MultiDex", "Deleted old secondary dex dir " + file.getPath());
                return;
            }
            Log.w("MultiDex", "Failed to delete secondary dex dir " + file.getPath());
        }
    }

    /* renamed from: e */
    public static void m20774e(Context context, File file, File file2, String str, String str2, boolean z8) {
        Set<File> set = f18049a;
        synchronized (set) {
            if (set.contains(file)) {
                return;
            }
            set.add(file);
            Log.w("MultiDex", "MultiDex is not guaranteed to work in SDK version " + Build.VERSION.SDK_INT + ": SDK version higher than 20 should be backed by runtime with built-in multidex capabilty but it's not the case here: java.vm.version=\"" + System.getProperty("java.vm.version") + "\"");
            try {
                ClassLoader classLoader = context.getClassLoader();
                if (classLoader == null) {
                    Log.e("MultiDex", "Context class loader is null. Must be running in test mode. Skip patching.");
                    return;
                }
                try {
                    m20773d(context);
                } catch (Throwable th) {
                    Log.w("MultiDex", "Something went wrong when trying to clear old MultiDex extraction, continuing without cleaning.", th);
                }
                File fileM20779j = m20779j(context, file2, str);
                MultiDexExtractor multiDexExtractor = new MultiDexExtractor(file, fileM20779j);
                try {
                    try {
                        m20781l(classLoader, fileM20779j, multiDexExtractor.m2205A(context, str2, false));
                    } catch (IOException e9) {
                        if (!z8) {
                            throw e9;
                        }
                        Log.w("MultiDex", "Failed to install extracted secondary dex files, retrying with forced extraction", e9);
                        m20781l(classLoader, fileM20779j, multiDexExtractor.m2205A(context, str2, true));
                    }
                    try {
                        e = null;
                    } catch (IOException e10) {
                        e = e10;
                    }
                    if (e != null) {
                        throw e;
                    }
                } finally {
                    try {
                        multiDexExtractor.close();
                    } catch (IOException unused) {
                    }
                }
            } catch (RuntimeException e11) {
                Log.w("MultiDex", "Failure while trying to obtain Context class loader. Must be running in test mode. Skip patching.", e11);
            }
        }
    }

    /* renamed from: f */
    public static void m20775f(Object obj, String str, Object[] objArr) throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        Field fieldM20776g = m20776g(obj, str);
        Object[] objArr2 = (Object[]) fieldM20776g.get(obj);
        Object[] objArr3 = (Object[]) Array.newInstance(objArr2.getClass().getComponentType(), objArr2.length + objArr.length);
        System.arraycopy(objArr2, 0, objArr3, 0, objArr2.length);
        System.arraycopy(objArr, 0, objArr3, objArr2.length, objArr.length);
        fieldM20776g.set(obj, objArr3);
    }

    /* renamed from: g */
    public static Field m20776g(Object obj, String str) throws NoSuchFieldException, SecurityException {
        for (Class<?> superclass = obj.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
            try {
                Field declaredField = superclass.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
            }
        }
        throw new NoSuchFieldException("Field " + str + " not found in " + obj.getClass());
    }

    /* renamed from: h */
    public static Method m20777h(Object obj, String str, Class<?>... clsArr) throws NoSuchMethodException, SecurityException {
        for (Class<?> superclass = obj.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
            try {
                Method declaredMethod = superclass.getDeclaredMethod(str, clsArr);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException unused) {
            }
        }
        throw new NoSuchMethodException("Method " + str + " with parameters " + Arrays.asList(clsArr) + " not found in " + obj.getClass());
    }

    /* renamed from: i */
    public static ApplicationInfo m20778i(Context context) {
        try {
            return context.getApplicationInfo();
        } catch (RuntimeException e9) {
            Log.w("MultiDex", "Failure while trying to obtain ApplicationInfo from Context. Must be running in test mode. Skip patching.", e9);
            return null;
        }
    }

    /* renamed from: j */
    public static File m20779j(Context context, File file, String str) throws IOException {
        File file2 = new File(file, "code_cache");
        try {
            m20783n(file2);
        } catch (IOException unused) {
            file2 = new File(context.getFilesDir(), "code_cache");
            m20783n(file2);
        }
        File file3 = new File(file2, str);
        m20783n(file3);
        return file3;
    }

    /* renamed from: k */
    public static void m20780k(Context context) {
        Log.i("MultiDex", "Installing application");
        if (f18050b) {
            Log.i("MultiDex", "VM has multidex support, MultiDex support library is disabled.");
            return;
        }
        try {
            ApplicationInfo applicationInfoM20778i = m20778i(context);
            if (applicationInfoM20778i == null) {
                Log.i("MultiDex", "No ApplicationInfo available, i.e. running on a test Context: MultiDex support library is disabled.");
            } else {
                m20774e(context, new File(applicationInfoM20778i.sourceDir), new File(applicationInfoM20778i.dataDir), "secondary-dexes", "", true);
                Log.i("MultiDex", "install done");
            }
        } catch (Exception e9) {
            Log.e("MultiDex", "MultiDex installation failure", e9);
            throw new RuntimeException("MultiDex installation failed (" + e9.getMessage() + ").");
        }
    }

    /* renamed from: l */
    public static void m20781l(ClassLoader classLoader, File file, List<? extends File> list) throws IllegalAccessException, NoSuchFieldException, SecurityException, IOException, IllegalArgumentException {
        if (list.isEmpty()) {
            return;
        }
        a.m20784a(classLoader, list, file);
    }

    /* renamed from: m */
    public static boolean m20782m(String str) throws NumberFormatException {
        boolean z8 = false;
        if (str != null) {
            Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(str);
            if (matcher.matches()) {
                try {
                    int i9 = Integer.parseInt(matcher.group(1));
                    int i10 = Integer.parseInt(matcher.group(2));
                    if (i9 > 2 || (i9 == 2 && i10 >= 1)) {
                        z8 = true;
                    }
                } catch (NumberFormatException unused) {
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("VM with version ");
        sb.append(str);
        sb.append(z8 ? " has multidex support" : " does not have multidex support");
        Log.i("MultiDex", sb.toString());
        return z8;
    }

    /* renamed from: n */
    public static void m20783n(File file) throws IOException {
        file.mkdir();
        if (file.isDirectory()) {
            return;
        }
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            Log.e("MultiDex", "Failed to create dir " + file.getPath() + ". Parent file is null.");
        } else {
            Log.e("MultiDex", "Failed to create dir " + file.getPath() + ". parent file is a dir " + parentFile.isDirectory() + ", a file " + parentFile.isFile() + ", exists " + parentFile.exists() + ", readable " + parentFile.canRead() + ", writable " + parentFile.canWrite());
        }
        throw new IOException("Failed to create directory " + file.getPath());
    }
}
