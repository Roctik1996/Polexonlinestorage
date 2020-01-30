package com.polexexpress.polexonlinestorage.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.textfield.TextInputEditText;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.polexexpress.polexonlinestorage.R;
import com.polexexpress.polexonlinestorage.model.Login;
import com.polexexpress.polexonlinestorage.other.Const;
import com.polexexpress.polexonlinestorage.presenter.LoginPresenter;
import com.polexexpress.polexonlinestorage.view.LoginView;

import java.util.List;
import java.util.Objects;

import static android.text.TextUtils.isEmpty;

public class LoginActivity extends MvpAppCompatActivity implements LoginView {

    private static final String APP_PREFERENCES = "setting";
    private static final String APP_PREFERENCES_USERNAME = "username";
    private static final String APP_PREFERENCES_PASS = "pass";
    private SharedPreferences mSettings;
    private EditText edtLogin;
    private TextInputEditText edtPass;

    @InjectPresenter
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtLogin = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);
        Button signInBtn = findViewById(R.id.signIn);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        requestPermission();

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings.contains(APP_PREFERENCES_USERNAME) && mSettings.contains(APP_PREFERENCES_PASS)) {
            if (!mSettings.getString(APP_PREFERENCES_USERNAME, "").equals("") && !mSettings.getString(APP_PREFERENCES_PASS, "").equals("")) {
                String username = mSettings.getString(APP_PREFERENCES_USERNAME, "");
                String password = mSettings.getString(APP_PREFERENCES_PASS, "");
                loginPresenter.signIn(Const.grant_type, username, password);
            }
        }
        signInBtn.setOnClickListener(v -> {
            if (validate(edtLogin.getText().toString(), Objects.requireNonNull(edtPass.getText()).toString())) {
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putString(APP_PREFERENCES_USERNAME, edtLogin.getText().toString());
                editor.putString(APP_PREFERENCES_PASS, edtPass.getText().toString());
                editor.apply();
                loginPresenter.signIn(Const.grant_type, edtLogin.getText().toString(), edtPass.getText().toString());
            }

        });
    }

    private void requestPermission() {
        Dexter.withActivity(this).withPermissions(Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            System.out.println("ok");
                        }
                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).withErrorListener(error -> Toast.makeText(this, "Error occurred! ", Toast.LENGTH_SHORT).show())
                .onSameThread()
                .check();
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Нужн разрешения");
        builder.setMessage("Это приложение требует разрешение для корректной работы");
        builder.setPositiveButton("Перейти к настройкам", (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton("Отмена", (dialog, which) -> dialog.cancel());
        builder.show();

    }

    private void openSettings() {
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", this.getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    @Override
    public void showProgress(boolean isLoading) {
        FrameLayout frameLayout = findViewById(R.id.frame);
        ProgressBar progressBar = findViewById(R.id.loader);
        if (isLoading) {
            frameLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            frameLayout.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void signIn(Login login) {
        if (login != null) {
            Intent main = new Intent(this, MainActivity.class);
            Const.token = login.getAccessToken();
            Const.token_type = login.getTokenType();
            Const.refresh_token = login.getRefreshToken();
            startActivity(main);
            finish();
        }
    }

    private boolean validate(String username, String password) {
        if (isEmpty(username)) {
            Toast.makeText(this, "Введите имя пользователя", Toast.LENGTH_LONG).show();
            return false;
        }
        if (isEmpty(password)) {
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
