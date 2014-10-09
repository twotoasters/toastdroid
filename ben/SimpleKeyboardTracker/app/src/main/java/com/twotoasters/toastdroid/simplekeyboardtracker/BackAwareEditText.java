package com.twotoasters.toastdroid.simplekeyboardtracker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;

public class BackAwareEditText extends android.widget.EditText {

    private BackPressedListener mOnImeBack;

    public BackAwareEditText(Context context) {
        super(context);
    }

    public BackAwareEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BackAwareEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            if (mOnImeBack != null) mOnImeBack.onImeBack(this);
        }
        return super.dispatchKeyEvent(event);
    }

    public void setBackPressedListener(BackPressedListener listener) {
        mOnImeBack = listener;
    }

    public interface BackPressedListener {
        void onImeBack(BackAwareEditText editText);
    }
}

