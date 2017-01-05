# 自定义VideoView MediaController 半屏全屏播放 #

使用系统vedioview，底部的MediaController布局有点不好看，尤其是进度条，不能实现办半屏与全屏的切换

直接复制系统源码来进行修改

# MediaController.Java #

	public class MediaController extends FrameLayout {

布局相关的修改，建议源码中有的控件若无需要就先隐藏（不要删除，否则又得去修改MediaController类）。

新加的控件直接暴露接口回调

# VideoView.java #

	VideoView extends SurfaceView implements MediaPlayerControl

- 实现系统源码MediaPlayerControl接口，修改为实现自定义MediaController.MediaPlayerControl接口
- onMeasure中注释掉那些解码大小的判断，直接采用当前默认大小，然后设置mSurfaceHolder.setFixedSize(width, height);这句若没写，全屏会失败
