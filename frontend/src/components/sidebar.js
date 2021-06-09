import '../App.css'
import React, { useState } from 'react'

function Sidebar() {
    return (
        <div className="sidebar">
            <input type="text" id="login_form" placeholder="login" /><br />
            <input type="password" id="password_form" placeholder="hasło" />
            <button type="button">Zaloguj</button>
            <button type="button">Zarejestruj</button>
            <input type="text" id="search_form" placeholder="wyszukaj" />
            <button type="button" class="wide_button">Najpopularniejsze wątki</button>
            <ul>
                <li>Wątek 1</li>
                <li>Wątek 2</li>
                <li>Wątek 3</li>
            </ul>
            <button type="button" class="wide_button">Dodaj wątek</button>
        </div>
    )
}

export default Sidebar
