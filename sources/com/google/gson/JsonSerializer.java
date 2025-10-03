package com.google.gson;

import java.lang.reflect.Type;

/* loaded from: classes2.dex */
public interface JsonSerializer<T> {
    JsonElement serialize(T t8, Type type, JsonSerializationContext jsonSerializationContext);
}
