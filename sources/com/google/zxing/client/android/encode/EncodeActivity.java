package com.google.zxing.client.android.encode;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.C4453R;
import com.google.zxing.client.android.Contents;
import com.google.zxing.client.android.FinishListener;
import com.google.zxing.client.android.Intents;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public final class EncodeActivity extends Activity {
    private static final int MAX_BARCODE_FILENAME_LENGTH = 24;
    private static final Pattern NOT_ALPHANUMERIC = Pattern.compile("[^A-Za-z0-9]");
    private static final String TAG = "EncodeActivity";
    private static final String USE_VCARD_KEY = "USE_VCARD";
    private QRCodeEncoder qrCodeEncoder;

    private static CharSequence makeBarcodeFileName(CharSequence charSequence) {
        String strReplaceAll = NOT_ALPHANUMERIC.matcher(charSequence).replaceAll("_");
        return strReplaceAll.length() > 24 ? strReplaceAll.substring(0, 24) : strReplaceAll;
    }

    private void share() throws Throwable {
        FileOutputStream fileOutputStream;
        QRCodeEncoder qRCodeEncoder = this.qrCodeEncoder;
        if (qRCodeEncoder == null) {
            Log.w(TAG, "No existing barcode to send?");
            return;
        }
        String contents = qRCodeEncoder.getContents();
        if (contents == null) {
            Log.w(TAG, "No existing barcode to send?");
            return;
        }
        try {
            Bitmap bitmapEncodeAsBitmap = qRCodeEncoder.encodeAsBitmap();
            if (bitmapEncodeAsBitmap == null) {
                return;
            }
            File file = new File(new File(Environment.getExternalStorageDirectory(), "BarcodeScanner"), "Barcodes");
            if (!file.exists() && !file.mkdirs()) {
                Log.w(TAG, "Couldn't make dir " + file);
                showErrorMessage(C4453R.string.msg_unmount_usb);
                return;
            }
            File file2 = new File(file, ((Object) makeBarcodeFileName(contents)) + ".png");
            if (!file2.delete()) {
                Log.w(TAG, "Could not delete " + file2);
            }
            FileOutputStream fileOutputStream2 = null;
            try {
                try {
                    fileOutputStream = new FileOutputStream(file2);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (FileNotFoundException e9) {
                e = e9;
            }
            try {
                bitmapEncodeAsBitmap.compress(Bitmap.CompressFormat.PNG, 0, fileOutputStream);
                try {
                    fileOutputStream.close();
                } catch (IOException unused) {
                }
                Intent intent = new Intent("android.intent.action.SEND", Uri.parse("mailto:"));
                intent.putExtra("android.intent.extra.SUBJECT", getString(C4453R.string.app_name) + " - " + qRCodeEncoder.getTitle());
                intent.putExtra("android.intent.extra.TEXT", contents);
                intent.putExtra("android.intent.extra.STREAM", Uri.parse("file://" + file2.getAbsolutePath()));
                intent.setType("image/png");
                intent.addFlags(524288);
                startActivity(Intent.createChooser(intent, null));
            } catch (FileNotFoundException e10) {
                e = e10;
                fileOutputStream2 = fileOutputStream;
                Log.w(TAG, "Couldn't access file " + file2 + " due to " + e);
                showErrorMessage(C4453R.string.msg_unmount_usb);
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException unused2) {
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
        } catch (WriterException e11) {
            Log.w(TAG, e11);
        }
    }

    private void showErrorMessage(int i9) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(i9);
        builder.setPositiveButton(C4453R.string.button_ok, new FinishListener(this));
        builder.setOnCancelListener(new FinishListener(this));
        builder.show();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String action = intent.getAction();
        if (Intents.Encode.ACTION.equals(action) || "android.intent.action.SEND".equals(action)) {
            setContentView(C4453R.layout.encode);
        } else {
            finish();
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C4453R.menu.encode, menu);
        QRCodeEncoder qRCodeEncoder = this.qrCodeEncoder;
        int i9 = qRCodeEncoder != null && qRCodeEncoder.isUseVCard() ? C4453R.string.menu_encode_mecard : C4453R.string.menu_encode_vcard;
        MenuItem menuItemFindItem = menu.findItem(C4453R.id.menu_encode);
        menuItemFindItem.setTitle(i9);
        Intent intent = getIntent();
        if (intent != null) {
            menuItemFindItem.setVisible(Contents.Type.CONTACT.equals(intent.getStringExtra(Intents.Encode.TYPE)));
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int i9 = point.x;
        int i10 = point.y;
        if (i9 >= i10) {
            i9 = i10;
        }
        int i11 = (i9 * 7) / 8;
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        try {
            QRCodeEncoder qRCodeEncoder = new QRCodeEncoder(this, intent, i11, intent.getBooleanExtra(USE_VCARD_KEY, false));
            this.qrCodeEncoder = qRCodeEncoder;
            Bitmap bitmapEncodeAsBitmap = qRCodeEncoder.encodeAsBitmap();
            if (bitmapEncodeAsBitmap == null) {
                Log.w(TAG, "Could not encode barcode");
                showErrorMessage(C4453R.string.msg_encode_contents_failed);
                this.qrCodeEncoder = null;
                return;
            }
            ((ImageView) findViewById(C4453R.id.image_view)).setImageBitmap(bitmapEncodeAsBitmap);
            TextView textView = (TextView) findViewById(C4453R.id.contents_text_view);
            if (intent.getBooleanExtra(Intents.Encode.SHOW_CONTENTS, true)) {
                textView.setText(this.qrCodeEncoder.getDisplayContents());
                setTitle(this.qrCodeEncoder.getTitle());
            } else {
                textView.setText("");
                setTitle("");
            }
        } catch (WriterException e9) {
            Log.w(TAG, "Could not encode barcode", e9);
            showErrorMessage(C4453R.string.msg_encode_contents_failed);
            this.qrCodeEncoder = null;
        }
    }
}
