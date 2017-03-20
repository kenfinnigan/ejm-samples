import { connect } from 'react-redux';
import Loading from '../components/Loading';

const mapStateToProps = (state) => {
  return {
    loading: state.addressState.loading
  };
};

export default connect(mapStateToProps)(Loading);
