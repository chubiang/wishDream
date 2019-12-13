export const REMEMBER_ID = 'REMEMBER_ID';

export function rememberID(email, password, value) {
    return {
        type: REMEMBER_ID,
        email: email,
        password: password,
        rememberValue: value
    };
}