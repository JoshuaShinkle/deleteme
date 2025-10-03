package com.perfectcorp.ycl.p040bc.model;

import com.google.gson.annotations.SerializedName;
import com.perfectcorp.model.Model;

/* loaded from: classes2.dex */
public class Message extends Model {

    public static class JoinRoomResponse extends Model {
        public String assistantStatus;
        public String downloadArchiveUrl;
        public String downloadUrl;
        public String heartbeatUrl;
        public String hostHeartbeatUrl;
        public String likeUrl;
        public MsgOffset msgOffset;
        public String sendUrl;
        public String unlikeUrl;
        public VersionInfo versionInfo;
    }

    public static class MsgOffset extends Model {

        /* renamed from: hi */
        @SerializedName("HI")
        public Long f15983hi;

        /* renamed from: lo */
        @SerializedName("LO")
        public Long f15984lo;

        /* renamed from: me */
        @SerializedName("ME")
        public Long f15985me;
    }

    public static class Version extends Model {

        /* renamed from: android, reason: collision with root package name */
        @SerializedName("android")
        public String f22733android;
    }

    public static class VersionInfo extends Model {

        @SerializedName("recommended")
        public Version recommended;

        @SerializedName("required")
        public Version required;
    }
}
