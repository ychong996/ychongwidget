#ychongwidget<br>
##Dialog<br>
###LoadingDialog<br>
####使用<br>
```
    private var dialog: LoadingDialog? = null
    private fun showLoading() {
        dialog = LoadingDialog(this)
        dialog!!.setText("正在加载中")
            .setLoadingImg(R.mipmap.ic_loading)
            .setTextColor(R.color.color_0D71DF)
            .setBackgroundColor(R.color.color_red)
            .setBackground(R.drawable.bg_333333_side_radius)
            .isCan(true)
            .build()
    }

    private fun hideLoading() {
        if (dialog!=null){
            dialog!!.dismiss()
        }

    }
```
![](https://github.com/ychong996/ychongwidget/raw/master/images/LoadingDialog.jpg)  

###TipsDialog<br>
####使用<br>
```
     val dialog = TipsDialog(this)
                .setMsg("提示成功了")
                .build()
            dialog.setOnListener(object :
                OnListener {
                override fun listener() {
                    ToastUtils.showText(this@TipsDialogActivity,"知道了")
                }
            })
```
###InputDialog<br>
####使用
```
 val dialog = InputDialog(this)
                .setTitle("标题")
                .setTitleColor(R.color.color_666666)
                .setTitleSize(20f)
                .setHintText("请输入内容")
                .setDimAmount(0.2f)
                .build()
            dialog.setOnLeftListener(object : OnLeftListener{
                override fun left() {
                    ToastUtils.showText(this@InputDialogActivity,"左边")
                }

            }).setOnRightListener(object : OnRightListener{
                override fun right() {
                    ToastUtils.showText(this@InputDialogActivity,"右边")
                }

            })
```
###MessageDialog<br>
####使用
```
 val dialog = MessageDialog(this)
                .setMsg("您想清楚了吗？")
                .build()
```
###ResultDialog<br>
####使用
```
 binding.resultTv.setOnClickListener{
            val dialog = ResultDialog(this)
                .setResultImg(R.mipmap.ic_success_tag)
                .setTips("结果成功了")
                .build()
```
###ToastDialog<br>
####使用
```
val dialog = ToastDialog(this)
                .setMsg("吐司出来啦")
                .build()
```
##Button<br>
###YCHButton<br>
####使用
```
 <com.ychong.ychongwidget.widget.YCHButton
        android:layout_margin="@dimen/dp_16"
        app:radius_size="@dimen/dp_16"
        android:text="按钮"
        android:textColor="@color/color_999999"
        android:id="@+id/ycnBtn"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_width="match_parent"
        android:layout_height="@dimen/dp_50"
        app:pressed_color="@color/color_999999"
        app:normal_color="@color/color_0D71DF"/>
```

##Android依赖方式
```
Step 1. Add the JitPack repository to your build file 
Add it in your root build.gradle at the end of repositories:
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency
dependencies {
	        implementation 'com.github.ychong996:ychongwidget:v1.0.2'
	}
```