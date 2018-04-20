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
            if (!data.getBooleanExtra("isRegistered", false)) {
                finish();
            }
        }
    }

    /**
    private void create() {
        Quest parent1 = new Quest();
        parent1.setName("Parent 1");
        parent1.setWorkspaceId(1);
        Quest parent2 = new Quest();
        parent2.setName("Parent 2");
        parent2.setWorkspaceId(1);
        Quest parent3 = new Quest();
        parent3.setName("Parent 3");
        parent3.setWorkspaceId(1);

        Quest child1Par1 = new Quest();
        child1Par1.setName("Child 1 of Parent 1 and Parent 2");
        child1Par1.setWorkspaceId(1);
        Quest child2Par1 = new Quest();
        child2Par1.setName("Child 2 of Paren 1");
        child2Par1.setWorkspaceId(1);

        Quest child1Child1 = new Quest();
        child1Child1.setName("Child 1 of Child 1");
        child1Child1.setWorkspaceId(1);
        Quest child2Child1 = new Quest();
        child2Child1.setName("Child 2 of Child 1");
        child2Child1.setWorkspaceId(1);

        Quest child1Child2 = new Quest();
        child1Child2.setName("Child 1 of Child 2");
        child1Child2.setWorkspaceId(1);

        parent1.getChildren().add(child1Par1);
        parent2.getChildren().add(child1Par1);

        child1Par1.getChildren().add(child1Child1);
        child1Par1.getChildren().add(child2Child1);
        child2Par1.getChildren().add(child1Child2);

        long id = DbManager.getInstance().addQuest(parent1);
        parent1.setId(id);
        id = DbManager.getInstance().addQuest(parent2);
        parent2.setId(id);
        id = DbManager.getInstance().addQuest(parent3);
        parent3.setId(id);

        id = DbManager.getInstance().addQuest(child1Par1, parent1.getId(), parent2.getId());
        child1Par1.setId(id);
        id = DbManager.getInstance().addQuest(child2Par1, parent1.getId());
        child2Par1.setId(id);

        id = DbManager.getInstance().addQuest(child1Child1, child1Par1.getId());
        child1Child1.setId(id);
        id = DbManager.getInstance().addQuest(child2Child1, child1Par1.getId());
        child2Child1.setId(id);
        id = DbManager.getInstance().addQuest(child1Child2, child2Par1.getId());
        child1Child2.setId(id);
    }
     **/
}
