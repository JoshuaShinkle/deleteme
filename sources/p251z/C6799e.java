package p251z;

import android.os.LocaleList;
import java.util.Locale;

/* renamed from: z.e */
/* loaded from: classes.dex */
public final class C6799e implements InterfaceC6798d {

    /* renamed from: a */
    public final LocaleList f22536a;

    public C6799e(LocaleList localeList) {
        this.f22536a = localeList;
    }

    @Override // p251z.InterfaceC6798d
    /* renamed from: a */
    public Object mo25354a() {
        return this.f22536a;
    }

    public boolean equals(Object obj) {
        return this.f22536a.equals(((InterfaceC6798d) obj).mo25354a());
    }

    @Override // p251z.InterfaceC6798d
    public Locale get(int i9) {
        return this.f22536a.get(i9);
    }

    public int hashCode() {
        return this.f22536a.hashCode();
    }

    public String toString() {
        return this.f22536a.toString();
    }
}
