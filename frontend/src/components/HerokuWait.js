import './HerokuWait.css'
import React from 'react';

function HerokuWait(props) {
    const url = "https://projekt-pp-backend.herokuapp.com"

    var xhttp = new XMLHttpRequest()
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4) {
            props.setPage(props.PageEnum.home)
        }
    }

    xhttp.open("GET", url, true);
    xhttp.send();

    return (
        <div id="HerokuWait">
            <div id="container">
                <div id="box">
                    <div class="lds-dual-ring"></div>
                    <h1>Page is being loaded...</h1>
                    <h3>Server is starting, this can take up to one minute...</h3>
                    <h5>Servers are going to sleep automatically when no load is detected to save server resources.</h5>
                </div>
            </div>
        </div>
    )
}

export default HerokuWait
