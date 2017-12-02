import { combineReducers } from 'redux';

// Reducers
import categoryReducer from './category-reducer';

// Combine Reducers
var reducers = combineReducers({
    categoryState: categoryReducer
});

export default reducers;
