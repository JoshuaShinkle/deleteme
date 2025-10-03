package com.google.android.gms.internal.play_billing;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes2.dex */
final class zzdh {
    private static final char[] zza;

    static {
        char[] cArr = new char[80];
        zza = cArr;
        Arrays.fill(cArr, ' ');
    }

    public static String zza(zzdf zzdfVar, String str) throws SecurityException {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzdfVar, sb, 0);
        return sb.toString();
    }

    public static void zzb(StringBuilder sb, int i9, String str, Object obj) throws SecurityException {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zzb(sb, i9, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                zzb(sb, i9, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        zzc(i9, sb);
        if (!str.isEmpty()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(Character.toLowerCase(str.charAt(0)));
            for (int i10 = 1; i10 < str.length(); i10++) {
                char cCharAt = str.charAt(i10);
                if (Character.isUpperCase(cCharAt)) {
                    sb2.append("_");
                }
                sb2.append(Character.toLowerCase(cCharAt));
            }
            str = sb2.toString();
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(zzee.zza(new zzax(((String) obj).getBytes(zzcg.zzb))));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzba) {
            sb.append(": \"");
            sb.append(zzee.zza((zzba) obj));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzcb) {
            sb.append(" {");
            zzd((zzcb) obj, sb, i9 + 2);
            sb.append("\n");
            zzc(i9, sb);
            sb.append("}");
            return;
        }
        if (!(obj instanceof Map.Entry)) {
            sb.append(": ");
            sb.append(obj);
            return;
        }
        sb.append(" {");
        Map.Entry entry = (Map.Entry) obj;
        int i11 = i9 + 2;
        zzb(sb, i11, "key", entry.getKey());
        zzb(sb, i11, "value", entry.getValue());
        sb.append("\n");
        zzc(i9, sb);
        sb.append("}");
    }

    private static void zzc(int i9, StringBuilder sb) {
        while (i9 > 0) {
            int i10 = 80;
            if (i9 <= 80) {
                i10 = i9;
            }
            sb.append(zza, 0, i10);
            i9 -= i10;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void zzd(zzdf zzdfVar, StringBuilder sb, int i9) throws SecurityException {
        int i10;
        boolean zEquals;
        Method method;
        Method method2;
        HashSet hashSet = new HashSet();
        HashMap map = new HashMap();
        TreeMap treeMap = new TreeMap();
        Method[] declaredMethods = zzdfVar.getClass().getDeclaredMethods();
        int length = declaredMethods.length;
        int i11 = 0;
        while (true) {
            i10 = 3;
            if (i11 >= length) {
                break;
            }
            Method method3 = declaredMethods[i11];
            if (!Modifier.isStatic(method3.getModifiers()) && method3.getName().length() >= 3) {
                if (method3.getName().startsWith("set")) {
                    hashSet.add(method3.getName());
                } else if (Modifier.isPublic(method3.getModifiers()) && method3.getParameterTypes().length == 0) {
                    if (method3.getName().startsWith("has")) {
                        map.put(method3.getName(), method3);
                    } else if (method3.getName().startsWith("get")) {
                        treeMap.put(method3.getName(), method3);
                    }
                }
            }
            i11++;
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            String strSubstring = ((String) entry.getKey()).substring(i10);
            if (strSubstring.endsWith("List") && !strSubstring.endsWith("OrBuilderList") && !strSubstring.equals("List") && (method2 = (Method) entry.getValue()) != null && method2.getReturnType().equals(List.class)) {
                zzb(sb, i9, strSubstring.substring(0, strSubstring.length() - 4), zzcb.zzl(method2, zzdfVar, new Object[0]));
            } else if (strSubstring.endsWith("Map") && !strSubstring.equals("Map") && (method = (Method) entry.getValue()) != null && method.getReturnType().equals(Map.class) && !method.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method.getModifiers())) {
                zzb(sb, i9, strSubstring.substring(0, strSubstring.length() - 3), zzcb.zzl(method, zzdfVar, new Object[0]));
            } else if (hashSet.contains("set".concat(strSubstring)) && (!strSubstring.endsWith("Bytes") || !treeMap.containsKey("get".concat(String.valueOf(strSubstring.substring(0, strSubstring.length() - 5)))))) {
                Method method4 = (Method) entry.getValue();
                Method method5 = (Method) map.get("has".concat(strSubstring));
                if (method4 != null) {
                    Object objZzl = zzcb.zzl(method4, zzdfVar, new Object[0]);
                    if (method5 == null) {
                        if (objZzl instanceof Boolean) {
                            if (((Boolean) objZzl).booleanValue()) {
                                zzb(sb, i9, strSubstring, objZzl);
                            }
                        } else if (objZzl instanceof Integer) {
                            if (((Integer) objZzl).intValue() != 0) {
                            }
                        } else if (objZzl instanceof Float) {
                            if (Float.floatToRawIntBits(((Float) objZzl).floatValue()) != 0) {
                            }
                        } else if (!(objZzl instanceof Double)) {
                            if (objZzl instanceof String) {
                                zEquals = objZzl.equals("");
                            } else if (objZzl instanceof zzba) {
                                zEquals = objZzl.equals(zzba.zzb);
                            } else if (objZzl instanceof zzdf) {
                                if (objZzl != ((zzdf) objZzl).zzf()) {
                                }
                            } else if (!(objZzl instanceof Enum) || ((Enum) objZzl).ordinal() != 0) {
                            }
                            if (!zEquals) {
                            }
                        } else if (Double.doubleToRawLongBits(((Double) objZzl).doubleValue()) != 0) {
                        }
                    } else if (((Boolean) zzcb.zzl(method5, zzdfVar, new Object[0])).booleanValue()) {
                    }
                }
            }
            i10 = 3;
        }
        if (zzdfVar instanceof zzby) {
            throw null;
        }
        zzeh zzehVar = ((zzcb) zzdfVar).zzc;
        if (zzehVar != null) {
            zzehVar.zzi(sb, i9);
        }
    }
}
