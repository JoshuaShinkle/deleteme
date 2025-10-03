package com.google.android.exoplayer2.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class Util {
    private static final int[] CRC32_BYTES_MSBF;
    public static final String DEVICE;
    public static final String DEVICE_DEBUG_INFO;
    private static final Pattern ESCAPED_CHARACTER_PATTERN;
    public static final String MANUFACTURER;
    public static final String MODEL;
    public static final int SDK_INT;
    private static final String TAG = "Util";
    private static final Pattern XS_DATE_TIME_PATTERN;
    private static final Pattern XS_DURATION_PATTERN;

    static {
        int i9 = Build.VERSION.SDK_INT;
        SDK_INT = i9;
        String str = Build.DEVICE;
        DEVICE = str;
        String str2 = Build.MANUFACTURER;
        MANUFACTURER = str2;
        String str3 = Build.MODEL;
        MODEL = str3;
        DEVICE_DEBUG_INFO = str + ", " + str3 + ", " + str2 + ", " + i9;
        XS_DATE_TIME_PATTERN = Pattern.compile("(\\d\\d\\d\\d)\\-(\\d\\d)\\-(\\d\\d)[Tt](\\d\\d):(\\d\\d):(\\d\\d)([\\.,](\\d+))?([Zz]|((\\+|\\-)(\\d?\\d):?(\\d\\d)))?");
        XS_DURATION_PATTERN = Pattern.compile("^(-)?P(([0-9]*)Y)?(([0-9]*)M)?(([0-9]*)D)?(T(([0-9]*)H)?(([0-9]*)M)?(([0-9.]*)S)?)?$");
        ESCAPED_CHARACTER_PATTERN = Pattern.compile("%([A-Fa-f0-9]{2})");
        CRC32_BYTES_MSBF = new int[]{0, 79764919, 159529838, 222504665, 319059676, 398814059, 445009330, 507990021, 638119352, 583659535, 797628118, 726387553, 890018660, 835552979, 1015980042, 944750013, 1276238704, 1221641927, 1167319070, 1095957929, 1595256236, 1540665371, 1452775106, 1381403509, 1780037320, 1859660671, 1671105958, 1733955601, 2031960084, 2111593891, 1889500026, 1952343757, -1742489888, -1662866601, -1851683442, -1788833735, -1960329156, -1880695413, -2103051438, -2040207643, -1104454824, -1159051537, -1213636554, -1284997759, -1389417084, -1444007885, -1532160278, -1603531939, -734892656, -789352409, -575645954, -646886583, -952755380, -1007220997, -827056094, -898286187, -231047128, -151282273, -71779514, -8804623, -515967244, -436212925, -390279782, -327299027, 881225847, 809987520, 1023691545, 969234094, 662832811, 591600412, 771767749, 717299826, 311336399, 374308984, 453813921, 533576470, 25881363, 88864420, 134795389, 214552010, 2023205639, 2086057648, 1897238633, 1976864222, 1804852699, 1867694188, 1645340341, 1724971778, 1587496639, 1516133128, 1461550545, 1406951526, 1302016099, 1230646740, 1142491917, 1087903418, -1398421865, -1469785312, -1524105735, -1578704818, -1079922613, -1151291908, -1239184603, -1293773166, -1968362705, -1905510760, -2094067647, -2014441994, -1716953613, -1654112188, -1876203875, -1796572374, -525066777, -462094256, -382327159, -302564546, -206542021, -143559028, -97365931, -17609246, -960696225, -1031934488, -817968335, -872425850, -709327229, -780559564, -600130067, -654598054, 1762451694, 1842216281, 1619975040, 1682949687, 2047383090, 2127137669, 1938468188, 2001449195, 1325665622, 1271206113, 1183200824, 1111960463, 1543535498, 1489069629, 1434599652, 1363369299, 622672798, 568075817, 748617968, 677256519, 907627842, 853037301, 1067152940, 995781531, 51762726, 131386257, 177728840, 240578815, 269590778, 349224269, 429104020, 491947555, -248556018, -168932423, -122852000, -60002089, -500490030, -420856475, -341238852, -278395381, -685261898, -739858943, -559578920, -630940305, -1004286614, -1058877219, -845023740, -916395085, -1119974018, -1174433591, -1262701040, -1333941337, -1371866206, -1426332139, -1481064244, -1552294533, -1690935098, -1611170447, -1833673816, -1770699233, -2009983462, -1930228819, -2119160460, -2056179517, 1569362073, 1498123566, 1409854455, 1355396672, 1317987909, 1246755826, 1192025387, 1137557660, 2072149281, 2135122070, 1912620623, 1992383480, 1753615357, 1816598090, 1627664531, 1707420964, 295390185, 358241886, 404320391, 483945776, 43990325, 106832002, 186451547, 266083308, 932423249, 861060070, 1041341759, 986742920, 613929101, 542559546, 756411363, 701822548, -978770311, -1050133554, -869589737, -924188512, -693284699, -764654318, -550540341, -605129092, -475935807, -413084042, -366743377, -287118056, -257573603, -194731862, -114850189, -35218492, -1984365303, -1921392450, -2143631769, -2063868976, -1698919467, -1635936670, -1824608069, -1744851700, -1347415887, -1418654458, -1506661409, -1561119128, -1129027987, -1200260134, -1254728445, -1309196108};
    }

    private Util() {
    }

    public static long addWithOverflowDefault(long j9, long j10, long j11) {
        long j12 = j9 + j10;
        return ((j9 ^ j12) & (j10 ^ j12)) < 0 ? j11 : j12;
    }

    public static boolean areEqual(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static int binarySearchCeil(long[] jArr, long j9, boolean z8, boolean z9) {
        int i9;
        int iBinarySearch = Arrays.binarySearch(jArr, j9);
        if (iBinarySearch < 0) {
            i9 = ~iBinarySearch;
        } else {
            do {
                iBinarySearch++;
                if (iBinarySearch >= jArr.length) {
                    break;
                }
            } while (jArr[iBinarySearch] == j9);
            i9 = z8 ? iBinarySearch - 1 : iBinarySearch;
        }
        return z9 ? Math.min(jArr.length - 1, i9) : i9;
    }

    public static int binarySearchFloor(int[] iArr, int i9, boolean z8, boolean z9) {
        int i10;
        int iBinarySearch = Arrays.binarySearch(iArr, i9);
        if (iBinarySearch < 0) {
            i10 = -(iBinarySearch + 2);
        } else {
            do {
                iBinarySearch--;
                if (iBinarySearch < 0) {
                    break;
                }
            } while (iArr[iBinarySearch] == i9);
            i10 = z8 ? iBinarySearch + 1 : iBinarySearch;
        }
        return z9 ? Math.max(0, i10) : i10;
    }

    public static int ceilDivide(int i9, int i10) {
        return ((i9 + i10) - 1) / i10;
    }

    public static void closeQuietly(DataSource dataSource) {
        if (dataSource != null) {
            try {
                dataSource.close();
            } catch (IOException unused) {
            }
        }
    }

    public static int compareLong(long j9, long j10) {
        if (j9 < j10) {
            return -1;
        }
        return j9 == j10 ? 0 : 1;
    }

    public static int constrainValue(int i9, int i10, int i11) {
        return Math.max(i10, Math.min(i9, i11));
    }

    public static boolean contains(Object[] objArr, Object obj) {
        for (Object obj2 : objArr) {
            if (areEqual(obj2, obj)) {
                return true;
            }
        }
        return false;
    }

    public static int crc(byte[] bArr, int i9, int i10, int i11) {
        while (i9 < i10) {
            i11 = CRC32_BYTES_MSBF[((i11 >>> 24) ^ (bArr[i9] & 255)) & 255] ^ (i11 << 8);
            i9++;
        }
        return i11;
    }

    public static File createTempDirectory(Context context, String str) {
        File fileCreateTempFile = createTempFile(context, str);
        fileCreateTempFile.delete();
        fileCreateTempFile.mkdir();
        return fileCreateTempFile;
    }

    public static File createTempFile(Context context, String str) {
        return File.createTempFile(str, null, context.getCacheDir());
    }

    public static String escapeFileName(String str) {
        int length = str.length();
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < length; i11++) {
            if (shouldEscapeCharacter(str.charAt(i11))) {
                i10++;
            }
        }
        if (i10 == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder((i10 * 2) + length);
        while (i10 > 0) {
            int i12 = i9 + 1;
            char cCharAt = str.charAt(i9);
            if (shouldEscapeCharacter(cCharAt)) {
                sb.append('%');
                sb.append(Integer.toHexString(cCharAt));
                i10--;
            } else {
                sb.append(cCharAt);
            }
            i9 = i12;
        }
        if (i9 < length) {
            sb.append((CharSequence) str, i9, length);
        }
        return sb.toString();
    }

    public static String fromUtf8Bytes(byte[] bArr) {
        return new String(bArr, Charset.forName("UTF-8"));
    }

    public static int getAudioContentTypeForStreamType(int i9) {
        if (i9 != 0) {
            return (i9 == 1 || i9 == 2 || i9 == 4 || i9 == 5 || i9 == 8) ? 4 : 2;
        }
        return 1;
    }

    public static int getAudioUsageForStreamType(int i9) {
        if (i9 == 0) {
            return 2;
        }
        if (i9 == 1) {
            return 13;
        }
        if (i9 == 2) {
            return 6;
        }
        int i10 = 4;
        if (i9 != 4) {
            i10 = 5;
            if (i9 != 5) {
                return i9 != 8 ? 1 : 3;
            }
        }
        return i10;
    }

    public static byte[] getBytesFromHexString(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i9 = 0; i9 < length; i9++) {
            int i10 = i9 * 2;
            bArr[i9] = (byte) ((Character.digit(str.charAt(i10), 16) << 4) + Character.digit(str.charAt(i10 + 1), 16));
        }
        return bArr;
    }

    public static String getCodecsOfType(String str, int i9) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] strArrSplit = str.trim().split("(\\s*,\\s*)");
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArrSplit) {
            if (i9 == MimeTypes.getTrackTypeOfCodec(str2)) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(str2);
            }
        }
        if (sb.length() > 0) {
            return sb.toString();
        }
        return null;
    }

    public static String getCommaDelimitedSimpleClassNames(Object[] objArr) {
        StringBuilder sb = new StringBuilder();
        for (int i9 = 0; i9 < objArr.length; i9++) {
            sb.append(objArr[i9].getClass().getSimpleName());
            if (i9 < objArr.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public static int getDefaultBufferSize(int i9) {
        if (i9 == 0) {
            return C3322C.DEFAULT_MUXED_BUFFER_SIZE;
        }
        if (i9 == 1) {
            return C3322C.DEFAULT_AUDIO_BUFFER_SIZE;
        }
        if (i9 == 2) {
            return C3322C.DEFAULT_VIDEO_BUFFER_SIZE;
        }
        if (i9 == 3 || i9 == 4) {
            return 131072;
        }
        throw new IllegalStateException();
    }

    @TargetApi(16)
    private static void getDisplaySizeV16(Display display, Point point) {
        display.getSize(point);
    }

    @TargetApi(17)
    private static void getDisplaySizeV17(Display display, Point point) {
        display.getRealSize(point);
    }

    @TargetApi(23)
    private static void getDisplaySizeV23(Display display, Point point) {
        Display.Mode mode = display.getMode();
        point.x = mode.getPhysicalWidth();
        point.y = mode.getPhysicalHeight();
    }

    private static void getDisplaySizeV9(Display display, Point point) {
        point.x = display.getWidth();
        point.y = display.getHeight();
    }

    public static UUID getDrmUuid(String str) {
        String lowerInvariant = toLowerInvariant(str);
        lowerInvariant.hashCode();
        switch (lowerInvariant) {
            case "playready":
                return C3322C.PLAYREADY_UUID;
            case "widevine":
                return C3322C.WIDEVINE_UUID;
            case "clearkey":
                return C3322C.CLEARKEY_UUID;
            default:
                try {
                    return UUID.fromString(str);
                } catch (RuntimeException unused) {
                    return null;
                }
        }
    }

    public static int getIntegerCodeForString(String str) {
        int length = str.length();
        Assertions.checkArgument(length <= 4);
        int iCharAt = 0;
        for (int i9 = 0; i9 < length; i9++) {
            iCharAt = (iCharAt << 8) | str.charAt(i9);
        }
        return iCharAt;
    }

    public static long getMediaDurationForPlayoutDuration(long j9, float f9) {
        return f9 == 1.0f ? j9 : Math.round(j9 * f9);
    }

    public static int getPcmEncoding(int i9) {
        if (i9 == 8) {
            return 3;
        }
        if (i9 == 16) {
            return 2;
        }
        if (i9 != 24) {
            return i9 != 32 ? 0 : 1073741824;
        }
        return Integer.MIN_VALUE;
    }

    public static int getPcmFrameSize(int i9, int i10) {
        if (i9 == Integer.MIN_VALUE) {
            return i10 * 3;
        }
        if (i9 != 1073741824) {
            if (i9 == 2) {
                return i10 * 2;
            }
            if (i9 == 3) {
                return i10;
            }
            if (i9 != 4) {
                throw new IllegalArgumentException();
            }
        }
        return i10 * 4;
    }

    public static Point getPhysicalDisplaySize(Context context) {
        return getPhysicalDisplaySize(context, ((WindowManager) context.getSystemService("window")).getDefaultDisplay());
    }

    public static long getPlayoutDurationForMediaDuration(long j9, float f9) {
        return f9 == 1.0f ? j9 : Math.round(j9 / f9);
    }

    public static int getStreamTypeForAudioUsage(int i9) {
        if (i9 == 13) {
            return 1;
        }
        switch (i9) {
            case 2:
                return 0;
            case 3:
                return 8;
            case 4:
                return 4;
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
                return 5;
            case 6:
                return 2;
            default:
                return 3;
        }
    }

    public static String getStringForTime(StringBuilder sb, Formatter formatter, long j9) {
        if (j9 == C3322C.TIME_UNSET) {
            j9 = 0;
        }
        long j10 = (j9 + 500) / 1000;
        long j11 = j10 % 60;
        long j12 = (j10 / 60) % 60;
        long j13 = j10 / 3600;
        sb.setLength(0);
        return j13 > 0 ? formatter.format("%d:%02d:%02d", Long.valueOf(j13), Long.valueOf(j12), Long.valueOf(j11)).toString() : formatter.format("%02d:%02d", Long.valueOf(j12), Long.valueOf(j11)).toString();
    }

    public static String getUserAgent(Context context, String str) {
        String str2;
        try {
            str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            str2 = "?";
        }
        return str + "/" + str2 + " (Linux;Android " + Build.VERSION.RELEASE + ") " + ExoPlayerLibraryInfo.VERSION_SLASHY;
    }

    public static byte[] getUtf8Bytes(String str) {
        return str.getBytes(Charset.forName("UTF-8"));
    }

    public static int inferContentType(Uri uri) {
        String path = uri.getPath();
        if (path == null) {
            return 3;
        }
        return inferContentType(path);
    }

    public static boolean isEncodingHighResolutionIntegerPcm(int i9) {
        return i9 == Integer.MIN_VALUE || i9 == 1073741824;
    }

    public static boolean isLinebreak(int i9) {
        return i9 == 10 || i9 == 13;
    }

    public static boolean isLocalFileUri(Uri uri) {
        String scheme = uri.getScheme();
        return TextUtils.isEmpty(scheme) || scheme.equals("file");
    }

    @TargetApi(23)
    public static boolean maybeRequestReadExternalStoragePermission(Activity activity, Uri... uriArr) {
        if (SDK_INT < 23) {
            return false;
        }
        int length = uriArr.length;
        int i9 = 0;
        while (true) {
            if (i9 >= length) {
                break;
            }
            if (!isLocalFileUri(uriArr[i9])) {
                i9++;
            } else if (activity.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
                activity.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 0);
                return true;
            }
        }
        return false;
    }

    public static ExecutorService newSingleThreadExecutor(final String str) {
        return Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.google.android.exoplayer2.util.Util.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, str);
            }
        });
    }

    public static String normalizeLanguageCode(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new Locale(str).getISO3Language();
        } catch (MissingResourceException unused) {
            return toLowerInvariant(str);
        }
    }

    public static long parseXsDateTime(String str) throws ParserException {
        Matcher matcher = XS_DATE_TIME_PATTERN.matcher(str);
        if (!matcher.matches()) {
            throw new ParserException("Invalid date/time format: " + str);
        }
        int i9 = 0;
        if (matcher.group(9) != null && !matcher.group(9).equalsIgnoreCase("Z")) {
            i9 = (Integer.parseInt(matcher.group(12)) * 60) + Integer.parseInt(matcher.group(13));
            if (matcher.group(11).equals("-")) {
                i9 *= -1;
            }
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        gregorianCalendar.clear();
        gregorianCalendar.set(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)) - 1, Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6)));
        if (!TextUtils.isEmpty(matcher.group(8))) {
            gregorianCalendar.set(14, new BigDecimal("0." + matcher.group(8)).movePointRight(3).intValue());
        }
        long timeInMillis = gregorianCalendar.getTimeInMillis();
        return i9 != 0 ? timeInMillis - (i9 * 60000) : timeInMillis;
    }

    public static long parseXsDuration(String str) throws NumberFormatException {
        Matcher matcher = XS_DURATION_PATTERN.matcher(str);
        if (!matcher.matches()) {
            return (long) (Double.parseDouble(str) * 3600.0d * 1000.0d);
        }
        boolean zIsEmpty = true ^ TextUtils.isEmpty(matcher.group(1));
        String strGroup = matcher.group(3);
        double d9 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        double d10 = strGroup != null ? Double.parseDouble(strGroup) * 3.1556908E7d : 0.0d;
        String strGroup2 = matcher.group(5);
        double d11 = d10 + (strGroup2 != null ? Double.parseDouble(strGroup2) * 2629739.0d : 0.0d);
        String strGroup3 = matcher.group(7);
        double d12 = d11 + (strGroup3 != null ? Double.parseDouble(strGroup3) * 86400.0d : 0.0d);
        String strGroup4 = matcher.group(10);
        double d13 = d12 + (strGroup4 != null ? Double.parseDouble(strGroup4) * 3600.0d : 0.0d);
        String strGroup5 = matcher.group(12);
        double d14 = d13 + (strGroup5 != null ? Double.parseDouble(strGroup5) * 60.0d : 0.0d);
        String strGroup6 = matcher.group(14);
        if (strGroup6 != null) {
            d9 = Double.parseDouble(strGroup6);
        }
        long j9 = (long) ((d14 + d9) * 1000.0d);
        return zIsEmpty ? -j9 : j9;
    }

    public static void recursiveDelete(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                recursiveDelete(file2);
            }
        }
        file.delete();
    }

    public static <T> void removeRange(List<T> list, int i9, int i10) {
        list.subList(i9, i10).clear();
    }

    public static long resolveSeekPositionUs(long j9, SeekParameters seekParameters, long j10, long j11) {
        if (SeekParameters.EXACT.equals(seekParameters)) {
            return j9;
        }
        long jSubtractWithOverflowDefault = subtractWithOverflowDefault(j9, seekParameters.toleranceBeforeUs, Long.MIN_VALUE);
        long jAddWithOverflowDefault = addWithOverflowDefault(j9, seekParameters.toleranceAfterUs, Long.MAX_VALUE);
        boolean z8 = jSubtractWithOverflowDefault <= j10 && j10 <= jAddWithOverflowDefault;
        boolean z9 = jSubtractWithOverflowDefault <= j11 && j11 <= jAddWithOverflowDefault;
        return (z8 && z9) ? Math.abs(j10 - j9) <= Math.abs(j11 - j9) ? j10 : j11 : z8 ? j10 : z9 ? j11 : jSubtractWithOverflowDefault;
    }

    public static long scaleLargeTimestamp(long j9, long j10, long j11) {
        if (j11 >= j10 && j11 % j10 == 0) {
            return j9 / (j11 / j10);
        }
        if (j11 < j10 && j10 % j11 == 0) {
            return j9 * (j10 / j11);
        }
        return (long) (j9 * (j10 / j11));
    }

    public static long[] scaleLargeTimestamps(List<Long> list, long j9, long j10) {
        int size = list.size();
        long[] jArr = new long[size];
        int i9 = 0;
        if (j10 >= j9 && j10 % j9 == 0) {
            long j11 = j10 / j9;
            while (i9 < size) {
                jArr[i9] = list.get(i9).longValue() / j11;
                i9++;
            }
        } else if (j10 >= j9 || j9 % j10 != 0) {
            double d9 = j9 / j10;
            while (i9 < size) {
                jArr[i9] = (long) (list.get(i9).longValue() * d9);
                i9++;
            }
        } else {
            long j12 = j9 / j10;
            while (i9 < size) {
                jArr[i9] = list.get(i9).longValue() * j12;
                i9++;
            }
        }
        return jArr;
    }

    public static void scaleLargeTimestampsInPlace(long[] jArr, long j9, long j10) {
        int i9 = 0;
        if (j10 >= j9 && j10 % j9 == 0) {
            long j11 = j10 / j9;
            while (i9 < jArr.length) {
                jArr[i9] = jArr[i9] / j11;
                i9++;
            }
            return;
        }
        if (j10 >= j9 || j9 % j10 != 0) {
            double d9 = j9 / j10;
            while (i9 < jArr.length) {
                jArr[i9] = (long) (jArr[i9] * d9);
                i9++;
            }
            return;
        }
        long j12 = j9 / j10;
        while (i9 < jArr.length) {
            jArr[i9] = jArr[i9] * j12;
            i9++;
        }
    }

    private static boolean shouldEscapeCharacter(char c9) {
        return c9 == '\"' || c9 == '%' || c9 == '*' || c9 == '/' || c9 == ':' || c9 == '<' || c9 == '\\' || c9 == '|' || c9 == '>' || c9 == '?';
    }

    public static void sneakyThrow(Throwable th) throws Throwable {
        sneakyThrowInternal(th);
    }

    private static <T extends Throwable> void sneakyThrowInternal(Throwable th) throws Throwable {
        throw th;
    }

    public static long subtractWithOverflowDefault(long j9, long j10, long j11) {
        long j12 = j9 - j10;
        return ((j9 ^ j12) & (j10 ^ j9)) < 0 ? j11 : j12;
    }

    public static int[] toArray(List<Integer> list) {
        if (list == null) {
            return null;
        }
        int size = list.size();
        int[] iArr = new int[size];
        for (int i9 = 0; i9 < size; i9++) {
            iArr[i9] = list.get(i9).intValue();
        }
        return iArr;
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[4096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int i9 = inputStream.read(bArr);
            if (i9 == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i9);
        }
    }

    public static String toLowerInvariant(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase(Locale.US);
    }

    public static String toUpperInvariant(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase(Locale.US);
    }

    public static String unescapeFileName(String str) {
        int length = str.length();
        int iEnd = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < length; i10++) {
            if (str.charAt(i10) == '%') {
                i9++;
            }
        }
        if (i9 == 0) {
            return str;
        }
        int i11 = length - (i9 * 2);
        StringBuilder sb = new StringBuilder(i11);
        Matcher matcher = ESCAPED_CHARACTER_PATTERN.matcher(str);
        while (i9 > 0 && matcher.find()) {
            char c9 = (char) Integer.parseInt(matcher.group(1), 16);
            sb.append((CharSequence) str, iEnd, matcher.start());
            sb.append(c9);
            iEnd = matcher.end();
            i9--;
        }
        if (iEnd < length) {
            sb.append((CharSequence) str, iEnd, length);
        }
        if (sb.length() != i11) {
            return null;
        }
        return sb.toString();
    }

    public static long ceilDivide(long j9, long j10) {
        return ((j9 + j10) - 1) / j10;
    }

    public static void closeQuietly(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static long constrainValue(long j9, long j10, long j11) {
        return Math.max(j10, Math.min(j9, j11));
    }

    public static float constrainValue(float f9, float f10, float f11) {
        return Math.max(f10, Math.min(f9, f11));
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Point getPhysicalDisplaySize(Context context, Display display) throws ClassNotFoundException, NumberFormatException {
        String str;
        if (SDK_INT < 25 && display.getDisplayId() == 0) {
            String str2 = MANUFACTURER;
            if ("Sony".equals(str2) && MODEL.startsWith("BRAVIA") && context.getPackageManager().hasSystemFeature("com.sony.dtv.hardware.panel.qfhd")) {
                return new Point(3840, 2160);
            }
            if (!"NVIDIA".equals(str2) || !MODEL.contains("SHIELD")) {
                if ("philips".equals(toLowerInvariant(str2))) {
                    String str3 = MODEL;
                    if (str3.startsWith("QM1") || str3.equals("QV151E") || str3.equals("TPM171E")) {
                        try {
                            Class<?> cls = Class.forName("android.os.SystemProperties");
                            str = (String) cls.getMethod("get", String.class).invoke(cls, "sys.display-size");
                        } catch (Exception e9) {
                            Log.e(TAG, "Failed to read sys.display-size", e9);
                            str = null;
                        }
                        if (!TextUtils.isEmpty(str)) {
                            try {
                                String[] strArrSplit = str.trim().split("x");
                                if (strArrSplit.length == 2) {
                                    int i9 = Integer.parseInt(strArrSplit[0]);
                                    int i10 = Integer.parseInt(strArrSplit[1]);
                                    if (i9 > 0 && i10 > 0) {
                                        return new Point(i9, i10);
                                    }
                                }
                            } catch (NumberFormatException unused) {
                            }
                            Log.e(TAG, "Invalid sys.display-size: " + str);
                        }
                    }
                }
            }
        }
        Point point = new Point();
        int i11 = SDK_INT;
        if (i11 >= 23) {
            getDisplaySizeV23(display, point);
        } else if (i11 >= 17) {
            getDisplaySizeV17(display, point);
        } else if (i11 >= 16) {
            getDisplaySizeV16(display, point);
        } else {
            getDisplaySizeV9(display, point);
        }
        return point;
    }

    public static int inferContentType(String str) {
        String lowerInvariant = toLowerInvariant(str);
        if (lowerInvariant.endsWith(".mpd")) {
            return 0;
        }
        if (lowerInvariant.endsWith(".m3u8")) {
            return 2;
        }
        return lowerInvariant.matches(".*\\.ism(l)?(/manifest(\\(.+\\))?)?") ? 1 : 3;
    }

    public static <T> int binarySearchCeil(List<? extends Comparable<? super T>> list, T t8, boolean z8, boolean z9) {
        int i9;
        int iBinarySearch = Collections.binarySearch(list, t8);
        if (iBinarySearch < 0) {
            i9 = ~iBinarySearch;
        } else {
            int size = list.size();
            do {
                iBinarySearch++;
                if (iBinarySearch >= size) {
                    break;
                }
            } while (list.get(iBinarySearch).compareTo(t8) == 0);
            i9 = z8 ? iBinarySearch - 1 : iBinarySearch;
        }
        return z9 ? Math.min(list.size() - 1, i9) : i9;
    }

    public static int binarySearchFloor(long[] jArr, long j9, boolean z8, boolean z9) {
        int i9;
        int iBinarySearch = Arrays.binarySearch(jArr, j9);
        if (iBinarySearch < 0) {
            i9 = -(iBinarySearch + 2);
        } else {
            do {
                iBinarySearch--;
                if (iBinarySearch < 0) {
                    break;
                }
            } while (jArr[iBinarySearch] == j9);
            i9 = z8 ? iBinarySearch + 1 : iBinarySearch;
        }
        return z9 ? Math.max(0, i9) : i9;
    }

    public static <T> int binarySearchFloor(List<? extends Comparable<? super T>> list, T t8, boolean z8, boolean z9) {
        int i9;
        int iBinarySearch = Collections.binarySearch(list, t8);
        if (iBinarySearch < 0) {
            i9 = -(iBinarySearch + 2);
        } else {
            do {
                iBinarySearch--;
                if (iBinarySearch < 0) {
                    break;
                }
            } while (list.get(iBinarySearch).compareTo(t8) == 0);
            i9 = z8 ? iBinarySearch + 1 : iBinarySearch;
        }
        return z9 ? Math.max(0, i9) : i9;
    }
}
