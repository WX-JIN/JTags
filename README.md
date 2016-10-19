# JTags
#### &#160;&#160;&#160;&#160;JTags 可以展示自己要标明在APP页面任何位置的标签，也可以作为新手指导来提供用户说明，之后还有还会陆续添加一些相辅的功能，希望大家多多支持！ps：如果有什么bug或者优化点也可以向博主说下，留言
效果图如下（gif进行压缩效果变差了）：
![image](https://github.com/WX-JIN/JTags/blob/master/screenshots/4.gif)
![image](https://github.com/WX-JIN/JTags/blob/master/screenshots/5.gif)
##	简要说明

	
#### 目前封装库支持：

 * 新手引导功能，自动按步骤引导。
 * 手动关闭引导。
 * 贴标签（普通标签，焦点光晕标签，折线光晕标签三种标签）。
 * 隐藏所有显示的标签。
 * 显示所有隐藏的标签。
 * 打开所有标签焦点的光晕效果。
 * 关闭所有标签焦点的光晕效果。
 * 支持单个标签显示、隐藏。
 
源码地址：https://github.com/WX-JIN/JTags

##	用法

 * Android Studio

	引入依赖库
	```
    compile 'com.soubw:jtags:0.0.2'
	```	
	<font color=#ff0000>具体最新aar封装库版本，请查看github上的版本，地址：[https://github.com/WX-JIN/JTags](https://github.com/WX-JIN/JTags)</font>
 * Eclipse

	到[GitHub](https://github.com/WX-JIN/JTags)，copy源码

### 基本用法
#### 添加布局
```
    <com.soubw.jtags.JTagLayout
        android:id="@+id/jTagLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```
####获取标签布局对象
```
JTagLayout jTagLayout = (JTagLayout) findViewById(R.id.jTagLayout);
```
####标签监听
```
jTagLayout.setOnJTagsClickListener(new OnJTagClickListener() {
            @Override
            public void onJTagClick(JTag jTag) {
            }
});
```
####添加单个标签
```
JTagBean bean = new JTagBean(200,300,"WX_JIN", JTagType.NONE);
jTagLayout.addJTag(bean);
```
####添加多个标签
```
ArrayList<JTagBean> jTagBeans = new ArrayList<>();  jTagBeans.add(new JTagBean(120,222,"WX_JIN1", JTagType.CIRCLE));
jTagBeans.add(new JTagBean(60,40,"WX_JIN2", JTagType.NONE));
jTagBeans.add(new JTagBean(240,536,"WX_JIN3", JTagType.POLYLINE));
jTagLayout.addJTag(bean);
```
####新手引导说明
```
ArrayList<JTagBean> jTagBeans = new ArrayList<>(); 
jTagBeans.add( new JTagBean(JTagType.BG, R.drawable.guide_bg)); //添加一张新手指导透明浮层，类型为JTagType.BG，之后添加的标签会按步骤一步一步显示出来。
jTagBeans.add(new JTagBean(40,562,"第一步", JTagType.CIRCLE));
jTagBeans.add(new JTagBean(360,200,"第二步", JTagType.NONE));
jTagBeans.add(new JTagBean(140,436,"关闭", JTagType.POLYLINE));
jTagLayout.addJTag(bean);
```
####其他

```
jTag.showJTag();//显示单个标签
jTag.hideJTag();//隐藏单个标签
jTagLayout.showAllJTags();//显示所有标签
jTagLayout.hideAllJTags();//隐藏所有标签
jTagLayout.startAllAnim();//打开所有标签光晕效果
jTagLayout.cancelAllAnim();//取消所有标签光晕效果
jTagLayout.dismissGuide();//手动关闭剩下新手引导
```
#### JTagType标签类型
```
public enum JTagType {
    NONE,//普通
    CIRCLE,//焦点光晕效果
    POLYLINE,//折线
    BG,//新手引导
}
```
#### JPolyLineType折线方位类型
```
public enum  JPolyLineType {
    LEFT_TOP,//左上
    LEFT_BOTTOM,//左下
    RIGHT_TOP,//右上
    RIGHT_BOTTOM,//右下
}
```
#### JTagBean标签实体对象

```
private int jTagX;//标签焦点X位置 单位px
private int jTagY;//标签焦点Y位置 单位px
private int jTagTextColor;//标签内容颜色
private int jTagTextSize;//标签内容字体大小
private String jTagContent;//标签内容
private int jTagBg;//标签背景图
private JTagType jTagType;//标签类型
private JPolyLineType jPolyLineType;//为折线类型，折线方位
```
以上实体对象可以根据自己需求添加、设置。

详细用法可以参考[Demo](https://github.com/WX-JIN/JTags)

----------
最后，再次说下如果发现问题、或者有优化地方、或或者如果有新的辅助功能可以加入，可以在我的[GitHub](https://github.com/WX-JIN/JTags)上提issues，或者留言，博主会第一时间来处理。 谢谢！

----------
欢迎关注我的博客：
http://blog.csdn.net/wx_jin
