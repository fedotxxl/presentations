define("log", ["log.debug"], function(logDebug) {
    return {
        debug: function() {
            if (logDebug) {
                console.log(arguments);
            } else {
                console.warn("Debugging is disabled!")
            }
        }
    }
});