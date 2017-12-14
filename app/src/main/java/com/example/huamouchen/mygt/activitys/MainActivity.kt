package com.example.huamouchen.mygt.activitys

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.huamouchen.mygt.R
import com.example.huamouchen.mygt.fragments.*
import com.example.huamouchen.mygt.fragments.my_store.MyStoreFragment
import com.example.huamouchen.mygt.widgets.FlowRadioGroup
import com.example.huamouchen.mygt.widgets.PercentRelativeLayout
import java.util.*

class MainActivity : BaseActivity() {

    // fragment stack
    lateinit var performance: Stack<Fragment>
    lateinit var my_store: Stack<Fragment>
    lateinit var store_call: Stack<Fragment>
    lateinit var posm: Stack<Fragment>
    lateinit var selling_story: Stack<Fragment>
    lateinit var my_people: Stack<Fragment>
    lateinit var memo: Stack<Fragment>
    lateinit var other: Stack<Fragment>

    lateinit var rg: FlowRadioGroup // tab bar 的 radio group

    lateinit var rl_content: PercentRelativeLayout // 右边用来显示主体内容的 layout

    var currentFragment: Fragment? = null // 当前正在显示的 fragment


    /*
    * onCreate 方法
    * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRootView(R.layout.activity_main)

        initView()
        // 默认选中 Performance
        rg.check(R.id.rb_performance)
    }

    fun initView() {
        performance = Stack<Fragment>()
        my_store = Stack<Fragment>()
        store_call = Stack<Fragment>()
        posm = Stack<Fragment>()
        selling_story = Stack<Fragment>()
        my_people = Stack<Fragment>()
        memo = Stack<Fragment>()
        other = Stack<Fragment>()

        rg = findViewById(R.id.rg_tab)
        // 设置点击监听
        rg.setOnCheckedChangeListener { group, checkedId ->
            var showFragment: Fragment? = when (checkedId) {
                R.id.rb_performance -> performanceClick()
                R.id.rb_my_store -> my_storeChecked()
                R.id.rb_store_call -> store_callChecked()
                R.id.rb_posm -> posmChecked()
                R.id.rb_selling_story -> selling_storyChecked()
                R.id.rb_my_people -> my_peopleChecked()
                R.id.rb_memo -> memoChecked()
                R.id.rb_other -> otherChecked()
                else -> {
                    null
                }
            }

            displayFragmeng(showFragment, false)
        }

        rl_content = findViewById(R.id.rl_content)
    }

    /*
    * 点击 item，显示Fragment
    * 1.如果都没有显示fragment，即 currentFragment 为空， 就直接添加 fragment，如果要添加到栈，就推入栈
    * 2.如果有currentFragment，但是要显示的Fragment不一样，且是第一次添加，就走第一步，然后隐藏之前显示的Fragment
    * 3.如果有currentFragment，并且已经添加过了，就隐藏之前的Fragment，显示要显示的Fragment
    * */
    fun displayFragmeng(fragment: Fragment?, isAddToStack: Boolean) {
        // fragment 为空，直接返回
        if (fragment == null) return

        // fragment 不为空的情况
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        // 所有的 fragment 都是第一次显示
        if (currentFragment == null) {
            transaction.add(R.id.rl_content, fragment)
        } else if (currentFragment != fragment && !fragment.isAdded) { // 有正在显示的Fragment，即将要显示的Fragment是第一次显示
            transaction.hide(currentFragment).add(R.id.rl_content, fragment)
        } else { // fragment 已经显示过，只是进行切换
            transaction.hide(currentFragment).show(fragment)
        }
        transaction.commitAllowingStateLoss()
        // 更新当前的fragment
        currentFragment = fragment
    }

    private fun performanceClick(): Fragment {
        val fragment: PerformanceFragment
        if (performance.empty()) { // 栈为空，新建Fragment
            fragment = PerformanceFragment()
            performance.push(fragment)
        } else { // 栈里面有Fragment，就从栈里面去
            fragment = performance.last() as PerformanceFragment
        }
        return fragment
    }

    private fun my_storeChecked(): Fragment {
        val fragment: MyStoreFragment
        if (my_store.empty()) {
            fragment = MyStoreFragment()
            my_store.push(fragment)
        } else {
            fragment = my_store.last() as MyStoreFragment
        }
        return fragment
    }

    private fun store_callChecked(): Fragment {
        val fragment: StoreCallFragment
        if (store_call.empty()) {
            fragment = StoreCallFragment()
            store_call.push(fragment)
        } else {
            fragment = store_call.last() as StoreCallFragment
        }
        return fragment
    }

    private fun posmChecked(): Fragment {
        val fragment: PosmFragment
        if (posm.empty()) {
            fragment = PosmFragment()
            posm.push(fragment)
        } else {
            fragment = posm.last() as PosmFragment
        }
        return fragment
    }

    private fun selling_storyChecked(): Fragment {
        val fragment: SellingStoryFragment
        if (selling_story.empty()) {
            fragment = SellingStoryFragment()
            selling_story.push(fragment)
        } else {
            fragment = selling_story.last() as SellingStoryFragment
        }
        return fragment
    }

    private fun my_peopleChecked(): Fragment {
        val fragment: MyPeopleFragment
        if (my_people.empty()) {
            fragment = MyPeopleFragment()
            my_people.push(fragment)
        } else {
            fragment = my_people.last() as MyPeopleFragment
        }
        return fragment
    }

    private fun memoChecked(): Fragment {
        val fragment: MemoFragment
        if (memo.empty()) {
            fragment = MemoFragment()
            memo.push(fragment)
        } else {
            fragment = memo.last() as MemoFragment
        }
        return fragment
    }

    private fun otherChecked(): Fragment {
        val fragment: OthersFragment
        if (other.empty()) {
            fragment = OthersFragment()
            other.push(fragment)
        } else {
            fragment = other.last() as OthersFragment
        }
        return fragment
    }
}
