// module "NavRouter.js"
import React, { Component, Fragment } from 'react';
import { BrowserRouter as Router, Route, NavLink } from "react-router-dom";
import Nav from 'react-bootstrap/Nav';
import App from 'app';
import About from 'about';
import FindMember from 'findMember';

const NavRouter = () => {
  return (
    <Router>
      {/* <Route component={{} />*/}
      <Nav as="ul">
        // <Nav.Item as="li">
        //   <Nav.Link><NavLink to="/" activeClassName="active">Home</NavLink></Nav.Link>
        // </Nav.Item>
        <Nav.Item as="li">
          <Nav.Link><NavLink to="/about">About</NavLink></Nav.Link>
        </Nav.Item>
        <Nav.Item as="li">
          <Nav.Link><NavLink to="/findMember">Find Member</NavLink></Nav.Link>
        </Nav.Item>
      </Nav>

      <Route path="/" exact component={App} />
      <Route path="/about" component={About} />
      <Route path="/findMember" component={FindMember} />
    </Router>
  );
};

export default NavRouter;
