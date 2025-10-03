package com.google.zxing.client.android.history;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.zxing.client.android.C4453R;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.Intents;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public final class HistoryActivity extends ListActivity {
    private static final String TAG = "HistoryActivity";
    private ArrayAdapter<HistoryItem> adapter;
    private HistoryManager historyManager;
    private CharSequence originalTitle;

    private void reloadHistoryItems() throws Throwable {
        List<HistoryItem> listBuildHistoryItems = this.historyManager.buildHistoryItems();
        this.adapter.clear();
        Iterator<T> it = listBuildHistoryItems.iterator();
        while (it.hasNext()) {
            this.adapter.add((HistoryItem) it.next());
        }
        setTitle(((Object) this.originalTitle) + " (" + this.adapter.getCount() + ')');
        if (this.adapter.isEmpty()) {
            this.adapter.add(new HistoryItem(null, null, null));
        }
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem menuItem) throws Throwable {
        this.historyManager.deleteHistoryItem(menuItem.getItemId());
        reloadHistoryItems();
        return true;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.historyManager = new HistoryManager(this);
        HistoryItemAdapter historyItemAdapter = new HistoryItemAdapter(this);
        this.adapter = historyItemAdapter;
        setListAdapter(historyItemAdapter);
        registerForContextMenu(getListView());
        this.originalTitle = getTitle();
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        int i9 = ((AdapterView.AdapterContextMenuInfo) contextMenuInfo).position;
        if (i9 >= this.adapter.getCount() || this.adapter.getItem(i9).getResult() != null) {
            contextMenu.add(0, i9, i9, C4453R.string.history_clear_one_history_text);
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.historyManager.hasHistoryItems()) {
            getMenuInflater().inflate(C4453R.menu.history, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override // android.app.ListActivity
    public void onListItemClick(ListView listView, View view, int i9, long j9) {
        if (this.adapter.getItem(i9).getResult() != null) {
            Intent intent = new Intent(this, (Class<?>) CaptureActivity.class);
            intent.putExtra(Intents.History.ITEM_NUMBER, i9);
            setResult(-1, intent);
            finish();
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return true;
    }

    @Override // android.app.Activity
    public void onResume() throws Throwable {
        super.onResume();
        reloadHistoryItems();
    }
}
