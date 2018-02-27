import { combineReducers } from 'redux';

// Reducers
import categoryReducer from './category-reducer';
import securityReducer from './security-reducer';

// Combine Reducers
var reducers = combineReducers({
    categoryState: categoryReducer,
    securityState: securityReducer
});

export default reducers;
