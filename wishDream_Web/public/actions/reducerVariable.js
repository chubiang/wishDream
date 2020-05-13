export const REMEMBER_ID = 'REMEMBER_ID';

export function rememberID(email, password, value) {
    return {
        type: REMEMBER_ID,
        email: email,
        password: password,
        rememberValue: value
    };
}

export function signUpForm(email, username, password, repassword, allowReceive) {
    return {
        email: email,
        username: username,
        password: password,
        repassword: repassword,
        allowReceive: allowReceive
    }
}

export function signUpWithPetForm(petName, petAge, petGender, petBreeds) {
    return {
        petName: petName,
        petAge: petAge,
        petGender: petGender,
        petBreeds: petBreeds
    }
}