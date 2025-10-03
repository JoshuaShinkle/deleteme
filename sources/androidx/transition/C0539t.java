package androidx.transition;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* renamed from: androidx.transition.t */
/* loaded from: classes.dex */
public class C0539t {

    /* renamed from: b */
    public View f2966b;

    /* renamed from: a */
    public final Map<String, Object> f2965a = new HashMap();

    /* renamed from: c */
    public final ArrayList<AbstractC0532m> f2967c = new ArrayList<>();

    public boolean equals(Object obj) {
        if (!(obj instanceof C0539t)) {
            return false;
        }
        C0539t c0539t = (C0539t) obj;
        return this.f2966b == c0539t.f2966b && this.f2965a.equals(c0539t.f2965a);
    }

    public int hashCode() {
        return (this.f2966b.hashCode() * 31) + this.f2965a.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.f2966b + "\n") + "    values:";
        for (String str2 : this.f2965a.keySet()) {
            str = str + "    " + str2 + ": " + this.f2965a.get(str2) + "\n";
        }
        return str;
    }
}
