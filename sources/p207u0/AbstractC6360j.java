package p207u0;

import p207u0.AbstractC6360j;
import p208u1.C6361a;
import p208u1.InterfaceC6363c;

/* renamed from: u0.j */
/* loaded from: classes.dex */
public abstract class AbstractC6360j<CHILD extends AbstractC6360j<CHILD, TranscodeType>, TranscodeType> implements Cloneable {

    /* renamed from: b */
    public InterfaceC6363c<? super TranscodeType> f21499b = C6361a.m24444b();

    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final CHILD clone() {
        try {
            return (CHILD) super.clone();
        } catch (CloneNotSupportedException e9) {
            throw new RuntimeException(e9);
        }
    }

    /* renamed from: b */
    public final InterfaceC6363c<? super TranscodeType> m24443b() {
        return this.f21499b;
    }
}
