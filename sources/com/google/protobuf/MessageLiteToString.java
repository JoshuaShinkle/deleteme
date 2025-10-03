package com.google.protobuf;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.protobuf.GeneratedMessageLite;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* loaded from: classes2.dex */
final class MessageLiteToString {
    private static final String BUILDER_LIST_SUFFIX = "OrBuilderList";
    private static final String BYTES_SUFFIX = "Bytes";
    private static final String LIST_SUFFIX = "List";
    private static final String MAP_SUFFIX = "Map";

    private static final String camelCaseToSnakeCase(String str) {
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

    private static boolean isDefaultValue(Object obj) {
        return obj instanceof Boolean ? !((Boolean) obj).booleanValue() : obj instanceof Integer ? ((Integer) obj).intValue() == 0 : obj instanceof Float ? ((Float) obj).floatValue() == BitmapDescriptorFactory.HUE_RED : obj instanceof Double ? ((Double) obj).doubleValue() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : obj instanceof String ? obj.equals("") : obj instanceof ByteString ? obj.equals(ByteString.EMPTY) : obj instanceof MessageLite ? obj == ((MessageLite) obj).getDefaultInstanceForType() : (obj instanceof java.lang.Enum) && ((java.lang.Enum) obj).ordinal() == 0;
    }

    public static final void printField(StringBuilder sb, int i9, String str, Object obj) throws SecurityException {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                printField(sb, i9, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                printField(sb, i9, str, (Map.Entry) it2.next());
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
            sb.append(TextFormatEscaper.escapeText((String) obj));
            sb.append('\"');
            return;
        }
        if (obj instanceof ByteString) {
            sb.append(": \"");
            sb.append(TextFormatEscaper.escapeBytes((ByteString) obj));
            sb.append('\"');
            return;
        }
        if (obj instanceof GeneratedMessageLite) {
            sb.append(" {");
            reflectivePrintWithIndent((GeneratedMessageLite) obj, sb, i9 + 2);
            sb.append("\n");
            while (i10 < i9) {
                sb.append(' ');
                i10++;
            }
            sb.append("}");
            return;
        }
        if (!(obj instanceof Map.Entry)) {
            sb.append(": ");
            sb.append(obj.toString());
            return;
        }
        sb.append(" {");
        Map.Entry entry = (Map.Entry) obj;
        int i12 = i9 + 2;
        printField(sb, i12, "key", entry.getKey());
        printField(sb, i12, "value", entry.getValue());
        sb.append("\n");
        while (i10 < i9) {
            sb.append(' ');
            i10++;
        }
        sb.append("}");
    }

    private static void reflectivePrintWithIndent(MessageLite messageLite, StringBuilder sb, int i9) throws SecurityException {
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (java.lang.reflect.Method method : messageLite.getClass().getDeclaredMethods()) {
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
            if (strSubstring.endsWith(LIST_SUFFIX) && !strSubstring.endsWith(BUILDER_LIST_SUFFIX) && !strSubstring.equals(LIST_SUFFIX)) {
                String str2 = strSubstring.substring(0, 1).toLowerCase() + strSubstring.substring(1, strSubstring.length() - 4);
                java.lang.reflect.Method method2 = (java.lang.reflect.Method) map.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    printField(sb, i9, camelCaseToSnakeCase(str2), GeneratedMessageLite.invokeOrDie(method2, messageLite, new Object[0]));
                }
            }
            if (strSubstring.endsWith(MAP_SUFFIX) && !strSubstring.equals(MAP_SUFFIX)) {
                String str3 = strSubstring.substring(0, 1).toLowerCase() + strSubstring.substring(1, strSubstring.length() - 3);
                java.lang.reflect.Method method3 = (java.lang.reflect.Method) map.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    printField(sb, i9, camelCaseToSnakeCase(str3), GeneratedMessageLite.invokeOrDie(method3, messageLite, new Object[0]));
                }
            }
            if (((java.lang.reflect.Method) map2.get("set" + strSubstring)) != null) {
                if (strSubstring.endsWith(BYTES_SUFFIX)) {
                    if (map.containsKey("get" + strSubstring.substring(0, strSubstring.length() - 5))) {
                    }
                }
                String str4 = strSubstring.substring(0, 1).toLowerCase() + strSubstring.substring(1);
                java.lang.reflect.Method method4 = (java.lang.reflect.Method) map.get("get" + strSubstring);
                java.lang.reflect.Method method5 = (java.lang.reflect.Method) map.get("has" + strSubstring);
                if (method4 != null) {
                    Object objInvokeOrDie = GeneratedMessageLite.invokeOrDie(method4, messageLite, new Object[0]);
                    if (method5 != null) {
                        zBooleanValue = ((Boolean) GeneratedMessageLite.invokeOrDie(method5, messageLite, new Object[0])).booleanValue();
                    } else if (isDefaultValue(objInvokeOrDie)) {
                        zBooleanValue = false;
                    }
                    if (zBooleanValue) {
                        printField(sb, i9, camelCaseToSnakeCase(str4), objInvokeOrDie);
                    }
                }
            }
        }
        if (messageLite instanceof GeneratedMessageLite.ExtendableMessage) {
            Iterator<Map.Entry<T, Object>> it = ((GeneratedMessageLite.ExtendableMessage) messageLite).extensions.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                printField(sb, i9, "[" + ((GeneratedMessageLite.ExtensionDescriptor) entry.getKey()).getNumber() + "]", entry.getValue());
            }
        }
        UnknownFieldSetLite unknownFieldSetLite = ((GeneratedMessageLite) messageLite).unknownFields;
        if (unknownFieldSetLite != null) {
            unknownFieldSetLite.printWithIndent(sb, i9);
        }
    }

    public static String toString(MessageLite messageLite, String str) throws SecurityException {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        reflectivePrintWithIndent(messageLite, sb, 0);
        return sb.toString();
    }
}
