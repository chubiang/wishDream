// module "baseLayout.js"
import React, { Component, Fragment } from 'react';
import { Switch } from 'react-router';
import { Redirect, Link, Route } from 'react-router-dom';
import Typography from '@material-ui/core/Typography';
import { Container, Icon, SvgIcon } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import { blue, red } from '@material-ui/core/colors';

import layoutStyles from "styles/layout.scss";
import Header from "Component/header";
import Home from 'Home/home';
import About from 'About/about';
import FindMember from 'FindMember/findMember';


const BaseLayout = () => (
    <div className="base">
      <Header />
      <Container maxWidth="md" className={layoutStyles.App}>
        <main role="main">
        <Switch>
          <Route exact path="/" component={Home} />
          // <Route path="/about" component={About} />
          // <Route path="/findMember" component={FindMember} />
          {/* Not Found */}
          // <Route component={() => <Redirect to="/" />} />
        </Switch>
        </main>
      </Container>
    </div>
);

export default BaseLayout;
