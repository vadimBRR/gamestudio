import React, { useEffect, useState } from 'react';
import './App.css';
import GamePage from './pages/GamePage';
import { Route, Routes } from 'react-router-dom';
import Navbar from './components/navbar/Navbar';
import CommentsPage from './pages/CommentsPage';
import LeaderBoard from './pages/LeaderBoardPage';
import NotFoundPage from './pages/NotFoundPage';
import LoginModal from './components/loginwindow/LoginModal';

function App() {
  const game = "game_1024";
  const [isNewGame, setIsNewGame] = useState(false);
  const [showLogin, setShowLogin] = useState(false); 
  const [player, setPlayer] = useState(() => {
    const storedPlayer = localStorage.getItem('player');
    return storedPlayer ? JSON.parse(storedPlayer) : "";
  });
  
  useEffect(() => {
    localStorage.setItem('player', JSON.stringify(player));
  }, [player]);

  const handleOpenLogin = () => {
    if (player === "") {
      setShowLogin(true);
    }
  };

  const handleCloseLogin = () => {
    setShowLogin(false);
  };

  const handleLogin = (name) => {
    setPlayer(name);
    handleOpenLogin();
    if(name!==""){
      setIsNewGame(true);
    }
  };

  return (
    <div className='App'>
      <Navbar player={player} handleLogin={handleLogin}/>
      <Routes>
        <Route path='/' element={<GamePage player={player} game = {game} handleOpenLogin={handleOpenLogin} isNewGame={isNewGame} setIsNewGame={setIsNewGame}/>} />
        <Route path='/comments' element={<CommentsPage player={player} game = {game} handleOpenLogin={handleOpenLogin}/>} />
        <Route path='/leaderboard' element={<LeaderBoard game = {game}/>}  />
        <Route path='*' element={<NotFoundPage/>} />
      </Routes>
      <LoginModal show={showLogin} handleClose={handleCloseLogin} handleLogin={handleLogin} />
    </div>
  );
}

export default App;
