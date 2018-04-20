package ru.zuma.ikrafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.zuma.ikrafinal.db.AppDatabase;
import ru.zuma.ikrafinal.db.DbManager;
import ru.zuma.ikrafinal.model.User;

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
                user.setPassword(nameEdit.getText().toString());
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
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED, new Intent());
        finish();
    }
}
