package com.perfectcorp.ycl.p040bc.model;

import android.net.Uri;
import com.perfectcorp.model.Model;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class Live extends Model {
    public Boolean chatEnabled;
    public Creator creator;
    public String description;
    public String eventId;
    public String inviteURL;
    public String liveId;
    public Boolean msgGroupEnabled;
    public Panel panel;
    public String privacy;
    public String pushAddr;

    /* renamed from: qa */
    public QNA f15981qa;
    public ArrayList<String> quality;
    public Boolean regNeeded;
    public String registerURL;
    public Integer registrantCount;
    public Boolean saveVod;
    public ScheduleInterval scheduleInterval;
    public String shareAddr;
    public Boolean signInRequiredToRegister;
    public Status status;
    public StreamAddrs streamAddrs;
    public String title;
    public String type;
    public RecordedVideo video;
    public WaterMark watermark;
    public Boolean watermarkEnabled;

    public static class Creator extends Model {
        public String avator;
        public String displayName;
        public Long uid;
        public Long userId;
    }

    public static class ListResult<T extends Model> extends LiveResponse {
        public ArrayList<T> results;
        public Integer totalSize;
    }

    public static class LiveList extends ListResult<Live> {
        public ArrayList<Live> lives;
    }

    public static class LivePermission extends Model {
        public Boolean chatBlocked;
        public Long liveId;
    }

    public static class LiveResponse extends Model {
        public Integer errorId;
        public String errorMsg;
        public Integer expireTime;
    }

    public static class Panel extends Model {
        public String eventId;
        public String meetingId;
    }

    public static class QNA extends Model {
        public Boolean enabled;
        public String eventId;
        public String meetingId;
    }

    public static class RecordedVideo extends Model {
        public String expirationDate;
        public Boolean expired;
        public Long fileSize;
        public Uri uri;
        public Integer videoLength;
    }

    public static class ScheduleInterval extends Model {
        public String endDate;
        public String startDate;
        public String timeZone;
    }

    public static class Status extends Model {
        public Integer onlineUsers;
        public String resumeTime;
        public String state;
        public Boolean unpublished;
    }

    public static class StreamAddrs extends Model {
        public Uri flv;
        public Uri hls;
        public Uri rtmp;
    }

    public static class WaterMark extends Model {
        public String position;
        public String text;
    }

    public static class avalibility extends Model {
        public Integer ticket;
        public Integer videoLength;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Live) && this.liveId.equals(((Live) obj).liveId);
    }

    @Override // com.perfectcorp.model.Model
    public Long getKey() {
        try {
            return Long.valueOf(Long.parseLong(this.liveId));
        } catch (Exception unused) {
            return 0L;
        }
    }

    public int hashCode() {
        return this.liveId.hashCode();
    }
}
