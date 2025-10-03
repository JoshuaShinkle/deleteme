package com.google.zxing.client.android.result;

import android.app.Activity;
import android.telephony.PhoneNumberUtils;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import com.google.zxing.client.android.C4453R;
import com.google.zxing.client.result.AddressBookParsedResult;
import com.google.zxing.client.result.ParsedResult;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes2.dex */
public final class AddressBookResultHandler extends ResultHandler {
    private static final int[] BUTTON_TEXTS;
    private static final DateFormat[] DATE_FORMATS;
    private int buttonCount;
    private final boolean[] fields;

    static {
        Locale locale = Locale.ENGLISH;
        DateFormat[] dateFormatArr = {new SimpleDateFormat("yyyyMMdd", locale), new SimpleDateFormat("yyyyMMdd'T'HHmmss", locale), new SimpleDateFormat("yyyy-MM-dd", locale), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", locale)};
        DATE_FORMATS = dateFormatArr;
        for (DateFormat dateFormat : dateFormatArr) {
            dateFormat.setLenient(false);
        }
        BUTTON_TEXTS = new int[]{C4453R.string.button_add_contact, C4453R.string.button_show_map, C4453R.string.button_dial, C4453R.string.button_email};
    }

    public AddressBookResultHandler(Activity activity, ParsedResult parsedResult) {
        String str;
        super(activity, parsedResult);
        AddressBookParsedResult addressBookParsedResult = (AddressBookParsedResult) parsedResult;
        String[] addresses = addressBookParsedResult.getAddresses();
        boolean z8 = (addresses == null || addresses.length <= 0 || (str = addresses[0]) == null || str.isEmpty()) ? false : true;
        String[] phoneNumbers = addressBookParsedResult.getPhoneNumbers();
        boolean z9 = phoneNumbers != null && phoneNumbers.length > 0;
        String[] emails = addressBookParsedResult.getEmails();
        this.fields = new boolean[]{true, z8, z9, emails != null && emails.length > 0};
        this.buttonCount = 0;
        for (int i9 = 0; i9 < 4; i9++) {
            if (this.fields[i9]) {
                this.buttonCount++;
            }
        }
    }

    private int mapIndexToAction(int i9) {
        if (i9 < this.buttonCount) {
            int i10 = -1;
            for (int i11 = 0; i11 < 4; i11++) {
                if (this.fields[i11]) {
                    i10++;
                }
                if (i10 == i9) {
                    return i11;
                }
            }
        }
        return -1;
    }

    private static Date parseDate(String str) {
        for (DateFormat dateFormat : DATE_FORMATS) {
            try {
                return dateFormat.parse(str);
            } catch (ParseException unused) {
            }
        }
        return null;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getButtonCount() {
        return this.buttonCount;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getButtonText(int i9) {
        return BUTTON_TEXTS[mapIndexToAction(i9)];
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public CharSequence getDisplayContents() {
        Date date;
        AddressBookParsedResult addressBookParsedResult = (AddressBookParsedResult) getResult();
        StringBuilder sb = new StringBuilder(100);
        ParsedResult.maybeAppend(addressBookParsedResult.getNames(), sb);
        int length = sb.length();
        String pronunciation = addressBookParsedResult.getPronunciation();
        if (pronunciation != null && !pronunciation.isEmpty()) {
            sb.append("\n(");
            sb.append(pronunciation);
            sb.append(')');
        }
        ParsedResult.maybeAppend(addressBookParsedResult.getTitle(), sb);
        ParsedResult.maybeAppend(addressBookParsedResult.getOrg(), sb);
        ParsedResult.maybeAppend(addressBookParsedResult.getAddresses(), sb);
        String[] phoneNumbers = addressBookParsedResult.getPhoneNumbers();
        if (phoneNumbers != null) {
            for (String str : phoneNumbers) {
                if (str != null) {
                    ParsedResult.maybeAppend(PhoneNumberUtils.formatNumber(str), sb);
                }
            }
        }
        ParsedResult.maybeAppend(addressBookParsedResult.getEmails(), sb);
        ParsedResult.maybeAppend(addressBookParsedResult.getURLs(), sb);
        String birthday = addressBookParsedResult.getBirthday();
        if (birthday != null && !birthday.isEmpty() && (date = parseDate(birthday)) != null) {
            ParsedResult.maybeAppend(DateFormat.getDateInstance(2).format(Long.valueOf(date.getTime())), sb);
        }
        ParsedResult.maybeAppend(addressBookParsedResult.getNote(), sb);
        if (length <= 0) {
            return sb.toString();
        }
        SpannableString spannableString = new SpannableString(sb.toString());
        spannableString.setSpan(new StyleSpan(1), 0, length, 0);
        return spannableString;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getDisplayTitle() {
        return C4453R.string.result_address_book;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public void handleButtonPress(int i9) {
        AddressBookParsedResult addressBookParsedResult = (AddressBookParsedResult) getResult();
        String[] addresses = addressBookParsedResult.getAddresses();
        String str = (addresses == null || addresses.length < 1) ? null : addresses[0];
        String[] addressTypes = addressBookParsedResult.getAddressTypes();
        String str2 = (addressTypes == null || addressTypes.length < 1) ? null : addressTypes[0];
        int iMapIndexToAction = mapIndexToAction(i9);
        if (iMapIndexToAction == 0) {
            addContact(addressBookParsedResult.getNames(), addressBookParsedResult.getNicknames(), addressBookParsedResult.getPronunciation(), addressBookParsedResult.getPhoneNumbers(), addressBookParsedResult.getPhoneTypes(), addressBookParsedResult.getEmails(), addressBookParsedResult.getEmailTypes(), addressBookParsedResult.getNote(), addressBookParsedResult.getInstantMessenger(), str, str2, addressBookParsedResult.getOrg(), addressBookParsedResult.getTitle(), addressBookParsedResult.getURLs(), addressBookParsedResult.getBirthday(), addressBookParsedResult.getGeo());
            return;
        }
        if (iMapIndexToAction == 1) {
            searchMap(str);
        } else if (iMapIndexToAction == 2) {
            dialPhone(addressBookParsedResult.getPhoneNumbers()[0]);
        } else {
            if (iMapIndexToAction != 3) {
                return;
            }
            sendEmail(addressBookParsedResult.getEmails(), null, null, null, null);
        }
    }
}
