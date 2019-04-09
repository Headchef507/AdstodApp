package com.example.adstod;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

// Controller fyrir upphafssíðu
public class MainActivity extends AppCompatActivity {

    private Button mAcceptButton;

    private void acceptTerms() {
        Intent intent = new Intent(this, LanguageActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://adstod.herokuapp.com/")
                .build();

        final HerokuService service = retrofit.create(HerokuService.class);
        //final HerokuService service = retrofit.create(HerokuService.class);
        // Takki til að samþykkja skilmála
        mAcceptButton = findViewById(R.id.accept_button);
        mAcceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> call = service.hello();
                final Context context = getApplicationContext();
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> _,
                                           Response<ResponseBody> response) {
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> _, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
                    }
                });
                acceptTerms();
            }
        });
    }
}
