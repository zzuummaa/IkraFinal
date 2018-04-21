package ru.zuma.ikrafinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.zuma.ikrafinal.db.DbManager;
import ru.zuma.ikrafinal.mechanics.QuestWalker;
import ru.zuma.ikrafinal.model.Quest;

public class QuestsGraphActivity extends AppCompatActivity {
    private final static String LOG_TAG = QuestsGraphActivity.class.getSimpleName();
    private final static int QUEST_LIST_RESULT = 0;

    private long workSpaceId;
    private List<Quest> listQuests;
    private QuestAdapter adapterQuests;
    private ListView lvQuests;

    private QuestWalker walker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quests);
        MainActionBar.addActionBar(this);

        workSpaceId = getIntent().getLongExtra("workspaceId", -1);
        if (workSpaceId == -1) {

            Toast.makeText(
                QuestsGraphActivity.this,
                "workspaceId not found",
                Toast.LENGTH_SHORT
            ).show();
            finish();

        }

        final Quest rootQuest = DbManager.getInstance().getQuestsGraph(workSpaceId);
        walker = new QuestWalker(rootQuest);

        listQuests = new ArrayList<>();
        listQuests.addAll(walker.getCurrentNode().getChildren());

        lvQuests = findViewById(R.id.lv_quests);
        adapterQuests = new QuestAdapter(this, listQuests);

        lvQuests.setAdapter(adapterQuests);
        lvQuests.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d(LOG_TAG, "listView item clicked");

                long childId = listQuests.get(position).getId();
                boolean changed = !walker.isChildLeaf(childId);
                if (changed) {
                    walker.walkToChild(childId);
                    listQuests.clear();
                    listQuests.addAll(walker.getCurrentNode().getChildren());
                    adapterQuests.notifyDataSetChanged();
                }

            }
        });

        Button btQuestsList = (Button) findViewById(R.id.bt_quests_list);
        btQuestsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestsGraphActivity.this, QuestsListActivity.class);
                intent.putExtra("workspaceId", workSpaceId);
                intent.putExtra("questId", walker.getCurrentNode().getId());
                startActivityForResult(intent, QUEST_LIST_RESULT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == QUEST_LIST_RESULT && resultCode == RESULT_OK) {

            long questId = data.getLongExtra("questId", -1);
            if (questId != -1) {
                DbManager.getInstance().addQuestChildren(walker.getCurrentNode(), questId);
                final Quest rootQuest = DbManager.getInstance().getQuestsGraph(workSpaceId);
                walker.rewalk(rootQuest);

                listQuests.clear();
                listQuests.addAll(walker.getCurrentNode().getChildren());
                adapterQuests.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Log.d(LOG_TAG, "onBackPressed()");


        boolean changed = walker.walkToParent();

        if (!changed) {

            finish();

        } else {

            listQuests.clear();
            listQuests.addAll(walker.getCurrentNode().getChildren());
            adapterQuests.notifyDataSetChanged();

        }
    }
}
