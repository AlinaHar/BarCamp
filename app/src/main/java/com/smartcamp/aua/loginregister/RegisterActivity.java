package com.smartcamp.aua.loginregister;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

/*import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;*/
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etName;
    private EditText etUsername;
    private EditText etPassword;
    private Button bRegister;
    private ImageView ivBack;
    private FirebaseAuth firebaseAuth;
    String name, username, password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etName = (EditText) findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        firebaseAuth = FirebaseAuth.getInstance();

       ivBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
           }
       });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = etName.getText().toString();
                final String password = etPassword.getText().toString();
                final String email = etEmail.getText().toString();

                //function to check if user has given all the details
                if (validate()){
                    //upload data to db
                    String user_email = etEmail.getText().toString().trim();//trim is for skipping whitespaces
                    String user_password = etPassword.getText().toString().trim();
                    String user_name = etName.getText().toString().trim();
                    //doPrint(user_name);

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                sendEmailVerification();
                                sendUserData();
                                firebaseAuth.signOut();//in order to make user null so that in loginactivity it will not run the if statement
                                //if registration was successful direct to login page
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }else{
                                Toast.makeText(RegisterActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
            }
        });
    }

            private Boolean validate(){
            Boolean result = false;

            name = etName.getText().toString();
            username = etUsername.getText().toString();
            password = etPassword.getText().toString();
            email = etEmail.getText().toString();

            if(username.isEmpty() || password.isEmpty() || email.isEmpty() || name.isEmpty()){
                Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
            }
            else if(password.length()<6){
                Toast.makeText(this, "Password should be at least 6 characters", Toast.LENGTH_SHORT).show();
            }
            else{
                result = true;
            }
            return result;
        }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                    }else{
                        Toast.makeText(RegisterActivity.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }

    //creates firebase db object and creates reference so the
    //reference will be the unique id of the user and under that we have three fields name, username, email
       private void sendUserData(){
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());//every user in Firebase auth has uniqueq user UID
            UserProfile userProfile = new UserProfile(username, name, email);
            myRef.setValue(userProfile);
        }





        /*private void doPrint(final String s){
            PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
            String jobName = this.getString(R.string.app_name) + " Document";
            PrintDocumentAdapter pda = new PrintDocumentAdapter(){
                @Override
                public void onWrite(PageRange[] pages, ParcelFileDescriptor destination, CancellationSignal cancellationSignal, WriteResultCallback callback){
                    InputStream input = null;
                    OutputStream output = null;

                    try {

                        input = new FileInputStream(s);
                        output = new FileOutputStream(destination.getFileDescriptor());

                        byte[] buf = new byte[1024];
                        int bytesRead;

                        while ((bytesRead = input.read(buf)) > 0) {
                            output.write(buf, 0, bytesRead);
                        }

                        callback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});

                    } catch (FileNotFoundException ee){
                        //Catch exception
                    } catch (Exception e) {
                        //Catch exception
                    } finally {
                        try {
                            input.close();
                            output.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal, LayoutResultCallback callback, Bundle extras){

                    if (cancellationSignal.isCanceled()) {
                        callback.onLayoutCancelled();
                        return;
                    }


                    PrintDocumentInfo pdi = new PrintDocumentInfo.Builder("test").setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT).build();

                    callback.onLayoutFinished(pdi, true);
                }
            };
            printManager.print(jobName, pda, null);

        }*/

        }







