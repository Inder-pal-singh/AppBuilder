package com.example.myapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity {
    private Button btn_click;
    private TextView textview77;
    CountDownTimer cTimer = null;
    private FirebaseAuth auth;

    public void btn(View view) {
        auth.signOut();
    }

    void startTimer() {


        cTimer = new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                FirebaseUser user = auth.getCurrentUser();


            }
        };
        cTimer.start();
    }

    public void signOutBtn(View view) {
        auth.signOut();
        setContentView(R.layout.signup);
    }
    FirebaseFirestore fireStore=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_home);
        onStart();
        final Map<String, Object> user = new HashMap<>();
        user.put("first", "Adnan");
        user.put("last", "Lovelace");
        user.put("born", 1815116);
        textview77=findViewById(R.id.textview77);
        btn_click=findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              fireStore.collection("users").document("123456").set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                  @Override
                  public void onSuccess(Void aVoid) {
                  }
              }).addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      Toast.makeText(Home.this, "Success", Toast.LENGTH_SHORT).show();
                  }
              });
            }
        });
        fireStore.collection("user").get();
    }

    protected void onStart() {
        super.onStart();
        startTimer();


    }
}