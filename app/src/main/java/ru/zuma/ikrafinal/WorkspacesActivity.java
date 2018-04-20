package ru.zuma.ikrafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ru.zuma.ikrafinal.db.DbManager;
import ru.zuma.ikrafinal.model.Workspace;

public class WorkspacesActivity extends AppCompatActivity {

    List<Workspace> listWorkspaces;
    WorkspaceAdapter adapterWorkspaces;
    ListView lvWorkspaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workspaces);

        listWorkspaces = DbManager.getInstance().getWorkspaces();

        lvWorkspaces = findViewById(R.id.lv_workspaces);
        adapterWorkspaces = new WorkspaceAdapter(this, listWorkspaces);

        lvWorkspaces.setAdapter(adapterWorkspaces);
        lvWorkspaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(WorkspacesActivity.this, QuestsActivity.class);
                Workspace workspace = listWorkspaces.get(position);
                intent.putExtra("workspaceId", workspace.getId());
                startActivity(intent);

            }
        });
    }
}
