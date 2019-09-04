// module "NavRouter.js"
import React, { Component, Fragment } from 'react';

import { BrowserRouter, Redirect, Link, Route } from 'react-router-dom';
import { Switch } from 'react-router';

import { makeStyles } from '@material-ui/core/styles';
import { AppBar, Toolbar, Typography, Button, IconButton, MenuIcon } from '@material-ui/core';

import Home from 'Home/home';
import About from 'About/about';
import FindMember from 'FindMember/findMember';

class NavRouter extends Component {
    render() {
      const classes = makeStyles(theme => ({
        root: {
          flexGrow: 1,
        },
        menuButton: {
          marginRight: theme.spacing(2),
        },
        title: {
          flexGrow: 1,
        },
      }));

      return (
        <div className={classes.root}>
          <AppBar position="static">
            <Toolbar>
              <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
                <MenuIcon />
              </IconButton>
              <Typography variant="h6" className={classes.title}>
                <Link to="/">Home</Link>
              </Typography>
              <Button color="inherit">Login</Button>
            </Toolbar>
          </AppBar>
        </div>
      );
  };
}

export default NavRouter;
