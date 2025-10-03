package com.google.android.gms.internal.play_billing;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import net.sqlcipher.database.SQLiteDatabase;
import sun.misc.Unsafe;

/* loaded from: classes2.dex */
final class zzdi<T> implements zzdp<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzeq.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzdf zzg;
    private final boolean zzh;
    private final int[] zzi;
    private final int zzj;
    private final int zzk;
    private final zzct zzl;
    private final zzeg zzm;
    private final zzbo zzn;
    private final int zzo;
    private final zzdk zzp;
    private final zzda zzq;

    private zzdi(int[] iArr, Object[] objArr, int i9, int i10, zzdf zzdfVar, int i11, boolean z8, int[] iArr2, int i12, int i13, zzdk zzdkVar, zzct zzctVar, zzeg zzegVar, zzbo zzboVar, zzda zzdaVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i9;
        this.zzf = i10;
        this.zzo = i11;
        boolean z9 = false;
        if (zzboVar != null && zzboVar.zzc(zzdfVar)) {
            z9 = true;
        }
        this.zzh = z9;
        this.zzi = iArr2;
        this.zzj = i12;
        this.zzk = i13;
        this.zzp = zzdkVar;
        this.zzl = zzctVar;
        this.zzm = zzegVar;
        this.zzn = zzboVar;
        this.zzg = zzdfVar;
        this.zzq = zzdaVar;
    }

    private final zzce zzA(int i9) {
        int i10 = i9 / 3;
        return (zzce) this.zzd[i10 + i10 + 1];
    }

    private final zzdp zzB(int i9) {
        int i10 = i9 / 3;
        int i11 = i10 + i10;
        zzdp zzdpVar = (zzdp) this.zzd[i11];
        if (zzdpVar != null) {
            return zzdpVar;
        }
        zzdp zzdpVarZzb = zzdn.zza().zzb((Class) this.zzd[i11 + 1]);
        this.zzd[i11] = zzdpVarZzb;
        return zzdpVarZzb;
    }

    private final Object zzC(int i9) {
        int i10 = i9 / 3;
        return this.zzd[i10 + i10];
    }

    private final Object zzD(Object obj, int i9) {
        zzdp zzdpVarZzB = zzB(i9);
        int iZzy = zzy(i9) & 1048575;
        if (!zzP(obj, i9)) {
            return zzdpVarZzB.zze();
        }
        Object object = zzb.getObject(obj, iZzy);
        if (zzS(object)) {
            return object;
        }
        Object objZze = zzdpVarZzB.zze();
        if (object != null) {
            zzdpVarZzB.zzg(objZze, object);
        }
        return objZze;
    }

    private final Object zzE(Object obj, int i9, int i10) {
        zzdp zzdpVarZzB = zzB(i10);
        if (!zzT(obj, i9, i10)) {
            return zzdpVarZzB.zze();
        }
        Object object = zzb.getObject(obj, zzy(i10) & 1048575);
        if (zzS(object)) {
            return object;
        }
        Object objZze = zzdpVarZzB.zze();
        if (object != null) {
            zzdpVarZzB.zzg(objZze, object);
        }
        return objZze;
    }

    private static Field zzF(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private static void zzG(Object obj) {
        if (!zzS(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(obj)));
        }
    }

    private final void zzH(Object obj, Object obj2, int i9) {
        if (zzP(obj2, i9)) {
            int iZzy = zzy(i9) & 1048575;
            Unsafe unsafe = zzb;
            long j9 = iZzy;
            Object object = unsafe.getObject(obj2, j9);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i9] + " is present but null: " + obj2.toString());
            }
            zzdp zzdpVarZzB = zzB(i9);
            if (!zzP(obj, i9)) {
                if (zzS(object)) {
                    Object objZze = zzdpVarZzB.zze();
                    zzdpVarZzB.zzg(objZze, object);
                    unsafe.putObject(obj, j9, objZze);
                } else {
                    unsafe.putObject(obj, j9, object);
                }
                zzJ(obj, i9);
                return;
            }
            Object object2 = unsafe.getObject(obj, j9);
            if (!zzS(object2)) {
                Object objZze2 = zzdpVarZzB.zze();
                zzdpVarZzB.zzg(objZze2, object2);
                unsafe.putObject(obj, j9, objZze2);
                object2 = objZze2;
            }
            zzdpVarZzB.zzg(object2, object);
        }
    }

    private final void zzI(Object obj, Object obj2, int i9) {
        int i10 = this.zzc[i9];
        if (zzT(obj2, i10, i9)) {
            int iZzy = zzy(i9) & 1048575;
            Unsafe unsafe = zzb;
            long j9 = iZzy;
            Object object = unsafe.getObject(obj2, j9);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i9] + " is present but null: " + obj2.toString());
            }
            zzdp zzdpVarZzB = zzB(i9);
            if (!zzT(obj, i10, i9)) {
                if (zzS(object)) {
                    Object objZze = zzdpVarZzB.zze();
                    zzdpVarZzB.zzg(objZze, object);
                    unsafe.putObject(obj, j9, objZze);
                } else {
                    unsafe.putObject(obj, j9, object);
                }
                zzK(obj, i10, i9);
                return;
            }
            Object object2 = unsafe.getObject(obj, j9);
            if (!zzS(object2)) {
                Object objZze2 = zzdpVarZzB.zze();
                zzdpVarZzB.zzg(objZze2, object2);
                unsafe.putObject(obj, j9, objZze2);
                object2 = objZze2;
            }
            zzdpVarZzB.zzg(object2, object);
        }
    }

    private final void zzJ(Object obj, int i9) {
        int iZzv = zzv(i9);
        long j9 = 1048575 & iZzv;
        if (j9 == 1048575) {
            return;
        }
        zzeq.zzq(obj, j9, (1 << (iZzv >>> 20)) | zzeq.zzc(obj, j9));
    }

    private final void zzK(Object obj, int i9, int i10) {
        zzeq.zzq(obj, zzv(i10) & 1048575, i9);
    }

    private final void zzL(Object obj, int i9, Object obj2) {
        zzb.putObject(obj, zzy(i9) & 1048575, obj2);
        zzJ(obj, i9);
    }

    private final void zzM(Object obj, int i9, int i10, Object obj2) {
        zzb.putObject(obj, zzy(i10) & 1048575, obj2);
        zzK(obj, i9, i10);
    }

    private final void zzN(zzey zzeyVar, int i9, Object obj, int i10) {
        if (obj == null) {
            return;
        }
        throw null;
    }

    private final boolean zzO(Object obj, Object obj2, int i9) {
        return zzP(obj, i9) == zzP(obj2, i9);
    }

    private final boolean zzP(Object obj, int i9) {
        int iZzv = zzv(i9);
        long j9 = iZzv & 1048575;
        if (j9 != 1048575) {
            return (zzeq.zzc(obj, j9) & (1 << (iZzv >>> 20))) != 0;
        }
        int iZzy = zzy(i9);
        long j10 = iZzy & 1048575;
        switch (zzx(iZzy)) {
            case 0:
                return Double.doubleToRawLongBits(zzeq.zza(obj, j10)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzeq.zzb(obj, j10)) != 0;
            case 2:
                return zzeq.zzd(obj, j10) != 0;
            case 3:
                return zzeq.zzd(obj, j10) != 0;
            case 4:
                return zzeq.zzc(obj, j10) != 0;
            case 5:
                return zzeq.zzd(obj, j10) != 0;
            case 6:
                return zzeq.zzc(obj, j10) != 0;
            case 7:
                return zzeq.zzw(obj, j10);
            case 8:
                Object objZzf = zzeq.zzf(obj, j10);
                if (objZzf instanceof String) {
                    return !((String) objZzf).isEmpty();
                }
                if (objZzf instanceof zzba) {
                    return !zzba.zzb.equals(objZzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzeq.zzf(obj, j10) != null;
            case 10:
                return !zzba.zzb.equals(zzeq.zzf(obj, j10));
            case 11:
                return zzeq.zzc(obj, j10) != 0;
            case 12:
                return zzeq.zzc(obj, j10) != 0;
            case 13:
                return zzeq.zzc(obj, j10) != 0;
            case 14:
                return zzeq.zzd(obj, j10) != 0;
            case 15:
                return zzeq.zzc(obj, j10) != 0;
            case 16:
                return zzeq.zzd(obj, j10) != 0;
            case 17:
                return zzeq.zzf(obj, j10) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzQ(Object obj, int i9, int i10, int i11, int i12) {
        return i10 == 1048575 ? zzP(obj, i9) : (i11 & i12) != 0;
    }

    private static boolean zzR(Object obj, int i9, zzdp zzdpVar) {
        return zzdpVar.zzk(zzeq.zzf(obj, i9 & 1048575));
    }

    private static boolean zzS(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzcb) {
            return ((zzcb) obj).zzt();
        }
        return true;
    }

    private final boolean zzT(Object obj, int i9, int i10) {
        return zzeq.zzc(obj, (long) (zzv(i10) & 1048575)) == i9;
    }

    private static boolean zzU(Object obj, long j9) {
        return ((Boolean) zzeq.zzf(obj, j9)).booleanValue();
    }

    private static final void zzV(int i9, Object obj, zzey zzeyVar) {
        if (obj instanceof String) {
            zzeyVar.zzF(i9, (String) obj);
        } else {
            zzeyVar.zzd(i9, (zzba) obj);
        }
    }

    public static zzeh zzd(Object obj) {
        zzcb zzcbVar = (zzcb) obj;
        zzeh zzehVar = zzcbVar.zzc;
        if (zzehVar != zzeh.zzc()) {
            return zzehVar;
        }
        zzeh zzehVarZzf = zzeh.zzf();
        zzcbVar.zzc = zzehVarZzf;
        return zzehVarZzf;
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x026d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static zzdi zzl(Class cls, zzdc zzdcVar, zzdk zzdkVar, zzct zzctVar, zzeg zzegVar, zzbo zzboVar, zzda zzdaVar) {
        int i9;
        int iCharAt;
        int iCharAt2;
        int i10;
        int[] iArr;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        char cCharAt;
        int i16;
        char cCharAt2;
        int i17;
        char cCharAt3;
        int i18;
        char cCharAt4;
        int i19;
        char cCharAt5;
        int i20;
        char cCharAt6;
        int i21;
        char cCharAt7;
        int i22;
        char cCharAt8;
        int i23;
        int i24;
        int i25;
        int i26;
        int i27;
        int iObjectFieldOffset;
        String str;
        int i28;
        int i29;
        int i30;
        int iObjectFieldOffset2;
        Field fieldZzF;
        char cCharAt9;
        int i31;
        int i32;
        Object obj;
        Field fieldZzF2;
        Object obj2;
        Field fieldZzF3;
        int i33;
        char cCharAt10;
        int i34;
        char cCharAt11;
        int i35;
        char cCharAt12;
        int i36;
        char cCharAt13;
        if (!(zzdcVar instanceof zzdo)) {
            throw null;
        }
        zzdo zzdoVar = (zzdo) zzdcVar;
        String strZzd = zzdoVar.zzd();
        int length = strZzd.length();
        char c9 = 55296;
        if (strZzd.charAt(0) >= 55296) {
            int i37 = 1;
            while (true) {
                i9 = i37 + 1;
                if (strZzd.charAt(i37) < 55296) {
                    break;
                }
                i37 = i9;
            }
        } else {
            i9 = 1;
        }
        int i38 = i9 + 1;
        int iCharAt3 = strZzd.charAt(i9);
        if (iCharAt3 >= 55296) {
            int i39 = iCharAt3 & 8191;
            int i40 = 13;
            while (true) {
                i36 = i38 + 1;
                cCharAt13 = strZzd.charAt(i38);
                if (cCharAt13 < 55296) {
                    break;
                }
                i39 |= (cCharAt13 & 8191) << i40;
                i40 += 13;
                i38 = i36;
            }
            iCharAt3 = i39 | (cCharAt13 << i40);
            i38 = i36;
        }
        if (iCharAt3 == 0) {
            iCharAt = 0;
            iCharAt2 = 0;
            i11 = 0;
            i14 = 0;
            i10 = 0;
            i12 = 0;
            iArr = zza;
            i13 = 0;
        } else {
            int i41 = i38 + 1;
            int iCharAt4 = strZzd.charAt(i38);
            if (iCharAt4 >= 55296) {
                int i42 = iCharAt4 & 8191;
                int i43 = 13;
                while (true) {
                    i22 = i41 + 1;
                    cCharAt8 = strZzd.charAt(i41);
                    if (cCharAt8 < 55296) {
                        break;
                    }
                    i42 |= (cCharAt8 & 8191) << i43;
                    i43 += 13;
                    i41 = i22;
                }
                iCharAt4 = i42 | (cCharAt8 << i43);
                i41 = i22;
            }
            int i44 = i41 + 1;
            int iCharAt5 = strZzd.charAt(i41);
            if (iCharAt5 >= 55296) {
                int i45 = iCharAt5 & 8191;
                int i46 = 13;
                while (true) {
                    i21 = i44 + 1;
                    cCharAt7 = strZzd.charAt(i44);
                    if (cCharAt7 < 55296) {
                        break;
                    }
                    i45 |= (cCharAt7 & 8191) << i46;
                    i46 += 13;
                    i44 = i21;
                }
                iCharAt5 = i45 | (cCharAt7 << i46);
                i44 = i21;
            }
            int i47 = i44 + 1;
            int iCharAt6 = strZzd.charAt(i44);
            if (iCharAt6 >= 55296) {
                int i48 = iCharAt6 & 8191;
                int i49 = 13;
                while (true) {
                    i20 = i47 + 1;
                    cCharAt6 = strZzd.charAt(i47);
                    if (cCharAt6 < 55296) {
                        break;
                    }
                    i48 |= (cCharAt6 & 8191) << i49;
                    i49 += 13;
                    i47 = i20;
                }
                iCharAt6 = i48 | (cCharAt6 << i49);
                i47 = i20;
            }
            int i50 = i47 + 1;
            int iCharAt7 = strZzd.charAt(i47);
            if (iCharAt7 >= 55296) {
                int i51 = iCharAt7 & 8191;
                int i52 = 13;
                while (true) {
                    i19 = i50 + 1;
                    cCharAt5 = strZzd.charAt(i50);
                    if (cCharAt5 < 55296) {
                        break;
                    }
                    i51 |= (cCharAt5 & 8191) << i52;
                    i52 += 13;
                    i50 = i19;
                }
                iCharAt7 = i51 | (cCharAt5 << i52);
                i50 = i19;
            }
            int i53 = i50 + 1;
            iCharAt = strZzd.charAt(i50);
            if (iCharAt >= 55296) {
                int i54 = iCharAt & 8191;
                int i55 = 13;
                while (true) {
                    i18 = i53 + 1;
                    cCharAt4 = strZzd.charAt(i53);
                    if (cCharAt4 < 55296) {
                        break;
                    }
                    i54 |= (cCharAt4 & 8191) << i55;
                    i55 += 13;
                    i53 = i18;
                }
                iCharAt = i54 | (cCharAt4 << i55);
                i53 = i18;
            }
            int i56 = i53 + 1;
            iCharAt2 = strZzd.charAt(i53);
            if (iCharAt2 >= 55296) {
                int i57 = iCharAt2 & 8191;
                int i58 = 13;
                while (true) {
                    i17 = i56 + 1;
                    cCharAt3 = strZzd.charAt(i56);
                    if (cCharAt3 < 55296) {
                        break;
                    }
                    i57 |= (cCharAt3 & 8191) << i58;
                    i58 += 13;
                    i56 = i17;
                }
                iCharAt2 = i57 | (cCharAt3 << i58);
                i56 = i17;
            }
            int i59 = i56 + 1;
            int iCharAt8 = strZzd.charAt(i56);
            if (iCharAt8 >= 55296) {
                int i60 = iCharAt8 & 8191;
                int i61 = 13;
                while (true) {
                    i16 = i59 + 1;
                    cCharAt2 = strZzd.charAt(i59);
                    if (cCharAt2 < 55296) {
                        break;
                    }
                    i60 |= (cCharAt2 & 8191) << i61;
                    i61 += 13;
                    i59 = i16;
                }
                iCharAt8 = i60 | (cCharAt2 << i61);
                i59 = i16;
            }
            int i62 = i59 + 1;
            int iCharAt9 = strZzd.charAt(i59);
            if (iCharAt9 >= 55296) {
                int i63 = iCharAt9 & 8191;
                int i64 = 13;
                while (true) {
                    i15 = i62 + 1;
                    cCharAt = strZzd.charAt(i62);
                    if (cCharAt < 55296) {
                        break;
                    }
                    i63 |= (cCharAt & 8191) << i64;
                    i64 += 13;
                    i62 = i15;
                }
                iCharAt9 = i63 | (cCharAt << i64);
                i62 = i15;
            }
            i10 = iCharAt4 + iCharAt4 + iCharAt5;
            iArr = new int[iCharAt9 + iCharAt2 + iCharAt8];
            i11 = iCharAt6;
            i12 = iCharAt9;
            i13 = iCharAt4;
            i14 = iCharAt7;
            i38 = i62;
        }
        Unsafe unsafe = zzb;
        Object[] objArrZze = zzdoVar.zze();
        Class<?> cls2 = zzdoVar.zza().getClass();
        int i65 = i12 + iCharAt2;
        int i66 = iCharAt + iCharAt;
        int[] iArr2 = new int[iCharAt * 3];
        Object[] objArr = new Object[i66];
        int i67 = 0;
        int i68 = 0;
        int i69 = i12;
        int i70 = i65;
        while (i38 < length) {
            int i71 = i38 + 1;
            int iCharAt10 = strZzd.charAt(i38);
            if (iCharAt10 >= c9) {
                int i72 = iCharAt10 & 8191;
                int i73 = i71;
                int i74 = 13;
                while (true) {
                    i35 = i73 + 1;
                    cCharAt12 = strZzd.charAt(i73);
                    if (cCharAt12 < c9) {
                        break;
                    }
                    i72 |= (cCharAt12 & 8191) << i74;
                    i74 += 13;
                    i73 = i35;
                }
                iCharAt10 = i72 | (cCharAt12 << i74);
                i23 = i35;
            } else {
                i23 = i71;
            }
            int i75 = i23 + 1;
            int iCharAt11 = strZzd.charAt(i23);
            if (iCharAt11 >= c9) {
                int i76 = iCharAt11 & 8191;
                int i77 = i75;
                int i78 = 13;
                while (true) {
                    i34 = i77 + 1;
                    cCharAt11 = strZzd.charAt(i77);
                    if (cCharAt11 < c9) {
                        break;
                    }
                    i76 |= (cCharAt11 & 8191) << i78;
                    i78 += 13;
                    i77 = i34;
                }
                iCharAt11 = i76 | (cCharAt11 << i78);
                i24 = i34;
            } else {
                i24 = i75;
            }
            if ((iCharAt11 & UserMetadata.MAX_ATTRIBUTE_SIZE) != 0) {
                iArr[i67] = i68;
                i67++;
            }
            int i79 = iCharAt11 & 255;
            if (i79 >= 51) {
                int i80 = i24 + 1;
                int iCharAt12 = strZzd.charAt(i24);
                i25 = length;
                char c10 = 55296;
                if (iCharAt12 >= 55296) {
                    int i81 = iCharAt12 & 8191;
                    int i82 = 13;
                    while (true) {
                        i33 = i80 + 1;
                        cCharAt10 = strZzd.charAt(i80);
                        if (cCharAt10 < c10) {
                            break;
                        }
                        i81 |= (cCharAt10 & 8191) << i82;
                        i82 += 13;
                        i80 = i33;
                        c10 = 55296;
                    }
                    iCharAt12 = i81 | (cCharAt10 << i82);
                    i80 = i33;
                }
                int i83 = i79 - 51;
                int i84 = i80;
                if (i83 == 9 || i83 == 17) {
                    int i85 = i68 / 3;
                    i32 = i10 + 1;
                    objArr[i85 + i85 + 1] = objArrZze[i10];
                } else {
                    if (i83 == 12 && (zzdoVar.zzc() == 1 || (iCharAt11 & 2048) != 0)) {
                        int i86 = i68 / 3;
                        i32 = i10 + 1;
                        objArr[i86 + i86 + 1] = objArrZze[i10];
                    }
                    int i87 = iCharAt12 + iCharAt12;
                    obj = objArrZze[i87];
                    if (obj instanceof Field) {
                        fieldZzF2 = zzF(cls2, (String) obj);
                        objArrZze[i87] = fieldZzF2;
                    } else {
                        fieldZzF2 = (Field) obj;
                    }
                    int i88 = i11;
                    i26 = i14;
                    iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZzF2);
                    int i89 = i87 + 1;
                    obj2 = objArrZze[i89];
                    if (obj2 instanceof Field) {
                        fieldZzF3 = zzF(cls2, (String) obj2);
                        objArrZze[i89] = fieldZzF3;
                    } else {
                        fieldZzF3 = (Field) obj2;
                    }
                    i27 = i88;
                    iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzF3);
                    i29 = i84;
                    i30 = 0;
                    str = strZzd;
                }
                i10 = i32;
                int i872 = iCharAt12 + iCharAt12;
                obj = objArrZze[i872];
                if (obj instanceof Field) {
                }
                int i882 = i11;
                i26 = i14;
                iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZzF2);
                int i892 = i872 + 1;
                obj2 = objArrZze[i892];
                if (obj2 instanceof Field) {
                }
                i27 = i882;
                iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzF3);
                i29 = i84;
                i30 = 0;
                str = strZzd;
            } else {
                i25 = length;
                int i90 = i11;
                i26 = i14;
                int i91 = i10 + 1;
                Field fieldZzF4 = zzF(cls2, (String) objArrZze[i10]);
                if (i79 == 9 || i79 == 17) {
                    i27 = i90;
                    int i92 = i68 / 3;
                    objArr[i92 + i92 + 1] = fieldZzF4.getType();
                } else {
                    if (i79 == 27 || i79 == 49) {
                        i27 = i90;
                        int i93 = i68 / 3;
                        i31 = i91 + 1;
                        objArr[i93 + i93 + 1] = objArrZze[i91];
                    } else if (i79 == 12 || i79 == 30 || i79 == 44) {
                        i27 = i90;
                        if (zzdoVar.zzc() == 1 || (iCharAt11 & 2048) != 0) {
                            int i94 = i68 / 3;
                            i31 = i91 + 1;
                            objArr[i94 + i94 + 1] = objArrZze[i91];
                        }
                    } else if (i79 == 50) {
                        int i95 = i69 + 1;
                        iArr[i69] = i68;
                        int i96 = i68 / 3;
                        int i97 = i91 + 1;
                        int i98 = i96 + i96;
                        objArr[i98] = objArrZze[i91];
                        if ((iCharAt11 & 2048) != 0) {
                            i91 = i97 + 1;
                            objArr[i98 + 1] = objArrZze[i97];
                            i27 = i90;
                            i69 = i95;
                        } else {
                            i69 = i95;
                            i91 = i97;
                            i27 = i90;
                        }
                    } else {
                        i27 = i90;
                    }
                    i91 = i31;
                }
                int iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldZzF4);
                iObjectFieldOffset = 1048575;
                if ((iCharAt11 & 4096) == 0 || i79 > 17) {
                    str = strZzd;
                    i28 = i91;
                    i29 = i24;
                    i30 = 0;
                } else {
                    int i99 = i24 + 1;
                    int iCharAt13 = strZzd.charAt(i24);
                    if (iCharAt13 >= 55296) {
                        int i100 = iCharAt13 & 8191;
                        int i101 = 13;
                        while (true) {
                            i29 = i99 + 1;
                            cCharAt9 = strZzd.charAt(i99);
                            if (cCharAt9 < 55296) {
                                break;
                            }
                            i100 |= (cCharAt9 & 8191) << i101;
                            i101 += 13;
                            i99 = i29;
                        }
                        iCharAt13 = i100 | (cCharAt9 << i101);
                    } else {
                        i29 = i99;
                    }
                    int i102 = i13 + i13 + (iCharAt13 / 32);
                    Object obj3 = objArrZze[i102];
                    str = strZzd;
                    if (obj3 instanceof Field) {
                        fieldZzF = (Field) obj3;
                    } else {
                        fieldZzF = zzF(cls2, (String) obj3);
                        objArrZze[i102] = fieldZzF;
                    }
                    i28 = i91;
                    i30 = iCharAt13 % 32;
                    iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzF);
                }
                if (i79 >= 18 && i79 <= 49) {
                    iArr[i70] = iObjectFieldOffset3;
                    i70++;
                }
                iObjectFieldOffset2 = iObjectFieldOffset3;
                i10 = i28;
            }
            int i103 = i68 + 1;
            iArr2[i68] = iCharAt10;
            int i104 = i103 + 1;
            iArr2[i103] = iObjectFieldOffset2 | ((iCharAt11 & 256) != 0 ? SQLiteDatabase.CREATE_IF_NECESSARY : 0) | ((iCharAt11 & 512) != 0 ? 536870912 : 0) | (i79 << 20);
            i68 = i104 + 1;
            iArr2[i104] = (i30 << 20) | iObjectFieldOffset;
            i38 = i29;
            length = i25;
            i11 = i27;
            strZzd = str;
            i14 = i26;
            c9 = 55296;
        }
        return new zzdi(iArr2, objArr, i11, i14, zzdoVar.zza(), zzdoVar.zzc(), false, iArr, i12, i65, zzdkVar, zzctVar, zzegVar, zzboVar, zzdaVar);
    }

    private static double zzm(Object obj, long j9) {
        return ((Double) zzeq.zzf(obj, j9)).doubleValue();
    }

    private static float zzn(Object obj, long j9) {
        return ((Float) zzeq.zzf(obj, j9)).floatValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final int zzo(Object obj) {
        int i9;
        int iZzx;
        int iZzx2;
        int iZzy;
        int iZzx3;
        int iZzx4;
        int iZzx5;
        int iZzx6;
        int iZzt;
        boolean z8;
        int iZzc;
        int iZzh;
        int iZzx7;
        int iZzx8;
        int iZzx9;
        int iZzx10;
        int iZzx11;
        int iZzx12;
        int iZzx13;
        Unsafe unsafe = zzb;
        int i10 = 1048575;
        int i11 = 1048575;
        int i12 = 0;
        int iZzx14 = 0;
        int i13 = 0;
        while (i12 < this.zzc.length) {
            int iZzy2 = zzy(i12);
            int[] iArr = this.zzc;
            int i14 = iArr[i12];
            int iZzx15 = zzx(iZzy2);
            if (iZzx15 <= 17) {
                int i15 = iArr[i12 + 2];
                int i16 = i15 & i10;
                int i17 = i15 >>> 20;
                if (i16 != i11) {
                    i13 = unsafe.getInt(obj, i16);
                    i11 = i16;
                }
                i9 = 1 << i17;
            } else {
                i9 = 0;
            }
            long j9 = iZzy2 & i10;
            switch (iZzx15) {
                case 0:
                    if ((i13 & i9) == 0) {
                        break;
                    } else {
                        iZzx = zzbi.zzx(i14 << 3);
                        iZzx4 = iZzx + 8;
                        iZzx14 += iZzx4;
                        break;
                    }
                case 1:
                    if ((i13 & i9) == 0) {
                        break;
                    } else {
                        iZzx2 = zzbi.zzx(i14 << 3);
                        iZzx4 = iZzx2 + 4;
                        iZzx14 += iZzx4;
                        break;
                    }
                case 2:
                    if ((i13 & i9) == 0) {
                        break;
                    } else {
                        iZzy = zzbi.zzy(unsafe.getLong(obj, j9));
                        iZzx3 = zzbi.zzx(i14 << 3);
                        iZzx14 += iZzx3 + iZzy;
                        break;
                    }
                case 3:
                    if ((i13 & i9) == 0) {
                        break;
                    } else {
                        iZzy = zzbi.zzy(unsafe.getLong(obj, j9));
                        iZzx3 = zzbi.zzx(i14 << 3);
                        iZzx14 += iZzx3 + iZzy;
                        break;
                    }
                case 4:
                    if ((i13 & i9) == 0) {
                        break;
                    } else {
                        iZzy = zzbi.zzu(unsafe.getInt(obj, j9));
                        iZzx3 = zzbi.zzx(i14 << 3);
                        iZzx14 += iZzx3 + iZzy;
                        break;
                    }
                case 5:
                    if ((i13 & i9) == 0) {
                        break;
                    } else {
                        iZzx = zzbi.zzx(i14 << 3);
                        iZzx4 = iZzx + 8;
                        iZzx14 += iZzx4;
                        break;
                    }
                case 6:
                    if ((i13 & i9) == 0) {
                        break;
                    } else {
                        iZzx2 = zzbi.zzx(i14 << 3);
                        iZzx4 = iZzx2 + 4;
                        iZzx14 += iZzx4;
                        break;
                    }
                case 7:
                    if ((i13 & i9) == 0) {
                        break;
                    } else {
                        iZzx4 = zzbi.zzx(i14 << 3) + 1;
                        iZzx14 += iZzx4;
                        break;
                    }
                case 8:
                    if ((i13 & i9) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(obj, j9);
                        if (!(object instanceof zzba)) {
                            iZzy = zzbi.zzw((String) object);
                            iZzx3 = zzbi.zzx(i14 << 3);
                            iZzx14 += iZzx3 + iZzy;
                            break;
                        } else {
                            int i18 = zzbi.zzb;
                            int iZzd = ((zzba) object).zzd();
                            iZzx5 = zzbi.zzx(iZzd) + iZzd;
                            iZzx6 = zzbi.zzx(i14 << 3);
                            iZzx4 = iZzx6 + iZzx5;
                            iZzx14 += iZzx4;
                            break;
                        }
                    }
                case 9:
                    if ((i13 & i9) == 0) {
                        break;
                    } else {
                        iZzx4 = zzdr.zzn(i14, unsafe.getObject(obj, j9), zzB(i12));
                        iZzx14 += iZzx4;
                        break;
                    }
                case 10:
                    if ((i13 & i9) == 0) {
                        break;
                    } else {
                        zzba zzbaVar = (zzba) unsafe.getObject(obj, j9);
                        int i19 = zzbi.zzb;
                        int iZzd2 = zzbaVar.zzd();
                        iZzx5 = zzbi.zzx(iZzd2) + iZzd2;
                        iZzx6 = zzbi.zzx(i14 << 3);
                        iZzx4 = iZzx6 + iZzx5;
                        iZzx14 += iZzx4;
                        break;
                    }
                case 11:
                    if ((i13 & i9) == 0) {
                        break;
                    } else {
                        iZzy = zzbi.zzx(unsafe.getInt(obj, j9));
                        iZzx3 = zzbi.zzx(i14 << 3);
                        iZzx14 += iZzx3 + iZzy;
                        break;
                    }
                case 12:
                    if ((i13 & i9) == 0) {
                        break;
                    } else {
                        iZzy = zzbi.zzu(unsafe.getInt(obj, j9));
                        iZzx3 = zzbi.zzx(i14 << 3);
                        iZzx14 += iZzx3 + iZzy;
                        break;
                    }
                case 13:
                    if ((i13 & i9) == 0) {
                        break;
                    } else {
                        iZzx2 = zzbi.zzx(i14 << 3);
                        iZzx4 = iZzx2 + 4;
                        iZzx14 += iZzx4;
                        break;
                    }
                case 14:
                    if ((i13 & i9) == 0) {
                        break;
                    } else {
                        iZzx = zzbi.zzx(i14 << 3);
                        iZzx4 = iZzx + 8;
                        iZzx14 += iZzx4;
                        break;
                    }
                case 15:
                    if ((i13 & i9) == 0) {
                        break;
                    } else {
                        int i20 = unsafe.getInt(obj, j9);
                        iZzx3 = zzbi.zzx(i14 << 3);
                        iZzy = zzbi.zzx((i20 >> 31) ^ (i20 + i20));
                        iZzx14 += iZzx3 + iZzy;
                        break;
                    }
                case 16:
                    if ((i9 & i13) == 0) {
                        break;
                    } else {
                        long j10 = unsafe.getLong(obj, j9);
                        iZzx14 += zzbi.zzx(i14 << 3) + zzbi.zzy((j10 >> 63) ^ (j10 + j10));
                        break;
                    }
                case 17:
                    if ((i13 & i9) == 0) {
                        break;
                    } else {
                        iZzx4 = zzbi.zzt(i14, (zzdf) unsafe.getObject(obj, j9), zzB(i12));
                        iZzx14 += iZzx4;
                        break;
                    }
                case 18:
                    iZzx4 = zzdr.zzg(i14, (List) unsafe.getObject(obj, j9), false);
                    iZzx14 += iZzx4;
                    break;
                case 19:
                    iZzx4 = zzdr.zze(i14, (List) unsafe.getObject(obj, j9), false);
                    iZzx14 += iZzx4;
                    break;
                case 20:
                    iZzx4 = zzdr.zzl(i14, (List) unsafe.getObject(obj, j9), false);
                    iZzx14 += iZzx4;
                    break;
                case 21:
                    iZzx4 = zzdr.zzw(i14, (List) unsafe.getObject(obj, j9), false);
                    iZzx14 += iZzx4;
                    break;
                case 22:
                    iZzx4 = zzdr.zzj(i14, (List) unsafe.getObject(obj, j9), false);
                    iZzx14 += iZzx4;
                    break;
                case 23:
                    iZzx4 = zzdr.zzg(i14, (List) unsafe.getObject(obj, j9), false);
                    iZzx14 += iZzx4;
                    break;
                case 24:
                    iZzx4 = zzdr.zze(i14, (List) unsafe.getObject(obj, j9), false);
                    iZzx14 += iZzx4;
                    break;
                case 25:
                    iZzx4 = zzdr.zza(i14, (List) unsafe.getObject(obj, j9), false);
                    iZzx14 += iZzx4;
                    break;
                case 26:
                    iZzt = zzdr.zzt(i14, (List) unsafe.getObject(obj, j9));
                    iZzx14 += iZzt;
                    break;
                case 27:
                    iZzt = zzdr.zzo(i14, (List) unsafe.getObject(obj, j9), zzB(i12));
                    iZzx14 += iZzt;
                    break;
                case 28:
                    iZzt = zzdr.zzb(i14, (List) unsafe.getObject(obj, j9));
                    iZzx14 += iZzt;
                    break;
                case 29:
                    iZzt = zzdr.zzu(i14, (List) unsafe.getObject(obj, j9), false);
                    iZzx14 += iZzt;
                    break;
                case 30:
                    z8 = false;
                    iZzc = zzdr.zzc(i14, (List) unsafe.getObject(obj, j9), false);
                    iZzx14 += iZzc;
                    break;
                case 31:
                    z8 = false;
                    iZzc = zzdr.zze(i14, (List) unsafe.getObject(obj, j9), false);
                    iZzx14 += iZzc;
                    break;
                case 32:
                    z8 = false;
                    iZzc = zzdr.zzg(i14, (List) unsafe.getObject(obj, j9), false);
                    iZzx14 += iZzc;
                    break;
                case 33:
                    z8 = false;
                    iZzc = zzdr.zzp(i14, (List) unsafe.getObject(obj, j9), false);
                    iZzx14 += iZzc;
                    break;
                case 34:
                    z8 = false;
                    iZzc = zzdr.zzr(i14, (List) unsafe.getObject(obj, j9), false);
                    iZzx14 += iZzc;
                    break;
                case 35:
                    iZzh = zzdr.zzh((List) unsafe.getObject(obj, j9));
                    if (iZzh > 0) {
                        iZzx7 = zzbi.zzx(iZzh);
                        iZzx8 = zzbi.zzx(i14 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 36:
                    iZzh = zzdr.zzf((List) unsafe.getObject(obj, j9));
                    if (iZzh > 0) {
                        iZzx7 = zzbi.zzx(iZzh);
                        iZzx8 = zzbi.zzx(i14 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 37:
                    iZzh = zzdr.zzm((List) unsafe.getObject(obj, j9));
                    if (iZzh > 0) {
                        iZzx7 = zzbi.zzx(iZzh);
                        iZzx8 = zzbi.zzx(i14 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 38:
                    iZzh = zzdr.zzx((List) unsafe.getObject(obj, j9));
                    if (iZzh > 0) {
                        iZzx7 = zzbi.zzx(iZzh);
                        iZzx8 = zzbi.zzx(i14 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 39:
                    iZzh = zzdr.zzk((List) unsafe.getObject(obj, j9));
                    if (iZzh > 0) {
                        iZzx7 = zzbi.zzx(iZzh);
                        iZzx8 = zzbi.zzx(i14 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 40:
                    iZzh = zzdr.zzh((List) unsafe.getObject(obj, j9));
                    if (iZzh > 0) {
                        iZzx7 = zzbi.zzx(iZzh);
                        iZzx8 = zzbi.zzx(i14 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 41:
                    iZzh = zzdr.zzf((List) unsafe.getObject(obj, j9));
                    if (iZzh > 0) {
                        iZzx7 = zzbi.zzx(iZzh);
                        iZzx8 = zzbi.zzx(i14 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 42:
                    List list = (List) unsafe.getObject(obj, j9);
                    int i21 = zzdr.zza;
                    iZzh = list.size();
                    if (iZzh > 0) {
                        iZzx7 = zzbi.zzx(iZzh);
                        iZzx8 = zzbi.zzx(i14 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 43:
                    iZzh = zzdr.zzv((List) unsafe.getObject(obj, j9));
                    if (iZzh > 0) {
                        iZzx7 = zzbi.zzx(iZzh);
                        iZzx8 = zzbi.zzx(i14 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 44:
                    iZzh = zzdr.zzd((List) unsafe.getObject(obj, j9));
                    if (iZzh > 0) {
                        iZzx7 = zzbi.zzx(iZzh);
                        iZzx8 = zzbi.zzx(i14 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 45:
                    iZzh = zzdr.zzf((List) unsafe.getObject(obj, j9));
                    if (iZzh > 0) {
                        iZzx7 = zzbi.zzx(iZzh);
                        iZzx8 = zzbi.zzx(i14 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 46:
                    iZzh = zzdr.zzh((List) unsafe.getObject(obj, j9));
                    if (iZzh > 0) {
                        iZzx7 = zzbi.zzx(iZzh);
                        iZzx8 = zzbi.zzx(i14 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 47:
                    iZzh = zzdr.zzq((List) unsafe.getObject(obj, j9));
                    if (iZzh > 0) {
                        iZzx7 = zzbi.zzx(iZzh);
                        iZzx8 = zzbi.zzx(i14 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 48:
                    iZzh = zzdr.zzs((List) unsafe.getObject(obj, j9));
                    if (iZzh > 0) {
                        iZzx7 = zzbi.zzx(iZzh);
                        iZzx8 = zzbi.zzx(i14 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 49:
                    iZzt = zzdr.zzi(i14, (List) unsafe.getObject(obj, j9), zzB(i12));
                    iZzx14 += iZzt;
                    break;
                case 50:
                    zzda.zza(i14, unsafe.getObject(obj, j9), zzC(i12));
                    break;
                case 51:
                    if (zzT(obj, i14, i12)) {
                        iZzx10 = zzbi.zzx(i14 << 3);
                        iZzt = iZzx10 + 8;
                        iZzx14 += iZzt;
                    }
                    break;
                case 52:
                    if (zzT(obj, i14, i12)) {
                        iZzx11 = zzbi.zzx(i14 << 3);
                        iZzt = iZzx11 + 4;
                        iZzx14 += iZzt;
                    }
                    break;
                case 53:
                    if (zzT(obj, i14, i12)) {
                        iZzh = zzbi.zzy(zzz(obj, j9));
                        iZzx9 = zzbi.zzx(i14 << 3);
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 54:
                    if (zzT(obj, i14, i12)) {
                        iZzh = zzbi.zzy(zzz(obj, j9));
                        iZzx9 = zzbi.zzx(i14 << 3);
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 55:
                    if (zzT(obj, i14, i12)) {
                        iZzh = zzbi.zzu(zzp(obj, j9));
                        iZzx9 = zzbi.zzx(i14 << 3);
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 56:
                    if (zzT(obj, i14, i12)) {
                        iZzx10 = zzbi.zzx(i14 << 3);
                        iZzt = iZzx10 + 8;
                        iZzx14 += iZzt;
                    }
                    break;
                case 57:
                    if (zzT(obj, i14, i12)) {
                        iZzx11 = zzbi.zzx(i14 << 3);
                        iZzt = iZzx11 + 4;
                        iZzx14 += iZzt;
                    }
                    break;
                case 58:
                    if (zzT(obj, i14, i12)) {
                        iZzt = zzbi.zzx(i14 << 3) + 1;
                        iZzx14 += iZzt;
                    }
                    break;
                case 59:
                    if (zzT(obj, i14, i12)) {
                        Object object2 = unsafe.getObject(obj, j9);
                        if (object2 instanceof zzba) {
                            int i22 = zzbi.zzb;
                            int iZzd3 = ((zzba) object2).zzd();
                            iZzx12 = zzbi.zzx(iZzd3) + iZzd3;
                            iZzx13 = zzbi.zzx(i14 << 3);
                            iZzt = iZzx13 + iZzx12;
                            iZzx14 += iZzt;
                        } else {
                            iZzh = zzbi.zzw((String) object2);
                            iZzx9 = zzbi.zzx(i14 << 3);
                            iZzx14 += iZzx9 + iZzh;
                        }
                    }
                    break;
                case 60:
                    if (zzT(obj, i14, i12)) {
                        iZzt = zzdr.zzn(i14, unsafe.getObject(obj, j9), zzB(i12));
                        iZzx14 += iZzt;
                    }
                    break;
                case 61:
                    if (zzT(obj, i14, i12)) {
                        zzba zzbaVar2 = (zzba) unsafe.getObject(obj, j9);
                        int i23 = zzbi.zzb;
                        int iZzd4 = zzbaVar2.zzd();
                        iZzx12 = zzbi.zzx(iZzd4) + iZzd4;
                        iZzx13 = zzbi.zzx(i14 << 3);
                        iZzt = iZzx13 + iZzx12;
                        iZzx14 += iZzt;
                    }
                    break;
                case 62:
                    if (zzT(obj, i14, i12)) {
                        iZzh = zzbi.zzx(zzp(obj, j9));
                        iZzx9 = zzbi.zzx(i14 << 3);
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 63:
                    if (zzT(obj, i14, i12)) {
                        iZzh = zzbi.zzu(zzp(obj, j9));
                        iZzx9 = zzbi.zzx(i14 << 3);
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 64:
                    if (zzT(obj, i14, i12)) {
                        iZzx11 = zzbi.zzx(i14 << 3);
                        iZzt = iZzx11 + 4;
                        iZzx14 += iZzt;
                    }
                    break;
                case 65:
                    if (zzT(obj, i14, i12)) {
                        iZzx10 = zzbi.zzx(i14 << 3);
                        iZzt = iZzx10 + 8;
                        iZzx14 += iZzt;
                    }
                    break;
                case 66:
                    if (zzT(obj, i14, i12)) {
                        int iZzp = zzp(obj, j9);
                        iZzx9 = zzbi.zzx(i14 << 3);
                        iZzh = zzbi.zzx((iZzp >> 31) ^ (iZzp + iZzp));
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 67:
                    if (zzT(obj, i14, i12)) {
                        long jZzz = zzz(obj, j9);
                        iZzx14 += zzbi.zzx(i14 << 3) + zzbi.zzy((jZzz >> 63) ^ (jZzz + jZzz));
                    }
                    break;
                case 68:
                    if (zzT(obj, i14, i12)) {
                        iZzt = zzbi.zzt(i14, (zzdf) unsafe.getObject(obj, j9), zzB(i12));
                        iZzx14 += iZzt;
                    }
                    break;
            }
            i12 += 3;
            i10 = 1048575;
        }
        zzeg zzegVar = this.zzm;
        int iZza = iZzx14 + zzegVar.zza(zzegVar.zzd(obj));
        if (!this.zzh) {
            return iZza;
        }
        this.zzn.zza(obj);
        throw null;
    }

    private static int zzp(Object obj, long j9) {
        return ((Integer) zzeq.zzf(obj, j9)).intValue();
    }

    private final int zzq(Object obj, byte[] bArr, int i9, int i10, int i11, long j9, zzan zzanVar) {
        Unsafe unsafe = zzb;
        Object objZzC = zzC(i11);
        Object object = unsafe.getObject(obj, j9);
        if (!((zzcz) object).zze()) {
            zzcz zzczVarZzb = zzcz.zza().zzb();
            zzda.zzb(zzczVarZzb, object);
            unsafe.putObject(obj, j9, zzczVarZzb);
        }
        throw null;
    }

    private final int zzr(Object obj, byte[] bArr, int i9, int i10, int i11, int i12, int i13, int i14, int i15, long j9, int i16, zzan zzanVar) throws zzci {
        Unsafe unsafe = zzb;
        long j10 = this.zzc[i16 + 2] & 1048575;
        switch (i15) {
            case 51:
                if (i13 == 1) {
                    unsafe.putObject(obj, j9, Double.valueOf(Double.longBitsToDouble(zzao.zzp(bArr, i9))));
                    int i17 = i9 + 8;
                    unsafe.putInt(obj, j10, i12);
                    return i17;
                }
                break;
            case 52:
                if (i13 == 5) {
                    unsafe.putObject(obj, j9, Float.valueOf(Float.intBitsToFloat(zzao.zzb(bArr, i9))));
                    int i18 = i9 + 4;
                    unsafe.putInt(obj, j10, i12);
                    return i18;
                }
                break;
            case 53:
            case 54:
                if (i13 == 0) {
                    int iZzm = zzao.zzm(bArr, i9, zzanVar);
                    unsafe.putObject(obj, j9, Long.valueOf(zzanVar.zzb));
                    unsafe.putInt(obj, j10, i12);
                    return iZzm;
                }
                break;
            case 55:
            case 62:
                if (i13 == 0) {
                    int iZzj = zzao.zzj(bArr, i9, zzanVar);
                    unsafe.putObject(obj, j9, Integer.valueOf(zzanVar.zza));
                    unsafe.putInt(obj, j10, i12);
                    return iZzj;
                }
                break;
            case 56:
            case 65:
                if (i13 == 1) {
                    unsafe.putObject(obj, j9, Long.valueOf(zzao.zzp(bArr, i9)));
                    int i19 = i9 + 8;
                    unsafe.putInt(obj, j10, i12);
                    return i19;
                }
                break;
            case 57:
            case 64:
                if (i13 == 5) {
                    unsafe.putObject(obj, j9, Integer.valueOf(zzao.zzb(bArr, i9)));
                    int i20 = i9 + 4;
                    unsafe.putInt(obj, j10, i12);
                    return i20;
                }
                break;
            case 58:
                if (i13 == 0) {
                    int iZzm2 = zzao.zzm(bArr, i9, zzanVar);
                    unsafe.putObject(obj, j9, Boolean.valueOf(zzanVar.zzb != 0));
                    unsafe.putInt(obj, j10, i12);
                    return iZzm2;
                }
                break;
            case 59:
                if (i13 == 2) {
                    int iZzj2 = zzao.zzj(bArr, i9, zzanVar);
                    int i21 = zzanVar.zza;
                    if (i21 == 0) {
                        unsafe.putObject(obj, j9, "");
                    } else {
                        if ((i14 & 536870912) != 0 && !zzev.zze(bArr, iZzj2, iZzj2 + i21)) {
                            throw zzci.zzc();
                        }
                        unsafe.putObject(obj, j9, new String(bArr, iZzj2, i21, zzcg.zzb));
                        iZzj2 += i21;
                    }
                    unsafe.putInt(obj, j10, i12);
                    return iZzj2;
                }
                break;
            case 60:
                if (i13 == 2) {
                    Object objZzE = zzE(obj, i12, i16);
                    int iZzo = zzao.zzo(objZzE, zzB(i16), bArr, i9, i10, zzanVar);
                    zzM(obj, i12, i16, objZzE);
                    return iZzo;
                }
                break;
            case 61:
                if (i13 == 2) {
                    int iZza = zzao.zza(bArr, i9, zzanVar);
                    unsafe.putObject(obj, j9, zzanVar.zzc);
                    unsafe.putInt(obj, j10, i12);
                    return iZza;
                }
                break;
            case 63:
                if (i13 == 0) {
                    int iZzj3 = zzao.zzj(bArr, i9, zzanVar);
                    int i22 = zzanVar.zza;
                    zzce zzceVarZzA = zzA(i16);
                    if (zzceVarZzA == null || zzceVarZzA.zza(i22)) {
                        unsafe.putObject(obj, j9, Integer.valueOf(i22));
                        unsafe.putInt(obj, j10, i12);
                    } else {
                        zzd(obj).zzj(i11, Long.valueOf(i22));
                    }
                    return iZzj3;
                }
                break;
            case 66:
                if (i13 == 0) {
                    int iZzj4 = zzao.zzj(bArr, i9, zzanVar);
                    unsafe.putObject(obj, j9, Integer.valueOf(zzbe.zzb(zzanVar.zza)));
                    unsafe.putInt(obj, j10, i12);
                    return iZzj4;
                }
                break;
            case 67:
                if (i13 == 0) {
                    int iZzm3 = zzao.zzm(bArr, i9, zzanVar);
                    unsafe.putObject(obj, j9, Long.valueOf(zzbe.zzc(zzanVar.zzb)));
                    unsafe.putInt(obj, j10, i12);
                    return iZzm3;
                }
                break;
            case 68:
                if (i13 == 3) {
                    Object objZzE2 = zzE(obj, i12, i16);
                    int iZzn = zzao.zzn(objZzE2, zzB(i16), bArr, i9, i10, (i11 & (-8)) | 4, zzanVar);
                    zzM(obj, i12, i16, objZzE2);
                    return iZzn;
                }
                break;
        }
        return i9;
    }

    private final int zzs(Object obj, byte[] bArr, int i9, int i10, int i11, int i12, int i13, int i14, long j9, int i15, long j10, zzan zzanVar) throws zzci {
        int i16;
        int i17;
        int i18;
        int i19;
        int iZzl;
        int iZzj = i9;
        Unsafe unsafe = zzb;
        zzcf zzcfVarZzd = (zzcf) unsafe.getObject(obj, j10);
        if (!zzcfVarZzd.zzc()) {
            int size = zzcfVarZzd.size();
            zzcfVarZzd = zzcfVarZzd.zzd(size == 0 ? 10 : size + size);
            unsafe.putObject(obj, j10, zzcfVarZzd);
        }
        switch (i15) {
            case 18:
            case 35:
                if (i13 == 2) {
                    zzbk zzbkVar = (zzbk) zzcfVarZzd;
                    int iZzj2 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i20 = zzanVar.zza + iZzj2;
                    while (iZzj2 < i20) {
                        zzbkVar.zze(Double.longBitsToDouble(zzao.zzp(bArr, iZzj2)));
                        iZzj2 += 8;
                    }
                    if (iZzj2 == i20) {
                        return iZzj2;
                    }
                    throw zzci.zzg();
                }
                if (i13 == 1) {
                    zzbk zzbkVar2 = (zzbk) zzcfVarZzd;
                    zzbkVar2.zze(Double.longBitsToDouble(zzao.zzp(bArr, i9)));
                    while (true) {
                        i16 = iZzj + 8;
                        if (i16 < i10) {
                            iZzj = zzao.zzj(bArr, i16, zzanVar);
                            if (i11 == zzanVar.zza) {
                                zzbkVar2.zze(Double.longBitsToDouble(zzao.zzp(bArr, iZzj)));
                            }
                        }
                    }
                    return i16;
                }
                break;
            case 19:
            case 36:
                if (i13 == 2) {
                    zzbu zzbuVar = (zzbu) zzcfVarZzd;
                    int iZzj3 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i21 = zzanVar.zza + iZzj3;
                    while (iZzj3 < i21) {
                        zzbuVar.zze(Float.intBitsToFloat(zzao.zzb(bArr, iZzj3)));
                        iZzj3 += 4;
                    }
                    if (iZzj3 == i21) {
                        return iZzj3;
                    }
                    throw zzci.zzg();
                }
                if (i13 == 5) {
                    zzbu zzbuVar2 = (zzbu) zzcfVarZzd;
                    zzbuVar2.zze(Float.intBitsToFloat(zzao.zzb(bArr, i9)));
                    while (true) {
                        i17 = iZzj + 4;
                        if (i17 < i10) {
                            iZzj = zzao.zzj(bArr, i17, zzanVar);
                            if (i11 == zzanVar.zza) {
                                zzbuVar2.zze(Float.intBitsToFloat(zzao.zzb(bArr, iZzj)));
                            }
                        }
                    }
                    return i17;
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i13 == 2) {
                    zzcu zzcuVar = (zzcu) zzcfVarZzd;
                    int iZzj4 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i22 = zzanVar.zza + iZzj4;
                    while (iZzj4 < i22) {
                        iZzj4 = zzao.zzm(bArr, iZzj4, zzanVar);
                        zzcuVar.zzf(zzanVar.zzb);
                    }
                    if (iZzj4 == i22) {
                        return iZzj4;
                    }
                    throw zzci.zzg();
                }
                if (i13 == 0) {
                    zzcu zzcuVar2 = (zzcu) zzcfVarZzd;
                    int iZzm = zzao.zzm(bArr, iZzj, zzanVar);
                    zzcuVar2.zzf(zzanVar.zzb);
                    while (iZzm < i10) {
                        int iZzj5 = zzao.zzj(bArr, iZzm, zzanVar);
                        if (i11 != zzanVar.zza) {
                            return iZzm;
                        }
                        iZzm = zzao.zzm(bArr, iZzj5, zzanVar);
                        zzcuVar2.zzf(zzanVar.zzb);
                    }
                    return iZzm;
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i13 == 2) {
                    return zzao.zzf(bArr, iZzj, zzcfVarZzd, zzanVar);
                }
                if (i13 == 0) {
                    return zzao.zzl(i11, bArr, i9, i10, zzcfVarZzd, zzanVar);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i13 == 2) {
                    zzcu zzcuVar3 = (zzcu) zzcfVarZzd;
                    int iZzj6 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i23 = zzanVar.zza + iZzj6;
                    while (iZzj6 < i23) {
                        zzcuVar3.zzf(zzao.zzp(bArr, iZzj6));
                        iZzj6 += 8;
                    }
                    if (iZzj6 == i23) {
                        return iZzj6;
                    }
                    throw zzci.zzg();
                }
                if (i13 == 1) {
                    zzcu zzcuVar4 = (zzcu) zzcfVarZzd;
                    zzcuVar4.zzf(zzao.zzp(bArr, i9));
                    while (true) {
                        i18 = iZzj + 8;
                        if (i18 < i10) {
                            iZzj = zzao.zzj(bArr, i18, zzanVar);
                            if (i11 == zzanVar.zza) {
                                zzcuVar4.zzf(zzao.zzp(bArr, iZzj));
                            }
                        }
                    }
                    return i18;
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i13 == 2) {
                    zzcc zzccVar = (zzcc) zzcfVarZzd;
                    int iZzj7 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i24 = zzanVar.zza + iZzj7;
                    while (iZzj7 < i24) {
                        zzccVar.zzf(zzao.zzb(bArr, iZzj7));
                        iZzj7 += 4;
                    }
                    if (iZzj7 == i24) {
                        return iZzj7;
                    }
                    throw zzci.zzg();
                }
                if (i13 == 5) {
                    zzcc zzccVar2 = (zzcc) zzcfVarZzd;
                    zzccVar2.zzf(zzao.zzb(bArr, i9));
                    while (true) {
                        i19 = iZzj + 4;
                        if (i19 < i10) {
                            iZzj = zzao.zzj(bArr, i19, zzanVar);
                            if (i11 == zzanVar.zza) {
                                zzccVar2.zzf(zzao.zzb(bArr, iZzj));
                            }
                        }
                    }
                    return i19;
                }
                break;
            case 25:
            case 42:
                if (i13 == 2) {
                    zzap zzapVar = (zzap) zzcfVarZzd;
                    int iZzj8 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i25 = zzanVar.zza + iZzj8;
                    while (iZzj8 < i25) {
                        iZzj8 = zzao.zzm(bArr, iZzj8, zzanVar);
                        zzapVar.zze(zzanVar.zzb != 0);
                    }
                    if (iZzj8 == i25) {
                        return iZzj8;
                    }
                    throw zzci.zzg();
                }
                if (i13 == 0) {
                    zzap zzapVar2 = (zzap) zzcfVarZzd;
                    int iZzm2 = zzao.zzm(bArr, iZzj, zzanVar);
                    zzapVar2.zze(zzanVar.zzb != 0);
                    while (iZzm2 < i10) {
                        int iZzj9 = zzao.zzj(bArr, iZzm2, zzanVar);
                        if (i11 != zzanVar.zza) {
                            return iZzm2;
                        }
                        iZzm2 = zzao.zzm(bArr, iZzj9, zzanVar);
                        zzapVar2.zze(zzanVar.zzb != 0);
                    }
                    return iZzm2;
                }
                break;
            case 26:
                if (i13 == 2) {
                    if ((j9 & 536870912) == 0) {
                        int iZzj10 = zzao.zzj(bArr, iZzj, zzanVar);
                        int i26 = zzanVar.zza;
                        if (i26 < 0) {
                            throw zzci.zzd();
                        }
                        if (i26 == 0) {
                            zzcfVarZzd.add("");
                        } else {
                            zzcfVarZzd.add(new String(bArr, iZzj10, i26, zzcg.zzb));
                            iZzj10 += i26;
                        }
                        while (iZzj10 < i10) {
                            int iZzj11 = zzao.zzj(bArr, iZzj10, zzanVar);
                            if (i11 != zzanVar.zza) {
                                return iZzj10;
                            }
                            iZzj10 = zzao.zzj(bArr, iZzj11, zzanVar);
                            int i27 = zzanVar.zza;
                            if (i27 < 0) {
                                throw zzci.zzd();
                            }
                            if (i27 == 0) {
                                zzcfVarZzd.add("");
                            } else {
                                zzcfVarZzd.add(new String(bArr, iZzj10, i27, zzcg.zzb));
                                iZzj10 += i27;
                            }
                        }
                        return iZzj10;
                    }
                    int iZzj12 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i28 = zzanVar.zza;
                    if (i28 < 0) {
                        throw zzci.zzd();
                    }
                    if (i28 == 0) {
                        zzcfVarZzd.add("");
                    } else {
                        int i29 = iZzj12 + i28;
                        if (!zzev.zze(bArr, iZzj12, i29)) {
                            throw zzci.zzc();
                        }
                        zzcfVarZzd.add(new String(bArr, iZzj12, i28, zzcg.zzb));
                        iZzj12 = i29;
                    }
                    while (iZzj12 < i10) {
                        int iZzj13 = zzao.zzj(bArr, iZzj12, zzanVar);
                        if (i11 != zzanVar.zza) {
                            return iZzj12;
                        }
                        iZzj12 = zzao.zzj(bArr, iZzj13, zzanVar);
                        int i30 = zzanVar.zza;
                        if (i30 < 0) {
                            throw zzci.zzd();
                        }
                        if (i30 == 0) {
                            zzcfVarZzd.add("");
                        } else {
                            int i31 = iZzj12 + i30;
                            if (!zzev.zze(bArr, iZzj12, i31)) {
                                throw zzci.zzc();
                            }
                            zzcfVarZzd.add(new String(bArr, iZzj12, i30, zzcg.zzb));
                            iZzj12 = i31;
                        }
                    }
                    return iZzj12;
                }
                break;
            case 27:
                if (i13 == 2) {
                    return zzao.zze(zzB(i14), i11, bArr, i9, i10, zzcfVarZzd, zzanVar);
                }
                break;
            case 28:
                if (i13 == 2) {
                    int iZzj14 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i32 = zzanVar.zza;
                    if (i32 < 0) {
                        throw zzci.zzd();
                    }
                    if (i32 > bArr.length - iZzj14) {
                        throw zzci.zzg();
                    }
                    if (i32 == 0) {
                        zzcfVarZzd.add(zzba.zzb);
                    } else {
                        zzcfVarZzd.add(zzba.zzl(bArr, iZzj14, i32));
                        iZzj14 += i32;
                    }
                    while (iZzj14 < i10) {
                        int iZzj15 = zzao.zzj(bArr, iZzj14, zzanVar);
                        if (i11 != zzanVar.zza) {
                            return iZzj14;
                        }
                        iZzj14 = zzao.zzj(bArr, iZzj15, zzanVar);
                        int i33 = zzanVar.zza;
                        if (i33 < 0) {
                            throw zzci.zzd();
                        }
                        if (i33 > bArr.length - iZzj14) {
                            throw zzci.zzg();
                        }
                        if (i33 == 0) {
                            zzcfVarZzd.add(zzba.zzb);
                        } else {
                            zzcfVarZzd.add(zzba.zzl(bArr, iZzj14, i33));
                            iZzj14 += i33;
                        }
                    }
                    return iZzj14;
                }
                break;
            case 30:
            case 44:
                if (i13 == 2) {
                    iZzl = zzao.zzf(bArr, iZzj, zzcfVarZzd, zzanVar);
                } else if (i13 == 0) {
                    iZzl = zzao.zzl(i11, bArr, i9, i10, zzcfVarZzd, zzanVar);
                }
                zzce zzceVarZzA = zzA(i14);
                zzeg zzegVar = this.zzm;
                int i34 = zzdr.zza;
                if (zzceVarZzA != null) {
                    Object objZzA = null;
                    if (zzcfVarZzd instanceof RandomAccess) {
                        int size2 = zzcfVarZzd.size();
                        int i35 = 0;
                        for (int i36 = 0; i36 < size2; i36++) {
                            int iIntValue = ((Integer) zzcfVarZzd.get(i36)).intValue();
                            if (zzceVarZzA.zza(iIntValue)) {
                                if (i36 != i35) {
                                    zzcfVarZzd.set(i35, Integer.valueOf(iIntValue));
                                }
                                i35++;
                            } else {
                                objZzA = zzdr.zzA(obj, i12, iIntValue, objZzA, zzegVar);
                            }
                        }
                        if (i35 != size2) {
                            zzcfVarZzd.subList(i35, size2).clear();
                            return iZzl;
                        }
                    } else {
                        Iterator it = zzcfVarZzd.iterator();
                        while (it.hasNext()) {
                            int iIntValue2 = ((Integer) it.next()).intValue();
                            if (!zzceVarZzA.zza(iIntValue2)) {
                                objZzA = zzdr.zzA(obj, i12, iIntValue2, objZzA, zzegVar);
                                it.remove();
                            }
                        }
                    }
                }
                return iZzl;
            case 33:
            case 47:
                if (i13 == 2) {
                    zzcc zzccVar3 = (zzcc) zzcfVarZzd;
                    int iZzj16 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i37 = zzanVar.zza + iZzj16;
                    while (iZzj16 < i37) {
                        iZzj16 = zzao.zzj(bArr, iZzj16, zzanVar);
                        zzccVar3.zzf(zzbe.zzb(zzanVar.zza));
                    }
                    if (iZzj16 == i37) {
                        return iZzj16;
                    }
                    throw zzci.zzg();
                }
                if (i13 == 0) {
                    zzcc zzccVar4 = (zzcc) zzcfVarZzd;
                    int iZzj17 = zzao.zzj(bArr, iZzj, zzanVar);
                    zzccVar4.zzf(zzbe.zzb(zzanVar.zza));
                    while (iZzj17 < i10) {
                        int iZzj18 = zzao.zzj(bArr, iZzj17, zzanVar);
                        if (i11 != zzanVar.zza) {
                            return iZzj17;
                        }
                        iZzj17 = zzao.zzj(bArr, iZzj18, zzanVar);
                        zzccVar4.zzf(zzbe.zzb(zzanVar.zza));
                    }
                    return iZzj17;
                }
                break;
            case 34:
            case 48:
                if (i13 == 2) {
                    zzcu zzcuVar5 = (zzcu) zzcfVarZzd;
                    int iZzj19 = zzao.zzj(bArr, iZzj, zzanVar);
                    int i38 = zzanVar.zza + iZzj19;
                    while (iZzj19 < i38) {
                        iZzj19 = zzao.zzm(bArr, iZzj19, zzanVar);
                        zzcuVar5.zzf(zzbe.zzc(zzanVar.zzb));
                    }
                    if (iZzj19 == i38) {
                        return iZzj19;
                    }
                    throw zzci.zzg();
                }
                if (i13 == 0) {
                    zzcu zzcuVar6 = (zzcu) zzcfVarZzd;
                    int iZzm3 = zzao.zzm(bArr, iZzj, zzanVar);
                    zzcuVar6.zzf(zzbe.zzc(zzanVar.zzb));
                    while (iZzm3 < i10) {
                        int iZzj20 = zzao.zzj(bArr, iZzm3, zzanVar);
                        if (i11 != zzanVar.zza) {
                            return iZzm3;
                        }
                        iZzm3 = zzao.zzm(bArr, iZzj20, zzanVar);
                        zzcuVar6.zzf(zzbe.zzc(zzanVar.zzb));
                    }
                    return iZzm3;
                }
                break;
            default:
                if (i13 == 3) {
                    zzdp zzdpVarZzB = zzB(i14);
                    int i39 = (i11 & (-8)) | 4;
                    int iZzc = zzao.zzc(zzdpVarZzB, bArr, i9, i10, i39, zzanVar);
                    zzcfVarZzd.add(zzanVar.zzc);
                    while (iZzc < i10) {
                        int iZzj21 = zzao.zzj(bArr, iZzc, zzanVar);
                        if (i11 != zzanVar.zza) {
                            return iZzc;
                        }
                        iZzc = zzao.zzc(zzdpVarZzB, bArr, iZzj21, i10, i39, zzanVar);
                        zzcfVarZzd.add(zzanVar.zzc);
                    }
                    return iZzc;
                }
                break;
        }
        return iZzj;
    }

    private final int zzt(int i9) {
        if (i9 < this.zze || i9 > this.zzf) {
            return -1;
        }
        return zzw(i9, 0);
    }

    private final int zzu(int i9, int i10) {
        if (i9 < this.zze || i9 > this.zzf) {
            return -1;
        }
        return zzw(i9, i10);
    }

    private final int zzv(int i9) {
        return this.zzc[i9 + 2];
    }

    private final int zzw(int i9, int i10) {
        int length = (this.zzc.length / 3) - 1;
        while (i10 <= length) {
            int i11 = (length + i10) >>> 1;
            int i12 = i11 * 3;
            int i13 = this.zzc[i12];
            if (i9 == i13) {
                return i12;
            }
            if (i9 < i13) {
                length = i11 - 1;
            } else {
                i10 = i11 + 1;
            }
        }
        return -1;
    }

    private static int zzx(int i9) {
        return (i9 >>> 20) & 255;
    }

    private final int zzy(int i9) {
        return this.zzc[i9 + 1];
    }

    private static long zzz(Object obj, long j9) {
        return ((Long) zzeq.zzf(obj, j9)).longValue();
    }

    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final int zza(Object obj) {
        int iZzx;
        int iZzx2;
        int iZzy;
        int iZzx3;
        int iZzx4;
        int iZzx5;
        int iZzx6;
        int iZzn;
        int iZzx7;
        int iZzy2;
        int iZzx8;
        int iZzx9;
        zzew zzewVar = zzew.zza;
        if (this.zzo - 1 == 0) {
            return zzo(obj);
        }
        Unsafe unsafe = zzb;
        int i9 = 0;
        for (int i10 = 0; i10 < this.zzc.length; i10 += 3) {
            int iZzy3 = zzy(i10);
            int iZzx10 = zzx(iZzy3);
            int i11 = this.zzc[i10];
            int i12 = iZzy3 & 1048575;
            if (iZzx10 >= zzbt.zzJ.zza() && iZzx10 <= zzbt.zzW.zza()) {
                int i13 = this.zzc[i10 + 2];
            }
            long j9 = i12;
            switch (iZzx10) {
                case 0:
                    if (zzP(obj, i10)) {
                        iZzx = zzbi.zzx(i11 << 3);
                        iZzn = iZzx + 8;
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzP(obj, i10)) {
                        iZzx2 = zzbi.zzx(i11 << 3);
                        iZzn = iZzx2 + 4;
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzP(obj, i10)) {
                        iZzy = zzbi.zzy(zzeq.zzd(obj, j9));
                        iZzx3 = zzbi.zzx(i11 << 3);
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzP(obj, i10)) {
                        iZzy = zzbi.zzy(zzeq.zzd(obj, j9));
                        iZzx3 = zzbi.zzx(i11 << 3);
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzP(obj, i10)) {
                        iZzy = zzbi.zzu(zzeq.zzc(obj, j9));
                        iZzx3 = zzbi.zzx(i11 << 3);
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzP(obj, i10)) {
                        iZzx = zzbi.zzx(i11 << 3);
                        iZzn = iZzx + 8;
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzP(obj, i10)) {
                        iZzx2 = zzbi.zzx(i11 << 3);
                        iZzn = iZzx2 + 4;
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzP(obj, i10)) {
                        iZzx4 = zzbi.zzx(i11 << 3);
                        iZzn = iZzx4 + 1;
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzP(obj, i10)) {
                        Object objZzf = zzeq.zzf(obj, j9);
                        if (objZzf instanceof zzba) {
                            int i14 = i11 << 3;
                            int i15 = zzbi.zzb;
                            int iZzd = ((zzba) objZzf).zzd();
                            iZzx5 = zzbi.zzx(iZzd) + iZzd;
                            iZzx6 = zzbi.zzx(i14);
                            iZzn = iZzx6 + iZzx5;
                            i9 += iZzn;
                            break;
                        } else {
                            iZzy = zzbi.zzw((String) objZzf);
                            iZzx3 = zzbi.zzx(i11 << 3);
                            i9 += iZzx3 + iZzy;
                            break;
                        }
                    } else {
                        break;
                    }
                case 9:
                    if (zzP(obj, i10)) {
                        iZzn = zzdr.zzn(i11, zzeq.zzf(obj, j9), zzB(i10));
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (zzP(obj, i10)) {
                        zzba zzbaVar = (zzba) zzeq.zzf(obj, j9);
                        int i16 = i11 << 3;
                        int i17 = zzbi.zzb;
                        int iZzd2 = zzbaVar.zzd();
                        iZzx5 = zzbi.zzx(iZzd2) + iZzd2;
                        iZzx6 = zzbi.zzx(i16);
                        iZzn = iZzx6 + iZzx5;
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzP(obj, i10)) {
                        iZzy = zzbi.zzx(zzeq.zzc(obj, j9));
                        iZzx3 = zzbi.zzx(i11 << 3);
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzP(obj, i10)) {
                        iZzy = zzbi.zzu(zzeq.zzc(obj, j9));
                        iZzx3 = zzbi.zzx(i11 << 3);
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzP(obj, i10)) {
                        iZzx2 = zzbi.zzx(i11 << 3);
                        iZzn = iZzx2 + 4;
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzP(obj, i10)) {
                        iZzx = zzbi.zzx(i11 << 3);
                        iZzn = iZzx + 8;
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzP(obj, i10)) {
                        int iZzc = zzeq.zzc(obj, j9);
                        iZzx3 = zzbi.zzx(i11 << 3);
                        iZzy = zzbi.zzx((iZzc >> 31) ^ (iZzc + iZzc));
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzP(obj, i10)) {
                        long jZzd = zzeq.zzd(obj, j9);
                        iZzx7 = zzbi.zzx(i11 << 3);
                        iZzy2 = zzbi.zzy((jZzd + jZzd) ^ (jZzd >> 63));
                        iZzn = iZzx7 + iZzy2;
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (zzP(obj, i10)) {
                        iZzn = zzbi.zzt(i11, (zzdf) zzeq.zzf(obj, j9), zzB(i10));
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    iZzn = zzdr.zzg(i11, (List) zzeq.zzf(obj, j9), false);
                    i9 += iZzn;
                    break;
                case 19:
                    iZzn = zzdr.zze(i11, (List) zzeq.zzf(obj, j9), false);
                    i9 += iZzn;
                    break;
                case 20:
                    iZzn = zzdr.zzl(i11, (List) zzeq.zzf(obj, j9), false);
                    i9 += iZzn;
                    break;
                case 21:
                    iZzn = zzdr.zzw(i11, (List) zzeq.zzf(obj, j9), false);
                    i9 += iZzn;
                    break;
                case 22:
                    iZzn = zzdr.zzj(i11, (List) zzeq.zzf(obj, j9), false);
                    i9 += iZzn;
                    break;
                case 23:
                    iZzn = zzdr.zzg(i11, (List) zzeq.zzf(obj, j9), false);
                    i9 += iZzn;
                    break;
                case 24:
                    iZzn = zzdr.zze(i11, (List) zzeq.zzf(obj, j9), false);
                    i9 += iZzn;
                    break;
                case 25:
                    iZzn = zzdr.zza(i11, (List) zzeq.zzf(obj, j9), false);
                    i9 += iZzn;
                    break;
                case 26:
                    iZzn = zzdr.zzt(i11, (List) zzeq.zzf(obj, j9));
                    i9 += iZzn;
                    break;
                case 27:
                    iZzn = zzdr.zzo(i11, (List) zzeq.zzf(obj, j9), zzB(i10));
                    i9 += iZzn;
                    break;
                case 28:
                    iZzn = zzdr.zzb(i11, (List) zzeq.zzf(obj, j9));
                    i9 += iZzn;
                    break;
                case 29:
                    iZzn = zzdr.zzu(i11, (List) zzeq.zzf(obj, j9), false);
                    i9 += iZzn;
                    break;
                case 30:
                    iZzn = zzdr.zzc(i11, (List) zzeq.zzf(obj, j9), false);
                    i9 += iZzn;
                    break;
                case 31:
                    iZzn = zzdr.zze(i11, (List) zzeq.zzf(obj, j9), false);
                    i9 += iZzn;
                    break;
                case 32:
                    iZzn = zzdr.zzg(i11, (List) zzeq.zzf(obj, j9), false);
                    i9 += iZzn;
                    break;
                case 33:
                    iZzn = zzdr.zzp(i11, (List) zzeq.zzf(obj, j9), false);
                    i9 += iZzn;
                    break;
                case 34:
                    iZzn = zzdr.zzr(i11, (List) zzeq.zzf(obj, j9), false);
                    i9 += iZzn;
                    break;
                case 35:
                    iZzy = zzdr.zzh((List) unsafe.getObject(obj, j9));
                    if (iZzy > 0) {
                        int i18 = i11 << 3;
                        iZzx8 = zzbi.zzx(iZzy);
                        iZzx9 = zzbi.zzx(i18);
                        iZzx3 = iZzx9 + iZzx8;
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    iZzy = zzdr.zzf((List) unsafe.getObject(obj, j9));
                    if (iZzy > 0) {
                        int i19 = i11 << 3;
                        iZzx8 = zzbi.zzx(iZzy);
                        iZzx9 = zzbi.zzx(i19);
                        iZzx3 = iZzx9 + iZzx8;
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    iZzy = zzdr.zzm((List) unsafe.getObject(obj, j9));
                    if (iZzy > 0) {
                        int i20 = i11 << 3;
                        iZzx8 = zzbi.zzx(iZzy);
                        iZzx9 = zzbi.zzx(i20);
                        iZzx3 = iZzx9 + iZzx8;
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    iZzy = zzdr.zzx((List) unsafe.getObject(obj, j9));
                    if (iZzy > 0) {
                        int i21 = i11 << 3;
                        iZzx8 = zzbi.zzx(iZzy);
                        iZzx9 = zzbi.zzx(i21);
                        iZzx3 = iZzx9 + iZzx8;
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    iZzy = zzdr.zzk((List) unsafe.getObject(obj, j9));
                    if (iZzy > 0) {
                        int i22 = i11 << 3;
                        iZzx8 = zzbi.zzx(iZzy);
                        iZzx9 = zzbi.zzx(i22);
                        iZzx3 = iZzx9 + iZzx8;
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    iZzy = zzdr.zzh((List) unsafe.getObject(obj, j9));
                    if (iZzy > 0) {
                        int i23 = i11 << 3;
                        iZzx8 = zzbi.zzx(iZzy);
                        iZzx9 = zzbi.zzx(i23);
                        iZzx3 = iZzx9 + iZzx8;
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    iZzy = zzdr.zzf((List) unsafe.getObject(obj, j9));
                    if (iZzy > 0) {
                        int i24 = i11 << 3;
                        iZzx8 = zzbi.zzx(iZzy);
                        iZzx9 = zzbi.zzx(i24);
                        iZzx3 = iZzx9 + iZzx8;
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    List list = (List) unsafe.getObject(obj, j9);
                    int i25 = zzdr.zza;
                    iZzy = list.size();
                    if (iZzy > 0) {
                        int i26 = i11 << 3;
                        iZzx8 = zzbi.zzx(iZzy);
                        iZzx9 = zzbi.zzx(i26);
                        iZzx3 = iZzx9 + iZzx8;
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    iZzy = zzdr.zzv((List) unsafe.getObject(obj, j9));
                    if (iZzy > 0) {
                        int i27 = i11 << 3;
                        iZzx8 = zzbi.zzx(iZzy);
                        iZzx9 = zzbi.zzx(i27);
                        iZzx3 = iZzx9 + iZzx8;
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    iZzy = zzdr.zzd((List) unsafe.getObject(obj, j9));
                    if (iZzy > 0) {
                        int i28 = i11 << 3;
                        iZzx8 = zzbi.zzx(iZzy);
                        iZzx9 = zzbi.zzx(i28);
                        iZzx3 = iZzx9 + iZzx8;
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    iZzy = zzdr.zzf((List) unsafe.getObject(obj, j9));
                    if (iZzy > 0) {
                        int i29 = i11 << 3;
                        iZzx8 = zzbi.zzx(iZzy);
                        iZzx9 = zzbi.zzx(i29);
                        iZzx3 = iZzx9 + iZzx8;
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    iZzy = zzdr.zzh((List) unsafe.getObject(obj, j9));
                    if (iZzy > 0) {
                        int i30 = i11 << 3;
                        iZzx8 = zzbi.zzx(iZzy);
                        iZzx9 = zzbi.zzx(i30);
                        iZzx3 = iZzx9 + iZzx8;
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    iZzy = zzdr.zzq((List) unsafe.getObject(obj, j9));
                    if (iZzy > 0) {
                        int i31 = i11 << 3;
                        iZzx8 = zzbi.zzx(iZzy);
                        iZzx9 = zzbi.zzx(i31);
                        iZzx3 = iZzx9 + iZzx8;
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    iZzy = zzdr.zzs((List) unsafe.getObject(obj, j9));
                    if (iZzy > 0) {
                        int i32 = i11 << 3;
                        iZzx8 = zzbi.zzx(iZzy);
                        iZzx9 = zzbi.zzx(i32);
                        iZzx3 = iZzx9 + iZzx8;
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    iZzn = zzdr.zzi(i11, (List) zzeq.zzf(obj, j9), zzB(i10));
                    i9 += iZzn;
                    break;
                case 50:
                    zzda.zza(i11, zzeq.zzf(obj, j9), zzC(i10));
                    break;
                case 51:
                    if (zzT(obj, i11, i10)) {
                        iZzx = zzbi.zzx(i11 << 3);
                        iZzn = iZzx + 8;
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzT(obj, i11, i10)) {
                        iZzx2 = zzbi.zzx(i11 << 3);
                        iZzn = iZzx2 + 4;
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzT(obj, i11, i10)) {
                        iZzy = zzbi.zzy(zzz(obj, j9));
                        iZzx3 = zzbi.zzx(i11 << 3);
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzT(obj, i11, i10)) {
                        iZzy = zzbi.zzy(zzz(obj, j9));
                        iZzx3 = zzbi.zzx(i11 << 3);
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzT(obj, i11, i10)) {
                        iZzy = zzbi.zzu(zzp(obj, j9));
                        iZzx3 = zzbi.zzx(i11 << 3);
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzT(obj, i11, i10)) {
                        iZzx = zzbi.zzx(i11 << 3);
                        iZzn = iZzx + 8;
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzT(obj, i11, i10)) {
                        iZzx2 = zzbi.zzx(i11 << 3);
                        iZzn = iZzx2 + 4;
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzT(obj, i11, i10)) {
                        iZzx4 = zzbi.zzx(i11 << 3);
                        iZzn = iZzx4 + 1;
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzT(obj, i11, i10)) {
                        Object objZzf2 = zzeq.zzf(obj, j9);
                        if (objZzf2 instanceof zzba) {
                            int i33 = i11 << 3;
                            int i34 = zzbi.zzb;
                            int iZzd3 = ((zzba) objZzf2).zzd();
                            iZzx5 = zzbi.zzx(iZzd3) + iZzd3;
                            iZzx6 = zzbi.zzx(i33);
                            iZzn = iZzx6 + iZzx5;
                            i9 += iZzn;
                            break;
                        } else {
                            iZzy = zzbi.zzw((String) objZzf2);
                            iZzx3 = zzbi.zzx(i11 << 3);
                            i9 += iZzx3 + iZzy;
                            break;
                        }
                    } else {
                        break;
                    }
                case 60:
                    if (zzT(obj, i11, i10)) {
                        iZzn = zzdr.zzn(i11, zzeq.zzf(obj, j9), zzB(i10));
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzT(obj, i11, i10)) {
                        zzba zzbaVar2 = (zzba) zzeq.zzf(obj, j9);
                        int i35 = i11 << 3;
                        int i36 = zzbi.zzb;
                        int iZzd4 = zzbaVar2.zzd();
                        iZzx5 = zzbi.zzx(iZzd4) + iZzd4;
                        iZzx6 = zzbi.zzx(i35);
                        iZzn = iZzx6 + iZzx5;
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzT(obj, i11, i10)) {
                        iZzy = zzbi.zzx(zzp(obj, j9));
                        iZzx3 = zzbi.zzx(i11 << 3);
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzT(obj, i11, i10)) {
                        iZzy = zzbi.zzu(zzp(obj, j9));
                        iZzx3 = zzbi.zzx(i11 << 3);
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzT(obj, i11, i10)) {
                        iZzx2 = zzbi.zzx(i11 << 3);
                        iZzn = iZzx2 + 4;
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzT(obj, i11, i10)) {
                        iZzx = zzbi.zzx(i11 << 3);
                        iZzn = iZzx + 8;
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzT(obj, i11, i10)) {
                        int iZzp = zzp(obj, j9);
                        iZzx3 = zzbi.zzx(i11 << 3);
                        iZzy = zzbi.zzx((iZzp >> 31) ^ (iZzp + iZzp));
                        i9 += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzT(obj, i11, i10)) {
                        long jZzz = zzz(obj, j9);
                        iZzx7 = zzbi.zzx(i11 << 3);
                        iZzy2 = zzbi.zzy((jZzz + jZzz) ^ (jZzz >> 63));
                        iZzn = iZzx7 + iZzy2;
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzT(obj, i11, i10)) {
                        iZzn = zzbi.zzt(i11, (zzdf) zzeq.zzf(obj, j9), zzB(i10));
                        i9 += iZzn;
                        break;
                    } else {
                        break;
                    }
            }
        }
        zzeg zzegVar = this.zzm;
        return i9 + zzegVar.zza(zzegVar.zzd(obj));
    }

    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final int zzb(Object obj) {
        int i9;
        long jDoubleToLongBits;
        int iFloatToIntBits;
        int length = this.zzc.length;
        int i10 = 0;
        for (int i11 = 0; i11 < length; i11 += 3) {
            int iZzy = zzy(i11);
            int i12 = this.zzc[i11];
            long j9 = 1048575 & iZzy;
            int iHashCode = 37;
            switch (zzx(iZzy)) {
                case 0:
                    i9 = i10 * 53;
                    jDoubleToLongBits = Double.doubleToLongBits(zzeq.zza(obj, j9));
                    byte[] bArr = zzcg.zzd;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i10 = i9 + iFloatToIntBits;
                    break;
                case 1:
                    i9 = i10 * 53;
                    iFloatToIntBits = Float.floatToIntBits(zzeq.zzb(obj, j9));
                    i10 = i9 + iFloatToIntBits;
                    break;
                case 2:
                    i9 = i10 * 53;
                    jDoubleToLongBits = zzeq.zzd(obj, j9);
                    byte[] bArr2 = zzcg.zzd;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i10 = i9 + iFloatToIntBits;
                    break;
                case 3:
                    i9 = i10 * 53;
                    jDoubleToLongBits = zzeq.zzd(obj, j9);
                    byte[] bArr3 = zzcg.zzd;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i10 = i9 + iFloatToIntBits;
                    break;
                case 4:
                    i9 = i10 * 53;
                    iFloatToIntBits = zzeq.zzc(obj, j9);
                    i10 = i9 + iFloatToIntBits;
                    break;
                case 5:
                    i9 = i10 * 53;
                    jDoubleToLongBits = zzeq.zzd(obj, j9);
                    byte[] bArr4 = zzcg.zzd;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i10 = i9 + iFloatToIntBits;
                    break;
                case 6:
                    i9 = i10 * 53;
                    iFloatToIntBits = zzeq.zzc(obj, j9);
                    i10 = i9 + iFloatToIntBits;
                    break;
                case 7:
                    i9 = i10 * 53;
                    iFloatToIntBits = zzcg.zza(zzeq.zzw(obj, j9));
                    i10 = i9 + iFloatToIntBits;
                    break;
                case 8:
                    i9 = i10 * 53;
                    iFloatToIntBits = ((String) zzeq.zzf(obj, j9)).hashCode();
                    i10 = i9 + iFloatToIntBits;
                    break;
                case 9:
                    Object objZzf = zzeq.zzf(obj, j9);
                    if (objZzf != null) {
                        iHashCode = objZzf.hashCode();
                    }
                    i10 = (i10 * 53) + iHashCode;
                    break;
                case 10:
                    i9 = i10 * 53;
                    iFloatToIntBits = zzeq.zzf(obj, j9).hashCode();
                    i10 = i9 + iFloatToIntBits;
                    break;
                case 11:
                    i9 = i10 * 53;
                    iFloatToIntBits = zzeq.zzc(obj, j9);
                    i10 = i9 + iFloatToIntBits;
                    break;
                case 12:
                    i9 = i10 * 53;
                    iFloatToIntBits = zzeq.zzc(obj, j9);
                    i10 = i9 + iFloatToIntBits;
                    break;
                case 13:
                    i9 = i10 * 53;
                    iFloatToIntBits = zzeq.zzc(obj, j9);
                    i10 = i9 + iFloatToIntBits;
                    break;
                case 14:
                    i9 = i10 * 53;
                    jDoubleToLongBits = zzeq.zzd(obj, j9);
                    byte[] bArr5 = zzcg.zzd;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i10 = i9 + iFloatToIntBits;
                    break;
                case 15:
                    i9 = i10 * 53;
                    iFloatToIntBits = zzeq.zzc(obj, j9);
                    i10 = i9 + iFloatToIntBits;
                    break;
                case 16:
                    i9 = i10 * 53;
                    jDoubleToLongBits = zzeq.zzd(obj, j9);
                    byte[] bArr6 = zzcg.zzd;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i10 = i9 + iFloatToIntBits;
                    break;
                case 17:
                    Object objZzf2 = zzeq.zzf(obj, j9);
                    if (objZzf2 != null) {
                        iHashCode = objZzf2.hashCode();
                    }
                    i10 = (i10 * 53) + iHashCode;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i9 = i10 * 53;
                    iFloatToIntBits = zzeq.zzf(obj, j9).hashCode();
                    i10 = i9 + iFloatToIntBits;
                    break;
                case 50:
                    i9 = i10 * 53;
                    iFloatToIntBits = zzeq.zzf(obj, j9).hashCode();
                    i10 = i9 + iFloatToIntBits;
                    break;
                case 51:
                    if (zzT(obj, i12, i11)) {
                        i9 = i10 * 53;
                        jDoubleToLongBits = Double.doubleToLongBits(zzm(obj, j9));
                        byte[] bArr7 = zzcg.zzd;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i10 = i9 + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzT(obj, i12, i11)) {
                        i9 = i10 * 53;
                        iFloatToIntBits = Float.floatToIntBits(zzn(obj, j9));
                        i10 = i9 + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzT(obj, i12, i11)) {
                        i9 = i10 * 53;
                        jDoubleToLongBits = zzz(obj, j9);
                        byte[] bArr8 = zzcg.zzd;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i10 = i9 + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzT(obj, i12, i11)) {
                        i9 = i10 * 53;
                        jDoubleToLongBits = zzz(obj, j9);
                        byte[] bArr9 = zzcg.zzd;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i10 = i9 + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzT(obj, i12, i11)) {
                        i9 = i10 * 53;
                        iFloatToIntBits = zzp(obj, j9);
                        i10 = i9 + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzT(obj, i12, i11)) {
                        i9 = i10 * 53;
                        jDoubleToLongBits = zzz(obj, j9);
                        byte[] bArr10 = zzcg.zzd;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i10 = i9 + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzT(obj, i12, i11)) {
                        i9 = i10 * 53;
                        iFloatToIntBits = zzp(obj, j9);
                        i10 = i9 + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzT(obj, i12, i11)) {
                        i9 = i10 * 53;
                        iFloatToIntBits = zzcg.zza(zzU(obj, j9));
                        i10 = i9 + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzT(obj, i12, i11)) {
                        i9 = i10 * 53;
                        iFloatToIntBits = ((String) zzeq.zzf(obj, j9)).hashCode();
                        i10 = i9 + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzT(obj, i12, i11)) {
                        i9 = i10 * 53;
                        iFloatToIntBits = zzeq.zzf(obj, j9).hashCode();
                        i10 = i9 + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzT(obj, i12, i11)) {
                        i9 = i10 * 53;
                        iFloatToIntBits = zzeq.zzf(obj, j9).hashCode();
                        i10 = i9 + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzT(obj, i12, i11)) {
                        i9 = i10 * 53;
                        iFloatToIntBits = zzp(obj, j9);
                        i10 = i9 + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzT(obj, i12, i11)) {
                        i9 = i10 * 53;
                        iFloatToIntBits = zzp(obj, j9);
                        i10 = i9 + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzT(obj, i12, i11)) {
                        i9 = i10 * 53;
                        iFloatToIntBits = zzp(obj, j9);
                        i10 = i9 + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzT(obj, i12, i11)) {
                        i9 = i10 * 53;
                        jDoubleToLongBits = zzz(obj, j9);
                        byte[] bArr11 = zzcg.zzd;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i10 = i9 + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzT(obj, i12, i11)) {
                        i9 = i10 * 53;
                        iFloatToIntBits = zzp(obj, j9);
                        i10 = i9 + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzT(obj, i12, i11)) {
                        i9 = i10 * 53;
                        jDoubleToLongBits = zzz(obj, j9);
                        byte[] bArr12 = zzcg.zzd;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i10 = i9 + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzT(obj, i12, i11)) {
                        i9 = i10 * 53;
                        iFloatToIntBits = zzeq.zzf(obj, j9).hashCode();
                        i10 = i9 + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int iHashCode2 = (i10 * 53) + this.zzm.zzd(obj).hashCode();
        if (!this.zzh) {
            return iHashCode2;
        }
        this.zzn.zza(obj);
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:109:0x030e, code lost:
    
        if (r0 != r22) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0310, code lost:
    
        r15 = r28;
        r14 = r29;
        r12 = r30;
        r1 = r31;
        r13 = r32;
        r11 = r33;
        r9 = r34;
        r8 = r19;
        r5 = r20;
        r3 = r21;
        r2 = r22;
        r6 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x032c, code lost:
    
        r2 = r0;
        r7 = r21;
        r6 = r24;
        r0 = r33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0360, code lost:
    
        if (r0 != r15) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x0388, code lost:
    
        if (r0 != r15) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x0403, code lost:
    
        if (r6 == 1048575) goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0405, code lost:
    
        r27.putInt(r12, r6, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x040b, code lost:
    
        r2 = r8.zzj;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x040f, code lost:
    
        if (r2 >= r8.zzk) goto L208;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x0411, code lost:
    
        r4 = r8.zzi[r2];
        r5 = r8.zzc[r4];
        r5 = com.google.android.gms.internal.play_billing.zzeq.zzf(r12, r8.zzy(r4) & 1048575);
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0423, code lost:
    
        if (r5 != null) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x042a, code lost:
    
        if (r8.zzA(r4) != null) goto L209;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x042c, code lost:
    
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x042f, code lost:
    
        r5 = (com.google.android.gms.internal.play_billing.zzcz) r5;
        r0 = (com.google.android.gms.internal.play_billing.zzcy) r8.zzC(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0437, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0438, code lost:
    
        if (r9 != 0) goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x043c, code lost:
    
        if (r0 != r32) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0443, code lost:
    
        throw com.google.android.gms.internal.play_billing.zzci.zze();
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0446, code lost:
    
        if (r0 > r32) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x0448, code lost:
    
        if (r3 != r9) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x044a, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x044f, code lost:
    
        throw com.google.android.gms.internal.play_billing.zzci.zze();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int zzc(Object obj, byte[] bArr, int i9, int i10, int i11, zzan zzanVar) throws zzci {
        Unsafe unsafe;
        int i12;
        Object obj2;
        zzdi<T> zzdiVar;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        Object obj3;
        int i21;
        zzan zzanVar2;
        int i22;
        int i23;
        int i24;
        int i25;
        int i26;
        int i27;
        int i28;
        int i29;
        int i30;
        int i31;
        int i32;
        int i33;
        zzdi<T> zzdiVar2 = this;
        Object obj4 = obj;
        byte[] bArr2 = bArr;
        int i34 = i10;
        int i35 = i11;
        zzan zzanVar3 = zzanVar;
        zzG(obj);
        Unsafe unsafe2 = zzb;
        int i36 = -1;
        int iZzi = i9;
        int i37 = -1;
        int i38 = 0;
        int i39 = 0;
        int i40 = 0;
        int i41 = 1048575;
        while (true) {
            if (iZzi < i34) {
                int i42 = iZzi + 1;
                byte b9 = bArr2[iZzi];
                if (b9 < 0) {
                    int iZzk = zzao.zzk(b9, bArr2, i42, zzanVar3);
                    i13 = zzanVar3.zza;
                    i42 = iZzk;
                } else {
                    i13 = b9;
                }
                int i43 = i13 >>> 3;
                int iZzu = i43 > i37 ? zzdiVar2.zzu(i43, i38 / 3) : zzdiVar2.zzt(i43);
                if (iZzu == i36) {
                    i14 = i43;
                    i15 = i42;
                    i16 = i13;
                    i17 = i40;
                    i18 = i36;
                    unsafe = unsafe2;
                    i19 = i35;
                    i20 = 0;
                } else {
                    int i44 = i13 & 7;
                    int[] iArr = zzdiVar2.zzc;
                    int i45 = iArr[iZzu + 1];
                    int iZzx = zzx(i45);
                    int i46 = i42;
                    int i47 = i13;
                    long j9 = i45 & 1048575;
                    if (iZzx <= 17) {
                        int i48 = iArr[iZzu + 2];
                        int i49 = 1 << (i48 >>> 20);
                        int i50 = i48 & 1048575;
                        if (i50 != i41) {
                            if (i41 != 1048575) {
                                unsafe2.putInt(obj4, i41, i40);
                            }
                            i23 = i50;
                            i22 = unsafe2.getInt(obj4, i50);
                        } else {
                            i22 = i40;
                            i23 = i41;
                        }
                        switch (iZzx) {
                            case 0:
                                i24 = iZzu;
                                i25 = i43;
                                i26 = i46;
                                if (i44 != 1) {
                                    i31 = i24;
                                    i19 = i11;
                                    i17 = i22;
                                    unsafe = unsafe2;
                                    i14 = i25;
                                    i20 = i31;
                                    i15 = i26;
                                    i16 = i47;
                                    i41 = i23;
                                    i18 = -1;
                                    break;
                                } else {
                                    zzeq.zzo(obj4, j9, Double.longBitsToDouble(zzao.zzp(bArr2, i26)));
                                    iZzi = i26 + 8;
                                    i40 = i22 | i49;
                                    i34 = i10;
                                    i38 = i24;
                                    i37 = i25;
                                    i39 = i47;
                                    i41 = i23;
                                    i36 = -1;
                                    i35 = i11;
                                    break;
                                }
                            case 1:
                                i24 = iZzu;
                                i25 = i43;
                                i26 = i46;
                                if (i44 != 5) {
                                    i31 = i24;
                                    i19 = i11;
                                    i17 = i22;
                                    unsafe = unsafe2;
                                    i14 = i25;
                                    i20 = i31;
                                    i15 = i26;
                                    i16 = i47;
                                    i41 = i23;
                                    i18 = -1;
                                    break;
                                } else {
                                    zzeq.zzp(obj4, j9, Float.intBitsToFloat(zzao.zzb(bArr2, i26)));
                                    iZzi = i26 + 4;
                                    i40 = i22 | i49;
                                    i34 = i10;
                                    i38 = i24;
                                    i37 = i25;
                                    i39 = i47;
                                    i41 = i23;
                                    i36 = -1;
                                    i35 = i11;
                                    break;
                                }
                            case 2:
                            case 3:
                                i24 = iZzu;
                                i25 = i43;
                                i26 = i46;
                                if (i44 != 0) {
                                    i31 = i24;
                                    i19 = i11;
                                    i17 = i22;
                                    unsafe = unsafe2;
                                    i14 = i25;
                                    i20 = i31;
                                    i15 = i26;
                                    i16 = i47;
                                    i41 = i23;
                                    i18 = -1;
                                    break;
                                } else {
                                    int iZzm = zzao.zzm(bArr2, i26, zzanVar3);
                                    unsafe2.putLong(obj, j9, zzanVar3.zzb);
                                    i40 = i22 | i49;
                                    i38 = i24;
                                    i37 = i25;
                                    iZzi = iZzm;
                                    i39 = i47;
                                    i41 = i23;
                                    i36 = -1;
                                    i34 = i10;
                                    i35 = i11;
                                    break;
                                }
                            case 4:
                            case 11:
                                i24 = iZzu;
                                i25 = i43;
                                i26 = i46;
                                if (i44 != 0) {
                                    i31 = i24;
                                    i19 = i11;
                                    i17 = i22;
                                    unsafe = unsafe2;
                                    i14 = i25;
                                    i20 = i31;
                                    i15 = i26;
                                    i16 = i47;
                                    i41 = i23;
                                    i18 = -1;
                                    break;
                                } else {
                                    iZzi = zzao.zzj(bArr2, i26, zzanVar3);
                                    unsafe2.putInt(obj4, j9, zzanVar3.zza);
                                    i40 = i22 | i49;
                                    i34 = i10;
                                    i38 = i24;
                                    i37 = i25;
                                    i39 = i47;
                                    i41 = i23;
                                    i36 = -1;
                                    i35 = i11;
                                    break;
                                }
                            case 5:
                            case 14:
                                i24 = iZzu;
                                i25 = i43;
                                i27 = i46;
                                if (i44 != 1) {
                                    i26 = i27;
                                    i31 = i24;
                                    i19 = i11;
                                    i17 = i22;
                                    unsafe = unsafe2;
                                    i14 = i25;
                                    i20 = i31;
                                    i15 = i26;
                                    i16 = i47;
                                    i41 = i23;
                                    i18 = -1;
                                    break;
                                } else {
                                    i26 = i27;
                                    unsafe2.putLong(obj, j9, zzao.zzp(bArr2, i27));
                                    iZzi = i26 + 8;
                                    i40 = i22 | i49;
                                    i34 = i10;
                                    i38 = i24;
                                    i37 = i25;
                                    i39 = i47;
                                    i41 = i23;
                                    i36 = -1;
                                    i35 = i11;
                                    break;
                                }
                            case 6:
                            case 13:
                                i24 = iZzu;
                                i25 = i43;
                                i27 = i46;
                                if (i44 != 5) {
                                    i26 = i27;
                                    i31 = i24;
                                    i19 = i11;
                                    i17 = i22;
                                    unsafe = unsafe2;
                                    i14 = i25;
                                    i20 = i31;
                                    i15 = i26;
                                    i16 = i47;
                                    i41 = i23;
                                    i18 = -1;
                                    break;
                                } else {
                                    unsafe2.putInt(obj4, j9, zzao.zzb(bArr2, i27));
                                    iZzi = i27 + 4;
                                    i40 = i22 | i49;
                                    i34 = i10;
                                    i38 = i24;
                                    i37 = i25;
                                    i39 = i47;
                                    i41 = i23;
                                    i36 = -1;
                                    i35 = i11;
                                    break;
                                }
                            case 7:
                                i24 = iZzu;
                                i25 = i43;
                                i27 = i46;
                                if (i44 != 0) {
                                    i26 = i27;
                                    i31 = i24;
                                    i19 = i11;
                                    i17 = i22;
                                    unsafe = unsafe2;
                                    i14 = i25;
                                    i20 = i31;
                                    i15 = i26;
                                    i16 = i47;
                                    i41 = i23;
                                    i18 = -1;
                                    break;
                                } else {
                                    iZzi = zzao.zzm(bArr2, i27, zzanVar3);
                                    zzeq.zzm(obj4, j9, zzanVar3.zzb != 0);
                                    i40 = i22 | i49;
                                    i34 = i10;
                                    i38 = i24;
                                    i37 = i25;
                                    i39 = i47;
                                    i41 = i23;
                                    i36 = -1;
                                    i35 = i11;
                                    break;
                                }
                            case 8:
                                i24 = iZzu;
                                i25 = i43;
                                i27 = i46;
                                if (i44 != 2) {
                                    i26 = i27;
                                    i31 = i24;
                                    i19 = i11;
                                    i17 = i22;
                                    unsafe = unsafe2;
                                    i14 = i25;
                                    i20 = i31;
                                    i15 = i26;
                                    i16 = i47;
                                    i41 = i23;
                                    i18 = -1;
                                    break;
                                } else {
                                    iZzi = (i45 & 536870912) == 0 ? zzao.zzg(bArr2, i27, zzanVar3) : zzao.zzh(bArr2, i27, zzanVar3);
                                    unsafe2.putObject(obj4, j9, zzanVar3.zzc);
                                    i40 = i22 | i49;
                                    i34 = i10;
                                    i38 = i24;
                                    i37 = i25;
                                    i39 = i47;
                                    i41 = i23;
                                    i36 = -1;
                                    i35 = i11;
                                    break;
                                }
                            case 9:
                                i24 = iZzu;
                                i25 = i43;
                                i27 = i46;
                                if (i44 != 2) {
                                    i47 = i47;
                                    i26 = i27;
                                    i31 = i24;
                                    i19 = i11;
                                    i17 = i22;
                                    unsafe = unsafe2;
                                    i14 = i25;
                                    i20 = i31;
                                    i15 = i26;
                                    i16 = i47;
                                    i41 = i23;
                                    i18 = -1;
                                    break;
                                } else {
                                    Object objZzD = zzdiVar2.zzD(obj4, i24);
                                    i47 = i47;
                                    iZzi = zzao.zzo(objZzD, zzdiVar2.zzB(i24), bArr, i27, i10, zzanVar);
                                    zzdiVar2.zzL(obj4, i24, objZzD);
                                    i40 = i22 | i49;
                                    i34 = i10;
                                    i38 = i24;
                                    i37 = i25;
                                    i39 = i47;
                                    i41 = i23;
                                    i36 = -1;
                                    i35 = i11;
                                    break;
                                }
                            case 10:
                                i28 = iZzu;
                                i25 = i43;
                                i29 = i47;
                                i30 = i46;
                                if (i44 != 2) {
                                    i31 = i28;
                                    i47 = i29;
                                    i26 = i30;
                                    i19 = i11;
                                    i17 = i22;
                                    unsafe = unsafe2;
                                    i14 = i25;
                                    i20 = i31;
                                    i15 = i26;
                                    i16 = i47;
                                    i41 = i23;
                                    i18 = -1;
                                    break;
                                } else {
                                    iZzi = zzao.zza(bArr2, i30, zzanVar3);
                                    unsafe2.putObject(obj4, j9, zzanVar3.zzc);
                                    i40 = i22 | i49;
                                    i38 = i28;
                                    i37 = i25;
                                    i39 = i29;
                                    i41 = i23;
                                    i36 = -1;
                                    i34 = i10;
                                    i35 = i11;
                                    break;
                                }
                            case 12:
                                i28 = iZzu;
                                i25 = i43;
                                i29 = i47;
                                i30 = i46;
                                if (i44 != 0) {
                                    i31 = i28;
                                    i47 = i29;
                                    i26 = i30;
                                    i19 = i11;
                                    i17 = i22;
                                    unsafe = unsafe2;
                                    i14 = i25;
                                    i20 = i31;
                                    i15 = i26;
                                    i16 = i47;
                                    i41 = i23;
                                    i18 = -1;
                                    break;
                                } else {
                                    iZzi = zzao.zzj(bArr2, i30, zzanVar3);
                                    int i51 = zzanVar3.zza;
                                    zzce zzceVarZzA = zzdiVar2.zzA(i28);
                                    if (zzceVarZzA == null || zzceVarZzA.zza(i51)) {
                                        unsafe2.putInt(obj4, j9, i51);
                                        i40 = i22 | i49;
                                        i38 = i28;
                                        i37 = i25;
                                        i39 = i29;
                                        i41 = i23;
                                        i36 = -1;
                                        i34 = i10;
                                        i35 = i11;
                                        break;
                                    } else {
                                        zzd(obj).zzj(i29, Long.valueOf(i51));
                                        i38 = i28;
                                        i40 = i22;
                                        i37 = i25;
                                        i39 = i29;
                                        i41 = i23;
                                        i36 = -1;
                                        i34 = i10;
                                        i35 = i11;
                                    }
                                }
                                break;
                            case 15:
                                i28 = iZzu;
                                i25 = i43;
                                i29 = i47;
                                i30 = i46;
                                if (i44 != 0) {
                                    i31 = i28;
                                    i47 = i29;
                                    i26 = i30;
                                    i19 = i11;
                                    i17 = i22;
                                    unsafe = unsafe2;
                                    i14 = i25;
                                    i20 = i31;
                                    i15 = i26;
                                    i16 = i47;
                                    i41 = i23;
                                    i18 = -1;
                                    break;
                                } else {
                                    iZzi = zzao.zzj(bArr2, i30, zzanVar3);
                                    unsafe2.putInt(obj4, j9, zzbe.zzb(zzanVar3.zza));
                                    i40 = i22 | i49;
                                    i38 = i28;
                                    i37 = i25;
                                    i39 = i29;
                                    i41 = i23;
                                    i36 = -1;
                                    i34 = i10;
                                    i35 = i11;
                                    break;
                                }
                            case 16:
                                if (i44 != 0) {
                                    i25 = i43;
                                    i31 = iZzu;
                                    i26 = i46;
                                    i19 = i11;
                                    i17 = i22;
                                    unsafe = unsafe2;
                                    i14 = i25;
                                    i20 = i31;
                                    i15 = i26;
                                    i16 = i47;
                                    i41 = i23;
                                    i18 = -1;
                                    break;
                                } else {
                                    int iZzm2 = zzao.zzm(bArr2, i46, zzanVar3);
                                    unsafe2.putLong(obj, j9, zzbe.zzc(zzanVar3.zzb));
                                    i40 = i22 | i49;
                                    i38 = iZzu;
                                    i37 = i43;
                                    i39 = i47;
                                    iZzi = iZzm2;
                                    i41 = i23;
                                    i36 = -1;
                                    i34 = i10;
                                    i35 = i11;
                                    break;
                                }
                            default:
                                i24 = iZzu;
                                i25 = i43;
                                i26 = i46;
                                if (i44 != 3) {
                                    i31 = i24;
                                    i19 = i11;
                                    i17 = i22;
                                    unsafe = unsafe2;
                                    i14 = i25;
                                    i20 = i31;
                                    i15 = i26;
                                    i16 = i47;
                                    i41 = i23;
                                    i18 = -1;
                                    break;
                                } else {
                                    Object objZzD2 = zzdiVar2.zzD(obj4, i24);
                                    iZzi = zzao.zzn(objZzD2, zzdiVar2.zzB(i24), bArr, i26, i10, (i25 << 3) | 4, zzanVar);
                                    zzdiVar2.zzL(obj4, i24, objZzD2);
                                    i40 = i22 | i49;
                                    i34 = i10;
                                    i37 = i25;
                                    i38 = i24;
                                    i39 = i47;
                                    i41 = i23;
                                    i36 = -1;
                                    bArr2 = bArr;
                                    i35 = i11;
                                    break;
                                }
                        }
                    } else {
                        if (iZzx != 27) {
                            i17 = i40;
                            i32 = i41;
                            if (iZzx <= 49) {
                                i14 = i43;
                                i18 = -1;
                                unsafe = unsafe2;
                                i20 = iZzu;
                                iZzi = zzs(obj, bArr, i46, i10, i47, i43, i44, iZzu, i45, iZzx, j9, zzanVar);
                            } else {
                                i14 = i43;
                                unsafe = unsafe2;
                                i20 = iZzu;
                                i33 = i46;
                                i18 = -1;
                                if (iZzx != 50) {
                                    iZzi = zzr(obj, bArr, i33, i10, i47, i14, i44, i45, iZzx, j9, i20, zzanVar);
                                } else if (i44 == 2) {
                                    iZzi = zzq(obj, bArr, i33, i10, i20, j9, zzanVar);
                                }
                            }
                        } else if (i44 == 2) {
                            zzcf zzcfVarZzd = (zzcf) unsafe2.getObject(obj4, j9);
                            if (!zzcfVarZzd.zzc()) {
                                int size = zzcfVarZzd.size();
                                zzcfVarZzd = zzcfVarZzd.zzd(size == 0 ? 10 : size + size);
                                unsafe2.putObject(obj4, j9, zzcfVarZzd);
                            }
                            iZzi = zzao.zze(zzdiVar2.zzB(iZzu), i47, bArr, i46, i10, zzcfVarZzd, zzanVar);
                            i34 = i10;
                            i35 = i11;
                            i37 = i43;
                            i38 = iZzu;
                            i40 = i40;
                            i39 = i47;
                            i41 = i41;
                            i36 = -1;
                            bArr2 = bArr;
                        } else {
                            i17 = i40;
                            i32 = i41;
                            i14 = i43;
                            unsafe = unsafe2;
                            i20 = iZzu;
                            i33 = i46;
                            i18 = -1;
                        }
                        i19 = i11;
                        i15 = i33;
                        i16 = i47;
                        i41 = i32;
                    }
                }
                if (i16 != i19 || i19 == 0) {
                    int i52 = i19;
                    if (this.zzh) {
                        zzanVar2 = zzanVar;
                        zzbn zzbnVar = zzanVar2.zzd;
                        if (zzbnVar != zzbn.zza) {
                            i21 = i14;
                            if (zzbnVar.zzb(this.zzg, i21) != null) {
                                throw null;
                            }
                            iZzi = zzao.zzi(i16, bArr, i15, i10, zzd(obj), zzanVar);
                            obj3 = obj;
                            i34 = i10;
                            i39 = i16;
                            zzdiVar2 = this;
                            i37 = i21;
                            obj4 = obj3;
                            i36 = i18;
                            i40 = i17;
                            i38 = i20;
                            bArr2 = bArr;
                            i35 = i52;
                            zzanVar3 = zzanVar2;
                            unsafe2 = unsafe;
                        } else {
                            obj3 = obj;
                            i21 = i14;
                        }
                    } else {
                        obj3 = obj;
                        i21 = i14;
                        zzanVar2 = zzanVar;
                    }
                    iZzi = zzao.zzi(i16, bArr, i15, i10, zzd(obj), zzanVar);
                    i34 = i10;
                    i39 = i16;
                    zzdiVar2 = this;
                    i37 = i21;
                    obj4 = obj3;
                    i36 = i18;
                    i40 = i17;
                    i38 = i20;
                    bArr2 = bArr;
                    i35 = i52;
                    zzanVar3 = zzanVar2;
                    unsafe2 = unsafe;
                } else {
                    zzdiVar = this;
                    obj2 = obj;
                    i12 = i19;
                    iZzi = i15;
                    i39 = i16;
                    i40 = i17;
                }
            } else {
                unsafe = unsafe2;
                i12 = i35;
                obj2 = obj4;
                zzdiVar = zzdiVar2;
            }
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final Object zze() {
        return ((zzcb) this.zzg).zzi();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x006c  */
    @Override // com.google.android.gms.internal.play_billing.zzdp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzf(Object obj) {
        if (zzS(obj)) {
            if (obj instanceof zzcb) {
                zzcb zzcbVar = (zzcb) obj;
                zzcbVar.zzq(Integer.MAX_VALUE);
                zzcbVar.zza = 0;
                zzcbVar.zzo();
            }
            int length = this.zzc.length;
            for (int i9 = 0; i9 < length; i9 += 3) {
                int iZzy = zzy(i9);
                int i10 = 1048575 & iZzy;
                int iZzx = zzx(iZzy);
                long j9 = i10;
                if (iZzx != 9) {
                    if (iZzx != 60 && iZzx != 68) {
                        switch (iZzx) {
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                            case 49:
                                this.zzl.zza(obj, j9);
                                break;
                            case 50:
                                Unsafe unsafe = zzb;
                                Object object = unsafe.getObject(obj, j9);
                                if (object != null) {
                                    ((zzcz) object).zzc();
                                    unsafe.putObject(obj, j9, object);
                                    break;
                                } else {
                                    break;
                                }
                        }
                    } else if (zzT(obj, this.zzc[i9], i9)) {
                        zzB(i9).zzf(zzb.getObject(obj, j9));
                    }
                } else if (zzP(obj, i9)) {
                    zzB(i9).zzf(zzb.getObject(obj, j9));
                }
            }
            this.zzm.zzg(obj);
            if (this.zzh) {
                this.zzn.zzb(obj);
            }
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final void zzg(Object obj, Object obj2) {
        zzG(obj);
        obj2.getClass();
        for (int i9 = 0; i9 < this.zzc.length; i9 += 3) {
            int iZzy = zzy(i9);
            int i10 = this.zzc[i9];
            long j9 = 1048575 & iZzy;
            switch (zzx(iZzy)) {
                case 0:
                    if (zzP(obj2, i9)) {
                        zzeq.zzo(obj, j9, zzeq.zza(obj2, j9));
                        zzJ(obj, i9);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzP(obj2, i9)) {
                        zzeq.zzp(obj, j9, zzeq.zzb(obj2, j9));
                        zzJ(obj, i9);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzP(obj2, i9)) {
                        zzeq.zzr(obj, j9, zzeq.zzd(obj2, j9));
                        zzJ(obj, i9);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzP(obj2, i9)) {
                        zzeq.zzr(obj, j9, zzeq.zzd(obj2, j9));
                        zzJ(obj, i9);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzP(obj2, i9)) {
                        zzeq.zzq(obj, j9, zzeq.zzc(obj2, j9));
                        zzJ(obj, i9);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzP(obj2, i9)) {
                        zzeq.zzr(obj, j9, zzeq.zzd(obj2, j9));
                        zzJ(obj, i9);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzP(obj2, i9)) {
                        zzeq.zzq(obj, j9, zzeq.zzc(obj2, j9));
                        zzJ(obj, i9);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzP(obj2, i9)) {
                        zzeq.zzm(obj, j9, zzeq.zzw(obj2, j9));
                        zzJ(obj, i9);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzP(obj2, i9)) {
                        zzeq.zzs(obj, j9, zzeq.zzf(obj2, j9));
                        zzJ(obj, i9);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzH(obj, obj2, i9);
                    break;
                case 10:
                    if (zzP(obj2, i9)) {
                        zzeq.zzs(obj, j9, zzeq.zzf(obj2, j9));
                        zzJ(obj, i9);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzP(obj2, i9)) {
                        zzeq.zzq(obj, j9, zzeq.zzc(obj2, j9));
                        zzJ(obj, i9);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzP(obj2, i9)) {
                        zzeq.zzq(obj, j9, zzeq.zzc(obj2, j9));
                        zzJ(obj, i9);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzP(obj2, i9)) {
                        zzeq.zzq(obj, j9, zzeq.zzc(obj2, j9));
                        zzJ(obj, i9);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzP(obj2, i9)) {
                        zzeq.zzr(obj, j9, zzeq.zzd(obj2, j9));
                        zzJ(obj, i9);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzP(obj2, i9)) {
                        zzeq.zzq(obj, j9, zzeq.zzc(obj2, j9));
                        zzJ(obj, i9);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzP(obj2, i9)) {
                        zzeq.zzr(obj, j9, zzeq.zzd(obj2, j9));
                        zzJ(obj, i9);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzH(obj, obj2, i9);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzl.zzb(obj, obj2, j9);
                    break;
                case 50:
                    int i11 = zzdr.zza;
                    zzeq.zzs(obj, j9, zzda.zzb(zzeq.zzf(obj, j9), zzeq.zzf(obj2, j9)));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zzT(obj2, i10, i9)) {
                        zzeq.zzs(obj, j9, zzeq.zzf(obj2, j9));
                        zzK(obj, i10, i9);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzI(obj, obj2, i9);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzT(obj2, i10, i9)) {
                        zzeq.zzs(obj, j9, zzeq.zzf(obj2, j9));
                        zzK(obj, i10, i9);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzI(obj, obj2, i9);
                    break;
            }
        }
        zzdr.zzB(this.zzm, obj, obj2);
        if (this.zzh) {
            this.zzn.zza(obj2);
            throw null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x02d9, code lost:
    
        if (r0 != r15) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x02fd, code lost:
    
        if (r0 != r15) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0294, code lost:
    
        if (r0 != r5) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0296, code lost:
    
        r15 = r30;
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r9 = r18;
        r1 = r19;
        r2 = r22;
        r6 = r25;
        r7 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x02ac, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:27:0x008d. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v12, types: [int] */
    @Override // com.google.android.gms.internal.play_billing.zzdp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzh(Object obj, byte[] bArr, int i9, int i10, zzan zzanVar) throws zzci {
        byte b9;
        int iZzk;
        int i11;
        int i12;
        int i13;
        Unsafe unsafe;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        zzdi<T> zzdiVar = this;
        Object obj2 = obj;
        byte[] bArr2 = bArr;
        int i21 = i10;
        zzan zzanVar2 = zzanVar;
        zzew zzewVar = zzew.zza;
        int i22 = -1;
        if (zzdiVar.zzo - 1 == 0) {
            zzc(obj, bArr, i9, i10, 0, zzanVar);
            return;
        }
        zzG(obj);
        Unsafe unsafe2 = zzb;
        int i23 = 1048575;
        int iZzi = i9;
        int i24 = 1048575;
        int i25 = -1;
        int i26 = 0;
        int i27 = 0;
        while (iZzi < i21) {
            int i28 = iZzi + 1;
            byte b10 = bArr2[iZzi];
            if (b10 < 0) {
                iZzk = zzao.zzk(b10, bArr2, i28, zzanVar2);
                b9 = zzanVar2.zza;
            } else {
                b9 = b10;
                iZzk = i28;
            }
            int i29 = b9 >>> 3;
            int iZzu = i29 > i25 ? zzdiVar.zzu(i29, i26 / 3) : zzdiVar.zzt(i29);
            if (iZzu == i22) {
                i11 = iZzk;
                i12 = i29;
                i13 = i22;
                unsafe = unsafe2;
                i14 = 0;
            } else {
                int i30 = b9 & 7;
                int[] iArr = zzdiVar.zzc;
                int i31 = iArr[iZzu + 1];
                int iZzx = zzx(i31);
                long j9 = i31 & i23;
                if (iZzx <= 17) {
                    int i32 = iArr[iZzu + 2];
                    int i33 = 1 << (i32 >>> 20);
                    int i34 = i32 & 1048575;
                    if (i34 != i24) {
                        if (i24 != 1048575) {
                            unsafe2.putInt(obj2, i24, i27);
                        }
                        if (i34 != 1048575) {
                            i27 = unsafe2.getInt(obj2, i34);
                        }
                        i24 = i34;
                    }
                    switch (iZzx) {
                        case 0:
                            i16 = 1048575;
                            i15 = iZzu;
                            i12 = i29;
                            i17 = iZzk;
                            if (i30 != 1) {
                                i11 = i17;
                                unsafe = unsafe2;
                                i14 = i15;
                                i13 = -1;
                                break;
                            } else {
                                zzeq.zzo(obj2, j9, Double.longBitsToDouble(zzao.zzp(bArr2, i17)));
                                iZzi = i17 + 8;
                                i27 |= i33;
                                i26 = i15;
                                i25 = i12;
                                i23 = i16;
                                i22 = -1;
                                i21 = i10;
                                break;
                            }
                        case 1:
                            i16 = 1048575;
                            i15 = iZzu;
                            i12 = i29;
                            i17 = iZzk;
                            if (i30 != 5) {
                                i11 = i17;
                                unsafe = unsafe2;
                                i14 = i15;
                                i13 = -1;
                                break;
                            } else {
                                zzeq.zzp(obj2, j9, Float.intBitsToFloat(zzao.zzb(bArr2, i17)));
                                iZzi = i17 + 4;
                                i27 |= i33;
                                i26 = i15;
                                i25 = i12;
                                i23 = i16;
                                i22 = -1;
                                i21 = i10;
                                break;
                            }
                        case 2:
                        case 3:
                            i16 = 1048575;
                            i15 = iZzu;
                            i12 = i29;
                            i17 = iZzk;
                            if (i30 != 0) {
                                i11 = i17;
                                unsafe = unsafe2;
                                i14 = i15;
                                i13 = -1;
                                break;
                            } else {
                                int iZzm = zzao.zzm(bArr2, i17, zzanVar2);
                                unsafe2.putLong(obj, j9, zzanVar2.zzb);
                                i27 |= i33;
                                i26 = i15;
                                iZzi = iZzm;
                                i25 = i12;
                                i23 = i16;
                                i22 = -1;
                                i21 = i10;
                                break;
                            }
                        case 4:
                        case 11:
                            i16 = 1048575;
                            i15 = iZzu;
                            i12 = i29;
                            i17 = iZzk;
                            if (i30 != 0) {
                                i11 = i17;
                                unsafe = unsafe2;
                                i14 = i15;
                                i13 = -1;
                                break;
                            } else {
                                iZzi = zzao.zzj(bArr2, i17, zzanVar2);
                                unsafe2.putInt(obj2, j9, zzanVar2.zza);
                                i27 |= i33;
                                i26 = i15;
                                i25 = i12;
                                i23 = i16;
                                i22 = -1;
                                i21 = i10;
                                break;
                            }
                        case 5:
                        case 14:
                            i16 = 1048575;
                            i15 = iZzu;
                            i12 = i29;
                            if (i30 != 1) {
                                i17 = iZzk;
                                i11 = i17;
                                unsafe = unsafe2;
                                i14 = i15;
                                i13 = -1;
                                break;
                            } else {
                                unsafe2.putLong(obj, j9, zzao.zzp(bArr2, iZzk));
                                iZzi = iZzk + 8;
                                i27 |= i33;
                                i26 = i15;
                                i25 = i12;
                                i23 = i16;
                                i22 = -1;
                                i21 = i10;
                                break;
                            }
                        case 6:
                        case 13:
                            i16 = 1048575;
                            i15 = iZzu;
                            i12 = i29;
                            if (i30 != 5) {
                                i17 = iZzk;
                                i11 = i17;
                                unsafe = unsafe2;
                                i14 = i15;
                                i13 = -1;
                                break;
                            } else {
                                unsafe2.putInt(obj2, j9, zzao.zzb(bArr2, iZzk));
                                iZzi = iZzk + 4;
                                i27 |= i33;
                                i26 = i15;
                                i25 = i12;
                                i23 = i16;
                                i22 = -1;
                                i21 = i10;
                                break;
                            }
                        case 7:
                            i16 = 1048575;
                            i15 = iZzu;
                            i12 = i29;
                            if (i30 != 0) {
                                i17 = iZzk;
                                i11 = i17;
                                unsafe = unsafe2;
                                i14 = i15;
                                i13 = -1;
                                break;
                            } else {
                                iZzi = zzao.zzm(bArr2, iZzk, zzanVar2);
                                zzeq.zzm(obj2, j9, zzanVar2.zzb != 0);
                                i27 |= i33;
                                i26 = i15;
                                i25 = i12;
                                i23 = i16;
                                i22 = -1;
                                i21 = i10;
                                break;
                            }
                        case 8:
                            i16 = 1048575;
                            i15 = iZzu;
                            i12 = i29;
                            if (i30 != 2) {
                                i17 = iZzk;
                                i11 = i17;
                                unsafe = unsafe2;
                                i14 = i15;
                                i13 = -1;
                                break;
                            } else {
                                iZzi = (i31 & 536870912) == 0 ? zzao.zzg(bArr2, iZzk, zzanVar2) : zzao.zzh(bArr2, iZzk, zzanVar2);
                                unsafe2.putObject(obj2, j9, zzanVar2.zzc);
                                i27 |= i33;
                                i26 = i15;
                                i25 = i12;
                                i23 = i16;
                                i22 = -1;
                                i21 = i10;
                                break;
                            }
                        case 9:
                            i16 = 1048575;
                            i15 = iZzu;
                            i12 = i29;
                            if (i30 != 2) {
                                i17 = iZzk;
                                i11 = i17;
                                unsafe = unsafe2;
                                i14 = i15;
                                i13 = -1;
                                break;
                            } else {
                                Object objZzD = zzdiVar.zzD(obj2, i15);
                                iZzi = zzao.zzo(objZzD, zzdiVar.zzB(i15), bArr, iZzk, i10, zzanVar);
                                zzdiVar.zzL(obj2, i15, objZzD);
                                i27 |= i33;
                                i26 = i15;
                                i25 = i12;
                                i23 = i16;
                                i22 = -1;
                                i21 = i10;
                                break;
                            }
                        case 10:
                            i16 = 1048575;
                            i15 = iZzu;
                            i12 = i29;
                            if (i30 != 2) {
                                i17 = iZzk;
                                i11 = i17;
                                unsafe = unsafe2;
                                i14 = i15;
                                i13 = -1;
                                break;
                            } else {
                                iZzi = zzao.zza(bArr2, iZzk, zzanVar2);
                                unsafe2.putObject(obj2, j9, zzanVar2.zzc);
                                i27 |= i33;
                                i26 = i15;
                                i25 = i12;
                                i23 = i16;
                                i22 = -1;
                                i21 = i10;
                                break;
                            }
                        case 12:
                            i16 = 1048575;
                            i15 = iZzu;
                            i12 = i29;
                            if (i30 != 0) {
                                i17 = iZzk;
                                i11 = i17;
                                unsafe = unsafe2;
                                i14 = i15;
                                i13 = -1;
                                break;
                            } else {
                                iZzi = zzao.zzj(bArr2, iZzk, zzanVar2);
                                unsafe2.putInt(obj2, j9, zzanVar2.zza);
                                i27 |= i33;
                                i26 = i15;
                                i25 = i12;
                                i23 = i16;
                                i22 = -1;
                                i21 = i10;
                                break;
                            }
                        case 15:
                            i16 = 1048575;
                            i15 = iZzu;
                            i12 = i29;
                            if (i30 != 0) {
                                i17 = iZzk;
                                i11 = i17;
                                unsafe = unsafe2;
                                i14 = i15;
                                i13 = -1;
                                break;
                            } else {
                                iZzi = zzao.zzj(bArr2, iZzk, zzanVar2);
                                unsafe2.putInt(obj2, j9, zzbe.zzb(zzanVar2.zza));
                                i27 |= i33;
                                i26 = i15;
                                i25 = i12;
                                i23 = i16;
                                i22 = -1;
                                i21 = i10;
                                break;
                            }
                        case 16:
                            if (i30 != 0) {
                                i12 = i29;
                                i17 = iZzk;
                                i15 = iZzu;
                                i11 = i17;
                                unsafe = unsafe2;
                                i14 = i15;
                                i13 = -1;
                                break;
                            } else {
                                int iZzm2 = zzao.zzm(bArr2, iZzk, zzanVar2);
                                i12 = i29;
                                i16 = 1048575;
                                unsafe2.putLong(obj, j9, zzbe.zzc(zzanVar2.zzb));
                                i27 |= i33;
                                i26 = iZzu;
                                iZzi = iZzm2;
                                i25 = i12;
                                i23 = i16;
                                i22 = -1;
                                i21 = i10;
                                break;
                            }
                        default:
                            i15 = iZzu;
                            i12 = i29;
                            i17 = iZzk;
                            i11 = i17;
                            unsafe = unsafe2;
                            i14 = i15;
                            i13 = -1;
                            break;
                    }
                } else {
                    i12 = i29;
                    int i35 = iZzk;
                    if (iZzx != 27) {
                        i14 = iZzu;
                        if (iZzx <= 49) {
                            i19 = i27;
                            i20 = i24;
                            unsafe = unsafe2;
                            i13 = -1;
                            iZzi = zzs(obj, bArr, i35, i10, b9, i12, i30, i14, i31, iZzx, j9, zzanVar);
                        } else {
                            i18 = i35;
                            i19 = i27;
                            i20 = i24;
                            unsafe = unsafe2;
                            i13 = -1;
                            if (iZzx != 50) {
                                iZzi = zzr(obj, bArr, i18, i10, b9, i12, i30, i31, iZzx, j9, i14, zzanVar);
                            } else if (i30 == 2) {
                                iZzi = zzq(obj, bArr, i18, i10, i14, j9, zzanVar);
                            }
                        }
                    } else if (i30 == 2) {
                        zzcf zzcfVarZzd = (zzcf) unsafe2.getObject(obj2, j9);
                        if (!zzcfVarZzd.zzc()) {
                            int size = zzcfVarZzd.size();
                            zzcfVarZzd = zzcfVarZzd.zzd(size == 0 ? 10 : size + size);
                            unsafe2.putObject(obj2, j9, zzcfVarZzd);
                        }
                        iZzi = zzao.zze(zzdiVar.zzB(iZzu), b9, bArr, i35, i10, zzcfVarZzd, zzanVar);
                        i21 = i10;
                        i27 = i27;
                        i25 = i12;
                        i26 = iZzu;
                        i23 = 1048575;
                        i22 = -1;
                    } else {
                        i14 = iZzu;
                        i18 = i35;
                        i19 = i27;
                        i20 = i24;
                        unsafe = unsafe2;
                        i13 = -1;
                    }
                    i11 = i18;
                    i27 = i19;
                    i24 = i20;
                }
                unsafe2 = unsafe;
                i23 = 1048575;
            }
            iZzi = zzao.zzi(b9, bArr, i11, i10, zzd(obj), zzanVar);
            zzdiVar = this;
            obj2 = obj;
            bArr2 = bArr;
            i21 = i10;
            zzanVar2 = zzanVar;
            i22 = i13;
            i25 = i12;
            i26 = i14;
            unsafe2 = unsafe;
            i23 = 1048575;
        }
        int i36 = i27;
        Unsafe unsafe3 = unsafe2;
        if (i24 != i23) {
            unsafe3.putInt(obj, i24, i36);
        }
        if (iZzi != i10) {
            throw zzci.zze();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final void zzi(Object obj, zzey zzeyVar) {
        int i9;
        int i10;
        int i11;
        zzew zzewVar = zzew.zza;
        int i12 = 0;
        int i13 = 1048575;
        if (this.zzo - 1 != 0) {
            if (this.zzh) {
                this.zzn.zza(obj);
                throw null;
            }
            int length = this.zzc.length;
            for (int i14 = 0; i14 < length; i14 += 3) {
                int iZzy = zzy(i14);
                int i15 = this.zzc[i14];
                switch (zzx(iZzy)) {
                    case 0:
                        if (zzP(obj, i14)) {
                            zzeyVar.zzf(i15, zzeq.zza(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzP(obj, i14)) {
                            zzeyVar.zzo(i15, zzeq.zzb(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzP(obj, i14)) {
                            zzeyVar.zzt(i15, zzeq.zzd(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzP(obj, i14)) {
                            zzeyVar.zzJ(i15, zzeq.zzd(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzP(obj, i14)) {
                            zzeyVar.zzr(i15, zzeq.zzc(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzP(obj, i14)) {
                            zzeyVar.zzm(i15, zzeq.zzd(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzP(obj, i14)) {
                            zzeyVar.zzk(i15, zzeq.zzc(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzP(obj, i14)) {
                            zzeyVar.zzb(i15, zzeq.zzw(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zzP(obj, i14)) {
                            zzV(i15, zzeq.zzf(obj, iZzy & 1048575), zzeyVar);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        if (zzP(obj, i14)) {
                            zzeyVar.zzv(i15, zzeq.zzf(obj, iZzy & 1048575), zzB(i14));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zzP(obj, i14)) {
                            zzeyVar.zzd(i15, (zzba) zzeq.zzf(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zzP(obj, i14)) {
                            zzeyVar.zzH(i15, zzeq.zzc(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzP(obj, i14)) {
                            zzeyVar.zzi(i15, zzeq.zzc(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzP(obj, i14)) {
                            zzeyVar.zzw(i15, zzeq.zzc(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzP(obj, i14)) {
                            zzeyVar.zzy(i15, zzeq.zzd(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzP(obj, i14)) {
                            zzeyVar.zzA(i15, zzeq.zzc(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzP(obj, i14)) {
                            zzeyVar.zzC(i15, zzeq.zzd(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zzP(obj, i14)) {
                            zzeyVar.zzq(i15, zzeq.zzf(obj, iZzy & 1048575), zzB(i14));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        zzdr.zzF(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 19:
                        zzdr.zzJ(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 20:
                        zzdr.zzM(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 21:
                        zzdr.zzU(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 22:
                        zzdr.zzL(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 23:
                        zzdr.zzI(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 24:
                        zzdr.zzH(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 25:
                        zzdr.zzD(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 26:
                        zzdr.zzS(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar);
                        break;
                    case 27:
                        zzdr.zzN(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, zzB(i14));
                        break;
                    case 28:
                        zzdr.zzE(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar);
                        break;
                    case 29:
                        zzdr.zzT(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 30:
                        zzdr.zzG(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 31:
                        zzdr.zzO(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 32:
                        zzdr.zzP(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 33:
                        zzdr.zzQ(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 34:
                        zzdr.zzR(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, false);
                        break;
                    case 35:
                        zzdr.zzF(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 36:
                        zzdr.zzJ(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 37:
                        zzdr.zzM(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 38:
                        zzdr.zzU(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 39:
                        zzdr.zzL(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 40:
                        zzdr.zzI(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 41:
                        zzdr.zzH(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 42:
                        zzdr.zzD(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 43:
                        zzdr.zzT(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 44:
                        zzdr.zzG(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 45:
                        zzdr.zzO(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 46:
                        zzdr.zzP(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 47:
                        zzdr.zzQ(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 48:
                        zzdr.zzR(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, true);
                        break;
                    case 49:
                        zzdr.zzK(i15, (List) zzeq.zzf(obj, iZzy & 1048575), zzeyVar, zzB(i14));
                        break;
                    case 50:
                        zzN(zzeyVar, i15, zzeq.zzf(obj, iZzy & 1048575), i14);
                        break;
                    case 51:
                        if (zzT(obj, i15, i14)) {
                            zzeyVar.zzf(i15, zzm(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zzT(obj, i15, i14)) {
                            zzeyVar.zzo(i15, zzn(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zzT(obj, i15, i14)) {
                            zzeyVar.zzt(i15, zzz(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zzT(obj, i15, i14)) {
                            zzeyVar.zzJ(i15, zzz(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zzT(obj, i15, i14)) {
                            zzeyVar.zzr(i15, zzp(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zzT(obj, i15, i14)) {
                            zzeyVar.zzm(i15, zzz(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zzT(obj, i15, i14)) {
                            zzeyVar.zzk(i15, zzp(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zzT(obj, i15, i14)) {
                            zzeyVar.zzb(i15, zzU(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zzT(obj, i15, i14)) {
                            zzV(i15, zzeq.zzf(obj, iZzy & 1048575), zzeyVar);
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (zzT(obj, i15, i14)) {
                            zzeyVar.zzv(i15, zzeq.zzf(obj, iZzy & 1048575), zzB(i14));
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zzT(obj, i15, i14)) {
                            zzeyVar.zzd(i15, (zzba) zzeq.zzf(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zzT(obj, i15, i14)) {
                            zzeyVar.zzH(i15, zzp(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zzT(obj, i15, i14)) {
                            zzeyVar.zzi(i15, zzp(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zzT(obj, i15, i14)) {
                            zzeyVar.zzw(i15, zzp(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zzT(obj, i15, i14)) {
                            zzeyVar.zzy(i15, zzz(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zzT(obj, i15, i14)) {
                            zzeyVar.zzA(i15, zzp(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zzT(obj, i15, i14)) {
                            zzeyVar.zzC(i15, zzz(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zzT(obj, i15, i14)) {
                            zzeyVar.zzq(i15, zzeq.zzf(obj, iZzy & 1048575), zzB(i14));
                            break;
                        } else {
                            break;
                        }
                }
            }
            zzeg zzegVar = this.zzm;
            zzegVar.zzi(zzegVar.zzd(obj), zzeyVar);
            return;
        }
        if (this.zzh) {
            this.zzn.zza(obj);
            throw null;
        }
        int length2 = this.zzc.length;
        Unsafe unsafe = zzb;
        int i16 = 0;
        int i17 = 0;
        int i18 = 1048575;
        while (i16 < length2) {
            int iZzy2 = zzy(i16);
            int[] iArr = this.zzc;
            int i19 = iArr[i16];
            int iZzx = zzx(iZzy2);
            if (iZzx <= 17) {
                int i20 = iArr[i16 + 2];
                int i21 = i20 & i13;
                if (i21 != i18) {
                    i17 = unsafe.getInt(obj, i21);
                    i18 = i21;
                }
                i9 = 1 << (i20 >>> 20);
            } else {
                i9 = i12;
            }
            long j9 = iZzy2 & i13;
            switch (iZzx) {
                case 0:
                    i10 = 0;
                    if ((i17 & i9) != 0) {
                        zzeyVar.zzf(i19, zzeq.zza(obj, j9));
                        break;
                    } else {
                        break;
                    }
                case 1:
                    i10 = 0;
                    if ((i17 & i9) != 0) {
                        zzeyVar.zzo(i19, zzeq.zzb(obj, j9));
                        break;
                    } else {
                        break;
                    }
                case 2:
                    i10 = 0;
                    if ((i17 & i9) != 0) {
                        zzeyVar.zzt(i19, unsafe.getLong(obj, j9));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    i10 = 0;
                    if ((i17 & i9) != 0) {
                        zzeyVar.zzJ(i19, unsafe.getLong(obj, j9));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    i10 = 0;
                    if ((i17 & i9) != 0) {
                        zzeyVar.zzr(i19, unsafe.getInt(obj, j9));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    i10 = 0;
                    if ((i17 & i9) != 0) {
                        zzeyVar.zzm(i19, unsafe.getLong(obj, j9));
                        break;
                    } else {
                        break;
                    }
                case 6:
                    i10 = 0;
                    if ((i17 & i9) != 0) {
                        zzeyVar.zzk(i19, unsafe.getInt(obj, j9));
                        break;
                    } else {
                        break;
                    }
                case 7:
                    i10 = 0;
                    if ((i17 & i9) != 0) {
                        zzeyVar.zzb(i19, zzeq.zzw(obj, j9));
                        break;
                    } else {
                        break;
                    }
                case 8:
                    i10 = 0;
                    if ((i17 & i9) != 0) {
                        zzV(i19, unsafe.getObject(obj, j9), zzeyVar);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    i10 = 0;
                    if ((i17 & i9) != 0) {
                        zzeyVar.zzv(i19, unsafe.getObject(obj, j9), zzB(i16));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    i10 = 0;
                    if ((i17 & i9) != 0) {
                        zzeyVar.zzd(i19, (zzba) unsafe.getObject(obj, j9));
                        break;
                    } else {
                        break;
                    }
                case 11:
                    i10 = 0;
                    if ((i17 & i9) != 0) {
                        zzeyVar.zzH(i19, unsafe.getInt(obj, j9));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    i10 = 0;
                    if ((i17 & i9) != 0) {
                        zzeyVar.zzi(i19, unsafe.getInt(obj, j9));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    i10 = 0;
                    if ((i17 & i9) != 0) {
                        zzeyVar.zzw(i19, unsafe.getInt(obj, j9));
                        break;
                    } else {
                        break;
                    }
                case 14:
                    i10 = 0;
                    if ((i17 & i9) != 0) {
                        zzeyVar.zzy(i19, unsafe.getLong(obj, j9));
                        break;
                    } else {
                        break;
                    }
                case 15:
                    i10 = 0;
                    if ((i17 & i9) != 0) {
                        zzeyVar.zzA(i19, unsafe.getInt(obj, j9));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    i10 = 0;
                    if ((i17 & i9) != 0) {
                        zzeyVar.zzC(i19, unsafe.getLong(obj, j9));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    i10 = 0;
                    if ((i17 & i9) != 0) {
                        zzeyVar.zzq(i19, unsafe.getObject(obj, j9), zzB(i16));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    i10 = 0;
                    zzdr.zzF(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, false);
                    break;
                case 19:
                    i10 = 0;
                    zzdr.zzJ(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, false);
                    break;
                case 20:
                    i10 = 0;
                    zzdr.zzM(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, false);
                    break;
                case 21:
                    i10 = 0;
                    zzdr.zzU(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, false);
                    break;
                case 22:
                    i10 = 0;
                    zzdr.zzL(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, false);
                    break;
                case 23:
                    i10 = 0;
                    zzdr.zzI(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, false);
                    break;
                case 24:
                    i10 = 0;
                    zzdr.zzH(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, false);
                    break;
                case 25:
                    i10 = 0;
                    zzdr.zzD(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, false);
                    break;
                case 26:
                    zzdr.zzS(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar);
                    i10 = 0;
                    break;
                case 27:
                    zzdr.zzN(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, zzB(i16));
                    i10 = 0;
                    break;
                case 28:
                    zzdr.zzE(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar);
                    i10 = 0;
                    break;
                case 29:
                    i11 = 0;
                    zzdr.zzT(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, false);
                    i10 = i11;
                    break;
                case 30:
                    i11 = 0;
                    zzdr.zzG(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, false);
                    i10 = i11;
                    break;
                case 31:
                    i11 = 0;
                    zzdr.zzO(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, false);
                    i10 = i11;
                    break;
                case 32:
                    i11 = 0;
                    zzdr.zzP(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, false);
                    i10 = i11;
                    break;
                case 33:
                    i11 = 0;
                    zzdr.zzQ(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, false);
                    i10 = i11;
                    break;
                case 34:
                    i11 = 0;
                    zzdr.zzR(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, false);
                    i10 = i11;
                    break;
                case 35:
                    zzdr.zzF(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, true);
                    i10 = 0;
                    break;
                case 36:
                    zzdr.zzJ(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, true);
                    i10 = 0;
                    break;
                case 37:
                    zzdr.zzM(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, true);
                    i10 = 0;
                    break;
                case 38:
                    zzdr.zzU(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, true);
                    i10 = 0;
                    break;
                case 39:
                    zzdr.zzL(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, true);
                    i10 = 0;
                    break;
                case 40:
                    zzdr.zzI(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, true);
                    i10 = 0;
                    break;
                case 41:
                    zzdr.zzH(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, true);
                    i10 = 0;
                    break;
                case 42:
                    zzdr.zzD(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, true);
                    i10 = 0;
                    break;
                case 43:
                    zzdr.zzT(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, true);
                    i10 = 0;
                    break;
                case 44:
                    zzdr.zzG(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, true);
                    i10 = 0;
                    break;
                case 45:
                    zzdr.zzO(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, true);
                    i10 = 0;
                    break;
                case 46:
                    zzdr.zzP(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, true);
                    i10 = 0;
                    break;
                case 47:
                    zzdr.zzQ(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, true);
                    i10 = 0;
                    break;
                case 48:
                    zzdr.zzR(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, true);
                    i10 = 0;
                    break;
                case 49:
                    zzdr.zzK(this.zzc[i16], (List) unsafe.getObject(obj, j9), zzeyVar, zzB(i16));
                    i10 = 0;
                    break;
                case 50:
                    zzN(zzeyVar, i19, unsafe.getObject(obj, j9), i16);
                    i10 = 0;
                    break;
                case 51:
                    if (zzT(obj, i19, i16)) {
                        zzeyVar.zzf(i19, zzm(obj, j9));
                    }
                    i10 = 0;
                    break;
                case 52:
                    if (zzT(obj, i19, i16)) {
                        zzeyVar.zzo(i19, zzn(obj, j9));
                    }
                    i10 = 0;
                    break;
                case 53:
                    if (zzT(obj, i19, i16)) {
                        zzeyVar.zzt(i19, zzz(obj, j9));
                    }
                    i10 = 0;
                    break;
                case 54:
                    if (zzT(obj, i19, i16)) {
                        zzeyVar.zzJ(i19, zzz(obj, j9));
                    }
                    i10 = 0;
                    break;
                case 55:
                    if (zzT(obj, i19, i16)) {
                        zzeyVar.zzr(i19, zzp(obj, j9));
                    }
                    i10 = 0;
                    break;
                case 56:
                    if (zzT(obj, i19, i16)) {
                        zzeyVar.zzm(i19, zzz(obj, j9));
                    }
                    i10 = 0;
                    break;
                case 57:
                    if (zzT(obj, i19, i16)) {
                        zzeyVar.zzk(i19, zzp(obj, j9));
                    }
                    i10 = 0;
                    break;
                case 58:
                    if (zzT(obj, i19, i16)) {
                        zzeyVar.zzb(i19, zzU(obj, j9));
                    }
                    i10 = 0;
                    break;
                case 59:
                    if (zzT(obj, i19, i16)) {
                        zzV(i19, unsafe.getObject(obj, j9), zzeyVar);
                    }
                    i10 = 0;
                    break;
                case 60:
                    if (zzT(obj, i19, i16)) {
                        zzeyVar.zzv(i19, unsafe.getObject(obj, j9), zzB(i16));
                    }
                    i10 = 0;
                    break;
                case 61:
                    if (zzT(obj, i19, i16)) {
                        zzeyVar.zzd(i19, (zzba) unsafe.getObject(obj, j9));
                    }
                    i10 = 0;
                    break;
                case 62:
                    if (zzT(obj, i19, i16)) {
                        zzeyVar.zzH(i19, zzp(obj, j9));
                    }
                    i10 = 0;
                    break;
                case 63:
                    if (zzT(obj, i19, i16)) {
                        zzeyVar.zzi(i19, zzp(obj, j9));
                    }
                    i10 = 0;
                    break;
                case 64:
                    if (zzT(obj, i19, i16)) {
                        zzeyVar.zzw(i19, zzp(obj, j9));
                    }
                    i10 = 0;
                    break;
                case 65:
                    if (zzT(obj, i19, i16)) {
                        zzeyVar.zzy(i19, zzz(obj, j9));
                    }
                    i10 = 0;
                    break;
                case 66:
                    if (zzT(obj, i19, i16)) {
                        zzeyVar.zzA(i19, zzp(obj, j9));
                    }
                    i10 = 0;
                    break;
                case 67:
                    if (zzT(obj, i19, i16)) {
                        zzeyVar.zzC(i19, zzz(obj, j9));
                    }
                    i10 = 0;
                    break;
                case 68:
                    if (zzT(obj, i19, i16)) {
                        zzeyVar.zzq(i19, unsafe.getObject(obj, j9), zzB(i16));
                    }
                    i10 = 0;
                    break;
                default:
                    i10 = 0;
                    break;
            }
            i16 += 3;
            i12 = i10;
            i13 = 1048575;
        }
        zzeg zzegVar2 = this.zzm;
        zzegVar2.zzi(zzegVar2.zzd(obj), zzeyVar);
    }

    @Override // com.google.android.gms.internal.play_billing.zzdp
    public final boolean zzj(Object obj, Object obj2) {
        boolean zZzV;
        int length = this.zzc.length;
        for (int i9 = 0; i9 < length; i9 += 3) {
            int iZzy = zzy(i9);
            long j9 = iZzy & 1048575;
            switch (zzx(iZzy)) {
                case 0:
                    if (!zzO(obj, obj2, i9) || Double.doubleToLongBits(zzeq.zza(obj, j9)) != Double.doubleToLongBits(zzeq.zza(obj2, j9))) {
                        return false;
                    }
                    continue;
                    break;
                case 1:
                    if (!zzO(obj, obj2, i9) || Float.floatToIntBits(zzeq.zzb(obj, j9)) != Float.floatToIntBits(zzeq.zzb(obj2, j9))) {
                        return false;
                    }
                    continue;
                    break;
                case 2:
                    if (!zzO(obj, obj2, i9) || zzeq.zzd(obj, j9) != zzeq.zzd(obj2, j9)) {
                        return false;
                    }
                    continue;
                    break;
                case 3:
                    if (!zzO(obj, obj2, i9) || zzeq.zzd(obj, j9) != zzeq.zzd(obj2, j9)) {
                        return false;
                    }
                    continue;
                    break;
                case 4:
                    if (!zzO(obj, obj2, i9) || zzeq.zzc(obj, j9) != zzeq.zzc(obj2, j9)) {
                        return false;
                    }
                    continue;
                    break;
                case 5:
                    if (!zzO(obj, obj2, i9) || zzeq.zzd(obj, j9) != zzeq.zzd(obj2, j9)) {
                        return false;
                    }
                    continue;
                    break;
                case 6:
                    if (!zzO(obj, obj2, i9) || zzeq.zzc(obj, j9) != zzeq.zzc(obj2, j9)) {
                        return false;
                    }
                    continue;
                    break;
                case 7:
                    if (!zzO(obj, obj2, i9) || zzeq.zzw(obj, j9) != zzeq.zzw(obj2, j9)) {
                        return false;
                    }
                    continue;
                    break;
                case 8:
                    if (!zzO(obj, obj2, i9) || !zzdr.zzV(zzeq.zzf(obj, j9), zzeq.zzf(obj2, j9))) {
                        return false;
                    }
                    continue;
                    break;
                case 9:
                    if (!zzO(obj, obj2, i9) || !zzdr.zzV(zzeq.zzf(obj, j9), zzeq.zzf(obj2, j9))) {
                        return false;
                    }
                    continue;
                    break;
                case 10:
                    if (!zzO(obj, obj2, i9) || !zzdr.zzV(zzeq.zzf(obj, j9), zzeq.zzf(obj2, j9))) {
                        return false;
                    }
                    continue;
                    break;
                case 11:
                    if (!zzO(obj, obj2, i9) || zzeq.zzc(obj, j9) != zzeq.zzc(obj2, j9)) {
                        return false;
                    }
                    continue;
                    break;
                case 12:
                    if (!zzO(obj, obj2, i9) || zzeq.zzc(obj, j9) != zzeq.zzc(obj2, j9)) {
                        return false;
                    }
                    continue;
                    break;
                case 13:
                    if (!zzO(obj, obj2, i9) || zzeq.zzc(obj, j9) != zzeq.zzc(obj2, j9)) {
                        return false;
                    }
                    continue;
                    break;
                case 14:
                    if (!zzO(obj, obj2, i9) || zzeq.zzd(obj, j9) != zzeq.zzd(obj2, j9)) {
                        return false;
                    }
                    continue;
                    break;
                case 15:
                    if (!zzO(obj, obj2, i9) || zzeq.zzc(obj, j9) != zzeq.zzc(obj2, j9)) {
                        return false;
                    }
                    continue;
                    break;
                case 16:
                    if (!zzO(obj, obj2, i9) || zzeq.zzd(obj, j9) != zzeq.zzd(obj2, j9)) {
                        return false;
                    }
                    continue;
                    break;
                case 17:
                    if (!zzO(obj, obj2, i9) || !zzdr.zzV(zzeq.zzf(obj, j9), zzeq.zzf(obj2, j9))) {
                        return false;
                    }
                    continue;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    zZzV = zzdr.zzV(zzeq.zzf(obj, j9), zzeq.zzf(obj2, j9));
                    break;
                case 50:
                    zZzV = zzdr.zzV(zzeq.zzf(obj, j9), zzeq.zzf(obj2, j9));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long jZzv = zzv(i9) & 1048575;
                    if (zzeq.zzc(obj, jZzv) != zzeq.zzc(obj2, jZzv) || !zzdr.zzV(zzeq.zzf(obj, j9), zzeq.zzf(obj2, j9))) {
                        return false;
                    }
                    continue;
                    break;
                default:
            }
            if (!zZzV) {
                return false;
            }
        }
        if (!this.zzm.zzd(obj).equals(this.zzm.zzd(obj2))) {
            return false;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzn.zza(obj);
        this.zzn.zza(obj2);
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x009e  */
    @Override // com.google.android.gms.internal.play_billing.zzdp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean zzk(Object obj) {
        int i9;
        int i10;
        int i11 = 0;
        int i12 = 0;
        int i13 = 1048575;
        while (i12 < this.zzj) {
            int i14 = this.zzi[i12];
            int i15 = this.zzc[i14];
            int iZzy = zzy(i14);
            int i16 = this.zzc[i14 + 2];
            int i17 = i16 & 1048575;
            int i18 = 1 << (i16 >>> 20);
            if (i17 != i13) {
                if (i17 != 1048575) {
                    i11 = zzb.getInt(obj, i17);
                }
                i10 = i11;
                i9 = i17;
            } else {
                i9 = i13;
                i10 = i11;
            }
            if ((268435456 & iZzy) != 0 && !zzQ(obj, i14, i9, i10, i18)) {
                return false;
            }
            int iZzx = zzx(iZzy);
            if (iZzx == 9 || iZzx == 17) {
                if (zzQ(obj, i14, i9, i10, i18) && !zzR(obj, iZzy, zzB(i14))) {
                    return false;
                }
            } else if (iZzx == 27) {
                List list = (List) zzeq.zzf(obj, iZzy & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzdp zzdpVarZzB = zzB(i14);
                    for (int i19 = 0; i19 < list.size(); i19++) {
                        if (!zzdpVarZzB.zzk(list.get(i19))) {
                            return false;
                        }
                    }
                }
            } else if (iZzx == 60 || iZzx == 68) {
                if (zzT(obj, i15, i14) && !zzR(obj, iZzy, zzB(i14))) {
                    return false;
                }
            } else if (iZzx != 49) {
                if (iZzx == 50 && !((zzcz) zzeq.zzf(obj, iZzy & 1048575)).isEmpty()) {
                    throw null;
                }
            }
            i12++;
            i13 = i9;
            i11 = i10;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzn.zza(obj);
        throw null;
    }
}
