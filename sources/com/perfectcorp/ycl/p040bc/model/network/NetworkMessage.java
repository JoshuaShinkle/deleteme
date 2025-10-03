package com.perfectcorp.ycl.p040bc.model.network;

import com.google.common.net.HttpHeaders;
import com.google.firebase.messaging.Constants;
import com.perfectcorp.model.Model;
import com.perfectcorp.utility.C4509d;
import com.perfectcorp.utility.C4510e;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.p040bc.model.Message;
import com.perfectcorp.ycl.p040bc.model.network.Key;
import com.perfectcorp.ycl.p040bc.model.network.NetworkManager;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p047d5.C4677a;
import p147n5.C5369g;
import p147n5.InterfaceC5367e;

/* loaded from: classes2.dex */
public class NetworkMessage {
    private static final int PAGE_SIZE = 20;
    private static final String appName = "android";
    public static String heartbeatUrl;
    public static String likeMsgUrl;
    public static String recommendVersion;
    public static String requiredVersion;
    public static String sendMsgUrl;
    public static String unlikeMsgUrl;

    public interface CallBack<T> {
        void onComplete(T t8);

        void onError();
    }

    public static class HeartBeatResponse extends Model {
        public Integer interval;
    }

    public enum Priority {
        LO("LO"),
        ME("ME"),
        HI("HI");

        private final String val;

        Priority(String str) {
            this.val = str;
        }

        public String getValue() {
            return this.val;
        }
    }

    public static class SendMessageResponse extends Model {
        public String liveId;
        public String messageId;
    }

    public static PromisedTask addAnonymousParticipantName(final String str) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkMessage.15
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (NetworkManager.initResponse.live.addAnonymousDisplayName == null) {
                    reportError(NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.live.addAnonymousDisplayName);
                c4510e.m18125d("liveId", str);
                c4510e.m18125d("uuid", Key.Init.Parameter.uuid);
                c4510e.m18125d("displayName", C4677a.m18708l());
                return c4510e;
            }
        }).then(NetworkManager.sendPost());
    }

    public static PromisedTask<?, ?, String> downloadArchivedMessage(final String str, final String str2, final long j9, final long j10) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkMessage.11
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (str == null) {
                    throw new PromisedTask.CustomErrorException("download Archived Message API is not initialized", NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                }
                C4510e c4510e = new C4510e(str);
                c4510e.m18125d("liveId", str2);
                c4510e.m18125d("endOffset", Long.valueOf(j9));
                c4510e.m18125d("pageToken", Long.valueOf(j10));
                return c4510e;
            }
        }).then(NetworkManager.sendPost()).then(new PromisedTask<String, Void, String>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkMessage.10
            @Override // com.perfectcorp.utility.PromisedTask
            public String doInBackground(String str3) {
                if (str3 != null) {
                    return str3;
                }
                throw new PromisedTask.CustomErrorException("empty response", -2147483645);
            }
        });
    }

    public static void downloadArchivedMessageRecursive(final String str, final String str2, final long j9, long j10, final List<JSONObject> list, final CallBack<List<JSONObject>> callBack) {
        downloadArchivedMessage(str, str2, j9, j10).done(new PromisedTask.AbstractC4504d<String>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkMessage.9
            @Override // com.perfectcorp.utility.PromisedTask
            public void onError(int i9) {
                callBack.onError();
            }

            @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
            public void onDone(String str3) throws JSONException {
                try {
                    JSONObject jSONObject = new JSONObject(str3);
                    long jOptLong = jSONObject.optLong("nextPageToken", 0L);
                    JSONArray jSONArray = jSONObject.getJSONArray("results");
                    for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                        list.add(jSONArray.getJSONObject(i9));
                    }
                    if (jOptLong == 0) {
                        callBack.onComplete(list);
                    } else {
                        NetworkMessage.downloadArchivedMessageRecursive(str, str2, j9, jOptLong, list, callBack);
                    }
                } catch (JSONException unused) {
                    callBack.onError();
                }
            }
        });
    }

    public static PromisedTask<?, ?, HeartBeatResponse> heartBeat(final String str, int i9) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkMessage.6
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (NetworkMessage.heartbeatUrl == null) {
                    reportError(NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkMessage.heartbeatUrl);
                c4510e.m18125d("liveId", str);
                c4510e.m18125d("uuid", Key.Init.Parameter.uuid);
                return c4510e;
            }
        }).then(NetworkManager.sendPost()).then(new PromisedTask<String, Void, HeartBeatResponse>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkMessage.5
            @Override // com.perfectcorp.utility.PromisedTask
            public HeartBeatResponse doInBackground(String str2) {
                if (str2 != null) {
                    return (HeartBeatResponse) Model.parseFromJSON(HeartBeatResponse.class, str2);
                }
                reportError(-2147483645);
                return null;
            }
        });
    }

    public static PromisedTask<?, ?, Message.JoinRoomResponse> joinRoom(final String str, final String str2, final boolean z8) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkMessage.2
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (NetworkManager.initResponse.live.joinLive == null) {
                    reportError(NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.live.joinLive);
                if (!C4509d.m18120b(str)) {
                    if (z8) {
                        c4510e.m18125d("accessToken", str);
                    } else {
                        c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                    }
                }
                c4510e.m18125d("liveId", str2);
                c4510e.m18125d("uuid", Key.Init.Parameter.uuid);
                c4510e.f15957f = "application/x-www-form-urlencoded";
                return c4510e;
            }
        }).then(NetworkManager.sendPost()).then(new PromisedTask<String, Void, Message.JoinRoomResponse>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkMessage.1
            @Override // com.perfectcorp.utility.PromisedTask
            public Message.JoinRoomResponse doInBackground(String str3) {
                if (str3 == null) {
                    reportError(-2147483645);
                    return null;
                }
                Message.JoinRoomResponse joinRoomResponse = (Message.JoinRoomResponse) C5369g.GSON.fromJson(str3, Message.JoinRoomResponse.class);
                if (joinRoomResponse != null) {
                    NetworkMessage.sendMsgUrl = joinRoomResponse.sendUrl;
                    NetworkMessage.heartbeatUrl = joinRoomResponse.heartbeatUrl;
                    NetworkMessage.likeMsgUrl = joinRoomResponse.likeUrl;
                    NetworkMessage.unlikeMsgUrl = joinRoomResponse.unlikeUrl;
                    Message.VersionInfo versionInfo = joinRoomResponse.versionInfo;
                    NetworkMessage.recommendVersion = versionInfo.recommended.f22733android;
                    NetworkMessage.requiredVersion = versionInfo.required.f22733android;
                }
                return joinRoomResponse;
            }
        });
    }

    public static PromisedTask likeMsg(final String str, final long j9, final long j10) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkMessage.7
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (NetworkMessage.likeMsgUrl == null) {
                    reportError(NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkMessage.likeMsgUrl);
                c4510e.m18125d("liveId", str);
                c4510e.m18125d(Constants.FirelogAnalytics.PARAM_MESSAGE_ID, Long.valueOf(j9));
                c4510e.m18125d("uuid", Key.Init.Parameter.uuid);
                long j11 = j10;
                if (j11 > 0) {
                    c4510e.m18125d("userId", Long.valueOf(j11));
                }
                return c4510e;
            }
        }).then(NetworkManager.sendPost());
    }

    public static PromisedTask<?, ?, String> listAssistant(final String str, final String str2, final long j9) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkMessage.14
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (NetworkManager.initResponse.assistant.listCandidate == null) {
                    reportError(NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.assistant.listCandidate);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                c4510e.m18125d("liveId", str2);
                c4510e.m18125d("pageIndex", Long.valueOf(j9));
                c4510e.m18125d("pageSize", 20);
                return c4510e;
            }
        }).then(NetworkManager.sendPost()).then(new PromisedTask<String, Void, String>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkMessage.13
            @Override // com.perfectcorp.utility.PromisedTask
            public String doInBackground(String str3) {
                if (str3 != null) {
                    return str3;
                }
                throw new PromisedTask.CustomErrorException("empty response", -2147483645);
            }
        });
    }

    public static void listAssistantRecursive(final String str, final String str2, long j9, final List<JSONObject> list, final CallBack<List<JSONObject>> callBack) {
        listAssistant(str, str2, j9).done(new PromisedTask.AbstractC4504d<String>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkMessage.12
            @Override // com.perfectcorp.utility.PromisedTask
            public void onError(int i9) {
                callBack.onError();
            }

            @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
            public void onDone(String str3) throws JSONException {
                try {
                    JSONObject jSONObject = new JSONObject(str3);
                    int iOptInt = jSONObject.optInt("totalSize", 0);
                    JSONArray jSONArray = jSONObject.getJSONArray("results");
                    for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                        list.add(jSONArray.getJSONObject(i9));
                    }
                    if (((int) Math.ceil((iOptInt * 1.0f) / list.size())) < 2) {
                        callBack.onComplete(list);
                    } else {
                        NetworkMessage.listAssistantRecursive(str, str2, list.size() / 20, list, callBack);
                    }
                } catch (JSONException unused) {
                    callBack.onError();
                }
            }
        });
    }

    public static PromisedTask<?, ?, SendMessageResponse> sendMsg(final String str, final String str2, final InterfaceC5367e interfaceC5367e) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkMessage.4
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (NetworkMessage.sendMsgUrl == null) {
                    reportError(NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkMessage.sendMsgUrl);
                c4510e.m18125d("liveId", str);
                c4510e.m18125d("type", str2);
                c4510e.m18125d("message", C5369g.GSON.toJson(interfaceC5367e));
                c4510e.f15957f = "application/x-www-form-urlencoded";
                return c4510e;
            }
        }).then(NetworkManager.sendPost()).then(new PromisedTask<String, Void, SendMessageResponse>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkMessage.3
            @Override // com.perfectcorp.utility.PromisedTask
            public SendMessageResponse doInBackground(String str3) {
                if (str3 != null) {
                    return (SendMessageResponse) Model.parseFromJSON(SendMessageResponse.class, str3);
                }
                reportError(-2147483645);
                return null;
            }
        });
    }

    public static PromisedTask unlikeMsg(final String str, final long j9, final long j10) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkMessage.8
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (NetworkMessage.unlikeMsgUrl == null) {
                    reportError(NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkMessage.unlikeMsgUrl);
                c4510e.m18125d("liveId", str);
                c4510e.m18125d(Constants.FirelogAnalytics.PARAM_MESSAGE_ID, Long.valueOf(j9));
                c4510e.m18125d("uuid", Key.Init.Parameter.uuid);
                long j11 = j10;
                if (j11 > 0) {
                    c4510e.m18125d("userId", Long.valueOf(j11));
                }
                return c4510e;
            }
        }).then(NetworkManager.sendPost());
    }
}
