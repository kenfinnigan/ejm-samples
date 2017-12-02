import React from 'react';
import { Router, Route, browserHistory, IndexRoute } from 'react-router';

import App from './pages/App';
import CategoryList from './containers/CategoryListContainer';
import Category from './pages/Category';
import NewCategory from './containers/NewCategoryContainer';

export default (
  <Router history={browserHistory}>
    <Route path="/" component={App}>
      <IndexRoute component={CategoryList} />

      <Route path="/category">
        <IndexRoute component={CategoryList} />
        <Route path="/category/new" component={NewCategory} />
        <Route path="/category/:categoryId" component={Category} />
      </Route>
    </Route>
  </Router>
);
