package com.eccuria.demos.calculadora;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends Activity {
    private double tipPercentage = -1;
    public final static String KEY_TOTAL_INCLUDING_TIP = "totalIncludingTip";
    public final static String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSubmit = (Button)findViewById(R.id.btnSubmit);
        RadioGroup radioGroupTip = (RadioGroup) findViewById(R.id.radioGroupTip);

        btnSubmit.setOnClickListener(new buttonListener());
        radioGroupTip.setOnCheckedChangeListener(new radioChangeListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class buttonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.i(TAG,"El botón ha sido presionado");
            if (tipPercentage != -1) {
                EditText editTextTotal = (EditText)findViewById(R.id.total);
                String txtTotal = editTextTotal.getText().toString();

                if (txtTotal.equals("")) {
                    Toast.makeText(MainActivity.this, R.string.err_editText_empty, Toast.LENGTH_SHORT).show();
                } else {
                    double total = Double.parseDouble(txtTotal);
                    total += total*tipPercentage;

                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        Fragment fragment = getFragmentManager().findFragmentById(R.id.resultFragment);
                        if (fragment != null && fragment.isInLayout()) {
                            ResultFragment resultFragment = (ResultFragment)fragment;
                            resultFragment.setResult(total);
                        }
                    } else {
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtra(KEY_TOTAL_INCLUDING_TIP, total);
                        startActivity(intent);
                    }
                }
            } else {
                Toast.makeText(MainActivity.this, R.string.err_option_not_selected, Toast.LENGTH_SHORT).show();
            }
        }
    }

    class radioChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            Log.i(TAG,"Cambio en el radio group");
            switch (checkedId) {
                case R.id.radio10p:
                    tipPercentage = 0.10;
                    break;
                case R.id.radio15p:
                    tipPercentage = 0.15;
                    break;
                case R.id.radio18p:
                    tipPercentage = 0.18;
                    break;
            }

        }
    }
}
