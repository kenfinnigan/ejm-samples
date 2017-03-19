import { connect } from 'react-redux';
import { getAddress, updateAddress } from '../actions/address-actions';
import Address from '../components/Address';

const mapStateToProps = (state, ownProps) => {
  return {
    address: state.addressState.activeAddress,
    addressId: ownProps.id
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    getAddress: (id) => dispatch(getAddress(id)),
    updateAddress: (address) => dispatch(updateAddress(address))
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(Address);
