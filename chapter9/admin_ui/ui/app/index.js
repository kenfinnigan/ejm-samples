import React from 'react';
import { render } from 'react-dom';
import { Provider } from 'react-redux';

import store from './store';
import router from './router';

import KeycloakService from './keycloak-service';

store.getState().securityState.keycloak = new KeycloakService();

store.getState().securityState.keycloak.init()
  .then(() => render(
                <Provider store={store}>{router}</Provider>,
                document.getElementById('app')
              )
  );
