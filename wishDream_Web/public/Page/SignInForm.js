// module "SignIn.js"
import React, { Component, Fragment } from 'react'
import Avatar from '@material-ui/core/Avatar'
import { Button, TextField, FormControl, FormGroup, FormControlLabel } from '@material-ui/core'
import Checkbox from '@material-ui/core/Checkbox'
import Link from '@material-ui/core/Link'
import Grid from '@material-ui/core/Grid'
import LockOutlinedIcon from '@material-ui/icons/LockOutlined'
import Typography from '@material-ui/core/Typography'
import { makeStyles } from '@material-ui/core/styles'
import RendorCheckbox from '../Component/RenderCheckbox'
import { Field, reduxForm } from 'redux-form'
import validateSignIn from '../services/validateSignIn'


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

function SignIn() {
  const classes = useStyles();
  const [state, setState] = React.useState({
    remember: {value: true, lable: 'Remember ID'},
  });
  const { remember } = state;
  
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
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="email"
            label="Email Address"
            name="email"
            autoComplete="email"
            autoFocus
          />
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            autoComplete="current-password"
          />
          {/* <Field name="remeber" component={} label="Remember" /> */}
          <RendorCheckbox value={remember.value} label={remember.lable}/>
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