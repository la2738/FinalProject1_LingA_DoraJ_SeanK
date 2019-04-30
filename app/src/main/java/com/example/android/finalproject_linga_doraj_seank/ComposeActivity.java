package com.example.android.finalproject_linga_doraj_seank;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class ComposeActivity extends AppCompatActivity {

    public static final int RC_PHOTO_PICKER = 2;
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 60;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mPostDatabaseReference;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mPostPhotosStorageReference;
    private FirebaseAuth mFirebaseAuth;

    private ImageView addPhoto;
    private EditText postTitle;
    private EditText postContent;
    private Button postButton;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        Intent intent = getIntent();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();
        mPostDatabaseReference = mFirebaseDatabase.getReference().child("Posts");
        mPostPhotosStorageReference = mFirebaseStorage.getReference().child("post_photos");
        mFirebaseAuth = FirebaseAuth.getInstance();

        addPhoto = (ImageView)findViewById(R.id.add_pic_button);
        postTitle = (EditText)findViewById(R.id.post_title);
        postContent = (EditText)findViewById(R.id.post_content);
        postButton = (Button)findViewById(R.id.post_button);

        List<Post> newPost = new ArrayList<>();

        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
            }
        });

        postTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    postButton.setEnabled(true);
                } else {
                    postButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        postTitle.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComposeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK){
            Uri selectedImageUri = data.getData();
            final StorageReference photoRef = mPostPhotosStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return photoRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
                        username = currentUser.getDisplayName();
                        Post newPost = new Post(downloadUri.toString(), postTitle.getText().toString(), "👤"+username, "♡ 0");
                        mPostDatabaseReference.push().setValue(newPost);
                    } else {
                        Toast.makeText(ComposeActivity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
