import * as types from '../actions/action-types';

export function getAddressesSuccess(addresses) {
  return {
    type: types.GET_ADDRESSES_SUCCESS,
    addresses
  };
}

export function getAddressSuccess(address) {
  return {
    type: types.GET_ADDRESS_SUCCESS,
    address
  };
}

export function deleteAddressSuccess(addressId) {
  return {
    type: types.DELETE_ADDRESS_SUCCESS,
    addressId
  };
}
