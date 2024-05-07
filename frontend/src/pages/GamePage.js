import React from 'react'

import { useState } from 'react';
import GameBoard from '../components/game/GameBoard';
import { addScore } from '../_api/scores.service';
import { Button } from 'react-bootstrap';
import './GamePage.css';



const GamePage = ({player, game, handleOpenLogin, isNewGame,setIsNewGame}) => {
  const [scores, setScores] = useState(0);
  const [isGameFinished, setIsGameFinished] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const [isPlayerWon, setIsPlayerWon] = useState(false);
  const [showStatus, setShowStatus] = useState(false);
  

  const handleSendScore = () => {
    addScore(game, player, scores);
  }


  return (
    
    <div className='GamePage'>
    {player ? (
      <>
      <div className='gameboard_container pt-5'>
        <GameBoard player={player}
          isGameFinished={isGameFinished}
          setIsGameFinished={setIsGameFinished}
          scores={scores} setScores={setScores}
          showModal={showModal}
          setShowModal={setShowModal}
          setIsPlayerWon={setIsPlayerWon}
          handleSendScore={handleSendScore}
          setShowStatus={setShowStatus} 
          isNewGame = {isNewGame}
          setIsNewGame={setIsNewGame}
          />
          

      </div>
      <div className={`container_status ${showStatus ? 'blur' : ''}`}>
        <div className='container_box'>
          <h2 className='text-white status_game'>{isPlayerWon ? 'You win!' : 'You Lose!'}</h2>
          <h2 className='text-white score_result'>Score: {scores}</h2>
          <div className='container_button'>
            <Button variant="light" className='mt-3 largeButton' size="lg" onClick={() => setShowStatus(false)}>Close</Button>
            <Button variant="light" className='mt-3 largeButton' size="lg" onClick={() => setShowModal(true)}>New game</Button>

          </div>
        </div>
      </div>
      </>):(
        <div className={`container_status blur`}>
        <div className='container_box'>
          <h2 className='text-white login_text'>Please log in</h2>
          <div className='container_button'>
            <Button variant="light" className='mt-3 largeButtonLogin' size="lg" onClick={ handleOpenLogin}>Log in</Button>

          </div>
        </div>
      </div>
      )}
      
    </div>
  )
}

export default GamePage