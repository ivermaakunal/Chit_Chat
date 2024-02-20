package com.example.whatsapp1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class form extends AppCompatActivity {

    private static final int pick_request = 1;
    private Button upload;
    private ProgressBar progress;
    private FloatingActionButton pick;
    private EditText namex,phnumx;
    private ImageView img;

    private DatabaseReference ref;
    private StorageReference ref2;
    private Uri imguri;
    String name;
    String phnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        pick = findViewById(R.id.pick);
        namex = findViewById(R.id.usrname);
        phnumx = findViewById(R.id.usrphonee);
        img = findViewById(R.id.imgpick);
        upload = findViewById(R.id.btconfirm);
        progress = findViewById(R.id.progress_bar);
        ref2 = FirebaseStorage.getInstance().getReference("uploads");
        ref = FirebaseDatabase.getInstance().getReference("uploads");


        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               choose();

            }

        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadfirebase();
                Intent i = new Intent(getApplicationContext(),MainActivity3.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void choose() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,pick_request);
    }

    private String checkExten(Uri uri){
        ContentResolver ce = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(ce.getType(uri));
    }

    private void uploadfirebase() {
        if (imguri != null){
            StorageReference filere = ref2.child(System.currentTimeMillis() + "." + checkExten(imguri));
            filere.putFile(imguri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progress.setProgress(0);
                        }
                    },2000);

                    Toast.makeText(form.this, "Upload Success", Toast.LENGTH_SHORT).show();
                    usrModel us = new usrModel(taskSnapshot.getMetadata().getReference().getDownloadUrl().toString(),namex.getText().toString().trim(), phnumx.getText().toString());
                    String uId = ref.push().getKey();
                    ref.child(uId).setValue(us);
                }
            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(form.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double xprogress = (100.0*snapshot.getBytesTransferred()/ snapshot.getTotalByteCount());
                            progress.setProgress((int) xprogress);
                        }
                    });
        }
        else{
            Toast.makeText(this, "Select Photo", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==pick_request && resultCode == RESULT_OK
            && data != null && data.getData() != null){
                imguri = data.getData();

                img.setImageURI(imguri);

            }
    }
}