package org.coqur.updater.record;

/**
 * Update Response
 *
 * @author Quanyec
 */
public class UpdateInfo {
    // version's information
    private String commentUrl;
    // destination's url
    private String upgradeUrl;

    public UpdateInfo() {
    }

    public UpdateInfo(String commentUrl, String upgradeUrl) {
        this.commentUrl = commentUrl;
        this.upgradeUrl = upgradeUrl;
    }

    public String getCommentUrl() {
        return commentUrl;
    }

    public void setCommentUrl(String commentUrl) {
        this.commentUrl = commentUrl;
    }

    public String getUpgradeUrl() {
        return upgradeUrl;
    }

    public void setUpgradeUrl(String upgradeUrl) {
        this.upgradeUrl = upgradeUrl;
    }

    @Override
    public String toString() {
        return "UpdateResp{" +
                "commentUrl='" + commentUrl + '\'' +
                ", upgradeUrl='" + upgradeUrl + '\'' +
                '}';
    }
}
