import React from 'react';
import './GameBoard.css';

const Tile = ({ value, moved, doubled, is_new,selectedGameTheme, size,rowIdx, colIdx, isChooseTile,handleUsePerkWithCord}) => {
  const tileClass = value !== -1 ? `tile size${size} tile${value}_${selectedGameTheme} ${is_new ? 'new' : ''} ${doubled ? 'doubled' : ''} ${moved ? 'moving' : ''} ${isChooseTile ? 'choosing' : ''} ` : `tile size${size} tile_obstacle `;
  const handleChooseTile=()=>{
    handleUsePerkWithCord(rowIdx, colIdx);
  }
  return (
    <>

    {isChooseTile?(
      <div className={tileClass} onClick={handleChooseTile}>
    </div>
    ):(
      <div className={tileClass} >
    </div>
    )}
    </>
    
  );
};

export default Tile;
