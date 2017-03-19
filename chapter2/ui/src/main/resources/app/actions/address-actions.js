import * as types from '../actions/action-types';
import { browserHistory } from 'react-router';
import axios from 'axios';

const ROOT_URL = 'http://localhost:8081';

export function loading(bool) {
  return {
    type: types.LOADING,
    payload: bool
  };
}

export function notifySuccess(header, content) {
  return {
    type: types.NOTIFY_SUCCESS,
    payload: {
      header: header,
      content: content
    }
  };
}

export function notifySuccessWithTimeout(header, content, timeout) {
  return dispatch => {
    dispatch(notifySuccess(header, content));

    setTimeout(() => {
      dispatch(notifyReset());
    }, timeout);
  };
}

export function notifyError(header, content) {
  return {
    type: types.NOTIFY_ERROR,
    payload: {
      header: header,
      content: content
    }
  };
}

export function notifyErrorWithTimeout(header, content, timeout) {
  return dispatch => {
    dispatch(notifyError(header, content));

    setTimeout(() => {
      dispatch(notifyReset());
    }, timeout);
  };
}

export function notifyReset() {
  return {
    type: types.NOTIFY_RESET
  };
}

export function formatAddress(address) {
  var output = '';

  if (address.firstLine) {
    output += address.firstLine + ', ';
  }

  if (address.secondLine) {
    output += address.secondLine + ', ';
  }

  if (address.city) {
    output += address.city + ', ';
  }

  if (address.state) {
    output += address.state;
  }

  return output;
}

export function getAddresses() {
  return dispatch => {
    dispatch(loading(true));

    axios.get(`${ROOT_URL}/address`)
      .then(response => {
        dispatch(loading(false));
        dispatch(getAddressesSuccess(response.data));
      })
      .catch(error => {
        dispatch(loading(false));

        if (error.response) {
          dispatch(notifyError("Failed to retrieve addresses", error.response.data));
        } else {
          dispatch(notifyError("Failed to retrieve addresses", error.message));
        }
      });
  };
}

export function getAddressesSuccess(addresses) {
  return {
    type: types.GET_ADDRESSES_SUCCESS,
    payload: addresses
  };
}


export function createAddress(props) {
  return dispatch => {
    dispatch(loading(true));
    axios.post(`${ROOT_URL}/address`, props)
      .then(response => {
        dispatch(loading(false));
        browserHistory.push('/address');
        dispatch(resetActiveAddress());
        dispatch(notifySuccessWithTimeout("Address successfully created", formatAddress(response.data), 3500));
      })
      .catch(error => {
        if (error.response) {
          dispatch(notifyError("Failed to create address", error.response.data));
        } else {
          dispatch(notifyError("Failed to create address", error.message));
        }
      });
  };
}

export function createAddressSuccess(newAddress) {
  return {
    type: types.CREATE_ADDRESS_SUCCESS,
    payload: newAddress
  };
}

export function resetCreatedAddress() {
  return {
    type: types.RESET_NEW_ADDRESS
  }
}


export function getAddress(id) {
  return dispatch => {
    axios.get(`${ROOT_URL}/address/${id}`)
      .then(response => {
        dispatch(getAddressSuccess(response.data));
      })
      .catch(error => {
        if (error.response) {
          dispatch(notifyError("Failed to retrieve address with id " + id, error.response.data));
        } else {
          dispatch(notifyError("Failed to retrieve address with id " + id, error.message));
        }
      });
  };
}

export function getAddressSuccess(activeAddress) {
  return {
    type: types.GET_ADDRESS_SUCCESS,
    payload: activeAddress
  };
}

export function resetActiveAddress() {
  return {
    type: types.RESET_ACTIVE_ADDRESS
  }
}


export function updateAddress(address) {
  return dispatch => {
    dispatch(loading(true));
    axios.put(`${ROOT_URL}/address/${address.id}`, address)
      .then(response => {
        dispatch(loading(false));
        browserHistory.push('/address');
        dispatch(resetActiveAddress());
        dispatch(notifySuccessWithTimeout("Address successfully updated", formatAddress(address), 3500));
      })
      .catch(error => {
        if (error.response) {
          dispatch(notifyError("Failed to update address", error.response.data));
        } else {
          dispatch(notifyError("Failed to update address", error.message));
        }
      });
  };
}


export function deleteAddress(id) {
  return dispatch => {
    axios.delete(`${ROOT_URL}/address/${id}`)
      .then(response => {
        dispatch(deleteAddressSuccess(response.data));
        dispatch(notifySuccessWithTimeout("Successfully deleted address", formatAddress(response.data), 3500));
      })
      .catch(error => {
        if (error.response) {
          dispatch(notifyError("Failed to delete address", error.response.data));
        } else {
          dispatch(notifyError("Failed to delete address", error.message));
        }
      });
  };
}

export function deleteAddressSuccess(deletedAddress) {
  return {
    type: types.DELETE_ADDRESS_SUCCESS,
    payload: deletedAddress
  };
}
