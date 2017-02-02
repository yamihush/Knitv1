package com.example.logeshwa.knitv1.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logeshwa.knitv1.R;
import com.example.logeshwa.knitv1.fragment.addCompanyFragment;
import com.example.logeshwa.knitv1.fragment.companyfragment;

import java.util.ArrayList;

public class Save_Input extends AppCompatActivity {


    Button btnAddNewRecord;
    TextView frNoRecordsFound;
    SQLiteHelper sQLiteHelper;
    LinearLayout parentLayout;
    LinearLayout layoutDisplayPeople;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = new companyfragment();
        //Toast.makeText(this,"came inside  save input 1",Toast.LENGTH_SHORT).show();

        transaction.replace(R.id.fragmenthome,fragment);
        transaction.commit();
        //setContentView(R.layout.company_fragment);
       getAllWidgets();
        sQLiteHelper = new SQLiteHelper(Save_Input.this);
        //bindWidgetsWithEvent();

        onAddRecord();
        displayAllRecords();

    }

    private void getAllWidgets() {
      // btnAddNewRecord = (Button) findViewById(R.id.btnSaveCompanydetails);
        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        layoutDisplayPeople = (LinearLayout) findViewById(R.id.layoutDisplayPeople);
        frNoRecordsFound = (TextView) findViewById(R.id.frNoRecordsFound);
    }

    private void bindWidgetsWithEvent() {
           btnAddNewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddRecord();
            }
        });
    }


    private void onAddRecord() {
        Intent intent = new Intent(Save_Input.this, TableManipulationActivity.class);
        intent.putExtra(Constants.DML_TYPE, Constants.INSERT);
        startActivityForResult(intent, Constants.ADD_RECORD);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String firstname = data.getStringExtra(Constants.FIRST_NAME);
            String lastname = data.getStringExtra(Constants.LAST_NAME);
            String emailadd = data.getStringExtra(Constants.EMAIL_ADD);
            ContactModel contact = new ContactModel();
            contact.setFirstName(firstname);
            contact.setLastName(lastname);
            contact.setEmailAdd(emailadd);
            if (requestCode == Constants.ADD_RECORD) {
                sQLiteHelper.insertRecord(contact);
            }

            displayAllRecords();
        }

    }

    private void displayAllRecords() {
        LinearLayout inflateParentView;
        parentLayout.removeAllViews();
        ArrayList<ContactModel> contacts = sQLiteHelper.getAllRecords();
        if (contacts.size() > 0) {
            frNoRecordsFound.setVisibility(View.GONE);
            ContactModel contactModel;
            for (int i = 0; i < contacts.size(); i++) {
                contactModel = contacts.get(i);
                final Holder holder = new Holder();

                final View view = LayoutInflater.from(this).inflate(R.layout.inflate_record, null);
                inflateParentView = (LinearLayout) view.findViewById(R.id.inflateParentView);

                holder.tvName = (TextView) view.findViewById(R.id.tvFullName);
                view.setTag(contactModel.getID());
                holder.firstname = contactModel.getFirstName();
                holder.lastname = contactModel.getLastName();
                holder.emailadd = contactModel.getEmailAdd();
                String personName = holder.firstname + " " + holder.lastname + " " + holder.emailadd;
                holder.tvName.setText(personName);

                //final CharSequence[] items = {Constants.UPDATE, Constants.DELETE};
                parentLayout.addView(view);
            }
        } else {
            frNoRecordsFound.setVisibility(View.VISIBLE);
        }
    }

    private class Holder {

        TextView tvName;
        String firstname;
        String lastname;
        String emailadd;
    }
}
