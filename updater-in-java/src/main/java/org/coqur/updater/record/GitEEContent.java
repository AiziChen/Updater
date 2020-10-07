package org.coqur.updater.record;

import java.util.List;

public class GitEEContent {
    private String url;
    private String content;
    private List<GitEETreeItem> tree;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<GitEETreeItem> getTree() {
        return tree;
    }

    public void setTree(List<GitEETreeItem> tree) {
        this.tree = tree;
    }
}

