import React, { Component } from 'react';
import Header from './Header';
import LeftNav from './LeftNav';

export default class App extends Component {
  render() {
    return (
      <div>
        <Header/>
        <div>
          <LeftNav/>
          <div className="container-fluid container-cards-pf container-pf-nav-pf-vertical container-pf-nav-pf-vertical-with-secondary">
            <div className="row row-cards-pf">
              {this.props.children}
            </div>
          </div>
        </div>
      </div>
    );
  }
}
