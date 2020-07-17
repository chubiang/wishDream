// module "baseLayout.js"
import React, { Component, Fragment, Suspense, lazy } from 'react'
import SockJsClient from 'react-stomp'
import { Switch } from 'react-router'
import { Redirect, Link, Route } from 'react-router-dom'
import { Container, Icon, SvgIcon } from '@material-ui/core'
import { makeStyles } from '@material-ui/core/styles'
import CssBaseline from '@material-ui/core/CssBaseline'

import Header from "Component/Layout/Header"
import Footer from "Component/Layout/Footer"
import About from 'Page/About'
import FindMember from 'Page/FindMember'
import LoadingBar from '../LoadingBar'

const Home = lazy(() => import('Page/Home'));

const useStyles = makeStyles({
  app: {
      marginTop: 80
  }
});

const BaseLayout = ({cookies, history}) => {
  const classes = useStyles();

  return(
      <div className="base">
        {/* <SockJsClient url={url} topics={['/topics/all']}
            onMessage={(msg) => { console.log('send to server : '+msg); }}
            onConnect={() => { connected = true; }}
            onDisconnect={() => { connected = false; }}
            debug={ false }
            ref={ (client) => { clientRef = client }} /> */}
        <Header cookies={cookies} history={history}  />
        <Container maxWidth="md" component="main" className={classes.app}>
          <CssBaseline />
          <div className="content">
            <Suspense fallback={<div>Loading...</div>}>
              <Switch>
                <Route exact path="/" component={Home} cookies={cookies} />
              </Switch>
            </Suspense>
          </div>
        </Container>
        <Footer />
      </div>
  )
}

export default BaseLayout
