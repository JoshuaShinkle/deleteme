package com.google.gson.internal.bind;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes2.dex */
public final class JsonTreeReader extends JsonReader {
    private int[] pathIndices;
    private String[] pathNames;
    private Object[] stack;
    private int stackSize;
    private static final Reader UNREADABLE_READER = new Reader() { // from class: com.google.gson.internal.bind.JsonTreeReader.1
        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            throw new AssertionError();
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i9, int i10) {
            throw new AssertionError();
        }
    };
    private static final Object SENTINEL_CLOSED = new Object();

    public JsonTreeReader(JsonElement jsonElement) {
        super(UNREADABLE_READER);
        this.stack = new Object[32];
        this.stackSize = 0;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        push(jsonElement);
    }

    private void expect(JsonToken jsonToken) {
        if (peek() == jsonToken) {
            return;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + peek() + locationString());
    }

    private String getPath(boolean z8) {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i9 = 0;
        while (true) {
            int i10 = this.stackSize;
            if (i9 >= i10) {
                return sb.toString();
            }
            Object[] objArr = this.stack;
            Object obj = objArr[i9];
            if (obj instanceof JsonArray) {
                i9++;
                if (i9 < i10 && (objArr[i9] instanceof Iterator)) {
                    int i11 = this.pathIndices[i9];
                    if (z8 && i11 > 0 && (i9 == i10 - 1 || i9 == i10 - 2)) {
                        i11--;
                    }
                    sb.append('[');
                    sb.append(i11);
                    sb.append(']');
                }
            } else if ((obj instanceof JsonObject) && (i9 = i9 + 1) < i10 && (objArr[i9] instanceof Iterator)) {
                sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                String str = this.pathNames[i9];
                if (str != null) {
                    sb.append(str);
                }
            }
            i9++;
        }
    }

    private String locationString() {
        return " at path " + getPath();
    }

    private Object peekStack() {
        return this.stack[this.stackSize - 1];
    }

    private Object popStack() {
        Object[] objArr = this.stack;
        int i9 = this.stackSize - 1;
        this.stackSize = i9;
        Object obj = objArr[i9];
        objArr[i9] = null;
        return obj;
    }

    private void push(Object obj) {
        int i9 = this.stackSize;
        Object[] objArr = this.stack;
        if (i9 == objArr.length) {
            int i10 = i9 * 2;
            this.stack = Arrays.copyOf(objArr, i10);
            this.pathIndices = Arrays.copyOf(this.pathIndices, i10);
            this.pathNames = (String[]) Arrays.copyOf(this.pathNames, i10);
        }
        Object[] objArr2 = this.stack;
        int i11 = this.stackSize;
        this.stackSize = i11 + 1;
        objArr2[i11] = obj;
    }

    @Override // com.google.gson.stream.JsonReader
    public void beginArray() {
        expect(JsonToken.BEGIN_ARRAY);
        push(((JsonArray) peekStack()).iterator());
        this.pathIndices[this.stackSize - 1] = 0;
    }

    @Override // com.google.gson.stream.JsonReader
    public void beginObject() {
        expect(JsonToken.BEGIN_OBJECT);
        push(((JsonObject) peekStack()).entrySet().iterator());
    }

    @Override // com.google.gson.stream.JsonReader, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.stack = new Object[]{SENTINEL_CLOSED};
        this.stackSize = 1;
    }

    @Override // com.google.gson.stream.JsonReader
    public void endArray() {
        expect(JsonToken.END_ARRAY);
        popStack();
        popStack();
        int i9 = this.stackSize;
        if (i9 > 0) {
            int[] iArr = this.pathIndices;
            int i10 = i9 - 1;
            iArr[i10] = iArr[i10] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public void endObject() {
        expect(JsonToken.END_OBJECT);
        popStack();
        popStack();
        int i9 = this.stackSize;
        if (i9 > 0) {
            int[] iArr = this.pathIndices;
            int i10 = i9 - 1;
            iArr[i10] = iArr[i10] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public String getPreviousPath() {
        return getPath(true);
    }

    @Override // com.google.gson.stream.JsonReader
    public boolean hasNext() {
        JsonToken jsonTokenPeek = peek();
        return (jsonTokenPeek == JsonToken.END_OBJECT || jsonTokenPeek == JsonToken.END_ARRAY || jsonTokenPeek == JsonToken.END_DOCUMENT) ? false : true;
    }

    @Override // com.google.gson.stream.JsonReader
    public boolean nextBoolean() {
        expect(JsonToken.BOOLEAN);
        boolean asBoolean = ((JsonPrimitive) popStack()).getAsBoolean();
        int i9 = this.stackSize;
        if (i9 > 0) {
            int[] iArr = this.pathIndices;
            int i10 = i9 - 1;
            iArr[i10] = iArr[i10] + 1;
        }
        return asBoolean;
    }

    @Override // com.google.gson.stream.JsonReader
    public double nextDouble() {
        JsonToken jsonTokenPeek = peek();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenPeek != jsonToken && jsonTokenPeek != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenPeek + locationString());
        }
        double asDouble = ((JsonPrimitive) peekStack()).getAsDouble();
        if (!isLenient() && (Double.isNaN(asDouble) || Double.isInfinite(asDouble))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + asDouble);
        }
        popStack();
        int i9 = this.stackSize;
        if (i9 > 0) {
            int[] iArr = this.pathIndices;
            int i10 = i9 - 1;
            iArr[i10] = iArr[i10] + 1;
        }
        return asDouble;
    }

    @Override // com.google.gson.stream.JsonReader
    public int nextInt() {
        JsonToken jsonTokenPeek = peek();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenPeek != jsonToken && jsonTokenPeek != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenPeek + locationString());
        }
        int asInt = ((JsonPrimitive) peekStack()).getAsInt();
        popStack();
        int i9 = this.stackSize;
        if (i9 > 0) {
            int[] iArr = this.pathIndices;
            int i10 = i9 - 1;
            iArr[i10] = iArr[i10] + 1;
        }
        return asInt;
    }

    public JsonElement nextJsonElement() {
        JsonToken jsonTokenPeek = peek();
        if (jsonTokenPeek != JsonToken.NAME && jsonTokenPeek != JsonToken.END_ARRAY && jsonTokenPeek != JsonToken.END_OBJECT && jsonTokenPeek != JsonToken.END_DOCUMENT) {
            JsonElement jsonElement = (JsonElement) peekStack();
            skipValue();
            return jsonElement;
        }
        throw new IllegalStateException("Unexpected " + jsonTokenPeek + " when reading a JsonElement.");
    }

    @Override // com.google.gson.stream.JsonReader
    public long nextLong() {
        JsonToken jsonTokenPeek = peek();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenPeek != jsonToken && jsonTokenPeek != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenPeek + locationString());
        }
        long asLong = ((JsonPrimitive) peekStack()).getAsLong();
        popStack();
        int i9 = this.stackSize;
        if (i9 > 0) {
            int[] iArr = this.pathIndices;
            int i10 = i9 - 1;
            iArr[i10] = iArr[i10] + 1;
        }
        return asLong;
    }

    @Override // com.google.gson.stream.JsonReader
    public String nextName() {
        expect(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) peekStack()).next();
        String str = (String) entry.getKey();
        this.pathNames[this.stackSize - 1] = str;
        push(entry.getValue());
        return str;
    }

    @Override // com.google.gson.stream.JsonReader
    public void nextNull() {
        expect(JsonToken.NULL);
        popStack();
        int i9 = this.stackSize;
        if (i9 > 0) {
            int[] iArr = this.pathIndices;
            int i10 = i9 - 1;
            iArr[i10] = iArr[i10] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public String nextString() {
        JsonToken jsonTokenPeek = peek();
        JsonToken jsonToken = JsonToken.STRING;
        if (jsonTokenPeek == jsonToken || jsonTokenPeek == JsonToken.NUMBER) {
            String asString = ((JsonPrimitive) popStack()).getAsString();
            int i9 = this.stackSize;
            if (i9 > 0) {
                int[] iArr = this.pathIndices;
                int i10 = i9 - 1;
                iArr[i10] = iArr[i10] + 1;
            }
            return asString;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenPeek + locationString());
    }

    @Override // com.google.gson.stream.JsonReader
    public JsonToken peek() {
        if (this.stackSize == 0) {
            return JsonToken.END_DOCUMENT;
        }
        Object objPeekStack = peekStack();
        if (objPeekStack instanceof Iterator) {
            boolean z8 = this.stack[this.stackSize - 2] instanceof JsonObject;
            Iterator it = (Iterator) objPeekStack;
            if (!it.hasNext()) {
                return z8 ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            }
            if (z8) {
                return JsonToken.NAME;
            }
            push(it.next());
            return peek();
        }
        if (objPeekStack instanceof JsonObject) {
            return JsonToken.BEGIN_OBJECT;
        }
        if (objPeekStack instanceof JsonArray) {
            return JsonToken.BEGIN_ARRAY;
        }
        if (!(objPeekStack instanceof JsonPrimitive)) {
            if (objPeekStack instanceof JsonNull) {
                return JsonToken.NULL;
            }
            if (objPeekStack == SENTINEL_CLOSED) {
                throw new IllegalStateException("JsonReader is closed");
            }
            throw new AssertionError();
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) objPeekStack;
        if (jsonPrimitive.isString()) {
            return JsonToken.STRING;
        }
        if (jsonPrimitive.isBoolean()) {
            return JsonToken.BOOLEAN;
        }
        if (jsonPrimitive.isNumber()) {
            return JsonToken.NUMBER;
        }
        throw new AssertionError();
    }

    public void promoteNameToValue() {
        expect(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) peekStack()).next();
        push(entry.getValue());
        push(new JsonPrimitive((String) entry.getKey()));
    }

    @Override // com.google.gson.stream.JsonReader
    public void skipValue() {
        if (peek() == JsonToken.NAME) {
            nextName();
            this.pathNames[this.stackSize - 2] = "null";
        } else {
            popStack();
            int i9 = this.stackSize;
            if (i9 > 0) {
                this.pathNames[i9 - 1] = "null";
            }
        }
        int i10 = this.stackSize;
        if (i10 > 0) {
            int[] iArr = this.pathIndices;
            int i11 = i10 - 1;
            iArr[i11] = iArr[i11] + 1;
        }
    }

    @Override // com.google.gson.stream.JsonReader
    public String toString() {
        return JsonTreeReader.class.getSimpleName() + locationString();
    }

    @Override // com.google.gson.stream.JsonReader
    public String getPath() {
        return getPath(false);
    }
}
