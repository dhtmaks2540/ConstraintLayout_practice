package kr.co.lee.constraintslayoutpractice

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kr.co.lee.constraintslayoutpractice.databinding.ActivityBasicCenteringBinding
import kr.co.lee.constraintslayoutpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var mTag: String = "activity_main"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(mTag)
    }

    // onConfigurationChanged: Called by the system when the device configuration changes while your activity is running
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setContentView(mTag)
    }

    // Called when the activity has detected the user's press of the back key
    override fun onBackPressed() {
        // mTag가 activity_main 아닐 때
        if(!mTag.equals("activity_main")) {
            // activity_main으로 간다
            mTag = "activity_main"
            setContentView(mTag)
        } else {
            super.onBackPressed()
        }
    }

    fun show(v: View) {
        // View 객체를 얻어와서 tag 프로퍼티를 얻어오고 그 tag를 사용해 activity 셋팅
        mTag = v.tag as String
        setContentView(mTag)
    }

    fun showConstraintSetExample(view: View) {
        startActivity(Intent(this, ConstraintSetActivity::class.java))
    }

    // tag를 받아서 화면을 정하는 메소드
    // 화면만 바뀔경우 사용
    private fun setContentView(tag: String) {
        // getIdentifier: Return a resource identifier for the given resource name
        // name: 요구되는 리소스의 이름
        // defType: 찾을 리소스의 타입
        // defPackage: 찾을 기본적인 package
        // packageName 프로퍼티: Return the name of this application's package
        val id: Int = resources.getIdentifier(tag, "layout", packageName)
        setContentView(id)
    }
}