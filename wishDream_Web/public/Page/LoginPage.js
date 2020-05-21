import React, { Component } from 'react';
import { BrowserRouter, Redirect, Link, Route } from 'react-router-dom';
import { Switch } from 'react-router';
import { CookiesProvider } from 'react-cookie';
import { withCookies } from 'react-cookie';
import { createStore, combineReducers } from 'redux';
import { Provider } from 'react-redux';
import { createMuiTheme } from '@material-ui/core/styles';
import { ThemeProvider } from '@material-ui/styles';
import { purple, pink } from '@material-ui/core/colors';
import SignInForm from './SignInForm';
import SignUpForm from './SignUpForm';
import { signInReducer } from '../reducers/signIn';
import { signUpReducer } from '../reducers/signUp';


const rootReducer = combineReducers({
    signIn: signInReducer,
    signUp: signUpReducer
})

const store = (window.devToolsExtension
  ? window.devToolsExtension()(createStore)
  : createStore)(rootReducer)

export const GlobalTheme = createMuiTheme({
	palette: {
		primary: purple,
		secondary: pink
	}
})

class LoginPage extends Component {
    render() {
        return (
            <BrowserRouter>
							<Provider store={store}>
                <ThemeProvider theme={GlobalTheme}>
									<CookiesProvider>
                    <Switch>
                        <Route path="/login"
                        render={ () => (<SignInForm cookies={this.props.cookies} />)}/>
                        <Route path="/signUp"
                        render={ () => (<SignUpForm cookies={this.props.cookies} />)}/>
                        <Route path="/" component={() => { window.location.href = "/"; return null; }} />
                    </Switch>
                    </CookiesProvider>
									</ThemeProvider>
                </Provider>
            </BrowserRouter>
        )
    }
}

export default withCookies(LoginPage)
