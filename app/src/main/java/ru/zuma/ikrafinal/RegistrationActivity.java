package ru.zuma.ikrafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.zuma.ikrafinal.db.AppDatabase;
import ru.zuma.ikrafinal.db.DbManager;
import ru.zuma.ikrafinal.model.Quest;
import ru.zuma.ikrafinal.model.User;
import ru.zuma.ikrafinal.model.Workspace;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button button = findViewById(R.id.bt_register);
        final EditText nameEdit = findViewById(R.id.et_login);
        final EditText passEdit = findViewById(R.id.et_pass);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setName(nameEdit.getText().toString());
                user.setPassword(passEdit.getText().toString());
                DbManager.getInstance().addUser(user);
                setResult(RESULT_OK, new Intent());
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        User user = DbManager.getInstance().getUser();
        if (user != null) {
            setResult(RESULT_OK, new Intent());
            finish();
        } else {
            create();
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED, new Intent());
        finish();
    }

    private void create() {
        Workspace workspace = new Workspace();
        workspace.setName("First project!");
        long workspaceId = DbManager.getInstance().addWorkspace(workspace);
        workspace.setId(workspaceId);

        Quest parent1 = new Quest();
        parent1.setName("Parent 1");
        parent1.setWorkspaceId(workspaceId);
        Quest parent2 = new Quest();
        parent2.setName("Parent 2");
        parent2.setWorkspaceId(workspaceId);
        Quest parent3 = new Quest();
        parent3.setName("Parent 3");
        parent3.setWorkspaceId(workspaceId);

        Quest child1Par1 = new Quest();
        child1Par1.setName("Child 1 of Parent 1 and Parent 2");
        child1Par1.setWorkspaceId(workspaceId);
        Quest child2Par1 = new Quest();
        child2Par1.setName("Child 2 of Paren 1");
        child2Par1.setWorkspaceId(workspaceId);

        Quest child1Child1 = new Quest();
        child1Child1.setName("Child 1 of Child 1");
        child1Child1.setWorkspaceId(workspaceId);
        Quest child2Child1 = new Quest();
        child2Child1.setName("Child 2 of Child 1");
        child2Child1.setWorkspaceId(workspaceId);

        Quest child1Child2 = new Quest();
        child1Child2.setName("Child 1 of Child 2");
        child1Child2.setWorkspaceId(workspaceId);

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
}
