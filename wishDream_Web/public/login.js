import React from 'react';
import ReactDOM from 'react-dom';
import LoginPage from './Page/LoginPage'
import { Provider } from 'react-redux'
import { createStore, combineReducers } from 'redux';
import { reducer as reduxFormReducer } from 'redux-form';


const store = (window.devToolsExtension
    ? window.devToolsExtension()(createStore)
    : createStore)(combineReducers({
        form: reduxFormReducer, // mounted under "form"
      }));

ReactDOM.render(<Provider store={store}><LoginPage /></Provider>, document.getElementById('loginPage'));
