package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhv;
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
final class zzjk<T> implements zzjv<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzkt.zzc();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzjg zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final boolean zzk;
    private final int[] zzl;
    private final int zzm;
    private final int zzn;
    private final zzjo zzo;
    private final zziq zzp;
    private final zzkn<?, ?> zzq;
    private final zzhk<?> zzr;
    private final zziz zzs;

    private zzjk(int[] iArr, Object[] objArr, int i9, int i10, zzjg zzjgVar, boolean z8, boolean z9, int[] iArr2, int i11, int i12, zzjo zzjoVar, zziq zziqVar, zzkn<?, ?> zzknVar, zzhk<?> zzhkVar, zziz zzizVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i9;
        this.zzf = i10;
        this.zzi = zzjgVar instanceof zzhv;
        this.zzj = z8;
        this.zzh = zzhkVar != null && zzhkVar.zza(zzjgVar);
        this.zzk = false;
        this.zzl = iArr2;
        this.zzm = i11;
        this.zzn = i12;
        this.zzo = zzjoVar;
        this.zzp = zziqVar;
        this.zzq = zzknVar;
        this.zzr = zzhkVar;
        this.zzg = zzjgVar;
        this.zzs = zzizVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:163:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0388  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static <T> zzjk<T> zza(Class<T> cls, zzje zzjeVar, zzjo zzjoVar, zziq zziqVar, zzkn<?, ?> zzknVar, zzhk<?> zzhkVar, zziz zzizVar) {
        int i9;
        int iCharAt;
        int iCharAt2;
        int iCharAt3;
        int iCharAt4;
        int iCharAt5;
        int i10;
        int[] iArr;
        int i11;
        char cCharAt;
        int i12;
        char cCharAt2;
        int i13;
        char cCharAt3;
        int i14;
        char cCharAt4;
        int i15;
        char cCharAt5;
        int i16;
        char cCharAt6;
        int i17;
        char cCharAt7;
        int i18;
        char cCharAt8;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int iObjectFieldOffset;
        String str;
        boolean z8;
        boolean z9;
        int iObjectFieldOffset2;
        int i24;
        int i25;
        Field fieldZza;
        int i26;
        char cCharAt9;
        int i27;
        int i28;
        Field fieldZza2;
        Field fieldZza3;
        int i29;
        char cCharAt10;
        int i30;
        char cCharAt11;
        int i31;
        char cCharAt12;
        int i32;
        char cCharAt13;
        if (!(zzjeVar instanceof zzjt)) {
            ((zzkk) zzjeVar).zza();
            int i33 = zzhv.zze.zza;
            throw new NoSuchMethodError();
        }
        zzjt zzjtVar = (zzjt) zzjeVar;
        int i34 = 0;
        boolean z10 = zzjtVar.zza() == zzhv.zze.zzi;
        String strZzd = zzjtVar.zzd();
        int length = strZzd.length();
        if (strZzd.charAt(0) >= 55296) {
            int i35 = 1;
            while (true) {
                i9 = i35 + 1;
                if (strZzd.charAt(i35) < 55296) {
                    break;
                }
                i35 = i9;
            }
        } else {
            i9 = 1;
        }
        int i36 = i9 + 1;
        int iCharAt6 = strZzd.charAt(i9);
        if (iCharAt6 >= 55296) {
            int i37 = iCharAt6 & 8191;
            int i38 = 13;
            while (true) {
                i32 = i36 + 1;
                cCharAt13 = strZzd.charAt(i36);
                if (cCharAt13 < 55296) {
                    break;
                }
                i37 |= (cCharAt13 & 8191) << i38;
                i38 += 13;
                i36 = i32;
            }
            iCharAt6 = i37 | (cCharAt13 << i38);
            i36 = i32;
        }
        if (iCharAt6 == 0) {
            iCharAt = 0;
            iCharAt2 = 0;
            iCharAt3 = 0;
            iCharAt4 = 0;
            iCharAt5 = 0;
            iArr = zza;
            i10 = 0;
        } else {
            int i39 = i36 + 1;
            int iCharAt7 = strZzd.charAt(i36);
            if (iCharAt7 >= 55296) {
                int i40 = iCharAt7 & 8191;
                int i41 = 13;
                while (true) {
                    i18 = i39 + 1;
                    cCharAt8 = strZzd.charAt(i39);
                    if (cCharAt8 < 55296) {
                        break;
                    }
                    i40 |= (cCharAt8 & 8191) << i41;
                    i41 += 13;
                    i39 = i18;
                }
                iCharAt7 = i40 | (cCharAt8 << i41);
                i39 = i18;
            }
            int i42 = i39 + 1;
            int iCharAt8 = strZzd.charAt(i39);
            if (iCharAt8 >= 55296) {
                int i43 = iCharAt8 & 8191;
                int i44 = 13;
                while (true) {
                    i17 = i42 + 1;
                    cCharAt7 = strZzd.charAt(i42);
                    if (cCharAt7 < 55296) {
                        break;
                    }
                    i43 |= (cCharAt7 & 8191) << i44;
                    i44 += 13;
                    i42 = i17;
                }
                iCharAt8 = i43 | (cCharAt7 << i44);
                i42 = i17;
            }
            int i45 = i42 + 1;
            iCharAt = strZzd.charAt(i42);
            if (iCharAt >= 55296) {
                int i46 = iCharAt & 8191;
                int i47 = 13;
                while (true) {
                    i16 = i45 + 1;
                    cCharAt6 = strZzd.charAt(i45);
                    if (cCharAt6 < 55296) {
                        break;
                    }
                    i46 |= (cCharAt6 & 8191) << i47;
                    i47 += 13;
                    i45 = i16;
                }
                iCharAt = i46 | (cCharAt6 << i47);
                i45 = i16;
            }
            int i48 = i45 + 1;
            iCharAt2 = strZzd.charAt(i45);
            if (iCharAt2 >= 55296) {
                int i49 = iCharAt2 & 8191;
                int i50 = 13;
                while (true) {
                    i15 = i48 + 1;
                    cCharAt5 = strZzd.charAt(i48);
                    if (cCharAt5 < 55296) {
                        break;
                    }
                    i49 |= (cCharAt5 & 8191) << i50;
                    i50 += 13;
                    i48 = i15;
                }
                iCharAt2 = i49 | (cCharAt5 << i50);
                i48 = i15;
            }
            int i51 = i48 + 1;
            iCharAt3 = strZzd.charAt(i48);
            if (iCharAt3 >= 55296) {
                int i52 = iCharAt3 & 8191;
                int i53 = 13;
                while (true) {
                    i14 = i51 + 1;
                    cCharAt4 = strZzd.charAt(i51);
                    if (cCharAt4 < 55296) {
                        break;
                    }
                    i52 |= (cCharAt4 & 8191) << i53;
                    i53 += 13;
                    i51 = i14;
                }
                iCharAt3 = i52 | (cCharAt4 << i53);
                i51 = i14;
            }
            int i54 = i51 + 1;
            iCharAt4 = strZzd.charAt(i51);
            if (iCharAt4 >= 55296) {
                int i55 = iCharAt4 & 8191;
                int i56 = 13;
                while (true) {
                    i13 = i54 + 1;
                    cCharAt3 = strZzd.charAt(i54);
                    if (cCharAt3 < 55296) {
                        break;
                    }
                    i55 |= (cCharAt3 & 8191) << i56;
                    i56 += 13;
                    i54 = i13;
                }
                iCharAt4 = i55 | (cCharAt3 << i56);
                i54 = i13;
            }
            int i57 = i54 + 1;
            int iCharAt9 = strZzd.charAt(i54);
            if (iCharAt9 >= 55296) {
                int i58 = iCharAt9 & 8191;
                int i59 = 13;
                while (true) {
                    i12 = i57 + 1;
                    cCharAt2 = strZzd.charAt(i57);
                    if (cCharAt2 < 55296) {
                        break;
                    }
                    i58 |= (cCharAt2 & 8191) << i59;
                    i59 += 13;
                    i57 = i12;
                }
                iCharAt9 = i58 | (cCharAt2 << i59);
                i57 = i12;
            }
            int i60 = i57 + 1;
            iCharAt5 = strZzd.charAt(i57);
            if (iCharAt5 >= 55296) {
                int i61 = iCharAt5 & 8191;
                int i62 = i60;
                int i63 = 13;
                while (true) {
                    i11 = i62 + 1;
                    cCharAt = strZzd.charAt(i62);
                    if (cCharAt < 55296) {
                        break;
                    }
                    i61 |= (cCharAt & 8191) << i63;
                    i63 += 13;
                    i62 = i11;
                }
                iCharAt5 = i61 | (cCharAt << i63);
                i60 = i11;
            }
            i10 = (iCharAt7 << 1) + iCharAt8;
            iArr = new int[iCharAt5 + iCharAt4 + iCharAt9];
            i34 = iCharAt7;
            i36 = i60;
        }
        Unsafe unsafe = zzb;
        Object[] objArrZze = zzjtVar.zze();
        Class<?> cls2 = zzjtVar.zzc().getClass();
        int i64 = i36;
        int[] iArr2 = new int[iCharAt3 * 3];
        Object[] objArr = new Object[iCharAt3 << 1];
        int i65 = iCharAt5 + iCharAt4;
        int i66 = i10;
        int i67 = iCharAt5;
        int i68 = i64;
        int i69 = i65;
        int i70 = 0;
        int i71 = 0;
        while (i68 < length) {
            int i72 = i68 + 1;
            int iCharAt10 = strZzd.charAt(i68);
            if (iCharAt10 >= 55296) {
                int i73 = iCharAt10 & 8191;
                int i74 = i72;
                int i75 = 13;
                while (true) {
                    i31 = i74 + 1;
                    cCharAt12 = strZzd.charAt(i74);
                    i19 = length;
                    if (cCharAt12 < 55296) {
                        break;
                    }
                    i73 |= (cCharAt12 & 8191) << i75;
                    i75 += 13;
                    i74 = i31;
                    length = i19;
                }
                iCharAt10 = i73 | (cCharAt12 << i75);
                i20 = i31;
            } else {
                i19 = length;
                i20 = i72;
            }
            int i76 = i20 + 1;
            int iCharAt11 = strZzd.charAt(i20);
            if (iCharAt11 >= 55296) {
                int i77 = iCharAt11 & 8191;
                int i78 = i76;
                int i79 = 13;
                while (true) {
                    i30 = i78 + 1;
                    cCharAt11 = strZzd.charAt(i78);
                    i21 = iCharAt5;
                    if (cCharAt11 < 55296) {
                        break;
                    }
                    i77 |= (cCharAt11 & 8191) << i79;
                    i79 += 13;
                    i78 = i30;
                    iCharAt5 = i21;
                }
                iCharAt11 = i77 | (cCharAt11 << i79);
                i22 = i30;
            } else {
                i21 = iCharAt5;
                i22 = i76;
            }
            int i80 = iCharAt11 & 255;
            int i81 = iCharAt2;
            if ((iCharAt11 & UserMetadata.MAX_ATTRIBUTE_SIZE) != 0) {
                iArr[i70] = i71;
                i70++;
            }
            int i82 = iCharAt;
            if (i80 >= 51) {
                int i83 = i22 + 1;
                int iCharAt12 = strZzd.charAt(i22);
                char c9 = 55296;
                if (iCharAt12 >= 55296) {
                    int i84 = iCharAt12 & 8191;
                    int i85 = 13;
                    while (true) {
                        i29 = i83 + 1;
                        cCharAt10 = strZzd.charAt(i83);
                        if (cCharAt10 < c9) {
                            break;
                        }
                        i84 |= (cCharAt10 & 8191) << i85;
                        i85 += 13;
                        i83 = i29;
                        c9 = 55296;
                    }
                    iCharAt12 = i84 | (cCharAt10 << i85);
                    i83 = i29;
                }
                int i86 = i80 - 51;
                int i87 = i83;
                if (i86 == 9 || i86 == 17) {
                    i28 = 1;
                    objArr[((i71 / 3) << 1) + 1] = objArrZze[i66];
                    i66++;
                } else {
                    if (i86 == 12 && !z10) {
                        objArr[((i71 / 3) << 1) + 1] = objArrZze[i66];
                        i66++;
                    }
                    i28 = 1;
                }
                int i88 = iCharAt12 << i28;
                Object obj = objArrZze[i88];
                if (obj instanceof Field) {
                    fieldZza2 = (Field) obj;
                } else {
                    fieldZza2 = zza(cls2, (String) obj);
                    objArrZze[i88] = fieldZza2;
                }
                int iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldZza2);
                int i89 = i88 + 1;
                Object obj2 = objArrZze[i89];
                if (obj2 instanceof Field) {
                    fieldZza3 = (Field) obj2;
                } else {
                    fieldZza3 = zza(cls2, (String) obj2);
                    objArrZze[i89] = fieldZza3;
                }
                str = strZzd;
                iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZza3);
                z8 = z10;
                iObjectFieldOffset = iObjectFieldOffset3;
                i24 = i87;
                i25 = 0;
                z9 = true;
            } else {
                int i90 = i66 + 1;
                Field fieldZza4 = zza(cls2, (String) objArrZze[i66]);
                if (i80 == 9 || i80 == 17) {
                    objArr[((i71 / 3) << 1) + 1] = fieldZza4.getType();
                } else {
                    if (i80 == 27 || i80 == 49) {
                        i27 = i90 + 1;
                        objArr[((i71 / 3) << 1) + 1] = objArrZze[i90];
                    } else {
                        if (i80 == 12 || i80 == 30 || i80 == 44) {
                            if (!z10) {
                                i27 = i90 + 1;
                                objArr[((i71 / 3) << 1) + 1] = objArrZze[i90];
                            }
                        } else if (i80 == 50) {
                            int i91 = i67 + 1;
                            iArr[i67] = i71;
                            int i92 = (i71 / 3) << 1;
                            i27 = i90 + 1;
                            objArr[i92] = objArrZze[i90];
                            if ((iCharAt11 & 2048) != 0) {
                                i90 = i27 + 1;
                                objArr[i92 + 1] = objArrZze[i27];
                                i67 = i91;
                            } else {
                                i67 = i91;
                            }
                        }
                        iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZza4);
                        int i93 = i23;
                        if ((iCharAt11 & 4096) != 4096 || i80 > 17) {
                            str = strZzd;
                            z8 = z10;
                            z9 = true;
                            iObjectFieldOffset2 = 1048575;
                            i24 = i22;
                            i25 = 0;
                        } else {
                            int i94 = i22 + 1;
                            int iCharAt13 = strZzd.charAt(i22);
                            if (iCharAt13 >= 55296) {
                                int i95 = iCharAt13 & 8191;
                                int i96 = 13;
                                while (true) {
                                    i26 = i94 + 1;
                                    cCharAt9 = strZzd.charAt(i94);
                                    if (cCharAt9 < 55296) {
                                        break;
                                    }
                                    i95 |= (cCharAt9 & 8191) << i96;
                                    i96 += 13;
                                    i94 = i26;
                                }
                                iCharAt13 = i95 | (cCharAt9 << i96);
                                i94 = i26;
                            }
                            z9 = true;
                            int i97 = (i34 << 1) + (iCharAt13 / 32);
                            Object obj3 = objArrZze[i97];
                            str = strZzd;
                            if (obj3 instanceof Field) {
                                fieldZza = (Field) obj3;
                            } else {
                                fieldZza = zza(cls2, (String) obj3);
                                objArrZze[i97] = fieldZza;
                            }
                            i24 = i94;
                            z8 = z10;
                            iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZza);
                            i25 = iCharAt13 % 32;
                        }
                        if (i80 >= 18 && i80 <= 49) {
                            iArr[i69] = iObjectFieldOffset;
                            i69++;
                        }
                        i66 = i93;
                    }
                    i23 = i27;
                    iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZza4);
                    int i932 = i23;
                    if ((iCharAt11 & 4096) != 4096) {
                        str = strZzd;
                        z8 = z10;
                        z9 = true;
                        iObjectFieldOffset2 = 1048575;
                        i24 = i22;
                        i25 = 0;
                        if (i80 >= 18) {
                            iArr[i69] = iObjectFieldOffset;
                            i69++;
                        }
                        i66 = i932;
                    }
                }
                i23 = i90;
                iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZza4);
                int i9322 = i23;
                if ((iCharAt11 & 4096) != 4096) {
                }
            }
            int i98 = i71 + 1;
            iArr2[i71] = iCharAt10;
            int i99 = i98 + 1;
            int i100 = i34;
            iArr2[i98] = ((iCharAt11 & 256) != 0 ? SQLiteDatabase.CREATE_IF_NECESSARY : 0) | ((iCharAt11 & 512) != 0 ? 536870912 : 0) | (i80 << 20) | iObjectFieldOffset;
            int i101 = i99 + 1;
            iArr2[i99] = (i25 << 20) | iObjectFieldOffset2;
            z10 = z8;
            i34 = i100;
            iCharAt2 = i81;
            i68 = i24;
            iCharAt5 = i21;
            iCharAt = i82;
            strZzd = str;
            i71 = i101;
            length = i19;
        }
        return new zzjk<>(iArr2, objArr, iCharAt, iCharAt2, zzjtVar.zzc(), z10, false, iArr, iCharAt5, i65, zzjoVar, zziqVar, zzknVar, zzhkVar, zzizVar);
    }

    private final zzic zzc(int i9) {
        return (zzic) this.zzd[((i9 / 3) << 1) + 1];
    }

    private static zzkq zze(Object obj) {
        zzhv zzhvVar = (zzhv) obj;
        zzkq zzkqVar = zzhvVar.zzb;
        if (zzkqVar != zzkq.zza()) {
            return zzkqVar;
        }
        zzkq zzkqVarZzb = zzkq.zzb();
        zzhvVar.zzb = zzkqVarZzb;
        return zzkqVarZzb;
    }

    private static boolean zzf(int i9) {
        return (i9 & 536870912) != 0;
    }

    private static <T> boolean zzf(T t8, long j9) {
        return ((Boolean) zzkt.zzf(t8, j9)).booleanValue();
    }

    private final int zzg(int i9) {
        if (i9 < this.zze || i9 > this.zzf) {
            return -1;
        }
        return zzb(i9, 0);
    }

    @Override // com.google.android.gms.internal.measurement.zzjv
    public final void zzb(T t8, T t9) {
        t9.getClass();
        for (int i9 = 0; i9 < this.zzc.length; i9 += 3) {
            int iZzd = zzd(i9);
            long j9 = 1048575 & iZzd;
            int i10 = this.zzc[i9];
            switch ((iZzd & 267386880) >>> 20) {
                case 0:
                    if (zza((zzjk<T>) t9, i9)) {
                        zzkt.zza(t8, j9, zzkt.zze(t9, j9));
                        zzb((zzjk<T>) t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zza((zzjk<T>) t9, i9)) {
                        zzkt.zza((Object) t8, j9, zzkt.zzd(t9, j9));
                        zzb((zzjk<T>) t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zza((zzjk<T>) t9, i9)) {
                        zzkt.zza((Object) t8, j9, zzkt.zzb(t9, j9));
                        zzb((zzjk<T>) t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zza((zzjk<T>) t9, i9)) {
                        zzkt.zza((Object) t8, j9, zzkt.zzb(t9, j9));
                        zzb((zzjk<T>) t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zza((zzjk<T>) t9, i9)) {
                        zzkt.zza((Object) t8, j9, zzkt.zza(t9, j9));
                        zzb((zzjk<T>) t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zza((zzjk<T>) t9, i9)) {
                        zzkt.zza((Object) t8, j9, zzkt.zzb(t9, j9));
                        zzb((zzjk<T>) t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zza((zzjk<T>) t9, i9)) {
                        zzkt.zza((Object) t8, j9, zzkt.zza(t9, j9));
                        zzb((zzjk<T>) t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zza((zzjk<T>) t9, i9)) {
                        zzkt.zza(t8, j9, zzkt.zzc(t9, j9));
                        zzb((zzjk<T>) t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zza((zzjk<T>) t9, i9)) {
                        zzkt.zza(t8, j9, zzkt.zzf(t9, j9));
                        zzb((zzjk<T>) t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zza(t8, t9, i9);
                    break;
                case 10:
                    if (zza((zzjk<T>) t9, i9)) {
                        zzkt.zza(t8, j9, zzkt.zzf(t9, j9));
                        zzb((zzjk<T>) t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zza((zzjk<T>) t9, i9)) {
                        zzkt.zza((Object) t8, j9, zzkt.zza(t9, j9));
                        zzb((zzjk<T>) t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zza((zzjk<T>) t9, i9)) {
                        zzkt.zza((Object) t8, j9, zzkt.zza(t9, j9));
                        zzb((zzjk<T>) t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zza((zzjk<T>) t9, i9)) {
                        zzkt.zza((Object) t8, j9, zzkt.zza(t9, j9));
                        zzb((zzjk<T>) t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zza((zzjk<T>) t9, i9)) {
                        zzkt.zza((Object) t8, j9, zzkt.zzb(t9, j9));
                        zzb((zzjk<T>) t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zza((zzjk<T>) t9, i9)) {
                        zzkt.zza((Object) t8, j9, zzkt.zza(t9, j9));
                        zzb((zzjk<T>) t8, i9);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zza((zzjk<T>) t9, i9)) {
                        zzkt.zza((Object) t8, j9, zzkt.zzb(t9, j9));
                        zzb((zzjk<T>) t8, i9);
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
                    this.zzp.zza(t8, t9, j9);
                    break;
                case 50:
                    zzjx.zza(this.zzs, t8, t9, j9);
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
                    if (zza((zzjk<T>) t9, i10, i9)) {
                        zzkt.zza(t8, j9, zzkt.zzf(t9, j9));
                        zzb((zzjk<T>) t8, i10, i9);
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
                    if (zza((zzjk<T>) t9, i10, i9)) {
                        zzkt.zza(t8, j9, zzkt.zzf(t9, j9));
                        zzb((zzjk<T>) t8, i10, i9);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzb(t8, t9, i9);
                    break;
            }
        }
        zzjx.zza(this.zzq, t8, t9);
        if (this.zzh) {
            zzjx.zza(this.zzr, t8, t9);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00dd  */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23, types: [com.google.android.gms.internal.measurement.zzjv] */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.google.android.gms.internal.measurement.zzjv] */
    @Override // com.google.android.gms.internal.measurement.zzjv
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean zzd(T t8) {
        int i9;
        int i10;
        int i11 = 1048575;
        int i12 = 0;
        int i13 = 0;
        while (true) {
            boolean z8 = true;
            if (i13 >= this.zzm) {
                return !this.zzh || this.zzr.zza(t8).zzf();
            }
            int i14 = this.zzl[i13];
            int i15 = this.zzc[i14];
            int iZzd = zzd(i14);
            int i16 = this.zzc[i14 + 2];
            int i17 = i16 & 1048575;
            int i18 = 1 << (i16 >>> 20);
            if (i17 != i11) {
                if (i17 != 1048575) {
                    i12 = zzb.getInt(t8, i17);
                }
                i10 = i12;
                i9 = i17;
            } else {
                i9 = i11;
                i10 = i12;
            }
            if (((268435456 & iZzd) != 0) && !zza((zzjk<T>) t8, i14, i9, i10, i18)) {
                return false;
            }
            int i19 = (267386880 & iZzd) >>> 20;
            if (i19 == 9 || i19 == 17) {
                if (zza((zzjk<T>) t8, i14, i9, i10, i18) && !zza(t8, iZzd, zza(i14))) {
                    return false;
                }
            } else if (i19 == 27) {
                List list = (List) zzkt.zzf(t8, iZzd & 1048575);
                if (!list.isEmpty()) {
                    ?? Zza = zza(i14);
                    int i20 = 0;
                    while (true) {
                        if (i20 >= list.size()) {
                            break;
                        }
                        if (!Zza.zzd(list.get(i20))) {
                            z8 = false;
                            break;
                        }
                        i20++;
                    }
                }
                if (!z8) {
                    return false;
                }
            } else if (i19 == 60 || i19 == 68) {
                if (zza((zzjk<T>) t8, i15, i14) && !zza(t8, iZzd, zza(i14))) {
                    return false;
                }
            } else if (i19 != 49) {
                if (i19 != 50) {
                    continue;
                } else {
                    Map<?, ?> mapZzb = this.zzs.zzb(zzkt.zzf(t8, iZzd & 1048575));
                    if (!mapZzb.isEmpty()) {
                        if (this.zzs.zzf(zzb(i14)).zzc.zza() == zzlh.MESSAGE) {
                            Iterator<?> it = mapZzb.values().iterator();
                            ?? Zza2 = 0;
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                Object next = it.next();
                                Zza2 = Zza2;
                                if (Zza2 == 0) {
                                    Zza2 = zzjr.zza().zza((Class) next.getClass());
                                }
                                if (!Zza2.zzd(next)) {
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
            i13++;
            i11 = i9;
            i12 = i10;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzjv
    public final void zzc(T t8) {
        int i9;
        int i10 = this.zzm;
        while (true) {
            i9 = this.zzn;
            if (i10 >= i9) {
                break;
            }
            long jZzd = zzd(this.zzl[i10]) & 1048575;
            Object objZzf = zzkt.zzf(t8, jZzd);
            if (objZzf != null) {
                zzkt.zza(t8, jZzd, this.zzs.zzd(objZzf));
            }
            i10++;
        }
        int length = this.zzl.length;
        while (i9 < length) {
            this.zzp.zzb(t8, this.zzl[i9]);
            i9++;
        }
        this.zzq.zzd(t8);
        if (this.zzh) {
            this.zzr.zzc(t8);
        }
    }

    private final int zze(int i9) {
        return this.zzc[i9 + 2];
    }

    private static <T> long zze(T t8, long j9) {
        return ((Long) zzkt.zzf(t8, j9)).longValue();
    }

    private static <T> float zzc(T t8, long j9) {
        return ((Float) zzkt.zzf(t8, j9)).floatValue();
    }

    private final boolean zzc(T t8, T t9, int i9) {
        return zza((zzjk<T>) t8, i9) == zza((zzjk<T>) t9, i9);
    }

    private final int zzd(int i9) {
        return this.zzc[i9 + 1];
    }

    private static <T> int zzd(T t8, long j9) {
        return ((Integer) zzkt.zzf(t8, j9)).intValue();
    }

    private final void zzb(T t8, T t9, int i9) {
        int iZzd = zzd(i9);
        int i10 = this.zzc[i9];
        long j9 = iZzd & 1048575;
        if (zza((zzjk<T>) t9, i10, i9)) {
            Object objZzf = zzkt.zzf(t8, j9);
            Object objZzf2 = zzkt.zzf(t9, j9);
            if (objZzf != null && objZzf2 != null) {
                zzkt.zza(t8, j9, zzhx.zza(objZzf, objZzf2));
                zzb((zzjk<T>) t8, i10, i9);
            } else if (objZzf2 != null) {
                zzkt.zza(t8, j9, objZzf2);
                zzb((zzjk<T>) t8, i10, i9);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:328:0x0809 A[PHI: r5
      0x0809: PHI (r5v4 int) = 
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v16 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v1 int)
      (r5v17 int)
      (r5v1 int)
     binds: [B:204:0x04b2, B:366:0x08ad, B:360:0x0891, B:357:0x087f, B:354:0x0870, B:351:0x0863, B:348:0x0856, B:344:0x084b, B:341:0x0840, B:338:0x0833, B:335:0x0826, B:332:0x0813, B:305:0x0722, B:302:0x070d, B:299:0x06f8, B:296:0x06e3, B:293:0x06ce, B:290:0x06b9, B:287:0x06a3, B:284:0x068d, B:281:0x0677, B:278:0x0661, B:275:0x064b, B:272:0x0635, B:269:0x061f, B:266:0x0609, B:261:0x05d5, B:258:0x05c8, B:255:0x05b8, B:252:0x05a8, B:249:0x0598, B:246:0x058a, B:243:0x057d, B:240:0x0570, B:234:0x0552, B:231:0x053e, B:228:0x052c, B:225:0x051c, B:222:0x050c, B:346:0x0852, B:219:0x04ff, B:216:0x04f1, B:213:0x04e1, B:210:0x04d1, B:327:0x0808, B:207:0x04bb] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.google.android.gms.internal.measurement.zzjv
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int zzb(T t8) {
        int i9;
        int i10;
        int i11;
        long j9;
        int iZzd;
        int iZzb;
        int iZzj;
        int iZzh;
        int iZzi;
        int iZze;
        int iZzg;
        int iZzb2;
        int iZzi2;
        int iZze2;
        int iZzg2;
        int i12 = 267386880;
        int i13 = 1048575;
        int i14 = 1;
        int i15 = 0;
        if (this.zzj) {
            Unsafe unsafe = zzb;
            int i16 = 0;
            int i17 = 0;
            while (i16 < this.zzc.length) {
                int iZzd2 = zzd(i16);
                int i18 = (iZzd2 & i12) >>> 20;
                int i19 = this.zzc[i16];
                long j10 = iZzd2 & 1048575;
                if (i18 >= zzhp.zza.zza() && i18 <= zzhp.zzb.zza()) {
                    int i20 = this.zzc[i16 + 2];
                }
                switch (i18) {
                    case 0:
                        if (zza((zzjk<T>) t8, i16)) {
                            iZzb2 = zzhf.zzb(i19, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zza((zzjk<T>) t8, i16)) {
                            iZzb2 = zzhf.zzb(i19, BitmapDescriptorFactory.HUE_RED);
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zza((zzjk<T>) t8, i16)) {
                            iZzb2 = zzhf.zzd(i19, zzkt.zzb(t8, j10));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zza((zzjk<T>) t8, i16)) {
                            iZzb2 = zzhf.zze(i19, zzkt.zzb(t8, j10));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zza((zzjk<T>) t8, i16)) {
                            iZzb2 = zzhf.zzf(i19, zzkt.zza(t8, j10));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zza((zzjk<T>) t8, i16)) {
                            iZzb2 = zzhf.zzg(i19, 0L);
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zza((zzjk<T>) t8, i16)) {
                            iZzb2 = zzhf.zzi(i19, 0);
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zza((zzjk<T>) t8, i16)) {
                            iZzb2 = zzhf.zzb(i19, true);
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zza((zzjk<T>) t8, i16)) {
                            Object objZzf = zzkt.zzf(t8, j10);
                            if (objZzf instanceof zzgm) {
                                iZzb2 = zzhf.zzc(i19, (zzgm) objZzf);
                            } else {
                                iZzb2 = zzhf.zzb(i19, (String) objZzf);
                            }
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        if (zza((zzjk<T>) t8, i16)) {
                            iZzb2 = zzjx.zza(i19, zzkt.zzf(t8, j10), zza(i16));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zza((zzjk<T>) t8, i16)) {
                            iZzb2 = zzhf.zzc(i19, (zzgm) zzkt.zzf(t8, j10));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zza((zzjk<T>) t8, i16)) {
                            iZzb2 = zzhf.zzg(i19, zzkt.zza(t8, j10));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zza((zzjk<T>) t8, i16)) {
                            iZzb2 = zzhf.zzk(i19, zzkt.zza(t8, j10));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zza((zzjk<T>) t8, i16)) {
                            iZzb2 = zzhf.zzj(i19, 0);
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zza((zzjk<T>) t8, i16)) {
                            iZzb2 = zzhf.zzh(i19, 0L);
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zza((zzjk<T>) t8, i16)) {
                            iZzb2 = zzhf.zzh(i19, zzkt.zza(t8, j10));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zza((zzjk<T>) t8, i16)) {
                            iZzb2 = zzhf.zzf(i19, zzkt.zzb(t8, j10));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zza((zzjk<T>) t8, i16)) {
                            iZzb2 = zzhf.zzc(i19, (zzjg) zzkt.zzf(t8, j10), zza(i16));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        iZzb2 = zzjx.zzi(i19, zza(t8, j10), false);
                        i17 += iZzb2;
                        break;
                    case 19:
                        iZzb2 = zzjx.zzh(i19, zza(t8, j10), false);
                        i17 += iZzb2;
                        break;
                    case 20:
                        iZzb2 = zzjx.zza(i19, (List<Long>) zza(t8, j10), false);
                        i17 += iZzb2;
                        break;
                    case 21:
                        iZzb2 = zzjx.zzb(i19, (List<Long>) zza(t8, j10), false);
                        i17 += iZzb2;
                        break;
                    case 22:
                        iZzb2 = zzjx.zze(i19, zza(t8, j10), false);
                        i17 += iZzb2;
                        break;
                    case 23:
                        iZzb2 = zzjx.zzi(i19, zza(t8, j10), false);
                        i17 += iZzb2;
                        break;
                    case 24:
                        iZzb2 = zzjx.zzh(i19, zza(t8, j10), false);
                        i17 += iZzb2;
                        break;
                    case 25:
                        iZzb2 = zzjx.zzj(i19, zza(t8, j10), false);
                        i17 += iZzb2;
                        break;
                    case 26:
                        iZzb2 = zzjx.zza(i19, zza(t8, j10));
                        i17 += iZzb2;
                        break;
                    case 27:
                        iZzb2 = zzjx.zza(i19, zza(t8, j10), zza(i16));
                        i17 += iZzb2;
                        break;
                    case 28:
                        iZzb2 = zzjx.zzb(i19, zza(t8, j10));
                        i17 += iZzb2;
                        break;
                    case 29:
                        iZzb2 = zzjx.zzf(i19, zza(t8, j10), false);
                        i17 += iZzb2;
                        break;
                    case 30:
                        iZzb2 = zzjx.zzd(i19, zza(t8, j10), false);
                        i17 += iZzb2;
                        break;
                    case 31:
                        iZzb2 = zzjx.zzh(i19, zza(t8, j10), false);
                        i17 += iZzb2;
                        break;
                    case 32:
                        iZzb2 = zzjx.zzi(i19, zza(t8, j10), false);
                        i17 += iZzb2;
                        break;
                    case 33:
                        iZzb2 = zzjx.zzg(i19, zza(t8, j10), false);
                        i17 += iZzb2;
                        break;
                    case 34:
                        iZzb2 = zzjx.zzc(i19, zza(t8, j10), false);
                        i17 += iZzb2;
                        break;
                    case 35:
                        iZzi2 = zzjx.zzi((List) unsafe.getObject(t8, j10));
                        if (iZzi2 > 0) {
                            iZze2 = zzhf.zze(i19);
                            iZzg2 = zzhf.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 36:
                        iZzi2 = zzjx.zzh((List) unsafe.getObject(t8, j10));
                        if (iZzi2 > 0) {
                            iZze2 = zzhf.zze(i19);
                            iZzg2 = zzhf.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 37:
                        iZzi2 = zzjx.zza((List<Long>) unsafe.getObject(t8, j10));
                        if (iZzi2 > 0) {
                            iZze2 = zzhf.zze(i19);
                            iZzg2 = zzhf.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 38:
                        iZzi2 = zzjx.zzb((List) unsafe.getObject(t8, j10));
                        if (iZzi2 > 0) {
                            iZze2 = zzhf.zze(i19);
                            iZzg2 = zzhf.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 39:
                        iZzi2 = zzjx.zze((List) unsafe.getObject(t8, j10));
                        if (iZzi2 > 0) {
                            iZze2 = zzhf.zze(i19);
                            iZzg2 = zzhf.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 40:
                        iZzi2 = zzjx.zzi((List) unsafe.getObject(t8, j10));
                        if (iZzi2 > 0) {
                            iZze2 = zzhf.zze(i19);
                            iZzg2 = zzhf.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 41:
                        iZzi2 = zzjx.zzh((List) unsafe.getObject(t8, j10));
                        if (iZzi2 > 0) {
                            iZze2 = zzhf.zze(i19);
                            iZzg2 = zzhf.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 42:
                        iZzi2 = zzjx.zzj((List) unsafe.getObject(t8, j10));
                        if (iZzi2 > 0) {
                            iZze2 = zzhf.zze(i19);
                            iZzg2 = zzhf.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 43:
                        iZzi2 = zzjx.zzf((List) unsafe.getObject(t8, j10));
                        if (iZzi2 > 0) {
                            iZze2 = zzhf.zze(i19);
                            iZzg2 = zzhf.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 44:
                        iZzi2 = zzjx.zzd((List) unsafe.getObject(t8, j10));
                        if (iZzi2 > 0) {
                            iZze2 = zzhf.zze(i19);
                            iZzg2 = zzhf.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 45:
                        iZzi2 = zzjx.zzh((List) unsafe.getObject(t8, j10));
                        if (iZzi2 > 0) {
                            iZze2 = zzhf.zze(i19);
                            iZzg2 = zzhf.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 46:
                        iZzi2 = zzjx.zzi((List) unsafe.getObject(t8, j10));
                        if (iZzi2 > 0) {
                            iZze2 = zzhf.zze(i19);
                            iZzg2 = zzhf.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 47:
                        iZzi2 = zzjx.zzg((List) unsafe.getObject(t8, j10));
                        if (iZzi2 > 0) {
                            iZze2 = zzhf.zze(i19);
                            iZzg2 = zzhf.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 48:
                        iZzi2 = zzjx.zzc((List) unsafe.getObject(t8, j10));
                        if (iZzi2 > 0) {
                            iZze2 = zzhf.zze(i19);
                            iZzg2 = zzhf.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 49:
                        iZzb2 = zzjx.zzb(i19, (List<zzjg>) zza(t8, j10), zza(i16));
                        i17 += iZzb2;
                        break;
                    case 50:
                        iZzb2 = this.zzs.zza(i19, zzkt.zzf(t8, j10), zzb(i16));
                        i17 += iZzb2;
                        break;
                    case 51:
                        if (zza((zzjk<T>) t8, i19, i16)) {
                            iZzb2 = zzhf.zzb(i19, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zza((zzjk<T>) t8, i19, i16)) {
                            iZzb2 = zzhf.zzb(i19, BitmapDescriptorFactory.HUE_RED);
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zza((zzjk<T>) t8, i19, i16)) {
                            iZzb2 = zzhf.zzd(i19, zze(t8, j10));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zza((zzjk<T>) t8, i19, i16)) {
                            iZzb2 = zzhf.zze(i19, zze(t8, j10));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zza((zzjk<T>) t8, i19, i16)) {
                            iZzb2 = zzhf.zzf(i19, zzd(t8, j10));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zza((zzjk<T>) t8, i19, i16)) {
                            iZzb2 = zzhf.zzg(i19, 0L);
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zza((zzjk<T>) t8, i19, i16)) {
                            iZzb2 = zzhf.zzi(i19, 0);
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zza((zzjk<T>) t8, i19, i16)) {
                            iZzb2 = zzhf.zzb(i19, true);
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zza((zzjk<T>) t8, i19, i16)) {
                            Object objZzf2 = zzkt.zzf(t8, j10);
                            if (objZzf2 instanceof zzgm) {
                                iZzb2 = zzhf.zzc(i19, (zzgm) objZzf2);
                            } else {
                                iZzb2 = zzhf.zzb(i19, (String) objZzf2);
                            }
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (zza((zzjk<T>) t8, i19, i16)) {
                            iZzb2 = zzjx.zza(i19, zzkt.zzf(t8, j10), zza(i16));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zza((zzjk<T>) t8, i19, i16)) {
                            iZzb2 = zzhf.zzc(i19, (zzgm) zzkt.zzf(t8, j10));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zza((zzjk<T>) t8, i19, i16)) {
                            iZzb2 = zzhf.zzg(i19, zzd(t8, j10));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zza((zzjk<T>) t8, i19, i16)) {
                            iZzb2 = zzhf.zzk(i19, zzd(t8, j10));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zza((zzjk<T>) t8, i19, i16)) {
                            iZzb2 = zzhf.zzj(i19, 0);
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zza((zzjk<T>) t8, i19, i16)) {
                            iZzb2 = zzhf.zzh(i19, 0L);
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zza((zzjk<T>) t8, i19, i16)) {
                            iZzb2 = zzhf.zzh(i19, zzd(t8, j10));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zza((zzjk<T>) t8, i19, i16)) {
                            iZzb2 = zzhf.zzf(i19, zze(t8, j10));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zza((zzjk<T>) t8, i19, i16)) {
                            iZzb2 = zzhf.zzc(i19, (zzjg) zzkt.zzf(t8, j10), zza(i16));
                            i17 += iZzb2;
                            break;
                        } else {
                            break;
                        }
                }
                i16 += 3;
                i12 = 267386880;
            }
            return i17 + zza((zzkn) this.zzq, (Object) t8);
        }
        Unsafe unsafe2 = zzb;
        int i21 = 1048575;
        int i22 = 0;
        int iZzb3 = 0;
        int i23 = 0;
        while (i22 < this.zzc.length) {
            int iZzd3 = zzd(i22);
            int[] iArr = this.zzc;
            int i24 = iArr[i22];
            int i25 = (iZzd3 & 267386880) >>> 20;
            if (i25 <= 17) {
                int i26 = iArr[i22 + 2];
                int i27 = i26 & i13;
                i9 = i14 << (i26 >>> 20);
                if (i27 != i21) {
                    i23 = unsafe2.getInt(t8, i27);
                    i21 = i27;
                }
            } else {
                i9 = 0;
            }
            long j11 = iZzd3 & i13;
            switch (i25) {
                case 0:
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    if ((i23 & i9) != 0) {
                        iZzb3 += zzhf.zzb(i24, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    if ((i23 & i9) != 0) {
                        iZzb3 += zzhf.zzb(i24, BitmapDescriptorFactory.HUE_RED);
                    }
                    break;
                case 2:
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    if ((i9 & i23) != 0) {
                        iZzd = zzhf.zzd(i24, unsafe2.getLong(t8, j11));
                        iZzb3 += iZzd;
                    }
                    break;
                case 3:
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    if ((i9 & i23) != 0) {
                        iZzd = zzhf.zze(i24, unsafe2.getLong(t8, j11));
                        iZzb3 += iZzd;
                    }
                    break;
                case 4:
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    if ((i9 & i23) != 0) {
                        iZzd = zzhf.zzf(i24, unsafe2.getInt(t8, j11));
                        iZzb3 += iZzd;
                    }
                    break;
                case 5:
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    if ((i23 & i9) != 0) {
                        iZzd = zzhf.zzg(i24, 0L);
                        iZzb3 += iZzd;
                    }
                    break;
                case 6:
                    i10 = 1;
                    i11 = 0;
                    if ((i23 & i9) != 0) {
                        iZzb3 += zzhf.zzi(i24, 0);
                    }
                    j9 = 0;
                    break;
                case 7:
                    if ((i23 & i9) != 0) {
                        i10 = 1;
                        iZzb3 += zzhf.zzb(i24, true);
                    } else {
                        i10 = 1;
                    }
                    i11 = 0;
                    j9 = 0;
                    break;
                case 8:
                    if ((i23 & i9) != 0) {
                        Object object = unsafe2.getObject(t8, j11);
                        if (object instanceof zzgm) {
                            iZzb = zzhf.zzc(i24, (zzgm) object);
                        } else {
                            iZzb = zzhf.zzb(i24, (String) object);
                        }
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 9:
                    if ((i23 & i9) != 0) {
                        iZzb = zzjx.zza(i24, unsafe2.getObject(t8, j11), zza(i22));
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 10:
                    if ((i23 & i9) != 0) {
                        iZzb = zzhf.zzc(i24, (zzgm) unsafe2.getObject(t8, j11));
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 11:
                    if ((i23 & i9) != 0) {
                        iZzb = zzhf.zzg(i24, unsafe2.getInt(t8, j11));
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 12:
                    if ((i23 & i9) != 0) {
                        iZzb = zzhf.zzk(i24, unsafe2.getInt(t8, j11));
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 13:
                    if ((i23 & i9) != 0) {
                        iZzj = zzhf.zzj(i24, 0);
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 14:
                    if ((i23 & i9) != 0) {
                        iZzb = zzhf.zzh(i24, 0L);
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 15:
                    if ((i23 & i9) != 0) {
                        iZzb = zzhf.zzh(i24, unsafe2.getInt(t8, j11));
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 16:
                    if ((i23 & i9) != 0) {
                        iZzb = zzhf.zzf(i24, unsafe2.getLong(t8, j11));
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 17:
                    if ((i23 & i9) != 0) {
                        iZzb = zzhf.zzc(i24, (zzjg) unsafe2.getObject(t8, j11), zza(i22));
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 18:
                    iZzb = zzjx.zzi(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb3 += iZzb;
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 19:
                    i11 = 0;
                    iZzh = zzjx.zzh(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb3 += iZzh;
                    i10 = 1;
                    j9 = 0;
                    break;
                case 20:
                    i11 = 0;
                    iZzh = zzjx.zza(i24, (List<Long>) unsafe2.getObject(t8, j11), false);
                    iZzb3 += iZzh;
                    i10 = 1;
                    j9 = 0;
                    break;
                case 21:
                    i11 = 0;
                    iZzh = zzjx.zzb(i24, (List<Long>) unsafe2.getObject(t8, j11), false);
                    iZzb3 += iZzh;
                    i10 = 1;
                    j9 = 0;
                    break;
                case 22:
                    i11 = 0;
                    iZzh = zzjx.zze(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb3 += iZzh;
                    i10 = 1;
                    j9 = 0;
                    break;
                case 23:
                    i11 = 0;
                    iZzh = zzjx.zzi(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb3 += iZzh;
                    i10 = 1;
                    j9 = 0;
                    break;
                case 24:
                    i11 = 0;
                    iZzh = zzjx.zzh(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb3 += iZzh;
                    i10 = 1;
                    j9 = 0;
                    break;
                case 25:
                    i11 = 0;
                    iZzh = zzjx.zzj(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb3 += iZzh;
                    i10 = 1;
                    j9 = 0;
                    break;
                case 26:
                    iZzb = zzjx.zza(i24, (List<?>) unsafe2.getObject(t8, j11));
                    iZzb3 += iZzb;
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 27:
                    iZzb = zzjx.zza(i24, (List<?>) unsafe2.getObject(t8, j11), zza(i22));
                    iZzb3 += iZzb;
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 28:
                    iZzb = zzjx.zzb(i24, (List) unsafe2.getObject(t8, j11));
                    iZzb3 += iZzb;
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 29:
                    iZzb = zzjx.zzf(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb3 += iZzb;
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 30:
                    i11 = 0;
                    iZzh = zzjx.zzd(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb3 += iZzh;
                    i10 = 1;
                    j9 = 0;
                    break;
                case 31:
                    i11 = 0;
                    iZzh = zzjx.zzh(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb3 += iZzh;
                    i10 = 1;
                    j9 = 0;
                    break;
                case 32:
                    i11 = 0;
                    iZzh = zzjx.zzi(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb3 += iZzh;
                    i10 = 1;
                    j9 = 0;
                    break;
                case 33:
                    i11 = 0;
                    iZzh = zzjx.zzg(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb3 += iZzh;
                    i10 = 1;
                    j9 = 0;
                    break;
                case 34:
                    i11 = 0;
                    iZzh = zzjx.zzc(i24, (List) unsafe2.getObject(t8, j11), false);
                    iZzb3 += iZzh;
                    i10 = 1;
                    j9 = 0;
                    break;
                case 35:
                    iZzi = zzjx.zzi((List) unsafe2.getObject(t8, j11));
                    if (iZzi > 0) {
                        iZze = zzhf.zze(i24);
                        iZzg = zzhf.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 36:
                    iZzi = zzjx.zzh((List) unsafe2.getObject(t8, j11));
                    if (iZzi > 0) {
                        iZze = zzhf.zze(i24);
                        iZzg = zzhf.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 37:
                    iZzi = zzjx.zza((List<Long>) unsafe2.getObject(t8, j11));
                    if (iZzi > 0) {
                        iZze = zzhf.zze(i24);
                        iZzg = zzhf.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 38:
                    iZzi = zzjx.zzb((List) unsafe2.getObject(t8, j11));
                    if (iZzi > 0) {
                        iZze = zzhf.zze(i24);
                        iZzg = zzhf.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 39:
                    iZzi = zzjx.zze((List) unsafe2.getObject(t8, j11));
                    if (iZzi > 0) {
                        iZze = zzhf.zze(i24);
                        iZzg = zzhf.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 40:
                    iZzi = zzjx.zzi((List) unsafe2.getObject(t8, j11));
                    if (iZzi > 0) {
                        iZze = zzhf.zze(i24);
                        iZzg = zzhf.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 41:
                    iZzi = zzjx.zzh((List) unsafe2.getObject(t8, j11));
                    if (iZzi > 0) {
                        iZze = zzhf.zze(i24);
                        iZzg = zzhf.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 42:
                    iZzi = zzjx.zzj((List) unsafe2.getObject(t8, j11));
                    if (iZzi > 0) {
                        iZze = zzhf.zze(i24);
                        iZzg = zzhf.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 43:
                    iZzi = zzjx.zzf((List) unsafe2.getObject(t8, j11));
                    if (iZzi > 0) {
                        iZze = zzhf.zze(i24);
                        iZzg = zzhf.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 44:
                    iZzi = zzjx.zzd((List) unsafe2.getObject(t8, j11));
                    if (iZzi > 0) {
                        iZze = zzhf.zze(i24);
                        iZzg = zzhf.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 45:
                    iZzi = zzjx.zzh((List) unsafe2.getObject(t8, j11));
                    if (iZzi > 0) {
                        iZze = zzhf.zze(i24);
                        iZzg = zzhf.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 46:
                    iZzi = zzjx.zzi((List) unsafe2.getObject(t8, j11));
                    if (iZzi > 0) {
                        iZze = zzhf.zze(i24);
                        iZzg = zzhf.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 47:
                    iZzi = zzjx.zzg((List) unsafe2.getObject(t8, j11));
                    if (iZzi > 0) {
                        iZze = zzhf.zze(i24);
                        iZzg = zzhf.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 48:
                    iZzi = zzjx.zzc((List) unsafe2.getObject(t8, j11));
                    if (iZzi > 0) {
                        iZze = zzhf.zze(i24);
                        iZzg = zzhf.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 49:
                    iZzb = zzjx.zzb(i24, (List<zzjg>) unsafe2.getObject(t8, j11), zza(i22));
                    iZzb3 += iZzb;
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 50:
                    iZzb = this.zzs.zza(i24, unsafe2.getObject(t8, j11), zzb(i22));
                    iZzb3 += iZzb;
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 51:
                    if (zza((zzjk<T>) t8, i24, i22)) {
                        iZzb = zzhf.zzb(i24, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 52:
                    if (zza((zzjk<T>) t8, i24, i22)) {
                        iZzj = zzhf.zzb(i24, BitmapDescriptorFactory.HUE_RED);
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 53:
                    if (zza((zzjk<T>) t8, i24, i22)) {
                        iZzb = zzhf.zzd(i24, zze(t8, j11));
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 54:
                    if (zza((zzjk<T>) t8, i24, i22)) {
                        iZzb = zzhf.zze(i24, zze(t8, j11));
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 55:
                    if (zza((zzjk<T>) t8, i24, i22)) {
                        iZzb = zzhf.zzf(i24, zzd(t8, j11));
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 56:
                    if (zza((zzjk<T>) t8, i24, i22)) {
                        iZzb = zzhf.zzg(i24, 0L);
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 57:
                    if (zza((zzjk<T>) t8, i24, i22)) {
                        iZzj = zzhf.zzi(i24, 0);
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 58:
                    if (zza((zzjk<T>) t8, i24, i22)) {
                        iZzj = zzhf.zzb(i24, true);
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 59:
                    if (zza((zzjk<T>) t8, i24, i22)) {
                        Object object2 = unsafe2.getObject(t8, j11);
                        if (object2 instanceof zzgm) {
                            iZzb = zzhf.zzc(i24, (zzgm) object2);
                        } else {
                            iZzb = zzhf.zzb(i24, (String) object2);
                        }
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 60:
                    if (zza((zzjk<T>) t8, i24, i22)) {
                        iZzb = zzjx.zza(i24, unsafe2.getObject(t8, j11), zza(i22));
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 61:
                    if (zza((zzjk<T>) t8, i24, i22)) {
                        iZzb = zzhf.zzc(i24, (zzgm) unsafe2.getObject(t8, j11));
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 62:
                    if (zza((zzjk<T>) t8, i24, i22)) {
                        iZzb = zzhf.zzg(i24, zzd(t8, j11));
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 63:
                    if (zza((zzjk<T>) t8, i24, i22)) {
                        iZzb = zzhf.zzk(i24, zzd(t8, j11));
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 64:
                    if (zza((zzjk<T>) t8, i24, i22)) {
                        iZzj = zzhf.zzj(i24, 0);
                        iZzb3 += iZzj;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 65:
                    if (zza((zzjk<T>) t8, i24, i22)) {
                        iZzb = zzhf.zzh(i24, 0L);
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 66:
                    if (zza((zzjk<T>) t8, i24, i22)) {
                        iZzb = zzhf.zzh(i24, zzd(t8, j11));
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 67:
                    if (zza((zzjk<T>) t8, i24, i22)) {
                        iZzb = zzhf.zzf(i24, zze(t8, j11));
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
                case 68:
                    if (zza((zzjk<T>) t8, i24, i22)) {
                        iZzb = zzhf.zzc(i24, (zzjg) unsafe2.getObject(t8, j11), zza(i22));
                        iZzb3 += iZzb;
                    }
                    i10 = 1;
                    i11 = 0;
                    j9 = 0;
                    break;
            }
            i22 += 3;
            i14 = i10;
            i15 = i11;
            i13 = 1048575;
        }
        int iZza = i15;
        int iZza2 = iZzb3 + zza((zzkn) this.zzq, (Object) t8);
        if (!this.zzh) {
            return iZza2;
        }
        zzho<T> zzhoVarZza = this.zzr.zza(t8);
        for (int i28 = iZza; i28 < zzhoVarZza.zza.zzc(); i28++) {
            Map.Entry entryZzb = zzhoVarZza.zza.zzb(i28);
            iZza += zzho.zza((zzhq<?>) entryZzb.getKey(), entryZzb.getValue());
        }
        for (Map.Entry entry : zzhoVarZza.zza.zzd()) {
            iZza += zzho.zza((zzhq<?>) entry.getKey(), entry.getValue());
        }
        return iZza2 + iZza;
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

    @Override // com.google.android.gms.internal.measurement.zzjv
    public final T zza() {
        return (T) this.zzo.zza(this.zzg);
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x01c1  */
    @Override // com.google.android.gms.internal.measurement.zzjv
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean zza(T t8, T t9) {
        int length = this.zzc.length;
        int i9 = 0;
        while (true) {
            boolean zZza = true;
            if (i9 < length) {
                int iZzd = zzd(i9);
                long j9 = iZzd & 1048575;
                switch ((iZzd & 267386880) >>> 20) {
                    case 0:
                        if (!zzc(t8, t9, i9) || Double.doubleToLongBits(zzkt.zze(t8, j9)) != Double.doubleToLongBits(zzkt.zze(t9, j9))) {
                            zZza = false;
                            break;
                        }
                        break;
                    case 1:
                        if (!zzc(t8, t9, i9) || Float.floatToIntBits(zzkt.zzd(t8, j9)) != Float.floatToIntBits(zzkt.zzd(t9, j9))) {
                        }
                        break;
                    case 2:
                        if (!zzc(t8, t9, i9) || zzkt.zzb(t8, j9) != zzkt.zzb(t9, j9)) {
                        }
                        break;
                    case 3:
                        if (!zzc(t8, t9, i9) || zzkt.zzb(t8, j9) != zzkt.zzb(t9, j9)) {
                        }
                        break;
                    case 4:
                        if (!zzc(t8, t9, i9) || zzkt.zza(t8, j9) != zzkt.zza(t9, j9)) {
                        }
                        break;
                    case 5:
                        if (!zzc(t8, t9, i9) || zzkt.zzb(t8, j9) != zzkt.zzb(t9, j9)) {
                        }
                        break;
                    case 6:
                        if (!zzc(t8, t9, i9) || zzkt.zza(t8, j9) != zzkt.zza(t9, j9)) {
                        }
                        break;
                    case 7:
                        if (!zzc(t8, t9, i9) || zzkt.zzc(t8, j9) != zzkt.zzc(t9, j9)) {
                        }
                        break;
                    case 8:
                        if (!zzc(t8, t9, i9) || !zzjx.zza(zzkt.zzf(t8, j9), zzkt.zzf(t9, j9))) {
                        }
                        break;
                    case 9:
                        if (!zzc(t8, t9, i9) || !zzjx.zza(zzkt.zzf(t8, j9), zzkt.zzf(t9, j9))) {
                        }
                        break;
                    case 10:
                        if (!zzc(t8, t9, i9) || !zzjx.zza(zzkt.zzf(t8, j9), zzkt.zzf(t9, j9))) {
                        }
                        break;
                    case 11:
                        if (!zzc(t8, t9, i9) || zzkt.zza(t8, j9) != zzkt.zza(t9, j9)) {
                        }
                        break;
                    case 12:
                        if (!zzc(t8, t9, i9) || zzkt.zza(t8, j9) != zzkt.zza(t9, j9)) {
                        }
                        break;
                    case 13:
                        if (!zzc(t8, t9, i9) || zzkt.zza(t8, j9) != zzkt.zza(t9, j9)) {
                        }
                        break;
                    case 14:
                        if (!zzc(t8, t9, i9) || zzkt.zzb(t8, j9) != zzkt.zzb(t9, j9)) {
                        }
                        break;
                    case 15:
                        if (!zzc(t8, t9, i9) || zzkt.zza(t8, j9) != zzkt.zza(t9, j9)) {
                        }
                        break;
                    case 16:
                        if (!zzc(t8, t9, i9) || zzkt.zzb(t8, j9) != zzkt.zzb(t9, j9)) {
                        }
                        break;
                    case 17:
                        if (!zzc(t8, t9, i9) || !zzjx.zza(zzkt.zzf(t8, j9), zzkt.zzf(t9, j9))) {
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
                        zZza = zzjx.zza(zzkt.zzf(t8, j9), zzkt.zzf(t9, j9));
                        break;
                    case 50:
                        zZza = zzjx.zza(zzkt.zzf(t8, j9), zzkt.zzf(t9, j9));
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
                        long jZze = zze(i9) & 1048575;
                        if (zzkt.zza(t8, jZze) != zzkt.zza(t9, jZze) || !zzjx.zza(zzkt.zzf(t8, j9), zzkt.zzf(t9, j9))) {
                        }
                        break;
                }
                if (!zZza) {
                    return false;
                }
                i9 += 3;
            } else {
                if (!this.zzq.zzb(t8).equals(this.zzq.zzb(t9))) {
                    return false;
                }
                if (this.zzh) {
                    return this.zzr.zza(t8).equals(this.zzr.zza(t9));
                }
                return true;
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzjv
    public final int zza(T t8) {
        int i9;
        int iZza;
        int length = this.zzc.length;
        int i10 = 0;
        for (int i11 = 0; i11 < length; i11 += 3) {
            int iZzd = zzd(i11);
            int i12 = this.zzc[i11];
            long j9 = 1048575 & iZzd;
            int iHashCode = 37;
            switch ((iZzd & 267386880) >>> 20) {
                case 0:
                    i9 = i10 * 53;
                    iZza = zzhx.zza(Double.doubleToLongBits(zzkt.zze(t8, j9)));
                    i10 = i9 + iZza;
                    break;
                case 1:
                    i9 = i10 * 53;
                    iZza = Float.floatToIntBits(zzkt.zzd(t8, j9));
                    i10 = i9 + iZza;
                    break;
                case 2:
                    i9 = i10 * 53;
                    iZza = zzhx.zza(zzkt.zzb(t8, j9));
                    i10 = i9 + iZza;
                    break;
                case 3:
                    i9 = i10 * 53;
                    iZza = zzhx.zza(zzkt.zzb(t8, j9));
                    i10 = i9 + iZza;
                    break;
                case 4:
                    i9 = i10 * 53;
                    iZza = zzkt.zza(t8, j9);
                    i10 = i9 + iZza;
                    break;
                case 5:
                    i9 = i10 * 53;
                    iZza = zzhx.zza(zzkt.zzb(t8, j9));
                    i10 = i9 + iZza;
                    break;
                case 6:
                    i9 = i10 * 53;
                    iZza = zzkt.zza(t8, j9);
                    i10 = i9 + iZza;
                    break;
                case 7:
                    i9 = i10 * 53;
                    iZza = zzhx.zza(zzkt.zzc(t8, j9));
                    i10 = i9 + iZza;
                    break;
                case 8:
                    i9 = i10 * 53;
                    iZza = ((String) zzkt.zzf(t8, j9)).hashCode();
                    i10 = i9 + iZza;
                    break;
                case 9:
                    Object objZzf = zzkt.zzf(t8, j9);
                    if (objZzf != null) {
                        iHashCode = objZzf.hashCode();
                    }
                    i10 = (i10 * 53) + iHashCode;
                    break;
                case 10:
                    i9 = i10 * 53;
                    iZza = zzkt.zzf(t8, j9).hashCode();
                    i10 = i9 + iZza;
                    break;
                case 11:
                    i9 = i10 * 53;
                    iZza = zzkt.zza(t8, j9);
                    i10 = i9 + iZza;
                    break;
                case 12:
                    i9 = i10 * 53;
                    iZza = zzkt.zza(t8, j9);
                    i10 = i9 + iZza;
                    break;
                case 13:
                    i9 = i10 * 53;
                    iZza = zzkt.zza(t8, j9);
                    i10 = i9 + iZza;
                    break;
                case 14:
                    i9 = i10 * 53;
                    iZza = zzhx.zza(zzkt.zzb(t8, j9));
                    i10 = i9 + iZza;
                    break;
                case 15:
                    i9 = i10 * 53;
                    iZza = zzkt.zza(t8, j9);
                    i10 = i9 + iZza;
                    break;
                case 16:
                    i9 = i10 * 53;
                    iZza = zzhx.zza(zzkt.zzb(t8, j9));
                    i10 = i9 + iZza;
                    break;
                case 17:
                    Object objZzf2 = zzkt.zzf(t8, j9);
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
                    iZza = zzkt.zzf(t8, j9).hashCode();
                    i10 = i9 + iZza;
                    break;
                case 50:
                    i9 = i10 * 53;
                    iZza = zzkt.zzf(t8, j9).hashCode();
                    i10 = i9 + iZza;
                    break;
                case 51:
                    if (zza((zzjk<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZza = zzhx.zza(Double.doubleToLongBits(zzb(t8, j9)));
                        i10 = i9 + iZza;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zza((zzjk<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZza = Float.floatToIntBits(zzc(t8, j9));
                        i10 = i9 + iZza;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zza((zzjk<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZza = zzhx.zza(zze(t8, j9));
                        i10 = i9 + iZza;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zza((zzjk<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZza = zzhx.zza(zze(t8, j9));
                        i10 = i9 + iZza;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zza((zzjk<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZza = zzd(t8, j9);
                        i10 = i9 + iZza;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zza((zzjk<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZza = zzhx.zza(zze(t8, j9));
                        i10 = i9 + iZza;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zza((zzjk<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZza = zzd(t8, j9);
                        i10 = i9 + iZza;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zza((zzjk<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZza = zzhx.zza(zzf(t8, j9));
                        i10 = i9 + iZza;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zza((zzjk<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZza = ((String) zzkt.zzf(t8, j9)).hashCode();
                        i10 = i9 + iZza;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zza((zzjk<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZza = zzkt.zzf(t8, j9).hashCode();
                        i10 = i9 + iZza;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zza((zzjk<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZza = zzkt.zzf(t8, j9).hashCode();
                        i10 = i9 + iZza;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zza((zzjk<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZza = zzd(t8, j9);
                        i10 = i9 + iZza;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zza((zzjk<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZza = zzd(t8, j9);
                        i10 = i9 + iZza;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zza((zzjk<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZza = zzd(t8, j9);
                        i10 = i9 + iZza;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zza((zzjk<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZza = zzhx.zza(zze(t8, j9));
                        i10 = i9 + iZza;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zza((zzjk<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZza = zzd(t8, j9);
                        i10 = i9 + iZza;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zza((zzjk<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZza = zzhx.zza(zze(t8, j9));
                        i10 = i9 + iZza;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zza((zzjk<T>) t8, i12, i11)) {
                        i9 = i10 * 53;
                        iZza = zzkt.zzf(t8, j9).hashCode();
                        i10 = i9 + iZza;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int iHashCode2 = (i10 * 53) + this.zzq.zzb(t8).hashCode();
        return this.zzh ? (iHashCode2 * 53) + this.zzr.zza(t8).hashCode() : iHashCode2;
    }

    private final void zza(T t8, T t9, int i9) {
        long jZzd = zzd(i9) & 1048575;
        if (zza((zzjk<T>) t9, i9)) {
            Object objZzf = zzkt.zzf(t8, jZzd);
            Object objZzf2 = zzkt.zzf(t9, jZzd);
            if (objZzf != null && objZzf2 != null) {
                zzkt.zza(t8, jZzd, zzhx.zza(objZzf, objZzf2));
                zzb((zzjk<T>) t8, i9);
            } else if (objZzf2 != null) {
                zzkt.zza(t8, jZzd, objZzf2);
                zzb((zzjk<T>) t8, i9);
            }
        }
    }

    private static <UT, UB> int zza(zzkn<UT, UB> zzknVar, T t8) {
        return zzknVar.zzf(zzknVar.zzb(t8));
    }

    private static List<?> zza(Object obj, long j9) {
        return (List) zzkt.zzf(obj, j9);
    }

    /* JADX WARN: Removed duplicated region for block: B:178:0x054a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0032  */
    @Override // com.google.android.gms.internal.measurement.zzjv
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(T t8, zzlk zzlkVar) {
        Iterator itZzd;
        Map.Entry<?, ?> entry;
        Iterator itZze;
        Map.Entry<?, ?> entry2;
        if (zzlkVar.zza() == zzhv.zze.zzk) {
            zza(this.zzq, t8, zzlkVar);
            if (this.zzh) {
                zzho<T> zzhoVarZza = this.zzr.zza(t8);
                if (zzhoVarZza.zza.isEmpty()) {
                    itZze = null;
                    entry2 = null;
                } else {
                    itZze = zzhoVarZza.zze();
                    entry2 = (Map.Entry) itZze.next();
                }
            }
            for (int length = this.zzc.length - 3; length >= 0; length -= 3) {
                int iZzd = zzd(length);
                int i9 = this.zzc[length];
                while (entry2 != null && this.zzr.zza(entry2) > i9) {
                    this.zzr.zza(zzlkVar, entry2);
                    entry2 = itZze.hasNext() ? (Map.Entry) itZze.next() : null;
                }
                switch ((iZzd & 267386880) >>> 20) {
                    case 0:
                        if (zza((zzjk<T>) t8, length)) {
                            zzlkVar.zza(i9, zzkt.zze(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zza((zzjk<T>) t8, length)) {
                            zzlkVar.zza(i9, zzkt.zzd(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zza((zzjk<T>) t8, length)) {
                            zzlkVar.zza(i9, zzkt.zzb(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zza((zzjk<T>) t8, length)) {
                            zzlkVar.zzc(i9, zzkt.zzb(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zza((zzjk<T>) t8, length)) {
                            zzlkVar.zzc(i9, zzkt.zza(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zza((zzjk<T>) t8, length)) {
                            zzlkVar.zzd(i9, zzkt.zzb(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zza((zzjk<T>) t8, length)) {
                            zzlkVar.zzd(i9, zzkt.zza(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zza((zzjk<T>) t8, length)) {
                            zzlkVar.zza(i9, zzkt.zzc(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zza((zzjk<T>) t8, length)) {
                            zza(i9, zzkt.zzf(t8, iZzd & 1048575), zzlkVar);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        if (zza((zzjk<T>) t8, length)) {
                            zzlkVar.zza(i9, zzkt.zzf(t8, iZzd & 1048575), zza(length));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zza((zzjk<T>) t8, length)) {
                            zzlkVar.zza(i9, (zzgm) zzkt.zzf(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zza((zzjk<T>) t8, length)) {
                            zzlkVar.zze(i9, zzkt.zza(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zza((zzjk<T>) t8, length)) {
                            zzlkVar.zzb(i9, zzkt.zza(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zza((zzjk<T>) t8, length)) {
                            zzlkVar.zza(i9, zzkt.zza(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zza((zzjk<T>) t8, length)) {
                            zzlkVar.zzb(i9, zzkt.zzb(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zza((zzjk<T>) t8, length)) {
                            zzlkVar.zzf(i9, zzkt.zza(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zza((zzjk<T>) t8, length)) {
                            zzlkVar.zze(i9, zzkt.zzb(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zza((zzjk<T>) t8, length)) {
                            zzlkVar.zzb(i9, zzkt.zzf(t8, iZzd & 1048575), zza(length));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        zzjx.zza(this.zzc[length], (List<Double>) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, false);
                        break;
                    case 19:
                        zzjx.zzb(this.zzc[length], (List<Float>) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, false);
                        break;
                    case 20:
                        zzjx.zzc(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, false);
                        break;
                    case 21:
                        zzjx.zzd(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, false);
                        break;
                    case 22:
                        zzjx.zzh(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, false);
                        break;
                    case 23:
                        zzjx.zzf(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, false);
                        break;
                    case 24:
                        zzjx.zzk(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, false);
                        break;
                    case 25:
                        zzjx.zzn(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, false);
                        break;
                    case 26:
                        zzjx.zza(this.zzc[length], (List<String>) zzkt.zzf(t8, iZzd & 1048575), zzlkVar);
                        break;
                    case 27:
                        zzjx.zza(this.zzc[length], (List<?>) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, zza(length));
                        break;
                    case 28:
                        zzjx.zzb(this.zzc[length], (List<zzgm>) zzkt.zzf(t8, iZzd & 1048575), zzlkVar);
                        break;
                    case 29:
                        zzjx.zzi(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, false);
                        break;
                    case 30:
                        zzjx.zzm(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, false);
                        break;
                    case 31:
                        zzjx.zzl(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, false);
                        break;
                    case 32:
                        zzjx.zzg(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, false);
                        break;
                    case 33:
                        zzjx.zzj(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, false);
                        break;
                    case 34:
                        zzjx.zze(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, false);
                        break;
                    case 35:
                        zzjx.zza(this.zzc[length], (List<Double>) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, true);
                        break;
                    case 36:
                        zzjx.zzb(this.zzc[length], (List<Float>) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, true);
                        break;
                    case 37:
                        zzjx.zzc(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, true);
                        break;
                    case 38:
                        zzjx.zzd(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, true);
                        break;
                    case 39:
                        zzjx.zzh(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, true);
                        break;
                    case 40:
                        zzjx.zzf(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, true);
                        break;
                    case 41:
                        zzjx.zzk(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, true);
                        break;
                    case 42:
                        zzjx.zzn(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, true);
                        break;
                    case 43:
                        zzjx.zzi(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, true);
                        break;
                    case 44:
                        zzjx.zzm(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, true);
                        break;
                    case 45:
                        zzjx.zzl(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, true);
                        break;
                    case 46:
                        zzjx.zzg(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, true);
                        break;
                    case 47:
                        zzjx.zzj(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, true);
                        break;
                    case 48:
                        zzjx.zze(this.zzc[length], (List) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, true);
                        break;
                    case 49:
                        zzjx.zzb(this.zzc[length], (List<?>) zzkt.zzf(t8, iZzd & 1048575), zzlkVar, zza(length));
                        break;
                    case 50:
                        zza(zzlkVar, i9, zzkt.zzf(t8, iZzd & 1048575), length);
                        break;
                    case 51:
                        if (zza((zzjk<T>) t8, i9, length)) {
                            zzlkVar.zza(i9, zzb(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zza((zzjk<T>) t8, i9, length)) {
                            zzlkVar.zza(i9, zzc(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zza((zzjk<T>) t8, i9, length)) {
                            zzlkVar.zza(i9, zze(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zza((zzjk<T>) t8, i9, length)) {
                            zzlkVar.zzc(i9, zze(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zza((zzjk<T>) t8, i9, length)) {
                            zzlkVar.zzc(i9, zzd(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zza((zzjk<T>) t8, i9, length)) {
                            zzlkVar.zzd(i9, zze(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zza((zzjk<T>) t8, i9, length)) {
                            zzlkVar.zzd(i9, zzd(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zza((zzjk<T>) t8, i9, length)) {
                            zzlkVar.zza(i9, zzf(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zza((zzjk<T>) t8, i9, length)) {
                            zza(i9, zzkt.zzf(t8, iZzd & 1048575), zzlkVar);
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (zza((zzjk<T>) t8, i9, length)) {
                            zzlkVar.zza(i9, zzkt.zzf(t8, iZzd & 1048575), zza(length));
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zza((zzjk<T>) t8, i9, length)) {
                            zzlkVar.zza(i9, (zzgm) zzkt.zzf(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zza((zzjk<T>) t8, i9, length)) {
                            zzlkVar.zze(i9, zzd(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zza((zzjk<T>) t8, i9, length)) {
                            zzlkVar.zzb(i9, zzd(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zza((zzjk<T>) t8, i9, length)) {
                            zzlkVar.zza(i9, zzd(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zza((zzjk<T>) t8, i9, length)) {
                            zzlkVar.zzb(i9, zze(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zza((zzjk<T>) t8, i9, length)) {
                            zzlkVar.zzf(i9, zzd(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zza((zzjk<T>) t8, i9, length)) {
                            zzlkVar.zze(i9, zze(t8, iZzd & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zza((zzjk<T>) t8, i9, length)) {
                            zzlkVar.zzb(i9, zzkt.zzf(t8, iZzd & 1048575), zza(length));
                            break;
                        } else {
                            break;
                        }
                }
            }
            while (entry2 != null) {
                this.zzr.zza(zzlkVar, entry2);
                entry2 = itZze.hasNext() ? (Map.Entry) itZze.next() : null;
            }
            return;
        }
        if (this.zzj) {
            if (this.zzh) {
                zzho<T> zzhoVarZza2 = this.zzr.zza(t8);
                if (zzhoVarZza2.zza.isEmpty()) {
                    itZzd = null;
                    entry = null;
                } else {
                    itZzd = zzhoVarZza2.zzd();
                    entry = (Map.Entry) itZzd.next();
                }
            }
            int length2 = this.zzc.length;
            for (int i10 = 0; i10 < length2; i10 += 3) {
                int iZzd2 = zzd(i10);
                int i11 = this.zzc[i10];
                while (entry != null && this.zzr.zza(entry) <= i11) {
                    this.zzr.zza(zzlkVar, entry);
                    entry = itZzd.hasNext() ? (Map.Entry) itZzd.next() : null;
                }
                switch ((iZzd2 & 267386880) >>> 20) {
                    case 0:
                        if (zza((zzjk<T>) t8, i10)) {
                            zzlkVar.zza(i11, zzkt.zze(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zza((zzjk<T>) t8, i10)) {
                            zzlkVar.zza(i11, zzkt.zzd(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zza((zzjk<T>) t8, i10)) {
                            zzlkVar.zza(i11, zzkt.zzb(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zza((zzjk<T>) t8, i10)) {
                            zzlkVar.zzc(i11, zzkt.zzb(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zza((zzjk<T>) t8, i10)) {
                            zzlkVar.zzc(i11, zzkt.zza(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zza((zzjk<T>) t8, i10)) {
                            zzlkVar.zzd(i11, zzkt.zzb(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zza((zzjk<T>) t8, i10)) {
                            zzlkVar.zzd(i11, zzkt.zza(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zza((zzjk<T>) t8, i10)) {
                            zzlkVar.zza(i11, zzkt.zzc(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zza((zzjk<T>) t8, i10)) {
                            zza(i11, zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        if (zza((zzjk<T>) t8, i10)) {
                            zzlkVar.zza(i11, zzkt.zzf(t8, iZzd2 & 1048575), zza(i10));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zza((zzjk<T>) t8, i10)) {
                            zzlkVar.zza(i11, (zzgm) zzkt.zzf(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zza((zzjk<T>) t8, i10)) {
                            zzlkVar.zze(i11, zzkt.zza(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zza((zzjk<T>) t8, i10)) {
                            zzlkVar.zzb(i11, zzkt.zza(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zza((zzjk<T>) t8, i10)) {
                            zzlkVar.zza(i11, zzkt.zza(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zza((zzjk<T>) t8, i10)) {
                            zzlkVar.zzb(i11, zzkt.zzb(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zza((zzjk<T>) t8, i10)) {
                            zzlkVar.zzf(i11, zzkt.zza(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zza((zzjk<T>) t8, i10)) {
                            zzlkVar.zze(i11, zzkt.zzb(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zza((zzjk<T>) t8, i10)) {
                            zzlkVar.zzb(i11, zzkt.zzf(t8, iZzd2 & 1048575), zza(i10));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        zzjx.zza(this.zzc[i10], (List<Double>) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, false);
                        break;
                    case 19:
                        zzjx.zzb(this.zzc[i10], (List<Float>) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, false);
                        break;
                    case 20:
                        zzjx.zzc(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, false);
                        break;
                    case 21:
                        zzjx.zzd(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, false);
                        break;
                    case 22:
                        zzjx.zzh(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, false);
                        break;
                    case 23:
                        zzjx.zzf(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, false);
                        break;
                    case 24:
                        zzjx.zzk(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, false);
                        break;
                    case 25:
                        zzjx.zzn(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, false);
                        break;
                    case 26:
                        zzjx.zza(this.zzc[i10], (List<String>) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar);
                        break;
                    case 27:
                        zzjx.zza(this.zzc[i10], (List<?>) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, zza(i10));
                        break;
                    case 28:
                        zzjx.zzb(this.zzc[i10], (List<zzgm>) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar);
                        break;
                    case 29:
                        zzjx.zzi(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, false);
                        break;
                    case 30:
                        zzjx.zzm(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, false);
                        break;
                    case 31:
                        zzjx.zzl(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, false);
                        break;
                    case 32:
                        zzjx.zzg(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, false);
                        break;
                    case 33:
                        zzjx.zzj(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, false);
                        break;
                    case 34:
                        zzjx.zze(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, false);
                        break;
                    case 35:
                        zzjx.zza(this.zzc[i10], (List<Double>) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, true);
                        break;
                    case 36:
                        zzjx.zzb(this.zzc[i10], (List<Float>) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, true);
                        break;
                    case 37:
                        zzjx.zzc(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, true);
                        break;
                    case 38:
                        zzjx.zzd(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, true);
                        break;
                    case 39:
                        zzjx.zzh(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, true);
                        break;
                    case 40:
                        zzjx.zzf(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, true);
                        break;
                    case 41:
                        zzjx.zzk(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, true);
                        break;
                    case 42:
                        zzjx.zzn(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, true);
                        break;
                    case 43:
                        zzjx.zzi(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, true);
                        break;
                    case 44:
                        zzjx.zzm(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, true);
                        break;
                    case 45:
                        zzjx.zzl(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, true);
                        break;
                    case 46:
                        zzjx.zzg(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, true);
                        break;
                    case 47:
                        zzjx.zzj(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, true);
                        break;
                    case 48:
                        zzjx.zze(this.zzc[i10], (List) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, true);
                        break;
                    case 49:
                        zzjx.zzb(this.zzc[i10], (List<?>) zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar, zza(i10));
                        break;
                    case 50:
                        zza(zzlkVar, i11, zzkt.zzf(t8, iZzd2 & 1048575), i10);
                        break;
                    case 51:
                        if (zza((zzjk<T>) t8, i11, i10)) {
                            zzlkVar.zza(i11, zzb(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zza((zzjk<T>) t8, i11, i10)) {
                            zzlkVar.zza(i11, zzc(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zza((zzjk<T>) t8, i11, i10)) {
                            zzlkVar.zza(i11, zze(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zza((zzjk<T>) t8, i11, i10)) {
                            zzlkVar.zzc(i11, zze(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zza((zzjk<T>) t8, i11, i10)) {
                            zzlkVar.zzc(i11, zzd(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zza((zzjk<T>) t8, i11, i10)) {
                            zzlkVar.zzd(i11, zze(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zza((zzjk<T>) t8, i11, i10)) {
                            zzlkVar.zzd(i11, zzd(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zza((zzjk<T>) t8, i11, i10)) {
                            zzlkVar.zza(i11, zzf(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zza((zzjk<T>) t8, i11, i10)) {
                            zza(i11, zzkt.zzf(t8, iZzd2 & 1048575), zzlkVar);
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (zza((zzjk<T>) t8, i11, i10)) {
                            zzlkVar.zza(i11, zzkt.zzf(t8, iZzd2 & 1048575), zza(i10));
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zza((zzjk<T>) t8, i11, i10)) {
                            zzlkVar.zza(i11, (zzgm) zzkt.zzf(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zza((zzjk<T>) t8, i11, i10)) {
                            zzlkVar.zze(i11, zzd(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zza((zzjk<T>) t8, i11, i10)) {
                            zzlkVar.zzb(i11, zzd(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zza((zzjk<T>) t8, i11, i10)) {
                            zzlkVar.zza(i11, zzd(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zza((zzjk<T>) t8, i11, i10)) {
                            zzlkVar.zzb(i11, zze(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zza((zzjk<T>) t8, i11, i10)) {
                            zzlkVar.zzf(i11, zzd(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zza((zzjk<T>) t8, i11, i10)) {
                            zzlkVar.zze(i11, zze(t8, iZzd2 & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zza((zzjk<T>) t8, i11, i10)) {
                            zzlkVar.zzb(i11, zzkt.zzf(t8, iZzd2 & 1048575), zza(i10));
                            break;
                        } else {
                            break;
                        }
                }
            }
            while (entry != null) {
                this.zzr.zza(zzlkVar, entry);
                entry = itZzd.hasNext() ? (Map.Entry) itZzd.next() : null;
            }
            zza(this.zzq, t8, zzlkVar);
            return;
        }
        zzb((zzjk<T>) t8, zzlkVar);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void zzb(T t8, zzlk zzlkVar) {
        Iterator itZzd;
        Map.Entry<?, ?> entry;
        int i9;
        boolean z8;
        if (this.zzh) {
            zzho<T> zzhoVarZza = this.zzr.zza(t8);
            if (zzhoVarZza.zza.isEmpty()) {
                itZzd = null;
                entry = null;
            } else {
                itZzd = zzhoVarZza.zzd();
                entry = (Map.Entry) itZzd.next();
            }
        }
        int length = this.zzc.length;
        Unsafe unsafe = zzb;
        int i10 = 1048575;
        int i11 = 0;
        for (int i12 = 0; i12 < length; i12 += 3) {
            int iZzd = zzd(i12);
            int[] iArr = this.zzc;
            int i13 = iArr[i12];
            int i14 = (iZzd & 267386880) >>> 20;
            if (this.zzj || i14 > 17) {
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
            while (entry != null && this.zzr.zza(entry) <= i13) {
                this.zzr.zza(zzlkVar, entry);
                entry = itZzd.hasNext() ? (Map.Entry) itZzd.next() : null;
            }
            long j9 = iZzd & 1048575;
            switch (i14) {
                case 0:
                    if ((i9 & i11) != 0) {
                        zzlkVar.zza(i13, zzkt.zze(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if ((i9 & i11) != 0) {
                        zzlkVar.zza(i13, zzkt.zzd(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if ((i9 & i11) != 0) {
                        zzlkVar.zza(i13, unsafe.getLong(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if ((i9 & i11) != 0) {
                        zzlkVar.zzc(i13, unsafe.getLong(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if ((i9 & i11) != 0) {
                        zzlkVar.zzc(i13, unsafe.getInt(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if ((i9 & i11) != 0) {
                        zzlkVar.zzd(i13, unsafe.getLong(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if ((i9 & i11) != 0) {
                        zzlkVar.zzd(i13, unsafe.getInt(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if ((i9 & i11) != 0) {
                        zzlkVar.zza(i13, zzkt.zzc(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if ((i9 & i11) != 0) {
                        zza(i13, unsafe.getObject(t8, j9), zzlkVar);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    if ((i9 & i11) != 0) {
                        zzlkVar.zza(i13, unsafe.getObject(t8, j9), zza(i12));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if ((i9 & i11) != 0) {
                        zzlkVar.zza(i13, (zzgm) unsafe.getObject(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if ((i9 & i11) != 0) {
                        zzlkVar.zze(i13, unsafe.getInt(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if ((i9 & i11) != 0) {
                        zzlkVar.zzb(i13, unsafe.getInt(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if ((i9 & i11) != 0) {
                        zzlkVar.zza(i13, unsafe.getInt(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if ((i9 & i11) != 0) {
                        zzlkVar.zzb(i13, unsafe.getLong(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if ((i9 & i11) != 0) {
                        zzlkVar.zzf(i13, unsafe.getInt(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if ((i9 & i11) != 0) {
                        zzlkVar.zze(i13, unsafe.getLong(t8, j9));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if ((i9 & i11) != 0) {
                        zzlkVar.zzb(i13, unsafe.getObject(t8, j9), zza(i12));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    z8 = false;
                    zzjx.zza(this.zzc[i12], (List<Double>) unsafe.getObject(t8, j9), zzlkVar, false);
                    break;
                case 19:
                    z8 = false;
                    zzjx.zzb(this.zzc[i12], (List<Float>) unsafe.getObject(t8, j9), zzlkVar, false);
                    break;
                case 20:
                    z8 = false;
                    zzjx.zzc(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, false);
                    break;
                case 21:
                    z8 = false;
                    zzjx.zzd(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, false);
                    break;
                case 22:
                    z8 = false;
                    zzjx.zzh(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, false);
                    break;
                case 23:
                    z8 = false;
                    zzjx.zzf(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, false);
                    break;
                case 24:
                    z8 = false;
                    zzjx.zzk(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, false);
                    break;
                case 25:
                    z8 = false;
                    zzjx.zzn(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, false);
                    break;
                case 26:
                    zzjx.zza(this.zzc[i12], (List<String>) unsafe.getObject(t8, j9), zzlkVar);
                    break;
                case 27:
                    zzjx.zza(this.zzc[i12], (List<?>) unsafe.getObject(t8, j9), zzlkVar, zza(i12));
                    break;
                case 28:
                    zzjx.zzb(this.zzc[i12], (List<zzgm>) unsafe.getObject(t8, j9), zzlkVar);
                    break;
                case 29:
                    z8 = false;
                    zzjx.zzi(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, false);
                    break;
                case 30:
                    z8 = false;
                    zzjx.zzm(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, false);
                    break;
                case 31:
                    z8 = false;
                    zzjx.zzl(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, false);
                    break;
                case 32:
                    z8 = false;
                    zzjx.zzg(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, false);
                    break;
                case 33:
                    z8 = false;
                    zzjx.zzj(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, false);
                    break;
                case 34:
                    z8 = false;
                    zzjx.zze(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, false);
                    break;
                case 35:
                    zzjx.zza(this.zzc[i12], (List<Double>) unsafe.getObject(t8, j9), zzlkVar, true);
                    break;
                case 36:
                    zzjx.zzb(this.zzc[i12], (List<Float>) unsafe.getObject(t8, j9), zzlkVar, true);
                    break;
                case 37:
                    zzjx.zzc(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, true);
                    break;
                case 38:
                    zzjx.zzd(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, true);
                    break;
                case 39:
                    zzjx.zzh(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, true);
                    break;
                case 40:
                    zzjx.zzf(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, true);
                    break;
                case 41:
                    zzjx.zzk(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, true);
                    break;
                case 42:
                    zzjx.zzn(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, true);
                    break;
                case 43:
                    zzjx.zzi(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, true);
                    break;
                case 44:
                    zzjx.zzm(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, true);
                    break;
                case 45:
                    zzjx.zzl(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, true);
                    break;
                case 46:
                    zzjx.zzg(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, true);
                    break;
                case 47:
                    zzjx.zzj(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, true);
                    break;
                case 48:
                    zzjx.zze(this.zzc[i12], (List) unsafe.getObject(t8, j9), zzlkVar, true);
                    break;
                case 49:
                    zzjx.zzb(this.zzc[i12], (List<?>) unsafe.getObject(t8, j9), zzlkVar, zza(i12));
                    break;
                case 50:
                    zza(zzlkVar, i13, unsafe.getObject(t8, j9), i12);
                    break;
                case 51:
                    if (zza((zzjk<T>) t8, i13, i12)) {
                        zzlkVar.zza(i13, zzb(t8, j9));
                    }
                    break;
                case 52:
                    if (zza((zzjk<T>) t8, i13, i12)) {
                        zzlkVar.zza(i13, zzc(t8, j9));
                    }
                    break;
                case 53:
                    if (zza((zzjk<T>) t8, i13, i12)) {
                        zzlkVar.zza(i13, zze(t8, j9));
                    }
                    break;
                case 54:
                    if (zza((zzjk<T>) t8, i13, i12)) {
                        zzlkVar.zzc(i13, zze(t8, j9));
                    }
                    break;
                case 55:
                    if (zza((zzjk<T>) t8, i13, i12)) {
                        zzlkVar.zzc(i13, zzd(t8, j9));
                    }
                    break;
                case 56:
                    if (zza((zzjk<T>) t8, i13, i12)) {
                        zzlkVar.zzd(i13, zze(t8, j9));
                    }
                    break;
                case 57:
                    if (zza((zzjk<T>) t8, i13, i12)) {
                        zzlkVar.zzd(i13, zzd(t8, j9));
                    }
                    break;
                case 58:
                    if (zza((zzjk<T>) t8, i13, i12)) {
                        zzlkVar.zza(i13, zzf(t8, j9));
                    }
                    break;
                case 59:
                    if (zza((zzjk<T>) t8, i13, i12)) {
                        zza(i13, unsafe.getObject(t8, j9), zzlkVar);
                    }
                    break;
                case 60:
                    if (zza((zzjk<T>) t8, i13, i12)) {
                        zzlkVar.zza(i13, unsafe.getObject(t8, j9), zza(i12));
                    }
                    break;
                case 61:
                    if (zza((zzjk<T>) t8, i13, i12)) {
                        zzlkVar.zza(i13, (zzgm) unsafe.getObject(t8, j9));
                    }
                    break;
                case 62:
                    if (zza((zzjk<T>) t8, i13, i12)) {
                        zzlkVar.zze(i13, zzd(t8, j9));
                    }
                    break;
                case 63:
                    if (zza((zzjk<T>) t8, i13, i12)) {
                        zzlkVar.zzb(i13, zzd(t8, j9));
                    }
                    break;
                case 64:
                    if (zza((zzjk<T>) t8, i13, i12)) {
                        zzlkVar.zza(i13, zzd(t8, j9));
                    }
                    break;
                case 65:
                    if (zza((zzjk<T>) t8, i13, i12)) {
                        zzlkVar.zzb(i13, zze(t8, j9));
                    }
                    break;
                case 66:
                    if (zza((zzjk<T>) t8, i13, i12)) {
                        zzlkVar.zzf(i13, zzd(t8, j9));
                    }
                    break;
                case 67:
                    if (zza((zzjk<T>) t8, i13, i12)) {
                        zzlkVar.zze(i13, zze(t8, j9));
                    }
                    break;
                case 68:
                    if (zza((zzjk<T>) t8, i13, i12)) {
                        zzlkVar.zzb(i13, unsafe.getObject(t8, j9), zza(i12));
                    }
                    break;
            }
        }
        while (entry != null) {
            this.zzr.zza(zzlkVar, entry);
            entry = itZzd.hasNext() ? (Map.Entry) itZzd.next() : null;
        }
        zza(this.zzq, t8, zzlkVar);
    }

    private final Object zzb(int i9) {
        return this.zzd[(i9 / 3) << 1];
    }

    private static <T> double zzb(T t8, long j9) {
        return ((Double) zzkt.zzf(t8, j9)).doubleValue();
    }

    private final void zzb(T t8, int i9) {
        int iZze = zze(i9);
        long j9 = 1048575 & iZze;
        if (j9 == 1048575) {
            return;
        }
        zzkt.zza((Object) t8, j9, (1 << (iZze >>> 20)) | zzkt.zza(t8, j9));
    }

    private final void zzb(T t8, int i9, int i10) {
        zzkt.zza((Object) t8, zze(i10) & 1048575, i9);
    }

    private final int zzb(int i9, int i10) {
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

    private final <K, V> void zza(zzlk zzlkVar, int i9, Object obj, int i10) {
        if (obj != null) {
            zzlkVar.zza(i9, this.zzs.zzf(zzb(i10)), this.zzs.zzb(obj));
        }
    }

    private static <UT, UB> void zza(zzkn<UT, UB> zzknVar, T t8, zzlk zzlkVar) {
        zzknVar.zza((zzkn<UT, UB>) zzknVar.zzb(t8), zzlkVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:165:0x05ce A[LOOP:5: B:163:0x05ca->B:165:0x05ce, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:167:0x05db  */
    @Override // com.google.android.gms.internal.measurement.zzjv
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(T t8, zzjw zzjwVar, zzhi zzhiVar) {
        int i9;
        int iZza;
        int iZzg;
        zzhiVar.getClass();
        zzkn zzknVar = this.zzq;
        zzhk<?> zzhkVar = this.zzr;
        Object objZzb = null;
        Object objZza = null;
        while (true) {
            try {
                iZza = zzjwVar.zza();
                iZzg = zzg(iZza);
            } catch (Throwable th) {
                while (i9 < this.zzn) {
                }
                if (objZza != null) {
                }
                throw th;
            }
            if (iZzg >= 0) {
                int iZzd = zzd(iZzg);
                switch ((267386880 & iZzd) >>> 20) {
                    case 0:
                        zzkt.zza(t8, iZzd & 1048575, zzjwVar.zzd());
                        zzb((zzjk<T>) t8, iZzg);
                        continue;
                    case 1:
                        zzkt.zza((Object) t8, iZzd & 1048575, zzjwVar.zze());
                        zzb((zzjk<T>) t8, iZzg);
                        continue;
                    case 2:
                        zzkt.zza((Object) t8, iZzd & 1048575, zzjwVar.zzg());
                        zzb((zzjk<T>) t8, iZzg);
                        continue;
                    case 3:
                        zzkt.zza((Object) t8, iZzd & 1048575, zzjwVar.zzf());
                        zzb((zzjk<T>) t8, iZzg);
                        continue;
                    case 4:
                        zzkt.zza((Object) t8, iZzd & 1048575, zzjwVar.zzh());
                        zzb((zzjk<T>) t8, iZzg);
                        continue;
                    case 5:
                        zzkt.zza((Object) t8, iZzd & 1048575, zzjwVar.zzi());
                        zzb((zzjk<T>) t8, iZzg);
                        continue;
                    case 6:
                        zzkt.zza((Object) t8, iZzd & 1048575, zzjwVar.zzj());
                        zzb((zzjk<T>) t8, iZzg);
                        continue;
                    case 7:
                        zzkt.zza(t8, iZzd & 1048575, zzjwVar.zzk());
                        zzb((zzjk<T>) t8, iZzg);
                        continue;
                    case 8:
                        zza(t8, iZzd, zzjwVar);
                        zzb((zzjk<T>) t8, iZzg);
                        continue;
                    case 9:
                        if (zza((zzjk<T>) t8, iZzg)) {
                            long j9 = iZzd & 1048575;
                            zzkt.zza(t8, j9, zzhx.zza(zzkt.zzf(t8, j9), zzjwVar.zza(zza(iZzg), zzhiVar)));
                            break;
                        } else {
                            zzkt.zza(t8, iZzd & 1048575, zzjwVar.zza(zza(iZzg), zzhiVar));
                            zzb((zzjk<T>) t8, iZzg);
                            continue;
                        }
                    case 10:
                        zzkt.zza(t8, iZzd & 1048575, zzjwVar.zzn());
                        zzb((zzjk<T>) t8, iZzg);
                        continue;
                    case 11:
                        zzkt.zza((Object) t8, iZzd & 1048575, zzjwVar.zzo());
                        zzb((zzjk<T>) t8, iZzg);
                        continue;
                    case 12:
                        int iZzp = zzjwVar.zzp();
                        zzic zzicVarZzc = zzc(iZzg);
                        if (zzicVarZzc != null && !zzicVarZzc.zza(iZzp)) {
                            objZza = zzjx.zza(iZza, iZzp, objZza, (zzkn<UT, Object>) zzknVar);
                            break;
                        } else {
                            zzkt.zza((Object) t8, iZzd & 1048575, iZzp);
                            zzb((zzjk<T>) t8, iZzg);
                            continue;
                        }
                        break;
                    case 13:
                        zzkt.zza((Object) t8, iZzd & 1048575, zzjwVar.zzq());
                        zzb((zzjk<T>) t8, iZzg);
                        continue;
                    case 14:
                        zzkt.zza((Object) t8, iZzd & 1048575, zzjwVar.zzr());
                        zzb((zzjk<T>) t8, iZzg);
                        continue;
                    case 15:
                        zzkt.zza((Object) t8, iZzd & 1048575, zzjwVar.zzs());
                        zzb((zzjk<T>) t8, iZzg);
                        continue;
                    case 16:
                        zzkt.zza((Object) t8, iZzd & 1048575, zzjwVar.zzt());
                        zzb((zzjk<T>) t8, iZzg);
                        continue;
                    case 17:
                        if (zza((zzjk<T>) t8, iZzg)) {
                            long j10 = iZzd & 1048575;
                            zzkt.zza(t8, j10, zzhx.zza(zzkt.zzf(t8, j10), zzjwVar.zzb(zza(iZzg), zzhiVar)));
                            break;
                        } else {
                            zzkt.zza(t8, iZzd & 1048575, zzjwVar.zzb(zza(iZzg), zzhiVar));
                            zzb((zzjk<T>) t8, iZzg);
                            continue;
                        }
                    case 18:
                        zzjwVar.zza(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 19:
                        zzjwVar.zzb(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 20:
                        zzjwVar.zzd(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 21:
                        zzjwVar.zzc(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 22:
                        zzjwVar.zze(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 23:
                        zzjwVar.zzf(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 24:
                        zzjwVar.zzg(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 25:
                        zzjwVar.zzh(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 26:
                        if (zzf(iZzd)) {
                            zzjwVar.zzj(this.zzp.zza(t8, iZzd & 1048575));
                            break;
                        } else {
                            zzjwVar.zzi(this.zzp.zza(t8, iZzd & 1048575));
                            continue;
                        }
                    case 27:
                        zzjwVar.zza(this.zzp.zza(t8, iZzd & 1048575), zza(iZzg), zzhiVar);
                        continue;
                    case 28:
                        zzjwVar.zzk(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 29:
                        zzjwVar.zzl(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 30:
                        List<Integer> listZza = this.zzp.zza(t8, iZzd & 1048575);
                        zzjwVar.zzm(listZza);
                        objZza = zzjx.zza(iZza, listZza, zzc(iZzg), objZza, zzknVar);
                        continue;
                    case 31:
                        zzjwVar.zzn(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 32:
                        zzjwVar.zzo(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 33:
                        zzjwVar.zzp(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 34:
                        zzjwVar.zzq(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 35:
                        zzjwVar.zza(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 36:
                        zzjwVar.zzb(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 37:
                        zzjwVar.zzd(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 38:
                        zzjwVar.zzc(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 39:
                        zzjwVar.zze(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 40:
                        zzjwVar.zzf(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 41:
                        zzjwVar.zzg(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 42:
                        zzjwVar.zzh(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 43:
                        zzjwVar.zzl(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 44:
                        List<Integer> listZza2 = this.zzp.zza(t8, iZzd & 1048575);
                        zzjwVar.zzm(listZza2);
                        objZza = zzjx.zza(iZza, listZza2, zzc(iZzg), objZza, zzknVar);
                        continue;
                    case 45:
                        zzjwVar.zzn(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 46:
                        zzjwVar.zzo(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 47:
                        zzjwVar.zzp(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 48:
                        zzjwVar.zzq(this.zzp.zza(t8, iZzd & 1048575));
                        continue;
                    case 49:
                        zzjwVar.zzb(this.zzp.zza(t8, iZzd & 1048575), zza(iZzg), zzhiVar);
                        continue;
                    case 50:
                        Object objZzb2 = zzb(iZzg);
                        long jZzd = zzd(iZzg) & 1048575;
                        Object objZzf = zzkt.zzf(t8, jZzd);
                        if (objZzf == null) {
                            objZzf = this.zzs.zze(objZzb2);
                            zzkt.zza(t8, jZzd, objZzf);
                        } else if (this.zzs.zzc(objZzf)) {
                            Object objZze = this.zzs.zze(objZzb2);
                            this.zzs.zza(objZze, objZzf);
                            zzkt.zza(t8, jZzd, objZze);
                            objZzf = objZze;
                        }
                        zzjwVar.zza(this.zzs.zza(objZzf), this.zzs.zzf(objZzb2), zzhiVar);
                        continue;
                    case 51:
                        zzkt.zza(t8, iZzd & 1048575, Double.valueOf(zzjwVar.zzd()));
                        zzb((zzjk<T>) t8, iZza, iZzg);
                        continue;
                    case 52:
                        zzkt.zza(t8, iZzd & 1048575, Float.valueOf(zzjwVar.zze()));
                        zzb((zzjk<T>) t8, iZza, iZzg);
                        continue;
                    case 53:
                        zzkt.zza(t8, iZzd & 1048575, Long.valueOf(zzjwVar.zzg()));
                        zzb((zzjk<T>) t8, iZza, iZzg);
                        continue;
                    case 54:
                        zzkt.zza(t8, iZzd & 1048575, Long.valueOf(zzjwVar.zzf()));
                        zzb((zzjk<T>) t8, iZza, iZzg);
                        continue;
                    case 55:
                        zzkt.zza(t8, iZzd & 1048575, Integer.valueOf(zzjwVar.zzh()));
                        zzb((zzjk<T>) t8, iZza, iZzg);
                        continue;
                    case 56:
                        zzkt.zza(t8, iZzd & 1048575, Long.valueOf(zzjwVar.zzi()));
                        zzb((zzjk<T>) t8, iZza, iZzg);
                        continue;
                    case 57:
                        zzkt.zza(t8, iZzd & 1048575, Integer.valueOf(zzjwVar.zzj()));
                        zzb((zzjk<T>) t8, iZza, iZzg);
                        continue;
                    case 58:
                        zzkt.zza(t8, iZzd & 1048575, Boolean.valueOf(zzjwVar.zzk()));
                        zzb((zzjk<T>) t8, iZza, iZzg);
                        continue;
                    case 59:
                        zza(t8, iZzd, zzjwVar);
                        zzb((zzjk<T>) t8, iZza, iZzg);
                        continue;
                    case 60:
                        if (zza((zzjk<T>) t8, iZza, iZzg)) {
                            long j11 = iZzd & 1048575;
                            zzkt.zza(t8, j11, zzhx.zza(zzkt.zzf(t8, j11), zzjwVar.zza(zza(iZzg), zzhiVar)));
                        } else {
                            zzkt.zza(t8, iZzd & 1048575, zzjwVar.zza(zza(iZzg), zzhiVar));
                            zzb((zzjk<T>) t8, iZzg);
                        }
                        zzb((zzjk<T>) t8, iZza, iZzg);
                        continue;
                    case 61:
                        zzkt.zza(t8, iZzd & 1048575, zzjwVar.zzn());
                        zzb((zzjk<T>) t8, iZza, iZzg);
                        continue;
                    case 62:
                        zzkt.zza(t8, iZzd & 1048575, Integer.valueOf(zzjwVar.zzo()));
                        zzb((zzjk<T>) t8, iZza, iZzg);
                        continue;
                    case 63:
                        int iZzp2 = zzjwVar.zzp();
                        zzic zzicVarZzc2 = zzc(iZzg);
                        if (zzicVarZzc2 != null && !zzicVarZzc2.zza(iZzp2)) {
                            objZza = zzjx.zza(iZza, iZzp2, objZza, (zzkn<UT, Object>) zzknVar);
                            break;
                        } else {
                            zzkt.zza(t8, iZzd & 1048575, Integer.valueOf(iZzp2));
                            zzb((zzjk<T>) t8, iZza, iZzg);
                            continue;
                        }
                    case 64:
                        zzkt.zza(t8, iZzd & 1048575, Integer.valueOf(zzjwVar.zzq()));
                        zzb((zzjk<T>) t8, iZza, iZzg);
                        continue;
                    case 65:
                        zzkt.zza(t8, iZzd & 1048575, Long.valueOf(zzjwVar.zzr()));
                        zzb((zzjk<T>) t8, iZza, iZzg);
                        continue;
                    case 66:
                        zzkt.zza(t8, iZzd & 1048575, Integer.valueOf(zzjwVar.zzs()));
                        zzb((zzjk<T>) t8, iZza, iZzg);
                        continue;
                    case 67:
                        zzkt.zza(t8, iZzd & 1048575, Long.valueOf(zzjwVar.zzt()));
                        zzb((zzjk<T>) t8, iZza, iZzg);
                        continue;
                    case 68:
                        zzkt.zza(t8, iZzd & 1048575, zzjwVar.zzb(zza(iZzg), zzhiVar));
                        zzb((zzjk<T>) t8, iZza, iZzg);
                        continue;
                    default:
                        if (objZza == null) {
                            try {
                                objZza = zzknVar.zza();
                            } catch (zzif unused) {
                                zzknVar.zza(zzjwVar);
                                if (objZza == null) {
                                    objZza = zzknVar.zzc(t8);
                                }
                                if (!zzknVar.zza((zzkn) objZza, zzjwVar)) {
                                    for (int i10 = this.zzm; i10 < this.zzn; i10++) {
                                        objZza = zza((Object) t8, this.zzl[i10], (int) objZza, (zzkn<UT, int>) zzknVar);
                                    }
                                    if (objZza != null) {
                                        zzknVar.zzb((Object) t8, (T) objZza);
                                        return;
                                    }
                                    return;
                                }
                                break;
                            }
                        }
                        if (!zzknVar.zza((zzkn) objZza, zzjwVar)) {
                            for (int i11 = this.zzm; i11 < this.zzn; i11++) {
                                objZza = zza((Object) t8, this.zzl[i11], (int) objZza, (zzkn<UT, int>) zzknVar);
                            }
                            if (objZza != null) {
                                zzknVar.zzb((Object) t8, (T) objZza);
                                return;
                            }
                            return;
                        }
                        break;
                }
                for (i9 = this.zzm; i9 < this.zzn; i9++) {
                    objZza = zza((Object) t8, this.zzl[i9], (int) objZza, (zzkn<UT, int>) zzknVar);
                }
                if (objZza != null) {
                    zzknVar.zzb((Object) t8, (T) objZza);
                }
                throw th;
            }
            if (iZza == Integer.MAX_VALUE) {
                for (int i12 = this.zzm; i12 < this.zzn; i12++) {
                    objZza = zza((Object) t8, this.zzl[i12], (int) objZza, (zzkn<UT, int>) zzknVar);
                }
                if (objZza != null) {
                    zzknVar.zzb((Object) t8, (T) objZza);
                    return;
                }
                return;
            }
            Object objZza2 = !this.zzh ? null : zzhkVar.zza(zzhiVar, this.zzg, iZza);
            if (objZza2 != null) {
                if (objZzb == null) {
                    objZzb = zzhkVar.zzb(t8);
                }
                zzho<T> zzhoVar = objZzb;
                objZza = zzhkVar.zza(zzjwVar, objZza2, zzhiVar, zzhoVar, objZza, zzknVar);
                objZzb = zzhoVar;
            } else {
                zzknVar.zza(zzjwVar);
                if (objZza == null) {
                    objZza = zzknVar.zzc(t8);
                }
                if (!zzknVar.zza((zzkn) objZza, zzjwVar)) {
                    for (int i13 = this.zzm; i13 < this.zzn; i13++) {
                        objZza = zza((Object) t8, this.zzl[i13], (int) objZza, (zzkn<UT, int>) zzknVar);
                    }
                    if (objZza != null) {
                        zzknVar.zzb((Object) t8, (T) objZza);
                        return;
                    }
                    return;
                }
            }
        }
    }

    private static int zza(byte[] bArr, int i9, int i10, zzle zzleVar, Class<?> cls, zzgl zzglVar) {
        switch (zzjj.zza[zzleVar.ordinal()]) {
            case 1:
                int iZzb = zzgi.zzb(bArr, i9, zzglVar);
                zzglVar.zzc = Boolean.valueOf(zzglVar.zzb != 0);
                return iZzb;
            case 2:
                return zzgi.zze(bArr, i9, zzglVar);
            case 3:
                zzglVar.zzc = Double.valueOf(zzgi.zzc(bArr, i9));
                return i9 + 8;
            case 4:
            case 5:
                zzglVar.zzc = Integer.valueOf(zzgi.zza(bArr, i9));
                return i9 + 4;
            case 6:
            case 7:
                zzglVar.zzc = Long.valueOf(zzgi.zzb(bArr, i9));
                return i9 + 8;
            case 8:
                zzglVar.zzc = Float.valueOf(zzgi.zzd(bArr, i9));
                return i9 + 4;
            case 9:
            case 10:
            case 11:
                int iZza = zzgi.zza(bArr, i9, zzglVar);
                zzglVar.zzc = Integer.valueOf(zzglVar.zza);
                return iZza;
            case 12:
            case 13:
                int iZzb2 = zzgi.zzb(bArr, i9, zzglVar);
                zzglVar.zzc = Long.valueOf(zzglVar.zzb);
                return iZzb2;
            case 14:
                return zzgi.zza(zzjr.zza().zza((Class) cls), bArr, i9, i10, zzglVar);
            case 15:
                int iZza2 = zzgi.zza(bArr, i9, zzglVar);
                zzglVar.zzc = Integer.valueOf(zzgy.zze(zzglVar.zza));
                return iZza2;
            case 16:
                int iZzb3 = zzgi.zzb(bArr, i9, zzglVar);
                zzglVar.zzc = Long.valueOf(zzgy.zza(zzglVar.zzb));
                return iZzb3;
            case 17:
                return zzgi.zzd(bArr, i9, zzglVar);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    private final int zza(T t8, byte[] bArr, int i9, int i10, int i11, int i12, int i13, int i14, long j9, int i15, long j10, zzgl zzglVar) throws zzig {
        int iZza;
        int iZza2 = i9;
        Unsafe unsafe = zzb;
        zzid zzidVarZza = (zzid) unsafe.getObject(t8, j10);
        if (!zzidVarZza.zza()) {
            int size = zzidVarZza.size();
            zzidVarZza = zzidVarZza.zza(size == 0 ? 10 : size << 1);
            unsafe.putObject(t8, j10, zzidVarZza);
        }
        switch (i15) {
            case 18:
            case 35:
                if (i13 == 2) {
                    zzhg zzhgVar = (zzhg) zzidVarZza;
                    int iZza3 = zzgi.zza(bArr, iZza2, zzglVar);
                    int i16 = zzglVar.zza + iZza3;
                    while (iZza3 < i16) {
                        zzhgVar.zza(zzgi.zzc(bArr, iZza3));
                        iZza3 += 8;
                    }
                    if (iZza3 == i16) {
                        return iZza3;
                    }
                    throw zzig.zza();
                }
                if (i13 == 1) {
                    zzhg zzhgVar2 = (zzhg) zzidVarZza;
                    zzhgVar2.zza(zzgi.zzc(bArr, i9));
                    while (true) {
                        int i17 = iZza2 + 8;
                        if (i17 >= i10) {
                            return i17;
                        }
                        iZza2 = zzgi.zza(bArr, i17, zzglVar);
                        if (i11 != zzglVar.zza) {
                            return i17;
                        }
                        zzhgVar2.zza(zzgi.zzc(bArr, iZza2));
                    }
                }
                return iZza2;
            case 19:
            case 36:
                if (i13 == 2) {
                    zzhu zzhuVar = (zzhu) zzidVarZza;
                    int iZza4 = zzgi.zza(bArr, iZza2, zzglVar);
                    int i18 = zzglVar.zza + iZza4;
                    while (iZza4 < i18) {
                        zzhuVar.zza(zzgi.zzd(bArr, iZza4));
                        iZza4 += 4;
                    }
                    if (iZza4 == i18) {
                        return iZza4;
                    }
                    throw zzig.zza();
                }
                if (i13 == 5) {
                    zzhu zzhuVar2 = (zzhu) zzidVarZza;
                    zzhuVar2.zza(zzgi.zzd(bArr, i9));
                    while (true) {
                        int i19 = iZza2 + 4;
                        if (i19 >= i10) {
                            return i19;
                        }
                        iZza2 = zzgi.zza(bArr, i19, zzglVar);
                        if (i11 != zzglVar.zza) {
                            return i19;
                        }
                        zzhuVar2.zza(zzgi.zzd(bArr, iZza2));
                    }
                }
                return iZza2;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i13 == 2) {
                    zziu zziuVar = (zziu) zzidVarZza;
                    int iZza5 = zzgi.zza(bArr, iZza2, zzglVar);
                    int i20 = zzglVar.zza + iZza5;
                    while (iZza5 < i20) {
                        iZza5 = zzgi.zzb(bArr, iZza5, zzglVar);
                        zziuVar.zza(zzglVar.zzb);
                    }
                    if (iZza5 == i20) {
                        return iZza5;
                    }
                    throw zzig.zza();
                }
                if (i13 == 0) {
                    zziu zziuVar2 = (zziu) zzidVarZza;
                    int iZzb = zzgi.zzb(bArr, iZza2, zzglVar);
                    zziuVar2.zza(zzglVar.zzb);
                    while (iZzb < i10) {
                        int iZza6 = zzgi.zza(bArr, iZzb, zzglVar);
                        if (i11 != zzglVar.zza) {
                            return iZzb;
                        }
                        iZzb = zzgi.zzb(bArr, iZza6, zzglVar);
                        zziuVar2.zza(zzglVar.zzb);
                    }
                    return iZzb;
                }
                return iZza2;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i13 == 2) {
                    return zzgi.zza(bArr, iZza2, (zzid<?>) zzidVarZza, zzglVar);
                }
                if (i13 == 0) {
                    return zzgi.zza(i11, bArr, i9, i10, (zzid<?>) zzidVarZza, zzglVar);
                }
                return iZza2;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i13 == 2) {
                    zziu zziuVar3 = (zziu) zzidVarZza;
                    int iZza7 = zzgi.zza(bArr, iZza2, zzglVar);
                    int i21 = zzglVar.zza + iZza7;
                    while (iZza7 < i21) {
                        zziuVar3.zza(zzgi.zzb(bArr, iZza7));
                        iZza7 += 8;
                    }
                    if (iZza7 == i21) {
                        return iZza7;
                    }
                    throw zzig.zza();
                }
                if (i13 == 1) {
                    zziu zziuVar4 = (zziu) zzidVarZza;
                    zziuVar4.zza(zzgi.zzb(bArr, i9));
                    while (true) {
                        int i22 = iZza2 + 8;
                        if (i22 >= i10) {
                            return i22;
                        }
                        iZza2 = zzgi.zza(bArr, i22, zzglVar);
                        if (i11 != zzglVar.zza) {
                            return i22;
                        }
                        zziuVar4.zza(zzgi.zzb(bArr, iZza2));
                    }
                }
                return iZza2;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i13 == 2) {
                    zzhy zzhyVar = (zzhy) zzidVarZza;
                    int iZza8 = zzgi.zza(bArr, iZza2, zzglVar);
                    int i23 = zzglVar.zza + iZza8;
                    while (iZza8 < i23) {
                        zzhyVar.zzd(zzgi.zza(bArr, iZza8));
                        iZza8 += 4;
                    }
                    if (iZza8 == i23) {
                        return iZza8;
                    }
                    throw zzig.zza();
                }
                if (i13 == 5) {
                    zzhy zzhyVar2 = (zzhy) zzidVarZza;
                    zzhyVar2.zzd(zzgi.zza(bArr, i9));
                    while (true) {
                        int i24 = iZza2 + 4;
                        if (i24 >= i10) {
                            return i24;
                        }
                        iZza2 = zzgi.zza(bArr, i24, zzglVar);
                        if (i11 != zzglVar.zza) {
                            return i24;
                        }
                        zzhyVar2.zzd(zzgi.zza(bArr, iZza2));
                    }
                }
                return iZza2;
            case 25:
            case 42:
                if (i13 == 2) {
                    zzgk zzgkVar = (zzgk) zzidVarZza;
                    iZza = zzgi.zza(bArr, iZza2, zzglVar);
                    int i25 = zzglVar.zza + iZza;
                    while (iZza < i25) {
                        iZza = zzgi.zzb(bArr, iZza, zzglVar);
                        zzgkVar.zza(zzglVar.zzb != 0);
                    }
                    if (iZza != i25) {
                        throw zzig.zza();
                    }
                    return iZza;
                }
                if (i13 == 0) {
                    zzgk zzgkVar2 = (zzgk) zzidVarZza;
                    iZza2 = zzgi.zzb(bArr, iZza2, zzglVar);
                    zzgkVar2.zza(zzglVar.zzb != 0);
                    while (iZza2 < i10) {
                        int iZza9 = zzgi.zza(bArr, iZza2, zzglVar);
                        if (i11 == zzglVar.zza) {
                            iZza2 = zzgi.zzb(bArr, iZza9, zzglVar);
                            zzgkVar2.zza(zzglVar.zzb != 0);
                        }
                    }
                }
                return iZza2;
            case 26:
                if (i13 == 2) {
                    if ((j9 & 536870912) == 0) {
                        int iZza10 = zzgi.zza(bArr, iZza2, zzglVar);
                        int i26 = zzglVar.zza;
                        if (i26 < 0) {
                            throw zzig.zzb();
                        }
                        if (i26 == 0) {
                            zzidVarZza.add("");
                        } else {
                            zzidVarZza.add(new String(bArr, iZza10, i26, zzhx.zza));
                            iZza10 += i26;
                        }
                        while (iZza10 < i10) {
                            int iZza11 = zzgi.zza(bArr, iZza10, zzglVar);
                            if (i11 != zzglVar.zza) {
                                return iZza10;
                            }
                            iZza10 = zzgi.zza(bArr, iZza11, zzglVar);
                            int i27 = zzglVar.zza;
                            if (i27 < 0) {
                                throw zzig.zzb();
                            }
                            if (i27 == 0) {
                                zzidVarZza.add("");
                            } else {
                                zzidVarZza.add(new String(bArr, iZza10, i27, zzhx.zza));
                                iZza10 += i27;
                            }
                        }
                        return iZza10;
                    }
                    int iZza12 = zzgi.zza(bArr, iZza2, zzglVar);
                    int i28 = zzglVar.zza;
                    if (i28 < 0) {
                        throw zzig.zzb();
                    }
                    if (i28 == 0) {
                        zzidVarZza.add("");
                    } else {
                        int i29 = iZza12 + i28;
                        if (zzkw.zza(bArr, iZza12, i29)) {
                            zzidVarZza.add(new String(bArr, iZza12, i28, zzhx.zza));
                            iZza12 = i29;
                        } else {
                            throw zzig.zzh();
                        }
                    }
                    while (iZza12 < i10) {
                        int iZza13 = zzgi.zza(bArr, iZza12, zzglVar);
                        if (i11 != zzglVar.zza) {
                            return iZza12;
                        }
                        iZza12 = zzgi.zza(bArr, iZza13, zzglVar);
                        int i30 = zzglVar.zza;
                        if (i30 < 0) {
                            throw zzig.zzb();
                        }
                        if (i30 == 0) {
                            zzidVarZza.add("");
                        } else {
                            int i31 = iZza12 + i30;
                            if (zzkw.zza(bArr, iZza12, i31)) {
                                zzidVarZza.add(new String(bArr, iZza12, i30, zzhx.zza));
                                iZza12 = i31;
                            } else {
                                throw zzig.zzh();
                            }
                        }
                    }
                    return iZza12;
                }
                return iZza2;
            case 27:
                if (i13 == 2) {
                    return zzgi.zza(zza(i14), i11, bArr, i9, i10, zzidVarZza, zzglVar);
                }
                return iZza2;
            case 28:
                if (i13 == 2) {
                    int iZza14 = zzgi.zza(bArr, iZza2, zzglVar);
                    int i32 = zzglVar.zza;
                    if (i32 >= 0) {
                        if (i32 > bArr.length - iZza14) {
                            throw zzig.zza();
                        }
                        if (i32 == 0) {
                            zzidVarZza.add(zzgm.zza);
                        } else {
                            zzidVarZza.add(zzgm.zza(bArr, iZza14, i32));
                            iZza14 += i32;
                        }
                        while (iZza14 < i10) {
                            int iZza15 = zzgi.zza(bArr, iZza14, zzglVar);
                            if (i11 != zzglVar.zza) {
                                return iZza14;
                            }
                            iZza14 = zzgi.zza(bArr, iZza15, zzglVar);
                            int i33 = zzglVar.zza;
                            if (i33 >= 0) {
                                if (i33 > bArr.length - iZza14) {
                                    throw zzig.zza();
                                }
                                if (i33 == 0) {
                                    zzidVarZza.add(zzgm.zza);
                                } else {
                                    zzidVarZza.add(zzgm.zza(bArr, iZza14, i33));
                                    iZza14 += i33;
                                }
                            } else {
                                throw zzig.zzb();
                            }
                        }
                        return iZza14;
                    }
                    throw zzig.zzb();
                }
                return iZza2;
            case 30:
            case 44:
                if (i13 != 2) {
                    if (i13 == 0) {
                        iZza = zzgi.zza(i11, bArr, i9, i10, (zzid<?>) zzidVarZza, zzglVar);
                    }
                    return iZza2;
                }
                iZza = zzgi.zza(bArr, iZza2, (zzid<?>) zzidVarZza, zzglVar);
                zzhv zzhvVar = (zzhv) t8;
                zzkq zzkqVar = zzhvVar.zzb;
                if (zzkqVar == zzkq.zza()) {
                    zzkqVar = null;
                }
                zzkq zzkqVar2 = (zzkq) zzjx.zza(i12, zzidVarZza, zzc(i14), zzkqVar, this.zzq);
                if (zzkqVar2 != null) {
                    zzhvVar.zzb = zzkqVar2;
                }
                return iZza;
            case 33:
            case 47:
                if (i13 == 2) {
                    zzhy zzhyVar3 = (zzhy) zzidVarZza;
                    int iZza16 = zzgi.zza(bArr, iZza2, zzglVar);
                    int i34 = zzglVar.zza + iZza16;
                    while (iZza16 < i34) {
                        iZza16 = zzgi.zza(bArr, iZza16, zzglVar);
                        zzhyVar3.zzd(zzgy.zze(zzglVar.zza));
                    }
                    if (iZza16 == i34) {
                        return iZza16;
                    }
                    throw zzig.zza();
                }
                if (i13 == 0) {
                    zzhy zzhyVar4 = (zzhy) zzidVarZza;
                    int iZza17 = zzgi.zza(bArr, iZza2, zzglVar);
                    zzhyVar4.zzd(zzgy.zze(zzglVar.zza));
                    while (iZza17 < i10) {
                        int iZza18 = zzgi.zza(bArr, iZza17, zzglVar);
                        if (i11 != zzglVar.zza) {
                            return iZza17;
                        }
                        iZza17 = zzgi.zza(bArr, iZza18, zzglVar);
                        zzhyVar4.zzd(zzgy.zze(zzglVar.zza));
                    }
                    return iZza17;
                }
                return iZza2;
            case 34:
            case 48:
                if (i13 == 2) {
                    zziu zziuVar5 = (zziu) zzidVarZza;
                    int iZza19 = zzgi.zza(bArr, iZza2, zzglVar);
                    int i35 = zzglVar.zza + iZza19;
                    while (iZza19 < i35) {
                        iZza19 = zzgi.zzb(bArr, iZza19, zzglVar);
                        zziuVar5.zza(zzgy.zza(zzglVar.zzb));
                    }
                    if (iZza19 == i35) {
                        return iZza19;
                    }
                    throw zzig.zza();
                }
                if (i13 == 0) {
                    zziu zziuVar6 = (zziu) zzidVarZza;
                    int iZzb2 = zzgi.zzb(bArr, iZza2, zzglVar);
                    zziuVar6.zza(zzgy.zza(zzglVar.zzb));
                    while (iZzb2 < i10) {
                        int iZza20 = zzgi.zza(bArr, iZzb2, zzglVar);
                        if (i11 != zzglVar.zza) {
                            return iZzb2;
                        }
                        iZzb2 = zzgi.zzb(bArr, iZza20, zzglVar);
                        zziuVar6.zza(zzgy.zza(zzglVar.zzb));
                    }
                    return iZzb2;
                }
                return iZza2;
            case 49:
                if (i13 == 3) {
                    zzjv zzjvVarZza = zza(i14);
                    int i36 = (i11 & (-8)) | 4;
                    iZza2 = zzgi.zza(zzjvVarZza, bArr, i9, i10, i36, zzglVar);
                    zzidVarZza.add(zzglVar.zzc);
                    while (iZza2 < i10) {
                        int iZza21 = zzgi.zza(bArr, iZza2, zzglVar);
                        if (i11 == zzglVar.zza) {
                            iZza2 = zzgi.zza(zzjvVarZza, bArr, iZza21, i10, i36, zzglVar);
                            zzidVarZza.add(zzglVar.zzc);
                        }
                    }
                }
                return iZza2;
            default:
                return iZza2;
        }
    }

    private final <K, V> int zza(T t8, byte[] bArr, int i9, int i10, int i11, long j9, zzgl zzglVar) throws zzig {
        Unsafe unsafe = zzb;
        Object objZzb = zzb(i11);
        Object object = unsafe.getObject(t8, j9);
        if (this.zzs.zzc(object)) {
            Object objZze = this.zzs.zze(objZzb);
            this.zzs.zza(objZze, object);
            unsafe.putObject(t8, j9, objZze);
            object = objZze;
        }
        zzix<?, ?> zzixVarZzf = this.zzs.zzf(objZzb);
        Map<?, ?> mapZza = this.zzs.zza(object);
        int iZza = zzgi.zza(bArr, i9, zzglVar);
        int i12 = zzglVar.zza;
        if (i12 >= 0 && i12 <= i10 - iZza) {
            int i13 = i12 + iZza;
            K k9 = zzixVarZzf.zzb;
            V v8 = zzixVarZzf.zzd;
            while (iZza < i13) {
                int iZza2 = iZza + 1;
                int i14 = bArr[iZza];
                if (i14 < 0) {
                    iZza2 = zzgi.zza(i14, bArr, iZza2, zzglVar);
                    i14 = zzglVar.zza;
                }
                int i15 = iZza2;
                int i16 = i14 >>> 3;
                int i17 = i14 & 7;
                if (i16 != 1) {
                    if (i16 == 2 && i17 == zzixVarZzf.zzc.zzb()) {
                        iZza = zza(bArr, i15, i10, zzixVarZzf.zzc, zzixVarZzf.zzd.getClass(), zzglVar);
                        v8 = zzglVar.zzc;
                    } else {
                        iZza = zzgi.zza(i14, bArr, i15, i10, zzglVar);
                    }
                } else if (i17 == zzixVarZzf.zza.zzb()) {
                    iZza = zza(bArr, i15, i10, zzixVarZzf.zza, (Class<?>) null, zzglVar);
                    k9 = (K) zzglVar.zzc;
                } else {
                    iZza = zzgi.zza(i14, bArr, i15, i10, zzglVar);
                }
            }
            if (iZza == i13) {
                mapZza.put(k9, v8);
                return i13;
            }
            throw zzig.zzg();
        }
        throw zzig.zza();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final int zza(T t8, byte[] bArr, int i9, int i10, int i11, int i12, int i13, int i14, int i15, long j9, int i16, zzgl zzglVar) throws zzig {
        int iZzb;
        Unsafe unsafe = zzb;
        long j10 = this.zzc[i16 + 2] & 1048575;
        switch (i15) {
            case 51:
                if (i13 == 1) {
                    unsafe.putObject(t8, j9, Double.valueOf(zzgi.zzc(bArr, i9)));
                    iZzb = i9 + 8;
                    unsafe.putInt(t8, j10, i12);
                    return iZzb;
                }
                return i9;
            case 52:
                if (i13 == 5) {
                    unsafe.putObject(t8, j9, Float.valueOf(zzgi.zzd(bArr, i9)));
                    iZzb = i9 + 4;
                    unsafe.putInt(t8, j10, i12);
                    return iZzb;
                }
                return i9;
            case 53:
            case 54:
                if (i13 == 0) {
                    iZzb = zzgi.zzb(bArr, i9, zzglVar);
                    unsafe.putObject(t8, j9, Long.valueOf(zzglVar.zzb));
                    unsafe.putInt(t8, j10, i12);
                    return iZzb;
                }
                return i9;
            case 55:
            case 62:
                if (i13 == 0) {
                    iZzb = zzgi.zza(bArr, i9, zzglVar);
                    unsafe.putObject(t8, j9, Integer.valueOf(zzglVar.zza));
                    unsafe.putInt(t8, j10, i12);
                    return iZzb;
                }
                return i9;
            case 56:
            case 65:
                if (i13 == 1) {
                    unsafe.putObject(t8, j9, Long.valueOf(zzgi.zzb(bArr, i9)));
                    iZzb = i9 + 8;
                    unsafe.putInt(t8, j10, i12);
                    return iZzb;
                }
                return i9;
            case 57:
            case 64:
                if (i13 == 5) {
                    unsafe.putObject(t8, j9, Integer.valueOf(zzgi.zza(bArr, i9)));
                    iZzb = i9 + 4;
                    unsafe.putInt(t8, j10, i12);
                    return iZzb;
                }
                return i9;
            case 58:
                if (i13 == 0) {
                    iZzb = zzgi.zzb(bArr, i9, zzglVar);
                    unsafe.putObject(t8, j9, Boolean.valueOf(zzglVar.zzb != 0));
                    unsafe.putInt(t8, j10, i12);
                    return iZzb;
                }
                return i9;
            case 59:
                if (i13 == 2) {
                    int iZza = zzgi.zza(bArr, i9, zzglVar);
                    int i17 = zzglVar.zza;
                    if (i17 == 0) {
                        unsafe.putObject(t8, j9, "");
                    } else {
                        if ((i14 & 536870912) != 0 && !zzkw.zza(bArr, iZza, iZza + i17)) {
                            throw zzig.zzh();
                        }
                        unsafe.putObject(t8, j9, new String(bArr, iZza, i17, zzhx.zza));
                        iZza += i17;
                    }
                    unsafe.putInt(t8, j10, i12);
                    return iZza;
                }
                return i9;
            case 60:
                if (i13 == 2) {
                    int iZza2 = zzgi.zza(zza(i16), bArr, i9, i10, zzglVar);
                    Object object = unsafe.getInt(t8, j10) == i12 ? unsafe.getObject(t8, j9) : null;
                    if (object == null) {
                        unsafe.putObject(t8, j9, zzglVar.zzc);
                    } else {
                        unsafe.putObject(t8, j9, zzhx.zza(object, zzglVar.zzc));
                    }
                    unsafe.putInt(t8, j10, i12);
                    return iZza2;
                }
                return i9;
            case 61:
                if (i13 == 2) {
                    iZzb = zzgi.zze(bArr, i9, zzglVar);
                    unsafe.putObject(t8, j9, zzglVar.zzc);
                    unsafe.putInt(t8, j10, i12);
                    return iZzb;
                }
                return i9;
            case 63:
                if (i13 == 0) {
                    int iZza3 = zzgi.zza(bArr, i9, zzglVar);
                    int i18 = zzglVar.zza;
                    zzic zzicVarZzc = zzc(i16);
                    if (zzicVarZzc != null && !zzicVarZzc.zza(i18)) {
                        zze(t8).zza(i11, Long.valueOf(i18));
                        return iZza3;
                    }
                    unsafe.putObject(t8, j9, Integer.valueOf(i18));
                    iZzb = iZza3;
                    unsafe.putInt(t8, j10, i12);
                    return iZzb;
                }
                return i9;
            case 66:
                if (i13 == 0) {
                    iZzb = zzgi.zza(bArr, i9, zzglVar);
                    unsafe.putObject(t8, j9, Integer.valueOf(zzgy.zze(zzglVar.zza)));
                    unsafe.putInt(t8, j10, i12);
                    return iZzb;
                }
                return i9;
            case 67:
                if (i13 == 0) {
                    iZzb = zzgi.zzb(bArr, i9, zzglVar);
                    unsafe.putObject(t8, j9, Long.valueOf(zzgy.zza(zzglVar.zzb)));
                    unsafe.putInt(t8, j10, i12);
                    return iZzb;
                }
                return i9;
            case 68:
                if (i13 == 3) {
                    iZzb = zzgi.zza(zza(i16), bArr, i9, i10, (i11 & (-8)) | 4, zzglVar);
                    Object object2 = unsafe.getInt(t8, j10) == i12 ? unsafe.getObject(t8, j9) : null;
                    if (object2 == null) {
                        unsafe.putObject(t8, j9, zzglVar.zzc);
                    } else {
                        unsafe.putObject(t8, j9, zzhx.zza(object2, zzglVar.zzc));
                    }
                    unsafe.putInt(t8, j10, i12);
                    return iZzb;
                }
                return i9;
            default:
                return i9;
        }
    }

    private final zzjv zza(int i9) {
        int i10 = (i9 / 3) << 1;
        zzjv zzjvVar = (zzjv) this.zzd[i10];
        if (zzjvVar != null) {
            return zzjvVar;
        }
        zzjv<T> zzjvVarZza = zzjr.zza().zza((Class) this.zzd[i10 + 1]);
        this.zzd[i10] = zzjvVarZza;
        return zzjvVarZza;
    }

    /* JADX WARN: Code restructure failed: missing block: B:151:0x04c4, code lost:
    
        if (r6 == 1048575) goto L153;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x04c6, code lost:
    
        r25.putInt(r12, r6, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x04cc, code lost:
    
        r1 = r9.zzm;
        r2 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x04d1, code lost:
    
        if (r1 >= r9.zzn) goto L238;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x04d3, code lost:
    
        r2 = (com.google.android.gms.internal.measurement.zzkq) r9.zza(r12, r9.zzl[r1], (int) r2, (com.google.android.gms.internal.measurement.zzkn<UT, int>) r9.zzq);
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x04e2, code lost:
    
        if (r2 == null) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x04e4, code lost:
    
        r9.zzq.zzb(r12, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x04e9, code lost:
    
        if (r7 != 0) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x04ed, code lost:
    
        if (r0 != r32) goto L163;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x04f4, code lost:
    
        throw com.google.android.gms.internal.measurement.zzig.zzg();
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x04f7, code lost:
    
        if (r0 > r32) goto L169;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x04f9, code lost:
    
        if (r3 != r7) goto L169;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x04fb, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x0500, code lost:
    
        throw com.google.android.gms.internal.measurement.zzig.zzg();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int zza(T t8, byte[] bArr, int i9, int i10, int i11, zzgl zzglVar) throws zzig {
        Unsafe unsafe;
        int i12;
        Object obj;
        zzjk<T> zzjkVar;
        int i13;
        int iZzg;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        Object obj2;
        int i19;
        zzgl zzglVar2;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int iZzb;
        int i25;
        int i26;
        int i27;
        int i28;
        zzjk<T> zzjkVar2 = this;
        Object obj3 = t8;
        byte[] bArr2 = bArr;
        int i29 = i10;
        int i30 = i11;
        zzgl zzglVar3 = zzglVar;
        Unsafe unsafe2 = zzb;
        int iZza = i9;
        int i31 = 0;
        int i32 = 0;
        int i33 = 0;
        int i34 = -1;
        int i35 = 1048575;
        while (true) {
            if (iZza < i29) {
                int i36 = iZza + 1;
                byte b9 = bArr2[iZza];
                if (b9 < 0) {
                    int iZza2 = zzgi.zza(b9, bArr2, i36, zzglVar3);
                    i13 = zzglVar3.zza;
                    i36 = iZza2;
                } else {
                    i13 = b9;
                }
                int i37 = i13 >>> 3;
                int i38 = i13 & 7;
                if (i37 > i34) {
                    iZzg = zzjkVar2.zza(i37, i31 / 3);
                } else {
                    iZzg = zzjkVar2.zzg(i37);
                }
                int i39 = iZzg;
                if (i39 == -1) {
                    i14 = i37;
                    i15 = i36;
                    i16 = i13;
                    i17 = i33;
                    unsafe = unsafe2;
                    i12 = i30;
                    i18 = 0;
                } else {
                    int[] iArr = zzjkVar2.zzc;
                    int i40 = iArr[i39 + 1];
                    int i41 = (i40 & 267386880) >>> 20;
                    int i42 = i13;
                    long j9 = i40 & 1048575;
                    if (i41 <= 17) {
                        int i43 = iArr[i39 + 2];
                        int i44 = 1 << (i43 >>> 20);
                        int i45 = i43 & 1048575;
                        if (i45 != i35) {
                            if (i35 != 1048575) {
                                unsafe2.putInt(obj3, i35, i33);
                            }
                            i33 = unsafe2.getInt(obj3, i45);
                            i20 = i45;
                        } else {
                            i20 = i35;
                        }
                        int i46 = i33;
                        switch (i41) {
                            case 0:
                                i21 = i37;
                                i22 = i20;
                                i23 = i42;
                                bArr2 = bArr;
                                i24 = i39;
                                if (i38 != 1) {
                                    i15 = i36;
                                    i17 = i46;
                                    i16 = i23;
                                    unsafe = unsafe2;
                                    i18 = i24;
                                    i35 = i22;
                                    i12 = i11;
                                    i14 = i21;
                                    break;
                                } else {
                                    zzkt.zza(obj3, j9, zzgi.zzc(bArr2, i36));
                                    iZza = i36 + 8;
                                    i33 = i46 | i44;
                                    i35 = i22;
                                    i32 = i23;
                                    i34 = i21;
                                    i31 = i24;
                                    i29 = i10;
                                    i30 = i11;
                                    break;
                                }
                            case 1:
                                i21 = i37;
                                i22 = i20;
                                i23 = i42;
                                bArr2 = bArr;
                                i24 = i39;
                                if (i38 != 5) {
                                    i15 = i36;
                                    i17 = i46;
                                    i16 = i23;
                                    unsafe = unsafe2;
                                    i18 = i24;
                                    i35 = i22;
                                    i12 = i11;
                                    i14 = i21;
                                    break;
                                } else {
                                    zzkt.zza(obj3, j9, zzgi.zzd(bArr2, i36));
                                    iZza = i36 + 4;
                                    i33 = i46 | i44;
                                    i35 = i22;
                                    i32 = i23;
                                    i34 = i21;
                                    i31 = i24;
                                    i29 = i10;
                                    i30 = i11;
                                    break;
                                }
                            case 2:
                            case 3:
                                i21 = i37;
                                i22 = i20;
                                i23 = i42;
                                bArr2 = bArr;
                                i24 = i39;
                                if (i38 != 0) {
                                    i15 = i36;
                                    i17 = i46;
                                    i16 = i23;
                                    unsafe = unsafe2;
                                    i18 = i24;
                                    i35 = i22;
                                    i12 = i11;
                                    i14 = i21;
                                    break;
                                } else {
                                    iZzb = zzgi.zzb(bArr2, i36, zzglVar3);
                                    unsafe2.putLong(t8, j9, zzglVar3.zzb);
                                    i33 = i46 | i44;
                                    i35 = i22;
                                    i32 = i23;
                                    iZza = iZzb;
                                    i34 = i21;
                                    i31 = i24;
                                    i29 = i10;
                                    i30 = i11;
                                    break;
                                }
                            case 4:
                            case 11:
                                i21 = i37;
                                i22 = i20;
                                i23 = i42;
                                bArr2 = bArr;
                                i24 = i39;
                                if (i38 != 0) {
                                    i15 = i36;
                                    i17 = i46;
                                    i16 = i23;
                                    unsafe = unsafe2;
                                    i18 = i24;
                                    i35 = i22;
                                    i12 = i11;
                                    i14 = i21;
                                    break;
                                } else {
                                    iZza = zzgi.zza(bArr2, i36, zzglVar3);
                                    unsafe2.putInt(obj3, j9, zzglVar3.zza);
                                    i33 = i46 | i44;
                                    i35 = i22;
                                    i32 = i23;
                                    i34 = i21;
                                    i31 = i24;
                                    i29 = i10;
                                    i30 = i11;
                                    break;
                                }
                            case 5:
                            case 14:
                                i21 = i37;
                                i22 = i20;
                                i23 = i42;
                                bArr2 = bArr;
                                i24 = i39;
                                if (i38 != 1) {
                                    i15 = i36;
                                    i17 = i46;
                                    i16 = i23;
                                    unsafe = unsafe2;
                                    i18 = i24;
                                    i35 = i22;
                                    i12 = i11;
                                    i14 = i21;
                                    break;
                                } else {
                                    unsafe2.putLong(t8, j9, zzgi.zzb(bArr2, i36));
                                    iZza = i36 + 8;
                                    i33 = i46 | i44;
                                    i35 = i22;
                                    i32 = i23;
                                    i34 = i21;
                                    i31 = i24;
                                    i29 = i10;
                                    i30 = i11;
                                    break;
                                }
                            case 6:
                            case 13:
                                i21 = i37;
                                i22 = i20;
                                i23 = i42;
                                bArr2 = bArr;
                                i24 = i39;
                                i25 = i10;
                                if (i38 != 5) {
                                    i15 = i36;
                                    i17 = i46;
                                    i16 = i23;
                                    unsafe = unsafe2;
                                    i18 = i24;
                                    i35 = i22;
                                    i12 = i11;
                                    i14 = i21;
                                    break;
                                } else {
                                    unsafe2.putInt(obj3, j9, zzgi.zza(bArr2, i36));
                                    iZza = i36 + 4;
                                    i33 = i46 | i44;
                                    i35 = i22;
                                    i32 = i23;
                                    i34 = i21;
                                    i30 = i11;
                                    int i47 = i24;
                                    i29 = i25;
                                    i31 = i47;
                                    break;
                                }
                            case 7:
                                i21 = i37;
                                i22 = i20;
                                i23 = i42;
                                bArr2 = bArr;
                                i24 = i39;
                                i25 = i10;
                                if (i38 != 0) {
                                    i15 = i36;
                                    i17 = i46;
                                    i16 = i23;
                                    unsafe = unsafe2;
                                    i18 = i24;
                                    i35 = i22;
                                    i12 = i11;
                                    i14 = i21;
                                    break;
                                } else {
                                    int iZzb2 = zzgi.zzb(bArr2, i36, zzglVar3);
                                    zzkt.zza(obj3, j9, zzglVar3.zzb != 0);
                                    i33 = i46 | i44;
                                    i35 = i22;
                                    iZza = iZzb2;
                                    i32 = i23;
                                    i34 = i21;
                                    i30 = i11;
                                    int i472 = i24;
                                    i29 = i25;
                                    i31 = i472;
                                    break;
                                }
                            case 8:
                                i21 = i37;
                                i22 = i20;
                                i23 = i42;
                                bArr2 = bArr;
                                i24 = i39;
                                i25 = i10;
                                if (i38 != 2) {
                                    i15 = i36;
                                    i17 = i46;
                                    i16 = i23;
                                    unsafe = unsafe2;
                                    i18 = i24;
                                    i35 = i22;
                                    i12 = i11;
                                    i14 = i21;
                                    break;
                                } else {
                                    if ((i40 & 536870912) == 0) {
                                        iZza = zzgi.zzc(bArr2, i36, zzglVar3);
                                    } else {
                                        iZza = zzgi.zzd(bArr2, i36, zzglVar3);
                                    }
                                    unsafe2.putObject(obj3, j9, zzglVar3.zzc);
                                    i33 = i46 | i44;
                                    i35 = i22;
                                    i32 = i23;
                                    i34 = i21;
                                    i30 = i11;
                                    int i4722 = i24;
                                    i29 = i25;
                                    i31 = i4722;
                                    break;
                                }
                            case 9:
                                i21 = i37;
                                i22 = i20;
                                i23 = i42;
                                bArr2 = bArr;
                                i24 = i39;
                                if (i38 != 2) {
                                    i15 = i36;
                                    i17 = i46;
                                    i16 = i23;
                                    unsafe = unsafe2;
                                    i18 = i24;
                                    i35 = i22;
                                    i12 = i11;
                                    i14 = i21;
                                    break;
                                } else {
                                    i25 = i10;
                                    iZza = zzgi.zza(zzjkVar2.zza(i24), bArr2, i36, i25, zzglVar3);
                                    if ((i46 & i44) == 0) {
                                        unsafe2.putObject(obj3, j9, zzglVar3.zzc);
                                    } else {
                                        unsafe2.putObject(obj3, j9, zzhx.zza(unsafe2.getObject(obj3, j9), zzglVar3.zzc));
                                    }
                                    i33 = i46 | i44;
                                    i35 = i22;
                                    i32 = i23;
                                    i34 = i21;
                                    i30 = i11;
                                    int i47222 = i24;
                                    i29 = i25;
                                    i31 = i47222;
                                    break;
                                }
                            case 10:
                                i21 = i37;
                                i22 = i20;
                                i23 = i42;
                                bArr2 = bArr;
                                i24 = i39;
                                if (i38 != 2) {
                                    i15 = i36;
                                    i17 = i46;
                                    i16 = i23;
                                    unsafe = unsafe2;
                                    i18 = i24;
                                    i35 = i22;
                                    i12 = i11;
                                    i14 = i21;
                                    break;
                                } else {
                                    iZza = zzgi.zze(bArr2, i36, zzglVar3);
                                    unsafe2.putObject(obj3, j9, zzglVar3.zzc);
                                    i33 = i46 | i44;
                                    i35 = i22;
                                    i32 = i23;
                                    i34 = i21;
                                    i31 = i24;
                                    i29 = i10;
                                    i30 = i11;
                                    break;
                                }
                            case 12:
                                i21 = i37;
                                i22 = i20;
                                i23 = i42;
                                bArr2 = bArr;
                                i24 = i39;
                                if (i38 != 0) {
                                    i15 = i36;
                                    i17 = i46;
                                    i16 = i23;
                                    unsafe = unsafe2;
                                    i18 = i24;
                                    i35 = i22;
                                    i12 = i11;
                                    i14 = i21;
                                    break;
                                } else {
                                    iZza = zzgi.zza(bArr2, i36, zzglVar3);
                                    int i48 = zzglVar3.zza;
                                    zzic zzicVarZzc = zzjkVar2.zzc(i24);
                                    if (zzicVarZzc != null && !zzicVarZzc.zza(i48)) {
                                        zze(t8).zza(i23, Long.valueOf(i48));
                                        i33 = i46;
                                        i32 = i23;
                                        i34 = i21;
                                        i31 = i24;
                                        i35 = i22;
                                        i29 = i10;
                                        i30 = i11;
                                    } else {
                                        unsafe2.putInt(obj3, j9, i48);
                                        i33 = i46 | i44;
                                        i35 = i22;
                                        i32 = i23;
                                        i34 = i21;
                                        i31 = i24;
                                        i29 = i10;
                                        i30 = i11;
                                        break;
                                    }
                                }
                                break;
                            case 15:
                                i21 = i37;
                                i22 = i20;
                                i23 = i42;
                                bArr2 = bArr;
                                i24 = i39;
                                if (i38 != 0) {
                                    i15 = i36;
                                    i17 = i46;
                                    i16 = i23;
                                    unsafe = unsafe2;
                                    i18 = i24;
                                    i35 = i22;
                                    i12 = i11;
                                    i14 = i21;
                                    break;
                                } else {
                                    iZza = zzgi.zza(bArr2, i36, zzglVar3);
                                    unsafe2.putInt(obj3, j9, zzgy.zze(zzglVar3.zza));
                                    i33 = i46 | i44;
                                    i35 = i22;
                                    i32 = i23;
                                    i34 = i21;
                                    i31 = i24;
                                    i29 = i10;
                                    i30 = i11;
                                    break;
                                }
                            case 16:
                                i21 = i37;
                                i22 = i20;
                                i23 = i42;
                                if (i38 != 0) {
                                    i24 = i39;
                                    i15 = i36;
                                    i17 = i46;
                                    i16 = i23;
                                    unsafe = unsafe2;
                                    i18 = i24;
                                    i35 = i22;
                                    i12 = i11;
                                    i14 = i21;
                                    break;
                                } else {
                                    bArr2 = bArr;
                                    iZzb = zzgi.zzb(bArr2, i36, zzglVar3);
                                    i24 = i39;
                                    unsafe2.putLong(t8, j9, zzgy.zza(zzglVar3.zzb));
                                    i33 = i46 | i44;
                                    i35 = i22;
                                    i32 = i23;
                                    iZza = iZzb;
                                    i34 = i21;
                                    i31 = i24;
                                    i29 = i10;
                                    i30 = i11;
                                    break;
                                }
                            case 17:
                                if (i38 != 3) {
                                    i21 = i37;
                                    i22 = i20;
                                    i23 = i42;
                                    i24 = i39;
                                    i15 = i36;
                                    i17 = i46;
                                    i16 = i23;
                                    unsafe = unsafe2;
                                    i18 = i24;
                                    i35 = i22;
                                    i12 = i11;
                                    i14 = i21;
                                    break;
                                } else {
                                    int i49 = i20;
                                    iZza = zzgi.zza(zzjkVar2.zza(i39), bArr, i36, i10, (i37 << 3) | 4, zzglVar);
                                    if ((i46 & i44) == 0) {
                                        unsafe2.putObject(obj3, j9, zzglVar3.zzc);
                                    } else {
                                        unsafe2.putObject(obj3, j9, zzhx.zza(unsafe2.getObject(obj3, j9), zzglVar3.zzc));
                                    }
                                    i33 = i46 | i44;
                                    bArr2 = bArr;
                                    i35 = i49;
                                    i29 = i10;
                                    i32 = i42;
                                    i31 = i39;
                                    i34 = i37;
                                    i30 = i11;
                                    break;
                                }
                            default:
                                i21 = i37;
                                i24 = i39;
                                i22 = i20;
                                i23 = i42;
                                i15 = i36;
                                i17 = i46;
                                i16 = i23;
                                unsafe = unsafe2;
                                i18 = i24;
                                i35 = i22;
                                i12 = i11;
                                i14 = i21;
                                break;
                        }
                    } else {
                        bArr2 = bArr;
                        if (i41 != 27) {
                            i17 = i33;
                            i26 = i35;
                            if (i41 <= 49) {
                                int i50 = i36;
                                i14 = i37;
                                i28 = i42;
                                unsafe = unsafe2;
                                i18 = i39;
                                iZza = zza((zzjk<T>) t8, bArr, i36, i10, i42, i14, i38, i39, i40, i41, j9, zzglVar);
                                if (iZza == i50) {
                                    i12 = i11;
                                    i15 = iZza;
                                    i35 = i26;
                                    i16 = i28;
                                } else {
                                    zzjkVar2 = this;
                                    obj3 = t8;
                                    bArr2 = bArr;
                                    i34 = i14;
                                    i29 = i10;
                                    i30 = i11;
                                    zzglVar3 = zzglVar;
                                    i31 = i18;
                                    i33 = i17;
                                    i35 = i26;
                                    i32 = i28;
                                    unsafe2 = unsafe;
                                }
                            } else {
                                i27 = i36;
                                i14 = i37;
                                i28 = i42;
                                unsafe = unsafe2;
                                i18 = i39;
                                if (i41 != 50) {
                                    iZza = zza((zzjk<T>) t8, bArr, i27, i10, i28, i14, i38, i40, i41, j9, i18, zzglVar);
                                    if (iZza != i27) {
                                        zzjkVar2 = this;
                                        obj3 = t8;
                                        bArr2 = bArr;
                                        i29 = i10;
                                        zzglVar3 = zzglVar;
                                        i32 = i28;
                                        i34 = i14;
                                        i31 = i18;
                                        i33 = i17;
                                        i35 = i26;
                                        unsafe2 = unsafe;
                                        i30 = i11;
                                    }
                                } else if (i38 == 2) {
                                    iZza = zza((zzjk<T>) t8, bArr, i27, i10, i18, j9, zzglVar);
                                    if (iZza != i27) {
                                        zzjkVar2 = this;
                                        obj3 = t8;
                                        bArr2 = bArr;
                                        i34 = i14;
                                        i29 = i10;
                                        i30 = i11;
                                        zzglVar3 = zzglVar;
                                        i31 = i18;
                                        i33 = i17;
                                        i35 = i26;
                                        i32 = i28;
                                        unsafe2 = unsafe;
                                    }
                                }
                                i12 = i11;
                                i15 = iZza;
                                i35 = i26;
                                i16 = i28;
                            }
                        } else if (i38 == 2) {
                            zzid zzidVarZza = (zzid) unsafe2.getObject(obj3, j9);
                            if (!zzidVarZza.zza()) {
                                int size = zzidVarZza.size();
                                zzidVarZza = zzidVarZza.zza(size == 0 ? 10 : size << 1);
                                unsafe2.putObject(obj3, j9, zzidVarZza);
                            }
                            iZza = zzgi.zza(zzjkVar2.zza(i39), i42, bArr, i36, i10, zzidVarZza, zzglVar);
                            i32 = i42;
                            i34 = i37;
                            i31 = i39;
                            i33 = i33;
                            i35 = i35;
                            i29 = i10;
                            i30 = i11;
                        } else {
                            i17 = i33;
                            i26 = i35;
                            i27 = i36;
                            i14 = i37;
                            i28 = i42;
                            unsafe = unsafe2;
                            i18 = i39;
                        }
                        i12 = i11;
                        i15 = i27;
                        i35 = i26;
                        i16 = i28;
                    }
                }
                if (i16 != i12 || i12 == 0) {
                    if (this.zzh) {
                        zzglVar2 = zzglVar;
                        if (zzglVar2.zzd != zzhi.zza()) {
                            int i51 = i14;
                            if (zzglVar2.zzd.zza(this.zzg, i51) == null) {
                                iZza = zzgi.zza(i16, bArr, i15, i10, zze(t8), zzglVar);
                                obj3 = t8;
                                bArr2 = bArr;
                                i29 = i10;
                                i32 = i16;
                                zzjkVar2 = this;
                                zzglVar3 = zzglVar2;
                                i34 = i51;
                                i31 = i18;
                                i33 = i17;
                                unsafe2 = unsafe;
                                i30 = i12;
                            } else {
                                zzhv.zzd zzdVar = (zzhv.zzd) t8;
                                zzdVar.zza();
                                zzho<zzhv.zzc> zzhoVar = zzdVar.zzc;
                                throw new NoSuchMethodError();
                            }
                        } else {
                            obj2 = t8;
                            i19 = i14;
                        }
                    } else {
                        obj2 = t8;
                        i19 = i14;
                        zzglVar2 = zzglVar;
                    }
                    iZza = zzgi.zza(i16, bArr, i15, i10, zze(t8), zzglVar);
                    i29 = i10;
                    i32 = i16;
                    zzjkVar2 = this;
                    zzglVar3 = zzglVar2;
                    i34 = i19;
                    obj3 = obj2;
                    i31 = i18;
                    i33 = i17;
                    unsafe2 = unsafe;
                    bArr2 = bArr;
                    i30 = i12;
                } else {
                    zzjkVar = this;
                    obj = t8;
                    iZza = i15;
                    i32 = i16;
                    i33 = i17;
                }
            } else {
                unsafe = unsafe2;
                i12 = i30;
                obj = obj3;
                zzjkVar = zzjkVar2;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x02d1, code lost:
    
        if (r0 == r5) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x02d5, code lost:
    
        r15 = r30;
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r2 = r18;
        r10 = r20;
        r1 = r25;
        r6 = r27;
        r7 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x031a, code lost:
    
        if (r0 == r15) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x033d, code lost:
    
        if (r0 == r15) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x033f, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:29:0x0094. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v11, types: [int] */
    @Override // com.google.android.gms.internal.measurement.zzjv
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(T t8, byte[] bArr, int i9, int i10, zzgl zzglVar) throws zzig {
        byte b9;
        int iZza;
        int iZzg;
        int i11;
        int i12;
        Unsafe unsafe;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        Unsafe unsafe2;
        int i20;
        int i21;
        Unsafe unsafe3;
        Unsafe unsafe4;
        zzjk<T> zzjkVar = this;
        T t9 = t8;
        byte[] bArr2 = bArr;
        int i22 = i10;
        zzgl zzglVar2 = zzglVar;
        if (zzjkVar.zzj) {
            Unsafe unsafe5 = zzb;
            int i23 = -1;
            int i24 = 1048575;
            int iZza2 = i9;
            int i25 = 1048575;
            int i26 = -1;
            int i27 = 0;
            int i28 = 0;
            while (iZza2 < i22) {
                int i29 = iZza2 + 1;
                byte b10 = bArr2[iZza2];
                if (b10 < 0) {
                    iZza = zzgi.zza(b10, bArr2, i29, zzglVar2);
                    b9 = zzglVar2.zza;
                } else {
                    b9 = b10;
                    iZza = i29;
                }
                int i30 = b9 >>> 3;
                int i31 = b9 & 7;
                if (i30 > i26) {
                    iZzg = zzjkVar.zza(i30, i27 / 3);
                } else {
                    iZzg = zzjkVar.zzg(i30);
                }
                int i32 = iZzg;
                if (i32 == i23) {
                    i11 = iZza;
                    i12 = i30;
                    unsafe = unsafe5;
                    i13 = i23;
                    i14 = 0;
                } else {
                    int[] iArr = zzjkVar.zzc;
                    int i33 = iArr[i32 + 1];
                    int i34 = (i33 & 267386880) >>> 20;
                    Unsafe unsafe6 = unsafe5;
                    long j9 = i33 & i24;
                    if (i34 <= 17) {
                        int i35 = iArr[i32 + 2];
                        int i36 = 1 << (i35 >>> 20);
                        int i37 = i35 & 1048575;
                        if (i37 != i25) {
                            if (i25 != 1048575) {
                                long j10 = i25;
                                unsafe4 = unsafe6;
                                unsafe4.putInt(t9, j10, i28);
                            } else {
                                unsafe4 = unsafe6;
                            }
                            if (i37 != 1048575) {
                                i28 = unsafe4.getInt(t9, i37);
                            }
                            unsafe2 = unsafe4;
                            i25 = i37;
                        } else {
                            unsafe2 = unsafe6;
                        }
                        switch (i34) {
                            case 0:
                                i12 = i30;
                                i16 = 1048575;
                                i20 = i32;
                                i21 = iZza;
                                i15 = i25;
                                unsafe3 = unsafe2;
                                if (i31 != 1) {
                                    i11 = i21;
                                    unsafe = unsafe3;
                                    i14 = i20;
                                    i25 = i15;
                                    i13 = -1;
                                    break;
                                } else {
                                    zzkt.zza(t9, j9, zzgi.zzc(bArr2, i21));
                                    iZza2 = i21 + 8;
                                    i28 |= i36;
                                    unsafe5 = unsafe3;
                                    i27 = i20;
                                    i25 = i15;
                                    i26 = i12;
                                    i24 = i16;
                                    i23 = -1;
                                    break;
                                }
                            case 1:
                                i12 = i30;
                                i16 = 1048575;
                                i20 = i32;
                                i21 = iZza;
                                i15 = i25;
                                unsafe3 = unsafe2;
                                if (i31 != 5) {
                                    i11 = i21;
                                    unsafe = unsafe3;
                                    i14 = i20;
                                    i25 = i15;
                                    i13 = -1;
                                    break;
                                } else {
                                    zzkt.zza((Object) t9, j9, zzgi.zzd(bArr2, i21));
                                    iZza2 = i21 + 4;
                                    i28 |= i36;
                                    unsafe5 = unsafe3;
                                    i27 = i20;
                                    i25 = i15;
                                    i26 = i12;
                                    i24 = i16;
                                    i23 = -1;
                                    break;
                                }
                            case 2:
                            case 3:
                                i12 = i30;
                                i16 = 1048575;
                                i20 = i32;
                                i21 = iZza;
                                i15 = i25;
                                unsafe3 = unsafe2;
                                if (i31 != 0) {
                                    i11 = i21;
                                    unsafe = unsafe3;
                                    i14 = i20;
                                    i25 = i15;
                                    i13 = -1;
                                    break;
                                } else {
                                    int iZzb = zzgi.zzb(bArr2, i21, zzglVar2);
                                    unsafe3.putLong(t8, j9, zzglVar2.zzb);
                                    i28 |= i36;
                                    unsafe5 = unsafe3;
                                    i27 = i20;
                                    iZza2 = iZzb;
                                    i25 = i15;
                                    i26 = i12;
                                    i24 = i16;
                                    i23 = -1;
                                    break;
                                }
                            case 4:
                            case 11:
                                i12 = i30;
                                i16 = 1048575;
                                i20 = i32;
                                i21 = iZza;
                                i15 = i25;
                                unsafe3 = unsafe2;
                                if (i31 != 0) {
                                    i11 = i21;
                                    unsafe = unsafe3;
                                    i14 = i20;
                                    i25 = i15;
                                    i13 = -1;
                                    break;
                                } else {
                                    iZza2 = zzgi.zza(bArr2, i21, zzglVar2);
                                    unsafe3.putInt(t9, j9, zzglVar2.zza);
                                    i28 |= i36;
                                    unsafe5 = unsafe3;
                                    i27 = i20;
                                    i25 = i15;
                                    i26 = i12;
                                    i24 = i16;
                                    i23 = -1;
                                    break;
                                }
                            case 5:
                            case 14:
                                i12 = i30;
                                i16 = 1048575;
                                i20 = i32;
                                i15 = i25;
                                unsafe3 = unsafe2;
                                if (i31 != 1) {
                                    i21 = iZza;
                                    i11 = i21;
                                    unsafe = unsafe3;
                                    i14 = i20;
                                    i25 = i15;
                                    i13 = -1;
                                    break;
                                } else {
                                    unsafe3.putLong(t8, j9, zzgi.zzb(bArr2, iZza));
                                    iZza2 = iZza + 8;
                                    i28 |= i36;
                                    unsafe5 = unsafe3;
                                    i27 = i20;
                                    i25 = i15;
                                    i26 = i12;
                                    i24 = i16;
                                    i23 = -1;
                                    break;
                                }
                            case 6:
                            case 13:
                                i12 = i30;
                                i16 = 1048575;
                                i20 = i32;
                                i15 = i25;
                                unsafe3 = unsafe2;
                                if (i31 != 5) {
                                    i21 = iZza;
                                    i11 = i21;
                                    unsafe = unsafe3;
                                    i14 = i20;
                                    i25 = i15;
                                    i13 = -1;
                                    break;
                                } else {
                                    unsafe3.putInt(t9, j9, zzgi.zza(bArr2, iZza));
                                    iZza2 = iZza + 4;
                                    i28 |= i36;
                                    unsafe5 = unsafe3;
                                    i27 = i20;
                                    i25 = i15;
                                    i26 = i12;
                                    i24 = i16;
                                    i23 = -1;
                                    break;
                                }
                            case 7:
                                i12 = i30;
                                i16 = 1048575;
                                i20 = i32;
                                i15 = i25;
                                unsafe3 = unsafe2;
                                if (i31 != 0) {
                                    i21 = iZza;
                                    i11 = i21;
                                    unsafe = unsafe3;
                                    i14 = i20;
                                    i25 = i15;
                                    i13 = -1;
                                    break;
                                } else {
                                    iZza2 = zzgi.zzb(bArr2, iZza, zzglVar2);
                                    zzkt.zza(t9, j9, zzglVar2.zzb != 0);
                                    i28 |= i36;
                                    unsafe5 = unsafe3;
                                    i27 = i20;
                                    i25 = i15;
                                    i26 = i12;
                                    i24 = i16;
                                    i23 = -1;
                                    break;
                                }
                            case 8:
                                i12 = i30;
                                i16 = 1048575;
                                i20 = i32;
                                i15 = i25;
                                unsafe3 = unsafe2;
                                if (i31 != 2) {
                                    i21 = iZza;
                                    i11 = i21;
                                    unsafe = unsafe3;
                                    i14 = i20;
                                    i25 = i15;
                                    i13 = -1;
                                    break;
                                } else {
                                    if ((i33 & 536870912) == 0) {
                                        iZza2 = zzgi.zzc(bArr2, iZza, zzglVar2);
                                    } else {
                                        iZza2 = zzgi.zzd(bArr2, iZza, zzglVar2);
                                    }
                                    unsafe3.putObject(t9, j9, zzglVar2.zzc);
                                    i28 |= i36;
                                    unsafe5 = unsafe3;
                                    i27 = i20;
                                    i25 = i15;
                                    i26 = i12;
                                    i24 = i16;
                                    i23 = -1;
                                    break;
                                }
                            case 9:
                                i12 = i30;
                                i16 = 1048575;
                                i20 = i32;
                                i15 = i25;
                                unsafe3 = unsafe2;
                                if (i31 != 2) {
                                    i21 = iZza;
                                    i11 = i21;
                                    unsafe = unsafe3;
                                    i14 = i20;
                                    i25 = i15;
                                    i13 = -1;
                                    break;
                                } else {
                                    iZza2 = zzgi.zza(zzjkVar.zza(i20), bArr2, iZza, i22, zzglVar2);
                                    Object object = unsafe3.getObject(t9, j9);
                                    if (object == null) {
                                        unsafe3.putObject(t9, j9, zzglVar2.zzc);
                                    } else {
                                        unsafe3.putObject(t9, j9, zzhx.zza(object, zzglVar2.zzc));
                                    }
                                    i28 |= i36;
                                    unsafe5 = unsafe3;
                                    i27 = i20;
                                    i25 = i15;
                                    i26 = i12;
                                    i24 = i16;
                                    i23 = -1;
                                    break;
                                }
                            case 10:
                                i12 = i30;
                                i16 = 1048575;
                                i20 = i32;
                                i15 = i25;
                                unsafe3 = unsafe2;
                                if (i31 != 2) {
                                    i21 = iZza;
                                    i11 = i21;
                                    unsafe = unsafe3;
                                    i14 = i20;
                                    i25 = i15;
                                    i13 = -1;
                                    break;
                                } else {
                                    iZza2 = zzgi.zze(bArr2, iZza, zzglVar2);
                                    unsafe3.putObject(t9, j9, zzglVar2.zzc);
                                    i28 |= i36;
                                    unsafe5 = unsafe3;
                                    i27 = i20;
                                    i25 = i15;
                                    i26 = i12;
                                    i24 = i16;
                                    i23 = -1;
                                    break;
                                }
                            case 12:
                                i12 = i30;
                                i16 = 1048575;
                                i20 = i32;
                                i15 = i25;
                                unsafe3 = unsafe2;
                                if (i31 != 0) {
                                    i21 = iZza;
                                    i11 = i21;
                                    unsafe = unsafe3;
                                    i14 = i20;
                                    i25 = i15;
                                    i13 = -1;
                                    break;
                                } else {
                                    iZza2 = zzgi.zza(bArr2, iZza, zzglVar2);
                                    unsafe3.putInt(t9, j9, zzglVar2.zza);
                                    i28 |= i36;
                                    unsafe5 = unsafe3;
                                    i27 = i20;
                                    i25 = i15;
                                    i26 = i12;
                                    i24 = i16;
                                    i23 = -1;
                                    break;
                                }
                            case 15:
                                i12 = i30;
                                i16 = 1048575;
                                i20 = i32;
                                i15 = i25;
                                unsafe3 = unsafe2;
                                if (i31 != 0) {
                                    i21 = iZza;
                                    i11 = i21;
                                    unsafe = unsafe3;
                                    i14 = i20;
                                    i25 = i15;
                                    i13 = -1;
                                    break;
                                } else {
                                    iZza2 = zzgi.zza(bArr2, iZza, zzglVar2);
                                    unsafe3.putInt(t9, j9, zzgy.zze(zzglVar2.zza));
                                    i28 |= i36;
                                    unsafe5 = unsafe3;
                                    i27 = i20;
                                    i25 = i15;
                                    i26 = i12;
                                    i24 = i16;
                                    i23 = -1;
                                    break;
                                }
                            case 16:
                                if (i31 != 0) {
                                    i12 = i30;
                                    i15 = i25;
                                    unsafe3 = unsafe2;
                                    i21 = iZza;
                                    i20 = i32;
                                    i11 = i21;
                                    unsafe = unsafe3;
                                    i14 = i20;
                                    i25 = i15;
                                    i13 = -1;
                                    break;
                                } else {
                                    int iZzb2 = zzgi.zzb(bArr2, iZza, zzglVar2);
                                    i15 = i25;
                                    i12 = i30;
                                    i16 = 1048575;
                                    unsafe2.putLong(t8, j9, zzgy.zza(zzglVar2.zzb));
                                    i28 |= i36;
                                    unsafe5 = unsafe2;
                                    i27 = i32;
                                    iZza2 = iZzb2;
                                    i25 = i15;
                                    i26 = i12;
                                    i24 = i16;
                                    i23 = -1;
                                    break;
                                }
                            default:
                                i12 = i30;
                                i20 = i32;
                                i21 = iZza;
                                i15 = i25;
                                unsafe3 = unsafe2;
                                i11 = i21;
                                unsafe = unsafe3;
                                i14 = i20;
                                i25 = i15;
                                i13 = -1;
                                break;
                        }
                    } else {
                        i12 = i30;
                        int i38 = iZza;
                        i15 = i25;
                        i16 = 1048575;
                        if (i34 != 27) {
                            i14 = i32;
                            if (i34 <= 49) {
                                i18 = i28;
                                i19 = i15;
                                unsafe = unsafe6;
                                i13 = -1;
                                iZza2 = zza((zzjk<T>) t8, bArr, i38, i10, b9, i12, i31, i14, i33, i34, j9, zzglVar);
                            } else {
                                i17 = i38;
                                i18 = i28;
                                unsafe = unsafe6;
                                i19 = i15;
                                i13 = -1;
                                if (i34 != 50) {
                                    iZza2 = zza((zzjk<T>) t8, bArr, i17, i10, b9, i12, i31, i33, i34, j9, i14, zzglVar);
                                } else if (i31 == 2) {
                                    iZza2 = zza((zzjk<T>) t8, bArr, i17, i10, i14, j9, zzglVar);
                                }
                            }
                            unsafe5 = unsafe;
                            i24 = 1048575;
                        } else if (i31 == 2) {
                            zzid zzidVarZza = (zzid) unsafe6.getObject(t9, j9);
                            if (!zzidVarZza.zza()) {
                                int size = zzidVarZza.size();
                                zzidVarZza = zzidVarZza.zza(size == 0 ? 10 : size << 1);
                                unsafe6.putObject(t9, j9, zzidVarZza);
                            }
                            iZza2 = zzgi.zza(zzjkVar.zza(i32), b9, bArr, i38, i10, zzidVarZza, zzglVar);
                            unsafe5 = unsafe6;
                            i28 = i28;
                            i27 = i32;
                            i25 = i15;
                            i26 = i12;
                            i24 = i16;
                            i23 = -1;
                        } else {
                            i14 = i32;
                            i17 = i38;
                            i18 = i28;
                            unsafe = unsafe6;
                            i19 = i15;
                            i13 = -1;
                        }
                        i11 = i17;
                        i28 = i18;
                        i25 = i19;
                    }
                }
                iZza2 = zzgi.zza(b9, bArr, i11, i10, zze(t8), zzglVar);
                zzjkVar = this;
                t9 = t8;
                bArr2 = bArr;
                i22 = i10;
                zzglVar2 = zzglVar;
                i27 = i14;
                i23 = i13;
                i26 = i12;
                unsafe5 = unsafe;
                i24 = 1048575;
            }
            int i39 = i28;
            Unsafe unsafe7 = unsafe5;
            if (i25 != i24) {
                unsafe7.putInt(t8, i25, i39);
            }
            if (iZza2 != i10) {
                throw zzig.zzg();
            }
            return;
        }
        zza((zzjk<T>) t8, bArr, i9, i10, 0, zzglVar);
    }

    private final <UT, UB> UB zza(Object obj, int i9, UB ub, zzkn<UT, UB> zzknVar) {
        zzic zzicVarZzc;
        int i10 = this.zzc[i9];
        Object objZzf = zzkt.zzf(obj, zzd(i9) & 1048575);
        return (objZzf == null || (zzicVarZzc = zzc(i9)) == null) ? ub : (UB) zza(i9, i10, this.zzs.zza(objZzf), zzicVarZzc, (zzic) ub, (zzkn<UT, zzic>) zzknVar);
    }

    private final <K, V, UT, UB> UB zza(int i9, int i10, Map<K, V> map, zzic zzicVar, UB ub, zzkn<UT, UB> zzknVar) {
        zzix<?, ?> zzixVarZzf = this.zzs.zzf(zzb(i9));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!zzicVar.zza(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzknVar.zza();
                }
                zzgu zzguVarZzc = zzgm.zzc(zziy.zza(zzixVarZzf, next.getKey(), next.getValue()));
                try {
                    zziy.zza(zzguVarZzc.zzb(), zzixVarZzf, next.getKey(), next.getValue());
                    zzknVar.zza((zzkn<UT, UB>) ub, i10, zzguVarZzc.zza());
                    it.remove();
                } catch (IOException e9) {
                    throw new RuntimeException(e9);
                }
            }
        }
        return ub;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object obj, int i9, zzjv zzjvVar) {
        return zzjvVar.zzd(zzkt.zzf(obj, i9 & 1048575));
    }

    private static void zza(int i9, Object obj, zzlk zzlkVar) {
        if (obj instanceof String) {
            zzlkVar.zza(i9, (String) obj);
        } else {
            zzlkVar.zza(i9, (zzgm) obj);
        }
    }

    private final void zza(Object obj, int i9, zzjw zzjwVar) {
        if (zzf(i9)) {
            zzkt.zza(obj, i9 & 1048575, zzjwVar.zzm());
        } else if (this.zzi) {
            zzkt.zza(obj, i9 & 1048575, zzjwVar.zzl());
        } else {
            zzkt.zza(obj, i9 & 1048575, zzjwVar.zzn());
        }
    }

    private final boolean zza(T t8, int i9, int i10, int i11, int i12) {
        if (i10 == 1048575) {
            return zza((zzjk<T>) t8, i9);
        }
        return (i11 & i12) != 0;
    }

    private final boolean zza(T t8, int i9) {
        int iZze = zze(i9);
        long j9 = iZze & 1048575;
        if (j9 != 1048575) {
            return (zzkt.zza(t8, j9) & (1 << (iZze >>> 20))) != 0;
        }
        int iZzd = zzd(i9);
        long j10 = iZzd & 1048575;
        switch ((iZzd & 267386880) >>> 20) {
            case 0:
                return zzkt.zze(t8, j10) != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            case 1:
                return zzkt.zzd(t8, j10) != BitmapDescriptorFactory.HUE_RED;
            case 2:
                return zzkt.zzb(t8, j10) != 0;
            case 3:
                return zzkt.zzb(t8, j10) != 0;
            case 4:
                return zzkt.zza(t8, j10) != 0;
            case 5:
                return zzkt.zzb(t8, j10) != 0;
            case 6:
                return zzkt.zza(t8, j10) != 0;
            case 7:
                return zzkt.zzc(t8, j10);
            case 8:
                Object objZzf = zzkt.zzf(t8, j10);
                if (objZzf instanceof String) {
                    return !((String) objZzf).isEmpty();
                }
                if (objZzf instanceof zzgm) {
                    return !zzgm.zza.equals(objZzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzkt.zzf(t8, j10) != null;
            case 10:
                return !zzgm.zza.equals(zzkt.zzf(t8, j10));
            case 11:
                return zzkt.zza(t8, j10) != 0;
            case 12:
                return zzkt.zza(t8, j10) != 0;
            case 13:
                return zzkt.zza(t8, j10) != 0;
            case 14:
                return zzkt.zzb(t8, j10) != 0;
            case 15:
                return zzkt.zza(t8, j10) != 0;
            case 16:
                return zzkt.zzb(t8, j10) != 0;
            case 17:
                return zzkt.zzf(t8, j10) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zza(T t8, int i9, int i10) {
        return zzkt.zza(t8, (long) (zze(i10) & 1048575)) == i9;
    }

    private final int zza(int i9, int i10) {
        if (i9 < this.zze || i9 > this.zzf) {
            return -1;
        }
        return zzb(i9, i10);
    }
}
