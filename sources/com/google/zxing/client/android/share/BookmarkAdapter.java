package com.google.zxing.client.android.share;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.zxing.client.android.C4453R;

/* loaded from: classes2.dex */
final class BookmarkAdapter extends BaseAdapter {
    private final Context context;
    private final Cursor cursor;

    public BookmarkAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.cursor.isClosed()) {
            return 0;
        }
        return this.cursor.getCount();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i9) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i9) {
        return i9;
    }

    @Override // android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        if (!(view instanceof LinearLayout)) {
            view = LayoutInflater.from(this.context).inflate(C4453R.layout.bookmark_picker_list_item, viewGroup, false);
        }
        if (!this.cursor.isClosed()) {
            this.cursor.moveToPosition(i9);
            ((TextView) view.findViewById(C4453R.id.bookmark_title)).setText(this.cursor.getString(0));
            ((TextView) view.findViewById(C4453R.id.bookmark_url)).setText(this.cursor.getString(1));
        }
        return view;
    }
}
