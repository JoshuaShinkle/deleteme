package com.google.i18n.phonenumbers.nano;

import com.google.android.exoplayer2.extractor.p037ts.TsExtractor;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.InternalNano;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.MessageNano;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.WireFormatNano;
import net.sqlcipher.database.SQLiteDatabase;

/* loaded from: classes2.dex */
public interface Phonemetadata {

    public static final class NumberFormat extends MessageNano {
        private static volatile NumberFormat[] _emptyArray;
        public String domesticCarrierCodeFormattingRule;
        public String format;
        public String[] leadingDigitsPattern;
        public String nationalPrefixFormattingRule;
        public boolean nationalPrefixOptionalWhenFormatting;
        public String pattern;

        public NumberFormat() {
            clear();
        }

        public static NumberFormat[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new NumberFormat[0];
                    }
                }
            }
            return _emptyArray;
        }

        public static NumberFormat parseFrom(byte[] bArr) {
            return (NumberFormat) MessageNano.mergeFrom(new NumberFormat(), bArr);
        }

        public NumberFormat clear() {
            this.pattern = "";
            this.format = "";
            this.leadingDigitsPattern = WireFormatNano.EMPTY_STRING_ARRAY;
            this.nationalPrefixFormattingRule = "";
            this.nationalPrefixOptionalWhenFormatting = false;
            this.domesticCarrierCodeFormattingRule = "";
            this.cachedSize = -1;
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.MessageNano
        public int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize() + CodedOutputByteBufferNano.computeStringSize(1, this.pattern) + CodedOutputByteBufferNano.computeStringSize(2, this.format);
            String[] strArr = this.leadingDigitsPattern;
            if (strArr != null && strArr.length > 0) {
                int i9 = 0;
                int iComputeStringSizeNoTag = 0;
                int i10 = 0;
                while (true) {
                    String[] strArr2 = this.leadingDigitsPattern;
                    if (i9 >= strArr2.length) {
                        break;
                    }
                    String str = strArr2[i9];
                    if (str != null) {
                        i10++;
                        iComputeStringSizeNoTag += CodedOutputByteBufferNano.computeStringSizeNoTag(str);
                    }
                    i9++;
                }
                iComputeSerializedSize = iComputeSerializedSize + iComputeStringSizeNoTag + (i10 * 1);
            }
            if (!this.nationalPrefixFormattingRule.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(4, this.nationalPrefixFormattingRule);
            }
            if (!this.domesticCarrierCodeFormattingRule.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(5, this.domesticCarrierCodeFormattingRule);
            }
            boolean z8 = this.nationalPrefixOptionalWhenFormatting;
            return z8 ? iComputeSerializedSize + CodedOutputByteBufferNano.computeBoolSize(6, z8) : iComputeSerializedSize;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.MessageNano
        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws CodedOutputByteBufferNano.OutOfSpaceException {
            codedOutputByteBufferNano.writeString(1, this.pattern);
            codedOutputByteBufferNano.writeString(2, this.format);
            String[] strArr = this.leadingDigitsPattern;
            if (strArr != null && strArr.length > 0) {
                int i9 = 0;
                while (true) {
                    String[] strArr2 = this.leadingDigitsPattern;
                    if (i9 >= strArr2.length) {
                        break;
                    }
                    String str = strArr2[i9];
                    if (str != null) {
                        codedOutputByteBufferNano.writeString(3, str);
                    }
                    i9++;
                }
            }
            if (!this.nationalPrefixFormattingRule.equals("")) {
                codedOutputByteBufferNano.writeString(4, this.nationalPrefixFormattingRule);
            }
            if (!this.domesticCarrierCodeFormattingRule.equals("")) {
                codedOutputByteBufferNano.writeString(5, this.domesticCarrierCodeFormattingRule);
            }
            boolean z8 = this.nationalPrefixOptionalWhenFormatting;
            if (z8) {
                codedOutputByteBufferNano.writeBool(6, z8);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        public static NumberFormat parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
            return new NumberFormat().mergeFrom(codedInputByteBufferNano);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.MessageNano
        public NumberFormat mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws InvalidProtocolBufferNanoException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 10) {
                    this.pattern = codedInputByteBufferNano.readString();
                } else if (tag == 18) {
                    this.format = codedInputByteBufferNano.readString();
                } else if (tag == 26) {
                    int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 26);
                    String[] strArr = this.leadingDigitsPattern;
                    int length = strArr == null ? 0 : strArr.length;
                    int i9 = repeatedFieldArrayLength + length;
                    String[] strArr2 = new String[i9];
                    if (length != 0) {
                        System.arraycopy(strArr, 0, strArr2, 0, length);
                    }
                    while (length < i9 - 1) {
                        strArr2[length] = codedInputByteBufferNano.readString();
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    strArr2[length] = codedInputByteBufferNano.readString();
                    this.leadingDigitsPattern = strArr2;
                } else if (tag == 34) {
                    this.nationalPrefixFormattingRule = codedInputByteBufferNano.readString();
                } else if (tag == 42) {
                    this.domesticCarrierCodeFormattingRule = codedInputByteBufferNano.readString();
                } else if (tag == 48) {
                    this.nationalPrefixOptionalWhenFormatting = codedInputByteBufferNano.readBool();
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                    return this;
                }
            }
        }
    }

    public static final class PhoneMetadata extends MessageNano {
        private static volatile PhoneMetadata[] _emptyArray;
        public PhoneNumberDesc carrierSpecific;
        public int countryCode;
        public PhoneNumberDesc emergency;
        public PhoneNumberDesc fixedLine;
        public PhoneNumberDesc generalDesc;

        /* renamed from: id */
        public String f15595id;
        public String internationalPrefix;
        public NumberFormat[] intlNumberFormat;
        public String leadingDigits;
        public boolean leadingZeroPossible;
        public boolean mainCountryForCode;
        public PhoneNumberDesc mobile;
        public boolean mobileNumberPortableRegion;
        public String nationalPrefix;
        public String nationalPrefixForParsing;
        public String nationalPrefixTransformRule;
        public PhoneNumberDesc noInternationalDialling;
        public NumberFormat[] numberFormat;
        public PhoneNumberDesc pager;
        public PhoneNumberDesc personalNumber;
        public String preferredExtnPrefix;
        public String preferredInternationalPrefix;
        public PhoneNumberDesc premiumRate;
        public boolean sameMobileAndFixedLinePattern;
        public PhoneNumberDesc sharedCost;
        public PhoneNumberDesc shortCode;
        public PhoneNumberDesc standardRate;
        public PhoneNumberDesc tollFree;
        public PhoneNumberDesc uan;
        public PhoneNumberDesc voicemail;
        public PhoneNumberDesc voip;

        public PhoneMetadata() {
            clear();
        }

        public static PhoneMetadata[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new PhoneMetadata[0];
                    }
                }
            }
            return _emptyArray;
        }

        public static PhoneMetadata parseFrom(byte[] bArr) {
            return (PhoneMetadata) MessageNano.mergeFrom(new PhoneMetadata(), bArr);
        }

        public PhoneMetadata clear() {
            this.generalDesc = null;
            this.fixedLine = null;
            this.mobile = null;
            this.tollFree = null;
            this.premiumRate = null;
            this.sharedCost = null;
            this.personalNumber = null;
            this.voip = null;
            this.pager = null;
            this.uan = null;
            this.emergency = null;
            this.voicemail = null;
            this.shortCode = null;
            this.standardRate = null;
            this.carrierSpecific = null;
            this.noInternationalDialling = null;
            this.f15595id = "";
            this.countryCode = 0;
            this.internationalPrefix = "";
            this.preferredInternationalPrefix = "";
            this.nationalPrefix = "";
            this.preferredExtnPrefix = "";
            this.nationalPrefixForParsing = "";
            this.nationalPrefixTransformRule = "";
            this.sameMobileAndFixedLinePattern = false;
            this.numberFormat = NumberFormat.emptyArray();
            this.intlNumberFormat = NumberFormat.emptyArray();
            this.mainCountryForCode = false;
            this.leadingDigits = "";
            this.leadingZeroPossible = false;
            this.mobileNumberPortableRegion = false;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.MessageNano
        public int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            PhoneNumberDesc phoneNumberDesc = this.generalDesc;
            if (phoneNumberDesc != null) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(1, phoneNumberDesc);
            }
            PhoneNumberDesc phoneNumberDesc2 = this.fixedLine;
            if (phoneNumberDesc2 != null) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(2, phoneNumberDesc2);
            }
            PhoneNumberDesc phoneNumberDesc3 = this.mobile;
            if (phoneNumberDesc3 != null) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(3, phoneNumberDesc3);
            }
            PhoneNumberDesc phoneNumberDesc4 = this.tollFree;
            if (phoneNumberDesc4 != null) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(4, phoneNumberDesc4);
            }
            PhoneNumberDesc phoneNumberDesc5 = this.premiumRate;
            if (phoneNumberDesc5 != null) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(5, phoneNumberDesc5);
            }
            PhoneNumberDesc phoneNumberDesc6 = this.sharedCost;
            if (phoneNumberDesc6 != null) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(6, phoneNumberDesc6);
            }
            PhoneNumberDesc phoneNumberDesc7 = this.personalNumber;
            if (phoneNumberDesc7 != null) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(7, phoneNumberDesc7);
            }
            PhoneNumberDesc phoneNumberDesc8 = this.voip;
            if (phoneNumberDesc8 != null) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(8, phoneNumberDesc8);
            }
            int iComputeStringSize = iComputeSerializedSize + CodedOutputByteBufferNano.computeStringSize(9, this.f15595id);
            int i9 = this.countryCode;
            if (i9 != 0) {
                iComputeStringSize += CodedOutputByteBufferNano.computeInt32Size(10, i9);
            }
            if (!this.internationalPrefix.equals("")) {
                iComputeStringSize += CodedOutputByteBufferNano.computeStringSize(11, this.internationalPrefix);
            }
            if (!this.nationalPrefix.equals("")) {
                iComputeStringSize += CodedOutputByteBufferNano.computeStringSize(12, this.nationalPrefix);
            }
            if (!this.preferredExtnPrefix.equals("")) {
                iComputeStringSize += CodedOutputByteBufferNano.computeStringSize(13, this.preferredExtnPrefix);
            }
            if (!this.nationalPrefixForParsing.equals("")) {
                iComputeStringSize += CodedOutputByteBufferNano.computeStringSize(15, this.nationalPrefixForParsing);
            }
            if (!this.nationalPrefixTransformRule.equals("")) {
                iComputeStringSize += CodedOutputByteBufferNano.computeStringSize(16, this.nationalPrefixTransformRule);
            }
            if (!this.preferredInternationalPrefix.equals("")) {
                iComputeStringSize += CodedOutputByteBufferNano.computeStringSize(17, this.preferredInternationalPrefix);
            }
            boolean z8 = this.sameMobileAndFixedLinePattern;
            if (z8) {
                iComputeStringSize += CodedOutputByteBufferNano.computeBoolSize(18, z8);
            }
            NumberFormat[] numberFormatArr = this.numberFormat;
            int i10 = 0;
            if (numberFormatArr != null && numberFormatArr.length > 0) {
                int i11 = 0;
                while (true) {
                    NumberFormat[] numberFormatArr2 = this.numberFormat;
                    if (i11 >= numberFormatArr2.length) {
                        break;
                    }
                    NumberFormat numberFormat = numberFormatArr2[i11];
                    if (numberFormat != null) {
                        iComputeStringSize += CodedOutputByteBufferNano.computeMessageSize(19, numberFormat);
                    }
                    i11++;
                }
            }
            NumberFormat[] numberFormatArr3 = this.intlNumberFormat;
            if (numberFormatArr3 != null && numberFormatArr3.length > 0) {
                while (true) {
                    NumberFormat[] numberFormatArr4 = this.intlNumberFormat;
                    if (i10 >= numberFormatArr4.length) {
                        break;
                    }
                    NumberFormat numberFormat2 = numberFormatArr4[i10];
                    if (numberFormat2 != null) {
                        iComputeStringSize += CodedOutputByteBufferNano.computeMessageSize(20, numberFormat2);
                    }
                    i10++;
                }
            }
            PhoneNumberDesc phoneNumberDesc9 = this.pager;
            if (phoneNumberDesc9 != null) {
                iComputeStringSize += CodedOutputByteBufferNano.computeMessageSize(21, phoneNumberDesc9);
            }
            boolean z9 = this.mainCountryForCode;
            if (z9) {
                iComputeStringSize += CodedOutputByteBufferNano.computeBoolSize(22, z9);
            }
            if (!this.leadingDigits.equals("")) {
                iComputeStringSize += CodedOutputByteBufferNano.computeStringSize(23, this.leadingDigits);
            }
            PhoneNumberDesc phoneNumberDesc10 = this.noInternationalDialling;
            if (phoneNumberDesc10 != null) {
                iComputeStringSize += CodedOutputByteBufferNano.computeMessageSize(24, phoneNumberDesc10);
            }
            PhoneNumberDesc phoneNumberDesc11 = this.uan;
            if (phoneNumberDesc11 != null) {
                iComputeStringSize += CodedOutputByteBufferNano.computeMessageSize(25, phoneNumberDesc11);
            }
            boolean z10 = this.leadingZeroPossible;
            if (z10) {
                iComputeStringSize += CodedOutputByteBufferNano.computeBoolSize(26, z10);
            }
            PhoneNumberDesc phoneNumberDesc12 = this.emergency;
            if (phoneNumberDesc12 != null) {
                iComputeStringSize += CodedOutputByteBufferNano.computeMessageSize(27, phoneNumberDesc12);
            }
            PhoneNumberDesc phoneNumberDesc13 = this.voicemail;
            if (phoneNumberDesc13 != null) {
                iComputeStringSize += CodedOutputByteBufferNano.computeMessageSize(28, phoneNumberDesc13);
            }
            PhoneNumberDesc phoneNumberDesc14 = this.shortCode;
            if (phoneNumberDesc14 != null) {
                iComputeStringSize += CodedOutputByteBufferNano.computeMessageSize(29, phoneNumberDesc14);
            }
            PhoneNumberDesc phoneNumberDesc15 = this.standardRate;
            if (phoneNumberDesc15 != null) {
                iComputeStringSize += CodedOutputByteBufferNano.computeMessageSize(30, phoneNumberDesc15);
            }
            PhoneNumberDesc phoneNumberDesc16 = this.carrierSpecific;
            if (phoneNumberDesc16 != null) {
                iComputeStringSize += CodedOutputByteBufferNano.computeMessageSize(31, phoneNumberDesc16);
            }
            boolean z11 = this.mobileNumberPortableRegion;
            return z11 ? iComputeStringSize + CodedOutputByteBufferNano.computeBoolSize(32, z11) : iComputeStringSize;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.MessageNano
        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws CodedOutputByteBufferNano.OutOfSpaceException {
            PhoneNumberDesc phoneNumberDesc = this.generalDesc;
            if (phoneNumberDesc != null) {
                codedOutputByteBufferNano.writeMessage(1, phoneNumberDesc);
            }
            PhoneNumberDesc phoneNumberDesc2 = this.fixedLine;
            if (phoneNumberDesc2 != null) {
                codedOutputByteBufferNano.writeMessage(2, phoneNumberDesc2);
            }
            PhoneNumberDesc phoneNumberDesc3 = this.mobile;
            if (phoneNumberDesc3 != null) {
                codedOutputByteBufferNano.writeMessage(3, phoneNumberDesc3);
            }
            PhoneNumberDesc phoneNumberDesc4 = this.tollFree;
            if (phoneNumberDesc4 != null) {
                codedOutputByteBufferNano.writeMessage(4, phoneNumberDesc4);
            }
            PhoneNumberDesc phoneNumberDesc5 = this.premiumRate;
            if (phoneNumberDesc5 != null) {
                codedOutputByteBufferNano.writeMessage(5, phoneNumberDesc5);
            }
            PhoneNumberDesc phoneNumberDesc6 = this.sharedCost;
            if (phoneNumberDesc6 != null) {
                codedOutputByteBufferNano.writeMessage(6, phoneNumberDesc6);
            }
            PhoneNumberDesc phoneNumberDesc7 = this.personalNumber;
            if (phoneNumberDesc7 != null) {
                codedOutputByteBufferNano.writeMessage(7, phoneNumberDesc7);
            }
            PhoneNumberDesc phoneNumberDesc8 = this.voip;
            if (phoneNumberDesc8 != null) {
                codedOutputByteBufferNano.writeMessage(8, phoneNumberDesc8);
            }
            codedOutputByteBufferNano.writeString(9, this.f15595id);
            int i9 = this.countryCode;
            if (i9 != 0) {
                codedOutputByteBufferNano.writeInt32(10, i9);
            }
            if (!this.internationalPrefix.equals("")) {
                codedOutputByteBufferNano.writeString(11, this.internationalPrefix);
            }
            if (!this.nationalPrefix.equals("")) {
                codedOutputByteBufferNano.writeString(12, this.nationalPrefix);
            }
            if (!this.preferredExtnPrefix.equals("")) {
                codedOutputByteBufferNano.writeString(13, this.preferredExtnPrefix);
            }
            if (!this.nationalPrefixForParsing.equals("")) {
                codedOutputByteBufferNano.writeString(15, this.nationalPrefixForParsing);
            }
            if (!this.nationalPrefixTransformRule.equals("")) {
                codedOutputByteBufferNano.writeString(16, this.nationalPrefixTransformRule);
            }
            if (!this.preferredInternationalPrefix.equals("")) {
                codedOutputByteBufferNano.writeString(17, this.preferredInternationalPrefix);
            }
            boolean z8 = this.sameMobileAndFixedLinePattern;
            if (z8) {
                codedOutputByteBufferNano.writeBool(18, z8);
            }
            NumberFormat[] numberFormatArr = this.numberFormat;
            int i10 = 0;
            if (numberFormatArr != null && numberFormatArr.length > 0) {
                int i11 = 0;
                while (true) {
                    NumberFormat[] numberFormatArr2 = this.numberFormat;
                    if (i11 >= numberFormatArr2.length) {
                        break;
                    }
                    NumberFormat numberFormat = numberFormatArr2[i11];
                    if (numberFormat != null) {
                        codedOutputByteBufferNano.writeMessage(19, numberFormat);
                    }
                    i11++;
                }
            }
            NumberFormat[] numberFormatArr3 = this.intlNumberFormat;
            if (numberFormatArr3 != null && numberFormatArr3.length > 0) {
                while (true) {
                    NumberFormat[] numberFormatArr4 = this.intlNumberFormat;
                    if (i10 >= numberFormatArr4.length) {
                        break;
                    }
                    NumberFormat numberFormat2 = numberFormatArr4[i10];
                    if (numberFormat2 != null) {
                        codedOutputByteBufferNano.writeMessage(20, numberFormat2);
                    }
                    i10++;
                }
            }
            PhoneNumberDesc phoneNumberDesc9 = this.pager;
            if (phoneNumberDesc9 != null) {
                codedOutputByteBufferNano.writeMessage(21, phoneNumberDesc9);
            }
            boolean z9 = this.mainCountryForCode;
            if (z9) {
                codedOutputByteBufferNano.writeBool(22, z9);
            }
            if (!this.leadingDigits.equals("")) {
                codedOutputByteBufferNano.writeString(23, this.leadingDigits);
            }
            PhoneNumberDesc phoneNumberDesc10 = this.noInternationalDialling;
            if (phoneNumberDesc10 != null) {
                codedOutputByteBufferNano.writeMessage(24, phoneNumberDesc10);
            }
            PhoneNumberDesc phoneNumberDesc11 = this.uan;
            if (phoneNumberDesc11 != null) {
                codedOutputByteBufferNano.writeMessage(25, phoneNumberDesc11);
            }
            boolean z10 = this.leadingZeroPossible;
            if (z10) {
                codedOutputByteBufferNano.writeBool(26, z10);
            }
            PhoneNumberDesc phoneNumberDesc12 = this.emergency;
            if (phoneNumberDesc12 != null) {
                codedOutputByteBufferNano.writeMessage(27, phoneNumberDesc12);
            }
            PhoneNumberDesc phoneNumberDesc13 = this.voicemail;
            if (phoneNumberDesc13 != null) {
                codedOutputByteBufferNano.writeMessage(28, phoneNumberDesc13);
            }
            PhoneNumberDesc phoneNumberDesc14 = this.shortCode;
            if (phoneNumberDesc14 != null) {
                codedOutputByteBufferNano.writeMessage(29, phoneNumberDesc14);
            }
            PhoneNumberDesc phoneNumberDesc15 = this.standardRate;
            if (phoneNumberDesc15 != null) {
                codedOutputByteBufferNano.writeMessage(30, phoneNumberDesc15);
            }
            PhoneNumberDesc phoneNumberDesc16 = this.carrierSpecific;
            if (phoneNumberDesc16 != null) {
                codedOutputByteBufferNano.writeMessage(31, phoneNumberDesc16);
            }
            boolean z11 = this.mobileNumberPortableRegion;
            if (z11) {
                codedOutputByteBufferNano.writeBool(32, z11);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        public static PhoneMetadata parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
            return new PhoneMetadata().mergeFrom(codedInputByteBufferNano);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.MessageNano
        public PhoneMetadata mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws InvalidProtocolBufferNanoException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 10:
                        if (this.generalDesc == null) {
                            this.generalDesc = new PhoneNumberDesc();
                        }
                        codedInputByteBufferNano.readMessage(this.generalDesc);
                        break;
                    case 18:
                        if (this.fixedLine == null) {
                            this.fixedLine = new PhoneNumberDesc();
                        }
                        codedInputByteBufferNano.readMessage(this.fixedLine);
                        break;
                    case 26:
                        if (this.mobile == null) {
                            this.mobile = new PhoneNumberDesc();
                        }
                        codedInputByteBufferNano.readMessage(this.mobile);
                        break;
                    case 34:
                        if (this.tollFree == null) {
                            this.tollFree = new PhoneNumberDesc();
                        }
                        codedInputByteBufferNano.readMessage(this.tollFree);
                        break;
                    case 42:
                        if (this.premiumRate == null) {
                            this.premiumRate = new PhoneNumberDesc();
                        }
                        codedInputByteBufferNano.readMessage(this.premiumRate);
                        break;
                    case 50:
                        if (this.sharedCost == null) {
                            this.sharedCost = new PhoneNumberDesc();
                        }
                        codedInputByteBufferNano.readMessage(this.sharedCost);
                        break;
                    case 58:
                        if (this.personalNumber == null) {
                            this.personalNumber = new PhoneNumberDesc();
                        }
                        codedInputByteBufferNano.readMessage(this.personalNumber);
                        break;
                    case 66:
                        if (this.voip == null) {
                            this.voip = new PhoneNumberDesc();
                        }
                        codedInputByteBufferNano.readMessage(this.voip);
                        break;
                    case 74:
                        this.f15595id = codedInputByteBufferNano.readString();
                        break;
                    case 80:
                        this.countryCode = codedInputByteBufferNano.readInt32();
                        break;
                    case 90:
                        this.internationalPrefix = codedInputByteBufferNano.readString();
                        break;
                    case 98:
                        this.nationalPrefix = codedInputByteBufferNano.readString();
                        break;
                    case 106:
                        this.preferredExtnPrefix = codedInputByteBufferNano.readString();
                        break;
                    case 122:
                        this.nationalPrefixForParsing = codedInputByteBufferNano.readString();
                        break;
                    case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /* 130 */:
                        this.nationalPrefixTransformRule = codedInputByteBufferNano.readString();
                        break;
                    case TsExtractor.TS_STREAM_TYPE_DTS /* 138 */:
                        this.preferredInternationalPrefix = codedInputByteBufferNano.readString();
                        break;
                    case 144:
                        this.sameMobileAndFixedLinePattern = codedInputByteBufferNano.readBool();
                        break;
                    case 154:
                        int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 154);
                        NumberFormat[] numberFormatArr = this.numberFormat;
                        int length = numberFormatArr == null ? 0 : numberFormatArr.length;
                        int i9 = repeatedFieldArrayLength + length;
                        NumberFormat[] numberFormatArr2 = new NumberFormat[i9];
                        if (length != 0) {
                            System.arraycopy(numberFormatArr, 0, numberFormatArr2, 0, length);
                        }
                        while (length < i9 - 1) {
                            NumberFormat numberFormat = new NumberFormat();
                            numberFormatArr2[length] = numberFormat;
                            codedInputByteBufferNano.readMessage(numberFormat);
                            codedInputByteBufferNano.readTag();
                            length++;
                        }
                        NumberFormat numberFormat2 = new NumberFormat();
                        numberFormatArr2[length] = numberFormat2;
                        codedInputByteBufferNano.readMessage(numberFormat2);
                        this.numberFormat = numberFormatArr2;
                        break;
                    case 162:
                        int repeatedFieldArrayLength2 = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 162);
                        NumberFormat[] numberFormatArr3 = this.intlNumberFormat;
                        int length2 = numberFormatArr3 == null ? 0 : numberFormatArr3.length;
                        int i10 = repeatedFieldArrayLength2 + length2;
                        NumberFormat[] numberFormatArr4 = new NumberFormat[i10];
                        if (length2 != 0) {
                            System.arraycopy(numberFormatArr3, 0, numberFormatArr4, 0, length2);
                        }
                        while (length2 < i10 - 1) {
                            NumberFormat numberFormat3 = new NumberFormat();
                            numberFormatArr4[length2] = numberFormat3;
                            codedInputByteBufferNano.readMessage(numberFormat3);
                            codedInputByteBufferNano.readTag();
                            length2++;
                        }
                        NumberFormat numberFormat4 = new NumberFormat();
                        numberFormatArr4[length2] = numberFormat4;
                        codedInputByteBufferNano.readMessage(numberFormat4);
                        this.intlNumberFormat = numberFormatArr4;
                        break;
                    case 170:
                        if (this.pager == null) {
                            this.pager = new PhoneNumberDesc();
                        }
                        codedInputByteBufferNano.readMessage(this.pager);
                        break;
                    case 176:
                        this.mainCountryForCode = codedInputByteBufferNano.readBool();
                        break;
                    case 186:
                        this.leadingDigits = codedInputByteBufferNano.readString();
                        break;
                    case 194:
                        if (this.noInternationalDialling == null) {
                            this.noInternationalDialling = new PhoneNumberDesc();
                        }
                        codedInputByteBufferNano.readMessage(this.noInternationalDialling);
                        break;
                    case 202:
                        if (this.uan == null) {
                            this.uan = new PhoneNumberDesc();
                        }
                        codedInputByteBufferNano.readMessage(this.uan);
                        break;
                    case 208:
                        this.leadingZeroPossible = codedInputByteBufferNano.readBool();
                        break;
                    case 218:
                        if (this.emergency == null) {
                            this.emergency = new PhoneNumberDesc();
                        }
                        codedInputByteBufferNano.readMessage(this.emergency);
                        break;
                    case 226:
                        if (this.voicemail == null) {
                            this.voicemail = new PhoneNumberDesc();
                        }
                        codedInputByteBufferNano.readMessage(this.voicemail);
                        break;
                    case 234:
                        if (this.shortCode == null) {
                            this.shortCode = new PhoneNumberDesc();
                        }
                        codedInputByteBufferNano.readMessage(this.shortCode);
                        break;
                    case 242:
                        if (this.standardRate == null) {
                            this.standardRate = new PhoneNumberDesc();
                        }
                        codedInputByteBufferNano.readMessage(this.standardRate);
                        break;
                    case SQLiteDatabase.MAX_SQL_CACHE_SIZE /* 250 */:
                        if (this.carrierSpecific == null) {
                            this.carrierSpecific = new PhoneNumberDesc();
                        }
                        codedInputByteBufferNano.readMessage(this.carrierSpecific);
                        break;
                    case 256:
                        this.mobileNumberPortableRegion = codedInputByteBufferNano.readBool();
                        break;
                    default:
                        if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                            return this;
                        }
                        break;
                }
            }
        }
    }

    public static final class PhoneMetadataCollection extends MessageNano {
        private static volatile PhoneMetadataCollection[] _emptyArray;
        public PhoneMetadata[] metadata;

        public PhoneMetadataCollection() {
            clear();
        }

        public static PhoneMetadataCollection[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new PhoneMetadataCollection[0];
                    }
                }
            }
            return _emptyArray;
        }

        public static PhoneMetadataCollection parseFrom(byte[] bArr) {
            return (PhoneMetadataCollection) MessageNano.mergeFrom(new PhoneMetadataCollection(), bArr);
        }

        public PhoneMetadataCollection clear() {
            this.metadata = PhoneMetadata.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.MessageNano
        public int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            PhoneMetadata[] phoneMetadataArr = this.metadata;
            if (phoneMetadataArr != null && phoneMetadataArr.length > 0) {
                int i9 = 0;
                while (true) {
                    PhoneMetadata[] phoneMetadataArr2 = this.metadata;
                    if (i9 >= phoneMetadataArr2.length) {
                        break;
                    }
                    PhoneMetadata phoneMetadata = phoneMetadataArr2[i9];
                    if (phoneMetadata != null) {
                        iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(1, phoneMetadata);
                    }
                    i9++;
                }
            }
            return iComputeSerializedSize;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.MessageNano
        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws CodedOutputByteBufferNano.OutOfSpaceException {
            PhoneMetadata[] phoneMetadataArr = this.metadata;
            if (phoneMetadataArr != null && phoneMetadataArr.length > 0) {
                int i9 = 0;
                while (true) {
                    PhoneMetadata[] phoneMetadataArr2 = this.metadata;
                    if (i9 >= phoneMetadataArr2.length) {
                        break;
                    }
                    PhoneMetadata phoneMetadata = phoneMetadataArr2[i9];
                    if (phoneMetadata != null) {
                        codedOutputByteBufferNano.writeMessage(1, phoneMetadata);
                    }
                    i9++;
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        public static PhoneMetadataCollection parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
            return new PhoneMetadataCollection().mergeFrom(codedInputByteBufferNano);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.MessageNano
        public PhoneMetadataCollection mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws InvalidProtocolBufferNanoException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 10) {
                    int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                    PhoneMetadata[] phoneMetadataArr = this.metadata;
                    int length = phoneMetadataArr == null ? 0 : phoneMetadataArr.length;
                    int i9 = repeatedFieldArrayLength + length;
                    PhoneMetadata[] phoneMetadataArr2 = new PhoneMetadata[i9];
                    if (length != 0) {
                        System.arraycopy(phoneMetadataArr, 0, phoneMetadataArr2, 0, length);
                    }
                    while (length < i9 - 1) {
                        PhoneMetadata phoneMetadata = new PhoneMetadata();
                        phoneMetadataArr2[length] = phoneMetadata;
                        codedInputByteBufferNano.readMessage(phoneMetadata);
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    PhoneMetadata phoneMetadata2 = new PhoneMetadata();
                    phoneMetadataArr2[length] = phoneMetadata2;
                    codedInputByteBufferNano.readMessage(phoneMetadata2);
                    this.metadata = phoneMetadataArr2;
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                    return this;
                }
            }
        }
    }

    public static final class PhoneNumberDesc extends MessageNano {
        private static volatile PhoneNumberDesc[] _emptyArray;
        public String exampleNumber;
        public String nationalNumberPattern;
        public String possibleNumberPattern;

        public PhoneNumberDesc() {
            clear();
        }

        public static PhoneNumberDesc[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new PhoneNumberDesc[0];
                    }
                }
            }
            return _emptyArray;
        }

        public static PhoneNumberDesc parseFrom(byte[] bArr) {
            return (PhoneNumberDesc) MessageNano.mergeFrom(new PhoneNumberDesc(), bArr);
        }

        public PhoneNumberDesc clear() {
            this.nationalNumberPattern = "";
            this.possibleNumberPattern = "";
            this.exampleNumber = "";
            this.cachedSize = -1;
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.MessageNano
        public int computeSerializedSize() {
            int iComputeSerializedSize = super.computeSerializedSize();
            if (!this.nationalNumberPattern.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(2, this.nationalNumberPattern);
            }
            if (!this.possibleNumberPattern.equals("")) {
                iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(3, this.possibleNumberPattern);
            }
            return !this.exampleNumber.equals("") ? iComputeSerializedSize + CodedOutputByteBufferNano.computeStringSize(6, this.exampleNumber) : iComputeSerializedSize;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.MessageNano
        public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws CodedOutputByteBufferNano.OutOfSpaceException {
            if (!this.nationalNumberPattern.equals("")) {
                codedOutputByteBufferNano.writeString(2, this.nationalNumberPattern);
            }
            if (!this.possibleNumberPattern.equals("")) {
                codedOutputByteBufferNano.writeString(3, this.possibleNumberPattern);
            }
            if (!this.exampleNumber.equals("")) {
                codedOutputByteBufferNano.writeString(6, this.exampleNumber);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        public static PhoneNumberDesc parseFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
            return new PhoneNumberDesc().mergeFrom(codedInputByteBufferNano);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.MessageNano
        public PhoneNumberDesc mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws InvalidProtocolBufferNanoException {
            while (true) {
                int tag = codedInputByteBufferNano.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 18) {
                    this.nationalNumberPattern = codedInputByteBufferNano.readString();
                } else if (tag == 26) {
                    this.possibleNumberPattern = codedInputByteBufferNano.readString();
                } else if (tag == 50) {
                    this.exampleNumber = codedInputByteBufferNano.readString();
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, tag)) {
                    return this;
                }
            }
        }
    }
}
