import React from 'react';
import Header from '../containers/HeaderContainer';
import LeftNav from './LeftNav';
import Loading from '../containers/LoadingContainer';
import Notification from '../containers/NotificationContainer';

export default function(props) {
  return (
    <div>
      <Header/>
      <div>
        <LeftNav/>
        <div className="container-fluid container-cards-pf container-pf-nav-pf-vertical container-pf-nav-pf-vertical-with-secondary">
          <div className="row row-cards-pf">
            <div style={{width: 1000}}>
              <Loading/>
              <Notification/>
              {props.children}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
