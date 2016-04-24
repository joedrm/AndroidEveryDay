
## Mac上配置adb命令

- 在当前HOME目录下输入：touch .bash_profile
- 输入open -e .bash_profile
- 打开文件开始编辑：
	export PATH=${PATH}:/Users/apple/Library/Android/sdk/platform-tools
	export PATH=${PATH}:/Users/apple/Library/Android/sdk/tools
- 保存文件，关闭.bash_profile
- 更新刚配置的环境变量，输入：source .bash_profile
- 验证：adb devices，显示：List of devices attached 安装成功

## 常用的adb命令
adb devices 列出所有的设
adb start-server 开启adb服务
adb kill-server	关闭adb服务
adb logcat 查看Log
adb shell 挂载到Linux的空间 
adb install <应用程序(加扩展名)> 	安装应用程序
adb install <应用程序(加扩展名)> 安装应用到指定模拟器
adb uninstall <程序包名>
adb pull <remote> <local> 
adb push <local> <remote>
