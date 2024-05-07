import React, { useState } from 'react';
import { Dropdown, Modal } from 'react-bootstrap';
import "./NewGameModal.scss"
function NewGameModal({ show, handleClose, handleNewGame, setShowStatus }) {
  const [selectedFieldSize, setSelectedFieldSize] = useState("2");
  const [selectedMode, setSelectedMode] = useState("1");
  const [selectedTile, setSelectedTile] = useState("1024");
  const [useSuperPowers, setUseSuperPowers] = useState(false);

  const fieldSizes = {
    '1': '5x5',
    '2': '4x4',
    '3': '3x3'
  };

  const modes = {
    '1': 'Default   ',
    '2': 'Obstacle',
    '3': '3x3'
  };

  const handleFieldSizeClick = (size) => {
    setSelectedFieldSize(size);
  };

  const handleGameThemeClick = (theme) => {
    setSelectedMode(theme);
  };

  const handleCreateGame = () => {
    setShowStatus(false);
    handleClose();
    handleNewGame(selectedFieldSize, selectedMode, useSuperPowers, selectedTile);
  };

  return (
    <Modal show={show} onHide={handleClose} className="new-game-modal">
      <Modal.Header closeButton>
        <Modal.Title className='text-white'>New Game</Modal.Title>
      </Modal.Header>
      <div className='content_box'>
        <Modal.Body>
          <h5 className='modal_text'>Field size: {fieldSizes[selectedFieldSize]}</h5>
          <div className="image-container mb-4">
            <img
              src="/images/field_size/3x3.png"
              alt="3x3"
              className={selectedFieldSize === '3' ? 'modal-image selected' : 'modal-image'}
              onClick={() => handleFieldSizeClick('3')}
            />
            <img
              src="/images/field_size/4x4.png"
              alt="4x4"
              className={selectedFieldSize === '2' ? 'modal-image selected' : 'modal-image'}
              onClick={() => handleFieldSizeClick('2')}
            />
            <img
              src="/images/field_size/5x5.png"
              alt="5x5"
              className={selectedFieldSize === '1' ? 'modal-image selected' : 'modal-image'}
              onClick={() => handleFieldSizeClick('1')}
            />
          </div>

          <h5 className='modal_text hz'>Game mode: {modes[selectedMode]}</h5>
          <div className="image-container">
            <img
              src="/images/field_size/8_default.jpg"
              alt="Default Mode"
              className={selectedMode === '1' ? 'modal-image selected' : 'modal-image'}
              onClick={() => handleGameThemeClick('1')}
            />
            <img
              src="/images/prison2.png"
              alt="Obstacle Mode"
              className={selectedMode === '2' ? 'modal-image selected' : 'modal-image'}
              onClick={() => handleGameThemeClick('2')}
            />

          </div>
          <div className='superpower_container'>
            <h5 className='modal_text hz'>Use superpowers:</h5>
            <input
              type="checkbox"
              checked={useSuperPowers}
              onChange={() => setUseSuperPowers(!useSuperPowers)}
              className="superpower_checkbox"
            />

          </div>
          <div className='container_choose_tile'>
          <h5 className='modal_text'>Winning tile: </h5>
          <Dropdown>
            <Dropdown.Toggle  id="dropdown-basic">
              {selectedTile}
            </Dropdown.Toggle>

            <Dropdown.Menu className='dropdown_class'>
              <Dropdown.Item onClick={()=> setSelectedTile(64)}>64</Dropdown.Item>
              <Dropdown.Item onClick={()=> setSelectedTile(128)}>128</Dropdown.Item>
              <Dropdown.Item onClick={()=> setSelectedTile(256)}>256</Dropdown.Item>
              <Dropdown.Item onClick={()=> setSelectedTile(512)}>512</Dropdown.Item>
              <Dropdown.Item onClick={()=> setSelectedTile(1024)}>1024</Dropdown.Item>
              <Dropdown.Item onClick={()=> setSelectedTile(2048)}>2048</Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>

          </div>



        </Modal.Body>
        <hr />
        <Modal.Footer>
          <div className='footer_container'>
            <button className='closebutton modal_button' onClick={handleClose}>
              Close
            </button>
            <button className='create_button modal_button' onClick={handleCreateGame}>
              Create
            </button>
          </div>

        </Modal.Footer>
      </div>
    </Modal>
  );
}

export default NewGameModal;
