package com.google.android.gms.internal.measurement;

import java.lang.reflect.Type;
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
public final class zzhp {
    public static final zzhp zza;
    private static final zzhp zzaa;
    private static final zzhp zzab;
    private static final zzhp zzac;
    private static final zzhp zzad;
    private static final zzhp zzae;
    private static final zzhp zzaf;
    private static final zzhp zzag;
    private static final zzhp zzah;
    private static final zzhp zzai;
    private static final zzhp zzaj;
    private static final zzhp zzak;
    private static final zzhp zzal;
    private static final zzhp zzam;
    private static final zzhp zzan;
    private static final zzhp zzao;
    private static final zzhp zzap;
    private static final zzhp zzaq;
    private static final zzhp zzar;
    private static final zzhp zzas;
    private static final zzhp zzat;
    private static final zzhp zzau;
    private static final zzhp zzav;
    private static final zzhp zzaw;
    private static final zzhp zzax;
    private static final zzhp zzay;
    public static final zzhp zzb;
    private static final zzhp[] zzbe;
    private static final Type[] zzbf;
    private static final /* synthetic */ zzhp[] zzbg;
    private static final zzhp zzc;
    private static final zzhp zzd;
    private static final zzhp zze;
    private static final zzhp zzf;
    private static final zzhp zzg;
    private static final zzhp zzh;
    private static final zzhp zzi;
    private static final zzhp zzj;
    private static final zzhp zzk;
    private static final zzhp zzl;
    private static final zzhp zzm;
    private static final zzhp zzn;
    private static final zzhp zzo;
    private static final zzhp zzp;
    private static final zzhp zzq;
    private static final zzhp zzr;
    private static final zzhp zzs;
    private static final zzhp zzt;
    private static final zzhp zzu;
    private static final zzhp zzv;
    private static final zzhp zzw;
    private static final zzhp zzx;
    private static final zzhp zzy;
    private static final zzhp zzz;
    private final zzii zzaz;
    private final int zzba;
    private final zzhr zzbb;
    private final Class<?> zzbc;
    private final boolean zzbd;

    static {
        zzhr zzhrVar = zzhr.SCALAR;
        zzii zziiVar = zzii.zze;
        zzhp zzhpVar = new zzhp("DOUBLE", 0, 0, zzhrVar, zziiVar);
        zzc = zzhpVar;
        zzii zziiVar2 = zzii.zzd;
        zzhp zzhpVar2 = new zzhp("FLOAT", 1, 1, zzhrVar, zziiVar2);
        zzd = zzhpVar2;
        zzii zziiVar3 = zzii.zzc;
        zzhp zzhpVar3 = new zzhp("INT64", 2, 2, zzhrVar, zziiVar3);
        zze = zzhpVar3;
        zzhp zzhpVar4 = new zzhp("UINT64", 3, 3, zzhrVar, zziiVar3);
        zzf = zzhpVar4;
        zzii zziiVar4 = zzii.zzb;
        zzhp zzhpVar5 = new zzhp("INT32", 4, 4, zzhrVar, zziiVar4);
        zzg = zzhpVar5;
        zzhp zzhpVar6 = new zzhp("FIXED64", 5, 5, zzhrVar, zziiVar3);
        zzh = zzhpVar6;
        zzhp zzhpVar7 = new zzhp("FIXED32", 6, 6, zzhrVar, zziiVar4);
        zzi = zzhpVar7;
        zzii zziiVar5 = zzii.zzf;
        zzhp zzhpVar8 = new zzhp("BOOL", 7, 7, zzhrVar, zziiVar5);
        zzj = zzhpVar8;
        zzii zziiVar6 = zzii.zzg;
        zzhp zzhpVar9 = new zzhp("STRING", 8, 8, zzhrVar, zziiVar6);
        zzk = zzhpVar9;
        zzii zziiVar7 = zzii.zzj;
        zzhp zzhpVar10 = new zzhp(C5371i.type, 9, 9, zzhrVar, zziiVar7);
        zzl = zzhpVar10;
        zzii zziiVar8 = zzii.zzh;
        zzhp zzhpVar11 = new zzhp("BYTES", 10, 10, zzhrVar, zziiVar8);
        zzm = zzhpVar11;
        zzhp zzhpVar12 = new zzhp("UINT32", 11, 11, zzhrVar, zziiVar4);
        zzn = zzhpVar12;
        zzii zziiVar9 = zzii.zzi;
        zzhp zzhpVar13 = new zzhp("ENUM", 12, 12, zzhrVar, zziiVar9);
        zzo = zzhpVar13;
        zzhp zzhpVar14 = new zzhp("SFIXED32", 13, 13, zzhrVar, zziiVar4);
        zzp = zzhpVar14;
        zzhp zzhpVar15 = new zzhp("SFIXED64", 14, 14, zzhrVar, zziiVar3);
        zzq = zzhpVar15;
        zzhp zzhpVar16 = new zzhp("SINT32", 15, 15, zzhrVar, zziiVar4);
        zzr = zzhpVar16;
        zzhp zzhpVar17 = new zzhp("SINT64", 16, 16, zzhrVar, zziiVar3);
        zzs = zzhpVar17;
        zzhp zzhpVar18 = new zzhp("GROUP", 17, 17, zzhrVar, zziiVar7);
        zzt = zzhpVar18;
        zzhr zzhrVar2 = zzhr.VECTOR;
        zzhp zzhpVar19 = new zzhp("DOUBLE_LIST", 18, 18, zzhrVar2, zziiVar);
        zzu = zzhpVar19;
        zzhp zzhpVar20 = new zzhp("FLOAT_LIST", 19, 19, zzhrVar2, zziiVar2);
        zzv = zzhpVar20;
        zzhp zzhpVar21 = new zzhp("INT64_LIST", 20, 20, zzhrVar2, zziiVar3);
        zzw = zzhpVar21;
        zzhp zzhpVar22 = new zzhp("UINT64_LIST", 21, 21, zzhrVar2, zziiVar3);
        zzx = zzhpVar22;
        zzhp zzhpVar23 = new zzhp("INT32_LIST", 22, 22, zzhrVar2, zziiVar4);
        zzy = zzhpVar23;
        zzhp zzhpVar24 = new zzhp("FIXED64_LIST", 23, 23, zzhrVar2, zziiVar3);
        zzz = zzhpVar24;
        zzhp zzhpVar25 = new zzhp("FIXED32_LIST", 24, 24, zzhrVar2, zziiVar4);
        zzaa = zzhpVar25;
        zzhp zzhpVar26 = new zzhp("BOOL_LIST", 25, 25, zzhrVar2, zziiVar5);
        zzab = zzhpVar26;
        zzhp zzhpVar27 = new zzhp("STRING_LIST", 26, 26, zzhrVar2, zziiVar6);
        zzac = zzhpVar27;
        zzhp zzhpVar28 = new zzhp("MESSAGE_LIST", 27, 27, zzhrVar2, zziiVar7);
        zzad = zzhpVar28;
        zzhp zzhpVar29 = new zzhp("BYTES_LIST", 28, 28, zzhrVar2, zziiVar8);
        zzae = zzhpVar29;
        zzhp zzhpVar30 = new zzhp("UINT32_LIST", 29, 29, zzhrVar2, zziiVar4);
        zzaf = zzhpVar30;
        zzhp zzhpVar31 = new zzhp("ENUM_LIST", 30, 30, zzhrVar2, zziiVar9);
        zzag = zzhpVar31;
        zzhp zzhpVar32 = new zzhp("SFIXED32_LIST", 31, 31, zzhrVar2, zziiVar4);
        zzah = zzhpVar32;
        zzhp zzhpVar33 = new zzhp("SFIXED64_LIST", 32, 32, zzhrVar2, zziiVar3);
        zzai = zzhpVar33;
        zzhp zzhpVar34 = new zzhp("SINT32_LIST", 33, 33, zzhrVar2, zziiVar4);
        zzaj = zzhpVar34;
        zzhp zzhpVar35 = new zzhp("SINT64_LIST", 34, 34, zzhrVar2, zziiVar3);
        zzak = zzhpVar35;
        zzhr zzhrVar3 = zzhr.PACKED_VECTOR;
        zzhp zzhpVar36 = new zzhp("DOUBLE_LIST_PACKED", 35, 35, zzhrVar3, zziiVar);
        zza = zzhpVar36;
        zzhp zzhpVar37 = new zzhp("FLOAT_LIST_PACKED", 36, 36, zzhrVar3, zziiVar2);
        zzal = zzhpVar37;
        zzhp zzhpVar38 = new zzhp("INT64_LIST_PACKED", 37, 37, zzhrVar3, zziiVar3);
        zzam = zzhpVar38;
        zzhp zzhpVar39 = new zzhp("UINT64_LIST_PACKED", 38, 38, zzhrVar3, zziiVar3);
        zzan = zzhpVar39;
        zzhp zzhpVar40 = new zzhp("INT32_LIST_PACKED", 39, 39, zzhrVar3, zziiVar4);
        zzao = zzhpVar40;
        zzhp zzhpVar41 = new zzhp("FIXED64_LIST_PACKED", 40, 40, zzhrVar3, zziiVar3);
        zzap = zzhpVar41;
        zzhp zzhpVar42 = new zzhp("FIXED32_LIST_PACKED", 41, 41, zzhrVar3, zziiVar4);
        zzaq = zzhpVar42;
        zzhp zzhpVar43 = new zzhp("BOOL_LIST_PACKED", 42, 42, zzhrVar3, zziiVar5);
        zzar = zzhpVar43;
        zzhp zzhpVar44 = new zzhp("UINT32_LIST_PACKED", 43, 43, zzhrVar3, zziiVar4);
        zzas = zzhpVar44;
        zzhp zzhpVar45 = new zzhp("ENUM_LIST_PACKED", 44, 44, zzhrVar3, zziiVar9);
        zzat = zzhpVar45;
        zzhp zzhpVar46 = new zzhp("SFIXED32_LIST_PACKED", 45, 45, zzhrVar3, zziiVar4);
        zzau = zzhpVar46;
        zzhp zzhpVar47 = new zzhp("SFIXED64_LIST_PACKED", 46, 46, zzhrVar3, zziiVar3);
        zzav = zzhpVar47;
        zzhp zzhpVar48 = new zzhp("SINT32_LIST_PACKED", 47, 47, zzhrVar3, zziiVar4);
        zzaw = zzhpVar48;
        zzhp zzhpVar49 = new zzhp("SINT64_LIST_PACKED", 48, 48, zzhrVar3, zziiVar3);
        zzb = zzhpVar49;
        zzhp zzhpVar50 = new zzhp("GROUP_LIST", 49, 49, zzhrVar2, zziiVar7);
        zzax = zzhpVar50;
        zzhp zzhpVar51 = new zzhp("MAP", 50, 50, zzhr.MAP, zzii.zza);
        zzay = zzhpVar51;
        zzbg = new zzhp[]{zzhpVar, zzhpVar2, zzhpVar3, zzhpVar4, zzhpVar5, zzhpVar6, zzhpVar7, zzhpVar8, zzhpVar9, zzhpVar10, zzhpVar11, zzhpVar12, zzhpVar13, zzhpVar14, zzhpVar15, zzhpVar16, zzhpVar17, zzhpVar18, zzhpVar19, zzhpVar20, zzhpVar21, zzhpVar22, zzhpVar23, zzhpVar24, zzhpVar25, zzhpVar26, zzhpVar27, zzhpVar28, zzhpVar29, zzhpVar30, zzhpVar31, zzhpVar32, zzhpVar33, zzhpVar34, zzhpVar35, zzhpVar36, zzhpVar37, zzhpVar38, zzhpVar39, zzhpVar40, zzhpVar41, zzhpVar42, zzhpVar43, zzhpVar44, zzhpVar45, zzhpVar46, zzhpVar47, zzhpVar48, zzhpVar49, zzhpVar50, zzhpVar51};
        zzbf = new Type[0];
        zzhp[] zzhpVarArrValues = values();
        zzbe = new zzhp[zzhpVarArrValues.length];
        for (zzhp zzhpVar52 : zzhpVarArrValues) {
            zzbe[zzhpVar52.zzba] = zzhpVar52;
        }
    }

    private zzhp(String str, int i9, int i10, zzhr zzhrVar, zzii zziiVar) {
        int i11;
        this.zzba = i10;
        this.zzbb = zzhrVar;
        this.zzaz = zziiVar;
        int i12 = zzhs.zza[zzhrVar.ordinal()];
        if (i12 == 1 || i12 == 2) {
            this.zzbc = zziiVar.zza();
        } else {
            this.zzbc = null;
        }
        this.zzbd = (zzhrVar != zzhr.SCALAR || (i11 = zzhs.zzb[zziiVar.ordinal()]) == 1 || i11 == 2 || i11 == 3) ? false : true;
    }

    public static zzhp[] values() {
        return (zzhp[]) zzbg.clone();
    }

    public final int zza() {
        return this.zzba;
    }
}
