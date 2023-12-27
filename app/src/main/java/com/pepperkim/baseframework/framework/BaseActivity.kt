package com.pepperkim.baseframework.framework

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.pepperkim.baseframework.R

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    lateinit var binding : T
    abstract val TAG :String // 액티비티 태그
    abstract val layoutRes: Int // 바인딩에 필요한 layout
//    abstract val viewModel: V? = null // 뷰모델

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
    }

    open fun setEditTextNullCheck(input: AppCompatEditText, msg: Int) : Boolean {
        return if(input.text.toString().isEmpty()){
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
            input.requestFocus()
            true
        }else{
            false
        }
    }

    open fun setTextWatcher(input: AppCompatEditText, text : MutableLiveData<String>){
        val textWatcher = object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                text.value = input.text.toString()
            }
        }
        input.addTextChangedListener(textWatcher)
    }
}