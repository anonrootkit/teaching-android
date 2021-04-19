package com.example.helloworld

import java.util.regex.Matcher
import java.util.regex.Pattern


private var PASSWORD_MIN_LENGTH: Int = 8
private var EMAIL_MIN_SIZE: Int = 8
private var CORRECT_EMAIL: String = "ankit@ankit.com"
private var CORRECT_PASSWORD: String = "Ankit@ankit123"
val statesList: ArrayList<String> = ArrayList<String>().apply {
    add("Andhra")
    add("Arunachal")
    add("Delhi")
    add("HP")
    add("TM")
    add("Andhra")
    add("Arunachal")
    add("Delhi")
    add("HP")
    add("TM")
    add("Andhra")
    add("Andhra")
    add("Andhra")
    add("Andhra")
    add("Andhra")
    add("Andhra")
    add("Andhra")
    add("Andhra")
    add("Arunachal")
    add("Delhi")
    add("HP")
    add("TM")
}
val nameList : ArrayList<String> = ArrayList<String>().apply {
    add("Ankit")
    add("Abhishek")
    add("Ansh")
    add("Ankit")
    add("Abhishek")
    add("Ansh")
    add("Ankit")
    add("Abhishek")
    add("Ansh")
    add("Ankit")
    add("Abhishek")
    add("Ansh")
}

val passwordList : ArrayList<String> = ArrayList<String>().apply {
    add("#2323Apldpdfpk")
    add("#2323Apldpdfpk")
    add("#2323Apldpdfpk")
    add("#2323Apldpdfpk")
    add("#2323Apldpdfpk")
    add("#2323Apldpdfpk")
    add("#2323Apldpdfpk")
    add("#2323Apldpdfpk")
    add("#2323Apldpdfpk")
    add("#2323Apldpdfpk")
    add("#2323Apldpdfpk")
    add("#2323Apldpdfpk")
}


private val passwordRegex: String =
    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=()])(?=\\S+$).{8,20}$"
private val emailRegex: String = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
        "\\@" +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
        "(" +
        "\\." +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
        ")+"

fun checkEmailValidUsingRegex(email: String): Boolean {
    val pattern: Pattern = Pattern.compile(emailRegex)

    if (email.length < EMAIL_MIN_SIZE) {
        return false
    }

    val matcher: Matcher = pattern.matcher(email)

    return matcher.matches()
}

fun checkPasswordValidUsingRegex(password: String): Boolean {

    val pattern: Pattern = Pattern.compile(passwordRegex)

    if (password.length < PASSWORD_MIN_LENGTH) {
        return false
    }

    val matcher: Matcher = pattern.matcher(password)

    return matcher.matches()
}

fun checkPasswordValid(password: String): Boolean {

    if (password.length < PASSWORD_MIN_LENGTH) return false

    var doesCapitalLetterExist: Boolean = false

    for (character in password) {
        if (character.toInt() in 65..90) {
            doesCapitalLetterExist = true
            break
        }
    }

    if (doesCapitalLetterExist) {

        var doesNumberExist: Boolean = false

        for (character in password) {
            if (character.toInt() in 48..57) {
                doesNumberExist = true
                break
            }
        }

        if (doesNumberExist) {

            var doesSpecialCharacterExist: Boolean = false

            for (character in password) {
                if (character.toInt() == 35 || character.toInt() == 64) {
                    doesSpecialCharacterExist = true
                    break
                }
            }

            return doesSpecialCharacterExist

        } else {
            return false
        }

    } else {
        return false
    }

}

fun checkEmailValid(email: String): Boolean {
    return email.length >= EMAIL_MIN_SIZE
}

fun signInUser(emailString: String, passwordString: String): Boolean {
    return emailString == CORRECT_EMAIL && passwordString == CORRECT_PASSWORD
}