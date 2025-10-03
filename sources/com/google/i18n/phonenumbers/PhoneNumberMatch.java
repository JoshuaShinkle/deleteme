package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.Phonenumber;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class PhoneNumberMatch {
    private final Phonenumber.PhoneNumber number;
    private final String rawString;
    private final int start;

    public PhoneNumberMatch(int i9, String str, Phonenumber.PhoneNumber phoneNumber) {
        if (i9 < 0) {
            throw new IllegalArgumentException("Start index must be >= 0.");
        }
        if (str == null || phoneNumber == null) {
            throw null;
        }
        this.start = i9;
        this.rawString = str;
        this.number = phoneNumber;
    }

    public int end() {
        return this.start + this.rawString.length();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PhoneNumberMatch)) {
            return false;
        }
        PhoneNumberMatch phoneNumberMatch = (PhoneNumberMatch) obj;
        return this.rawString.equals(phoneNumberMatch.rawString) && this.start == phoneNumberMatch.start && this.number.equals(phoneNumberMatch.number);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.start), this.rawString, this.number});
    }

    public Phonenumber.PhoneNumber number() {
        return this.number;
    }

    public String rawString() {
        return this.rawString;
    }

    public int start() {
        return this.start;
    }

    public String toString() {
        int iStart = start();
        int iEnd = end();
        String strValueOf = String.valueOf(this.rawString);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 43);
        sb.append("PhoneNumberMatch [");
        sb.append(iStart);
        sb.append(",");
        sb.append(iEnd);
        sb.append(") ");
        sb.append(strValueOf);
        return sb.toString();
    }
}
