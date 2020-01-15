// module "app.js"
import React from 'react';
import { CookiesProvider, withCookies } from 'react-cookie';
import { BrowserRouter } from 'react-router-dom';
import { createMuiTheme } from '@material-ui/core/styles'
import purple from '@material-ui/core/colors/purple'
import pink from '@material-ui/core/colors/pink'

import BaseLayout from "Component/Layout/BaseLayout";
import "styles/app.scss";
import "styles/material_font_roboto.css";
import "styles/material_icon.css";


const theme = createMuiTheme({
  palette: {
    primary: purple,
    secondary: pink
  }
});


const App = ({cookies}) => (
  <BrowserRouter>
    <CookiesProvider>
    <BaseLayout cookies={cookies} />
    </CookiesProvider>
  </BrowserRouter>
);

export default withCookies(App);
