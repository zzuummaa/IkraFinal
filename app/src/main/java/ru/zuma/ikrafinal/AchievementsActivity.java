package ru.zuma.ikrafinal;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AchievementsActivity extends AppCompatActivity {

    List<String> listAchievements;
    ArrayAdapter<String> adapterAchievements;
    ListView lvAchievements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workspaces);
        MainActionBar.addActionBar(this);

        listAchievements = new ArrayList<>();
        listAchievements.add("Достижение 1");
        listAchievements.add("Достижение 2");

        lvAchievements = findViewById(R.id.lv_workspaces);
        adapterAchievements = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listAchievements);

        lvAchievements.setAdapter(adapterAchievements);
        lvAchievements.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(AchievementsActivity.this, AchievementActivity.class);
                intent.putExtra("achievementId", position);
                startActivity(intent);

            }
        });
    }
}