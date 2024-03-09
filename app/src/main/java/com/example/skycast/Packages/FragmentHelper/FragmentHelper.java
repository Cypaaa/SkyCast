package com.example.skycast.Packages.FragmentHelper;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.skycast.R;

import java.util.HashMap;

public class FragmentHelper {
    // id of the current fragment
    public int CurrentFragment;
    // if of the element holding the fragment
    public int FragmentHolder;
    // fragment manager to replace a fragment
    private FragmentManager FragmentManager;
    private HashMap<Integer, Fragment> Fragments;

    public FragmentHelper(FragmentManager fragmentManager, int fragmentHolder) {
        this.FragmentManager = fragmentManager;
        this.FragmentHolder = fragmentHolder;
        this.Fragments = new HashMap<>();
    }

    public void Register(Fragment fragment, int fragmentID) {
        Fragments.putIfAbsent(fragmentID, fragment);
    }

    // replace a fragment by another one from its id if the fragment is registered in Fragments
    // return true if succeed, false otherwise
    public boolean Replace(int id) {
        Fragment fragment = this.Fragments.get(id);
        if (fragment != null) {
            this.CurrentFragment = id;
            FragmentTransaction ft = this.FragmentManager.beginTransaction();
            ft.replace(this.FragmentHolder, fragment);
            ft.commit();
            return true;
        }
        return false;
    }

    // replace a fragment by another from itself and register this new fragment in Fragments
    public void ReplaceFragment(Fragment fragment, int fragmentID) {
        this.CurrentFragment = fragmentID;
        this.Register(fragment, fragmentID);
        FragmentTransaction ft = this.FragmentManager.beginTransaction();
        ft.replace(this.FragmentHolder, fragment);
        ft.commit();
    }
}
