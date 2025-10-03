package com.cyberlink.you.database;

import android.content.Context;
import android.util.Log;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2953c0;
import com.cyberlink.you.utility.ULogUtility;
import com.google.common.net.HttpHeaders;
import java.lang.ref.WeakReference;
import net.sqlcipher.DatabaseErrorHandler;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabaseHook;
import net.sqlcipher.database.SQLiteException;
import net.sqlcipher.database.SQLiteOpenHelper;

/* renamed from: com.cyberlink.you.database.d0 */
/* loaded from: classes.dex */
public class C2956d0 extends SQLiteOpenHelper {

    /* renamed from: a */
    public static boolean f13154a = false;

    /* renamed from: b */
    public static WeakReference<b> f13155b = new WeakReference<>(null);

    /* renamed from: com.cyberlink.you.database.d0$a */
    public class a implements SQLiteDatabaseHook {
        @Override // net.sqlcipher.database.SQLiteDatabaseHook
        public void postKey(SQLiteDatabase sQLiteDatabase) {
        }

        @Override // net.sqlcipher.database.SQLiteDatabaseHook
        public void preKey(SQLiteDatabase sQLiteDatabase) {
        }
    }

    /* renamed from: com.cyberlink.you.database.d0$b */
    public interface b {
        void onComplete();

        void onStart();
    }

    public C2956d0(Context context, DatabaseErrorHandler databaseErrorHandler) {
        super(context, "pht.sqlite", null, 48, new a(), databaseErrorHandler);
        SQLiteDatabase.loadLibs(Globals.m7388i0().getApplicationContext());
    }

    /* renamed from: c */
    public static int m14992c() {
        return 48;
    }

    /* renamed from: d */
    public static synchronized boolean m14993d() {
        return f13154a;
    }

    /* renamed from: e */
    public static synchronized void m14994e() {
        b bVar = f13155b.get();
        if (bVar != null) {
            bVar.onComplete();
        }
    }

    /* renamed from: f */
    public static synchronized void m14995f() {
        b bVar = f13155b.get();
        if (bVar != null) {
            bVar.onStart();
        }
    }

    /* renamed from: g */
    public static synchronized void m14996g(b bVar) {
        f13155b = new WeakReference<>(bVar);
    }

    /* renamed from: a */
    public final void m14997a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        try {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str + "_Tmp");
            sQLiteDatabase.execSQL("ALTER TABLE " + str + " RENAME TO " + str + "_Tmp");
            sQLiteDatabase.execSQL(str2);
            sQLiteDatabase.execSQL("INSERT OR IGNORE INTO " + str + " (" + str3 + ") SELECT " + str3 + " FROM " + str + "_Tmp");
            StringBuilder sb = new StringBuilder();
            sb.append("DROP TABLE ");
            sb.append(str);
            sb.append("_Tmp");
            sQLiteDatabase.execSQL(sb.toString());
        } catch (Exception e9) {
            ULogUtility.m16685u("DatabaseOpenHelper", "ConvertTable", e9);
            Globals.m7388i0().m7430G2(true);
        }
    }

    /* renamed from: b */
    public final void m14998b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE GroupAlbum (_id INTEGER PRIMARY KEY,GroupId TEXT,AlbumId TEXT,AlbumName TEXT,LastModified INTEGER,NumberOfMedia INTEGER,CreatorId TEXT,AlbumType TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE CLGroup (_id INTEGER,GroupId INTEGER PRIMARY KEY,GroupType TEXT,DisplayName TEXT,ChatAlbumId TEXT,Jid TEXT,Avatar TEXT,AvatarAlbumId TEXT,LastModified INTEGER,NumberOfMember INTEGER,NumberOfAdmin INTEGER,hiddenAlbumId TEXT,LastRead INTEGER,isHidden INTEGER,isDisabled INTEGER,isNotificationDisabled INTEGER,LastDeleteChatTime INTEGER,DraftText TEXT,GroupSubType TEXT,LastMsg TEXT,topicAlbumId TEXT,partOfAdmins TEXT,partOfMembers TEXT,isAdmin INTEGER,circleType TEXT,isArchive INTEGER,inviteGroupLink TEXT,unreadPollsCount INTEGER DEFAULT 0,cover TEXT,coverAlbumId TEXT,isOrganizationGroup INTEGER,isEnableE2EE INTEGER DEFAULT 0,isBroadcastOnly INTEGER DEFAULT 0,showBroadcastConfig INTEGER DEFAULT 0,setAsReminder INTEGER DEFAULT 0);");
        sQLiteDatabase.execSQL("CREATE TABLE Sticker (_id INTEGER,StickerId INTEGER PRIMARY KEY,PackId INTEGER,StickerOrder INTEGER,OriginalURL TEXT,OriginalLocalFilePath TEXT,ThumbnailURL TEXT,ThumbnailLocalFilePath TEXT,LastModified INTEGER,Width INTEGER,Height INTEGER,AnimPngFilename TEXT,Duration INTEGER);");
        sQLiteDatabase.execSQL("CREATE TABLE StickerPack (_id INTEGER,PackId INTEGER PRIMARY KEY,PackType TEXT,PurchaseType TEXT,PackName TEXT,Description TEXT,Expiration TEXT,Url TEXT,Status TEXT,LastModified INTEGER,PublisherLastModified INTEGER,PublisherName TEXT,PublisherTitleOfUrl TEXT,PublisherUrl TEXT,isShowed INTEGER,iapItem TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE User (_id INTEGER,UserId INTEGER PRIMARY KEY,Jid TEXT,DisplayName TEXT,Avatar TEXT,AvatarAlbumId TEXT,Cover TEXT,CoverAlbumId TEXT,NickName TEXT,IsHidden INTEGER,IsBlocked INTEGER,isBrokenUp INTEGER,LastModified INTEGER,IsFriend INTEGER,FriendshipCreatedTime INTEGER,UserType TEXT,PublicId TEXT,StatusMessage TEXT,Country TEXT,isDeleted INTEGER DEFAULT 0,orgName TEXT,department TEXT,orgTitle TEXT,email TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE SendReceipt (_id INTEGER PRIMARY KEY,MessageId TEXT,ReceiptId TEXT,Status TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE RecvReceipt (MessageId TEXT,UserId TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE UploadMedia (_id INTEGER PRIMARY KEY,UploadId TEXT,UploadType INTEGER,UploadContext TEXT,SendingTime INTEGER);");
        sQLiteDatabase.execSQL("CREATE TABLE MediaNote (_id INTEGER PRIMARY KEY,MediaId INTEGER,Metadata TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE Media (_id INTEGER,AlbumId TEXT,MediaId INTEGER PRIMARY KEY,MediaName TEXT,Description TEXT,MediaType TEXT,LastModified INTEGER,expiredDate INTEGER DEFAULT 0,CreatorId TEXT,Thumbnail TEXT,Original TEXT,Width INTEGER,Height INTEGER,CommentTextCount INTEGER DEFAULT 0,CommentMediaCount INTEGER DEFAULT 0,CommentDoodleCount INTEGER DEFAULT 0,TotalCommentCount INTEGER DEFAULT 0,DescriptionLastModified INTEGER DEFAULT 0);");
        sQLiteDatabase.execSQL("CREATE TABLE Message (_id INTEGER,MessageId TEXT PRIMARY KEY,GroupId TEXT,SendTime INTEGER,MessageType TEXT,MessageContent TEXT,ReadCount INTEGER,UserId TEXT,UserName TEXT,UserAvatar TEXT,Status TEXT,TTLStatus TEXT,SDStarttime INTEGER,SDTotaltime INTEGER,SCSendtime INTEGER,MemberStatus TEXT,IsNewVersion INTEGER,SrcXml TEXT,UploadStatus TEXT,SearchMessage TEXT,OriginalSenderId TEXT,OriginalMessageId TEXT,LastForwarderId TEXT,isFromBroadcaster INTEGER DEFAULT 0,totalSizeOfInvitee INTEGER DEFAULT 0);");
        sQLiteDatabase.execSQL("CREATE TABLE PhotoComment (_id INTEGER,CommentId INTEGER PRIMARY KEY,MediaId INTEGER,CreatorId INTEGER,Comment TEXT,LastModified INTEGER,CreatedTime INTEGER,CommentType TEXT,MediaComment TEXT,UploadStatus INTEGER);");
        sQLiteDatabase.execSQL("CREATE TABLE GroupMember (GroupId INTEGER,UserId INTEGER,PRIMARY KEY (GroupId, UserId));");
        sQLiteDatabase.execSQL("CREATE TABLE NotificationRecord (MessageId TEXT PRIMARY KEY,NotificationTime INTEGER);");
        sQLiteDatabase.execSQL("CREATE TABLE BullentinTopicComment (id INTEGER PRIMARY KEY,TopicId INTEGER,CreatorId INTEGER,Description TEXT,LikeCount INTEGER,isLiked INTEGER,Components TEXT,CreatedTime INTEGER,LastModified INTEGER,isUnread INTEGER);");
        sQLiteDatabase.execSQL("CREATE TABLE GroupAdmin (_id INTEGER PRIMARY KEY,GroupId INTEGER,UserId INTEGER);");
        sQLiteDatabase.execSQL("CREATE TABLE BullentinTopic (TopicId INTEGER PRIMARY KEY,GroupId INTEGER,Description TEXT,Title TEXT,LikeCount INTEGER,PostCount INTEGER,isLiked INTEGER,LastModified INTEGER,lastPostTime INTEGER,lastStickyTime INTEGER,isSticky INTEGER,Components TEXT,CreatedTime INTEGER,isClosed INTEGER,isCreateByAdmin INTEGER,CreatorId INTEGER, isNotificationDisabled INTEGER, Unread INTEGER, topicVersion TEXT DEFAULT '1.0',topicType TEXT DEFAULT 'Topic',isPollMultipleChoose INTEGER, pollExpirationTime INTEGER, numberOfVoters INTEGER, isVoted INTEGER DEFAULT 0,lastReadTime INTEGER DEFAULT 0,isPollSecretBallot INTEGER, isPollShowAfterAccomplish INTEGER,isProhibitModifyVotes INTEGER,isPollLimitVoteCount INTEGER,pollLimitVoteType TEXT,pollLimitVoteCount INTEGER,pollVersion INTEGER);");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS PollOption (optionId INTEGER PRIMARY KEY,topicId INTEGER,optionOrder INTEGER,optionDescription TEXT,numberOfPolls INTEGER DEFAULT 0,isVoted INTEGER DEFAULT 0,lastModified TEXT,optionComment TEXT,lastVotedTime TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS OrganizationMember (_id INTEGER PRIMARY KEY,OrganizationId INTEGER,UserId INTEGER);");
        sQLiteDatabase.execSQL(C2953c0.d.f13142i);
        sQLiteDatabase.execSQL(C2953c0.f.f13145a);
        sQLiteDatabase.execSQL("CREATE INDEX MessageId_ ON RecvReceipt(MessageId)");
        sQLiteDatabase.execSQL(C2953c0.d.f13143j);
        C2946a.m14864b(sQLiteDatabase);
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS RecvReceipt");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS UrlPreview (Url TEXT PRIMARY KEY,Title TEXT,Description TEXT,ImageUrl TEXT,SendingTime INTEGER);");
        sQLiteDatabase.execSQL("CREATE INDEX Url_ ON UrlPreview(Url)");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS PhoneCall (_id INTEGER PRIMARY KEY,Number TEXT,DisplayName TEXT,IsReceive INTEGER,IsExtension INTEGER,IsMissing INTEGER,Date TEXT,isFromContact INTEGER,UserId TEXT);");
    }

    /* renamed from: h */
    public final void m14999h(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        m15000i(sQLiteDatabase, str, str2, true);
    }

    /* renamed from: i */
    public final void m15000i(SQLiteDatabase sQLiteDatabase, String str, String str2, boolean z8) {
        try {
            sQLiteDatabase.execSQL(str);
        } catch (SQLiteException e9) {
            ULogUtility.m16685u("DatabaseOpenHelper", str2, e9);
            e9.printStackTrace();
            if (z8) {
                Globals.m7388i0().m7430G2(true);
            }
        } catch (Exception e10) {
            ULogUtility.m16685u("DatabaseOpenHelper", str2, e10);
            e10.printStackTrace();
            if (z8) {
                Globals.m7388i0().m7430G2(true);
            }
        }
    }

    @Override // net.sqlcipher.database.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        try {
            try {
                ULogUtility.m16686v("Create", sQLiteDatabase.getPath());
                Log.i("DatabaseOpenHelper", "creating schema");
                m14998b(sQLiteDatabase);
                sQLiteDatabase.setTransactionSuccessful();
                ULogUtility.m16686v("Create", "Success " + sQLiteDatabase.getPath());
            } catch (Exception e9) {
                ULogUtility.m16685u("DatabaseOpenHelper", "onCreate ", e9);
            }
        } finally {
            Log.i("DatabaseOpenHelper", "end transaction");
            sQLiteDatabase.endTransaction();
        }
    }

    @Override // net.sqlcipher.database.SQLiteOpenHelper
    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        Log.i("DatabaseOpenHelper", "Open database");
        super.onOpen(sQLiteDatabase);
        sQLiteDatabase.rawExecSQL("PRAGMA journal_mode=WAL;");
    }

    @Override // net.sqlcipher.database.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i9, int i10) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        f13154a = true;
        m14995f();
        for (int i11 = i9 + 1; i11 <= i10; i11++) {
            Log.w("DatabaseOpenHelper", "Upgrading database from version " + i9 + " to " + i11);
            StringBuilder sb = new StringBuilder();
            sb.append(i9);
            sb.append("(");
            sb.append(Globals.m7388i0().m7438I0());
            sb.append(") to ");
            sb.append(i11);
            sb.append("(");
            Globals.m7388i0();
            sb.append(Globals.m7368J());
            sb.append(")");
            ULogUtility.m16686v(HttpHeaders.UPGRADE, sb.toString());
            switch (i11) {
                case 2:
                    try {
                        sQLiteDatabase.execSQL("CREATE TABLE SendReceipt (_id INTEGER PRIMARY KEY,MessageId TEXT,ReceiptId TEXT,Status TEXT);");
                        sQLiteDatabase.execSQL("CREATE TABLE RecvReceipt (MessageId TEXT,UserId TEXT);");
                        break;
                    } catch (Exception e9) {
                        ULogUtility.m16685u("DatabaseOpenHelper", "onUpgrade ", e9);
                        break;
                    }
                case 3:
                    try {
                        sQLiteDatabase.execSQL("CREATE TABLE UploadMedia (_id INTEGER PRIMARY KEY,UploadId TEXT,UploadType INTEGER,UploadContext TEXT,SendingTime INTEGER);");
                        sQLiteDatabase.execSQL("CREATE TABLE MediaNote (_id INTEGER PRIMARY KEY,MediaId INTEGER,Metadata TEXT);");
                        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS PhotoComment");
                        sQLiteDatabase.execSQL("CREATE TABLE PhotoComment (_id INTEGER PRIMARY KEY,CommentId INTEGER,MediaId INTEGER,CreatorId INTEGER,Comment TEXT,LastModified INTEGER,CommentType TEXT,MediaComment TEXT,UploadStatus INTEGER);");
                        sQLiteDatabase.execSQL("ALTER TABLE CLGroup ADD COLUMN LastDeleteChatTime INTEGER");
                        sQLiteDatabase.execSQL("ALTER TABLE User ADD COLUMN FriendshipCreatedTime INTEGER DEFAULT -1");
                        break;
                    } catch (Exception e10) {
                        ULogUtility.m16685u("DatabaseOpenHelper", "onUpgrade ", e10);
                        break;
                    }
                case 4:
                    try {
                        m14997a(sQLiteDatabase, "CLGroup", "CREATE TABLE CLGroup (_id INTEGER,GroupId INTEGER PRIMARY KEY,GroupType TEXT,DisplayName TEXT,ChatAlbumId TEXT,Jid TEXT,Avatar TEXT,AvatarAlbumId TEXT,LastModified INTEGER,NumberOfMember INTEGER,hiddenAlbumId TEXT,LastRead INTEGER,isHidden INTEGER,isDisabled INTEGER,isNotificationDisabled INTEGER,LastDeleteChatTime INTEGER);", C2953c0.a.f13130a);
                        m14997a(sQLiteDatabase, "Message", "CREATE TABLE Message (_id INTEGER,MessageId TEXT PRIMARY KEY,GroupId TEXT,SendTime INTEGER,MessageType TEXT,MessageContent TEXT,ReadCount INTEGER,UserId TEXT,UserName TEXT,UserAvatar TEXT,Status TEXT,TTLStatus TEXT,SDStarttime INTEGER,SDTotaltime INTEGER,SCSendtime INTEGER,MemberStatus TEXT,IsNewVersion INTEGER,SrcXml TEXT);", C2953c0.d.f13134a);
                        m14997a(sQLiteDatabase, "User", "CREATE TABLE User (_id INTEGER,UserId INTEGER PRIMARY KEY,Jid TEXT,DisplayName TEXT,Avatar TEXT,AvatarAlbumId TEXT,Cover TEXT,CoverAlbumId TEXT,NickName TEXT,IsHidden INTEGER,IsBlocked INTEGER,isBrokenUp INTEGER,LastModified INTEGER,IsFriend INTEGER,FriendshipCreatedTime INTEGER);", C2953c0.i.f13148a);
                        m14997a(sQLiteDatabase, "Sticker", "CREATE TABLE Sticker (_id INTEGER,StickerId INTEGER PRIMARY KEY,PackId INTEGER,StickerOrder INTEGER,OriginalURL TEXT,OriginalLocalFilePath TEXT,ThumbnailURL TEXT,ThumbnailLocalFilePath TEXT,LastModified INTEGER,Width INTEGER,Height INTEGER,AnimPngFilename TEXT,Duration INTEGER);", C2953c0.g.f13146a);
                        m14997a(sQLiteDatabase, "StickerPack", "CREATE TABLE StickerPack (_id INTEGER,PackId INTEGER PRIMARY KEY,PackType TEXT,PurchaseType TEXT,PackName TEXT,Description TEXT,Expiration TEXT,Url TEXT,Status TEXT,LastModified INTEGER,PublisherLastModified INTEGER,PublisherName TEXT,PublisherTitleOfUrl TEXT,PublisherUrl TEXT,isShowed INTEGER);", C2953c0.h.f13147a);
                        break;
                    } catch (Exception e11) {
                        ULogUtility.m16685u("DatabaseOpenHelper", "onUpgrade ", e11);
                        break;
                    }
                case 5:
                    try {
                        sQLiteDatabase.execSQL("ALTER TABLE Message ADD COLUMN UploadStatus TEXT DEFAULT '0'");
                        sQLiteDatabase.execSQL(C2953c0.d.f13136c);
                        sQLiteDatabase.execSQL(C2953c0.d.f13137d);
                        sQLiteDatabase.execSQL(C2953c0.d.f13138e);
                        sQLiteDatabase.execSQL(C2953c0.d.f13139f);
                        sQLiteDatabase.execSQL(C2953c0.d.f13140g);
                        break;
                    } catch (Exception e12) {
                        ULogUtility.m16685u("DatabaseOpenHelper", "onUpgrade ", e12);
                        break;
                    }
                case 6:
                    try {
                        sQLiteDatabase.execSQL(C2953c0.d.f13141h);
                        break;
                    } catch (Exception e13) {
                        ULogUtility.m16685u("DatabaseOpenHelper", "onUpgrade ", e13);
                        break;
                    }
                case 7:
                    try {
                        m14997a(sQLiteDatabase, "Media", "CREATE TABLE Media (_id INTEGER,AlbumId TEXT,MediaId INTEGER PRIMARY KEY,MediaName TEXT,Description TEXT,MediaType TEXT,LastModified INTEGER,CreatorId TEXT,Thumbnail TEXT,Original TEXT,Width INTEGER,Height INTEGER,CommentTextCount INTEGER DEFAULT 0,CommentMediaCount INTEGER DEFAULT 0);", C2953c0.c.f13132a);
                        sQLiteDatabase.execSQL("UPDATE GroupAlbum SET LastModified = '0'");
                        m14997a(sQLiteDatabase, "PhotoComment", "CREATE TABLE PhotoComment (_id INTEGER,CommentId INTEGER PRIMARY KEY,MediaId INTEGER,CreatorId INTEGER,Comment TEXT,LastModified INTEGER,CommentType TEXT,MediaComment TEXT,UploadStatus INTEGER);", C2953c0.e.f13144a);
                        break;
                    } catch (Exception e14) {
                        ULogUtility.m16685u("DatabaseOpenHelper", "onUpgrade ", e14);
                        break;
                    }
                case 8:
                    try {
                        sQLiteDatabase.execSQL("CREATE TABLE GroupMember (_id INTEGER PRIMARY KEY,GroupId INTEGER,UserId INTEGER);");
                        break;
                    } catch (Exception e15) {
                        ULogUtility.m16685u("DatabaseOpenHelper", "onUpgrade ", e15);
                        break;
                    }
                case 9:
                    try {
                        sQLiteDatabase.execSQL("ALTER TABLE CLGroup ADD COLUMN DraftText TEXT");
                        sQLiteDatabase.execSQL("ALTER TABLE CLGroup ADD COLUMN GroupSubType TEXT DEFAULT 'General'");
                        sQLiteDatabase.execSQL("CREATE TABLE NotificationRecord (MessageId TEXT PRIMARY KEY,NotificationTime INTEGER);");
                        sQLiteDatabase.execSQL("ALTER TABLE User ADD COLUMN UserType TEXT DEFAULT 'General'");
                        break;
                    } catch (Exception e16) {
                        ULogUtility.m16685u("DatabaseOpenHelper", "onUpgrade ", e16);
                        break;
                    }
                case 10:
                    try {
                        sQLiteDatabase.execSQL("ALTER TABLE StickerPack ADD COLUMN iapItem TEXT DEFAULT 'Null'");
                        break;
                    } catch (Exception e17) {
                        ULogUtility.m16685u("DatabaseOpenHelper", "onUpgrade ", e17);
                        break;
                    }
                case 11:
                    try {
                        sQLiteDatabase.execSQL("ALTER TABLE CLGroup ADD COLUMN LastMsg TEXT");
                        break;
                    } catch (Exception e18) {
                        ULogUtility.m16685u("DatabaseOpenHelper", "onUpgrade ", e18);
                        break;
                    }
                case 12:
                    try {
                        m14999h(sQLiteDatabase, "ALTER TABLE CLGroup ADD COLUMN topicAlbumId TEXT", "onUpgrade ");
                        m14999h(sQLiteDatabase, "ALTER TABLE CLGroup ADD COLUMN NumberOfAdmin INTEGER", "onUpgrade ");
                        m14999h(sQLiteDatabase, "ALTER TABLE CLGroup ADD COLUMN partOfAdmins TEXT", "onUpgrade ");
                        m14999h(sQLiteDatabase, "ALTER TABLE CLGroup ADD COLUMN partOfMembers TEXT", "onUpgrade ");
                        m14999h(sQLiteDatabase, "ALTER TABLE CLGroup ADD COLUMN isAdmin INTEGER", "onUpgrade ");
                        m14999h(sQLiteDatabase, "ALTER TABLE CLGroup ADD COLUMN circleType TEXT", "onUpgrade ");
                        m14999h(sQLiteDatabase, "CREATE TABLE BullentinTopicComment (id INTEGER PRIMARY KEY,TopicId INTEGER,CreatorId INTEGER,Description TEXT,LikeCount INTEGER,isLiked INTEGER,Components TEXT,CreatedTime INTEGER,LastModified INTEGER,isUnread INTEGER);", "onUpgrade ");
                        m14999h(sQLiteDatabase, "CREATE TABLE BullentinTopic (TopicId INTEGER PRIMARY KEY,GroupId INTEGER,Description TEXT,Title TEXT,LikeCount INTEGER,PostCount INTEGER,isLiked INTEGER,LastModified INTEGER,lastPostTime INTEGER,lastStickyTime INTEGER,isSticky INTEGER,Components TEXT,CreatedTime INTEGER,isClosed INTEGER,isCreateByAdmin INTEGER,CreatorId INTEGER, isNotificationDisabled INTEGER, Unread INTEGER);", "onUpgrade ");
                        m14999h(sQLiteDatabase, "CREATE TABLE GroupAdmin (_id INTEGER PRIMARY KEY,GroupId INTEGER,UserId INTEGER);", "onUpgrade ");
                        m14997a(sQLiteDatabase, "Media", "CREATE TABLE Media (_id INTEGER,AlbumId TEXT,MediaId INTEGER PRIMARY KEY,MediaName TEXT,Description TEXT,MediaType TEXT,LastModified INTEGER,CreatorId TEXT,Thumbnail TEXT,Original TEXT,Width INTEGER,Height INTEGER,CommentTextCount INTEGER DEFAULT 0,CommentMediaCount INTEGER DEFAULT 0,CommentDoodleCount INTEGER DEFAULT 0,TotalCommentCount INTEGER DEFAULT 0);", C2953c0.c.f13133b);
                        break;
                    } catch (Exception e19) {
                        ULogUtility.m16685u("DatabaseOpenHelper", "onUpgrade ", e19);
                        break;
                    }
                case 13:
                    m14997a(sQLiteDatabase, "User", "CREATE TABLE User (_id INTEGER,UserId INTEGER PRIMARY KEY,Jid TEXT,DisplayName TEXT,Avatar TEXT,AvatarAlbumId TEXT,Cover TEXT,CoverAlbumId TEXT,NickName TEXT,IsHidden INTEGER,IsBlocked INTEGER,isBrokenUp INTEGER,LastModified INTEGER,IsFriend INTEGER,FriendshipCreatedTime INTEGER,UserType TEXT,PublicId TEXT,StatusMessage TEXT,Country TEXT);", C2953c0.i.f13149b);
                    m14999h(sQLiteDatabase, "ALTER TABLE CLGroup ADD COLUMN isArchive INTEGER DEFAULT 0", "onUpgrade ");
                    m14999h(sQLiteDatabase, "ALTER TABLE CLGroup ADD COLUMN inviteGroupLink TEXT", "onUpgrade ");
                    m14999h(sQLiteDatabase, "ALTER TABLE BullentinTopic ADD COLUMN topicVersion TEXT DEFAULT '1.0'", "onUpgrade ");
                    m14999h(sQLiteDatabase, "ALTER TABLE BullentinTopic ADD COLUMN topicType TEXT DEFAULT 'Topic'", "onUpgrade ");
                    m14999h(sQLiteDatabase, "ALTER TABLE BullentinTopic ADD COLUMN isPollMultipleChoose INTEGER", "onUpgrade ");
                    m14999h(sQLiteDatabase, "ALTER TABLE BullentinTopic ADD COLUMN pollExpirationTime INTEGER", "onUpgrade ");
                    m14999h(sQLiteDatabase, "ALTER TABLE BullentinTopic ADD COLUMN numberOfVoters INTEGER", "onUpgrade ");
                    m14999h(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS PollOption (optionId INTEGER PRIMARY KEY,topicId INTEGER,optionOrder INTEGER,optionDescription TEXT,numberOfPolls INTEGER DEFAULT 0,isVoted INTEGER DEFAULT 0,lastModified TEXT);", "onUpgrade ");
                    break;
                case 14:
                    m14999h(sQLiteDatabase, "ALTER TABLE CLGroup ADD COLUMN unreadPollsCount INTEGER DEFAULT 0", "onUpgrade ");
                    m14999h(sQLiteDatabase, "ALTER TABLE BullentinTopic ADD COLUMN isVoted INTEGER DEFAULT 0", "onUpgrade ");
                    break;
                case 15:
                    m14999h(sQLiteDatabase, "ALTER TABLE CLGroup ADD COLUMN cover TEXT", "onUpgrade ");
                    m14999h(sQLiteDatabase, "ALTER TABLE CLGroup ADD COLUMN coverAlbumId TEXT", "onUpgrade ");
                    break;
                case 16:
                    m15000i(sQLiteDatabase, "ALTER TABLE PollOption ADD COLUMN optionComment TEXT", "onUpgrade ", false);
                    m14999h(sQLiteDatabase, "ALTER TABLE CLGroup ADD COLUMN isOrganizationGroup INTEGER", "onUpgrade ");
                    m14999h(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS OrganizationMember (_id INTEGER PRIMARY KEY,OrganizationId INTEGER,UserId INTEGER);", "onUpgrade ");
                    break;
                case 17:
                    m15000i(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS PollOption (optionId INTEGER PRIMARY KEY,topicId INTEGER,optionOrder INTEGER,optionDescription TEXT,numberOfPolls INTEGER DEFAULT 0,isVoted INTEGER DEFAULT 0,lastModified TEXT);", "onUpgrade ", false);
                    m15000i(sQLiteDatabase, "ALTER TABLE PollOption ADD COLUMN optionComment TEXT", "onUpgrade ", false);
                    m15000i(sQLiteDatabase, "ALTER TABLE CLGroup ADD COLUMN isOrganizationGroup INTEGER", "onUpgrade ", false);
                    m15000i(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS OrganizationMember (_id INTEGER PRIMARY KEY,OrganizationId INTEGER,UserId INTEGER);", "onUpgrade ", false);
                    break;
                case 18:
                    m15000i(sQLiteDatabase, C2953c0.d.f13142i, "onUpgrade ", false);
                    break;
                case 19:
                    m15000i(sQLiteDatabase, C2953c0.f.f13145a, "onUpgrade ", false);
                    m15000i(sQLiteDatabase, "CREATE INDEX MessageId_ ON RecvReceipt(MessageId)", "onUpgrade ", false);
                    break;
                case 20:
                    m15000i(sQLiteDatabase, C2953c0.d.f13143j, "onUpgrade ", false);
                    break;
                case 21:
                    m15000i(sQLiteDatabase, "DELETE FROM GroupMember WHERE _id NOT IN (SELECT MIN(_id) FROM GroupMember GROUP BY GroupId,UserId)", "onUpgrade ", false);
                    m14997a(sQLiteDatabase, "GroupMember", "CREATE TABLE GroupMember (GroupId INTEGER,UserId INTEGER,PRIMARY KEY (GroupId, UserId));", C2953c0.b.f13131a);
                    break;
                case 22:
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS Image");
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ImageCache");
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ImageDevelopHistoryStep");
                    break;
                case 23:
                    sQLiteDatabase.execSQL("UPDATE BullentinTopic SET topicType = 'Poll' WHERE topicType = 'DinBenDon'");
                    break;
                case 24:
                    m15000i(sQLiteDatabase, "ALTER TABLE Message ADD COLUMN SearchMessage TEXT", "onUpgrade ", false);
                    break;
                case 25:
                    m15000i(sQLiteDatabase, "ALTER TABLE BullentinTopic ADD COLUMN lastReadTime INTEGER DEFAULT 0", "onUpgrade ", false);
                    break;
                case 26:
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS Event");
                    break;
                case 27:
                    C2946a.m14865c(sQLiteDatabase);
                    break;
                case 28:
                    C2946a.m14867i(sQLiteDatabase);
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS RecvReceipt");
                    break;
                case 29:
                    sQLiteDatabase.execSQL("UPDATE Message SET UserAvatar = NULL WHERE UserAvatar IS NOT NULL");
                    break;
                case 30:
                    m15000i(sQLiteDatabase, "ALTER TABLE Media ADD COLUMN DescriptionLastModified INTEGER", "onUpgrade ", false);
                    break;
                case 31:
                    m15000i(sQLiteDatabase, "ALTER TABLE User ADD COLUMN isDeleted INTEGER DEFAULT 0", "onUpgrade ", false);
                case 32:
                    m15000i(sQLiteDatabase, "ALTER TABLE CLGroup ADD COLUMN isEnableE2EE INTEGER DEFAULT 0", "onUpgrade ", false);
                    break;
                case 33:
                    m15000i(sQLiteDatabase, "ALTER TABLE Media ADD COLUMN expiredDate INTEGER DEFAULT 0", "onUpgrade ", false);
                    break;
                case 34:
                    C2946a.m14868j(sQLiteDatabase);
                    break;
                case 35:
                    m15000i(sQLiteDatabase, "ALTER TABLE Message ADD COLUMN OriginalSenderId TEXT", "onUpgrade ", false);
                    m15000i(sQLiteDatabase, "ALTER TABLE Message ADD COLUMN OriginalMessageId TEXT", "onUpgrade ", false);
                    m15000i(sQLiteDatabase, "ALTER TABLE Message ADD COLUMN LastForwarderId TEXT", "onUpgrade ", false);
                    break;
                case 36:
                    m15000i(sQLiteDatabase, "ALTER TABLE User ADD COLUMN orgName TEXT", "onUpgrade ", false);
                    m15000i(sQLiteDatabase, "ALTER TABLE User ADD COLUMN department TEXT", "onUpgrade ", false);
                    m15000i(sQLiteDatabase, "ALTER TABLE User ADD COLUMN orgTitle TEXT", "onUpgrade ", false);
                    break;
                case 37:
                    m15000i(sQLiteDatabase, "ALTER TABLE CLGroup ADD COLUMN isBroadcastOnly INTEGER DEFAULT 0", "onUpgrade ", false);
                    m15000i(sQLiteDatabase, "ALTER TABLE CLGroup ADD COLUMN showBroadcastConfig INTEGER DEFAULT 0", "onUpgrade ", false);
                    break;
                case 38:
                    m15000i(sQLiteDatabase, "ALTER TABLE Message ADD COLUMN isFromBroadcaster INTEGER DEFAULT 0", "onUpgrade ", false);
                    break;
                case 39:
                    m15000i(sQLiteDatabase, "ALTER TABLE User ADD COLUMN email TEXT", "onUpgrade ", false);
                    break;
                case 40:
                    m15000i(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS UrlPreview (Url TEXT PRIMARY KEY,Title TEXT,Description TEXT,ImageUrl TEXT,SendingTime INTEGER);", "onUpgrade ", false);
                    m15000i(sQLiteDatabase, "CREATE INDEX Url_ ON UrlPreview(Url)", "onUpgrade ", false);
                    break;
                case 41:
                    m15000i(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS PhoneCall (_id INTEGER PRIMARY KEY,Number TEXT,DisplayName TEXT,IsReceive INTEGER,IsExtension INTEGER,IsMissing INTEGER,Date TEXT);", "onUpgrade ", false);
                case 42:
                    m15000i(sQLiteDatabase, "ALTER TABLE PhoneCall ADD COLUMN isFromContact INTEGER DEFAULT 0", "onUpgrade ", false);
                    m15000i(sQLiteDatabase, "ALTER TABLE PhoneCall ADD COLUMN UserId TEXT ", "onUpgrade ", false);
                    break;
                case 43:
                    m15000i(sQLiteDatabase, "ALTER TABLE CLGroup ADD COLUMN setAsReminder INTEGER DEFAULT 0", "onUpgrade ", false);
                    break;
                case 44:
                    m15000i(sQLiteDatabase, "ALTER TABLE PhotoComment ADD COLUMN CreatedTime INTEGER DEFAULT 0", "onUpgrade ", false);
                    break;
                case 45:
                    m15000i(sQLiteDatabase, "ALTER TABLE BullentinTopic ADD COLUMN isPollSecretBallot INTEGER", "onUpgrade ", false);
                    m15000i(sQLiteDatabase, "ALTER TABLE BullentinTopic ADD COLUMN isPollShowAfterAccomplish INTEGER", "onUpgrade ", false);
                    break;
                case 46:
                    m15000i(sQLiteDatabase, "ALTER TABLE PollOption ADD COLUMN lastVotedTime TEXT", "onUpgrade ", false);
                    break;
                case 47:
                    m15000i(sQLiteDatabase, "ALTER TABLE BullentinTopic ADD COLUMN isProhibitModifyVotes INTEGER", "onUpgrade ", false);
                    m15000i(sQLiteDatabase, "ALTER TABLE BullentinTopic ADD COLUMN isPollLimitVoteCount INTEGER", "onUpgrade ", false);
                    m15000i(sQLiteDatabase, "ALTER TABLE BullentinTopic ADD COLUMN pollLimitVoteType TEXT", "onUpgrade ", false);
                    m15000i(sQLiteDatabase, "ALTER TABLE BullentinTopic ADD COLUMN pollLimitVoteCount INTEGER", "onUpgrade ", false);
                    m15000i(sQLiteDatabase, "ALTER TABLE BullentinTopic ADD COLUMN pollVersion INTEGER", "onUpgrade ", false);
                    break;
                case 48:
                    m15000i(sQLiteDatabase, "ALTER TABLE Message ADD COLUMN totalSizeOfInvitee INTEGER DEFAULT 0", "onUpgrade ", false);
                    break;
            }
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        f13154a = false;
        m14994e();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Upgrading done ");
        long j9 = jCurrentTimeMillis2 - jCurrentTimeMillis;
        sb2.append(String.valueOf(j9));
        sb2.append(" ms");
        Log.e("DatabaseOpenHelper", sb2.toString());
        ULogUtility.m16686v(HttpHeaders.UPGRADE, "done " + String.valueOf(j9) + " ms");
    }
}
