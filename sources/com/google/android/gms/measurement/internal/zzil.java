package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcd;
import com.google.android.gms.internal.measurement.zzmb;
import com.google.android.gms.internal.measurement.zznq;
import com.google.android.gms.internal.measurement.zznr;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
final class zzil extends zzkm {
    public zzil(zzkp zzkpVar) {
        super(zzkpVar);
    }

    public final byte[] zza(zzar zzarVar, String str) {
        zzky next;
        Bundle bundleZzb;
        zzcd.zzg.zza zzaVar;
        zzf zzfVar;
        zzcd.zzf.zza zzaVar2;
        Bundle bundle;
        byte[] bArr;
        long j9;
        zzan zzanVarZza;
        zzc();
        this.zzy.zzad();
        Preconditions.checkNotNull(zzarVar);
        Preconditions.checkNotEmpty(str);
        if (!zzs().zze(str, zzat.zzaw)) {
            zzq().zzv().zza("Generating ScionPayload disabled. packageName", str);
            return new byte[0];
        }
        if (!"_iap".equals(zzarVar.zza) && !"_iapx".equals(zzarVar.zza)) {
            zzq().zzv().zza("Generating a payload for this event is not available. package_name, event_name", str, zzarVar.zza);
            return null;
        }
        zzcd.zzf.zza zzaVarZzb = zzcd.zzf.zzb();
        zzi().zze();
        try {
            zzf zzfVarZzb = zzi().zzb(str);
            if (zzfVarZzb == null) {
                zzq().zzv().zza("Log and bundle not available. package_name", str);
                return new byte[0];
            }
            if (!zzfVarZzb.zzr()) {
                zzq().zzv().zza("Log and bundle disabled. package_name", str);
                return new byte[0];
            }
            zzcd.zzg.zza zzaVarZza = zzcd.zzg.zzbh().zza(1).zza("android");
            if (!TextUtils.isEmpty(zzfVarZzb.zzc())) {
                zzaVarZza.zzf(zzfVarZzb.zzc());
            }
            if (!TextUtils.isEmpty(zzfVarZzb.zzn())) {
                zzaVarZza.zze(zzfVarZzb.zzn());
            }
            if (!TextUtils.isEmpty(zzfVarZzb.zzl())) {
                zzaVarZza.zzg(zzfVarZzb.zzl());
            }
            if (zzfVarZzb.zzm() != -2147483648L) {
                zzaVarZza.zzh((int) zzfVarZzb.zzm());
            }
            zzaVarZza.zzf(zzfVarZzb.zzo()).zzk(zzfVarZzb.zzq());
            if (zznq.zzb() && zzs().zze(zzfVarZzb.zzc(), zzat.zzbj)) {
                if (!TextUtils.isEmpty(zzfVarZzb.zze())) {
                    zzaVarZza.zzk(zzfVarZzb.zze());
                } else if (!TextUtils.isEmpty(zzfVarZzb.zzg())) {
                    zzaVarZza.zzp(zzfVarZzb.zzg());
                } else if (!TextUtils.isEmpty(zzfVarZzb.zzf())) {
                    zzaVarZza.zzo(zzfVarZzb.zzf());
                }
            } else if (!TextUtils.isEmpty(zzfVarZzb.zze())) {
                zzaVarZza.zzk(zzfVarZzb.zze());
            } else if (!TextUtils.isEmpty(zzfVarZzb.zzf())) {
                zzaVarZza.zzo(zzfVarZzb.zzf());
            }
            zzad zzadVarZza = this.zza.zza(str);
            zzaVarZza.zzh(zzfVarZzb.zzp());
            if (this.zzy.zzaa() && zzs().zzh(zzaVarZza.zzj())) {
                if (!zzmb.zzb() || !zzs().zza(zzat.zzcp)) {
                    zzaVarZza.zzj();
                    if (!TextUtils.isEmpty(null)) {
                        zzaVarZza.zzn(null);
                    }
                } else if (zzadVarZza.zzc() && !TextUtils.isEmpty(null)) {
                    zzaVarZza.zzn(null);
                }
            }
            if (zzmb.zzb() && zzs().zza(zzat.zzcp)) {
                zzaVarZza.zzq(zzadVarZza.zza());
            }
            if (!zzmb.zzb() || !zzs().zza(zzat.zzcp) || zzadVarZza.zzc()) {
                Pair<String, Boolean> pairZza = zzf().zza(zzfVarZzb.zzc(), zzadVarZza);
                if (zzfVarZzb.zzaf() && pairZza != null && !TextUtils.isEmpty((CharSequence) pairZza.first)) {
                    zzaVarZza.zzh(zza((String) pairZza.first, Long.toString(zzarVar.zzd)));
                    Object obj = pairZza.second;
                    if (obj != null) {
                        zzaVarZza.zza(((Boolean) obj).booleanValue());
                    }
                }
            }
            zzk().zzaa();
            zzcd.zzg.zza zzaVarZzc = zzaVarZza.zzc(Build.MODEL);
            zzk().zzaa();
            zzaVarZzc.zzb(Build.VERSION.RELEASE).zzf((int) zzk().zze()).zzd(zzk().zzf());
            if (!zzmb.zzb() || !zzs().zza(zzat.zzcp) || zzadVarZza.zze()) {
                zzaVarZza.zzi(zza(zzfVarZzb.zzd(), Long.toString(zzarVar.zzd)));
            }
            if (!TextUtils.isEmpty(zzfVarZzb.zzi())) {
                zzaVarZza.zzl(zzfVarZzb.zzi());
            }
            String strZzc = zzfVarZzb.zzc();
            List<zzky> listZza = zzi().zza(strZzc);
            Iterator<zzky> it = listZza.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if ("_lte".equals(next.zzc)) {
                    break;
                }
            }
            if (next == null || next.zze == null) {
                zzky zzkyVar = new zzky(strZzc, "auto", "_lte", zzl().currentTimeMillis(), 0L);
                listZza.add(zzkyVar);
                zzi().zza(zzkyVar);
            }
            zzkt zzktVarMo17540f_ = mo17540f_();
            zzktVarMo17540f_.zzq().zzw().zza("Checking account type status for ad personalization signals");
            if (zzktVarMo17540f_.zzk().zzi()) {
                String strZzc2 = zzfVarZzb.zzc();
                if (zzfVarZzb.zzaf() && zzktVarMo17540f_.zzj().zze(strZzc2)) {
                    zzktVarMo17540f_.zzq().zzv().zza("Turning off ad personalization due to account type");
                    Iterator<zzky> it2 = listZza.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        if ("_npa".equals(it2.next().zzc)) {
                            it2.remove();
                            break;
                        }
                    }
                    listZza.add(new zzky(strZzc2, "auto", "_npa", zzktVarMo17540f_.zzl().currentTimeMillis(), 1L));
                }
            }
            zzcd.zzk[] zzkVarArr = new zzcd.zzk[listZza.size()];
            for (int i9 = 0; i9 < listZza.size(); i9++) {
                zzcd.zzk.zza zzaVarZza2 = zzcd.zzk.zzj().zza(listZza.get(i9).zzc).zza(listZza.get(i9).zzd);
                mo17540f_().zza(zzaVarZza2, listZza.get(i9).zze);
                zzkVarArr[i9] = (zzcd.zzk) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZza2.zzy());
            }
            zzaVarZza.zzb(Arrays.asList(zzkVarArr));
            if (zznr.zzb() && zzs().zza(zzat.zzch) && zzs().zza(zzat.zzci)) {
                zzfb zzfbVarZza = zzfb.zza(zzarVar);
                zzo().zza(zzfbVarZza.zzb, zzi().zzi(str));
                zzo().zza(zzfbVarZza, zzs().zza(str));
                bundleZzb = zzfbVarZza.zzb;
            } else {
                bundleZzb = zzarVar.zzb.zzb();
            }
            Bundle bundle2 = bundleZzb;
            bundle2.putLong("_c", 1L);
            zzq().zzv().zza("Marking in-app purchase as real-time");
            bundle2.putLong("_r", 1L);
            bundle2.putString("_o", zzarVar.zzc);
            if (zzo().zze(zzaVarZza.zzj())) {
                zzo().zza(bundle2, "_dbg", (Object) 1L);
                zzo().zza(bundle2, "_r", (Object) 1L);
            }
            zzan zzanVarZza2 = zzi().zza(str, zzarVar.zza);
            if (zzanVarZza2 == null) {
                zzfVar = zzfVarZzb;
                zzaVar = zzaVarZza;
                zzaVar2 = zzaVarZzb;
                bundle = bundle2;
                bArr = null;
                zzanVarZza = new zzan(str, zzarVar.zza, 0L, 0L, zzarVar.zzd, 0L, null, null, null, null);
                j9 = 0;
            } else {
                zzaVar = zzaVarZza;
                zzfVar = zzfVarZzb;
                zzaVar2 = zzaVarZzb;
                bundle = bundle2;
                bArr = null;
                j9 = zzanVarZza2.zzf;
                zzanVarZza = zzanVarZza2.zza(zzarVar.zzd);
            }
            zzi().zza(zzanVarZza);
            zzak zzakVar = new zzak(this.zzy, zzarVar.zzc, str, zzarVar.zza, zzarVar.zzd, j9, bundle);
            zzcd.zzc.zza zzaVarZzb2 = zzcd.zzc.zzj().zza(zzakVar.zzc).zza(zzakVar.zzb).zzb(zzakVar.zzd);
            Iterator<String> it3 = zzakVar.zze.iterator();
            while (it3.hasNext()) {
                String next2 = it3.next();
                zzcd.zze.zza zzaVarZza3 = zzcd.zze.zzm().zza(next2);
                mo17540f_().zza(zzaVarZza3, zzakVar.zze.zza(next2));
                zzaVarZzb2.zza(zzaVarZza3);
            }
            zzcd.zzg.zza zzaVar3 = zzaVar;
            zzaVar3.zza(zzaVarZzb2).zza(zzcd.zzh.zza().zza(zzcd.zzd.zza().zza(zzanVarZza.zzc).zza(zzarVar.zza)));
            zzaVar3.zzc(zzh().zza(zzfVar.zzc(), Collections.emptyList(), zzaVar3.zzd(), Long.valueOf(zzaVarZzb2.zzf()), Long.valueOf(zzaVarZzb2.zzf())));
            if (zzaVarZzb2.zze()) {
                zzaVar3.zzb(zzaVarZzb2.zzf()).zzc(zzaVarZzb2.zzf());
            }
            long jZzk = zzfVar.zzk();
            if (jZzk != 0) {
                zzaVar3.zze(jZzk);
            }
            long jZzj = zzfVar.zzj();
            if (jZzj != 0) {
                zzaVar3.zzd(jZzj);
            } else if (jZzk != 0) {
                zzaVar3.zzd(jZzk);
            }
            zzfVar.zzv();
            zzaVar3.zzg((int) zzfVar.zzs()).zzg(31049L).zza(zzl().currentTimeMillis()).zzb(true);
            zzcd.zzf.zza zzaVar4 = zzaVar2;
            zzaVar4.zza(zzaVar3);
            zzf zzfVar2 = zzfVar;
            zzfVar2.zza(zzaVar3.zzf());
            zzfVar2.zzb(zzaVar3.zzg());
            zzi().zza(zzfVar2);
            zzi().m17536b_();
            try {
                return mo17540f_().zzc(((zzcd.zzf) ((com.google.android.gms.internal.measurement.zzhv) zzaVar4.zzy())).zzbk());
            } catch (IOException e9) {
                zzq().zze().zza("Data loss. Failed to bundle and serialize. appId", zzex.zza(str), e9);
                return bArr;
            }
        } catch (SecurityException e10) {
            zzq().zzv().zza("Resettable device id encryption failed", e10.getMessage());
            return new byte[0];
        } catch (SecurityException e11) {
            zzq().zzv().zza("app instance id encryption failed", e11.getMessage());
            return new byte[0];
        } finally {
            zzi().zzg();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzkm
    public final boolean zzd() {
        return false;
    }

    private static String zza(String str, String str2) {
        throw new SecurityException("This implementation should not be used.");
    }
}
