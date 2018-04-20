package ru.zuma.ikrafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.zuma.ikrafinal.db.DbManager;
import ru.zuma.ikrafinal.model.Workspace;

public class AddWorkspaceActivity extends AppCompatActivity {
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workspace);

        etName = findViewById(R.id.et_workspace_name);

        Button button = findViewById(R.id.bt_workspace_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etName.getText().equals("")) {

                    Toast.makeText(
                        AddWorkspaceActivity.this,
                        "Одно из полей пустое",
                        Toast.LENGTH_SHORT
                    ).show();

                } else {

                    Workspace workspace = new Workspace();
                    workspace.setName(etName.getText().toString());
                    long workspaceId = DbManager.getInstance().addWorkspace(workspace);

                    Intent intent = new Intent();
                    intent.putExtra("workspaceId", workspaceId);
                    setResult(RESULT_OK, intent);
                    finish();

                }

            }
        });
    }
}
