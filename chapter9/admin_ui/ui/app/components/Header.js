import React, { Component } from 'react';

export default class Header extends Component {
  renderLoginButton() {
    return (
      <li className="dropdown">
        <a className="dropdown-toggle nav-item-iconic" href={this.props.login}>Login</a>
      </li>
    );
  }

  renderUserAccount() {
    return (
      <li className="dropdown">
        <a href="#" className="dropdown-toggle nav-item-iconic" id="dropdownmenu" data-toggle="dropdown">
          <span className="pficon pficon-user"></span>
          {this.props.user} <b className="caret"></b>
        </a>
        <ul className="dropdown-menu" aria-labelledby="dropdownmenu">
          <li>
            <a href={this.props.myAccount}>My Account</a>
          </li>
          <li className="divider"></li>
          <li>
            <a href={this.props.logout}>Logout</a>
          </li>
        </ul>
      </li>
    );
  }

  render() {
    return (
      <nav className="navbar navbar-pf-vertical">
        <div className="navbar-header">
          <button className="navbar-toggle">
            <span className="sr-only">Toggle navigation</span>
            <span className="icon-bar"></span>
            <span className="icon-bar"></span>
            <span className="icon-bar"></span>
          </button>
          <a href="/" className="navbar-brand">
            <span className="navbar-brand-name">Cayambe Admin</span>
          </a>
        </div>
        <div className="collapse navbar-collapse">
          <ul className="nav navbar-nav navbar-right navbar-iconic">
            {this.props.authenticated ? this.renderUserAccount() : this.renderLoginButton()}
          </ul>
        </div>
      </nav>
    );
  }
}
