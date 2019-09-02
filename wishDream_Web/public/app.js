// module "app.js"
import React, { Component, Fragment } from 'react';
import NavRouter from "Component/NavRouter";
import "styles/app.scss"

class App extends Component {
  render() {
    return (
      <div className="App">
        <NavRouter />
      </div>
    );
  }
}

export default App;
