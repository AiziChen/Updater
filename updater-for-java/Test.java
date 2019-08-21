/**
 * 测试类
 * @author Quanyec
 */
public class Test {
    public static void main(String[] args) {
        UpdateMsg uMsg = Updater.getUpdate("aizichen", "AkNote", 1.1);
        System.out.println(uMsg);
        uMsg = Updater.getUpdateGitee("quanyec", "upTest", 3.2);
        System.out.println(uMsg);
    }
}