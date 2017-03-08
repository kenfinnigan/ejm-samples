import React from 'react';
import { connect } from 'react-redux';
import Address from './Address';
import * as addressApi from '../api/address-api';

class AddressContainer extends React.Component {

  componentDidMount() {
    let addressId = this.props.params.addressId
    addressApi.getAddress(addressId);
  }

  render() {
    return (
      <Address {...this.props.address} />
    );
  }

}

const mapStateToProps = function(store) {
  return {
    address: store.addressState.address
  };
};

export default connect(mapStateToProps)(AddressContainer);
