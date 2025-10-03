package com.google.zxing.client.android.share;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.List;

/* loaded from: classes2.dex */
public final class AppPickerActivity extends ListActivity {
    private AsyncTask<Object, Object, List<AppInfo>> backgroundTask;

    @Override // android.app.ListActivity
    public void onListItemClick(ListView listView, View view, int i9, long j9) {
        ListAdapter listAdapter = getListAdapter();
        if (i9 < 0 || i9 >= listAdapter.getCount()) {
            setResult(0);
        } else {
            String packageName = ((AppInfo) listAdapter.getItem(i9)).getPackageName();
            Intent intent = new Intent();
            intent.addFlags(524288);
            intent.putExtra("url", "market://details?id=" + packageName);
            setResult(-1, intent);
        }
        finish();
    }

    @Override // android.app.Activity
    public void onPause() {
        AsyncTask<Object, Object, List<AppInfo>> asyncTask = this.backgroundTask;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.backgroundTask = null;
        }
        super.onPause();
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        LoadPackagesAsyncTask loadPackagesAsyncTask = new LoadPackagesAsyncTask(this);
        this.backgroundTask = loadPackagesAsyncTask;
        loadPackagesAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
    }
}
