package lv.pw3.repositoryg_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    static final String INPUT_FIELD_TXT_KEY = "input_field_txt_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String savedTextInput = sharedPref.getString(INPUT_FIELD_TXT_KEY, "");

        EditText inputField = findViewById(R.id.txtInputField);
        inputField.setText(savedTextInput);


        Button btnActivity2 = findViewById(R.id.goActicity2Btn);
        btnActivity2.setText(R.string.btn_activity_2);

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setText(R.string.btn_save);

        // add items to the dropdown
        Spinner themeSpinner = findViewById(R.id.themeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.themes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        themeSpinner.setAdapter(adapter);

        // go to 2nd activity
        btnActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Activity2.class));
            }
        });

        // save content of input field in SharedPreferences
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputFieldTxt = inputField.getText().toString();
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(INPUT_FIELD_TXT_KEY, inputFieldTxt);
                editor.apply();
            }
        });

        themeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedTheme = parentView.getItemAtPosition(position).toString();

                if (selectedTheme.equals("Dark theme")) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else if (selectedTheme.equals("Light theme")) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    // TODO implement default to take from SharedPreferences
                    // For now setting to light theme
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // nothing to implement there for now
            }
        });

    }
}