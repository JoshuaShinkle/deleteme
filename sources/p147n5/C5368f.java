package p147n5;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import p087h5.C5025c;
import p087h5.C5026d;

/* renamed from: n5.f */
/* loaded from: classes2.dex */
public final class C5368f {

    @SerializedName("timestamp")
    public final long timeMs;

    @SerializedName(C5370h.type)
    private final List<a> systemResource = Collections.emptyList();

    @SerializedName(C5371i.type)
    private final List<a> messageResource = Collections.emptyList();

    @SerializedName(C5366d.type)
    private final List<a> eventResource = Collections.emptyList();
    private final List<C5370h> system = new ArrayList();
    private final List<C5371i> message = new ArrayList();
    private final List<C5366d> event = new ArrayList();

    /* renamed from: n5.f$a */
    public static class a {
        public String body;

        /* renamed from: id */
        public String f18251id;
        public String liveId;
        public long timestamp;
        public String type;
    }

    private C5368f() {
        Long l9 = Long.MIN_VALUE;
        this.timeMs = l9.longValue();
    }

    public static C5368f fromJson(String str) throws InvalidObjectException {
        try {
            C5368f c5368f = (C5368f) C5369g.GSON.fromJson(str, C5368f.class);
            if (c5368f == null) {
                throw new InvalidObjectException("invalid JSON");
            }
            Iterator it = C5025c.m19596c(c5368f.systemResource).iterator();
            while (it.hasNext()) {
                c5368f.system.add((C5370h) C5369g.GSON.fromJson(((a) it.next()).body, C5370h.class));
            }
            Iterator it2 = C5025c.m19596c(c5368f.messageResource).iterator();
            while (it2.hasNext()) {
                a aVar = (a) it2.next();
                C5371i c5371i = (C5371i) C5369g.GSON.fromJson(aVar.body, C5371i.class);
                c5368f.message.add(new C5371i(c5371i.userId, c5371i.uuid, c5371i.name, c5371i.text, aVar.f18251id));
            }
            Iterator it3 = C5025c.m19596c(c5368f.eventResource).iterator();
            while (it3.hasNext()) {
                a aVar2 = (a) it3.next();
                C5366d c5366d = (C5366d) C5369g.GSON.fromJson(aVar2.body, C5366d.class);
                c5366d.f18250id = aVar2.f18251id;
                c5366d.timestamp = aVar2.timestamp;
                c5368f.event.add(c5366d);
            }
            return c5368f;
        } catch (JsonSyntaxException unused) {
            throw new InvalidObjectException("invalid JSON");
        }
    }

    public Iterable<? extends InterfaceC5367e> asIterable() {
        return C5025c.m19597d(C5025c.m19596c(this.system, this.message, this.event));
    }

    public <T extends InterfaceC5367e> List<T> get(Class<T> cls) {
        try {
            List list = (List) C5368f.class.getDeclaredField(C5369g.typeId(cls)).get(this);
            return list != null ? Collections.unmodifiableList(list) : Collections.emptyList();
        } catch (Throwable th) {
            throw C5026d.m19600a(th);
        }
    }

    public String toString() {
        return "\"MessagePack\":" + C5369g.GSON.toJson(this);
    }
}
