package com.google.android.gms.internal.measurement;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import p147n5.C5371i;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'zzb' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes2.dex */
public final class zzii {
    public static final zzii zza;
    public static final zzii zzb;
    public static final zzii zzc;
    public static final zzii zzd;
    public static final zzii zze;
    public static final zzii zzf;
    public static final zzii zzg;
    public static final zzii zzh;
    public static final zzii zzi;
    public static final zzii zzj;
    private static final /* synthetic */ zzii[] zzn;
    private final Class<?> zzk;
    private final Class<?> zzl;
    private final Object zzm;

    static {
        zzii zziiVar = new zzii("VOID", 0, Void.class, Void.class, null);
        zza = zziiVar;
        Class cls = Integer.TYPE;
        zzii zziiVar2 = new zzii("INT", 1, cls, Integer.class, 0);
        zzb = zziiVar2;
        zzii zziiVar3 = new zzii("LONG", 2, Long.TYPE, Long.class, 0L);
        zzc = zziiVar3;
        zzii zziiVar4 = new zzii("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(BitmapDescriptorFactory.HUE_RED));
        zzd = zziiVar4;
        zzii zziiVar5 = new zzii("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
        zze = zziiVar5;
        zzii zziiVar6 = new zzii("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.FALSE);
        zzf = zziiVar6;
        zzii zziiVar7 = new zzii("STRING", 6, String.class, String.class, "");
        zzg = zziiVar7;
        zzii zziiVar8 = new zzii("BYTE_STRING", 7, zzgm.class, zzgm.class, zzgm.zza);
        zzh = zziiVar8;
        zzii zziiVar9 = new zzii("ENUM", 8, cls, Integer.class, null);
        zzi = zziiVar9;
        zzii zziiVar10 = new zzii(C5371i.type, 9, Object.class, Object.class, null);
        zzj = zziiVar10;
        zzn = new zzii[]{zziiVar, zziiVar2, zziiVar3, zziiVar4, zziiVar5, zziiVar6, zziiVar7, zziiVar8, zziiVar9, zziiVar10};
    }

    private zzii(String str, int i9, Class cls, Class cls2, Object obj) {
        this.zzk = cls;
        this.zzl = cls2;
        this.zzm = obj;
    }

    public static zzii[] values() {
        return (zzii[]) zzn.clone();
    }

    public final Class<?> zza() {
        return this.zzl;
    }
}
