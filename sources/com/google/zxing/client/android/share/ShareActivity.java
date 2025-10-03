package com.google.zxing.client.android.share;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.android.C4453R;
import com.google.zxing.client.android.Contents;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.clipboard.ClipboardInterface;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public final class ShareActivity extends Activity {
    private static final int PICK_APP = 2;
    private static final int PICK_BOOKMARK = 0;
    private static final int PICK_CONTACT = 1;
    private static final String TAG = "ShareActivity";
    private View clipboardButton;
    private final View.OnClickListener contactListener = new View.OnClickListener() { // from class: com.google.zxing.client.android.share.ShareActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI);
            intent.addFlags(524288);
            ShareActivity.this.startActivityForResult(intent, 1);
        }
    };
    private final View.OnClickListener bookmarkListener = new View.OnClickListener() { // from class: com.google.zxing.client.android.share.ShareActivity.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent("android.intent.action.PICK");
            intent.addFlags(524288);
            intent.setClassName(ShareActivity.this, BookmarkPickerActivity.class.getName());
            ShareActivity.this.startActivityForResult(intent, 0);
        }
    };
    private final View.OnClickListener appListener = new View.OnClickListener() { // from class: com.google.zxing.client.android.share.ShareActivity.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent("android.intent.action.PICK");
            intent.addFlags(524288);
            intent.setClassName(ShareActivity.this, AppPickerActivity.class.getName());
            ShareActivity.this.startActivityForResult(intent, 2);
        }
    };
    private final View.OnClickListener clipboardListener = new View.OnClickListener() { // from class: com.google.zxing.client.android.share.ShareActivity.4
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CharSequence text = ClipboardInterface.getText(ShareActivity.this);
            if (text != null) {
                ShareActivity.this.launchSearch(text.toString());
            }
        }
    };
    private final View.OnKeyListener textListener = new View.OnKeyListener() { // from class: com.google.zxing.client.android.share.ShareActivity.5
        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i9, KeyEvent keyEvent) {
            if (i9 != 66 || keyEvent.getAction() != 0) {
                return false;
            }
            String string = ((TextView) view).getText().toString();
            if (string == null || string.isEmpty()) {
                return true;
            }
            ShareActivity.this.launchSearch(string);
            return true;
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void launchSearch(String str) {
        Intent intent = new Intent(Intents.Encode.ACTION);
        intent.addFlags(524288);
        intent.putExtra(Intents.Encode.TYPE, Contents.Type.TEXT);
        intent.putExtra(Intents.Encode.DATA, str);
        intent.putExtra(Intents.Encode.FORMAT, BarcodeFormat.QR_CODE.toString());
        startActivity(intent);
    }

    private static String massageContactData(String str) {
        if (str.indexOf(10) >= 0) {
            str = str.replace("\n", StringUtils.SPACE);
        }
        return str.indexOf(13) >= 0 ? str.replace(StringUtils.f19107CR, StringUtils.SPACE) : str;
    }

    private void showContactAsBarcode(Uri uri) {
        String string;
        Log.i(TAG, "Showing contact URI as barcode: " + uri);
        if (uri == null) {
            return;
        }
        ContentResolver contentResolver = getContentResolver();
        try {
            Cursor cursorQuery = contentResolver.query(uri, null, null, null, null);
            if (cursorQuery == null) {
                return;
            }
            try {
                if (cursorQuery.moveToFirst()) {
                    String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("_id"));
                    String string3 = cursorQuery.getString(cursorQuery.getColumnIndex("display_name"));
                    int i9 = 0;
                    boolean z8 = cursorQuery.getInt(cursorQuery.getColumnIndex("has_phone_number")) > 0;
                    cursorQuery.close();
                    Bundle bundle = new Bundle();
                    if (string3 != null && !string3.isEmpty()) {
                        bundle.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, massageContactData(string3));
                    }
                    if (z8) {
                        cursorQuery = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id=" + string2, null, null);
                        if (cursorQuery != null) {
                            try {
                                int columnIndex = cursorQuery.getColumnIndex("data1");
                                int columnIndex2 = cursorQuery.getColumnIndex("data2");
                                int i10 = 0;
                                while (cursorQuery.moveToNext()) {
                                    String[] strArr = Contents.PHONE_KEYS;
                                    if (i10 >= strArr.length) {
                                        break;
                                    }
                                    String string4 = cursorQuery.getString(columnIndex);
                                    if (string4 != null && !string4.isEmpty()) {
                                        bundle.putString(strArr[i10], massageContactData(string4));
                                    }
                                    bundle.putInt(Contents.PHONE_TYPE_KEYS[i10], cursorQuery.getInt(columnIndex2));
                                    i10++;
                                }
                                cursorQuery.close();
                            } finally {
                            }
                        }
                    }
                    cursorQuery = contentResolver.query(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI, null, "contact_id=" + string2, null, null);
                    if (cursorQuery != null) {
                        try {
                            if (cursorQuery.moveToNext() && (string = cursorQuery.getString(cursorQuery.getColumnIndex("data1"))) != null && !string.isEmpty()) {
                                bundle.putString("postal", massageContactData(string));
                            }
                            cursorQuery.close();
                        } finally {
                        }
                    }
                    cursorQuery = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, "contact_id=" + string2, null, null);
                    if (cursorQuery != null) {
                        try {
                            int columnIndex3 = cursorQuery.getColumnIndex("data1");
                            while (cursorQuery.moveToNext()) {
                                String[] strArr2 = Contents.EMAIL_KEYS;
                                if (i9 >= strArr2.length) {
                                    break;
                                }
                                String string5 = cursorQuery.getString(columnIndex3);
                                if (string5 != null && !string5.isEmpty()) {
                                    bundle.putString(strArr2[i9], massageContactData(string5));
                                }
                                i9++;
                            }
                        } finally {
                        }
                    }
                    Intent intent = new Intent(Intents.Encode.ACTION);
                    intent.addFlags(524288);
                    intent.putExtra(Intents.Encode.TYPE, Contents.Type.CONTACT);
                    intent.putExtra(Intents.Encode.DATA, bundle);
                    intent.putExtra(Intents.Encode.FORMAT, BarcodeFormat.QR_CODE.toString());
                    Log.i(TAG, "Sending bundle for encoding: " + bundle);
                    startActivity(intent);
                }
            } finally {
            }
        } catch (IllegalArgumentException unused) {
        }
    }

    private void showTextAsBarcode(String str) {
        Log.i(TAG, "Showing text as barcode: " + str);
        if (str == null) {
            return;
        }
        Intent intent = new Intent(Intents.Encode.ACTION);
        intent.addFlags(524288);
        intent.putExtra(Intents.Encode.TYPE, Contents.Type.TEXT);
        intent.putExtra(Intents.Encode.DATA, str);
        intent.putExtra(Intents.Encode.FORMAT, BarcodeFormat.QR_CODE.toString());
        startActivity(intent);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        if (i10 == -1) {
            if (i9 != 0) {
                if (i9 == 1) {
                    showContactAsBarcode(intent.getData());
                    return;
                } else if (i9 != 2) {
                    return;
                }
            }
            showTextAsBarcode(intent.getStringExtra("url"));
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C4453R.layout.share);
        findViewById(C4453R.id.share_contact_button).setOnClickListener(this.contactListener);
        findViewById(C4453R.id.share_bookmark_button).setOnClickListener(this.bookmarkListener);
        findViewById(C4453R.id.share_app_button).setOnClickListener(this.appListener);
        View viewFindViewById = findViewById(C4453R.id.share_clipboard_button);
        this.clipboardButton = viewFindViewById;
        viewFindViewById.setOnClickListener(this.clipboardListener);
        findViewById(C4453R.id.share_text_view).setOnKeyListener(this.textListener);
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.clipboardButton.setEnabled(ClipboardInterface.hasText(this));
    }
}
