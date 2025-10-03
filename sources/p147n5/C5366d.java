package p147n5;

import java.util.ArrayList;
import java.util.Map;

/* renamed from: n5.d */
/* loaded from: classes2.dex */
public final class C5366d extends C5363a {
    public static final String type = "EVENT";
    public a attributes;
    public final String event;

    /* renamed from: id */
    public String f18250id;
    public long timestamp;

    /* renamed from: n5.d$a */
    public static class a {
        public int duration;
        public String featureName;
        public String messageId;
        public Integer onlineUsers;
        public ArrayList<String> quality;
        public Map<String, String> recommended;
        public Map<String, String> required;
        public String resumeTime;
        public String state;
        public String uid;
        public String userId;
        public String uuid;
    }

    private C5366d() {
        this(null, null);
    }

    public C5366d(String str, String str2) {
        this.event = str;
        this.f18250id = str2;
    }
}
