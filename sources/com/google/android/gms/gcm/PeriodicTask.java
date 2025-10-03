package com.google.android.gms.gcm;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.gcm.Task;

/* loaded from: classes2.dex */
public class PeriodicTask extends Task {
    public static final Parcelable.Creator<PeriodicTask> CREATOR = new zzk();
    protected long mFlexInSeconds;
    protected long mIntervalInSeconds;

    public static class Builder extends Task.Builder {
        private long zzao = -1;
        private long zzap = -1;

        public Builder() {
            this.isPersisted = true;
        }

        @Override // com.google.android.gms.gcm.Task.Builder
        public PeriodicTask build() {
            checkConditions();
            return new PeriodicTask(this, (zzk) null);
        }

        @Override // com.google.android.gms.gcm.Task.Builder
        public void checkConditions() {
            super.checkConditions();
            long j9 = this.zzao;
            if (j9 == -1) {
                throw new IllegalArgumentException("Must call setPeriod(long) to establish an execution interval for this periodic task.");
            }
            if (j9 <= 0) {
                long j10 = this.zzao;
                StringBuilder sb = new StringBuilder(66);
                sb.append("Period set cannot be less than or equal to 0: ");
                sb.append(j10);
                throw new IllegalArgumentException(sb.toString());
            }
            long j11 = this.zzap;
            if (j11 == -1) {
                this.zzap = (long) (j9 * 0.1f);
            } else if (j11 > j9) {
                this.zzap = j9;
            }
        }

        @Override // com.google.android.gms.gcm.Task.Builder
        public Builder setExtras(Bundle bundle) {
            this.extras = bundle;
            return this;
        }

        public Builder setFlex(long j9) {
            this.zzap = j9;
            return this;
        }

        public Builder setPeriod(long j9) {
            this.zzao = j9;
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

    private PeriodicTask(Builder builder) {
        super(builder);
        this.mIntervalInSeconds = -1L;
        this.mFlexInSeconds = -1L;
        this.mIntervalInSeconds = builder.zzao;
        this.mFlexInSeconds = Math.min(builder.zzap, this.mIntervalInSeconds);
    }

    public long getFlex() {
        return this.mFlexInSeconds;
    }

    public long getPeriod() {
        return this.mIntervalInSeconds;
    }

    @Override // com.google.android.gms.gcm.Task
    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putLong("period", this.mIntervalInSeconds);
        bundle.putLong("period_flex", this.mFlexInSeconds);
    }

    public String toString() {
        String string = super.toString();
        long period = getPeriod();
        long flex = getFlex();
        StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 54);
        sb.append(string);
        sb.append(" period=");
        sb.append(period);
        sb.append(" flex=");
        sb.append(flex);
        return sb.toString();
    }

    @Override // com.google.android.gms.gcm.Task, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        super.writeToParcel(parcel, i9);
        parcel.writeLong(this.mIntervalInSeconds);
        parcel.writeLong(this.mFlexInSeconds);
    }

    @Deprecated
    private PeriodicTask(Parcel parcel) {
        super(parcel);
        this.mIntervalInSeconds = -1L;
        this.mFlexInSeconds = -1L;
        this.mIntervalInSeconds = parcel.readLong();
        this.mFlexInSeconds = Math.min(parcel.readLong(), this.mIntervalInSeconds);
    }

    public /* synthetic */ PeriodicTask(Parcel parcel, zzk zzkVar) {
        this(parcel);
    }

    public /* synthetic */ PeriodicTask(Builder builder, zzk zzkVar) {
        this(builder);
    }
}
