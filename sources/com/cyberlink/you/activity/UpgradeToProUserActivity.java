package com.cyberlink.you.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.android.vending.billing.util.BillingManager;
import com.cyberlink.meeting.doserver.C1260a;
import com.cyberlink.meeting.model.SubscriptionInfo;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.feedback.PromisedTask;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import p086h4.C5006j;
import p116k4.C5182t;
import p189s0.AbstractC6243a;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class UpgradeToProUserActivity extends BaseActivity {

    /* renamed from: d */
    public BillingManager f9359d;

    /* renamed from: f */
    public TextView f9361f;

    /* renamed from: g */
    public TextView f9362g;

    /* renamed from: h */
    public ViewPager f9363h;

    /* renamed from: i */
    public C5006j f9364i;

    /* renamed from: j */
    public Button f9365j;

    /* renamed from: k */
    public DialogC3133q f9366k;

    /* renamed from: l */
    public View f9367l;

    /* renamed from: m */
    public View f9368m;

    /* renamed from: c */
    public final String f9358c = "UpgradeToProUser";

    /* renamed from: e */
    public final List<SkuDetails> f9360e = new ArrayList();

    /* renamed from: n */
    public boolean f9369n = false;

    /* renamed from: o */
    public boolean f9370o = false;

    /* renamed from: p */
    public boolean f9371p = false;

    /* renamed from: q */
    public final String f9372q = "productId";

    /* renamed from: r */
    public final String f9373r = "period";

    /* renamed from: s */
    public boolean f9374s = false;

    /* renamed from: t */
    public final ViewPager.InterfaceC0557j f9375t = new C1803b();

    /* renamed from: com.cyberlink.you.activity.UpgradeToProUserActivity$a */
    public class C1802a implements BillingManager.BillingUpdatesListener {
        public C1802a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: i */
        public /* synthetic */ void m10559i(Dialog dialog, View view) {
            dialog.dismiss();
            UpgradeToProUserActivity.this.finish();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: j */
        public /* synthetic */ void m10560j(Dialog dialog, View view) {
            dialog.dismiss();
            UpgradeToProUserActivity.this.finish();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: k */
        public /* synthetic */ void m10561k(BillingManager.ErrorMessage errorMessage) throws Resources.NotFoundException {
            if (UpgradeToProUserActivity.this.f9374s) {
                return;
            }
            UpgradeToProUserActivity.this.f9374s = true;
            if (BillingManager.ErrorMessage.CONNECT_FAIL == errorMessage) {
                final Dialog dialogM16385d = C3123g.m16385d(UpgradeToProUserActivity.this, UpgradeToProUserActivity.this.getResources().getString(R.string.connect_play_store_fail_title), UpgradeToProUserActivity.this.getResources().getString(R.string.connect_play_store_fail_content));
                dialogM16385d.setCancelable(false);
                TextView textView = (TextView) dialogM16385d.findViewById(R.id.v_btn);
                textView.setText(R.string.ok);
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.qj
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f11122b.m10559i(dialogM16385d, view);
                    }
                });
                dialogM16385d.show();
                return;
            }
            if (BillingManager.ErrorMessage.GOOGLE_UPGRADE_FAIL != errorMessage) {
                if (BillingManager.ErrorMessage.ALREADY_OWNED_PRODUCT == errorMessage) {
                    UpgradeToProUserActivity.this.m10550z0();
                    return;
                }
                return;
            }
            final Dialog dialogM16385d2 = C3123g.m16385d(UpgradeToProUserActivity.this, UpgradeToProUserActivity.this.getResources().getString(R.string.upgrade_pro_fail_title), UpgradeToProUserActivity.this.getResources().getString(R.string.upgrade_pro_fail_content));
            dialogM16385d2.setCancelable(false);
            TextView textView2 = (TextView) dialogM16385d2.findViewById(R.id.v_btn);
            textView2.setText(R.string.ok);
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.rj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f11152b.m10560j(dialogM16385d2, view);
                }
            });
            dialogM16385d2.show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: l */
        public /* synthetic */ void m10562l(Dialog dialog, View view) {
            dialog.dismiss();
            UpgradeToProUserActivity.this.finish();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: m */
        public /* synthetic */ void m10563m(Dialog dialog, List list, View view) {
            dialog.dismiss();
            UpgradeToProUserActivity.this.f9359d.processPurchases(list, true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: n */
        public /* synthetic */ void m10564n(List list, boolean z8, List list2) throws Resources.NotFoundException {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Purchase purchase = (Purchase) it.next();
                if (UpgradeToProUserActivity.this.f9359d.getAllSubscriptionProProductId().contains(purchase.m3644e().get(0))) {
                    if (UpgradeToProUserActivity.this.f9359d.getSubscriptionProProductId().contains(purchase.m3644e().get(0))) {
                        Globals.m7388i0().m7476Q3(true);
                        SkuDetails skuDetailsM10541d0 = UpgradeToProUserActivity.this.m10541d0("productId", purchase.m3644e().get(0));
                        if (skuDetailsM10541d0 != null && !TextUtils.isEmpty(skuDetailsM10541d0.m3647a())) {
                            ULogUtility.m16670f("UpgradeToProUser", "onPurchasesOwned UseProFreeTrialBefore product id:" + purchase.m3644e().get(0));
                            Globals.m7388i0().m7559i4();
                        }
                        if (z8) {
                            UpgradeToProUserActivity.this.finish();
                        } else {
                            ULogUtility.m16670f("UpgradeToProUser", "onPurchasesOwned successPurchases owned product id:" + purchase.m3644e().get(0));
                            onError(BillingManager.ErrorMessage.ALREADY_OWNED_PRODUCT);
                        }
                    } else {
                        ULogUtility.m16670f("UpgradeToProUser", "onPurchasesOwned successPurchases owned product id:" + purchase.m3644e().get(0));
                        onError(BillingManager.ErrorMessage.ALREADY_OWNED_PRODUCT);
                    }
                }
            }
            final ArrayList arrayList = new ArrayList();
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                Pair pair = (Pair) it2.next();
                Purchase purchase2 = (Purchase) pair.first;
                BillingManager.ErrorMessage errorMessage = (BillingManager.ErrorMessage) pair.second;
                if (UpgradeToProUserActivity.this.f9359d.getAllSubscriptionProProductId().contains(purchase2.m3644e().get(0))) {
                    if (errorMessage == BillingManager.ErrorMessage.ALREADY_OWNED_PRODUCT) {
                        ULogUtility.m16670f("UpgradeToProUser", "onPurchasesOwned failPurchases owned product id:" + purchase2.m3644e().get(0));
                        onError(errorMessage);
                        return;
                    }
                    if (UpgradeToProUserActivity.this.f9359d.getSubscriptionProProductId().contains(purchase2.m3644e().get(0))) {
                        arrayList.add(purchase2);
                    }
                }
            }
            if (arrayList.isEmpty() || !z8) {
                return;
            }
            final Dialog dialogM16386e = C3123g.m16386e(UpgradeToProUserActivity.this, UpgradeToProUserActivity.this.getResources().getString(R.string.upgrade_pro_fail_title), UpgradeToProUserActivity.this.getResources().getString(R.string.upgrade_pro_fail_content), false);
            dialogM16386e.setCancelable(false);
            TextView textView = (TextView) dialogM16386e.findViewById(R.id.h_btn_cancel);
            textView.setText(R.string.cancel);
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.oj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f11019b.m10562l(dialogM16386e, view);
                }
            });
            TextView textView2 = (TextView) dialogM16386e.findViewById(R.id.h_btn_ok);
            textView2.setText(R.string.retry);
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.pj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f11049b.m10563m(dialogM16386e, arrayList, view);
                }
            });
            dialogM16386e.show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: o */
        public /* synthetic */ void m10565o() {
            if (UpgradeToProUserActivity.this.f9366k.isShowing()) {
                return;
            }
            UpgradeToProUserActivity.this.f9366k.show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: p */
        public /* synthetic */ void m10566p(List list) {
            String str;
            TextView textView = (TextView) UpgradeToProUserActivity.this.f9367l.findViewById(R.id.monthPrice);
            TextView textView2 = (TextView) UpgradeToProUserActivity.this.f9368m.findViewById(R.id.perMonthPrice);
            TextView textView3 = (TextView) UpgradeToProUserActivity.this.f9368m.findViewById(R.id.yearPrice);
            List<String> subscriptionProProductId = UpgradeToProUserActivity.this.f9359d.getSubscriptionProProductId();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                SkuDetails skuDetails = (SkuDetails) it.next();
                ULogUtility.m16670f("UpgradeToProUser", "onSkuDetailsResponse price:" + skuDetails.m3649c() + "  price_amount_micros:" + skuDetails.m3650d());
                if (!subscriptionProProductId.contains(skuDetails.m3651e())) {
                    return;
                }
                UpgradeToProUserActivity.this.f9360e.add(skuDetails);
                if (BillingManager.Period.MONTH.getPeriod().equals(skuDetails.m3652f())) {
                    textView.setText(String.format(UpgradeToProUserActivity.this.getResources().getString(R.string.upgrade_pro_monthly_price_v2), skuDetails.m3649c()));
                    if (UpgradeToProUserActivity.this.f9367l.findViewById(R.id.radio_month_pro).isSelected()) {
                        if (UpgradeToProUserActivity.this.m10542e0(skuDetails)) {
                            UpgradeToProUserActivity.this.f9365j.setText(R.string.upgrade_pro_no_free_trial);
                            UpgradeToProUserActivity.this.f9362g.setVisibility(8);
                        } else {
                            UpgradeToProUserActivity.this.f9365j.setText(R.string.upgrade_pro_free_trial);
                            UpgradeToProUserActivity.this.f9362g.setText(String.format(UpgradeToProUserActivity.this.getResources().getString(R.string.upgrade_pro_monthly_price), skuDetails.m3649c()));
                        }
                    }
                } else if (BillingManager.Period.YEAR.getPeriod().equals(skuDetails.m3652f())) {
                    try {
                        str = String.format(UpgradeToProUserActivity.this.getResources().getString(R.string.upgrade_pro_yearly_per_month_price), UpgradeToProUserActivity.this.m10540c0(skuDetails));
                    } catch (Exception unused) {
                        ULogUtility.m16670f("UpgradeToProUser", "SkuDetails:" + skuDetails.m3648b());
                        str = "";
                    }
                    textView2.setText(str);
                    textView3.setText(String.format(UpgradeToProUserActivity.this.getResources().getString(R.string.upgrade_pro_yearly_price), skuDetails.m3649c()));
                    if (UpgradeToProUserActivity.this.f9368m.findViewById(R.id.radio_year_pro).isSelected()) {
                        if (UpgradeToProUserActivity.this.m10542e0(skuDetails)) {
                            UpgradeToProUserActivity.this.f9365j.setText(R.string.upgrade_pro_no_free_trial);
                            UpgradeToProUserActivity.this.f9362g.setVisibility(8);
                        } else {
                            UpgradeToProUserActivity.this.f9365j.setText(R.string.upgrade_pro_free_trial);
                            UpgradeToProUserActivity.this.f9362g.setText(String.format(UpgradeToProUserActivity.this.getResources().getString(R.string.upgrade_pro_free_yearly_price), skuDetails.m3649c()));
                        }
                    }
                }
            }
        }

        @Override // com.android.vending.billing.util.BillingManager.BillingUpdatesListener
        public void onError(final BillingManager.ErrorMessage errorMessage) {
            UpgradeToProUserActivity.this.f9371p = true;
            UpgradeToProUserActivity.this.m10544i0();
            UpgradeToProUserActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.kj
                @Override // java.lang.Runnable
                public final void run() throws Resources.NotFoundException {
                    this.f10833b.m10561k(errorMessage);
                }
            });
        }

        @Override // com.android.vending.billing.util.BillingManager.BillingUpdatesListener
        public void onPurchasesEmpty() {
            UpgradeToProUserActivity.this.f9370o = true;
            UpgradeToProUserActivity.this.m10544i0();
        }

        @Override // com.android.vending.billing.util.BillingManager.BillingUpdatesListener
        public void onPurchasesOwned(final List<Purchase> list, final List<Pair<Purchase, BillingManager.ErrorMessage>> list2, final boolean z8) {
            UpgradeToProUserActivity.this.f9370o = true;
            UpgradeToProUserActivity.this.m10544i0();
            UpgradeToProUserActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.lj
                @Override // java.lang.Runnable
                public final void run() throws Resources.NotFoundException {
                    this.f10865b.m10564n(list, z8, list2);
                }
            });
        }

        @Override // com.android.vending.billing.util.BillingManager.BillingUpdatesListener
        public void onPurchasesVerify() {
            UpgradeToProUserActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.mj
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10906b.m10565o();
                }
            });
        }

        @Override // com.android.vending.billing.util.BillingManager.BillingUpdatesListener
        public void onSkuDetailsResponse(final List<SkuDetails> list) {
            UpgradeToProUserActivity.this.f9369n = true;
            UpgradeToProUserActivity.this.m10544i0();
            UpgradeToProUserActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.nj
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10932b.m10566p(list);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.UpgradeToProUserActivity$b */
    public class C1803b implements ViewPager.InterfaceC0557j {
        public C1803b() {
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageScrollStateChanged(int i9) {
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageScrolled(int i9, float f9, int i10) {
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageSelected(int i9) {
            UpgradeToProUserActivity.this.f9364i.m19451b(i9);
            if (i9 == 0) {
                UpgradeToProUserActivity.this.f9361f.setText(R.string.upgrade_pro_content);
                UpgradeToProUserActivity.this.findViewById(R.id.pro_content_time).setVisibility(0);
            } else if (i9 == 1) {
                UpgradeToProUserActivity.this.f9361f.setText(R.string.upgrade_pro_content2);
                UpgradeToProUserActivity.this.findViewById(R.id.pro_content_time).setVisibility(4);
            } else {
                UpgradeToProUserActivity.this.f9361f.setText(R.string.upgrade_pro_content3);
                UpgradeToProUserActivity.this.findViewById(R.id.pro_content_time).setVisibility(4);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.UpgradeToProUserActivity$c */
    public class C1804c extends PromisedTask.AbstractC3021b<SubscriptionInfo> {
        public C1804c() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: s */
        public /* synthetic */ void m10568s(Dialog dialog, View view) {
            dialog.dismiss();
            UpgradeToProUserActivity.this.finish();
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) throws Resources.NotFoundException {
            UpgradeToProUserActivity.this.f9371p = true;
            UpgradeToProUserActivity.this.m10544i0();
            final Dialog dialogM16385d = C3123g.m16385d(UpgradeToProUserActivity.this, UpgradeToProUserActivity.this.getResources().getString(R.string.upgrade_pro_fail_title), UpgradeToProUserActivity.this.getResources().getString(R.string.upgrade_pro_fail_content));
            dialogM16385d.setCancelable(false);
            TextView textView = (TextView) dialogM16385d.findViewById(R.id.v_btn);
            textView.setText(R.string.ok);
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.sj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f11355b.m10568s(dialogM16385d, view);
                }
            });
            dialogM16385d.show();
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: t, reason: merged with bridge method [inline-methods] */
        public void mo5703q(SubscriptionInfo subscriptionInfo) {
            boolean zEqualsIgnoreCase = "pro".equalsIgnoreCase(subscriptionInfo.plan);
            Globals.m7388i0().m7476Q3(zEqualsIgnoreCase);
            Globals.m7388i0().m7642x3(Integer.parseInt(subscriptionInfo.attendeeCapacity));
            Globals.m7388i0().m7647y3(Integer.parseInt(subscriptionInfo.maximumLength));
            Globals.m7388i0().m7646y2(subscriptionInfo.androidOnHoldProductIds);
            if (zEqualsIgnoreCase) {
                UpgradeToProUserActivity.this.f9371p = true;
                UpgradeToProUserActivity.this.m10544i0();
                UpgradeToProUserActivity.this.m10550z0();
            } else {
                if (Globals.m7388i0().m7427G().isEmpty()) {
                    UpgradeToProUserActivity.this.m10545j0();
                    return;
                }
                UpgradeToProUserActivity.this.f9371p = true;
                UpgradeToProUserActivity.this.m10544i0();
                UpgradeToProUserActivity.this.m10549y0();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.UpgradeToProUserActivity$d */
    public class C1805d extends AbstractC6243a {

        /* renamed from: a */
        public final List<Integer> f9379a;

        /* renamed from: b */
        public final Activity f9380b;

        public C1805d(Activity activity) {
            this.f9380b = activity;
            ArrayList arrayList = new ArrayList();
            this.f9379a = arrayList;
            arrayList.add(Integer.valueOf(R.drawable.upgrade_pro_image_01));
            arrayList.add(Integer.valueOf(R.drawable.upgrade_pro_image_02));
            arrayList.add(Integer.valueOf(R.drawable.upgrade_pro_image_03));
        }

        @Override // p189s0.AbstractC6243a
        public void destroyItem(ViewGroup viewGroup, int i9, Object obj) {
            viewGroup.removeView((View) obj);
        }

        @Override // p189s0.AbstractC6243a
        public int getCount() {
            return this.f9379a.size();
        }

        @Override // p189s0.AbstractC6243a
        public Object instantiateItem(ViewGroup viewGroup, int i9) {
            View viewInflate = this.f9380b.getLayoutInflater().inflate(R.layout.view_item_show_pro_image, viewGroup, false);
            ((ImageView) viewInflate.findViewById(R.id.proImageView)).setImageResource(this.f9379a.get(i9).intValue());
            viewGroup.addView(viewInflate);
            return viewInflate;
        }

        @Override // p189s0.AbstractC6243a
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n0 */
    public /* synthetic */ void m10520n0() {
        C1260a.m5672i(Globals.m7388i0().m7506X()).m15439e(new C1804c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o0 */
    public /* synthetic */ void m10522o0() {
        if (this.f9366k.isShowing()) {
            if (this.f9371p) {
                this.f9366k.dismiss();
            } else if (this.f9369n && this.f9370o) {
                this.f9366k.dismiss();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p0 */
    public /* synthetic */ void m10523p0(ImageView imageView, ImageView imageView2, View view) {
        this.f9367l.setSelected(true);
        this.f9368m.setSelected(false);
        imageView.setImageResource(R.drawable.icon_select);
        imageView2.setImageResource(R.drawable.icon_deselect);
        this.f9367l.setBackgroundResource(R.drawable.bg_white_border);
        this.f9368m.setBackgroundResource(R.color.white);
        TextView textView = (TextView) this.f9367l.findViewById(R.id.monthPrice);
        TextView textView2 = (TextView) this.f9368m.findViewById(R.id.perMonthPrice);
        textView.setTextColor(getResources().getColor(R.color.upgrade_pro_option_select));
        textView2.setTextColor(getResources().getColor(R.color.upgrade_pro_option_deselect));
        SkuDetails skuDetailsM10541d0 = m10541d0("period", BillingManager.Period.MONTH.getPeriod());
        if (skuDetailsM10541d0 != null) {
            if (m10542e0(skuDetailsM10541d0)) {
                this.f9365j.setText(R.string.upgrade_pro_no_free_trial);
                this.f9362g.setVisibility(8);
            } else {
                this.f9365j.setText(R.string.upgrade_pro_free_trial);
                this.f9362g.setText(String.format(getResources().getString(R.string.upgrade_pro_monthly_price), skuDetailsM10541d0.m3649c()));
                this.f9362g.setVisibility(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q0 */
    public /* synthetic */ void m10525q0(ImageView imageView, ImageView imageView2, View view) {
        this.f9367l.setSelected(false);
        this.f9368m.setSelected(true);
        imageView.setImageResource(R.drawable.icon_deselect);
        imageView2.setImageResource(R.drawable.icon_select);
        this.f9367l.setBackgroundResource(R.color.white);
        this.f9368m.setBackgroundResource(R.drawable.bg_white_border);
        TextView textView = (TextView) this.f9367l.findViewById(R.id.monthPrice);
        TextView textView2 = (TextView) this.f9368m.findViewById(R.id.perMonthPrice);
        textView.setTextColor(getResources().getColor(R.color.upgrade_pro_option_deselect));
        textView2.setTextColor(getResources().getColor(R.color.upgrade_pro_option_select));
        SkuDetails skuDetailsM10541d0 = m10541d0("period", BillingManager.Period.YEAR.getPeriod());
        if (skuDetailsM10541d0 != null) {
            if (m10542e0(skuDetailsM10541d0)) {
                this.f9365j.setText(R.string.upgrade_pro_no_free_trial);
                this.f9362g.setVisibility(8);
            } else {
                this.f9365j.setText(R.string.upgrade_pro_free_trial);
                this.f9362g.setText(String.format(getResources().getString(R.string.upgrade_pro_free_yearly_price), skuDetailsM10541d0.m3649c()));
                this.f9362g.setVisibility(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r0 */
    public /* synthetic */ void m10527r0(View view) {
        SkuDetails skuDetailsM10541d0 = this.f9367l.isSelected() ? m10541d0("period", BillingManager.Period.MONTH.getPeriod()) : this.f9368m.isSelected() ? m10541d0("period", BillingManager.Period.YEAR.getPeriod()) : null;
        if (skuDetailsM10541d0 != null) {
            this.f9359d.launchBillingFlow(this, skuDetailsM10541d0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s0 */
    public /* synthetic */ void m10529s0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u0 */
    public /* synthetic */ void m10531u0(Dialog dialog, View view) {
        dialog.dismiss();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w0 */
    public /* synthetic */ void m10534w0(Dialog dialog, View view) {
        dialog.dismiss();
        CLUtility.m16473O1(this, Globals.m7388i0().m7427G().get(0));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x0 */
    public /* synthetic */ void m10535x0(Dialog dialog, View view) {
        dialog.dismiss();
        finish();
    }

    /* renamed from: Z */
    public void m10538Z() {
        C6385v.m24526d(new Runnable() { // from class: com.cyberlink.you.activity.fj
            @Override // java.lang.Runnable
            public final void run() {
                this.f10499b.m10520n0();
            }
        });
    }

    /* renamed from: b0 */
    public final Locale m10539b0() {
        Locale locale = Locale.getDefault();
        return Arrays.asList(new Locale("de").getLanguage(), new Locale("es").getLanguage(), new Locale("fr").getLanguage(), new Locale("it").getLanguage(), new Locale("ja").getLanguage(), new Locale("ko").getLanguage(), new Locale("zh").getLanguage()).contains(locale.getLanguage()) ? locale : Locale.US;
    }

    /* renamed from: c0 */
    public final String m10540c0(SkuDetails skuDetails) {
        int i9;
        String strM3649c = skuDetails.m3649c();
        int i10 = -1;
        for (char c9 : strM3649c.toCharArray()) {
            i10++;
            if (c9 >= '0' && c9 <= '9') {
                break;
            }
        }
        String strSubstring = strM3649c.substring(0, i10);
        int length = strM3649c.length() - 1;
        while (true) {
            if (length <= 0) {
                i9 = -1;
                break;
            }
            char cCharAt = strM3649c.charAt(length);
            if (cCharAt >= '0' && cCharAt <= '9') {
                i9 = length + 1;
                break;
            }
            length--;
        }
        return String.format(m10539b0(), strSubstring + "%.2f" + ((i9 == -1 || i9 == strM3649c.length() - 1) ? "" : strM3649c.substring(i9)), Double.valueOf((skuDetails.m3650d() / 1000000.0d) / 12.0d));
    }

    /* renamed from: d0 */
    public final SkuDetails m10541d0(String str, String str2) {
        SkuDetails next;
        if ("productId".equals(str)) {
            Iterator<SkuDetails> it = this.f9360e.iterator();
            while (it.hasNext()) {
                next = it.next();
                if (next.m3651e().equals(str2)) {
                }
            }
            return null;
        }
        if (!"period".equals(str)) {
            return null;
        }
        Iterator<SkuDetails> it2 = this.f9360e.iterator();
        while (it2.hasNext()) {
            next = it2.next();
            if (next.m3652f().equals(str2)) {
            }
        }
        return null;
        return next;
    }

    /* renamed from: e0 */
    public final boolean m10542e0(SkuDetails skuDetails) {
        return TextUtils.isEmpty(skuDetails.m3647a()) || Globals.m7388i0().m7545f2();
    }

    /* renamed from: g0 */
    public final void m10543g0() {
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    /* renamed from: i0 */
    public final void m10544i0() {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.gj
            @Override // java.lang.Runnable
            public final void run() {
                this.f10699b.m10522o0();
            }
        });
    }

    /* renamed from: j0 */
    public final void m10545j0() {
        C1802a c1802a = new C1802a();
        BillingManager billingManager = new BillingManager(this);
        this.f9359d = billingManager;
        billingManager.setBillingUpdatesListener(c1802a);
        this.f9359d.queryData();
    }

    /* renamed from: k0 */
    public final void m10546k0() {
        C1805d c1805d = new C1805d(this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.upgradeProViewpager);
        this.f9363h = viewPager;
        viewPager.setAdapter(c1805d);
        this.f9363h.m3187c(this.f9375t);
        C5006j c5006j = new C5006j((LinearLayout) findViewById(R.id.upgradeProImagePageIndex));
        this.f9364i = c5006j;
        c5006j.m19450a(this, 3);
        this.f9364i.m19451b(0);
        this.f9363h.setCurrentItem(0);
    }

    /* renamed from: l0 */
    public final void m10547l0() {
        String strM20258b = C5182t.m20258b();
        String strM20257a = C5182t.m20257a();
        C5182t.m20260d(this, (TextView) findViewById(R.id.pro_use), getString(R.string.upgrade_pro_use, strM20258b));
        C5182t.m20260d(this, (TextView) findViewById(R.id.pro_policy), getString(R.string.upgrade_pro_policy, strM20257a));
    }

    /* renamed from: m0 */
    public final void m10548m0() {
        this.f9367l = findViewById(R.id.upgrade_pro_month);
        this.f9368m = findViewById(R.id.upgrade_pro_year);
        this.f9365j = (Button) findViewById(R.id.upgradeProButton);
        this.f9367l.setSelected(false);
        this.f9368m.setSelected(true);
        final ImageView imageView = (ImageView) this.f9367l.findViewById(R.id.radio_month_pro);
        final ImageView imageView2 = (ImageView) this.f9368m.findViewById(R.id.radio_year_pro);
        this.f9362g = (TextView) findViewById(R.id.priceDescription);
        this.f9367l.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.cj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10348b.m10523p0(imageView, imageView2, view);
            }
        });
        this.f9368m.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.dj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10377b.m10525q0(imageView, imageView2, view);
            }
        });
        this.f9365j.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.ej
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10413b.m10527r0(view);
            }
        });
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m10543g0();
        setContentView(R.layout.activity_upgrade_to_pro_user);
        DialogC3133q dialogC3133qM16410a = new DialogC3133q.b(this).m16410a();
        this.f9366k = dialogC3133qM16410a;
        dialogC3133qM16410a.show();
        this.f9361f = (TextView) findViewById(R.id.pro_content);
        if (Globals.m7388i0().m7408C0() > 0) {
            ((TextView) findViewById(R.id.pro_content_time)).setText(getString(R.string.upgrade_pro_limit_time_with_duration, Integer.valueOf(Globals.m7388i0().m7408C0())));
        }
        m10548m0();
        m10538Z();
        m10547l0();
        m10546k0();
        findViewById(R.id.upgrade_pro_back).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.bj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f9758b.m10529s0(view);
            }
        });
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        BillingManager billingManager = this.f9359d;
        if (billingManager != null) {
            billingManager.destroy();
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z8) {
        super.onWindowFocusChanged(z8);
        if (z8) {
            m10543g0();
        }
    }

    /* renamed from: y0 */
    public final void m10549y0() {
        Globals.m7388i0().m7606r3(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));
        final Dialog dialogM16386e = C3123g.m16386e(this, "", getResources().getString(R.string.account_hold_suggestion), false);
        dialogM16386e.setCancelable(false);
        TextView textView = (TextView) dialogM16386e.findViewById(R.id.h_btn_cancel);
        textView.setText(R.string.cancel);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.ij
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10762b.m10531u0(dialogM16386e, view);
            }
        });
        TextView textView2 = (TextView) dialogM16386e.findViewById(R.id.h_btn_ok);
        textView2.setText(R.string.account_hold_fix);
        textView2.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.jj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10797b.m10534w0(dialogM16386e, view);
            }
        });
        dialogM16386e.show();
    }

    /* renamed from: z0 */
    public final void m10550z0() {
        final Dialog dialogM16385d = C3123g.m16385d(this, getResources().getString(R.string.upgrade_pro_fail_title), getResources().getString(R.string.upgrade_pro_fail_owned_content));
        dialogM16385d.setCancelable(false);
        TextView textView = (TextView) dialogM16385d.findViewById(R.id.v_btn);
        textView.setText(R.string.ok);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.hj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10732b.m10535x0(dialogM16385d, view);
            }
        });
        dialogM16385d.show();
    }
}
