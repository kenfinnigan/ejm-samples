import { connect } from 'react-redux';
import { getAddresses, deleteAddress } from '../actions/address-actions';
import AddressList from '../components/AddressList';

const mapStateToProps = (state) => {
  return {
    addressList: state.addressState.addressList,
    adminRole: state.securityState.adminRole
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    getAddresses: () => dispatch(getAddresses()),
    deleteAddress: (id) => dispatch(deleteAddress(id))
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(AddressList);
