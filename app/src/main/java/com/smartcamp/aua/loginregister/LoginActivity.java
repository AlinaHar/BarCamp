package com.smartcamp.aua.loginregister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.print.PrintManager;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;*/

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button bLogin;
    private TextView registerLink;
    private ImageView ivLogo;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView tvForgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //FirebaseUser user = firebaseAuth.getCurrentUser();
        tvForgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        ivLogo = (ImageView) findViewById(R.id.ivLogo);
        etEmail = (EditText) findViewById(R.id.etEmail1);
        etPassword = (EditText) findViewById(R.id.etPassword1);
        bLogin = (Button) findViewById(R.id.bLogin);
        registerLink = (TextView) findViewById(R.id.tvRegisterHere);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);



        //if already logged in, direct him to new page and not to registration
        /*if(user != null){
            finish();
            startActivity(new Intent(LoginActivity.this, RetreiveWorkshopList.class));
        }*/

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, PasswordActivity.class));
            }
        });
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(etEmail.getText().toString(), etPassword.getText().toString());
            }


        });
    }
        private void validate (String userEmail, String userPassword) {
            progressDialog.setMessage("Verifying");
            progressDialog.show();


                firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
                            if (user.isEmailVerified()) {
                                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(LoginActivity.this, WelcomePageActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Login failed PLease verify your email", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, "Login failed. Please verify your e-mail", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                    }
                });
            }
        }




