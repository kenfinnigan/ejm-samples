import Keycloak from 'keycloak-js';
import store from './store';

export default class KeycloakService {

  auth = {
    authz: null,
    loginUrl: null,
    logoutUrl: null,
    myAccount: null,
    updateId: null,
  };

  init() {
    const keycloakAuth = Keycloak('/keycloak.json');

    return new Promise((resolve, reject) => {
      keycloakAuth.init({ onLoad: 'check-sso' })
        .success((authenticated) => {
          this.auth.authz = keycloakAuth;
          this.auth.loginUrl = this.auth.authz.createLoginUrl();

          if (authenticated) {
            this.auth.myAccount = this.auth.authz.createAccountUrl();
            this.auth.logoutUrl = this.auth.authz.createLogoutUrl();
            store.getState().securityState.authenticated = true;
            store.getState().securityState.adminRole = this.auth.authz.hasRealmRole('admin');

            this.auth.updateId = setInterval(() => {
              this.auth.authz.updateToken()
                .success((refreshed) => {
                  if (refreshed) {
                    console.log("Token refreshed");
                  } else {
                    console.log("Token not refreshed, still valid");
                  }
                })
                .error(() => {
                  console.log("Failed to refresh token");
                });
            }, 5000);

            if (this.auth.authz.idToken) {
              store.getState().securityState.user = this.auth.authz.idTokenParsed.name;
            } else {
              keycloakAuth.loadUserProfile(
                () => {
                  store.getState().securityState.user = this.auth.authz.profile.firstName + ' ' + this.auth.authz.profile.lastName;
                },
                () => {
                  resolve();
                }
              );
            }
          }
          resolve();
        })
        .error(() => {
          reject();
        });
    });
  }

  getToken() {
    return new Promise((resolve, reject) => {
      if (this.auth.authz.token) {
        resolve(this.auth.authz.token);
      } else {
        reject('No token found');
      }
    });
  }
}