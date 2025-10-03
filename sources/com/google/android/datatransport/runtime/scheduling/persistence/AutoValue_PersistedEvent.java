package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;

/* loaded from: classes.dex */
final class AutoValue_PersistedEvent extends PersistedEvent {
    private final EventInternal event;

    /* renamed from: id */
    private final long f15262id;
    private final TransportContext transportContext;

    public AutoValue_PersistedEvent(long j9, TransportContext transportContext, EventInternal eventInternal) {
        this.f15262id = j9;
        if (transportContext == null) {
            throw new NullPointerException("Null transportContext");
        }
        this.transportContext = transportContext;
        if (eventInternal == null) {
            throw new NullPointerException("Null event");
        }
        this.event = eventInternal;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PersistedEvent)) {
            return false;
        }
        PersistedEvent persistedEvent = (PersistedEvent) obj;
        return this.f15262id == persistedEvent.getId() && this.transportContext.equals(persistedEvent.getTransportContext()) && this.event.equals(persistedEvent.getEvent());
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent
    public EventInternal getEvent() {
        return this.event;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent
    public long getId() {
        return this.f15262id;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent
    public TransportContext getTransportContext() {
        return this.transportContext;
    }

    public int hashCode() {
        long j9 = this.f15262id;
        return ((((((int) (j9 ^ (j9 >>> 32))) ^ 1000003) * 1000003) ^ this.transportContext.hashCode()) * 1000003) ^ this.event.hashCode();
    }

    public String toString() {
        return "PersistedEvent{id=" + this.f15262id + ", transportContext=" + this.transportContext + ", event=" + this.event + "}";
    }
}
