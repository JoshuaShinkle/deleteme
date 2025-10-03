package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.utility.CLUtility;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import p116k4.C5140e0;

/* loaded from: classes.dex */
public class RingtonePickerActivity extends BaseActivity {

    /* renamed from: c */
    public C1669b f8719c;

    /* renamed from: d */
    public AdapterView.OnItemClickListener f8720d = new AdapterView.OnItemClickListener() { // from class: com.cyberlink.you.activity.ld
        @Override // android.widget.AdapterView.OnItemClickListener
        public final void onItemClick(AdapterView adapterView, View view, int i9, long j9) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
            this.f10857b.m9623l(adapterView, view, i9, j9);
        }
    };

    /* renamed from: com.cyberlink.you.activity.RingtonePickerActivity$b */
    public static class C1669b extends ArrayAdapter<C1670c> {

        /* renamed from: b */
        public LayoutInflater f8721b;

        /* renamed from: c */
        public int f8722c;

        /* renamed from: com.cyberlink.you.activity.RingtonePickerActivity$b$a */
        public class a {

            /* renamed from: a */
            public TextView f8723a;

            /* renamed from: b */
            public ImageView f8724b;

            public a() {
            }
        }

        public C1669b(Context context) {
            super(context, R.layout.view_item_ringtone);
            this.f8721b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        /* renamed from: c */
        public final int m9629c() {
            return this.f8722c;
        }

        /* renamed from: d */
        public final void m9630d(int i9) {
            this.f8722c = i9;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = this.f8721b.inflate(R.layout.view_item_ringtone, viewGroup, false);
                aVar = new a();
                aVar.f8723a = (TextView) view.findViewById(R.id.ringtoneName);
                aVar.f8724b = (ImageView) view.findViewById(R.id.ringtoneCheckBox);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            C1670c c1670c = (C1670c) getItem(i9);
            if (c1670c != null) {
                aVar.f8723a.setText(c1670c.f8726a);
                aVar.f8724b.setVisibility(i9 != this.f8722c ? 4 : 0);
            }
            return view;
        }
    }

    /* renamed from: com.cyberlink.you.activity.RingtonePickerActivity$c */
    public static class C1670c {

        /* renamed from: a */
        public String f8726a;

        /* renamed from: b */
        public Uri f8727b;

        public C1670c(String str, Uri uri) {
            this.f8726a = str;
            this.f8727b = uri;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public /* synthetic */ void m9623l(AdapterView adapterView, View view, int i9, long j9) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        C1670c c1670c = (C1670c) this.f8719c.getItem(i9);
        if (c1670c != null) {
            C5140e0 c5140e0 = new C5140e0();
            c5140e0.m20025q(5);
            c5140e0.m20017i(this, c1670c.f8727b);
            this.f8719c.m9630d(i9);
            this.f8719c.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n */
    public /* synthetic */ void m9624n(View view) {
        m9626o();
    }

    /* renamed from: k */
    public final List<C1670c> m9625k() {
        ArrayList arrayList = new ArrayList();
        int i9 = 0;
        while (true) {
            String[] strArr = CLUtility.f14428d;
            if (i9 >= strArr.length) {
                break;
            }
            arrayList.add(new C1670c(CLUtility.f14427c[i9], Uri.parse("android.resource://" + getPackageName() + "/raw/messagenotify_" + strArr[i9])));
            i9++;
        }
        RingtoneManager ringtoneManager = new RingtoneManager((Activity) this);
        ringtoneManager.setType(2);
        Cursor cursor = ringtoneManager.getCursor();
        while (cursor.moveToNext()) {
            arrayList.add(new C1670c(cursor.getString(1), ringtoneManager.getRingtoneUri(cursor.getPosition())));
        }
        return arrayList;
    }

    /* renamed from: o */
    public final void m9626o() {
        Intent intent = new Intent();
        C1669b c1669b = this.f8719c;
        C1670c c1670c = (C1670c) c1669b.getItem(c1669b.m9629c());
        if (c1670c != null) {
            intent.putExtra("android.intent.extra.ringtone.PICKED_URI", c1670c.f8727b);
        }
        setResult(-1, intent);
        finish();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m9626o();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ringtone_picker);
        findViewById(R.id.RingtoneBackBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.md
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10891b.m9624n(view);
            }
        });
        ListView listView = (ListView) findViewById(R.id.RingtoneList);
        listView.setOnItemClickListener(this.f8720d);
        C1669b c1669b = new C1669b(this);
        this.f8719c = c1669b;
        listView.setAdapter((ListAdapter) c1669b);
        Uri uriM7433H0 = Globals.m7388i0().m7433H0(false);
        List<C1670c> listM9625k = m9625k();
        for (int i9 = 0; i9 < listM9625k.size(); i9++) {
            if (uriM7433H0.equals(listM9625k.get(i9).f8727b)) {
                this.f8719c.m9630d(i9);
            }
            this.f8719c.add(listM9625k.get(i9));
        }
    }
}
