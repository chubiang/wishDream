// module "SignIn.js"
import React, { Component, useEffect } from 'react'
import Avatar from '@material-ui/core/Avatar'
import { Button, TextField } from '@material-ui/core'
import Link from '@material-ui/core/Link'
import Grid from '@material-ui/core/Grid'
import LockOutlinedIcon from '@material-ui/icons/LockOutlined'
import Typography from '@material-ui/core/Typography'
import { makeStyles } from '@material-ui/core/styles'
import RenderCheckbox  from '../Component/RenderCheckbox'
import { Form, Field, reduxForm } from 'redux-form'
import validateSignIn from '../services/validateSignIn'
import { rememberID } from '../actions/login'
import { connect } from 'react-redux'
import axios from 'axios'
import Constants from '../services/constants'
import querystring from 'querystring'
import { withRouter } from 'react-router'

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
  loginText: {
    margin: '8px 0'
  }
}));
const rememberLabel = "Remember ID";
const inputField = ({input, label, defaultValue, type, meta: {touched, error}}) => {
  const classes = useStyles();
  return(
    <div>
      <TextField
        id={input.name}
        name={input.name}
        defaultValue={defaultValue}
        onChange={input.onChange}
        className={classes.loginText}
        color="secondary" 
        label={label}
        type={type}
        fullWidth />
        { touched && (<span>{error}</span>)}
    </div>
  )
};
function SignIn(props) {
  const classes = useStyles();
  const { cookies, error, handleSubmit, pristine, reset, submitting } = props;

  let email = props.email;
  let password = props.password;
  let rememberValue = props.rememberValue;
  
  useEffect(() => {
      
      const cookieRemember = Boolean(cookies.get('remember'));
      const cookieEmail = cookies.get('email');
      if (cookieRemember && !email) {
        props.store.dispatch(rememberID(cookieEmail, password, cookieRemember));
      }
  });

  const handleChange = (event) => {
    if (event.target.id == 'email') {
      email = event.target.value;
    } else {
      password = event.target.value;
    }
  }
  
  const checkRemeberID = (event) => {
    event.preventDefault();
    
    if (!email) {
      event.target.checked = false;
      return;
    }
    const value = props.store.getState().rememberValue;
    if (!value && cookies.get('email') == undefined) {
      cookies.set('email', email, { path: "/login" });
      cookies.set('remember', !rememberValue, { path: "/login" });
      event.target.checked = true;
    } else if (value && cookies.get('email')) {
      cookies.remove('email', { path: "/login" });
      cookies.remove('remember', { path: "/login" });
      event.target.checked = false;
    }
    props.store.dispatch(rememberID(email, password, value));
  }

  function submit() {
    const config = {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    };
    const sendData = querystring.stringify({ username: email, password: password, 'X-XSRF-TOKEN': cookies.get('XSRF-TOKEN')});
    
    axios.post(Constants.Url.member.login, sendData, config)
      .then((res) => {
        props.history.push("/");
 
    });
  }

  const rememberField = () => (
    <RenderCheckbox 
      name="rememberValue"
      label={rememberLabel} 
      value={rememberValue}
      change={checkRemeberID} />
  );

  return (
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign In
        </Typography>
        <form className={classes.form} onSubmit={handleSubmit(submit)}>
          <Field
            id="email" name="email" type="email" label="Email"
            defaultValue={email}
            component={inputField}
            onChange={handleChange}
          /><br/>
          <Field
            id="password" name="password" type="password" label="Password"
            defaultValue ={password}
            component={inputField}
            onChange={handleChange}
          />
          <Field name="remeberValue" component={rememberField} />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            disabled={submitting}
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

const mapStateToProps = (state, ownProps) => {
  state.rememberValue = Boolean(ownProps.cookies.get('remember'));
  state.email = ownProps.cookies.get('email');
  return state;
};

const SignInForm = reduxForm({
  form: 'signIn',
  // initialValues: initSignInForm,
  validateSignIn
})(SignIn)

export default connect(mapStateToProps)(withRouter(SignInForm))