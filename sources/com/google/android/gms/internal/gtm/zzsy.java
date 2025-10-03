package com.google.android.gms.internal.gtm;

import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
interface zzsy {
    int getTag();

    double readDouble();

    float readFloat();

    String readString();

    void readStringList(List<String> list);

    <T> T zza(zzsz<T> zzszVar, zzqp zzqpVar);

    <T> void zza(List<T> list, zzsz<T> zzszVar, zzqp zzqpVar);

    <K, V> void zza(Map<K, V> map, zzsd<K, V> zzsdVar, zzqp zzqpVar);

    @Deprecated
    <T> T zzb(zzsz<T> zzszVar, zzqp zzqpVar);

    @Deprecated
    <T> void zzb(List<T> list, zzsz<T> zzszVar, zzqp zzqpVar);

    void zzg(List<Double> list);

    void zzh(List<Float> list);

    void zzi(List<Long> list);

    void zzj(List<Long> list);

    void zzk(List<Integer> list);

    void zzl(List<Long> list);

    void zzm(List<Integer> list);

    void zzn(List<Boolean> list);

    long zznj();

    long zznk();

    int zznl();

    long zznm();

    int zznn();

    boolean zzno();

    String zznp();

    zzps zznq();

    int zznr();

    int zzns();

    int zznt();

    long zznu();

    int zznv();

    long zznw();

    void zzo(List<String> list);

    int zzog();

    boolean zzoh();

    void zzp(List<zzps> list);

    void zzq(List<Integer> list);

    void zzr(List<Integer> list);

    void zzs(List<Integer> list);

    void zzt(List<Long> list);

    void zzu(List<Integer> list);

    void zzv(List<Long> list);
}
