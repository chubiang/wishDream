import React from 'react';
import ReactDOM from 'react-dom';
import CssBaseline from '@material-ui/core/CssBaseline';
import { ThemeProvider } from '@material-ui/styles';

import App from 'app';
import { amber, deepPurple } from '@material-ui/core/colors';
import { createMuiTheme } from '@material-ui/core';

const theme = createMuiTheme({
    palette: {
      primary: deepPurple,
      secondary: amber,
    },
    status: {
      danger: 'orange',
    },
  });


ReactDOM.render(
    <ThemeProvider theme={theme}>
        <App />
    </ThemeProvider>
, document.getElementById('root'));
