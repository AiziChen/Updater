/**
 * 获取版最新版本的下载链接。若无最新版本，则返回null
 * @author Quanyec
 * @param {string} ownerName github用户名
 * @param {string} repoName 仓库名
 * @param {number} currentVersionCode 当前应用版本号，纯数字。如：1.1
 */
// export default
function getUpdate(ownerName, repoName, currentVersionCode) {
    let UpdateMsg = {};

    let respData = getData("https://api.github.com/repos/" + ownerName + "/" + repoName + "/contents");
    if (respData == null) {
        return null;
    }
    let data = JSON.parse(respData);
    for (let i = 0; i < data.length; i++) {
        let file = data[i];
        let fileName = file.name.toLocaleLowerCase();
        if (file.type == "file" &&
                fileName.lastIndexOf("-v") != -1) {
            let versonNumber = fileName.substring(fileName.lastIndexOf("-v") + 2
                        , fileName.lastIndexOf("."));
            versonNumber = Number.parseFloat(versonNumber);
            if (versonNumber > currentVersionCode) {
                if (fileName.endsWith(".txt")) {
                    UpdateMsg["msgUrl"] = file.url;
                } else {
                    UpdateMsg["binUrl"] = file.url;
                }
            }
        }
    }
    return UpdateMsg == {} ? null : UpdateMsg;
}

/**
 * 码云版本
 * @param {string} ownerName 
 * @param {string} repoName 
 * @param {number} currentVersionCode 
 */
// export default
function getUpdateGitee(ownerName, repoName, currentVersionCode) {
    let UpdateMsg = {};

    let respData = getData("https://gitee.com/api/v5/repos/" + ownerName + "/" + repoName + "/git/trees/master");
    let downloadUrl = "https://gitee.com/quanyec/" + repoName + "/raw/master/";
    if (respData == null) {
        return null;
    }
    let data = JSON.parse(respData).tree;
    for (let i = 0; i < data.length; i++) {
        let file = data[i];
        let filePath = file.path.toLocaleLowerCase();
        if (file.type == "blob" &&
                filePath.lastIndexOf("-v") != -1) {
            let versonNumber = filePath.substring(filePath.lastIndexOf("-v") + 2
                        , filePath.lastIndexOf("."));
            versonNumber = Number.parseFloat(versonNumber);
            if (versonNumber > currentVersionCode) {
                if (filePath.endsWith(".txt")) {
                    UpdateMsg["msgUrl"] = downloadUrl + file.path;
                } else {
                    UpdateMsg["binUrl"] = downloadUrl + file.path;
                }
            }
        }
    }
    return UpdateMsg == {} ? null : UpdateMsg;
}

/**
 * 请求url并返回结果
 * @param {string} requestUrl 请求的url
 */
function getData(requestUrl) {
    let result = null;
    let req = new XMLHttpRequest();
    req.onreadystatechange = (event) => {
        if (req.readyState == 4 && req.status == 200) {
            result = req.response;
        }
    };
    req.open("GET", requestUrl, false);
    req.send();
    return result;
}