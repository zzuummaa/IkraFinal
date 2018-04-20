package ru.zuma.ikrafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuestsActivity extends AppCompatActivity {

    List<String> listQuests;
    ArrayAdapter<String> adapterQuests;
    ListView lvQuests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quests);

        listQuests = new ArrayList<>();
        listQuests.add("Квест 1");
        listQuests.add("Квест 2");

        lvQuests = findViewById(R.id.lv_quests);
        adapterQuests = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listQuests);

        lvQuests.setAdapter(adapterQuests);
        lvQuests.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(
                    QuestsActivity.this,
                    listQuests.get(position) + " нажат",
                    Toast.LENGTH_SHORT
                ).show();

            }
        });
    }
}
