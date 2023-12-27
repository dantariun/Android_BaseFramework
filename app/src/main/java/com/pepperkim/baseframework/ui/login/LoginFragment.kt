package com.pepperkim.baseframework.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.pepperkim.baseframework.R
import com.pepperkim.baseframework.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)


        return binding.root
    }

    private fun setUI(){
        binding.login.setOnClickListener {
            activity?.run{
                Navigation.findNavController(this, R.id.action_login_to_signup)
            }
        }
    }
}