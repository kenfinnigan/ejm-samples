import * as types from '../actions/action-types';
import _ from 'lodash';

const INITIAL_STATE = {
  loading: false,
  notification: {error: false, success: false, header: null, content: null},
  categoryList: [],
  newCategory: null,
  activeCategory: null,
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

    case types.GET_CATEGORIES_SUCCESS:
      return { ...state, categoryList: action.payload };


    case types.GET_CATEGORY_SUCCESS:
      return { ...state, activeCategory: action.payload };
    case types.RESET_ACTIVE_CATEGORY:
      return { ...state, activeCategory: null };


    case types.CREATE_CATEGORY_SUCCESS:
    	return {...state, newCategory: action.payload }
    case types.RESET_NEW_CATEGORY:
    	return {...state, newCategory: null }


    case types.DELETE_CATEGORY_SUCCESS:
    	return {...state, categoryList: [] }
  }

  return state;
}
