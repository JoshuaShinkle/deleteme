package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhv;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* loaded from: classes2.dex */
final class zzjh {
    public static String zza(zzjg zzjgVar, String str) throws SecurityException {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zza(zzjgVar, sb, 0);
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:84:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void zza(zzjg zzjgVar, StringBuilder sb, int i9) throws SecurityException {
        boolean zEquals;
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (Method method : zzjgVar.getClass().getDeclaredMethods()) {
            map2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                map.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String strSubstring = str.startsWith("get") ? str.substring(3) : str;
            boolean zBooleanValue = true;
            if (strSubstring.endsWith("List") && !strSubstring.endsWith("OrBuilderList") && !strSubstring.equals("List")) {
                String strValueOf = String.valueOf(strSubstring.substring(0, 1).toLowerCase());
                String strValueOf2 = String.valueOf(strSubstring.substring(1, strSubstring.length() - 4));
                String strConcat = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
                Method method2 = (Method) map.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zza(sb, i9, zza(strConcat), zzhv.zza(method2, zzjgVar, new Object[0]));
                }
            }
            if (strSubstring.endsWith("Map") && !strSubstring.equals("Map")) {
                String strValueOf3 = String.valueOf(strSubstring.substring(0, 1).toLowerCase());
                String strValueOf4 = String.valueOf(strSubstring.substring(1, strSubstring.length() - 3));
                String strConcat2 = strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3);
                Method method3 = (Method) map.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zza(sb, i9, zza(strConcat2), zzhv.zza(method3, zzjgVar, new Object[0]));
                }
            }
            if (((Method) map2.get(strSubstring.length() != 0 ? "set".concat(strSubstring) : new String("set"))) != null) {
                if (strSubstring.endsWith("Bytes")) {
                    String strValueOf5 = String.valueOf(strSubstring.substring(0, strSubstring.length() - 5));
                    if (!map.containsKey(strValueOf5.length() != 0 ? "get".concat(strValueOf5) : new String("get"))) {
                    }
                }
                String strValueOf6 = String.valueOf(strSubstring.substring(0, 1).toLowerCase());
                String strValueOf7 = String.valueOf(strSubstring.substring(1));
                String strConcat3 = strValueOf7.length() != 0 ? strValueOf6.concat(strValueOf7) : new String(strValueOf6);
                Method method4 = (Method) map.get(strSubstring.length() != 0 ? "get".concat(strSubstring) : new String("get"));
                Method method5 = (Method) map.get(strSubstring.length() != 0 ? "has".concat(strSubstring) : new String("has"));
                if (method4 != null) {
                    Object objZza = zzhv.zza(method4, zzjgVar, new Object[0]);
                    if (method5 == null) {
                        if (objZza instanceof Boolean) {
                            zEquals = !((Boolean) objZza).booleanValue();
                        } else if (objZza instanceof Integer) {
                            if (((Integer) objZza).intValue() == 0) {
                            }
                        } else if (objZza instanceof Float) {
                            if (((Float) objZza).floatValue() == BitmapDescriptorFactory.HUE_RED) {
                            }
                        } else if (objZza instanceof Double) {
                            if (((Double) objZza).doubleValue() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            }
                        } else if (objZza instanceof String) {
                            zEquals = objZza.equals("");
                        } else if (objZza instanceof zzgm) {
                            zEquals = objZza.equals(zzgm.zza);
                        } else if (!(objZza instanceof zzjg) ? !(objZza instanceof Enum) || ((Enum) objZza).ordinal() != 0 : objZza != ((zzjg) objZza).zzbv()) {
                        }
                        if (zEquals) {
                            zBooleanValue = false;
                        }
                    } else {
                        zBooleanValue = ((Boolean) zzhv.zza(method5, zzjgVar, new Object[0])).booleanValue();
                    }
                    if (zBooleanValue) {
                        zza(sb, i9, zza(strConcat3), objZza);
                    }
                }
            }
        }
        if (zzjgVar instanceof zzhv.zzd) {
            Iterator<Map.Entry<T, Object>> itZzd = ((zzhv.zzd) zzjgVar).zzc.zzd();
            if (itZzd.hasNext()) {
                throw new NoSuchMethodError();
            }
        }
        zzkq zzkqVar = ((zzhv) zzjgVar).zzb;
        if (zzkqVar != null) {
            zzkqVar.zza(sb, i9);
        }
    }

    public static final void zza(StringBuilder sb, int i9, String str, Object obj) throws SecurityException {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zza(sb, i9, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                zza(sb, i9, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        int i10 = 0;
        for (int i11 = 0; i11 < i9; i11++) {
            sb.append(' ');
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(zzkj.zza(zzgm.zza((String) obj)));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzgm) {
            sb.append(": \"");
            sb.append(zzkj.zza((zzgm) obj));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzhv) {
            sb.append(" {");
            zza((zzhv) obj, sb, i9 + 2);
            sb.append("\n");
            while (i10 < i9) {
                sb.append(' ');
                i10++;
            }
            sb.append("}");
            return;
        }
        if (obj instanceof Map.Entry) {
            sb.append(" {");
            Map.Entry entry = (Map.Entry) obj;
            int i12 = i9 + 2;
            zza(sb, i12, "key", entry.getKey());
            zza(sb, i12, "value", entry.getValue());
            sb.append("\n");
            while (i10 < i9) {
                sb.append(' ');
                i10++;
            }
            sb.append("}");
            return;
        }
        sb.append(": ");
        sb.append(obj.toString());
    }

    private static final String zza(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i9 = 0; i9 < str.length(); i9++) {
            char cCharAt = str.charAt(i9);
            if (Character.isUpperCase(cCharAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(cCharAt));
        }
        return sb.toString();
    }
}
