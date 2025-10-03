package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzch;
import com.google.android.gms.internal.gtm.zzcz;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@VisibleForTesting
/* loaded from: classes2.dex */
public class HitBuilders {

    @VisibleForTesting
    @Deprecated
    public static class AppViewBuilder extends HitBuilder<AppViewBuilder> {
        public AppViewBuilder() {
            set("&t", "screenview");
        }
    }

    @VisibleForTesting
    public static class ExceptionBuilder extends HitBuilder<ExceptionBuilder> {
        public ExceptionBuilder() {
            set("&t", "exception");
        }

        public ExceptionBuilder setDescription(String str) {
            set("&exd", str);
            return this;
        }

        public ExceptionBuilder setFatal(boolean z8) {
            set("&exf", zzcz.zzc(z8));
            return this;
        }
    }

    @VisibleForTesting
    public static class HitBuilder<T extends HitBuilder> {
        private ProductAction zzrx;
        private Map<String, String> map = new HashMap();
        private Map<String, List<Product>> zzry = new HashMap();
        private List<Promotion> zzrz = new ArrayList();
        private List<Product> zzsa = new ArrayList();

        private final T zza(String str, String str2) {
            if (str2 != null) {
                this.map.put(str, str2);
            }
            return this;
        }

        public T addImpression(Product product, String str) {
            if (product == null) {
                zzch.zzac("product should be non-null");
                return this;
            }
            if (str == null) {
                str = "";
            }
            if (!this.zzry.containsKey(str)) {
                this.zzry.put(str, new ArrayList());
            }
            this.zzry.get(str).add(product);
            return this;
        }

        public T addProduct(Product product) {
            if (product == null) {
                zzch.zzac("product should be non-null");
                return this;
            }
            this.zzsa.add(product);
            return this;
        }

        public T addPromotion(Promotion promotion) {
            if (promotion == null) {
                zzch.zzac("promotion should be non-null");
                return this;
            }
            this.zzrz.add(promotion);
            return this;
        }

        public Map<String, String> build() {
            HashMap map = new HashMap(this.map);
            ProductAction productAction = this.zzrx;
            if (productAction != null) {
                map.putAll(productAction.build());
            }
            Iterator<Promotion> it = this.zzrz.iterator();
            int i9 = 1;
            while (it.hasNext()) {
                map.putAll(it.next().zzn(zzd.zzj(i9)));
                i9++;
            }
            Iterator<Product> it2 = this.zzsa.iterator();
            int i10 = 1;
            while (it2.hasNext()) {
                map.putAll(it2.next().zzn(zzd.zzh(i10)));
                i10++;
            }
            int i11 = 1;
            for (Map.Entry<String, List<Product>> entry : this.zzry.entrySet()) {
                List<Product> value = entry.getValue();
                String strZzm = zzd.zzm(i11);
                int i12 = 1;
                for (Product product : value) {
                    String strValueOf = String.valueOf(strZzm);
                    String strValueOf2 = String.valueOf(zzd.zzl(i12));
                    map.putAll(product.zzn(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf)));
                    i12++;
                }
                if (!TextUtils.isEmpty(entry.getKey())) {
                    String strValueOf3 = String.valueOf(strZzm);
                    map.put("nm".length() != 0 ? strValueOf3.concat("nm") : new String(strValueOf3), entry.getKey());
                }
                i11++;
            }
            return map;
        }

        @VisibleForTesting
        public String get(String str) {
            return this.map.get(str);
        }

        public final T set(String str, String str2) {
            if (str != null) {
                this.map.put(str, str2);
            } else {
                zzch.zzac("HitBuilder.set() called with a null paramName.");
            }
            return this;
        }

        public final T setAll(Map<String, String> map) {
            if (map == null) {
                return this;
            }
            this.map.putAll(new HashMap(map));
            return this;
        }

        public T setCampaignParamsFromUrl(String str) throws UnsupportedEncodingException {
            String strZzah = zzcz.zzah(str);
            if (TextUtils.isEmpty(strZzah)) {
                return this;
            }
            Map<String, String> mapZzaf = zzcz.zzaf(strZzah);
            zza("&cc", mapZzaf.get("utm_content"));
            zza("&cm", mapZzaf.get("utm_medium"));
            zza("&cn", mapZzaf.get("utm_campaign"));
            zza("&cs", mapZzaf.get("utm_source"));
            zza("&ck", mapZzaf.get("utm_term"));
            zza("&ci", mapZzaf.get("utm_id"));
            zza("&anid", mapZzaf.get("anid"));
            zza("&gclid", mapZzaf.get("gclid"));
            zza("&dclid", mapZzaf.get("dclid"));
            zza("&aclid", mapZzaf.get(FirebaseAnalytics.Param.ACLID));
            zza("&gmob_t", mapZzaf.get("gmob_t"));
            return this;
        }

        public T setCustomDimension(int i9, String str) {
            set(zzd.zzd(i9), str);
            return this;
        }

        public T setCustomMetric(int i9, float f9) {
            set(zzd.zzf(i9), Float.toString(f9));
            return this;
        }

        public T setHitType(String str) {
            set("&t", str);
            return this;
        }

        public T setNewSession() {
            set("&sc", TtmlNode.START);
            return this;
        }

        public T setNonInteraction(boolean z8) {
            set("&ni", zzcz.zzc(z8));
            return this;
        }

        public T setProductAction(ProductAction productAction) {
            this.zzrx = productAction;
            return this;
        }

        public T setPromotionAction(String str) {
            this.map.put("&promoa", str);
            return this;
        }
    }

    @VisibleForTesting
    @Deprecated
    public static class ItemBuilder extends HitBuilder<ItemBuilder> {
        public ItemBuilder() {
            set("&t", "item");
        }

        public ItemBuilder setCategory(String str) {
            set("&iv", str);
            return this;
        }

        public ItemBuilder setCurrencyCode(String str) {
            set("&cu", str);
            return this;
        }

        public ItemBuilder setName(String str) {
            set("&in", str);
            return this;
        }

        public ItemBuilder setPrice(double d9) {
            set("&ip", Double.toString(d9));
            return this;
        }

        public ItemBuilder setQuantity(long j9) {
            set("&iq", Long.toString(j9));
            return this;
        }

        public ItemBuilder setSku(String str) {
            set("&ic", str);
            return this;
        }

        public ItemBuilder setTransactionId(String str) {
            set("&ti", str);
            return this;
        }
    }

    @VisibleForTesting
    public static class ScreenViewBuilder extends HitBuilder<ScreenViewBuilder> {
        public ScreenViewBuilder() {
            set("&t", "screenview");
        }
    }

    @VisibleForTesting
    public static class SocialBuilder extends HitBuilder<SocialBuilder> {
        public SocialBuilder() {
            set("&t", "social");
        }

        public SocialBuilder setAction(String str) {
            set("&sa", str);
            return this;
        }

        public SocialBuilder setNetwork(String str) {
            set("&sn", str);
            return this;
        }

        public SocialBuilder setTarget(String str) {
            set("&st", str);
            return this;
        }
    }

    @VisibleForTesting
    @Deprecated
    public static class TransactionBuilder extends HitBuilder<TransactionBuilder> {
        public TransactionBuilder() {
            set("&t", "transaction");
        }

        public TransactionBuilder setAffiliation(String str) {
            set("&ta", str);
            return this;
        }

        public TransactionBuilder setCurrencyCode(String str) {
            set("&cu", str);
            return this;
        }

        public TransactionBuilder setRevenue(double d9) {
            set("&tr", Double.toString(d9));
            return this;
        }

        public TransactionBuilder setShipping(double d9) {
            set("&ts", Double.toString(d9));
            return this;
        }

        public TransactionBuilder setTax(double d9) {
            set("&tt", Double.toString(d9));
            return this;
        }

        public TransactionBuilder setTransactionId(String str) {
            set("&ti", str);
            return this;
        }
    }

    @VisibleForTesting
    public static class EventBuilder extends HitBuilder<EventBuilder> {
        public EventBuilder() {
            set("&t", "event");
        }

        public EventBuilder setAction(String str) {
            set("&ea", str);
            return this;
        }

        public EventBuilder setCategory(String str) {
            set("&ec", str);
            return this;
        }

        public EventBuilder setLabel(String str) {
            set("&el", str);
            return this;
        }

        public EventBuilder setValue(long j9) {
            set("&ev", Long.toString(j9));
            return this;
        }

        public EventBuilder(String str, String str2) {
            this();
            setCategory(str);
            setAction(str2);
        }
    }

    @VisibleForTesting
    public static class TimingBuilder extends HitBuilder<TimingBuilder> {
        public TimingBuilder() {
            set("&t", "timing");
        }

        public TimingBuilder setCategory(String str) {
            set("&utc", str);
            return this;
        }

        public TimingBuilder setLabel(String str) {
            set("&utl", str);
            return this;
        }

        public TimingBuilder setValue(long j9) {
            set("&utt", Long.toString(j9));
            return this;
        }

        public TimingBuilder setVariable(String str) {
            set("&utv", str);
            return this;
        }

        public TimingBuilder(String str, String str2, long j9) {
            this();
            setVariable(str2);
            setValue(j9);
            setCategory(str);
        }
    }
}
