import React, { Component } from 'react';
import AddressForm from './AddressForm';

export default class Address extends Component {
  constructor(props) {
    super(props);

    this.state = {
      address: this.props.address,
      saving: false
    };

    this.handleFormSubmit = this.handleFormSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  componentWillMount() {
    this.props.getAddress(this.props.addressId);
  }

  componentWillReceiveProps(nextProps) {
    if (!this.state.address || this.state.address.id != nextProps.address.id) {
      this.setState({address: nextProps.address});
    }
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

    this.props.updateAddress(this.state.address);
  }

  render() {
    if (!this.state.address) {
      return null;
    }

    return (
      <div className="panel panel-default">
        <div className="panel-heading">
          <h3 className="panel-title">Address Detail</h3>
        </div>
        <AddressForm address={this.state.address} onSave={this.handleFormSubmit} onChange={this.handleChange} saving={this.state.saving} />
      </div>
    );
  }
}
