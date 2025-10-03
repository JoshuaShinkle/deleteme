package org.jivesoftware.smack.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import p028c7.C0751b;
import p028c7.C0752c;
import p028c7.InterfaceC0750a;

/* renamed from: org.jivesoftware.smack.util.d */
/* loaded from: classes.dex */
public class C5610d {

    /* renamed from: a */
    public static final Logger f19515a = Logger.getLogger(C5610d.class.getName());

    /* renamed from: b */
    public static InterfaceC0750a f19516b = null;

    /* renamed from: a */
    public static int m22317a(int[] iArr, double d9) {
        int length = iArr.length;
        int i9 = 0;
        for (int i10 = 0; i10 < length && d9 >= iArr[i10]; i10++) {
            i9++;
        }
        return i9;
    }

    /* renamed from: b */
    public static void m22318b() {
        InterfaceC0750a interfaceC0750a;
        String[] strArr = {"javax.JavaxResolver", "minidns.MiniDnsResolver", "dnsjava.DNSJavaResolver"};
        for (int i9 = 0; i9 < 3; i9++) {
            try {
                interfaceC0750a = (InterfaceC0750a) Class.forName("org.jivesoftware.smack.util.dns" + strArr[i9]).getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            } catch (ClassNotFoundException e9) {
                f19515a.log(Level.FINE, "Exception on init", (Throwable) e9);
            } catch (IllegalAccessException e10) {
                f19515a.log(Level.FINE, "Exception on init", (Throwable) e10);
            } catch (IllegalArgumentException e11) {
                f19515a.log(Level.FINE, "Exception on init", (Throwable) e11);
            } catch (NoSuchMethodException e12) {
                f19515a.log(Level.FINE, "Exception on init", (Throwable) e12);
            } catch (SecurityException e13) {
                f19515a.log(Level.FINE, "Exception on init", (Throwable) e13);
            } catch (InvocationTargetException e14) {
                f19515a.log(Level.FINE, "Exception on init", (Throwable) e14);
            }
            if (interfaceC0750a != null) {
                m22321e(interfaceC0750a);
                return;
            }
            continue;
        }
    }

    /* renamed from: c */
    public static List<C0751b> m22319c(String str, char c9) {
        String str2;
        ArrayList arrayList = new ArrayList();
        if (c9 == 's') {
            str2 = "_xmpp-server._tcp." + str;
        } else if (c9 == 'c') {
            str2 = "_xmpp-client._tcp." + str;
        } else {
            str2 = str;
        }
        List<C0752c> listM3627a = f19516b.m3627a(str2);
        if (f19515a.isLoggable(Level.FINE)) {
            String str3 = "Resolved SRV RR for " + str2 + ":";
            Iterator<C0752c> it = listM3627a.iterator();
            while (it.hasNext()) {
                str3 = str3 + StringUtils.SPACE + it.next();
            }
            f19515a.fine(str3);
        }
        arrayList.addAll(m22322f(listM3627a));
        arrayList.add(new C0751b(str));
        return arrayList;
    }

    /* renamed from: d */
    public static List<C0751b> m22320d(String str) {
        if (f19516b != null) {
            return m22319c(str, 'c');
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new C0751b(str, 5222));
        return arrayList;
    }

    /* renamed from: e */
    public static void m22321e(InterfaceC0750a interfaceC0750a) {
        f19516b = interfaceC0750a;
    }

    /* renamed from: f */
    public static List<C0751b> m22322f(List<C0752c> list) {
        if (list.size() == 1 && list.get(0).m3629b().equals(".")) {
            return Collections.emptyList();
        }
        Collections.sort(list);
        TreeMap treeMap = new TreeMap();
        for (C0752c c0752c : list) {
            Integer numValueOf = Integer.valueOf(c0752c.m3633f());
            List linkedList = (List) treeMap.get(numValueOf);
            if (linkedList == null) {
                linkedList = new LinkedList();
                treeMap.put(numValueOf, linkedList);
            }
            linkedList.add(c0752c);
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = treeMap.keySet().iterator();
        while (it.hasNext()) {
            List list2 = (List) treeMap.get((Integer) it.next());
            while (true) {
                int size = list2.size();
                if (size > 0) {
                    int[] iArr = new int[list2.size()];
                    Iterator it2 = list2.iterator();
                    int i9 = 1;
                    while (it2.hasNext()) {
                        if (((C0752c) it2.next()).m3634g() > 0) {
                            i9 = 0;
                        }
                    }
                    Iterator it3 = list2.iterator();
                    int iM3634g = 0;
                    int i10 = 0;
                    while (it3.hasNext()) {
                        iM3634g += ((C0752c) it3.next()).m3634g() + i9;
                        iArr[i10] = iM3634g;
                        i10++;
                    }
                    arrayList.add((C0752c) list2.remove(iM3634g == 0 ? (int) (Math.random() * size) : m22317a(iArr, Math.random() * iM3634g)));
                }
            }
        }
        return arrayList;
    }
}
