# Updater
A tool that use github or gitee to release latest software.
### Now Support  JavaScript and Java Languages.

## How to use
1. In JavaScript
```javascript
    <script src="./updater.js"></script>
    <script>
      let msgObj = getUpdateGitee("quanyec", "upTest", 1.1);
      if (msgObj != null) {
          alert("发现新版本：\n" + JSON.stringify(msgObj));
      }
    </script>
````
2. In Java
```java
    UpdateMsg uMsg = Updater.getUpdate("aizichen", "AkNote", 1.1);
    System.out.println(uMsg);
    uMsg = Updater.getUpdateGitee("quanyec", "upTest", 3.2);
    System.out.println(uMsg);
```
