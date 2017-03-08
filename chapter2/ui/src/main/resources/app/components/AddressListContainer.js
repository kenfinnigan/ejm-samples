import React from 'react';
import { connect } from 'react-redux';
import AddressList from './AddressList';
import * as addressApi from '../api/address-api';
import store from '../store';

const AddressListContainer = React.createClass({

  componentDidMount: function() {
    addressApi.getAddresses();
  },

  render: function() {
    return (
      <AddressList addresses={this.props.addresses} deleteAddress={addressApi.deleteAddress} />
    );
  }

});

const mapStateToProps = function(store) {
  return {
    addresses: store.addressState.addresses
  };
};

export default connect(mapStateToProps)(AddressListContainer);
