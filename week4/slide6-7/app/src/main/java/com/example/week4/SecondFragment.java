package com.example.week4;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.week4.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private Button button1;
    private Button button2;
    public SecondFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        button1 = view.findViewById(R.id.button_second);
        button2 = view.findViewById(R.id.button_anchor);
        registerForContextMenu(view);
//        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return view;

    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1Clicked();
            }
        });
    }

    private void button1Clicked() {
        PopupMenu popupMenu = new PopupMenu(requireContext(), this.button2);
        popupMenu.inflate(R.menu.popup);
        Menu menu = popupMenu.getMenu();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                button2.setText(menuItem.getTitle());
                return false;
            }
        });
        popupMenu.show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}