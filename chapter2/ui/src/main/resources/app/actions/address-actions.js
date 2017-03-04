import * as types from '../actions/action-types';

export function getAddressesSuccess(addresses) {
  return {
    type: types.GET_ADDRESSES_SUCCESS,
    addresses
  };
}

export function deleteAddressSuccess(addressId) {
  return {
    type: types.DELETE_ADDRESS_SUCCESS,
    addressId
  };
}
