import React, { Component } from 'react';

export default class Notification extends Component {
  render() {
    const { error, success, header, content } = this.props.notification;

    if (!error && !success) {
      return null;
    }

    var alertType = error ? "alert-danger"
                  : success ? "alert-success"
                  : "";

    var iconType = error ? "pficon-error-circle-o"
                  : success ? "pficon-ok"
                  : "";

    return (
      <div className={"alert alert-dismissable " + alertType}>
        <button type="button" className="close" data-dismiss="alert" aria-hidden="true">
          <span className="pficon pficon-close"></span>
        </button>
        <span className={"pficon " + iconType}></span>
        <span><strong>{header}: </strong> {content}</span>
      </div>
    );
  }
}
