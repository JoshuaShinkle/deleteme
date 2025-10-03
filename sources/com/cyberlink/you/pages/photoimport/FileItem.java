package com.cyberlink.you.pages.photoimport;

import android.net.Uri;
import android.text.TextUtils;
import com.cyberlink.you.utility.CLUtility;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5154j;

/* loaded from: classes.dex */
public class FileItem implements Serializable {
    private String bucket;
    private String fileName;
    private String filePath;
    private long fileSize;
    private String fileUri;
    private boolean isCopied;

    public FileItem(JSONObject jSONObject) {
        this(m16113e(jSONObject, "bucket"), m16113e(jSONObject, "filePath"), m16113e(jSONObject, "fileUri"));
    }

    /* renamed from: e */
    public static String m16113e(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getString(str);
        } catch (JSONException e9) {
            C5154j.m20076j(e9);
            return null;
        }
    }

    /* renamed from: a */
    public String m16114a() {
        return this.fileName;
    }

    /* renamed from: b */
    public String m16115b() {
        return this.filePath;
    }

    /* renamed from: c */
    public long m16116c() {
        return this.fileSize;
    }

    /* renamed from: d */
    public String m16117d() {
        return this.fileUri;
    }

    /* renamed from: f */
    public boolean m16118f() {
        return this.isCopied;
    }

    /* renamed from: g */
    public String m16119g() throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("bucket", this.bucket);
            jSONObject.put("filePath", this.filePath);
            jSONObject.put("fileUri", this.fileUri);
            return jSONObject.toString();
        } catch (JSONException e9) {
            C5154j.m20076j(e9);
            return "";
        }
    }

    public FileItem(String str, String str2, String str3) {
        this(str, str2, str3, false);
    }

    public FileItem(String str, String str2, String str3, boolean z8) {
        this.bucket = str;
        this.filePath = str2;
        this.fileUri = str3;
        Uri uri = TextUtils.isEmpty(str3) ? null : Uri.parse(str3);
        this.fileName = CLUtility.m16552k0(str2, uri);
        this.fileSize = CLUtility.m16572p0(str2, uri);
        this.isCopied = z8;
    }
}
