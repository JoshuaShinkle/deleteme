package com.android.vending.billing.util;

import android.app.Activity;
import android.content.Context;
import android.util.Pair;
import com.android.billingclient.api.AbstractC0763d;
import com.android.billingclient.api.C0754a;
import com.android.billingclient.api.C0772g;
import com.android.billingclient.api.C0774h;
import com.android.billingclient.api.C0784m;
import com.android.billingclient.api.C0786n;
import com.android.billingclient.api.InterfaceC0757b;
import com.android.billingclient.api.InterfaceC0769f;
import com.android.billingclient.api.InterfaceC0780k;
import com.android.billingclient.api.InterfaceC0782l;
import com.android.billingclient.api.InterfaceC0788o;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.cyberlink.you.Globals;
import com.cyberlink.you.utility.ULogUtility;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.p040bc.model.network.NetworkLive;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p074g2.C4831a;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class BillingManager implements InterfaceC0782l {
    private AbstractC0763d billingClient;
    private BillingUpdatesListener mBillingUpdatesListener;
    private final String TAG = "BillingManager";
    private boolean serviceConnected = false;
    private boolean isInAppReady = false;
    private boolean isSubscriptionReady = false;

    public interface BillingUpdatesListener {
        void onError(ErrorMessage errorMessage);

        void onPurchasesEmpty();

        void onPurchasesOwned(List<Purchase> list, List<Pair<Purchase, ErrorMessage>> list2, boolean z8);

        void onPurchasesVerify();

        void onSkuDetailsResponse(List<SkuDetails> list);
    }

    public enum ErrorMessage {
        CONNECT_FAIL,
        GOOGLE_UPGRADE_FAIL,
        ALREADY_OWNED_PRODUCT,
        UPDATE_ERROR,
        VERIFY_ERROR
    }

    public enum Period {
        MONTH("P1M"),
        YEAR("P1Y");

        private final String name;

        Period(String str) {
            this.name = str;
        }

        public String getPeriod() {
            return this.name;
        }
    }

    public interface UpdateProductListener {
        void onFail(ErrorMessage errorMessage);

        void onSuccess();
    }

    public interface VerifyProductListener {
        void onFail(ErrorMessage errorMessage);

        void onSuccess(JSONObject jSONObject);
    }

    public BillingManager(Context context) {
        this.billingClient = AbstractC0763d.m3670f(context).m3680b().m3681c(this).m3679a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void acknowledgePurchase(final Purchase purchase, final UpdateProductListener updateProductListener) {
        if (purchase.m3645f()) {
            return;
        }
        this.billingClient.mo3671a(C0754a.m3660b().m3664b(purchase.m3642c()).m3663a(), new InterfaceC0757b() { // from class: com.android.vending.billing.util.c
            @Override // com.android.billingclient.api.InterfaceC0757b
            /* renamed from: a */
            public final void mo3668a(C0774h c0774h) {
                this.f3662a.lambda$acknowledgePurchase$2(purchase, updateProductListener, c0774h);
            }
        });
    }

    private static String getProTypeByCountry() {
        String country = Locale.getDefault().getCountry();
        for (String str : C4831a.m19169c("subscription_id_sea_country").split(",")) {
            if (str.equals(country)) {
                return "sea";
            }
        }
        return "other";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSubscriptionProProduct(String str) {
        return getSubscriptionProProductId().contains(str);
    }

    private boolean isSubscriptionSupported() {
        AbstractC0763d abstractC0763d = this.billingClient;
        if (abstractC0763d == null) {
            return false;
        }
        if (abstractC0763d.mo3673c("subscriptions").m3760b() == 0) {
            return true;
        }
        this.mBillingUpdatesListener.onError(ErrorMessage.CONNECT_FAIL);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$acknowledgePurchase$2(Purchase purchase, UpdateProductListener updateProductListener, C0774h c0774h) {
        if (c0774h.m3760b() == 0) {
            if (getAllSubscriptionProProductId().contains(purchase.m3644e().get(0))) {
                notifyToUpdateSubscription(purchase, updateProductListener);
            }
        } else {
            updateProductListener.onFail(ErrorMessage.UPDATE_ERROR);
            ULogUtility.m16670f("BillingManager", "purchase product id:" + purchase.m3644e().get(0) + " onAcknowledge fail");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$processPurchases$1(final List list, final boolean z8) {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            final Purchase purchase = (Purchase) it.next();
            if (purchase.m3641b() == 1) {
                verify(purchase, new VerifyProductListener() { // from class: com.android.vending.billing.util.BillingManager.5
                    @Override // com.android.vending.billing.util.BillingManager.VerifyProductListener
                    public void onFail(ErrorMessage errorMessage) {
                        arrayList2.add(new Pair(purchase, errorMessage));
                        if (arrayList.size() + arrayList2.size() == list.size()) {
                            BillingManager.this.mBillingUpdatesListener.onPurchasesOwned(arrayList, arrayList2, z8);
                        }
                        ULogUtility.m16670f("BillingManager", "purchase product id:" + purchase.m3644e().get(0) + " verify fail");
                    }

                    @Override // com.android.vending.billing.util.BillingManager.VerifyProductListener
                    public void onSuccess(JSONObject jSONObject) {
                        UpdateProductListener updateProductListener = new UpdateProductListener() { // from class: com.android.vending.billing.util.BillingManager.5.1
                            @Override // com.android.vending.billing.util.BillingManager.UpdateProductListener
                            public void onFail(ErrorMessage errorMessage) {
                                arrayList2.add(new Pair(purchase, errorMessage));
                                if (arrayList.size() + arrayList2.size() == list.size()) {
                                    BillingUpdatesListener billingUpdatesListener = BillingManager.this.mBillingUpdatesListener;
                                    C08165 c08165 = C08165.this;
                                    billingUpdatesListener.onPurchasesOwned(arrayList, arrayList2, z8);
                                }
                                ULogUtility.m16670f("BillingManager", "purchase product id:" + purchase.m3644e().get(0) + " notifyToUpdateSubscription fail");
                            }

                            @Override // com.android.vending.billing.util.BillingManager.UpdateProductListener
                            public void onSuccess() {
                                C08165 c08165 = C08165.this;
                                arrayList.add(purchase);
                                if (arrayList.size() + arrayList2.size() == list.size()) {
                                    BillingUpdatesListener billingUpdatesListener = BillingManager.this.mBillingUpdatesListener;
                                    C08165 c081652 = C08165.this;
                                    billingUpdatesListener.onPurchasesOwned(arrayList, arrayList2, z8);
                                }
                            }
                        };
                        if (jSONObject.optInt("acknowledgementState", 0) == 0) {
                            BillingManager.this.acknowledgePurchase(purchase, updateProductListener);
                            return;
                        }
                        if (BillingManager.this.getAllSubscriptionProProductId().contains(purchase.m3644e().get(0))) {
                            if (BillingManager.this.isSubscriptionProProduct(purchase.m3644e().get(0))) {
                                BillingManager.this.notifyToUpdateSubscription(purchase, updateProductListener);
                                return;
                            }
                            ULogUtility.m16670f("BillingManager", "purchase product id:" + purchase.m3644e().get(0) + " verify own product");
                            BillingManager.this.mBillingUpdatesListener.onError(ErrorMessage.ALREADY_OWNED_PRODUCT);
                        }
                    }
                });
            } else if (purchase.m3641b() == 2) {
                arrayList2.add(new Pair(purchase, ErrorMessage.GOOGLE_UPGRADE_FAIL));
                if (arrayList.size() + arrayList2.size() == list.size()) {
                    this.mBillingUpdatesListener.onPurchasesOwned(arrayList, arrayList2, z8);
                }
                ULogUtility.m16670f("BillingManager", "purchase product id:" + purchase.m3644e().get(0) + " PENDING");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$querySkuDetailsAsync$0(C0774h c0774h, List list) {
        if (c0774h.m3760b() != 0) {
            this.mBillingUpdatesListener.onError(ErrorMessage.CONNECT_FAIL);
            ULogUtility.m16670f("BillingManager", "querySkuDetailsAsync fail");
        } else if (!list.isEmpty()) {
            this.mBillingUpdatesListener.onSkuDetailsResponse(list);
        } else {
            this.mBillingUpdatesListener.onError(ErrorMessage.CONNECT_FAIL);
            ULogUtility.m16670f("BillingManager", "querySkuDetailsAsync fail on skuDetailsList empty");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyToUpdateSubscription(final Purchase purchase, final UpdateProductListener updateProductListener) {
        NetworkLive.updateSubscription(Globals.m7388i0().m7506X(), purchase.m3640a()).done(new PromisedTask.AbstractC4504d<JSONObject>() { // from class: com.android.vending.billing.util.BillingManager.7
            @Override // com.perfectcorp.utility.PromisedTask
            public void onError(int i9) {
                super.onError(i9);
                if (i9 == 409) {
                    updateProductListener.onFail(ErrorMessage.ALREADY_OWNED_PRODUCT);
                } else {
                    updateProductListener.onFail(ErrorMessage.UPDATE_ERROR);
                }
                ULogUtility.m16670f("BillingManager", "notifyToUpdateSubscription sku:" + purchase.m3644e().get(0) + " errorCode:" + i9);
            }

            @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
            public void onDone(JSONObject jSONObject) {
                if (BillingManager.this.updateProSuccess(jSONObject)) {
                    updateProductListener.onSuccess();
                    return;
                }
                updateProductListener.onFail(ErrorMessage.UPDATE_ERROR);
                ULogUtility.m16670f("BillingManager", "notifyToUpdateSubscription sku:" + purchase.m3644e().get(0) + " updateProSuccess false");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryPurchasesAsync() {
        BillingUpdatesListener billingUpdatesListener = this.mBillingUpdatesListener;
        if (billingUpdatesListener == null) {
            return;
        }
        if (!this.serviceConnected) {
            billingUpdatesListener.onError(ErrorMessage.CONNECT_FAIL);
            return;
        }
        this.isInAppReady = false;
        this.isSubscriptionReady = false;
        final ArrayList arrayList = new ArrayList();
        this.billingClient.mo3676g(C0784m.m3766a().m3770b("inapp").m3769a(), new InterfaceC0780k() { // from class: com.android.vending.billing.util.BillingManager.3
            @Override // com.android.billingclient.api.InterfaceC0780k
            public void onQueryPurchasesResponse(C0774h c0774h, List<Purchase> list) {
                if (c0774h.m3760b() == 0) {
                    arrayList.addAll(list);
                }
                BillingManager.this.isInAppReady = true;
                if (BillingManager.this.isSubscriptionReady) {
                    BillingManager.this.processPurchases(arrayList, false);
                }
            }
        });
        if (isSubscriptionSupported()) {
            this.billingClient.mo3676g(C0784m.m3766a().m3770b("subs").m3769a(), new InterfaceC0780k() { // from class: com.android.vending.billing.util.BillingManager.4
                @Override // com.android.billingclient.api.InterfaceC0780k
                public void onQueryPurchasesResponse(C0774h c0774h, List<Purchase> list) {
                    if (c0774h.m3760b() == 0) {
                        arrayList.addAll(list);
                    } else {
                        BillingManager.this.mBillingUpdatesListener.onError(ErrorMessage.CONNECT_FAIL);
                    }
                    BillingManager.this.isSubscriptionReady = true;
                    if (BillingManager.this.isInAppReady) {
                        BillingManager.this.processPurchases(arrayList, false);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void querySkuDetailsAsync(String str, List<String> list) {
        BillingUpdatesListener billingUpdatesListener = this.mBillingUpdatesListener;
        if (billingUpdatesListener == null) {
            return;
        }
        if (!this.serviceConnected) {
            billingUpdatesListener.onError(ErrorMessage.CONNECT_FAIL);
        } else {
            this.billingClient.mo3677h(C0786n.m3773c().m3779b(list).m3780c(str).m3778a(), new InterfaceC0788o() { // from class: com.android.vending.billing.util.b
                @Override // com.android.billingclient.api.InterfaceC0788o
                /* renamed from: a */
                public final void mo3783a(C0774h c0774h, List list2) {
                    this.f3661a.lambda$querySkuDetailsAsync$0(c0774h, list2);
                }
            });
        }
    }

    private void startServiceConnection() {
        if (this.billingClient.mo3674d()) {
            return;
        }
        this.billingClient.mo3678i(new InterfaceC0769f() { // from class: com.android.vending.billing.util.BillingManager.1
            @Override // com.android.billingclient.api.InterfaceC0769f
            public void onBillingServiceDisconnected() {
                BillingManager.this.serviceConnected = false;
            }

            @Override // com.android.billingclient.api.InterfaceC0769f
            public void onBillingSetupFinished(C0774h c0774h) {
                if (c0774h.m3760b() == 0) {
                    BillingManager.this.serviceConnected = true;
                    BillingManager.this.queryData();
                    return;
                }
                BillingManager.this.serviceConnected = false;
                if (BillingManager.this.mBillingUpdatesListener == null) {
                    return;
                }
                BillingManager.this.mBillingUpdatesListener.onError(ErrorMessage.CONNECT_FAIL);
                ULogUtility.m16670f("BillingManager", "onBillingSetupFinished fail");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updateProSuccess(JSONObject jSONObject) throws JSONException {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(FirebaseAnalytics.Param.ITEMS);
            if (jSONArray.length() > 0) {
                return "PRO".equals(jSONArray.getJSONObject(0).getString("plan"));
            }
            return false;
        } catch (JSONException e9) {
            ULogUtility.m16670f("BillingManager", "updateProSuccess JSONException:" + e9.getMessage());
            return false;
        }
    }

    private void verify(final Purchase purchase, final VerifyProductListener verifyProductListener) {
        NetworkLive.verifySubscription(Globals.m7388i0().m7506X(), purchase.m3640a()).done(new PromisedTask.AbstractC4504d<JSONObject>() { // from class: com.android.vending.billing.util.BillingManager.6
            @Override // com.perfectcorp.utility.PromisedTask
            public void onError(int i9) {
                super.onError(i9);
                verifyProductListener.onFail(ErrorMessage.VERIFY_ERROR);
                ULogUtility.m16670f("BillingManager", "verify sku:" + purchase.m3644e().get(0) + " errorCode:" + i9);
            }

            @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
            public void onDone(JSONObject jSONObject) {
                verifyProductListener.onSuccess(jSONObject);
            }
        });
    }

    public void destroy() {
        this.serviceConnected = false;
        AbstractC0763d abstractC0763d = this.billingClient;
        if (abstractC0763d != null) {
            abstractC0763d.mo3672b();
            this.billingClient = null;
        }
    }

    public List<String> getAllSubscriptionProProductId() {
        ArrayList arrayList = new ArrayList();
        String[] strArrSplit = C4831a.m19169c("subscription_id_other_android").split(",");
        String[] strArrSplit2 = C4831a.m19169c("subscription_id_sea_android").split(",");
        Collections.addAll(arrayList, strArrSplit);
        Collections.addAll(arrayList, strArrSplit2);
        return arrayList;
    }

    public List<String> getSubscriptionProProductId() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getSubscriptionProProductId(Period.MONTH));
        arrayList.add(getSubscriptionProProductId(Period.YEAR));
        return arrayList;
    }

    public void launchBillingFlow(Activity activity, SkuDetails skuDetails) {
        BillingUpdatesListener billingUpdatesListener = this.mBillingUpdatesListener;
        if (billingUpdatesListener == null) {
            return;
        }
        if (!this.serviceConnected) {
            billingUpdatesListener.onError(ErrorMessage.CONNECT_FAIL);
        } else {
            this.billingClient.mo3675e(activity, C0772g.m3724a().m3743b(skuDetails).m3742a());
        }
    }

    @Override // com.android.billingclient.api.InterfaceC0782l
    public void onPurchasesUpdated(C0774h c0774h, List<Purchase> list) {
        if (c0774h.m3760b() != 0 || list == null) {
            if (c0774h.m3760b() == 7) {
                this.mBillingUpdatesListener.onError(ErrorMessage.ALREADY_OWNED_PRODUCT);
                ULogUtility.m16670f("BillingManager", "onPurchasesUpdated owned product");
                return;
            } else {
                if (c0774h.m3760b() != 1) {
                    this.mBillingUpdatesListener.onError(ErrorMessage.GOOGLE_UPGRADE_FAIL);
                    ULogUtility.m16670f("BillingManager", "onPurchasesUpdated fail");
                    return;
                }
                return;
            }
        }
        Iterator<Purchase> it = list.iterator();
        while (it.hasNext()) {
            ULogUtility.m16670f("BillingManager", "onPurchasesUpdated click purchase:" + it.next().m3644e().get(0));
        }
        processPurchases(list, true);
    }

    public void processPurchases(final List<Purchase> list, final boolean z8) {
        if (this.mBillingUpdatesListener == null) {
            return;
        }
        if (list.isEmpty()) {
            this.mBillingUpdatesListener.onPurchasesEmpty();
        } else {
            this.mBillingUpdatesListener.onPurchasesVerify();
            C6385v.m24526d(new Runnable() { // from class: com.android.vending.billing.util.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3658b.lambda$processPurchases$1(list, z8);
                }
            });
        }
    }

    public void queryData() {
        if (this.mBillingUpdatesListener == null) {
            return;
        }
        if (this.serviceConnected) {
            new Thread() { // from class: com.android.vending.billing.util.BillingManager.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    BillingManager billingManager = BillingManager.this;
                    billingManager.querySkuDetailsAsync("subs", billingManager.getSubscriptionProProductId());
                    BillingManager.this.queryPurchasesAsync();
                }
            }.start();
        } else {
            startServiceConnection();
        }
    }

    public void setBillingUpdatesListener(BillingUpdatesListener billingUpdatesListener) {
        this.mBillingUpdatesListener = billingUpdatesListener;
    }

    private String getSubscriptionProProductId(Period period) {
        String proTypeByCountry = getProTypeByCountry();
        if ("other".equals(proTypeByCountry)) {
            String[] strArrSplit = C4831a.m19169c("subscription_id_other_android").split(",");
            if (Period.MONTH == period) {
                return strArrSplit[0];
            }
            return Period.YEAR == period ? strArrSplit[1] : "";
        }
        if (!"sea".equals(proTypeByCountry)) {
            return "";
        }
        String[] strArrSplit2 = C4831a.m19169c("subscription_id_sea_android").split(",");
        if (Period.MONTH == period) {
            return strArrSplit2[0];
        }
        return Period.YEAR == period ? strArrSplit2[1] : "";
    }
}
