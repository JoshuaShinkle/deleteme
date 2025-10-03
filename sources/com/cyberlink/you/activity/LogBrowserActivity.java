package com.cyberlink.you.activity;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import p116k4.C5187v0;
import p209u2.C6369f;

/* loaded from: classes.dex */
public class LogBrowserActivity extends BaseActivity {

    /* renamed from: c */
    public ULogUtility.LogFileType f8041c = ULogUtility.LogFileType.Log;

    /* renamed from: d */
    public File f8042d = null;

    /* renamed from: e */
    public View.OnClickListener f8043e = new View.OnClickListener() { // from class: com.cyberlink.you.activity.h7
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10716b.m8722r(view);
        }
    };

    /* renamed from: f */
    public View.OnClickListener f8044f = new View.OnClickListener() { // from class: com.cyberlink.you.activity.i7
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10747b.m8725v(view);
        }
    };

    /* renamed from: g */
    public AdapterView.OnItemClickListener f8045g = new AdapterView.OnItemClickListener() { // from class: com.cyberlink.you.activity.j7
        @Override // android.widget.AdapterView.OnItemClickListener
        public final void onItemClick(AdapterView adapterView, View view, int i9, long j9) throws Throwable {
            this.f10778b.m8726w(adapterView, view, i9, j9);
        }
    };

    /* renamed from: com.cyberlink.you.activity.LogBrowserActivity$a */
    public class C1548a extends ArrayAdapter<File> {
        public C1548a(Context context, int i9, File[] fileArr) {
            super(context, i9, fileArr);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            TextView textView;
            if (view == null) {
                view = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.simple_list_item_1, (ViewGroup) null);
                textView = (TextView) view.findViewById(R.id.text1);
                textView.setTextColor(LogBrowserActivity.this.m8727o().getResources().getColor(com.cyberlink.p030U.R.color.you_color_normal));
                view.setTag(textView);
            } else {
                textView = (TextView) view.getTag();
            }
            textView.setText(((File) getItem(i9)).getName());
            return view;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r */
    public /* synthetic */ void m8722r(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s */
    public /* synthetic */ void m8723s(DialogInterface dialogInterface, int i9) {
        File[] fileArrListFiles;
        File file = this.f8042d;
        if (file != null && (fileArrListFiles = file.listFiles()) != null && fileArrListFiles.length > 0) {
            for (File file2 : fileArrListFiles) {
                file2.delete();
            }
        }
        dialogInterface.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v */
    public /* synthetic */ void m8725v(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure to delete all files?");
        builder.setPositiveButton(com.cyberlink.p030U.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.k7
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f10814b.m8723s(dialogInterface, i9);
            }
        });
        builder.setNegativeButton(com.cyberlink.p030U.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.l7
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w */
    public /* synthetic */ void m8726w(AdapterView adapterView, View view, int i9, long j9) throws Throwable {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(1);
            File file = (File) adapterView.getItemAtPosition(i9);
            File file2 = new File(ULogUtility.m16678n(this.f8041c, false), file.getName());
            CLUtility.m16595v(file.toString(), file2.toString());
            MediaScannerConnection.scanFile(m8727o(), new String[]{file2.toString()}, null, null);
            intent.setDataAndType(C6369f.m24471m(file2), "text/plain");
            startActivity(intent);
        } catch (Exception e9) {
            C5187v0.m20268d(e9.toString());
            e9.printStackTrace();
        }
    }

    /* renamed from: o */
    public Activity m8727o() {
        return this;
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        String string;
        super.onCreate(bundle);
        setContentView(com.cyberlink.p030U.R.layout.activity_log_browser);
        findViewById(com.cyberlink.p030U.R.id.LogBrowserBackBtn).setOnClickListener(this.f8043e);
        findViewById(com.cyberlink.p030U.R.id.LogDeleteBtn).setOnClickListener(this.f8044f);
        Bundle extras = getIntent().getExtras();
        if (extras != null && (string = extras.getString("type")) != null) {
            this.f8041c = ULogUtility.LogFileType.valueOf(string);
            ((TextView) findViewById(com.cyberlink.p030U.R.id.LogBrowserTopBarTitle)).setText(string);
        }
        m8728q();
    }

    /* renamed from: q */
    public final void m8728q() {
        File fileM16678n = ULogUtility.m16678n(this.f8041c, true);
        this.f8042d = fileM16678n;
        File[] fileArrListFiles = fileM16678n.listFiles();
        Arrays.sort(fileArrListFiles, Collections.reverseOrder());
        ListView listView = (ListView) findViewById(com.cyberlink.p030U.R.id.LogBrowserFileListView);
        listView.setAdapter((ListAdapter) new C1548a(m8727o(), R.layout.simple_list_item_1, fileArrListFiles));
        listView.setOnItemClickListener(this.f8045g);
    }
}
