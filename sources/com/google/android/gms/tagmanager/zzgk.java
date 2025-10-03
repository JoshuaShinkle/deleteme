package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ShowFirstParty
@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzgk extends zzgh {

    /* renamed from: ID */
    private static final String f15363ID = com.google.android.gms.internal.gtm.zza.UNIVERSAL_ANALYTICS.toString();
    private static final String zzalx = com.google.android.gms.internal.gtm.zzb.ACCOUNT.toString();
    private static final String zzaly = com.google.android.gms.internal.gtm.zzb.ANALYTICS_PASS_THROUGH.toString();
    private static final String zzalz = com.google.android.gms.internal.gtm.zzb.ENABLE_ECOMMERCE.toString();
    private static final String zzama = com.google.android.gms.internal.gtm.zzb.ECOMMERCE_USE_DATA_LAYER.toString();
    private static final String zzamb = com.google.android.gms.internal.gtm.zzb.ECOMMERCE_MACRO_DATA.toString();
    private static final String zzamc = com.google.android.gms.internal.gtm.zzb.ANALYTICS_FIELDS.toString();
    private static final String zzamd = com.google.android.gms.internal.gtm.zzb.TRACK_TRANSACTION.toString();
    private static final String zzame = com.google.android.gms.internal.gtm.zzb.TRANSACTION_DATALAYER_MAP.toString();
    private static final String zzamf = com.google.android.gms.internal.gtm.zzb.TRANSACTION_ITEM_DATALAYER_MAP.toString();
    private static final List<String> zzamg = Arrays.asList(ProductAction.ACTION_DETAIL, ProductAction.ACTION_CHECKOUT, "checkout_option", "click", ProductAction.ACTION_ADD, ProductAction.ACTION_REMOVE, "purchase", "refund");
    private static final Pattern zzamh = Pattern.compile("dimension(\\d+)");
    private static final Pattern zzami = Pattern.compile("metric(\\d+)");
    private static Map<String, String> zzamj;
    private static Map<String, String> zzamk;
    private final DataLayer zzaed;
    private final Set<String> zzaml;
    private final zzgf zzamm;

    public zzgk(Context context, DataLayer dataLayer) {
        this(context, dataLayer, new zzgf(context));
    }

    private final String zzbr(String str) {
        Object obj = this.zzaed.get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    private static boolean zzc(Map<String, com.google.android.gms.internal.gtm.zzl> map, String str) {
        com.google.android.gms.internal.gtm.zzl zzlVar = map.get(str);
        if (zzlVar == null) {
            return false;
        }
        return zzgj.zzg(zzlVar).booleanValue();
    }

    private static Product zzf(Map<String, Object> map) {
        Product product = new Product();
        Object obj = map.get(TtmlNode.ATTR_ID);
        if (obj != null) {
            product.setId(String.valueOf(obj));
        }
        Object obj2 = map.get(AppMeasurementSdk.ConditionalUserProperty.NAME);
        if (obj2 != null) {
            product.setName(String.valueOf(obj2));
        }
        Object obj3 = map.get("brand");
        if (obj3 != null) {
            product.setBrand(String.valueOf(obj3));
        }
        Object obj4 = map.get("category");
        if (obj4 != null) {
            product.setCategory(String.valueOf(obj4));
        }
        Object obj5 = map.get("variant");
        if (obj5 != null) {
            product.setVariant(String.valueOf(obj5));
        }
        Object obj6 = map.get(FirebaseAnalytics.Param.COUPON);
        if (obj6 != null) {
            product.setCouponCode(String.valueOf(obj6));
        }
        Object obj7 = map.get("position");
        if (obj7 != null) {
            product.setPosition(zzn(obj7).intValue());
        }
        Object obj8 = map.get(FirebaseAnalytics.Param.PRICE);
        if (obj8 != null) {
            product.setPrice(zzm(obj8).doubleValue());
        }
        Object obj9 = map.get(FirebaseAnalytics.Param.QUANTITY);
        if (obj9 != null) {
            product.setQuantity(zzn(obj9).intValue());
        }
        for (String str : map.keySet()) {
            Matcher matcher = zzamh.matcher(str);
            if (matcher.matches()) {
                try {
                    product.setCustomDimension(Integer.parseInt(matcher.group(1)), String.valueOf(map.get(str)));
                } catch (NumberFormatException unused) {
                    String strValueOf = String.valueOf(str);
                    zzdi.zzac(strValueOf.length() != 0 ? "illegal number in custom dimension value: ".concat(strValueOf) : new String("illegal number in custom dimension value: "));
                }
            } else {
                Matcher matcher2 = zzami.matcher(str);
                if (matcher2.matches()) {
                    try {
                        product.setCustomMetric(Integer.parseInt(matcher2.group(1)), zzn(map.get(str)).intValue());
                    } catch (NumberFormatException unused2) {
                        String strValueOf2 = String.valueOf(str);
                        zzdi.zzac(strValueOf2.length() != 0 ? "illegal number in custom metric value: ".concat(strValueOf2) : new String("illegal number in custom metric value: "));
                    }
                }
            }
        }
        return product;
    }

    private static Map<String, String> zzi(com.google.android.gms.internal.gtm.zzl zzlVar) {
        Object objZzh = zzgj.zzh(zzlVar);
        if (!(objZzh instanceof Map)) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : ((Map) objZzh).entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }

    private final Map<String, String> zzj(com.google.android.gms.internal.gtm.zzl zzlVar) {
        Map<String, String> mapZzi;
        if (zzlVar != null && (mapZzi = zzi(zzlVar)) != null) {
            String str = mapZzi.get("&aip");
            if (str != null && this.zzaml.contains(str.toLowerCase())) {
                mapZzi.remove("&aip");
            }
            return mapZzi;
        }
        return new HashMap();
    }

    private static Double zzm(Object obj) {
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj);
            } catch (NumberFormatException e9) {
                String strValueOf = String.valueOf(e9.getMessage());
                throw new RuntimeException(strValueOf.length() != 0 ? "Cannot convert the object to Double: ".concat(strValueOf) : new String("Cannot convert the object to Double: "));
            }
        }
        if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        }
        if (obj instanceof Double) {
            return (Double) obj;
        }
        String strValueOf2 = String.valueOf(obj.toString());
        throw new RuntimeException(strValueOf2.length() != 0 ? "Cannot convert the object to Double: ".concat(strValueOf2) : new String("Cannot convert the object to Double: "));
    }

    private static Integer zzn(Object obj) {
        if (obj instanceof String) {
            try {
                return Integer.valueOf((String) obj);
            } catch (NumberFormatException e9) {
                String strValueOf = String.valueOf(e9.getMessage());
                throw new RuntimeException(strValueOf.length() != 0 ? "Cannot convert the object to Integer: ".concat(strValueOf) : new String("Cannot convert the object to Integer: "));
            }
        }
        if (obj instanceof Double) {
            return Integer.valueOf(((Double) obj).intValue());
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        String strValueOf2 = String.valueOf(obj.toString());
        throw new RuntimeException(strValueOf2.length() != 0 ? "Cannot convert the object to Integer: ".concat(strValueOf2) : new String("Cannot convert the object to Integer: "));
    }

    @Override // com.google.android.gms.tagmanager.zzgh, com.google.android.gms.tagmanager.zzbq
    public final /* bridge */ /* synthetic */ com.google.android.gms.internal.gtm.zzl zzb(Map map) {
        return super.zzb(map);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0171  */
    @Override // com.google.android.gms.tagmanager.zzgh
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzd(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        Map<String, String> mapZzi;
        Map<String, String> mapZzi2;
        Map map2;
        ProductAction productAction;
        Tracker trackerZzbm = this.zzamm.zzbm("_GTM_DEFAULT_TRACKER_");
        trackerZzbm.enableAdvertisingIdCollection(zzc(map, "collect_adid"));
        int i9 = 0;
        List<Map> list = null;
        if (!zzc(map, zzalz)) {
            if (zzc(map, zzaly)) {
                trackerZzbm.send(zzj(map.get(zzamc)));
                return;
            }
            if (!zzc(map, zzamd)) {
                zzdi.zzac("Ignoring unknown tag.");
                return;
            }
            String strZzbr = zzbr("transactionId");
            if (strZzbr == null) {
                zzdi.zzav("Cannot find transactionId in data layer.");
                return;
            }
            ArrayList arrayList = new ArrayList();
            try {
                Map<String, String> mapZzj = zzj(map.get(zzamc));
                mapZzj.put("&t", "transaction");
                com.google.android.gms.internal.gtm.zzl zzlVar = map.get(zzame);
                if (zzlVar != null) {
                    mapZzi = zzi(zzlVar);
                } else {
                    if (zzamj == null) {
                        HashMap map3 = new HashMap();
                        map3.put("transactionId", "&ti");
                        map3.put("transactionAffiliation", "&ta");
                        map3.put("transactionTax", "&tt");
                        map3.put("transactionShipping", "&ts");
                        map3.put("transactionTotal", "&tr");
                        map3.put("transactionCurrency", "&cu");
                        zzamj = map3;
                    }
                    mapZzi = zzamj;
                }
                for (Map.Entry<String, String> entry : mapZzi.entrySet()) {
                    zzd(mapZzj, entry.getValue(), zzbr(entry.getKey()));
                }
                arrayList.add(mapZzj);
                Object obj = this.zzaed.get("transactionProducts");
                if (obj != null) {
                    if (!(obj instanceof List)) {
                        throw new IllegalArgumentException("transactionProducts should be of type List.");
                    }
                    Iterator it = ((List) obj).iterator();
                    while (it.hasNext()) {
                        if (!(it.next() instanceof Map)) {
                            throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
                        }
                    }
                    list = (List) obj;
                }
                if (list != null) {
                    for (Map map4 : list) {
                        if (map4.get(AppMeasurementSdk.ConditionalUserProperty.NAME) == null) {
                            zzdi.zzav("Unable to send transaction item hit due to missing 'name' field.");
                            return;
                        }
                        Map<String, String> mapZzj2 = zzj(map.get(zzamc));
                        mapZzj2.put("&t", "item");
                        mapZzj2.put("&ti", strZzbr);
                        com.google.android.gms.internal.gtm.zzl zzlVar2 = map.get(zzamf);
                        if (zzlVar2 != null) {
                            mapZzi2 = zzi(zzlVar2);
                        } else {
                            if (zzamk == null) {
                                HashMap map5 = new HashMap();
                                map5.put(AppMeasurementSdk.ConditionalUserProperty.NAME, "&in");
                                map5.put("sku", "&ic");
                                map5.put("category", "&iv");
                                map5.put(FirebaseAnalytics.Param.PRICE, "&ip");
                                map5.put(FirebaseAnalytics.Param.QUANTITY, "&iq");
                                map5.put(FirebaseAnalytics.Param.CURRENCY, "&cu");
                                zzamk = map5;
                            }
                            mapZzi2 = zzamk;
                        }
                        for (Map.Entry<String, String> entry2 : mapZzi2.entrySet()) {
                            zzd(mapZzj2, entry2.getValue(), (String) map4.get(entry2.getKey()));
                        }
                        arrayList.add(mapZzj2);
                    }
                }
                int size = arrayList.size();
                while (i9 < size) {
                    Object obj2 = arrayList.get(i9);
                    i9++;
                    trackerZzbm.send((Map) obj2);
                }
                return;
            } catch (IllegalArgumentException e9) {
                zzdi.zza("Unable to send transaction", e9);
                return;
            }
        }
        HitBuilders.ScreenViewBuilder screenViewBuilder = new HitBuilders.ScreenViewBuilder();
        Map<String, String> mapZzj3 = zzj(map.get(zzamc));
        screenViewBuilder.setAll(mapZzj3);
        if (zzc(map, zzama)) {
            Object obj3 = this.zzaed.get("ecommerce");
            map2 = obj3 instanceof Map ? (Map) obj3 : null;
        } else {
            Object objZzh = zzgj.zzh(map.get(zzamb));
            if (objZzh instanceof Map) {
                map2 = (Map) objZzh;
            }
        }
        if (map2 != null) {
            String str = mapZzj3.get("&cu");
            if (str == null) {
                str = (String) map2.get("currencyCode");
            }
            if (str != null) {
                screenViewBuilder.set("&cu", str);
            }
            Object obj4 = map2.get("impressions");
            if (obj4 instanceof List) {
                for (Map map6 : (List) obj4) {
                    try {
                        screenViewBuilder.addImpression(zzf(map6), (String) map6.get("list"));
                    } catch (RuntimeException e10) {
                        String strValueOf = String.valueOf(e10.getMessage());
                        zzdi.zzav(strValueOf.length() != 0 ? "Failed to extract a product from DataLayer. ".concat(strValueOf) : new String("Failed to extract a product from DataLayer. "));
                    }
                }
            }
            if (map2.containsKey("promoClick")) {
                list = (List) ((Map) map2.get("promoClick")).get("promotions");
            } else if (map2.containsKey("promoView")) {
                list = (List) ((Map) map2.get("promoView")).get("promotions");
            }
            if (list != null) {
                for (Map map7 : list) {
                    try {
                        Promotion promotion = new Promotion();
                        String str2 = (String) map7.get(TtmlNode.ATTR_ID);
                        if (str2 != null) {
                            promotion.setId(str2);
                        }
                        String str3 = (String) map7.get(AppMeasurementSdk.ConditionalUserProperty.NAME);
                        if (str3 != null) {
                            promotion.setName(str3);
                        }
                        String str4 = (String) map7.get("creative");
                        if (str4 != null) {
                            promotion.setCreative(str4);
                        }
                        String str5 = (String) map7.get("position");
                        if (str5 != null) {
                            promotion.setPosition(str5);
                        }
                        screenViewBuilder.addPromotion(promotion);
                    } catch (RuntimeException e11) {
                        String strValueOf2 = String.valueOf(e11.getMessage());
                        zzdi.zzav(strValueOf2.length() != 0 ? "Failed to extract a promotion from DataLayer. ".concat(strValueOf2) : new String("Failed to extract a promotion from DataLayer. "));
                    }
                }
                if (map2.containsKey("promoClick")) {
                    screenViewBuilder.set("&promoa", "click");
                    if (i9 != 0) {
                        Iterator<String> it2 = zzamg.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            String next = it2.next();
                            if (map2.containsKey(next)) {
                                Map map8 = (Map) map2.get(next);
                                List list2 = (List) map8.get("products");
                                if (list2 != null) {
                                    Iterator it3 = list2.iterator();
                                    while (it3.hasNext()) {
                                        try {
                                            screenViewBuilder.addProduct(zzf((Map) it3.next()));
                                        } catch (RuntimeException e12) {
                                            String strValueOf3 = String.valueOf(e12.getMessage());
                                            zzdi.zzav(strValueOf3.length() != 0 ? "Failed to extract a product from DataLayer. ".concat(strValueOf3) : new String("Failed to extract a product from DataLayer. "));
                                        }
                                    }
                                }
                                try {
                                    if (map8.containsKey("actionField")) {
                                        Map map9 = (Map) map8.get("actionField");
                                        productAction = new ProductAction(next);
                                        Object obj5 = map9.get(TtmlNode.ATTR_ID);
                                        if (obj5 != null) {
                                            productAction.setTransactionId(String.valueOf(obj5));
                                        }
                                        Object obj6 = map9.get(FirebaseAnalytics.Param.AFFILIATION);
                                        if (obj6 != null) {
                                            productAction.setTransactionAffiliation(String.valueOf(obj6));
                                        }
                                        Object obj7 = map9.get(FirebaseAnalytics.Param.COUPON);
                                        if (obj7 != null) {
                                            productAction.setTransactionCouponCode(String.valueOf(obj7));
                                        }
                                        Object obj8 = map9.get("list");
                                        if (obj8 != null) {
                                            productAction.setProductActionList(String.valueOf(obj8));
                                        }
                                        Object obj9 = map9.get("option");
                                        if (obj9 != null) {
                                            productAction.setCheckoutOptions(String.valueOf(obj9));
                                        }
                                        Object obj10 = map9.get("revenue");
                                        if (obj10 != null) {
                                            productAction.setTransactionRevenue(zzm(obj10).doubleValue());
                                        }
                                        Object obj11 = map9.get(FirebaseAnalytics.Param.TAX);
                                        if (obj11 != null) {
                                            productAction.setTransactionTax(zzm(obj11).doubleValue());
                                        }
                                        Object obj12 = map9.get(FirebaseAnalytics.Param.SHIPPING);
                                        if (obj12 != null) {
                                            productAction.setTransactionShipping(zzm(obj12).doubleValue());
                                        }
                                        Object obj13 = map9.get("step");
                                        if (obj13 != null) {
                                            productAction.setCheckoutStep(zzn(obj13).intValue());
                                        }
                                    } else {
                                        productAction = new ProductAction(next);
                                    }
                                    screenViewBuilder.setProductAction(productAction);
                                } catch (RuntimeException e13) {
                                    String strValueOf4 = String.valueOf(e13.getMessage());
                                    zzdi.zzav(strValueOf4.length() != 0 ? "Failed to extract a product action from DataLayer. ".concat(strValueOf4) : new String("Failed to extract a product action from DataLayer. "));
                                }
                            }
                        }
                    }
                } else {
                    screenViewBuilder.set("&promoa", Promotion.ACTION_VIEW);
                    i9 = 1;
                    if (i9 != 0) {
                    }
                }
            } else {
                i9 = 1;
                if (i9 != 0) {
                }
            }
        }
        trackerZzbm.send(screenViewBuilder.build());
    }

    @Override // com.google.android.gms.tagmanager.zzgh, com.google.android.gms.tagmanager.zzbq
    public final /* bridge */ /* synthetic */ boolean zzgw() {
        return super.zzgw();
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final /* bridge */ /* synthetic */ String zzif() {
        return super.zzif();
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final /* bridge */ /* synthetic */ Set zzig() {
        return super.zzig();
    }

    @VisibleForTesting
    private zzgk(Context context, DataLayer dataLayer, zzgf zzgfVar) {
        super(f15363ID, new String[0]);
        this.zzaed = dataLayer;
        this.zzamm = zzgfVar;
        HashSet hashSet = new HashSet();
        this.zzaml = hashSet;
        hashSet.add("");
        hashSet.add("0");
        hashSet.add("false");
    }

    private static void zzd(Map<String, String> map, String str, String str2) {
        if (str2 != null) {
            map.put(str, str2);
        }
    }
}
