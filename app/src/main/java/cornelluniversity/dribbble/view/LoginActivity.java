package cornelluniversity.dribbble.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import cornelluniversity.dribbble.R;
import cornelluniversity.dribbble.dribbble.Dribbble;
import cornelluniversity.dribbble.dribbble.auth.Auth;
import cornelluniversity.dribbble.dribbble.auth.AuthActivity;

/**
 * Created by Ruihao on 8/16/17.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.activity_login_btn)
    TextView loginBtn;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Auth.REQ_CODE && resultCode == Activity.RESULT_OK) {
            final String authCode = data.getStringExtra(AuthActivity.KEY_CODE);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String token = Auth.fetchAccessToken(authCode);
                        Dribbble.login(LoginActivity.this, token);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (IOException | JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        cornelluniversity.dribbble.dribbble.Dribbble.init(LoginActivity.this);

        if(!cornelluniversity.dribbble.dribbble.Dribbble.isLoggedIn()) {
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Auth.openAuthActivity(LoginActivity.this);
                }
            });
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}

