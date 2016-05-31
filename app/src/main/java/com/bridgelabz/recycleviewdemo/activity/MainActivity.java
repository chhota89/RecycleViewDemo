package com.bridgelabz.recycleviewdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bridgelabz.recycleviewdemo.R;
import com.bridgelabz.recycleviewdemo.adapter.RecycleViewAdapter;
import com.bridgelabz.recycleviewdemo.model.User;
import com.bridgelabz.recycleviewdemo.utility.GooglePlayServiceChecker;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<User> list;
    RecyclerView recyclerView;
    EditText text;
    Button add;
    RecycleViewAdapter recycleviewAdapter;
    public final String TAG="MainActivity";
    int number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=new ArrayList<>();
        for(int i=0;i<100;i++){
            list.add(new User("Name",i));
        }
        hideSoftKeyboard();
        //check for google play service is avilable or not
        boolean googlePlayService=GooglePlayServiceChecker.isGooglePlayServicesAvailable(MainActivity.this);
        if(!googlePlayService)
            Toast.makeText(MainActivity.this,"Play service is not avilable.",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this,"Play service Present.",Toast.LENGTH_LONG).show();
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        DefaultItemAnimator defaultItemAnimator= new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        defaultItemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(defaultItemAnimator);
        recycleviewAdapter=new RecycleViewAdapter(list);
        recyclerView.setAdapter(recycleviewAdapter);
        text=(EditText)findViewById(R.id.value);
        add=(Button)findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    number=Integer.parseInt(text.getText().toString());
                    hideSoftKeyboard();
                    list.add(number,new User("New Add",number));
                    recycleviewAdapter.notifyItemInserted(number);
                }
                catch (NumberFormatException exception){
                    Toast.makeText(MainActivity.this,"Enter Number only.",Toast.LENGTH_LONG).show();
                }
                catch (Exception exception){
                    Log.e(TAG, "onClick: "+exception.getMessage() );
                }
            }

            public void hideSoftKeyboard() {
                if(getCurrentFocus()!=null) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            }
        });

    }

    public void activityTransition(View view){
        startActivity(new Intent(MainActivity.this, ActivityTransition1.class));
    }
    public void activityFirebase(View view){
        startActivity(new Intent(MainActivity.this, FirebaseMenu.class));
    }

    public void subcribeToken(View view){
        Log.d(TAG, "InstanceID token: " + FirebaseInstanceId.getInstance().getToken());
    }


    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
