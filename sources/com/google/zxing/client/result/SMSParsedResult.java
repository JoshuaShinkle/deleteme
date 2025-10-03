package com.google.zxing.client.result;

/* loaded from: classes2.dex */
public final class SMSParsedResult extends ParsedResult {
    private final String body;
    private final String[] numbers;
    private final String subject;
    private final String[] vias;

    public SMSParsedResult(String str, String str2, String str3, String str4) {
        super(ParsedResultType.SMS);
        this.numbers = new String[]{str};
        this.vias = new String[]{str2};
        this.subject = str3;
        this.body = str4;
    }

    public String getBody() {
        return this.body;
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(100);
        ParsedResult.maybeAppend(this.numbers, sb);
        ParsedResult.maybeAppend(this.subject, sb);
        ParsedResult.maybeAppend(this.body, sb);
        return sb.toString();
    }

    public String[] getNumbers() {
        return this.numbers;
    }

    public String getSMSURI() {
        StringBuilder sb = new StringBuilder();
        sb.append("sms:");
        boolean z8 = true;
        for (int i9 = 0; i9 < this.numbers.length; i9++) {
            if (z8) {
                z8 = false;
            } else {
                sb.append(',');
            }
            sb.append(this.numbers[i9]);
            String[] strArr = this.vias;
            if (strArr != null && strArr[i9] != null) {
                sb.append(";via=");
                sb.append(this.vias[i9]);
            }
        }
        boolean z9 = this.body != null;
        boolean z10 = this.subject != null;
        if (z9 || z10) {
            sb.append('?');
            if (z9) {
                sb.append("body=");
                sb.append(this.body);
            }
            if (z10) {
                if (z9) {
                    sb.append('&');
                }
                sb.append("subject=");
                sb.append(this.subject);
            }
        }
        return sb.toString();
    }

    public String getSubject() {
        return this.subject;
    }

    public String[] getVias() {
        return this.vias;
    }

    public SMSParsedResult(String[] strArr, String[] strArr2, String str, String str2) {
        super(ParsedResultType.SMS);
        this.numbers = strArr;
        this.vias = strArr2;
        this.subject = str;
        this.body = str2;
    }
}
