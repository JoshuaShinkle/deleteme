package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzc;
import com.google.android.gms.internal.gtm.zzor;
import com.google.android.gms.internal.gtm.zzot;
import com.google.android.gms.internal.gtm.zzov;
import com.google.android.gms.internal.gtm.zzox;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@VisibleForTesting
/* loaded from: classes2.dex */
final class zzfb {
    private static final zzdz<com.google.android.gms.internal.gtm.zzl> zzajp = new zzdz<>(zzgj.zzkc(), true);
    private final DataLayer zzaed;
    private final zzov zzajq;
    private final zzbo zzajr;
    private final Map<String, zzbq> zzajs;
    private final Map<String, zzbq> zzajt;
    private final Map<String, zzbq> zzaju;
    private final zzp<zzot, zzdz<com.google.android.gms.internal.gtm.zzl>> zzajv;
    private final zzp<String, zzfh> zzajw;
    private final Set<zzox> zzajx;
    private final Map<String, zzfi> zzajy;
    private volatile String zzajz;
    private int zzaka;

    public zzfb(Context context, zzov zzovVar, DataLayer dataLayer, zzan zzanVar, zzan zzanVar2, zzbo zzboVar) {
        if (zzovVar == null) {
            throw new NullPointerException("resource cannot be null");
        }
        this.zzajq = zzovVar;
        HashSet<zzox> hashSet = new HashSet(zzovVar.zzls());
        this.zzajx = hashSet;
        this.zzaed = dataLayer;
        this.zzajr = zzboVar;
        zzfc zzfcVar = new zzfc(this);
        new zzq();
        this.zzajv = zzq.zza(ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES, zzfcVar);
        zzfd zzfdVar = new zzfd(this);
        new zzq();
        this.zzajw = zzq.zza(ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES, zzfdVar);
        this.zzajs = new HashMap();
        zzb(new zzm(context));
        zzb(new zzam(zzanVar2));
        zzb(new zzaz(dataLayer));
        zzb(new zzgk(context, dataLayer));
        this.zzajt = new HashMap();
        zzc(new zzak());
        zzc(new zzbl());
        zzc(new zzbm());
        zzc(new zzbs());
        zzc(new zzbt());
        zzc(new zzde());
        zzc(new zzdf());
        zzc(new zzel());
        zzc(new zzfy());
        this.zzaju = new HashMap();
        zza(new zze(context));
        zza(new zzf(context));
        zza(new zzh(context));
        zza(new zzi(context));
        zza(new zzj(context));
        zza(new zzk(context));
        zza(new zzl(context));
        zza(new zzt());
        zza(new zzaj(zzovVar.getVersion()));
        zza(new zzam(zzanVar));
        zza(new zzas(dataLayer));
        zza(new zzbc(context));
        zza(new zzbd());
        zza(new zzbk());
        zza(new zzbp(this));
        zza(new zzbu());
        zza(new zzbv());
        zza(new zzcv(context));
        zza(new zzcx());
        zza(new zzdd());
        zza(new zzdk());
        zza(new zzdm(context));
        zza(new zzea());
        zza(new zzee());
        zza(new zzei());
        zza(new zzek());
        zza(new zzem(context));
        zza(new zzfj());
        zza(new zzfk());
        zza(new zzge());
        zza(new zzgl());
        this.zzajy = new HashMap();
        for (zzox zzoxVar : hashSet) {
            for (int i9 = 0; i9 < zzoxVar.zzmq().size(); i9++) {
                zzot zzotVar = zzoxVar.zzmq().get(i9);
                zzfi zzfiVarZzb = zzb(this.zzajy, zza(zzotVar));
                zzfiVarZzb.zza(zzoxVar);
                zzfiVarZzb.zza(zzoxVar, zzotVar);
                zzfiVarZzb.zza(zzoxVar, "Unknown");
            }
            for (int i10 = 0; i10 < zzoxVar.zzmr().size(); i10++) {
                zzot zzotVar2 = zzoxVar.zzmr().get(i10);
                zzfi zzfiVarZzb2 = zzb(this.zzajy, zza(zzotVar2));
                zzfiVarZzb2.zza(zzoxVar);
                zzfiVarZzb2.zzb(zzoxVar, zzotVar2);
                zzfiVarZzb2.zzb(zzoxVar, "Unknown");
            }
        }
        for (Map.Entry<String, List<zzot>> entry : this.zzajq.zzmo().entrySet()) {
            for (zzot zzotVar3 : entry.getValue()) {
                if (!zzgj.zzg(zzotVar3.zzlu().get(com.google.android.gms.internal.gtm.zzb.NOT_DEFAULT_MACRO.toString())).booleanValue()) {
                    zzb(this.zzajy, entry.getKey()).zzb(zzotVar3);
                }
            }
        }
    }

    private final zzdz<Set<zzot>> zza(Set<zzox> set, Set<String> set2, zzfg zzfgVar, zzfa zzfaVar) {
        boolean z8;
        zzdz zzdzVar;
        Set<zzot> hashSet = new HashSet<>();
        Set<zzot> hashSet2 = new HashSet<>();
        while (true) {
            for (zzox zzoxVar : set) {
                zzeq zzeqVarZzis = zzfaVar.zzis();
                Iterator<zzot> it = zzoxVar.zzlx().iterator();
                while (true) {
                    boolean z9 = true;
                    while (true) {
                        if (!it.hasNext()) {
                            Iterator<zzot> it2 = zzoxVar.zzlw().iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    Boolean bool = Boolean.TRUE;
                                    zzgj.zzi(bool);
                                    zzdzVar = new zzdz(bool, z9);
                                    break;
                                }
                                zzdz<Boolean> zzdzVarZza = zza(it2.next(), set2, zzeqVarZzis.zzin());
                                if (!zzdzVarZza.getObject().booleanValue()) {
                                    Boolean bool2 = Boolean.FALSE;
                                    zzgj.zzi(bool2);
                                    zzdzVar = new zzdz(bool2, zzdzVarZza.zziu());
                                    break;
                                }
                                z9 = z9 && zzdzVarZza.zziu();
                            }
                        } else {
                            zzdz<Boolean> zzdzVarZza2 = zza(it.next(), set2, zzeqVarZzis.zzim());
                            if (zzdzVarZza2.getObject().booleanValue()) {
                                Boolean bool3 = Boolean.FALSE;
                                zzgj.zzi(bool3);
                                zzdzVar = new zzdz(bool3, zzdzVarZza2.zziu());
                                break;
                            }
                            if (!z9 || !zzdzVarZza2.zziu()) {
                                z9 = false;
                            }
                        }
                    }
                }
                if (((Boolean) zzdzVar.getObject()).booleanValue()) {
                    zzfgVar.zza(zzoxVar, hashSet, hashSet2, zzeqVarZzis);
                }
                z8 = z8 && zzdzVar.zziu();
            }
            hashSet.removeAll(hashSet2);
            zzfaVar.zzb(hashSet);
            return new zzdz<>(hashSet, z8);
        }
    }

    private static zzfi zzb(Map<String, zzfi> map, String str) {
        zzfi zzfiVar = map.get(str);
        if (zzfiVar != null) {
            return zzfiVar;
        }
        zzfi zzfiVar2 = new zzfi();
        map.put(str, zzfiVar2);
        return zzfiVar2;
    }

    @VisibleForTesting
    private final synchronized void zzbk(String str) {
        this.zzajz = str;
    }

    @VisibleForTesting
    private final void zzc(zzbq zzbqVar) {
        zza(this.zzajt, zzbqVar);
    }

    private final String zzjg() {
        if (this.zzaka <= 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(this.zzaka));
        for (int i9 = 2; i9 < this.zzaka; i9++) {
            sb.append(' ');
        }
        sb.append(": ");
        return sb.toString();
    }

    public final synchronized void zzan(String str) {
        zzbk(str);
        zzar zzarVarZzid = this.zzajr.zzba(str).zzid();
        Iterator<zzot> it = zza(this.zzajx, new HashSet(), new zzff(this), zzarVarZzid.zzhs()).getObject().iterator();
        while (it.hasNext()) {
            zza(this.zzajs, it.next(), new HashSet(), zzarVarZzid.zzhr());
        }
        zzbk(null);
    }

    public final zzdz<com.google.android.gms.internal.gtm.zzl> zzbj(String str) {
        this.zzaka = 0;
        return zza(str, new HashSet(), this.zzajr.zzaz(str).zzic());
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0100 A[Catch: all -> 0x0172, TryCatch #0 {, blocks: (B:4:0x0003, B:5:0x0007, B:7:0x000d, B:9:0x0017, B:12:0x0021, B:14:0x0027, B:15:0x002d, B:17:0x0034, B:18:0x0040, B:21:0x0049, B:23:0x0053, B:26:0x007c, B:27:0x007f, B:24:0x0077, B:28:0x0082, B:30:0x008a, B:32:0x0092, B:58:0x014c, B:33:0x0099, B:37:0x00b2, B:40:0x00c2, B:42:0x00ca, B:47:0x00e7, B:49:0x0100, B:51:0x0108, B:52:0x0124, B:54:0x012e, B:55:0x013e, B:56:0x0143, B:46:0x00d6, B:57:0x0147, B:36:0x00a7, B:59:0x0150), top: B:66:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized void zze(List<com.google.android.gms.internal.gtm.zzj> list) {
        for (com.google.android.gms.internal.gtm.zzj zzjVar : list) {
            String str = zzjVar.name;
            if (str == null || !str.startsWith("gaExperiment:")) {
                String strValueOf = String.valueOf(zzjVar);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 22);
                sb.append("Ignored supplemental: ");
                sb.append(strValueOf);
                zzdi.zzab(sb.toString());
            } else {
                DataLayer dataLayer = this.zzaed;
                com.google.android.gms.internal.gtm.zzh zzhVar = zzjVar.zzqi;
                if (zzhVar == null) {
                    zzdi.zzac("supplemental missing experimentSupplemental");
                } else {
                    for (com.google.android.gms.internal.gtm.zzl zzlVar : zzhVar.zzpf) {
                        dataLayer.zzaq(zzgj.zzc(zzlVar));
                    }
                    com.google.android.gms.internal.gtm.zzl[] zzlVarArr = zzjVar.zzqi.zzpe;
                    int length = zzlVarArr.length;
                    int i9 = 0;
                    while (true) {
                        Map<String, Object> map = null;
                        if (i9 >= length) {
                            break;
                        }
                        Object objZzh = zzgj.zzh(zzlVarArr[i9]);
                        if (objZzh instanceof Map) {
                            map = (Map) objZzh;
                        } else {
                            String strValueOf2 = String.valueOf(objZzh);
                            StringBuilder sb2 = new StringBuilder(strValueOf2.length() + 36);
                            sb2.append("value: ");
                            sb2.append(strValueOf2);
                            sb2.append(" is not a map value, ignored.");
                            zzdi.zzac(sb2.toString());
                        }
                        if (map != null) {
                            dataLayer.push(map);
                        }
                        i9++;
                    }
                    for (zzc.C6855zzc c6855zzc : zzjVar.zzqi.zzpg) {
                        if (c6855zzc.hasKey()) {
                            Object objValueOf = dataLayer.get(c6855zzc.getKey());
                            Long lValueOf = !(objValueOf instanceof Number) ? null : Long.valueOf(((Number) objValueOf).longValue());
                            long jZzg = c6855zzc.zzg();
                            long jZzh = c6855zzc.zzh();
                            if (c6855zzc.zzi() && lValueOf != null && lValueOf.longValue() >= jZzg && lValueOf.longValue() <= jZzh) {
                                dataLayer.zzaq(c6855zzc.getKey());
                                Map<String, Object> mapZzg = DataLayer.zzg(c6855zzc.getKey(), objValueOf);
                                if (c6855zzc.zzj() > 0) {
                                }
                                dataLayer.push(mapZzg);
                            } else if (jZzg <= jZzh) {
                                objValueOf = Long.valueOf(Math.round((Math.random() * (jZzh - jZzg)) + jZzg));
                                dataLayer.zzaq(c6855zzc.getKey());
                                Map<String, Object> mapZzg2 = DataLayer.zzg(c6855zzc.getKey(), objValueOf);
                                if (c6855zzc.zzj() > 0) {
                                    if (mapZzg2.containsKey("gtm")) {
                                        Object obj = mapZzg2.get("gtm");
                                        if (obj instanceof Map) {
                                            ((Map) obj).put("lifetime", Long.valueOf(c6855zzc.zzj()));
                                        } else {
                                            zzdi.zzac("GaExperimentRandom: gtm not a map");
                                        }
                                    } else {
                                        mapZzg2.put("gtm", DataLayer.mapOf("lifetime", Long.valueOf(c6855zzc.zzj())));
                                    }
                                }
                                dataLayer.push(mapZzg2);
                            } else {
                                zzdi.zzac("GaExperimentRandom: random range invalid");
                            }
                        } else {
                            zzdi.zzac("GaExperimentRandom: No key");
                        }
                    }
                }
            }
        }
    }

    public final synchronized String zzjf() {
        return this.zzajz;
    }

    @VisibleForTesting
    private final void zzb(zzbq zzbqVar) {
        zza(this.zzajs, zzbqVar);
    }

    private static String zza(zzot zzotVar) {
        return zzgj.zzc(zzotVar.zzlu().get(com.google.android.gms.internal.gtm.zzb.INSTANCE_NAME.toString()));
    }

    private static void zza(Map<String, zzbq> map, zzbq zzbqVar) {
        if (map.containsKey(zzbqVar.zzif())) {
            String strValueOf = String.valueOf(zzbqVar.zzif());
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "Duplicate function type name: ".concat(strValueOf) : new String("Duplicate function type name: "));
        }
        map.put(zzbqVar.zzif(), zzbqVar);
    }

    @VisibleForTesting
    private final void zza(zzbq zzbqVar) {
        zza(this.zzaju, zzbqVar);
    }

    @VisibleForTesting
    private final zzdz<Boolean> zza(zzot zzotVar, Set<String> set, zzen zzenVar) {
        zzdz<com.google.android.gms.internal.gtm.zzl> zzdzVarZza = zza(this.zzajt, zzotVar, set, zzenVar);
        Boolean boolZzg = zzgj.zzg(zzdzVarZza.getObject());
        zzenVar.zza(zzgj.zzi(boolZzg));
        return new zzdz<>(boolZzg, zzdzVarZza.zziu());
    }

    private final zzdz<com.google.android.gms.internal.gtm.zzl> zza(String str, Set<String> set, zzdl zzdlVar) throws InterruptedException {
        zzot next;
        this.zzaka++;
        zzfh zzfhVar = this.zzajw.get(str);
        if (zzfhVar != null) {
            this.zzajr.zzie();
            zza(zzfhVar.zzji(), set);
            this.zzaka--;
            return zzfhVar.zzjh();
        }
        zzfi zzfiVar = this.zzajy.get(str);
        if (zzfiVar == null) {
            String strZzjg = zzjg();
            StringBuilder sb = new StringBuilder(String.valueOf(strZzjg).length() + 15 + String.valueOf(str).length());
            sb.append(strZzjg);
            sb.append("Invalid macro: ");
            sb.append(str);
            zzdi.zzav(sb.toString());
            this.zzaka--;
            return zzajp;
        }
        zzdz<Set<zzot>> zzdzVarZza = zza(zzfiVar.zzjj(), set, new zzfe(this, zzfiVar.zzjk(), zzfiVar.zzjl(), zzfiVar.zzjn(), zzfiVar.zzjm()), zzdlVar.zzhs());
        if (zzdzVarZza.getObject().isEmpty()) {
            next = zzfiVar.zzjo();
        } else {
            if (zzdzVarZza.getObject().size() > 1) {
                String strZzjg2 = zzjg();
                StringBuilder sb2 = new StringBuilder(String.valueOf(strZzjg2).length() + 37 + String.valueOf(str).length());
                sb2.append(strZzjg2);
                sb2.append("Multiple macros active for macroName ");
                sb2.append(str);
                zzdi.zzac(sb2.toString());
            }
            next = zzdzVarZza.getObject().iterator().next();
        }
        if (next == null) {
            this.zzaka--;
            return zzajp;
        }
        zzdz<com.google.android.gms.internal.gtm.zzl> zzdzVarZza2 = zza(this.zzaju, next, set, zzdlVar.zzil());
        boolean z8 = zzdzVarZza.zziu() && zzdzVarZza2.zziu();
        zzdz<com.google.android.gms.internal.gtm.zzl> zzdzVar = zzajp;
        if (zzdzVarZza2 != zzdzVar) {
            zzdzVar = new zzdz<>(zzdzVarZza2.getObject(), z8);
        }
        com.google.android.gms.internal.gtm.zzl zzlVarZzji = next.zzji();
        if (zzdzVar.zziu()) {
            this.zzajw.zza(str, new zzfh(zzdzVar, zzlVarZzji));
        }
        zza(zzlVarZzji, set);
        this.zzaka--;
        return zzdzVar;
    }

    private final void zza(com.google.android.gms.internal.gtm.zzl zzlVar, Set<String> set) throws InterruptedException {
        zzdz<com.google.android.gms.internal.gtm.zzl> zzdzVarZza;
        if (zzlVar == null || (zzdzVarZza = zza(zzlVar, set, new zzdx())) == zzajp) {
            return;
        }
        Object objZzh = zzgj.zzh(zzdzVarZza.getObject());
        if (objZzh instanceof Map) {
            this.zzaed.push((Map) objZzh);
            return;
        }
        if (objZzh instanceof List) {
            for (Object obj : (List) objZzh) {
                if (obj instanceof Map) {
                    this.zzaed.push((Map) obj);
                } else {
                    zzdi.zzac("pushAfterEvaluate: value not a Map");
                }
            }
            return;
        }
        zzdi.zzac("pushAfterEvaluate: value not a Map or List");
    }

    private final zzdz<com.google.android.gms.internal.gtm.zzl> zza(com.google.android.gms.internal.gtm.zzl zzlVar, Set<String> set, zzgm zzgmVar) {
        zzdz<com.google.android.gms.internal.gtm.zzl> zzdzVar;
        if (!zzlVar.zzqw) {
            return new zzdz<>(zzlVar, true);
        }
        int i9 = zzlVar.type;
        if (i9 == 2) {
            com.google.android.gms.internal.gtm.zzl zzlVarZzk = zzor.zzk(zzlVar);
            zzlVarZzk.zzqn = new com.google.android.gms.internal.gtm.zzl[zzlVar.zzqn.length];
            int i10 = 0;
            while (true) {
                com.google.android.gms.internal.gtm.zzl[] zzlVarArr = zzlVar.zzqn;
                if (i10 < zzlVarArr.length) {
                    zzdz<com.google.android.gms.internal.gtm.zzl> zzdzVarZza = zza(zzlVarArr[i10], set, zzgmVar.zzv(i10));
                    zzdz<com.google.android.gms.internal.gtm.zzl> zzdzVar2 = zzajp;
                    if (zzdzVarZza == zzdzVar2) {
                        return zzdzVar2;
                    }
                    zzlVarZzk.zzqn[i10] = zzdzVarZza.getObject();
                    i10++;
                } else {
                    return new zzdz<>(zzlVarZzk, false);
                }
            }
        } else {
            if (i9 == 3) {
                com.google.android.gms.internal.gtm.zzl zzlVarZzk2 = zzor.zzk(zzlVar);
                com.google.android.gms.internal.gtm.zzl[] zzlVarArr2 = zzlVar.zzqo;
                if (zzlVarArr2.length != zzlVar.zzqp.length) {
                    String strValueOf = String.valueOf(zzlVar.toString());
                    zzdi.zzav(strValueOf.length() != 0 ? "Invalid serving value: ".concat(strValueOf) : new String("Invalid serving value: "));
                    return zzajp;
                }
                zzlVarZzk2.zzqo = new com.google.android.gms.internal.gtm.zzl[zzlVarArr2.length];
                zzlVarZzk2.zzqp = new com.google.android.gms.internal.gtm.zzl[zzlVar.zzqo.length];
                int i11 = 0;
                while (true) {
                    com.google.android.gms.internal.gtm.zzl[] zzlVarArr3 = zzlVar.zzqo;
                    if (i11 < zzlVarArr3.length) {
                        zzdz<com.google.android.gms.internal.gtm.zzl> zzdzVarZza2 = zza(zzlVarArr3[i11], set, zzgmVar.zzw(i11));
                        zzdz<com.google.android.gms.internal.gtm.zzl> zzdzVarZza3 = zza(zzlVar.zzqp[i11], set, zzgmVar.zzx(i11));
                        zzdzVar = zzajp;
                        if (zzdzVarZza2 == zzdzVar || zzdzVarZza3 == zzdzVar) {
                            break;
                        }
                        zzlVarZzk2.zzqo[i11] = zzdzVarZza2.getObject();
                        zzlVarZzk2.zzqp[i11] = zzdzVarZza3.getObject();
                        i11++;
                    } else {
                        return new zzdz<>(zzlVarZzk2, false);
                    }
                }
                return zzdzVar;
            }
            if (i9 == 4) {
                if (set.contains(zzlVar.zzqq)) {
                    String str = zzlVar.zzqq;
                    String string = set.toString();
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 79 + String.valueOf(string).length());
                    sb.append("Macro cycle detected.  Current macro reference: ");
                    sb.append(str);
                    sb.append(".  Previous macro references: ");
                    sb.append(string);
                    sb.append(".");
                    zzdi.zzav(sb.toString());
                    return zzajp;
                }
                set.add(zzlVar.zzqq);
                zzdz<com.google.android.gms.internal.gtm.zzl> zzdzVarZza4 = zzgn.zza(zza(zzlVar.zzqq, set, zzgmVar.zzit()), zzlVar.zzqv);
                set.remove(zzlVar.zzqq);
                return zzdzVarZza4;
            }
            if (i9 != 7) {
                StringBuilder sb2 = new StringBuilder(25);
                sb2.append("Unknown type: ");
                sb2.append(i9);
                zzdi.zzav(sb2.toString());
                return zzajp;
            }
            com.google.android.gms.internal.gtm.zzl zzlVarZzk3 = zzor.zzk(zzlVar);
            zzlVarZzk3.zzqu = new com.google.android.gms.internal.gtm.zzl[zzlVar.zzqu.length];
            int i12 = 0;
            while (true) {
                com.google.android.gms.internal.gtm.zzl[] zzlVarArr4 = zzlVar.zzqu;
                if (i12 < zzlVarArr4.length) {
                    zzdz<com.google.android.gms.internal.gtm.zzl> zzdzVarZza5 = zza(zzlVarArr4[i12], set, zzgmVar.zzy(i12));
                    zzdz<com.google.android.gms.internal.gtm.zzl> zzdzVar3 = zzajp;
                    if (zzdzVarZza5 == zzdzVar3) {
                        return zzdzVar3;
                    }
                    zzlVarZzk3.zzqu[i12] = zzdzVarZza5.getObject();
                    i12++;
                } else {
                    return new zzdz<>(zzlVarZzk3, false);
                }
            }
        }
    }

    private final zzdz<com.google.android.gms.internal.gtm.zzl> zza(Map<String, zzbq> map, zzot zzotVar, Set<String> set, zzen zzenVar) {
        com.google.android.gms.internal.gtm.zzl zzlVar = zzotVar.zzlu().get(com.google.android.gms.internal.gtm.zzb.FUNCTION.toString());
        if (zzlVar == null) {
            zzdi.zzav("No function id in properties");
            return zzajp;
        }
        String str = zzlVar.zzqr;
        zzbq zzbqVar = map.get(str);
        if (zzbqVar == null) {
            zzdi.zzav(String.valueOf(str).concat(" has no backing implementation."));
            return zzajp;
        }
        zzdz<com.google.android.gms.internal.gtm.zzl> zzdzVar = this.zzajv.get(zzotVar);
        if (zzdzVar != null) {
            this.zzajr.zzie();
            return zzdzVar;
        }
        HashMap map2 = new HashMap();
        boolean z8 = true;
        for (Map.Entry<String, com.google.android.gms.internal.gtm.zzl> entry : zzotVar.zzlu().entrySet()) {
            zzdz<com.google.android.gms.internal.gtm.zzl> zzdzVarZza = zza(entry.getValue(), set, zzenVar.zzbg(entry.getKey()).zzb(entry.getValue()));
            zzdz<com.google.android.gms.internal.gtm.zzl> zzdzVar2 = zzajp;
            if (zzdzVarZza == zzdzVar2) {
                return zzdzVar2;
            }
            if (zzdzVarZza.zziu()) {
                zzotVar.zza(entry.getKey(), zzdzVarZza.getObject());
            } else {
                z8 = false;
            }
            map2.put(entry.getKey(), zzdzVarZza.getObject());
        }
        if (!zzbqVar.zza(map2.keySet())) {
            String strValueOf = String.valueOf(zzbqVar.zzig());
            String strValueOf2 = String.valueOf(map2.keySet());
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 43 + strValueOf.length() + strValueOf2.length());
            sb.append("Incorrect keys for function ");
            sb.append(str);
            sb.append(" required ");
            sb.append(strValueOf);
            sb.append(" had ");
            sb.append(strValueOf2);
            zzdi.zzav(sb.toString());
            return zzajp;
        }
        boolean z9 = z8 && zzbqVar.zzgw();
        zzdz<com.google.android.gms.internal.gtm.zzl> zzdzVar3 = new zzdz<>(zzbqVar.zzb(map2), z9);
        if (z9) {
            this.zzajv.zza(zzotVar, zzdzVar3);
        }
        zzenVar.zza(zzdzVar3.getObject());
        return zzdzVar3;
    }
}
