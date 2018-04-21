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

import ru.zuma.ikrafinal.db.DbManager;
import ru.zuma.ikrafinal.model.Achievment;

public class AchievementsActivity extends AppCompatActivity {

    List<Achievment> listAchievements;
    AchievementAdapter adapterAchievements;
    ListView lvAchievements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workspaces);
        MainActionBar.addActionBar(this);

        listAchievements = DbManager.getInstance().getUserAchievements();

        lvAchievements = findViewById(R.id.lv_workspaces);
        adapterAchievements = new AchievementAdapter(this, listAchievements);

        lvAchievements.setAdapter(adapterAchievements);
        lvAchievements.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(AchievementsActivity.this, AchievementActivity.class);
                Achievment achievment = listAchievements.get(position);
                intent.putExtra("achievementId", achievment.getId());
                startActivity(intent);

            }
        });
    }
}