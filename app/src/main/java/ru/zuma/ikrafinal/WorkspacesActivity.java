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

public class WorkspacesActivity extends AppCompatActivity {

    List<String> listWorkspaces;
    ArrayAdapter<String> adapterWorkspaces;
    ListView lvWorkspaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workspaces);

        listWorkspaces = new ArrayList<>();
        listWorkspaces.add("Проект 1");
        listWorkspaces.add("Проект 2");

        lvWorkspaces = findViewById(R.id.lv_workspaces);
        adapterWorkspaces = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listWorkspaces);

        lvWorkspaces.setAdapter(adapterWorkspaces);
        lvWorkspaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(WorkspacesActivity.this, QuestsActivity.class);
                intent.putExtra("workspaceId", position);
                startActivity(intent);

            }
        });
    }
}
