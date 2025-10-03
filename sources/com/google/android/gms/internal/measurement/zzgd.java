package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzgf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class zzgd<MessageType extends zzgd<MessageType, BuilderType>, BuilderType extends zzgf<MessageType, BuilderType>> implements zzjg {
    protected int zza = 0;

    public static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzhx.zza(iterable);
        if (iterable instanceof zzin) {
            List<?> listZzb = ((zzin) iterable).zzb();
            zzin zzinVar = (zzin) list;
            int size = list.size();
            for (Object obj : listZzb) {
                if (obj == null) {
                    int size2 = zzinVar.size() - size;
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Element at index ");
                    sb.append(size2);
                    sb.append(" is null.");
                    String string = sb.toString();
                    for (int size3 = zzinVar.size() - 1; size3 >= size; size3--) {
                        zzinVar.remove(size3);
                    }
                    throw new NullPointerException(string);
                }
                if (obj instanceof zzgm) {
                    zzinVar.zza((zzgm) obj);
                } else {
                    zzinVar.add((String) obj);
                }
            }
            return;
        }
        if (iterable instanceof zzjs) {
            list.addAll((Collection) iterable);
            return;
        }
        if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
            ((ArrayList) list).ensureCapacity(list.size() + ((Collection) iterable).size());
        }
        int size4 = list.size();
        for (T t8 : iterable) {
            if (t8 == null) {
                int size5 = list.size() - size4;
                StringBuilder sb2 = new StringBuilder(37);
                sb2.append("Element at index ");
                sb2.append(size5);
                sb2.append(" is null.");
                String string2 = sb2.toString();
                for (int size6 = list.size() - 1; size6 >= size4; size6--) {
                    list.remove(size6);
                }
                throw new NullPointerException(string2);
            }
            list.add(t8);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final zzgm zzbj() {
        try {
            zzgu zzguVarZzc = zzgm.zzc(zzbp());
            zza(zzguVarZzc.zzb());
            return zzguVarZzc.zza();
        } catch (IOException e9) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(name.length() + 62 + "ByteString".length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("ByteString");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e9);
        }
    }

    public final byte[] zzbk() {
        try {
            byte[] bArr = new byte[zzbp()];
            zzhf zzhfVarZza = zzhf.zza(bArr);
            zza(zzhfVarZza);
            zzhfVarZza.zzb();
            return bArr;
        } catch (IOException e9) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(name.length() + 62 + "byte array".length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("byte array");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e9);
        }
    }

    public int zzbl() {
        throw new UnsupportedOperationException();
    }

    public void zzc(int i9) {
        throw new UnsupportedOperationException();
    }
}
