import React, { Component, useEffect, useState, createContext } from 'react'
import { Avatar, Button, TextField, Link, Grid, Box, 
  Typography, CssBaseline, Container } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import { connect } from 'react-redux'
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router';
import { signUp, signUpWithPet } from '../actions/reducerVariable'
import validateSignIn from '../services/validateSignIn';
import RenderCheckbox  from '../Component/RenderCheckbox'
import WithPetForm  from '../Component/WithPetForm'

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

function SignUp(props) {
  const classes = useStyles();

  const [userInfo, setUserInfo] = React.useState({});
  const [withPet, setWithPet] = React.useState(false);

  const handleSubmit = () => {

  };

  const showWithPetForm = (event) => {
    console.log(event, event.target.checked);
    setWithPet(event.target.checked);
    
  }

  const checkBoxAllowReceive = () => (
    <RenderCheckbox
        name="withPet"
        label="Add my pet information" 
        value={withPet}
        change={showWithPetForm} />
  )

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
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
                id="repassword"
                autoComplete="confirm-password"
              />
            </Grid>
            <Grid item xs={12}>
                <Field name="withPet" component={checkBoxAllowReceive} />
            </Grid>
            { withPet ? <WithPetForm /> : null }
          </Grid>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
          >
            Sign Up
          </Button>
          <Grid container justify="flex-end">
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
    'signUp': () => dispatch(signUp()),
    'signUpWithPet': () => dispatch(signUpWithPet())
  }
}

const SignUpForm = reduxForm({ 
  form: 'signUp', 
  validateSignIn 
})(SignUp)

export default connect(mapStateToProps, mapDispatchToProps)(withRouter(SignUpForm))