package com.example.logeshwa.knitv1.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logeshwa.knitv1.R;
import com.example.logeshwa.knitv1.database.Constants;
import com.example.logeshwa.knitv1.database.ContactModel;
import com.example.logeshwa.knitv1.database.SQLiteHelper;
import com.example.logeshwa.knitv1.database.Save_Input;
import com.example.logeshwa.knitv1.database.TableManipulationActivity;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by logeshwa on 11/22/2016.
 */



public class companyfragment extends Fragment {


    Button btnAddNewRecord;
    TextView frNoRecordsFound;
    SQLiteHelper sQLiteHelper;
    LinearLayout parentLayout;
    LinearLayout layoutDisplayPeople;



    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.company_fragment, container, false);


        return view;


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Context context0 = getActivity().getApplicationContext();
        getAllWidgets(view);
        sQLiteHelper = new SQLiteHelper(context0);
        bindWidgetsWithEvent(view,context0);
        displayAllRecords();

    }



    private void getAllWidgets(View view) {

        parentLayout = (LinearLayout) view.findViewById(R.id.parentLayout);
        layoutDisplayPeople = (LinearLayout) view.findViewById(R.id.layoutDisplayPeople);
        frNoRecordsFound = (TextView) view.findViewById(R.id.frNoRecordsFound);
    }
    private void bindWidgetsWithEvent(View view, final Context context) {
        FloatingActionButton btn =  (FloatingActionButton) view.findViewById(R.id.AddcompanyfloatingActionButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();

                onAddRecord(context);
            }
        });
    }



    private void onAddRecord(Context con) {


        Intent intent = new Intent(con, TableManipulationActivity.class);
        intent.putExtra(Constants.DML_TYPE, Constants.INSERT);
        startActivityForResult(intent, Constants.ADD_RECORD);
        Toast.makeText(con, "on add record", Toast.LENGTH_SHORT).show();
        displayAllRecords();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String firstname = data.getStringExtra(Constants.FIRST_NAME);
            String lastname = data.getStringExtra(Constants.LAST_NAME);
            String emailadd = data.getStringExtra(Constants.EMAIL_ADD);
            String companyname = data.getStringExtra(Constants.COMPANY_NAME);
            String companytype = data.getStringExtra(Constants.COMPANY_TYPE);
            String phoneno = data.getStringExtra(Constants.PHONE_NO);
            ContactModel contact = new ContactModel();
            contact.setFirstName(firstname);
            contact.setLastName(lastname);
            contact.setEmailAdd(emailadd);
            contact.setCompanyName(companyname);
            contact.setCompanyType(companytype);
            contact.setPhoneNo(phoneno);


            if (requestCode == Constants.ADD_RECORD) {
                sQLiteHelper.insertRecord(contact);
            }
            displayAllRecords();
        }

    }

    private void displayAllRecords() {


        LinearLayout inflateParentView;
        parentLayout.removeAllViews();

        final Context context1 = getActivity().getApplicationContext();
        ArrayList<ContactModel> contacts = sQLiteHelper.getAllRecords();
        if (contacts.size() > 0) {
            frNoRecordsFound.setVisibility(View.GONE);
            ContactModel contactModel;
            for (int i = 0; i < contacts.size(); i++) {
                contactModel = contacts.get(i);
                final companyfragment.Holder holder = new companyfragment.Holder();

                final View view = LayoutInflater.from(context1).inflate(R.layout.inflate_record, null);
                inflateParentView = (LinearLayout) view.findViewById(R.id.inflateParentView);

                holder.tvName = (TextView) view.findViewById(R.id.tvFullName);
                view.setTag(contactModel.getID());
                holder.firstname = contactModel.getFirstName();
                holder.lastname = contactModel.getLastName();
                holder.emailadd = contactModel.getEmailAdd();
                holder.companyname = contactModel.getCompanyName();
                holder.companytype = contactModel.getCompanyType();
                holder.phone = contactModel.getPhoneNo();
                String personName = "Compnay Name: " + holder.companyname + "\nCompany Type: " + holder.companytype + "\nPerson Name:"   + holder.firstname + "." + holder.lastname + " \nMobile No: " + holder.phone + " \nEmail ID: " + holder.emailadd;
                holder.tvName.setText(personName);

                //final CharSequence[] items = {Constants.UPDATE, Constants.DELETE};
                parentLayout.addView(view);
            }
        } else {
            frNoRecordsFound.setVisibility(View.VISIBLE);
        }


        Toast.makeText(context1, "Display all records", Toast.LENGTH_SHORT).show();


    }

    private class Holder {

        TextView tvName;
        String firstname;
        String lastname;
        String emailadd;

        String companyname;
        String companytype;
        String phone;
    }

}
