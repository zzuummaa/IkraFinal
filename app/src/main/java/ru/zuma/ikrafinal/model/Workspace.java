package ru.zuma.ikrafinal.model;

/**
 * Created by sibirsky on 20.04.18.
 */

public class Workspace {

    private long id;
    private long rootQuestId;
    private String name;

    public Workspace()
    {
    }

    public Workspace(long id, long rootQuestId, String name) {
        this.id = id;
        this.rootQuestId = rootQuestId;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRootQuestId() {
        return rootQuestId;
    }

    public void setRootQuestId(long rootQuestId) {
        this.rootQuestId = rootQuestId;
    }
}
