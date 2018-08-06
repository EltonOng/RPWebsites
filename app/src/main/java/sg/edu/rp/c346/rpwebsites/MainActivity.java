package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spnCat;
    Spinner spnSubCat;
    Button btnGo;

    ArrayList<String> alSchool;
    ArrayAdapter aaSchool;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnCat = findViewById(R.id.spinnerCat);
        spnSubCat = findViewById(R.id.spinnerSubCat);
        btnGo = findViewById(R.id.buttonGo);

        alSchool = new ArrayList<>();
        aaSchool = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,alSchool);

        String[] strSubCat = getResources().getStringArray(R.array.RP);
        alSchool.addAll(Arrays.asList(strSubCat));

        spnCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                alSchool.clear();
                switch (i){
                    case 0:
                        String[] strRP = getResources().getStringArray(R.array.RP);
                        alSchool.addAll(Arrays.asList(strRP));
                        break;
                    case 1:
                        String[] strSOI = getResources().getStringArray(R.array.SOI);
                        alSchool.addAll(Arrays.asList(strSOI));
                        break;
                }
                spnSubCat.setAdapter(aaSchool);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPos = spnCat.getSelectedItemPosition();
                int subCurrentPos = spnSubCat.getSelectedItemPosition();
                if(currentPos == 0){
                    if(subCurrentPos == 0){
                        url = "https://www.rp.edu.sg/";
                    }
                    else{
                        url = "https://www.rp.edu.sg/student-life";
                    }
                }
                else{
                    if(subCurrentPos == 0){
                        url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47";
                    }
                    else{
                        url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12";
                    }
                }
                Intent intent = new Intent(getBaseContext(), WebPage.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putInt("Cat",spnCat.getSelectedItemPosition());
        prefEdit.putInt("SubCat",spnSubCat.getSelectedItemPosition());
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int cat = prefs.getInt("Cat",0);
        int subCat = prefs.getInt("SubCat",0);

        spnCat.setSelection(cat);
        spnSubCat.setSelection(subCat);
        
    }
}
