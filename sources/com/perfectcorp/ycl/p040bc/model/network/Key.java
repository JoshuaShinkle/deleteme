package com.perfectcorp.ycl.p040bc.model.network;

import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.iid.MessengerIpcClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.perfectcorp.model.Model;
import p047d5.C4677a;

/* loaded from: classes2.dex */
public class Key {

    public static class Init {
        public static final Gson GSON = new GsonBuilder().create();
        public static _Parameter Parameter = null;

        public static class Response extends Model {

            @SerializedName("live.assistant")
            public Assistant assistant;

            @SerializedName("live.live")
            public Live live;

            /* renamed from: org, reason: collision with root package name */
            @SerializedName("live.org")
            public Org f22734org;

            @SerializedName("live.registration")
            public Register register;

            @SerializedName("live.subscription")
            public Subscription subscription;

            @SerializedName("live.user")
            public User user;

            public static class Assistant extends Model {

                @SerializedName(MessengerIpcClient.KEY_ACK)
                public String candidateAck;

                @SerializedName("list")
                public String listCandidate;
            }

            public static class Live extends Model {

                @SerializedName("participant.anonymous.displayname.add")
                public String addAnonymousDisplayName;

                @SerializedName("watchHistory.add")
                public String addWatchHistory;

                @SerializedName("watchHistory.delete")
                public String deleteWatchHistory;

                @SerializedName(SearchIntents.EXTRA_QUERY)
                public String getLive;

                @SerializedName("join")
                public String joinLive;

                @SerializedName("watchHistory.list")
                public String listWatchHistory;

                @SerializedName("user.permission.query")
                public String queryPermission;
            }

            public static class Org extends Model {

                @SerializedName("directory.client.list")
                public String contacts;

                @SerializedName("directory.client.search")
                public String contactsSearch;

                @SerializedName("department")
                public String department;

                @SerializedName("department.member")
                public String departmentMembers;
            }

            public static class Register extends Model {

                @SerializedName("list")
                public String listLiveUsers;

                @SerializedName("listUser")
                public String listRegisterLives;

                @SerializedName("form")
                public String queryRegistrationForm;

                @SerializedName("create")
                public String registerLive;
            }

            public static class Subscription extends Model {

                @SerializedName("android.query")
                public String getData;

                @SerializedName("android.create")
                public String updateToProUser;
            }

            public static class User extends Model {

                @SerializedName("face.collection.query")
                public String faceCollection;

                @SerializedName("org.query")
                public String organization;

                @SerializedName("permissions.list")
                public String permissions;

                @SerializedName("face.create")
                public String registerFace;

                @SerializedName("face.reset")
                public String resetFace;
            }
        }

        public static class _Parameter {

            /* renamed from: ap */
            public final String f15987ap = C4677a.f16375l;
            public final String version = C4677a.m18721y();
            public final String versionType = C4677a.m18677A();
            public final String buildNumber = C4677a.m18716t();
            public final String uuid = C4677a.m18718v();
            public final String model = C4677a.m18712p();
            public final String vender = C4677a.m18720x();
            public final String resolution = C4677a.m18715s();
            public final String apiVersion = C4677a.m18703g();
            public final String apnsType = C4677a.m18704h();
            public final String userAgent = C4677a.m18719w();
        }

        public static void initParameter() {
            if (Parameter == null) {
                Parameter = new _Parameter();
            }
        }
    }
}
