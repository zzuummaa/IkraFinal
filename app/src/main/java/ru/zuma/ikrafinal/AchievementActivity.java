package ru.zuma.ikrafinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import ru.zuma.ikrafinal.db.DbManager;

public class AchievementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);
        MainActionBar.addActionBar(this);

        TextView tvDescription = findViewById(R.id.tv_description);
        TextView tvCompleted = findViewById(R.id.tv_completed);
        TextView tvAchievement = findViewById(R.id.tv_achievement);

        int achievementId = getIntent().getIntExtra("achievementId", -1);
        if (achievementId == -1) {

            Toast.makeText(
                AchievementActivity.this,
                "achievementId not found",
                Toast.LENGTH_SHORT
            ).show();
            finish();

        } else {
            //String achievementName = DbManager.getInstance().get//...
            String achievementName = "Повелитель Дейнерис";
            //Если ачивка ещё не получена, то описание скрыто. Описание - часть награды
            String achievementDescription = "Покрыл не только весь мир..";
            String achievementState = "Получено"

            tvAchievement.setText(achievementName);
            tvDescription.setText(achievementDescription);
            tvCompleted.setText("получено | не получено");

        }
    }
}
