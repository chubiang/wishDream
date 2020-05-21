export const REMEMBER_ID = 'REMEMBER_ID';

export function rememberID(email, password, rememberValue) {
    return {
        type: REMEMBER_ID,
        email: email,
        password: password,
        rememberValue: rememberValue,
    };
}

export function signUp() {
    return {
        type: 'SIGN_UP'
    }
}

export function signUpWithPet() {
    return {
        type: 'SIGN_UP_WITH_PET'
    }
}