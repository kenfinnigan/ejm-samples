import { connect } from 'react-redux';
import { createCategory } from '../actions/category-actions';
import NewCategory from '../components/NewCategory';

const mapStateToProps = (state, ownProps) => {
  return {
    categoryList: state.categoryState.categoryList
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    createCategory: (category) => dispatch(createCategory(category))
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(NewCategory);
