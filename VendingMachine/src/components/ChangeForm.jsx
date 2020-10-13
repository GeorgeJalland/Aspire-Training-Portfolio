import React, { Component } from "react";
import { Button, Container, Card } from "react-bootstrap";

class ChangeForm extends Component {
  state = {};
  render() {
    return (
      <Container>
        <h3>Change</h3>
        <Card className="changeCard">
          <Card.Body>
            Quarters:{this.props.change.quarters}, Dimes: {this.props.change.dimes}
            <br /> Nickels:{this.props.change.nickels}, Pennies: {this.props.change.pennies}
          </Card.Body>
        </Card>
        <Button onClick={() => this.props.handleChangeReturn()}>Return Change</Button>
      </Container>
    );
  }
}

export default ChangeForm;
