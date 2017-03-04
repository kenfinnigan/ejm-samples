import * as types from '../actions/action-types';
import _ from 'lodash';

const initialState = {
  addresses: []
};

const addressReducer = function(state = initialState, action) {

  switch(action.type) {

    case types.GET_ADDRESSES_SUCCESS:
      return Object.assign({}, state, { addresses: action.addresses });

    case types.DELETE_ADDRESS_SUCCESS:

      // Use lodash to create a new address array without the address we want to remove
      const newAddresses = _.filter(state.addresses, address => address.id != action.addressId);
      return Object.assign({}, state, { addresses: newAddresses });

  }

  return state;

}

export default addressReducer;
