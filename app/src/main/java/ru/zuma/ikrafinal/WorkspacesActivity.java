package ru.zuma.ikrafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

import ru.zuma.ikrafinal.db.DbManager;
import ru.zuma.ikrafinal.model.Workspace;

public class WorkspacesActivity extends AppCompatActivity {
    static int ADD_WORKSPACE_RESULT = 0;

    List<Workspace> listWorkspaces;
    WorkspaceAdapter adapterWorkspaces;
    ListView lvWorkspaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workspaces);
        MainActionBar.addActionBar(this);

        listWorkspaces = DbManager.getInstance().getWorkspaces();

        lvWorkspaces = findViewById(R.id.lv_workspaces);
        adapterWorkspaces = new WorkspaceAdapter(this, listWorkspaces);

        lvWorkspaces.setAdapter(adapterWorkspaces);
        lvWorkspaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(WorkspacesActivity.this, QuestsGraphActivity.class);
                Workspace workspace = listWorkspaces.get(position);
                intent.putExtra("workspaceId", workspace.getId());
                startActivity(intent);

            }
        });

        ImageButton btAddWorkspace = (ImageButton) findViewById(R.id.bt_add_workspace);
        btAddWorkspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkspacesActivity.this, AddWorkspaceActivity.class);
                startActivityForResult(intent, ADD_WORKSPACE_RESULT);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_WORKSPACE_RESULT && resultCode == RESULT_OK) {

            long workspaceId = data.getLongExtra("workspaceId", -1);
            if (workspaceId != -1) {

                // TODO get single workspace from db by workspaceId
                //List<Workspace> workspaces = DbManager.getInstance().getWorkspaces();
                Workspace workspace = DbManager.getInstance().getWorkspace(workspaceId);
                listWorkspaces.add(workspace);
                adapterWorkspaces.notifyDataSetChanged();

            }

        }
    }
}
