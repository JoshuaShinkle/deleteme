package com.perfectcorp.ycl.p040bc.model.network;

import android.text.TextUtils;
import com.google.android.gms.common.Scopes;
import com.google.common.net.HttpHeaders;
import com.perfectcorp.model.Model;
import com.perfectcorp.utility.C4509d;
import com.perfectcorp.utility.C4510e;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.commons.utility.Log;
import com.perfectcorp.ycl.p040bc.model.DepartmentList;
import com.perfectcorp.ycl.p040bc.model.DepartmentMemberList;
import com.perfectcorp.ycl.p040bc.model.Live;
import com.perfectcorp.ycl.p040bc.model.OrgContactList;
import com.perfectcorp.ycl.p040bc.model.WatchHistoryList;
import com.perfectcorp.ycl.p040bc.model.network.Key;
import com.perfectcorp.ycl.p040bc.model.network.NetworkManager;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p147n5.C5369g;

/* loaded from: classes2.dex */
public class NetworkLive {
    public static final Long EMPTY_PARAMETER = 0L;

    public static class STATE {
        public static final String COMPLETE = "COMPLETE";
        public static final String LIVE = "LIVE";
        public static final String SCHEDULED = "SCHEDULED";
        public static final String STOPPED = "STOPPED";
        public static final String VIDEO_ON_DEMAND = "VOD";
    }

    public static PromisedTask<?, ?, ?> addWatchHistory(final String str, final String str2) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.6
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (NetworkManager.initResponse.live.addWatchHistory == null) {
                    reportError(NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.live.addWatchHistory);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                if (!C4509d.m18120b(str2)) {
                    c4510e.m18125d("liveId", str2);
                }
                return c4510e;
            }
        }).then(NetworkManager.sendPost());
    }

    public static PromisedTask<?, ?, String> candidateAssistantAck(final String str, final long j9) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.28
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (TextUtils.isEmpty(NetworkManager.initResponse.assistant.candidateAck)) {
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.assistant.candidateAck);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                if (j9 != NetworkLive.EMPTY_PARAMETER.longValue()) {
                    c4510e.m18125d("liveId", Long.valueOf(j9));
                }
                return c4510e;
            }
        }).then(NetworkManager.sendPost());
    }

    public static PromisedTask deleteWatchHistory(final String str, final List<WatchHistoryList.LiveHistoryInfo> list) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.5
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (NetworkManager.initResponse.live.deleteWatchHistory == null) {
                    reportError(NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.live.deleteWatchHistory);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    c4510e.m18125d("liveIds", ((WatchHistoryList.LiveHistoryInfo) it.next()).liveId);
                }
                return c4510e;
            }
        }).then(NetworkManager.sendPost());
    }

    public static PromisedTask<?, ?, Live> getLive(final String str, final long j9, final String str2, final boolean z8) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.2
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (NetworkManager.initResponse.live.getLive == null) {
                    throw new PromisedTask.CustomErrorException("getLive API is not initialized", NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.live.getLive);
                if (!C4509d.m18120b(str) && !z8) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                if (j9 != NetworkLive.EMPTY_PARAMETER.longValue()) {
                    c4510e.m18125d("eventId", Long.valueOf(j9));
                }
                if (!C4509d.m18120b(str2)) {
                    c4510e.m18125d("liveId", str2);
                }
                return c4510e;
            }
        }).then(NetworkManager.sendGet()).then(new PromisedTask<String, Void, Live>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.1
            @Override // com.perfectcorp.utility.PromisedTask
            public Live doInBackground(String str3) {
                if (str3 == null) {
                    throw new PromisedTask.CustomErrorException("empty response", -2147483645);
                }
                Live live = (Live) Model.parseFromJSON(Live.class, str3);
                if (live != null) {
                    return live;
                }
                throw new PromisedTask.CustomErrorException("Live jason response parse error", -2147483645);
            }
        });
    }

    public static PromisedTask<?, ?, String> getOrganizationId(final String str) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.18
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (TextUtils.isEmpty(NetworkManager.initResponse.user.organization)) {
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.user.organization);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                return c4510e;
            }
        }).then(NetworkManager.sendPost()).then(new PromisedTask<String, Void, String>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.17
            @Override // com.perfectcorp.utility.PromisedTask
            public String doInBackground(String str2) {
                if (str2 == null) {
                    reportError(-2147483645);
                    return null;
                }
                try {
                    return new JSONObject(str2).getString("organizationId");
                } catch (JSONException e9) {
                    Log.m18149c("NetworkLive", e9.getMessage());
                    return "";
                }
            }
        });
    }

    public static PromisedTask<?, ?, JSONArray> getPermissions(final String str) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.20
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (TextUtils.isEmpty(NetworkManager.initResponse.user.permissions)) {
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.user.permissions);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                return c4510e;
            }
        }).then(NetworkManager.sendPost()).then(new PromisedTask<String, Void, JSONArray>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.19
            @Override // com.perfectcorp.utility.PromisedTask
            public JSONArray doInBackground(String str2) {
                if (str2 == null) {
                    reportError(-2147483645);
                    return null;
                }
                try {
                    return new JSONObject(str2).getJSONArray("permissions");
                } catch (JSONException e9) {
                    Log.m18149c("NetworkLive", e9.getMessage());
                    return null;
                }
            }
        });
    }

    public static PromisedTask<?, ?, Live.LivePermission> isBlockByHost(final String str, final String str2) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.8
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (NetworkManager.initResponse.live.queryPermission == null) {
                    throw new PromisedTask.CustomErrorException("getLive API is not initialized", NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.live.queryPermission);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                if (!C4509d.m18120b(str2)) {
                    c4510e.m18125d("liveId", str2);
                }
                c4510e.m18125d("uuid", Key.Init.Parameter.uuid);
                return c4510e;
            }
        }).then(NetworkManager.sendGet()).then(new PromisedTask<String, Void, Live.LivePermission>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.7
            @Override // com.perfectcorp.utility.PromisedTask
            public Live.LivePermission doInBackground(String str3) {
                if (str3 == null) {
                    throw new PromisedTask.CustomErrorException("empty response", -2147483645);
                }
                Live.LivePermission livePermission = (Live.LivePermission) Model.parseFromJSON(Live.LivePermission.class, str3);
                if (livePermission != null) {
                    return livePermission;
                }
                throw new PromisedTask.CustomErrorException("LivePermission jason response parse error", -2147483645);
            }
        });
    }

    public static PromisedTask<?, ?, OrgContactList> listContacts(final String str, final String str2, final int i9, final int i10) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.16
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (NetworkManager.initResponse.f22734org.contacts == null) {
                    reportError(NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.f22734org.contacts);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                c4510e.m18125d("directoryId", !C4509d.m18120b(str2) ? str2 : "");
                c4510e.m18125d("pageIndex", Integer.valueOf(i9));
                c4510e.m18125d("pageSize", Integer.valueOf(i10));
                return c4510e;
            }
        }).then(NetworkManager.sendPost()).then(new PromisedTask<String, Void, OrgContactList>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.15
            @Override // com.perfectcorp.utility.PromisedTask
            public OrgContactList doInBackground(String str3) {
                if (str3 != null) {
                    return (OrgContactList) C5369g.GSON.fromJson(str3, OrgContactList.class);
                }
                reportError(-2147483645);
                return null;
            }
        });
    }

    public static PromisedTask<?, ?, DepartmentList> listDepartment(final String str, final int i9, final int i10, final String str2) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.12
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                Key.Init.Response.Org org2 = NetworkManager.initResponse.f22734org;
                if (org2 == null || org2.department == null) {
                    reportError(NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.f22734org.department);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                c4510e.m18125d("pageIndex", Integer.valueOf(i9));
                c4510e.m18125d("pageSize", Integer.valueOf(i10));
                if (!C4509d.m18120b(str2)) {
                    c4510e.m18125d("sortby", str2);
                }
                return c4510e;
            }
        }).then(NetworkManager.sendPost()).then(new PromisedTask<String, Void, DepartmentList>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.11
            @Override // com.perfectcorp.utility.PromisedTask
            public DepartmentList doInBackground(String str3) {
                if (str3 != null) {
                    return (DepartmentList) C5369g.GSON.fromJson(str3, DepartmentList.class);
                }
                reportError(-2147483645);
                return null;
            }
        });
    }

    public static PromisedTask<?, ?, DepartmentMemberList> listDepartmentMembers(final String str, final String str2, final int i9, final int i10, final String str3) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.14
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                Key.Init.Response.Org org2 = NetworkManager.initResponse.f22734org;
                if (org2 == null || org2.departmentMembers == null) {
                    reportError(NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.f22734org.departmentMembers);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                if (!C4509d.m18120b(str2)) {
                    c4510e.m18125d("departmentId", str2);
                }
                c4510e.m18125d("pageIndex", Integer.valueOf(i9));
                c4510e.m18125d("pageSize", Integer.valueOf(i10));
                if (!C4509d.m18120b(str3)) {
                    c4510e.m18125d("sortby", str3);
                }
                return c4510e;
            }
        }).then(NetworkManager.sendPost()).then(new PromisedTask<String, Void, DepartmentMemberList>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.13
            @Override // com.perfectcorp.utility.PromisedTask
            public DepartmentMemberList doInBackground(String str4) {
                if (str4 != null) {
                    return (DepartmentMemberList) C5369g.GSON.fromJson(str4, DepartmentMemberList.class);
                }
                reportError(-2147483645);
                return null;
            }
        });
    }

    public static PromisedTask<?, ?, WatchHistoryList> listWatchHistory(final String str, final int i9, final int i10) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.4
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (NetworkManager.initResponse.live.listWatchHistory == null) {
                    reportError(NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.live.listWatchHistory);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                c4510e.m18125d("pageIndex", Integer.valueOf(i9));
                c4510e.m18125d("pageSize", Integer.valueOf(i10));
                return c4510e;
            }
        }).then(NetworkManager.sendPost()).then(new PromisedTask<String, Void, WatchHistoryList>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.3
            @Override // com.perfectcorp.utility.PromisedTask
            public WatchHistoryList doInBackground(String str2) {
                if (str2 != null) {
                    return (WatchHistoryList) C5369g.GSON.fromJson(str2, WatchHistoryList.class);
                }
                reportError(-2147483645);
                return null;
            }
        });
    }

    public static PromisedTask<?, ?, ?> registerUserFace(final String str, final List<String> list, final int i9, final int i10) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.21
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (TextUtils.isEmpty(NetworkManager.initResponse.user.registerFace)) {
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.user.registerFace);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    c4510e.m18125d("faceFeatures", (String) it.next());
                }
                c4510e.m18125d("featureType", Integer.valueOf(i9));
                c4510e.m18125d("featureSubType", Integer.valueOf(i10));
                return c4510e;
            }
        }).then(NetworkManager.sendPost());
    }

    public static PromisedTask<?, ?, ?> resetUserFace(final String str, final String str2, final String str3) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.22
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (TextUtils.isEmpty(NetworkManager.initResponse.user.resetFace)) {
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.user.resetFace);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                c4510e.m18125d(Scopes.EMAIL, str2);
                c4510e.m18125d("password", str3);
                return c4510e;
            }
        }).then(NetworkManager.sendPost());
    }

    public static PromisedTask<?, ?, OrgContactList> searchOrgContacts(final String str, final String str2, String str3, final int i9, final int i10) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.10
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (NetworkManager.initResponse.f22734org.contactsSearch == null) {
                    reportError(NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.f22734org.contactsSearch);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                if (!C4509d.m18120b(str2)) {
                    c4510e.m18125d("keyword", str2);
                }
                c4510e.m18125d("pageIndex", Integer.valueOf(i9));
                c4510e.m18125d("pageSize", Integer.valueOf(i10));
                return c4510e;
            }
        }).then(NetworkManager.sendPost()).then(new PromisedTask<String, Void, OrgContactList>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.9
            @Override // com.perfectcorp.utility.PromisedTask
            public OrgContactList doInBackground(String str4) {
                if (str4 != null) {
                    return (OrgContactList) C5369g.GSON.fromJson(str4, OrgContactList.class);
                }
                reportError(-2147483645);
                return null;
            }
        });
    }

    public static PromisedTask<?, ?, JSONObject> updateSubscription(final String str, final String str2) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.27
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (TextUtils.isEmpty(NetworkManager.initResponse.subscription.updateToProUser)) {
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.subscription.updateToProUser);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                c4510e.m18125d("data", str2);
                return c4510e;
            }
        }).then(NetworkManager.sendPost()).then(new PromisedTask<String, Void, JSONObject>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.26
            @Override // com.perfectcorp.utility.PromisedTask
            public JSONObject doInBackground(String str3) {
                if (str3 == null) {
                    reportError(-2147483645);
                    return null;
                }
                try {
                    return new JSONObject(str3);
                } catch (JSONException e9) {
                    Log.m18149c("NetworkLive", e9.getMessage());
                    return null;
                }
            }
        });
    }

    public static PromisedTask<?, ?, String> userFaceCollection(final String str) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.23
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (TextUtils.isEmpty(NetworkManager.initResponse.user.faceCollection)) {
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.user.faceCollection);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                return c4510e;
            }
        }).then(NetworkManager.sendGet());
    }

    public static PromisedTask<?, ?, JSONObject> verifySubscription(final String str, final String str2) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.25
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (TextUtils.isEmpty(NetworkManager.initResponse.subscription.getData)) {
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.subscription.getData);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                c4510e.m18125d("data", str2);
                return c4510e;
            }
        }).then(NetworkManager.sendPost()).then(new PromisedTask<String, Void, JSONObject>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkLive.24
            @Override // com.perfectcorp.utility.PromisedTask
            public JSONObject doInBackground(String str3) {
                if (str3 == null) {
                    reportError(-2147483645);
                    return null;
                }
                try {
                    return new JSONObject(str3);
                } catch (JSONException e9) {
                    Log.m18149c("NetworkLive", e9.getMessage());
                    return null;
                }
            }
        });
    }
}
