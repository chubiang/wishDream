// module "NavRouter.js"
import React, { Component, Fragment } from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";
import Link from '@material-ui/core/Link';
import Home from 'home';
import About from 'about';
import FindMember from 'findMember';

const NavRouter = () => {
  return (
    <Router>
      {/* <Route component={{} />*/}
      <div>
        <Link to="/home">Home</Link>
        <Link to="/about">About</Link>
        <Link to="/findMember">Find Member</Link>
      </div>

      <Route path="/home" exact component={Home} />
      <Route path="/about" component={About} />
      <Route path="/findMember" component={FindMember} />
    </Router>
  );
};

export default NavRouter;
