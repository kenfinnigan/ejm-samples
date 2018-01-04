import React, { Component } from 'react';
import CategoryContainer from '../containers/CategoryContainer';

export default function(props) {
  return (
    <CategoryContainer id={props.params.categoryId}/>
  );
}
