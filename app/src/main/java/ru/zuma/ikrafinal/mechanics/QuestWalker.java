package ru.zuma.ikrafinal.mechanics;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

import ru.zuma.ikrafinal.db.dataset.QuestDataSet;
import ru.zuma.ikrafinal.model.Quest;

/**
 * Created by sibirsky on 20.04.18.
 */

public class QuestWalker {

    private Quest root;

    private Quest currentNode;
    private Deque<Quest> walkStack = new ArrayDeque<>();

    public QuestWalker(Quest root) {
        this.root = root;
        this.currentNode = this.root;
    }

    public Quest getCurrentNode() {
        return currentNode;
    }

    public boolean walkToChild(final long childId) {
        for (Quest child : currentNode.getChildren()) {
            if (child.getId() == childId) {
                walkStack.addFirst(currentNode);
                currentNode = child;
                return true;
            }
        }
        return false;
    }

    public boolean walkToParent() {
        if (walkStack.isEmpty()) {
            return false;
        }
        currentNode = walkStack.pollFirst();
        return true;
    }

    public boolean isChildLeaf(long childId) {
        for (Quest child : currentNode.getChildren()) {
            if (child.getId() == childId) {
                return child.getChildren().isEmpty();
            }
        }
        return true;
    }


    public void rewalk(Quest newRoot) {
        this.root = newRoot;
        Quest newCurrent = this.root;
        int count = 0;
        while (count != walkStack.size()-1) {
            Quest step = walkStack.peekLast();
            for (Quest child : newCurrent.getChildren()) {
                if (child.getId() == step.getId()) {
                    newCurrent = child;
                }
            }
            count++;
        }
        for (Quest child : newCurrent.getChildren()) {
            if (child.getId() == currentNode.getId()) {
                newCurrent = child;
            }
        }
        this.currentNode = newCurrent;
    }
}
