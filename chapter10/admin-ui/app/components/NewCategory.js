import React, { Component } from 'react';
import CategoryForm from './CategoryForm';

export default class Category extends Component {
  constructor(props) {
    super(props);

    this.state = {
      category: {
        name: '',
        header: '',
        imagePath: '',
        visible: null,
        parent: ''
      },
      saving: false
    };

    this.handleFormSubmit = this.handleFormSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(e) {
    const field = e.target.name;
    const category = this.state.category;
    if (field === "visible") {
      category[field] = e.target.value === 'true' ? true : false;
    } else {
      category[field] = e.target.value;
    }

    this.setState({
      category: category
    });
  }

  handleFormSubmit(e) {
    e.preventDefault();
    this.setState({saving: true});

    this.props.createCategory(this.state.category);
  }

  render() {
    return (
      <div className="panel panel-default">
        <div className="panel-heading">
          <h3 className="panel-title">Create Category</h3>
        </div>
        <CategoryForm category={this.state.category} categoryList={this.props.categoryList} onSave={this.handleFormSubmit} onChange={this.handleChange} saving={this.state.saving} disabled={false} />
      </div>
    );
  }
}
