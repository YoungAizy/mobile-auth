package com.softwares.techvibez.phoneauth2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.twilio.verification.TwilioVerification;
import com.twilio.verification.external.VerificationStatus;

public class MyVerificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        VerificationStatus verificationStatus = TwilioVerification.getVerificationStatus(intent);


    }
}
