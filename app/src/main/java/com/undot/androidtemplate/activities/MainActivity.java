package com.undot.androidtemplate.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.squareup.haha.perflib.Main;
import com.undot.androidtemplate.R;
import com.undot.androidtemplate.network.NetworkController;
import com.undot.androidtemplate.network.requests.LoginRequest;
import com.undot.androidtemplate.network.responses.LoginResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.saripaar_button)
    void openSaripaarActivity(){
        startActivity(new Intent(MainActivity.this,SaripaarActivity.class));
    }

    @OnClick(R.id.rv_button)
    void openRVActivity(){
        startActivity(new Intent(MainActivity.this,RecycleViewActivity.class));
    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    // This method will be called when a SomeOtherEvent is posted
    @Subscribe
    public void loginResponse(LoginResponse event){
            if(event.isSuccess())
            {
                Timber.d("login success! from MainActivity");
            }
        else {
                Timber.d("login failed from MainActivity");
            }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
