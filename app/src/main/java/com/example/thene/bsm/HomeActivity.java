package com.example.thene.bsm;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.multidots.fingerprintauth.AuthErrorCodes;
import com.multidots.fingerprintauth.FingerPrintAuthCallback;
import com.multidots.fingerprintauth.FingerPrintAuthHelper;
import com.multidots.fingerprintauth.FingerPrintUtils;


public class HomeActivity extends AppCompatActivity implements FingerPrintAuthCallback {

    private boolean view = false;
    private String password;

    public static final String MESAGE = "wiadomosc";

    private FingerPrintAuthHelper mFingerPrintAuthHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mFingerPrintAuthHelper = FingerPrintAuthHelper.getHelper(this, this);
        mFingerPrintAuthHelper.stopAuth();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button button = (Button) findViewById(R.id.buttonChangePass);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);

                LayoutInflater inflater = HomeActivity.this.getLayoutInflater();
                alertDialog.setView(inflater.inflate(R.layout.auth_dialog, null));

                alertDialog.setNeutralButton("Use Password", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        mFingerPrintAuthHelper.stopAuth();
                        dialog.cancel();

                        AlertDialog.Builder passDialog = new  AlertDialog.Builder(HomeActivity.this);

                        LayoutInflater inflaterpass = HomeActivity.this.getLayoutInflater();
                        passDialog.setView(inflaterpass.inflate(R.layout.pass_dialog, null));

                        passDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){

                                Dialog f = (Dialog) dialog;
                                EditText editText = (EditText) f.findViewById(R.id.passwordin);
                                password = editText.getText().toString();
                                if (PassHandler.checkPassword(password)) {
                                    dialog.cancel();
                                    Intent intent = new Intent(HomeActivity.this, ChangePassActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(), R.string.error_incorrect_password, Toast.LENGTH_SHORT).show();
                                }

                                dialog.cancel();
                            }
                        });

                        passDialog.setNegativeButton("Anuluj", new DialogInterface.OnClickListener(){
                            public void  onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        passDialog.show();
                    }
                });

                alertDialog.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        mFingerPrintAuthHelper.stopAuth();
                        dialog.cancel();
                    }
                });

                alertDialog.setCancelable(false);

                alertDialog.show();

                mFingerPrintAuthHelper.startAuth();
            }
        });

        Button button2 = (Button) findViewById(R.id.buttonView);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);

                LayoutInflater inflater = HomeActivity.this.getLayoutInflater();
                alertDialog.setView(inflater.inflate(R.layout.auth_dialog, null));

                alertDialog.setNeutralButton("Use Password", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        mFingerPrintAuthHelper.stopAuth();
                        view = false;
                        dialog.cancel();

                        AlertDialog.Builder passDialog = new  AlertDialog.Builder(HomeActivity.this);

                        LayoutInflater inflaterpass = HomeActivity.this.getLayoutInflater();
                        passDialog.setView(inflaterpass.inflate(R.layout.pass_dialog, null));

                        passDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                           public void onClick(DialogInterface dialog, int which){

                               Dialog f = (Dialog) dialog;
                               EditText editText = (EditText) f.findViewById(R.id.passwordin);
                               password = editText.getText().toString();
                               if (PassHandler.checkPassword(password)) {
                                   dialog.cancel();
                                   Intent intent = new Intent(HomeActivity.this, ViewMessageActivity.class);
                                   intent.putExtra(MESAGE, PassHandler.getMessage());
                                   startActivity(intent);
                               }
                               else {
                                   dialog.cancel();
                                   Toast.makeText(getApplicationContext(), R.string.error_incorrect_password, Toast.LENGTH_SHORT).show();
                               }

                               dialog.cancel();
                           }
                        });

                        passDialog.setNegativeButton("Anuluj", new DialogInterface.OnClickListener(){
                           public void  onClick(DialogInterface dialog, int which) {
                               dialog.cancel();
                           }
                        });

                        passDialog.show();
                    }
                });

                alertDialog.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        mFingerPrintAuthHelper.stopAuth();
                        view = false;
                        dialog.cancel();
                    }
                });

                alertDialog.setCancelable(false);

                alertDialog.show();

                view = true;

                mFingerPrintAuthHelper.startAuth();


            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //start finger print authentication
       // mFingerPrintAuthHelper.startAuth();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFingerPrintAuthHelper.stopAuth();
    }

    @Override
    public void onNoFingerPrintHardwareFound() {
        //mAuthMsgTv.setText("Your device does not have finger print scanner. Please type 1234 to authenticate.");
        //mSwitcher.showNext();
    }

    @Override
    public void onNoFingerPrintRegistered() {
        //mAuthMsgTv.setText("There are no finger prints registered on this device. Please register your finger from settings.");
        //mGoToSettingsBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBelowMarshmallow() {
        //mAuthMsgTv.setText("You are running older version of android that does not support finger print authentication. Please type 1234 to authenticate.");
        //mSwitcher.showNext();
    }

    @Override
    public void onAuthSuccess(FingerprintManager.CryptoObject cryptoObject) {
        Toast.makeText(HomeActivity.this, "Authentication succeeded.", Toast.LENGTH_SHORT).show();
        if(view) {
            Intent intent = new Intent(HomeActivity.this, ViewMessageActivity.class);
            intent.putExtra(MESAGE, PassHandler.getMessage());
            startActivity(intent);
        }
        else
            startActivity(new Intent(HomeActivity.this, ChangePassActivity.class));
    }

    @Override
    public void onAuthFailed(int errorCode, String errorMessage) {
        switch (errorCode) {
            case AuthErrorCodes.CANNOT_RECOGNIZE_ERROR:
                Toast.makeText(HomeActivity.this, "Cannot recognize your finger print. Please try again.", Toast.LENGTH_SHORT).show();
                //mAuthMsgTv.setText("Cannot recognize your finger print. Please try again.");
                break;
            case AuthErrorCodes.NON_RECOVERABLE_ERROR:
                //mAuthMsgTv.setText("Cannot initialize finger print authentication. Please type 1234 to authenticate.");
                //mSwitcher.showNext();
                break;
            case AuthErrorCodes.RECOVERABLE_ERROR:
                //mAuthMsgTv.setText(errorMessage);
                break;
        }
    }
}
