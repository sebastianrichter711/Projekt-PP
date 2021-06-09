import './App.css'
import React, { useState } from 'react'
import Sidebar from './components/sidebar'
import Sections from './components/sections'

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
      <Sidebar />
      <div className="container">
        {        
          (() => {
            switch (page) {
              case PageEnum.home:
                return <Sections />
              case PageEnum.register:
                return
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
