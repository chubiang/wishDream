export const REMEMBER_ID = 'REMEMBER_ID';

export function rememberID(email, value) {
    return {
        type: REMEMBER_ID,
        email: email,
        remember: { value: value } 
    };
}