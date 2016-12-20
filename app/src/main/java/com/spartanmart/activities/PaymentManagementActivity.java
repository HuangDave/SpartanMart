package com.spartanmart.activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.spartanmart.R;
import com.spartanmart.activities.base_activities.BaseActivity;

/**
 * Created by David on 12/19/16.
 */

public class PaymentManagementActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_management);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.payment_method, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add: onUserSelectAddPaymentMethod(); break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onUserSelectAddPaymentMethod() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //startActivity(new Intent(getApplicationContext(), c));
    }
}
