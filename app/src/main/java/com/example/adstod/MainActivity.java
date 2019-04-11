package com.example.adstod;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.json.JSONArray;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

// Controller fyrir upphafssíðu
public class MainActivity extends AppCompatActivity {
    private List<Question> Questions;

    private Button mAcceptButton;
    private Button mRejectButton;
    private static final String KEY_PERMISSION = "com.example.adstod.language";

    private void acceptTerms() {
        Intent intent = new Intent(this, LanguageActivity.class);
        intent.putExtra(KEY_PERMISSION, 1);
        startActivity(intent);
    }

    private void rejectTerms() {
        Intent intent = new Intent(this, LanguageActivity.class);
        intent.putExtra(KEY_PERMISSION, 0);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://adstodbackend.herokuapp.com/")
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
                        acceptTerms();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> _, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(context, "Tenging mistókst, vinsamlegast tengdu tækið við net\nConnection Failed, please connect the device to the internet", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        mRejectButton = findViewById(R.id.reject_button);
        mRejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> call = service.hello();
                final Context context = getApplicationContext();
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> _,
                                           Response<ResponseBody> response) {
                        rejectTerms();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> _, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(context, "Tenging mistókst, vinsamlegast tengdu tækið við net\nConnection Failed, please connect the device to the internet", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
