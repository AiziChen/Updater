package org.coqur.updater.record;

public class GithubContent {
    private String name;
    // private String path;
    // private String sha;
    // private long size;
    private String url;
    // private String html_url;
    // private String git_url;
    private String download_url;
    private String type;
    // private GithubLinks _links;

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    @Override
    public String toString() {
        return "GithubContent{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", download_url='" + download_url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
