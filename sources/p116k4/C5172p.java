package p116k4;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.SettingActivity;
import com.cyberlink.you.bulletin.TopicCommentObj;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.GroupAlbumObj;
import com.cyberlink.you.database.PollOptionObj;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.database.TopicObj;
import com.cyberlink.you.friends.C3061a;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.friends.InvitationFriend;
import com.cyberlink.you.friends.SuggestionFriend;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.plus.PlusShare;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: k4.p */
/* loaded from: classes.dex */
public class C5172p {
    /* renamed from: A */
    public static List<TopicObj> m20174A(String str, long j9) {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayM20196r = m20196r(str);
        if (jSONArrayM20196r != null) {
            for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
                try {
                    TopicObj topicObjM20204z = m20204z(jSONArrayM20196r.getJSONObject(i9), j9);
                    if (topicObjM20204z != null) {
                        arrayList.add(topicObjM20204z);
                    }
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    /* renamed from: B */
    public static String m20175B(JSONObject jSONObject) {
        try {
            return jSONObject.getString("message");
        } catch (JSONException unused) {
            Log.d("FriendsClientUtils", "[parseUpgradeInfo] JObj=" + jSONObject.toString());
            return null;
        }
    }

    /* renamed from: C */
    public static String m20176C(JSONObject jSONObject) {
        try {
            return jSONObject.getString("status");
        } catch (JSONException unused) {
            Log.d("FriendsClientUtils", "[parseUserStatus] JObj=" + jSONObject.toString());
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00ce  */
    /* renamed from: D */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static List<StickerPackObj> m20177D(JSONArray jSONArray, boolean z8, boolean z9) {
        JSONArray jSONArray2 = jSONArray;
        if (jSONArray2 == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i9 = 0;
        while (i9 < jSONArray.length()) {
            try {
                JSONObject jSONObject = jSONArray2.getJSONObject(i9);
                try {
                    long j9 = jSONObject.getLong("packId");
                    String string = jSONObject.getString("packType");
                    String string2 = jSONObject.getString("purchaseType");
                    String string3 = jSONObject.getString("packName");
                    String string4 = jSONObject.getString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION);
                    String string5 = jSONObject.getString("expiration");
                    long j10 = jSONObject.getLong("lastModified");
                    StickerPackObj.Status status = StickerPackObj.Status.NONE;
                    String string6 = jSONObject.has("publishedDate") ? jSONObject.getString("iapItem") : "";
                    StickerPackObj.C2943b c2943b = new StickerPackObj.C2943b();
                    JSONObject jSONObject2 = jSONObject.getJSONObject("url");
                    c2943b.f13073a = jSONObject2.getString("zip");
                    c2943b.f13074b = jSONObject2.getString("cover");
                    c2943b.f13076d = jSONObject2.getString("thumbnail");
                    c2943b.f13078f = jSONObject2.getString("preview");
                    if (z8 || z9) {
                        String strM16541h1 = CLUtility.m16541h1(j9);
                        StringBuilder sb = new StringBuilder();
                        sb.append(strM16541h1);
                        String str = File.separator;
                        sb.append(str);
                        sb.append("cover");
                        c2943b.f13075c = sb.toString();
                        c2943b.f13077e = strM16541h1 + str + "thumbnail";
                        c2943b.f13079g = strM16541h1 + str + "preview";
                        StickerPackObj.Status status2 = z8 ? StickerPackObj.Status.BUILTIN : status;
                        JSONObject jSONObject3 = jSONObject.getJSONObject("publisher");
                        long j11 = jSONObject3.getLong("lastModified");
                        String string7 = jSONObject3.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                        String string8 = jSONObject3.getString("titleOfUrl");
                        String string9 = jSONObject3.getString("url");
                        if (jSONObject.has("publishedDate") && !jSONObject.isNull("publishedDate")) {
                            j11 = jSONObject.getLong("publishedDate");
                        }
                        arrayList.add(new StickerPackObj(-1L, j9, string, string2, string3, string4, string5, c2943b, status2, j10, j11, string7, string8, string9, z9, string6));
                    }
                } catch (JSONException unused) {
                    Log.e("FriendsClientUtils", "[sticker.pack.info] Parse item error. JSONstr=" + jSONObject.toString());
                }
            } catch (JSONException unused2) {
                Log.e("FriendsClientUtils", "[sticker.pack.info] groupinfo parse error. JSONstr=" + jSONArray.toString());
            }
            i9++;
            jSONArray2 = jSONArray;
        }
        return arrayList;
    }

    /* renamed from: E */
    public static List<C2973l0> m20178E(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayM20196r = m20196r(str2);
        if (jSONArrayM20196r != null) {
            for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
                try {
                    C2973l0 c2973l0M20188j = m20188j(str, jSONArrayM20196r.getJSONObject(i9));
                    if (c2973l0M20188j != null) {
                        arrayList.add(c2973l0M20188j);
                    }
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            }
        }
        Log.d("FriendsClientUtils", "[parseVideoList] end videoList = " + arrayList);
        return arrayList;
    }

    /* renamed from: a */
    public static int m20179a(String str, String str2) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject(str2).getJSONObject("albums");
            if (!jSONObject.has(str)) {
                return 0;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return jSONObject2.optInt("maxMediaLimit", CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT) - jSONObject2.optInt("currentMediaCount", 0);
        } catch (Exception e9) {
            ULogUtility.m16676l("FriendsClientUtils", "getSelectMediaCount exception" + e9);
            return 0;
        }
    }

    /* renamed from: b */
    public static boolean m20180b(String str, String str2) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject(str2).getJSONObject("groups");
            if (!jSONObject.has(str)) {
                return false;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return jSONObject2.optInt("currentAlbumCount", 0) >= jSONObject2.optInt("maxAlbumLimit", CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
        } catch (Exception e9) {
            ULogUtility.m16676l("FriendsClientUtils", "isReachAlbumLimit exception" + e9);
            return false;
        }
    }

    /* renamed from: c */
    public static Pair<C2973l0, List<String>> m20181c(String str, JSONArray jSONArray) throws JSONException {
        JSONObject jSONObject;
        if (jSONArray == null || jSONArray.length() < 1) {
            jSONObject = null;
        } else {
            try {
                jSONObject = jSONArray.getJSONObject(0);
            } catch (JSONException unused) {
                Log.d("FriendsClientUtils", "[listMediaColumn] The JSONObject is null");
            }
        }
        if (jSONObject == null) {
            return Pair.create(null, null);
        }
        ArrayList arrayList = new ArrayList();
        C2973l0 c2973l0M20188j = m20188j(str, jSONObject);
        if (jSONObject.has("mediaName")) {
            arrayList.add("MediaName");
        }
        if (jSONObject.has("lastModified")) {
            arrayList.add("LastModified");
        }
        if (jSONObject.has("expiredDate")) {
            arrayList.add("expiredDate");
        }
        if (jSONObject.has(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION)) {
            arrayList.add("Description");
        }
        if (jSONObject.has("mediaType")) {
            arrayList.add("MediaType");
        }
        if (jSONObject.has("creatorId")) {
            arrayList.add("CreatorId");
        }
        if (jSONObject.has("commentTextCount")) {
            arrayList.add("CommentTextCount");
        }
        if (jSONObject.has("commentMediaCount")) {
            arrayList.add("CommentMediaCount");
        }
        if (jSONObject.has("commentDoodleCount")) {
            arrayList.add("CommentDoodleCount");
        }
        if (jSONObject.has("totalCommentCount")) {
            arrayList.add("TotalCommentCount");
        }
        if (jSONObject.has("descriptionLastModified")) {
            arrayList.add("DescriptionLastModified");
        }
        if (jSONObject.has("thumbnail")) {
            arrayList.add("Thumbnail");
        }
        if (jSONObject.has("original")) {
            arrayList.add("Original");
        }
        return Pair.create(c2973l0M20188j, arrayList);
    }

    /* renamed from: d */
    public static TopicCommentObj m20182d(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            Log.e("FriendsClientUtils", "[parseBullentinTopicCommentObj] JObj is null.");
            return null;
        }
        try {
            if (jSONObject.has("isDeleted") && jSONObject.getBoolean("isDeleted")) {
                return null;
            }
            return new TopicCommentObj(jSONObject.getLong(TtmlNode.ATTR_ID), jSONObject.getLong("topicId"), jSONObject.getLong("creatorId"), jSONObject.isNull(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION) ? "" : jSONObject.getString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION), jSONObject.getInt("likeCount"), jSONObject.getBoolean("isLiked"), Pair.create(TopicCommentObj.FromSource.Server, jSONObject.getString("components")), jSONObject.getLong("createdTime") * 1000, jSONObject.getLong("lastModified") * 1000, true);
        } catch (JSONException unused) {
            Log.d("FriendsClientUtils", "[parseBullentinTopicComment] JObj=" + jSONObject.toString());
            return null;
        }
    }

    /* renamed from: e */
    public static C3061a m20183e(JSONObject jSONObject, long j9) throws JSONException {
        String string;
        if (jSONObject == null) {
            return null;
        }
        try {
            long j10 = jSONObject.getLong("commentId");
            long j11 = jSONObject.getLong("creatorId");
            String string2 = jSONObject.getString("comment");
            long j12 = jSONObject.getLong("lastModified") * 1000;
            long j13 = jSONObject.getLong("createdTime") * 1000;
            try {
                string = jSONObject.getString("commentType");
            } catch (Exception e9) {
                e9.printStackTrace();
                string = "CommentText";
            }
            C3061a.a aVar = new C3061a.a();
            try {
                if (!jSONObject.isNull("mediaComment")) {
                    aVar = m20191m(string, jSONObject.getJSONObject("mediaComment"));
                }
            } catch (Exception e10) {
                e10.printStackTrace();
            }
            return new C3061a(-1L, j10, j9, j11, string2, j12, j13, string, aVar, false, 2);
        } catch (JSONException unused) {
            Log.e("FriendsClientUtils", "[parseCommentObj] JSONstr=" + jSONObject.toString());
            return null;
        }
    }

    /* renamed from: f */
    public static Friend m20184f(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            Friend friendM15003C = C2950b0.m14899A().m15003C(String.valueOf(jSONObject.getLong("userId")));
            if (friendM15003C == null) {
                friendM15003C = new Friend();
            }
            friendM15003C.f13645c = jSONObject.getLong("userId");
            friendM15003C.m15624e(jSONObject.getString("displayName"));
            friendM15003C.f13647e = jSONObject.getString("avatar");
            friendM15003C.f13648f = jSONObject.getString("jid");
            String string = "";
            if (jSONObject.has("cover")) {
                friendM15003C.f13650h = jSONObject.isNull("cover") ? "" : jSONObject.getString("cover");
            }
            if (jSONObject.has("coverAlbumId")) {
                friendM15003C.f13651i = jSONObject.getString("coverAlbumId");
            }
            if (jSONObject.has("nickname")) {
                friendM15003C.m15625f(jSONObject.isNull("nickname") ? "" : jSONObject.getString("nickname"));
            }
            if (jSONObject.has("isHidden")) {
                friendM15003C.f13654l = jSONObject.getBoolean("isHidden");
            }
            if (jSONObject.has("isBlocked")) {
                friendM15003C.f13655m = jSONObject.getBoolean("isBlocked");
            }
            if (jSONObject.has("isBrokenUp")) {
                friendM15003C.f13656n = jSONObject.getBoolean("isBrokenUp");
            }
            if (jSONObject.has("isFriend")) {
                friendM15003C.f13658p = jSONObject.getBoolean("isFriend");
            }
            if (jSONObject.has("lastModified")) {
                friendM15003C.f13657o = jSONObject.getLong("lastModified");
            }
            if (jSONObject.has("createdTimeOfFriendship")) {
                friendM15003C.f13659q = jSONObject.optLong("createdTimeOfFriendship", new Date().getTime() / 1000);
            }
            if (jSONObject.has("userType")) {
                friendM15003C.f13660r = jSONObject.getString("userType");
            }
            if (jSONObject.has("publicId")) {
                friendM15003C.f13661s = jSONObject.isNull("publicId") ? "" : jSONObject.getString("publicId");
            }
            if (jSONObject.has("statusMessage")) {
                friendM15003C.f13662t = jSONObject.isNull("statusMessage") ? "" : jSONObject.getString("statusMessage");
            }
            if (jSONObject.has("regionCode")) {
                friendM15003C.f13663u = jSONObject.isNull("regionCode") ? "" : jSONObject.getString("regionCode");
            }
            if (jSONObject.has("isDeleted")) {
                friendM15003C.f13664v = jSONObject.getBoolean("isDeleted");
            }
            if (jSONObject.has("orgName")) {
                friendM15003C.f13665w = jSONObject.isNull("orgName") ? "" : jSONObject.getString("orgName");
            }
            if (jSONObject.has("orgTitle")) {
                friendM15003C.f13667y = jSONObject.isNull("orgTitle") ? "" : jSONObject.getString("orgTitle");
            }
            if (jSONObject.has("department")) {
                if (!jSONObject.isNull("department")) {
                    string = jSONObject.getString("department");
                }
                friendM15003C.f13666x = string;
            }
            if (jSONObject.has("attrs")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("attrs");
                if (jSONObject2.has("user.email")) {
                    friendM15003C.f13668z = jSONObject2.getString("user.email");
                }
            }
            return friendM15003C;
        } catch (JSONException unused) {
            Log.e("FriendsClientUtils", "[paresUserInfoFromServerResponse] 'userId', 'displaName', 'avatar' missing. JSONstr=" + jSONObject.toString());
            return null;
        }
    }

    /* renamed from: g */
    public static GroupAlbumObj m20185g(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return null;
        }
        try {
            Long l9 = -1L;
            return new GroupAlbumObj(l9.longValue(), str, jSONObject.getString("albumId"), jSONObject.getString("albumName"), jSONObject.getLong("lastModified") * 1000, jSONObject.getInt("numberOfMedia"), jSONObject.getString("creatorId"), jSONObject.getString("albumType"));
        } catch (JSONException unused) {
            Log.e("FriendsClientUtils", "[parseGroupAlbumObj] JSONstr=" + jSONObject.toString());
            return null;
        }
    }

    /* renamed from: h */
    public static Group m20186h(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Group group = new Group();
        try {
            group.f13716c = jSONObject.getString("groupType");
            String string = "";
            group.f13717d = jSONObject.isNull("displayName") ? "" : jSONObject.getString("displayName");
            group.f13726m = Long.valueOf(jSONObject.getLong("lastModified"));
            group.f13727n = jSONObject.getLong("groupId");
            group.f13728o = jSONObject.getLong("numberOfMember");
            group.f13729p = jSONObject.getLong("numberOfAdmin");
            group.f13718e = jSONObject.getString("chatAlbumId");
            group.f13719f = jSONObject.getString("avatarAlbumId");
            group.f13723j = jSONObject.getString("jid");
            group.f13724k = jSONObject.getString("avatar");
            group.f13720g = jSONObject.getString("hiddenAlbumId");
            if (jSONObject.has("isHidden")) {
                group.f13731r = jSONObject.getBoolean("isHidden");
            }
            if (jSONObject.has("isDisabled")) {
                group.f13732s = jSONObject.getBoolean("isDisabled");
            }
            if (jSONObject.has("isNotificationDisabled")) {
                group.f13733t = jSONObject.getBoolean("isNotificationDisabled");
            }
            if (jSONObject.has("groupSubType")) {
                group.f13738y = jSONObject.getString("groupSubType");
            }
            if (jSONObject.has("topicAlbumId")) {
                group.f13721h = jSONObject.getString("topicAlbumId");
            }
            if (jSONObject.has("partOfAdmins")) {
                JSONArray jSONArray = jSONObject.getJSONArray("partOfAdmins");
                if (group.f13703B == null) {
                    group.f13703B = new ArrayList(jSONArray.length());
                }
                for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                    group.f13703B.add(Long.valueOf(jSONArray.getLong(i9)));
                }
            }
            if (jSONObject.has("partOfMembers")) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("partOfMembers");
                if (group.f13702A == null) {
                    group.f13702A = new ArrayList(jSONArray2.length());
                }
                for (int i10 = 0; i10 < jSONArray2.length(); i10++) {
                    group.f13702A.add(Long.valueOf(jSONArray2.getLong(i10)));
                }
            }
            if (jSONObject.has("isAdmin")) {
                group.f13704C = jSONObject.getBoolean("isAdmin");
            }
            if (jSONObject.has("circleType")) {
                group.f13705D = jSONObject.getString("circleType");
            }
            if (jSONObject.has("cover")) {
                group.f13725l = jSONObject.isNull("cover") ? "" : jSONObject.getString("cover");
            }
            if (jSONObject.has("coverAlbumId")) {
                if (!jSONObject.isNull("coverAlbumId")) {
                    string = jSONObject.getString("coverAlbumId");
                }
                group.f13722i = string;
            }
            if (jSONObject.has("isOrganizationGroup")) {
                group.f13709H = jSONObject.getBoolean("isOrganizationGroup");
            }
            if (jSONObject.has("enableE2ee")) {
                group.f13711J = jSONObject.getBoolean("enableE2ee");
            }
            if (jSONObject.has("isBroadcastingOnlyMode")) {
                group.f13712K = jSONObject.getBoolean("isBroadcastingOnlyMode");
            }
            if (jSONObject.has("showBroadcastingConfig")) {
                group.f13713L = jSONObject.getBoolean("showBroadcastingConfig");
            }
            return group;
        } catch (JSONException unused) {
            Log.e("FriendsClientUtils", "[paresUserInfoFromServerResponse] JSONstr=" + jSONObject.toString());
            return null;
        }
    }

    /* renamed from: i */
    public static InvitationFriend m20187i(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        InvitationFriend invitationFriend = new InvitationFriend();
        try {
            invitationFriend.f13741b = jSONObject.getString("displayName");
            invitationFriend.f13742c = jSONObject.getString("inviteStatus");
            invitationFriend.f13743d = jSONObject.getString("avatar");
            invitationFriend.f13744e = jSONObject.getLong("inviteId");
            invitationFriend.f13745f = jSONObject.getString("sentTime");
            invitationFriend.f13746g = jSONObject.has("inviteeId") ? jSONObject.getLong("inviteeId") : invitationFriend.f13746g;
            invitationFriend.f13747h = jSONObject.has("inviterId") ? jSONObject.getLong("inviterId") : invitationFriend.f13747h;
            invitationFriend.f13748i = jSONObject.getString("nickname");
            String string = "";
            if (jSONObject.has("cover")) {
                invitationFriend.f13749j = jSONObject.isNull("cover") ? "" : jSONObject.getString("cover");
            }
            if (jSONObject.has("statusMessage")) {
                invitationFriend.f13750k = jSONObject.isNull("statusMessage") ? "" : jSONObject.getString("statusMessage");
            }
            invitationFriend.f13751l = jSONObject.has("regionCode") ? jSONObject.getString("regionCode") : invitationFriend.f13751l;
            if (jSONObject.has("message")) {
                invitationFriend.f13752m = jSONObject.isNull("message") ? "" : jSONObject.getString("message");
            }
            if (jSONObject.has("orgName")) {
                invitationFriend.f13753n = jSONObject.isNull("orgName") ? "" : jSONObject.getString("orgName");
            }
            if (jSONObject.has("orgTitle")) {
                invitationFriend.f13754o = jSONObject.isNull("orgTitle") ? "" : jSONObject.getString("orgTitle");
            }
            if (jSONObject.has("department")) {
                invitationFriend.f13755p = jSONObject.isNull("department") ? "" : jSONObject.getString("department");
            }
            if (jSONObject.has("inviterEmail")) {
                if (!jSONObject.isNull("inviterEmail")) {
                    string = jSONObject.getString("inviterEmail");
                }
                invitationFriend.f13756q = string;
            }
            return invitationFriend;
        } catch (JSONException unused) {
            Log.e("FriendsClientUtils", "[paresInvitationFriendFromServerResponse] parse missing. JSONstr=" + jSONObject.toString());
            return null;
        }
    }

    /* renamed from: j */
    public static C2973l0 m20188j(String str, JSONObject jSONObject) {
        return m20189k(str, jSONObject, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:148:0x022f A[Catch: JSONException -> 0x02d6, TRY_LEAVE, TryCatch #4 {JSONException -> 0x02d6, blocks: (B:146:0x0227, B:148:0x022f, B:155:0x0245), top: B:210:0x0227 }] */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02cd  */
    /* renamed from: k */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static C2973l0 m20189k(String str, JSONObject jSONObject, boolean z8) {
        String str2;
        String str3;
        String str4;
        int i9;
        int i10;
        String string;
        int i11;
        String string2;
        String string3;
        if (jSONObject == null) {
            Log.d("FriendsClientUtils", "[parseMediaInfo] The JSONObject is null");
            return null;
        }
        try {
            long j9 = (!jSONObject.has("mediaId") || jSONObject.isNull("mediaId")) ? -1L : jSONObject.getLong("mediaId");
            if (j9 == -1) {
                Log.d("FriendsClientUtils", "[parseMediaInfo] Can't get media id.");
                return null;
            }
            String string4 = (!jSONObject.has("mediaName") || jSONObject.isNull("mediaName")) ? "" : jSONObject.getString("mediaName");
            long j10 = (!jSONObject.has("lastModified") || jSONObject.isNull("lastModified")) ? 0L : jSONObject.getLong("lastModified") * 1000;
            long j11 = (!jSONObject.has("expiredDate") || jSONObject.isNull("expiredDate")) ? 0L : jSONObject.getLong("expiredDate");
            String string5 = (!jSONObject.has(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION) || jSONObject.isNull(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION)) ? "" : jSONObject.getString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION);
            String string6 = (!jSONObject.has("mediaType") || jSONObject.isNull("mediaType")) ? "" : jSONObject.getString("mediaType");
            String string7 = (!jSONObject.has("creatorId") || jSONObject.isNull("creatorId")) ? "" : jSONObject.getString("creatorId");
            int i12 = (!jSONObject.has("commentTextCount") || jSONObject.isNull("commentTextCount")) ? 0 : jSONObject.getInt("commentTextCount");
            int i13 = (!jSONObject.has("commentMediaCount") || jSONObject.isNull("commentMediaCount")) ? 0 : jSONObject.getInt("commentMediaCount");
            int i14 = (!jSONObject.has("commentDoodleCount") || jSONObject.isNull("commentDoodleCount")) ? 0 : jSONObject.getInt("commentDoodleCount");
            int i15 = (!jSONObject.has("totalCommentCount") || jSONObject.isNull("totalCommentCount")) ? 0 : jSONObject.getInt("totalCommentCount");
            long j12 = (!jSONObject.has("descriptionLastModified") || jSONObject.isNull("descriptionLastModified")) ? 0L : jSONObject.getLong("descriptionLastModified") * 1000;
            String strValueOf = (z8 && jSONObject.has("albumId") && !jSONObject.isNull("albumId")) ? String.valueOf(jSONObject.getInt("albumId")) : str;
            C2973l0.a aVar = new C2973l0.a();
            try {
                if (jSONObject.has("thumbnail")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("thumbnail");
                    if (jSONObject2.has("downloadURL")) {
                        try {
                            string2 = jSONObject2.getString("downloadURL");
                        } catch (JSONException e9) {
                            e = e9;
                            i9 = 0;
                            str2 = "width";
                            str3 = "height";
                            str4 = "md5";
                            Log.e("FriendsClientUtils", "[paresMediaInfo] no thumbnail", e);
                            i = i9;
                            i10 = 0;
                            C2973l0.a aVar2 = new C2973l0.a();
                            if (!jSONObject.has("original")) {
                            }
                            C2973l0 c2973l0 = new C2973l0(-1L, strValueOf, j9, string4, string5, string6, j10, string7, aVar, aVar2, i10, i, i12, i13, i14, i15, j12);
                            c2973l0.m15120G(j11);
                            return c2973l0;
                        }
                    } else {
                        string2 = "";
                    }
                    aVar.f13200d = string2;
                    aVar.f13197a = jSONObject2.has("fileSize") ? jSONObject2.getLong("fileSize") : 0L;
                    aVar.f13198b = jSONObject2.has("hashKey") ? jSONObject2.getString("hashKey") : "";
                    str4 = "md5";
                    try {
                        if (jSONObject2.has(str4)) {
                            try {
                                string3 = jSONObject2.getString(str4);
                            } catch (JSONException e10) {
                                e = e10;
                                i9 = 0;
                                str2 = "width";
                                str3 = "height";
                                Log.e("FriendsClientUtils", "[paresMediaInfo] no thumbnail", e);
                                i = i9;
                                i10 = 0;
                                C2973l0.a aVar22 = new C2973l0.a();
                                if (!jSONObject.has("original")) {
                                }
                                C2973l0 c2973l02 = new C2973l0(-1L, strValueOf, j9, string4, string5, string6, j10, string7, aVar, aVar22, i10, i, i12, i13, i14, i15, j12);
                                c2973l02.m15120G(j11);
                                return c2973l02;
                            }
                        } else {
                            string3 = "";
                        }
                        aVar.f13199c = string3;
                        str3 = "height";
                    } catch (JSONException e11) {
                        e = e11;
                        str2 = "width";
                        str3 = "height";
                    }
                    try {
                        if (jSONObject2.has(str3)) {
                            try {
                                i9 = Integer.parseInt(jSONObject2.getString(str3));
                            } catch (JSONException e12) {
                                e = e12;
                                i9 = 0;
                                str2 = "width";
                                Log.e("FriendsClientUtils", "[paresMediaInfo] no thumbnail", e);
                                i = i9;
                                i10 = 0;
                                C2973l0.a aVar222 = new C2973l0.a();
                                if (!jSONObject.has("original")) {
                                }
                                C2973l0 c2973l022 = new C2973l0(-1L, strValueOf, j9, string4, string5, string6, j10, string7, aVar, aVar222, i10, i, i12, i13, i14, i15, j12);
                                c2973l022.m15120G(j11);
                                return c2973l022;
                            }
                        } else {
                            i9 = 0;
                        }
                        str2 = "width";
                        try {
                            i11 = jSONObject2.has(str2) ? Integer.parseInt(jSONObject2.getString(str2)) : 0;
                            i = i9;
                        } catch (JSONException e13) {
                            e = e13;
                            Log.e("FriendsClientUtils", "[paresMediaInfo] no thumbnail", e);
                            i = i9;
                            i10 = 0;
                            C2973l0.a aVar2222 = new C2973l0.a();
                            if (!jSONObject.has("original")) {
                            }
                            C2973l0 c2973l0222 = new C2973l0(-1L, strValueOf, j9, string4, string5, string6, j10, string7, aVar, aVar2222, i10, i, i12, i13, i14, i15, j12);
                            c2973l0222.m15120G(j11);
                            return c2973l0222;
                        }
                    } catch (JSONException e14) {
                        e = e14;
                        str2 = "width";
                        i9 = 0;
                        Log.e("FriendsClientUtils", "[paresMediaInfo] no thumbnail", e);
                        i = i9;
                        i10 = 0;
                        C2973l0.a aVar22222 = new C2973l0.a();
                        if (!jSONObject.has("original")) {
                        }
                        C2973l0 c2973l02222 = new C2973l0(-1L, strValueOf, j9, string4, string5, string6, j10, string7, aVar, aVar22222, i10, i, i12, i13, i14, i15, j12);
                        c2973l02222.m15120G(j11);
                        return c2973l02222;
                    }
                } else {
                    str2 = "width";
                    str3 = "height";
                    str4 = "md5";
                    i11 = 0;
                }
                i10 = i11;
            } catch (JSONException e15) {
                e = e15;
                str2 = "width";
                str3 = "height";
                str4 = "md5";
            }
            C2973l0.a aVar222222 = new C2973l0.a();
            try {
                if (!jSONObject.has("original")) {
                    JSONObject jSONObject3 = jSONObject.getJSONObject("original");
                    if (jSONObject3.has("downloadURL")) {
                        try {
                            string = jSONObject3.getString("downloadURL");
                        } catch (JSONException e16) {
                            e = e16;
                            Log.e("FriendsClientUtils", "[paresMediaInfo] no original", e);
                            C2973l0 c2973l022222 = new C2973l0(-1L, strValueOf, j9, string4, string5, string6, j10, string7, aVar, aVar222222, i10, i, i12, i13, i14, i15, j12);
                            c2973l022222.m15120G(j11);
                            return c2973l022222;
                        }
                    } else {
                        string = "";
                    }
                    aVar222222.f13200d = string;
                    int i16 = i10;
                    int i17 = i;
                    try {
                        aVar222222.f13197a = jSONObject3.has("fileSize") ? jSONObject3.getLong("fileSize") : 0L;
                        aVar222222.f13198b = jSONObject3.has("hashKey") ? jSONObject3.getString("hashKey") : "";
                        aVar222222.f13199c = jSONObject3.has(str4) ? jSONObject3.getString(str4) : "";
                        aVar222222.f13202f = jSONObject3.has("creatorId") ? jSONObject3.getString("creatorId") : "";
                        aVar222222.f13203g = jSONObject3.has("duration") ? jSONObject3.getString("duration") : "0";
                        i = jSONObject3.has(str3) ? Integer.parseInt(jSONObject3.getString(str3)) : i17;
                        try {
                            i10 = jSONObject3.has(str2) ? Integer.parseInt(jSONObject3.getString(str2)) : i16;
                            if (aVar222222.f13202f == "null") {
                                aVar222222.f13202f = "-1";
                            }
                        } catch (JSONException e17) {
                            e = e17;
                            i10 = i16;
                            Log.e("FriendsClientUtils", "[paresMediaInfo] no original", e);
                            C2973l0 c2973l0222222 = new C2973l0(-1L, strValueOf, j9, string4, string5, string6, j10, string7, aVar, aVar222222, i10, i, i12, i13, i14, i15, j12);
                            c2973l0222222.m15120G(j11);
                            return c2973l0222222;
                        }
                    } catch (JSONException e18) {
                        e = e18;
                        i = i17;
                    }
                }
            } catch (JSONException e19) {
                e = e19;
            }
            C2973l0 c2973l02222222 = new C2973l0(-1L, strValueOf, j9, string4, string5, string6, j10, string7, aVar, aVar222222, i10, i, i12, i13, i14, i15, j12);
            c2973l02222222.m15120G(j11);
            return c2973l02222222;
        } catch (JSONException e20) {
            Log.e("FriendsClientUtils", "[paresMediaInfo] JSONstr=" + jSONObject.toString(), e20);
            return null;
        }
    }

    /* renamed from: l */
    public static List<C2973l0> m20190l(String str, JSONArray jSONArray, boolean z8) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                try {
                    C2973l0 c2973l0M20189k = m20189k(str, jSONArray.getJSONObject(i9), z8);
                    if (c2973l0M20189k != null) {
                        arrayList.add(c2973l0M20189k);
                    }
                } catch (JSONException e9) {
                    Log.e("FriendsClientUtils", "", e9);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: m */
    public static C3061a.a m20191m(String str, JSONObject jSONObject) {
        C3061a.a aVar = new C3061a.a();
        try {
            aVar.f13817d = jSONObject.getString("downloadURL");
            aVar.f13814a = jSONObject.getLong("fileSize");
            aVar.f13815b = jSONObject.getString("hashKey");
            aVar.f13816c = jSONObject.getString("md5");
            try {
                if (str.equals("CommentDoodle")) {
                    aVar.f13819f = jSONObject.getString(TtmlNode.ATTR_TTS_COLOR);
                } else {
                    aVar.f13819f = jSONObject.getString("duration");
                }
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
            try {
                if (jSONObject.has("type")) {
                    aVar.f13820g = jSONObject.getString("type");
                }
            } catch (JSONException e10) {
                e10.printStackTrace();
            }
        } catch (JSONException unused) {
            Log.d("FriendsClientUtils", "[parseMediaNote] JObj=" + jSONObject.toString());
        }
        return aVar;
    }

    /* renamed from: n */
    public static C3061a.a m20192n(JSONObject jSONObject) {
        Object obj;
        C3061a.a aVar = new C3061a.a();
        try {
            obj = jSONObject.get("mediaNotes");
        } catch (JSONException unused) {
            Log.d("FriendsClientUtils", "[parseMediaNoteFromMediaInfo] JObj=" + jSONObject.toString());
        }
        if (obj instanceof JSONObject) {
            return m20191m("CommentMedia", (JSONObject) obj);
        }
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            if (jSONArray.length() > 0) {
                return m20191m("CommentMedia", jSONArray.getJSONObject(0));
            }
        }
        return aVar;
    }

    /* renamed from: o */
    public static PollOptionObj m20193o(String str) {
        JSONObject jSONObjectM20195q = m20195q(str);
        if (jSONObjectM20195q == null) {
            try {
                jSONObjectM20195q = new JSONObject(str);
            } catch (JSONException unused) {
                if (jSONObjectM20195q == null) {
                    return null;
                }
                Log.d("FriendsClientUtils", "[parsePollOption] JObj=" + jSONObjectM20195q.toString());
                return null;
            }
        }
        return new PollOptionObj(jSONObjectM20195q.getLong(TtmlNode.ATTR_ID), jSONObjectM20195q.getLong("topicId"), jSONObjectM20195q.getInt("optionOrder"), jSONObjectM20195q.getString("optionDescription"), jSONObjectM20195q.getInt("numberOfPolls"), jSONObjectM20195q.getBoolean("isVoted"), jSONObjectM20195q.getLong("lastModified") * 1000, null, jSONObjectM20195q.optLong("lastVotedTime", -1L));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001f A[Catch: JSONException -> 0x006f, TRY_LEAVE, TryCatch #3 {JSONException -> 0x006f, blocks: (B:8:0x0019, B:10:0x001f), top: B:29:0x0019 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0077  */
    /* renamed from: p */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static List<PollOptionObj> m20194p(String str) {
        int i9;
        JSONArray jSONArrayM20196r = m20196r(str);
        ArrayList arrayList = null;
        if (jSONArrayM20196r != null) {
            ArrayList arrayList2 = new ArrayList(jSONArrayM20196r.length());
            i9 = 0;
            while (i9 < jSONArrayM20196r.length()) {
            }
            return arrayList2;
        }
        try {
            jSONArrayM20196r = new JSONArray(str);
            try {
                ArrayList arrayList22 = new ArrayList(jSONArrayM20196r.length());
                i9 = 0;
                while (i9 < jSONArrayM20196r.length()) {
                    try {
                        JSONObject jSONObject = jSONArrayM20196r.getJSONObject(i9);
                        JSONArray jSONArray = jSONArrayM20196r;
                        int i10 = i9;
                        try {
                            arrayList22.add(new PollOptionObj(jSONObject.getLong(TtmlNode.ATTR_ID), jSONObject.getLong("topicId"), jSONObject.getInt("optionOrder"), jSONObject.getString("optionDescription"), jSONObject.getInt("numberOfPolls"), jSONObject.getBoolean("isVoted"), 1000 * jSONObject.getLong("lastModified"), null, jSONObject.optLong("lastVotedTime", -1L)));
                            i9 = i10 + 1;
                            jSONArrayM20196r = jSONArray;
                        } catch (JSONException unused) {
                            jSONArrayM20196r = jSONArray;
                            arrayList = arrayList22;
                            if (jSONArrayM20196r != null) {
                            }
                            return arrayList;
                        }
                    } catch (JSONException unused2) {
                    }
                }
                return arrayList22;
            } catch (JSONException unused3) {
            }
        } catch (JSONException unused4) {
        }
        if (jSONArrayM20196r != null) {
            Log.d("FriendsClientUtils", "[parsePollOptions] JObj=" + jSONArrayM20196r.toString());
        }
        return arrayList;
    }

    /* renamed from: q */
    public static JSONObject m20195q(String str) {
        JSONObject jSONObject;
        if (str == null) {
            Log.e("FriendsClientUtils", "[parseJObj] JSON string is null.");
            return null;
        }
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException unused) {
            Log.e("FriendsClientUtils", "[parseJObj] Parse error. JSONstr=" + str);
            jSONObject = null;
        }
        if (jSONObject != null) {
            try {
                return jSONObject.getJSONObject("result");
            } catch (JSONException unused2) {
                Log.e("FriendsClientUtils", "[parseResults] 'result' missing. JSONstr=" + str);
            }
        }
        return null;
    }

    /* renamed from: r */
    public static JSONArray m20196r(String str) {
        JSONObject jSONObject;
        if (str != null) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException unused) {
                Log.e("FriendsClientUtils", "[parseJObj] Parse error. JSONstr=" + str);
            }
        } else {
            jSONObject = null;
        }
        if (jSONObject != null) {
            try {
                return jSONObject.getJSONArray("results");
            } catch (JSONException unused2) {
                Log.e("FriendsClientUtils", "[parseResults] 'results' missing. JSONstr=" + str);
            }
        }
        return null;
    }

    /* renamed from: s */
    public static UserInfo m20197s(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        UserInfo userInfo = new UserInfo();
        try {
            userInfo.f13777b = jSONObject.getLong("userId");
            String string = "";
            userInfo.f13778c = jSONObject.isNull("displayName") ? "" : jSONObject.getString("displayName");
            userInfo.f13779d = jSONObject.isNull("avatar") ? "" : jSONObject.getString("avatar");
            userInfo.f13780e = jSONObject.isNull("statusMessage") ? "" : jSONObject.getString("statusMessage");
            userInfo.f13781f = jSONObject.isNull("publicId") ? "" : jSONObject.getString("publicId");
            userInfo.f13782g = jSONObject.getString("jid");
            userInfo.f13783h = jSONObject.getString("avatarAlbumId");
            userInfo.f13785j = jSONObject.isNull("cover") ? "" : jSONObject.getString("cover");
            userInfo.f13784i = jSONObject.getString("coverAlbumId");
            userInfo.f13787l = jSONObject.getString("hiddenAlbumId");
            if (jSONObject.has("orgName")) {
                userInfo.f13789n = jSONObject.isNull("orgName") ? "" : jSONObject.getString("orgName");
            }
            if (jSONObject.has("orgTitle")) {
                userInfo.f13791p = jSONObject.isNull("orgTitle") ? "" : jSONObject.getString("orgTitle");
            }
            if (jSONObject.has("department")) {
                if (!jSONObject.isNull("department")) {
                    string = jSONObject.getString("department");
                }
                userInfo.f13790o = string;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("attrs");
            if (jSONObject2 != null) {
                if (jSONObject2.has("notification.disabled")) {
                    userInfo.f13792q = jSONObject2.getString("notification.disabled").equals("0");
                }
                if (jSONObject2.has("profile.publicId.enabled")) {
                    userInfo.f13793r = !jSONObject2.getString("profile.publicId.enabled").equals("0");
                }
                if (jSONObject2.has("profile.pin.enabled")) {
                    userInfo.f13794s = !jSONObject2.getString("profile.pin.enabled").equals("0");
                } else {
                    userInfo.f13794s = UserInfo.f13770H;
                }
                if (jSONObject2.has("friend.autoInvite.enabled")) {
                    userInfo.f13795t = !jSONObject2.getString("friend.autoInvite.enabled").equals("0");
                }
                if (jSONObject2.has("friend.autoAccept.enabled")) {
                    userInfo.f13796u = !jSONObject2.getString("friend.autoAccept.enabled").equals("0");
                }
                if (jSONObject2.has("notification.hide.message")) {
                    userInfo.f13797v = !jSONObject2.getString("notification.hide.message").equals("0");
                } else {
                    Context applicationContext = Globals.m7388i0().getApplicationContext();
                    if (applicationContext != null) {
                        userInfo.f13797v = !AbstractC5146g0.m20046d("setting_notification_show_preview", SettingActivity.f8945T, applicationContext);
                    } else {
                        userInfo.f13797v = !SettingActivity.f8945T;
                    }
                }
                if (jSONObject2.has("user.email")) {
                    userInfo.f13798w = jSONObject2.getString("user.email");
                    Globals.m7388i0().m7564j3(true);
                    Globals.m7388i0().m7501V3(userInfo.f13798w);
                }
                if (jSONObject2.has("dou.allow.meeting.recording")) {
                    userInfo.f13799x = jSONObject2.getString("dou.allow.meeting.recording");
                }
                if (jSONObject2.has("dou.e2ee.update.status")) {
                    userInfo.m15774e(jSONObject2.getString("dou.e2ee.update.status"));
                }
                if (jSONObject2.has("profile.forward.include.sender")) {
                    userInfo.f13800y = "1".equals(jSONObject2.getString("profile.forward.include.sender"));
                }
                if (jSONObject2.has("peopleSearch.allowed")) {
                    userInfo.f13771A = "1".equals(jSONObject2.getString("peopleSearch.allowed"));
                }
            }
            JSONArray jSONArray = jSONObject.getJSONArray("accounts");
            if (jSONArray != null) {
                userInfo.f13788m = jSONArray.toString();
                for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                    JSONObject jSONObject3 = jSONArray.getJSONObject(i9);
                    if (jSONObject3.getString("accountSource").equals("Phone")) {
                        userInfo.f13786k = jSONObject3.getString("account");
                    }
                }
            }
            return userInfo;
        } catch (JSONException unused) {
            Log.e("FriendsClientUtils", "[paresUserInfoFromServerResponse] 'userId', 'displaName', 'avatar' missing. JSONstr=" + jSONObject.toString());
            return null;
        }
    }

    /* renamed from: t */
    public static UserInfo m20198t(String str) {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException unused) {
            Log.e("FriendsClientUtils", "[parseSelfInfoFromJSONStr] Parse error. JSONstr=" + str);
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        try {
            jSONObject2 = jSONObject.getJSONObject("result");
        } catch (JSONException unused2) {
            Log.e("FriendsClientUtils", "[parseSelfInfoFromJSONStr] 'results' missing. JSONstr=" + str);
            jSONObject2 = null;
        }
        if (jSONObject2 != null) {
            return m20197s(jSONObject2);
        }
        return null;
    }

    /* renamed from: u */
    public static StickerObj m20199u(JSONObject jSONObject) {
        try {
            long j9 = jSONObject.getLong("stickerId");
            long j10 = jSONObject.getLong("packId");
            StickerObj stickerObj = new StickerObj(-1L, j9, j10, jSONObject.has("stickerOrder") ? jSONObject.getLong("stickerOrder") : 0L, jSONObject.has("lastModified") ? jSONObject.getLong("lastModified") : 0L, jSONObject.getString("originalURL"), jSONObject.getString("thumbnailURL"), 0, 0);
            StringBuilder sb = new StringBuilder();
            sb.append(CLUtility.m16541h1(j10));
            String str = File.separator;
            sb.append(str);
            sb.append(String.valueOf(j9));
            sb.append("_");
            sb.append("thumbnail");
            stickerObj.m16294s(sb.toString());
            stickerObj.m16293r(CLUtility.m16541h1(j10) + str + String.valueOf(j9));
            return stickerObj;
        } catch (JSONException unused) {
            Log.d("FriendsClientUtils", "[parseBullentinTopicComment] JObj=" + jSONObject.toString());
            return null;
        }
    }

    /* renamed from: v */
    public static List<StickerObj> m20200v(JSONArray jSONArray, StickerPackObj stickerPackObj) {
        ArrayList arrayList = new ArrayList();
        boolean z8 = false;
        int i9 = 0;
        while (i9 < jSONArray.length()) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i9);
                try {
                    long j9 = jSONObject.getLong("stickerId");
                    long j10 = jSONObject.getLong("stickerOrder");
                    long j11 = jSONObject.getLong("lastModified");
                    String string = jSONObject.getString("originalURL");
                    String string2 = jSONObject.getString("thumbnailURL");
                    StringBuilder sb = new StringBuilder();
                    sb.append(CLUtility.m16541h1(stickerPackObj.m14803g()));
                    String str = File.separator;
                    sb.append(str);
                    String string3 = sb.toString();
                    String str2 = string3 + Long.toString(j9);
                    String str3 = str2 + "_thumbnail";
                    Pair<Integer, Integer> pairM16506Y0 = CLUtility.m16506Y0(str3, null, z8);
                    if (stickerPackObj.m14805i().equals("AnimationPNG")) {
                        File file = new File(str2);
                        File file2 = new File(str2 + ".tmp");
                        if (file2.exists()) {
                            CLUtility.m16447I(file2);
                        }
                        file.renameTo(file2);
                        CLUtility.m16474O2(str2 + ".tmp", string3);
                        if (file2.exists()) {
                            file2.delete();
                        }
                        if (!CLUtility.m16613z1(str2 + str + "info.json", null)) {
                            return null;
                        }
                    } else {
                        if (!CLUtility.m16613z1(str2, null)) {
                            return null;
                        }
                        if (!CLUtility.m16613z1(str3, null)) {
                            return null;
                        }
                    }
                    arrayList.add(new StickerObj(-1L, j9, stickerPackObj.m14803g(), j10, j11, string, str2, string2, str3, ((Integer) pairM16506Y0.first).intValue(), ((Integer) pairM16506Y0.second).intValue()));
                } catch (JSONException unused) {
                    Log.e("FriendsClientUtils", "[sticker.sticker.list] Parse item error. JSONstr=" + jSONObject.toString());
                }
            } catch (JSONException unused2) {
                Log.e("FriendsClientUtils", "[sticker.sticker.list] groupinfo parse error. JSONstr=" + jSONArray.toString());
            }
            i9++;
            z8 = false;
        }
        return arrayList;
    }

    /* renamed from: w */
    public static SuggestionFriend m20201w(JSONObject jSONObject) {
        SuggestionFriend suggestionFriend = new SuggestionFriend();
        suggestionFriend.f13759c = jSONObject.getLong("userId");
        suggestionFriend.f13761e = jSONObject.isNull("displayName") ? "" : jSONObject.getString("displayName");
        suggestionFriend.f13760d = jSONObject.isNull("avatar") ? "" : jSONObject.getString("avatar");
        suggestionFriend.f13768l = jSONObject.getBoolean("isFriend");
        return suggestionFriend;
    }

    /* renamed from: x */
    public static List<SuggestionFriend> m20202x(String str) throws JSONException {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("results");
            int length = jSONArray.length();
            for (int i9 = 0; i9 < length; i9++) {
                arrayList.add(m20201w(jSONArray.getJSONObject(i9)));
            }
        } catch (JSONException e9) {
            Log.e("FriendsClientUtils", "[parseSuggestFriendArray]", e9);
        }
        return arrayList;
    }

    /* renamed from: y */
    public static SuggestionFriend m20203y(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        SuggestionFriend suggestionFriend = new SuggestionFriend();
        try {
            suggestionFriend.f13758b = jSONObject.getString("inviteStatus");
            suggestionFriend.f13759c = jSONObject.getLong("userId");
            suggestionFriend.f13760d = jSONObject.getString("avatar");
            suggestionFriend.f13761e = jSONObject.getString("displayName");
            String string = "";
            if (jSONObject.has("sourceType")) {
                suggestionFriend.f13762f = jSONObject.isNull("sourceType") ? "" : jSONObject.getString("sourceType");
            }
            suggestionFriend.f13765i = jSONObject.getLong("createdTime");
            if (jSONObject.has("cover")) {
                suggestionFriend.f13764h = jSONObject.isNull("cover") ? "" : jSONObject.getString("cover");
            }
            if (jSONObject.has("statusMessage")) {
                suggestionFriend.f13766j = jSONObject.isNull("statusMessage") ? "" : jSONObject.getString("statusMessage");
            }
            if (jSONObject.has("regionCode")) {
                suggestionFriend.f13763g = jSONObject.isNull("regionCode") ? "" : jSONObject.getString("regionCode");
            }
            if (jSONObject.has("publicId")) {
                if (!jSONObject.isNull("publicId")) {
                    string = jSONObject.getString("publicId");
                }
                suggestionFriend.f13767k = string;
            }
            return suggestionFriend;
        } catch (JSONException unused) {
            Log.e("FriendsClientUtils", "[paresSuggestionFriendFromServerResponse] parse missing. JSONstr=" + jSONObject.toString());
            return null;
        }
    }

    /* renamed from: z */
    public static TopicObj m20204z(JSONObject jSONObject, long j9) {
        String str;
        long j10;
        boolean z8;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        int i9;
        int i10;
        int i11;
        try {
            if (jSONObject.has("isDeleted") && jSONObject.getBoolean("isDeleted")) {
                return null;
            }
            long j11 = jSONObject.getLong(TtmlNode.ATTR_ID);
            String string = jSONObject.getString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE);
            String string2 = "";
            String string3 = jSONObject.isNull(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION) ? "" : jSONObject.getString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION);
            int i12 = jSONObject.getInt("likeCount");
            int i13 = jSONObject.getInt("postCount");
            boolean z13 = jSONObject.getBoolean("isLiked");
            long j12 = jSONObject.getLong("lastModified") * 1000;
            long j13 = jSONObject.isNull("lastPostTime") ? 0L : jSONObject.getLong("lastPostTime") * 1000;
            long j14 = jSONObject.isNull("lastStickyTime") ? 0L : jSONObject.getLong("lastStickyTime") * 1000;
            boolean z14 = jSONObject.getBoolean("isSticky");
            String string4 = jSONObject.getString("components");
            long j15 = jSONObject.getLong("createdTime") * 1000;
            boolean z15 = jSONObject.getBoolean("isClosed");
            boolean z16 = jSONObject.getBoolean("isCreatedByAdmin");
            long j16 = jSONObject.getLong("creatorId");
            boolean z17 = jSONObject.getBoolean("isNotificationDisabled");
            String string5 = jSONObject.getString("topicVersion");
            String string6 = jSONObject.getString("topicType");
            int i14 = jSONObject.has("numberOfVoters") ? jSONObject.getInt("numberOfVoters") : 0;
            boolean z18 = jSONObject.has("isVoted") ? jSONObject.getBoolean("isVoted") : false;
            if (jSONObject.has(TtmlNode.TAG_METADATA)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(TtmlNode.TAG_METADATA);
                boolean z19 = jSONObject2.has("poll.multiple.choose") ? jSONObject2.getBoolean("poll.multiple.choose") : false;
                long j17 = jSONObject2.has("poll.expiration.time") ? jSONObject2.getLong("poll.expiration.time") * 1000 : 0L;
                boolean z20 = jSONObject2.has("poll.is.secret.ballot") ? jSONObject2.getBoolean("poll.is.secret.ballot") : false;
                boolean z21 = jSONObject2.has("poll.show.after.accomplish") ? jSONObject2.getBoolean("poll.show.after.accomplish") : false;
                boolean z22 = jSONObject2.has("poll.prohibit.vote") ? jSONObject2.getBoolean("poll.prohibit.vote") : false;
                boolean z23 = jSONObject2.has("poll.limit.votes.enabled") ? jSONObject2.getBoolean("poll.limit.votes.enabled") : false;
                if (z23) {
                    string2 = jSONObject2.getString("poll.limit.votes.criteria");
                    i11 = jSONObject2.getInt("poll.limit.votes.criteria.num");
                } else {
                    i11 = 0;
                }
                if (jSONObject2.has("poll.feature.version")) {
                    i10 = jSONObject2.getInt("poll.feature.version");
                    z12 = z23;
                    z11 = z22;
                    z10 = z21;
                    z9 = z20;
                    i9 = i11;
                    z8 = z19;
                    str = string2;
                    j10 = j17;
                } else {
                    z12 = z23;
                    z11 = z22;
                    z10 = z21;
                    z9 = z20;
                    i9 = i11;
                    z8 = z19;
                    str = string2;
                    j10 = j17;
                    i10 = 0;
                }
            } else {
                str = "";
                j10 = 0;
                z8 = false;
                z9 = false;
                z10 = false;
                z11 = false;
                z12 = false;
                i9 = 0;
                i10 = 0;
            }
            return new TopicObj(j11, j9, string, string3, i12, i13, z13, j12, j13, j14, z14, Pair.create(TopicCommentObj.FromSource.Server, string4), j15, z15, z16, j16, z17, 0, string5, string6, z8, j10, i14, z18, 0L, z9, z10, z11, z12, str, i9, i10);
        } catch (JSONException unused) {
            Log.d("FriendsClientUtils", "[parseTopic] JObj=" + jSONObject.toString());
            return null;
        }
    }
}
