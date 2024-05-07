import React from 'react'
import { Spinner } from 'react-bootstrap'
import 'semantic-ui-css/semantic.min.css'
import "./LoaderComponent.css"
const LoaderComponent = () => {
  return (
    <div className='loaderContainer'>
      <Spinner animation="border" role="status" variant="light" style={{ width: "150px", height: "150px" }}>
        <span className="visually-hidden">Loading...</span>
      </Spinner>
    </div>
  )
}

export default LoaderComponent