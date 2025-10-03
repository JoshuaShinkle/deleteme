package com.google.firebase.encoders.proto;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.proto.Protobuf;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
final class ProtobufDataEncoderContext implements ObjectEncoderContext {
    private final ObjectEncoder<Object> fallbackEncoder;
    private final Map<Class<?>, ObjectEncoder<?>> objectEncoders;
    private OutputStream output;
    private final ProtobufValueEncoderContext valueEncoderContext = new ProtobufValueEncoderContext(this);
    private final Map<Class<?>, ValueEncoder<?>> valueEncoders;
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final FieldDescriptor MAP_KEY_DESC = FieldDescriptor.builder("key").withProperty(AtProtobuf.builder().tag(1).build()).build();
    private static final FieldDescriptor MAP_VALUE_DESC = FieldDescriptor.builder("value").withProperty(AtProtobuf.builder().tag(2).build()).build();
    private static final ObjectEncoder<Map.Entry<Object, Object>> DEFAULT_MAP_ENCODER = new ObjectEncoder() { // from class: com.google.firebase.encoders.proto.a
        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public final void encode(Object obj, ObjectEncoderContext objectEncoderContext) {
            ProtobufDataEncoderContext.lambda$static$0((Map.Entry) obj, objectEncoderContext);
        }
    };

    /* renamed from: com.google.firebase.encoders.proto.ProtobufDataEncoderContext$1 */
    public static /* synthetic */ class C41651 {

        /* renamed from: $SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding */
        static final /* synthetic */ int[] f15566x531607dd;

        static {
            int[] iArr = new int[Protobuf.IntEncoding.values().length];
            f15566x531607dd = iArr;
            try {
                iArr[Protobuf.IntEncoding.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15566x531607dd[Protobuf.IntEncoding.SIGNED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15566x531607dd[Protobuf.IntEncoding.FIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public ProtobufDataEncoderContext(OutputStream outputStream, Map<Class<?>, ObjectEncoder<?>> map, Map<Class<?>, ValueEncoder<?>> map2, ObjectEncoder<Object> objectEncoder) {
        this.output = outputStream;
        this.objectEncoders = map;
        this.valueEncoders = map2;
        this.fallbackEncoder = objectEncoder;
    }

    private static ByteBuffer allocateBuffer(int i9) {
        return ByteBuffer.allocate(i9).order(ByteOrder.LITTLE_ENDIAN);
    }

    private <T> long determineSize(ObjectEncoder<T> objectEncoder, T t8) throws IOException {
        LengthCountingOutputStream lengthCountingOutputStream = new LengthCountingOutputStream();
        try {
            OutputStream outputStream = this.output;
            this.output = lengthCountingOutputStream;
            try {
                objectEncoder.encode(t8, this);
                this.output = outputStream;
                long length = lengthCountingOutputStream.getLength();
                lengthCountingOutputStream.close();
                return length;
            } catch (Throwable th) {
                this.output = outputStream;
                throw th;
            }
        } catch (Throwable th2) {
            try {
                lengthCountingOutputStream.close();
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
            throw th2;
        }
    }

    private <T> ProtobufDataEncoderContext doEncode(ObjectEncoder<T> objectEncoder, FieldDescriptor fieldDescriptor, T t8, boolean z8) throws IOException {
        long jDetermineSize = determineSize(objectEncoder, t8);
        if (z8 && jDetermineSize == 0) {
            return this;
        }
        writeVarInt32((getTag(fieldDescriptor) << 3) | 2);
        writeVarInt64(jDetermineSize);
        objectEncoder.encode(t8, this);
        return this;
    }

    private static Protobuf getProtobuf(FieldDescriptor fieldDescriptor) {
        Protobuf protobuf = (Protobuf) fieldDescriptor.getProperty(Protobuf.class);
        if (protobuf != null) {
            return protobuf;
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    private static int getTag(FieldDescriptor fieldDescriptor) {
        Protobuf protobuf = (Protobuf) fieldDescriptor.getProperty(Protobuf.class);
        if (protobuf != null) {
            return protobuf.tag();
        }
        throw new EncodingException("Field has no @Protobuf config");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$0(Map.Entry entry, ObjectEncoderContext objectEncoderContext) {
        objectEncoderContext.add(MAP_KEY_DESC, entry.getKey());
        objectEncoderContext.add(MAP_VALUE_DESC, entry.getValue());
    }

    private void writeVarInt32(int i9) throws IOException {
        while ((i9 & (-128)) != 0) {
            this.output.write((i9 & 127) | 128);
            i9 >>>= 7;
        }
        this.output.write(i9 & 127);
    }

    private void writeVarInt64(long j9) throws IOException {
        while (((-128) & j9) != 0) {
            this.output.write((((int) j9) & 127) | 128);
            j9 >>>= 7;
        }
        this.output.write(((int) j9) & 127);
    }

    public ProtobufDataEncoderContext encode(Object obj) {
        if (obj == null) {
            return this;
        }
        ObjectEncoder<?> objectEncoder = this.objectEncoders.get(obj.getClass());
        if (objectEncoder != null) {
            objectEncoder.encode(obj, this);
            return this;
        }
        throw new EncodingException("No encoder for " + obj.getClass());
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext inline(Object obj) {
        return encode(obj);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext nested(String str) {
        return nested(FieldDescriptor.m17815of(str));
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext nested(FieldDescriptor fieldDescriptor) {
        throw new EncodingException("nested() is not implemented for protobuf encoding.");
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String str, Object obj) {
        return add(FieldDescriptor.m17815of(str), obj);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String str, double d9) {
        return add(FieldDescriptor.m17815of(str), d9);
    }

    private <T> ProtobufDataEncoderContext doEncode(ValueEncoder<T> valueEncoder, FieldDescriptor fieldDescriptor, T t8, boolean z8) {
        this.valueEncoderContext.resetContext(fieldDescriptor, z8);
        valueEncoder.encode(t8, this.valueEncoderContext);
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String str, int i9) {
        return add(FieldDescriptor.m17815of(str), i9);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String str, long j9) {
        return add(FieldDescriptor.m17815of(str), j9);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String str, boolean z8) {
        return add(FieldDescriptor.m17815of(str), z8);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, Object obj) {
        return add(fieldDescriptor, obj, true);
    }

    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, Object obj, boolean z8) {
        if (obj == null) {
            return this;
        }
        if (obj instanceof CharSequence) {
            CharSequence charSequence = (CharSequence) obj;
            if (z8 && charSequence.length() == 0) {
                return this;
            }
            writeVarInt32((getTag(fieldDescriptor) << 3) | 2);
            byte[] bytes = charSequence.toString().getBytes(UTF_8);
            writeVarInt32(bytes.length);
            this.output.write(bytes);
            return this;
        }
        if (obj instanceof Collection) {
            Iterator it = ((Collection) obj).iterator();
            while (it.hasNext()) {
                add(fieldDescriptor, it.next(), false);
            }
            return this;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                doEncode((ObjectEncoder<FieldDescriptor>) DEFAULT_MAP_ENCODER, fieldDescriptor, (FieldDescriptor) it2.next(), false);
            }
            return this;
        }
        if (obj instanceof Double) {
            return add(fieldDescriptor, ((Double) obj).doubleValue(), z8);
        }
        if (obj instanceof Float) {
            return add(fieldDescriptor, ((Float) obj).floatValue(), z8);
        }
        if (obj instanceof Number) {
            return add(fieldDescriptor, ((Number) obj).longValue(), z8);
        }
        if (obj instanceof Boolean) {
            return add(fieldDescriptor, ((Boolean) obj).booleanValue(), z8);
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            if (z8 && bArr.length == 0) {
                return this;
            }
            writeVarInt32((getTag(fieldDescriptor) << 3) | 2);
            writeVarInt32(bArr.length);
            this.output.write(bArr);
            return this;
        }
        ObjectEncoder<?> objectEncoder = this.objectEncoders.get(obj.getClass());
        if (objectEncoder != null) {
            return doEncode((ObjectEncoder<FieldDescriptor>) objectEncoder, fieldDescriptor, (FieldDescriptor) obj, z8);
        }
        ValueEncoder<?> valueEncoder = this.valueEncoders.get(obj.getClass());
        if (valueEncoder != null) {
            return doEncode((ValueEncoder<FieldDescriptor>) valueEncoder, fieldDescriptor, (FieldDescriptor) obj, z8);
        }
        if (obj instanceof ProtoEnum) {
            return add(fieldDescriptor, ((ProtoEnum) obj).getNumber());
        }
        if (obj instanceof Enum) {
            return add(fieldDescriptor, ((Enum) obj).ordinal());
        }
        return doEncode((ObjectEncoder<FieldDescriptor>) this.fallbackEncoder, fieldDescriptor, (FieldDescriptor) obj, z8);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, double d9) {
        return add(fieldDescriptor, d9, true);
    }

    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, double d9, boolean z8) {
        if (z8 && d9 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return this;
        }
        writeVarInt32((getTag(fieldDescriptor) << 3) | 1);
        this.output.write(allocateBuffer(8).putDouble(d9).array());
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, float f9) {
        return add(fieldDescriptor, f9, true);
    }

    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, float f9, boolean z8) {
        if (z8 && f9 == BitmapDescriptorFactory.HUE_RED) {
            return this;
        }
        writeVarInt32((getTag(fieldDescriptor) << 3) | 5);
        this.output.write(allocateBuffer(4).putFloat(f9).array());
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, int i9) {
        return add(fieldDescriptor, i9, true);
    }

    public ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, int i9, boolean z8) {
        if (z8 && i9 == 0) {
            return this;
        }
        Protobuf protobuf = getProtobuf(fieldDescriptor);
        int i10 = C41651.f15566x531607dd[protobuf.intEncoding().ordinal()];
        if (i10 == 1) {
            writeVarInt32(protobuf.tag() << 3);
            writeVarInt32(i9);
        } else if (i10 == 2) {
            writeVarInt32(protobuf.tag() << 3);
            writeVarInt32((i9 << 1) ^ (i9 >> 31));
        } else if (i10 == 3) {
            writeVarInt32((protobuf.tag() << 3) | 5);
            this.output.write(allocateBuffer(4).putInt(i9).array());
        }
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, long j9) {
        return add(fieldDescriptor, j9, true);
    }

    public ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, long j9, boolean z8) {
        if (z8 && j9 == 0) {
            return this;
        }
        Protobuf protobuf = getProtobuf(fieldDescriptor);
        int i9 = C41651.f15566x531607dd[protobuf.intEncoding().ordinal()];
        if (i9 == 1) {
            writeVarInt32(protobuf.tag() << 3);
            writeVarInt64(j9);
        } else if (i9 == 2) {
            writeVarInt32(protobuf.tag() << 3);
            writeVarInt64((j9 >> 63) ^ (j9 << 1));
        } else if (i9 == 3) {
            writeVarInt32((protobuf.tag() << 3) | 1);
            this.output.write(allocateBuffer(8).putLong(j9).array());
        }
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, boolean z8) {
        return add(fieldDescriptor, z8, true);
    }

    public ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor, boolean z8, boolean z9) {
        return add(fieldDescriptor, z8 ? 1 : 0, z9);
    }
}
