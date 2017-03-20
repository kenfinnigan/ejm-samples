import * as types from '../actions/action-types';
import _ from 'lodash';

const INITIAL_STATE = {
  loading: false,
  notification: {error: false, success: false, header: null, content: null},
  addressList: [],
  newAddress: null,
  activeAddress: null,
};

export default function(state = INITIAL_STATE, action) {

  switch(action.type) {

    case types.LOADING:
      return { ...state, loading: action.payload };
    case types.NOTIFY_SUCCESS:
      return { ...state, notification: {error: false, success: true, header: action.payload.header, content: action.payload.content} };
    case types.NOTIFY_ERROR:
      return { ...state, notification: {error: true, success: false, header: action.payload.header, content: action.payload.content} };
    case types.NOTIFY_RESET:
      return { ...state, notification: {error: false, success: false, header: null, content: null} };

    case types.GET_ADDRESSES_SUCCESS:
      return { ...state, addressList: action.payload };


    case types.GET_ADDRESS_SUCCESS:
      return { ...state, activeAddress: action.payload };
    case types.RESET_ACTIVE_ADDRESS:
      return { ...state, activeAddress: null };


    case types.CREATE_ADDRESS_SUCCESS:
    	return {...state, newAddress: action.payload }
    case types.RESET_NEW_ADDRESS:
    	return {...state, newAddress: null }


    case types.DELETE_ADDRESS_SUCCESS:
      // Use lodash to create a new address array without the address we want to remove
      const newAddresses = _.filter(state.addressList, address => address.id != action.payload.id);
    	return {...state, addressList: newAddresses }
  }

  return state;
}
