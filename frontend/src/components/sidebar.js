import './Sidebar.css'
import React, { useState, useEffect } from 'react'
import { isExpired, decodeToken } from 'react-jwt'

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
      var c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
}

function setCookie(cname, cvalue, exhours) {
    var d = new Date();
    d.setTime(d.getTime() + (exhours*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function Sidebar(props) {
    const [logged, setLogged] = useState(false)
    const [login_button_text, setLoginButtonText] = useState("Zaloguj")

    // useEffect(() => {
    //     setLogged((async () => {
    //         const jwt = getCookie("jwt")
    //         if (jwt == "") return false
    
    //         const response = await fetch("https://projekt-pp-backend.herokuapp.com/authorities", {
    //             credentials: 'same-origin',
    //             headers: {
    //                 'Authorization': `Bearer $jwt`
    //             }
    //         })
    
    //         if (response.status === 200) return true
    //         return false
    //     })())
    // })

    const logout = () => {
        setCookie("jwt", )
    }

    const openRegisterPage = () => {
        props.setPage(props.PageEnum.register)
    }

    const signin = async () => {
        setLoginButtonText("Logowanie...")

        let data = {
            "username": document.getElementById("login_field").value,
            "password": document.getElementById("password_field").value
        }

        fetch("https://projekt-pp-backend.herokuapp.com/login", {
            method: 'POST',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then((response) => {
            if (response.status === 200) {
                setCookie("jwt", response.json().jwt)
                setLogged(true)
            } else {
                alert(response.status)
            }
        })
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
                            <button type="button" onClick={signin}>{login_button_text}</button>
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
