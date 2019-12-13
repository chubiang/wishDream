import React, { Component } from 'react';
import { BrowserRouter, Redirect, Link, Route } from 'react-router-dom';
import { Switch } from 'react-router'
import CssBaseline from '@material-ui/core/CssBaseline'
import SignInForm from './SignInForm';
import { CookiesProvider } from 'react-cookie';
import { withCookies } from 'react-cookie';
import { createStore, combineReducers } from 'redux';
import { Provider } from 'react-redux';
import { signInReducer } from '../reducers/login';

const store = (window.devToolsExtension
  ? window.devToolsExtension()(createStore)
  : createStore)(signInReducer);
class LoginPage extends Component {
    
    render() {
        return (
            <BrowserRouter>
                <Provider store={store}>
                    <CookiesProvider>
                    <Switch>
                        <Route path="/login"
                        render={ () => (<SignInForm cookies={this.props.cookies} store={store} />)}/>
                    </Switch>
                    </CookiesProvider>
                </Provider>
            </BrowserRouter>
        )
    }
}

export default withCookies(LoginPage);
