import { connect } from 'react-redux';
import { getCategories, deleteCategory } from '../actions/category-actions';
import CategoryList from '../components/CategoryList';

const mapStateToProps = (state) => {
  return {
    categoryList: state.categoryState.categoryList,
    adminRole: state.securityState.adminRole
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    getCategories: () => dispatch(getCategories()),
    deleteCategory: (id) => dispatch(deleteCategory(id))
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(CategoryList);
