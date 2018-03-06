package com.lockscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class LockScreenActivity extends Activity {

    @Override
    public void onAttachedToWindow() {
        // TODO Auto-generated method stub
        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG | WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onAttachedToWindow();
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_lock_screen);

        TextView tvUnlock = (TextView) findViewById(R.id.tvUnlock);
        tvUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView tvUnlock2 = (TextView) findViewById(R.id.tvUnlock2);
        tvUnlock2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnOpenApp = (Button) findViewById(R.id.btnOpenApp);
        btnOpenApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LockScreenActivity.this, StartLockScreen.class));
                finish();
            }
        });

        HorizontalPagerWithPageControl mPager = (HorizontalPagerWithPageControl) findViewById(R.id.horizontal_pager);
        mPager.addPagerControl();

        try {
            startService(new Intent(this, LockScreenService.class));
//            StateListener phoneStateListener = new StateListener();
//            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//            telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

//    class StateListener extends PhoneStateListener {
//        @Override
//        public void onCallStateChanged(int state, String incomingNumber) {
//
//            super.onCallStateChanged(state, incomingNumber);
//            switch (state) {
//                case TelephonyManager.CALL_STATE_RINGING:
//                    break;
//                case TelephonyManager.CALL_STATE_OFFHOOK:
//                    System.out.println("call Activity off hook");
//                    finish();
//                    break;
//                case TelephonyManager.CALL_STATE_IDLE:
//                    break;
//            }
//        }
//    }

    @Override
    public void onBackPressed() {
        // Don't allow back to dismiss.
        return;
    }

    //only used in lockdown mode
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) || (keyCode == KeyEvent.KEYCODE_POWER) || (keyCode == KeyEvent.KEYCODE_VOLUME_UP) || (keyCode == KeyEvent.KEYCODE_CAMERA)) {
            return true;
        }
        if ((keyCode == KeyEvent.KEYCODE_HOME)) {
            return true;
        }

        return false;

    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_POWER || (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN) || (event.getKeyCode() == KeyEvent.KEYCODE_POWER)) {
            return false;
        }
        if ((event.getKeyCode() == KeyEvent.KEYCODE_HOME)) {
            return true;
        }
        return false;
    }

    public void onDestroy() {
        super.onDestroy();
    }

}