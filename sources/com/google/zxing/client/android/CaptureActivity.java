package com.google.zxing.client.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.camera.CameraManager;
import com.google.zxing.client.android.clipboard.ClipboardInterface;
import com.google.zxing.client.android.history.HistoryManager;
import com.google.zxing.client.android.result.ResultButtonListener;
import com.google.zxing.client.android.result.ResultHandler;
import com.google.zxing.client.android.result.ResultHandlerFactory;
import com.google.zxing.client.android.result.supplement.SupplementalInfoRetriever;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
public final class CaptureActivity extends Activity implements SurfaceHolder.Callback {
    private static final long BULK_MODE_SCAN_DELAY_MS = 1000;
    private static final long DEFAULT_INTENT_RESULT_DURATION_MS = 1500;
    public static final int HISTORY_REQUEST_CODE = 47820;
    private static final String TAG = "CaptureActivity";
    private AmbientLightManager ambientLightManager;
    private BeepManager beepManager;
    private CameraManager cameraManager;
    private String characterSet;
    private boolean copyToClipboard;
    private Collection<BarcodeFormat> decodeFormats;
    private Map<DecodeHintType, ?> decodeHints;
    private CaptureActivityHandler handler;
    private boolean hasSurface;
    private HistoryManager historyManager;
    private InactivityTimer inactivityTimer;
    private Result lastResult;
    private View resultView;
    private Result savedResultToShow;
    private ScanFromWebPageManager scanFromWebPageManager;
    private IntentSource source;
    private String sourceUrl;
    private TextView statusView;
    private ViewfinderView viewfinderView;
    private static final String[] ZXING_URLS = {"http://zxing.appspot.com/scan", "zxing://scan/"};
    private static final Collection<ResultMetadataType> DISPLAYABLE_METADATA_TYPES = EnumSet.of(ResultMetadataType.ISSUE_NUMBER, ResultMetadataType.SUGGESTED_PRICE, ResultMetadataType.ERROR_CORRECTION_LEVEL, ResultMetadataType.POSSIBLE_COUNTRY);

    /* renamed from: com.google.zxing.client.android.CaptureActivity$1 */
    public static /* synthetic */ class C44491 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$client$android$IntentSource;

        static {
            int[] iArr = new int[IntentSource.values().length];
            $SwitchMap$com$google$zxing$client$android$IntentSource = iArr;
            try {
                iArr[IntentSource.NATIVE_APP_INTENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$zxing$client$android$IntentSource[IntentSource.PRODUCT_SEARCH_LINK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$zxing$client$android$IntentSource[IntentSource.ZXING_LINK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$zxing$client$android$IntentSource[IntentSource.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void decodeOrStoreSavedBitmap(Bitmap bitmap, Result result) {
        CaptureActivityHandler captureActivityHandler = this.handler;
        if (captureActivityHandler == null) {
            this.savedResultToShow = result;
            return;
        }
        if (result != null) {
            this.savedResultToShow = result;
        }
        Result result2 = this.savedResultToShow;
        if (result2 != null) {
            this.handler.sendMessage(Message.obtain(captureActivityHandler, C4453R.id.decode_succeeded, result2));
        }
        this.savedResultToShow = null;
    }

    private void displayFrameworkBugMessageAndExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(C4453R.string.app_name));
        builder.setMessage(getString(C4453R.string.msg_camera_framework_bug));
        builder.setPositiveButton(C4453R.string.button_ok, new FinishListener(this));
        builder.setOnCancelListener(new FinishListener(this));
        builder.show();
    }

    private static void drawLine(Canvas canvas, Paint paint, ResultPoint resultPoint, ResultPoint resultPoint2, float f9) {
        if (resultPoint == null || resultPoint2 == null) {
            return;
        }
        canvas.drawLine(f9 * resultPoint.getX(), f9 * resultPoint.getY(), f9 * resultPoint2.getX(), f9 * resultPoint2.getY(), paint);
    }

    private void drawResultPoints(Bitmap bitmap, float f9, Result result) {
        ResultPoint[] resultPoints = result.getResultPoints();
        if (resultPoints == null || resultPoints.length <= 0) {
            return;
        }
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(C4453R.color.result_points));
        if (resultPoints.length == 2) {
            paint.setStrokeWidth(4.0f);
            drawLine(canvas, paint, resultPoints[0], resultPoints[1], f9);
            return;
        }
        if (resultPoints.length == 4 && (result.getBarcodeFormat() == BarcodeFormat.UPC_A || result.getBarcodeFormat() == BarcodeFormat.EAN_13)) {
            drawLine(canvas, paint, resultPoints[0], resultPoints[1], f9);
            drawLine(canvas, paint, resultPoints[2], resultPoints[3], f9);
            return;
        }
        paint.setStrokeWidth(10.0f);
        for (ResultPoint resultPoint : resultPoints) {
            if (resultPoint != null) {
                canvas.drawPoint(resultPoint.getX() * f9, resultPoint.getY() * f9, paint);
            }
        }
    }

    private int getCurrentOrientation() {
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        return getResources().getConfiguration().orientation == 2 ? (rotation == 0 || rotation == 1) ? 0 : 8 : (rotation == 0 || rotation == 3) ? 1 : 9;
    }

    private void handleDecodeExternally(Result result, ResultHandler resultHandler, Bitmap bitmap) {
        ScanFromWebPageManager scanFromWebPageManager;
        if (bitmap != null) {
            this.viewfinderView.drawResultBitmap(bitmap);
        }
        Intent intent = getIntent();
        long longExtra = DEFAULT_INTENT_RESULT_DURATION_MS;
        if (intent != null) {
            longExtra = getIntent().getLongExtra(Intents.Scan.RESULT_DISPLAY_DURATION_MS, DEFAULT_INTENT_RESULT_DURATION_MS);
        }
        int i9 = 0;
        if (longExtra > 0) {
            String strValueOf = String.valueOf(result);
            if (strValueOf.length() > 32) {
                strValueOf = strValueOf.substring(0, 32) + " ...";
            }
            this.statusView.setText(getString(resultHandler.getDisplayTitle()) + " : " + strValueOf);
        }
        if (this.copyToClipboard && !resultHandler.areContentsSecure()) {
            ClipboardInterface.setText(resultHandler.getDisplayContents(), this);
        }
        IntentSource intentSource = this.source;
        if (intentSource != IntentSource.NATIVE_APP_INTENT) {
            if (intentSource == IntentSource.PRODUCT_SEARCH_LINK) {
                sendReplyMessage(C4453R.id.launch_product_query, this.sourceUrl.substring(0, this.sourceUrl.lastIndexOf("/scan")) + "?q=" + ((Object) resultHandler.getDisplayContents()) + "&source=zxing", longExtra);
                return;
            }
            if (intentSource == IntentSource.ZXING_LINK && (scanFromWebPageManager = this.scanFromWebPageManager) != null && scanFromWebPageManager.isScanFromWebPage()) {
                Object objBuildReplyURL = this.scanFromWebPageManager.buildReplyURL(result, resultHandler);
                this.scanFromWebPageManager = null;
                sendReplyMessage(C4453R.id.launch_product_query, objBuildReplyURL, longExtra);
                return;
            }
            return;
        }
        Intent intent2 = new Intent(getIntent().getAction());
        intent2.addFlags(524288);
        intent2.putExtra(Intents.Scan.RESULT, result.toString());
        intent2.putExtra(Intents.Scan.RESULT_FORMAT, result.getBarcodeFormat().toString());
        byte[] rawBytes = result.getRawBytes();
        if (rawBytes != null && rawBytes.length > 0) {
            intent2.putExtra(Intents.Scan.RESULT_BYTES, rawBytes);
        }
        Map<ResultMetadataType, Object> resultMetadata = result.getResultMetadata();
        if (resultMetadata != null) {
            ResultMetadataType resultMetadataType = ResultMetadataType.UPC_EAN_EXTENSION;
            if (resultMetadata.containsKey(resultMetadataType)) {
                intent2.putExtra(Intents.Scan.RESULT_UPC_EAN_EXTENSION, resultMetadata.get(resultMetadataType).toString());
            }
            Number number = (Number) resultMetadata.get(ResultMetadataType.ORIENTATION);
            if (number != null) {
                intent2.putExtra(Intents.Scan.RESULT_ORIENTATION, number.intValue());
            }
            String str = (String) resultMetadata.get(ResultMetadataType.ERROR_CORRECTION_LEVEL);
            if (str != null) {
                intent2.putExtra(Intents.Scan.RESULT_ERROR_CORRECTION_LEVEL, str);
            }
            Iterable iterable = (Iterable) resultMetadata.get(ResultMetadataType.BYTE_SEGMENTS);
            if (iterable != null) {
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    intent2.putExtra(Intents.Scan.RESULT_BYTE_SEGMENTS_PREFIX + i9, (byte[]) it.next());
                    i9++;
                }
            }
        }
        sendReplyMessage(C4453R.id.return_scan_result, intent2, longExtra);
    }

    private void handleDecodeInternally(Result result, ResultHandler resultHandler, Bitmap bitmap) {
        CharSequence displayContents = resultHandler.getDisplayContents();
        if (this.copyToClipboard && !resultHandler.areContentsSecure()) {
            ClipboardInterface.setText(displayContents, this);
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (resultHandler.getDefaultButtonID() != null && defaultSharedPreferences.getBoolean(PreferencesActivity.KEY_AUTO_OPEN_WEB, false)) {
            resultHandler.handleButtonPress(resultHandler.getDefaultButtonID().intValue());
            return;
        }
        this.statusView.setVisibility(8);
        this.viewfinderView.setVisibility(8);
        this.resultView.setVisibility(0);
        ImageView imageView = (ImageView) findViewById(C4453R.id.barcode_image_view);
        if (bitmap == null) {
            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), C4453R.drawable.launcher_icon));
        } else {
            imageView.setImageBitmap(bitmap);
        }
        ((TextView) findViewById(C4453R.id.format_text_view)).setText(result.getBarcodeFormat().toString());
        ((TextView) findViewById(C4453R.id.type_text_view)).setText(resultHandler.getType().toString());
        ((TextView) findViewById(C4453R.id.time_text_view)).setText(DateFormat.getDateTimeInstance(3, 3).format(new Date(result.getTimestamp())));
        TextView textView = (TextView) findViewById(C4453R.id.meta_text_view);
        View viewFindViewById = findViewById(C4453R.id.meta_text_view_label);
        textView.setVisibility(8);
        viewFindViewById.setVisibility(8);
        Map<ResultMetadataType, Object> resultMetadata = result.getResultMetadata();
        if (resultMetadata != null) {
            StringBuilder sb = new StringBuilder(20);
            for (Map.Entry<ResultMetadataType, Object> entry : resultMetadata.entrySet()) {
                if (DISPLAYABLE_METADATA_TYPES.contains(entry.getKey())) {
                    sb.append(entry.getValue());
                    sb.append('\n');
                }
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
                textView.setText(sb);
                textView.setVisibility(0);
                viewFindViewById.setVisibility(0);
            }
        }
        TextView textView2 = (TextView) findViewById(C4453R.id.contents_text_view);
        textView2.setText(displayContents);
        textView2.setTextSize(2, Math.max(22, 32 - (displayContents.length() / 4)));
        TextView textView3 = (TextView) findViewById(C4453R.id.contents_supplement_text_view);
        textView3.setText("");
        textView3.setOnClickListener(null);
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean(PreferencesActivity.KEY_SUPPLEMENTAL, true)) {
            SupplementalInfoRetriever.maybeInvokeRetrieval(textView3, resultHandler.getResult(), this.historyManager, this);
        }
        int buttonCount = resultHandler.getButtonCount();
        ViewGroup viewGroup = (ViewGroup) findViewById(C4453R.id.result_button_view);
        viewGroup.requestFocus();
        for (int i9 = 0; i9 < 4; i9++) {
            TextView textView4 = (TextView) viewGroup.getChildAt(i9);
            if (i9 < buttonCount) {
                textView4.setVisibility(0);
                textView4.setText(resultHandler.getButtonText(i9));
                textView4.setOnClickListener(new ResultButtonListener(resultHandler, i9));
            } else {
                textView4.setVisibility(8);
            }
        }
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (this.cameraManager.isOpen()) {
            Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?");
            return;
        }
        try {
            this.cameraManager.openDriver(surfaceHolder);
            if (this.handler == null) {
                this.handler = new CaptureActivityHandler(this, this.decodeFormats, this.decodeHints, this.characterSet, this.cameraManager);
            }
            decodeOrStoreSavedBitmap(null, null);
        } catch (IOException e9) {
            Log.w(TAG, e9);
            displayFrameworkBugMessageAndExit();
        } catch (RuntimeException e10) {
            Log.w(TAG, "Unexpected error initializing camera", e10);
            displayFrameworkBugMessageAndExit();
        }
    }

    private static boolean isZXingURL(String str) {
        if (str == null) {
            return false;
        }
        for (String str2 : ZXING_URLS) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    private void resetStatusView() {
        this.resultView.setVisibility(8);
        this.statusView.setText(C4453R.string.msg_default_status);
        this.statusView.setVisibility(0);
        this.viewfinderView.setVisibility(0);
        this.lastResult = null;
    }

    private void sendReplyMessage(int i9, Object obj, long j9) {
        CaptureActivityHandler captureActivityHandler = this.handler;
        if (captureActivityHandler != null) {
            Message messageObtain = Message.obtain(captureActivityHandler, i9, obj);
            if (j9 > 0) {
                this.handler.sendMessageDelayed(messageObtain, j9);
            } else {
                this.handler.sendMessage(messageObtain);
            }
        }
    }

    public void drawViewfinder() {
        this.viewfinderView.drawViewfinder();
    }

    public CameraManager getCameraManager() {
        return this.cameraManager;
    }

    public Handler getHandler() {
        return this.handler;
    }

    public ViewfinderView getViewfinderView() {
        return this.viewfinderView;
    }

    public void handleDecode(Result result, Bitmap bitmap, float f9) throws Throwable {
        this.inactivityTimer.onActivity();
        this.lastResult = result;
        ResultHandler resultHandlerMakeResultHandler = ResultHandlerFactory.makeResultHandler(this, result);
        boolean z8 = bitmap != null;
        if (z8) {
            this.historyManager.addHistoryItem(result, resultHandlerMakeResultHandler);
            this.beepManager.playBeepSoundAndVibrate();
            drawResultPoints(bitmap, f9, result);
        }
        int i9 = C44491.$SwitchMap$com$google$zxing$client$android$IntentSource[this.source.ordinal()];
        if (i9 == 1 || i9 == 2) {
            handleDecodeExternally(result, resultHandlerMakeResultHandler, bitmap);
            return;
        }
        if (i9 == 3) {
            ScanFromWebPageManager scanFromWebPageManager = this.scanFromWebPageManager;
            if (scanFromWebPageManager == null || !scanFromWebPageManager.isScanFromWebPage()) {
                handleDecodeInternally(result, resultHandlerMakeResultHandler, bitmap);
                return;
            } else {
                handleDecodeExternally(result, resultHandlerMakeResultHandler, bitmap);
                return;
            }
        }
        if (i9 != 4) {
            return;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!z8 || !defaultSharedPreferences.getBoolean(PreferencesActivity.KEY_BULK_MODE, false)) {
            handleDecodeInternally(result, resultHandlerMakeResultHandler, bitmap);
            return;
        }
        Toast.makeText(getApplicationContext(), getResources().getString(C4453R.string.msg_bulk_mode_scanned) + " (" + result.getText() + ')', 0).show();
        restartPreviewAfterDelay(1000L);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        int intExtra;
        if (i10 != -1 || i9 != 47820 || this.historyManager == null || (intExtra = intent.getIntExtra(Intents.History.ITEM_NUMBER, -1)) < 0) {
            return;
        }
        decodeOrStoreSavedBitmap(null, this.historyManager.buildHistoryItem(intExtra).getResult());
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        setContentView(C4453R.layout.capture);
        this.hasSurface = false;
        this.inactivityTimer = new InactivityTimer(this);
        this.beepManager = new BeepManager(this);
        this.ambientLightManager = new AmbientLightManager(this);
        PreferenceManager.setDefaultValues(this, C4453R.xml.preferences, false);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C4453R.menu.capture, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override // android.app.Activity
    public void onDestroy() {
        this.inactivityTimer.shutdown();
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i9, KeyEvent keyEvent) {
        if (i9 != 4) {
            if (i9 != 27 && i9 != 80) {
                if (i9 == 24) {
                    this.cameraManager.setTorch(true);
                } else if (i9 == 25) {
                    this.cameraManager.setTorch(false);
                    return true;
                }
            }
            return true;
        }
        IntentSource intentSource = this.source;
        if (intentSource == IntentSource.NATIVE_APP_INTENT) {
            setResult(0);
            finish();
            return true;
        }
        if ((intentSource == IntentSource.NONE || intentSource == IntentSource.ZXING_LINK) && this.lastResult != null) {
            restartPreviewAfterDelay(0L);
            return true;
        }
        return super.onKeyDown(i9, keyEvent);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        new Intent("android.intent.action.VIEW").addFlags(524288);
        return true;
    }

    @Override // android.app.Activity
    public void onPause() throws InterruptedException {
        CaptureActivityHandler captureActivityHandler = this.handler;
        if (captureActivityHandler != null) {
            captureActivityHandler.quitSynchronously();
            this.handler = null;
        }
        this.inactivityTimer.onPause();
        this.ambientLightManager.stop();
        this.beepManager.close();
        this.cameraManager.closeDriver();
        if (!this.hasSurface) {
            ((SurfaceView) findViewById(C4453R.id.preview_view)).getHolder().removeCallback(this);
        }
        super.onPause();
    }

    @Override // android.app.Activity
    public void onResume() throws Throwable {
        int intExtra;
        super.onResume();
        HistoryManager historyManager = new HistoryManager(this);
        this.historyManager = historyManager;
        historyManager.trimHistory();
        this.cameraManager = new CameraManager(getApplication());
        ViewfinderView viewfinderView = (ViewfinderView) findViewById(C4453R.id.viewfinder_view);
        this.viewfinderView = viewfinderView;
        viewfinderView.setCameraManager(this.cameraManager);
        this.resultView = findViewById(C4453R.id.result_view);
        this.statusView = (TextView) findViewById(C4453R.id.status_view);
        this.handler = null;
        this.lastResult = null;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean z8 = true;
        if (defaultSharedPreferences.getBoolean(PreferencesActivity.KEY_DISABLE_AUTO_ORIENTATION, true)) {
            setRequestedOrientation(getCurrentOrientation());
        } else {
            setRequestedOrientation(6);
        }
        resetStatusView();
        this.beepManager.updatePrefs();
        this.ambientLightManager.start(this.cameraManager);
        this.inactivityTimer.onResume();
        Intent intent = getIntent();
        if (!defaultSharedPreferences.getBoolean(PreferencesActivity.KEY_COPY_TO_CLIPBOARD, true) || (intent != null && !intent.getBooleanExtra(Intents.Scan.SAVE_HISTORY, true))) {
            z8 = false;
        }
        this.copyToClipboard = z8;
        this.source = IntentSource.NONE;
        this.sourceUrl = null;
        this.scanFromWebPageManager = null;
        this.decodeFormats = null;
        this.characterSet = null;
        if (intent != null) {
            String action = intent.getAction();
            String dataString = intent.getDataString();
            if (Intents.Scan.ACTION.equals(action)) {
                this.source = IntentSource.NATIVE_APP_INTENT;
                this.decodeFormats = DecodeFormatManager.parseDecodeFormats(intent);
                this.decodeHints = DecodeHintManager.parseDecodeHints(intent);
                if (intent.hasExtra(Intents.Scan.WIDTH) && intent.hasExtra(Intents.Scan.HEIGHT)) {
                    int intExtra2 = intent.getIntExtra(Intents.Scan.WIDTH, 0);
                    int intExtra3 = intent.getIntExtra(Intents.Scan.HEIGHT, 0);
                    if (intExtra2 > 0 && intExtra3 > 0) {
                        this.cameraManager.setManualFramingRect(intExtra2, intExtra3);
                    }
                }
                if (intent.hasExtra(Intents.Scan.CAMERA_ID) && (intExtra = intent.getIntExtra(Intents.Scan.CAMERA_ID, -1)) >= 0) {
                    this.cameraManager.setManualCameraId(intExtra);
                }
                String stringExtra = intent.getStringExtra(Intents.Scan.PROMPT_MESSAGE);
                if (stringExtra != null) {
                    this.statusView.setText(stringExtra);
                }
            } else if (isZXingURL(dataString)) {
                this.source = IntentSource.ZXING_LINK;
                this.sourceUrl = dataString;
                Uri uri = Uri.parse(dataString);
                this.scanFromWebPageManager = new ScanFromWebPageManager(uri);
                this.decodeFormats = DecodeFormatManager.parseDecodeFormats(uri);
                this.decodeHints = DecodeHintManager.parseDecodeHints(uri);
            }
            this.characterSet = intent.getStringExtra(Intents.Scan.CHARACTER_SET);
        }
        SurfaceHolder holder = ((SurfaceView) findViewById(C4453R.id.preview_view)).getHolder();
        if (this.hasSurface) {
            initCamera(holder);
        } else {
            holder.addCallback(this);
        }
    }

    public void restartPreviewAfterDelay(long j9) {
        CaptureActivityHandler captureActivityHandler = this.handler;
        if (captureActivityHandler != null) {
            captureActivityHandler.sendEmptyMessageDelayed(C4453R.id.restart_preview, j9);
        }
        resetStatusView();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i9, int i10, int i11) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            Log.e(TAG, "*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (this.hasSurface) {
            return;
        }
        this.hasSurface = true;
        initCamera(surfaceHolder);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.hasSurface = false;
    }
}
