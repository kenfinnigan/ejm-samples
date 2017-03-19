import React, { Component } from 'react';
import AddressForm from './AddressForm';

export default class Address extends Component {
  constructor(props) {
    super(props);

    this.state = {
      address: {
        id: '',
        firstLine: '',
        secondLine: '',
        city: '',
        state: ''
      },
      saving: false
    };

    this.handleFormSubmit = this.handleFormSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(e) {
    const field = e.target.name;
    const address = this.state.address;
    address[field] = e.target.value;

    this.setState({
      address: address
    });
  }

  handleFormSubmit(e) {
    e.preventDefault();
    this.setState({saving: true});

    this.props.createAddress(this.state.address);
  }

  render() {
    return (
      <div className="panel panel-default">
        <div className="panel-heading">
          <h3 className="panel-title">Create Address</h3>
        </div>
        <AddressForm address={this.state.address} onSave={this.handleFormSubmit} onChange={this.handleChange} saving={this.state.saving} />
      </div>
    );
  }
}
