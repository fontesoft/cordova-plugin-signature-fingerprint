# Cordova Signature Fingerprint Plugin

Get the fingerprint signature of your app's certificate and its package name.

Especially useful for adding yet another layer of security in your API calls.

## Installation

### With cordova-cli

If you are using [cordova-cli](https://github.com/apache/cordova-cli), install with:

```
$ cordova plugin add cordova-plugin-signature-fingerprint
```

### With plugman

With a plain [plugman](https://github.com/apache/cordova-plugman), you should be
able to install with something like:

```
$ plugman --platform <ios|android> --project <directory> --plugin https://github.com/fontesoft/cordova-plugin-signature-fingerprint.git
```

## Supported Platforms

- Android

## Methods

- cordova.getSignatureFingerprint.getSignature
- cordova.getSignatureFingerprint.getPackageName

### cordova.getSignatureFingerprint.getSignature

Return SHA1 Fingerprint of the app certificate

    cordova.getSignatureFingerprint.getSignature(successCallback, [errorCallback]);

#### Parameters

- successCallback: The callback that is passed fingerprint
- errorCallback: (Optional) The callback that executes if an error occurs.

#### Example

```javascript

    // successCallback
    var onSuccess = function(sha1) {
      alert("SHA1: " + sha1);
    }

    // errorCallback
    var onError = function(error) {
      alert("ERROR: " + error);
    }

    cordova.getSignatureFingerprint.getSignature(onSuccess, onError);

```

### cordova.getSignatureFingerprint.getPackageName

Returns the package name of the app

    cordova.getSignatureFingerprint.getPackageName(successCallback, [errorCallback]);

#### Parameters

- successCallback: The callback that is passed fingerprint
- errorCallback: (Optional) The callback that executes if an error occurs.

#### Example

```javascript

    // successCallback
    var onSuccess = function(name) {
      alert("Package Name: " + name);
    }

    // errorCallback
    var onError = function(error) {
      alert("ERROR: " + error);
    }

    cordova.getSignatureFingerprint.getPackageName(onSuccess, onError);

```

## License

The Cordova Signature Fingerprint Plugin is open-sourced software licensed under the [MIT license](http://opensource.org/licenses/MIT).
