package com.cyberlink.you.pages.photoimport;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;
import p056e4.C4755b;

/* loaded from: classes.dex */
public class VideoItem implements Serializable {
    private static final long serialVersionUID = 1;
    private String mBucketId;
    private String mDisplayName;
    private long mDuration;
    private int mHeight;
    private boolean mIsServerTranscode;
    private String mThumbnailPath;
    private long mVideoId;
    private String mVideoPath;
    private String mVideoUri;
    private int mWidth;

    public VideoItem(String str, long j9, String str2, String str3, String str4, String str5, long j10, boolean z8, int i9, int i10) {
        this.mBucketId = str;
        this.mVideoId = j9;
        this.mVideoPath = str2;
        this.mVideoUri = str3;
        this.mThumbnailPath = str4;
        this.mDisplayName = str5;
        this.mDuration = j10;
        this.mIsServerTranscode = z8;
        this.mWidth = i9;
        this.mHeight = i10;
    }

    /* renamed from: a */
    public String m16212a() {
        String str = this.mDisplayName;
        return str != null ? str : "";
    }

    /* renamed from: b */
    public long m16213b() {
        return C4755b.m18879b(this.mDuration);
    }

    /* renamed from: c */
    public int m16214c() {
        return this.mHeight;
    }

    /* renamed from: d */
    public boolean m16215d() {
        return this.mIsServerTranscode;
    }

    /* renamed from: e */
    public String m16216e() {
        String str = this.mThumbnailPath;
        return str != null ? str : "";
    }

    /* renamed from: f */
    public long m16217f() {
        return this.mVideoId;
    }

    /* renamed from: g */
    public String m16218g() {
        String str = this.mVideoPath;
        return str != null ? str : "";
    }

    /* renamed from: h */
    public String m16219h() {
        String str = this.mVideoUri;
        return str != null ? str : "";
    }

    /* renamed from: i */
    public int m16220i() {
        return this.mWidth;
    }

    /* renamed from: j */
    public void m16221j(String str) {
        if (str == null) {
            return;
        }
        this.mDisplayName = str;
    }

    /* renamed from: k */
    public void m16222k(int i9) {
        this.mHeight = i9;
    }

    /* renamed from: l */
    public void m16223l(boolean z8) {
        this.mIsServerTranscode = z8;
    }

    /* renamed from: m */
    public void m16224m(String str) {
        if (str == null) {
            return;
        }
        this.mThumbnailPath = str;
    }

    /* renamed from: n */
    public void m16225n(String str) {
        if (str == null) {
            return;
        }
        this.mVideoPath = str;
    }

    /* renamed from: o */
    public void m16226o(int i9) {
        this.mWidth = i9;
    }

    /* renamed from: p */
    public String m16227p() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("bucketId", this.mBucketId);
            jSONObject.put("videoId", this.mVideoId);
            jSONObject.put("videoPath", this.mVideoPath);
            jSONObject.put("videoUri", this.mVideoUri);
            jSONObject.put("thumbnailPath", this.mThumbnailPath);
            jSONObject.put("displayName", this.mDisplayName);
            jSONObject.put("duration", this.mDuration);
            jSONObject.put("isServerTranscode", this.mIsServerTranscode);
            jSONObject.put("width", this.mWidth);
            jSONObject.put("height", this.mHeight);
            return jSONObject.toString();
        } catch (JSONException e9) {
            e9.printStackTrace();
            return "";
        }
    }

    public String toString() {
        return ((((((((("{\"Video Id\":\"" + m16217f() + "\",") + "\"Video Path\":\"" + m16218g() + "\",") + "\"Video Uri\":\"" + m16219h() + "\",") + "\"Thumbnail Path\":\"" + m16216e() + "\",") + "\"Display Name\":\"" + m16212a() + "\",") + "\"Duration\":\"" + m16213b() + "\"") + "\"isServerTranscode\":\"" + m16215d() + "\"") + "\"Width\":\"" + m16220i() + "\"") + "\"Height\":\"" + m16214c() + "\"") + "}";
    }

    public VideoItem(JSONObject jSONObject) {
        this.mIsServerTranscode = false;
        this.mWidth = 0;
        this.mHeight = 0;
        try {
            this.mBucketId = jSONObject.getString("bucketId");
            this.mVideoId = jSONObject.getLong("videoId");
            this.mVideoPath = jSONObject.getString("videoPath");
            this.mVideoUri = jSONObject.getString("videoUri");
            this.mThumbnailPath = jSONObject.getString("thumbnailPath");
            this.mDisplayName = jSONObject.getString("displayName");
            this.mDuration = jSONObject.getLong("duration");
            if (jSONObject.has("isServerTranscode")) {
                this.mIsServerTranscode = jSONObject.getBoolean("isServerTranscode");
            }
            if (jSONObject.has("width")) {
                this.mWidth = jSONObject.getInt("width");
            }
            if (jSONObject.has("height")) {
                this.mHeight = jSONObject.getInt("height");
            }
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
    }
}
