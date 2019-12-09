import { REMEMBER_ID } from '../actions/login';

export const initSignInForm = {
    remember: {value: false, label: 'Remember ID'},
    email : ''
};

export const signInReducer = (state = initSignInForm, action) => {
    var rememberValue = state.remember.value;
    switch(action.type) {
        case REMEMBER_ID: 
            if (action.remember) rememberValue = action.remember.value;
            return Object.assign({}, state, {
                remember: { 
                    value: rememberValue, 
                    lable: state.remember.label
                },
                email : action.email,
            });
        default:
            return state;
    }
}