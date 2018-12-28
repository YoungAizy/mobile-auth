package com.softwares.techvibez.phoneauth2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.twilio.verification.TwilioVerification;
import com.twilio.verification.external.Via;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login_Signup extends AppCompatActivity {

    private EditText mTextNewToDo;
    private Button sendNumb;
    private Button changeNumb;
    private TextView phone_number;
    private Button verify_code;
    private LinearLayout code_layout;

    private TwilioVerification twilioVerification;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_signup_page);

        twilioVerification = new TwilioVerification(this);

        initTokenSeverApi();

    }

    private TokenServerApi tokenseverapi;

    public void initTokenSeverApi(){
        String Token_Sever_Url = "https://phoneauth-test100.herokuapp.com";

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Token_Sever_Url)
                .build();


        tokenseverapi = retrofit.create(TokenServerApi.class);

    }

    public void PhoneAuth(View view){

        mTextNewToDo = findViewById(R.id.textNewToDo);
        String numberToVerify = mTextNewToDo.toString(); //Should come from user input
        phone_number = findViewById(R.id.phone_text);
        phone_number =  mTextNewToDo;


        mTextNewToDo.setVisibility(View.GONE);
        phone_number.setVisibility(View.VISIBLE);

        sendNumb = findViewById(R.id.buttonSend_numb);
        sendNumb.setVisibility(View.GONE);

        changeNumb = findViewById(R.id.change_number);
        changeNumb.setVisibility(View.VISIBLE);

        code_layout = findViewById(R.id.code_layout);
        code_layout.setVisibility(View.VISIBLE);

        tokenseverapi
                .getToken(numberToVerify)
                .enqueue(new retrofit2.Callback<TokenSeverResponse>() {

                    @Override
                    public void onResponse(Call<TokenSeverResponse> call,
                                           Response<TokenSeverResponse> response) {
                        String jwtToken = response.body().getJwtToken();
                        twilioVerification.startVerification(jwtToken, Via.SMS);
                    }

                    @Override
                    public void onFailure(Call<TokenSeverResponse> call, Throwable t) {
                        throw new RuntimeException(t); //Woops!
                    }

                });

    }
}
