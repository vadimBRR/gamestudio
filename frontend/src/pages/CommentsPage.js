import React from 'react'
import { addComment, fetchComments } from '../_api/comment.service';
import Comments from '../components/comments/Comments';
import { useState, useEffect } from 'react';
import './CommentsPage.css';
import LoaderComponent from '../components/loader/LoaderComponent';
import ModalAddComment from '../components/comments/ModalAddComment';
import { Button } from 'react-bootstrap';
import StarRating from '../components/rating/StarRating';
import { addRating, fetchAvgRating, fetchRatingByPlayer } from '../_api/rating.service';


const CommentsPage = ({ player, game, handleOpenLogin }) => {
  const [comments, setComments] = useState([]);
  const [avgRating, setAvgRating] = useState(0);
  const [playerRating, setPlayerRating] = useState(0);

  const [loading, setLoading] = useState(true);
  const [showModal, setShowModal] = useState(false);
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);  


  const handleResize = () => {
    setWindowWidth(window.innerWidth);   
  };

  const fetchCommentsData = () => {
    fetchComments(game).then((res) => setComments(res.data));

  }
  const fetchRatingData = () => {
    fetchAvgRating(game).then((res) => {
      setAvgRating(res.data);
    });
  }
  const fetchRatingDataByPlayer = () => {
    fetchRatingByPlayer(game, player).then((res) => {
      setPlayerRating(res.data);
    });
  }
  useEffect(() => {

    setLoading(true);
    fetchCommentsData();
    fetchRatingData();
    if (avgRating !== 0) {
      fetchRatingDataByPlayer();
    }
    setTimeout(() => setLoading(false), 500);

    window.addEventListener('resize', handleResize);
    return () => {
      window.removeEventListener('resize', handleResize);   
    };
  }, []);

  const handleSendComment = (comment) => {
    addComment(game, player, comment)
      .then(res => {
        fetchCommentsData();
      })
  }
  const handleSendRating = (rating) => {
    if(player ===''){
      handleOpenLogin();
    }else{
      addRating(game, player, rating)
      .then(res => {
        fetchRatingData();
        fetchRatingDataByPlayer();
      })
    }
    

  }
  if (loading) {
    return <LoaderComponent />;
  }

  if (comments.length === 0) {
    return (
      <div className='noResultContainer'>
        <div className='largeText'>No comments yet :(</div>
        {player === "" ?
          <Button variant="light" className='largeButton' onClick={handleOpenLogin}>Add Comment</Button>
          :
          <Button variant="light" className='largeButton' onClick={() => setShowModal(true)}>Add Comment</Button>
        }
        <ModalAddComment show={showModal} handleClose={() => setShowModal(false)} handleAddComment={handleSendComment} />
      </div>
    );
  }

  return (
    <>
      <div className='CommentsPage'>
        <div className='pageBlock'>
          <div className='containerComment'>
            <div className='headerComment'>
              <div className='header_container'>
                <h2 className='mb-3 text-white main_title'>Comments:</h2>

                {windowWidth > 689 && <StarRating avgRating={avgRating} playerRating={playerRating} handleSendRating={handleSendRating} />}
                {player === "" ?
                  <img src='/images/plus-square.svg' height={40} className='add_comment mb-3' alt='Write the comment' onClick={handleOpenLogin} />
                  :
                  <img src='/images/plus-square.svg' height={40} className='add_comment mb-3' alt='Write the comment' onClick={() => setShowModal(true)} />
                }
              </div>
              {windowWidth < 689 && <StarRating avgRating={avgRating} playerRating={playerRating} handleSendRating={handleSendRating} />}

            </div>
            <Comments comments={comments} />
          </div>
        </div>
      </div>
      <ModalAddComment show={showModal} handleClose={() => setShowModal(false)} handleAddComment={handleSendComment} />
    </>
  );
};

export default CommentsPage;