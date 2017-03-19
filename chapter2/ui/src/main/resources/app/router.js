import React from 'react';
import { Router, Route, browserHistory, IndexRoute } from 'react-router';

import App from './pages/App';
import AddressList from './containers/AddressListContainer';
import Address from './pages/Address';
import NewAddress from './containers/NewAddressContainer';

export default (
  <Router history={browserHistory}>
    <Route path="/" component={App}>
      <IndexRoute component={AddressList} />

      <Route path="/address">
        <IndexRoute component={AddressList} />
        <Route path="/address/new" component={NewAddress} />
        <Route path="/address/:addressId" component={Address} />
      </Route>
    </Route>
  </Router>
);
