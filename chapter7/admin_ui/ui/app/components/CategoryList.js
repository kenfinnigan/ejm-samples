import React, { Component } from 'react';
import { Link } from 'react-router';
import CategoryListItem from '../components/CategoryListItem';

export default class CategoryList extends Component {
  constructor(props) {
    super(props);

    this.state = {
      formattedTable: false
    };

    this.deleteCategory = this.deleteCategory.bind(this);
  }

  componentWillMount() {
    this.props.getCategories();
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.categoryList.length === 0) {
      this.props.getCategories();
    }
  }

  componentDidUpdate(prevProps, prevState) {
    if (prevProps.categoryList.length === 0) {
      $(this.refs.categoryTree).treegrid();
    }
  }

  deleteCategory(id) {
    this.props.deleteCategory(id);
  }

  createCategory(category) {
    return <CategoryListItem category={category} key={category.id} onDelete={this.deleteCategory} adminRole={this.props.adminRole} />;
  }

  render() {
    return (
      <div>
        <h1>Categories</h1>
        {/*
        <div className="table-view-pf-btn pull-right">
          <Link className="btn btn-info" to={'/category/new'}>Create Category</Link>
        </div>
        */}
        <table ref="categoryTree" className="table table-bordered table-hover table-treegrid">
          <thead>
            <tr>
              <th>Category Name</th>
              <th>Visible</th>
              <th>Created</th>
              <th>Updated</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {this.props.categoryList.map(this.createCategory, this)}
          </tbody>
        </table>
      </div>
    );
  }
}
