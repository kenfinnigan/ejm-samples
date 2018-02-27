import React from 'react';
import { connect } from 'react-redux';
import Header from '../components/Header.js';
import store from '../store';

const mapStateToProps = (state, ownProps) => {
  const login = store.getState().securityState.keycloak.auth.loginUrl;
  const logout = store.getState().securityState.keycloak.auth.logoutUrl;
  const acct = store.getState().securityState.keycloak.auth.myAccount;

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
