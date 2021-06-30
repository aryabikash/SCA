package com.example.sca_new;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class Fragment1 extends Fragment implements View.OnClickListener{

    ImageView imageview;
    TextView nameEt,emailEt,contactEt,addressEt;
    ImageButton imageMenu;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        imageview = getActivity().findViewById(R.id.iv_f1);
        nameEt = getActivity().findViewById(R.id.tv_name_f1);
        emailEt = getActivity().findViewById(R.id.tv_email_f1);
        contactEt = getActivity().findViewById(R.id.tv_contact_f1);
        addressEt = getActivity().findViewById(R.id.tv_address_f1);

        imageMenu = getActivity().findViewById(R.id.ib_menu_f1);

        imageMenu.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.ib_menu_f1:
                BottomSheetMenu bottomSheetMenu = new BottomSheetMenu();
                bottomSheetMenu.show(getFragmentManager(),"bottomsheet");


        }
    }
    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null)
            Log.d("check","success");
        String currentid = user.getUid();
        DocumentReference reference;
        FirebaseFirestore fiestore = FirebaseFirestore.getInstance();

        reference = fiestore.collection("user").document(currentid);


        reference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()) {

                            String nameResult = task.getResult().getString("name");
                            String emailResult = task.getResult().getString("email");
                            String contactResult = task.getResult().getString("contact");
                            String addressResult = task.getResult().getString("address");
                            String url = task.getResult().getString("url");


                            Picasso.get().load(url).into(imageview);
                            nameEt.setText(nameResult);
                            emailEt.setText(emailResult);
                            addressEt.setText(addressResult);
                            contactEt.setText(contactResult);
                        } else {
                            Intent intent = new Intent(getActivity(), CreateProfile.class);
                            startActivity(intent);
                        }
                    }
                });
    }


}
