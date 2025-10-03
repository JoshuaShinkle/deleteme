package com.google.zxing.client.result;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* loaded from: classes2.dex */
public final class GeoParsedResult extends ParsedResult {
    private final double altitude;
    private final double latitude;
    private final double longitude;
    private final String query;

    public GeoParsedResult(double d9, double d10, double d11, String str) {
        super(ParsedResultType.GEO);
        this.latitude = d9;
        this.longitude = d10;
        this.altitude = d11;
        this.query = str;
    }

    public double getAltitude() {
        return this.altitude;
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(20);
        sb.append(this.latitude);
        sb.append(", ");
        sb.append(this.longitude);
        if (this.altitude > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            sb.append(", ");
            sb.append(this.altitude);
            sb.append('m');
        }
        if (this.query != null) {
            sb.append(" (");
            sb.append(this.query);
            sb.append(')');
        }
        return sb.toString();
    }

    public String getGeoURI() {
        StringBuilder sb = new StringBuilder();
        sb.append("geo:");
        sb.append(this.latitude);
        sb.append(',');
        sb.append(this.longitude);
        if (this.altitude > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            sb.append(',');
            sb.append(this.altitude);
        }
        if (this.query != null) {
            sb.append('?');
            sb.append(this.query);
        }
        return sb.toString();
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public String getQuery() {
        return this.query;
    }
}
