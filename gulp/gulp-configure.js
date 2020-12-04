"use strict";

const server = {
    path: "./dist/",
    port: 8081,
    proxy: "http://localhost:8080/consumables/"
    },
    paths = {
        clean: {
            static: [
                "./src/main/resources/static/css/*",
                "./src/main/resources/static/js/*"
            ],
            target: [
                "./target/classes/static/*",
                "./target/classes/templates/*",
            ]
        },
        thymeleaf: {
            src: "./src/main/resources/templates/**/*.html",
            target: "./target/classes/templates/",
            watch: "./src/main/resources/templates/**/*.html"
        },
        styles: {
            src: "./src/main/resources/static/styles/style.{scss,sass}",
            static: "./src/main/resources/static/css/",
            target: "./target/classes/static/css/",
            watch: [
                "./src/main/resources/static/blocks/**/*.{scss,sass}",
                "./src/main/resources/static/styles/**/*.{scss,sass}"
            ]
        },
        fonts: {
            src: "./src/main/resources/static/fonts/**/*.{woff,woff2}",
            target: "./target/classes/static/fonts/",
            watch: "./src/main/resources/static/fonts/**/*.{woff,woff2}"
        },
        scripts: {
            src: "./src/main/resources/static/scripts/index.js",
            static: "./src/main/resources/static/js/",
            target: "./target/classes/static/js/",
            watch: [
                "./src/main/resources/static/blocks/**/*.js",
                "./src/main/resources/static/scripts/**/*.js"
            ]
        },
        images: {
            src: "./src/main/resources/static/img/**/*.{jpg,jpeg,png,gif,tiff,svg}",
            target: "./target/classes/static/img/",
            watch: "./src/main/resources/static/img/**/*.{jpg,jpeg,png,gif,tiff,svg}"
        }
    };

export { paths, server };