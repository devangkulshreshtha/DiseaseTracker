package devang.developer.com.diseasetracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class Symptoms2 extends ActionBarActivity implements View.OnClickListener{
    ListView lv;
    Button button;
    private ListView lvw;
    ArrayAdapter<String> adapter;
    EditText inputSearch;
    ArrayList<HashMap<String, String>> productList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms2);
        button=(Button)findViewById(R.id.button3);
        button.setOnClickListener(this);
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        lv = (ListView)findViewById(R.id.listView);
        String[] symptoms = getselectedSymptoms();
        lv.setAdapter(new CustomAdapter4(this, symptoms));

        // Listview Data
        String products[] = values.symptoms;

        lvw = (ListView) findViewById(R.id.my_list_view);
        inputSearch = (EditText) findViewById(R.id.editText3);

        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.simple_row, R.id.product_name, products);
        lvw.setAdapter(adapter);
        lvw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvw.setVisibility(View.INVISIBLE);
                TextView tv = (TextView)view.findViewById(R.id.product_name);
                int i = get_symptom_index(tv.getText().toString());
                values.selectedsymptoms[i] = 1;
                Toast.makeText(Symptoms2.this, String.valueOf(i), Toast.LENGTH_SHORT).show();
                lv.setAdapter(new CustomAdapter4(Symptoms2.this, getselectedSymptoms()));
                InputMethodManager imm = (InputMethodManager)getSystemService(
                        Symptoms2.this.INPUT_METHOD_SERVICE);
//txtName is a reference of an EditText Field
                imm.hideSoftInputFromWindow(inputSearch.getWindowToken(), 0);
                inputSearch.setText(null);
                lvw.setVisibility(View.INVISIBLE);
            }
        });
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Symptoms2.this.adapter.getFilter().filter(s);
                lvw.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private int get_symptom_index(String s) {
        for(int i=0;i<values.symptoms.length;i++)
        {
            if(values.symptoms[i].equals(s))
                return i;
        }
        return 0;
    }

    private String[] getselectedSymptoms()
    {
        int k = 0;
        for (int i = 1; i < values.selectedsymptoms.length;)
        {
            int l = k;
            if (values.selectedsymptoms[i] == 1)
            {
                l = k + 1;
            }
            i++;
            k = l;
        }

        String as[] = new String[k];
        k = 0;
        for (int j = 1; j < values.selectedsymptoms.length;)
        {
            int i1 = k;
            if (values.selectedsymptoms[j] == 1)
            {
                as[k] = values.symptoms[j];
                i1 = k + 1;
            }
            j++;
            k = i1;
        }

        return as;
    }

    @Override
    public void onClick(View v) {
        int i;
        for(i=0;i<values.selectedsymptoms.length;i++)
        {
            if(values.selectedsymptoms[i] == 1)
                break;;
        }
        if(i == values.selectedsymptoms.length)
            Toast.makeText(this, "No symptoms yet", Toast.LENGTH_SHORT).show();
        else
            startActivity(new Intent(Symptoms2.this, RiskFactors.class));
    }
}
