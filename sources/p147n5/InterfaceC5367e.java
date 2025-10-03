package p147n5;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import java.util.Set;

/* renamed from: n5.e */
/* loaded from: classes2.dex */
public interface InterfaceC5367e {
    public static final String NO_ID = "";
    public static final String NO_USER = "";
    public static final Set<Class<? extends InterfaceC5367e>> TYPES;
    public static final Set<String> TYPE_NAMES;

    /* renamed from: n5.e$a */
    public class a implements Function<Class<? extends InterfaceC5367e>, String> {
        @Override // com.google.common.base.Function
        public String apply(Class<? extends InterfaceC5367e> cls) {
            return C5369g.typeId(cls);
        }
    }

    static {
        ImmutableSet immutableSetM17618of = ImmutableSet.m17618of(C5370h.class, C5371i.class, C5366d.class);
        TYPES = immutableSetM17618of;
        TYPE_NAMES = FluentIterable.from(immutableSetM17618of).transform(new a()).toSet();
    }
}
