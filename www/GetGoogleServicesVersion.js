var exec = require('cordova/exec');

function GetGServicesVersion() {
	var self = this;
}

GetGServicesVersion.prototype.isValidVersion = function(version, successCallback, errorCallback) {
	exec(function(result) {
        successCallback(result);
    }, 
    function(error) {
    	errorCallback(error);
        console.log(error);
    }, 'GetGoogleServicesVersion', 'isValidVersion', [version]);
};

GetGServicesVersion.prototype.toString = function() {
	return "GetGoogleServicesVersion ready for action";
};

module.exports = new GetGServicesVersion();
