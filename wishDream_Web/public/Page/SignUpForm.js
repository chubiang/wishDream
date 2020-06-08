import React, { useEffect, useState, createRef, createContext } from 'react'
import { Avatar, Button, TextField, Link, Grid, Box, 
  Typography, CssBaseline, Container, FormHelperText } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import { connect } from 'react-redux'
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router';
import Axios from 'axios';
import Querystring from 'querystring'
import { signUp, signUpWithPet } from '../actions/reducerVariable'
import validateSignIn from '../services/validateSignIn';
import RenderCheckbox  from '../Component/RenderCheckbox'
import WithPetForm  from '../Component/WithPetForm'
import Constants from '../services/constants';
import MessageDialog from '../Component/MessageDialog'
import "core-js/modules/es.promise"
import { format } from 'date-fns';

function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {'Copyright © '}
      <Link color="inherit" href="#">
        WishDream
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(3),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));


export const AlertContext = createContext();
function SignUp(props) {
  const classes = useStyles();
  const [userInfo, setUserInfo] = useState(props.signUp);
  const [withPet, setWithPet] = useState(false);
  const [petInfo, setPetInfo] = useState(props.signUpWithPet);
  const ref = createRef();
  const [open, setOpen] = useState(false);
  const [hasError, setHasError] = useState(false);
  const [alert, setAlert] = useState({ 
    fullWidth: true, 
    maxWidth:'xs',
    title: 'Invalid',
    content: 'Please enter required values.',
    buttons: [{color: 'primary', text: 'Ok', classes: 'alertBtn'}]
  });

  const config = {
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
    }
  };

  useEffect(() => {
    if (petInfo.petBreedId) setHasError(false);
    console.log('effect', userInfo, petInfo);
  })

  function handleSubmit(e) {
    e.preventDefault();
    props.onSignUp(userInfo);
    let sendData = userInfo;
    if (!!withPet) {
      if (!petInfo.petBreedId) setHasError(true);
      if (userInfo.repassword != userInfo.password) {
        alert.title = 'Invalid'
        alert.content = 'Reconfirm password is different from password.'
        setOpen(true);
        return;
      }
      
      petInfo.petBirth = format(petInfo.petBirth, 'yyyy-MM-dd');
      props.onSignUpWithPet(petInfo);
      
      sendData = Object.assign(userInfo, petInfo);
    }
    console.log(userInfo, petInfo);
    Axios.post(Constants.Url.member.join, Querystring.stringify(sendData), config)
    .then(function(res) {
      // props.history.push("/login");
    })
    .catch(function(error) {
      alert.title = error.response.data.exception;
      alert.content = error.response.data.message;
      alert.maxWidth = 'sm';
      setOpen(true);
    })
  }

  const checkPetInfo = () => (
    <RenderCheckbox
        name="withPet"
        label="With My Pet Information" 
        value={withPet}
        change={() => {setWithPet(event.target.checked);}} />
  )

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <MessageDialog 
          fullWidth={alert.fullWidth} 
          maxWidth={alert.maxWidth}
          title={alert.title} 
          content={alert.content}
          handleClose={() => {setOpen(false);}}
          buttons={alert.buttons}
          open={open}
          ref={ref} />
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign up
        </Typography>
        <form className={classes.form} onSubmit={handleSubmit}>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                autoComplete="fname"
                name="email"
                type="email"
                variant="outlined"
                required
                fullWidth
                id="Email Address"
                label="Email"
                onChange={(e)=>{userInfo.email = e.target.value}}
                autoComplete="email"
                autoFocus
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="username"
                label="Username"
                onChange={(e)=>{userInfo.username = e.target.value}}
                name="username"
                autoComplete="username"
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                onChange={(e)=>{userInfo.password = e.target.value}}
                id="password"
                autoComplete="new-password"
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                name="repassword"
                label="Repassword"
                type="password"
                onChange={(e)=>{userInfo.repassword = e.target.value}}
                id="repassword"
                autoComplete="confirm-password"
              />
            </Grid>
            <Grid item xs={12}>
                <Field name="withPet" component={checkPetInfo} />
            </Grid>
            { withPet ? <WithPetForm hasError={hasError} petInfo={petInfo} /> : null }
          </Grid>
          <Button
            fullWidth
            type="submit"
            variant="contained"
            color="primary"
            className={classes.submit}
          >
            Sign Up
          </Button>
          <Grid container justify="center">
            <Grid item>
              <Link href="/login" variant="body2">
                Already have an account? Sign in
              </Link>
            </Grid>
          </Grid>
        </form>
      </div>
      <Box mt={5}>
        <Copyright />
      </Box>
    </Container>
  )
}

const mapStateToProps = (state, ownProps) => {
   console.log(state, ownProps);
  return state;
}

const mapDispatchToProps = dispatch => {
  return {
    onSignUp: (userInfo) => dispatch(signUp(userInfo)),
    onSignUpWithPet: (petInfo) => dispatch(signUpWithPet(petInfo))
  }
}

const SignUpForm = reduxForm({ 
  form: 'signUp', 
  validateSignIn 
})(SignUp)

export default connect(mapStateToProps, mapDispatchToProps)(withRouter(SignUpForm))