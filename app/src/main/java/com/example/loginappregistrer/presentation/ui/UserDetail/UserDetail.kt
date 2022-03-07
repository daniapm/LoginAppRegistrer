package com.example.loginappregistrer.presentation.ui.UserDetail

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.loginappregistrer.R
import com.example.loginappregistrer.presentation.ui.UserLogin.LoginViewModel
import com.example.loginappregistrer.presentation.viewmodel.UserEvent

class UserDetail : Fragment() {

    private val userDetailViewModel: UserDetailViewModel by activityViewModels()
    private lateinit var detail: TextView
    private lateinit var welcomeMessage: TextView
    private lateinit var depositar: Button
    private lateinit var retirar: Button
    private lateinit var myPreferen : LoginViewModel.MyPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        depositar = view.findViewById(R.id.depositar)
        retirar = view.findViewById(R.id.retirar)

        depositar.setOnClickListener{
            findNavController().navigate(R.id.action_userDetail_to_depositor)
        }

        retirar.setOnClickListener{
            findNavController().navigate(R.id.action_userDetail_to_retirar2)
        }

        welcomeMessage = view.findViewById(R.id.text_welcome)

        myPreferen = LoginViewModel.MyPreference(this.context)
        val nombre = myPreferen.getLoginName()
        welcomeMessage.text = getString(R.string.welcome_message, nombre)

        detail = view.findViewById(R.id.text_detail)
        val userBalance = userDetailViewModel.userBalance("Myra.Hoppe")
        detail.text = getString(R.string.text_balance, userBalance)

        userDetailViewModel.event.observe(this, {
            when(it) {
                is UserEvent.ShowError -> { showError() }
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
