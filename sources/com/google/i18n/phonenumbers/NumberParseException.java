package com.google.i18n.phonenumbers;

/* loaded from: classes2.dex */
public class NumberParseException extends Exception {
    private ErrorType errorType;
    private String message;

    public enum ErrorType {
        INVALID_COUNTRY_CODE,
        NOT_A_NUMBER,
        TOO_SHORT_AFTER_IDD,
        TOO_SHORT_NSN,
        TOO_LONG
    }

    public NumberParseException(ErrorType errorType, String str) {
        super(str);
        this.message = str;
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    @Override // java.lang.Throwable
    public String toString() {
        String strValueOf = String.valueOf(this.errorType);
        String strValueOf2 = String.valueOf(this.message);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 14 + strValueOf2.length());
        sb.append("Error type: ");
        sb.append(strValueOf);
        sb.append(". ");
        sb.append(strValueOf2);
        return sb.toString();
    }
}
