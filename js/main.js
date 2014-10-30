(function() {
    (function configRequireJs() {
        require.config({
            baseUrl: "/js/amd",
            waitSeconds: 90
        });
    }());

    require(["log"], function(log) {
        log.debug("Hello world!");
    });
}());