package com.shrewd.develop.updates2k19.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;
import com.shrewd.develop.updates2k19.Model.Department;
import com.shrewd.develop.updates2k19.Model.User;
import com.shrewd.develop.updates2k19.R;
import com.shrewd.develop.updates2k19.Utilities.CS;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private Context mContext;
    private EditText etFirstName,etLastName,etCollege,etEnrolment,etMobileNo;
    private SearchableSpinner spnDepartment,spnYear;
    private RadioGroup rdoGender,rdoYear;
    private Button btnSubmit;
    private RadioButton rbtnMale,rbtnFemale,rbtn1,rbtn2,rbtn3,rbtn4;
    private FirebaseFunctions mFunctions;
    private ArrayList<String> alDepartment = new ArrayList<>();
    private ArrayList<String> alEvent = new ArrayList<>();
    private final String TAG = "RegistrationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initUI();
    }

    private void initUI() {
        mContext = RegistrationActivity.this;
        etFirstName = (EditText)findViewById(R.id.etFirstName);
        etLastName = (EditText)findViewById(R.id.etLastName);
        etCollege = (EditText)findViewById(R.id.etCollege);
        etEnrolment = (EditText)findViewById(R.id.etEnrolment);
        etMobileNo = (EditText)findViewById(R.id.etMobileNo);
        spnDepartment = (SearchableSpinner)findViewById(R.id.spnDepartment);
        rdoGender = (RadioGroup)findViewById(R.id.rdoGender);
        rdoYear = (RadioGroup)findViewById(R.id.rdoYear);
        rbtn1 = (RadioButton)findViewById(R.id.rbtn1);
        rbtn2 = (RadioButton)findViewById(R.id.rbtn2);
        rbtn3 = (RadioButton)findViewById(R.id.rbtn3);
        rbtn4 = (RadioButton)findViewById(R.id.rbtn4);
        rbtnMale = (RadioButton)findViewById(R.id.rbtnMale);
        rbtnFemale = (RadioButton)findViewById(R.id.rbtnFemale);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);

        mFunctions = FirebaseFunctions.getInstance();

        //ArrayListDepartment


        clickListener();
    }

    private void clickListener() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser firebaseUseruser = FirebaseAuth.getInstance().getCurrentUser();
                int year=1;
                switch (rdoYear.getCheckedRadioButtonId()) {
                    case R.id.rbtn1:
                        year=1;
                        break;
                    case R.id.rbtn2:
                        year=2;
                        break;
                    case R.id.rbtn3:
                        year=3;
                        break;
                    case R.id.rbtn4:
                        year=4;
                        break;
                }
                User user = new User(etCollege.getText().toString(),spnDepartment.getSelectedItem().toString(),firebaseUseruser.getEmail(),etEnrolment.getText().toString(),etFirstName.getText().toString(),etLastName.getText().toString(),(rdoGender.getCheckedRadioButtonId() == rbtnMale.getId()) ? 1 : 0,etMobileNo.getText().toString(),0,year);

                Map<String,Object> data = user.getHashmap();

                Task<String> task = mFunctions
                        .getHttpsCallable(CS.registerUser)
                        .call(data)
                        .continueWith(new Continuation<HttpsCallableResult, String>() {
                            @Override
                            public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                                // This continuation runs on either success or failure, but if the task
                                // has failed then getResult() will throw an Exception which will be
                                // propagated down.
                                String result = (String) task.getResult().getData();
                                Log.e(TAG, "then: "+result );
                                return result;
                            }
                        }).addOnSuccessListener(new OnSuccessListener<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Log.e(TAG, "onSuccess: " );
                            }
                        })
                        .addOnCompleteListener(new OnCompleteListener<String>() {
                            @Override
                            public void onComplete(@NonNull Task<String> task) {
                                if (task.isSuccessful()) {
                                    Log.e(TAG, "onComplete: "+task.getResult() );
                                    startActivity(new Intent(mContext,MainActivity.class));
                                    finish();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(TAG, "onFailure: "+e.getMessage() );
                            }
                        });
            }
        });
    }

    private void clearText() {
        etFirstName.setText("");
        etLastName.setText("");
        etCollege.setText("");
        etEnrolment.setText("");
        etMobileNo.setText("");
    }

    private Task<String> addUser(String text) {
        // Create the arguments to the callable function.
        Map<String, Object> data = new HashMap<>();
        data.put(CS.college, text);
        data.put("push", true);

        return mFunctions
                .getHttpsCallable("registerUser")
                .call(data)
                .continueWith(new Continuation<HttpsCallableResult, String>() {
                    @Override
                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        // This continuation runs on either success or failure, but if the task
                        // has failed then getResult() will throw an Exception which will be
                        // propagated down.
                        String result = (String) task.getResult().getData();
                        Log.e(TAG, "then: "+result );
                        return result;
                    }
                });
    }
}
