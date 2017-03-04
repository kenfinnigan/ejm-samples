import React from 'react';
import { Router, Route, browserHistory, IndexRoute } from 'react-router';

import App from './components/App';
import AddressListContainer from './components/AddressListContainer';

export default (
  <Router history={ browserHistory }>
    <Route path="/" component={ App }>
      <IndexRoute component={ AddressListContainer }/>

      <Route path="/addresses" component={ AddressListContainer }>
      </Route>
    </Route>
  </Router>
);
