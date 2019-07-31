import React, { Component, Fragment } from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

import "babel-polyfill";
import "./styles/app.scss"


class App extends Component {
  render() {



    return (
      <Router>
        <Container className="App">
          
          {/* <Route component={{} />*/}
        </Container>
      </Router>
    );
  }
}

export default App;
