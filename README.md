# Updater
A tool that use github or gitee to release latest software.
### Now Support  JavaScript and Java Languages.

## The Rules

1. Must to put it in the `root` directory.

2. Must contain all the release-file and the release-notes.txt

3. Release file rules is:

    release-file: `[file-name]-v[version-code].[suffix]`

    such as: `TestApp-v2.3.apk`

    release-notes.txt: `[file-name]-v[version-code].txt`

    such as: `TestApp-Notes-v2.3.txt`

4. Prameter rules is:

   `getUpdate/getUpdateGitee(ownerName, repositoryName, currentVersionCode)`
   
   such as: see the `How to use them` below.

## How to use them
1. In JavaScript
```javascript
    // import the updater.js file
    <script src="./updater.js"></script>
    <script>
      let msgObj1 = getUpdate("aizichen", "upTest", 1.1);
      if (msgObj1 != null) {
          alert("发现新版本：\n" + JSON.stringify(msgObj1));
      }
      let msgObj2 = getUpdateGitee("quanyec", "upTest", 3.2);
      if (msgObj2 != null) {
          alert("发现新版本：\n" + JSON.stringify(msgObj2));
      }
    </script>
````
2. In Java
```java
    // need to import the `gson` library.
    UpdateMsg uMsg = Updater.getUpdate("aizichen", "upTest", 1.1);
    System.out.println(uMsg);
    uMsg = Updater.getUpdateGitee("quanyec", "upTest", 3.2);
    System.out.println(uMsg);
```
