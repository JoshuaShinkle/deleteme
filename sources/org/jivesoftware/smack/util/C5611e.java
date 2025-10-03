package org.jivesoftware.smack.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/* renamed from: org.jivesoftware.smack.util.e */
/* loaded from: classes.dex */
public final class C5611e {

    /* renamed from: a */
    public static final Logger f19517a = Logger.getLogger(C5611e.class.getName());

    /* renamed from: a */
    public static boolean m22323a(String str, Set<String> set) throws Throwable {
        BufferedReader bufferedReader = null;
        InputStream inputStreamM22325c = m22325c(str, null);
        try {
            if (inputStreamM22325c == null) {
                return false;
            }
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStreamM22325c));
                while (true) {
                    try {
                        String line = bufferedReader2.readLine();
                        if (line == null) {
                            inputStreamM22325c.close();
                            bufferedReader2.close();
                            return true;
                        }
                        set.add(line);
                    } catch (IOException unused) {
                        throw new IOException("readLine() fail");
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        inputStreamM22325c.close();
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        throw th;
                    }
                }
            } catch (IOException unused2) {
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: b */
    public static List<ClassLoader> m22324b() {
        ClassLoader[] classLoaderArr = {C5611e.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        ArrayList arrayList = new ArrayList(2);
        for (int i9 = 0; i9 < 2; i9++) {
            ClassLoader classLoader = classLoaderArr[i9];
            if (classLoader != null) {
                arrayList.add(classLoader);
            }
        }
        return arrayList;
    }

    /* renamed from: c */
    public static InputStream m22325c(String str, ClassLoader classLoader) throws MalformedURLException {
        URI uriCreate = URI.create(str);
        if (uriCreate.getScheme() == null) {
            throw new MalformedURLException("No protocol found in file URL: " + str);
        }
        if (!uriCreate.getScheme().equals("classpath")) {
            return uriCreate.toURL().openStream();
        }
        List<ClassLoader> listM22324b = m22324b();
        if (classLoader != null) {
            listM22324b.add(0, classLoader);
        }
        Iterator<ClassLoader> it = listM22324b.iterator();
        while (it.hasNext()) {
            InputStream resourceAsStream = it.next().getResourceAsStream(uriCreate.getSchemeSpecificPart());
            if (resourceAsStream != null) {
                return resourceAsStream;
            }
        }
        return null;
    }
}
