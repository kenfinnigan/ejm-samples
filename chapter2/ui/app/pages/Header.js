import React from 'react';

export default function(props) {
  return (
    <nav className="navbar navbar-pf-vertical">
      <div className="navbar-header">
        <div className="navbar-toggle">
          <span className="sr-only">Toggle navigation</span>
          <span className="icon-bar"></span>
          <span className="icon-bar"></span>
          <span className="icon-bar"></span>
        </div>
        <a href="/" className="navbar-brand">
          <span className="navbar-brand-name">Cayambe Admin</span>
        </a>
      </div>
    </nav>
  );
}
