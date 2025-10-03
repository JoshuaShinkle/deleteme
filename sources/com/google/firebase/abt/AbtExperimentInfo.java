package com.google.firebase.abt;

import android.text.TextUtils;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes2.dex */
public class AbtExperimentInfo {
    static final String EXPERIMENT_ID_KEY = "experimentId";
    static final String EXPERIMENT_START_TIME_KEY = "experimentStartTime";
    static final String TIME_TO_LIVE_KEY = "timeToLiveMillis";
    static final String TRIGGER_EVENT_KEY = "triggerEvent";
    static final String TRIGGER_TIMEOUT_KEY = "triggerTimeoutMillis";
    static final String VARIANT_ID_KEY = "variantId";
    private final String experimentId;
    private final Date experimentStartTime;
    private final long timeToLiveInMillis;
    private final String triggerEventName;
    private final long triggerTimeoutInMillis;
    private final String variantId;
    private static final String[] ALL_REQUIRED_KEYS = {"experimentId", "experimentStartTime", "timeToLiveMillis", "triggerTimeoutMillis", "variantId"};
    static final DateFormat protoTimestampStringParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);

    public AbtExperimentInfo(String str, String str2, String str3, Date date, long j9, long j10) {
        this.experimentId = str;
        this.variantId = str2;
        this.triggerEventName = str3;
        this.experimentStartTime = date;
        this.triggerTimeoutInMillis = j9;
        this.timeToLiveInMillis = j10;
    }

    public static AbtExperimentInfo fromConditionalUserProperty(AnalyticsConnector.ConditionalUserProperty conditionalUserProperty) {
        String str = conditionalUserProperty.triggerEventName;
        if (str == null) {
            str = "";
        }
        return new AbtExperimentInfo(conditionalUserProperty.name, String.valueOf(conditionalUserProperty.value), str, new Date(conditionalUserProperty.creationTimestamp), conditionalUserProperty.triggerTimeout, conditionalUserProperty.timeToLive);
    }

    public static AbtExperimentInfo fromMap(Map<String, String> map) throws AbtException, NumberFormatException, ParseException {
        validateExperimentInfoMap(map);
        try {
            return new AbtExperimentInfo(map.get("experimentId"), map.get("variantId"), map.containsKey("triggerEvent") ? map.get("triggerEvent") : "", protoTimestampStringParser.parse(map.get("experimentStartTime")), Long.parseLong(map.get("triggerTimeoutMillis")), Long.parseLong(map.get("timeToLiveMillis")));
        } catch (NumberFormatException e9) {
            throw new AbtException("Could not process experiment: one of the durations could not be converted into a long.", e9);
        } catch (ParseException e10) {
            throw new AbtException("Could not process experiment: parsing experiment start time failed.", e10);
        }
    }

    public static void validateAbtExperimentInfo(AbtExperimentInfo abtExperimentInfo) throws AbtException {
        validateExperimentInfoMap(abtExperimentInfo.toStringMap());
    }

    private static void validateExperimentInfoMap(Map<String, String> map) throws AbtException {
        ArrayList arrayList = new ArrayList();
        for (String str : ALL_REQUIRED_KEYS) {
            if (!map.containsKey(str)) {
                arrayList.add(str);
            }
        }
        if (!arrayList.isEmpty()) {
            throw new AbtException(String.format("The following keys are missing from the experiment info map: %s", arrayList));
        }
    }

    public String getExperimentId() {
        return this.experimentId;
    }

    public long getStartTimeInMillisSinceEpoch() {
        return this.experimentStartTime.getTime();
    }

    public long getTimeToLiveInMillis() {
        return this.timeToLiveInMillis;
    }

    public String getTriggerEventName() {
        return this.triggerEventName;
    }

    public long getTriggerTimeoutInMillis() {
        return this.triggerTimeoutInMillis;
    }

    public String getVariantId() {
        return this.variantId;
    }

    public AnalyticsConnector.ConditionalUserProperty toConditionalUserProperty(String str) {
        AnalyticsConnector.ConditionalUserProperty conditionalUserProperty = new AnalyticsConnector.ConditionalUserProperty();
        conditionalUserProperty.origin = str;
        conditionalUserProperty.creationTimestamp = getStartTimeInMillisSinceEpoch();
        conditionalUserProperty.name = this.experimentId;
        conditionalUserProperty.value = this.variantId;
        conditionalUserProperty.triggerEventName = TextUtils.isEmpty(this.triggerEventName) ? null : this.triggerEventName;
        conditionalUserProperty.triggerTimeout = this.triggerTimeoutInMillis;
        conditionalUserProperty.timeToLive = this.timeToLiveInMillis;
        return conditionalUserProperty;
    }

    public Map<String, String> toStringMap() {
        HashMap map = new HashMap();
        map.put("experimentId", this.experimentId);
        map.put("variantId", this.variantId);
        map.put("triggerEvent", this.triggerEventName);
        map.put("experimentStartTime", protoTimestampStringParser.format(this.experimentStartTime));
        map.put("triggerTimeoutMillis", Long.toString(this.triggerTimeoutInMillis));
        map.put("timeToLiveMillis", Long.toString(this.timeToLiveInMillis));
        return map;
    }
}
