package com.example.luggerz_jovon.AdminFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luggerz_jovon.AllUsers;
import com.example.luggerz_jovon.R;
import com.example.luggerz_jovon.UsersAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminUsersFragment extends Fragment {

    DatabaseReference lugReference;
    DatabaseReference driverReference;
    RecyclerView recyclerView;
    ArrayList<AllUsers> list;
    UsersAdapter adapter;
    private FirebaseAuth mAuth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String customerId = FirebaseAuth.getInstance().getCurrentUser().getUid();



        recyclerView = view.findViewById(R.id.list_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<AllUsers>();

        lugReference = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers");
        driverReference = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers");

        Query driver = driverReference.orderByChild("name");
        driver.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    AllUsers l = dataSnapshot1.getValue(AllUsers.class);
                    String userName = l.getName();
                    String userPhone = l.getPhone();
                    AllUsers fire = new AllUsers(userName, userPhone);
                    list.add(l);
                }
                adapter = new UsersAdapter(getContext(), list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Ooops.....something is wrong", Toast.LENGTH_SHORT).show();

            }
        });

//        lugReference.child("Customers");

        //Attempting to filter by customerId
        Query query = lugReference.orderByChild("name");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    AllUsers l = dataSnapshot1.getValue(AllUsers.class);
                    String userName = l.getName();
                    String userPhone = l.getPhone();
                    AllUsers fire = new AllUsers(userName, userPhone);
                    list.add(l);
                }
                adapter = new UsersAdapter(getContext(), list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Ooops.....something is wrong", Toast.LENGTH_SHORT).show();

            }
        });



    }


}
