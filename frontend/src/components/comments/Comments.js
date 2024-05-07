import React from 'react'
import './Comments.css';
import { formatDateToString } from '../../_api/utils';

const Comments = ({ comments }) => {
  return (
    <>

      {comments.map(comment => (
        <div key={comment.ident} className='container_card'>
          <div className='header_card'>
            <span className='player_name'>
              {comment.player}
            </span>
            <div>
              {formatDateToString(new Date(comment.commentedOn))}
            </div>
          </div>
          <hr/>
          <div className='footer_card'>
            {comment.comment}
          </div>
        </div>
      ))}
    </>
  )
}

export default Comments