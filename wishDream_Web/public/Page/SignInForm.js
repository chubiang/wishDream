// module "SignIn.js"
import React, { Component, Fragment, useState, useEffect } from 'react'
import Avatar from '@material-ui/core/Avatar'
import { Button, TextField, FormControl, FormGroup, FormControlLabel } from '@material-ui/core'
import Checkbox from '@material-ui/core/Checkbox'
import Link from '@material-ui/core/Link'
import Grid from '@material-ui/core/Grid'
import LockOutlinedIcon from '@material-ui/icons/LockOutlined'
import Typography from '@material-ui/core/Typography'
import { makeStyles } from '@material-ui/core/styles'
import RenderCheckbox  from '../Component/RenderCheckbox'
import { Field, reduxForm } from 'redux-form'
import validateSignIn from '../services/validateSignIn'
import { initSignInForm } from '../reducers/login';

const useStyles = makeStyles(theme => ({
  '@global': {
    body: {
      backgroundColor: theme.palette.common.white,
    },
  },
  paper: {
  width: '480px',
  paddingTop: '80px',
    margin: '0 auto',
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
    marginTop: theme.spacing(1),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
    height: '50px'
  },
}));

function SignIn(props) {
  const classes = useStyles();
  const [state, setState] = useState(initSignInForm);
  const [password, setPassword] = useState('');
  const { cookies } = props;



  useEffect(() => {
      console.log(props, cookies, props.store.getState());

      // if (cookies.get('remember')) {
        // state.email = cookies.get('emailCookie');
        // state.remember.value = cookies.get('rememberCookie');
      // }
  });
  
  const checkRemeberID = (event) => {
    if (state.remember.value && !cookies.get('email')) {
      cookies.set('email', state.email, { path: "/login" });
      cookies.set('remember', state.remember.value, { path: "/login" });
    } else if (state.remember.value && cookies.get('email').length) {
      props.store.dispatch(rememberID(cookies.get('email'), cookies.get('remember')));
    }
    console.log(props.store.getState().email, cookies, cookies.get('email'));
  }
  
  const handleSubmit = (event) => {
    event.preventDefault();
  }

  const handleChange = name => event => {
    console.log(name);
    
  }

  return (
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign In
        </Typography>
        <form className={classes.form} onSubmit={handleSubmit} noValidate>
          <Field
            name="email"
            component={TextField}
            value={props.store.getState().email}
            placeholder="Email"
          /><br/>
          <Field
            name="password"
            component={TextField}
            value={password}
            placeholder="password"
          />
          {/* <Field name="remeber" component={} label="Remember" /> */}
          <RenderCheckbox value={props.store.getState().remember.value} label={props.store.getState().remember.label} change={checkRemeberID}/>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
          >
            Sign In
          </Button>
          <Grid container>
            <Grid item xs>
              <Link href="#" variant="body2">
                Forgot password?
              </Link>
            </Grid>
            <Grid item>
              <Link href="#" variant="body2">
                {"Don't have an account? Sign Up"}
              </Link>
            </Grid>
          </Grid>
        </form>
      </div>
  )
}

const SignInForm = reduxForm({
  form: 'SignIn',
  validateSignIn
})(SignIn)

export default SignInForm;