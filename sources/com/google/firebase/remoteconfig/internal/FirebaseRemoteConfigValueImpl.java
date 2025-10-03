package com.google.firebase.remoteconfig.internal;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;

/* loaded from: classes2.dex */
public class FirebaseRemoteConfigValueImpl implements FirebaseRemoteConfigValue {
    private static final String ILLEGAL_ARGUMENT_STRING_FORMAT = "[Value: %s] cannot be converted to a %s.";
    private final int source;
    private final String value;

    public FirebaseRemoteConfigValueImpl(String str, int i9) {
        this.value = str;
        this.source = i9;
    }

    private String asTrimmedString() {
        return asString().trim();
    }

    private void throwIfNullValue() {
        if (this.value == null) {
            throw new IllegalArgumentException("Value is null, and cannot be converted to the desired type.");
        }
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
    public boolean asBoolean() {
        if (this.source == 0) {
            return false;
        }
        String strAsTrimmedString = asTrimmedString();
        if (ConfigGetParameterHandler.TRUE_REGEX.matcher(strAsTrimmedString).matches()) {
            return true;
        }
        if (ConfigGetParameterHandler.FALSE_REGEX.matcher(strAsTrimmedString).matches()) {
            return false;
        }
        throw new IllegalArgumentException(String.format(ILLEGAL_ARGUMENT_STRING_FORMAT, strAsTrimmedString, "boolean"));
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
    public byte[] asByteArray() {
        return this.source == 0 ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_BYTE_ARRAY : this.value.getBytes(ConfigGetParameterHandler.FRC_BYTE_ARRAY_ENCODING);
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
    public double asDouble() {
        if (this.source == 0) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        String strAsTrimmedString = asTrimmedString();
        try {
            return Double.valueOf(strAsTrimmedString).doubleValue();
        } catch (NumberFormatException e9) {
            throw new IllegalArgumentException(String.format(ILLEGAL_ARGUMENT_STRING_FORMAT, strAsTrimmedString, "double"), e9);
        }
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
    public long asLong() {
        if (this.source == 0) {
            return 0L;
        }
        String strAsTrimmedString = asTrimmedString();
        try {
            return Long.valueOf(strAsTrimmedString).longValue();
        } catch (NumberFormatException e9) {
            throw new IllegalArgumentException(String.format(ILLEGAL_ARGUMENT_STRING_FORMAT, strAsTrimmedString, "long"), e9);
        }
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
    public String asString() {
        if (this.source == 0) {
            return "";
        }
        throwIfNullValue();
        return this.value;
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
    public int getSource() {
        return this.source;
    }
}
