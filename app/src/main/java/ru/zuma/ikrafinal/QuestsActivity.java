package ru.zuma.ikrafinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ru.zuma.ikrafinal.db.DbManager;
import ru.zuma.ikrafinal.model.Quest;

public class QuestsActivity extends AppCompatActivity {
    private final static String LOG_TAG = QuestsActivity.class.getSimpleName();

    Stack<Quest> questStack;

    List<Quest> listQuests;
    QuestAdapter adapterQuests;
    ListView lvQuests;

    Quest quest1;
    Quest quest11;

    Quest quest2;
    Quest quest21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quests);

//        int workSpaceId = getIntent().getIntExtra("workspaceId", -1);
//        if (workSpaceId == -1) {
//
//            Toast.makeText(
//                QuestsActivity.this,
//                "workspaceId not found",
//                Toast.LENGTH_SHORT
//            );
//            finish();
//
//        }

//        listQuests = DbManager.getInstance().getQuestsList(workSpaceId);
        listQuests = new ArrayList<>();

        quest11 = new Quest();
        quest11.setName("Квест 11");

        quest1 = new Quest();
        quest1.setName("Квест 1");
        quest1.getChildren().add(quest11);

        quest21 = new Quest();
        quest21.setName("Квест 21");

        quest2 = new Quest();
        quest2.setName("Квест 2");
        quest2.getChildren().add(quest21);

        listQuests.add(quest1);
        listQuests.add(quest2);

        Quest hack = new Quest();
        hack.getChildren().addAll(listQuests);

        questStack = new Stack<>();
        questStack.push(hack);

        lvQuests = findViewById(R.id.lv_quests);
        adapterQuests = new QuestAdapter(this, listQuests);

        lvQuests.setAdapter(adapterQuests);
        lvQuests.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d(LOG_TAG, "listView item clicked");

                Quest children = listQuests.get(position);
                questStack.push(children);
                listQuests.clear();
                listQuests.addAll(children.getChildren());
                adapterQuests.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void onBackPressed() {
        Log.d(LOG_TAG, "onBackPressed()");

        questStack.pop();

        if (questStack.empty()) {

            finish();

        } else {

            Quest curr = questStack.peek();

            listQuests.clear();
            listQuests.addAll(curr.getChildren());
            adapterQuests.notifyDataSetChanged();

        }
    }
}
