import Keycloak from 'keycloak-js';
import store from './store';

export default class KeycloakService {

  auth = {
    authz: null,
    loginUrl: null,
    myAccount: null,
    updateId: null,
  };

  init() {
    const keycloakAuth = Keycloak('/keycloak.json');

    return new Promise((resolve, reject) => {
      keycloakAuth.init({ onLoad: 'check-sso' })
        .success((authenticated) => {
          this.auth.authz = keycloakAuth;
          this.auth.loginUrl = keycloakAuth.createLoginUrl();

          if (authenticated) {
            this.auth.myAccount = keycloakAuth.createAccountUrl();
            store.getState().securityState.authenticated = true;
            store.getState().securityState.adminRole = keycloakAuth.hasRealmRole('admin');

            this.auth.updateId = setInterval(() => {
              keycloakAuth.updateToken(15)
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
            }, 10000);

            if (keycloakAuth.idToken) {
              store.getState().securityState.user = keycloakAuth.idTokenParsed.name;
            } else {
              keycloakAuth.loadUserProfile(
                () => {
                  store.getState().securityState.user = keycloakAuth.profile.firstName + ' ' + keycloakAuth.profile.lastName;
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

  logout() {
    clearInterval(this.auth.updateId);
    this.auth.myAccount = null;
    this.auth.updateId = null;
    window.location.href = keycloakAuth.logout();
  }

  getToken() {
    return new Promise((resolve, reject) => {
      if (typeof this.auth.authz.token !== 'undefined') {
        this.auth.authz
          .updateToken()
          .success(() => {
            resolve(this.auth.authz.token);
          })
          .error(() => {
            reject('Failed to refresh token');
          });
      } else {
        reject('No token found');
      }
    });
  }
}