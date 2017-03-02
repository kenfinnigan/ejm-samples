import React, { Component } from 'react';

export default class Header extends Component {
  render() {
    return (
      <nav className="navbar navbar-pf-vertical">
        <div className="navbar-header">
          <div className="navbar-toggle">
            <span className="sr-only">Toggle navigation</span>
            <span className="icon-bar"></span>
            <span className="icon-bar"></span>
            <span className="icon-bar"></span>
          </div>
          <a href="/" className="navbar-brand">
            <span className="navbar-brand-name">Address Database</span>
          </a>
        </div>
      </nav>
    );
  }
}
