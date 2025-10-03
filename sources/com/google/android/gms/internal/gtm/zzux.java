package com.google.android.gms.internal.gtm;

import com.google.common.primitives.UnsignedBytes;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.commons.p159io.IOUtils;

/* loaded from: classes2.dex */
public final class zzux {
    private static void zza(String str, Object obj, StringBuffer stringBuffer, StringBuffer stringBuffer2) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        if (obj != null) {
            if (!(obj instanceof zzuw)) {
                String strZzde = zzde(str);
                stringBuffer2.append(stringBuffer);
                stringBuffer2.append(strZzde);
                stringBuffer2.append(": ");
                if (obj instanceof String) {
                    String strConcat = (String) obj;
                    if (!strConcat.startsWith("http") && strConcat.length() > 200) {
                        strConcat = String.valueOf(strConcat.substring(0, 200)).concat("[...]");
                    }
                    int length = strConcat.length();
                    StringBuilder sb = new StringBuilder(length);
                    for (int i9 = 0; i9 < length; i9++) {
                        char cCharAt = strConcat.charAt(i9);
                        if (cCharAt < ' ' || cCharAt > '~' || cCharAt == '\"' || cCharAt == '\'') {
                            sb.append(String.format("\\u%04x", Integer.valueOf(cCharAt)));
                        } else {
                            sb.append(cCharAt);
                        }
                    }
                    String string = sb.toString();
                    stringBuffer2.append("\"");
                    stringBuffer2.append(string);
                    stringBuffer2.append("\"");
                } else if (obj instanceof byte[]) {
                    stringBuffer2.append('\"');
                    for (byte b9 : (byte[]) obj) {
                        int i10 = b9 & UnsignedBytes.MAX_VALUE;
                        if (i10 == 92 || i10 == 34) {
                            stringBuffer2.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                            stringBuffer2.append((char) i10);
                        } else if (i10 < 32 || i10 >= 127) {
                            stringBuffer2.append(String.format("\\%03o", Integer.valueOf(i10)));
                        } else {
                            stringBuffer2.append((char) i10);
                        }
                    }
                    stringBuffer2.append('\"');
                } else {
                    stringBuffer2.append(obj);
                }
                stringBuffer2.append("\n");
                return;
            }
            int length2 = stringBuffer.length();
            if (str != null) {
                stringBuffer2.append(stringBuffer);
                stringBuffer2.append(zzde(str));
                stringBuffer2.append(" <\n");
                stringBuffer.append("  ");
            }
            Class<?> cls = obj.getClass();
            for (Field field : cls.getFields()) {
                int modifiers = field.getModifiers();
                String name = field.getName();
                if (!"cachedSize".equals(name) && (modifiers & 1) == 1 && (modifiers & 8) != 8 && !name.startsWith("_") && !name.endsWith("_")) {
                    Class<?> type = field.getType();
                    Object obj2 = field.get(obj);
                    if (!type.isArray() || type.getComponentType() == Byte.TYPE) {
                        zza(name, obj2, stringBuffer, stringBuffer2);
                    } else {
                        int length3 = obj2 == null ? 0 : Array.getLength(obj2);
                        for (int i11 = 0; i11 < length3; i11++) {
                            zza(name, Array.get(obj2, i11), stringBuffer, stringBuffer2);
                        }
                    }
                }
            }
            for (Method method : cls.getMethods()) {
                String name2 = method.getName();
                if (name2.startsWith("set")) {
                    String strSubstring = name2.substring(3);
                    try {
                        String strValueOf = String.valueOf(strSubstring);
                        if (((Boolean) cls.getMethod(strValueOf.length() != 0 ? "has".concat(strValueOf) : new String("has"), new Class[0]).invoke(obj, new Object[0])).booleanValue()) {
                            String strValueOf2 = String.valueOf(strSubstring);
                            zza(strSubstring, cls.getMethod(strValueOf2.length() != 0 ? "get".concat(strValueOf2) : new String("get"), new Class[0]).invoke(obj, new Object[0]), stringBuffer, stringBuffer2);
                        }
                    } catch (NoSuchMethodException unused) {
                    }
                }
            }
            if (str != null) {
                stringBuffer.setLength(length2);
                stringBuffer2.append(stringBuffer);
                stringBuffer2.append(">\n");
            }
        }
    }

    public static <T extends zzuw> String zzc(T t8) throws SecurityException, IllegalArgumentException {
        if (t8 == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            zza(null, t8, new StringBuffer(), stringBuffer);
            return stringBuffer.toString();
        } catch (IllegalAccessException e9) {
            String strValueOf = String.valueOf(e9.getMessage());
            return strValueOf.length() != 0 ? "Error printing proto: ".concat(strValueOf) : new String("Error printing proto: ");
        } catch (InvocationTargetException e10) {
            String strValueOf2 = String.valueOf(e10.getMessage());
            return strValueOf2.length() != 0 ? "Error printing proto: ".concat(strValueOf2) : new String("Error printing proto: ");
        }
    }

    private static String zzde(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i9 = 0; i9 < str.length(); i9++) {
            char cCharAt = str.charAt(i9);
            if (i9 == 0) {
                stringBuffer.append(Character.toLowerCase(cCharAt));
            } else if (Character.isUpperCase(cCharAt)) {
                stringBuffer.append('_');
                stringBuffer.append(Character.toLowerCase(cCharAt));
            } else {
                stringBuffer.append(cCharAt);
            }
        }
        return stringBuffer.toString();
    }
}
