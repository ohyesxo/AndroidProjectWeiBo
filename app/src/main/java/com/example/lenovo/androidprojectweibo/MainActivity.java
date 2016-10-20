package com.example.lenovo.androidprojectweibo;

import android.app.Fragment;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.behaviour.BottomNavBarFabBehaviour;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{
List<Fragment> fragmentList;
    BottomNavigationBar bottomNavigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        initial();
        setBottomNavigationBar();

    }
public void setView(){
    setContentView(R.layout.bottom_bar);
}
    public void initial(){
        bottomNavigationBar= (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

    }



    public void setBottomNavigationBar(){
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.a,"A"));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.b,"B"));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.c,"C"));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.d,"D"));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.e,"E"));
        bottomNavigationBar.setFirstSelectedPosition(0);
        bottomNavigationBar.initialise();
    }
/*    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.frameLayout_bottom, HomeFragment.newInstance("Home"));
        transaction.commit();
    }
    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("Home"));
        fragments.add(BookFragment.newInstance("Books"));
        fragments.add(MusicFragment.newInstance("Music"));
        fragments.add(TvFragment.newInstance("Movies & TV"));
        fragments.add(GameFragment.newInstance("Games"));
        return fragments;
    }*/
    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
