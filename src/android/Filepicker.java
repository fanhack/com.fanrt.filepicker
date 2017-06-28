package cn.com.chaochuang;


import android.app.Activity;
import android.util.Log;

import com.cookie.filepicker.controller.DialogSelectionListener;
import com.cookie.filepicker.model.DialogConfigs;
import com.cookie.filepicker.model.DialogProperties;
import com.cookie.filepicker.view.FilePickerDialog;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import com.fanrt.filepicker.R;

/**
 * Created by Shicx on 2017-4-16.
 */

public class Filepicker extends CordovaPlugin {

  private static final String TAG_FILEPICKER2 = "filepicker2";

  private Activity cordoavActivity;
  private FilePickerDialog dialog;



  @Override
  public boolean execute(String action, JSONArray args,final CallbackContext callbackContext) throws JSONException {
    //传入的配置信息
    JSONObject options = args.getJSONObject(0);

    Log.d(TAG_FILEPICKER2,"传入的配置值"+options.toString());

    cordoavActivity = this.cordova.getActivity();
    //弹出框配置信息
    DialogProperties properties=new DialogProperties();
    //多选
    properties.selection_mode = DialogConfigs.MULTI_MODE;
    //只能选文件
    properties.selection_type=DialogConfigs.FILE_SELECT;
    //只能选的文件后缀（如".jpg,.png,.doc"）
    String exts = options.getString("exts");
    if(exts!=null&&!"".equals(exts.trim())){
      properties.extensions = exts.split(",");
    }
    //文件路径设置
    String rootPath = options.getString("rootPath");
    if(rootPath!=null&&!"".equals(rootPath.trim())){
      properties.root = new File(rootPath);
    }else{
      properties.root=new File(DialogConfigs.DEFAULT_DIR);
    }

    dialog=new FilePickerDialog(cordoavActivity,properties);
    dialog.setTitle(R.string.filepicker_title);

    dialog.show();

    //选择完成监听方法
    dialog.setDialogSelectionListener(new DialogSelectionListener() {
      @Override
      public void onSelectedFilePaths(String[] files) {
        Log.d(TAG_FILEPICKER2,"选择完成："+files.toString());
        JSONArray fileArr = new JSONArray();
        for(String path:files) {
          JSONObject fileInfo = new JSONObject();
          File file = new File(path);
          try {
            fileInfo.put("fileName",file.getName());
            fileInfo.put("filePath",file.getAbsolutePath());
            fileInfo.put("fileSize",file.getTotalSpace());
            fileInfo.put("isFile",file.isFile());
          } catch (JSONException e) {
            e.printStackTrace();
          }
          fileArr.put(fileInfo);
        }
        callbackContext.success(fileArr);
      }
    });
    return true;
  }
}
