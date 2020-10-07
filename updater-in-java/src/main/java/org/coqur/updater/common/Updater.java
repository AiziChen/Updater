package org.coqur.updater.common;

import org.coqur.updater.record.UpdateInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Updater {

    String COMMENT_NAME = "comment";

    /**
     * Check for update
     * File Type: [name]-[version].suffix
     *
     * @return If has a new version, return the `UpdateInfo` instance, otherwise the `null` value
     */
    UpdateInfo checkUpdate(@NotNull String ownerName, @NotNull String repoName, @NotNull String currentVersion, @Nullable String dir, @NotNull String fileName);
}
