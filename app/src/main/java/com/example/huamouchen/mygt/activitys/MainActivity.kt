package com.example.huamouchen.mygt.activitys

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.huamouchen.mygt.R
import com.example.huamouchen.mygt.fragments.memo.MemoFragment
import com.example.huamouchen.mygt.fragments.my_people.MyPeopleFragment
import com.example.huamouchen.mygt.fragments.my_store.MyStoreFragment
import com.example.huamouchen.mygt.fragments.others.OthersFragment
import com.example.huamouchen.mygt.fragments.performance.PerformanceFragment
import com.example.huamouchen.mygt.fragments.posm.PosmFragment
import com.example.huamouchen.mygt.fragments.selling_story.SellingStoryFragment
import com.example.huamouchen.mygt.fragments.store_call.StoreCallFragment
import com.example.huamouchen.mygt.widgets.FlowRadioGroup
import com.example.huamouchen.mygt.widgets.PercentRelativeLayout
import kotlinx.android.synthetic.main.fragment_performance.*
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

            displayFragment(showFragment, false)
        }

        rl_content = findViewById(R.id.rl_content)
    }

    /*
    * 点击返回按钮
    * */
    override fun onBackPressed() {
        when (rg.checkedRadioButtonId) {
            R.id.rb_performance -> {
                if (performance.size <= 1) {
//                    finish()
                }
                else {
                    performance.pop();
                    val fragment = performance.peek()
                    displayFragment(fragment, false)
                }
            }
            R.id.rb_my_store -> {
                val fragment = my_store.get(my_store.size - 2)
                displayFragment(fragment, false)
                my_store.pop()
            }

        }
    }

    /*
    * 隐藏 Fragment
    * */
    fun hideFragment() {
        val fragment = when (rg.checkedRadioButtonId) {
            R.id.rb_performance -> performance.pop()
            R.id.rb_my_store -> my_store.pop()
            R.id.rb_store_call -> store_call.pop()
            R.id.rb_posm -> posm.pop()
            R.id.rb_selling_story -> selling_story.pop()
            R.id.rb_my_people -> my_people.pop()
            R.id.rb_memo -> memo.pop()
            R.id.rb_other -> other.pop()
            else -> {
                null
            }
        }

        if (fragment == null) return

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        transaction.hide(currentFragment).show(fragment)
        transaction.commitAllowingStateLoss()
        // 更新当前的fragment
        currentFragment = fragment
    }

    /*
    * 点击 item，显示Fragment
    * 1.如果都没有显示fragment，即 currentFragment 为空， 就直接添加 fragment，如果要添加到栈，就推入栈
    * 2.如果有currentFragment，但是要显示的Fragment不一样，且是第一次添加，就走第一步，然后隐藏之前显示的Fragment
    * 3.如果有currentFragment，并且已经添加过了，就隐藏之前的Fragment，显示要显示的Fragment
    * */
    fun displayFragment(fragment: Fragment?, isAddToStack: Boolean) {
        // fragment 为空，直接返回
        if (fragment == null) return

        // fragment 不为空的情况
        if (isAddToStack) {
            when (rg.checkedRadioButtonId) {
                R.id.rb_performance -> performance.push(fragment)
                R.id.rb_my_store -> my_store.push(fragment)
                R.id.rb_store_call -> store_call.push(fragment)
                R.id.rb_posm -> posm.push(fragment)
                R.id.rb_selling_story -> selling_story.push(fragment)
                R.id.rb_my_people -> my_people.push(fragment)
                R.id.rb_memo -> memo.push(fragment)
                R.id.rb_other -> other.push(fragment)
            }
        }

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
        val fragment: Fragment
        if (performance.empty()) { // 栈为空，新建Fragment
            fragment = PerformanceFragment()
            performance.push(fragment)
        } else { // 栈里面有Fragment，就从栈里面去
            fragment = performance.last()
        }
        return fragment
    }

    private fun my_storeChecked(): Fragment {
        val fragment: Fragment
        if (my_store.empty()) {
            fragment = MyStoreFragment()
            my_store.push(fragment)
        } else {
            fragment = my_store.last()
        }
        return fragment
    }

    private fun store_callChecked(): Fragment {
        val fragment: Fragment
        if (store_call.empty()) {
            fragment = StoreCallFragment()
            store_call.push(fragment)
        } else {
            fragment = store_call.last()
        }
        return fragment
    }

    private fun posmChecked(): Fragment {
        val fragment: Fragment
        if (posm.empty()) {
            fragment = PosmFragment()
            posm.push(fragment)
        } else {
            fragment = posm.last()
        }
        return fragment
    }

    private fun selling_storyChecked(): Fragment {
        val fragment: Fragment
        if (selling_story.empty()) {
            fragment = SellingStoryFragment()
            selling_story.push(fragment)
        } else {
            fragment = selling_story.last()
        }
        return fragment
    }

    private fun my_peopleChecked(): Fragment {
        val fragment: Fragment
        if (my_people.empty()) {
            fragment = MyPeopleFragment()
            my_people.push(fragment)
        } else {
            fragment = my_people.last()
        }
        return fragment
    }

    private fun memoChecked(): Fragment {
        val fragment: Fragment
        if (memo.empty()) {
            fragment = MemoFragment()
            memo.push(fragment)
        } else {
            fragment = memo.last()
        }
        return fragment
    }

    private fun otherChecked(): Fragment {
        val fragment: Fragment
        if (other.empty()) {
            fragment = OthersFragment()
            other.push(fragment)
        } else {
            fragment = other.last()
        }
        return fragment
    }
}
