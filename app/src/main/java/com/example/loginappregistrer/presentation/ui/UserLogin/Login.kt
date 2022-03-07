package com.example.loginappregistrer.presentation.ui.UserLogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.loginappregistrer.R
import com.example.loginappregistrer.domain.model.User
import com.example.loginappregistrer.presentation.viewmodel.UserEvent


class Login : Fragment() {

    private val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var login: Button
    private lateinit var register: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userName = view.findViewById(R.id.username)
        password = view.findViewById(R.id.password)
        login = view.findViewById(R.id.login)
        register = view.findViewById(R.id.register)

        register.setOnClickListener{
            findNavController().navigate(R.id.action_login2_to_register2)
        }
        login.setOnClickListener {
            loginViewModel.login(userName.text.toString(), password.text.toString())
        }
        loginViewModel.navigateToUserDetails.observe(this, { hasFinished ->
            if (hasFinished == true) {
                login.setOnClickListener {
                    findNavController().navigate(R.id.action_login2_to_userDetail)
                }
                loginViewModel.doneNavigatingUserDetails()
            }
        })
        loginViewModel.errotoast.observe(this, { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
                loginViewModel.doneToast()
            }
        })

        loginViewModel.errorToastInvalidDate.observe(this, Observer { hasError ->
            if (hasError == true) {
                Toast.makeText(
                    requireContext(),
                    "Please check your Username or PassWorrd",
                    Toast.LENGTH_SHORT
                ).show()
                loginViewModel.donetoastInvalidDate()
            }
        })

        loginViewModel.event.observe(this, {
            when(it) {
                is UserEvent.ShowError -> { showError() }
                is UserEvent.SetUsers -> {

                }
            }
        })
    }

    private fun showError() {
        Toast.makeText(
            requireContext(),
            "Error al cargar los datos",
            Toast.LENGTH_SHORT
        ).show()
    }

}
