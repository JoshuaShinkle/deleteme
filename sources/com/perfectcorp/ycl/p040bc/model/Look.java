package com.perfectcorp.ycl.p040bc.model;

import android.net.Uri;
import com.perfectcorp.model.Model;
import java.util.ArrayList;
import java.util.Date;

/* loaded from: classes2.dex */
public class Look extends Model {
    public Uri attachmentUrl;
    public Uri cover;
    public String description;
    public Long downloadCount;
    public String featureRoomId;

    /* renamed from: id */
    public Long f15982id;
    public String imageUrls;
    public Date lastModified;
    public Long likeCount;
    public String name;
    public ArrayList<String> tags;
    public Long typeId;
    public Long userId;

    public static class ImageUrls extends Model {
        public Uri beforeAfter;
        public Uri cover;
    }

    public ImageUrls getImageUrls() {
        return (ImageUrls) Model.parseFromJSON(ImageUrls.class, this.imageUrls);
    }

    @Override // com.perfectcorp.model.Model
    public Long getKey() {
        return this.f15982id;
    }
}
