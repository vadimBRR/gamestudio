import React, { useEffect, useRef, useState } from 'react';
import { fetchField, fetchNewGame, move, UseAbitilites, UseAbitilitesWithCord } from '../../_api/game.service';
import Tile from './Tile';
import NewGameModal from './NewGameModal';
import "./GameBoard.css";
import LoaderComponent from '../loader/LoaderComponent';
import ThemesButton from './ThemesButton';
import { useSwipeable } from 'react-swipeable';

function GameBoard({ player, setIsGameFinished, isGameFinished, scores, setScores, showModal, setShowModal, setIsPlayerWon, handleSendScore, setShowStatus, isNewGame, setIsNewGame }) {
  const [field, setField] = useState([]);
  const [perks, setPerks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [isChooseTile, setIsChooseTile] = useState(false);
  const [numberPerk, setNumberPerk] = useState(-1);
  const isGameFinishedRef = useRef(isGameFinished);
  const [isSendedScore, setIsSendedScore] = useState(() => {
    const storedIsSendedScore = localStorage.getItem('isSendedScore');
    return storedIsSendedScore ? JSON.parse(storedIsSendedScore) : false;
  });
  useEffect(() => {
    localStorage.setItem('isSendedScore', JSON.stringify(isSendedScore));
  }, [isSendedScore]);

  const [selectedGameTheme, setSelectedGameTheme] = useState(() => {
    return localStorage.getItem('selectedGameTheme') || 'theme2';
  });

  useEffect(() => {
    localStorage.setItem('selectedGameTheme', selectedGameTheme);
  }, [selectedGameTheme]);

  useEffect(() => {
    isGameFinishedRef.current = isGameFinished;
    if (isGameFinishedRef.current === true) setShowStatus(true);

    if (isGameFinishedRef.current && !isSendedScore) {
      setIsSendedScore(true);
      handleSendScore();
    }
  }, [isGameFinished]);


  const [useSuperPowers, setUseSuperPowers] = useState(() => {
    const storedUseSuperPowers = localStorage.getItem('useSuperPowers');
    return storedUseSuperPowers ? JSON.parse(storedUseSuperPowers) : false;
  });

  useEffect(() => {
    localStorage.setItem('useSuperPowers', useSuperPowers);
  }, [useSuperPowers]);

  const fetchData = () => {
    fetchField().then((res) => {
      handleResponse(res);
    });
  };

  useEffect(() => {
    fetchData();
    setTimeout(() => {
      setLoading(false);
    }, 500);
  }, []);


  const handleResponse = (res) => {
    setField(res.data.tiles);
    setIsGameFinished(res.data.gameFinished);
    setScores(res.data.scores);
    setIsPlayerWon(res.data.playerWon);
    setPerks(res.data.perks);
    
  };

  const handleUsePerk = async (number) => {

    if (!isGameFinishedRef.current && perks.includes(number)) {
      const resp = await UseAbitilites(number);
      handleResponse(resp);
    }
  }

  const handleUsePerkWithCord = async (row, col) => {
    if (!isGameFinishedRef.current) {
      const resp = await UseAbitilitesWithCord(numberPerk, row, col);
      handleResponse(resp);
      setNumberPerk(-1);
      setIsChooseTile(!isChooseTile);
    }
  }

  const chooseTile = (number) => {
    if (!isGameFinishedRef.current && perks.includes(number)) {
      setNumberPerk(number);
      setIsChooseTile(!isChooseTile);
    }
  }



  const handleKeyDown = async (event) => {
    if (!isGameFinishedRef.current ) {
      switch (event.key) {
        case 'ArrowUp':
        case 'W':
        case 'w':
          const moveResponseUp = await move('UP');
          handleResponse(moveResponseUp);
          break;
        case 'ArrowDown':
        case 'S':
        case 's':
          const moveResponseDown = await move('DOWN');
          handleResponse(moveResponseDown);
          break;
        case 'ArrowLeft':
        case 'A':
        case 'a':
          const moveResponseLeft = await move('LEFT');
          handleResponse(moveResponseLeft);
          break;
        case 'ArrowRight':
        case 'D':
        case 'd':
          const moveResponseRight = await move('RIGHT');
          handleResponse(moveResponseRight);
          break;
        default:
          break;
      }
    }
  };


  const handlers = useSwipeable({
    onSwipedUp: () => handleSwipes('UP'),
    onSwipedDown: () => handleSwipes('DOWN'),
    onSwipedLeft: () => handleSwipes('LEFT'),
    onSwipedRight: () => handleSwipes('RIGHT'),
    trackMouse: true 
  });

  const handleSwipes = async (direction) => {
    if (!isGameFinishedRef.current) {
      const moveResponse = await move(direction);
      handleResponse(moveResponse);
    }
  };

  const handleNewGame = async (selectedFieldSize, selectedMode, useSuperPowersValue, selectedTile) => {
    setShowModal(false);
    setIsSendedScore(false);
    setUseSuperPowers(useSuperPowersValue);
    setIsChooseTile(false);
    const newGameResponse = await fetchNewGame(selectedFieldSize, selectedMode, selectedTile, useSuperPowersValue);
    handleResponse(newGameResponse);
  };


  useEffect(() => {
    if (isNewGame) {
      setLoading(true);
      handleNewGame(2, 1, false, 1024);
      setIsNewGame(false); 
      setShowStatus(false);

      setTimeout(() => {
        setLoading(false);
      }, 1000);
    }
  }, [isNewGame]);


  useEffect(() => {
    window.addEventListener('keydown', handleKeyDown);
    return () => {
      window.removeEventListener('keydown', handleKeyDown);
    };
  }, []);



  if (loading) {
    return <LoaderComponent />
  }
  const s = field.map((row, rowIdx) => {
    return (
      <div key={`row-${rowIdx}`} className='board_row'>
        {row.map((col, colIdx) => {
          return (
            <div key={`-${rowIdx}-${colIdx}`} className={`cell size${field.length}`}>
              {col.value !== 0 && <Tile value={col.value} doubled={col.doubled} moved={col.moved} is_new={col.new} selectedGameTheme={selectedGameTheme} size={field.length} rowIdx={rowIdx} colIdx={colIdx} isChooseTile={isChooseTile} handleUsePerkWithCord={handleUsePerkWithCord} />}
            </div>
          );
        })}
      </div>
    );
  });

  return (
    <>

      <div {...handlers} className={`gameboard `}>
        <div className='components_container'>
          {useSuperPowers &&
            <div className={`abilities_container ${useSuperPowers ? 'showAbilities' : ''}`}>
              <div className='abilities_btn'>
                <img src='/images/abilities_images/sort_field.png' alt='sort field' width={60} className={`pb-2 ${perks.includes(1) ? 'active_perk' : ''}`} onClick={() => handleUsePerk(1)} />
                <img src='/images/abilities_images/double_tile.png' alt='double the tile' width={60} className={`pb-2 ${perks.includes(2) && 'active_perk'}`} onClick={() => chooseTile(2)} />
                <img src='/images/abilities_images/replace_with_eight.png' alt='replace with eight' width={60} className={`pb-2 ${perks.includes(3) && 'active_perk'}`} onClick={() => handleUsePerk(3)} />
                <img src='/images/abilities_images/delete_tile.png' alt='delete the tile' width={60} className={`pb-2 ${perks.includes(0) && 'active_perk'}`} onClick={() => chooseTile(0)} />
              </div>
            </div>
          }

          <div className='container_game'>
            <div className='container_scores'>
              <div>{player}</div>
              <div>{scores}</div>
            </div>
            <div className='container_board'>
              <div className='border_field '>
                <div className={`field `}>
                  {s}
                </div>
              </div>
              {useSuperPowers &&
                <div className={`abilities_container_phone ${useSuperPowers ? 'showAbilities' : ''}`}>
                  <div className='abilities_btn_phone'>
                    <img src='/images/abilities_images/sort_field.png' alt='sort field' className={`${perks.includes(1) ? 'active_perk' : ''}`} onClick={() => handleUsePerk(1)} />
                    <img src='/images/abilities_images/double_tile.png' alt='double the tile' className={`${perks.includes(2) && 'active_perk'}`} onClick={() => chooseTile(2)} />
                    <img src='/images/abilities_images/replace_with_eight.png' alt='replace with eight' className={`${perks.includes(3) && 'active_perk'}`} onClick={() => handleUsePerk(3)} />
                    <img src='/images/abilities_images/delete_tile.png' alt='delete the tile' className={`${perks.includes(0) && 'active_perk'}`} onClick={() => chooseTile(0)} />
                  </div>
                </div>
              }
              <button className='button_newgame' size="lg" onClick={() => setShowModal(true)}>New game</button>
            </div>
          </div>
        </div>


        <ThemesButton selectedGameTheme={selectedGameTheme} setSelectedGameTheme={setSelectedGameTheme} />
      </div>
      <NewGameModal show={showModal} handleClose={() => setShowModal(false)} handleNewGame={handleNewGame} setShowStatus={setShowStatus} />
    </>
  );
}

export default GameBoard;
