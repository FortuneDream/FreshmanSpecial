package com.excitingboat.freshmanspecial.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.config.Config;
import com.excitingboat.freshmanspecial.model.bean.User;
import com.excitingboat.freshmanspecial.presenter.LoginPresenter;
import com.excitingboat.freshmanspecial.view.iview.ILoginView;

public class FreshmanLoginActivity extends AppCompatActivity implements ILoginView {

    private AutoCompleteTextView usernameView;
    private EditText passwordView;
    private View mProgressView;
    private View mLoginFormView;
    private LoginPresenter loginPresenter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freshman_login);
        init();
    }

    private void init() {
        findViewById(R.id.bt_toolbar_back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FreshmanLoginActivity.this.finish();
            }
        });
        ((TextView)findViewById(R.id.tv_toolbar_title)).setText(R.string.freshman_login);
        usernameView = (AutoCompleteTextView) findViewById(R.id.email);
        passwordView = (EditText) findViewById(R.id.password);
        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button login = (Button) findViewById(R.id.login_button);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        loginPresenter = new LoginPresenter(this);
        user = new User();
    }


    private void attemptLogin() {
        usernameView.setError(null);
        passwordView.setError(null);

        String username = usernameView.getText().toString();
        String password = passwordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password)) {
            passwordView.setError(getString(R.string.field_required));
            focusView = passwordView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }


        if (TextUtils.isEmpty(username)) {
            usernameView.setError(getString(R.string.field_required));
            focusView = usernameView;
            cancel = true;
        } else if (!isUsernameValid(username)) {
            usernameView.setError(getString(R.string.error_invalid_username));
            focusView = usernameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            user.setUsername(username);
            user.setPassword(password);
            loginPresenter.Login(user);
        }
    }

    private boolean isUsernameValid(String username) {
        //TODO 条件待修改
        return username.length() > 5;
    }

    private boolean isPasswordValid(String password) {
        //TODO 条件待修改
        return password.length() > 5;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void LoginSuccess() {
        showProgress(false);
        Toast.makeText(FreshmanLoginActivity.this, R.string.login_success, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void LoginFail(int errorType) {
        showProgress(false);
        switch (errorType) {
            case Config.ERROR_INCORRECT:
                Toast.makeText(FreshmanLoginActivity.this, R.string.error_incorrect, Toast.LENGTH_SHORT).show();
                break;
            case Config.ERROR_NETWORK:
                Toast.makeText(FreshmanLoginActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

