package com.example.keypadcustom;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements KeypadAdapter.OnKeyTypeCallback {

    GridView gvKeyboard;

    OtpEditText otpEditText;

    String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gvKeyboard = findViewById(R.id.gvKeyboard);
        otpEditText = findViewById(R.id.et_otp);

        KeypadAdapter adapter = new KeypadAdapter(this, getResources().getStringArray(R.array.keypad), this);
        gvKeyboard.setAdapter(adapter);
    }


    private void validateOtp(String s){
        Log.v("test123", "s:"+ s + " " + + s.length());

        if (s.equals("1234")) {
            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
        } else if (s.length() == 4) {
            Log.v("test123", "s" + s.length());
            Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
            otpEditText.setText(null);
            otp = null;
        }
    }

    @Override
    public void onTextChangeListener(String key) {
        if(key.equals(getString(R.string.clear_keypad))){
            if(otp != null && !otp.isEmpty()) {
                otp = otp.substring(0, otp.length() - 1);
                otpEditText.setText(otp);
            }
        }
        else if(key.equals(getString(R.string.next_keypad))){
            validateOtp(otp);
        }
        else if(otp == null || otp.length() < 4 ) {
            if (otp == null) otp = "" + key;
            else otp += key;
            otpEditText.setText(otp);
        }
    }
}
