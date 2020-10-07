import org.coqur.updater.UpdaterForGitEE;
import org.coqur.updater.UpdaterForGithub;
import org.coqur.updater.record.UpdateInfo;
import org.junit.jupiter.api.Test;

public class UpdateTest {

    /**
     * github 更新的使用例子
     * 测试的 GitHub仓库 地址为: https://github.com/AiziChen/upgradeList.git
     */
    @Test
    public void githubUpdateTest() {
        UpdaterForGithub up1 = new UpdaterForGithub();
        // `comment`或`fileName`文件两者必需同时存在于仓库中，详情查看本测试仓库地址：https://github.com/AiziChen/upgradeList.git
        UpdateInfo info = up1.checkUpdate("AiziChen", "upgradeList", "v1.1", "upTest", "myApp");
        // 若info为null，则表示未找到新版本，或者是在github仓库中缺少`comment`或`fileName`文件。
        if (info != null) {
            System.out.println("commentUrl: " + info.getCommentUrl());
            System.out.println("upgradeUrl: " + info.getUpgradeUrl());
        }
    }

    /**
     * gitEE 更新的使用例子
     * 测试的 GitHub仓库 地址为: https://gitee.com/quanyec/upgrade-list.git
     * 注：当以 gitEE 作为软件更新仓库提交新版本的工具时，其 branch 必须为 `master`
     */
    @Test
    public void gitEEUpdateTest() {
        UpdaterForGitEE up1 = new UpdaterForGitEE();
        // `comment`或`fileName`文件两者必需同时存在于仓库中，详情查看本测试仓库地址：https://gitee.com/quanyec/upgrade-list.git
        UpdateInfo info = up1.checkUpdate("quanyec", "upgrade-list", "v1.1", "upTest", "myApp");
        // 若info为null，则表示未找到新版本，或者是在gitEE仓库中缺少`comment`或`fileName`文件。
        if (info != null) {
            System.out.println("commentUrl: " + info.getCommentUrl());
            System.out.println("upgradeUrl: " + info.getUpgradeUrl());
        }
    }
}
