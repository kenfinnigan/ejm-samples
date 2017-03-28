import { combineReducers } from 'redux';

// Reducers
import addressReducer from './address-reducer';
import securityReducer from './security-reducer';

// Combine Reducers
var reducers = combineReducers({
    addressState: addressReducer,
    securityState: securityReducer
});

export default reducers;
