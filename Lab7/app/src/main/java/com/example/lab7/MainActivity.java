package com.example.lab7;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import Data.Item;
import Fragments.Fragment1;
import Fragments.Fragment2;

public class MainActivity extends FragmentActivity implements Fragment1.OnItemInterface{

    private Fragment2 mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        mFragment = new Fragment2();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.collection, mFragment);
        transaction.commit();
    }

    @Override
    public void ReplaceItemInfo(Item item) {
        mFragment.ReplaceItemInfo(item);
    }
}
