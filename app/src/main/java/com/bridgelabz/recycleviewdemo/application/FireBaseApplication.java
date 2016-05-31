package com.bridgelabz.recycleviewdemo.application;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/**
 * Created by bridgelabz5 on 24/5/16.
 */
public class FireBaseApplication extends Application{

    static DatabaseReference mDatabase;

    synchronized public DatabaseReference getInstance(){
        if(mDatabase==null){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.keepSynced(true);
        }
        return mDatabase;
    }
}
