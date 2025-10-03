package com.google.android.gms.internal.gtm;

import com.google.android.exoplayer2.extractor.p037ts.TsExtractor;
import com.google.android.gms.internal.gtm.zzc;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* loaded from: classes2.dex */
public final class zzi extends zzuq<zzi> {
    public String version;
    private String[] zzph;
    public String[] zzpi;
    public zzl[] zzpj;
    public zzc.zzd[] zzpk;
    public zzc.zzb[] zzpl;
    public zzc.zzb[] zzpm;
    public zzc.zzb[] zzpn;
    public zzc.zze[] zzpo;
    private String zzpp;
    private String zzpq;
    private String zzpr;
    private zzc.zza zzps;
    private float zzpt;
    private boolean zzpu;
    private String[] zzpv;
    public int zzpw;

    public zzi() {
        String[] strArr = zzuz.zzbhu;
        this.zzph = strArr;
        this.zzpi = strArr;
        this.zzpj = zzl.zzaa();
        this.zzpk = new zzc.zzd[0];
        this.zzpl = new zzc.zzb[0];
        this.zzpm = new zzc.zzb[0];
        this.zzpn = new zzc.zzb[0];
        this.zzpo = new zzc.zze[0];
        this.zzpp = "";
        this.zzpq = "";
        this.zzpr = "0";
        this.version = "";
        this.zzps = null;
        this.zzpt = BitmapDescriptorFactory.HUE_RED;
        this.zzpu = false;
        this.zzpv = strArr;
        this.zzpw = 0;
        this.zzbhb = null;
        this.zzbhl = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzi)) {
            return false;
        }
        zzi zziVar = (zzi) obj;
        if (!zzuu.equals(this.zzph, zziVar.zzph) || !zzuu.equals(this.zzpi, zziVar.zzpi) || !zzuu.equals(this.zzpj, zziVar.zzpj) || !zzuu.equals(this.zzpk, zziVar.zzpk) || !zzuu.equals(this.zzpl, zziVar.zzpl) || !zzuu.equals(this.zzpm, zziVar.zzpm) || !zzuu.equals(this.zzpn, zziVar.zzpn) || !zzuu.equals(this.zzpo, zziVar.zzpo)) {
            return false;
        }
        String str = this.zzpp;
        if (str == null) {
            if (zziVar.zzpp != null) {
                return false;
            }
        } else if (!str.equals(zziVar.zzpp)) {
            return false;
        }
        String str2 = this.zzpq;
        if (str2 == null) {
            if (zziVar.zzpq != null) {
                return false;
            }
        } else if (!str2.equals(zziVar.zzpq)) {
            return false;
        }
        String str3 = this.zzpr;
        if (str3 == null) {
            if (zziVar.zzpr != null) {
                return false;
            }
        } else if (!str3.equals(zziVar.zzpr)) {
            return false;
        }
        String str4 = this.version;
        if (str4 == null) {
            if (zziVar.version != null) {
                return false;
            }
        } else if (!str4.equals(zziVar.version)) {
            return false;
        }
        zzc.zza zzaVar = this.zzps;
        if (zzaVar == null) {
            if (zziVar.zzps != null) {
                return false;
            }
        } else if (!zzaVar.equals(zziVar.zzps)) {
            return false;
        }
        if (Float.floatToIntBits(this.zzpt) != Float.floatToIntBits(zziVar.zzpt) || this.zzpu != zziVar.zzpu || !zzuu.equals(this.zzpv, zziVar.zzpv) || this.zzpw != zziVar.zzpw) {
            return false;
        }
        zzus zzusVar = this.zzbhb;
        if (zzusVar != null && !zzusVar.isEmpty()) {
            return this.zzbhb.equals(zziVar.zzbhb);
        }
        zzus zzusVar2 = zziVar.zzbhb;
        return zzusVar2 == null || zzusVar2.isEmpty();
    }

    public final int hashCode() {
        int iHashCode = (((((((((((((((((zzi.class.getName().hashCode() + 527) * 31) + zzuu.hashCode(this.zzph)) * 31) + zzuu.hashCode(this.zzpi)) * 31) + zzuu.hashCode(this.zzpj)) * 31) + zzuu.hashCode(this.zzpk)) * 31) + zzuu.hashCode(this.zzpl)) * 31) + zzuu.hashCode(this.zzpm)) * 31) + zzuu.hashCode(this.zzpn)) * 31) + zzuu.hashCode(this.zzpo)) * 31;
        String str = this.zzpp;
        int iHashCode2 = 0;
        int iHashCode3 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzpq;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.zzpr;
        int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.version;
        int iHashCode6 = iHashCode5 + (str4 == null ? 0 : str4.hashCode());
        zzc.zza zzaVar = this.zzps;
        int iHashCode7 = ((((((((((iHashCode6 * 31) + (zzaVar == null ? 0 : zzaVar.hashCode())) * 31) + Float.floatToIntBits(this.zzpt)) * 31) + (this.zzpu ? 1231 : 1237)) * 31) + zzuu.hashCode(this.zzpv)) * 31) + this.zzpw) * 31;
        zzus zzusVar = this.zzbhb;
        if (zzusVar != null && !zzusVar.isEmpty()) {
            iHashCode2 = this.zzbhb.hashCode();
        }
        return iHashCode7 + iHashCode2;
    }

    @Override // com.google.android.gms.internal.gtm.zzuq, com.google.android.gms.internal.gtm.zzuw
    public final void zza(zzuo zzuoVar) throws zzup {
        String[] strArr = this.zzpi;
        int i9 = 0;
        if (strArr != null && strArr.length > 0) {
            int i10 = 0;
            while (true) {
                String[] strArr2 = this.zzpi;
                if (i10 >= strArr2.length) {
                    break;
                }
                String str = strArr2[i10];
                if (str != null) {
                    zzuoVar.zza(1, str);
                }
                i10++;
            }
        }
        zzl[] zzlVarArr = this.zzpj;
        if (zzlVarArr != null && zzlVarArr.length > 0) {
            int i11 = 0;
            while (true) {
                zzl[] zzlVarArr2 = this.zzpj;
                if (i11 >= zzlVarArr2.length) {
                    break;
                }
                zzl zzlVar = zzlVarArr2[i11];
                if (zzlVar != null) {
                    zzuoVar.zza(2, zzlVar);
                }
                i11++;
            }
        }
        zzc.zzd[] zzdVarArr = this.zzpk;
        if (zzdVarArr != null && zzdVarArr.length > 0) {
            int i12 = 0;
            while (true) {
                zzc.zzd[] zzdVarArr2 = this.zzpk;
                if (i12 >= zzdVarArr2.length) {
                    break;
                }
                zzc.zzd zzdVar = zzdVarArr2[i12];
                if (zzdVar != null) {
                    zzuoVar.zze(3, zzdVar);
                }
                i12++;
            }
        }
        zzc.zzb[] zzbVarArr = this.zzpl;
        if (zzbVarArr != null && zzbVarArr.length > 0) {
            int i13 = 0;
            while (true) {
                zzc.zzb[] zzbVarArr2 = this.zzpl;
                if (i13 >= zzbVarArr2.length) {
                    break;
                }
                zzc.zzb zzbVar = zzbVarArr2[i13];
                if (zzbVar != null) {
                    zzuoVar.zze(4, zzbVar);
                }
                i13++;
            }
        }
        zzc.zzb[] zzbVarArr3 = this.zzpm;
        if (zzbVarArr3 != null && zzbVarArr3.length > 0) {
            int i14 = 0;
            while (true) {
                zzc.zzb[] zzbVarArr4 = this.zzpm;
                if (i14 >= zzbVarArr4.length) {
                    break;
                }
                zzc.zzb zzbVar2 = zzbVarArr4[i14];
                if (zzbVar2 != null) {
                    zzuoVar.zze(5, zzbVar2);
                }
                i14++;
            }
        }
        zzc.zzb[] zzbVarArr5 = this.zzpn;
        if (zzbVarArr5 != null && zzbVarArr5.length > 0) {
            int i15 = 0;
            while (true) {
                zzc.zzb[] zzbVarArr6 = this.zzpn;
                if (i15 >= zzbVarArr6.length) {
                    break;
                }
                zzc.zzb zzbVar3 = zzbVarArr6[i15];
                if (zzbVar3 != null) {
                    zzuoVar.zze(6, zzbVar3);
                }
                i15++;
            }
        }
        zzc.zze[] zzeVarArr = this.zzpo;
        if (zzeVarArr != null && zzeVarArr.length > 0) {
            int i16 = 0;
            while (true) {
                zzc.zze[] zzeVarArr2 = this.zzpo;
                if (i16 >= zzeVarArr2.length) {
                    break;
                }
                zzc.zze zzeVar = zzeVarArr2[i16];
                if (zzeVar != null) {
                    zzuoVar.zze(7, zzeVar);
                }
                i16++;
            }
        }
        String str2 = this.zzpp;
        if (str2 != null && !str2.equals("")) {
            zzuoVar.zza(9, this.zzpp);
        }
        String str3 = this.zzpq;
        if (str3 != null && !str3.equals("")) {
            zzuoVar.zza(10, this.zzpq);
        }
        String str4 = this.zzpr;
        if (str4 != null && !str4.equals("0")) {
            zzuoVar.zza(12, this.zzpr);
        }
        String str5 = this.version;
        if (str5 != null && !str5.equals("")) {
            zzuoVar.zza(13, this.version);
        }
        zzc.zza zzaVar = this.zzps;
        if (zzaVar != null) {
            zzuoVar.zze(14, zzaVar);
        }
        if (Float.floatToIntBits(this.zzpt) != Float.floatToIntBits(BitmapDescriptorFactory.HUE_RED)) {
            float f9 = this.zzpt;
            zzuoVar.zzd(15, 5);
            zzuoVar.zzcc(Float.floatToIntBits(f9));
        }
        String[] strArr3 = this.zzpv;
        if (strArr3 != null && strArr3.length > 0) {
            int i17 = 0;
            while (true) {
                String[] strArr4 = this.zzpv;
                if (i17 >= strArr4.length) {
                    break;
                }
                String str6 = strArr4[i17];
                if (str6 != null) {
                    zzuoVar.zza(16, str6);
                }
                i17++;
            }
        }
        int i18 = this.zzpw;
        if (i18 != 0) {
            zzuoVar.zze(17, i18);
        }
        boolean z8 = this.zzpu;
        if (z8) {
            zzuoVar.zzb(18, z8);
        }
        String[] strArr5 = this.zzph;
        if (strArr5 != null && strArr5.length > 0) {
            while (true) {
                String[] strArr6 = this.zzph;
                if (i9 >= strArr6.length) {
                    break;
                }
                String str7 = strArr6[i9];
                if (str7 != null) {
                    zzuoVar.zza(19, str7);
                }
                i9++;
            }
        }
        super.zza(zzuoVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzuq, com.google.android.gms.internal.gtm.zzuw
    public final int zzy() {
        int iZzy = super.zzy();
        String[] strArr = this.zzpi;
        int i9 = 0;
        if (strArr != null && strArr.length > 0) {
            int i10 = 0;
            int iZzda = 0;
            int i11 = 0;
            while (true) {
                String[] strArr2 = this.zzpi;
                if (i10 >= strArr2.length) {
                    break;
                }
                String str = strArr2[i10];
                if (str != null) {
                    i11++;
                    iZzda += zzuo.zzda(str);
                }
                i10++;
            }
            iZzy = iZzy + iZzda + (i11 * 1);
        }
        zzl[] zzlVarArr = this.zzpj;
        if (zzlVarArr != null && zzlVarArr.length > 0) {
            int i12 = 0;
            while (true) {
                zzl[] zzlVarArr2 = this.zzpj;
                if (i12 >= zzlVarArr2.length) {
                    break;
                }
                zzl zzlVar = zzlVarArr2[i12];
                if (zzlVar != null) {
                    iZzy += zzuo.zzb(2, zzlVar);
                }
                i12++;
            }
        }
        zzc.zzd[] zzdVarArr = this.zzpk;
        if (zzdVarArr != null && zzdVarArr.length > 0) {
            int i13 = 0;
            while (true) {
                zzc.zzd[] zzdVarArr2 = this.zzpk;
                if (i13 >= zzdVarArr2.length) {
                    break;
                }
                zzc.zzd zzdVar = zzdVarArr2[i13];
                if (zzdVar != null) {
                    iZzy += zzqj.zzc(3, zzdVar);
                }
                i13++;
            }
        }
        zzc.zzb[] zzbVarArr = this.zzpl;
        if (zzbVarArr != null && zzbVarArr.length > 0) {
            int i14 = 0;
            while (true) {
                zzc.zzb[] zzbVarArr2 = this.zzpl;
                if (i14 >= zzbVarArr2.length) {
                    break;
                }
                zzc.zzb zzbVar = zzbVarArr2[i14];
                if (zzbVar != null) {
                    iZzy += zzqj.zzc(4, zzbVar);
                }
                i14++;
            }
        }
        zzc.zzb[] zzbVarArr3 = this.zzpm;
        if (zzbVarArr3 != null && zzbVarArr3.length > 0) {
            int i15 = 0;
            while (true) {
                zzc.zzb[] zzbVarArr4 = this.zzpm;
                if (i15 >= zzbVarArr4.length) {
                    break;
                }
                zzc.zzb zzbVar2 = zzbVarArr4[i15];
                if (zzbVar2 != null) {
                    iZzy += zzqj.zzc(5, zzbVar2);
                }
                i15++;
            }
        }
        zzc.zzb[] zzbVarArr5 = this.zzpn;
        if (zzbVarArr5 != null && zzbVarArr5.length > 0) {
            int i16 = 0;
            while (true) {
                zzc.zzb[] zzbVarArr6 = this.zzpn;
                if (i16 >= zzbVarArr6.length) {
                    break;
                }
                zzc.zzb zzbVar3 = zzbVarArr6[i16];
                if (zzbVar3 != null) {
                    iZzy += zzqj.zzc(6, zzbVar3);
                }
                i16++;
            }
        }
        zzc.zze[] zzeVarArr = this.zzpo;
        if (zzeVarArr != null && zzeVarArr.length > 0) {
            int i17 = 0;
            while (true) {
                zzc.zze[] zzeVarArr2 = this.zzpo;
                if (i17 >= zzeVarArr2.length) {
                    break;
                }
                zzc.zze zzeVar = zzeVarArr2[i17];
                if (zzeVar != null) {
                    iZzy += zzqj.zzc(7, zzeVar);
                }
                i17++;
            }
        }
        String str2 = this.zzpp;
        if (str2 != null && !str2.equals("")) {
            iZzy += zzuo.zzb(9, this.zzpp);
        }
        String str3 = this.zzpq;
        if (str3 != null && !str3.equals("")) {
            iZzy += zzuo.zzb(10, this.zzpq);
        }
        String str4 = this.zzpr;
        if (str4 != null && !str4.equals("0")) {
            iZzy += zzuo.zzb(12, this.zzpr);
        }
        String str5 = this.version;
        if (str5 != null && !str5.equals("")) {
            iZzy += zzuo.zzb(13, this.version);
        }
        zzc.zza zzaVar = this.zzps;
        if (zzaVar != null) {
            iZzy += zzqj.zzc(14, zzaVar);
        }
        if (Float.floatToIntBits(this.zzpt) != Float.floatToIntBits(BitmapDescriptorFactory.HUE_RED)) {
            iZzy += zzuo.zzbb(15) + 4;
        }
        String[] strArr3 = this.zzpv;
        if (strArr3 != null && strArr3.length > 0) {
            int i18 = 0;
            int iZzda2 = 0;
            int i19 = 0;
            while (true) {
                String[] strArr4 = this.zzpv;
                if (i18 >= strArr4.length) {
                    break;
                }
                String str6 = strArr4[i18];
                if (str6 != null) {
                    i19++;
                    iZzda2 += zzuo.zzda(str6);
                }
                i18++;
            }
            iZzy = iZzy + iZzda2 + (i19 * 2);
        }
        int i20 = this.zzpw;
        if (i20 != 0) {
            iZzy += zzuo.zzi(17, i20);
        }
        if (this.zzpu) {
            iZzy += zzuo.zzbb(18) + 1;
        }
        String[] strArr5 = this.zzph;
        if (strArr5 == null || strArr5.length <= 0) {
            return iZzy;
        }
        int iZzda3 = 0;
        int i21 = 0;
        while (true) {
            String[] strArr6 = this.zzph;
            if (i9 >= strArr6.length) {
                return iZzy + iZzda3 + (i21 * 2);
            }
            String str7 = strArr6[i9];
            if (str7 != null) {
                i21++;
                iZzda3 += zzuo.zzda(str7);
            }
            i9++;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzuw
    public final /* synthetic */ zzuw zza(zzun zzunVar) throws zzuv {
        while (true) {
            int iZzni = zzunVar.zzni();
            switch (iZzni) {
                case 0:
                    return this;
                case 10:
                    int iZzb = zzuz.zzb(zzunVar, 10);
                    String[] strArr = this.zzpi;
                    int length = strArr == null ? 0 : strArr.length;
                    int i9 = iZzb + length;
                    String[] strArr2 = new String[i9];
                    if (length != 0) {
                        System.arraycopy(strArr, 0, strArr2, 0, length);
                    }
                    while (length < i9 - 1) {
                        strArr2[length] = zzunVar.readString();
                        zzunVar.zzni();
                        length++;
                    }
                    strArr2[length] = zzunVar.readString();
                    this.zzpi = strArr2;
                    break;
                case 18:
                    int iZzb2 = zzuz.zzb(zzunVar, 18);
                    zzl[] zzlVarArr = this.zzpj;
                    int length2 = zzlVarArr == null ? 0 : zzlVarArr.length;
                    int i10 = iZzb2 + length2;
                    zzl[] zzlVarArr2 = new zzl[i10];
                    if (length2 != 0) {
                        System.arraycopy(zzlVarArr, 0, zzlVarArr2, 0, length2);
                    }
                    while (length2 < i10 - 1) {
                        zzl zzlVar = new zzl();
                        zzlVarArr2[length2] = zzlVar;
                        zzunVar.zza(zzlVar);
                        zzunVar.zzni();
                        length2++;
                    }
                    zzl zzlVar2 = new zzl();
                    zzlVarArr2[length2] = zzlVar2;
                    zzunVar.zza(zzlVar2);
                    this.zzpj = zzlVarArr2;
                    break;
                case 26:
                    int iZzb3 = zzuz.zzb(zzunVar, 26);
                    zzc.zzd[] zzdVarArr = this.zzpk;
                    int length3 = zzdVarArr == null ? 0 : zzdVarArr.length;
                    int i11 = iZzb3 + length3;
                    zzc.zzd[] zzdVarArr2 = new zzc.zzd[i11];
                    if (length3 != 0) {
                        System.arraycopy(zzdVarArr, 0, zzdVarArr2, 0, length3);
                    }
                    while (length3 < i11 - 1) {
                        zzdVarArr2[length3] = (zzc.zzd) zzunVar.zza(zzc.zzd.zza());
                        zzunVar.zzni();
                        length3++;
                    }
                    zzdVarArr2[length3] = (zzc.zzd) zzunVar.zza(zzc.zzd.zza());
                    this.zzpk = zzdVarArr2;
                    break;
                case 34:
                    int iZzb4 = zzuz.zzb(zzunVar, 34);
                    zzc.zzb[] zzbVarArr = this.zzpl;
                    int length4 = zzbVarArr == null ? 0 : zzbVarArr.length;
                    int i12 = iZzb4 + length4;
                    zzc.zzb[] zzbVarArr2 = new zzc.zzb[i12];
                    if (length4 != 0) {
                        System.arraycopy(zzbVarArr, 0, zzbVarArr2, 0, length4);
                    }
                    while (length4 < i12 - 1) {
                        zzbVarArr2[length4] = (zzc.zzb) zzunVar.zza(zzc.zzb.zza());
                        zzunVar.zzni();
                        length4++;
                    }
                    zzbVarArr2[length4] = (zzc.zzb) zzunVar.zza(zzc.zzb.zza());
                    this.zzpl = zzbVarArr2;
                    break;
                case 42:
                    int iZzb5 = zzuz.zzb(zzunVar, 42);
                    zzc.zzb[] zzbVarArr3 = this.zzpm;
                    int length5 = zzbVarArr3 == null ? 0 : zzbVarArr3.length;
                    int i13 = iZzb5 + length5;
                    zzc.zzb[] zzbVarArr4 = new zzc.zzb[i13];
                    if (length5 != 0) {
                        System.arraycopy(zzbVarArr3, 0, zzbVarArr4, 0, length5);
                    }
                    while (length5 < i13 - 1) {
                        zzbVarArr4[length5] = (zzc.zzb) zzunVar.zza(zzc.zzb.zza());
                        zzunVar.zzni();
                        length5++;
                    }
                    zzbVarArr4[length5] = (zzc.zzb) zzunVar.zza(zzc.zzb.zza());
                    this.zzpm = zzbVarArr4;
                    break;
                case 50:
                    int iZzb6 = zzuz.zzb(zzunVar, 50);
                    zzc.zzb[] zzbVarArr5 = this.zzpn;
                    int length6 = zzbVarArr5 == null ? 0 : zzbVarArr5.length;
                    int i14 = iZzb6 + length6;
                    zzc.zzb[] zzbVarArr6 = new zzc.zzb[i14];
                    if (length6 != 0) {
                        System.arraycopy(zzbVarArr5, 0, zzbVarArr6, 0, length6);
                    }
                    while (length6 < i14 - 1) {
                        zzbVarArr6[length6] = (zzc.zzb) zzunVar.zza(zzc.zzb.zza());
                        zzunVar.zzni();
                        length6++;
                    }
                    zzbVarArr6[length6] = (zzc.zzb) zzunVar.zza(zzc.zzb.zza());
                    this.zzpn = zzbVarArr6;
                    break;
                case 58:
                    int iZzb7 = zzuz.zzb(zzunVar, 58);
                    zzc.zze[] zzeVarArr = this.zzpo;
                    int length7 = zzeVarArr == null ? 0 : zzeVarArr.length;
                    int i15 = iZzb7 + length7;
                    zzc.zze[] zzeVarArr2 = new zzc.zze[i15];
                    if (length7 != 0) {
                        System.arraycopy(zzeVarArr, 0, zzeVarArr2, 0, length7);
                    }
                    while (length7 < i15 - 1) {
                        zzeVarArr2[length7] = (zzc.zze) zzunVar.zza(zzc.zze.zza());
                        zzunVar.zzni();
                        length7++;
                    }
                    zzeVarArr2[length7] = (zzc.zze) zzunVar.zza(zzc.zze.zza());
                    this.zzpo = zzeVarArr2;
                    break;
                case 74:
                    this.zzpp = zzunVar.readString();
                    break;
                case 82:
                    this.zzpq = zzunVar.readString();
                    break;
                case 98:
                    this.zzpr = zzunVar.readString();
                    break;
                case 106:
                    this.version = zzunVar.readString();
                    break;
                case 114:
                    zzc.zza zzaVar = (zzc.zza) zzunVar.zza(zzc.zza.zza());
                    zzc.zza zzaVar2 = this.zzps;
                    if (zzaVar2 != null) {
                        zzaVar = (zzc.zza) ((zzrc) zzaVar2.zzpd().zza((zzc.zza.C6854zza) zzaVar).zzpm());
                    }
                    this.zzps = zzaVar;
                    break;
                case 125:
                    this.zzpt = Float.intBitsToFloat(zzunVar.zzoc());
                    break;
                case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /* 130 */:
                    int iZzb8 = zzuz.zzb(zzunVar, TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
                    String[] strArr3 = this.zzpv;
                    int length8 = strArr3 == null ? 0 : strArr3.length;
                    int i16 = iZzb8 + length8;
                    String[] strArr4 = new String[i16];
                    if (length8 != 0) {
                        System.arraycopy(strArr3, 0, strArr4, 0, length8);
                    }
                    while (length8 < i16 - 1) {
                        strArr4[length8] = zzunVar.readString();
                        zzunVar.zzni();
                        length8++;
                    }
                    strArr4[length8] = zzunVar.readString();
                    this.zzpv = strArr4;
                    break;
                case 136:
                    this.zzpw = zzunVar.zzoa();
                    break;
                case 144:
                    this.zzpu = zzunVar.zzno();
                    break;
                case 154:
                    int iZzb9 = zzuz.zzb(zzunVar, 154);
                    String[] strArr5 = this.zzph;
                    int length9 = strArr5 == null ? 0 : strArr5.length;
                    int i17 = iZzb9 + length9;
                    String[] strArr6 = new String[i17];
                    if (length9 != 0) {
                        System.arraycopy(strArr5, 0, strArr6, 0, length9);
                    }
                    while (length9 < i17 - 1) {
                        strArr6[length9] = zzunVar.readString();
                        zzunVar.zzni();
                        length9++;
                    }
                    strArr6[length9] = zzunVar.readString();
                    this.zzph = strArr6;
                    break;
                default:
                    if (!super.zza(zzunVar, iZzni)) {
                        return this;
                    }
                    break;
            }
        }
    }
}
