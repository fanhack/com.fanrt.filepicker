/**
 * Created by cookie on 2016/6/23.
 */
var exec = require('cordova/exec');
//定义导航对象 与 方法
var ccFilepicker = {
    pickFiles:function(data, successCallback, errorCallback) {
        if(!data){
            data = {
                exts:"",
                rootPath:""
            }
        }
        if(!data.exts){
            data.exts="";
        }
        if(!data.rootPath){
            data.rootPath="";
        }
        exec(successCallback, errorCallback, 'Filepicker', 'pickFiles', [data]);
    }
};

module.exports= ccFilepicker;