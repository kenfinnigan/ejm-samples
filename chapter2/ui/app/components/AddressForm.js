import React, { Component } from 'react';

export default class AddressForm extends Component {
  render() {
    return (
      <div className="panel-body">
        <form className="form-horizontal">
          <div className="form-group">
            <label className="col-sm-2 control-label" htmlFor="textInput-markup">First Line</label>
            <div className="col-sm-10">
              <input type="text" name="firstLine" className="form-control" value={this.props.address.firstLine} onChange={this.props.onChange} />
            </div>
          </div>
          <div className="form-group">
            <label className="col-sm-2 control-label" htmlFor="textInput-markup">Second Line</label>
            <div className="col-sm-10">
              <input type="text" name="secondLine" className="form-control" value={this.props.address.secondLine} onChange={this.props.onChange} />
            </div>
          </div>
          <div className="form-group">
            <label className="col-sm-2 control-label" htmlFor="textInput-markup">City</label>
            <div className="col-sm-10">
              <input type="text" name="city" className="form-control" value={this.props.address.city} onChange={this.props.onChange} />
            </div>
          </div>
          <div className="form-group">
            <label className="col-sm-2 control-label" htmlFor="textInput-markup">State</label>
            <div className="col-sm-10">
              <input type="text" name="state" className="form-control" value={this.props.address.state} onChange={this.props.onChange} />
            </div>
          </div>
          <div className="form-group">
            <div className="col-sm-offset-2 col-sm-10">
              <button type="submit" className="btn btn-primary" disabled={this.props.saving} onClick={this.props.onSave}>{this.props.saving ? "Saving..." : "Save"}</button>
            </div>
          </div>
        </form>
      </div>
    );
  }
}
