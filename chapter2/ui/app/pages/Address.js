import React, { Component } from 'react';
import AddressContainer from '../containers/AddressContainer';

export default function(props) {
  return (
    <AddressContainer id={props.params.addressId}/>
  );
}
