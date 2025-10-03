package com.google.android.gms.internal.measurement;

import p147n5.C5371i;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'zzc' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes2.dex */
public class zzle {
    public static final zzle zza;
    public static final zzle zzb;
    public static final zzle zzc;
    public static final zzle zzd;
    public static final zzle zze;
    public static final zzle zzf;
    public static final zzle zzg;
    public static final zzle zzh;
    public static final zzle zzi;
    public static final zzle zzj;
    public static final zzle zzk;
    public static final zzle zzl;
    public static final zzle zzm;
    public static final zzle zzn;
    public static final zzle zzo;
    public static final zzle zzp;
    public static final zzle zzq;
    public static final zzle zzr;
    private static final /* synthetic */ zzle[] zzu;
    private final zzlh zzs;
    private final int zzt;

    static {
        zzle zzleVar = new zzle("DOUBLE", 0, zzlh.DOUBLE, 1);
        zza = zzleVar;
        zzle zzleVar2 = new zzle("FLOAT", 1, zzlh.FLOAT, 5);
        zzb = zzleVar2;
        zzlh zzlhVar = zzlh.LONG;
        final int i9 = 2;
        zzle zzleVar3 = new zzle("INT64", 2, zzlhVar, 0);
        zzc = zzleVar3;
        final int i10 = 3;
        zzle zzleVar4 = new zzle("UINT64", 3, zzlhVar, 0);
        zzd = zzleVar4;
        zzlh zzlhVar2 = zzlh.INT;
        zzle zzleVar5 = new zzle("INT32", 4, zzlhVar2, 0);
        zze = zzleVar5;
        zzle zzleVar6 = new zzle("FIXED64", 5, zzlhVar, 1);
        zzf = zzleVar6;
        zzle zzleVar7 = new zzle("FIXED32", 6, zzlhVar2, 5);
        zzg = zzleVar7;
        zzle zzleVar8 = new zzle("BOOL", 7, zzlh.BOOLEAN, 0);
        zzh = zzleVar8;
        final zzlh zzlhVar3 = zzlh.STRING;
        final String str = "STRING";
        final int i11 = 8;
        zzle zzleVar9 = new zzle(str, i11, zzlhVar3, i9) { // from class: com.google.android.gms.internal.measurement.zzld
            {
                int i12 = 8;
                int i13 = 2;
                zzlb zzlbVar = null;
            }
        };
        zzi = zzleVar9;
        final zzlh zzlhVar4 = zzlh.MESSAGE;
        final String str2 = "GROUP";
        final int i12 = 9;
        zzle zzleVar10 = new zzle(str2, i12, zzlhVar4, i10) { // from class: com.google.android.gms.internal.measurement.zzlg
            {
                int i13 = 9;
                int i14 = 3;
                zzlb zzlbVar = null;
            }
        };
        zzj = zzleVar10;
        final String str3 = C5371i.type;
        final int i13 = 10;
        final int i14 = 2;
        zzle zzleVar11 = new zzle(str3, i13, zzlhVar4, i14) { // from class: com.google.android.gms.internal.measurement.zzlf
            {
                int i15 = 10;
                int i16 = 2;
                zzlb zzlbVar = null;
            }
        };
        zzk = zzleVar11;
        final zzlh zzlhVar5 = zzlh.BYTE_STRING;
        final String str4 = "BYTES";
        final int i15 = 11;
        zzle zzleVar12 = new zzle(str4, i15, zzlhVar5, i14) { // from class: com.google.android.gms.internal.measurement.zzli
            {
                int i16 = 11;
                int i17 = 2;
                zzlb zzlbVar = null;
            }
        };
        zzl = zzleVar12;
        zzle zzleVar13 = new zzle("UINT32", 12, zzlhVar2, 0);
        zzm = zzleVar13;
        zzle zzleVar14 = new zzle("ENUM", 13, zzlh.ENUM, 0);
        zzn = zzleVar14;
        zzle zzleVar15 = new zzle("SFIXED32", 14, zzlhVar2, 5);
        zzo = zzleVar15;
        zzle zzleVar16 = new zzle("SFIXED64", 15, zzlhVar, 1);
        zzp = zzleVar16;
        zzle zzleVar17 = new zzle("SINT32", 16, zzlhVar2, 0);
        zzq = zzleVar17;
        zzle zzleVar18 = new zzle("SINT64", 17, zzlhVar, 0);
        zzr = zzleVar18;
        zzu = new zzle[]{zzleVar, zzleVar2, zzleVar3, zzleVar4, zzleVar5, zzleVar6, zzleVar7, zzleVar8, zzleVar9, zzleVar10, zzleVar11, zzleVar12, zzleVar13, zzleVar14, zzleVar15, zzleVar16, zzleVar17, zzleVar18};
    }

    private zzle(String str, int i9, zzlh zzlhVar, int i10) {
        this.zzs = zzlhVar;
        this.zzt = i10;
    }

    public static zzle[] values() {
        return (zzle[]) zzu.clone();
    }

    public final zzlh zza() {
        return this.zzs;
    }

    public final int zzb() {
        return this.zzt;
    }

    public /* synthetic */ zzle(String str, int i9, zzlh zzlhVar, int i10, zzlb zzlbVar) {
        this(str, i9, zzlhVar, i10);
    }
}
