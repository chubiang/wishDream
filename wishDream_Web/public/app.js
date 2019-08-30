import "styles/app.scss"
import NavRouter from "Component/NavRouter";

class App extends Component {
  render() {
    return (
      <Container className="App">
        <NavRouter />
      </Container>
    );
  }
}

export default App;
