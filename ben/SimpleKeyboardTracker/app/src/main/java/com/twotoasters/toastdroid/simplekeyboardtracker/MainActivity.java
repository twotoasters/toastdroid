package com.twotoasters.toastdroid.simplekeyboardtracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.twotoasters.toastdroid.simplekeyboardtracker.BackAwareEditText.BackPressedListener;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


public class MainActivity extends Activity {

    private boolean isKeyboardOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BackAwareEditText editText = (BackAwareEditText) findViewById(R.id.edit_text);
        editText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                isKeyboardOpen = true;
            }
        });

        editText.setBackPressedListener(new BackPressedListener() {
            @Override
            public void onImeBack(BackAwareEditText editText) {
                isKeyboardOpen = false;
            }
        });

        final Activity activity = this;
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int message = isKeyboardOpen ? R.string.keyboard_open : R.string.keyboard_closed;
                Crouton.cancelAllCroutons();
                Crouton.showText(activity, message, Style.INFO);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        isKeyboardOpen = false;
    }
}
