import React from 'react';
import ReactDOM from 'react-dom';
import CssBaseline from '@material-ui/core/CssBaseline';
import { ThemeProvider } from '@material-ui/styles';

import App from 'app';
import { amber, deepPurple } from '@material-ui/core/colors';
import { createMuiTheme } from '@material-ui/core/styles';

export const GlobalTheme = createMuiTheme({
    palette: {
      primary: deepPurple,
      secondary: amber,
      whiteFont: 'rgba(0, 0, 0, 0.85)',
      whiteIcon: '#ede7f6'
    },
    status: {
      danger: 'orange',
    },
    overrides: {
      MuiToolbar: {
        root: {
          backgroundColor: '#512da8',
          color: '#ede7f6'
        }
      },
      MuiDialog: {
        paper: {
          backgroundColor: '#fff',
          color: 'rgba(0, 0, 0, 0.85)'
        }
      },
    }
});

ReactDOM.render(
    <ThemeProvider theme={GlobalTheme}>
        <App />
    </ThemeProvider>
, document.getElementById('root'));
