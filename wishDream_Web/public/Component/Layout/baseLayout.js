// module "baseLayout.js"
import React, { Component, Fragment } from 'react'
import SockJsClient from 'react-stomp'
import { Switch } from 'react-router'
import { Redirect, Link, Route } from 'react-router-dom'
import { Container, Icon, SvgIcon } from '@material-ui/core'
import { makeStyles } from '@material-ui/core/styles'
import CssBaseline from '@material-ui/core/CssBaseline'

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
  let connected = false;
  let clientRef;
  const sendMessage = (msg) => {
    console.log('clientRef', clientRef);
    
  }
  
  return(
      <div className="base">
        <SockJsClient url='http://localhost:8080/topic' topics={['/alarm']}
            onMessage={(msg) => { console.log('send to server : '+msg); }}
            onConnect={() => { connected = true; }}
            onDisconnect={() => { connected = false; }}
            debug={ false }
            ref={ (client) => { clientRef = client }} />
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
