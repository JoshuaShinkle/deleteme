package com.google.firebase.crashlytics.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class DevelopmentPlatformProvider {
    private static final String FLUTTER_ASSET_FILE = "flutter_assets/NOTICES.Z";
    private static final String FLUTTER_PLATFORM = "Flutter";
    private static final String UNITY_PLATFORM = "Unity";
    private static final String UNITY_VERSION_FIELD = "com.google.firebase.crashlytics.unity_version";
    private final Context context;
    private DevelopmentPlatform developmentPlatform = null;

    public class DevelopmentPlatform {
        private final String developmentPlatform;
        private final String developmentPlatformVersion;

        private DevelopmentPlatform() throws Resources.NotFoundException {
            int resourcesIdentifier = CommonUtils.getResourcesIdentifier(DevelopmentPlatformProvider.this.context, DevelopmentPlatformProvider.UNITY_VERSION_FIELD, "string");
            if (resourcesIdentifier == 0) {
                if (!DevelopmentPlatformProvider.this.assetFileExists(DevelopmentPlatformProvider.FLUTTER_ASSET_FILE)) {
                    this.developmentPlatform = null;
                    this.developmentPlatformVersion = null;
                    return;
                } else {
                    this.developmentPlatform = DevelopmentPlatformProvider.FLUTTER_PLATFORM;
                    this.developmentPlatformVersion = null;
                    Logger.getLogger().m17773v("Development platform is: Flutter");
                    return;
                }
            }
            this.developmentPlatform = DevelopmentPlatformProvider.UNITY_PLATFORM;
            String string = DevelopmentPlatformProvider.this.context.getResources().getString(resourcesIdentifier);
            this.developmentPlatformVersion = string;
            Logger.getLogger().m17773v("Unity Editor version is: " + string);
        }
    }

    public DevelopmentPlatformProvider(Context context) {
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean assetFileExists(String str) throws IOException {
        if (this.context.getAssets() == null) {
            return false;
        }
        try {
            InputStream inputStreamOpen = this.context.getAssets().open(str);
            if (inputStreamOpen == null) {
                return true;
            }
            inputStreamOpen.close();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    private DevelopmentPlatform initDevelopmentPlatform() {
        if (this.developmentPlatform == null) {
            this.developmentPlatform = new DevelopmentPlatform();
        }
        return this.developmentPlatform;
    }

    public static boolean isUnity(Context context) {
        return CommonUtils.getResourcesIdentifier(context, UNITY_VERSION_FIELD, "string") != 0;
    }

    public String getDevelopmentPlatform() {
        return initDevelopmentPlatform().developmentPlatform;
    }

    public String getDevelopmentPlatformVersion() {
        return initDevelopmentPlatform().developmentPlatformVersion;
    }
}
