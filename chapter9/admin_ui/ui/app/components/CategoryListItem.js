import React, { Component } from 'react';
import { Link } from 'react-router';
import CategoryListItemRowHeader from '../components/CategoryListItemRowHeader';
import { formatDate } from '../util/date-formatter';

export default class CategoryListItem extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    const category = this.props.category;

    return (
      <CategoryListItemRowHeader category={category}>
        <td className="treegrid-node">
          <span className="icon node-icon fa fa-folder-o"></span>
          {category.name}
        </td>
        <td>{'' + category.visible}</td>
        <td>{formatDate(category.created)}</td>
        <td>{formatDate(category.updated)}</td>
        <td>
          <div className="table-view-pf-btn">
            <Link className="btn btn-info" to={'/category/' + category.id}>Details</Link>
            <span>&nbsp;&nbsp;</span>
            <button disabled={!this.props.adminRole} className="btn btn-danger" onClick={() => this.props.onDelete(category.id)}>Delete</button>
          </div>
        </td>
      </CategoryListItemRowHeader>
    );
  }
}