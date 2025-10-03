package com.google.android.gms.internal.gtm;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import com.google.android.gms.internal.gtm.zzbn;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes2.dex */
class zzbo<T extends zzbn> extends zzam {
    private zzbp<T> zzyn;

    public zzbo(zzap zzapVar, zzbp<T> zzbpVar) {
        super(zzapVar);
        this.zzyn = zzbpVar;
    }

    private final T zza(XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException, NumberFormatException {
        try {
            xmlResourceParser.next();
            int eventType = xmlResourceParser.getEventType();
            while (eventType != 1) {
                if (xmlResourceParser.getEventType() == 2) {
                    String lowerCase = xmlResourceParser.getName().toLowerCase(Locale.US);
                    if (lowerCase.equals("screenname")) {
                        String attributeValue = xmlResourceParser.getAttributeValue(null, AppMeasurementSdk.ConditionalUserProperty.NAME);
                        String strTrim = xmlResourceParser.nextText().trim();
                        if (!TextUtils.isEmpty(attributeValue) && !TextUtils.isEmpty(strTrim)) {
                            this.zzyn.zzb(attributeValue, strTrim);
                        }
                    } else if (lowerCase.equals("string")) {
                        String attributeValue2 = xmlResourceParser.getAttributeValue(null, AppMeasurementSdk.ConditionalUserProperty.NAME);
                        String strTrim2 = xmlResourceParser.nextText().trim();
                        if (!TextUtils.isEmpty(attributeValue2) && strTrim2 != null) {
                            this.zzyn.zzc(attributeValue2, strTrim2);
                        }
                    } else if (lowerCase.equals("bool")) {
                        String attributeValue3 = xmlResourceParser.getAttributeValue(null, AppMeasurementSdk.ConditionalUserProperty.NAME);
                        String strTrim3 = xmlResourceParser.nextText().trim();
                        if (!TextUtils.isEmpty(attributeValue3) && !TextUtils.isEmpty(strTrim3)) {
                            try {
                                this.zzyn.zza(attributeValue3, Boolean.parseBoolean(strTrim3));
                            } catch (NumberFormatException e9) {
                                zzc("Error parsing bool configuration value", strTrim3, e9);
                            }
                        }
                    } else if (lowerCase.equals("integer")) {
                        String attributeValue4 = xmlResourceParser.getAttributeValue(null, AppMeasurementSdk.ConditionalUserProperty.NAME);
                        String strTrim4 = xmlResourceParser.nextText().trim();
                        if (!TextUtils.isEmpty(attributeValue4) && !TextUtils.isEmpty(strTrim4)) {
                            try {
                                this.zzyn.zzb(attributeValue4, Integer.parseInt(strTrim4));
                            } catch (NumberFormatException e10) {
                                zzc("Error parsing int configuration value", strTrim4, e10);
                            }
                        }
                    }
                }
                eventType = xmlResourceParser.next();
            }
        } catch (IOException | XmlPullParserException e11) {
            zze("Error parsing tracker configuration file", e11);
        }
        return (T) this.zzyn.zzel();
    }

    public T zzq(int i9) {
        try {
            return (T) zza(zzcm().zzdc().getResources().getXml(i9));
        } catch (Resources.NotFoundException e9) {
            zzd("inflate() called with unknown resourceId", e9);
            return null;
        }
    }
}
