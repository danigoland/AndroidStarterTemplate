package com.undot.androidtemplate.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.CreditCard;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.undot.androidtemplate.R;
import com.undot.androidtemplate.utils.GeneralUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SaripaarActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    @Email
    @Bind(R.id.saripaar_email)
    public EditText email;

    @NotEmpty
    @CreditCard
    @Bind(R.id.saripaar_creditcard)
    public EditText creditCard;

    private Validator validator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saripaar);
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }


    @OnClick(R.id.saripaar_validate)
        void validate(){
        validator.validate();
    }



    @Override
    public void onValidationSucceeded() {
        GeneralUtils.showToastShort(this,"Yay! we got it right!");
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                GeneralUtils.showToastLong(this,message);
            }
        }
    }
}
