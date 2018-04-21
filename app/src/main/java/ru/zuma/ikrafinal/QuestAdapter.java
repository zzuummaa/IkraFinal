package ru.zuma.ikrafinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import ru.zuma.ikrafinal.db.DbManager;
import ru.zuma.ikrafinal.mechanics.QuestMechanics;
import ru.zuma.ikrafinal.model.Quest;

/**
 * Created by Stephan on 20.04.2018.
 */

public class QuestAdapter extends BaseAdapter{
    Context ctx;
    LayoutInflater lInflater;
    List<Quest> objects;

    QuestAdapter(Context context, List<Quest> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.quest_item, parent, false);
        }

        final Quest p = getQuest(position);//Govno

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.questName)).setText(p.getName());
        ((TextView) view.findViewById(R.id.questBrief)).setText(p.getDescription());
        ((CheckBox) view.findViewById(R.id.questState)).setChecked(p.isCompleted());
        ((CheckBox) view.findViewById(R.id.questState)).setEnabled(!p.isCompleted());
        ((CheckBox) view.findViewById(R.id.questState)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (p.isCompleted()) {
                    return;
                }
                boolean isCompleted = QuestMechanics.completeQuest(p);
                if (isCompleted) {
                    ((CheckBox) view).setChecked(true);
                    ((CheckBox) view).setEnabled(false);
                    DbManager.getInstance().updateQuest(p);
                    QuestMechanics.calculateAchievement(p.getWorkspaceId());
                } else {
                    ((CheckBox) view).setChecked(false);
                }
            }
        });
        if (p.getChildren().size()>0)
            ((TextView) view.findViewById(R.id.questContext)).setText("> ");
        else
            ((TextView) view.findViewById(R.id.questContext)).setText("- ");
        return view;
    }

    // товар по позиции
    Quest getQuest(int position) {
        return ((Quest) getItem(position));
    }
}
