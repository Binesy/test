package uk.co.rbinesconsulting.babylonhealthtest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import uk.co.rbinesconsulting.babylonhealthtest.ImageLoader;
import uk.co.rbinesconsulting.babylonhealthtest.R;
import uk.co.rbinesconsulting.babylonhealthtest.data.DataService;
import uk.co.rbinesconsulting.babylonhealthtest.data.entity.Person;

public class PersonListFragment extends Fragment {

    private RecyclerView recyclerView;
    private PersonAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_person_list, container);

        adapter = new PersonAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return recyclerView;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        getContacts();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void getContacts() {
        getContext().startService(DataService.getPeopleIntent(getContext()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Person[] people) {
        adapter.setPeople(people);
    }

    public static class Navigation {
        Person person;

        public Navigation(Person person) {
            this.person = person;
        }

        public Person getPerson() {
            return person;
        }
    }

    static class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

        private Person[] people;

        public void setPeople(Person[] people) {
            this.people = people;
            notifyDataSetChanged();
        }

        private void displayDetails(int position) {
            EventBus.getDefault().post(new Navigation(people[position]));
        }

        @Override
        public PersonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_person, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(PersonAdapter.ViewHolder holder, final int position) {
            if(people != null && people[position] != null) {
                holder.textView.setText(people[position].getFirstName() + ' ' + people[position].getSurname());
                holder.parent.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        displayDetails(position);
                    }
                });

                ImageLoader.loadImage(holder.image, people[position].getAvatarUrl());
            }
        }

        @Override
        public int getItemCount() {
            if (people == null) {
                return 0;
            }

            return people.length;
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            View parent;
            ImageView image;
            TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                parent = itemView;
                image = (ImageView) itemView.findViewById(R.id.image);
                textView = (TextView) itemView.findViewById(R.id.name);
            }
        }
    }
}