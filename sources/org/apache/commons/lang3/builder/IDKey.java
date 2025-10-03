package org.apache.commons.lang3.builder;

/* loaded from: classes.dex */
final class IDKey {

    /* renamed from: id */
    private final int f19109id;
    private final Object value;

    public IDKey(Object obj) {
        this.f19109id = System.identityHashCode(obj);
        this.value = obj;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IDKey)) {
            return false;
        }
        IDKey iDKey = (IDKey) obj;
        return this.f19109id == iDKey.f19109id && this.value == iDKey.value;
    }

    public int hashCode() {
        return this.f19109id;
    }
}
