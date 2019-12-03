// module "baseLayout.js"
import React, { Component, Fragment } from 'react'
import { Switch } from 'react-router'
import { Redirect, Link, Route } from 'react-router-dom'
import { Container, Icon, SvgIcon } from '@material-ui/core'
import { makeStyles } from '@material-ui/core/styles'
import { blue, red } from '@material-ui/core/colors'
import CssBaseline from '@material-ui/core/CssBaseline'

import layoutStyles from "styles/layout.scss"
import Header from "Component/Layout/Header"
import Footer from "Component/Layout/Footer"
import Home from 'Page/Home'
import About from 'Page/About'
import FindMember from 'Page/FindMember'


const BaseLayout = () => (
    <div className="base">
      <Header />
      <Container maxWidth="md" component="main" className={layoutStyles.App}>
        <CssBaseline />
        <Switch>
          <Route exact path="/" component={Home} />
        </Switch>
        <Footer />
      </Container>
    </div>
)

export default BaseLayout
