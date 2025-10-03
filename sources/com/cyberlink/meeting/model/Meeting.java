package com.cyberlink.meeting.model;

import androidx.annotation.Keep;
import com.cyberlink.you.feedback.Model;

@Keep
/* loaded from: classes.dex */
public class Meeting extends Model {
    public String callType;
    public Creator creator;
    public String eventId;
    public String groupId;
    public Boolean hasPassword;
    public InvitationOnly invitationOnly;
    public Boolean isLtiMeeting;
    public String meetingId;
    public String mserverAddr;
    public String shareAddr;
    public String title;
    public String token;
    public String type;
    public VoipInfo voipInfo;
    public WaitingRoom waitingRoom;

    @Keep
    public static class Creator extends Model {
        public String avatar;
        public String displayName;
        public String uid;
        public String userId;
    }

    @Keep
    public static class InvitationOnly extends Model {
        public Boolean isInvitationOnly;
        public Boolean needFaceVerification;
    }

    @Keep
    public static class VoipInfo extends Model {
        public String domainKey;
        public String exchangeAddr;
        public String extension;
        public String qserverAddr;
    }

    @Keep
    public static class WaitingRoom extends Model {
        public String condition;
        public String description;
        public Boolean enabled;
    }
}
