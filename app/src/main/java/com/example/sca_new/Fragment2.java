package com.example.sca_new;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Fragment2 extends Fragment {

    EditText etCropName,etArea,etDate;
    Button button;
    UploadTask uploadTask;
    StorageReference storageReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    All_UserMmber member;
    String currentUserId;
    private FirebaseAuth mAuth;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        etCropName = getActivity().findViewById(R.id.et_crop);
        etArea = getActivity().findViewById(R.id.et_area);
        etDate = getActivity().findViewById(R.id.et_date);


        button = getActivity().findViewById(R.id.book_bottom);

        button.setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public void onStart(){
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null)
            Log.d("check","success");
        String currentid = user.getUid();
        DocumentReference reference;
        FirebaseFirestore fiestore = FirebaseFirestore.getInstance();

        reference = fiestore.collection("user").document(currentid);



    }
}
