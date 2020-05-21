export const initSignInForm = {
    email: '',
    password: '',
    rememberValue: false
};

export const signInReducer = (state = initSignInForm, action) => {
    // console.log('reducer',state,action);
    
    switch(action.type) {
        case 'REMEMBER_ID': 
            return Object.assign({}, {
                email: action.email,
                password: action.password,
                rememberValue: action.rememberValue
            });
        default:
            return state;
    }
}