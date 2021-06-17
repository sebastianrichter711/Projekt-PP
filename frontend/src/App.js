import './App.css'
import React, { useState } from 'react'
import RegisterForm from './components/RegisterForm'
import Sidebar from './components/Sidebar'
import Sections from './components/Sections'

function App() {
  const PageEnum = Object.freeze({
    "home": 1,
    "register": 2,
    "section": 3,
    "thread": 4,
    "add_thread": 5
  })

  const [page, setPage] = useState(PageEnum.home)

  return (
    <div id="app">
      <Sidebar page={page} setPage={setPage} PageEnum={PageEnum} />
      <div className="container">
        {        
          (() => {
            switch (page) {
              case PageEnum.home:
                return <Sections />
              case PageEnum.register:
                return <RegisterForm />
              case PageEnum.section:
                return
              case PageEnum.thread:
                return
              case PageEnum.add_thread:
                return
            }
          })()
        }
      </div>
    </div>
  )
}

export default App
