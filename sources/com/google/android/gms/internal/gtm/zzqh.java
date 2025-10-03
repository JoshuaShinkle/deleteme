package com.google.android.gms.internal.gtm;

import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzqh implements zzsy {
    private int tag;
    private final zzqe zzawp;
    private int zzawq;
    private int zzawr = 0;

    private zzqh(zzqe zzqeVar) {
        zzqe zzqeVar2 = (zzqe) zzre.zza(zzqeVar, "input");
        this.zzawp = zzqeVar2;
        zzqeVar2.zzawi = this;
    }

    public static zzqh zza(zzqe zzqeVar) {
        zzqh zzqhVar = zzqeVar.zzawi;
        return zzqhVar != null ? zzqhVar : new zzqh(zzqeVar);
    }

    private final void zzat(int i9) throws zzrl {
        if ((this.tag & 7) != i9) {
            throw zzrk.zzpt();
        }
    }

    private static void zzau(int i9) throws zzrk {
        if ((i9 & 7) != 0) {
            throw zzrk.zzpv();
        }
    }

    private static void zzav(int i9) throws zzrk {
        if ((i9 & 3) != 0) {
            throw zzrk.zzpv();
        }
    }

    private final void zzaw(int i9) throws zzrk {
        if (this.zzawp.zznz() != i9) {
            throw zzrk.zzpp();
        }
    }

    private final <T> T zzc(zzsz<T> zzszVar, zzqp zzqpVar) throws zzrk {
        int iZznr = this.zzawp.zznr();
        zzqe zzqeVar = this.zzawp;
        if (zzqeVar.zzawf >= zzqeVar.zzawg) {
            throw zzrk.zzpu();
        }
        int iZzaq = zzqeVar.zzaq(iZznr);
        T tNewInstance = zzszVar.newInstance();
        this.zzawp.zzawf++;
        zzszVar.zza(tNewInstance, this, zzqpVar);
        zzszVar.zzt(tNewInstance);
        this.zzawp.zzan(0);
        r5.zzawf--;
        this.zzawp.zzar(iZzaq);
        return tNewInstance;
    }

    private final <T> T zzd(zzsz<T> zzszVar, zzqp zzqpVar) {
        int i9 = this.zzawq;
        this.zzawq = ((this.tag >>> 3) << 3) | 4;
        try {
            T tNewInstance = zzszVar.newInstance();
            zzszVar.zza(tNewInstance, this, zzqpVar);
            zzszVar.zzt(tNewInstance);
            if (this.tag == this.zzawq) {
                return tNewInstance;
            }
            throw zzrk.zzpv();
        } finally {
            this.zzawq = i9;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final int getTag() {
        return this.tag;
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final double readDouble() throws zzrl {
        zzat(1);
        return this.zzawp.readDouble();
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final float readFloat() throws zzrl {
        zzat(5);
        return this.zzawp.readFloat();
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final String readString() throws zzrl {
        zzat(2);
        return this.zzawp.readString();
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final void readStringList(List<String> list) throws zzrl {
        zza(list, false);
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final <T> T zzb(zzsz<T> zzszVar, zzqp zzqpVar) throws zzrl {
        zzat(3);
        return (T) zzd(zzszVar, zzqpVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final void zzg(List<Double> list) throws zzrk {
        int iZzni;
        int iZzni2;
        if (!(list instanceof zzqm)) {
            int i9 = this.tag & 7;
            if (i9 == 1) {
                do {
                    list.add(Double.valueOf(this.zzawp.readDouble()));
                    if (this.zzawp.zzny()) {
                        return;
                    } else {
                        iZzni = this.zzawp.zzni();
                    }
                } while (iZzni == this.tag);
                this.zzawr = iZzni;
                return;
            }
            if (i9 != 2) {
                throw zzrk.zzpt();
            }
            int iZznr = this.zzawp.zznr();
            zzau(iZznr);
            int iZznz = this.zzawp.zznz() + iZznr;
            do {
                list.add(Double.valueOf(this.zzawp.readDouble()));
            } while (this.zzawp.zznz() < iZznz);
            return;
        }
        zzqm zzqmVar = (zzqm) list;
        int i10 = this.tag & 7;
        if (i10 == 1) {
            do {
                zzqmVar.zzd(this.zzawp.readDouble());
                if (this.zzawp.zzny()) {
                    return;
                } else {
                    iZzni2 = this.zzawp.zzni();
                }
            } while (iZzni2 == this.tag);
            this.zzawr = iZzni2;
            return;
        }
        if (i10 != 2) {
            throw zzrk.zzpt();
        }
        int iZznr2 = this.zzawp.zznr();
        zzau(iZznr2);
        int iZznz2 = this.zzawp.zznz() + iZznr2;
        do {
            zzqmVar.zzd(this.zzawp.readDouble());
        } while (this.zzawp.zznz() < iZznz2);
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final void zzh(List<Float> list) throws zzrk {
        int iZzni;
        int iZzni2;
        if (!(list instanceof zzqz)) {
            int i9 = this.tag & 7;
            if (i9 == 2) {
                int iZznr = this.zzawp.zznr();
                zzav(iZznr);
                int iZznz = this.zzawp.zznz() + iZznr;
                do {
                    list.add(Float.valueOf(this.zzawp.readFloat()));
                } while (this.zzawp.zznz() < iZznz);
                return;
            }
            if (i9 != 5) {
                throw zzrk.zzpt();
            }
            do {
                list.add(Float.valueOf(this.zzawp.readFloat()));
                if (this.zzawp.zzny()) {
                    return;
                } else {
                    iZzni = this.zzawp.zzni();
                }
            } while (iZzni == this.tag);
            this.zzawr = iZzni;
            return;
        }
        zzqz zzqzVar = (zzqz) list;
        int i10 = this.tag & 7;
        if (i10 == 2) {
            int iZznr2 = this.zzawp.zznr();
            zzav(iZznr2);
            int iZznz2 = this.zzawp.zznz() + iZznr2;
            do {
                zzqzVar.zzc(this.zzawp.readFloat());
            } while (this.zzawp.zznz() < iZznz2);
            return;
        }
        if (i10 != 5) {
            throw zzrk.zzpt();
        }
        do {
            zzqzVar.zzc(this.zzawp.readFloat());
            if (this.zzawp.zzny()) {
                return;
            } else {
                iZzni2 = this.zzawp.zzni();
            }
        } while (iZzni2 == this.tag);
        this.zzawr = iZzni2;
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final void zzi(List<Long> list) throws zzrk {
        int iZzni;
        int iZzni2;
        if (!(list instanceof zzry)) {
            int i9 = this.tag & 7;
            if (i9 == 0) {
                do {
                    list.add(Long.valueOf(this.zzawp.zznj()));
                    if (this.zzawp.zzny()) {
                        return;
                    } else {
                        iZzni = this.zzawp.zzni();
                    }
                } while (iZzni == this.tag);
                this.zzawr = iZzni;
                return;
            }
            if (i9 != 2) {
                throw zzrk.zzpt();
            }
            int iZznz = this.zzawp.zznz() + this.zzawp.zznr();
            do {
                list.add(Long.valueOf(this.zzawp.zznj()));
            } while (this.zzawp.zznz() < iZznz);
            zzaw(iZznz);
            return;
        }
        zzry zzryVar = (zzry) list;
        int i10 = this.tag & 7;
        if (i10 == 0) {
            do {
                zzryVar.zzaa(this.zzawp.zznj());
                if (this.zzawp.zzny()) {
                    return;
                } else {
                    iZzni2 = this.zzawp.zzni();
                }
            } while (iZzni2 == this.tag);
            this.zzawr = iZzni2;
            return;
        }
        if (i10 != 2) {
            throw zzrk.zzpt();
        }
        int iZznz2 = this.zzawp.zznz() + this.zzawp.zznr();
        do {
            zzryVar.zzaa(this.zzawp.zznj());
        } while (this.zzawp.zznz() < iZznz2);
        zzaw(iZznz2);
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final void zzj(List<Long> list) throws zzrk {
        int iZzni;
        int iZzni2;
        if (!(list instanceof zzry)) {
            int i9 = this.tag & 7;
            if (i9 == 0) {
                do {
                    list.add(Long.valueOf(this.zzawp.zznk()));
                    if (this.zzawp.zzny()) {
                        return;
                    } else {
                        iZzni = this.zzawp.zzni();
                    }
                } while (iZzni == this.tag);
                this.zzawr = iZzni;
                return;
            }
            if (i9 != 2) {
                throw zzrk.zzpt();
            }
            int iZznz = this.zzawp.zznz() + this.zzawp.zznr();
            do {
                list.add(Long.valueOf(this.zzawp.zznk()));
            } while (this.zzawp.zznz() < iZznz);
            zzaw(iZznz);
            return;
        }
        zzry zzryVar = (zzry) list;
        int i10 = this.tag & 7;
        if (i10 == 0) {
            do {
                zzryVar.zzaa(this.zzawp.zznk());
                if (this.zzawp.zzny()) {
                    return;
                } else {
                    iZzni2 = this.zzawp.zzni();
                }
            } while (iZzni2 == this.tag);
            this.zzawr = iZzni2;
            return;
        }
        if (i10 != 2) {
            throw zzrk.zzpt();
        }
        int iZznz2 = this.zzawp.zznz() + this.zzawp.zznr();
        do {
            zzryVar.zzaa(this.zzawp.zznk());
        } while (this.zzawp.zznz() < iZznz2);
        zzaw(iZznz2);
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final void zzk(List<Integer> list) throws zzrk {
        int iZzni;
        int iZzni2;
        if (!(list instanceof zzrd)) {
            int i9 = this.tag & 7;
            if (i9 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzawp.zznl()));
                    if (this.zzawp.zzny()) {
                        return;
                    } else {
                        iZzni = this.zzawp.zzni();
                    }
                } while (iZzni == this.tag);
                this.zzawr = iZzni;
                return;
            }
            if (i9 != 2) {
                throw zzrk.zzpt();
            }
            int iZznz = this.zzawp.zznz() + this.zzawp.zznr();
            do {
                list.add(Integer.valueOf(this.zzawp.zznl()));
            } while (this.zzawp.zznz() < iZznz);
            zzaw(iZznz);
            return;
        }
        zzrd zzrdVar = (zzrd) list;
        int i10 = this.tag & 7;
        if (i10 == 0) {
            do {
                zzrdVar.zzbm(this.zzawp.zznl());
                if (this.zzawp.zzny()) {
                    return;
                } else {
                    iZzni2 = this.zzawp.zzni();
                }
            } while (iZzni2 == this.tag);
            this.zzawr = iZzni2;
            return;
        }
        if (i10 != 2) {
            throw zzrk.zzpt();
        }
        int iZznz2 = this.zzawp.zznz() + this.zzawp.zznr();
        do {
            zzrdVar.zzbm(this.zzawp.zznl());
        } while (this.zzawp.zznz() < iZznz2);
        zzaw(iZznz2);
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final void zzl(List<Long> list) throws zzrk {
        int iZzni;
        int iZzni2;
        if (!(list instanceof zzry)) {
            int i9 = this.tag & 7;
            if (i9 == 1) {
                do {
                    list.add(Long.valueOf(this.zzawp.zznm()));
                    if (this.zzawp.zzny()) {
                        return;
                    } else {
                        iZzni = this.zzawp.zzni();
                    }
                } while (iZzni == this.tag);
                this.zzawr = iZzni;
                return;
            }
            if (i9 != 2) {
                throw zzrk.zzpt();
            }
            int iZznr = this.zzawp.zznr();
            zzau(iZznr);
            int iZznz = this.zzawp.zznz() + iZznr;
            do {
                list.add(Long.valueOf(this.zzawp.zznm()));
            } while (this.zzawp.zznz() < iZznz);
            return;
        }
        zzry zzryVar = (zzry) list;
        int i10 = this.tag & 7;
        if (i10 == 1) {
            do {
                zzryVar.zzaa(this.zzawp.zznm());
                if (this.zzawp.zzny()) {
                    return;
                } else {
                    iZzni2 = this.zzawp.zzni();
                }
            } while (iZzni2 == this.tag);
            this.zzawr = iZzni2;
            return;
        }
        if (i10 != 2) {
            throw zzrk.zzpt();
        }
        int iZznr2 = this.zzawp.zznr();
        zzau(iZznr2);
        int iZznz2 = this.zzawp.zznz() + iZznr2;
        do {
            zzryVar.zzaa(this.zzawp.zznm());
        } while (this.zzawp.zznz() < iZznz2);
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final void zzm(List<Integer> list) throws zzrk {
        int iZzni;
        int iZzni2;
        if (!(list instanceof zzrd)) {
            int i9 = this.tag & 7;
            if (i9 == 2) {
                int iZznr = this.zzawp.zznr();
                zzav(iZznr);
                int iZznz = this.zzawp.zznz() + iZznr;
                do {
                    list.add(Integer.valueOf(this.zzawp.zznn()));
                } while (this.zzawp.zznz() < iZznz);
                return;
            }
            if (i9 != 5) {
                throw zzrk.zzpt();
            }
            do {
                list.add(Integer.valueOf(this.zzawp.zznn()));
                if (this.zzawp.zzny()) {
                    return;
                } else {
                    iZzni = this.zzawp.zzni();
                }
            } while (iZzni == this.tag);
            this.zzawr = iZzni;
            return;
        }
        zzrd zzrdVar = (zzrd) list;
        int i10 = this.tag & 7;
        if (i10 == 2) {
            int iZznr2 = this.zzawp.zznr();
            zzav(iZznr2);
            int iZznz2 = this.zzawp.zznz() + iZznr2;
            do {
                zzrdVar.zzbm(this.zzawp.zznn());
            } while (this.zzawp.zznz() < iZznz2);
            return;
        }
        if (i10 != 5) {
            throw zzrk.zzpt();
        }
        do {
            zzrdVar.zzbm(this.zzawp.zznn());
            if (this.zzawp.zzny()) {
                return;
            } else {
                iZzni2 = this.zzawp.zzni();
            }
        } while (iZzni2 == this.tag);
        this.zzawr = iZzni2;
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final void zzn(List<Boolean> list) throws zzrk {
        int iZzni;
        int iZzni2;
        if (!(list instanceof zzpq)) {
            int i9 = this.tag & 7;
            if (i9 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zzawp.zzno()));
                    if (this.zzawp.zzny()) {
                        return;
                    } else {
                        iZzni = this.zzawp.zzni();
                    }
                } while (iZzni == this.tag);
                this.zzawr = iZzni;
                return;
            }
            if (i9 != 2) {
                throw zzrk.zzpt();
            }
            int iZznz = this.zzawp.zznz() + this.zzawp.zznr();
            do {
                list.add(Boolean.valueOf(this.zzawp.zzno()));
            } while (this.zzawp.zznz() < iZznz);
            zzaw(iZznz);
            return;
        }
        zzpq zzpqVar = (zzpq) list;
        int i10 = this.tag & 7;
        if (i10 == 0) {
            do {
                zzpqVar.addBoolean(this.zzawp.zzno());
                if (this.zzawp.zzny()) {
                    return;
                } else {
                    iZzni2 = this.zzawp.zzni();
                }
            } while (iZzni2 == this.tag);
            this.zzawr = iZzni2;
            return;
        }
        if (i10 != 2) {
            throw zzrk.zzpt();
        }
        int iZznz2 = this.zzawp.zznz() + this.zzawp.zznr();
        do {
            zzpqVar.addBoolean(this.zzawp.zzno());
        } while (this.zzawp.zznz() < iZznz2);
        zzaw(iZznz2);
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final long zznj() throws zzrl {
        zzat(0);
        return this.zzawp.zznj();
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final long zznk() throws zzrl {
        zzat(0);
        return this.zzawp.zznk();
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final int zznl() throws zzrl {
        zzat(0);
        return this.zzawp.zznl();
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final long zznm() throws zzrl {
        zzat(1);
        return this.zzawp.zznm();
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final int zznn() throws zzrl {
        zzat(5);
        return this.zzawp.zznn();
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final boolean zzno() throws zzrl {
        zzat(0);
        return this.zzawp.zzno();
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final String zznp() throws zzrl {
        zzat(2);
        return this.zzawp.zznp();
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final zzps zznq() throws zzrl {
        zzat(2);
        return this.zzawp.zznq();
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final int zznr() throws zzrl {
        zzat(0);
        return this.zzawp.zznr();
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final int zzns() throws zzrl {
        zzat(0);
        return this.zzawp.zzns();
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final int zznt() throws zzrl {
        zzat(5);
        return this.zzawp.zznt();
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final long zznu() throws zzrl {
        zzat(1);
        return this.zzawp.zznu();
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final int zznv() throws zzrl {
        zzat(0);
        return this.zzawp.zznv();
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final long zznw() throws zzrl {
        zzat(0);
        return this.zzawp.zznw();
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final void zzo(List<String> list) throws zzrl {
        zza(list, true);
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final int zzog() {
        int i9 = this.zzawr;
        if (i9 != 0) {
            this.tag = i9;
            this.zzawr = 0;
        } else {
            this.tag = this.zzawp.zzni();
        }
        int i10 = this.tag;
        if (i10 == 0 || i10 == this.zzawq) {
            return Integer.MAX_VALUE;
        }
        return i10 >>> 3;
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final boolean zzoh() {
        int i9;
        if (this.zzawp.zzny() || (i9 = this.tag) == this.zzawq) {
            return false;
        }
        return this.zzawp.zzao(i9);
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final void zzp(List<zzps> list) throws zzrl {
        int iZzni;
        if ((this.tag & 7) != 2) {
            throw zzrk.zzpt();
        }
        do {
            list.add(zznq());
            if (this.zzawp.zzny()) {
                return;
            } else {
                iZzni = this.zzawp.zzni();
            }
        } while (iZzni == this.tag);
        this.zzawr = iZzni;
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final void zzq(List<Integer> list) throws zzrk {
        int iZzni;
        int iZzni2;
        if (!(list instanceof zzrd)) {
            int i9 = this.tag & 7;
            if (i9 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzawp.zznr()));
                    if (this.zzawp.zzny()) {
                        return;
                    } else {
                        iZzni = this.zzawp.zzni();
                    }
                } while (iZzni == this.tag);
                this.zzawr = iZzni;
                return;
            }
            if (i9 != 2) {
                throw zzrk.zzpt();
            }
            int iZznz = this.zzawp.zznz() + this.zzawp.zznr();
            do {
                list.add(Integer.valueOf(this.zzawp.zznr()));
            } while (this.zzawp.zznz() < iZznz);
            zzaw(iZznz);
            return;
        }
        zzrd zzrdVar = (zzrd) list;
        int i10 = this.tag & 7;
        if (i10 == 0) {
            do {
                zzrdVar.zzbm(this.zzawp.zznr());
                if (this.zzawp.zzny()) {
                    return;
                } else {
                    iZzni2 = this.zzawp.zzni();
                }
            } while (iZzni2 == this.tag);
            this.zzawr = iZzni2;
            return;
        }
        if (i10 != 2) {
            throw zzrk.zzpt();
        }
        int iZznz2 = this.zzawp.zznz() + this.zzawp.zznr();
        do {
            zzrdVar.zzbm(this.zzawp.zznr());
        } while (this.zzawp.zznz() < iZznz2);
        zzaw(iZznz2);
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final void zzr(List<Integer> list) throws zzrk {
        int iZzni;
        int iZzni2;
        if (!(list instanceof zzrd)) {
            int i9 = this.tag & 7;
            if (i9 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzawp.zzns()));
                    if (this.zzawp.zzny()) {
                        return;
                    } else {
                        iZzni = this.zzawp.zzni();
                    }
                } while (iZzni == this.tag);
                this.zzawr = iZzni;
                return;
            }
            if (i9 != 2) {
                throw zzrk.zzpt();
            }
            int iZznz = this.zzawp.zznz() + this.zzawp.zznr();
            do {
                list.add(Integer.valueOf(this.zzawp.zzns()));
            } while (this.zzawp.zznz() < iZznz);
            zzaw(iZznz);
            return;
        }
        zzrd zzrdVar = (zzrd) list;
        int i10 = this.tag & 7;
        if (i10 == 0) {
            do {
                zzrdVar.zzbm(this.zzawp.zzns());
                if (this.zzawp.zzny()) {
                    return;
                } else {
                    iZzni2 = this.zzawp.zzni();
                }
            } while (iZzni2 == this.tag);
            this.zzawr = iZzni2;
            return;
        }
        if (i10 != 2) {
            throw zzrk.zzpt();
        }
        int iZznz2 = this.zzawp.zznz() + this.zzawp.zznr();
        do {
            zzrdVar.zzbm(this.zzawp.zzns());
        } while (this.zzawp.zznz() < iZznz2);
        zzaw(iZznz2);
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final void zzs(List<Integer> list) throws zzrk {
        int iZzni;
        int iZzni2;
        if (!(list instanceof zzrd)) {
            int i9 = this.tag & 7;
            if (i9 == 2) {
                int iZznr = this.zzawp.zznr();
                zzav(iZznr);
                int iZznz = this.zzawp.zznz() + iZznr;
                do {
                    list.add(Integer.valueOf(this.zzawp.zznt()));
                } while (this.zzawp.zznz() < iZznz);
                return;
            }
            if (i9 != 5) {
                throw zzrk.zzpt();
            }
            do {
                list.add(Integer.valueOf(this.zzawp.zznt()));
                if (this.zzawp.zzny()) {
                    return;
                } else {
                    iZzni = this.zzawp.zzni();
                }
            } while (iZzni == this.tag);
            this.zzawr = iZzni;
            return;
        }
        zzrd zzrdVar = (zzrd) list;
        int i10 = this.tag & 7;
        if (i10 == 2) {
            int iZznr2 = this.zzawp.zznr();
            zzav(iZznr2);
            int iZznz2 = this.zzawp.zznz() + iZznr2;
            do {
                zzrdVar.zzbm(this.zzawp.zznt());
            } while (this.zzawp.zznz() < iZznz2);
            return;
        }
        if (i10 != 5) {
            throw zzrk.zzpt();
        }
        do {
            zzrdVar.zzbm(this.zzawp.zznt());
            if (this.zzawp.zzny()) {
                return;
            } else {
                iZzni2 = this.zzawp.zzni();
            }
        } while (iZzni2 == this.tag);
        this.zzawr = iZzni2;
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final void zzt(List<Long> list) throws zzrk {
        int iZzni;
        int iZzni2;
        if (!(list instanceof zzry)) {
            int i9 = this.tag & 7;
            if (i9 == 1) {
                do {
                    list.add(Long.valueOf(this.zzawp.zznu()));
                    if (this.zzawp.zzny()) {
                        return;
                    } else {
                        iZzni = this.zzawp.zzni();
                    }
                } while (iZzni == this.tag);
                this.zzawr = iZzni;
                return;
            }
            if (i9 != 2) {
                throw zzrk.zzpt();
            }
            int iZznr = this.zzawp.zznr();
            zzau(iZznr);
            int iZznz = this.zzawp.zznz() + iZznr;
            do {
                list.add(Long.valueOf(this.zzawp.zznu()));
            } while (this.zzawp.zznz() < iZznz);
            return;
        }
        zzry zzryVar = (zzry) list;
        int i10 = this.tag & 7;
        if (i10 == 1) {
            do {
                zzryVar.zzaa(this.zzawp.zznu());
                if (this.zzawp.zzny()) {
                    return;
                } else {
                    iZzni2 = this.zzawp.zzni();
                }
            } while (iZzni2 == this.tag);
            this.zzawr = iZzni2;
            return;
        }
        if (i10 != 2) {
            throw zzrk.zzpt();
        }
        int iZznr2 = this.zzawp.zznr();
        zzau(iZznr2);
        int iZznz2 = this.zzawp.zznz() + iZznr2;
        do {
            zzryVar.zzaa(this.zzawp.zznu());
        } while (this.zzawp.zznz() < iZznz2);
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final void zzu(List<Integer> list) throws zzrk {
        int iZzni;
        int iZzni2;
        if (!(list instanceof zzrd)) {
            int i9 = this.tag & 7;
            if (i9 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzawp.zznv()));
                    if (this.zzawp.zzny()) {
                        return;
                    } else {
                        iZzni = this.zzawp.zzni();
                    }
                } while (iZzni == this.tag);
                this.zzawr = iZzni;
                return;
            }
            if (i9 != 2) {
                throw zzrk.zzpt();
            }
            int iZznz = this.zzawp.zznz() + this.zzawp.zznr();
            do {
                list.add(Integer.valueOf(this.zzawp.zznv()));
            } while (this.zzawp.zznz() < iZznz);
            zzaw(iZznz);
            return;
        }
        zzrd zzrdVar = (zzrd) list;
        int i10 = this.tag & 7;
        if (i10 == 0) {
            do {
                zzrdVar.zzbm(this.zzawp.zznv());
                if (this.zzawp.zzny()) {
                    return;
                } else {
                    iZzni2 = this.zzawp.zzni();
                }
            } while (iZzni2 == this.tag);
            this.zzawr = iZzni2;
            return;
        }
        if (i10 != 2) {
            throw zzrk.zzpt();
        }
        int iZznz2 = this.zzawp.zznz() + this.zzawp.zznr();
        do {
            zzrdVar.zzbm(this.zzawp.zznv());
        } while (this.zzawp.zznz() < iZznz2);
        zzaw(iZznz2);
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final void zzv(List<Long> list) throws zzrk {
        int iZzni;
        int iZzni2;
        if (!(list instanceof zzry)) {
            int i9 = this.tag & 7;
            if (i9 == 0) {
                do {
                    list.add(Long.valueOf(this.zzawp.zznw()));
                    if (this.zzawp.zzny()) {
                        return;
                    } else {
                        iZzni = this.zzawp.zzni();
                    }
                } while (iZzni == this.tag);
                this.zzawr = iZzni;
                return;
            }
            if (i9 != 2) {
                throw zzrk.zzpt();
            }
            int iZznz = this.zzawp.zznz() + this.zzawp.zznr();
            do {
                list.add(Long.valueOf(this.zzawp.zznw()));
            } while (this.zzawp.zznz() < iZznz);
            zzaw(iZznz);
            return;
        }
        zzry zzryVar = (zzry) list;
        int i10 = this.tag & 7;
        if (i10 == 0) {
            do {
                zzryVar.zzaa(this.zzawp.zznw());
                if (this.zzawp.zzny()) {
                    return;
                } else {
                    iZzni2 = this.zzawp.zzni();
                }
            } while (iZzni2 == this.tag);
            this.zzawr = iZzni2;
            return;
        }
        if (i10 != 2) {
            throw zzrk.zzpt();
        }
        int iZznz2 = this.zzawp.zznz() + this.zzawp.zznr();
        do {
            zzryVar.zzaa(this.zzawp.zznw());
        } while (this.zzawp.zznz() < iZznz2);
        zzaw(iZznz2);
    }

    @Override // com.google.android.gms.internal.gtm.zzsy
    public final <T> T zza(zzsz<T> zzszVar, zzqp zzqpVar) throws zzrl {
        zzat(2);
        return (T) zzc(zzszVar, zzqpVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.gtm.zzsy
    public final <T> void zzb(List<T> list, zzsz<T> zzszVar, zzqp zzqpVar) throws zzrl {
        int iZzni;
        int i9 = this.tag;
        if ((i9 & 7) == 3) {
            do {
                list.add(zzd(zzszVar, zzqpVar));
                if (this.zzawp.zzny() || this.zzawr != 0) {
                    return;
                } else {
                    iZzni = this.zzawp.zzni();
                }
            } while (iZzni == i9);
            this.zzawr = iZzni;
            return;
        }
        throw zzrk.zzpt();
    }

    private final void zza(List<String> list, boolean z8) throws zzrl {
        int iZzni;
        int iZzni2;
        if ((this.tag & 7) == 2) {
            if ((list instanceof zzrt) && !z8) {
                zzrt zzrtVar = (zzrt) list;
                do {
                    zzrtVar.zzc(zznq());
                    if (this.zzawp.zzny()) {
                        return;
                    } else {
                        iZzni2 = this.zzawp.zzni();
                    }
                } while (iZzni2 == this.tag);
                this.zzawr = iZzni2;
                return;
            }
            do {
                list.add(z8 ? zznp() : readString());
                if (this.zzawp.zzny()) {
                    return;
                } else {
                    iZzni = this.zzawp.zzni();
                }
            } while (iZzni == this.tag);
            this.zzawr = iZzni;
            return;
        }
        throw zzrk.zzpt();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.gtm.zzsy
    public final <T> void zza(List<T> list, zzsz<T> zzszVar, zzqp zzqpVar) throws zzrl {
        int iZzni;
        int i9 = this.tag;
        if ((i9 & 7) == 2) {
            do {
                list.add(zzc(zzszVar, zzqpVar));
                if (this.zzawp.zzny() || this.zzawr != 0) {
                    return;
                } else {
                    iZzni = this.zzawp.zzni();
                }
            } while (iZzni == i9);
            this.zzawr = iZzni;
            return;
        }
        throw zzrk.zzpt();
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x005b, code lost:
    
        r8.put(r2, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0063, code lost:
    
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.gtm.zzsy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final <K, V> void zza(Map<K, V> map, zzsd<K, V> zzsdVar, zzqp zzqpVar) throws zzrl {
        zzat(2);
        int iZzaq = this.zzawp.zzaq(this.zzawp.zznr());
        Object objZza = zzsdVar.zzbcq;
        Object objZza2 = zzsdVar.zzbcs;
        while (true) {
            try {
                int iZzog = zzog();
                if (iZzog == Integer.MAX_VALUE || this.zzawp.zzny()) {
                    break;
                }
                if (iZzog == 1) {
                    objZza = zza(zzsdVar.zzbcp, (Class<?>) null, (zzqp) null);
                } else if (iZzog != 2) {
                    try {
                        if (!zzoh()) {
                            throw new zzrk("Unable to parse map entry.");
                        }
                    } catch (zzrl unused) {
                        if (!zzoh()) {
                            throw new zzrk("Unable to parse map entry.");
                        }
                    }
                } else {
                    objZza2 = zza(zzsdVar.zzbcr, zzsdVar.zzbcs.getClass(), zzqpVar);
                }
            } finally {
                this.zzawp.zzar(iZzaq);
            }
        }
    }

    private final Object zza(zzug zzugVar, Class<?> cls, zzqp zzqpVar) throws zzrl {
        switch (zzqi.zzaws[zzugVar.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzno());
            case 2:
                return zznq();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzns());
            case 5:
                return Integer.valueOf(zznn());
            case 6:
                return Long.valueOf(zznm());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zznl());
            case 9:
                return Long.valueOf(zznk());
            case 10:
                zzat(2);
                return zzc(zzsw.zzqs().zzi(cls), zzqpVar);
            case 11:
                return Integer.valueOf(zznt());
            case 12:
                return Long.valueOf(zznu());
            case 13:
                return Integer.valueOf(zznv());
            case 14:
                return Long.valueOf(zznw());
            case 15:
                return zznp();
            case 16:
                return Integer.valueOf(zznr());
            case 17:
                return Long.valueOf(zznj());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }
}
