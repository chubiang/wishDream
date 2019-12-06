import { REMEMBER_ID } from '../actions/login';

export const initSignInForm = {
    remember: {value: false, label: 'Remember ID'},
    email : ''
};

export const signInReducer = (state = initSignInForm, action) => {
    switch(action.type) {
        case REMEMBER_ID: 
            return Object.assign({}, state, {
                remember: { 
                    value: action.remember.value || state.remember.value, 
                    lable: state.remember.label
                },
                email : action.email,
            });
        default:
            return state;
    }
}