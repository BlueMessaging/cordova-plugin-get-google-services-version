# cordova-plugin-get-google-services-version

## Descripción
Plugin que permite evaluar la versión de Google Services, debido a que Firebase usa la versión 11.+, éste requiere una versión mínima de GS: 12211000. Por lo tanto, el presente plugin ayuda saber si la versión de GS del teléfono cumple con el requerido por SF.

Al plugin se le debe de especificar el núemro de versión de Google Services que se desea evaluar.

3 atributos en forma de arreglo son regresados al momento de realizar la petición:
* [0] - "true" si la versión de Google services del teléfono es igual o mayor a la especificada, "false" si la versión es menor
* [1] - Es el número de versión de Google services del teléfono
* [2] - El el número de versión de Google services que se especificó

## Implementación

La implementación del plugin se realiza vía callback, por ejemplo:

```javascript
	// minimum Google Services Version required to work with SF
  var minumumGSVersion = 12211000;

  var success = function(result) {
      var googleServicesVersion = {
          "isValidVersion": result[0],
          "mobileVersion": result[1],
          "minumumVersionReq" : result[2]
      };
      if (googleServicesVersion.isValidVersion == "false") {
          //do something
      }
  };

  var reject = function(e) {
      console.log(e);
  };

  // Set minimum Google Sevices Version, if mobile's version is ok, 'isValidVersion' method returns 'true', otherwise 'false' is returned
  window.GetGoogleServicesVersion.isValidVersion(minumumGSVersion, success, reject);
```
