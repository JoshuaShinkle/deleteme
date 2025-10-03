package p071g;

import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

/* renamed from: g.i */
/* loaded from: classes.dex */
public class WindowCallbackC4803i implements Window.Callback {

    /* renamed from: b */
    public final Window.Callback f16722b;

    public WindowCallbackC4803i(Window.Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Window callback may not be null");
        }
        this.f16722b = callback;
    }

    /* renamed from: a */
    public final Window.Callback m19071a() {
        return this.f16722b;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return this.f16722b.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.f16722b.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return this.f16722b.dispatchKeyShortcutEvent(keyEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return this.f16722b.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.f16722b.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return this.f16722b.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public void onActionModeFinished(ActionMode actionMode) {
        this.f16722b.onActionModeFinished(actionMode);
    }

    @Override // android.view.Window.Callback
    public void onActionModeStarted(ActionMode actionMode) {
        this.f16722b.onActionModeStarted(actionMode);
    }

    @Override // android.view.Window.Callback
    public void onAttachedToWindow() {
        this.f16722b.onAttachedToWindow();
    }

    @Override // android.view.Window.Callback
    public boolean onCreatePanelMenu(int i9, Menu menu) {
        return this.f16722b.onCreatePanelMenu(i9, menu);
    }

    @Override // android.view.Window.Callback
    public View onCreatePanelView(int i9) {
        return this.f16722b.onCreatePanelView(i9);
    }

    @Override // android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.f16722b.onDetachedFromWindow();
    }

    @Override // android.view.Window.Callback
    public boolean onMenuItemSelected(int i9, MenuItem menuItem) {
        return this.f16722b.onMenuItemSelected(i9, menuItem);
    }

    @Override // android.view.Window.Callback
    public boolean onMenuOpened(int i9, Menu menu) {
        return this.f16722b.onMenuOpened(i9, menu);
    }

    @Override // android.view.Window.Callback
    public void onPanelClosed(int i9, Menu menu) {
        this.f16722b.onPanelClosed(i9, menu);
    }

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean z8) {
        this.f16722b.onPointerCaptureChanged(z8);
    }

    @Override // android.view.Window.Callback
    public boolean onPreparePanel(int i9, View view, Menu menu) {
        return this.f16722b.onPreparePanel(i9, view, menu);
    }

    @Override // android.view.Window.Callback
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i9) {
        this.f16722b.onProvideKeyboardShortcuts(list, menu, i9);
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested(SearchEvent searchEvent) {
        return this.f16722b.onSearchRequested(searchEvent);
    }

    @Override // android.view.Window.Callback
    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        this.f16722b.onWindowAttributesChanged(layoutParams);
    }

    @Override // android.view.Window.Callback
    public void onWindowFocusChanged(boolean z8) {
        this.f16722b.onWindowFocusChanged(z8);
    }

    @Override // android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i9) {
        return this.f16722b.onWindowStartingActionMode(callback, i9);
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested() {
        return this.f16722b.onSearchRequested();
    }
}
