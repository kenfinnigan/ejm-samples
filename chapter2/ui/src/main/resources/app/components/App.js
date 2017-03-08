import React from 'react';
import Header from './Header';
import LeftNav from './LeftNav';

export default function(props) {
  return (
    <div>
      <Header/>
      <div>
        <LeftNav/>
        <div className="container-fluid container-cards-pf container-pf-nav-pf-vertical container-pf-nav-pf-vertical-with-secondary">
          <div className="row row-cards-pf">
            <div style={{width: 800}}>
              {props.children}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
