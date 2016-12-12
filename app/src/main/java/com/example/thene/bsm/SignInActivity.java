package com.example.thene.bsm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.codec.digest.DigestUtils;

import static com.example.thene.bsm.PassHandler.setMessage;
import static com.example.thene.bsm.PassHandler.setPassword;

public class SignInActivity extends AppCompatActivity {

    private String message;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button button = (Button) findViewById(R.id.buttonSignin);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editTextPassword);
                password = editText.getText().toString();
                setPassword(password);
                editText = (EditText) findViewById(R.id.editTextMess);
                message = editText.getText().toString();
                setMessage(message);


                if (!password.matches("") && !message.matches("")) {
                    Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), R.string.error_incorrect_data, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
