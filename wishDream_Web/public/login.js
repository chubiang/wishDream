import React from 'react';
import ReactDOM from 'react-dom';
import LoginPage from './Page/LoginPage'
import { Provider } from 'react-redux'
import { createStore, combineReducers } from 'redux';
import { reducer as reduxFormReducer } from 'redux-form';


ReactDOM.render(<LoginPage />, document.getElementById('loginPage'));
