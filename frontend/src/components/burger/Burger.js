import React, { useState } from 'react'
import './Burger.css';
import { Link, useLocation } from 'react-router-dom';

const Burger = ({player,handleLogin}) => {
  const [isOpened, setIsOpened] = useState(false);
  const location = useLocation();

  const handleCheckboxChange = () => {
    setIsOpened(!isOpened)
  };

  return (
    <div>

      <div className="menu cross menu--1">
        <div className={`burger_container ${isOpened ? 'showBurgerContainer' : ''}`}>
          <label>
            <input type="checkbox" onChange={handleCheckboxChange} checked={isOpened} />
            <svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
              <circle cx="50" cy="50" r="30" />
              <path className="line--1" d="M0 40h62c13 0 6 28-4 18L35 35" />
              <path className="line--2" d="M0 50h70" />
              <path className="line--3" d="M0 60h62c13 0 6-28-4-18L35 65" />
            </svg>

          </label>
          <div className={`opened_burger ${isOpened ? 'show' : ''}`}>

          <Link to="/">
              <div className={`burger_item ${location.pathname === '/' ? 'hidden' : ''}`}>

                <lord-icon
                  src="https://cdn.lordicon.com/cnpvyndp.json"
                  trigger="hover"
                  class="icon_home current-color"
                  >
                </lord-icon>
              </div>
            </Link>
            
            <Link to="/comments">
              <div className={`burger_item ${location.pathname === '/comments' ? 'hidden' : ''}`}>
                <lord-icon
                  src="https://cdn.lordicon.com/fdxqrdfe.json"
                  trigger="hover"
                  class="current-color lordicon"
                  >
                </lord-icon>
              </div>
            </Link>
            
            <Link to="/leaderboard">
              <div className={`leaderboard burger_item ${location.pathname === '/leaderboard' ? 'hidden' : ''}`}>
              </div>
            </Link>

              <div className={`logout burger_item ${player ==="" ? 'hidden' : ''}`} onClick={()=>handleLogin("")}>
              </div>

              <div className={`signin burger_item ${player !=="" ? 'hidden' : ''}`} onClick={()=>handleLogin("")}>
              </div>


          </div>

        </div>
      </div>

    </div >

  )
}

export default Burger