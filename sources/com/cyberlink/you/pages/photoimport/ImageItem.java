package com.cyberlink.you.pages.photoimport;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.gms.plus.PlusShare;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ImageItem implements Serializable {
    private static final long serialVersionUID = 1;
    private String appliedEffectImg;
    private String audioDuration;
    private String audioPath;
    private String bucket;
    private String bucketId;
    private boolean checked;
    private int childCount;
    private String description;
    private String handDrawImg;
    private String handDrawImgUri;
    private String hashKey;
    private long imageId;
    private boolean needDeleteOrgImage;
    private int orgHeight;
    private String orgImg;
    private String orgImgUri;
    private int orgWidth;
    private int orientation;
    private String title;

    public ImageItem(JSONObject jSONObject) {
        try {
            this.bucket = jSONObject.getString("bucket");
            this.orgImg = jSONObject.optString("orgImg");
            this.orgImgUri = jSONObject.optString("orgImgUri");
            this.orgWidth = jSONObject.getInt("orgWidth");
            this.orgHeight = jSONObject.getInt("orgHeight");
            this.title = jSONObject.getString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE);
            this.bucketId = jSONObject.getString("bucketId");
            this.imageId = jSONObject.getLong("imageId");
            this.childCount = jSONObject.getInt("childCount");
            this.checked = jSONObject.getBoolean("checked");
            this.orientation = jSONObject.getInt("orientation");
            this.hashKey = jSONObject.getString("hashKey");
            this.description = jSONObject.getString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION);
            this.audioPath = jSONObject.getString("audioPath");
            this.audioDuration = jSONObject.getString("audioDuration");
            this.handDrawImg = jSONObject.optString("handDrawImg");
            this.handDrawImgUri = jSONObject.optString("handDrawImgUri");
            this.appliedEffectImg = jSONObject.optString("appliedEffectImg");
            this.needDeleteOrgImage = jSONObject.optBoolean("needDeleteOrgImage");
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: A */
    public void m16120A(String str) {
        this.handDrawImg = str;
    }

    /* renamed from: B */
    public void m16121B(String str) {
        this.handDrawImgUri = str;
    }

    /* renamed from: C */
    public void m16122C(long j9) {
        this.imageId = j9;
    }

    /* renamed from: D */
    public void m16123D(boolean z8) {
        this.needDeleteOrgImage = z8;
    }

    /* renamed from: E */
    public void m16124E(String str) {
        this.orgImg = str;
        if (CLUtility.m16613z1(str, null)) {
            Pair<Integer, Integer> pairM16503X0 = CLUtility.m16503X0(this.orgImg, null);
            if (((Integer) pairM16503X0.first).intValue() <= 0 || ((Integer) pairM16503X0.second).intValue() <= 0) {
                return;
            }
            this.orgWidth = ((Integer) pairM16503X0.first).intValue();
            this.orgHeight = ((Integer) pairM16503X0.second).intValue();
        }
    }

    /* renamed from: F */
    public void m16125F(String str) {
        this.orgImgUri = str;
        Uri uriM16510Z1 = CLUtility.m16510Z1(str);
        if (CLUtility.m16613z1(null, uriM16510Z1)) {
            Pair<Integer, Integer> pairM16503X0 = CLUtility.m16503X0(null, uriM16510Z1);
            if (((Integer) pairM16503X0.first).intValue() <= 0 || ((Integer) pairM16503X0.second).intValue() <= 0) {
                return;
            }
            this.orgWidth = ((Integer) pairM16503X0.first).intValue();
            this.orgHeight = ((Integer) pairM16503X0.second).intValue();
        }
    }

    /* renamed from: G */
    public void m16126G(String str) {
        this.title = str;
    }

    /* renamed from: H */
    public String m16127H() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("bucket", this.bucket);
            jSONObject.put("orgImg", this.orgImg);
            jSONObject.put("orgImgUri", this.orgImgUri);
            jSONObject.put("orgWidth", this.orgWidth);
            jSONObject.put("orgHeight", this.orgHeight);
            jSONObject.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, this.title);
            jSONObject.put("bucketId", this.bucketId);
            jSONObject.put("imageId", this.imageId);
            jSONObject.put("childCount", this.childCount);
            jSONObject.put("checked", this.checked);
            jSONObject.put("orientation", this.orientation);
            jSONObject.put("hashKey", this.hashKey);
            jSONObject.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, this.description);
            jSONObject.put("audioPath", this.audioPath);
            jSONObject.put("audioDuration", this.audioDuration);
            jSONObject.put("handDrawImg", this.handDrawImg);
            jSONObject.put("handDrawImgUri", this.handDrawImgUri);
            jSONObject.put("appliedEffectImg", this.appliedEffectImg);
            jSONObject.put("needDeleteOrgImage", this.needDeleteOrgImage);
            return jSONObject.toString();
        } catch (JSONException e9) {
            e9.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public String m16128a() {
        return this.audioDuration;
    }

    /* renamed from: b */
    public String m16129b() {
        return this.audioPath;
    }

    /* renamed from: c */
    public String m16130c() {
        return this.bucket;
    }

    /* renamed from: d */
    public String m16131d() {
        return this.bucketId;
    }

    /* renamed from: e */
    public boolean m16132e() {
        return this.checked;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.imageId == ((ImageItem) obj).imageId;
    }

    /* renamed from: f */
    public int m16133f() {
        return this.childCount;
    }

    /* renamed from: g */
    public String m16134g() {
        return this.description;
    }

    /* renamed from: h */
    public String m16135h() {
        return !TextUtils.isEmpty(this.handDrawImg) ? this.handDrawImg : this.orgImg;
    }

    public int hashCode() {
        long j9 = this.imageId;
        return (int) (j9 ^ (j9 >>> 32));
    }

    /* renamed from: i */
    public String m16136i() {
        return !TextUtils.isEmpty(this.handDrawImgUri) ? this.handDrawImgUri : this.orgImgUri;
    }

    /* renamed from: j */
    public String m16137j() {
        String str = this.orgImg;
        if (!TextUtils.isEmpty(this.appliedEffectImg)) {
            str = this.appliedEffectImg;
        }
        return !TextUtils.isEmpty(this.handDrawImg) ? this.handDrawImg : str;
    }

    /* renamed from: k */
    public String m16138k() {
        String str = this.orgImgUri;
        if (!TextUtils.isEmpty(this.appliedEffectImg)) {
            str = this.appliedEffectImg;
        }
        return !TextUtils.isEmpty(this.handDrawImgUri) ? this.handDrawImgUri : str;
    }

    /* renamed from: l */
    public String m16139l() {
        return this.handDrawImg;
    }

    /* renamed from: m */
    public String m16140m() {
        return this.handDrawImgUri;
    }

    /* renamed from: n */
    public String m16141n() {
        return this.hashKey;
    }

    /* renamed from: o */
    public long m16142o() {
        return this.imageId;
    }

    /* renamed from: p */
    public int m16143p() {
        return this.orgHeight;
    }

    /* renamed from: q */
    public String m16144q() {
        return this.orgImg;
    }

    /* renamed from: r */
    public String m16145r() {
        return this.orgImgUri;
    }

    /* renamed from: s */
    public int m16146s() {
        return this.orgWidth;
    }

    /* renamed from: t */
    public String m16147t() {
        return this.title;
    }

    public String toString() {
        return (((((((((((((((((("{\"bucket\":\"" + this.bucket + "\",") + "\"orgImg\":\"" + this.orgImg + "\",") + "\"orgImgUri\":\"" + this.orgImgUri + "\",") + "\"orgWidth\":\"" + this.orgWidth + "\",") + "\"orgHeight\":\"" + this.orgHeight + "\",") + "\"title\":\"" + this.title + "\",") + "\"bucketId\":\"" + this.bucketId + "\",") + "\"imageId\":\"" + this.imageId + "\",") + "\"childCount\":\"" + this.childCount + "\",") + "\"checked\":\"" + this.checked + "\",") + "\"orientation\":\"" + this.orientation + "\",") + "\"hashKey\":\"" + this.hashKey + "\",") + "\"description\":\"" + this.description + "\",") + "\"audioPath\":\"" + this.audioPath + "\",") + "\"audioDuration\":\"" + this.audioDuration + "\"") + "\"handDrawImg\":\"" + this.handDrawImg + "\"") + "\"handDrawImgUri\":\"" + this.handDrawImgUri + "\"") + "\"appliedEffectImg\":\"" + this.appliedEffectImg + "\"") + "}";
    }

    /* renamed from: u */
    public boolean m16148u() {
        return this.needDeleteOrgImage;
    }

    /* renamed from: v */
    public void m16149v(String str) {
        this.audioDuration = str;
    }

    /* renamed from: w */
    public void m16150w(String str) {
        this.audioPath = str;
    }

    /* renamed from: x */
    public void m16151x(boolean z8) {
        this.checked = z8;
    }

    /* renamed from: y */
    public void m16152y(int i9) {
        this.childCount = i9;
    }

    /* renamed from: z */
    public void m16153z(String str) {
        this.description = str;
    }

    public ImageItem(String str, long j9, String str2, String str3, String str4, int i9, int i10, String str5) {
        this(str, j9, str2, str3, null, str4, i9, i10, str5, "", "", "", "", null, "", false);
    }

    public ImageItem(String str, long j9, String str2, String str3, String str4, String str5, int i9, int i10, String str6) {
        this(str, j9, str2, str3, str4, str5, i9, i10, str6, "", "", "", "", null, "", false);
    }

    public ImageItem(String str, long j9, String str2, String str3, String str4, String str5, int i9, int i10, String str6, String str7, String str8, String str9, String str10, String str11, String str12, boolean z8) {
        this.bucket = str2;
        this.orgImg = str3;
        this.orgImgUri = str4;
        this.orgWidth = 0;
        this.orgHeight = 0;
        this.title = str5;
        this.bucketId = str;
        this.imageId = j9;
        this.childCount = i9;
        this.checked = false;
        this.orientation = i10;
        this.hashKey = str6;
        this.description = str7;
        this.audioPath = str8;
        this.audioDuration = str9;
        this.handDrawImg = str10;
        this.handDrawImgUri = str11;
        this.appliedEffectImg = str12;
        this.needDeleteOrgImage = z8;
    }
}
