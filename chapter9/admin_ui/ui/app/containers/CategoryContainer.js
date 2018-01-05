import { connect } from 'react-redux';
import { getCategory, updateCategory } from '../actions/category-actions';
import Category from '../components/Category';

const mapStateToProps = (state, ownProps) => {
  return {
    category: state.categoryState.activeCategory,
    categoryList: state.categoryState.categoryList,
    categoryId: ownProps.id
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    getCategory: (id) => dispatch(getCategory(id)),
    updateCategory: (category) => dispatch(updateCategory(category))
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(Category);
