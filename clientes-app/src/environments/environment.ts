// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
//passando a URL q roda a NOSSA API no BACKEND... pois todas
//comecam com LOCALHOST:8080...
  apiUrlBase: 'http://localhost:8080',

//DECLARANDO os DADOS de ACESSO a APLICACAO no BACKEND...
//para uma aplicacao FROTEND se conectar com uma no BACKEND e nescessario
//q a APLICACAO aplicacao do FRONT tenha um USUARIO e SENHA para se conectar
//com o BACKEND (no caso CLIENTID e o CLIENTSECRET)
 clientId: 'my-angular-app',
 clientSecret: '@321',
 obterTokenUrl: '/oauth/token'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
