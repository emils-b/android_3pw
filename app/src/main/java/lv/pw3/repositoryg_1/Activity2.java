package lv.pw3.repositoryg_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Button btnPreferences = findViewById(R.id.readPreferencesBtn);
        btnPreferences.setText(R.string.btn_read_preferences);
        SharedPreferences sharedPref = this.getSharedPreferences("MainActivity",Context.MODE_PRIVATE);

        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setText(R.string.btn_back);

        // go to 1st activity
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String savedTextInput = sharedPref.getString("stored_value", "");
                Log.d("fetchedText",savedTextInput);
                TextView textField = findViewById(R.id.savedTxt);
                textField.setText(savedTextInput);

                if (savedTextInput.equals("")) {
                    Toast toast = Toast.makeText(context,"Nothing found!",duration);
                    toast.show();
                }
            }
        });
    }
}