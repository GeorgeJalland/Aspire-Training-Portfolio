import React, { Component } from "react";
import { Button, Container, Card } from "react-bootstrap";

class PurchaseForm extends Component {
  state = {
    itemSelection: "",
  };

  render() {
    return (
      <Container>
        <h3>Messages</h3>
        <Card className="depoCard">
          <Card.Body>{this.props.message}</Card.Body>
        </Card>
        <div style={{ marginTop: "10px", fontSize: "20px" }}>
          Item:
          <input
            style={{ marginLeft: "10px" }}
            type="number"
            placeholder="Select an item"
            onChange={(event) => this.setState({ itemSelection: event.target.value })}
          />
        </div>
        <Button onClick={() => this.props.handlePurchase(this.state.itemSelection)}>Purchase</Button>
      </Container>
    );
  }
}

export default PurchaseForm;
