package com.google.firebase.encoders.json;

import android.util.Base64;
import android.util.JsonWriter;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
final class JsonValueObjectEncoderContext implements ObjectEncoderContext, ValueEncoderContext {
    private final ObjectEncoder<Object> fallbackEncoder;
    private final boolean ignoreNullValues;
    private final JsonWriter jsonWriter;
    private final Map<Class<?>, ObjectEncoder<?>> objectEncoders;
    private final Map<Class<?>, ValueEncoder<?>> valueEncoders;
    private JsonValueObjectEncoderContext childContext = null;
    private boolean active = true;

    public JsonValueObjectEncoderContext(Writer writer, Map<Class<?>, ObjectEncoder<?>> map, Map<Class<?>, ValueEncoder<?>> map2, ObjectEncoder<Object> objectEncoder, boolean z8) {
        this.jsonWriter = new JsonWriter(writer);
        this.objectEncoders = map;
        this.valueEncoders = map2;
        this.fallbackEncoder = objectEncoder;
        this.ignoreNullValues = z8;
    }

    private boolean cannotBeInline(Object obj) {
        return obj == null || obj.getClass().isArray() || (obj instanceof Collection) || (obj instanceof Date) || (obj instanceof Enum) || (obj instanceof Number);
    }

    private JsonValueObjectEncoderContext internalAdd(String str, Object obj) throws IOException {
        maybeUnNest();
        this.jsonWriter.name(str);
        if (obj != null) {
            return add(obj, false);
        }
        this.jsonWriter.nullValue();
        return this;
    }

    private JsonValueObjectEncoderContext internalAddIgnoreNullValues(String str, Object obj) throws IOException {
        if (obj == null) {
            return this;
        }
        maybeUnNest();
        this.jsonWriter.name(str);
        return add(obj, false);
    }

    private void maybeUnNest() throws IOException {
        if (!this.active) {
            throw new IllegalStateException("Parent context used since this context was created. Cannot use this context anymore.");
        }
        JsonValueObjectEncoderContext jsonValueObjectEncoderContext = this.childContext;
        if (jsonValueObjectEncoderContext != null) {
            jsonValueObjectEncoderContext.maybeUnNest();
            this.childContext.active = false;
            this.childContext = null;
            this.jsonWriter.endObject();
        }
    }

    public void close() throws IOException {
        maybeUnNest();
        this.jsonWriter.flush();
    }

    public JsonValueObjectEncoderContext doEncode(ObjectEncoder<Object> objectEncoder, Object obj, boolean z8) throws IOException {
        if (!z8) {
            this.jsonWriter.beginObject();
        }
        objectEncoder.encode(obj, this);
        if (!z8) {
            this.jsonWriter.endObject();
        }
        return this;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext inline(Object obj) {
        return add(obj, true);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext nested(String str) throws IOException {
        maybeUnNest();
        this.childContext = new JsonValueObjectEncoderContext(this);
        this.jsonWriter.name(str);
        this.jsonWriter.beginObject();
        return this.childContext;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext nested(FieldDescriptor fieldDescriptor) {
        return nested(fieldDescriptor.getName());
    }

    private JsonValueObjectEncoderContext(JsonValueObjectEncoderContext jsonValueObjectEncoderContext) {
        this.jsonWriter = jsonValueObjectEncoderContext.jsonWriter;
        this.objectEncoders = jsonValueObjectEncoderContext.objectEncoders;
        this.valueEncoders = jsonValueObjectEncoderContext.valueEncoders;
        this.fallbackEncoder = jsonValueObjectEncoderContext.fallbackEncoder;
        this.ignoreNullValues = jsonValueObjectEncoderContext.ignoreNullValues;
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public JsonValueObjectEncoderContext add(String str, Object obj) {
        if (this.ignoreNullValues) {
            return internalAddIgnoreNullValues(str, obj);
        }
        return internalAdd(str, obj);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public JsonValueObjectEncoderContext add(String str, double d9) throws IOException {
        maybeUnNest();
        this.jsonWriter.name(str);
        return add(d9);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public JsonValueObjectEncoderContext add(String str, int i9) throws IOException {
        maybeUnNest();
        this.jsonWriter.name(str);
        return add(i9);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public JsonValueObjectEncoderContext add(String str, long j9) throws IOException {
        maybeUnNest();
        this.jsonWriter.name(str);
        return add(j9);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public JsonValueObjectEncoderContext add(String str, boolean z8) throws IOException {
        maybeUnNest();
        this.jsonWriter.name(str);
        return add(z8);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, Object obj) {
        return add(fieldDescriptor.getName(), obj);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, float f9) {
        return add(fieldDescriptor.getName(), f9);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, double d9) {
        return add(fieldDescriptor.getName(), d9);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, int i9) {
        return add(fieldDescriptor.getName(), i9);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, long j9) {
        return add(fieldDescriptor.getName(), j9);
    }

    @Override // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor, boolean z8) {
        return add(fieldDescriptor.getName(), z8);
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public JsonValueObjectEncoderContext add(String str) throws IOException {
        maybeUnNest();
        this.jsonWriter.value(str);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public JsonValueObjectEncoderContext add(float f9) throws IOException {
        maybeUnNest();
        this.jsonWriter.value(f9);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public JsonValueObjectEncoderContext add(double d9) throws IOException {
        maybeUnNest();
        this.jsonWriter.value(d9);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public JsonValueObjectEncoderContext add(int i9) throws IOException {
        maybeUnNest();
        this.jsonWriter.value(i9);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public JsonValueObjectEncoderContext add(long j9) throws IOException {
        maybeUnNest();
        this.jsonWriter.value(j9);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public JsonValueObjectEncoderContext add(boolean z8) throws IOException {
        maybeUnNest();
        this.jsonWriter.value(z8);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public JsonValueObjectEncoderContext add(byte[] bArr) throws IOException {
        maybeUnNest();
        if (bArr == null) {
            this.jsonWriter.nullValue();
        } else {
            this.jsonWriter.value(Base64.encodeToString(bArr, 2));
        }
        return this;
    }

    public JsonValueObjectEncoderContext add(Object obj, boolean z8) throws IOException {
        int i9 = 0;
        if (z8 && cannotBeInline(obj)) {
            Object[] objArr = new Object[1];
            objArr[0] = obj == null ? null : obj.getClass();
            throw new EncodingException(String.format("%s cannot be encoded inline", objArr));
        }
        if (obj == null) {
            this.jsonWriter.nullValue();
            return this;
        }
        if (obj instanceof Number) {
            this.jsonWriter.value((Number) obj);
            return this;
        }
        if (obj.getClass().isArray()) {
            if (obj instanceof byte[]) {
                return add((byte[]) obj);
            }
            this.jsonWriter.beginArray();
            if (obj instanceof int[]) {
                int length = ((int[]) obj).length;
                while (i9 < length) {
                    this.jsonWriter.value(r6[i9]);
                    i9++;
                }
            } else if (obj instanceof long[]) {
                long[] jArr = (long[]) obj;
                int length2 = jArr.length;
                while (i9 < length2) {
                    add(jArr[i9]);
                    i9++;
                }
            } else if (obj instanceof double[]) {
                double[] dArr = (double[]) obj;
                int length3 = dArr.length;
                while (i9 < length3) {
                    this.jsonWriter.value(dArr[i9]);
                    i9++;
                }
            } else if (obj instanceof boolean[]) {
                boolean[] zArr = (boolean[]) obj;
                int length4 = zArr.length;
                while (i9 < length4) {
                    this.jsonWriter.value(zArr[i9]);
                    i9++;
                }
            } else if (obj instanceof Number[]) {
                for (Number number : (Number[]) obj) {
                    add((Object) number, false);
                }
            } else {
                for (Object obj2 : (Object[]) obj) {
                    add(obj2, false);
                }
            }
            this.jsonWriter.endArray();
            return this;
        }
        if (obj instanceof Collection) {
            this.jsonWriter.beginArray();
            Iterator it = ((Collection) obj).iterator();
            while (it.hasNext()) {
                add(it.next(), false);
            }
            this.jsonWriter.endArray();
            return this;
        }
        if (obj instanceof Map) {
            this.jsonWriter.beginObject();
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                Object key = entry.getKey();
                try {
                    add((String) key, entry.getValue());
                } catch (ClassCastException e9) {
                    throw new EncodingException(String.format("Only String keys are currently supported in maps, got %s of type %s instead.", key, key.getClass()), e9);
                }
            }
            this.jsonWriter.endObject();
            return this;
        }
        ObjectEncoder<?> objectEncoder = this.objectEncoders.get(obj.getClass());
        if (objectEncoder != null) {
            return doEncode(objectEncoder, obj, z8);
        }
        ValueEncoder<?> valueEncoder = this.valueEncoders.get(obj.getClass());
        if (valueEncoder != null) {
            valueEncoder.encode(obj, this);
            return this;
        }
        if (obj instanceof Enum) {
            add(((Enum) obj).name());
            return this;
        }
        return doEncode(this.fallbackEncoder, obj, z8);
    }
}
