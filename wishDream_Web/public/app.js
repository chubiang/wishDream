// module "app.js"
import React from 'react';
import { createMuiTheme, responsiveFontSizes } from '@material-ui/core/styles';
import pink from '@material-ui/core/colors/pink';
import red from '@material-ui/core/colors/red';
import BaseLayout from "Component/Layout/BaseLayout";
import "styles/app.scss";
import "styles/material_font_roboto.css";
import "styles/material_icon.css";


import { BrowserRouter } from 'react-router-dom';
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
