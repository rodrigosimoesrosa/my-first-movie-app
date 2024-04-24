package com.mirabilis.myfirstappmovie.validator

class PasswordValidator {

    /**
     * Expressão regular que valida senha
     * 8 a 16 characteres
     * Letra maiscula e minuscula (pelo menos 1)
     * Caracteres especiais (pelo menos 1)
     */
    private val regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,16}\$"
    
    fun isValid(password: String): Boolean {
        return password.matches(regex.toRegex())
    }

    fun isValidAndEqual(password: String, confirmationPassword: String): Boolean {
        if (!isValid(password)) {
            return false
        }
        
        if (!isValid(confirmationPassword)) {
            return false
        }
        
        return password == confirmationPassword
    }
}
