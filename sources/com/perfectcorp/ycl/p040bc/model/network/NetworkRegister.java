package com.perfectcorp.ycl.p040bc.model.network;

import com.google.android.gms.common.Scopes;
import com.google.common.net.HttpHeaders;
import com.perfectcorp.model.Model;
import com.perfectcorp.utility.C4509d;
import com.perfectcorp.utility.C4510e;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.p040bc.model.Register;
import com.perfectcorp.ycl.p040bc.model.network.NetworkManager;
import java.util.Map;
import p147n5.C5369g;

/* loaded from: classes2.dex */
public class NetworkRegister {
    public static final Long EMPTY_PARAMETER = 0L;

    public static PromisedTask<?, ?, Register.listUserRegisterLiveResult> listRegisterLives(final String str, final int i9, final int i10) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkRegister.4
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (NetworkManager.initResponse.register.listRegisterLives == null) {
                    reportError(NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.register.listRegisterLives);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                c4510e.m18125d("pageIndex", Integer.valueOf(i9));
                c4510e.m18125d("pageSize", Integer.valueOf(i10));
                return c4510e;
            }
        }).then(NetworkManager.sendGet()).then(new PromisedTask<String, Void, Register.listUserRegisterLiveResult>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkRegister.3
            @Override // com.perfectcorp.utility.PromisedTask
            public Register.listUserRegisterLiveResult doInBackground(String str2) {
                if (str2 == null) {
                    reportError(-2147483645);
                    return null;
                }
                Register.listUserRegisterLiveResult listuserregisterliveresult = (Register.listUserRegisterLiveResult) C5369g.GSON.fromJson(str2, Register.listUserRegisterLiveResult.class);
                if (listuserregisterliveresult != null) {
                    return listuserregisterliveresult;
                }
                throw new PromisedTask.CustomErrorException("listLiveUsers jason response parse error", -2147483645);
            }
        });
    }

    public static PromisedTask<?, ?, Register.queryRegistrationFormResult> queryRegistrationForm(final String str, final String str2) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkRegister.6
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (NetworkManager.initResponse.register.queryRegistrationForm == null) {
                    reportError(NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                    return null;
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.register.queryRegistrationForm);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                if (!C4509d.m18120b(str2)) {
                    c4510e.m18125d("liveId", str2);
                }
                return c4510e;
            }
        }).then(NetworkManager.sendGet()).then(new PromisedTask<String, Void, Register.queryRegistrationFormResult>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkRegister.5
            @Override // com.perfectcorp.utility.PromisedTask
            public Register.queryRegistrationFormResult doInBackground(String str3) {
                if (str3 == null) {
                    throw new PromisedTask.CustomErrorException("empty response", -2147483645);
                }
                Register.queryRegistrationFormResult queryregistrationformresult = (Register.queryRegistrationFormResult) Model.parseFromJSON(Register.queryRegistrationFormResult.class, str3);
                if (queryregistrationformresult != null) {
                    return queryregistrationformresult;
                }
                throw new PromisedTask.CustomErrorException("queryRegistrationForm jason response parse error", -2147483645);
            }
        });
    }

    public static PromisedTask<?, ?, Register.registerLiveResult> registerLive(final String str, final String str2, final String str3, final String str4, final Map<String, String> map) {
        return NetworkManager.instance(NetworkManager.LIMITED_TASK_EXECUTOR).then(new PromisedTask<NetworkManager, Void, C4510e>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkRegister.2
            @Override // com.perfectcorp.utility.PromisedTask
            public C4510e doInBackground(NetworkManager networkManager) {
                if (NetworkManager.initResponse.register.registerLive == null) {
                    throw new PromisedTask.CustomErrorException("getLive API is not initialized", NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue());
                }
                C4510e c4510e = new C4510e(NetworkManager.initResponse.register.registerLive);
                if (!C4509d.m18120b(str)) {
                    c4510e.m18124c(HttpHeaders.AUTHORIZATION, "Bearer " + str);
                }
                if (!C4509d.m18120b(str2)) {
                    c4510e.m18125d("liveId", str2);
                }
                if (!C4509d.m18120b(str3)) {
                    c4510e.m18125d(Scopes.EMAIL, str3);
                }
                if (!C4509d.m18120b(str4)) {
                    c4510e.m18125d("displayName", str4);
                }
                for (Map.Entry entry : map.entrySet()) {
                    String str5 = (String) entry.getKey();
                    String str6 = (String) entry.getValue();
                    if (!C4509d.m18120b(str6)) {
                        c4510e.m18125d(str5, str6);
                    }
                }
                return c4510e;
            }
        }).then(NetworkManager.sendPost()).then(new PromisedTask<String, Void, Register.registerLiveResult>() { // from class: com.perfectcorp.ycl.bc.model.network.NetworkRegister.1
            @Override // com.perfectcorp.utility.PromisedTask
            public Register.registerLiveResult doInBackground(String str5) {
                if (str5 == null) {
                    throw new PromisedTask.CustomErrorException("empty response", -2147483645);
                }
                Register.registerLiveResult registerliveresult = (Register.registerLiveResult) Model.parseFromJSON(Register.registerLiveResult.class, str5);
                if (registerliveresult != null) {
                    return registerliveresult;
                }
                throw new PromisedTask.CustomErrorException("registerLive jason response parse error", -2147483645);
            }
        });
    }
}
