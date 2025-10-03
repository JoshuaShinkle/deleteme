package org.xbill.DNS;

import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;

/* loaded from: classes3.dex */
final class TypeBitmap implements Serializable {
    private static final long serialVersionUID = -125354057735389003L;
    private TreeSet types;

    /* renamed from: b */
    public static void m23226b(C5858d c5858d, TreeSet treeSet, int i9) {
        int iIntValue = ((((Integer) treeSet.last()).intValue() & 255) / 8) + 1;
        int[] iArr = new int[iIntValue];
        c5858d.m23243k(i9);
        c5858d.m23243k(iIntValue);
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            int iIntValue2 = ((Integer) it.next()).intValue();
            int i10 = (iIntValue2 & 255) / 8;
            iArr[i10] = (1 << (7 - (iIntValue2 % 8))) | iArr[i10];
        }
        for (int i11 = 0; i11 < iIntValue; i11++) {
            c5858d.m23243k(iArr[i11]);
        }
    }

    /* renamed from: a */
    public boolean m23227a() {
        return this.types.isEmpty();
    }

    /* renamed from: c */
    public void m23228c(C5858d c5858d) {
        if (this.types.size() == 0) {
            return;
        }
        TreeSet treeSet = new TreeSet();
        Iterator it = this.types.iterator();
        int i9 = -1;
        while (it.hasNext()) {
            int iIntValue = ((Integer) it.next()).intValue();
            int i10 = iIntValue >> 8;
            if (i10 != i9) {
                if (treeSet.size() > 0) {
                    m23226b(c5858d, treeSet, i9);
                    treeSet.clear();
                }
                i9 = i10;
            }
            treeSet.add(new Integer(iIntValue));
        }
        m23226b(c5858d, treeSet, i9);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = this.types.iterator();
        while (it.hasNext()) {
            stringBuffer.append(C5865k.m23267b(((Integer) it.next()).intValue()));
            if (it.hasNext()) {
                stringBuffer.append(' ');
            }
        }
        return stringBuffer.toString();
    }
}
