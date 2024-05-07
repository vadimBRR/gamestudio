import React from 'react';
import { Modal, Form } from 'react-bootstrap';
import { useForm } from "react-hook-form";
import './LoginModal.scss';   

function LoginModal({ show, handleClose, handleLogin }) {
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset
  } = useForm({mode: "onChange"});

  const onSubmit = (data) => {
    handleLogin(data.username);
    handleClose();
    reset();   
  };

  return (
    <Modal show={show} onHide={handleClose} >
      <Modal.Header closeButton>
        <Modal.Title>Login</Modal.Title>
      </Modal.Header>
      <div className='content_box'>
      <Modal.Body>
        <Form onSubmit={handleSubmit(onSubmit)}>
          <Form.Group>
            <Form.Control
              {...register("username", {
                required: "Username is required",
                minLength: {
                  value: 3,
                  message: "Name must be at least 3 characters long",
                },
                maxLength: {
                  value: 10,
                  message: "Name must be no more than 10 characters long",
                }
              })}
              placeholder="Write the name"
              isInvalid={errors.username}
              className="login-input"
              autocomplete="off"
            />
            <Form.Control.Feedback type="invalid">
              {errors.username?.message}
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
          Log in
        </button>
        </div>
      </Modal.Footer>
      </div>
    </Modal>
  );
}

export default LoginModal;
