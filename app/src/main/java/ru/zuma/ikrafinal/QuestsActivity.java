package ru.zuma.ikrafinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.zuma.ikrafinal.db.DbManager;
import ru.zuma.ikrafinal.mechanics.QuestWalker;
import ru.zuma.ikrafinal.model.Quest;

public class QuestsActivity extends AppCompatActivity {
    private final static String LOG_TAG = QuestsActivity.class.getSimpleName();

    private List<Quest> listQuests;
    private QuestAdapter adapterQuests;
    private ListView lvQuests;

    private QuestWalker walker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quests);
        MainActionBar.addActionBar(this);

        long workSpaceId = getIntent().getLongExtra("workspaceId", -1);
        if (workSpaceId == -1) {

            Toast.makeText(
                QuestsActivity.this,
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
