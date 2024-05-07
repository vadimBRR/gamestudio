import React from 'react'
import { useState, useEffect } from 'react';
import './LeaderBoardPage.css';
import LoaderComponent from '../components/loader/LoaderComponent';
import {   fetchScores } from '../_api/scores.service';
import LeaderBoard from '../components/leaderboard/LeaderBoard';



const LeaderBoardPage = ({game}) => {
  const [pointsList, setPointsList] = useState([])
  const [loading, setLoading] = useState(true);


  const fetchScoresData = () => {
    fetchScores(game).then((res) => setPointsList(res.data))
  }

 
  useEffect(() => {
    setLoading(true);
    fetchScoresData();

    setTimeout(() => {
      setLoading(false);
    }, 500);

  }, []);

  if (loading) {
    return <LoaderComponent />
  }
  if (pointsList.length === 0) {
    return (
      <div className='noResultContainer '>
        <div className='largeText'>No game played yet :(</div>
      </div>
    )
  }
  return (
    <>

      <div className='LeaderBoardPage '>

        <div className='pageBlockPlayers'>

          <div className='containerPlayers'>
            <h2 className='mb-3 text-white leaderboard_title'>LeaderBoard</h2>
            <LeaderBoard scores={pointsList} />
          </div>
        </div>
      </div>
    </>



  )
}

export default LeaderBoardPage