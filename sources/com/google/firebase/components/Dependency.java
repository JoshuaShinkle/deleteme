package com.google.firebase.components;

/* loaded from: classes2.dex */
public final class Dependency {
    private final Qualified<?> anInterface;
    private final int injection;
    private final int type;

    private Dependency(Class<?> cls, int i9, int i10) {
        this((Qualified<?>) Qualified.unqualified(cls), i9, i10);
    }

    public static Dependency deferred(Class<?> cls) {
        return new Dependency(cls, 0, 2);
    }

    private static String describeInjection(int i9) {
        if (i9 == 0) {
            return "direct";
        }
        if (i9 == 1) {
            return "provider";
        }
        if (i9 == 2) {
            return "deferred";
        }
        throw new AssertionError("Unsupported injection: " + i9);
    }

    @Deprecated
    public static Dependency optional(Class<?> cls) {
        return new Dependency(cls, 0, 0);
    }

    public static Dependency optionalProvider(Class<?> cls) {
        return new Dependency(cls, 0, 1);
    }

    public static Dependency required(Class<?> cls) {
        return new Dependency(cls, 1, 0);
    }

    public static Dependency requiredProvider(Class<?> cls) {
        return new Dependency(cls, 1, 1);
    }

    public static Dependency setOf(Class<?> cls) {
        return new Dependency(cls, 2, 0);
    }

    public static Dependency setOfProvider(Class<?> cls) {
        return new Dependency(cls, 2, 1);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Dependency)) {
            return false;
        }
        Dependency dependency = (Dependency) obj;
        return this.anInterface.equals(dependency.anInterface) && this.type == dependency.type && this.injection == dependency.injection;
    }

    public Qualified<?> getInterface() {
        return this.anInterface;
    }

    public int hashCode() {
        return ((((this.anInterface.hashCode() ^ 1000003) * 1000003) ^ this.type) * 1000003) ^ this.injection;
    }

    public boolean isDeferred() {
        return this.injection == 2;
    }

    public boolean isDirectInjection() {
        return this.injection == 0;
    }

    public boolean isRequired() {
        return this.type == 1;
    }

    public boolean isSet() {
        return this.type == 2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Dependency{anInterface=");
        sb.append(this.anInterface);
        sb.append(", type=");
        int i9 = this.type;
        sb.append(i9 == 1 ? "required" : i9 == 0 ? "optional" : "set");
        sb.append(", injection=");
        sb.append(describeInjection(this.injection));
        sb.append("}");
        return sb.toString();
    }

    private Dependency(Qualified<?> qualified, int i9, int i10) {
        this.anInterface = (Qualified) Preconditions.checkNotNull(qualified, "Null dependency anInterface.");
        this.type = i9;
        this.injection = i10;
    }

    public static Dependency deferred(Qualified<?> qualified) {
        return new Dependency(qualified, 0, 2);
    }

    public static Dependency optionalProvider(Qualified<?> qualified) {
        return new Dependency(qualified, 0, 1);
    }

    public static Dependency required(Qualified<?> qualified) {
        return new Dependency(qualified, 1, 0);
    }

    public static Dependency requiredProvider(Qualified<?> qualified) {
        return new Dependency(qualified, 1, 1);
    }

    public static Dependency setOf(Qualified<?> qualified) {
        return new Dependency(qualified, 2, 0);
    }

    public static Dependency setOfProvider(Qualified<?> qualified) {
        return new Dependency(qualified, 2, 1);
    }
}
