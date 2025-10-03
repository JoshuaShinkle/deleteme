package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzbv;
import com.google.android.gms.internal.measurement.zzcd;
import com.google.android.gms.internal.measurement.zzmh;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes2.dex */
public final class zzkt extends zzkm {
    public zzkt(zzkp zzkpVar) {
        super(zzkpVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    /* renamed from: f_ */
    public final /* bridge */ /* synthetic */ zzkt mo17540f_() {
        return super.mo17540f_();
    }

    public final void zza(zzcd.zzk.zza zzaVar, Object obj) {
        Preconditions.checkNotNull(obj);
        zzaVar.zza().zzb().zzc();
        if (obj instanceof String) {
            zzaVar.zzb((String) obj);
            return;
        }
        if (obj instanceof Long) {
            zzaVar.zzb(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zzaVar.zza(((Double) obj).doubleValue());
        } else {
            zzq().zze().zza("Ignoring invalid (type) user attribute value", obj);
        }
    }

    public final Object zzb(zzcd.zzc zzcVar, String str) {
        zzcd.zze zzeVarZza = zza(zzcVar, str);
        if (zzeVarZza == null) {
            return null;
        }
        if (zzeVarZza.zzc()) {
            return zzeVarZza.zzd();
        }
        if (zzeVarZza.zze()) {
            return Long.valueOf(zzeVarZza.zzf());
        }
        if (zzeVarZza.zzi()) {
            return Double.valueOf(zzeVarZza.zzj());
        }
        if (!zzmh.zzb() || !zzs().zza(zzat.zzbz) || zzeVarZza.zzl() <= 0) {
            return null;
        }
        List<zzcd.zze> listZzk = zzeVarZza.zzk();
        ArrayList arrayList = new ArrayList();
        for (zzcd.zze zzeVar : listZzk) {
            if (zzeVar != null) {
                Bundle bundle = new Bundle();
                for (zzcd.zze zzeVar2 : zzeVar.zzk()) {
                    if (zzeVar2.zzc()) {
                        bundle.putString(zzeVar2.zzb(), zzeVar2.zzd());
                    } else if (zzeVar2.zze()) {
                        bundle.putLong(zzeVar2.zzb(), zzeVar2.zzf());
                    } else if (zzeVar2.zzi()) {
                        bundle.putDouble(zzeVar2.zzb(), zzeVar2.zzj());
                    }
                }
                if (!bundle.isEmpty()) {
                    arrayList.add(bundle);
                }
            }
        }
        return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
    }

    public final byte[] zzc(byte[] bArr) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e9) {
            zzq().zze().zza("Failed to gzip content", e9);
            throw e9;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzkm
    public final boolean zzd() {
        return false;
    }

    public final List<Integer> zze() throws NumberFormatException {
        Map<String, String> mapZza = zzat.zza(this.zza.zzm());
        if (mapZza == null || mapZza.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int iIntValue = zzat.zzao.zza(null).intValue();
        for (Map.Entry<String, String> entry : mapZza.entrySet()) {
            if (entry.getKey().startsWith("measurement.id.")) {
                try {
                    int i9 = Integer.parseInt(entry.getValue());
                    if (i9 != 0) {
                        arrayList.add(Integer.valueOf(i9));
                        if (arrayList.size() >= iIntValue) {
                            zzq().zzh().zza("Too many experiment IDs. Number of IDs", Integer.valueOf(arrayList.size()));
                            break;
                        }
                        continue;
                    } else {
                        continue;
                    }
                } catch (NumberFormatException e9) {
                    zzq().zzh().zza("Experiment ID NumberFormatException", e9);
                }
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    public final /* bridge */ /* synthetic */ zzjv zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    public final /* bridge */ /* synthetic */ zzo zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    public final /* bridge */ /* synthetic */ zzac zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    public final /* bridge */ /* synthetic */ zzfv zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzal zzk() {
        return super.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ Clock zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ Context zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzev zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzkx zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzfu zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzex zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzfj zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzy zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    public final void zza(zzcd.zze.zza zzaVar, Object obj) {
        Preconditions.checkNotNull(obj);
        zzaVar.zza().zzb().zzc().zze();
        if (obj instanceof String) {
            zzaVar.zzb((String) obj);
            return;
        }
        if (obj instanceof Long) {
            zzaVar.zza(((Long) obj).longValue());
            return;
        }
        if (obj instanceof Double) {
            zzaVar.zza(((Double) obj).doubleValue());
        } else if (zzmh.zzb() && zzs().zza(zzat.zzbz) && (obj instanceof Bundle[])) {
            zzaVar.zza(zza((Bundle[]) obj));
        } else {
            zzq().zze().zza("Ignoring invalid (type) event param value", obj);
        }
    }

    public static zzcd.zze zza(zzcd.zzc zzcVar, String str) {
        for (zzcd.zze zzeVar : zzcVar.zza()) {
            if (zzeVar.zzb().equals(str)) {
                return zzeVar;
            }
        }
        return null;
    }

    public final zzcd.zzc zza(zzak zzakVar) {
        zzcd.zzc.zza zzaVarZzb = zzcd.zzc.zzj().zzb(zzakVar.zzd);
        Iterator<String> it = zzakVar.zze.iterator();
        while (it.hasNext()) {
            String next = it.next();
            zzcd.zze.zza zzaVarZza = zzcd.zze.zzm().zza(next);
            zza(zzaVarZza, zzakVar.zze.zza(next));
            zzaVarZzb.zza(zzaVarZza);
        }
        return (zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzb.zzy());
    }

    public final byte[] zzb(byte[] bArr) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[UserMetadata.MAX_ATTRIBUTE_SIZE];
            while (true) {
                int i9 = gZIPInputStream.read(bArr2);
                if (i9 > 0) {
                    byteArrayOutputStream.write(bArr2, 0, i9);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e9) {
            zzq().zze().zza("Failed to ungzip content", e9);
            throw e9;
        }
    }

    public final void zza(zzcd.zzc.zza zzaVar, String str, Object obj) {
        List<zzcd.zze> listZza = zzaVar.zza();
        int i9 = 0;
        while (true) {
            if (i9 >= listZza.size()) {
                i9 = -1;
                break;
            } else if (str.equals(listZza.get(i9).zzb())) {
                break;
            } else {
                i9++;
            }
        }
        zzcd.zze.zza zzaVarZza = zzcd.zze.zzm().zza(str);
        if (obj instanceof Long) {
            zzaVarZza.zza(((Long) obj).longValue());
        } else if (obj instanceof String) {
            zzaVarZza.zzb((String) obj);
        } else if (obj instanceof Double) {
            zzaVarZza.zza(((Double) obj).doubleValue());
        } else if (zzmh.zzb() && zzs().zza(zzat.zzbz) && (obj instanceof Bundle[])) {
            zzaVarZza.zza(zza((Bundle[]) obj));
        }
        if (i9 >= 0) {
            zzaVar.zza(i9, zzaVarZza);
        } else {
            zzaVar.zza(zzaVarZza);
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    public final String zza(zzcd.zzf zzfVar) {
        if (zzfVar == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        for (zzcd.zzg zzgVar : zzfVar.zza()) {
            if (zzgVar != null) {
                zza(sb, 1);
                sb.append("bundle {\n");
                if (zzgVar.zza()) {
                    zza(sb, 1, "protocol_version", Integer.valueOf(zzgVar.zzb()));
                }
                zza(sb, 1, "platform", zzgVar.zzq());
                if (zzgVar.zzz()) {
                    zza(sb, 1, "gmp_version", Long.valueOf(zzgVar.zzaa()));
                }
                if (zzgVar.zzab()) {
                    zza(sb, 1, "uploading_gmp_version", Long.valueOf(zzgVar.zzac()));
                }
                if (zzgVar.zzbc()) {
                    zza(sb, 1, "dynamite_version", Long.valueOf(zzgVar.zzbd()));
                }
                if (zzgVar.zzau()) {
                    zza(sb, 1, "config_version", Long.valueOf(zzgVar.zzav()));
                }
                zza(sb, 1, "gmp_app_id", zzgVar.zzam());
                zza(sb, 1, "admob_app_id", zzgVar.zzbb());
                zza(sb, 1, "app_id", zzgVar.zzx());
                zza(sb, 1, "app_version", zzgVar.zzy());
                if (zzgVar.zzar()) {
                    zza(sb, 1, "app_version_major", Integer.valueOf(zzgVar.zzas()));
                }
                zza(sb, 1, "firebase_instance_id", zzgVar.zzaq());
                if (zzgVar.zzah()) {
                    zza(sb, 1, "dev_cert_hash", Long.valueOf(zzgVar.zzai()));
                }
                zza(sb, 1, "app_store", zzgVar.zzw());
                if (zzgVar.zzg()) {
                    zza(sb, 1, "upload_timestamp_millis", Long.valueOf(zzgVar.zzh()));
                }
                if (zzgVar.zzi()) {
                    zza(sb, 1, "start_timestamp_millis", Long.valueOf(zzgVar.zzj()));
                }
                if (zzgVar.zzk()) {
                    zza(sb, 1, "end_timestamp_millis", Long.valueOf(zzgVar.zzl()));
                }
                if (zzgVar.zzm()) {
                    zza(sb, 1, "previous_bundle_start_timestamp_millis", Long.valueOf(zzgVar.zzn()));
                }
                if (zzgVar.zzo()) {
                    zza(sb, 1, "previous_bundle_end_timestamp_millis", Long.valueOf(zzgVar.zzp()));
                }
                zza(sb, 1, "app_instance_id", zzgVar.zzag());
                zza(sb, 1, "resettable_device_id", zzgVar.zzad());
                zza(sb, 1, "device_id", zzgVar.zzat());
                zza(sb, 1, "ds_id", zzgVar.zzay());
                if (zzgVar.zzae()) {
                    zza(sb, 1, "limited_ad_tracking", Boolean.valueOf(zzgVar.zzaf()));
                }
                zza(sb, 1, "os_version", zzgVar.zzr());
                zza(sb, 1, "device_model", zzgVar.zzs());
                zza(sb, 1, "user_default_language", zzgVar.zzt());
                if (zzgVar.zzu()) {
                    zza(sb, 1, "time_zone_offset_minutes", Integer.valueOf(zzgVar.zzv()));
                }
                if (zzgVar.zzaj()) {
                    zza(sb, 1, "bundle_sequential_index", Integer.valueOf(zzgVar.zzak()));
                }
                if (zzgVar.zzan()) {
                    zza(sb, 1, "service_upload", Boolean.valueOf(zzgVar.zzao()));
                }
                zza(sb, 1, "health_monitor", zzgVar.zzal());
                if (!zzs().zza(zzat.zzcf) && zzgVar.zzaw() && zzgVar.zzax() != 0) {
                    zza(sb, 1, "android_id", Long.valueOf(zzgVar.zzax()));
                }
                if (zzgVar.zzaz()) {
                    zza(sb, 1, "retry_counter", Integer.valueOf(zzgVar.zzba()));
                }
                if (zzgVar.zzbf()) {
                    zza(sb, 1, "consent_signals", zzgVar.zzbg());
                }
                List<zzcd.zzk> listZze = zzgVar.zze();
                if (listZze != null) {
                    for (zzcd.zzk zzkVar : listZze) {
                        if (zzkVar != null) {
                            zza(sb, 2);
                            sb.append("user_property {\n");
                            zza(sb, 2, "set_timestamp_millis", zzkVar.zza() ? Long.valueOf(zzkVar.zzb()) : null);
                            zza(sb, 2, AppMeasurementSdk.ConditionalUserProperty.NAME, zzn().zzc(zzkVar.zzc()));
                            zza(sb, 2, "string_value", zzkVar.zze());
                            zza(sb, 2, "int_value", zzkVar.zzf() ? Long.valueOf(zzkVar.zzg()) : null);
                            zza(sb, 2, "double_value", zzkVar.zzh() ? Double.valueOf(zzkVar.zzi()) : null);
                            zza(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<zzcd.zza> listZzap = zzgVar.zzap();
                String strZzx = zzgVar.zzx();
                if (listZzap != null) {
                    for (zzcd.zza zzaVar : listZzap) {
                        if (zzaVar != null) {
                            zza(sb, 2);
                            sb.append("audience_membership {\n");
                            if (zzaVar.zza()) {
                                zza(sb, 2, "audience_id", Integer.valueOf(zzaVar.zzb()));
                            }
                            if (zzaVar.zzf()) {
                                zza(sb, 2, "new_audience", Boolean.valueOf(zzaVar.zzg()));
                            }
                            zza(sb, 2, "current_data", zzaVar.zzc(), strZzx);
                            if (zzaVar.zzd()) {
                                zza(sb, 2, "previous_data", zzaVar.zze(), strZzx);
                            }
                            zza(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<zzcd.zzc> listZzc = zzgVar.zzc();
                if (listZzc != null) {
                    for (zzcd.zzc zzcVar : listZzc) {
                        if (zzcVar != null) {
                            zza(sb, 2);
                            sb.append("event {\n");
                            zza(sb, 2, AppMeasurementSdk.ConditionalUserProperty.NAME, zzn().zza(zzcVar.zzc()));
                            if (zzcVar.zzd()) {
                                zza(sb, 2, "timestamp_millis", Long.valueOf(zzcVar.zze()));
                            }
                            if (zzcVar.zzf()) {
                                zza(sb, 2, "previous_timestamp_millis", Long.valueOf(zzcVar.zzg()));
                            }
                            if (zzcVar.zzh()) {
                                zza(sb, 2, "count", Integer.valueOf(zzcVar.zzi()));
                            }
                            if (zzcVar.zzb() != 0) {
                                zza(sb, 2, zzcVar.zza());
                            }
                            zza(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                zza(sb, 1);
                sb.append("}\n");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    public final String zza(zzbv.zzb zzbVar) {
        if (zzbVar == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        if (zzbVar.zza()) {
            zza(sb, 0, "filter_id", Integer.valueOf(zzbVar.zzb()));
        }
        zza(sb, 0, "event_name", zzn().zza(zzbVar.zzc()));
        String strZza = zza(zzbVar.zzh(), zzbVar.zzi(), zzbVar.zzk());
        if (!strZza.isEmpty()) {
            zza(sb, 0, "filter_type", strZza);
        }
        if (zzbVar.zzf()) {
            zza(sb, 1, "event_count_filter", zzbVar.zzg());
        }
        if (zzbVar.zze() > 0) {
            sb.append("  filters {\n");
            Iterator<zzbv.zzc> it = zzbVar.zzd().iterator();
            while (it.hasNext()) {
                zza(sb, 2, it.next());
            }
        }
        zza(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    public final String zza(zzbv.zze zzeVar) {
        if (zzeVar == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        if (zzeVar.zza()) {
            zza(sb, 0, "filter_id", Integer.valueOf(zzeVar.zzb()));
        }
        zza(sb, 0, "property_name", zzn().zzc(zzeVar.zzc()));
        String strZza = zza(zzeVar.zze(), zzeVar.zzf(), zzeVar.zzh());
        if (!strZza.isEmpty()) {
            zza(sb, 0, "filter_type", strZza);
        }
        zza(sb, 1, zzeVar.zzd());
        sb.append("}\n");
        return sb.toString();
    }

    private static String zza(boolean z8, boolean z9, boolean z10) {
        StringBuilder sb = new StringBuilder();
        if (z8) {
            sb.append("Dynamic ");
        }
        if (z9) {
            sb.append("Sequence ");
        }
        if (z10) {
            sb.append("Session-Scoped ");
        }
        return sb.toString();
    }

    private final void zza(StringBuilder sb, int i9, List<zzcd.zze> list) {
        if (list == null) {
            return;
        }
        int i10 = i9 + 1;
        for (zzcd.zze zzeVar : list) {
            if (zzeVar != null) {
                zza(sb, i10);
                sb.append("param {\n");
                if (zzmh.zzb() && zzs().zza(zzat.zzbx)) {
                    zza(sb, i10, AppMeasurementSdk.ConditionalUserProperty.NAME, zzeVar.zza() ? zzn().zzb(zzeVar.zzb()) : null);
                    zza(sb, i10, "string_value", zzeVar.zzc() ? zzeVar.zzd() : null);
                    zza(sb, i10, "int_value", zzeVar.zze() ? Long.valueOf(zzeVar.zzf()) : null);
                    zza(sb, i10, "double_value", zzeVar.zzi() ? Double.valueOf(zzeVar.zzj()) : null);
                    if (zzeVar.zzl() > 0) {
                        zza(sb, i10, zzeVar.zzk());
                    }
                } else {
                    zza(sb, i10, AppMeasurementSdk.ConditionalUserProperty.NAME, zzn().zzb(zzeVar.zzb()));
                    zza(sb, i10, "string_value", zzeVar.zzd());
                    zza(sb, i10, "int_value", zzeVar.zze() ? Long.valueOf(zzeVar.zzf()) : null);
                    zza(sb, i10, "double_value", zzeVar.zzi() ? Double.valueOf(zzeVar.zzj()) : null);
                }
                zza(sb, i10);
                sb.append("}\n");
            }
        }
    }

    private static void zza(StringBuilder sb, int i9, String str, zzcd.zzi zziVar, String str2) {
        if (zziVar == null) {
            return;
        }
        zza(sb, 3);
        sb.append(str);
        sb.append(" {\n");
        if (zziVar.zzd() != 0) {
            zza(sb, 4);
            sb.append("results: ");
            int i10 = 0;
            for (Long l9 : zziVar.zzc()) {
                int i11 = i10 + 1;
                if (i10 != 0) {
                    sb.append(", ");
                }
                sb.append(l9);
                i10 = i11;
            }
            sb.append('\n');
        }
        if (zziVar.zzb() != 0) {
            zza(sb, 4);
            sb.append("status: ");
            int i12 = 0;
            for (Long l10 : zziVar.zza()) {
                int i13 = i12 + 1;
                if (i12 != 0) {
                    sb.append(", ");
                }
                sb.append(l10);
                i12 = i13;
            }
            sb.append('\n');
        }
        if (zziVar.zzf() != 0) {
            zza(sb, 4);
            sb.append("dynamic_filter_timestamps: {");
            int i14 = 0;
            for (zzcd.zzb zzbVar : zziVar.zze()) {
                int i15 = i14 + 1;
                if (i14 != 0) {
                    sb.append(", ");
                }
                sb.append(zzbVar.zza() ? Integer.valueOf(zzbVar.zzb()) : null);
                sb.append(":");
                sb.append(zzbVar.zzc() ? Long.valueOf(zzbVar.zzd()) : null);
                i14 = i15;
            }
            sb.append("}\n");
        }
        if (zziVar.zzh() != 0) {
            zza(sb, 4);
            sb.append("sequence_filter_timestamps: {");
            int i16 = 0;
            for (zzcd.zzj zzjVar : zziVar.zzg()) {
                int i17 = i16 + 1;
                if (i16 != 0) {
                    sb.append(", ");
                }
                sb.append(zzjVar.zza() ? Integer.valueOf(zzjVar.zzb()) : null);
                sb.append(": [");
                Iterator<Long> it = zzjVar.zzc().iterator();
                int i18 = 0;
                while (it.hasNext()) {
                    long jLongValue = it.next().longValue();
                    int i19 = i18 + 1;
                    if (i18 != 0) {
                        sb.append(", ");
                    }
                    sb.append(jLongValue);
                    i18 = i19;
                }
                sb.append("]");
                i16 = i17;
            }
            sb.append("}\n");
        }
        zza(sb, 3);
        sb.append("}\n");
    }

    private static void zza(StringBuilder sb, int i9, String str, zzbv.zzd zzdVar) {
        if (zzdVar == null) {
            return;
        }
        zza(sb, i9);
        sb.append(str);
        sb.append(" {\n");
        if (zzdVar.zza()) {
            zza(sb, i9, "comparison_type", zzdVar.zzb().name());
        }
        if (zzdVar.zzc()) {
            zza(sb, i9, "match_as_float", Boolean.valueOf(zzdVar.zzd()));
        }
        if (zzdVar.zze()) {
            zza(sb, i9, "comparison_value", zzdVar.zzf());
        }
        if (zzdVar.zzg()) {
            zza(sb, i9, "min_comparison_value", zzdVar.zzh());
        }
        if (zzdVar.zzi()) {
            zza(sb, i9, "max_comparison_value", zzdVar.zzj());
        }
        zza(sb, i9);
        sb.append("}\n");
    }

    private final void zza(StringBuilder sb, int i9, zzbv.zzc zzcVar) {
        if (zzcVar == null) {
            return;
        }
        zza(sb, i9);
        sb.append("filter {\n");
        if (zzcVar.zze()) {
            zza(sb, i9, "complement", Boolean.valueOf(zzcVar.zzf()));
        }
        if (zzcVar.zzg()) {
            zza(sb, i9, "param_name", zzn().zzb(zzcVar.zzh()));
        }
        if (zzcVar.zza()) {
            int i10 = i9 + 1;
            zzbv.zzf zzfVarZzb = zzcVar.zzb();
            if (zzfVarZzb != null) {
                zza(sb, i10);
                sb.append("string_filter");
                sb.append(" {\n");
                if (zzfVarZzb.zza()) {
                    zza(sb, i10, "match_type", zzfVarZzb.zzb().name());
                }
                if (zzfVarZzb.zzc()) {
                    zza(sb, i10, "expression", zzfVarZzb.zzd());
                }
                if (zzfVarZzb.zze()) {
                    zza(sb, i10, "case_sensitive", Boolean.valueOf(zzfVarZzb.zzf()));
                }
                if (zzfVarZzb.zzh() > 0) {
                    zza(sb, i10 + 1);
                    sb.append("expression_list {\n");
                    for (String str : zzfVarZzb.zzg()) {
                        zza(sb, i10 + 2);
                        sb.append(str);
                        sb.append("\n");
                    }
                    sb.append("}\n");
                }
                zza(sb, i10);
                sb.append("}\n");
            }
        }
        if (zzcVar.zzc()) {
            zza(sb, i9 + 1, "number_filter", zzcVar.zzd());
        }
        zza(sb, i9);
        sb.append("}\n");
    }

    private static void zza(StringBuilder sb, int i9) {
        for (int i10 = 0; i10 < i9; i10++) {
            sb.append("  ");
        }
    }

    private static void zza(StringBuilder sb, int i9, String str, Object obj) {
        if (obj == null) {
            return;
        }
        zza(sb, i9 + 1);
        sb.append(str);
        sb.append(": ");
        sb.append(obj);
        sb.append('\n');
    }

    public final <T extends Parcelable> T zza(byte[] bArr, Parcelable.Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.unmarshall(bArr, 0, bArr.length);
            parcelObtain.setDataPosition(0);
            return creator.createFromParcel(parcelObtain);
        } catch (SafeParcelReader.ParseException unused) {
            zzq().zze().zza("Failed to load parcelable from buffer");
            return null;
        } finally {
            parcelObtain.recycle();
        }
    }

    public static boolean zza(zzar zzarVar, zzn zznVar) {
        Preconditions.checkNotNull(zzarVar);
        Preconditions.checkNotNull(zznVar);
        return (TextUtils.isEmpty(zznVar.zzb) && TextUtils.isEmpty(zznVar.zzr)) ? false : true;
    }

    public static boolean zza(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    public static boolean zza(List<Long> list, int i9) {
        if (i9 < (list.size() << 6)) {
            return ((1 << (i9 % 64)) & list.get(i9 / 64).longValue()) != 0;
        }
        return false;
    }

    public static List<Long> zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        ArrayList arrayList = new ArrayList(length);
        for (int i9 = 0; i9 < length; i9++) {
            long j9 = 0;
            for (int i10 = 0; i10 < 64; i10++) {
                int i11 = (i9 << 6) + i10;
                if (i11 < bitSet.length()) {
                    if (bitSet.get(i11)) {
                        j9 |= 1 << i10;
                    }
                }
            }
            arrayList.add(Long.valueOf(j9));
        }
        return arrayList;
    }

    public final List<Long> zza(List<Long> list, List<Integer> list2) {
        int i9;
        ArrayList arrayList = new ArrayList(list);
        for (Integer num : list2) {
            if (num.intValue() < 0) {
                zzq().zzh().zza("Ignoring negative bit index to be cleared", num);
            } else {
                int iIntValue = num.intValue() / 64;
                if (iIntValue >= arrayList.size()) {
                    zzq().zzh().zza("Ignoring bit index greater than bitSet size", num, Integer.valueOf(arrayList.size()));
                } else {
                    arrayList.set(iIntValue, Long.valueOf(((Long) arrayList.get(iIntValue)).longValue() & (~(1 << (num.intValue() % 64)))));
                }
            }
        }
        int size = arrayList.size();
        int size2 = arrayList.size() - 1;
        while (true) {
            int i10 = size2;
            i9 = size;
            size = i10;
            if (size < 0 || ((Long) arrayList.get(size)).longValue() != 0) {
                break;
            }
            size2 = size - 1;
        }
        return arrayList.subList(0, i9);
    }

    public final boolean zza(long j9, long j10) {
        return j9 == 0 || j10 <= 0 || Math.abs(zzl().currentTimeMillis() - j9) > j10;
    }

    public final long zza(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        zzo().zzc();
        MessageDigest messageDigestZzh = zzkx.zzh();
        if (messageDigestZzh == null) {
            zzq().zze().zza("Failed to get MD5");
            return 0L;
        }
        return zzkx.zza(messageDigestZzh.digest(bArr));
    }

    public static <Builder extends com.google.android.gms.internal.measurement.zzjf> Builder zza(Builder builder, byte[] bArr) {
        com.google.android.gms.internal.measurement.zzhi zzhiVarZzb = com.google.android.gms.internal.measurement.zzhi.zzb();
        if (zzhiVarZzb != null) {
            return (Builder) builder.zza(bArr, zzhiVarZzb);
        }
        return (Builder) builder.zza(bArr);
    }

    public static int zza(zzcd.zzg.zza zzaVar, String str) {
        if (zzaVar == null) {
            return -1;
        }
        for (int i9 = 0; i9 < zzaVar.zze(); i9++) {
            if (str.equals(zzaVar.zzd(i9).zzc())) {
                return i9;
            }
        }
        return -1;
    }

    private static List<zzcd.zze> zza(Bundle[] bundleArr) {
        ArrayList arrayList = new ArrayList();
        for (Bundle bundle : bundleArr) {
            if (bundle != null) {
                zzcd.zze.zza zzaVarZzm = zzcd.zze.zzm();
                for (String str : bundle.keySet()) {
                    zzcd.zze.zza zzaVarZza = zzcd.zze.zzm().zza(str);
                    Object obj = bundle.get(str);
                    if (obj instanceof Long) {
                        zzaVarZza.zza(((Long) obj).longValue());
                    } else if (obj instanceof String) {
                        zzaVarZza.zzb((String) obj);
                    } else if (obj instanceof Double) {
                        zzaVarZza.zza(((Double) obj).doubleValue());
                    }
                    zzaVarZzm.zza(zzaVarZza);
                }
                if (zzaVarZzm.zzd() > 0) {
                    arrayList.add((zzcd.zze) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzm.zzy()));
                }
            }
        }
        return arrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }
}
