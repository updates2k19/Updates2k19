package com.shrewd.develop.updates2k19.Utilities;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.shrewd.develop.updates2k19.Activity.LoginActivity;
import com.shrewd.develop.updates2k19.Fragment.EventsFragment;
import com.shrewd.develop.updates2k19.R;

public class CU {

    private final static String TAG = "Common Utilities";

    public static void showProgress(ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
    }

    public static void hideProgress(ProgressBar progressBar) {
        progressBar.setVisibility(View.GONE);
    }

    public static void Logout(Context mContext) {
        FirebaseAuth.getInstance().signOut();
        mContext.startActivity(new Intent(mContext, LoginActivity.class));
    }

    public static void setActionBar(ActionBar actionBar, View view, String title) {
//Set actionbar
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT
        );
        actionBar.setCustomView(view,layoutParams);
        Toolbar parent = (Toolbar) view.getParent();
        parent.setPadding(0,0,0,0);
        parent.setContentInsetsAbsolute(0,0);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(title);
    }

    public static void displaySelectedFragment(Fragment fragment, FragmentManager fragmentManager, int id) {
        if (fragment == null) {
            Log.e("Fragment Null", "onNavigationItemSelected: ");
            fragment = new EventsFragment();
        }
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(id, fragment);
        ft.commit();
    }

}
