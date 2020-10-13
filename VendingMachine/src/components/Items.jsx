import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import Item from "./Item";
import "../App.css";

class Items extends React.Component {
  render() {
    return (
      <Container fluid>
        <Row className="d-flex">
          {this.props.items.map((item, i) => {
            return (
              <Col className="" sm={4}>
                <Item uni={item.id} product={item.name} price={item.price} quantity={item.quantity} />
              </Col>
            );
          })}
        </Row>
      </Container>
    );
  }
}

export default Items;
