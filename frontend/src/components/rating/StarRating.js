import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import "bootstrap-icons/font/bootstrap-icons.css";
import "./StartRating.css"

const StarRating = ({ avgRating, playerRating, handleSendRating }) => {
  const [hover, setHover] = useState(null);

  const renderStarIcon = (index, rating) => {
    if (hover !== null) {
      return index < hover ? "bi-star-fill" : "bi-star";
    }
    return index < Math.round(rating) ? "bi-star-fill" : "bi-star";
  };

  const renderColor = (index) => {
    if (hover !== null) {
      return index <= hover ? 'gold' : 'none';  
    }
    if(Math.round(playerRating) > Math.round(avgRating)){
       if (index <= Math.round(avgRating)) {
        return 'orange';  
      }else if (index <= Math.round(playerRating)) {
        return 'gold';  
      }
    }else if(Math.round(playerRating) <= Math.round(avgRating)){
      if (index <= Math.round(playerRating)) {
        return 'gold';  
      } else if (index <= Math.round(avgRating)) {
        return 'orange';  
      }
    }

    return 'none'; 
  };


  return (
    <div>
      <div className='container_start pb-2'>
      {[...Array(5)].map((star, index) => {
        const ratingValue = index + 1;

        return (
          <div key={ratingValue} onMouseEnter={() => setHover(ratingValue)} onMouseLeave={() => setHover(null)} onClick={() => handleSendRating(ratingValue)}>
            <i 
              style={{
                cursor: 'pointer',
                transition: 'color 200ms',
                color: renderColor(ratingValue) === 'none' ? 'gray' : renderColor(ratingValue),
                stroke: 'red',
                strokeWidth: renderStarIcon(index, Math.max(avgRating, playerRating)) === "bi-star-fill" ? '0' : '15px' // Adjust stroke width for filled stars
              }} 
              className={`bi ${renderStarIcon(index, Math.max(avgRating, playerRating))} stars_icon`}
            ></i>
          </div>
        );
      })}
    </div>
    </div>
    
  );
};

export default StarRating;
