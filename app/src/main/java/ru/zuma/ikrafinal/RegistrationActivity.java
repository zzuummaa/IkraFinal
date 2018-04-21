package ru.zuma.ikrafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.zuma.ikrafinal.db.AppDatabase;
import ru.zuma.ikrafinal.db.DbManager;
import ru.zuma.ikrafinal.model.AchievementType;
import ru.zuma.ikrafinal.model.Achievment;
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
            setResult(RESULT_OK);
            finish();
        } else {
            create();
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }

    private void create() {
//        Workspace workspace = new Workspace();
//        workspace.setName("Кухня");
//        workspace = DbManager.getInstance().addWorkspace(workspace);
//        long workspaceId = workspace.getId();
//
//        Quest parent1 = new Quest(false);
//        parent1.setName("Сделать коктейль");
//        parent1.setWorkspaceId(workspaceId);
//
//        Quest child1Par1 = new Quest(false);
//        child1Par1.setName("Купить яишницу");
//        child1Par1.setWorkspaceId(workspaceId);
//        Quest child2Par1 = new Quest(false);
//        child2Par1.setName("Купить мороженое");
//        child2Par1.setWorkspaceId(workspaceId);
//
//
//        parent1.getChildren().add(child1Par1);
//        parent1.getChildren().add(child2Par1);
//
//
//        long id = DbManager.getInstance().addQuest(parent1, workspace.getRootQuestId());
//        parent1.setId(id);
//
//        id = DbManager.getInstance().addQuest(child1Par1, parent1.getId());
//        child1Par1.setId(id);
//        id = DbManager.getInstance().addQuest(child2Par1, parent1.getId());
//        child2Par1.setId(id);

        Achievment achievment = new Achievment();
        achievment.setType(AchievementType.FIRST_BLOOD);
        DbManager.getInstance().addAchievment(achievment);
        achievment = new Achievment();
        achievment.setType(AchievementType.MONEY_MAKER);
        DbManager.getInstance().addAchievment(achievment);
        achievment = new Achievment();
        achievment.setType(AchievementType.THE_KING);
        DbManager.getInstance().addAchievment(achievment);
        achievment = new Achievment();
        achievment.setType(AchievementType.UNSTOPABLE);
        DbManager.getInstance().addAchievment(achievment);
    }
}
