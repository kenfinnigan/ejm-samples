import React from 'react';
import { render } from 'react-dom';
import { Router, Route, browserHistory } from 'react-router';

import App from './components/App';
import Addresses from './components/Addresses';

render(
  <Router history={ browserHistory }>
    <Route component={ App }>
      <Route path="/" component={ Addresses }/>
      <Route path="/addresses" component={ Addresses }/>
    </Route>
  </Router>,
  document.getElementById('app')
);
