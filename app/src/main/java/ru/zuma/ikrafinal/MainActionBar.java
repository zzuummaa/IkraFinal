package ru.zuma.ikrafinal;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Badgeable;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

public class MainActionBar {
    static final int ITEM_ID_WORKSPACES = 0;
    static final int ITEM_ID_QUESTGRAPH = 1;
    static final int ITEM_ID_QUESTLIST =  2;

    static final String LOG_TAG = MainActivity.class.getName();
    //private Drawer.Result drawerResult = null;

    public static Drawer.Result addActionBar(final AppCompatActivity activity) {

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        //activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Drawer.Result[] drawerResult = new Drawer.Result[1];

        drawerResult[0] = new Drawer()
            .withActivity(activity)
            .withToolbar(toolbar)
            .withActionBarDrawerToggle(true)
            .withHeader(R.layout.drawer_header)
            .addDrawerItems(
                    new PrimaryDrawerItem().withName(R.string.quests).withIcon(FontAwesome.Icon.faw_home).withBadge("123").withIdentifier(1),
                    new PrimaryDrawerItem().withName(R.string.categories).withIcon(FontAwesome.Icon.faw_gamepad),
                    new PrimaryDrawerItem().withName(R.string.tasks).withIcon(FontAwesome.Icon.faw_eye).withBadge("6").withIdentifier(2),
                    new PrimaryDrawerItem().withName(R.string.today).withIcon(FontAwesome.Icon.faw_trash_o),
                    new PrimaryDrawerItem().withName(R.string.achievements).withIcon(FontAwesome.Icon.faw_gamepad),
                    new DividerDrawerItem(),
                    new SecondaryDrawerItem().withName(R.string.search).withIcon(FontAwesome.Icon.faw_search),
                    new SecondaryDrawerItem().withName(R.string.settings).withIcon(FontAwesome.Icon.faw_cog)
            )
            /*
            .withOnDrawerListener(new Drawer.OnDrawerListener() {
                @Override
                public void onDrawerOpened(View drawerView) {
                    // Скрываем клавиатуру при открытии Navigation Drawer
                    InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                }
            })*/
            .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                @Override
                // Обработка клика
                public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                    if (drawerItem instanceof Nameable) {
                        onNameableClick(activity, (Nameable)drawerItem);
                    }
                    if (drawerItem instanceof Badgeable) {
                        Badgeable badgeable = (Badgeable)drawerItem;
                        onBadgeableClick(activity, badgeable, drawerResult[0], position);
                    }
                }
            })
            .build();

        return drawerResult[0];
    }

    public static void onBadgeableClick(AppCompatActivity activity,Badgeable badgeable,
                                        Drawer.Result drawerResult, int position) {

        if (badgeable.getBadge() != null) {
            // учтите, не делайте так, если ваш бейдж содержит символ "+"
            try {
                int badge = Integer.valueOf(badgeable.getBadge());
                if (badge > 0) {
                    drawerResult.updateBadge(String.valueOf(badge - 1), position);
                }
            } catch (Exception e) {
                Log.d(LOG_TAG, "Не нажимайте на бейдж, содержащий плюс! :)");
            }
        }

    }

    public static void onNameableClick(AppCompatActivity activity, Nameable numeable) {

        Toast.makeText(
            activity,
            activity.getString(numeable.getNameRes()),
            Toast.LENGTH_SHORT
        ).show();

    }
    /*
    @Override
    public void onBackPressed() {
        // Закрываем Navigation Drawer по нажатию системной кнопки "Назад" если он открыт
        if (drawerResult.isDrawerOpen()) {
            drawerResult.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
    */
}
