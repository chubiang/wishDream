// module "baseLayout.js"
import React, { Component, Fragment } from 'react'
import SockJsClient from 'react-stomp'
import { Switch } from 'react-router'
import { Redirect, Link, Route } from 'react-router-dom'
import { Container, Icon, SvgIcon } from '@material-ui/core'
import { makeStyles } from '@material-ui/core/styles'
import { blue, red } from '@material-ui/core/colors'
import CssBaseline from '@material-ui/core/CssBaseline'

import AppStyles from "styles/app.scss"
import Header from "Component/Layout/Header"
import Footer from "Component/Layout/Footer"
import Home from 'Page/Home'
import About from 'Page/About'
import FindMember from 'Page/FindMember'

const useStyles = makeStyles({
  app: {
      marginTop: 80
  }
});

const BaseLayout = ({cookies}) => {
  const classes = useStyles();
  
  return(
      <div className="base">
        {/* <SockJsClient url='http://localhost:8080/eventEmitter' topics={['/topics/all']}
            onMessage={(msg) => { console.log(msg); }} /> */}
        <Header cookies={cookies} />
        <Container maxWidth="md" component="main" className={classes.app}>
          <CssBaseline />
          <div className="content">
            <Switch>
              <Route exact path="/" component={Home} cookies={cookies} />
            </Switch>
          </div>
        </Container>
        <Footer />
      </div>
  )
}

export default BaseLayout
