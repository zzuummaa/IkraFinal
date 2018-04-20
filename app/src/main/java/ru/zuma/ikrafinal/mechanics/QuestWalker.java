package ru.zuma.ikrafinal.mechanics;

import java.util.Stack;

import ru.zuma.ikrafinal.model.Quest;

/**
 * Created by sibirsky on 20.04.18.
 */

public class QuestWalker {

    private Quest root;

    private Quest currentNode;
    private Stack<Quest> walkStack = new Stack<>();

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
                walkStack.push(currentNode);
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
        currentNode = walkStack.pop();
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
}
