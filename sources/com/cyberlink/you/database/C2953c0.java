package com.cyberlink.you.database;

import android.provider.BaseColumns;
import com.cyberlink.you.database.MessageObj;

/* renamed from: com.cyberlink.you.database.c0 */
/* loaded from: classes.dex */
public final class C2953c0 {

    /* renamed from: a */
    public static String f13129a = ",";

    /* renamed from: com.cyberlink.you.database.c0$a */
    public static final class a implements BaseColumns {

        /* renamed from: a */
        public static final String f13130a = "_id" + C2953c0.f13129a + "GroupId" + C2953c0.f13129a + "GroupType" + C2953c0.f13129a + "DisplayName" + C2953c0.f13129a + "ChatAlbumId" + C2953c0.f13129a + "Jid" + C2953c0.f13129a + "Avatar" + C2953c0.f13129a + "AvatarAlbumId" + C2953c0.f13129a + "LastModified" + C2953c0.f13129a + "NumberOfMember" + C2953c0.f13129a + "hiddenAlbumId" + C2953c0.f13129a + "LastRead" + C2953c0.f13129a + "isHidden" + C2953c0.f13129a + "isDisabled" + C2953c0.f13129a + "isNotificationDisabled" + C2953c0.f13129a + "LastDeleteChatTime";
    }

    /* renamed from: com.cyberlink.you.database.c0$b */
    public static final class b implements BaseColumns {

        /* renamed from: a */
        public static final String f13131a = "GroupId" + C2953c0.f13129a + "UserId";
    }

    /* renamed from: com.cyberlink.you.database.c0$c */
    public static final class c implements BaseColumns {

        /* renamed from: a */
        public static final String f13132a = "_id" + C2953c0.f13129a + "AlbumId" + C2953c0.f13129a + "MediaId" + C2953c0.f13129a + "MediaName" + C2953c0.f13129a + "Description" + C2953c0.f13129a + "MediaType" + C2953c0.f13129a + "LastModified" + C2953c0.f13129a + "CreatorId" + C2953c0.f13129a + "Thumbnail" + C2953c0.f13129a + "Original" + C2953c0.f13129a + "Width" + C2953c0.f13129a + "Height";

        /* renamed from: b */
        public static final String f13133b = "_id" + C2953c0.f13129a + "AlbumId" + C2953c0.f13129a + "MediaId" + C2953c0.f13129a + "MediaName" + C2953c0.f13129a + "Description" + C2953c0.f13129a + "MediaType" + C2953c0.f13129a + "LastModified" + C2953c0.f13129a + "CreatorId" + C2953c0.f13129a + "Thumbnail" + C2953c0.f13129a + "Original" + C2953c0.f13129a + "Width" + C2953c0.f13129a + "Height" + C2953c0.f13129a + "CommentTextCount" + C2953c0.f13129a + "CommentMediaCount";
    }

    /* renamed from: com.cyberlink.you.database.c0$d */
    public static final class d implements BaseColumns {

        /* renamed from: a */
        public static final String f13134a = "_id" + C2953c0.f13129a + "MessageId" + C2953c0.f13129a + "GroupId" + C2953c0.f13129a + "SendTime" + C2953c0.f13129a + "MessageType" + C2953c0.f13129a + "MessageContent" + C2953c0.f13129a + "ReadCount" + C2953c0.f13129a + "UserId" + C2953c0.f13129a + "UserName" + C2953c0.f13129a + "UserAvatar" + C2953c0.f13129a + "Status" + C2953c0.f13129a + "TTLStatus" + C2953c0.f13129a + "SDStarttime" + C2953c0.f13129a + "SDTotaltime" + C2953c0.f13129a + "SCSendtime" + C2953c0.f13129a + "MemberStatus";

        /* renamed from: b */
        public static final String f13135b = "_id" + C2953c0.f13129a + "MessageId" + C2953c0.f13129a + "GroupId" + C2953c0.f13129a + "SendTime" + C2953c0.f13129a + "MessageType" + C2953c0.f13129a + "MessageContent" + C2953c0.f13129a + "ReadCount" + C2953c0.f13129a + "UserId" + C2953c0.f13129a + "UserName" + C2953c0.f13129a + "UserAvatar" + C2953c0.f13129a + "Status" + C2953c0.f13129a + "TTLStatus" + C2953c0.f13129a + "SDStarttime" + C2953c0.f13129a + "SDTotaltime" + C2953c0.f13129a + "SCSendtime" + C2953c0.f13129a + "MemberStatus" + C2953c0.f13129a + "IsNewVersion" + C2953c0.f13129a + "SrcXml";

        /* renamed from: c */
        public static final String f13136c;

        /* renamed from: d */
        public static final String f13137d;

        /* renamed from: e */
        public static final String f13138e;

        /* renamed from: f */
        public static final String f13139f;

        /* renamed from: g */
        public static final String f13140g;

        /* renamed from: h */
        public static final String f13141h;

        /* renamed from: i */
        public static final String f13142i;

        /* renamed from: j */
        public static final String f13143j;

        static {
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE Message SET Status='2',UploadStatus='1' WHERE (MessageType= '");
            MessageObj.MessageType messageType = MessageObj.MessageType.Photo;
            sb.append(messageType.toString());
            sb.append("' OR ");
            sb.append("MessageType");
            sb.append("= '");
            MessageObj.MessageType messageType2 = MessageObj.MessageType.PhotoNote;
            sb.append(messageType2.toString());
            sb.append("' OR ");
            sb.append("MessageType");
            sb.append("= '");
            MessageObj.MessageType messageType3 = MessageObj.MessageType.Audio;
            sb.append(messageType3.toString());
            sb.append("') AND ");
            sb.append("Status");
            sb.append("='");
            sb.append("1");
            sb.append("'");
            f13136c = sb.toString();
            f13137d = "UPDATE Message SET Status='2',UploadStatus='4' WHERE (MessageType= '" + messageType.toString() + "' OR MessageType= '" + messageType2.toString() + "' OR MessageType= '" + messageType3.toString() + "') AND Status='4'";
            f13138e = "UPDATE Message SET Status='2',UploadStatus='3' WHERE (MessageType= '" + messageType.toString() + "' OR MessageType= '" + messageType2.toString() + "' OR MessageType= '" + messageType3.toString() + "') AND Status='7'";
            f13139f = "UPDATE Message SET Status='0',UploadStatus='2' WHERE (MessageType= '" + messageType.toString() + "' OR MessageType= '" + messageType2.toString() + "' OR MessageType= '" + messageType3.toString() + "') AND Status='8'";
            f13140g = "UPDATE Message SET Status='2',UploadStatus='5' WHERE (MessageType= '" + messageType.toString() + "' OR MessageType= '" + messageType2.toString() + "' OR MessageType= '" + messageType3.toString() + "') AND Status='9'";
            f13141h = "UPDATE Message SET UploadStatus='3' WHERE (MessageType= '" + messageType.toString() + "' OR MessageType= '" + messageType2.toString() + "' OR MessageType= '" + messageType3.toString() + "') AND Status='0' AND UploadStatus='0'";
            StringBuilder sb2 = new StringBuilder();
            sb2.append("CREATE INDEX GroupId_Status_SendTime ON Message(GroupId");
            sb2.append(C2953c0.f13129a);
            sb2.append("Status");
            sb2.append(C2953c0.f13129a);
            sb2.append("SendTime");
            sb2.append(")");
            f13142i = sb2.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append("CREATE INDEX GroupId_SendTime ON Message(GroupId");
            sb3.append(C2953c0.f13129a);
            sb3.append("SendTime");
            sb3.append(")");
            f13143j = sb3.toString();
        }
    }

    /* renamed from: com.cyberlink.you.database.c0$e */
    public static final class e implements BaseColumns {

        /* renamed from: a */
        public static final String f13144a = "_id" + C2953c0.f13129a + "CommentId" + C2953c0.f13129a + "MediaId" + C2953c0.f13129a + "CreatorId" + C2953c0.f13129a + "Comment" + C2953c0.f13129a + "LastModified" + C2953c0.f13129a + "CreatedTime" + C2953c0.f13129a + "CommentType" + C2953c0.f13129a + "MediaComment" + C2953c0.f13129a + "UploadStatus";
    }

    /* renamed from: com.cyberlink.you.database.c0$f */
    public static final class f implements BaseColumns {

        /* renamed from: a */
        public static final String f13145a = "CREATE INDEX MessageId_UserId ON RecvReceipt(MessageId" + C2953c0.f13129a + "UserId)";
    }

    /* renamed from: com.cyberlink.you.database.c0$g */
    public static final class g implements BaseColumns {

        /* renamed from: a */
        public static final String f13146a = "_id" + C2953c0.f13129a + "StickerId" + C2953c0.f13129a + "PackId" + C2953c0.f13129a + "StickerOrder" + C2953c0.f13129a + "OriginalURL" + C2953c0.f13129a + "OriginalLocalFilePath" + C2953c0.f13129a + "ThumbnailURL" + C2953c0.f13129a + "ThumbnailLocalFilePath" + C2953c0.f13129a + "LastModified" + C2953c0.f13129a + "Width" + C2953c0.f13129a + "Height" + C2953c0.f13129a + "AnimPngFilename" + C2953c0.f13129a + "Duration";
    }

    /* renamed from: com.cyberlink.you.database.c0$h */
    public static final class h implements BaseColumns {

        /* renamed from: a */
        public static final String f13147a = "_id" + C2953c0.f13129a + "PackId" + C2953c0.f13129a + "PackType" + C2953c0.f13129a + "PurchaseType" + C2953c0.f13129a + "PackName" + C2953c0.f13129a + "Description" + C2953c0.f13129a + "Expiration" + C2953c0.f13129a + "Url" + C2953c0.f13129a + "Status" + C2953c0.f13129a + "LastModified" + C2953c0.f13129a + "PublisherLastModified" + C2953c0.f13129a + "PublisherName" + C2953c0.f13129a + "PublisherTitleOfUrl" + C2953c0.f13129a + "PublisherUrl" + C2953c0.f13129a + "isShowed";
    }

    /* renamed from: com.cyberlink.you.database.c0$i */
    public static final class i implements BaseColumns {

        /* renamed from: a */
        public static final String f13148a = "_id" + C2953c0.f13129a + "UserId" + C2953c0.f13129a + "Jid" + C2953c0.f13129a + "DisplayName" + C2953c0.f13129a + "Avatar" + C2953c0.f13129a + "AvatarAlbumId" + C2953c0.f13129a + "Cover" + C2953c0.f13129a + "CoverAlbumId" + C2953c0.f13129a + "NickName" + C2953c0.f13129a + "IsHidden" + C2953c0.f13129a + "IsBlocked" + C2953c0.f13129a + "isBrokenUp" + C2953c0.f13129a + "LastModified" + C2953c0.f13129a + "IsFriend" + C2953c0.f13129a + "FriendshipCreatedTime";

        /* renamed from: b */
        public static final String f13149b = "_id" + C2953c0.f13129a + "UserId" + C2953c0.f13129a + "Jid" + C2953c0.f13129a + "DisplayName" + C2953c0.f13129a + "Avatar" + C2953c0.f13129a + "AvatarAlbumId" + C2953c0.f13129a + "Cover" + C2953c0.f13129a + "CoverAlbumId" + C2953c0.f13129a + "NickName" + C2953c0.f13129a + "IsHidden" + C2953c0.f13129a + "IsBlocked" + C2953c0.f13129a + "isBrokenUp" + C2953c0.f13129a + "LastModified" + C2953c0.f13129a + "IsFriend" + C2953c0.f13129a + "FriendshipCreatedTime" + C2953c0.f13129a + "UserType";
    }
}
