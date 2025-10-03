package com.google.firebase.crashlytics.ndk;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.text.TextUtils;
import com.google.firebase.crashlytics.internal.Logger;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
class JniNativeApi implements NativeApi {
    private static final FilenameFilter APK_FILTER = new FilenameFilter() { // from class: com.google.firebase.crashlytics.ndk.d
        @Override // java.io.FilenameFilter
        public final boolean accept(File file, String str) {
            return JniNativeApi.lambda$static$0(file, str);
        }
    };
    private static final boolean LIB_CRASHLYTICS_LOADED;
    private final Context context;

    static {
        boolean z8;
        try {
            System.loadLibrary("crashlytics");
            z8 = true;
        } catch (UnsatisfiedLinkError e9) {
            Logger.getLogger().m17769e("libcrashlytics could not be loaded. This APK may not have been compiled for this device's architecture. NDK crashes will not be reported to Crashlytics:\n" + e9.getLocalizedMessage());
            z8 = false;
        }
        LIB_CRASHLYTICS_LOADED = z8;
    }

    public JniNativeApi(Context context) {
        this.context = context;
    }

    @TargetApi(21)
    public static void addSplitSourceDirs(List<String> list, PackageInfo packageInfo) {
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        String[] strArr = applicationInfo.splitSourceDirs;
        if (strArr != null) {
            Collections.addAll(list, strArr);
        }
        File file = new File(applicationInfo.dataDir, String.format("files/splitcompat/%s/verified-splits", getVersionCodeAsString(packageInfo)));
        if (!file.exists()) {
            Logger.getLogger().m17767d("No dynamic features found at " + file.getAbsolutePath());
            return;
        }
        File[] fileArrListFiles = file.listFiles(APK_FILTER);
        if (fileArrListFiles == null) {
            fileArrListFiles = new File[0];
        }
        Logger.getLogger().m17767d("Found " + fileArrListFiles.length + " APKs in " + file.getAbsolutePath());
        for (File file2 : fileArrListFiles) {
            Logger.getLogger().m17767d("Adding " + file2.getName() + " to classpath.");
            list.add(file2.getAbsolutePath());
        }
    }

    private static int getPackageInfoFlags() {
        return 9216;
    }

    private static String getVersionCodeAsString(PackageInfo packageInfo) {
        return Build.VERSION.SDK_INT >= 28 ? Long.toString(packageInfo.getLongVersionCode()) : Integer.toString(packageInfo.versionCode);
    }

    public static boolean isAtLeastLollipop() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$0(File file, String str) {
        return str.toLowerCase().endsWith(".apk");
    }

    private native boolean nativeInit(String[] strArr, Object obj);

    @Override // com.google.firebase.crashlytics.ndk.NativeApi
    public boolean initialize(String str, AssetManager assetManager) throws PackageManager.NameNotFoundException {
        String[] strArrMakePackagePaths = makePackagePaths(Build.CPU_ABI);
        if (strArrMakePackagePaths.length < 2) {
            return false;
        }
        return LIB_CRASHLYTICS_LOADED && nativeInit(new String[]{strArrMakePackagePaths[0], strArrMakePackagePaths[1], str}, assetManager);
    }

    public String[] makePackagePaths(String str) throws PackageManager.NameNotFoundException {
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), getPackageInfoFlags());
            ArrayList<String> arrayList = new ArrayList(10);
            arrayList.add(packageInfo.applicationInfo.sourceDir);
            if (isAtLeastLollipop()) {
                addSplitSourceDirs(arrayList, packageInfo);
            }
            String[] strArr = packageInfo.applicationInfo.sharedLibraryFiles;
            if (strArr != null) {
                Collections.addAll(arrayList, strArr);
            }
            ArrayList arrayList2 = new ArrayList(10);
            File parentFile = new File(packageInfo.applicationInfo.nativeLibraryDir).getParentFile();
            if (parentFile != null) {
                arrayList2.add(new File(parentFile, str).getPath());
                if (str.startsWith("arm64")) {
                    arrayList2.add(new File(parentFile, "arm64").getPath());
                } else if (str.startsWith("arm")) {
                    arrayList2.add(new File(parentFile, "arm").getPath());
                }
            }
            for (String str2 : arrayList) {
                if (str2.endsWith(".apk")) {
                    arrayList2.add(str2 + "!/lib/" + str);
                }
            }
            arrayList2.add(System.getProperty("java.library.path"));
            arrayList2.add(packageInfo.applicationInfo.nativeLibraryDir);
            String str3 = File.pathSeparator;
            return new String[]{TextUtils.join(str3, arrayList), TextUtils.join(str3, arrayList2)};
        } catch (PackageManager.NameNotFoundException e9) {
            Logger.getLogger().m17770e("Unable to compose package paths", e9);
            throw new RuntimeException(e9);
        }
    }
}
