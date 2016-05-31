package com.bridgelabz.recycleviewdemo.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bridgelabz.recycleviewdemo.R;
import com.bridgelabz.recycleviewdemo.fragment.CustomDialogFragment;

public class ActivityTransition1 extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition1);

        final ImageView image=(ImageView)findViewById(R.id.image);
        textView=(TextView)findViewById(R.id.text);
        if(image!=null)
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Intent intent = new Intent(ActivityTransition1.this, ActivityTransition2.class);
                    Pair<View, String> pair1 = Pair.create((View) image, "profile");
                    Pair<View, String> pair2 = Pair.create((View) textView, "Title");

                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(ActivityTransition1.this,
                            pair1, pair2);
                    startActivity(intent, optionsCompat.toBundle());
                }else {
                    startActivity(new Intent(ActivityTransition1.this,ActivityTransition2.class));
                }
            }
        });
    }

    public void openDialog(View view){
        new AlertDialog.Builder(ActivityTransition1.this).setTitle("Dialog is created").setMessage("This dialg is created by mohmad")
        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ActivityTransition1.this,"Ok Press",Toast.LENGTH_LONG).show();
            }
        }).show();
        /*FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
       new  CustomDialogFragment().show(fm,"Fra");*/
    }

    public void appInvite(View view){

    }

}
