package com.shrewd.develop.updates2k19.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;
import com.shrewd.develop.updates2k19.R;
import com.shrewd.develop.updates2k19.Utilities.CS;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private Context mContext;
    private EditText etFirstName,etLastName,etCollege,etEnrolment,etMobileNo,etDepartment,etYear;
    private RadioGroup rdoGender;
    private Button btnSubmit;
    private RadioButton rbtnMale,rbtnFemale;
    private FirebaseFunctions mFunctions;
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
        etDepartment = (EditText)findViewById(R.id.etDepartment);
        etDepartment = (EditText)findViewById(R.id.etDepartment);
        etYear = (EditText)findViewById(R.id.etYear);
        rdoGender = (RadioGroup)findViewById(R.id.rdoGender);
        rbtnMale = (RadioButton)findViewById(R.id.rbtnMale);
        rbtnFemale = (RadioButton)findViewById(R.id.rbtnFemale);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);

        mFunctions = FirebaseFunctions.getInstance();
        clickListener();
    }

    private void clickListener() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> data = new HashMap<>();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null && user.getEmail() != null)
                    data.put(CS.email,user.getEmail() );
                data.put(CS.college, etCollege.getText().toString());
                data.put(CS.deptId, 7);
                data.put(CS.enrolment, etEnrolment.getText().toString());
                data.put(CS.firstName, etFirstName.getText().toString());
                data.put(CS.lastName, etLastName.getText().toString());
                data.put(CS.mobileNo, etMobileNo.getText().toString());
                data.put(CS.year, etYear.getText().toString());

                //MUST CHANGE
                data.put(CS.gender,1);

                data.put(CS.userTypeId,0);
//                data.put("push", true);

                Task<String> task = mFunctions
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
                        }).addOnCompleteListener(new OnCompleteListener<String>() {
                            @Override
                            public void onComplete(@NonNull Task<String> task) {
                                if (task.isSuccessful()) {
                                    Log.e(TAG, "onComplete: "+task.getResult() );
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
