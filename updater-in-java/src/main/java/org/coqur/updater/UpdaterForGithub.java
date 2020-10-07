package org.coqur.updater;

import com.google.gson.Gson;
import org.coqur.updater.common.Updater;
import org.coqur.updater.exception.InvalidVersionCodeException;
import org.coqur.updater.record.GithubContent;
import org.coqur.updater.record.UpdateInfo;
import org.coqur.updater.tools.$;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * Updater for github service
 *
 * @author Quanyec
 */
public class UpdaterForGithub implements Updater {
    public static final String GITHUB_REPO_API_SUFFIX = "https://api.github.com/repos/";
    private final static Gson g = new Gson();

    @Override
    public UpdateInfo checkUpdate(@NotNull String ownerName, @NotNull String repoName, @NotNull String currentVersion, @Nullable String dir, @NotNull String fileName) {
        String url = GITHUB_REPO_API_SUFFIX + ownerName + "/" + repoName + "/contents";

        if (dir != null && !dir.isEmpty()) {
            try {
                String resp = $.readUrlText(url);
                GithubContent[] contents = g.fromJson(resp, GithubContent[].class);
                for (GithubContent gc : contents) {
                    if (gc.getType().equals("dir") && gc.getName().equals(dir)) {
                        url = gc.getUrl();
                    }
                }
            } catch (IOException e) {
                return null;
            }
        }

        UpdateInfo info = new UpdateInfo();
        try {
            String resp = $.readUrlText(url);
            GithubContent[] contents = g.fromJson(resp, GithubContent[].class);
            for (GithubContent gc : contents) {
                if (gc.getType().equals("file")) {
                    String name = gc.getName();
                    if (name.startsWith(fileName)) {
                        try {
                            String v = name.substring(name.indexOf('-') + 1, name.lastIndexOf('.'));
                            if ($.isBiggerVersion(v, currentVersion)) {
                                info.setUpgradeUrl(gc.getDownload_url());
                            }
                        } catch (InvalidVersionCodeException | RuntimeException e) {
                            return null;
                        }
                    } else if (name.startsWith(COMMENT_NAME)) {
                        info.setCommentUrl(gc.getDownload_url());
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
