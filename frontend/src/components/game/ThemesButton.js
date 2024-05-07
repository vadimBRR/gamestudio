import React, { useState } from 'react'
import "./ThemesButton.css"
const ThemesButton = ({ selectedGameTheme, setSelectedGameTheme }) => {

  const [isShowMenu, setIsShowMenu] = useState(false);
  const handleGameThemeClick = (theme) => {
    setSelectedGameTheme(theme);
  };
  return (
    <div className={`theme_button ${isShowMenu ? 'show_menu' : ''}`}>
      <div className='circle_container' onClick={() => setIsShowMenu(!isShowMenu)}>
        <img src='/images/button_theme/theme.png' alt='themes'/>
      </div>
      <div className={`theme_menu ${isShowMenu ? 'show_menu_list' : ''}`} >
        
          <img
            src="/images/field_size/8_default.jpg"
            alt="Theme 1"
            className={`theme_image mb-2 ${selectedGameTheme === 'theme1' ? 'modal-image selected' : 'modal-image'}`}
            onClick={() => handleGameThemeClick('theme1')}
          />

          <img
            src="/images/anim_theme/8.gif"
            alt="Theme 2"
            className={`theme_image mb-2 ${selectedGameTheme === 'theme2' ? 'selected' : ''}`}
            onClick={() => handleGameThemeClick('theme2')}
          />
           
      </div>
    </div>

  )
}

export default ThemesButton