import React, { Component } from 'react';
import { BrowserRouter, Redirect, Link, Route } from 'react-router-dom';
import { Switch } from 'react-router'
import CssBaseline from '@material-ui/core/CssBaseline'
import SignInForm from './SignInForm';
import { CookiesProvider } from 'react-cookie';
import { withCookies } from 'react-cookie';
import { createStore } from 'redux';
import { Provider } from 'react-redux';
import { signInReducer } from '../reducers/login';

const store = createStore(signInReducer);
class LoginPage extends Component {

    componentDidMount() {
      console.log(store);
    }
    
    render() {
        return (
            <CookiesProvider>
                <BrowserRouter>
                    <Provider store={store}>
                        <Switch>
                            <Route path="/login"
                            render={ () => (<SignInForm cookies={this.props.cookies} store={store} />)}/>
                        </Switch>
                    </Provider>
                </BrowserRouter>
            </CookiesProvider>
        )
    }
}

export default withCookies(LoginPage);
