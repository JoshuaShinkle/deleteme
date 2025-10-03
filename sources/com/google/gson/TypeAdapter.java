package com.google.gson;

import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/* loaded from: classes2.dex */
public abstract class TypeAdapter<T> {
    public final T fromJson(Reader reader) {
        return read2(new JsonReader(reader));
    }

    public final T fromJsonTree(JsonElement jsonElement) {
        try {
            return read2(new JsonTreeReader(jsonElement));
        } catch (IOException e9) {
            throw new JsonIOException(e9);
        }
    }

    public final TypeAdapter<T> nullSafe() {
        return new TypeAdapter<T>() { // from class: com.google.gson.TypeAdapter.1
            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public T read2(JsonReader jsonReader) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return (T) TypeAdapter.this.read2(jsonReader);
                }
                jsonReader.nextNull();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, T t8) throws IOException {
                if (t8 == null) {
                    jsonWriter.nullValue();
                } else {
                    TypeAdapter.this.write(jsonWriter, t8);
                }
            }
        };
    }

    /* renamed from: read */
    public abstract T read2(JsonReader jsonReader);

    public final void toJson(Writer writer, T t8) {
        write(new JsonWriter(writer), t8);
    }

    public final JsonElement toJsonTree(T t8) {
        try {
            JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();
            write(jsonTreeWriter, t8);
            return jsonTreeWriter.get();
        } catch (IOException e9) {
            throw new JsonIOException(e9);
        }
    }

    public abstract void write(JsonWriter jsonWriter, T t8);

    public final T fromJson(String str) {
        return fromJson(new StringReader(str));
    }

    public final String toJson(T t8) {
        StringWriter stringWriter = new StringWriter();
        try {
            toJson(stringWriter, t8);
            return stringWriter.toString();
        } catch (IOException e9) {
            throw new AssertionError(e9);
        }
    }
}
