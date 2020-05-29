export const REMEMBER_ID = 'REMEMBER_ID';

export function rememberID(email, password, rememberValue) {
    return {
        type: REMEMBER_ID,
        email: email,
        password: password,
        rememberValue: rememberValue,
    };
}

export function signUp(userInfo) {
    return {
        type: 'SIGN_UP',
        email: userInfo.email,
        username: userInfo.username,
        password: userInfo.password,
        rePassword: userInfo.rePassword
    }
}

export function signUpWithPet(petInfo) {
    return {
        type: 'SIGN_UP_WITH_PET',
        petName: petInfo.petName,
        petAge: petInfo.petAge,
        subBreedId: petInfo.subBreedId,
        petGender: petInfo.petGender,
        petBirth: petInfo.petBirth
    }
}