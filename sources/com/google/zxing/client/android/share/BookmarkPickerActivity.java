package com.google.zxing.client.android.share;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import com.google.android.gms.plus.PlusShare;

/* loaded from: classes2.dex */
public final class BookmarkPickerActivity extends ListActivity {
    private static final String[] BOOKMARK_PROJECTION = {PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, "url"};
    private static final String BOOKMARK_SELECTION = "bookmark = 1 AND url IS NOT NULL";
    private static final String TAG = "BookmarkPickerActivity";
    static final int TITLE_COLUMN = 0;
    static final int URL_COLUMN = 1;
    private Cursor cursor;

    @Override // android.app.ListActivity
    public void onListItemClick(ListView listView, View view, int i9, long j9) {
        if (this.cursor.isClosed() || !this.cursor.moveToPosition(i9)) {
            setResult(0);
        } else {
            Intent intent = new Intent();
            intent.addFlags(524288);
            intent.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, this.cursor.getString(0));
            intent.putExtra("url", this.cursor.getString(1));
            setResult(-1, intent);
        }
        finish();
    }

    @Override // android.app.Activity
    public void onPause() {
        Cursor cursor = this.cursor;
        if (cursor != null) {
            cursor.close();
            this.cursor = null;
        }
        super.onPause();
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        Cursor cursorQuery = getContentResolver().query(Uri.parse("content://browser/bookmarks"), BOOKMARK_PROJECTION, BOOKMARK_SELECTION, null, null);
        this.cursor = cursorQuery;
        if (cursorQuery != null) {
            setListAdapter(new BookmarkAdapter(this, this.cursor));
        } else {
            Log.w(TAG, "No cursor returned for bookmark query");
            finish();
        }
    }
}
