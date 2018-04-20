package ru.zuma.ikrafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ru.zuma.ikrafinal.db.DbManager;
import ru.zuma.ikrafinal.model.Quest;

public class MainActivity extends AppCompatActivity {
    private final int REGISTRATION_RESULT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
        startActivityForResult(intent, REGISTRATION_RESULT);

        Button btWorkspaces = findViewById(R.id.bt_workspaces);
        btWorkspaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WorkspacesActivity.class);
                startActivity(intent);
            }
        });

        Button btAchievements = findViewById(R.id.bt_achievements);
        btAchievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AchievementsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REGISTRATION_RESULT) {
            if (resultCode == RESULT_CANCELED) {
                finish();
            }
        }
    }
}
