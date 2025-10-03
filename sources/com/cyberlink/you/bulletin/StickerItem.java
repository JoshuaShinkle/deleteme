package com.cyberlink.you.bulletin;

import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.sticker.StickerObj;

/* loaded from: classes.dex */
public class StickerItem extends StickerObj {

    /* renamed from: o */
    public String f12389o;

    public StickerItem(StickerObj stickerObj) {
        super(stickerObj.m16280e(), stickerObj.m16285j(), stickerObj.m16284i(), stickerObj.m16286k(), stickerObj.m16281f().getTime(), stickerObj.m16283h(), stickerObj.m16282g(), stickerObj.m16288m(), stickerObj.m16287l(), stickerObj.m16289n(), stickerObj.m16279d(), stickerObj.m16278c(), stickerObj.m16276a());
        StickerPackObj stickerPackObjM15293k = C2950b0.m14925x().m15293k(stickerObj.m16284i());
        this.f12389o = stickerPackObjM15293k != null ? stickerPackObjM15293k.m14805i() : null;
    }

    /* renamed from: w */
    public String m14024w() {
        return this.f12389o;
    }
}
