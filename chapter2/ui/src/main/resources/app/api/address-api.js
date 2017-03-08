import axios from 'axios';
import store from '../store';
import { getAddressesSuccess, getAddressSuccess, deleteAddressSuccess } from '../actions/address-actions';

/**
 * Get Addresses
 */

export function getAddresses() {
  return axios.get('http://localhost:8081/address')
    .then(response => {
      store.dispatch(getAddressesSuccess(response.data));
      return response.data;
    })
    .catch(error => handleError(error));
}

/**
 * Get Address
 */

export function getAddress(addressId) {
  return axios.get('http://localhost:8081/address/' + addressId)
    .then(response => {
      store.dispatch(getAddressSuccess(response.data));
      return response.data;
    })
    .catch(error => handleError(error));
}

/**
 * Delete an address
 */

export function deleteAddress(addressId) {
  return axios.delete('http://localhost:8081/address/' + addressId)
    .then(response => {
      store.dispatch(deleteAddressSuccess(addressId));
      return response;
    })
    .catch(error => handleError(error));
}

/**
 * Add an address
 */

export function addAddress() {

}

/**
 * Update address
 */

export function updateAddress() {

}

function handleError(error) {
  if (error.response) {
    // Server Error
    console.log(error.response.data);
    console.log(error.response.status);
    console.log(error.response.headers);
  } else {
    // Error making request
    console.log('Error creating request', error.message);
  }
}
