package ru.zuma.ikrafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.zuma.ikrafinal.db.DbManager;
import ru.zuma.ikrafinal.model.Quest;

public class AddQuestActivity extends AppCompatActivity {

    private long workspaceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quest);
        MainActionBar.addActionBar(this);

        workspaceId = getIntent().getLongExtra("workspaceId", -1);
        if (workspaceId == -1) {
            Toast.makeText(
                AddQuestActivity.this,
                getClass().getName() + " workspaceId not found",
                Toast.LENGTH_SHORT
            ).show();
            setResult(RESULT_CANCELED);
            finish();
        }

        Button button = findViewById(R.id.bt_quest_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Quest quest = createQuest(
                    workspaceId,
                    ((EditText)findViewById(R.id.et_quest_name)).getText().toString(),
                    ((EditText)findViewById(R.id.et_quest_description)).getText().toString(),
                    ((EditText)findViewById(R.id.et_quest_priority)).getText().toString(),
                    ((EditText)findViewById(R.id.et_quest_deadline)).getText().toString()
                );

                if (quest == null) {
                    Toast.makeText(
                        AddQuestActivity.this,
                        "Одно из полей введено не верно",
                        Toast.LENGTH_SHORT
                    ).show();

                    return;
                }

                long questId = DbManager.getInstance().addQuest(quest);
                Intent intent = new Intent();
                intent.putExtra("questId", questId);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public Quest createQuest(long    workspaceId,
                             String name,
                             String description,
                             String priority,
                             String deadline) {

        if (name.trim().equals("")) {
            return null;
        }

        Long longPriority;
        try {
            longPriority = Long.valueOf(priority);
        } catch (Exception e) {
            longPriority = new Long(0);
        }

        Quest quest = new Quest(false);
        quest.setName(name);
        quest.setWorkspaceId(workspaceId);
        quest.setDescription(description);
        quest.setPriority(longPriority);
        quest.setDeadline(deadline);

        return quest;
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
