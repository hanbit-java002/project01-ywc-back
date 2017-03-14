require.config({
    baseUrl: "/static",
    paths: {
        "async"         : "plugins/requirejs/async",
        "jquery"        : "plugins/jquery/jquery.min",
        "bootstrap"     : "plugins/bootstrap/js/bootstrap.min",
        "clipboard"     : "plugins/clipboard/clipboard.min",
		
        "common"        : "/js/common",
    },
    shim: {
        "bootstrap": {
            deps: ["jquery"],
        },
    },
    deps: [],
});

