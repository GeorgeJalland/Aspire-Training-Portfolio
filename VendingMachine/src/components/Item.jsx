import React from "react";
import { Card } from "react-bootstrap";

class Item extends React.Component {
  state = {};
  render() {
    return (
      <Card className="itemCard">
        <Card.Body>
          <Card.Title className="identifier"> {this.props.uni}</Card.Title>
          <Card.Title style={{ fontWeight: "bold" }}>{this.props.product}</Card.Title>
          <Card.Text>
            ${this.props.price}
            <br />
          </Card.Text>
          <Card.Text>Quantity left: {this.props.quantity}</Card.Text>
        </Card.Body>
      </Card>
    );
  }
}

export default Item;
