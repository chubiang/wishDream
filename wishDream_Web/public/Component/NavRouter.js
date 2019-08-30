import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import Nav from 'react-bootstrap/Nav'

const NavRouter = () => {
  return (
    <Router>
      {/* <Route component={{} />*/}
      <Nav activeKey="/" as="ul">
        <Nav.Item as="li">
          <Nav.Link><Link to="/">Home</Link></Nav.Link>
        </Nav.Item>
        <Nav.Item as="li">
          <Nav.Link><Link to="/about">About</Link></Nav.Link>
        </Nav.Item>
        <Nav.Item as="li">
          <Nav.Link><Link to="/findMember">Find Member</Link></Nav.Link>
        </Nav.Item>
      </Nav>

      <Route path="/" exact component={App} />
      <Route path="/about" component={About} />
      <Route path="/findMember" component={FindMember} />
    </Router>
  );
};

export default NavRouter;
