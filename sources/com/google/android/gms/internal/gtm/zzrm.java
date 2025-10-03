package com.google.android.gms.internal.gtm;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import p147n5.C5371i;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'zzbbm' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes2.dex */
public final class zzrm {
    public static final zzrm zzbbl;
    public static final zzrm zzbbm;
    public static final zzrm zzbbn;
    public static final zzrm zzbbo;
    public static final zzrm zzbbp;
    public static final zzrm zzbbq;
    public static final zzrm zzbbr;
    public static final zzrm zzbbs;
    public static final zzrm zzbbt;
    public static final zzrm zzbbu;
    private static final /* synthetic */ zzrm[] zzbby;
    private final Class<?> zzbbv;
    private final Class<?> zzbbw;
    private final Object zzbbx;

    static {
        zzrm zzrmVar = new zzrm("VOID", 0, Void.class, Void.class, null);
        zzbbl = zzrmVar;
        Class cls = Integer.TYPE;
        zzrm zzrmVar2 = new zzrm("INT", 1, cls, Integer.class, 0);
        zzbbm = zzrmVar2;
        zzrm zzrmVar3 = new zzrm("LONG", 2, Long.TYPE, Long.class, 0L);
        zzbbn = zzrmVar3;
        zzrm zzrmVar4 = new zzrm("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(BitmapDescriptorFactory.HUE_RED));
        zzbbo = zzrmVar4;
        zzrm zzrmVar5 = new zzrm("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
        zzbbp = zzrmVar5;
        zzrm zzrmVar6 = new zzrm("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.FALSE);
        zzbbq = zzrmVar6;
        zzrm zzrmVar7 = new zzrm("STRING", 6, String.class, String.class, "");
        zzbbr = zzrmVar7;
        zzrm zzrmVar8 = new zzrm("BYTE_STRING", 7, zzps.class, zzps.class, zzps.zzavx);
        zzbbs = zzrmVar8;
        zzrm zzrmVar9 = new zzrm("ENUM", 8, cls, Integer.class, null);
        zzbbt = zzrmVar9;
        zzrm zzrmVar10 = new zzrm(C5371i.type, 9, Object.class, Object.class, null);
        zzbbu = zzrmVar10;
        zzbby = new zzrm[]{zzrmVar, zzrmVar2, zzrmVar3, zzrmVar4, zzrmVar5, zzrmVar6, zzrmVar7, zzrmVar8, zzrmVar9, zzrmVar10};
    }

    private zzrm(String str, int i9, Class cls, Class cls2, Object obj) {
        this.zzbbv = cls;
        this.zzbbw = cls2;
        this.zzbbx = obj;
    }

    public static zzrm[] values() {
        return (zzrm[]) zzbby.clone();
    }

    public final Class<?> zzpx() {
        return this.zzbbw;
    }
}
