
module.exports = {
    root: true,
    modules: {
        "bem-tools": {
            plugins: {
                create: {
                    templateFolder: './bem-templates',
                    templates: {
                        'js-modules': './bem-templates/js',
                        'css-modules': './bem-templates/css',
                        'html-modules': './bem-templates/html'
                    },
                    techsTemplates: {
                        html: 'html-modules',
                        js: 'js-modules',
                        css: 'css-modules',
                        scss: 'css-modules'
                    },
                    techs: ["html", "scss", "js"],
                    levels: [
                        {
                            path: "src/main/resources/static/blocks/modules",
                            techs: ["js", "scss"],
                            default: true
                        },
                        {
                            path: "src/main/resources/templates/blocks",
                            techs: "html",
                            default: true
                        }
                    ]
                }
            }
        }
    }
};