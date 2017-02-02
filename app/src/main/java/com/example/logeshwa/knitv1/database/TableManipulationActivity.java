package com.example.logeshwa.knitv1.database;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.logeshwa.knitv1.R;
import com.example.logeshwa.knitv1.fragment.addCompanyFragment;

import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

/**
 * Created by logeshwa on 10/16/2016.
 */
public class TableManipulationActivity extends Activity {

    EditText etFirstname;
    EditText etLastname;
    EditText etEmail;
    EditText etPhone;
    EditText etCompanyName;
    Spinner etCompanytype;

    Button btnDML;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcompanyfragmant);
        getAllWidgets();
        bindWidgetsWithEvent();
        checkForRequest();
    }

    private void getAllWidgets() {
        etFirstname = (EditText) findViewById(R.id.etFirstName);
        etLastname = (EditText) findViewById(R.id.etLastname);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etCompanyName = (EditText) findViewById(R.id.etCompanyName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etCompanytype = (Spinner) findViewById(R.id.etCompanytype);


        btnDML = (Button) findViewById(R.id.btnSaveCompanydetails);
    }

    private void bindWidgetsWithEvent() {
        btnDML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick();
            }
        });
    }
    private boolean isValidEmail(CharSequence email) {
        if (!TextUtils.isEmpty(email)) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }

    private boolean isValidMobile(String phone2)
    {

        CharSequence cs = phone2;
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+",cs))
         {
            if(phone2.length() < 6 || phone2.length() > 13)
            {
                check = false;
                etPhone.setError("Not Valid Number");
            }
            else
            {
                check = true;
            }
         }
        else
        {
            check=false;
        }
        return check;
    }


    private void onButtonClick() {
        String STphone = etPhone.getText().toString();
        CharSequence csemail = etEmail.getText().toString();

        if(!isValidMobile(STphone)) {
            //Toast.makeText(getApplicationContext(), "Enter Vaild Phone number", Toast.LENGTH_LONG).show();
           // etPhone.selectAll();
        }else if(!isValidEmail(csemail)){

            //Toast.makeText(getApplicationContext(), "Enter Vaild EMail ID", Toast.LENGTH_LONG).show();

            etEmail.setError("Not a Valid Email");
        }else if (etFirstname.getText().toString().equals("") || etLastname.getText().toString().equals("") || etEmail.getText().toString().equals("") || etCompanyName.getText().toString().equals("") || etPhone.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Add All Fields", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra(Constants.FIRST_NAME, etFirstname.getText().toString());
            intent.putExtra(Constants.LAST_NAME, etLastname.getText().toString());
            intent.putExtra(Constants.EMAIL_ADD, etEmail.getText().toString());
            intent.putExtra(Constants.COMPANY_NAME, etCompanyName.getText().toString());
            intent.putExtra(Constants.COMPANY_TYPE, etCompanytype.getSelectedItem().toString());
            intent.putExtra(Constants.PHONE_NO, etPhone.getText().toString());
            //Log.d(TAG, "onButtonClick: "+ etCompanyName.getText().toString()+ etCompanytype.getSelectedItem().toString()+etPhone.getText().toString());
            Toast.makeText(getApplicationContext(), "Company Added!", Toast.LENGTH_LONG).show();
            setResult(RESULT_OK, intent);
            finish();
        }
    }


    private void checkForRequest() {
        String request = getIntent().getExtras().get(Constants.DML_TYPE).toString();
        if (request.equals(Constants.UPDATE)) {
            btnDML.setText(Constants.UPDATE);
            etFirstname.setText(getIntent().getExtras().get(Constants.FIRST_NAME).toString());
            etLastname.setText(getIntent().getExtras().get(Constants.LAST_NAME).toString());
            etEmail.setText(getIntent().getExtras().get(Constants.EMAIL_ADD).toString());
            etCompanyName.setText(getIntent().getExtras().get(Constants.COMPANY_NAME).toString());
            etPhone.setText(getIntent().getExtras().get(Constants.PHONE_NO).toString());


        } else {
            btnDML.setText(Constants.INSERT);
        }
    }

}
