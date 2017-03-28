import React from 'react';
import { render } from 'react-dom';
import { Provider } from 'react-redux';

import store from './store';
import router from './router';

import security from './security';

security.init({ onLoad: 'check-sso' })
  .success(authenticated => {
    store.getState().securityState.keycloak = security;

    render(
      <Provider store={store}>{router}</Provider>,
      document.getElementById('app')
    );

    if (authenticated) {
      store.getState().securityState.authenticated = true;

      if (security.idToken) {
        store.getState().securityState.user = security.idTokenParsed.name;
      } else {
        security.loadUserProfile(
          () => {
            store.getState().securityState.user = security.profile.firstName + ' ' + keycloak.profile.lastName;
          },
          () => {
          }
        );
      }
    }
  });
