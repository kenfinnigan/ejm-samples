import { connect } from 'react-redux';
import Notification from '../components/Notification';

const mapStateToProps = (state) => {
  return {
    notification: state.categoryState.notification
  };
};

export default connect(mapStateToProps)(Notification);
