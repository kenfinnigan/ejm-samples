import React, { Component } from 'react';

export default class CategoryForm extends Component {
  componentDidUpdate() {
    $(this.refs.parentSelect).selectpicker();
  }

  createCategoryOption(category) {
    return (<option value={category.id} key={category.id}>{category.name}</option>);
  }

  render() {
    return (
      <div className="panel-body">
        <form className="form-horizontal">
          <div className="form-group">
            <label className="col-sm-2 control-label" htmlFor="textInput-markup">Category Name</label>
            <div className="col-sm-10">
              <input type="text" name="name" className="form-control" disabled={this.props.disabled} value={this.props.category.name} onChange={this.props.onChange} />
            </div>
          </div>
          <div className="form-group">
            <label className="col-sm-2 control-label" htmlFor="textInput-markup">Header</label>
            <div className="col-sm-10">
              <input type="text" name="header" className="form-control" disabled={this.props.disabled} value={this.props.category.header} onChange={this.props.onChange} />
            </div>
          </div>
          <div className="form-group">
            <label className="col-sm-2 control-label" htmlFor="textInput-markup">Image Path</label>
            <div className="col-sm-10">
              <input type="text" name="imagePath" className="form-control" disabled={this.props.disabled} value={this.props.category.imagePath} onChange={this.props.onChange} />
            </div>
          </div>
          <div className="form-group">
            <label className="col-sm-2 control-label" htmlFor="textInput-markup">Visible</label>
            <div className="col-sm-10">
              <div className="form-check">
                <label className="form-check-label">
                  <input type="radio" className="form-check-input" name="visible" disabled={this.props.disabled} value={true} checked={this.props.category.visible === true} onChange={this.props.onChange} />
                  True
                </label>
              </div>
              <div className="form-check">
                <label className="form-check-label">
                  <input type="radio" className="form-check-input" name="visible" disabled={this.props.disabled} value={false} checked={this.props.category.visible === false} onChange={this.props.onChange} />
                  False
                </label>
              </div>
            </div>
          </div>
          <div className="form-group">
            <label className="col-sm-2 control-label" htmlFor="textInput-markup">Parent Category</label>
            <div className="col-sm-10">
              <select ref="parentSelect" name="parent" disabled={this.props.disabled} value={this.props.category.parent === null ? '' : this.props.category.parent.id} onChange={this.props.onChange}>
                <option value="" key="empty">None</option>
                {this.props.categoryList.map(this.createCategoryOption)}
              </select>
            </div>
          </div>
          <div className="form-group">
            <div className="col-sm-offset-2 col-sm-10">
              <button type="submit" className="btn btn-primary" disabled={this.props.disabled || this.props.saving} onClick={this.props.onSave}>{this.props.saving ? "Saving..." : "Save"}</button>
            </div>
          </div>
        </form>
      </div>
    );
  }
}
