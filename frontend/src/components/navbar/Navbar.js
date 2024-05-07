import React from 'react'
import "./Navbar.css";
import Burger from '../burger/Burger';

const Navbar = ({player,handleLogin}) => {
  return (
    <div className='navbarContainer'>

      <Burger player = {player} handleLogin={handleLogin}/>
    </div>
  )
}

export default Navbar