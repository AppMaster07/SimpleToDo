package sg.edu.rp.c346.id20002369.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView etMov;
    Button btnA, btnC, btnD;
    ListView lv;
    Spinner spn;
    ArrayAdapter adapter;
    ArrayList<String> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMov = findViewById(R.id.editText);
        btnA = findViewById(R.id.buttonAdd);
        btnD = findViewById(R.id.buttonDel);
        btnC = findViewById(R.id.buttonClear);
        lv = findViewById(R.id.listView);
        spn = findViewById(R.id.spinner);

        tasks = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        lv.setAdapter(adapter);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etMov.setHint("Type in a new task here");
                        btnD.setEnabled(false);
                        btnA.setEnabled(true);
                        break;
                    case 1:
                        btnA.setEnabled(false);
                        btnD.setEnabled(true);
                        etMov.setHint("Type in the index of the task to be removed");
                        if (tasks.isEmpty()) {
                            Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskEntered = etMov.getText().toString();
                tasks.add(taskEntered);
                adapter.notifyDataSetChanged();
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(etMov.getText().toString());
                if (tasks.size() < pos) {
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                }
                else{
                    tasks.remove(pos);
                }
                adapter.notifyDataSetChanged();
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasks.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }
}