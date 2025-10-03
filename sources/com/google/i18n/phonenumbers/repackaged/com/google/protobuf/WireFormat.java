package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import p147n5.C5371i;

/* loaded from: classes2.dex */
public final class WireFormat {
    static final int MESSAGE_SET_ITEM = 1;
    static final int MESSAGE_SET_MESSAGE = 3;
    static final int MESSAGE_SET_TYPE_ID = 2;
    static final int TAG_TYPE_BITS = 3;
    static final int TAG_TYPE_MASK = 7;
    public static final int WIRETYPE_END_GROUP = 4;
    public static final int WIRETYPE_FIXED32 = 5;
    public static final int WIRETYPE_FIXED64 = 1;
    public static final int WIRETYPE_LENGTH_DELIMITED = 2;
    public static final int WIRETYPE_START_GROUP = 3;
    public static final int WIRETYPE_VARINT = 0;
    static final int MESSAGE_SET_ITEM_TAG = makeTag(1, 3);
    static final int MESSAGE_SET_ITEM_END_TAG = makeTag(1, 4);
    static final int MESSAGE_SET_TYPE_ID_TAG = makeTag(2, 0);
    static final int MESSAGE_SET_MESSAGE_TAG = makeTag(3, 2);

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'INT64' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static class FieldType {
        private static final /* synthetic */ FieldType[] $VALUES;
        public static final FieldType BOOL;
        public static final FieldType BYTES;
        public static final FieldType DOUBLE;
        public static final FieldType ENUM;
        public static final FieldType FIXED32;
        public static final FieldType FIXED64;
        public static final FieldType FLOAT;
        public static final FieldType GROUP;
        public static final FieldType INT32;
        public static final FieldType INT64;
        public static final FieldType MESSAGE;
        public static final FieldType SFIXED32;
        public static final FieldType SFIXED64;
        public static final FieldType SINT32;
        public static final FieldType SINT64;
        public static final FieldType STRING;
        public static final FieldType UINT32;
        public static final FieldType UINT64;
        private final JavaType javaType;
        private final int wireType;

        static {
            FieldType fieldType = new FieldType("DOUBLE", 0, JavaType.DOUBLE, 1);
            DOUBLE = fieldType;
            FieldType fieldType2 = new FieldType("FLOAT", 1, JavaType.FLOAT, 5);
            FLOAT = fieldType2;
            JavaType javaType = JavaType.LONG;
            FieldType fieldType3 = new FieldType("INT64", 2, javaType, 0);
            INT64 = fieldType3;
            FieldType fieldType4 = new FieldType("UINT64", 3, javaType, 0);
            UINT64 = fieldType4;
            JavaType javaType2 = JavaType.INT;
            FieldType fieldType5 = new FieldType("INT32", 4, javaType2, 0);
            INT32 = fieldType5;
            FieldType fieldType6 = new FieldType("FIXED64", 5, javaType, 1);
            FIXED64 = fieldType6;
            FieldType fieldType7 = new FieldType("FIXED32", 6, javaType2, 5);
            FIXED32 = fieldType7;
            FieldType fieldType8 = new FieldType("BOOL", 7, JavaType.BOOLEAN, 0);
            BOOL = fieldType8;
            FieldType fieldType9 = new FieldType("STRING", 8, JavaType.STRING, 2) { // from class: com.google.i18n.phonenumbers.repackaged.com.google.protobuf.WireFormat.FieldType.1
                @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.WireFormat.FieldType
                public boolean isPackable() {
                    return false;
                }
            };
            STRING = fieldType9;
            JavaType javaType3 = JavaType.MESSAGE;
            FieldType fieldType10 = new FieldType("GROUP", 9, javaType3, 3) { // from class: com.google.i18n.phonenumbers.repackaged.com.google.protobuf.WireFormat.FieldType.2
                @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.WireFormat.FieldType
                public boolean isPackable() {
                    return false;
                }
            };
            GROUP = fieldType10;
            int i9 = 2;
            FieldType fieldType11 = new FieldType(C5371i.type, 10, javaType3, i9) { // from class: com.google.i18n.phonenumbers.repackaged.com.google.protobuf.WireFormat.FieldType.3
                @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.WireFormat.FieldType
                public boolean isPackable() {
                    return false;
                }
            };
            MESSAGE = fieldType11;
            FieldType fieldType12 = new FieldType("BYTES", 11, JavaType.BYTE_STRING, i9) { // from class: com.google.i18n.phonenumbers.repackaged.com.google.protobuf.WireFormat.FieldType.4
                @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.WireFormat.FieldType
                public boolean isPackable() {
                    return false;
                }
            };
            BYTES = fieldType12;
            FieldType fieldType13 = new FieldType("UINT32", 12, javaType2, 0);
            UINT32 = fieldType13;
            FieldType fieldType14 = new FieldType("ENUM", 13, JavaType.ENUM, 0);
            ENUM = fieldType14;
            FieldType fieldType15 = new FieldType("SFIXED32", 14, javaType2, 5);
            SFIXED32 = fieldType15;
            FieldType fieldType16 = new FieldType("SFIXED64", 15, javaType, 1);
            SFIXED64 = fieldType16;
            FieldType fieldType17 = new FieldType("SINT32", 16, javaType2, 0);
            SINT32 = fieldType17;
            FieldType fieldType18 = new FieldType("SINT64", 17, javaType, 0);
            SINT64 = fieldType18;
            $VALUES = new FieldType[]{fieldType, fieldType2, fieldType3, fieldType4, fieldType5, fieldType6, fieldType7, fieldType8, fieldType9, fieldType10, fieldType11, fieldType12, fieldType13, fieldType14, fieldType15, fieldType16, fieldType17, fieldType18};
        }

        public static FieldType valueOf(String str) {
            return (FieldType) Enum.valueOf(FieldType.class, str);
        }

        public static FieldType[] values() {
            return (FieldType[]) $VALUES.clone();
        }

        public JavaType getJavaType() {
            return this.javaType;
        }

        public int getWireType() {
            return this.wireType;
        }

        public boolean isPackable() {
            return true;
        }

        private FieldType(String str, int i9, JavaType javaType, int i10) {
            this.javaType = javaType;
            this.wireType = i10;
        }
    }

    public enum JavaType {
        INT(0),
        LONG(0L),
        FLOAT(Float.valueOf(BitmapDescriptorFactory.HUE_RED)),
        DOUBLE(Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)),
        BOOLEAN(Boolean.FALSE),
        STRING(""),
        BYTE_STRING(ByteString.EMPTY),
        ENUM(null),
        MESSAGE(null);

        private final Object defaultDefault;

        JavaType(Object obj) {
            this.defaultDefault = obj;
        }

        public Object getDefaultDefault() {
            return this.defaultDefault;
        }
    }

    private WireFormat() {
    }

    public static int getTagFieldNumber(int i9) {
        return i9 >>> 3;
    }

    public static int getTagWireType(int i9) {
        return i9 & 7;
    }

    public static int makeTag(int i9, int i10) {
        return (i9 << 3) | i10;
    }
}
