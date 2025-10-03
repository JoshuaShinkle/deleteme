package com.google.android.gms.gcm;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.gcm.Task;

/* loaded from: classes2.dex */
public class OneoffTask extends Task {
    public static final Parcelable.Creator<OneoffTask> CREATOR = new zzi();
    private final long zzal;
    private final long zzam;

    public static class Builder extends Task.Builder {
        private long zzal = -1;
        private long zzam = -1;

        public Builder() {
            this.isPersisted = false;
        }

        @Override // com.google.android.gms.gcm.Task.Builder
        public OneoffTask build() {
            checkConditions();
            return new OneoffTask(this, (zzi) null);
        }

        @Override // com.google.android.gms.gcm.Task.Builder
        public void checkConditions() {
            super.checkConditions();
            long j9 = this.zzal;
            if (j9 != -1) {
                long j10 = this.zzam;
                if (j10 != -1) {
                    if (j9 >= j10) {
                        throw new IllegalArgumentException("Window start must be shorter than window end.");
                    }
                    return;
                }
            }
            throw new IllegalArgumentException("Must specify an execution window using setExecutionWindow.");
        }

        public Builder setExecutionWindow(long j9, long j10) {
            this.zzal = j9;
            this.zzam = j10;
            return this;
        }

        @Override // com.google.android.gms.gcm.Task.Builder
        public Builder setExtras(Bundle bundle) {
            this.extras = bundle;
            return this;
        }

        @Override // com.google.android.gms.gcm.Task.Builder
        public Builder setPersisted(boolean z8) {
            this.isPersisted = z8;
            return this;
        }

        @Override // com.google.android.gms.gcm.Task.Builder
        public Builder setRequiredNetwork(int i9) {
            this.requiredNetworkState = i9;
            return this;
        }

        @Override // com.google.android.gms.gcm.Task.Builder
        public Builder setRequiresCharging(boolean z8) {
            this.requiresCharging = z8;
            return this;
        }

        @Override // com.google.android.gms.gcm.Task.Builder
        public Builder setService(Class<? extends GcmTaskService> cls) {
            this.gcmTaskService = cls.getName();
            return this;
        }

        @Override // com.google.android.gms.gcm.Task.Builder
        public Builder setTag(String str) {
            this.tag = str;
            return this;
        }

        @Override // com.google.android.gms.gcm.Task.Builder
        public Builder setUpdateCurrent(boolean z8) {
            this.updateCurrent = z8;
            return this;
        }

        @Override // com.google.android.gms.gcm.Task.Builder
        public /* bridge */ /* synthetic */ Task.Builder setService(Class cls) {
            return setService((Class<? extends GcmTaskService>) cls);
        }
    }

    private OneoffTask(Builder builder) {
        super(builder);
        this.zzal = builder.zzal;
        this.zzam = builder.zzam;
    }

    public long getWindowEnd() {
        return this.zzam;
    }

    public long getWindowStart() {
        return this.zzal;
    }

    @Override // com.google.android.gms.gcm.Task
    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putLong("window_start", this.zzal);
        bundle.putLong("window_end", this.zzam);
    }

    public String toString() {
        String string = super.toString();
        long windowStart = getWindowStart();
        long windowEnd = getWindowEnd();
        StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 64);
        sb.append(string);
        sb.append(" windowStart=");
        sb.append(windowStart);
        sb.append(" windowEnd=");
        sb.append(windowEnd);
        return sb.toString();
    }

    @Override // com.google.android.gms.gcm.Task, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        super.writeToParcel(parcel, i9);
        parcel.writeLong(this.zzal);
        parcel.writeLong(this.zzam);
    }

    @Deprecated
    private OneoffTask(Parcel parcel) {
        super(parcel);
        this.zzal = parcel.readLong();
        this.zzam = parcel.readLong();
    }

    public /* synthetic */ OneoffTask(Parcel parcel, zzi zziVar) {
        this(parcel);
    }

    public /* synthetic */ OneoffTask(Builder builder, zzi zziVar) {
        this(builder);
    }
}
