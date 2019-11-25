import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Redirect, Link, Route } from 'react-router-dom';
import { Switch } from 'react-router'
import CssBaseline from '@material-ui/core/CssBaseline'
import SignIn from './Page/SignIn';

const elements = (
    <BrowserRouter>
        <CssBaseline />
        <Switch>
            <Route exact path="/login" component={SignIn} />
        </Switch>
    </BrowserRouter>
);

ReactDOM.render(elements, document.getElementById('loginPage'));
