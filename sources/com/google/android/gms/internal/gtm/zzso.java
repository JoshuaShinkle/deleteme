package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzrc;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sqlcipher.database.SQLiteDatabase;
import sun.misc.Unsafe;

/* loaded from: classes2.dex */
final class zzso<T> implements zzsz<T> {
    private static final int[] zzbcw = new int[0];
    private static final Unsafe zzbcx = zztx.zzro();
    private final int[] zzbcy;
    private final Object[] zzbcz;
    private final int zzbda;
    private final int zzbdb;
    private final zzsk zzbdc;
    private final boolean zzbdd;
    private final boolean zzbde;
    private final boolean zzbdf;
    private final boolean zzbdg;
    private final int[] zzbdh;
    private final int zzbdi;
    private final int zzbdj;
    private final zzsr zzbdk;
    private final zzru zzbdl;
    private final zztr<?, ?> zzbdm;
    private final zzqq<?> zzbdn;
    private final zzsf zzbdo;

    private zzso(int[] iArr, Object[] objArr, int i9, int i10, zzsk zzskVar, boolean z8, boolean z9, int[] iArr2, int i11, int i12, zzsr zzsrVar, zzru zzruVar, zztr<?, ?> zztrVar, zzqq<?> zzqqVar, zzsf zzsfVar) {
        this.zzbcy = iArr;
        this.zzbcz = objArr;
        this.zzbda = i9;
        this.zzbdb = i10;
        this.zzbde = zzskVar instanceof zzrc;
        this.zzbdf = z8;
        this.zzbdd = zzqqVar != null && zzqqVar.zze(zzskVar);
        this.zzbdg = false;
        this.zzbdh = iArr2;
        this.zzbdi = i11;
        this.zzbdj = i12;
        this.zzbdk = zzsrVar;
        this.zzbdl = zzruVar;
        this.zzbdm = zztrVar;
        this.zzbdn = zzqqVar;
        this.zzbdc = zzskVar;
        this.zzbdo = zzsfVar;
    }

    public static <T> zzso<T> zza(Class<T> cls, zzsi zzsiVar, zzsr zzsrVar, zzru zzruVar, zztr<?, ?> zztrVar, zzqq<?> zzqqVar, zzsf zzsfVar) {
        int i9;
        int iCharAt;
        int iCharAt2;
        int i10;
        int i11;
        int i12;
        int i13;
        int[] iArr;
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
        boolean z8;
        int i25;
        zzsx zzsxVar;
        int i26;
        int iObjectFieldOffset;
        int i27;
        int i28;
        Class<?> cls2;
        String str;
        int iObjectFieldOffset2;
        int i29;
        Field fieldZza;
        int i30;
        char cCharAt9;
        int i31;
        Field fieldZza2;
        Field fieldZza3;
        int i32;
        char cCharAt10;
        int i33;
        char cCharAt11;
        int i34;
        char cCharAt12;
        int i35;
        char cCharAt13;
        char cCharAt14;
        if (!(zzsiVar instanceof zzsx)) {
            ((zztm) zzsiVar).zzql();
            int i36 = zzrc.zze.zzbar;
            throw new NoSuchMethodError();
        }
        zzsx zzsxVar2 = (zzsx) zzsiVar;
        int i37 = 0;
        boolean z9 = zzsxVar2.zzql() == zzrc.zze.zzbba;
        String strZzqt = zzsxVar2.zzqt();
        int length = strZzqt.length();
        int iCharAt3 = strZzqt.charAt(0);
        if (iCharAt3 >= 55296) {
            int i38 = iCharAt3 & 8191;
            int i39 = 1;
            int i40 = 13;
            while (true) {
                i9 = i39 + 1;
                cCharAt14 = strZzqt.charAt(i39);
                if (cCharAt14 < 55296) {
                    break;
                }
                i38 |= (cCharAt14 & 8191) << i40;
                i40 += 13;
                i39 = i9;
            }
            iCharAt3 = i38 | (cCharAt14 << i40);
        } else {
            i9 = 1;
        }
        int i41 = i9 + 1;
        int iCharAt4 = strZzqt.charAt(i9);
        if (iCharAt4 >= 55296) {
            int i42 = iCharAt4 & 8191;
            int i43 = 13;
            while (true) {
                i35 = i41 + 1;
                cCharAt13 = strZzqt.charAt(i41);
                if (cCharAt13 < 55296) {
                    break;
                }
                i42 |= (cCharAt13 & 8191) << i43;
                i43 += 13;
                i41 = i35;
            }
            iCharAt4 = i42 | (cCharAt13 << i43);
            i41 = i35;
        }
        if (iCharAt4 == 0) {
            i14 = 0;
            iCharAt = 0;
            i12 = 0;
            iCharAt2 = 0;
            i13 = 0;
            iArr = zzbcw;
            i11 = 0;
        } else {
            int i44 = i41 + 1;
            int iCharAt5 = strZzqt.charAt(i41);
            if (iCharAt5 >= 55296) {
                int i45 = iCharAt5 & 8191;
                int i46 = 13;
                while (true) {
                    i22 = i44 + 1;
                    cCharAt8 = strZzqt.charAt(i44);
                    if (cCharAt8 < 55296) {
                        break;
                    }
                    i45 |= (cCharAt8 & 8191) << i46;
                    i46 += 13;
                    i44 = i22;
                }
                iCharAt5 = i45 | (cCharAt8 << i46);
                i44 = i22;
            }
            int i47 = i44 + 1;
            int iCharAt6 = strZzqt.charAt(i44);
            if (iCharAt6 >= 55296) {
                int i48 = iCharAt6 & 8191;
                int i49 = 13;
                while (true) {
                    i21 = i47 + 1;
                    cCharAt7 = strZzqt.charAt(i47);
                    if (cCharAt7 < 55296) {
                        break;
                    }
                    i48 |= (cCharAt7 & 8191) << i49;
                    i49 += 13;
                    i47 = i21;
                }
                iCharAt6 = i48 | (cCharAt7 << i49);
                i47 = i21;
            }
            int i50 = i47 + 1;
            iCharAt = strZzqt.charAt(i47);
            if (iCharAt >= 55296) {
                int i51 = iCharAt & 8191;
                int i52 = 13;
                while (true) {
                    i20 = i50 + 1;
                    cCharAt6 = strZzqt.charAt(i50);
                    if (cCharAt6 < 55296) {
                        break;
                    }
                    i51 |= (cCharAt6 & 8191) << i52;
                    i52 += 13;
                    i50 = i20;
                }
                iCharAt = i51 | (cCharAt6 << i52);
                i50 = i20;
            }
            int i53 = i50 + 1;
            int iCharAt7 = strZzqt.charAt(i50);
            if (iCharAt7 >= 55296) {
                int i54 = iCharAt7 & 8191;
                int i55 = 13;
                while (true) {
                    i19 = i53 + 1;
                    cCharAt5 = strZzqt.charAt(i53);
                    if (cCharAt5 < 55296) {
                        break;
                    }
                    i54 |= (cCharAt5 & 8191) << i55;
                    i55 += 13;
                    i53 = i19;
                }
                iCharAt7 = i54 | (cCharAt5 << i55);
                i53 = i19;
            }
            int i56 = i53 + 1;
            iCharAt2 = strZzqt.charAt(i53);
            if (iCharAt2 >= 55296) {
                int i57 = iCharAt2 & 8191;
                int i58 = 13;
                while (true) {
                    i18 = i56 + 1;
                    cCharAt4 = strZzqt.charAt(i56);
                    if (cCharAt4 < 55296) {
                        break;
                    }
                    i57 |= (cCharAt4 & 8191) << i58;
                    i58 += 13;
                    i56 = i18;
                }
                iCharAt2 = i57 | (cCharAt4 << i58);
                i56 = i18;
            }
            int i59 = i56 + 1;
            int iCharAt8 = strZzqt.charAt(i56);
            if (iCharAt8 >= 55296) {
                int i60 = iCharAt8 & 8191;
                int i61 = 13;
                while (true) {
                    i17 = i59 + 1;
                    cCharAt3 = strZzqt.charAt(i59);
                    if (cCharAt3 < 55296) {
                        break;
                    }
                    i60 |= (cCharAt3 & 8191) << i61;
                    i61 += 13;
                    i59 = i17;
                }
                iCharAt8 = i60 | (cCharAt3 << i61);
                i59 = i17;
            }
            int i62 = i59 + 1;
            int iCharAt9 = strZzqt.charAt(i59);
            if (iCharAt9 >= 55296) {
                int i63 = iCharAt9 & 8191;
                int i64 = i62;
                int i65 = 13;
                while (true) {
                    i16 = i64 + 1;
                    cCharAt2 = strZzqt.charAt(i64);
                    if (cCharAt2 < 55296) {
                        break;
                    }
                    i63 |= (cCharAt2 & 8191) << i65;
                    i65 += 13;
                    i64 = i16;
                }
                iCharAt9 = i63 | (cCharAt2 << i65);
                i10 = i16;
            } else {
                i10 = i62;
            }
            int i66 = i10 + 1;
            int iCharAt10 = strZzqt.charAt(i10);
            if (iCharAt10 >= 55296) {
                int i67 = iCharAt10 & 8191;
                int i68 = i66;
                int i69 = 13;
                while (true) {
                    i15 = i68 + 1;
                    cCharAt = strZzqt.charAt(i68);
                    if (cCharAt < 55296) {
                        break;
                    }
                    i67 |= (cCharAt & 8191) << i69;
                    i69 += 13;
                    i68 = i15;
                }
                iCharAt10 = i67 | (cCharAt << i69);
                i66 = i15;
            }
            int[] iArr2 = new int[iCharAt10 + iCharAt8 + iCharAt9];
            int i70 = (iCharAt5 << 1) + iCharAt6;
            i11 = iCharAt7;
            i12 = i70;
            i13 = iCharAt10;
            i37 = iCharAt5;
            i41 = i66;
            int i71 = iCharAt8;
            iArr = iArr2;
            i14 = i71;
        }
        Unsafe unsafe = zzbcx;
        Object[] objArrZzqu = zzsxVar2.zzqu();
        Class<?> cls3 = zzsxVar2.zzqn().getClass();
        int i72 = i41;
        int[] iArr3 = new int[iCharAt2 * 3];
        Object[] objArr = new Object[iCharAt2 << 1];
        int i73 = i13 + i14;
        int i74 = i13;
        int i75 = i72;
        int i76 = i73;
        int i77 = 0;
        int i78 = 0;
        while (i75 < length) {
            int i79 = i75 + 1;
            int iCharAt11 = strZzqt.charAt(i75);
            int i80 = length;
            if (iCharAt11 >= 55296) {
                int i81 = iCharAt11 & 8191;
                int i82 = i79;
                int i83 = 13;
                while (true) {
                    i34 = i82 + 1;
                    cCharAt12 = strZzqt.charAt(i82);
                    i23 = i13;
                    if (cCharAt12 < 55296) {
                        break;
                    }
                    i81 |= (cCharAt12 & 8191) << i83;
                    i83 += 13;
                    i82 = i34;
                    i13 = i23;
                }
                iCharAt11 = i81 | (cCharAt12 << i83);
                i24 = i34;
            } else {
                i23 = i13;
                i24 = i79;
            }
            int i84 = i24 + 1;
            int iCharAt12 = strZzqt.charAt(i24);
            if (iCharAt12 >= 55296) {
                int i85 = iCharAt12 & 8191;
                int i86 = i84;
                int i87 = 13;
                while (true) {
                    i33 = i86 + 1;
                    cCharAt11 = strZzqt.charAt(i86);
                    z8 = z9;
                    if (cCharAt11 < 55296) {
                        break;
                    }
                    i85 |= (cCharAt11 & 8191) << i87;
                    i87 += 13;
                    i86 = i33;
                    z9 = z8;
                }
                iCharAt12 = i85 | (cCharAt11 << i87);
                i25 = i33;
            } else {
                z8 = z9;
                i25 = i84;
            }
            int i88 = iCharAt12 & 255;
            int i89 = i11;
            if ((iCharAt12 & UserMetadata.MAX_ATTRIBUTE_SIZE) != 0) {
                iArr[i77] = i78;
                i77++;
            }
            int i90 = iCharAt;
            if (i88 >= 51) {
                int i91 = i25 + 1;
                int iCharAt13 = strZzqt.charAt(i25);
                char c9 = 55296;
                if (iCharAt13 >= 55296) {
                    int i92 = iCharAt13 & 8191;
                    int i93 = 13;
                    while (true) {
                        i32 = i91 + 1;
                        cCharAt10 = strZzqt.charAt(i91);
                        if (cCharAt10 < c9) {
                            break;
                        }
                        i92 |= (cCharAt10 & 8191) << i93;
                        i93 += 13;
                        i91 = i32;
                        c9 = 55296;
                    }
                    iCharAt13 = i92 | (cCharAt10 << i93);
                    i91 = i32;
                }
                int i94 = i88 - 51;
                int i95 = i91;
                if (i94 == 9 || i94 == 17) {
                    objArr[((i78 / 3) << 1) + 1] = objArrZzqu[i12];
                    i12++;
                } else if (i94 == 12 && (iCharAt3 & 1) == 1) {
                    objArr[((i78 / 3) << 1) + 1] = objArrZzqu[i12];
                    i12++;
                }
                int i96 = iCharAt13 << 1;
                Object obj = objArrZzqu[i96];
                if (obj instanceof Field) {
                    fieldZza2 = (Field) obj;
                } else {
                    fieldZza2 = zza(cls3, (String) obj);
                    objArrZzqu[i96] = fieldZza2;
                }
                zzsxVar = zzsxVar2;
                String str2 = strZzqt;
                int iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldZza2);
                int i97 = i96 + 1;
                Object obj2 = objArrZzqu[i97];
                if (obj2 instanceof Field) {
                    fieldZza3 = (Field) obj2;
                } else {
                    fieldZza3 = zza(cls3, (String) obj2);
                    objArrZzqu[i97] = fieldZza3;
                }
                cls2 = cls3;
                i27 = i12;
                i25 = i95;
                str = str2;
                i29 = 0;
                iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZza3);
                iObjectFieldOffset = iObjectFieldOffset3;
                i28 = i37;
            } else {
                zzsxVar = zzsxVar2;
                String str3 = strZzqt;
                int i98 = i12 + 1;
                Field fieldZza4 = zza(cls3, (String) objArrZzqu[i12]);
                if (i88 == 9 || i88 == 17) {
                    i26 = 1;
                    objArr[((i78 / 3) << 1) + 1] = fieldZza4.getType();
                } else {
                    if (i88 == 27 || i88 == 49) {
                        i26 = 1;
                        i31 = i98 + 1;
                        objArr[((i78 / 3) << 1) + 1] = objArrZzqu[i98];
                    } else if (i88 == 12 || i88 == 30 || i88 == 44) {
                        i26 = 1;
                        if ((iCharAt3 & 1) == 1) {
                            i31 = i98 + 1;
                            objArr[((i78 / 3) << 1) + 1] = objArrZzqu[i98];
                        }
                    } else if (i88 == 50) {
                        int i99 = i74 + 1;
                        iArr[i74] = i78;
                        int i100 = (i78 / 3) << 1;
                        int i101 = i98 + 1;
                        objArr[i100] = objArrZzqu[i98];
                        if ((iCharAt12 & 2048) != 0) {
                            i98 = i101 + 1;
                            objArr[i100 + 1] = objArrZzqu[i101];
                            i74 = i99;
                            i26 = 1;
                        } else {
                            i98 = i101;
                            i26 = 1;
                            i74 = i99;
                        }
                    } else {
                        i26 = 1;
                    }
                    i98 = i31;
                }
                iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZza4);
                if ((iCharAt3 & 1) != i26 || i88 > 17) {
                    i27 = i98;
                    i28 = i37;
                    cls2 = cls3;
                    str = str3;
                    iObjectFieldOffset2 = 0;
                    i29 = 0;
                    if (i88 >= 18 && i88 <= 49) {
                        iArr[i76] = iObjectFieldOffset;
                        i76++;
                    }
                } else {
                    int i102 = i25 + 1;
                    str = str3;
                    int iCharAt14 = str.charAt(i25);
                    if (iCharAt14 >= 55296) {
                        int i103 = iCharAt14 & 8191;
                        int i104 = 13;
                        while (true) {
                            i30 = i102 + 1;
                            cCharAt9 = str.charAt(i102);
                            if (cCharAt9 < 55296) {
                                break;
                            }
                            i103 |= (cCharAt9 & 8191) << i104;
                            i104 += 13;
                            i102 = i30;
                        }
                        iCharAt14 = i103 | (cCharAt9 << i104);
                        i102 = i30;
                    }
                    int i105 = (i37 << 1) + (iCharAt14 / 32);
                    Object obj3 = objArrZzqu[i105];
                    i27 = i98;
                    if (obj3 instanceof Field) {
                        fieldZza = (Field) obj3;
                    } else {
                        fieldZza = zza(cls3, (String) obj3);
                        objArrZzqu[i105] = fieldZza;
                    }
                    i28 = i37;
                    cls2 = cls3;
                    i29 = iCharAt14 % 32;
                    iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZza);
                    i25 = i102;
                    if (i88 >= 18) {
                        iArr[i76] = iObjectFieldOffset;
                        i76++;
                    }
                }
            }
            int i106 = i78 + 1;
            iArr3[i78] = iCharAt11;
            int i107 = i106 + 1;
            iArr3[i106] = iObjectFieldOffset | ((iCharAt12 & 256) != 0 ? SQLiteDatabase.CREATE_IF_NECESSARY : 0) | ((iCharAt12 & 512) != 0 ? 536870912 : 0) | (i88 << 20);
            i78 = i107 + 1;
            iArr3[i107] = (i29 << 20) | iObjectFieldOffset2;
            i37 = i28;
            strZzqt = str;
            i75 = i25;
            cls3 = cls2;
            i11 = i89;
            length = i80;
            i13 = i23;
            z9 = z8;
            iCharAt = i90;
            i12 = i27;
            zzsxVar2 = zzsxVar;
        }
        return new zzso<>(iArr3, objArr, iCharAt, i11, zzsxVar2.zzqn(), z9, false, iArr, i13, i73, zzsrVar, zzruVar, zztrVar, zzqqVar, zzsfVar);
    }

    private final void zzb(T t8, T t9, int i9) {
        int iZzbr = zzbr(i9);
        int i10 = this.zzbcy[i9];
        long j9 = iZzbr & 1048575;
        if (zza((zzso<T>) t9, i10, i9)) {
            Object objZzp = zztx.zzp(t8, j9);
            Object objZzp2 = zztx.zzp(t9, j9);
            if (objZzp != null && objZzp2 != null) {
                zztx.zza(t8, j9, zzre.zzb(objZzp, objZzp2));
                zzb((zzso<T>) t8, i10, i9);
            } else if (objZzp2 != null) {
                zztx.zza(t8, j9, objZzp2);
                zzb((zzso<T>) t8, i10, i9);
            }
        }
    }

    private final zzsz zzbo(int i9) {
        int i10 = (i9 / 3) << 1;
        zzsz zzszVar = (zzsz) this.zzbcz[i10];
        if (zzszVar != null) {
            return zzszVar;
        }
        zzsz<T> zzszVarZzi = zzsw.zzqs().zzi((Class) this.zzbcz[i10 + 1]);
        this.zzbcz[i10] = zzszVarZzi;
        return zzszVarZzi;
    }

    private final Object zzbp(int i9) {
        return this.zzbcz[(i9 / 3) << 1];
    }

    private final zzrh zzbq(int i9) {
        return (zzrh) this.zzbcz[((i9 / 3) << 1) + 1];
    }

    private final int zzbr(int i9) {
        return this.zzbcy[i9 + 1];
    }

    private final int zzbs(int i9) {
        return this.zzbcy[i9 + 2];
    }

    private static boolean zzbt(int i9) {
        return (i9 & 536870912) != 0;
    }

    private final boolean zzc(T t8, T t9, int i9) {
        return zzb((zzso<T>) t8, i9) == zzb((zzso<T>) t9, i9);
    }

    private static <E> List<E> zze(Object obj, long j9) {
        return (List) zztx.zzp(obj, j9);
    }

    private static <T> double zzf(T t8, long j9) {
        return ((Double) zztx.zzp(t8, j9)).doubleValue();
    }

    private static <T> float zzg(T t8, long j9) {
        return ((Float) zztx.zzp(t8, j9)).floatValue();
    }

    private static <T> int zzh(T t8, long j9) {
        return ((Integer) zztx.zzp(t8, j9)).intValue();
    }

    private static <T> long zzi(T t8, long j9) {
        return ((Long) zztx.zzp(t8, j9)).longValue();
    }

    private static <T> boolean zzj(T t8, long j9) {
        return ((Boolean) zztx.zzp(t8, j9)).booleanValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x01c1  */
    @Override // com.google.android.gms.internal.gtm.zzsz
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean equals(T t8, T t9) {
        int length = this.zzbcy.length;
        int i9 = 0;
        while (true) {
            boolean zZze = true;
            if (i9 >= length) {
                if (!this.zzbdm.zzag(t8).equals(this.zzbdm.zzag(t9))) {
                    return false;
                }
                if (this.zzbdd) {
                    return this.zzbdn.zzr(t8).equals(this.zzbdn.zzr(t9));
                }
                return true;
            }
            int iZzbr = zzbr(i9);
            long j9 = iZzbr & 1048575;
            switch ((iZzbr & 267386880) >>> 20) {
                case 0:
                    if (!zzc(t8, t9, i9) || Double.doubleToLongBits(zztx.zzo(t8, j9)) != Double.doubleToLongBits(zztx.zzo(t9, j9))) {
                        zZze = false;
                        break;
                    }
                    break;
                case 1:
                    if (!zzc(t8, t9, i9) || Float.floatToIntBits(zztx.zzn(t8, j9)) != Float.floatToIntBits(zztx.zzn(t9, j9))) {
                    }
                    break;
                case 2:
                    if (!zzc(t8, t9, i9) || zztx.zzl(t8, j9) != zztx.zzl(t9, j9)) {
                    }
                    break;
                case 3:
                    if (!zzc(t8, t9, i9) || zztx.zzl(t8, j9) != zztx.zzl(t9, j9)) {
                    }
                    break;
                case 4:
                    if (!zzc(t8, t9, i9) || zztx.zzk(t8, j9) != zztx.zzk(t9, j9)) {
                    }
                    break;
                case 5:
                    if (!zzc(t8, t9, i9) || zztx.zzl(t8, j9) != zztx.zzl(t9, j9)) {
                    }
                    break;
                case 6:
                    if (!zzc(t8, t9, i9) || zztx.zzk(t8, j9) != zztx.zzk(t9, j9)) {
                    }
                    break;
                case 7:
                    if (!zzc(t8, t9, i9) || zztx.zzm(t8, j9) != zztx.zzm(t9, j9)) {
                    }
                    break;
                case 8:
                    if (!zzc(t8, t9, i9) || !zztb.zze(zztx.zzp(t8, j9), zztx.zzp(t9, j9))) {
                    }
                    break;
                case 9:
                    if (!zzc(t8, t9, i9) || !zztb.zze(zztx.zzp(t8, j9), zztx.zzp(t9, j9))) {
                    }
                    break;
                case 10:
                    if (!zzc(t8, t9, i9) || !zztb.zze(zztx.zzp(t8, j9), zztx.zzp(t9, j9))) {
                    }
                    break;
                case 11:
                    if (!zzc(t8, t9, i9) || zztx.zzk(t8, j9) != zztx.zzk(t9, j9)) {
                    }
                    break;
                case 12:
                    if (!zzc(t8, t9, i9) || zztx.zzk(t8, j9) != zztx.zzk(t9, j9)) {
                    }
                    break;
                case 13:
                    if (!zzc(t8, t9, i9) || zztx.zzk(t8, j9) != zztx.zzk(t9, j9)) {
                    }
                    break;
                case 14:
                    if (!zzc(t8, t9, i9) || zztx.zzl(t8, j9) != zztx.zzl(t9, j9)) {
                    }
                    break;
                case 15:
                    if (!zzc(t8, t9, i9) || zztx.zzk(t8, j9) != zztx.zzk(t9, j9)) {
                    }
                    break;
                case 16:
                    if (!zzc(t8, t9, i9) || zztx.zzl(t8, j9) != zztx.zzl(t9, j9)) {
                    }
                    break;
                case 17:
                    if (!zzc(t8, t9, i9) || !zztb.zze(zztx.zzp(t8, j9), zztx.zzp(t9, j9))) {
                    }
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
                    zZze = zztb.zze(zztx.zzp(t8, j9), zztx.zzp(t9, j9));
                    break;
                case 50:
                    zZze = zztb.zze(zztx.zzp(t8, j9), zztx.zzp(t9, j9));
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
                    long jZzbs = zzbs(i9) & 1048575;
                    if (zztx.zzk(t8, jZzbs) != zztx.zzk(t9, jZzbs) || !zztb.zze(zztx.zzp(t8, j9), zztx.zzp(t9, j9))) {
                    }
                    break;
            }
            if (!zZze) {
                return false;
            }
            i9 += 3;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzsz
    public final int hashCode(T t8) {
        int i9;
        int iZzz;
        int length = this.zzbcy.length;
        int i10 = 0;
        for (int i11 = 0; i11 < length; i11 += 3) {
            int iZzbr = zzbr(i11);
            int i12 = this.zzbcy[i11];
            long j9 = 1048575 & iZzbr;
            int iHashCode = 37;
            switch ((iZzbr & 267386880) >>> 20) {
                case 0:
                    i9 = i10 * 53;
                    iZzz = zzre.zzz(Double.doubleToLongBits(zztx.zzo(t8, j9)));
                    i10 = i9 + iZzz;
                    break;
                case 1:
                    i9 = i10 * 53;
                    iZzz = Float.floatToIntBits(zztx.zzn(t8, j9));
                    i10 = i9 + iZzz;
                    break;
                case 2:
                    i9 = i10 * 53;
                    iZzz = zzre.zzz(zztx.zzl(t8, j9));
                    i10 = i9 + iZzz;
                    break;
                case 3:
                    i9 = i10 * 53;
                    iZzz = zzre.zzz(zztx.zzl(t8, j9));
                    i10 = i9 + iZzz;
                    break;
                case 4:
                    i9 = i10 * 53;
                    iZzz = zztx.zzk(t8, j9);
                    i10 = i9 + iZzz;
                    break;
                case 5:
                    i9 = i10 * 53;
                    iZzz = zzre.zzz(zztx.zzl(t8, j9));
                    i10 = i9 + iZzz;
                    break;
                case 6:
                    i9 = i10 * 53;
                    iZzz = zztx.zzk(t8, j9);
                    i10 = i9 + iZzz;
                    break;
                case 7:
                    i9 = i10 * 53;
                    iZzz = zzre.zzk(zztx.zzm(t8, j9));
                    i10 = i9 + iZzz;
                    break;
                case 8:
                    i9 = i10 * 53;
                    iZzz = ((String) zztx.zzp(t8, j9)).hashCode();
                    i10 = i9 + iZzz;
                    break;
                case 9:
                    Object objZzp = zztx.zzp(t8, j9);
                    if (objZzp != null) {
                        iHashCode = objZzp.hashCode();
                    }
                    i10 = (i10 * 53) + iHashCode;
                    break;
                case 10:
                    i9 = i10 * 53;
                    iZzz = zztx.zzp(t8, j9).hashCode();
                    i10 = i9 + iZzz;
                    break;
                case 11:
                    i9 = i10 * 53;
                    iZzz = zztx.zzk(t8, j9);
                    i10 = i9 + iZzz;
                    break;
                case 12:
                    i9 = i10 * 53;
                    iZzz = zztx.zzk(t8, j9);
                    i10 = i9 + iZzz;
                    break;
                case 13:
                    i9 = i10 * 53;
                    iZzz = zztx.zzk(t8, j9);
                    i10 = i9 + iZzz;
                    break;
                case 14:
                    i9 = i10 * 53;
                    iZzz = zzre.zzz(zztx.zzl(t8, j9));
                    i10 = i9 + iZzz;
                    break;
                case 15:
                    i9 = i10 * 53;
                    iZzz = zztx.zzk(t8, j9);
                    i10 = i9 + iZzz;
                    break;
                case 16:
                    i9 = i10 * 53;
                    iZzz = zzre.zzz(zztx.zzl(t8, j9));
                    i10 = i9 + iZzz;
                    break;
                case 17:
                    Object objZzp2 = zztx.zzp(t8, j9);
                    if (objZzp2 != null) {
                        iHashCode = objZzp2.hashCode();
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
                    iZzz = zztx.zzp(t8, j9).hashCode();
                    i10 = i9 + iZzz;
                    break;
                case 50:
                    i9 = i10 * 53;
                    iZzz = zztx.zzp(t8, j9).hashCode();
                    i10 = i9 + iZzz;
                    break;
                case 51:
                    if (zza((zzso<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZzz = zzre.zzz(Double.doubleToLongBits(zzf(t8, j9)));
                        i10 = i9 + iZzz;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zza((zzso<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZzz = Float.floatToIntBits(zzg(t8, j9));
                        i10 = i9 + iZzz;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zza((zzso<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZzz = zzre.zzz(zzi(t8, j9));
                        i10 = i9 + iZzz;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zza((zzso<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZzz = zzre.zzz(zzi(t8, j9));
                        i10 = i9 + iZzz;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zza((zzso<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZzz = zzh(t8, j9);
                        i10 = i9 + iZzz;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zza((zzso<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZzz = zzre.zzz(zzi(t8, j9));
                        i10 = i9 + iZzz;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zza((zzso<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZzz = zzh(t8, j9);
                        i10 = i9 + iZzz;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zza((zzso<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZzz = zzre.zzk(zzj(t8, j9));
                        i10 = i9 + iZzz;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zza((zzso<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZzz = ((String) zztx.zzp(t8, j9)).hashCode();
                        i10 = i9 + iZzz;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zza((zzso<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZzz = zztx.zzp(t8, j9).hashCode();
                        i10 = i9 + iZzz;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zza((zzso<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZzz = zztx.zzp(t8, j9).hashCode();
                        i10 = i9 + iZzz;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zza((zzso<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZzz = zzh(t8, j9);
                        i10 = i9 + iZzz;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zza((zzso<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZzz = zzh(t8, j9);
                        i10 = i9 + iZzz;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zza((zzso<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZzz = zzh(t8, j9);
                        i10 = i9 + iZzz;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zza((zzso<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZzz = zzre.zzz(zzi(t8, j9));
                        i10 = i9 + iZzz;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zza((zzso<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZzz = zzh(t8, j9);
                        i10 = i9 + iZzz;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zza((zzso<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZzz = zzre.zzz(zzi(t8, j9));
                        i10 = i9 + iZzz;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zza((zzso<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZzz = zztx.zzp(t8, j9).hashCode();
                        i10 = i9 + iZzz;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int iHashCode2 = (i10 * 53) + this.zzbdm.zzag(t8).hashCode();
        return this.zzbdd ? (iHashCode2 * 53) + this.zzbdn.zzr(t8).hashCode() : iHashCode2;
    }

    @Override // com.google.android.gms.internal.gtm.zzsz
    public final T newInstance() {
        return (T) this.zzbdk.newInstance(this.zzbdc);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:421:0x090b A[PHI: r6
      0x090b: PHI (r6v4 int) = 
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v16 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v1 int)
      (r6v17 int)
      (r6v1 int)
     binds: [B:256:0x0545, B:459:0x09b0, B:453:0x0994, B:450:0x0982, B:447:0x0973, B:444:0x0966, B:441:0x0959, B:437:0x094e, B:434:0x0943, B:431:0x0936, B:428:0x0929, B:425:0x0916, B:396:0x081f, B:390:0x0802, B:384:0x07e5, B:378:0x07c8, B:372:0x07aa, B:366:0x078c, B:360:0x076e, B:354:0x0750, B:348:0x0732, B:342:0x0714, B:336:0x06f6, B:330:0x06d8, B:324:0x06ba, B:318:0x069c, B:313:0x0668, B:310:0x065b, B:307:0x064b, B:304:0x063b, B:301:0x062b, B:298:0x061d, B:295:0x0610, B:292:0x0603, B:286:0x05e5, B:283:0x05d1, B:280:0x05bf, B:277:0x05af, B:274:0x059f, B:439:0x0955, B:271:0x0592, B:268:0x0584, B:265:0x0574, B:262:0x0564, B:420:0x090a, B:259:0x054e] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.google.android.gms.internal.gtm.zzsz
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int zzad(T t8) {
        int i9;
        int i10;
        int i11;
        int i12;
        boolean z8;
        long j9;
        int iZzd;
        int iZzc;
        int iZzm;
        int iZzv;
        int iZzae;
        int iZzbb;
        int iZzbd;
        int iZzb;
        int iZzae2;
        int iZzbb2;
        int iZzbd2;
        int i13 = 267386880;
        int i14 = 1;
        int i15 = 0;
        if (this.zzbdf) {
            Unsafe unsafe = zzbcx;
            int i16 = 0;
            int i17 = 0;
            while (i16 < this.zzbcy.length) {
                int iZzbr = zzbr(i16);
                int i18 = (iZzbr & i13) >>> 20;
                int i19 = this.zzbcy[i16];
                long j10 = iZzbr & 1048575;
                int i20 = (i18 < zzqw.zzazb.m17532id() || i18 > zzqw.zzazo.m17532id()) ? 0 : this.zzbcy[i16 + 2] & 1048575;
                switch (i18) {
                    case 0:
                        if (zzb((zzso<T>) t8, i16)) {
                            iZzb = zzqj.zzb(i19, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzb((zzso<T>) t8, i16)) {
                            iZzb = zzqj.zzb(i19, BitmapDescriptorFactory.HUE_RED);
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzb((zzso<T>) t8, i16)) {
                            iZzb = zzqj.zzd(i19, zztx.zzl(t8, j10));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzb((zzso<T>) t8, i16)) {
                            iZzb = zzqj.zze(i19, zztx.zzl(t8, j10));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzb((zzso<T>) t8, i16)) {
                            iZzb = zzqj.zzi(i19, zztx.zzk(t8, j10));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzb((zzso<T>) t8, i16)) {
                            iZzb = zzqj.zzg(i19, 0L);
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzb((zzso<T>) t8, i16)) {
                            iZzb = zzqj.zzl(i19, 0);
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzb((zzso<T>) t8, i16)) {
                            iZzb = zzqj.zzc(i19, true);
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zzb((zzso<T>) t8, i16)) {
                            Object objZzp = zztx.zzp(t8, j10);
                            iZzb = objZzp instanceof zzps ? zzqj.zzc(i19, (zzps) objZzp) : zzqj.zzb(i19, (String) objZzp);
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        if (zzb((zzso<T>) t8, i16)) {
                            iZzb = zztb.zzc(i19, zztx.zzp(t8, j10), zzbo(i16));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zzb((zzso<T>) t8, i16)) {
                            iZzb = zzqj.zzc(i19, (zzps) zztx.zzp(t8, j10));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zzb((zzso<T>) t8, i16)) {
                            iZzb = zzqj.zzj(i19, zztx.zzk(t8, j10));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzb((zzso<T>) t8, i16)) {
                            iZzb = zzqj.zzn(i19, zztx.zzk(t8, j10));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzb((zzso<T>) t8, i16)) {
                            iZzb = zzqj.zzm(i19, 0);
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzb((zzso<T>) t8, i16)) {
                            iZzb = zzqj.zzh(i19, 0L);
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzb((zzso<T>) t8, i16)) {
                            iZzb = zzqj.zzk(i19, zztx.zzk(t8, j10));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzb((zzso<T>) t8, i16)) {
                            iZzb = zzqj.zzf(i19, zztx.zzl(t8, j10));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zzb((zzso<T>) t8, i16)) {
                            iZzb = zzqj.zzc(i19, (zzsk) zztx.zzp(t8, j10), zzbo(i16));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        iZzb = zztb.zzw(i19, zze(t8, j10), false);
                        i17 += iZzb;
                        break;
                    case 19:
                        iZzb = zztb.zzv(i19, zze(t8, j10), false);
                        i17 += iZzb;
                        break;
                    case 20:
                        iZzb = zztb.zzo(i19, zze(t8, j10), false);
                        i17 += iZzb;
                        break;
                    case 21:
                        iZzb = zztb.zzp(i19, zze(t8, j10), false);
                        i17 += iZzb;
                        break;
                    case 22:
                        iZzb = zztb.zzs(i19, zze(t8, j10), false);
                        i17 += iZzb;
                        break;
                    case 23:
                        iZzb = zztb.zzw(i19, zze(t8, j10), false);
                        i17 += iZzb;
                        break;
                    case 24:
                        iZzb = zztb.zzv(i19, zze(t8, j10), false);
                        i17 += iZzb;
                        break;
                    case 25:
                        iZzb = zztb.zzx(i19, zze(t8, j10), false);
                        i17 += iZzb;
                        break;
                    case 26:
                        iZzb = zztb.zzc(i19, zze(t8, j10));
                        i17 += iZzb;
                        break;
                    case 27:
                        iZzb = zztb.zzc(i19, (List<?>) zze(t8, j10), zzbo(i16));
                        i17 += iZzb;
                        break;
                    case 28:
                        iZzb = zztb.zzd(i19, zze(t8, j10));
                        i17 += iZzb;
                        break;
                    case 29:
                        iZzb = zztb.zzt(i19, zze(t8, j10), false);
                        i17 += iZzb;
                        break;
                    case 30:
                        iZzb = zztb.zzr(i19, zze(t8, j10), false);
                        i17 += iZzb;
                        break;
                    case 31:
                        iZzb = zztb.zzv(i19, zze(t8, j10), false);
                        i17 += iZzb;
                        break;
                    case 32:
                        iZzb = zztb.zzw(i19, zze(t8, j10), false);
                        i17 += iZzb;
                        break;
                    case 33:
                        iZzb = zztb.zzu(i19, zze(t8, j10), false);
                        i17 += iZzb;
                        break;
                    case 34:
                        iZzb = zztb.zzq(i19, zze(t8, j10), false);
                        i17 += iZzb;
                        break;
                    case 35:
                        iZzae2 = zztb.zzae((List) unsafe.getObject(t8, j10));
                        if (iZzae2 <= 0) {
                            break;
                        } else {
                            if (this.zzbdg) {
                                unsafe.putInt(t8, i20, iZzae2);
                            }
                            iZzbb2 = zzqj.zzbb(i19);
                            iZzbd2 = zzqj.zzbd(iZzae2);
                            iZzb = iZzbb2 + iZzbd2 + iZzae2;
                            i17 += iZzb;
                            break;
                        }
                    case 36:
                        iZzae2 = zztb.zzad((List) unsafe.getObject(t8, j10));
                        if (iZzae2 <= 0) {
                            break;
                        } else {
                            if (this.zzbdg) {
                                unsafe.putInt(t8, i20, iZzae2);
                            }
                            iZzbb2 = zzqj.zzbb(i19);
                            iZzbd2 = zzqj.zzbd(iZzae2);
                            iZzb = iZzbb2 + iZzbd2 + iZzae2;
                            i17 += iZzb;
                            break;
                        }
                    case 37:
                        iZzae2 = zztb.zzw((List) unsafe.getObject(t8, j10));
                        if (iZzae2 <= 0) {
                            break;
                        } else {
                            if (this.zzbdg) {
                                unsafe.putInt(t8, i20, iZzae2);
                            }
                            iZzbb2 = zzqj.zzbb(i19);
                            iZzbd2 = zzqj.zzbd(iZzae2);
                            iZzb = iZzbb2 + iZzbd2 + iZzae2;
                            i17 += iZzb;
                            break;
                        }
                    case 38:
                        iZzae2 = zztb.zzx((List) unsafe.getObject(t8, j10));
                        if (iZzae2 <= 0) {
                            break;
                        } else {
                            if (this.zzbdg) {
                                unsafe.putInt(t8, i20, iZzae2);
                            }
                            iZzbb2 = zzqj.zzbb(i19);
                            iZzbd2 = zzqj.zzbd(iZzae2);
                            iZzb = iZzbb2 + iZzbd2 + iZzae2;
                            i17 += iZzb;
                            break;
                        }
                    case 39:
                        iZzae2 = zztb.zzaa((List) unsafe.getObject(t8, j10));
                        if (iZzae2 <= 0) {
                            break;
                        } else {
                            if (this.zzbdg) {
                                unsafe.putInt(t8, i20, iZzae2);
                            }
                            iZzbb2 = zzqj.zzbb(i19);
                            iZzbd2 = zzqj.zzbd(iZzae2);
                            iZzb = iZzbb2 + iZzbd2 + iZzae2;
                            i17 += iZzb;
                            break;
                        }
                    case 40:
                        iZzae2 = zztb.zzae((List) unsafe.getObject(t8, j10));
                        if (iZzae2 <= 0) {
                            break;
                        } else {
                            if (this.zzbdg) {
                                unsafe.putInt(t8, i20, iZzae2);
                            }
                            iZzbb2 = zzqj.zzbb(i19);
                            iZzbd2 = zzqj.zzbd(iZzae2);
                            iZzb = iZzbb2 + iZzbd2 + iZzae2;
                            i17 += iZzb;
                            break;
                        }
                    case 41:
                        iZzae2 = zztb.zzad((List) unsafe.getObject(t8, j10));
                        if (iZzae2 <= 0) {
                            break;
                        } else {
                            if (this.zzbdg) {
                                unsafe.putInt(t8, i20, iZzae2);
                            }
                            iZzbb2 = zzqj.zzbb(i19);
                            iZzbd2 = zzqj.zzbd(iZzae2);
                            iZzb = iZzbb2 + iZzbd2 + iZzae2;
                            i17 += iZzb;
                            break;
                        }
                    case 42:
                        iZzae2 = zztb.zzaf((List) unsafe.getObject(t8, j10));
                        if (iZzae2 <= 0) {
                            break;
                        } else {
                            if (this.zzbdg) {
                                unsafe.putInt(t8, i20, iZzae2);
                            }
                            iZzbb2 = zzqj.zzbb(i19);
                            iZzbd2 = zzqj.zzbd(iZzae2);
                            iZzb = iZzbb2 + iZzbd2 + iZzae2;
                            i17 += iZzb;
                            break;
                        }
                    case 43:
                        iZzae2 = zztb.zzab((List) unsafe.getObject(t8, j10));
                        if (iZzae2 <= 0) {
                            break;
                        } else {
                            if (this.zzbdg) {
                                unsafe.putInt(t8, i20, iZzae2);
                            }
                            iZzbb2 = zzqj.zzbb(i19);
                            iZzbd2 = zzqj.zzbd(iZzae2);
                            iZzb = iZzbb2 + iZzbd2 + iZzae2;
                            i17 += iZzb;
                            break;
                        }
                    case 44:
                        iZzae2 = zztb.zzz((List) unsafe.getObject(t8, j10));
                        if (iZzae2 <= 0) {
                            break;
                        } else {
                            if (this.zzbdg) {
                                unsafe.putInt(t8, i20, iZzae2);
                            }
                            iZzbb2 = zzqj.zzbb(i19);
                            iZzbd2 = zzqj.zzbd(iZzae2);
                            iZzb = iZzbb2 + iZzbd2 + iZzae2;
                            i17 += iZzb;
                            break;
                        }
                    case 45:
                        iZzae2 = zztb.zzad((List) unsafe.getObject(t8, j10));
                        if (iZzae2 <= 0) {
                            break;
                        } else {
                            if (this.zzbdg) {
                                unsafe.putInt(t8, i20, iZzae2);
                            }
                            iZzbb2 = zzqj.zzbb(i19);
                            iZzbd2 = zzqj.zzbd(iZzae2);
                            iZzb = iZzbb2 + iZzbd2 + iZzae2;
                            i17 += iZzb;
                            break;
                        }
                    case 46:
                        iZzae2 = zztb.zzae((List) unsafe.getObject(t8, j10));
                        if (iZzae2 <= 0) {
                            break;
                        } else {
                            if (this.zzbdg) {
                                unsafe.putInt(t8, i20, iZzae2);
                            }
                            iZzbb2 = zzqj.zzbb(i19);
                            iZzbd2 = zzqj.zzbd(iZzae2);
                            iZzb = iZzbb2 + iZzbd2 + iZzae2;
                            i17 += iZzb;
                            break;
                        }
                    case 47:
                        iZzae2 = zztb.zzac((List) unsafe.getObject(t8, j10));
                        if (iZzae2 <= 0) {
                            break;
                        } else {
                            if (this.zzbdg) {
                                unsafe.putInt(t8, i20, iZzae2);
                            }
                            iZzbb2 = zzqj.zzbb(i19);
                            iZzbd2 = zzqj.zzbd(iZzae2);
                            iZzb = iZzbb2 + iZzbd2 + iZzae2;
                            i17 += iZzb;
                            break;
                        }
                    case 48:
                        iZzae2 = zztb.zzy((List) unsafe.getObject(t8, j10));
                        if (iZzae2 <= 0) {
                            break;
                        } else {
                            if (this.zzbdg) {
                                unsafe.putInt(t8, i20, iZzae2);
                            }
                            iZzbb2 = zzqj.zzbb(i19);
                            iZzbd2 = zzqj.zzbd(iZzae2);
                            iZzb = iZzbb2 + iZzbd2 + iZzae2;
                            i17 += iZzb;
                            break;
                        }
                    case 49:
                        iZzb = zztb.zzd(i19, zze(t8, j10), zzbo(i16));
                        i17 += iZzb;
                        break;
                    case 50:
                        iZzb = this.zzbdo.zzb(i19, zztx.zzp(t8, j10), zzbp(i16));
                        i17 += iZzb;
                        break;
                    case 51:
                        if (zza((zzso<T>) t8, i19, i16)) {
                            iZzb = zzqj.zzb(i19, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zza((zzso<T>) t8, i19, i16)) {
                            iZzb = zzqj.zzb(i19, BitmapDescriptorFactory.HUE_RED);
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zza((zzso<T>) t8, i19, i16)) {
                            iZzb = zzqj.zzd(i19, zzi(t8, j10));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zza((zzso<T>) t8, i19, i16)) {
                            iZzb = zzqj.zze(i19, zzi(t8, j10));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zza((zzso<T>) t8, i19, i16)) {
                            iZzb = zzqj.zzi(i19, zzh(t8, j10));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zza((zzso<T>) t8, i19, i16)) {
                            iZzb = zzqj.zzg(i19, 0L);
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zza((zzso<T>) t8, i19, i16)) {
                            iZzb = zzqj.zzl(i19, 0);
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zza((zzso<T>) t8, i19, i16)) {
                            iZzb = zzqj.zzc(i19, true);
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zza((zzso<T>) t8, i19, i16)) {
                            Object objZzp2 = zztx.zzp(t8, j10);
                            iZzb = objZzp2 instanceof zzps ? zzqj.zzc(i19, (zzps) objZzp2) : zzqj.zzb(i19, (String) objZzp2);
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (zza((zzso<T>) t8, i19, i16)) {
                            iZzb = zztb.zzc(i19, zztx.zzp(t8, j10), zzbo(i16));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zza((zzso<T>) t8, i19, i16)) {
                            iZzb = zzqj.zzc(i19, (zzps) zztx.zzp(t8, j10));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zza((zzso<T>) t8, i19, i16)) {
                            iZzb = zzqj.zzj(i19, zzh(t8, j10));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zza((zzso<T>) t8, i19, i16)) {
                            iZzb = zzqj.zzn(i19, zzh(t8, j10));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zza((zzso<T>) t8, i19, i16)) {
                            iZzb = zzqj.zzm(i19, 0);
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zza((zzso<T>) t8, i19, i16)) {
                            iZzb = zzqj.zzh(i19, 0L);
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zza((zzso<T>) t8, i19, i16)) {
                            iZzb = zzqj.zzk(i19, zzh(t8, j10));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zza((zzso<T>) t8, i19, i16)) {
                            iZzb = zzqj.zzf(i19, zzi(t8, j10));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zza((zzso<T>) t8, i19, i16)) {
                            iZzb = zzqj.zzc(i19, (zzsk) zztx.zzp(t8, j10), zzbo(i16));
                            i17 += iZzb;
                            break;
                        } else {
                            break;
                        }
                }
                i16 += 3;
                i13 = 267386880;
            }
            return i17 + zza(this.zzbdm, t8);
        }
        Unsafe unsafe2 = zzbcx;
        int i21 = -1;
        int i22 = 0;
        int iZzb2 = 0;
        int i23 = 0;
        while (i22 < this.zzbcy.length) {
            int iZzbr2 = zzbr(i22);
            int[] iArr = this.zzbcy;
            int i24 = iArr[i22];
            int i25 = (iZzbr2 & 267386880) >>> 20;
            if (i25 <= 17) {
                int i26 = iArr[i22 + 2];
                int i27 = i26 & 1048575;
                i10 = i14 << (i26 >>> 20);
                if (i27 != i21) {
                    i23 = unsafe2.getInt(t8, i27);
                    i21 = i27;
                }
                i9 = i26;
            } else {
                i9 = (!this.zzbdg || i25 < zzqw.zzazb.m17532id() || i25 > zzqw.zzazo.m17532id()) ? 0 : this.zzbcy[i22 + 2] & 1048575;
                i10 = 0;
            }
            long j11 = iZzbr2 & 1048575;
            switch (i25) {
                case 0:
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    if ((i23 & i10) != 0) {
                        iZzb2 += zzqj.zzb(i24, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    i11 = 1;
                    i12 = 0;
                    j9 = 0;
                    if ((i23 & i10) != 0) {
                        z8 = false;
                        iZzb2 += zzqj.zzb(i24, BitmapDescriptorFactory.HUE_RED);
                    } else {
                        z8 = false;
                    }
                    break;
                case 2:
                    i11 = 1;
                    i12 = 0;
                    j9 = 0;
                    if ((i23 & i10) != 0) {
                        iZzd = zzqj.zzd(i24, unsafe2.getLong(t8, j11));
                        iZzb2 += iZzd;
                    }
                    z8 = false;
                    break;
                case 3:
                    i11 = 1;
                    i12 = 0;
                    j9 = 0;
                    if ((i23 & i10) != 0) {
                        iZzd = zzqj.zze(i24, unsafe2.getLong(t8, j11));
                        iZzb2 += iZzd;
                    }
                    z8 = false;
                    break;
                case 4:
                    i11 = 1;
                    i12 = 0;
                    j9 = 0;
                    if ((i23 & i10) != 0) {
                        iZzd = zzqj.zzi(i24, unsafe2.getInt(t8, j11));
                        iZzb2 += iZzd;
                    }
                    z8 = false;
                    break;
                case 5:
                    i11 = 1;
                    i12 = 0;
                    j9 = 0;
                    if ((i23 & i10) != 0) {
                        iZzd = zzqj.zzg(i24, 0L);
                        iZzb2 += iZzd;
                    }
                    z8 = false;
                    break;
                case 6:
                    i11 = 1;
                    if ((i23 & i10) != 0) {
                        i12 = 0;
                        iZzb2 += zzqj.zzl(i24, 0);
                    } else {
                        i12 = 0;
                    }
                    z8 = false;
                    j9 = 0;
                    break;
                case 7:
                    if ((i23 & i10) != 0) {
                        i11 = 1;
                        iZzb2 += zzqj.zzc(i24, true);
                    } else {
                        i11 = 1;
                    }
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 8:
                    if ((i23 & i10) != 0) {
                        Object object = unsafe2.getObject(t8, j11);
                        iZzc = object instanceof zzps ? zzqj.zzc(i24, (zzps) object) : zzqj.zzb(i24, (String) object);
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 9:
                    if ((i23 & i10) != 0) {
                        iZzc = zztb.zzc(i24, unsafe2.getObject(t8, j11), zzbo(i22));
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 10:
                    if ((i23 & i10) != 0) {
                        iZzc = zzqj.zzc(i24, (zzps) unsafe2.getObject(t8, j11));
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 11:
                    if ((i23 & i10) != 0) {
                        iZzc = zzqj.zzj(i24, unsafe2.getInt(t8, j11));
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 12:
                    if ((i23 & i10) != 0) {
                        iZzc = zzqj.zzn(i24, unsafe2.getInt(t8, j11));
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 13:
                    if ((i23 & i10) != 0) {
                        iZzm = zzqj.zzm(i24, 0);
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 14:
                    if ((i23 & i10) != 0) {
                        iZzc = zzqj.zzh(i24, 0L);
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 15:
                    if ((i23 & i10) != 0) {
                        iZzc = zzqj.zzk(i24, unsafe2.getInt(t8, j11));
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 16:
                    if ((i23 & i10) != 0) {
                        iZzc = zzqj.zzf(i24, unsafe2.getLong(t8, j11));
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 17:
                    if ((i23 & i10) != 0) {
                        iZzc = zzqj.zzc(i24, (zzsk) unsafe2.getObject(t8, j11), zzbo(i22));
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 18:
                    iZzc = zztb.zzw(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb2 += iZzc;
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 19:
                    i12 = 0;
                    iZzv = zztb.zzv(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb2 += iZzv;
                    i11 = 1;
                    z8 = false;
                    j9 = 0;
                    break;
                case 20:
                    i12 = 0;
                    iZzv = zztb.zzo(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb2 += iZzv;
                    i11 = 1;
                    z8 = false;
                    j9 = 0;
                    break;
                case 21:
                    i12 = 0;
                    iZzv = zztb.zzp(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb2 += iZzv;
                    i11 = 1;
                    z8 = false;
                    j9 = 0;
                    break;
                case 22:
                    i12 = 0;
                    iZzv = zztb.zzs(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb2 += iZzv;
                    i11 = 1;
                    z8 = false;
                    j9 = 0;
                    break;
                case 23:
                    i12 = 0;
                    iZzv = zztb.zzw(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb2 += iZzv;
                    i11 = 1;
                    z8 = false;
                    j9 = 0;
                    break;
                case 24:
                    i12 = 0;
                    iZzv = zztb.zzv(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb2 += iZzv;
                    i11 = 1;
                    z8 = false;
                    j9 = 0;
                    break;
                case 25:
                    i12 = 0;
                    iZzv = zztb.zzx(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb2 += iZzv;
                    i11 = 1;
                    z8 = false;
                    j9 = 0;
                    break;
                case 26:
                    iZzc = zztb.zzc(i24, (List) unsafe2.getObject(t8, j11));
                    iZzb2 += iZzc;
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 27:
                    iZzc = zztb.zzc(i24, (List<?>) unsafe2.getObject(t8, j11), zzbo(i22));
                    iZzb2 += iZzc;
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 28:
                    iZzc = zztb.zzd(i24, (List) unsafe2.getObject(t8, j11));
                    iZzb2 += iZzc;
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 29:
                    iZzc = zztb.zzt(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb2 += iZzc;
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 30:
                    i12 = 0;
                    iZzv = zztb.zzr(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb2 += iZzv;
                    i11 = 1;
                    z8 = false;
                    j9 = 0;
                    break;
                case 31:
                    i12 = 0;
                    iZzv = zztb.zzv(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb2 += iZzv;
                    i11 = 1;
                    z8 = false;
                    j9 = 0;
                    break;
                case 32:
                    i12 = 0;
                    iZzv = zztb.zzw(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb2 += iZzv;
                    i11 = 1;
                    z8 = false;
                    j9 = 0;
                    break;
                case 33:
                    i12 = 0;
                    iZzv = zztb.zzu(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb2 += iZzv;
                    i11 = 1;
                    z8 = false;
                    j9 = 0;
                    break;
                case 34:
                    i12 = 0;
                    iZzv = zztb.zzq(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb2 += iZzv;
                    i11 = 1;
                    z8 = false;
                    j9 = 0;
                    break;
                case 35:
                    iZzae = zztb.zzae((List) unsafe2.getObject(t8, j11));
                    if (iZzae > 0) {
                        if (this.zzbdg) {
                            unsafe2.putInt(t8, i9, iZzae);
                        }
                        iZzbb = zzqj.zzbb(i24);
                        iZzbd = zzqj.zzbd(iZzae);
                        iZzm = iZzbb + iZzbd + iZzae;
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 36:
                    iZzae = zztb.zzad((List) unsafe2.getObject(t8, j11));
                    if (iZzae > 0) {
                        if (this.zzbdg) {
                            unsafe2.putInt(t8, i9, iZzae);
                        }
                        iZzbb = zzqj.zzbb(i24);
                        iZzbd = zzqj.zzbd(iZzae);
                        iZzm = iZzbb + iZzbd + iZzae;
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 37:
                    iZzae = zztb.zzw((List) unsafe2.getObject(t8, j11));
                    if (iZzae > 0) {
                        if (this.zzbdg) {
                            unsafe2.putInt(t8, i9, iZzae);
                        }
                        iZzbb = zzqj.zzbb(i24);
                        iZzbd = zzqj.zzbd(iZzae);
                        iZzm = iZzbb + iZzbd + iZzae;
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 38:
                    iZzae = zztb.zzx((List) unsafe2.getObject(t8, j11));
                    if (iZzae > 0) {
                        if (this.zzbdg) {
                            unsafe2.putInt(t8, i9, iZzae);
                        }
                        iZzbb = zzqj.zzbb(i24);
                        iZzbd = zzqj.zzbd(iZzae);
                        iZzm = iZzbb + iZzbd + iZzae;
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 39:
                    iZzae = zztb.zzaa((List) unsafe2.getObject(t8, j11));
                    if (iZzae > 0) {
                        if (this.zzbdg) {
                            unsafe2.putInt(t8, i9, iZzae);
                        }
                        iZzbb = zzqj.zzbb(i24);
                        iZzbd = zzqj.zzbd(iZzae);
                        iZzm = iZzbb + iZzbd + iZzae;
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 40:
                    iZzae = zztb.zzae((List) unsafe2.getObject(t8, j11));
                    if (iZzae > 0) {
                        if (this.zzbdg) {
                            unsafe2.putInt(t8, i9, iZzae);
                        }
                        iZzbb = zzqj.zzbb(i24);
                        iZzbd = zzqj.zzbd(iZzae);
                        iZzm = iZzbb + iZzbd + iZzae;
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 41:
                    iZzae = zztb.zzad((List) unsafe2.getObject(t8, j11));
                    if (iZzae > 0) {
                        if (this.zzbdg) {
                            unsafe2.putInt(t8, i9, iZzae);
                        }
                        iZzbb = zzqj.zzbb(i24);
                        iZzbd = zzqj.zzbd(iZzae);
                        iZzm = iZzbb + iZzbd + iZzae;
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 42:
                    iZzae = zztb.zzaf((List) unsafe2.getObject(t8, j11));
                    if (iZzae > 0) {
                        if (this.zzbdg) {
                            unsafe2.putInt(t8, i9, iZzae);
                        }
                        iZzbb = zzqj.zzbb(i24);
                        iZzbd = zzqj.zzbd(iZzae);
                        iZzm = iZzbb + iZzbd + iZzae;
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 43:
                    iZzae = zztb.zzab((List) unsafe2.getObject(t8, j11));
                    if (iZzae > 0) {
                        if (this.zzbdg) {
                            unsafe2.putInt(t8, i9, iZzae);
                        }
                        iZzbb = zzqj.zzbb(i24);
                        iZzbd = zzqj.zzbd(iZzae);
                        iZzm = iZzbb + iZzbd + iZzae;
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 44:
                    iZzae = zztb.zzz((List) unsafe2.getObject(t8, j11));
                    if (iZzae > 0) {
                        if (this.zzbdg) {
                            unsafe2.putInt(t8, i9, iZzae);
                        }
                        iZzbb = zzqj.zzbb(i24);
                        iZzbd = zzqj.zzbd(iZzae);
                        iZzm = iZzbb + iZzbd + iZzae;
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 45:
                    iZzae = zztb.zzad((List) unsafe2.getObject(t8, j11));
                    if (iZzae > 0) {
                        if (this.zzbdg) {
                            unsafe2.putInt(t8, i9, iZzae);
                        }
                        iZzbb = zzqj.zzbb(i24);
                        iZzbd = zzqj.zzbd(iZzae);
                        iZzm = iZzbb + iZzbd + iZzae;
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 46:
                    iZzae = zztb.zzae((List) unsafe2.getObject(t8, j11));
                    if (iZzae > 0) {
                        if (this.zzbdg) {
                            unsafe2.putInt(t8, i9, iZzae);
                        }
                        iZzbb = zzqj.zzbb(i24);
                        iZzbd = zzqj.zzbd(iZzae);
                        iZzm = iZzbb + iZzbd + iZzae;
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 47:
                    iZzae = zztb.zzac((List) unsafe2.getObject(t8, j11));
                    if (iZzae > 0) {
                        if (this.zzbdg) {
                            unsafe2.putInt(t8, i9, iZzae);
                        }
                        iZzbb = zzqj.zzbb(i24);
                        iZzbd = zzqj.zzbd(iZzae);
                        iZzm = iZzbb + iZzbd + iZzae;
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 48:
                    iZzae = zztb.zzy((List) unsafe2.getObject(t8, j11));
                    if (iZzae > 0) {
                        if (this.zzbdg) {
                            unsafe2.putInt(t8, i9, iZzae);
                        }
                        iZzbb = zzqj.zzbb(i24);
                        iZzbd = zzqj.zzbd(iZzae);
                        iZzm = iZzbb + iZzbd + iZzae;
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 49:
                    iZzc = zztb.zzd(i24, (List) unsafe2.getObject(t8, j11), zzbo(i22));
                    iZzb2 += iZzc;
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 50:
                    iZzc = this.zzbdo.zzb(i24, unsafe2.getObject(t8, j11), zzbp(i22));
                    iZzb2 += iZzc;
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 51:
                    if (zza((zzso<T>) t8, i24, i22)) {
                        iZzc = zzqj.zzb(i24, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 52:
                    if (zza((zzso<T>) t8, i24, i22)) {
                        iZzm = zzqj.zzb(i24, BitmapDescriptorFactory.HUE_RED);
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 53:
                    if (zza((zzso<T>) t8, i24, i22)) {
                        iZzc = zzqj.zzd(i24, zzi(t8, j11));
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 54:
                    if (zza((zzso<T>) t8, i24, i22)) {
                        iZzc = zzqj.zze(i24, zzi(t8, j11));
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 55:
                    if (zza((zzso<T>) t8, i24, i22)) {
                        iZzc = zzqj.zzi(i24, zzh(t8, j11));
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 56:
                    if (zza((zzso<T>) t8, i24, i22)) {
                        iZzc = zzqj.zzg(i24, 0L);
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 57:
                    if (zza((zzso<T>) t8, i24, i22)) {
                        iZzm = zzqj.zzl(i24, 0);
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 58:
                    if (zza((zzso<T>) t8, i24, i22)) {
                        iZzm = zzqj.zzc(i24, true);
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 59:
                    if (zza((zzso<T>) t8, i24, i22)) {
                        Object object2 = unsafe2.getObject(t8, j11);
                        iZzc = object2 instanceof zzps ? zzqj.zzc(i24, (zzps) object2) : zzqj.zzb(i24, (String) object2);
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 60:
                    if (zza((zzso<T>) t8, i24, i22)) {
                        iZzc = zztb.zzc(i24, unsafe2.getObject(t8, j11), zzbo(i22));
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 61:
                    if (zza((zzso<T>) t8, i24, i22)) {
                        iZzc = zzqj.zzc(i24, (zzps) unsafe2.getObject(t8, j11));
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 62:
                    if (zza((zzso<T>) t8, i24, i22)) {
                        iZzc = zzqj.zzj(i24, zzh(t8, j11));
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 63:
                    if (zza((zzso<T>) t8, i24, i22)) {
                        iZzc = zzqj.zzn(i24, zzh(t8, j11));
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 64:
                    if (zza((zzso<T>) t8, i24, i22)) {
                        iZzm = zzqj.zzm(i24, 0);
                        iZzb2 += iZzm;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 65:
                    if (zza((zzso<T>) t8, i24, i22)) {
                        iZzc = zzqj.zzh(i24, 0L);
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 66:
                    if (zza((zzso<T>) t8, i24, i22)) {
                        iZzc = zzqj.zzk(i24, zzh(t8, j11));
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 67:
                    if (zza((zzso<T>) t8, i24, i22)) {
                        iZzc = zzqj.zzf(i24, zzi(t8, j11));
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
                case 68:
                    if (zza((zzso<T>) t8, i24, i22)) {
                        iZzc = zzqj.zzc(i24, (zzsk) unsafe2.getObject(t8, j11), zzbo(i22));
                        iZzb2 += iZzc;
                    }
                    i11 = 1;
                    i12 = 0;
                    z8 = false;
                    j9 = 0;
                    break;
            }
            i22 += 3;
            i15 = i12;
            i14 = i11;
        }
        int iZzb3 = i15;
        int iZza = iZzb2 + zza(this.zzbdm, t8);
        if (!this.zzbdd) {
            return iZza;
        }
        zzqt<T> zzqtVarZzr = this.zzbdn.zzr(t8);
        for (int i28 = iZzb3; i28 < zzqtVarZzr.zzaxo.zzra(); i28++) {
            Map.Entry entryZzbv = zzqtVarZzr.zzaxo.zzbv(i28);
            iZzb3 += zzqt.zzb((zzqv<?>) entryZzbv.getKey(), entryZzbv.getValue());
        }
        for (Map.Entry entry : zzqtVarZzr.zzaxo.zzrb()) {
            iZzb3 += zzqt.zzb((zzqv<?>) entry.getKey(), entry.getValue());
        }
        return iZza + iZzb3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ca  */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.google.android.gms.internal.gtm.zzsz] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12, types: [com.google.android.gms.internal.gtm.zzsz] */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    @Override // com.google.android.gms.internal.gtm.zzsz
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean zzae(T t8) {
        int i9;
        int i10 = -1;
        int i11 = 0;
        int i12 = 0;
        while (true) {
            boolean z8 = true;
            if (i11 >= this.zzbdi) {
                return !this.zzbdd || this.zzbdn.zzr(t8).isInitialized();
            }
            int i13 = this.zzbdh[i11];
            int i14 = this.zzbcy[i13];
            int iZzbr = zzbr(i13);
            if (this.zzbdf) {
                i9 = 0;
            } else {
                int i15 = this.zzbcy[i13 + 2];
                int i16 = i15 & 1048575;
                i9 = 1 << (i15 >>> 20);
                if (i16 != i10) {
                    i12 = zzbcx.getInt(t8, i16);
                    i10 = i16;
                }
            }
            if (((268435456 & iZzbr) != 0) && !zza((zzso<T>) t8, i13, i12, i9)) {
                return false;
            }
            int i17 = (267386880 & iZzbr) >>> 20;
            if (i17 == 9 || i17 == 17) {
                if (zza((zzso<T>) t8, i13, i12, i9) && !zza(t8, iZzbr, zzbo(i13))) {
                    return false;
                }
            } else if (i17 == 27) {
                List list = (List) zztx.zzp(t8, iZzbr & 1048575);
                if (!list.isEmpty()) {
                    ?? Zzbo = zzbo(i13);
                    int i18 = 0;
                    while (true) {
                        if (i18 >= list.size()) {
                            break;
                        }
                        if (!Zzbo.zzae(list.get(i18))) {
                            z8 = false;
                            break;
                        }
                        i18++;
                    }
                }
                if (!z8) {
                    return false;
                }
            } else if (i17 == 60 || i17 == 68) {
                if (zza((zzso<T>) t8, i14, i13) && !zza(t8, iZzbr, zzbo(i13))) {
                    return false;
                }
            } else if (i17 != 49) {
                if (i17 != 50) {
                    continue;
                } else {
                    Map<?, ?> mapZzy = this.zzbdo.zzy(zztx.zzp(t8, iZzbr & 1048575));
                    if (!mapZzy.isEmpty()) {
                        if (this.zzbdo.zzac(zzbp(i13)).zzbcr.zzrs() == zzul.MESSAGE) {
                            Iterator<?> it = mapZzy.values().iterator();
                            ?? Zzi = 0;
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                Object next = it.next();
                                Zzi = Zzi;
                                if (Zzi == 0) {
                                    Zzi = zzsw.zzqs().zzi(next.getClass());
                                }
                                if (!Zzi.zzae(next)) {
                                    z8 = false;
                                    break;
                                }
                            }
                        }
                    }
                    if (!z8) {
                        return false;
                    }
                }
            }
            i11++;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzsz
    public final void zzd(T t8, T t9) {
        t9.getClass();
        for (int i9 = 0; i9 < this.zzbcy.length; i9 += 3) {
            int iZzbr = zzbr(i9);
            long j9 = 1048575 & iZzbr;
            int i10 = this.zzbcy[i9];
            switch ((iZzbr & 267386880) >>> 20) {
                case 0:
                    if (zzb((zzso<T>) t9, i9)) {
                        zztx.zza(t8, j9, zztx.zzo(t9, j9));
                        zzc(t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzb((zzso<T>) t9, i9)) {
                        zztx.zza((Object) t8, j9, zztx.zzn(t9, j9));
                        zzc(t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzb((zzso<T>) t9, i9)) {
                        zztx.zza((Object) t8, j9, zztx.zzl(t9, j9));
                        zzc(t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzb((zzso<T>) t9, i9)) {
                        zztx.zza((Object) t8, j9, zztx.zzl(t9, j9));
                        zzc(t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzb((zzso<T>) t9, i9)) {
                        zztx.zzb(t8, j9, zztx.zzk(t9, j9));
                        zzc(t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzb((zzso<T>) t9, i9)) {
                        zztx.zza((Object) t8, j9, zztx.zzl(t9, j9));
                        zzc(t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzb((zzso<T>) t9, i9)) {
                        zztx.zzb(t8, j9, zztx.zzk(t9, j9));
                        zzc(t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzb((zzso<T>) t9, i9)) {
                        zztx.zza(t8, j9, zztx.zzm(t9, j9));
                        zzc(t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzb((zzso<T>) t9, i9)) {
                        zztx.zza(t8, j9, zztx.zzp(t9, j9));
                        zzc(t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zza(t8, t9, i9);
                    break;
                case 10:
                    if (zzb((zzso<T>) t9, i9)) {
                        zztx.zza(t8, j9, zztx.zzp(t9, j9));
                        zzc(t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzb((zzso<T>) t9, i9)) {
                        zztx.zzb(t8, j9, zztx.zzk(t9, j9));
                        zzc(t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzb((zzso<T>) t9, i9)) {
                        zztx.zzb(t8, j9, zztx.zzk(t9, j9));
                        zzc(t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzb((zzso<T>) t9, i9)) {
                        zztx.zzb(t8, j9, zztx.zzk(t9, j9));
                        zzc(t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzb((zzso<T>) t9, i9)) {
                        zztx.zza((Object) t8, j9, zztx.zzl(t9, j9));
                        zzc(t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzb((zzso<T>) t9, i9)) {
                        zztx.zzb(t8, j9, zztx.zzk(t9, j9));
                        zzc(t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzb((zzso<T>) t9, i9)) {
                        zztx.zza((Object) t8, j9, zztx.zzl(t9, j9));
                        zzc(t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zza(t8, t9, i9);
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
                    this.zzbdl.zza(t8, t9, j9);
                    break;
                case 50:
                    zztb.zza(this.zzbdo, t8, t9, j9);
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
                    if (zza((zzso<T>) t9, i10, i9)) {
                        zztx.zza(t8, j9, zztx.zzp(t9, j9));
                        zzb((zzso<T>) t8, i10, i9);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzb(t8, t9, i9);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zza((zzso<T>) t9, i10, i9)) {
                        zztx.zza(t8, j9, zztx.zzp(t9, j9));
                        zzb((zzso<T>) t8, i10, i9);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzb(t8, t9, i9);
                    break;
            }
        }
        if (this.zzbdf) {
            return;
        }
        zztb.zza(this.zzbdm, t8, t9);
        if (this.zzbdd) {
            zztb.zza(this.zzbdn, t8, t9);
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzsz
    public final void zzt(T t8) {
        int i9;
        int i10 = this.zzbdi;
        while (true) {
            i9 = this.zzbdj;
            if (i10 >= i9) {
                break;
            }
            long jZzbr = zzbr(this.zzbdh[i10]) & 1048575;
            Object objZzp = zztx.zzp(t8, jZzbr);
            if (objZzp != null) {
                zztx.zza(t8, jZzbr, this.zzbdo.zzaa(objZzp));
            }
            i10++;
        }
        int length = this.zzbdh.length;
        while (i9 < length) {
            this.zzbdl.zzb(t8, this.zzbdh[i9]);
            i9++;
        }
        this.zzbdm.zzt(t8);
        if (this.zzbdd) {
            this.zzbdn.zzt(t8);
        }
    }

    private final void zzc(T t8, int i9) {
        if (this.zzbdf) {
            return;
        }
        int iZzbs = zzbs(i9);
        long j9 = iZzbs & 1048575;
        zztx.zzb(t8, j9, zztx.zzk(t8, j9) | (1 << (iZzbs >>> 20)));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void zzb(T t8, zzum zzumVar) {
        Iterator it;
        Map.Entry<?, ?> entry;
        int i9;
        boolean z8;
        if (this.zzbdd) {
            zzqt<T> zzqtVarZzr = this.zzbdn.zzr(t8);
            if (zzqtVarZzr.zzaxo.isEmpty()) {
                it = null;
                entry = null;
            } else {
                it = zzqtVarZzr.iterator();
                entry = (Map.Entry) it.next();
            }
        }
        int length = this.zzbcy.length;
        Unsafe unsafe = zzbcx;
        int i10 = -1;
        int i11 = 0;
        for (int i12 = 0; i12 < length; i12 += 3) {
            int iZzbr = zzbr(i12);
            int[] iArr = this.zzbcy;
            int i13 = iArr[i12];
            int i14 = (267386880 & iZzbr) >>> 20;
            if (this.zzbdf || i14 > 17) {
                i9 = 0;
            } else {
                int i15 = iArr[i12 + 2];
                int i16 = i15 & 1048575;
                if (i16 != i10) {
                    i11 = unsafe.getInt(t8, i16);
                    i10 = i16;
                }
                i9 = 1 << (i15 >>> 20);
            }
            while (entry != null && this.zzbdn.zzb(entry) <= i13) {
                this.zzbdn.zza(zzumVar, entry);
                entry = it.hasNext() ? (Map.Entry) it.next() : null;
            }
            long j9 = iZzbr & 1048575;
            switch (i14) {
                case 0:
                    if ((i9 & i11) != 0) {
                        zzumVar.zza(i13, zztx.zzo(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if ((i9 & i11) != 0) {
                        zzumVar.zza(i13, zztx.zzn(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if ((i9 & i11) != 0) {
                        zzumVar.zzi(i13, unsafe.getLong(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if ((i9 & i11) != 0) {
                        zzumVar.zza(i13, unsafe.getLong(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if ((i9 & i11) != 0) {
                        zzumVar.zze(i13, unsafe.getInt(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if ((i9 & i11) != 0) {
                        zzumVar.zzc(i13, unsafe.getLong(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if ((i9 & i11) != 0) {
                        zzumVar.zzh(i13, unsafe.getInt(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if ((i9 & i11) != 0) {
                        zzumVar.zzb(i13, zztx.zzm(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if ((i9 & i11) != 0) {
                        zza(i13, unsafe.getObject(t8, j9), zzumVar);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    if ((i9 & i11) != 0) {
                        zzumVar.zza(i13, unsafe.getObject(t8, j9), zzbo(i12));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if ((i9 & i11) != 0) {
                        zzumVar.zza(i13, (zzps) unsafe.getObject(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if ((i9 & i11) != 0) {
                        zzumVar.zzf(i13, unsafe.getInt(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if ((i9 & i11) != 0) {
                        zzumVar.zzp(i13, unsafe.getInt(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if ((i9 & i11) != 0) {
                        zzumVar.zzo(i13, unsafe.getInt(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if ((i9 & i11) != 0) {
                        zzumVar.zzj(i13, unsafe.getLong(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if ((i9 & i11) != 0) {
                        zzumVar.zzg(i13, unsafe.getInt(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if ((i9 & i11) != 0) {
                        zzumVar.zzb(i13, unsafe.getLong(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if ((i9 & i11) != 0) {
                        zzumVar.zzb(i13, unsafe.getObject(t8, j9), zzbo(i12));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    zztb.zza(this.zzbcy[i12], (List<Double>) unsafe.getObject(t8, j9), zzumVar, false);
                    break;
                case 19:
                    zztb.zzb(this.zzbcy[i12], (List<Float>) unsafe.getObject(t8, j9), zzumVar, false);
                    break;
                case 20:
                    zztb.zzc(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, false);
                    break;
                case 21:
                    zztb.zzd(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, false);
                    break;
                case 22:
                    zztb.zzh(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, false);
                    break;
                case 23:
                    zztb.zzf(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, false);
                    break;
                case 24:
                    zztb.zzk(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, false);
                    break;
                case 25:
                    zztb.zzn(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, false);
                    break;
                case 26:
                    zztb.zza(this.zzbcy[i12], (List<String>) unsafe.getObject(t8, j9), zzumVar);
                    break;
                case 27:
                    zztb.zza(this.zzbcy[i12], (List<?>) unsafe.getObject(t8, j9), zzumVar, zzbo(i12));
                    break;
                case 28:
                    zztb.zzb(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar);
                    break;
                case 29:
                    z8 = false;
                    zztb.zzi(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, false);
                    break;
                case 30:
                    z8 = false;
                    zztb.zzm(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, false);
                    break;
                case 31:
                    z8 = false;
                    zztb.zzl(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, false);
                    break;
                case 32:
                    z8 = false;
                    zztb.zzg(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, false);
                    break;
                case 33:
                    z8 = false;
                    zztb.zzj(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, false);
                    break;
                case 34:
                    z8 = false;
                    zztb.zze(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, false);
                    break;
                case 35:
                    zztb.zza(this.zzbcy[i12], (List<Double>) unsafe.getObject(t8, j9), zzumVar, true);
                    break;
                case 36:
                    zztb.zzb(this.zzbcy[i12], (List<Float>) unsafe.getObject(t8, j9), zzumVar, true);
                    break;
                case 37:
                    zztb.zzc(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, true);
                    break;
                case 38:
                    zztb.zzd(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, true);
                    break;
                case 39:
                    zztb.zzh(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, true);
                    break;
                case 40:
                    zztb.zzf(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, true);
                    break;
                case 41:
                    zztb.zzk(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, true);
                    break;
                case 42:
                    zztb.zzn(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, true);
                    break;
                case 43:
                    zztb.zzi(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, true);
                    break;
                case 44:
                    zztb.zzm(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, true);
                    break;
                case 45:
                    zztb.zzl(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, true);
                    break;
                case 46:
                    zztb.zzg(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, true);
                    break;
                case 47:
                    zztb.zzj(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, true);
                    break;
                case 48:
                    zztb.zze(this.zzbcy[i12], (List) unsafe.getObject(t8, j9), zzumVar, true);
                    break;
                case 49:
                    zztb.zzb(this.zzbcy[i12], (List<?>) unsafe.getObject(t8, j9), zzumVar, zzbo(i12));
                    break;
                case 50:
                    zza(zzumVar, i13, unsafe.getObject(t8, j9), i12);
                    break;
                case 51:
                    if (zza((zzso<T>) t8, i13, i12)) {
                        zzumVar.zza(i13, zzf(t8, j9));
                    }
                    break;
                case 52:
                    if (zza((zzso<T>) t8, i13, i12)) {
                        zzumVar.zza(i13, zzg(t8, j9));
                    }
                    break;
                case 53:
                    if (zza((zzso<T>) t8, i13, i12)) {
                        zzumVar.zzi(i13, zzi(t8, j9));
                    }
                    break;
                case 54:
                    if (zza((zzso<T>) t8, i13, i12)) {
                        zzumVar.zza(i13, zzi(t8, j9));
                    }
                    break;
                case 55:
                    if (zza((zzso<T>) t8, i13, i12)) {
                        zzumVar.zze(i13, zzh(t8, j9));
                    }
                    break;
                case 56:
                    if (zza((zzso<T>) t8, i13, i12)) {
                        zzumVar.zzc(i13, zzi(t8, j9));
                    }
                    break;
                case 57:
                    if (zza((zzso<T>) t8, i13, i12)) {
                        zzumVar.zzh(i13, zzh(t8, j9));
                    }
                    break;
                case 58:
                    if (zza((zzso<T>) t8, i13, i12)) {
                        zzumVar.zzb(i13, zzj(t8, j9));
                    }
                    break;
                case 59:
                    if (zza((zzso<T>) t8, i13, i12)) {
                        zza(i13, unsafe.getObject(t8, j9), zzumVar);
                    }
                    break;
                case 60:
                    if (zza((zzso<T>) t8, i13, i12)) {
                        zzumVar.zza(i13, unsafe.getObject(t8, j9), zzbo(i12));
                    }
                    break;
                case 61:
                    if (zza((zzso<T>) t8, i13, i12)) {
                        zzumVar.zza(i13, (zzps) unsafe.getObject(t8, j9));
                    }
                    break;
                case 62:
                    if (zza((zzso<T>) t8, i13, i12)) {
                        zzumVar.zzf(i13, zzh(t8, j9));
                    }
                    break;
                case 63:
                    if (zza((zzso<T>) t8, i13, i12)) {
                        zzumVar.zzp(i13, zzh(t8, j9));
                    }
                    break;
                case 64:
                    if (zza((zzso<T>) t8, i13, i12)) {
                        zzumVar.zzo(i13, zzh(t8, j9));
                    }
                    break;
                case 65:
                    if (zza((zzso<T>) t8, i13, i12)) {
                        zzumVar.zzj(i13, zzi(t8, j9));
                    }
                    break;
                case 66:
                    if (zza((zzso<T>) t8, i13, i12)) {
                        zzumVar.zzg(i13, zzh(t8, j9));
                    }
                    break;
                case 67:
                    if (zza((zzso<T>) t8, i13, i12)) {
                        zzumVar.zzb(i13, zzi(t8, j9));
                    }
                    break;
                case 68:
                    if (zza((zzso<T>) t8, i13, i12)) {
                        zzumVar.zzb(i13, unsafe.getObject(t8, j9), zzbo(i12));
                    }
                    break;
            }
        }
        while (entry != null) {
            this.zzbdn.zza(zzumVar, entry);
            entry = it.hasNext() ? (Map.Entry) it.next() : null;
        }
        zza(this.zzbdm, t8, zzumVar);
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String string = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + name.length() + String.valueOf(string).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(string);
            throw new RuntimeException(sb.toString());
        }
    }

    private final void zza(T t8, T t9, int i9) {
        long jZzbr = zzbr(i9) & 1048575;
        if (zzb((zzso<T>) t9, i9)) {
            Object objZzp = zztx.zzp(t8, jZzbr);
            Object objZzp2 = zztx.zzp(t9, jZzbr);
            if (objZzp != null && objZzp2 != null) {
                zztx.zza(t8, jZzbr, zzre.zzb(objZzp, objZzp2));
                zzc(t8, i9);
            } else if (objZzp2 != null) {
                zztx.zza(t8, jZzbr, objZzp2);
                zzc(t8, i9);
            }
        }
    }

    private static <UT, UB> int zza(zztr<UT, UB> zztrVar, T t8) {
        return zztrVar.zzad(zztrVar.zzag(t8));
    }

    /* JADX WARN: Removed duplicated region for block: B:178:0x054a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0032  */
    @Override // com.google.android.gms.internal.gtm.zzsz
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(T t8, zzum zzumVar) {
        Iterator it;
        Map.Entry<?, ?> entry;
        Iterator itDescendingIterator;
        Map.Entry<?, ?> entry2;
        if (zzumVar.zzol() == zzrc.zze.zzbbd) {
            zza(this.zzbdm, t8, zzumVar);
            if (this.zzbdd) {
                zzqt<T> zzqtVarZzr = this.zzbdn.zzr(t8);
                if (zzqtVarZzr.zzaxo.isEmpty()) {
                    itDescendingIterator = null;
                    entry2 = null;
                } else {
                    itDescendingIterator = zzqtVarZzr.descendingIterator();
                    entry2 = (Map.Entry) itDescendingIterator.next();
                }
            }
            for (int length = this.zzbcy.length - 3; length >= 0; length -= 3) {
                int iZzbr = zzbr(length);
                int i9 = this.zzbcy[length];
                while (entry2 != null && this.zzbdn.zzb(entry2) > i9) {
                    this.zzbdn.zza(zzumVar, entry2);
                    entry2 = itDescendingIterator.hasNext() ? (Map.Entry) itDescendingIterator.next() : null;
                }
                switch ((iZzbr & 267386880) >>> 20) {
                    case 0:
                        if (zzb((zzso<T>) t8, length)) {
                            zzumVar.zza(i9, zztx.zzo(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzb((zzso<T>) t8, length)) {
                            zzumVar.zza(i9, zztx.zzn(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzb((zzso<T>) t8, length)) {
                            zzumVar.zzi(i9, zztx.zzl(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzb((zzso<T>) t8, length)) {
                            zzumVar.zza(i9, zztx.zzl(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzb((zzso<T>) t8, length)) {
                            zzumVar.zze(i9, zztx.zzk(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzb((zzso<T>) t8, length)) {
                            zzumVar.zzc(i9, zztx.zzl(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzb((zzso<T>) t8, length)) {
                            zzumVar.zzh(i9, zztx.zzk(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzb((zzso<T>) t8, length)) {
                            zzumVar.zzb(i9, zztx.zzm(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zzb((zzso<T>) t8, length)) {
                            zza(i9, zztx.zzp(t8, iZzbr & 1048575), zzumVar);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        if (zzb((zzso<T>) t8, length)) {
                            zzumVar.zza(i9, zztx.zzp(t8, iZzbr & 1048575), zzbo(length));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zzb((zzso<T>) t8, length)) {
                            zzumVar.zza(i9, (zzps) zztx.zzp(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zzb((zzso<T>) t8, length)) {
                            zzumVar.zzf(i9, zztx.zzk(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzb((zzso<T>) t8, length)) {
                            zzumVar.zzp(i9, zztx.zzk(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzb((zzso<T>) t8, length)) {
                            zzumVar.zzo(i9, zztx.zzk(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzb((zzso<T>) t8, length)) {
                            zzumVar.zzj(i9, zztx.zzl(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzb((zzso<T>) t8, length)) {
                            zzumVar.zzg(i9, zztx.zzk(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzb((zzso<T>) t8, length)) {
                            zzumVar.zzb(i9, zztx.zzl(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zzb((zzso<T>) t8, length)) {
                            zzumVar.zzb(i9, zztx.zzp(t8, iZzbr & 1048575), zzbo(length));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        zztb.zza(this.zzbcy[length], (List<Double>) zztx.zzp(t8, iZzbr & 1048575), zzumVar, false);
                        break;
                    case 19:
                        zztb.zzb(this.zzbcy[length], (List<Float>) zztx.zzp(t8, iZzbr & 1048575), zzumVar, false);
                        break;
                    case 20:
                        zztb.zzc(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, false);
                        break;
                    case 21:
                        zztb.zzd(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, false);
                        break;
                    case 22:
                        zztb.zzh(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, false);
                        break;
                    case 23:
                        zztb.zzf(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, false);
                        break;
                    case 24:
                        zztb.zzk(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, false);
                        break;
                    case 25:
                        zztb.zzn(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, false);
                        break;
                    case 26:
                        zztb.zza(this.zzbcy[length], (List<String>) zztx.zzp(t8, iZzbr & 1048575), zzumVar);
                        break;
                    case 27:
                        zztb.zza(this.zzbcy[length], (List<?>) zztx.zzp(t8, iZzbr & 1048575), zzumVar, zzbo(length));
                        break;
                    case 28:
                        zztb.zzb(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar);
                        break;
                    case 29:
                        zztb.zzi(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, false);
                        break;
                    case 30:
                        zztb.zzm(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, false);
                        break;
                    case 31:
                        zztb.zzl(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, false);
                        break;
                    case 32:
                        zztb.zzg(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, false);
                        break;
                    case 33:
                        zztb.zzj(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, false);
                        break;
                    case 34:
                        zztb.zze(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, false);
                        break;
                    case 35:
                        zztb.zza(this.zzbcy[length], (List<Double>) zztx.zzp(t8, iZzbr & 1048575), zzumVar, true);
                        break;
                    case 36:
                        zztb.zzb(this.zzbcy[length], (List<Float>) zztx.zzp(t8, iZzbr & 1048575), zzumVar, true);
                        break;
                    case 37:
                        zztb.zzc(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, true);
                        break;
                    case 38:
                        zztb.zzd(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, true);
                        break;
                    case 39:
                        zztb.zzh(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, true);
                        break;
                    case 40:
                        zztb.zzf(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, true);
                        break;
                    case 41:
                        zztb.zzk(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, true);
                        break;
                    case 42:
                        zztb.zzn(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, true);
                        break;
                    case 43:
                        zztb.zzi(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, true);
                        break;
                    case 44:
                        zztb.zzm(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, true);
                        break;
                    case 45:
                        zztb.zzl(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, true);
                        break;
                    case 46:
                        zztb.zzg(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, true);
                        break;
                    case 47:
                        zztb.zzj(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, true);
                        break;
                    case 48:
                        zztb.zze(this.zzbcy[length], (List) zztx.zzp(t8, iZzbr & 1048575), zzumVar, true);
                        break;
                    case 49:
                        zztb.zzb(this.zzbcy[length], (List<?>) zztx.zzp(t8, iZzbr & 1048575), zzumVar, zzbo(length));
                        break;
                    case 50:
                        zza(zzumVar, i9, zztx.zzp(t8, iZzbr & 1048575), length);
                        break;
                    case 51:
                        if (zza((zzso<T>) t8, i9, length)) {
                            zzumVar.zza(i9, zzf(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zza((zzso<T>) t8, i9, length)) {
                            zzumVar.zza(i9, zzg(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zza((zzso<T>) t8, i9, length)) {
                            zzumVar.zzi(i9, zzi(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zza((zzso<T>) t8, i9, length)) {
                            zzumVar.zza(i9, zzi(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zza((zzso<T>) t8, i9, length)) {
                            zzumVar.zze(i9, zzh(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zza((zzso<T>) t8, i9, length)) {
                            zzumVar.zzc(i9, zzi(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zza((zzso<T>) t8, i9, length)) {
                            zzumVar.zzh(i9, zzh(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zza((zzso<T>) t8, i9, length)) {
                            zzumVar.zzb(i9, zzj(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zza((zzso<T>) t8, i9, length)) {
                            zza(i9, zztx.zzp(t8, iZzbr & 1048575), zzumVar);
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (zza((zzso<T>) t8, i9, length)) {
                            zzumVar.zza(i9, zztx.zzp(t8, iZzbr & 1048575), zzbo(length));
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zza((zzso<T>) t8, i9, length)) {
                            zzumVar.zza(i9, (zzps) zztx.zzp(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zza((zzso<T>) t8, i9, length)) {
                            zzumVar.zzf(i9, zzh(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zza((zzso<T>) t8, i9, length)) {
                            zzumVar.zzp(i9, zzh(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zza((zzso<T>) t8, i9, length)) {
                            zzumVar.zzo(i9, zzh(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zza((zzso<T>) t8, i9, length)) {
                            zzumVar.zzj(i9, zzi(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zza((zzso<T>) t8, i9, length)) {
                            zzumVar.zzg(i9, zzh(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zza((zzso<T>) t8, i9, length)) {
                            zzumVar.zzb(i9, zzi(t8, iZzbr & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zza((zzso<T>) t8, i9, length)) {
                            zzumVar.zzb(i9, zztx.zzp(t8, iZzbr & 1048575), zzbo(length));
                            break;
                        } else {
                            break;
                        }
                }
            }
            while (entry2 != null) {
                this.zzbdn.zza(zzumVar, entry2);
                entry2 = itDescendingIterator.hasNext() ? (Map.Entry) itDescendingIterator.next() : null;
            }
            return;
        }
        if (this.zzbdf) {
            if (this.zzbdd) {
                zzqt<T> zzqtVarZzr2 = this.zzbdn.zzr(t8);
                if (zzqtVarZzr2.zzaxo.isEmpty()) {
                    it = null;
                    entry = null;
                } else {
                    it = zzqtVarZzr2.iterator();
                    entry = (Map.Entry) it.next();
                }
            }
            int length2 = this.zzbcy.length;
            for (int i10 = 0; i10 < length2; i10 += 3) {
                int iZzbr2 = zzbr(i10);
                int i11 = this.zzbcy[i10];
                while (entry != null && this.zzbdn.zzb(entry) <= i11) {
                    this.zzbdn.zza(zzumVar, entry);
                    entry = it.hasNext() ? (Map.Entry) it.next() : null;
                }
                switch ((iZzbr2 & 267386880) >>> 20) {
                    case 0:
                        if (zzb((zzso<T>) t8, i10)) {
                            zzumVar.zza(i11, zztx.zzo(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzb((zzso<T>) t8, i10)) {
                            zzumVar.zza(i11, zztx.zzn(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzb((zzso<T>) t8, i10)) {
                            zzumVar.zzi(i11, zztx.zzl(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzb((zzso<T>) t8, i10)) {
                            zzumVar.zza(i11, zztx.zzl(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzb((zzso<T>) t8, i10)) {
                            zzumVar.zze(i11, zztx.zzk(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzb((zzso<T>) t8, i10)) {
                            zzumVar.zzc(i11, zztx.zzl(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzb((zzso<T>) t8, i10)) {
                            zzumVar.zzh(i11, zztx.zzk(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzb((zzso<T>) t8, i10)) {
                            zzumVar.zzb(i11, zztx.zzm(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zzb((zzso<T>) t8, i10)) {
                            zza(i11, zztx.zzp(t8, iZzbr2 & 1048575), zzumVar);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        if (zzb((zzso<T>) t8, i10)) {
                            zzumVar.zza(i11, zztx.zzp(t8, iZzbr2 & 1048575), zzbo(i10));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zzb((zzso<T>) t8, i10)) {
                            zzumVar.zza(i11, (zzps) zztx.zzp(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zzb((zzso<T>) t8, i10)) {
                            zzumVar.zzf(i11, zztx.zzk(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzb((zzso<T>) t8, i10)) {
                            zzumVar.zzp(i11, zztx.zzk(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzb((zzso<T>) t8, i10)) {
                            zzumVar.zzo(i11, zztx.zzk(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzb((zzso<T>) t8, i10)) {
                            zzumVar.zzj(i11, zztx.zzl(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzb((zzso<T>) t8, i10)) {
                            zzumVar.zzg(i11, zztx.zzk(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzb((zzso<T>) t8, i10)) {
                            zzumVar.zzb(i11, zztx.zzl(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zzb((zzso<T>) t8, i10)) {
                            zzumVar.zzb(i11, zztx.zzp(t8, iZzbr2 & 1048575), zzbo(i10));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        zztb.zza(this.zzbcy[i10], (List<Double>) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, false);
                        break;
                    case 19:
                        zztb.zzb(this.zzbcy[i10], (List<Float>) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, false);
                        break;
                    case 20:
                        zztb.zzc(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, false);
                        break;
                    case 21:
                        zztb.zzd(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, false);
                        break;
                    case 22:
                        zztb.zzh(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, false);
                        break;
                    case 23:
                        zztb.zzf(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, false);
                        break;
                    case 24:
                        zztb.zzk(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, false);
                        break;
                    case 25:
                        zztb.zzn(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, false);
                        break;
                    case 26:
                        zztb.zza(this.zzbcy[i10], (List<String>) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar);
                        break;
                    case 27:
                        zztb.zza(this.zzbcy[i10], (List<?>) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, zzbo(i10));
                        break;
                    case 28:
                        zztb.zzb(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar);
                        break;
                    case 29:
                        zztb.zzi(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, false);
                        break;
                    case 30:
                        zztb.zzm(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, false);
                        break;
                    case 31:
                        zztb.zzl(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, false);
                        break;
                    case 32:
                        zztb.zzg(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, false);
                        break;
                    case 33:
                        zztb.zzj(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, false);
                        break;
                    case 34:
                        zztb.zze(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, false);
                        break;
                    case 35:
                        zztb.zza(this.zzbcy[i10], (List<Double>) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, true);
                        break;
                    case 36:
                        zztb.zzb(this.zzbcy[i10], (List<Float>) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, true);
                        break;
                    case 37:
                        zztb.zzc(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, true);
                        break;
                    case 38:
                        zztb.zzd(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, true);
                        break;
                    case 39:
                        zztb.zzh(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, true);
                        break;
                    case 40:
                        zztb.zzf(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, true);
                        break;
                    case 41:
                        zztb.zzk(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, true);
                        break;
                    case 42:
                        zztb.zzn(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, true);
                        break;
                    case 43:
                        zztb.zzi(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, true);
                        break;
                    case 44:
                        zztb.zzm(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, true);
                        break;
                    case 45:
                        zztb.zzl(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, true);
                        break;
                    case 46:
                        zztb.zzg(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, true);
                        break;
                    case 47:
                        zztb.zzj(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, true);
                        break;
                    case 48:
                        zztb.zze(this.zzbcy[i10], (List) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, true);
                        break;
                    case 49:
                        zztb.zzb(this.zzbcy[i10], (List<?>) zztx.zzp(t8, iZzbr2 & 1048575), zzumVar, zzbo(i10));
                        break;
                    case 50:
                        zza(zzumVar, i11, zztx.zzp(t8, iZzbr2 & 1048575), i10);
                        break;
                    case 51:
                        if (zza((zzso<T>) t8, i11, i10)) {
                            zzumVar.zza(i11, zzf(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zza((zzso<T>) t8, i11, i10)) {
                            zzumVar.zza(i11, zzg(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zza((zzso<T>) t8, i11, i10)) {
                            zzumVar.zzi(i11, zzi(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zza((zzso<T>) t8, i11, i10)) {
                            zzumVar.zza(i11, zzi(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zza((zzso<T>) t8, i11, i10)) {
                            zzumVar.zze(i11, zzh(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zza((zzso<T>) t8, i11, i10)) {
                            zzumVar.zzc(i11, zzi(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zza((zzso<T>) t8, i11, i10)) {
                            zzumVar.zzh(i11, zzh(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zza((zzso<T>) t8, i11, i10)) {
                            zzumVar.zzb(i11, zzj(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zza((zzso<T>) t8, i11, i10)) {
                            zza(i11, zztx.zzp(t8, iZzbr2 & 1048575), zzumVar);
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (zza((zzso<T>) t8, i11, i10)) {
                            zzumVar.zza(i11, zztx.zzp(t8, iZzbr2 & 1048575), zzbo(i10));
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zza((zzso<T>) t8, i11, i10)) {
                            zzumVar.zza(i11, (zzps) zztx.zzp(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zza((zzso<T>) t8, i11, i10)) {
                            zzumVar.zzf(i11, zzh(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zza((zzso<T>) t8, i11, i10)) {
                            zzumVar.zzp(i11, zzh(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zza((zzso<T>) t8, i11, i10)) {
                            zzumVar.zzo(i11, zzh(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zza((zzso<T>) t8, i11, i10)) {
                            zzumVar.zzj(i11, zzi(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zza((zzso<T>) t8, i11, i10)) {
                            zzumVar.zzg(i11, zzh(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zza((zzso<T>) t8, i11, i10)) {
                            zzumVar.zzb(i11, zzi(t8, iZzbr2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zza((zzso<T>) t8, i11, i10)) {
                            zzumVar.zzb(i11, zztx.zzp(t8, iZzbr2 & 1048575), zzbo(i10));
                            break;
                        } else {
                            break;
                        }
                }
            }
            while (entry != null) {
                this.zzbdn.zza(zzumVar, entry);
                entry = it.hasNext() ? (Map.Entry) it.next() : null;
            }
            zza(this.zzbdm, t8, zzumVar);
            return;
        }
        zzb((zzso<T>) t8, zzumVar);
    }

    private final boolean zzb(T t8, int i9) {
        if (this.zzbdf) {
            int iZzbr = zzbr(i9);
            long j9 = iZzbr & 1048575;
            switch ((iZzbr & 267386880) >>> 20) {
                case 0:
                    return zztx.zzo(t8, j9) != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                case 1:
                    return zztx.zzn(t8, j9) != BitmapDescriptorFactory.HUE_RED;
                case 2:
                    return zztx.zzl(t8, j9) != 0;
                case 3:
                    return zztx.zzl(t8, j9) != 0;
                case 4:
                    return zztx.zzk(t8, j9) != 0;
                case 5:
                    return zztx.zzl(t8, j9) != 0;
                case 6:
                    return zztx.zzk(t8, j9) != 0;
                case 7:
                    return zztx.zzm(t8, j9);
                case 8:
                    Object objZzp = zztx.zzp(t8, j9);
                    if (objZzp instanceof String) {
                        return !((String) objZzp).isEmpty();
                    }
                    if (objZzp instanceof zzps) {
                        return !zzps.zzavx.equals(objZzp);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zztx.zzp(t8, j9) != null;
                case 10:
                    return !zzps.zzavx.equals(zztx.zzp(t8, j9));
                case 11:
                    return zztx.zzk(t8, j9) != 0;
                case 12:
                    return zztx.zzk(t8, j9) != 0;
                case 13:
                    return zztx.zzk(t8, j9) != 0;
                case 14:
                    return zztx.zzl(t8, j9) != 0;
                case 15:
                    return zztx.zzk(t8, j9) != 0;
                case 16:
                    return zztx.zzl(t8, j9) != 0;
                case 17:
                    return zztx.zzp(t8, j9) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        int iZzbs = zzbs(i9);
        return (zztx.zzk(t8, (long) (iZzbs & 1048575)) & (1 << (iZzbs >>> 20))) != 0;
    }

    private final void zzb(T t8, int i9, int i10) {
        zztx.zzb(t8, zzbs(i10) & 1048575, i9);
    }

    private final <K, V> void zza(zzum zzumVar, int i9, Object obj, int i10) {
        if (obj != null) {
            zzumVar.zza(i9, this.zzbdo.zzac(zzbp(i10)), this.zzbdo.zzy(obj));
        }
    }

    private static <UT, UB> void zza(zztr<UT, UB> zztrVar, T t8, zzum zzumVar) {
        zztrVar.zza((zztr<UT, UB>) zztrVar.zzag(t8), zzumVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:176:0x05df A[LOOP:6: B:174:0x05db->B:176:0x05df, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:178:0x05ec  */
    @Override // com.google.android.gms.internal.gtm.zzsz
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(T t8, zzsy zzsyVar, zzqp zzqpVar) {
        int i9;
        int iZzog;
        int i10;
        zzqpVar.getClass();
        zztr zztrVar = this.zzbdm;
        zzqq<?> zzqqVar = this.zzbdn;
        Object objZzs = null;
        Object objZza = null;
        while (true) {
            try {
                iZzog = zzsyVar.zzog();
                i10 = -1;
                if (iZzog >= this.zzbda && iZzog <= this.zzbdb) {
                    int length = (this.zzbcy.length / 3) - 1;
                    int i11 = 0;
                    while (true) {
                        if (i11 <= length) {
                            int i12 = (length + i11) >>> 1;
                            int i13 = i12 * 3;
                            int i14 = this.zzbcy[i13];
                            if (iZzog == i14) {
                                i10 = i13;
                            } else if (iZzog < i14) {
                                length = i12 - 1;
                            } else {
                                i11 = i12 + 1;
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                while (i9 < this.zzbdj) {
                }
                if (objZza != null) {
                }
                throw th;
            }
            if (i10 >= 0) {
                int iZzbr = zzbr(i10);
                switch ((267386880 & iZzbr) >>> 20) {
                    case 0:
                        zztx.zza(t8, iZzbr & 1048575, zzsyVar.readDouble());
                        zzc(t8, i10);
                        continue;
                    case 1:
                        zztx.zza((Object) t8, iZzbr & 1048575, zzsyVar.readFloat());
                        zzc(t8, i10);
                        continue;
                    case 2:
                        zztx.zza((Object) t8, iZzbr & 1048575, zzsyVar.zznk());
                        zzc(t8, i10);
                        continue;
                    case 3:
                        zztx.zza((Object) t8, iZzbr & 1048575, zzsyVar.zznj());
                        zzc(t8, i10);
                        continue;
                    case 4:
                        zztx.zzb(t8, iZzbr & 1048575, zzsyVar.zznl());
                        zzc(t8, i10);
                        continue;
                    case 5:
                        zztx.zza((Object) t8, iZzbr & 1048575, zzsyVar.zznm());
                        zzc(t8, i10);
                        continue;
                    case 6:
                        zztx.zzb(t8, iZzbr & 1048575, zzsyVar.zznn());
                        zzc(t8, i10);
                        continue;
                    case 7:
                        zztx.zza(t8, iZzbr & 1048575, zzsyVar.zzno());
                        zzc(t8, i10);
                        continue;
                    case 8:
                        zza(t8, iZzbr, zzsyVar);
                        zzc(t8, i10);
                        continue;
                    case 9:
                        if (zzb((zzso<T>) t8, i10)) {
                            long j9 = iZzbr & 1048575;
                            zztx.zza(t8, j9, zzre.zzb(zztx.zzp(t8, j9), zzsyVar.zza(zzbo(i10), zzqpVar)));
                            break;
                        } else {
                            zztx.zza(t8, iZzbr & 1048575, zzsyVar.zza(zzbo(i10), zzqpVar));
                            zzc(t8, i10);
                            continue;
                        }
                    case 10:
                        zztx.zza(t8, iZzbr & 1048575, zzsyVar.zznq());
                        zzc(t8, i10);
                        continue;
                    case 11:
                        zztx.zzb(t8, iZzbr & 1048575, zzsyVar.zznr());
                        zzc(t8, i10);
                        continue;
                    case 12:
                        int iZzns = zzsyVar.zzns();
                        zzrh zzrhVarZzbq = zzbq(i10);
                        if (zzrhVarZzbq != null && !zzrhVarZzbq.zzb(iZzns)) {
                            objZza = zztb.zza(iZzog, iZzns, objZza, (zztr<UT, Object>) zztrVar);
                            break;
                        } else {
                            zztx.zzb(t8, iZzbr & 1048575, iZzns);
                            zzc(t8, i10);
                            continue;
                        }
                        break;
                    case 13:
                        zztx.zzb(t8, iZzbr & 1048575, zzsyVar.zznt());
                        zzc(t8, i10);
                        continue;
                    case 14:
                        zztx.zza((Object) t8, iZzbr & 1048575, zzsyVar.zznu());
                        zzc(t8, i10);
                        continue;
                    case 15:
                        zztx.zzb(t8, iZzbr & 1048575, zzsyVar.zznv());
                        zzc(t8, i10);
                        continue;
                    case 16:
                        zztx.zza((Object) t8, iZzbr & 1048575, zzsyVar.zznw());
                        zzc(t8, i10);
                        continue;
                    case 17:
                        if (zzb((zzso<T>) t8, i10)) {
                            long j10 = iZzbr & 1048575;
                            zztx.zza(t8, j10, zzre.zzb(zztx.zzp(t8, j10), zzsyVar.zzb(zzbo(i10), zzqpVar)));
                            break;
                        } else {
                            zztx.zza(t8, iZzbr & 1048575, zzsyVar.zzb(zzbo(i10), zzqpVar));
                            zzc(t8, i10);
                            continue;
                        }
                    case 18:
                        zzsyVar.zzg(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 19:
                        zzsyVar.zzh(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 20:
                        zzsyVar.zzj(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 21:
                        zzsyVar.zzi(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 22:
                        zzsyVar.zzk(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 23:
                        zzsyVar.zzl(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 24:
                        zzsyVar.zzm(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 25:
                        zzsyVar.zzn(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 26:
                        if (zzbt(iZzbr)) {
                            zzsyVar.zzo(this.zzbdl.zza(t8, iZzbr & 1048575));
                            break;
                        } else {
                            zzsyVar.readStringList(this.zzbdl.zza(t8, iZzbr & 1048575));
                            continue;
                        }
                    case 27:
                        zzsyVar.zza(this.zzbdl.zza(t8, iZzbr & 1048575), zzbo(i10), zzqpVar);
                        continue;
                    case 28:
                        zzsyVar.zzp(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 29:
                        zzsyVar.zzq(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 30:
                        List<Integer> listZza = this.zzbdl.zza(t8, iZzbr & 1048575);
                        zzsyVar.zzr(listZza);
                        objZza = zztb.zza(iZzog, listZza, zzbq(i10), objZza, zztrVar);
                        continue;
                    case 31:
                        zzsyVar.zzs(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 32:
                        zzsyVar.zzt(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 33:
                        zzsyVar.zzu(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 34:
                        zzsyVar.zzv(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 35:
                        zzsyVar.zzg(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 36:
                        zzsyVar.zzh(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 37:
                        zzsyVar.zzj(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 38:
                        zzsyVar.zzi(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 39:
                        zzsyVar.zzk(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 40:
                        zzsyVar.zzl(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 41:
                        zzsyVar.zzm(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 42:
                        zzsyVar.zzn(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 43:
                        zzsyVar.zzq(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 44:
                        List<Integer> listZza2 = this.zzbdl.zza(t8, iZzbr & 1048575);
                        zzsyVar.zzr(listZza2);
                        objZza = zztb.zza(iZzog, listZza2, zzbq(i10), objZza, zztrVar);
                        continue;
                    case 45:
                        zzsyVar.zzs(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 46:
                        zzsyVar.zzt(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 47:
                        zzsyVar.zzu(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 48:
                        zzsyVar.zzv(this.zzbdl.zza(t8, iZzbr & 1048575));
                        continue;
                    case 49:
                        zzsyVar.zzb(this.zzbdl.zza(t8, iZzbr & 1048575), zzbo(i10), zzqpVar);
                        continue;
                    case 50:
                        Object objZzbp = zzbp(i10);
                        long jZzbr = zzbr(i10) & 1048575;
                        Object objZzp = zztx.zzp(t8, jZzbr);
                        if (objZzp == null) {
                            objZzp = this.zzbdo.zzab(objZzbp);
                            zztx.zza(t8, jZzbr, objZzp);
                        } else if (this.zzbdo.zzz(objZzp)) {
                            Object objZzab = this.zzbdo.zzab(objZzbp);
                            this.zzbdo.zzc(objZzab, objZzp);
                            zztx.zza(t8, jZzbr, objZzab);
                            objZzp = objZzab;
                        }
                        zzsyVar.zza(this.zzbdo.zzx(objZzp), this.zzbdo.zzac(objZzbp), zzqpVar);
                        continue;
                    case 51:
                        zztx.zza(t8, iZzbr & 1048575, Double.valueOf(zzsyVar.readDouble()));
                        zzb((zzso<T>) t8, iZzog, i10);
                        continue;
                    case 52:
                        zztx.zza(t8, iZzbr & 1048575, Float.valueOf(zzsyVar.readFloat()));
                        zzb((zzso<T>) t8, iZzog, i10);
                        continue;
                    case 53:
                        zztx.zza(t8, iZzbr & 1048575, Long.valueOf(zzsyVar.zznk()));
                        zzb((zzso<T>) t8, iZzog, i10);
                        continue;
                    case 54:
                        zztx.zza(t8, iZzbr & 1048575, Long.valueOf(zzsyVar.zznj()));
                        zzb((zzso<T>) t8, iZzog, i10);
                        continue;
                    case 55:
                        zztx.zza(t8, iZzbr & 1048575, Integer.valueOf(zzsyVar.zznl()));
                        zzb((zzso<T>) t8, iZzog, i10);
                        continue;
                    case 56:
                        zztx.zza(t8, iZzbr & 1048575, Long.valueOf(zzsyVar.zznm()));
                        zzb((zzso<T>) t8, iZzog, i10);
                        continue;
                    case 57:
                        zztx.zza(t8, iZzbr & 1048575, Integer.valueOf(zzsyVar.zznn()));
                        zzb((zzso<T>) t8, iZzog, i10);
                        continue;
                    case 58:
                        zztx.zza(t8, iZzbr & 1048575, Boolean.valueOf(zzsyVar.zzno()));
                        zzb((zzso<T>) t8, iZzog, i10);
                        continue;
                    case 59:
                        zza(t8, iZzbr, zzsyVar);
                        zzb((zzso<T>) t8, iZzog, i10);
                        continue;
                    case 60:
                        if (zza((zzso<T>) t8, iZzog, i10)) {
                            long j11 = iZzbr & 1048575;
                            zztx.zza(t8, j11, zzre.zzb(zztx.zzp(t8, j11), zzsyVar.zza(zzbo(i10), zzqpVar)));
                        } else {
                            zztx.zza(t8, iZzbr & 1048575, zzsyVar.zza(zzbo(i10), zzqpVar));
                            zzc(t8, i10);
                        }
                        zzb((zzso<T>) t8, iZzog, i10);
                        continue;
                    case 61:
                        zztx.zza(t8, iZzbr & 1048575, zzsyVar.zznq());
                        zzb((zzso<T>) t8, iZzog, i10);
                        continue;
                    case 62:
                        zztx.zza(t8, iZzbr & 1048575, Integer.valueOf(zzsyVar.zznr()));
                        zzb((zzso<T>) t8, iZzog, i10);
                        continue;
                    case 63:
                        int iZzns2 = zzsyVar.zzns();
                        zzrh zzrhVarZzbq2 = zzbq(i10);
                        if (zzrhVarZzbq2 != null && !zzrhVarZzbq2.zzb(iZzns2)) {
                            objZza = zztb.zza(iZzog, iZzns2, objZza, (zztr<UT, Object>) zztrVar);
                            break;
                        } else {
                            zztx.zza(t8, iZzbr & 1048575, Integer.valueOf(iZzns2));
                            zzb((zzso<T>) t8, iZzog, i10);
                            continue;
                        }
                        break;
                    case 64:
                        zztx.zza(t8, iZzbr & 1048575, Integer.valueOf(zzsyVar.zznt()));
                        zzb((zzso<T>) t8, iZzog, i10);
                        continue;
                    case 65:
                        zztx.zza(t8, iZzbr & 1048575, Long.valueOf(zzsyVar.zznu()));
                        zzb((zzso<T>) t8, iZzog, i10);
                        continue;
                    case 66:
                        zztx.zza(t8, iZzbr & 1048575, Integer.valueOf(zzsyVar.zznv()));
                        zzb((zzso<T>) t8, iZzog, i10);
                        continue;
                    case 67:
                        zztx.zza(t8, iZzbr & 1048575, Long.valueOf(zzsyVar.zznw()));
                        zzb((zzso<T>) t8, iZzog, i10);
                        continue;
                    case 68:
                        zztx.zza(t8, iZzbr & 1048575, zzsyVar.zzb(zzbo(i10), zzqpVar));
                        zzb((zzso<T>) t8, iZzog, i10);
                        continue;
                    default:
                        if (objZza == null) {
                            try {
                                objZza = zztrVar.zzri();
                            } catch (zzrl unused) {
                                zztrVar.zza(zzsyVar);
                                if (objZza == null) {
                                    objZza = zztrVar.zzah(t8);
                                }
                                if (!zztrVar.zza((zztr) objZza, zzsyVar)) {
                                    for (int i15 = this.zzbdi; i15 < this.zzbdj; i15++) {
                                        objZza = zza((Object) t8, this.zzbdh[i15], (int) objZza, (zztr<UT, int>) zztrVar);
                                    }
                                    if (objZza != null) {
                                        zztrVar.zzg(t8, objZza);
                                        return;
                                    }
                                    return;
                                }
                                break;
                            }
                        }
                        if (!zztrVar.zza((zztr) objZza, zzsyVar)) {
                            for (int i16 = this.zzbdi; i16 < this.zzbdj; i16++) {
                                objZza = zza((Object) t8, this.zzbdh[i16], (int) objZza, (zztr<UT, int>) zztrVar);
                            }
                            if (objZza != null) {
                                zztrVar.zzg(t8, objZza);
                                return;
                            }
                            return;
                        }
                        break;
                }
                for (i9 = this.zzbdi; i9 < this.zzbdj; i9++) {
                    objZza = zza((Object) t8, this.zzbdh[i9], (int) objZza, (zztr<UT, int>) zztrVar);
                }
                if (objZza != null) {
                    zztrVar.zzg(t8, objZza);
                }
                throw th;
            }
            if (iZzog == Integer.MAX_VALUE) {
                for (int i17 = this.zzbdi; i17 < this.zzbdj; i17++) {
                    objZza = zza((Object) t8, this.zzbdh[i17], (int) objZza, (zztr<UT, int>) zztrVar);
                }
                if (objZza != null) {
                    zztrVar.zzg(t8, objZza);
                    return;
                }
                return;
            }
            Object objZza2 = !this.zzbdd ? null : zzqqVar.zza(zzqpVar, this.zzbdc, iZzog);
            if (objZza2 != null) {
                if (objZzs == null) {
                    objZzs = zzqqVar.zzs(t8);
                }
                zzqt<T> zzqtVar = objZzs;
                objZza = zzqqVar.zza(zzsyVar, objZza2, zzqpVar, zzqtVar, objZza, zztrVar);
                objZzs = zzqtVar;
            } else {
                zztrVar.zza(zzsyVar);
                if (objZza == null) {
                    objZza = zztrVar.zzah(t8);
                }
                if (!zztrVar.zza((zztr) objZza, zzsyVar)) {
                    for (int i18 = this.zzbdi; i18 < this.zzbdj; i18++) {
                        objZza = zza((Object) t8, this.zzbdh[i18], (int) objZza, (zztr<UT, int>) zztrVar);
                    }
                    if (objZza != null) {
                        zztrVar.zzg(t8, objZza);
                        return;
                    }
                    return;
                }
            }
        }
    }

    private final <UT, UB> UB zza(Object obj, int i9, UB ub, zztr<UT, UB> zztrVar) {
        zzrh zzrhVarZzbq;
        int i10 = this.zzbcy[i9];
        Object objZzp = zztx.zzp(obj, zzbr(i9) & 1048575);
        return (objZzp == null || (zzrhVarZzbq = zzbq(i9)) == null) ? ub : (UB) zza(i9, i10, this.zzbdo.zzx(objZzp), zzrhVarZzbq, ub, zztrVar);
    }

    private final <K, V, UT, UB> UB zza(int i9, int i10, Map<K, V> map, zzrh zzrhVar, UB ub, zztr<UT, UB> zztrVar) {
        zzsd<?, ?> zzsdVarZzac = this.zzbdo.zzac(zzbp(i9));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!zzrhVar.zzb(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zztrVar.zzri();
                }
                zzqa zzqaVarZzam = zzps.zzam(zzsc.zza(zzsdVarZzac, next.getKey(), next.getValue()));
                try {
                    zzsc.zza(zzqaVarZzam.zznh(), zzsdVarZzac, next.getKey(), next.getValue());
                    zztrVar.zza((zztr<UT, UB>) ub, i10, zzqaVarZzam.zzng());
                    it.remove();
                } catch (IOException e9) {
                    throw new RuntimeException(e9);
                }
            }
        }
        return ub;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object obj, int i9, zzsz zzszVar) {
        return zzszVar.zzae(zztx.zzp(obj, i9 & 1048575));
    }

    private static void zza(int i9, Object obj, zzum zzumVar) {
        if (obj instanceof String) {
            zzumVar.zza(i9, (String) obj);
        } else {
            zzumVar.zza(i9, (zzps) obj);
        }
    }

    private final void zza(Object obj, int i9, zzsy zzsyVar) {
        if (zzbt(i9)) {
            zztx.zza(obj, i9 & 1048575, zzsyVar.zznp());
        } else if (this.zzbde) {
            zztx.zza(obj, i9 & 1048575, zzsyVar.readString());
        } else {
            zztx.zza(obj, i9 & 1048575, zzsyVar.zznq());
        }
    }

    private final boolean zza(T t8, int i9, int i10, int i11) {
        if (this.zzbdf) {
            return zzb((zzso<T>) t8, i9);
        }
        return (i10 & i11) != 0;
    }

    private final boolean zza(T t8, int i9, int i10) {
        return zztx.zzk(t8, (long) (zzbs(i10) & 1048575)) == i9;
    }
}
