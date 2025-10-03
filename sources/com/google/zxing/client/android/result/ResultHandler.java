package com.google.zxing.client.android.result;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.util.Log;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.zxing.Result;
import com.google.zxing.client.android.C4453R;
import com.google.zxing.client.android.Contents;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.LocaleManager;
import com.google.zxing.client.android.PreferencesActivity;
import com.google.zxing.client.android.book.SearchBookContentsActivity;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.ResultParser;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public abstract class ResultHandler {
    public static final int MAX_BUTTON_COUNT = 4;
    private static final int NO_TYPE = -1;
    private static final String TAG = "ResultHandler";
    private final Activity activity;
    private final String customProductSearch;
    private final Result rawResult;
    private final ParsedResult result;
    private static final String[] EMAIL_TYPE_STRINGS = {"home", "work", "mobile"};
    private static final String[] PHONE_TYPE_STRINGS = {"home", "work", "mobile", "fax", "pager", "main"};
    private static final String[] ADDRESS_TYPE_STRINGS = {"home", "work"};
    private static final int[] EMAIL_TYPE_VALUES = {1, 2, 4};
    private static final int[] PHONE_TYPE_VALUES = {1, 3, 2, 4, 6, 12};
    private static final int[] ADDRESS_TYPE_VALUES = {1, 2};

    public ResultHandler(Activity activity, ParsedResult parsedResult) {
        this(activity, parsedResult, null);
    }

    private static int doToContractType(String str, String[] strArr, int[] iArr) {
        if (str == null) {
            return -1;
        }
        for (int i9 = 0; i9 < strArr.length; i9++) {
            String str2 = strArr[i9];
            if (str.startsWith(str2) || str.startsWith(str2.toUpperCase(Locale.ENGLISH))) {
                return iArr[i9];
            }
        }
        return -1;
    }

    private String parseCustomSearchURL() {
        String string = PreferenceManager.getDefaultSharedPreferences(this.activity).getString(PreferencesActivity.KEY_CUSTOM_PRODUCT_SEARCH, null);
        if (string == null || !string.trim().isEmpty()) {
            return string;
        }
        return null;
    }

    private static void putExtra(Intent intent, String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            return;
        }
        intent.putExtra(str, str2);
    }

    private static int toAddressContractType(String str) {
        return doToContractType(str, ADDRESS_TYPE_STRINGS, ADDRESS_TYPE_VALUES);
    }

    private static int toEmailContractType(String str) {
        return doToContractType(str, EMAIL_TYPE_STRINGS, EMAIL_TYPE_VALUES);
    }

    private static int toPhoneContractType(String str) {
        return doToContractType(str, PHONE_TYPE_STRINGS, PHONE_TYPE_VALUES);
    }

    public final void addContact(String[] strArr, String[] strArr2, String str, String[] strArr3, String[] strArr4, String[] strArr5, String[] strArr6, String str2, String str3, String str4, String str5, String str6, String str7, String[] strArr7, String str8, String[] strArr8) {
        int addressContractType;
        int emailContractType;
        int phoneContractType;
        Intent intent = new Intent("android.intent.action.INSERT_OR_EDIT", ContactsContract.Contacts.CONTENT_URI);
        intent.setType("vnd.android.cursor.item/contact");
        putExtra(intent, AppMeasurementSdk.ConditionalUserProperty.NAME, strArr != null ? strArr[0] : null);
        putExtra(intent, "phonetic_name", str);
        int iMin = Math.min(strArr3 != null ? strArr3.length : 0, Contents.PHONE_KEYS.length);
        for (int i9 = 0; i9 < iMin; i9++) {
            putExtra(intent, Contents.PHONE_KEYS[i9], strArr3[i9]);
            if (strArr4 != null && i9 < strArr4.length && (phoneContractType = toPhoneContractType(strArr4[i9])) >= 0) {
                intent.putExtra(Contents.PHONE_TYPE_KEYS[i9], phoneContractType);
            }
        }
        int iMin2 = Math.min(strArr5 != null ? strArr5.length : 0, Contents.EMAIL_KEYS.length);
        for (int i10 = 0; i10 < iMin2; i10++) {
            putExtra(intent, Contents.EMAIL_KEYS[i10], strArr5[i10]);
            if (strArr6 != null && i10 < strArr6.length && (emailContractType = toEmailContractType(strArr6[i10])) >= 0) {
                intent.putExtra(Contents.EMAIL_TYPE_KEYS[i10], emailContractType);
            }
        }
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        if (strArr7 != null) {
            int length = strArr7.length;
            int i11 = 0;
            while (true) {
                if (i11 >= length) {
                    break;
                }
                String str9 = strArr7[i11];
                if (str9 != null && !str9.isEmpty()) {
                    ContentValues contentValues = new ContentValues(2);
                    contentValues.put("mimetype", "vnd.android.cursor.item/website");
                    contentValues.put("data1", str9);
                    arrayList.add(contentValues);
                    break;
                }
                i11++;
            }
        }
        if (str8 != null) {
            ContentValues contentValues2 = new ContentValues(3);
            contentValues2.put("mimetype", "vnd.android.cursor.item/contact_event");
            contentValues2.put("data2", (Integer) 3);
            contentValues2.put("data1", str8);
            arrayList.add(contentValues2);
        }
        if (strArr2 != null) {
            int length2 = strArr2.length;
            int i12 = 0;
            while (true) {
                if (i12 >= length2) {
                    break;
                }
                String str10 = strArr2[i12];
                if (str10 != null && !str10.isEmpty()) {
                    ContentValues contentValues3 = new ContentValues(3);
                    contentValues3.put("mimetype", "vnd.android.cursor.item/nickname");
                    contentValues3.put("data2", (Integer) 1);
                    contentValues3.put("data1", str10);
                    arrayList.add(contentValues3);
                    break;
                }
                i12++;
            }
        }
        if (!arrayList.isEmpty()) {
            intent.putParcelableArrayListExtra("data", arrayList);
        }
        StringBuilder sb = new StringBuilder();
        if (str2 != null) {
            sb.append('\n');
            sb.append(str2);
        }
        if (strArr8 != null) {
            sb.append('\n');
            sb.append(strArr8[0]);
            sb.append(',');
            sb.append(strArr8[1]);
        }
        if (sb.length() > 0) {
            putExtra(intent, "notes", sb.substring(1));
        }
        putExtra(intent, "im_handle", str3);
        putExtra(intent, "postal", str4);
        if (str5 != null && (addressContractType = toAddressContractType(str5)) >= 0) {
            intent.putExtra("postal_type", addressContractType);
        }
        putExtra(intent, "company", str6);
        putExtra(intent, "job_title", str7);
        launchIntent(intent);
    }

    public final void addEmailOnlyContact(String[] strArr, String[] strArr2) {
        addContact(null, null, null, null, null, strArr, strArr2, null, null, null, null, null, null, null, null, null);
    }

    public final void addPhoneOnlyContact(String[] strArr, String[] strArr2) {
        addContact(null, null, null, strArr, strArr2, null, null, null, null, null, null, null, null, null, null, null);
    }

    public boolean areContentsSecure() {
        return false;
    }

    public final void dialPhone(String str) {
        launchIntent(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str)));
    }

    public final void dialPhoneFromUri(String str) {
        launchIntent(new Intent("android.intent.action.DIAL", Uri.parse(str)));
    }

    public final String fillInCustomSearchURL(String str) throws UnsupportedEncodingException {
        if (this.customProductSearch == null) {
            return str;
        }
        try {
            str = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
        }
        String strReplaceFirst = this.customProductSearch;
        Result result = this.rawResult;
        if (result != null) {
            strReplaceFirst = strReplaceFirst.replaceFirst("%f(?![0-9a-f])", result.getBarcodeFormat().toString());
            if (strReplaceFirst.contains("%t")) {
                strReplaceFirst = strReplaceFirst.replace("%t", ResultParser.parseResult(this.rawResult).getType().toString());
            }
        }
        return strReplaceFirst.replace("%s", str);
    }

    public final Activity getActivity() {
        return this.activity;
    }

    public abstract int getButtonCount();

    public abstract int getButtonText(int i9);

    public Integer getDefaultButtonID() {
        return null;
    }

    public final void getDirections(double d9, double d10) {
        launchIntent(new Intent("android.intent.action.VIEW", Uri.parse("http://maps.google." + LocaleManager.getCountryTLD(this.activity) + "/maps?f=d&daddr=" + d9 + ',' + d10)));
    }

    public CharSequence getDisplayContents() {
        return this.result.getDisplayResult().replace(StringUtils.f19107CR, "");
    }

    public abstract int getDisplayTitle();

    public final ParsedResult getResult() {
        return this.result;
    }

    public final ParsedResultType getType() {
        return this.result.getType();
    }

    public abstract void handleButtonPress(int i9);

    public final boolean hasCustomProductSearch() {
        return this.customProductSearch != null;
    }

    public final void launchIntent(Intent intent) {
        try {
            rawLaunchIntent(intent);
        } catch (ActivityNotFoundException unused) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
            builder.setTitle(C4453R.string.app_name);
            builder.setMessage(C4453R.string.msg_intent_failed);
            builder.setPositiveButton(C4453R.string.button_ok, (DialogInterface.OnClickListener) null);
            builder.show();
        }
    }

    public final void openBookSearch(String str) {
        launchIntent(new Intent("android.intent.action.VIEW", Uri.parse("http://books.google." + LocaleManager.getBookSearchCountryTLD(this.activity) + "/books?vid=isbn" + str)));
    }

    public final void openMap(String str) {
        launchIntent(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    public final void openProductSearch(String str) {
        launchIntent(new Intent("android.intent.action.VIEW", Uri.parse("http://www.google." + LocaleManager.getProductSearchCountryTLD(this.activity) + "/m/products?q=" + str + "&source=zxing")));
    }

    public final void openURL(String str) {
        if (str.startsWith("HTTP://")) {
            str = "http" + str.substring(4);
        } else if (str.startsWith("HTTPS://")) {
            str = "https" + str.substring(5);
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        try {
            launchIntent(intent);
        } catch (ActivityNotFoundException unused) {
            Log.w(TAG, "Nothing available to handle " + intent);
        }
    }

    public final void rawLaunchIntent(Intent intent) {
        if (intent != null) {
            intent.addFlags(524288);
            Log.d(TAG, "Launching intent: " + intent + " with extras: " + intent.getExtras());
            this.activity.startActivity(intent);
        }
    }

    public final void searchBookContents(String str) {
        Intent intent = new Intent(Intents.SearchBookContents.ACTION);
        intent.setClassName(this.activity, SearchBookContentsActivity.class.getName());
        putExtra(intent, Intents.SearchBookContents.ISBN, str);
        launchIntent(intent);
    }

    public final void searchMap(String str) {
        launchIntent(new Intent("android.intent.action.VIEW", Uri.parse("geo:0,0?q=" + Uri.encode(str))));
    }

    public final void sendEmail(String[] strArr, String[] strArr2, String[] strArr3, String str, String str2) {
        Intent intent = new Intent("android.intent.action.SEND", Uri.parse("mailto:"));
        if (strArr != null && strArr.length != 0) {
            intent.putExtra("android.intent.extra.EMAIL", strArr);
        }
        if (strArr2 != null && strArr2.length != 0) {
            intent.putExtra("android.intent.extra.CC", strArr2);
        }
        if (strArr3 != null && strArr3.length != 0) {
            intent.putExtra("android.intent.extra.BCC", strArr3);
        }
        putExtra(intent, "android.intent.extra.SUBJECT", str);
        putExtra(intent, "android.intent.extra.TEXT", str2);
        intent.setType("text/plain");
        launchIntent(intent);
    }

    public final void sendMMS(String str, String str2, String str3) {
        sendMMSFromUri("mmsto:" + str, str2, str3);
    }

    public final void sendMMSFromUri(String str, String str2, String str3) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse(str));
        if (str2 == null || str2.isEmpty()) {
            putExtra(intent, "subject", this.activity.getString(C4453R.string.msg_default_mms_subject));
        } else {
            putExtra(intent, "subject", str2);
        }
        putExtra(intent, "sms_body", str3);
        intent.putExtra("compose_mode", true);
        launchIntent(intent);
    }

    public final void sendSMS(String str, String str2) {
        sendSMSFromUri("smsto:" + str, str2);
    }

    public final void sendSMSFromUri(String str, String str2) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse(str));
        putExtra(intent, "sms_body", str2);
        intent.putExtra("compose_mode", true);
        launchIntent(intent);
    }

    public final void shareByEmail(String str) {
        sendEmail(null, null, null, null, str);
    }

    public final void shareBySMS(String str) {
        sendSMSFromUri("smsto:", str);
    }

    public final void webSearch(String str) {
        Intent intent = new Intent("android.intent.action.WEB_SEARCH");
        intent.putExtra(SearchIntents.EXTRA_QUERY, str);
        launchIntent(intent);
    }

    public ResultHandler(Activity activity, ParsedResult parsedResult, Result result) {
        this.result = parsedResult;
        this.activity = activity;
        this.rawResult = result;
        this.customProductSearch = parseCustomSearchURL();
    }
}
