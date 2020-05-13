import React, { Component } from 'react';
import { BrowserRouter, Redirect, Link, Route } from 'react-router-dom';
import { Switch } from 'react-router';
import SignInForm from './SignInForm';
import { CookiesProvider } from 'react-cookie';
import { withCookies } from 'react-cookie';
import { createStore, combineReducers } from 'redux';
import { Provider } from 'react-redux';
import { signInReducer } from '../reducers/signIn';
import { signUpReducer } from '../reducers/signUp';


const rootReducer = combineReducers({
    signIn: signInReducer,
    signUp: signUpReducer
})

const store = (window.devToolsExtension
  ? window.devToolsExtension()(createStore)
  : createStore)(rootReducer)

class LoginPage extends Component {
    render() {
        return (
            <BrowserRouter>
                <Provider store={store}>
                    <CookiesProvider>
                    <Switch>
                        <Route path="/login"
                        render={ () => (<SignInForm cookies={this.props.cookies} />)}/>
                        <Route path="/signUp"
                        render={ () => (<SignUpForm cookies={this.props.cookies} />)}/>
                        <Route path="/" component={() => { window.location.href = "/"; return null; }} />
                    </Switch>
                    </CookiesProvider>
                </Provider>
            </BrowserRouter>
        )
    }
}

export default withCookies(LoginPage);
