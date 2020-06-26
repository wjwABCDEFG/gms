package com.gms.commons.domain;

import java.util.List;

public class MainMenu {
    private int id;
    private String title;
    private String path;
    private List<SubMenu> submenulist;

    public MainMenu() {
    }

    public MainMenu(String title, String path, List<SubMenu> submenulist) {
        this.title = title;
        this.path = path;
        this.submenulist = submenulist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<SubMenu> getSubmenulist() {
        return submenulist;
    }

    public void setSubmenulist(List<SubMenu> submenulist) {
        this.submenulist = submenulist;
    }

    @Override
    public String toString() {
        return "MainMenu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", submenulist=" + submenulist +
                '}';
    }
}
