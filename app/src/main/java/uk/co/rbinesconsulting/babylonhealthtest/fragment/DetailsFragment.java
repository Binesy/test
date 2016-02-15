package uk.co.rbinesconsulting.babylonhealthtest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.rbinesconsulting.babylonhealthtest.ImageLoader;
import uk.co.rbinesconsulting.babylonhealthtest.data.entity.Person;
import uk.co.rbinesconsulting.babylonhealthtest.R;

public class DetailsFragment extends Fragment {

    public static final String PERSON = "person";

    private ImageView image;
    private TextView firstName;
    private TextView surname;
    private TextView address;
    private TextView phoneNumber;
    private TextView email;
    private TextView createdAt;
    private TextView updatedAt;

    private Person person;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getActivity().getIntent();
        person = intent.getParcelableExtra(PERSON);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container);

        image = (ImageView) view.findViewById(R.id.image);
        firstName = (TextView) view.findViewById(R.id.first_name);
        surname = (TextView) view.findViewById(R.id.surname);
        address = (TextView) view.findViewById(R.id.address);
        phoneNumber = (TextView) view.findViewById(R.id.phone_number);
        email = (TextView) view.findViewById(R.id.email);
        createdAt = (TextView) view.findViewById(R.id.created_at);
        updatedAt = (TextView) view.findViewById(R.id.updated_at);

        firstName.setText(person.getFirstName());
        surname.setText(person.getSurname());
        address.setText(person.getAddress());
        phoneNumber.setText(person.getPhoneNumber());
        email.setText(person.getEmail());
        createdAt.setText(person.getCreatedAt());
        updatedAt.setText(person.getUpdatedAt());

        ImageLoader.loadImage(image, person.getAvatarUrl());

        return view;
    }
}