package kotlin.jvm.internal;

import java.io.Serializable;
import p007a6.C0042f;
import p007a6.C0043g;
import p007a6.InterfaceC0041e;

/* loaded from: classes2.dex */
public abstract class Lambda<R> implements InterfaceC0041e<R>, Serializable {
    private final int arity;

    public Lambda(int i9) {
        this.arity = i9;
    }

    public String toString() {
        String strM168a = C0043g.m168a(this);
        C0042f.m157d(strM168a, "renderLambdaToString(...)");
        return strM168a;
    }
}
