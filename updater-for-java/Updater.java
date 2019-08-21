import java.io.*;
import java.util.*;
import java.net.*;
import com.google.gson.*;

/**
 * 获取版最新版本的下载链接。若无最新版本，则返回null
 * @author Quanyec
 */
public class Updater {
	private static Gson gson = new GsonBuilder().create();
    public static UpdateMsg getUpdate(String ownerName, String repoName, double currentVersionCode) {
    	UpdateMsg uMsg = new UpdateMsg();
    	String urlStr = "https://api.github.com/repos/" + ownerName + "/" + repoName + "/contents";
    	String respJson = readUrlText(urlStr);
    	if (respJson == null) {
    		throw new RuntimeException("请求错误。");
    	}
    	GithubContent[] contents = gson.fromJson(respJson, GithubContent[].class);
        for (int i = 0; i < contents.length; ++i) {
            GithubContent content = contents[i];
            String url = content.getUrl();
            String name = content.getName().toLowerCase();
            String type = content.getType();

            if (type.equals("file") && name.contains("-v")) {
                String versionStr = name.substring(name.indexOf("-v") + 2, name.lastIndexOf("."));
                double versionNumber = Double.parseDouble(versionStr);
                if (versionNumber > currentVersionCode) {
                    if (name.endsWith(".txt")) {
                        uMsg.setMsgUrl(url);
                    } else {
                        uMsg.setBinUrl(url);
                    }
                }
            }
        }
        if (uMsg.getMsgUrl() == null || uMsg.getBinUrl() == null) {
            return null;
        } else {
            return uMsg;
        }
    }

    public static UpdateMsg getUpdateGitee(String ownerName, String repoName, double currentVersionCode) {
        UpdateMsg uMsg = new UpdateMsg();
        String urlStr = "https://gitee.com/api/v5/repos/" + ownerName + "/" + repoName + "/git/trees/master";
        String downloadUrl = "https://gitee.com/quanyec/" + repoName + "/raw/master/";
        String respJson = readUrlText(urlStr);
        if (respJson == null) {
            throw new RuntimeException("请求错误。");
        }
        GiteeContent content = gson.fromJson(respJson, GiteeContent.class);
        GiteeTree[] tree = content.getTree();
        for (int i = 0; i < tree.length; ++i) {
            GiteeTree node = tree[i];
            String filePathLowerCase = node.getPath().toLowerCase();
            String filePath = node.getPath();
            String type = node.getType();

            if (type.equals("blob") && filePathLowerCase.contains("-v")) {
                String versionStr = filePathLowerCase.substring(filePathLowerCase.indexOf("-v") + 2, filePathLowerCase.lastIndexOf("."));
                double versionNumber = Double.parseDouble(versionStr);
                if (versionNumber > currentVersionCode) {
                    if (filePathLowerCase.endsWith(".txt")) {
                        uMsg.setMsgUrl(downloadUrl + filePath);
                    } else {
                        uMsg.setBinUrl(downloadUrl + filePath);
                    }
                }
            }
        }
        if (uMsg.getMsgUrl() == null || uMsg.getBinUrl() == null) {
            return null;
        } else {
            return uMsg;
        }
    }

    private static String readUrlText(String urlStr) {
    	try {
	    	URL url = new URL(urlStr);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setConnectTimeout(5 * 1000);
	        conn.setRequestMethod("GET");
	        conn.addRequestProperty("Connection", "Keep-Alive");
	        conn.addRequestProperty("Content-Type", "*/*");
	        conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
	        conn.connect();

	        StringBuilder sb = new StringBuilder();
	        InputStream is = conn.getInputStream();
	        byte[] buff = new byte[256];
	        int hasRead = -1;
	        while ((hasRead = is.read(buff)) != -1) {
	        	sb.append(new String(buff, 0, hasRead));
	        }
	        return sb.toString();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}