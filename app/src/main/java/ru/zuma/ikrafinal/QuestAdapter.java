package ru.zuma.ikrafinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.zuma.ikrafinal.model.Quest;
import ru.zuma.ikrafinal.model.Workspace;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        Quest p = getQuest(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(android.R.id.text1)).setText(p.getName());

        return view;
    }

    // товар по позиции
    Quest getQuest(int position) {
        return ((Quest) getItem(position));
    }
}
