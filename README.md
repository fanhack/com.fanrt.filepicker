
# 注意事项

1.修改 Filepicker.java,FileListAdapter.java,FilePickerDialog.java,FilePickerPreference.java
 的 import xx.xxx.xxx.R,改成对应的项目id(也就是根目录下config.xml里widget的id)


# 使用

```
    // opts （exts：文件后缀，设置后只显示该后缀的文件.文件后缀组成的字符串，如".png,.jpg"，可为空;rootPath:开始显示的路径，可为空）
    window.plugins.ccFilepicker.pickFiles(opts,function (result) {
        defer.resolve(result);
    }, function (error) {
        defer.reject(error);
    });
```

