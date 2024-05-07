import React from 'react';
import { Modal, Form } from 'react-bootstrap';
import { useForm } from "react-hook-form";
import './ModalAddComment.css';   

function ModalAddComment({ show, handleClose, handleAddComment }) {
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset
  } = useForm({mode: "onChange"});

  const onSubmit = (data) => {
    handleAddComment(data.comment);
    handleClose();
    reset();  
  };

  return (
    <Modal show={show} onHide={handleClose} >
      <Modal.Header closeButton>
        <Modal.Title>Add Comment</Modal.Title>
      </Modal.Header>
      <div className='content_box'>
      <Modal.Body>
        <Form onSubmit={handleSubmit(onSubmit)}>
          <Form.Group>
            <Form.Control
              as="textarea"
              rows={3}
              {...register("comment", {
                required: "Comment is required",
                minLength: {
                  value: 3,
                  message: "Comment must be at least 3 characters long",
                },
                maxLength: {
                  value: 100,
                  message: "Comment must be no more than 100 characters long",
                }
              })}
              placeholder="Type your comment here..."
              isInvalid={errors.comment}
              className="comment-input"
            />
            <Form.Control.Feedback type="invalid">
              {errors.comment?.message}
            </Form.Control.Feedback>
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
      <div className='footer_container'>

        <button className='closebutton modal_button' onClick={handleClose}>
          Close
        </button>
        <button className='create_button modal_button' type="submit" onClick={handleSubmit(onSubmit)}>
          Add
        </button>
        </div>
      </Modal.Footer>
      </div>
    </Modal>
  );
}

export default ModalAddComment;
