package p251z;

import android.os.LocaleList;
import java.util.Locale;

/* renamed from: z.c */
/* loaded from: classes.dex */
public final class C6797c {

    /* renamed from: b */
    public static final C6797c f22534b = m25351a(new Locale[0]);

    /* renamed from: a */
    public InterfaceC6798d f22535a;

    public C6797c(InterfaceC6798d interfaceC6798d) {
        this.f22535a = interfaceC6798d;
    }

    /* renamed from: a */
    public static C6797c m25351a(Locale... localeArr) {
        return m25352c(new LocaleList(localeArr));
    }

    /* renamed from: c */
    public static C6797c m25352c(LocaleList localeList) {
        return new C6797c(new C6799e(localeList));
    }

    /* renamed from: b */
    public Locale m25353b(int i9) {
        return this.f22535a.get(i9);
    }

    public boolean equals(Object obj) {
        return (obj instanceof C6797c) && this.f22535a.equals(((C6797c) obj).f22535a);
    }

    public int hashCode() {
        return this.f22535a.hashCode();
    }

    public String toString() {
        return this.f22535a.toString();
    }
}
