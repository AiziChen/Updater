package org.coqur.updater;

import com.google.gson.Gson;
import org.coqur.updater.common.Updater;
import org.coqur.updater.exception.InvalidVersionCodeException;
import org.coqur.updater.record.GitEEContent;
import org.coqur.updater.record.GitEETreeItem;
import org.coqur.updater.record.UpdateInfo;
import org.coqur.updater.tools.$;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;

/**
 * Updater for github service
 *
 * @author Quanyec
 */
public class UpdaterForGitEE implements Updater {
    public static final String GITEE_REPO_API_SUFFIX = "https://gitee.com/api/v5/repos/";
    public static final String GITEE_REPO_DOWNLOAD_SUFFIX = "https://gitee.com/";
    private final static Gson g = new Gson();

    @Override
    public UpdateInfo checkUpdate(@NotNull String ownerName, @NotNull String repoName, @NotNull String currentVersion, @Nullable String dir, @NotNull String fileName) {
        String url = GITEE_REPO_API_SUFFIX + ownerName + "/" + repoName + "/git/trees/master";
        String downloadUrl = GITEE_REPO_DOWNLOAD_SUFFIX + ownerName + "/" + repoName + "/raw/master/";
        if (dir != null && !dir.isEmpty()) {
            try {
                String resp = $.readUrlText(url);
                List<GitEETreeItem> items = g.fromJson(resp, GitEEContent.class).getTree();
                for (GitEETreeItem item : items) {
                    if (item.getType().equals("tree") && item.getPath().equals(dir)) {
                        url = item.getUrl();
                        String path = item.getPath();
                        downloadUrl =  downloadUrl + path;
                    }
                }
            } catch (IOException e) {
                return null;
            }
        }

        UpdateInfo info = new UpdateInfo();
        try {
            String resp = $.readUrlText(url);
            List<GitEETreeItem> items = g.fromJson(resp, GitEEContent.class).getTree();
            for (GitEETreeItem item : items) {
                if (item.getType().equals("blob")) {
                    String name = item.getPath();
                    if (name.startsWith(fileName)) {
                        try {
                            String v = name.substring(name.indexOf('-') + 1, name.lastIndexOf('.'));
                            if ($.isBiggerVersion(v, currentVersion)) {
                                info.setUpgradeUrl(downloadUrl + "/" + item.getPath());
                            }
                        } catch (InvalidVersionCodeException | RuntimeException e) {
                            return null;
                        }
                    } else if (name.startsWith(COMMENT_NAME)) {
                        info.setCommentUrl(downloadUrl + "/" + item.getPath());
                    }
                }
            }
        } catch (IOException e) {
            return null;
        }

        return info.getCommentUrl() != null && info.getUpgradeUrl() != null
                ? info : null;
    }
}
