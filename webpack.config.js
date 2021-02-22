const path = require("path");
const webpack = require("webpack");

module.exports = {
    entry: {
        "main": "./src/main/resources/static/scripts/index.js",
        "departments": "./src/main/resources/static/scripts/import/pages/departments.js",
        "cartridges": "./src/main/resources/static/scripts/import/pages/cartridges.js",
        "toners": "./src/main/resources/static/scripts/import/pages/toners.js",
        "drums": "./src/main/resources/static/scripts/import/pages/drums.js",
        "magnetic-shafts": "./src/main/resources/static/scripts/import/pages/magnetic-shafts.js",
        "primary-charge-shafts": "./src/main/resources/static/scripts/import/pages/primary-charge-shafts.js",
        "cleaning-blades": "./src/main/resources/static/scripts/import/pages/cleaning-blades.js",
        "dispensing-blades": "./src/main/resources/static/scripts/import/pages/dispensing-blades.js"
    },

    output: {
        filename: "[name].js",
        chunkFilename: "[name].js",
        publicPath: "/"
    },

    optimization: {
        splitChunks: {
            cacheGroups: {
                vendor: {
                    test: /node_modules/,
                    chunks: "initial",
                    name: "vendor",
                    enforce: true
                }
            }
        }
    },

    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: require.resolve("babel-loader"),
                    query: {
                        presets: [
                            ["@babel/preset-env", { modules: false }]
                        ]
                    }
                }
            }
        ]
    },

    resolve: {
        alias: {
            "%blocks%": path.resolve(__dirname, "src/main/resources/static/blocks"),
            "%api%": path.resolve(__dirname, "src/main/resources/static/scripts/import/api"),
            "%config%": path.resolve(__dirname, "src/main/resources/static/scripts/import/config")
        }
    }
};