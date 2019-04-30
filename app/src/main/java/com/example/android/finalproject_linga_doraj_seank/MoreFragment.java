package com.example.android.finalproject_linga_doraj_seank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class MoreFragment extends Fragment {

    public static final int RC_SIGN_IN = 1;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private TextView userStatus;
    private LinearLayout loginStrip;

    private Context context ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_more, container, false);

        mFirebaseAuth = FirebaseAuth.getInstance();
        userStatus = (TextView) rootView.findViewById(R.id.user_status);
        loginStrip = (LinearLayout)rootView.findViewById(R.id.login_strip);

        setHasOptionsMenu(true);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    userStatus.setText("Welcome, " + user.getDisplayName());
                } else{
                    userStatus.setText("Log in or Sign Up   ");
                }
            }
        };
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);

        loginStrip.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mAuthStateListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user == null) {
                            startActivityForResult(
                                    AuthUI.getInstance()
                                            .createSignInIntentBuilder()
                                            .setIsSmartLockEnabled(false)
                                            .setAvailableProviders(Arrays.asList(
                                                    new AuthUI.IdpConfig.EmailBuilder().build(),
                                                    new AuthUI.IdpConfig.GoogleBuilder().build()))
                                            .build(),
                                    RC_SIGN_IN);
                        }
                    }
                };
                mFirebaseAuth.addAuthStateListener(mAuthStateListener);

                Intent intent = new Intent(getContext(), UserDevelopmentActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.liked).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context ;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null ;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Toast.makeText(getActivity(), "Signed in!", Toast.LENGTH_SHORT).show();
            userStatus.setText("Welcome, " + mFirebaseAuth.getCurrentUser().getDisplayName());
        } else if (requestCode == RESULT_CANCELED) {
            Toast.makeText(getActivity(), "Sign in Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout_button:
                AuthUI.getInstance().signOut(getActivity());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
