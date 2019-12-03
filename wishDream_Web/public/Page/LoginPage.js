import React, { Component } from 'react';
import { BrowserRouter, Redirect, Link, Route } from 'react-router-dom';
import { Switch } from 'react-router'
import CssBaseline from '@material-ui/core/CssBaseline'
import SignInForm from './SignInForm';

class LoginPage extends Component {
    render() {
        return (
            <BrowserRouter>
                <Switch>
                    <Route path="/login" component={SignInForm} />
                </Switch>
            </BrowserRouter>
        )
    }
}

export default LoginPage