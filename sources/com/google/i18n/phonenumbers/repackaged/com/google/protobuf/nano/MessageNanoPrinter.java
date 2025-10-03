package com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano;

import com.google.common.primitives.UnsignedBytes;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.commons.p159io.IOUtils;

/* loaded from: classes2.dex */
public final class MessageNanoPrinter {
    private static final String INDENT = "  ";
    private static final int MAX_STRING_LEN = 200;

    private MessageNanoPrinter() {
    }

    private static void appendQuotedBytes(byte[] bArr, StringBuffer stringBuffer) {
        if (bArr == null) {
            stringBuffer.append("\"\"");
            return;
        }
        stringBuffer.append('\"');
        for (byte b9 : bArr) {
            int i9 = b9 & UnsignedBytes.MAX_VALUE;
            if (i9 == 92 || i9 == 34) {
                stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                stringBuffer.append((char) i9);
            } else if (i9 < 32 || i9 >= 127) {
                stringBuffer.append(String.format("\\%03o", Integer.valueOf(i9)));
            } else {
                stringBuffer.append((char) i9);
            }
        }
        stringBuffer.append('\"');
    }

    private static String deCamelCaseify(String str) {
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

    private static String escapeString(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i9 = 0; i9 < length; i9++) {
            char cCharAt = str.charAt(i9);
            if (cCharAt < ' ' || cCharAt > '~' || cCharAt == '\"' || cCharAt == '\'') {
                sb.append(String.format("\\u%04x", Integer.valueOf(cCharAt)));
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    public static <T extends MessageNano> String print(T t8) throws SecurityException, IllegalArgumentException {
        if (t8 == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            print(null, t8, new StringBuffer(), stringBuffer);
            return stringBuffer.toString();
        } catch (IllegalAccessException e9) {
            String strValueOf = String.valueOf(e9.getMessage());
            return strValueOf.length() != 0 ? "Error printing proto: ".concat(strValueOf) : new String("Error printing proto: ");
        } catch (InvocationTargetException e10) {
            String strValueOf2 = String.valueOf(e10.getMessage());
            return strValueOf2.length() != 0 ? "Error printing proto: ".concat(strValueOf2) : new String("Error printing proto: ");
        }
    }

    private static String sanitizeString(String str) {
        if (!str.startsWith("http") && str.length() > MAX_STRING_LEN) {
            str = String.valueOf(str.substring(0, MAX_STRING_LEN)).concat("[...]");
        }
        return escapeString(str);
    }

    private static void print(String str, Object obj, StringBuffer stringBuffer, StringBuffer stringBuffer2) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        if (obj == null) {
            return;
        }
        if (obj instanceof MessageNano) {
            int length = stringBuffer.length();
            if (str != null) {
                stringBuffer2.append(stringBuffer);
                stringBuffer2.append(deCamelCaseify(str));
                stringBuffer2.append(" <\n");
                stringBuffer.append(INDENT);
            }
            Class<?> cls = obj.getClass();
            for (Field field : cls.getFields()) {
                int modifiers = field.getModifiers();
                String name = field.getName();
                if (!"cachedSize".equals(name) && (modifiers & 1) == 1 && (modifiers & 8) != 8 && !name.startsWith("_") && !name.endsWith("_")) {
                    Class<?> type = field.getType();
                    Object obj2 = field.get(obj);
                    if (type.isArray()) {
                        if (type.getComponentType() == Byte.TYPE) {
                            print(name, obj2, stringBuffer, stringBuffer2);
                        } else {
                            int length2 = obj2 == null ? 0 : Array.getLength(obj2);
                            for (int i9 = 0; i9 < length2; i9++) {
                                print(name, Array.get(obj2, i9), stringBuffer, stringBuffer2);
                            }
                        }
                    } else {
                        print(name, obj2, stringBuffer, stringBuffer2);
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
                            print(strSubstring, cls.getMethod(strValueOf2.length() != 0 ? "get".concat(strValueOf2) : new String("get"), new Class[0]).invoke(obj, new Object[0]), stringBuffer, stringBuffer2);
                        }
                    } catch (NoSuchMethodException unused) {
                    }
                }
            }
            if (str != null) {
                stringBuffer.setLength(length);
                stringBuffer2.append(stringBuffer);
                stringBuffer2.append(">\n");
                return;
            }
            return;
        }
        String strDeCamelCaseify = deCamelCaseify(str);
        stringBuffer2.append(stringBuffer);
        stringBuffer2.append(strDeCamelCaseify);
        stringBuffer2.append(": ");
        if (obj instanceof String) {
            String strSanitizeString = sanitizeString((String) obj);
            stringBuffer2.append("\"");
            stringBuffer2.append(strSanitizeString);
            stringBuffer2.append("\"");
        } else if (obj instanceof byte[]) {
            appendQuotedBytes((byte[]) obj, stringBuffer2);
        } else {
            stringBuffer2.append(obj);
        }
        stringBuffer2.append("\n");
    }
}
