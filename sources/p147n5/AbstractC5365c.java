package p147n5;

import com.google.gson.annotations.SerializedName;

/* renamed from: n5.c */
/* loaded from: classes2.dex */
public abstract class AbstractC5365c extends C5363a implements InterfaceC5372j {

    @SerializedName("avatar")
    public final String avname;

    @SerializedName("displayName")
    public final String name;
    public final String userId;
    public final String uuid;

    public AbstractC5365c(String str, String str2, String str3, String str4) {
        this.userId = str;
        this.name = str3;
        this.avname = str4;
        this.uuid = str2;
    }

    @Override // p147n5.InterfaceC5372j
    public final String getAvname() {
        return this.avname;
    }

    @Override // p147n5.InterfaceC5372j
    public final String getUUId() {
        return this.uuid;
    }

    @Override // p147n5.InterfaceC5372j
    public final String getUserId() {
        return this.userId;
    }

    @Override // p147n5.InterfaceC5372j
    public final String getUserName() {
        return this.name;
    }
}
