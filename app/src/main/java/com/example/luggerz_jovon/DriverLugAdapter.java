package com.example.luggerz_jovon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DriverLugAdapter extends RecyclerView.Adapter<DriverLugAdapter.DriverLugViewHolder> {
    private FirebaseAuth mAuth;
    private DatabaseReference lugReference;
    private String driverID, lugId;



    Context context;
        ArrayList<Lugs> lugs;
public DriverLugAdapter(Context c, ArrayList<Lugs> l){
        context = c;
        lugs = l;
        }


@NonNull
@Override
public DriverLugViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DriverLugViewHolder(LayoutInflater.from(context).inflate(R.layout.list_mylugs,parent, false));
        }

@Override
public void onBindViewHolder(@NonNull DriverLugViewHolder holder, int position) {
        holder.itemDescription.setText(lugs.get(position).getItemDescription());
        holder.date.setText(lugs.get(position).getDate());
        holder.time.setText(lugs.get(position).getTime());
        holder.pickupLocation.setText(lugs.get(position).getPickupLocation());
        holder.destination.setText(lugs.get(position).getDestination());
        holder.btnAccept.setVisibility(View.VISIBLE);
        holder.onClick(position);
        }

@Override
public int getItemCount() {
        return lugs.size();
        }

class DriverLugViewHolder extends RecyclerView.ViewHolder{
    TextView itemDescription, date, time, pickupLocation, destination;
    Button btnAccept;
    public DriverLugViewHolder(View itemView){
        super(itemView);
        mAuth = FirebaseAuth.getInstance();
        driverID = mAuth.getCurrentUser().getUid();




        itemDescription = itemView.findViewById(R.id.list_itemDescription);
        date = itemView.findViewById(R.id.list_date);
        time = itemView.findViewById(R.id.list_time);
        pickupLocation = itemView.findViewById(R.id.list_pickupLocation);
        destination = itemView.findViewById(R.id.list_destination);
        btnAccept = itemView.findViewById(R.id.acceptLug);

    }

    public void onClick(final int position)
    {
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lugId = lugs.get(position).getLugId();
                Toast.makeText(context, position+lugId+" is clicked", Toast.LENGTH_SHORT).show();
                //TODO: Implement Accept Lug functionality
                DatabaseReference lugRef = FirebaseDatabase.getInstance().getReference();
                Map<String, Object> map = new HashMap<>();
                map.put("driverId", driverID);
                lugRef.child("lugs").child(lugId).updateChildren(map);
               // lugReference.child("driverId").setValue(driverID);

            }

               FirebaseDatabase l = FirebaseDatabase.getInstance();

        });
    }
    }
}

