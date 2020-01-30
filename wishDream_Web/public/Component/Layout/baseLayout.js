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
const url = "ws://localhost:8080/topic";
let ws = null;

function onConnect() {
  ws = new WebSocket(url);
  ws.onopen = function() {
    console.log('Info: Connection Established.');
  }
  
  ws.onclose = function(event) {
    console.log('Info: Closing Connection.');
      return false;
  };
  return true;
}

function disconnect() 
{
    if (ws != null) {
        ws.close();
        ws = null;
    }
    return false;
}

const BaseLayout = ({cookies}) => {
  const classes = useStyles();
  let connected = false;
  connected = onConnect();
  ws.addEventListener('message', function(event) {
    console.log('message from server: '+ event.data);
  })
  
  return(
      <div className="base">
        {/* <SockJsClient url={url} topics={['/topics/all']}
            onMessage={(msg) => { console.log('send to server : '+msg); }}
            onConnect={() => { connected = true; }}
            onDisconnect={() => { connected = false; }}
            debug={ false }
            ref={ (client) => { clientRef = client }} /> */}
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
