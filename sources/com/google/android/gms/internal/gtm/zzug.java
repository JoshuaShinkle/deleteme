package com.google.android.gms.internal.gtm;

import p147n5.C5371i;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'zzbfr' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes2.dex */
public class zzug {
    public static final zzug zzbfp;
    public static final zzug zzbfq;
    public static final zzug zzbfr;
    public static final zzug zzbfs;
    public static final zzug zzbft;
    public static final zzug zzbfu;
    public static final zzug zzbfv;
    public static final zzug zzbfw;
    public static final zzug zzbfx;
    public static final zzug zzbfy;
    public static final zzug zzbfz;
    public static final zzug zzbga;
    public static final zzug zzbgb;
    public static final zzug zzbgc;
    public static final zzug zzbgd;
    public static final zzug zzbge;
    public static final zzug zzbgf;
    public static final zzug zzbgg;
    private static final /* synthetic */ zzug[] zzbgj;
    private final zzul zzbgh;
    private final int zzbgi;

    static {
        zzug zzugVar = new zzug("DOUBLE", 0, zzul.DOUBLE, 1);
        zzbfp = zzugVar;
        zzug zzugVar2 = new zzug("FLOAT", 1, zzul.FLOAT, 5);
        zzbfq = zzugVar2;
        zzul zzulVar = zzul.LONG;
        final int i9 = 2;
        zzug zzugVar3 = new zzug("INT64", 2, zzulVar, 0);
        zzbfr = zzugVar3;
        final int i10 = 3;
        zzug zzugVar4 = new zzug("UINT64", 3, zzulVar, 0);
        zzbfs = zzugVar4;
        zzul zzulVar2 = zzul.INT;
        zzug zzugVar5 = new zzug("INT32", 4, zzulVar2, 0);
        zzbft = zzugVar5;
        zzug zzugVar6 = new zzug("FIXED64", 5, zzulVar, 1);
        zzbfu = zzugVar6;
        zzug zzugVar7 = new zzug("FIXED32", 6, zzulVar2, 5);
        zzbfv = zzugVar7;
        zzug zzugVar8 = new zzug("BOOL", 7, zzul.BOOLEAN, 0);
        zzbfw = zzugVar8;
        final zzul zzulVar3 = zzul.STRING;
        final String str = "STRING";
        final int i11 = 8;
        zzug zzugVar9 = new zzug(str, i11, zzulVar3, i9) { // from class: com.google.android.gms.internal.gtm.zzuh
            {
                int i12 = 8;
                int i13 = 2;
                zzuf zzufVar = null;
            }
        };
        zzbfx = zzugVar9;
        final zzul zzulVar4 = zzul.MESSAGE;
        final String str2 = "GROUP";
        final int i12 = 9;
        zzug zzugVar10 = new zzug(str2, i12, zzulVar4, i10) { // from class: com.google.android.gms.internal.gtm.zzui
            {
                int i13 = 9;
                int i14 = 3;
                zzuf zzufVar = null;
            }
        };
        zzbfy = zzugVar10;
        final String str3 = C5371i.type;
        final int i13 = 10;
        final int i14 = 2;
        zzug zzugVar11 = new zzug(str3, i13, zzulVar4, i14) { // from class: com.google.android.gms.internal.gtm.zzuj
            {
                int i15 = 10;
                int i16 = 2;
                zzuf zzufVar = null;
            }
        };
        zzbfz = zzugVar11;
        final zzul zzulVar5 = zzul.BYTE_STRING;
        final String str4 = "BYTES";
        final int i15 = 11;
        zzug zzugVar12 = new zzug(str4, i15, zzulVar5, i14) { // from class: com.google.android.gms.internal.gtm.zzuk
            {
                int i16 = 11;
                int i17 = 2;
                zzuf zzufVar = null;
            }
        };
        zzbga = zzugVar12;
        zzug zzugVar13 = new zzug("UINT32", 12, zzulVar2, 0);
        zzbgb = zzugVar13;
        zzug zzugVar14 = new zzug("ENUM", 13, zzul.ENUM, 0);
        zzbgc = zzugVar14;
        zzug zzugVar15 = new zzug("SFIXED32", 14, zzulVar2, 5);
        zzbgd = zzugVar15;
        zzug zzugVar16 = new zzug("SFIXED64", 15, zzulVar, 1);
        zzbge = zzugVar16;
        zzug zzugVar17 = new zzug("SINT32", 16, zzulVar2, 0);
        zzbgf = zzugVar17;
        zzug zzugVar18 = new zzug("SINT64", 17, zzulVar, 0);
        zzbgg = zzugVar18;
        zzbgj = new zzug[]{zzugVar, zzugVar2, zzugVar3, zzugVar4, zzugVar5, zzugVar6, zzugVar7, zzugVar8, zzugVar9, zzugVar10, zzugVar11, zzugVar12, zzugVar13, zzugVar14, zzugVar15, zzugVar16, zzugVar17, zzugVar18};
    }

    private zzug(String str, int i9, zzul zzulVar, int i10) {
        this.zzbgh = zzulVar;
        this.zzbgi = i10;
    }

    public static zzug[] values() {
        return (zzug[]) zzbgj.clone();
    }

    public final zzul zzrs() {
        return this.zzbgh;
    }

    public final int zzrt() {
        return this.zzbgi;
    }

    public /* synthetic */ zzug(String str, int i9, zzul zzulVar, int i10, zzuf zzufVar) {
        this(str, i9, zzulVar, i10);
    }
}
