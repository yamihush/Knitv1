package com.example.logeshwa.knitv1.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.logeshwa.knitv1.R;
import com.example.logeshwa.knitv1.database.Constants;
import com.example.logeshwa.knitv1.database.Save_Input;

import static android.app.Activity.RESULT_OK;

/**
 * Created by logeshwa on 12/8/2016.
 */

public class addCompanyFragment extends Fragment {

    EditText etFirstname;
    EditText etLastname;
    EditText etEmail;

    Button btnDML;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addcompanyfragmant,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      /*  Button btn = (Button) view.findViewById(R.id.btnSaveCompanydetails);

        final Context context = getActivity().getApplicationContext();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"test",Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(getActivity(), Save_Input.class);
                startActivity(intent1);

            }
        });*/


       /* Button addbutton = (Button) view.findViewById(R.id.btnSaveCompanydetails);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), Save_Input.class);
                startActivity(intent1);

            }
        });


    }

}*/


        final Context con = getActivity().getApplicationContext();

            getAllWidgets(view);
            bindWidgetsWithEvent(view,con);
            checkForRequest();
        }

    private void getAllWidgets(View view) {
        etFirstname = (EditText) view.findViewById(R.id.etFirstName);
        etLastname = (EditText) view.findViewById(R.id.etLastname);
        etEmail = (EditText) view.findViewById(R.id.etEmail);

        btnDML = (Button) view.findViewById(R.id.btnSaveCompanydetails);
    }

    private void bindWidgetsWithEvent(View view, final Context con) {


        btnDML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(con);
            }
        });
    }



    private void onButtonClick(Context con) {


        if (etFirstname.getText().toString().equals("") || etLastname.getText().toString().equals("") || etEmail.getText().toString().equals("") ) {
            Toast.makeText(con, "Add All Fields", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra(Constants.FIRST_NAME, etFirstname.getText().toString());
            intent.putExtra(Constants.LAST_NAME, etLastname.getText().toString());
            intent.putExtra(Constants.EMAIL_ADD, etEmail.getText().toString());


            getActivity().setResult(Activity.RESULT_OK, intent);
            getActivity().finish();
        }
    }


    private void checkForRequest() {
        String request = getActivity().getIntent().getExtras().get(Constants.DML_TYPE).toString();
        if (request.equals(Constants.UPDATE)) {
            btnDML.setText(Constants.UPDATE);
            etFirstname.setText(getActivity().getIntent().getExtras().get(Constants.FIRST_NAME).toString());
            etLastname.setText(getActivity().getIntent().getExtras().get(Constants.LAST_NAME).toString());
            etEmail.setText(getActivity().getIntent().getExtras().get(Constants.EMAIL_ADD).toString());
        } else {
            btnDML.setText(Constants.INSERT);
        }
    }

}

