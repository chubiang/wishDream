import React, { Component, useEffect, useState, createContext } from 'react'
import { Button, TextField , Avatar, Link, Grid, Typography } from '@material-ui/core'
import LockOutlinedIcon from '@material-ui/icons/LockOutlined'
import { makeStyles } from '@material-ui/core/styles'
import RenderCheckbox  from '../Component/RenderCheckbox'
import { Form, Field, reduxForm } from 'redux-form'
import { connect } from 'react-redux'
import Axios from 'axios'
import Querystring from 'querystring'
import { withRouter } from 'react-router'
import validateSignIn from '../services/validateSignIn'
import { rememberID } from '../actions/reducerVariable'
import Constants from '../services/constants'
import MessageDialog from '../Component/MessageDialog'


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
  loginHelperBox: {
    display: 'grid'
  },
  kakaoBtn: {
    height: '50px',
    cursor: 'pointer'
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
  let rememberValue = props.signIn.rememberValue;
  const [open, setOpen] = useState(false);
  const alertDialog = { 
    fullWidth: true, 
    maxWidth:'xs',
    title: '로그인 실패',
    content: '아이디나 비밀번호가 틀렸습니다.',
    buttons: [{color: 'primary', text: 'Close', classes: 'alertBtn'},
              {color: 'primary', text: 'Ok', classes: 'alertBtn'}]
  };
  let email = props.signIn.email;
  let password = props.signIn.password;
  const ref = React.createRef();
  
  useEffect(() => {
      
      const cookieRemember = Boolean(cookies.get('remember'));
      const cookieEmail = cookies.get('email');
      
      if (cookieEmail && cookieRemember) {
        props.dispatch(rememberID(cookieEmail, password, cookieRemember));
        console.log('props', email, rememberValue);
      }
  }, []);

  const handleChange = (event) => {
    if (event.target.id == 'email') {
      email = event.target.value;
    } else {
      password = event.target.value;
    }
  }

  // const loginKakao = () => {
  //   Axios.get(Constants.Url.member.oauth2Kakao, {
  //     headers: {
  //       "UseCookies": false,
  //       "Content-type": "application/x-www-form-urlencoded"
  //     }
  //   })
  //   .then((res) => {
  //     console.log('res', res);
  //   });
  // }

  const checkRemeberID = (event) => {
    event.preventDefault();
    
    if (!rememberValue && event.target.checked) {
      cookies.set('remember', !rememberValue, { path: "/login" });
    } else if (rememberValue && !event.target.checked) {
      cookies.remove('remember', { path: "/login" });
    }
    if (cookies.get('email') && !event.target.checked) {
      cookies.remove('email', { path: "/login" });
    }   
    props.dispatch(rememberID(email, password, event.target.checked));
  }

  function submit() {
    if (rememberValue && cookies.get('email') == undefined) {
      cookies.set('email', email, { path: "/login" });
    }
    const config = {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    };
    const sendData = Querystring.stringify({ username: email, password: password});
    if (email && password) {
      Axios.post(Constants.Url.member.login, sendData, config)
        .then((res) => {
          props.history.push("/");
        }).catch(function(error) {
          setOpen(true);
        });
    } else {
      setOpen(true);
    }
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
      <MessageDialog 
        fullWidth={alertDialog.fullWidth} 
        maxWidth={alertDialog.maxWidth}
        title={alertDialog.title} 
        content={alertDialog.content}
        handleClose={() => {setOpen(false);}}
        buttons={alertDialog.buttons}
        open={open}
        ref={ref} />
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
          <Grid item xs className={classes.loginHelperBox}>
            <Link href="#" variant="body2">
              Forgot password?
            </Link>
            <Link href="/signUp" variant="body2">
              {"Sign Up"}
            </Link>
          </Grid>
          <Grid item>
            <Link href={Constants.Url.member.oauth2Kakao} variant="body2">
              <img src="/images/kakao_login_btn_large_narrow.png" className={classes.kakaoBtn}/>
            </Link>
          </Grid>
        </Grid>
      </form>
    </div>
  )
}

const mapStateToProps = (state, ownProps) => {
  if (!state.signIn.rememberValue) {
    state.signIn.rememberValue = Boolean(ownProps.cookies.get('remember'));
  }
  if (!state.signIn.email) {
    state.signIn.email = ownProps.cookies.get('email');
  }
  return state;
};

const mapDispatchToProps  = (dispatch) => {
  
  return {
    rememberId: () => dispatch(rememberID())
  }
}

const SignInForm = reduxForm({
  form: 'signIn',
  // initialValues: initSignInForm,
  validateSignIn
})(SignIn)

export default connect(mapStateToProps, mapDispatchToProps)(withRouter(SignInForm))