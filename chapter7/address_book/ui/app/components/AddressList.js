import React, { Component } from 'react';
import { Link } from 'react-router';

export default class AddressList extends Component {
  constructor(props) {
    super(props);

    this.deleteAddress = this.deleteAddress.bind(this);
  }

  componentWillMount() {
    this.props.getAddresses();
  }

  deleteAddress(id) {
    this.props.deleteAddress(id);
  }

  renderAddresses(addresses) {
    return addresses.map(address => {
      return (
        <tr key={address.id}>
          <td>{address.firstLine}</td>
          <td>{address.secondLine}</td>
          <td>{address.city}</td>
          <td>{address.state}</td>
          <td>
            <div className="table-view-pf-btn">
              <Link className="btn btn-info" to={'/address/' + address.id}>Details</Link>
              <span>&nbsp;&nbsp;</span>
              <button disabled={!this.props.adminRole} className="btn btn-danger" onClick={() => this.deleteAddress(address.id)}>Delete</button>
            </div>
          </td>
        </tr>
      );
    });
  }

  render() {
    return (
      <div>
        <h1>Addresses</h1>
        <div className="table-view-pf-btn pull-right">
          <Link className="btn btn-info" to={'/address/new'}>Create Address</Link>
        </div>
        <table id="addressTable" className="table table-striped table-bordered table-hover">
          <thead>
            <tr>
              <th>First Line</th>
              <th>Second Line</th>
              <th>City</th>
              <th>State</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {this.renderAddresses(this.props.addressList)}
          </tbody>
        </table>
      </div>
    );
  }
}
