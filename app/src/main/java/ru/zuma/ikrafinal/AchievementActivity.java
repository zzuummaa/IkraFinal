package ru.zuma.ikrafinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class AchievementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        TextView tvDescription = findViewById(R.id.tv_description);
        TextView tvCompleted = findViewById(R.id.tv_completed);

        int achievementId = getIntent().getIntExtra("achievementId", -1);
        if (achievementId == -1) {

            Toast.makeText(
                AchievementActivity.this,
                "achievementId not found",
                Toast.LENGTH_SHORT
            ).show();
            finish();

        } else {

            tvDescription.setText("Описание достижения №" + achievementId);
            tvCompleted.setText("получено | не получено");

        }
    }
}
