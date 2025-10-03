package com.google.android.datatransport;

import com.google.auto.value.AutoValue;

@AutoValue
/* loaded from: classes.dex */
public abstract class Event<T> {
    public static <T> Event<T> ofData(int i9, T t8) {
        return new AutoValue_Event(Integer.valueOf(i9), t8, Priority.DEFAULT);
    }

    public static <T> Event<T> ofTelemetry(int i9, T t8) {
        return new AutoValue_Event(Integer.valueOf(i9), t8, Priority.VERY_LOW);
    }

    public static <T> Event<T> ofUrgent(int i9, T t8) {
        return new AutoValue_Event(Integer.valueOf(i9), t8, Priority.HIGHEST);
    }

    public abstract Integer getCode();

    public abstract T getPayload();

    public abstract Priority getPriority();

    public static <T> Event<T> ofData(T t8) {
        return new AutoValue_Event(null, t8, Priority.DEFAULT);
    }

    public static <T> Event<T> ofTelemetry(T t8) {
        return new AutoValue_Event(null, t8, Priority.VERY_LOW);
    }

    public static <T> Event<T> ofUrgent(T t8) {
        return new AutoValue_Event(null, t8, Priority.HIGHEST);
    }
}
