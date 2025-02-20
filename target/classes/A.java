window.onload = function() {
    const ui = SwaggerUIBundle({
        url: "/v3/api-docs",
        dom_id: "#swagger-ui",
        presets: [SwaggerUIBundle.presets.apis, SwaggerUIStandalonePreset],
        layout: "StandaloneLayout",
        requestInterceptor: (req) => {
            if (req.method === "post" || req.method === "put") {
                fetch(`/payload/${req.url.split("/").pop()}`)
                    .then(response => response.json())
                    .then(data => {
                        if (Object.keys(data).length > 0) {
                            req.body = JSON.stringify(data);
                        }
                    })
                    .catch(error => console.error("Error loading saved payload:", error));
            }
            return req;
        }
    });

    window.ui = ui;
};
