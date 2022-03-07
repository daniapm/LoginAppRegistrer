package com.example.loginappregistrer.presentation.ui.UserRegister

import android.app.AlertDialog
import android.content.DialogInterface
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
import androidx.navigation.fragment.findNavController
import com.example.loginappregistrer.R
import java.lang.reflect.Array.set

class Register : Fragment() {

    private val registerViewModel: RegisterViewModel by activityViewModels()
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var repeat: EditText
    private lateinit var register: Button
    private lateinit var back: Button
    private lateinit var text: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstName = view.findViewById(R.id.first_name_register)
        lastName = view.findViewById(R.id.last_name_register)
        userName = view.findViewById(R.id.username_register)
        password = view.findViewById(R.id.password_register)
        repeat = view.findViewById(R.id.repeat_password_register)
        register = view.findViewById(R.id.register_ok)
        back = view.findViewById(R.id.back)
        text = view.findViewById(R.id.text_register)

        back.setOnClickListener {
            findNavController().navigate(R.id.action_register2_to_login2)
        }

        register.setOnClickListener {
            registerViewModel.register(firstName.text.toString(), lastName.text.toString(), userName.text.toString(), password.text.toString(), repeat.text.toString())
            registerViewModel.doneRegister.observe(this, { hasSucces ->
                if (hasSucces == true) {
                    Toast.makeText(requireContext(), "Successful registration", Toast.LENGTH_SHORT)
                        .show()
                    registerViewModel.doneRegisterexit()
                    findNavController().navigate(R.id.action_register2_to_userDetail)
                }
            })
        }

        registerViewModel.erroPass.observe(this, { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "The Password no macth", Toast.LENGTH_SHORT)
                    .show()
                registerViewModel.passError()
            }
        })

        registerViewModel.errotoast.observe(this, { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
                registerViewModel.donetoast()
            }
        })
        registerViewModel.errotoastUserName.observe(this, { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "UserName Already taken", Toast.LENGTH_SHORT)
                    .show()
                registerViewModel.doneToastUserName()
            }
        })
    }

    val alertDialog: AlertDialog? = activity?.let {
        val builder = AlertDialog.Builder(it)
        builder.apply {
            setPositiveButton(R.string.ok,
                DialogInterface.OnClickListener { dialog, id ->

                })
        }
        builder.create()
    }
}
