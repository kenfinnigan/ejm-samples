import React, { Component } from 'react';

export default class Address extends Component {
  constructor(props) {
    super(props);

    this.handleFormSubmit = this.handleFormSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(e) {
    const value = e.target.value;
    const name = e.target.name;

    this.setState({
      [name]: value
    });
  }

  handleFormSubmit(e) {
    e.preventDefault();

    // Submit update POST
  }

  render() {
    return (
      <div className="panel panel-default">
        <div className="panel-heading">
          <h3 className="panel-title">Address Detail</h3>
        </div>
        <div className="panel-body">
          <form className="form-horizontal" onSubmit={this.handleFormSubmit}>
            <div className="form-group">
              <label className="col-sm-2 control-label" htmlFor="textInput-markup">First Line</label>
              <div className="col-sm-10">
                <input type="text" name="firstLine" className="form-control" value={this.props.firstLine} onChange={this.handleChange} />
              </div>
            </div>
            <div className="form-group">
              <label className="col-sm-2 control-label" htmlFor="textInput-markup">Second Line</label>
              <div className="col-sm-10">
                <input type="text" name="secondLine" className="form-control" value={this.props.secondLine} onChange={this.handleChange} />
              </div>
            </div>
            <div className="form-group">
              <label className="col-sm-2 control-label" htmlFor="textInput-markup">City</label>
              <div className="col-sm-10">
                <input type="text" name="city" className="form-control" value={this.props.city} onChange={this.handleChange} />
              </div>
            </div>
            <div className="form-group">
              <label className="col-sm-2 control-label" htmlFor="textInput-markup">State</label>
              <div className="col-sm-10">
                <input type="text" name="state" className="form-control" value={this.props.state} onChange={this.handleChange} />
              </div>
            </div>
            <div className="form-group">
              <div className="col-sm-offset-2 col-sm-10">
                <button type="submit" className="btn btn-primary">Primary Action</button>
                <span>&nbsp;&nbsp;</span>
                <button type="submit" className="btn btn-default">Secondary Action</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    );
  }
}
