import React, { Component } from 'react';

export default class Loading extends Component {
  render() {
    if (this.props.loading) {
      return <div className="spinner spinner-lg"></div>
    } else {
      return null
    }
  }
}
