import React from 'react'
import "./LeaderBoard.css"

const LeaderBoard = ({ scores }) => {
  return (
    <>
      {scores.map((score, idx) => (
        <div key={score.ident} className={`container_card_points ${idx === 0 ? 'first_place_card' : idx === 1 ? 'second_place_card' : idx === 2 ? 'third_place_card' : ''}`}>
        <div className='left_container'>
        {idx!==0&&
        <span className={`card_number ${idx === 0 ? 'first_place' : idx === 1 ? 'second_place' : idx === 2 ? 'third_place' : ''}`}>{idx + 1}</span>
        }
        {idx===0&&(
             <div className='icon_container'> 
              <img src='/images/first_place5.png' alt='first place img' className='first_place_icon'/>
             </div>  
          )}
          <span className={`player_name_container ${idx === 0 ? 'first_place_playername' : idx === 1 ? 'second_place_playername' : idx === 2 ? 'third_place_playername' : ''}`}>
            {score.player}
          </span>
          
        </div>
          <span className={`points_container ${idx === 0 ? 'first_place_playername' : idx === 1 ? 'second_place_playername' : idx === 2 ? 'third_place_playername' : ''}`}>
            {score.points}
          </span>
        </div>
      ))}
    </>
  )
}

export default LeaderBoard