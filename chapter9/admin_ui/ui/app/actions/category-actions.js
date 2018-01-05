import * as types from '../actions/action-types';
import { browserHistory } from 'react-router';
import axios from 'axios';

import store from '../store';

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

function convertTreeToList(categoryRoot) {
  if (categoryRoot === null) {
    return null;
  }

  const catList = [];

  const buildList = function(categoryNode) {
    catList.push(categoryNode);

    if (categoryNode.children) {
      categoryNode.children.forEach(node => {
        catList.push(buildList(node));
      });
    }

  };

  buildList(categoryRoot);

  const trimList = catList.filter(cat => {
    return cat !== undefined;
  });

  return trimList;
}

export function getCategories() {
  return dispatch => {
    dispatch(loading(true));

    axios.get(`${ROOT_URL}/admin/categorytree`)
      .then(response => {
        dispatch(loading(false));
        dispatch(getCategoriesSuccess(convertTreeToList(response.data)));
      })
      .catch(error => {
        dispatch(loading(false));

        if (error.response) {
          dispatch(notifyError("Failed to retrieve categories", error.response.data));
        } else {
          dispatch(notifyError("Failed to retrieve categories", error.message));
        }
      });
  };
}

export function getCategoriesSuccess(categories) {
  return {
    type: types.GET_CATEGORIES_SUCCESS,
    payload: categories
  };
}


export function createCategory(props) {
  return dispatch => {
    dispatch(loading(true));
    axios.post(`${ROOT_URL}/admin/category`, props)
      .then(response => {
        dispatch(loading(false));
        browserHistory.push('/category');
        dispatch(resetActiveCategory());
        dispatch(notifySuccessWithTimeout("Category successfully created", props.name, 3500));
      })
      .catch(error => {
        dispatch(loading(false));
        if (error.response) {
          dispatch(notifyError("Failed to create category", error.response.data));
        } else {
          dispatch(notifyError("Failed to create category", error.message));
        }
      });
  };
}

export function createCategorySuccess(newCategory) {
  return {
    type: types.CREATE_CATEGORY_SUCCESS,
    payload: newCategory
  };
}

export function resetCreatedCategory() {
  return {
    type: types.RESET_NEW_CATEGORY
  }
}


export function getCategory(id) {
  return dispatch => {
    axios.get(`${ROOT_URL}/admin/category/${id}`)
      .then(response => {
        dispatch(getCategorySuccess(response.data));
      })
      .catch(error => {
        if (error.response) {
          dispatch(notifyError("Failed to retrieve category with id " + id, error.response.data));
        } else {
          dispatch(notifyError("Failed to retrieve category with id " + id, error.message));
        }
      });
  };
}

export function getCategorySuccess(activeCategory) {
  return {
    type: types.GET_CATEGORY_SUCCESS,
    payload: activeCategory
  };
}

export function resetActiveCategory() {
  return {
    type: types.RESET_ACTIVE_CATEGORY
  }
}


export function updateCategory(category) {
  return dispatch => {
    dispatch(loading(true));
    axios.put(`${ROOT_URL}/admin/category/${category.id}`, category)
      .then(response => {
        dispatch(loading(false));
        browserHistory.push('/category');
        dispatch(resetActiveCategory());
        dispatch(notifySuccessWithTimeout("Category successfully updated", category.name, 3500));
      })
      .catch(error => {
        dispatch(loading(false));
        if (error.response) {
          dispatch(notifyError("Failed to update category", error.response.data));
        } else {
          dispatch(notifyError("Failed to update category", error.message));
        }
      });
  };
}


export function deleteCategory(id) {
  return dispatch => {
    if (store.getState().securityState.authenticated) {
      store.getState().securityState.keycloak.getToken()
        .then(token => {
          axios.delete(`${ROOT_URL}/admin/category/${id}`, {
            headers: {
              'Authorization': 'Bearer ' + token
            }
          })
            .then(response => {
              dispatch(deleteCategorySuccess(response.data));
              dispatch(notifySuccessWithTimeout("Successfully deleted category", response.data, 3500));
            })
            .catch(error => {
              if (error.response) {
                dispatch(notifyError("Failed to delete category", error.response.data));
              } else {
                dispatch(notifyError("Failed to delete category", error.message));
              }
            });
        })
        .catch(error => {
          dispatch(notifyError("Error updating token", error));
        });
    } else {
      dispatch(notifyError("User is not authenticated", ""));
    }
  };
}

export function deleteCategorySuccess(deletedCategory) {
  return {
    type: types.DELETE_CATEGORY_SUCCESS,
    payload: deletedCategory
  };
}
