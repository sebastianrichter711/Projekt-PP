import './Sidebar.css'
import React, { useState } from 'react'

function Sidebar(props) {
    const [logged, setLogged] = useState(false)

    const logout = () => {
        // TODO
    }

    const openRegisterPage = () => {
        props.setPage(props.PageEnum.register)
    }

    return (
        <div className="sidebar">
            {(() => {
                if (props.page != props.PageEnum.register) {
                    if (logged) return (
                        <div id="login_form">
                            <p>@{"nazwa_użytkownika" /* TODO */}</p>
                            <button type="button" onClick={logout}>Wyloguj</button>
                        </div>
                    ) 
                    else return (
                        <div id="login_form">
                            <input type="text" id="login_field" placeholder="login" /><br />
                            <input type="password" id="password_field" placeholder="hasło" />
                            <button type="button">Zaloguj</button>
                            <button type="button" onClick={openRegisterPage}>Zarejestruj</button>
                        </div>
                    )
                }
            })()}
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
