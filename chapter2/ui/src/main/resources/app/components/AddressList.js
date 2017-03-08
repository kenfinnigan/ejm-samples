import React from 'react';
import { Link } from 'react-router';

export default function(props) {
  return (
    <div>
      <h1>Addresses</h1>
      <table id="addressTable" className="table table-striped table-bordered table-hover">
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
          {props.addresses.map(address => {
            return (
              <tr key={address.id}>
                <td>{address.firstLine}</td>
                <td>{address.secondLine}</td>
                <td>{address.city}</td>
                <td>{address.state}</td>
                <td>
                  <div className="table-view-pf-btn">
                    <Link className="btn btn-info" to={'/addresses/' + address.id}>Details</Link>
                    <span>&nbsp;&nbsp;</span>
                    <button className="btn btn-danger" onClick={props.deleteAddress.bind(null, address.id)}>Delete</button>
                  </div>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
}
