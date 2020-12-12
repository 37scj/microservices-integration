import React from 'react';
import './App.css';

const overall = {
  position: 'fixed',
  top: 0,
  bottom: 0,
  left: 0,
  right: 0,
  backgroundColor: 'rgba(0,0,0,0.3)',
}
function SuspenseApp() {
  return (
    <div className="App" style={overall}>
      Carregando página...
    </div>
  );
}


export default SuspenseApp;
