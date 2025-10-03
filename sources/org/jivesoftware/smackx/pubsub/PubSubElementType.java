package org.jivesoftware.smackx.pubsub;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Locale;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'b' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes.dex */
public final class PubSubElementType {

    /* renamed from: b */
    public static final PubSubElementType f19844b;

    /* renamed from: c */
    public static final PubSubElementType f19845c;

    /* renamed from: d */
    public static final PubSubElementType f19846d;

    /* renamed from: e */
    public static final PubSubElementType f19847e;

    /* renamed from: f */
    public static final PubSubElementType f19848f;

    /* renamed from: g */
    public static final PubSubElementType f19849g;

    /* renamed from: h */
    public static final PubSubElementType f19850h;

    /* renamed from: i */
    public static final PubSubElementType f19851i;

    /* renamed from: j */
    public static final PubSubElementType f19852j;

    /* renamed from: k */
    public static final PubSubElementType f19853k;

    /* renamed from: l */
    public static final PubSubElementType f19854l;

    /* renamed from: m */
    public static final PubSubElementType f19855m;

    /* renamed from: n */
    public static final PubSubElementType f19856n;

    /* renamed from: o */
    public static final PubSubElementType f19857o;

    /* renamed from: p */
    public static final PubSubElementType f19858p;

    /* renamed from: q */
    public static final PubSubElementType f19859q;

    /* renamed from: r */
    public static final PubSubElementType f19860r;

    /* renamed from: s */
    public static final PubSubElementType f19861s;

    /* renamed from: t */
    public static final PubSubElementType f19862t;

    /* renamed from: u */
    public static final PubSubElementType f19863u;

    /* renamed from: v */
    public static final PubSubElementType f19864v;

    /* renamed from: w */
    public static final PubSubElementType f19865w;

    /* renamed from: x */
    public static final /* synthetic */ PubSubElementType[] f19866x;
    private String eName;
    private PubSubNamespace nSpace;

    static {
        PubSubNamespace pubSubNamespace = PubSubNamespace.BASIC;
        PubSubElementType pubSubElementType = new PubSubElementType("CREATE", 0, "create", pubSubNamespace);
        f19844b = pubSubElementType;
        PubSubNamespace pubSubNamespace2 = PubSubNamespace.OWNER;
        PubSubElementType pubSubElementType2 = new PubSubElementType("DELETE", 1, "delete", pubSubNamespace2);
        f19845c = pubSubElementType2;
        PubSubNamespace pubSubNamespace3 = PubSubNamespace.EVENT;
        PubSubElementType pubSubElementType3 = new PubSubElementType("DELETE_EVENT", 2, "delete", pubSubNamespace3);
        f19846d = pubSubElementType3;
        PubSubElementType pubSubElementType4 = new PubSubElementType("CONFIGURE", 3, "configure", pubSubNamespace);
        f19847e = pubSubElementType4;
        PubSubElementType pubSubElementType5 = new PubSubElementType("CONFIGURE_OWNER", 4, "configure", pubSubNamespace2);
        f19848f = pubSubElementType5;
        PubSubElementType pubSubElementType6 = new PubSubElementType("CONFIGURATION", 5, "configuration", pubSubNamespace3);
        f19849g = pubSubElementType6;
        PubSubElementType pubSubElementType7 = new PubSubElementType("OPTIONS", 6, "options", pubSubNamespace);
        f19850h = pubSubElementType7;
        PubSubElementType pubSubElementType8 = new PubSubElementType("DEFAULT", 7, "default", pubSubNamespace2);
        f19851i = pubSubElementType8;
        PubSubElementType pubSubElementType9 = new PubSubElementType("ITEMS", 8, FirebaseAnalytics.Param.ITEMS, pubSubNamespace);
        f19852j = pubSubElementType9;
        PubSubElementType pubSubElementType10 = new PubSubElementType("ITEMS_EVENT", 9, FirebaseAnalytics.Param.ITEMS, pubSubNamespace3);
        f19853k = pubSubElementType10;
        PubSubElementType pubSubElementType11 = new PubSubElementType("ITEM", 10, "item", pubSubNamespace);
        f19854l = pubSubElementType11;
        PubSubElementType pubSubElementType12 = new PubSubElementType("ITEM_EVENT", 11, "item", pubSubNamespace3);
        f19855m = pubSubElementType12;
        PubSubElementType pubSubElementType13 = new PubSubElementType("PUBLISH", 12, "publish", pubSubNamespace);
        f19856n = pubSubElementType13;
        PubSubElementType pubSubElementType14 = new PubSubElementType("PUBLISH_OPTIONS", 13, "publish-options", pubSubNamespace);
        f19857o = pubSubElementType14;
        PubSubElementType pubSubElementType15 = new PubSubElementType("PURGE_OWNER", 14, "purge", pubSubNamespace2);
        f19858p = pubSubElementType15;
        PubSubElementType pubSubElementType16 = new PubSubElementType("PURGE_EVENT", 15, "purge", pubSubNamespace3);
        f19859q = pubSubElementType16;
        PubSubElementType pubSubElementType17 = new PubSubElementType("RETRACT", 16, "retract", pubSubNamespace);
        f19860r = pubSubElementType17;
        PubSubElementType pubSubElementType18 = new PubSubElementType("AFFILIATIONS", 17, "affiliations", pubSubNamespace);
        f19861s = pubSubElementType18;
        PubSubElementType pubSubElementType19 = new PubSubElementType("SUBSCRIBE", 18, "subscribe", pubSubNamespace);
        f19862t = pubSubElementType19;
        PubSubElementType pubSubElementType20 = new PubSubElementType("SUBSCRIPTION", 19, "subscription", pubSubNamespace);
        f19863u = pubSubElementType20;
        PubSubElementType pubSubElementType21 = new PubSubElementType("SUBSCRIPTIONS", 20, "subscriptions", pubSubNamespace);
        f19864v = pubSubElementType21;
        PubSubElementType pubSubElementType22 = new PubSubElementType("UNSUBSCRIBE", 21, "unsubscribe", pubSubNamespace);
        f19865w = pubSubElementType22;
        f19866x = new PubSubElementType[]{pubSubElementType, pubSubElementType2, pubSubElementType3, pubSubElementType4, pubSubElementType5, pubSubElementType6, pubSubElementType7, pubSubElementType8, pubSubElementType9, pubSubElementType10, pubSubElementType11, pubSubElementType12, pubSubElementType13, pubSubElementType14, pubSubElementType15, pubSubElementType16, pubSubElementType17, pubSubElementType18, pubSubElementType19, pubSubElementType20, pubSubElementType21, pubSubElementType22};
    }

    public PubSubElementType(String str, int i9, String str2, PubSubNamespace pubSubNamespace) {
        this.eName = str2;
        this.nSpace = pubSubNamespace;
    }

    /* renamed from: c */
    public static PubSubElementType m22685c(String str, String str2) {
        int iLastIndexOf = str2.lastIndexOf(35);
        String strSubstring = iLastIndexOf == -1 ? null : str2.substring(iLastIndexOf + 1);
        if (strSubstring == null) {
            return valueOf(str.toUpperCase(Locale.US).replace('-', '_'));
        }
        return valueOf((str + '_' + strSubstring).toUpperCase(Locale.US));
    }

    public static PubSubElementType valueOf(String str) {
        return (PubSubElementType) Enum.valueOf(PubSubElementType.class, str);
    }

    public static PubSubElementType[] values() {
        return (PubSubElementType[]) f19866x.clone();
    }

    /* renamed from: a */
    public String m22686a() {
        return this.eName;
    }

    /* renamed from: b */
    public PubSubNamespace m22687b() {
        return this.nSpace;
    }
}
