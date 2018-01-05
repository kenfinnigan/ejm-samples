import React, { Component } from 'react';
import { Link } from 'react-router';

export default class CategoryListItemRowHeader extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    const category = this.props.category;

    const parentId = "#parent-" + category.parent;
    const id = "parent-" + category.id;
    const hasParent = category.parent != null;

    if (hasParent) {
      return (
        <tr id={id} key={category.id} className="collapsed" data-parent={parentId}>
          {this.props.children}
        </tr>
      );
    }

    return (
      <tr id={id} key={category.id}>
        {this.props.children}
      </tr>
    );
  }
}