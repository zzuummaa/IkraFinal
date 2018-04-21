package ru.zuma.ikrafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ru.zuma.ikrafinal.db.DbManager;
import ru.zuma.ikrafinal.model.Quest;

public class QuestsListActivity extends AppCompatActivity {
    static int ADD_QUEST_RESULT = 0;
    private String LOG_TAG = QuestsListActivity.class.getName();

    private boolean isQuestsChanged = false;
    private long workSpaceId;
    private List<Quest> listQuests;
    private QuestAdapter adapterQuests;
    private ListView lvQuests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quests);

        workSpaceId = getIntent().getLongExtra("workspaceId", -1);
        if (workSpaceId == -1) {

            Toast.makeText(
                QuestsListActivity.this,
                getClass().getSimpleName() + " workspaceId not found",
                Toast.LENGTH_SHORT
            ).show();
            finish();

        }

        listQuests = DbManager.getInstance().getQuestsList(workSpaceId);

        adapterQuests = new QuestAdapter(this, listQuests);

        lvQuests = findViewById(R.id.lv_quests);
        lvQuests.setAdapter(adapterQuests);

        Button fab = (Button) findViewById(R.id.bt_add_quest);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestsListActivity.this, AddQuestActivity.class);
                intent.putExtra("workspaceId", workSpaceId);
                startActivityForResult(intent, ADD_QUEST_RESULT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_QUEST_RESULT && resultCode == RESULT_OK) {

            long questId = data.getLongExtra("questId", -1);
            if (questId != -1) {

                // TODO get single quest from db by workspaceId
                List<Quest> quests = DbManager.getInstance().getQuestsList(workSpaceId);
                listQuests.clear();
                listQuests.addAll(quests);
                adapterQuests.notifyDataSetChanged();
                isQuestsChanged = true;

            }

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("isQuestsChanged", isQuestsChanged);
        setResult(RESULT_OK, intent);
        finish();
    }
}
