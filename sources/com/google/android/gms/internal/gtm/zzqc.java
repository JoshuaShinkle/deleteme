package com.google.android.gms.internal.gtm;

import java.nio.charset.Charset;

/* loaded from: classes2.dex */
class zzqc extends zzqb {
    protected final byte[] zzawe;

    public zzqc(byte[] bArr) {
        bArr.getClass();
        this.zzawe = bArr;
    }

    @Override // com.google.android.gms.internal.gtm.zzps
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzps) || size() != ((zzps) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzqc)) {
            return obj.equals(this);
        }
        zzqc zzqcVar = (zzqc) obj;
        int iZzne = zzne();
        int iZzne2 = zzqcVar.zzne();
        if (iZzne == 0 || iZzne2 == 0 || iZzne == iZzne2) {
            return zza(zzqcVar, 0, size());
        }
        return false;
    }

    @Override // com.google.android.gms.internal.gtm.zzps
    public int size() {
        return this.zzawe.length;
    }

    @Override // com.google.android.gms.internal.gtm.zzps
    public final void zza(zzpr zzprVar) {
        zzprVar.zza(this.zzawe, zznf(), size());
    }

    @Override // com.google.android.gms.internal.gtm.zzps
    public byte zzak(int i9) {
        return this.zzawe[i9];
    }

    @Override // com.google.android.gms.internal.gtm.zzps
    public byte zzal(int i9) {
        return this.zzawe[i9];
    }

    @Override // com.google.android.gms.internal.gtm.zzps
    public final zzps zzc(int i9, int i10) {
        int iZzb = zzps.zzb(0, i10, size());
        return iZzb == 0 ? zzps.zzavx : new zzpx(this.zzawe, zznf(), iZzb);
    }

    @Override // com.google.android.gms.internal.gtm.zzps
    public final boolean zznd() {
        int iZznf = zznf();
        return zztz.zzf(this.zzawe, iZznf, size() + iZznf);
    }

    public int zznf() {
        return 0;
    }

    @Override // com.google.android.gms.internal.gtm.zzps
    public final String zza(Charset charset) {
        return new String(this.zzawe, zznf(), size(), charset);
    }

    @Override // com.google.android.gms.internal.gtm.zzqb
    public final boolean zza(zzps zzpsVar, int i9, int i10) {
        if (i10 <= zzpsVar.size()) {
            if (i10 <= zzpsVar.size()) {
                if (zzpsVar instanceof zzqc) {
                    zzqc zzqcVar = (zzqc) zzpsVar;
                    return zztx.zza(this.zzawe, zznf(), zzqcVar.zzawe, zzqcVar.zznf(), i10) == -1;
                }
                return zzpsVar.zzc(0, i10).equals(zzc(0, i10));
            }
            int size = zzpsVar.size();
            StringBuilder sb = new StringBuilder(59);
            sb.append("Ran off end of other: 0, ");
            sb.append(i10);
            sb.append(", ");
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        }
        int size2 = size();
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append("Length too large: ");
        sb2.append(i10);
        sb2.append(size2);
        throw new IllegalArgumentException(sb2.toString());
    }

    @Override // com.google.android.gms.internal.gtm.zzps
    public final int zza(int i9, int i10, int i11) {
        return zzre.zza(i9, this.zzawe, zznf(), i11);
    }
}
