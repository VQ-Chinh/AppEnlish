package com.thelazyteam.FireBaseConnection;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thelazyteam.entity.Grammar;
import com.thelazyteam.entity.UsingGrammar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AM on 5/23/2017.
 */

public class FireBaseConnection {

    private FirebaseDatabase database;
    private List<Grammar> list;

    public void addDGrammar() {
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        UsingGrammar u1 = new UsingGrammar();
        u1.setUsing("text1");
        u1.setExample("exp1");

        UsingGrammar u2 = new UsingGrammar();
        u2.setUsing("text2");
        u2.setExample("exp2");

        List<UsingGrammar> list = new ArrayList<UsingGrammar>();

        list.add(u1);
        list.add(u2);

        Grammar present = new Grammar();
        present.setName("present");
        present.setForm("form ne");
        present.setListUsingGrammar(list);

        Grammar past = new Grammar();
        present.setName("Past");
        present.setForm("form ne 2");
        present.setListUsingGrammar(list);

        myRef.child("Grammar").push().setValue(present);
        myRef.child("Grammar").push().setValue(past);
    }

    public List<Grammar> getDataGrammar() {
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Grammar");
         list = new ArrayList<>();
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Grammar gram = dataSnapshot.getValue(Grammar.class);
                addToList(gram);
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

        return list;
    }

    public void addToList(Grammar gram){
        Grammar data = new Grammar();
        data.setName(gram.getName());
        data.setForm(gram.getForm());

        list.add(data);
    }
}
