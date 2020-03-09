package tk.winsweb.myrestaurant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindFont;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import tk.winsweb.myrestaurant.Common.Common;
import tk.winsweb.myrestaurant.Retrofit.IMyRestaurantAPI;
import tk.winsweb.myrestaurant.Retrofit.RetrofitClient;

public class MainActivity extends AppCompatActivity {

    IMyRestaurantAPI myRestaurantAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    AlertDialog dialog;


    private List<AuthUI.IdpConfig> provider;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;

    private static final int APP_REQUEST_CODE =1234;

    @BindView(R.id.btn_sign_in)
    Button btn_sign_in;

    @OnClick(R.id.btn_sign_in)
    void loginUser()
    {
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(provider).build(),APP_REQUEST_CODE);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE)
        {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (requestCode == RESULT_OK)
            {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // Call listener change
            }
            else
            {
                Toast.makeText(this, "Failed to Sign in", Toast.LENGTH_SHORT).show();
            }


        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        init();
    }

    private void init()
    {
        provider = Arrays.asList(new AuthUI.IdpConfig.PhoneBuilder().build());

        firebaseAuth = FirebaseAuth.getInstance();

        listener = firebaseAuth1 -> {
            FirebaseUser user = firebaseAuth1.getCurrentUser();
            if (user != null) // User already login
            {

            /*    //Save FBID
                //Paper.b

                FirebaseInstanceId.getInstance()
                        .getInstanceId()
                        .addOnFailureListener(e -> Toast.makeText(MainActivity.this, "{GET TOKEN}" + e.getMessage(), Toast.LENGTH_SHORT).show()).addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Authorization", Common.builJWT(Common.API_KEY));
                        compositeDisposable.add(myRestaurantAPI.updateTokenToServer(headers,
                                task.getResult().getToken())
                                .subscribeOne(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(tokenModel -> {

                                    if (!tokenModel.isSuccess())

                                        Toast.makeText(MainActivity.this, "{UPDATE TOKEN" + tokenModel.getMessage(), Toast.LENGTH_SHORT).show();

                                    compositeDisposable.add(myRestaurantAPI.getUser(headers)
                                            .subscribeOne(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(tokenModel -> {
                                                //if user already registered

                                                if (userModel.isSuccess()) {
                                                    Common.currentUer = userModel.getResult.get(0);
                                                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                                    finish();
                                                } else {
                                                    startActivity(new Intent(MainActivity.this, UpdateInfoActivity.class));
                                                    finish();
                                                }

                                                dialog.dismiss();

                                            },
                                                    throwable ->{
                                                dialog.dismiss();
                                                        Toast.makeText(MainActivity.this, "{GET USER}" + throwable.getMessage, Toast.LENGTH_SHORT).show();
                                            }));
                                })

                    }
                });



                */


            }
            else
            {
                loginUser();
            }
        };


        dialog = new SpotsDialog.Builder().setContext(this).setCancelable(false).build();
        myRestaurantAPI = RetrofitClient.getInstance(Common.API_RESTAURANT_ENDPOINT).create(IMyRestaurantAPI.class);

    }


}
