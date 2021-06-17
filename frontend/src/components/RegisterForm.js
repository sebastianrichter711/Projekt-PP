import '../App.css'
import React, { useState } from 'react';

function RegisterForm() {
    return (
        <div id="registerForm">
            <input type="text" id="login_field" placeholder="login" />
            <input type="password" id="password_field" placeholder="hasło" />
            <input type="text" id="email_form" placeholder="email" />
            <button type="button">Zarejestruj</button>
        </div>
    )
}

export default RegisterForm
