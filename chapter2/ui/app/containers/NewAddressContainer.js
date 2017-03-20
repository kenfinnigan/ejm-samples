import { connect } from 'react-redux';
import { createAddress } from '../actions/address-actions';
import NewAddress from '../components/NewAddress';

const mapDispatchToProps = (dispatch) => {
  return {
    createAddress: (address) => dispatch(createAddress(address))
  }
};

export default connect(null, mapDispatchToProps)(NewAddress);
