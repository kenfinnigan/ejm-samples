import { combineReducers } from 'redux';

// Reducers
import addressReducer from './address-reducer';

// Combine Reducers
var reducers = combineReducers({
    addressState: addressReducer
});

export default reducers;
