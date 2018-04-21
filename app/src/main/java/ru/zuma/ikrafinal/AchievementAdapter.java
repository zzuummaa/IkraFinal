package ru.zuma.ikrafinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.zuma.ikrafinal.model.Achievment;
import ru.zuma.ikrafinal.model.Quest;

/**
 * Created by Stephan on 21.04.2018.
 */

public class AchievementAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<Achievment> objects;

    AchievementAdapter(Context context, List<Achievment> products) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item_achievement, parent, false);
        }

        Achievment p = getQuest(position);

        String name = p.getWorkspaceName() ==  null ? "Не раскрыто" : p.getWorkspaceName();
        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.achievement_name)).setText( String.valueOf(p.resolveName()) );
        ((TextView) view.findViewById(R.id.achievement_workspace)).setText( name );

        return view;
    }

    // товар по позиции
    Achievment getQuest(int position) {
        return ((Achievment) getItem(position));
    }
}
