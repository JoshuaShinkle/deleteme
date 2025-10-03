package com.google.android.gms.internal.gtm;

/* loaded from: classes2.dex */
public final class zzl extends zzuq<zzl> {
    private static volatile zzl[] zzqm;
    public int type = 1;
    public String string = "";
    public zzl[] zzqn = zzaa();
    public zzl[] zzqo = zzaa();
    public zzl[] zzqp = zzaa();
    public String zzqq = "";
    public String zzqr = "";
    public long zzqs = 0;
    public boolean zzqt = false;
    public zzl[] zzqu = zzaa();
    public int[] zzqv = zzuz.zzbcw;
    public boolean zzqw = false;

    public zzl() {
        this.zzbhb = null;
        this.zzbhl = -1;
    }

    public static zzl[] zzaa() {
        if (zzqm == null) {
            synchronized (zzuu.zzbhk) {
                if (zzqm == null) {
                    zzqm = new zzl[0];
                }
            }
        }
        return zzqm;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.gtm.zzuw
    /* renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzl zza(zzun zzunVar) throws zzuv {
        while (true) {
            int iZzni = zzunVar.zzni();
            switch (iZzni) {
                case 0:
                    return this;
                case 8:
                    int position = zzunVar.getPosition();
                    try {
                        int iZzoa = zzunVar.zzoa();
                        if (iZzoa <= 0 || iZzoa > 8) {
                            StringBuilder sb = new StringBuilder(36);
                            sb.append(iZzoa);
                            sb.append(" is not a valid enum Type");
                            throw new IllegalArgumentException(sb.toString());
                            break;
                        } else {
                            this.type = iZzoa;
                            break;
                        }
                    } catch (IllegalArgumentException unused) {
                        zzunVar.zzbz(position);
                        zza(zzunVar, iZzni);
                        break;
                    }
                    break;
                case 18:
                    this.string = zzunVar.readString();
                    break;
                case 26:
                    int iZzb = zzuz.zzb(zzunVar, 26);
                    zzl[] zzlVarArr = this.zzqn;
                    int length = zzlVarArr == null ? 0 : zzlVarArr.length;
                    int i9 = iZzb + length;
                    zzl[] zzlVarArr2 = new zzl[i9];
                    if (length != 0) {
                        System.arraycopy(zzlVarArr, 0, zzlVarArr2, 0, length);
                    }
                    while (length < i9 - 1) {
                        zzl zzlVar = new zzl();
                        zzlVarArr2[length] = zzlVar;
                        zzunVar.zza(zzlVar);
                        zzunVar.zzni();
                        length++;
                    }
                    zzl zzlVar2 = new zzl();
                    zzlVarArr2[length] = zzlVar2;
                    zzunVar.zza(zzlVar2);
                    this.zzqn = zzlVarArr2;
                    break;
                case 34:
                    int iZzb2 = zzuz.zzb(zzunVar, 34);
                    zzl[] zzlVarArr3 = this.zzqo;
                    int length2 = zzlVarArr3 == null ? 0 : zzlVarArr3.length;
                    int i10 = iZzb2 + length2;
                    zzl[] zzlVarArr4 = new zzl[i10];
                    if (length2 != 0) {
                        System.arraycopy(zzlVarArr3, 0, zzlVarArr4, 0, length2);
                    }
                    while (length2 < i10 - 1) {
                        zzl zzlVar3 = new zzl();
                        zzlVarArr4[length2] = zzlVar3;
                        zzunVar.zza(zzlVar3);
                        zzunVar.zzni();
                        length2++;
                    }
                    zzl zzlVar4 = new zzl();
                    zzlVarArr4[length2] = zzlVar4;
                    zzunVar.zza(zzlVar4);
                    this.zzqo = zzlVarArr4;
                    break;
                case 42:
                    int iZzb3 = zzuz.zzb(zzunVar, 42);
                    zzl[] zzlVarArr5 = this.zzqp;
                    int length3 = zzlVarArr5 == null ? 0 : zzlVarArr5.length;
                    int i11 = iZzb3 + length3;
                    zzl[] zzlVarArr6 = new zzl[i11];
                    if (length3 != 0) {
                        System.arraycopy(zzlVarArr5, 0, zzlVarArr6, 0, length3);
                    }
                    while (length3 < i11 - 1) {
                        zzl zzlVar5 = new zzl();
                        zzlVarArr6[length3] = zzlVar5;
                        zzunVar.zza(zzlVar5);
                        zzunVar.zzni();
                        length3++;
                    }
                    zzl zzlVar6 = new zzl();
                    zzlVarArr6[length3] = zzlVar6;
                    zzunVar.zza(zzlVar6);
                    this.zzqp = zzlVarArr6;
                    break;
                case 50:
                    this.zzqq = zzunVar.readString();
                    break;
                case 58:
                    this.zzqr = zzunVar.readString();
                    break;
                case 64:
                    this.zzqs = zzunVar.zzob();
                    break;
                case 72:
                    this.zzqw = zzunVar.zzno();
                    break;
                case 80:
                    int iZzb4 = zzuz.zzb(zzunVar, 80);
                    int[] iArr = new int[iZzb4];
                    int i12 = 0;
                    for (int i13 = 0; i13 < iZzb4; i13++) {
                        if (i13 != 0) {
                            zzunVar.zzni();
                        }
                        int position2 = zzunVar.getPosition();
                        try {
                            iArr[i12] = zzc(zzunVar.zzoa());
                            i12++;
                        } catch (IllegalArgumentException unused2) {
                            zzunVar.zzbz(position2);
                            zza(zzunVar, iZzni);
                        }
                    }
                    if (i12 == 0) {
                        break;
                    } else {
                        int[] iArr2 = this.zzqv;
                        int length4 = iArr2 == null ? 0 : iArr2.length;
                        if (length4 != 0 || i12 != iZzb4) {
                            int[] iArr3 = new int[length4 + i12];
                            if (length4 != 0) {
                                System.arraycopy(iArr2, 0, iArr3, 0, length4);
                            }
                            System.arraycopy(iArr, 0, iArr3, length4, i12);
                            this.zzqv = iArr3;
                            break;
                        } else {
                            this.zzqv = iArr;
                            break;
                        }
                    }
                case 82:
                    int iZzaq = zzunVar.zzaq(zzunVar.zzoa());
                    int position3 = zzunVar.getPosition();
                    int i14 = 0;
                    while (zzunVar.zzrv() > 0) {
                        try {
                            zzc(zzunVar.zzoa());
                            i14++;
                        } catch (IllegalArgumentException unused3) {
                        }
                    }
                    if (i14 != 0) {
                        zzunVar.zzbz(position3);
                        int[] iArr4 = this.zzqv;
                        int length5 = iArr4 == null ? 0 : iArr4.length;
                        int[] iArr5 = new int[i14 + length5];
                        if (length5 != 0) {
                            System.arraycopy(iArr4, 0, iArr5, 0, length5);
                        }
                        while (zzunVar.zzrv() > 0) {
                            int position4 = zzunVar.getPosition();
                            try {
                                iArr5[length5] = zzc(zzunVar.zzoa());
                                length5++;
                            } catch (IllegalArgumentException unused4) {
                                zzunVar.zzbz(position4);
                                zza(zzunVar, 80);
                            }
                        }
                        this.zzqv = iArr5;
                    }
                    zzunVar.zzar(iZzaq);
                    break;
                case 90:
                    int iZzb5 = zzuz.zzb(zzunVar, 90);
                    zzl[] zzlVarArr7 = this.zzqu;
                    int length6 = zzlVarArr7 == null ? 0 : zzlVarArr7.length;
                    int i15 = iZzb5 + length6;
                    zzl[] zzlVarArr8 = new zzl[i15];
                    if (length6 != 0) {
                        System.arraycopy(zzlVarArr7, 0, zzlVarArr8, 0, length6);
                    }
                    while (length6 < i15 - 1) {
                        zzl zzlVar7 = new zzl();
                        zzlVarArr8[length6] = zzlVar7;
                        zzunVar.zza(zzlVar7);
                        zzunVar.zzni();
                        length6++;
                    }
                    zzl zzlVar8 = new zzl();
                    zzlVarArr8[length6] = zzlVar8;
                    zzunVar.zza(zzlVar8);
                    this.zzqu = zzlVarArr8;
                    break;
                case 96:
                    this.zzqt = zzunVar.zzno();
                    break;
                default:
                    if (!super.zza(zzunVar, iZzni)) {
                        return this;
                    }
                    break;
            }
        }
    }

    private static int zzc(int i9) {
        if (i9 > 0 && i9 <= 17) {
            return i9;
        }
        StringBuilder sb = new StringBuilder(40);
        sb.append(i9);
        sb.append(" is not a valid enum Escaping");
        throw new IllegalArgumentException(sb.toString());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzl)) {
            return false;
        }
        zzl zzlVar = (zzl) obj;
        if (this.type != zzlVar.type) {
            return false;
        }
        String str = this.string;
        if (str == null) {
            if (zzlVar.string != null) {
                return false;
            }
        } else if (!str.equals(zzlVar.string)) {
            return false;
        }
        if (!zzuu.equals(this.zzqn, zzlVar.zzqn) || !zzuu.equals(this.zzqo, zzlVar.zzqo) || !zzuu.equals(this.zzqp, zzlVar.zzqp)) {
            return false;
        }
        String str2 = this.zzqq;
        if (str2 == null) {
            if (zzlVar.zzqq != null) {
                return false;
            }
        } else if (!str2.equals(zzlVar.zzqq)) {
            return false;
        }
        String str3 = this.zzqr;
        if (str3 == null) {
            if (zzlVar.zzqr != null) {
                return false;
            }
        } else if (!str3.equals(zzlVar.zzqr)) {
            return false;
        }
        if (this.zzqs != zzlVar.zzqs || this.zzqt != zzlVar.zzqt || !zzuu.equals(this.zzqu, zzlVar.zzqu) || !zzuu.equals(this.zzqv, zzlVar.zzqv) || this.zzqw != zzlVar.zzqw) {
            return false;
        }
        zzus zzusVar = this.zzbhb;
        if (zzusVar != null && !zzusVar.isEmpty()) {
            return this.zzbhb.equals(zzlVar.zzbhb);
        }
        zzus zzusVar2 = zzlVar.zzbhb;
        return zzusVar2 == null || zzusVar2.isEmpty();
    }

    public final int hashCode() {
        int iHashCode = (((zzl.class.getName().hashCode() + 527) * 31) + this.type) * 31;
        String str = this.string;
        int iHashCode2 = 0;
        int iHashCode3 = (((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + zzuu.hashCode(this.zzqn)) * 31) + zzuu.hashCode(this.zzqo)) * 31) + zzuu.hashCode(this.zzqp)) * 31;
        String str2 = this.zzqq;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.zzqr;
        int iHashCode5 = str3 == null ? 0 : str3.hashCode();
        long j9 = this.zzqs;
        int iHashCode6 = (((((((((((iHashCode4 + iHashCode5) * 31) + ((int) (j9 ^ (j9 >>> 32)))) * 31) + (this.zzqt ? 1231 : 1237)) * 31) + zzuu.hashCode(this.zzqu)) * 31) + zzuu.hashCode(this.zzqv)) * 31) + (this.zzqw ? 1231 : 1237)) * 31;
        zzus zzusVar = this.zzbhb;
        if (zzusVar != null && !zzusVar.isEmpty()) {
            iHashCode2 = this.zzbhb.hashCode();
        }
        return iHashCode6 + iHashCode2;
    }

    @Override // com.google.android.gms.internal.gtm.zzuq, com.google.android.gms.internal.gtm.zzuw
    public final void zza(zzuo zzuoVar) throws zzup {
        zzuoVar.zze(1, this.type);
        String str = this.string;
        if (str != null && !str.equals("")) {
            zzuoVar.zza(2, this.string);
        }
        zzl[] zzlVarArr = this.zzqn;
        int i9 = 0;
        if (zzlVarArr != null && zzlVarArr.length > 0) {
            int i10 = 0;
            while (true) {
                zzl[] zzlVarArr2 = this.zzqn;
                if (i10 >= zzlVarArr2.length) {
                    break;
                }
                zzl zzlVar = zzlVarArr2[i10];
                if (zzlVar != null) {
                    zzuoVar.zza(3, zzlVar);
                }
                i10++;
            }
        }
        zzl[] zzlVarArr3 = this.zzqo;
        if (zzlVarArr3 != null && zzlVarArr3.length > 0) {
            int i11 = 0;
            while (true) {
                zzl[] zzlVarArr4 = this.zzqo;
                if (i11 >= zzlVarArr4.length) {
                    break;
                }
                zzl zzlVar2 = zzlVarArr4[i11];
                if (zzlVar2 != null) {
                    zzuoVar.zza(4, zzlVar2);
                }
                i11++;
            }
        }
        zzl[] zzlVarArr5 = this.zzqp;
        if (zzlVarArr5 != null && zzlVarArr5.length > 0) {
            int i12 = 0;
            while (true) {
                zzl[] zzlVarArr6 = this.zzqp;
                if (i12 >= zzlVarArr6.length) {
                    break;
                }
                zzl zzlVar3 = zzlVarArr6[i12];
                if (zzlVar3 != null) {
                    zzuoVar.zza(5, zzlVar3);
                }
                i12++;
            }
        }
        String str2 = this.zzqq;
        if (str2 != null && !str2.equals("")) {
            zzuoVar.zza(6, this.zzqq);
        }
        String str3 = this.zzqr;
        if (str3 != null && !str3.equals("")) {
            zzuoVar.zza(7, this.zzqr);
        }
        long j9 = this.zzqs;
        if (j9 != 0) {
            zzuoVar.zzi(8, j9);
        }
        boolean z8 = this.zzqw;
        if (z8) {
            zzuoVar.zzb(9, z8);
        }
        int[] iArr = this.zzqv;
        if (iArr != null && iArr.length > 0) {
            int i13 = 0;
            while (true) {
                int[] iArr2 = this.zzqv;
                if (i13 >= iArr2.length) {
                    break;
                }
                zzuoVar.zze(10, iArr2[i13]);
                i13++;
            }
        }
        zzl[] zzlVarArr7 = this.zzqu;
        if (zzlVarArr7 != null && zzlVarArr7.length > 0) {
            while (true) {
                zzl[] zzlVarArr8 = this.zzqu;
                if (i9 >= zzlVarArr8.length) {
                    break;
                }
                zzl zzlVar4 = zzlVarArr8[i9];
                if (zzlVar4 != null) {
                    zzuoVar.zza(11, zzlVar4);
                }
                i9++;
            }
        }
        boolean z9 = this.zzqt;
        if (z9) {
            zzuoVar.zzb(12, z9);
        }
        super.zza(zzuoVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzuq, com.google.android.gms.internal.gtm.zzuw
    public final int zzy() {
        int[] iArr;
        int iZzy = super.zzy() + zzuo.zzi(1, this.type);
        String str = this.string;
        if (str != null && !str.equals("")) {
            iZzy += zzuo.zzb(2, this.string);
        }
        zzl[] zzlVarArr = this.zzqn;
        int i9 = 0;
        if (zzlVarArr != null && zzlVarArr.length > 0) {
            int i10 = 0;
            while (true) {
                zzl[] zzlVarArr2 = this.zzqn;
                if (i10 >= zzlVarArr2.length) {
                    break;
                }
                zzl zzlVar = zzlVarArr2[i10];
                if (zzlVar != null) {
                    iZzy += zzuo.zzb(3, zzlVar);
                }
                i10++;
            }
        }
        zzl[] zzlVarArr3 = this.zzqo;
        if (zzlVarArr3 != null && zzlVarArr3.length > 0) {
            int i11 = 0;
            while (true) {
                zzl[] zzlVarArr4 = this.zzqo;
                if (i11 >= zzlVarArr4.length) {
                    break;
                }
                zzl zzlVar2 = zzlVarArr4[i11];
                if (zzlVar2 != null) {
                    iZzy += zzuo.zzb(4, zzlVar2);
                }
                i11++;
            }
        }
        zzl[] zzlVarArr5 = this.zzqp;
        if (zzlVarArr5 != null && zzlVarArr5.length > 0) {
            int i12 = 0;
            while (true) {
                zzl[] zzlVarArr6 = this.zzqp;
                if (i12 >= zzlVarArr6.length) {
                    break;
                }
                zzl zzlVar3 = zzlVarArr6[i12];
                if (zzlVar3 != null) {
                    iZzy += zzuo.zzb(5, zzlVar3);
                }
                i12++;
            }
        }
        String str2 = this.zzqq;
        if (str2 != null && !str2.equals("")) {
            iZzy += zzuo.zzb(6, this.zzqq);
        }
        String str3 = this.zzqr;
        if (str3 != null && !str3.equals("")) {
            iZzy += zzuo.zzb(7, this.zzqr);
        }
        long j9 = this.zzqs;
        if (j9 != 0) {
            iZzy += zzuo.zzd(8, j9);
        }
        if (this.zzqw) {
            iZzy += zzuo.zzbb(9) + 1;
        }
        int[] iArr2 = this.zzqv;
        if (iArr2 != null && iArr2.length > 0) {
            int i13 = 0;
            int iZzbc = 0;
            while (true) {
                iArr = this.zzqv;
                if (i13 >= iArr.length) {
                    break;
                }
                iZzbc += zzuo.zzbc(iArr[i13]);
                i13++;
            }
            iZzy = iZzy + iZzbc + (iArr.length * 1);
        }
        zzl[] zzlVarArr7 = this.zzqu;
        if (zzlVarArr7 != null && zzlVarArr7.length > 0) {
            while (true) {
                zzl[] zzlVarArr8 = this.zzqu;
                if (i9 >= zzlVarArr8.length) {
                    break;
                }
                zzl zzlVar4 = zzlVarArr8[i9];
                if (zzlVar4 != null) {
                    iZzy += zzuo.zzb(11, zzlVar4);
                }
                i9++;
            }
        }
        return this.zzqt ? iZzy + zzuo.zzbb(12) + 1 : iZzy;
    }
}
