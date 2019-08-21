# Updater
A tool that use github or gitee to release latest software.
### Now Support  JavaScript and Java Languages.

## How to use
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
    UpdateMsg uMsg = Updater.getUpdate("aizichen", "upTest", 1.1);
    System.out.println(uMsg);
    uMsg = Updater.getUpdateGitee("quanyec", "upTest", 3.2);
    System.out.println(uMsg);
```
