const initSignUpForm = {
  email: '',
  username: '',
  password: '',
  repassword: ''
};

const initSignUpWithPetForm = {
  petName: '',
	petAge: '',
	petGender: '',
	petBreedId: ''
};

export const signUpReducer = (state = initSignUpForm, action) => {
  switch(action.type) {
      case 'SIGN_UP':
          return Object.assign({}, {
              email: action.email,
              username: action.username,
              password: action.password,
              repassword: action.repassword
          });
      default:
          return state;
  }
}

export const signUpWithPetReducer = (state = initSignUpWithPetForm, action) => {
  switch(action.type) {
      case 'SIGN_UP_WITH_PET':
          return Object.assign({}, {
              petName: action.petName,
              petAge: action.petAge,
              petGender: action.petGender,
              petBreedId: action.subBreedId
          });
      default:
          return state;
  }
}