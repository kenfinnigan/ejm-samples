import * as types from '../actions/action-types';
import { browserHistory } from 'react-router';
import security from '../security';

export function loginUrl() {
  return security.createLoginUrl();
}

export function logoutUrl() {
  return security.createLogoutUrl();
}

export function myAccountUrl() {
  return security.createAccountUrl();
}
