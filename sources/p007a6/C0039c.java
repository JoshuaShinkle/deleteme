package p007a6;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: a6.c */
/* loaded from: classes2.dex */
public final class C0039c {

    /* renamed from: a */
    public static final Object[] f135a = new Object[0];

    /* renamed from: a */
    public static final Object[] m152a(Collection<?> collection) {
        C0042f.m158e(collection, "collection");
        int size = collection.size();
        if (size != 0) {
            Iterator<?> it = collection.iterator();
            if (it.hasNext()) {
                Object[] objArrCopyOf = new Object[size];
                int i9 = 0;
                while (true) {
                    int i10 = i9 + 1;
                    objArrCopyOf[i9] = it.next();
                    if (i10 >= objArrCopyOf.length) {
                        if (!it.hasNext()) {
                            return objArrCopyOf;
                        }
                        int i11 = ((i10 * 3) + 1) >>> 1;
                        if (i11 <= i10) {
                            i11 = 2147483645;
                            if (i10 >= 2147483645) {
                                throw new OutOfMemoryError();
                            }
                        }
                        objArrCopyOf = Arrays.copyOf(objArrCopyOf, i11);
                        C0042f.m157d(objArrCopyOf, "copyOf(...)");
                    } else if (!it.hasNext()) {
                        Object[] objArrCopyOf2 = Arrays.copyOf(objArrCopyOf, i10);
                        C0042f.m157d(objArrCopyOf2, "copyOf(...)");
                        return objArrCopyOf2;
                    }
                    i9 = i10;
                }
            }
        }
        return f135a;
    }

    /* renamed from: b */
    public static final Object[] m153b(Collection<?> collection, Object[] objArr) throws NegativeArraySizeException {
        Object[] objArrCopyOf;
        C0042f.m158e(collection, "collection");
        objArr.getClass();
        int size = collection.size();
        int i9 = 0;
        if (size == 0) {
            if (objArr.length <= 0) {
                return objArr;
            }
            objArr[0] = null;
            return objArr;
        }
        Iterator<?> it = collection.iterator();
        if (!it.hasNext()) {
            if (objArr.length <= 0) {
                return objArr;
            }
            objArr[0] = null;
            return objArr;
        }
        if (size <= objArr.length) {
            objArrCopyOf = objArr;
        } else {
            Object objNewInstance = Array.newInstance(objArr.getClass().getComponentType(), size);
            C0042f.m156c(objNewInstance, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArrCopyOf = (Object[]) objNewInstance;
        }
        while (true) {
            int i10 = i9 + 1;
            objArrCopyOf[i9] = it.next();
            if (i10 >= objArrCopyOf.length) {
                if (!it.hasNext()) {
                    return objArrCopyOf;
                }
                int i11 = ((i10 * 3) + 1) >>> 1;
                if (i11 <= i10) {
                    i11 = 2147483645;
                    if (i10 >= 2147483645) {
                        throw new OutOfMemoryError();
                    }
                }
                objArrCopyOf = Arrays.copyOf(objArrCopyOf, i11);
                C0042f.m157d(objArrCopyOf, "copyOf(...)");
            } else if (!it.hasNext()) {
                if (objArrCopyOf == objArr) {
                    objArr[i10] = null;
                    return objArr;
                }
                Object[] objArrCopyOf2 = Arrays.copyOf(objArrCopyOf, i10);
                C0042f.m157d(objArrCopyOf2, "copyOf(...)");
                return objArrCopyOf2;
            }
            i9 = i10;
        }
    }
}
