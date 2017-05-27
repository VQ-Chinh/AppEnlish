package com.thelazyteam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thelazyteam.FireBaseConnection.FireBaseConnection;
import com.thelazyteam.SQLLite.GrammarDataBase;
import com.thelazyteam.entity.Grammar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    GrammarDataBase gramdata;
    Button bt;
    EditText t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Write a message to the database
        FireBaseConnection test = new FireBaseConnection();
        //test.addDGrammar();


        gramdata = new GrammarDataBase(this);

        getData();

        t = (EditText) findViewById(R.id.textvivi);
        bt = (Button) findViewById(R.id.buttonvivi);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Grammar> list = gramdata.getAllGrammar();
                t.setText(list.size() + "");
            }
        });
    }

    public void getData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Grammar");
        DatabaseReference version = database.getReference("version");
        version.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue().toString().equals("1"))
                    myRef.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Grammar gram = dataSnapshot.getValue(Grammar.class);
                            Toast.makeText(MainActivity.this, gram.getName(), Toast.LENGTH_SHORT).show();
                            gramdata.addGrammar(gram);
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
