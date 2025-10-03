package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.encoders.annotations.Encodable;

/* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage */
/* loaded from: classes2.dex */
final class C4128xfe724d07 extends CrashlyticsReport.Session.Event.Application.Execution.BinaryImage {
    private final long baseAddress;
    private final String name;
    private final long size;
    private final String uuid;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage$Builder */
    public static final class Builder extends CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder {
        private Long baseAddress;
        private String name;
        private Long size;
        private String uuid;

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.BinaryImage build() {
            String str = "";
            if (this.baseAddress == null) {
                str = " baseAddress";
            }
            if (this.size == null) {
                str = str + " size";
            }
            if (this.name == null) {
                str = str + " name";
            }
            if (str.isEmpty()) {
                return new C4128xfe724d07(this.baseAddress.longValue(), this.size.longValue(), this.name, this.uuid);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder setBaseAddress(long j9) {
            this.baseAddress = Long.valueOf(j9);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder setName(String str) {
            if (str == null) {
                throw new NullPointerException("Null name");
            }
            this.name = str;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder setSize(long j9) {
            this.size = Long.valueOf(j9);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder setUuid(String str) {
            this.uuid = str;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session.Event.Application.Execution.BinaryImage)) {
            return false;
        }
        CrashlyticsReport.Session.Event.Application.Execution.BinaryImage binaryImage = (CrashlyticsReport.Session.Event.Application.Execution.BinaryImage) obj;
        if (this.baseAddress == binaryImage.getBaseAddress() && this.size == binaryImage.getSize() && this.name.equals(binaryImage.getName())) {
            String str = this.uuid;
            if (str == null) {
                if (binaryImage.getUuid() == null) {
                    return true;
                }
            } else if (str.equals(binaryImage.getUuid())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage
    public long getBaseAddress() {
        return this.baseAddress;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage
    public String getName() {
        return this.name;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage
    public long getSize() {
        return this.size;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage
    @Encodable.Ignore
    public String getUuid() {
        return this.uuid;
    }

    public int hashCode() {
        long j9 = this.baseAddress;
        long j10 = this.size;
        int iHashCode = (((((((int) (j9 ^ (j9 >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((j10 >>> 32) ^ j10))) * 1000003) ^ this.name.hashCode()) * 1000003;
        String str = this.uuid;
        return iHashCode ^ (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "BinaryImage{baseAddress=" + this.baseAddress + ", size=" + this.size + ", name=" + this.name + ", uuid=" + this.uuid + "}";
    }

    private C4128xfe724d07(long j9, long j10, String str, String str2) {
        this.baseAddress = j9;
        this.size = j10;
        this.name = str;
        this.uuid = str2;
    }
}
