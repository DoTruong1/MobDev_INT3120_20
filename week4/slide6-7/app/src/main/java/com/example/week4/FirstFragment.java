package com.example.week4;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.week4.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private Button button;
    private TextView textView;
    public FirstFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        button = view.findViewById(R.id.button_first);
        textView = view.findViewById(R.id.textview_first);
        registerForContextMenu(view);
//        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return view;

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
//        if (view.getId() == R.id.button_first) {
            getActivity().getMenuInflater().inflate(R.menu.context,menu);
//        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

            if(item.getItemId() == R.id.context_1) {
                textView.setText("option 1");
                return true;
            } else if (item.getItemId() == R.id.context_2) {
                textView.setText("option 2");
                return true;
            }
            return super.onContextItemSelected(item);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().openContextMenu(button);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}