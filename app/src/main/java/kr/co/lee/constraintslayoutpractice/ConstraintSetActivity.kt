package kr.co.lee.constraintslayoutpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import kr.co.lee.constraintslayoutpractice.databinding.ActivityConstraintSetBinding

// const 최상위 프로퍼티에서 사용가능(일반적인 클래스 안에서 사용 불가)
// 원시타입과 String 타입만 받을 수 있음
private const val SHOW_BIG_IMAGE: String = "showBigImage"

class ConstraintSetActivity : AppCompatActivity() {

    // 이미지를 크게할지 안할지 정하는 변수
    private var mShowBigImage: Boolean = false

    // 최초의 상태를 위해 사용하는 ConstraintSet
    private val mConstraintSetNormal: ConstraintSet = ConstraintSet()

    // 최초 ConstraintLayout에 이미지를 더 크게 만드는 적용을 시키는 ConstraintSet
    private val mConstraintSetBig: ConstraintSet = ConstraintSet()

    private lateinit var binding: ActivityConstraintSetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConstraintSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Note that this can also be achieved by calling
        // `mConstraintSetNormal.load(this, R.layout.constraintset_example_main);`
        // Since we already have an inflated ConstraintLayout in `mRootLayout`, clone() is
        // faster and considered the best practice.
        
        // ConstraintLayout의 파라미터들을 복사한다
        mConstraintSetNormal.clone(binding.root)
        // 이미지 뷰가 거대해지는 레이아웃으로부터 제약조건들은 가져온다
        mConstraintSetBig.load(this, R.layout.activity_constraint_set_big)

        // savedInstanceState가 null 이면 첫 실행, null이 아니면 화면을 가로로 변경한다던지 그런 후
        if(savedInstanceState != null) {
            val previous: Boolean = savedInstanceState.getBoolean(SHOW_BIG_IMAGE)
            if(previous != mShowBigImage) {
                mShowBigImage = previous
                applyConfig()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Boolean 값으로 key, value 넣기
        outState.putBoolean(SHOW_BIG_IMAGE, mShowBigImage)
    }

    // Method called when the ImageView within R.layout.constraintset_example_main is clicked
    fun toggleMode(v: View) {
        TransitionManager.beginDelayedTransition(binding.root)
        mShowBigImage = !mShowBigImage
        applyConfig()
    }

    private fun applyConfig() {
        // 이미지를 크게 할꺼라면
        // applyTo: Apply the constraints to a ConstraintLayout
        if(mShowBigImage) {
            // Constraint Layout에 이미지가 커지는 constraints를 적용
            mConstraintSetBig.applyTo(binding.root)
        } else { // 이미지를 작게 할꺼라면
            // Constraint Layout에 이미지가 작아지는 constraints를 적용
            mConstraintSetNormal.applyTo(binding.root)
        }
    }
}