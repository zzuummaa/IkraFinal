package ru.zuma.ikrafinal.mechanics;

import ru.zuma.ikrafinal.model.Quest;

/**
 * Created by sibirsky on 21.04.18.
 */

public final class QuestMechanics {

    public static boolean completeQuest(Quest quest) {
        for (Quest child : quest.getChildren()) {
            if (child.isMandatory() && !child.isCompleted()) {
                quest.setCompleted(false);
                return false;
            }
        }
        quest.setCompleted(true);
        return true;
    }

    //public void linkQuest(Quest parent, )
}
