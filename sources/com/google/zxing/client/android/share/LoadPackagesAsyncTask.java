package com.google.zxing.client.android.share;

import android.app.ListActivity;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.google.zxing.client.android.C4453R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
final class LoadPackagesAsyncTask extends AsyncTask<Object, Object, List<AppInfo>> {
    private final ListActivity activity;
    private static final String[] PKG_PREFIX_WHITELIST = {"com.google.android.apps."};
    private static final String[] PKG_PREFIX_BLACKLIST = {"com.android.", "android", "com.google.android.", "com.htc"};

    public LoadPackagesAsyncTask(ListActivity listActivity) {
        this.activity = listActivity;
    }

    private static boolean isHidden(String str) {
        if (str == null) {
            return true;
        }
        for (String str2 : PKG_PREFIX_WHITELIST) {
            if (str.startsWith(str2)) {
                return false;
            }
        }
        for (String str3 : PKG_PREFIX_BLACKLIST) {
            if (str.startsWith(str3)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.os.AsyncTask
    public List<AppInfo> doInBackground(Object... objArr) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = this.activity.getPackageManager();
        for (PackageItemInfo packageItemInfo : packageManager.getInstalledApplications(0)) {
            String str = packageItemInfo.packageName;
            if (!isHidden(str)) {
                CharSequence charSequenceLoadLabel = packageItemInfo.loadLabel(packageManager);
                Drawable drawableLoadIcon = packageItemInfo.loadIcon(packageManager);
                if (charSequenceLoadLabel != null) {
                    arrayList.add(new AppInfo(str, charSequenceLoadLabel.toString(), drawableLoadIcon));
                }
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    @Override // android.os.AsyncTask
    public void onPostExecute(final List<AppInfo> list) {
        this.activity.setListAdapter(new ArrayAdapter<AppInfo>(this.activity, C4453R.layout.app_picker_list_item, C4453R.id.app_picker_list_item_label, list) { // from class: com.google.zxing.client.android.share.LoadPackagesAsyncTask.1
            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int i9, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i9, view, viewGroup);
                Drawable icon = ((AppInfo) list.get(i9)).getIcon();
                if (icon != null) {
                    ((ImageView) view2.findViewById(C4453R.id.app_picker_list_item_icon)).setImageDrawable(icon);
                }
                return view2;
            }
        });
    }
}
