// module "app.js"
import React, { Component, Fragment } from 'react';
import Layout from "Component/Layout";
import "styles/app.scss"
import "styles/material_icon.css"
import Typography from '@material-ui/core/Typography';
import { Container, Icon, SvgIcon } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import { blue, red } from '@material-ui/core/colors';

class App extends Component {
  render() {
    return (
      <Container maxWidth="md" className="App">
        <Layout />
      </Container>
    );
  }
}

export default App;
