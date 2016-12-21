package com.spartanmart.activities.base_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.spartanmart.R;
import com.spartanmart.activities.AccountActivity;
import com.spartanmart.activities.BrowseActivity;
import com.spartanmart.activities.PaymentManagementActivity;
import com.spartanmart.activities.TransactionsActivity;
import com.spartanmart.activities.authentication.LoginActivity;
import com.spartanmart.activities.product_management.UserProductsAcitivity;
import com.spartanmart.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected FrameLayout frameLayout;

    protected Toolbar mToolBar;

    protected DrawerLayout mDrawer;

    protected NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (User.currentUser() == null) {
            startActivity(new Intent(BaseActivity.this, LoginActivity.class));
        }

        super.setContentView(R.layout.activity_base);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        TextView tvName = (TextView)mNavigationView.getHeaderView(0).findViewById(R.id.nav_header_name);
        if (User.currentUser() != null) {
            String name = User.currentUser().name.get("first") + " " + User.currentUser().name.get("last");
            if (tvName.getText().toString() != name) {
                tvName.setText(name);
            }
        }

        frameLayout = (FrameLayout)findViewById(R.id.content_frame);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getLayoutInflater().inflate(layoutResID, frameLayout);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Class c = null;
        switch (item.getItemId()) {
            case R.id.nav_browse:           c = BrowseActivity.class;               break;
            case R.id.nav_products:         c = UserProductsAcitivity.class;        break;
            case R.id.nav_transactions:     c = TransactionsActivity.class;         break;
            case R.id.nav_account:          c = AccountActivity.class;              break;
            case R.id.nav_payment_methods:  c = PaymentManagementActivity.class;    break;
            case R.id.nav_logout:           c = LoginActivity.class;                break;
        }

        startActivity(new Intent(getApplicationContext(), c));
        finish();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
