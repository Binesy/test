package uk.co.rbinesconsulting.babylonhealthtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import uk.co.rbinesconsulting.babylonhealthtest.fragment.DetailsFragment;
import uk.co.rbinesconsulting.babylonhealthtest.fragment.PersonListFragment;
import uk.co.rbinesconsulting.babylonhealthtest.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onEvent(PersonListFragment.Navigation event) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsFragment.PERSON, event.getPerson());
        startActivity(intent);
    }
}