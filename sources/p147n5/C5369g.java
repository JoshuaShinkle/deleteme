package p147n5;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Set;
import p087h5.C5026d;

/* renamed from: n5.g */
/* loaded from: classes2.dex */
public final class C5369g {
    public static final Gson GSON = new GsonBuilder().create();

    private C5369g() {
    }

    public static void checkMessageType(Class<? extends InterfaceC5367e> cls) {
        Set<Class<? extends InterfaceC5367e>> set = InterfaceC5367e.TYPES;
        if (set.contains(cls)) {
            return;
        }
        throw new IllegalArgumentException(cls + " is invalid message type. Must be one of " + set);
    }

    public static String typeId(Class<? extends InterfaceC5367e> cls) {
        try {
            return (String) cls.getDeclaredField("type").get(null);
        } catch (Throwable th) {
            throw C5026d.m19600a(th);
        }
    }
}
