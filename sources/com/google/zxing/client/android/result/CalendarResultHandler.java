package com.google.zxing.client.android.result;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.plus.PlusShare;
import com.google.zxing.client.android.C4453R;
import com.google.zxing.client.result.CalendarParsedResult;
import com.google.zxing.client.result.ParsedResult;
import java.text.DateFormat;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;

/* loaded from: classes2.dex */
public final class CalendarResultHandler extends ResultHandler {
    private static final String TAG = "CalendarResultHandler";
    private static final int[] buttons = {C4453R.string.button_add_calendar};

    public CalendarResultHandler(Activity activity, ParsedResult parsedResult) {
        super(activity, parsedResult);
    }

    private void addCalendarEvent(String str, Date date, boolean z8, Date date2, String str2, String str3, String[] strArr) {
        Intent intent = new Intent("android.intent.action.INSERT");
        intent.setType("vnd.android.cursor.item/event");
        long time = date.getTime();
        intent.putExtra("beginTime", time);
        if (z8) {
            intent.putExtra("allDay", true);
        }
        if (date2 != null) {
            time = date2.getTime();
        } else if (z8) {
            time += DateUtils.MILLIS_PER_DAY;
        }
        intent.putExtra("endTime", time);
        intent.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, str);
        intent.putExtra("eventLocation", str2);
        intent.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, str3);
        if (strArr != null) {
            intent.putExtra("android.intent.extra.EMAIL", strArr);
        }
        try {
            rawLaunchIntent(intent);
        } catch (ActivityNotFoundException unused) {
            Log.w(TAG, "No calendar app available that responds to android.intent.action.INSERT");
            intent.setAction("android.intent.action.EDIT");
            launchIntent(intent);
        }
    }

    private static String format(boolean z8, Date date) {
        if (date == null) {
            return null;
        }
        return (z8 ? DateFormat.getDateInstance(2) : DateFormat.getDateTimeInstance(2, 2)).format(date);
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getButtonCount() {
        return buttons.length;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getButtonText(int i9) {
        return buttons[i9];
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public CharSequence getDisplayContents() {
        CalendarParsedResult calendarParsedResult = (CalendarParsedResult) getResult();
        StringBuilder sb = new StringBuilder(100);
        ParsedResult.maybeAppend(calendarParsedResult.getSummary(), sb);
        Date start = calendarParsedResult.getStart();
        ParsedResult.maybeAppend(format(calendarParsedResult.isStartAllDay(), start), sb);
        Date end = calendarParsedResult.getEnd();
        if (end != null) {
            if (calendarParsedResult.isEndAllDay() && !start.equals(end)) {
                end = new Date(end.getTime() - DateUtils.MILLIS_PER_DAY);
            }
            ParsedResult.maybeAppend(format(calendarParsedResult.isEndAllDay(), end), sb);
        }
        ParsedResult.maybeAppend(calendarParsedResult.getLocation(), sb);
        ParsedResult.maybeAppend(calendarParsedResult.getOrganizer(), sb);
        ParsedResult.maybeAppend(calendarParsedResult.getAttendees(), sb);
        ParsedResult.maybeAppend(calendarParsedResult.getDescription(), sb);
        return sb.toString();
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getDisplayTitle() {
        return C4453R.string.result_calendar;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public void handleButtonPress(int i9) {
        String str;
        if (i9 == 0) {
            CalendarParsedResult calendarParsedResult = (CalendarParsedResult) getResult();
            String description = calendarParsedResult.getDescription();
            String organizer = calendarParsedResult.getOrganizer();
            if (organizer == null) {
                str = description;
            } else if (description == null) {
                str = organizer;
            } else {
                description = description + '\n' + organizer;
                str = description;
            }
            addCalendarEvent(calendarParsedResult.getSummary(), calendarParsedResult.getStart(), calendarParsedResult.isStartAllDay(), calendarParsedResult.getEnd(), calendarParsedResult.getLocation(), str, calendarParsedResult.getAttendees());
        }
    }
}
