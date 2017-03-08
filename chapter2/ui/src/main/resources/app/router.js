import React from 'react';
import { Router, Route, browserHistory, IndexRoute } from 'react-router';

import App from './components/App';
import AddressListContainer from './components/AddressListContainer';
import AddressContainer from './components/AddressContainer';

export default (
  <Router history={ browserHistory }>
    <Route path="/" component={ App }>
      <IndexRoute component={ AddressListContainer } />

      <Route path="addresses">
        <IndexRoute component={ AddressListContainer } />
        <Route path=":addressId" component={ AddressContainer } />
      </Route>
    </Route>
  </Router>
);
