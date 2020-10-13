import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import Items from "./components/Items";
import DepositForm from "./components/DepositForm";
import PurchaseForm from "./components/PurchaseForm";
import ChangeForm from "./components/ChangeForm";

const SERVICE_URL = "http://tsg-vending.herokuapp.com/";

class App extends React.Component {
  state = {
    balance: 0,
    message: "",
    change: {
      quarters: 0,
      dimes: 0,
      nickels: 0,
      pennies: 0,
    },
    items: [
      {
        id: "",
        name: "",
        price: "",
        quantity: "",
      },
    ],
  };

  loadItems() {
    fetch(SERVICE_URL + "items")
      .then((data) => data.json())
      .then((data) => this.setState({ items: data }));
  }

  componentDidMount() {
    console.log("App is now mounted.");
    this.loadItems();
  }

  changeReturn = () => {
    let bal = this.state.balance;
    let numQuart = Math.floor(bal / 0.25);
    bal -= numQuart * 0.25;
    let numDimes = Math.floor(bal / 0.1);
    bal -= numDimes * 0.1;
    let numNickels = Math.floor(bal / 0.05);
    bal -= numNickels * 0.05;
    let numPennies = Math.floor(bal / 0.01);
    bal -= numPennies * 0.01;
    let newChange = {
      quarters: numQuart,
      dimes: numDimes,
      nickels: numNickels,
      pennies: numPennies,
    };
    this.setState({ change: newChange, balance: 0 });
  };

  purchaseItem = (itemId) => {
    console.log("purchase");
    fetch(SERVICE_URL + "money/" + this.state.balance + "/item/" + itemId, {
      method: "POST",
    }).then((response) => {
      if (response.status === 200) {
        this.setState({ message: "Thank you!", balance: 0 });
        response
          .json()
          .then((data) => {
            this.loadItems();
            this.setState({
              change: data,
            });
          })
          .catch((error) => {
            console.log("Add Contact - Error:", error);
          });
      } else {
        response
          .json()
          .then((data) => {
            this.setState({
              message: data.message,
            });
            this.loadItems();
          })
          .catch((error) => {
            console.log("Add Contact - Error:", error);
          });
      }
    });
  };

  addBalance = (coin) => {
    let x = this.state.balance;
    switch (coin) {
      case "dollar":
        x += 1;
        break;
      case "quarter":
        x += 0.25;
        break;
      case "dime":
        x += 0.1;
        break;
      case "nickel":
        x += 0.05;
        break;
    }
    this.setState({ balance: x });
  };

  render() {
    console.log("App is rendering.");
    return (
      <Container fluid className="main">
        <Row>
          <Col>
            <h1 style={{ marginTop: "10px", marginBottom: "0px", fontWeight: "bold" }}>Vending Machine</h1>
          </Col>
        </Row>
        <hr className="hr" />
        <Row>
          <Col sm={8}>
            <Items items={this.state.items} />
          </Col>
          <Col sm={4}>
            <Container>
              <Row style={{ height: "176px", justifyContent: "center" }}>
                <DepositForm balance={this.state.balance} handleDeposit={this.addBalance} />
              </Row>
              <hr className="hr" />
              <Row style={{ height: "176px", justifyContent: "center" }}>
                <PurchaseForm
                  balance={this.state.balance}
                  message={this.state.message}
                  handlePurchase={this.purchaseItem}
                />
              </Row>
              <hr className="hr" />
              <Row style={{ height: "176px" }}>
                <ChangeForm change={this.state.change} handleChangeReturn={this.changeReturn} />
              </Row>
            </Container>
          </Col>
        </Row>
      </Container>
    );
  }
}

export default App;
