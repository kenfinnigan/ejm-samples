import React from 'react';
import { connect } from 'react-redux';
import { loginUrl, logoutUrl, myAccountUrl } from '../actions/security-actions';
import Header from '../components/Header.js';

const mapStateToProps = (state, ownProps) => {
  const login = loginUrl();
  const logout = logoutUrl();
  const acct = myAccountUrl();

  return {
    authenticated: state.securityState.authenticated,
    adminRole: state.securityState.adminRole,
    user: state.securityState.user,
    login: login,
    logout: logout,
    myAccount: acct
  };
};

export default connect(mapStateToProps, null)(Header);
