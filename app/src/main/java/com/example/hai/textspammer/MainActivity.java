package com.example.hai.textspammer;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     *  Used to send the text message along with the configurations
     *
     * @param view
     */
    public void send(View view) {
        //Get phone number entered
        String phoneNumberString = getPhoneNumberString();
        //Get message entered
        String textMessageString = getTextMessageString();
        //Get interval entered
        EditText interval = (EditText) findViewById(R.id.intervalText);
       // int intervalNumber = Integer.parseInt(interval.getText().toString());
        //Get number to send entered
        EditText numberToSend = (EditText) findViewById(R.id.quantityText);
        int numberToSendNumber = Integer.parseInt(numberToSend.getText().toString());

        //Error handling
        if (phoneNumberString == null) {
            Log.d("ERROR ", " NO PHONE NUMBER ENTERED");
            return;
        }
        //Error handling for message
        Log.d("textMessageString"," : " + textMessageString);
        Log.d("phoneNumberString"," : " + phoneNumberString);
        if (phoneNumberString == null || phoneNumberString.equals("")) {
            return;
        }
        //Error handling
        if (numberToSendNumber <= 0 || numberToSend == null) {
            numberToSendNumber = 1;
        }

        //Send SMS to another device
        for (int i = 0; i < numberToSendNumber; i++) {
            sendSMS(phoneNumberString, textMessageString);
        }
    }

    String getPhoneNumberString() {
        EditText phoneNumber = (EditText) findViewById(R.id.phoneNumberText);
        return phoneNumber.getText().toString();
    }

    String getTextMessageString() {
        EditText textMessage = (EditText) findViewById(R.id.messageBoxText);
        return textMessage.getText().toString();
    }

    //---sends an SMS message to another device---
    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
