package com.google.zxing.client.android.encode;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.C4453R;
import com.google.zxing.client.android.Contents;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.result.AddressBookParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ResultParser;
import com.google.zxing.common.BitMatrix;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

/* loaded from: classes2.dex */
final class QRCodeEncoder {
    private static final int BLACK = -16777216;
    private static final String TAG = "QRCodeEncoder";
    private static final int WHITE = -1;
    private final Context activity;
    private String contents;
    private final int dimension;
    private String displayContents;
    private BarcodeFormat format;
    private String title;
    private final boolean useVCard;

    public QRCodeEncoder(Context context, Intent intent, int i9, boolean z8) throws Throwable {
        this.activity = context;
        this.dimension = i9;
        this.useVCard = z8;
        String action = intent.getAction();
        if (action.equals(Intents.Encode.ACTION)) {
            encodeContentsFromZXingIntent(intent);
        } else if (action.equals("android.intent.action.SEND")) {
            encodeContentsFromShareIntent(intent);
        }
    }

    private void encodeContentsFromShareIntent(Intent intent) throws Throwable {
        if (intent.hasExtra("android.intent.extra.STREAM")) {
            encodeFromStreamExtra(intent);
        } else {
            encodeFromTextExtras(intent);
        }
    }

    private boolean encodeContentsFromZXingIntent(Intent intent) {
        String stringExtra = intent.getStringExtra(Intents.Encode.FORMAT);
        this.format = null;
        if (stringExtra != null) {
            try {
                this.format = BarcodeFormat.valueOf(stringExtra);
            } catch (IllegalArgumentException unused) {
            }
        }
        BarcodeFormat barcodeFormat = this.format;
        if (barcodeFormat == null || barcodeFormat == BarcodeFormat.QR_CODE) {
            String stringExtra2 = intent.getStringExtra(Intents.Encode.TYPE);
            if (stringExtra2 == null || stringExtra2.isEmpty()) {
                return false;
            }
            this.format = BarcodeFormat.QR_CODE;
            encodeQRCodeContents(intent, stringExtra2);
        } else {
            String stringExtra3 = intent.getStringExtra(Intents.Encode.DATA);
            if (stringExtra3 != null && !stringExtra3.isEmpty()) {
                this.contents = stringExtra3;
                this.displayContents = stringExtra3;
                this.title = this.activity.getString(C4453R.string.contents_text);
            }
        }
        String str = this.contents;
        return (str == null || str.isEmpty()) ? false : true;
    }

    private void encodeFromStreamExtra(Intent intent) throws Throwable {
        InputStream inputStreamOpenInputStream;
        this.format = BarcodeFormat.QR_CODE;
        Bundle extras = intent.getExtras();
        if (extras == null) {
            throw new WriterException("No extras");
        }
        Uri uri = (Uri) extras.getParcelable("android.intent.extra.STREAM");
        if (uri == null) {
            throw new WriterException("No EXTRA_STREAM");
        }
        InputStream inputStream = null;
        try {
            try {
                inputStreamOpenInputStream = this.activity.getContentResolver().openInputStream(uri);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e9) {
            e = e9;
        }
        try {
            if (inputStreamOpenInputStream == null) {
                throw new WriterException("Can't open stream for " + uri);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[2048];
            while (true) {
                int i9 = inputStreamOpenInputStream.read(bArr);
                if (i9 <= 0) {
                    break;
                } else {
                    byteArrayOutputStream.write(bArr, 0, i9);
                }
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String str = new String(byteArray, 0, byteArray.length, "UTF-8");
            try {
                inputStreamOpenInputStream.close();
            } catch (IOException unused) {
            }
            String str2 = TAG;
            Log.d(str2, "Encoding share intent content:");
            Log.d(str2, str);
            ParsedResult result = ResultParser.parseResult(new Result(str, byteArray, null, BarcodeFormat.QR_CODE));
            if (!(result instanceof AddressBookParsedResult)) {
                throw new WriterException("Result was not an address");
            }
            encodeQRCodeContents((AddressBookParsedResult) result);
            String str3 = this.contents;
            if (str3 == null || str3.isEmpty()) {
                throw new WriterException("No content to encode");
            }
        } catch (IOException e10) {
            e = e10;
            throw new WriterException(e);
        } catch (Throwable th2) {
            th = th2;
            inputStream = inputStreamOpenInputStream;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    private void encodeFromTextExtras(Intent intent) throws WriterException {
        String strTrim = ContactEncoder.trim(intent.getStringExtra("android.intent.extra.TEXT"));
        if (strTrim == null && (strTrim = ContactEncoder.trim(intent.getStringExtra("android.intent.extra.HTML_TEXT"))) == null && (strTrim = ContactEncoder.trim(intent.getStringExtra("android.intent.extra.SUBJECT"))) == null) {
            String[] stringArrayExtra = intent.getStringArrayExtra("android.intent.extra.EMAIL");
            strTrim = stringArrayExtra != null ? ContactEncoder.trim(stringArrayExtra[0]) : "?";
        }
        if (strTrim == null || strTrim.isEmpty()) {
            throw new WriterException("Empty EXTRA_TEXT");
        }
        this.contents = strTrim;
        this.format = BarcodeFormat.QR_CODE;
        if (intent.hasExtra("android.intent.extra.SUBJECT")) {
            this.displayContents = intent.getStringExtra("android.intent.extra.SUBJECT");
        } else if (intent.hasExtra("android.intent.extra.TITLE")) {
            this.displayContents = intent.getStringExtra("android.intent.extra.TITLE");
        } else {
            this.displayContents = this.contents;
        }
        this.title = this.activity.getString(C4453R.string.contents_text);
    }

    private void encodeQRCodeContents(Intent intent, String str) {
        str.hashCode();
        switch (str) {
            case "PHONE_TYPE":
                String strTrim = ContactEncoder.trim(intent.getStringExtra(Intents.Encode.DATA));
                if (strTrim != null) {
                    this.contents = "tel:" + strTrim;
                    this.displayContents = PhoneNumberUtils.formatNumber(strTrim);
                    this.title = this.activity.getString(C4453R.string.contents_phone);
                    break;
                }
                break;
            case "CONTACT_TYPE":
                Bundle bundleExtra = intent.getBundleExtra(Intents.Encode.DATA);
                if (bundleExtra != null) {
                    String string = bundleExtra.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                    String string2 = bundleExtra.getString("company");
                    String string3 = bundleExtra.getString("postal");
                    List<String> allBundleValues = getAllBundleValues(bundleExtra, Contents.PHONE_KEYS);
                    List<String> allBundleValues2 = getAllBundleValues(bundleExtra, Contents.PHONE_TYPE_KEYS);
                    List<String> allBundleValues3 = getAllBundleValues(bundleExtra, Contents.EMAIL_KEYS);
                    String string4 = bundleExtra.getString(Contents.URL_KEY);
                    String[] strArrEncode = (this.useVCard ? new VCardContactEncoder() : new MECARDContactEncoder()).encode(Collections.singletonList(string), string2, Collections.singletonList(string3), allBundleValues, allBundleValues2, allBundleValues3, string4 == null ? null : Collections.singletonList(string4), bundleExtra.getString(Contents.NOTE_KEY));
                    if (!strArrEncode[1].isEmpty()) {
                        this.contents = strArrEncode[0];
                        this.displayContents = strArrEncode[1];
                        this.title = this.activity.getString(C4453R.string.contents_contact);
                        break;
                    }
                }
                break;
            case "SMS_TYPE":
                String strTrim2 = ContactEncoder.trim(intent.getStringExtra(Intents.Encode.DATA));
                if (strTrim2 != null) {
                    this.contents = "sms:" + strTrim2;
                    this.displayContents = PhoneNumberUtils.formatNumber(strTrim2);
                    this.title = this.activity.getString(C4453R.string.contents_sms);
                    break;
                }
                break;
            case "LOCATION_TYPE":
                Bundle bundleExtra2 = intent.getBundleExtra(Intents.Encode.DATA);
                if (bundleExtra2 != null) {
                    float f9 = bundleExtra2.getFloat("LAT", Float.MAX_VALUE);
                    float f10 = bundleExtra2.getFloat("LONG", Float.MAX_VALUE);
                    if (f9 != Float.MAX_VALUE && f10 != Float.MAX_VALUE) {
                        this.contents = "geo:" + f9 + ',' + f10;
                        StringBuilder sb = new StringBuilder();
                        sb.append(f9);
                        sb.append(",");
                        sb.append(f10);
                        this.displayContents = sb.toString();
                        this.title = this.activity.getString(C4453R.string.contents_location);
                        break;
                    }
                }
                break;
            case "TEXT_TYPE":
                String stringExtra = intent.getStringExtra(Intents.Encode.DATA);
                if (stringExtra != null && !stringExtra.isEmpty()) {
                    this.contents = stringExtra;
                    this.displayContents = stringExtra;
                    this.title = this.activity.getString(C4453R.string.contents_text);
                    break;
                }
                break;
            case "EMAIL_TYPE":
                String strTrim3 = ContactEncoder.trim(intent.getStringExtra(Intents.Encode.DATA));
                if (strTrim3 != null) {
                    this.contents = "mailto:" + strTrim3;
                    this.displayContents = strTrim3;
                    this.title = this.activity.getString(C4453R.string.contents_email);
                    break;
                }
                break;
        }
    }

    private static List<String> getAllBundleValues(Bundle bundle, String[] strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            Object obj = bundle.get(str);
            arrayList.add(obj == null ? null : obj.toString());
        }
        return arrayList;
    }

    private static String guessAppropriateEncoding(CharSequence charSequence) {
        for (int i9 = 0; i9 < charSequence.length(); i9++) {
            if (charSequence.charAt(i9) > 255) {
                return "UTF-8";
            }
        }
        return null;
    }

    private static List<String> toList(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        return Arrays.asList(strArr);
    }

    public Bitmap encodeAsBitmap() {
        EnumMap enumMap;
        String str = this.contents;
        if (str == null) {
            return null;
        }
        String strGuessAppropriateEncoding = guessAppropriateEncoding(str);
        if (strGuessAppropriateEncoding != null) {
            EnumMap enumMap2 = new EnumMap(EncodeHintType.class);
            enumMap2.put((EnumMap) EncodeHintType.CHARACTER_SET, (EncodeHintType) strGuessAppropriateEncoding);
            enumMap = enumMap2;
        } else {
            enumMap = null;
        }
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BarcodeFormat barcodeFormat = this.format;
            int i9 = this.dimension;
            BitMatrix bitMatrixEncode = multiFormatWriter.encode(str, barcodeFormat, i9, i9, enumMap);
            int width = bitMatrixEncode.getWidth();
            int height = bitMatrixEncode.getHeight();
            int[] iArr = new int[width * height];
            for (int i10 = 0; i10 < height; i10++) {
                int i11 = i10 * width;
                for (int i12 = 0; i12 < width; i12++) {
                    iArr[i11 + i12] = bitMatrixEncode.get(i12, i10) ? BLACK : -1;
                }
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
            return bitmapCreateBitmap;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public String getContents() {
        return this.contents;
    }

    public String getDisplayContents() {
        return this.displayContents;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isUseVCard() {
        return this.useVCard;
    }

    private void encodeQRCodeContents(AddressBookParsedResult addressBookParsedResult) {
        String[] strArrEncode = (this.useVCard ? new VCardContactEncoder() : new MECARDContactEncoder()).encode(toList(addressBookParsedResult.getNames()), addressBookParsedResult.getOrg(), toList(addressBookParsedResult.getAddresses()), toList(addressBookParsedResult.getPhoneNumbers()), null, toList(addressBookParsedResult.getEmails()), toList(addressBookParsedResult.getURLs()), null);
        if (strArrEncode[1].isEmpty()) {
            return;
        }
        this.contents = strArrEncode[0];
        this.displayContents = strArrEncode[1];
        this.title = this.activity.getString(C4453R.string.contents_contact);
    }
}
