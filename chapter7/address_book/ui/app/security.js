import Keycloak from 'keycloak-js';

const keycloak = Keycloak('/keycloak.json');

const update = function() {
  keycloak.updateToken(300)
    .success(function(refreshed) {
        if (refreshed) {
            alert('Token was successfully refreshed');
        } else {
            alert('Token is still valid');
        }
    }).error(function() {
        alert('Failed to refresh the token, or the session has expired');
    });
};

keycloak.onTokenExpired = function() {
  alert('onTokenExpired called');
  update();
};

export default keycloak;