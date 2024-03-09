package com.example.skycast;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skycast.Adapters.HourlyAdapter;
import com.example.skycast.Adapters.SimpleTextAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFavouriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFavouriteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFavouriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFavouriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFavouriteFragment newInstance(String param1, String param2) {
        SettingsFavouriteFragment fragment = new SettingsFavouriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_favourite, container, false);

        Button add_button = view.findViewById(R.id.add_button);
        add_button.setOnClickListener((e) -> {
            TextInputEditText text_input = view.findViewById(R.id.text_input);
            RecyclerView city_name_list = view.findViewById(R.id.city_name_list);

            SimpleTextAdapter adapter = new SimpleTextAdapter(text_input.getText().toString());
            city_name_list.setAdapter(adapter);
            Toast.makeText(container.getContext(), "added but invisible", Toast.LENGTH_SHORT).show();
        });

        // Inflate the layout for this fragment
        return view;
    }
}