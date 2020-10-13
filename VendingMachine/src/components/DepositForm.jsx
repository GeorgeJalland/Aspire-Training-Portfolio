import React, { Component } from "react";
import { Button, Container, Card } from "react-bootstrap";

class DepositForm extends Component {
  render() {
    return (
      <Container>
        <h3>Total $ In </h3>
        <Card className="depoCard">
          <Card.Body>{this.props.balance.toFixed(2)}</Card.Body>
        </Card>
        <Button onClick={() => this.props.handleDeposit("dollar")}>Add Dollar</Button>
        <Button onClick={() => this.props.handleDeposit("quarter")}>Add Quarter</Button>
        <Button onClick={() => this.props.handleDeposit("dime")}>Add Dime</Button>
        <Button onClick={() => this.props.handleDeposit("nickel")}>Add Nickel</Button>
      </Container>
    );
  }
}

export default DepositForm;
