package com.bridgelabz.recycleviewdemo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bridgelabz.recycleviewdemo.R;
import com.bridgelabz.recycleviewdemo.application.FireBaseApplication;
import com.bridgelabz.recycleviewdemo.model.UserModel;
import com.bridgelabz.recycleviewdemo.utility.ImageUtility;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirebaseMenu extends AppCompatActivity {

    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private String TAG="FirebaseMenu";
    private DatabaseReference mDatabase;
    FirebaseRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_menu);
        FireBaseApplication application=(FireBaseApplication)getApplication();
        mDatabase = application.getInstance();
        //. ref=mDatabase.child("awosome");
        Firebase.setAndroidContext(this);
        Firebase ref=new Firebase("https://recycleview-demo.firebaseio.com/awosome");
        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler_view);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter=new FirebaseRecyclerAdapter<UserModel, UserViewHolder>(UserModel.class, R.layout.adapter_firebase, UserViewHolder.class, ref) {
            @Override
            public void populateViewHolder(UserViewHolder chatMessageViewHolder, UserModel chatMessage, int position) {
                chatMessageViewHolder.name.setText(chatMessage.getFullName());
                chatMessageViewHolder.birthday.setText(chatMessage.getBirthYear());
                chatMessageViewHolder.imageView.setImageBitmap(ImageUtility.stringToBitmap(chatMessage.getImage()));
            }
        };
        recycler.setAdapter(adapter);
        /*mDatabase.child("awosome").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<Map<String,UserModel>> t=new GenericTypeIndicator<Map<String,UserModel>>(){};
                Map<String,UserModel> userList=dataSnapshot.getValue(t);
                ArrayList<UserModel> list=new ArrayList<UserModel>(userList.values());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        // Define Firebase Remote Config Settings.
        FirebaseRemoteConfigSettings firebaseRemoteConfigSettings =
                new FirebaseRemoteConfigSettings.Builder()
                        .setDeveloperModeEnabled(true)
                        .build();

        // Define default config values. Defaults are used when fetched config values are not
        // available. Eg: if an error occurred fetching values from the server.
        /*Map<String, Object> defaultConfigMap = new HashMap<>();
        defaultConfigMap.put("my_country", "Pakistan");*/

        // Apply config settings and default values.
        mFirebaseRemoteConfig.setConfigSettings(firebaseRemoteConfigSettings);
        mFirebaseRemoteConfig.setDefaults(R.xml.defaults_config);

        // Fetch remote config.
        fetchConfig();

    }

    // Fetch the config to determine the allowed length of messages.
    public void fetchConfig() {
        long cacheExpiration = 0;

        /*mFirebaseRemoteConfig.fetch(cacheExpiration).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mFirebaseRemoteConfig.activateFetched();
                TextView textView=(TextView)findViewById(R.id.textView);
                textView.setText(""+mFirebaseRemoteConfig.getString("my_country"));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(FirebaseMenu.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });*/

        /*TextView textView=(TextView)findViewById(R.id.textView);
        textView.setText(""+mFirebaseRemoteConfig.getString("my_country"));*/

        mFirebaseRemoteConfig.fetch().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Fetch Succeeded");
                    // Once the config is successfully fetched it must be activated before newly fetched
                    // values are returned.
                    mFirebaseRemoteConfig.activateFetched();
                } else {
                    Log.d(TAG, "Fetch failed");
                }
                TextView textView=(TextView)findViewById(R.id.textView);
                textView.setText(""+mFirebaseRemoteConfig.getString("my_country"));
            }
        });


    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView name,birthday;
        ImageView imageView;
        public UserViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.fullName);
            birthday=(TextView)itemView.findViewById(R.id.birthday);
            imageView=(ImageView)itemView.findViewById(R.id.image);
        }
    }
}
