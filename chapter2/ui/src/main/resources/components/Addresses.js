import React, { Component } from 'react';

export default class Addresses extends Component {
  render() {
    return (
      <div>
        <h1>Addresses</h1>
        <table id="addressTable" className="datatable table table-striped table-bordered">
          <thead>
            <tr>
              <th>First Line</th>
              <th>Second Line</th>
              <th>City</th>
              <th>State</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
          </tbody>
        </table>
      </div>
    );
  }
}
