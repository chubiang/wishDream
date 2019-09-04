// module "app.js"
import React, { Component, Fragment } from 'react';
import { createMuiTheme, responsiveFontSizes } from '@material-ui/core/styles';
import pink from '@material-ui/core/colors/pink';
import red from '@material-ui/core/colors/red';
import BaseLayout from "Component/baseLayout";
import "styles/app.scss";
import "styles/material_font_roboto.css";
import "styles/material_icon.css";


import { BrowserRouter, Redirect, Link, Route } from 'react-router-dom';
import { Switch } from 'react-router';
/*
let theme = createMuiTheme({
  palette: {
    primary: pink,
    secondary: red,
  },
  status: {
    danger: 'orange',
  },
});
theme = responsiveFontSizes(theme);
*/
const App = () => (
  <BrowserRouter>
    <BaseLayout />
  </BrowserRouter>
);

export default App;
