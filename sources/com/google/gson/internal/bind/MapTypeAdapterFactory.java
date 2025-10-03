package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes2.dex */
public final class MapTypeAdapterFactory implements TypeAdapterFactory {
    final boolean complexMapKeySerialization;
    private final ConstructorConstructor constructorConstructor;

    public final class Adapter<K, V> extends TypeAdapter<Map<K, V>> {
        private final ObjectConstructor<? extends Map<K, V>> constructor;
        private final TypeAdapter<K> keyTypeAdapter;
        private final TypeAdapter<V> valueTypeAdapter;

        public Adapter(Gson gson, Type type, TypeAdapter<K> typeAdapter, Type type2, TypeAdapter<V> typeAdapter2, ObjectConstructor<? extends Map<K, V>> objectConstructor) {
            this.keyTypeAdapter = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, type);
            this.valueTypeAdapter = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter2, type2);
            this.constructor = objectConstructor;
        }

        private String keyToString(JsonElement jsonElement) {
            if (!jsonElement.isJsonPrimitive()) {
                if (jsonElement.isJsonNull()) {
                    return "null";
                }
                throw new AssertionError();
            }
            JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
            if (asJsonPrimitive.isNumber()) {
                return String.valueOf(asJsonPrimitive.getAsNumber());
            }
            if (asJsonPrimitive.isBoolean()) {
                return Boolean.toString(asJsonPrimitive.getAsBoolean());
            }
            if (asJsonPrimitive.isString()) {
                return asJsonPrimitive.getAsString();
            }
            throw new AssertionError();
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Map<K, V> read2(JsonReader jsonReader) throws IOException {
            JsonToken jsonTokenPeek = jsonReader.peek();
            if (jsonTokenPeek == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            Map<K, V> mapConstruct = this.constructor.construct();
            if (jsonTokenPeek == JsonToken.BEGIN_ARRAY) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    jsonReader.beginArray();
                    K k9 = this.keyTypeAdapter.read2(jsonReader);
                    if (mapConstruct.put(k9, this.valueTypeAdapter.read2(jsonReader)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + k9);
                    }
                    jsonReader.endArray();
                }
                jsonReader.endArray();
            } else {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    JsonReaderInternalAccess.INSTANCE.promoteNameToValue(jsonReader);
                    K k10 = this.keyTypeAdapter.read2(jsonReader);
                    if (mapConstruct.put(k10, this.valueTypeAdapter.read2(jsonReader)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + k10);
                    }
                }
                jsonReader.endObject();
            }
            return mapConstruct;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Map<K, V> map) throws IOException {
            if (map == null) {
                jsonWriter.nullValue();
                return;
            }
            if (!MapTypeAdapterFactory.this.complexMapKeySerialization) {
                jsonWriter.beginObject();
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    jsonWriter.name(String.valueOf(entry.getKey()));
                    this.valueTypeAdapter.write(jsonWriter, entry.getValue());
                }
                jsonWriter.endObject();
                return;
            }
            ArrayList arrayList = new ArrayList(map.size());
            ArrayList arrayList2 = new ArrayList(map.size());
            int i9 = 0;
            boolean z8 = false;
            for (Map.Entry<K, V> entry2 : map.entrySet()) {
                JsonElement jsonTree = this.keyTypeAdapter.toJsonTree(entry2.getKey());
                arrayList.add(jsonTree);
                arrayList2.add(entry2.getValue());
                z8 |= jsonTree.isJsonArray() || jsonTree.isJsonObject();
            }
            if (!z8) {
                jsonWriter.beginObject();
                int size = arrayList.size();
                while (i9 < size) {
                    jsonWriter.name(keyToString((JsonElement) arrayList.get(i9)));
                    this.valueTypeAdapter.write(jsonWriter, arrayList2.get(i9));
                    i9++;
                }
                jsonWriter.endObject();
                return;
            }
            jsonWriter.beginArray();
            int size2 = arrayList.size();
            while (i9 < size2) {
                jsonWriter.beginArray();
                Streams.write((JsonElement) arrayList.get(i9), jsonWriter);
                this.valueTypeAdapter.write(jsonWriter, arrayList2.get(i9));
                jsonWriter.endArray();
                i9++;
            }
            jsonWriter.endArray();
        }
    }

    public MapTypeAdapterFactory(ConstructorConstructor constructorConstructor, boolean z8) {
        this.constructorConstructor = constructorConstructor;
        this.complexMapKeySerialization = z8;
    }

    private TypeAdapter<?> getKeyAdapter(Gson gson, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? TypeAdapters.BOOLEAN_AS_STRING : gson.getAdapter(TypeToken.get(type));
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) throws NoSuchMethodException, SecurityException {
        Type type = typeToken.getType();
        if (!Map.class.isAssignableFrom(typeToken.getRawType())) {
            return null;
        }
        Type[] mapKeyAndValueTypes = C$Gson$Types.getMapKeyAndValueTypes(type, C$Gson$Types.getRawType(type));
        return new Adapter(gson, mapKeyAndValueTypes[0], getKeyAdapter(gson, mapKeyAndValueTypes[0]), mapKeyAndValueTypes[1], gson.getAdapter(TypeToken.get(mapKeyAndValueTypes[1])), this.constructorConstructor.get(typeToken));
    }
}
