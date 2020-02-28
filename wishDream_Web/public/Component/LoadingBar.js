import React, { Component, Fragment, useEffect } from 'react'
import Backdrop from '@material-ui/core/Backdrop';
import CircularProgress from '@material-ui/core/CircularProgress';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles(theme => ({
    backdrop: {
      zIndex: theme.zIndex.drawer + 1,
      color: '#fff',
    },
  }));

const LoadingBar = ({loading}) => {
    const classes = useStyles();
    console.log(!!loading?  loading: true);
    
    return (
        <Backdrop className={classes.backdrop} open={(!!loading?  loading: true)}>
          <CircularProgress color="inherit" />
        </Backdrop>
    )
}

export default LoadingBar