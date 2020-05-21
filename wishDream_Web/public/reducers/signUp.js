const initSignUpForm = {
  email: '',
  username: '',
  password: '',
  repassword: '',
  allowReceive: false
};

export const signUpReducer = (state = initSignUpForm, action) => {
  switch(action.type) {
      case 'SIGN_UP':
          return Object.assign({}, {
              email: action.email,
              username: action.username,
              password: action.password,
              repassword: action.repassword,
              withPet: action.withPet
          });
      case 'PET':
          return Object.assign({}, {
              petName: action.petName,
              petAge: action.petAge,
              petGender: action.petGender,
              petBreeds: action.petBreeds
          });
      default:
          return state;
  }
}